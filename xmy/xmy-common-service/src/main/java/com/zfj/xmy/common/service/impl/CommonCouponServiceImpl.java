package com.zfj.xmy.common.service.impl;  

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.CouponPickMapper;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponPick;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.app.AppCouponInDto;
import com.zfj.xmy.common.persistence.pojo.common.couponReceiveByPhoneDto;
import com.zfj.xmy.common.service.CommonCouponService;
import com.zfj.xmy.redis.TokenManager;
import com.zfj.xmy.util.SendSMSUtil;

/** 
 * @Title: CommonCouponServiceImpl.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年11月22日 下午4:23:40 
 */
@Service
public class CommonCouponServiceImpl extends BaseService implements CommonCouponService{
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private CouponPickMapper couponPickMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void toReceiveCoupon(AppCouponInDto coupon,boolean canGetForNotShowInCouponCenter) {
		
		Long couponTypeId = coupon.getId(); //优惠劵类别id
		Long userId = coupon.getUserId();//用户id
		
		if (ObjectUtil.isNull(couponTypeId) ) {
			throw new BusinessException("优惠劵类别id不能为空");
		}
		if (ObjectUtil.isNull(userId) ) {
			throw new BusinessException("用户未登录");
		}
		
		CriteriaParameter param = new CriteriaParameter();
		Criteria criteria = param.createCriteria();
		
		//1. 查询之前是否领取了这个优惠券
		
		
		criteria.equalTo("user_id", coupon.getUserId());
		criteria.equalTo("coupon_id", coupon.getId());
		List<CouponUser> couponUsers = couponUserMapper.selectByExample(param);
		if (!CollectionUtils.isEmpty(couponUsers)) {
			throw new BusinessException("您已经领取了一次,请勿重复领取!");
		}
		
		//2.检查优惠劵是否可以领
		if (!canGetForNotShowInCouponCenter) {
			criteria.clearCriteria();
			criteria.equalTo("id", coupon.getId());
			criteria.equalTo("show_in_coupon_center", SystemConstant.COUPON.SHOW_IN_COUPON_CENTER_TRUE); //在前台显示的
			List<Coupon> coupons = couponMapper.selectByExample(param);
			if (ObjectUtil.isNull(coupons)) {
				throw new BusinessException("该优惠券不可领取");
			}
		}
		//3.检查优惠卷是否存在
		criteria.clearCriteria();
		criteria.isNull("user_id");// 没人领取过
		criteria.equalTo("coupon_id", coupon.getId());
		//
		List<CouponUser> noReceivecouponUsers = couponUserMapper.selectByExample(param);
		if (CollectionUtils.isEmpty(noReceivecouponUsers)) {
			throw new BusinessException("不好意思，您来晚了，该优惠券已经领取完了!");
		}else {
			//如果存在则领取
			CouponUser couponUser = noReceivecouponUsers.get(0);
			couponUser.setUserId(userId);
			couponUser.setReceiveTime(new Date());//领取时间
			couponUserMapper.updateByPrimaryKeySelective(couponUser);
		}
	}
	
	@Override
    @Transactional(rollbackFor=Exception.class)
    public boolean toReceiveCouponLogin(AppCouponInDto coupon,boolean canGetForNotShowInCouponCenter) {
        
        Long couponTypeId = coupon.getId(); //优惠劵类别id
        Long userId = coupon.getUserId();//用户id
        
        if (ObjectUtil.isNull(couponTypeId) ) {
            return false;
        }
        if (ObjectUtil.isNull(userId) ) {
            return false;
        }
        
        CriteriaParameter param = new CriteriaParameter();
        Criteria criteria = param.createCriteria();
        
        //1. 查询之前是否领取了这个优惠券
        
        
        criteria.equalTo("user_id", coupon.getUserId());
        criteria.equalTo("coupon_id", coupon.getId());
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(param);
        if (!CollectionUtils.isEmpty(couponUsers)) {
            return false;
        }
        
        //2.检查优惠劵是否可以领
        if (!canGetForNotShowInCouponCenter) {
            criteria.clearCriteria();
            criteria.equalTo("id", coupon.getId());
            criteria.equalTo("show_in_coupon_center", SystemConstant.COUPON.SHOW_IN_COUPON_CENTER_TRUE); //在前台显示的
            List<Coupon> coupons = couponMapper.selectByExample(param);
            if (ObjectUtil.isNull(coupons)) {
                return false;
            }
        }
        //3.检查优惠卷是否存在
        criteria.clearCriteria();
        criteria.isNull("user_id");// 没人领取过
        criteria.equalTo("coupon_id", coupon.getId());
        //
        List<CouponUser> noReceivecouponUsers = couponUserMapper.selectByExample(param);
        if (CollectionUtils.isEmpty(noReceivecouponUsers)) {
            return false;
        }else {
            //如果存在则领取
            CouponUser couponUser = noReceivecouponUsers.get(0);
            couponUser.setUserId(userId);
            couponUser.setReceiveTime(new Date());//领取时间
            couponUserMapper.updateByPrimaryKeySelective(couponUser);
        }
        return true;
    }

