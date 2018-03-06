package com.zfj.xmy.activity.service.pc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.common.DateUtil;
import com.zfj.xmy.activity.service.pc.PcCouponService;
import com.zfj.xmy.common.DateUtils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
@Service
public class PcCouponSreviceImpl extends BaseService implements PcCouponService{
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	//优惠券状态 1.可用  2.已用 3.过期 4.新到账 5.将过期 6.最优惠
	private final static int USABLE=1;
	private final static int USED=2;
	private final static int PAST=3;
	private final static int FRESH=4;
	private final static int WILL_EXPIRE=5;
	private final static int MOST_FAVORABLE=6;
	
	//一周
	private final static int WEEK=7;
	
	@Override
	public List<Coupon> findCouponByUserId(long userId) {
		//1.根据用户ID查询他的所有优惠卷
		String couponIds = "0";
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE,SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> userConponList = couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CouponUser couponUser : userConponList) {
			couponIds += ","+couponUser.getCouponId();
		}
		reqData.putValue("id", couponIds, SystemConstant.REQ_PARAMETER_IN);
		/*reqData.putValue("effective_start_time", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_LE);*/
		reqData.putValue("effective_end_time", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_GT);
		List<Coupon> coupons = couponMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData), reqData.getRowBounds());
		return coupons;
	}
	
	@Override
	public void findCouponByUserId(Long userId,PageBean pageBean) {
		//1.根据用户ID查询他的所有优惠卷
		String couponIds = "0";
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE,SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> userConponList = couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CouponUser couponUser : userConponList) {
			couponIds += ","+couponUser.getCouponId();
		}
		reqData.putValue("id", couponIds, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("effective_start_time", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_LE);
		reqData.putValue("effective_end_time", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_GT);
		List<Coupon> coupons = couponMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		for (Coupon coupon : coupons) {
			 if (coupon.getEffectiveStartTime().getTime() <= new Date().getTime()) {
				 coupon.setStatus(0); // 用status字段表示当前抵用券是否可使用 0 可使用 1不可使用
			 } else {
				 coupon.setStatus(1); // 
			 }
		}
		pageBean.setData(coupons);
		pageBean.setTotalCount(couponMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
	}
	

	@Override
	public void findAllCoupon(long userId,PageBean pageBean,int status) {
		//根据用户查询用户的优惠券
		String couponIds = "0";
		ReqData reqData=new ReqData();
		String date=DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		//可用优惠券
		if(status==USABLE){
			reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE,SystemConstant.REQ_PARAMETER_EQ);
		}
		//已用优惠券
		if(status==USED){
			reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_USED,SystemConstant.REQ_PARAMETER_EQ);
		}
		//过期优惠券
		if(status==PAST){
			reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE,SystemConstant.REQ_PARAMETER_EQ);
		}
		List<CouponUser> list=couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		
		//根据用户优惠券id查询优惠券详情
		for(CouponUser cou:list){
			couponIds += ","+cou.getCouponId();
		}
		reqData.putValue("id", couponIds, SystemConstant.REQ_PARAMETER_IN);
		if(status==USABLE){
			reqData.putValue("effective_end_time", date,SystemConstant.REQ_PARAMETER_GT);
		}
		if(status==PAST){
			reqData.putValue("effective_end_time", date,SystemConstant.REQ_PARAMETER_LT);
		}
		List<Coupon> coupons=couponMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(coupons);
		int count=couponMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}

	@Override
	public void findSortCoupon(long userId, PageBean pageBean, int sort) {
		//根据用户查询用户的优惠券
		String couponIds = "0";
		ReqData reqData = new ReqData();
		
		String date=DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		Date weekAgo=DateUtil.addDayOfYear(new Date(), -WEEK);
		String ago=DateUtil.format(weekAgo, "yyyy-MM-dd");
		
		Date weekLater=DateUtil.addDayOfYear(new Date(), WEEK);
		String later=DateUtil.format(weekLater, "yyyy-MM-dd");
		
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		//新到账优惠券
		if(sort==FRESH){
			reqData.putValue("receive_time", ago,SystemConstant.REQ_PARAMETER_GT);
		}
		if(sort==MOST_FAVORABLE){
			reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE,SystemConstant.REQ_PARAMETER_EQ);
		}
		List<CouponUser> list=couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		
		//根据用户优惠券id查询优惠券详情
		for(CouponUser cou:list){
			couponIds += ","+cou.getCouponId();
		}
		reqData.putValue("id", couponIds, SystemConstant.REQ_PARAMETER_IN);
		//将过期优惠券
		if(sort==WILL_EXPIRE){
			reqData.putValue("effective_end_time", later,SystemConstant.REQ_PARAMETER_LT);
			reqData.putValue("effective_end_time", date,SystemConstant.REQ_PARAMETER_GT);
		}
		//最优惠
		if(sort==MOST_FAVORABLE){
			reqData.putValue("effective_end_time", date,SystemConstant.REQ_PARAMETER_GT);
			reqData.setSortname("coupon_value");
		}
		List<Coupon> coupons=couponMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(coupons);
		int count=couponMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<Integer> countCoupon(long userId) {
		List<Integer> list=new ArrayList<Integer>();
		//可用优惠券统计
		String useIds = "0";
		ReqData reqData=new ReqData();
		String date=DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE,SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> uselist=couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for(CouponUser cou:uselist){
			useIds += ","+cou.getCouponId();
		}
		reqData.putValue("id", useIds, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("effective_end_time", date,SystemConstant.REQ_PARAMETER_GT);
		int use=couponMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		list.add(use);
		reqData.clearValue();
		
		//已用优惠券统计
		String uesdIds = "0";
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_USED,SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> usedlist=couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for(CouponUser cou:usedlist){
			uesdIds += ","+cou.getCouponId();
		}
		reqData.putValue("id", uesdIds, SystemConstant.REQ_PARAMETER_IN);
		int used=couponMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		list.add(used);
		reqData.clearValue();
		
		
		//过期优惠券统计
		String pastIds = "0";
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE,SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> pastlist=couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for(CouponUser cou:pastlist){
			pastIds += ","+cou.getCouponId();
		}
		reqData.putValue("id", pastIds, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("effective_end_time", date,SystemConstant.REQ_PARAMETER_LT);
		int past=couponMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		list.add(past);
		
		return list;
	}
	/**
	 * 用户绑定纸质优惠卷
	 */
	@Override
	@Transactional
	public Integer userBindPaperCoupon(Long userId, String paperCode) {
		CouponUser couponUser = null;
		int i = 0;
		ReqData reqData = new ReqData();
		//1.判断该用户是否已经帮点过了这类优惠卷并且未使用
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE, SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> selectByExample2 = couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		reqData.putValue("paper_coupon_code", paperCode, SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> selectByExample = couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!ObjectUtils.isEmpty(selectByExample)){
			couponUser = selectByExample.get(0);
			if(ObjectUtils.isEmpty(couponUser.getUserId())||couponUser.getUserId()==0L){
				boolean flag = false;
				for (CouponUser couponUser2 : selectByExample2) {
					if(couponUser2.getCouponId()==couponUser.getCouponId()){
						flag = true;//已经绑定了相同类型的优惠卷
					}
				}
				if(flag){
					i = 3;//用户已经绑定了相同类型
				}else{
					couponUser.setUserId(userId);
					couponUser.setReceiveTime(new Date());
					int j = couponUserMapper.updateByPrimaryKey(couponUser);
					if(j>0){
						i = 4;//绑定成功
					}
				}
			}else{
				i = 2;//该优惠卷已经被使用了
			}
		}else{
			i = 1;//优惠卷不存在
		}
		return i;
	}

}