	@Override
	public List<CouponUser> findCouponUserByUserId(Long userId) {
		CriteriaParameter param = new CriteriaParameter();
		Criteria criteria = param.createCriteria();
		criteria.equalTo("user_id", userId);
		List<CouponUser> couponUsers = couponUserMapper.selectByExample(param);
		return couponUsers;
	}
	/**
	 * 查询已绑定的总条数
	 */
	@Override
	public Integer countCoup(Long couponTypeId) {
		ReqData reqData = new ReqData();
		reqData.putValue("coupon_id", couponTypeId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("user_id", "", SystemConstant.REQ_PARAMETER_NE);
		int countByExample = couponUserMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return countByExample;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void couponReceiveByPhone(couponReceiveByPhoneDto couponReceiveByPhoneDto) {
		CriteriaParameter param = new CriteriaParameter();
		Criteria criteria = param.createCriteria();
		//1.取出要发送的优惠卷分类id
		Long coupon_category_id = couponReceiveByPhoneDto.getCouponCategoryId();
		String phone = couponReceiveByPhoneDto.getMobilePhone();
		if (StringUtil.isEmpty(phone)) {
			throw new BusinessException("手机号不能为空");
		}
		if (!NumberUtil.isNumber(phone)) {
			throw new BusinessException("手机号格式不正确");
		}
		if (phone.length() != 11) {
			throw new BusinessException("手机号格式不正确");
		}
		if (!phone.startsWith("1")) {
			throw new BusinessException("手机号格式不正确");
		}
		
		if (StringUtil.isEmpty(coupon_category_id)) {
			throw new BusinessException("优惠劵类别ID不能为空");
		}
		
		//1. 查询之前是否领取了这个优惠券
		criteria.equalTo("mobile_phone", phone);
		List<CouponPick> couponPicks = couponPickMapper.selectByExample(param);
		if (!CollectionUtils.isEmpty(couponPicks)) {
			throw new BusinessException("该手机号已经领取!");
		}
		
		//2.验证优惠卷表
		criteria.clearCriteria();
		criteria.equalTo("id", coupon_category_id);
		criteria.equalTo("show_in_coupon_center",SystemConstant.COUPON.SHOW_IN_COUPON_CENTER_TRUE); // 在前台显示的
		criteria.equalTo("status", SystemConstant.COUPON.NO_USE_STRING);
		
		List<Coupon> coupons = couponMapper.selectByExample(param);
		if (ObjectUtil.isNull(coupons)) {
			throw new BusinessException("未在前台展示的优惠劵码才可以领取");
		}
		Coupon coupon = couponMapper.selectByPrimaryKey(coupon_category_id);
		if (ObjectUtil.isNull(coupon)) {
			throw new BusinessException("优惠劵不存在");
		}
		
		//3.领取优惠卷纸质编码号
		criteria.clearCriteria();
		criteria.equalTo("coupon_id", coupon_category_id);
		criteria.isNull("user_id");
		List<CouponUser> couponUsers = couponUserMapper.selectByExample(param);
		if (CollectionUtils.isEmpty(couponUsers) ) {
			throw new BusinessException("该优惠劵已经领完");
		}else {
			CouponUser couponUser = couponUsers.get(0);
			couponUser.setUserId(0L); //匿名领取 用户id为0
			couponUser.setReceiveTime(DateUtil.date());
			couponUserMapper.updateByPrimaryKey(couponUser);
			//记录并发送短信
			CouponPick couponPick = new CouponPick();
			couponPick.setCouponCategoryId(coupon_category_id);
			couponPick.setCouponId(couponUser.getId());
			couponPick.setMobilePhone(phone);
			couponPick.setCreateTime(DateUtil.date());
			couponPickMapper.insertSelective(couponPick);
			String sendRes = SendSMSUtil.sendFullSMS(phone, "恭喜您获得10元抵用券，券码:" + couponUser.getPaperCouponCode()+",抵用券不抵邮费，使用截止日：1.31。网址：www.xmy365.com"+SendSMSUtil.sign);
			System.out.println("发送短信结果 :"+sendRes);
		}
	}
	
	/**
	 * 绑定用户购买的优惠券商品
	 * @param userId    
	 * @return void    
	 * Date:2018年3月5日 下午3:43:28 
	 */
	@Override
	public void bindBuyCouponGoods(Long orderId){
		//1. 查询订单
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (ObjectUtil.isNotNull(order)) {
			if(ObjectUtil.isNotNull(order.getUserId())) {
		//2. 查询订单商品
				CriteriaParameter param = new CriteriaParameter();
				Criteria criteria = param.createCriteria();
				criteria.equalTo("order_id", orderId);
				List<OrderGoods> list = orderGoodsMapper.selectByExample(param);
		//3. 判断订单商品是否含有抵用券商品	
				for (OrderGoods orderGoods : list) {
					Long couponGoods = SystemConstant.NEWACIVITY.COUPON_GOODS;
					if (couponGoods.equals(orderGoods.getGoodsId())) {
						Long[] couponIds = SystemConstant.NEWACIVITY.COUPON_GOODS_COUPON_ID;	
						for (Long couponId : couponIds) {
								//4. 绑定优惠券
								try {
									AppCouponInDto couponDto = new AppCouponInDto();
									couponDto.setId(couponId);
									couponDto.setUserId(order.getUserId());
									toReceiveCoupon(couponDto, true);
								} catch (BusinessException e) {
									// TODO: handle exception
									System.out.println("用户购买抵用券商品绑定抵用券出错"+e.getMessage());
								}
								
							}
					}
				}
			}
		}
		
	}
	
}
  