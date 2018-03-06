package com.zfj.xmy.order.service.app.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.alibaba.druid.util.StringUtils;
import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.xmy.common.DateUtils;
import com.zfj.xmy.common.FreightUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.EntityCouponMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.PayWayMapper;
import com.zfj.xmy.common.persistence.dao.PointsGoodsMapper;
import com.zfj.xmy.common.persistence.dao.RefundImageMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.dao.VocabularyMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.EntityCoupon;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.PayWay;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.RefundImage;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.common.persistence.pojo.app.DiscountActivityDto;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.common.service.CommonPayOrderService;
import com.zfj.xmy.common.service.CommonUserPointsService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.common.service.OrderCommitService;
import com.zfj.xmy.freight.vo.FreightGoods;
import com.zfj.xmy.order.persistence.app.dao.AppOrderExMapper;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppGoodsVo;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderAllDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderDir;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderGoodsDir;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderVo;
import com.zfj.xmy.order.persistence.app.pojo.dto.CancelOrderInDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.GoodsDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.OrderOutDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.RefundInDto;
import com.zfj.xmy.order.persistence.common.dao.OrderExMapper;
import com.zfj.xmy.order.persistence.common.pojo.dto.app.OrderStatusCountDto;
import com.zfj.xmy.order.service.app.AppOrderService;
import com.zfj.xmy.order.service.common.CommonOrderService;
import com.zfj.xmy.order.service.pc.PcOrderService;
import com.zfj.xmy.redis.RedisTokenManager;
import com.zfj.xmy.util.SendSMSUtil;

/**
 * 
 * @author wy
 *
 */
@Service
public class AppOrderServiceImpl implements AppOrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private AppOrderExMapper appOrderExMapper;
	
	@Autowired
	private TermDataMapper termDataMapper;
	
	@Autowired
	private PayWayMapper payWayMapper;
	
	@Autowired
	private RefundImageMapper refundImageMapper;
	
	@Autowired
	private VocabularyMapper vocabularyMapper;
 	
	@Autowired
	private OrderExMapper orderExMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingMapper;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private CommonLimitActivityService commonLimitActivityService;
	
	@Autowired
	private EntityCouponMapper entityCouponMapper;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	@Autowired
	private OnLineActivityService onLineService;
	
	@Autowired
	private PointsGoodsMapper pointsGoodsMapper;
	
	@Autowired
	private CommonOrderService commonOrderService;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private PcOrderService orderService;
	
	@Autowired
	private UserSpendPointsMapper userSpendPointsMapper;
	
	@Autowired
	private CommonPayOrderService commonPayOrderService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommonUserPointsService commonUserPointsService;
	
	@Autowired
	private PointsGoodsMapper goodsMapper2;
	
	@Autowired
	private OrderCommitService commitService;
	
	@Autowired
	private RedisTokenManager redisTokenManager;
	
	
	@Override
	public List<OrderOutDto> findByUserId(Long userid,AppOrderAllDto orderAllDto) {
		
		List<OrderOutDto> orderOuts = new ArrayList<OrderOutDto>();
		
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		
		String orderbycreatetime = orderAllDto.getOrderbycreatetime()==1?"ASC":"DESC"; //创建时间升降序
		
		//1.查询订单
		para.setOrderByClause("status asc,create_time "+orderbycreatetime);
		criteria.equalTo("user_id", userid);
		//criteria.equalTo("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE);//未删除的
		List<Order> orders = orderMapper.selectByExample(para);
		
		//2.查询商品关联 ,商品
		para.setOrderByClause(null);
		criteria.clearCriteria();
		List<Object> orderIds = getIds(orders);
		List<OrderGoods> orderGoods = null;
		List<Goods> goods = new ArrayList<Goods>(); //关联的所有商品
		if (CollectionUtils.isEmpty(orderIds)) {
			orderGoods = new ArrayList<OrderGoods>();
		}else {
			criteria.in("order_id", getIds(orders));
		    orderGoods = orderGoodsMapper.selectByExample(para);
		    
		    criteria.clearCriteria();
		    criteria.in("id", getGoodsIds(orderGoods));
		    goods = goodsMapper.selectByExample(para);
		    
		}
		//3.组装数据
		for (Order order : orders) {
			OrderOutDto orderOut = new OrderOutDto();
			orderOut.setOrderId(order.getId());
			orderOut.setNo(order.getNo());
			orderOut.setStatus(order.getStatus());
			orderOut.setPayType(order.getPayType());
			orderOut.setTotal(order.getTotal());
			orderOut.setCreateTime(order.getCreateTime());
			Integer goodsNum = 0; //每笔订单商品数
			List<GoodsDto> goodsDtos = new ArrayList<GoodsDto>();
			List<OrderGoods> singleOrderGoods = new ArrayList<OrderGoods>();
			for (OrderGoods orderGood : orderGoods) { //一个订单包含多个商品
				if (order.getId().equals(orderGood.getOrderId())) {
					goodsNum += orderGood.getNum();
					GoodsDto goodsDto = new GoodsDto();
					goodsDto.setGoodsId(orderGood.getGoodsId());
					goodsDto.setFullName(orderGood.getName());
					goodsDto.setGoodsNum(orderGood.getNum());
					goodsDto.setPrice(orderGood.getPrice());
					goodsDto.setPresentGoodsId(orderGood.getPresentGoodsId()); //赠品
					goodsDto.setPresentGoodsName(orderGood.getPresentGoodsName());	
					goodsDto.setPoints(orderGood.getPoints()); // 积分
					goodsDto.setSumPoints(orderGood.getSumPoints());
					for (Goods good : goods) {
						if (orderGood.getGoodsId().equals(good.getId())) {
							goodsDto.setImgUrl(commonGoodsService.getFirstGoodsImg(good.getId()));
						}
					}
					goodsDtos.add(goodsDto);
					singleOrderGoods.add(orderGood);
				}
			}
			//查询订单商品中是否含有跨境商品
			if (commonOrderService.checkOrderGoodsIsAcrossBorders(singleOrderGoods)) {
				orderOut.setIsAcrossBorders(SystemConstant.TERMDATA.NOT_ACROSSBORDERS);
			} else {
				orderOut.setIsAcrossBorders(SystemConstant.TERMDATA.IS_ACROSSBORDERS);
			}
			//判断订单是否可以取消 （针对的是已经付款成功的包括货到付款） 
			orderOut.setIsCancleOrder(commonPayOrderService.checkPaySucessOrderIsCancle(order.getId()));
			orderOut.setIsDel(order.getIsDel());
			orderOut.setGoodsNum(goodsNum);
			orderOut.setGoods(goodsDtos);
			orderOut.setFreight(order.getFreight());
			orderOuts.add(orderOut);
		}
		return orderOuts;
	}
	
	/**
	 * 通过订单获取id列表s
	 * @param orders
	 * @return
	 * @Description 
	 * @date 2017年7月20日  下午4:07:36
	 * @author wy
	 * 2017
	 * @return List<Long>
	 */
	private List<Object> getIds(List<Order> orders){
		List<Object> longs = new ArrayList<Object>();
		if (!CollectionUtils.isEmpty(orders)) {
			for (Order order : orders) {
				longs.add(order.getId());
			}
		}
		return longs;
	}
	
	/**
	 * 通过订单商品关系表获取 商品id列表
	 * @param orderGoods
	 * @return
	 * @Description 
	 * @date 2017年7月20日  下午5:45:25
	 * @author wy
	 * 2017
	 * @return List<Object>
	 */
	private List<Object> getGoodsIds(List<OrderGoods> orderGoods){
		List<Object> longs = new ArrayList<Object>();
		if (!CollectionUtils.isEmpty(orderGoods)) {
			for (OrderGoods orderGoods2 : orderGoods) {
				longs.add(orderGoods2.getGoodsId());
			}
		}
		return longs;
	}
	
	
	/**
	 * 生成订单编码
	 * @param maxNo
	 * @return    
	 * @return String    
	 * Date:2017年8月3日 下午5:23:33 
	 * @author hexw
	 */
	public String getNo(String maxNo){
		
		//订单编码规则      长度12位默认从000000100000 递增
		String no = String.valueOf(Long.parseLong(maxNo)+1);
		int sort =  (SystemConstant.ORDER.NO_LENGTH-no.length());
		if ( no.length() < SystemConstant.ORDER.NO_LENGTH ){
			for (int i = 0 ; i< sort ; i++){
				no = "0"+no;
			}
		}
		return no ;
	}
	
	@Override
	public List<OrderStatusCountDto> getOrderCount(Long userid){
		
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		
		//1.查询订单
		para.setOrderByClause("status asc");
		criteria.equalTo("user_id", userid);
		criteria.equalTo("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE);//未删除的
		List<OrderStatusCountDto> ordersStatusCounts = orderExMapper.findOrderStatusCount(para);
		
		//2. 组装数据 ，如果不包含的状态，填数据0
		List<Integer> fullstatuss = new ArrayList<Integer>();
		fullstatuss.add(SystemConstant.ORDER.STATUS_UNPAID);
		fullstatuss.add(SystemConstant.ORDER.STATUS_DELIVERY);
		fullstatuss.add(SystemConstant.ORDER.STATUS_FINISH_DELIVERY);
		fullstatuss.add(SystemConstant.ORDER.STATUS_WAITTING_COMMENT);
		fullstatuss.add(SystemConstant.ORDER.STATUS_FINISH);
		
		//2.1 有数据的则填充
		List<Integer> standStatuss = new ArrayList<Integer>();
		for (OrderStatusCountDto orderStatusCountDto : ordersStatusCounts) {
			standStatuss.add(orderStatusCountDto.getStatus());
		}
		
		//2.2 没有数据填 数量 0 
		Collection<Integer> integers = CollectionUtil.disjunction(standStatuss,fullstatuss);
		for (Integer integer : integers) {
			OrderStatusCountDto zero = new  OrderStatusCountDto();
			zero.setCount(0);
			zero.setStatus(integer);
			ordersStatusCounts.add(zero);
		}
		
		return ordersStatusCounts;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public AppOrderDir findOrder(List<AppGoodsVo> list, Long userId){
		AppOrderDir appOrderDir = new  AppOrderDir();
		appOrderDir.setTotalMoney(new BigDecimal(0));
		appOrderDir.setPoints(0);
		appOrderDir.setUsePoints(0);
		List<AppOrderGoodsDir>  goodsList = new ArrayList<AppOrderGoodsDir>();
		if (list.size() > 0) {
			// 1. 查询订单商品
			for (AppGoodsVo appGoodsVo : list) {
				//1.1查询订单商品
				ReqData reqData = new ReqData();
				reqData.putValue("id", appGoodsVo.getId(), SystemConstant.REQ_PARAMETER_EQ);
				AppOrderGoodsDir   appOrderGoodsDir = appOrderExMapper.findOrderGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
				//1.2如果是活动商品的 价格设置为活动商品的价格
				if ( appGoodsVo.getActivityId() != 0 ){ 
					appOrderGoodsDir.setPrice(getActivityGoodsPrice(appGoodsVo.getActivityId(), appGoodsVo.getId(), appGoodsVo.getActivityType()));
				} 
				if (ObjectUtil.isNotNull(appOrderGoodsDir.getPoints())){
					appOrderGoodsDir.setPoints(appOrderGoodsDir.getPoints()*appGoodsVo.getNum()); //商品的总积分
				}
				//如果有赠品则设置赠品id和name
				if(!ObjectUtils.isEmpty(appGoodsVo.getPresentGoodsId())&&appGoodsVo.getPresentGoodsId()!=0){
					appOrderGoodsDir.setPresentGoodsName(goodsMapper.selectByPrimaryKey(appGoodsVo.getPresentGoodsId()).getName());
					appOrderGoodsDir.setPresentGoodsId(appGoodsVo.getPresentGoodsId());
				}
				//如果是积分活动
				if(!ObjectUtils.isEmpty(appGoodsVo.getPointsActId())&&appGoodsVo.getPointsActId()!=0){
					reqData.clearValue();
					PointsGoods pointsGoods=pointsGoodsMapper.selectByPrimaryKey(appGoodsVo.getPointsActId().longValue());
					if(!ObjectUtils.isEmpty(pointsGoods)){
						appOrderGoodsDir.setActPoints(pointsGoods.getPoints());
						appOrderGoodsDir.setSumActPoints(appOrderGoodsDir.getActPoints()*appGoodsVo.getNum());
					}
					appOrderGoodsDir.setPointsActId(appGoodsVo.getPointsActId());
				}
				appOrderGoodsDir.setImg(commonGoodsService.getFirstGoodsImg(appGoodsVo.getId())); //商品图片
				Integer goodsNum = appGoodsVo.getNum();
				BigDecimal bigDecimalNum = new BigDecimal(Integer.toString(goodsNum));  
				BigDecimal sumPrice = appOrderGoodsDir.getPrice().multiply(bigDecimalNum); 
				appOrderGoodsDir.setSumPrice(sumPrice); //商品总价格
				appOrderGoodsDir.setActivityId(appGoodsVo.getActivityId());  //活动id
				appOrderGoodsDir.setActivityType(appGoodsVo.getActivityType()); //活动类型
				appOrderGoodsDir.setNum(appGoodsVo.getNum()); //商品数量
				goodsList.add(appOrderGoodsDir);
			}
			appOrderDir.setList(goodsList);
			// 2. 订单商品总金额 和总积分
			for (AppOrderGoodsDir appOrderGoodsDir : goodsList) {
				//如果是积分购买
				if(!ObjectUtils.isEmpty(appOrderGoodsDir.getSumActPoints())&&appOrderGoodsDir.getSumActPoints()!=0){
					appOrderDir.setUsePoints(appOrderDir.getUsePoints()+appOrderGoodsDir.getSumActPoints());
				}else{//如果不是积分购买
					if (appOrderDir.getPoints() != null ){
						appOrderDir.setPoints(appOrderDir.getPoints()+appOrderGoodsDir.getPoints()); //订单的总积分
					}
					appOrderDir.setTotalMoney(appOrderDir.getTotalMoney().add(appOrderGoodsDir.getSumPrice()));//订单的总金额
				}
			}	
			DecimalFormat df =new DecimalFormat("#.00");  //保留两位小数
			appOrderDir.setTotalMoney(new  BigDecimal(df.format(appOrderDir.getTotalMoney())));
			appOrderDir.setPoints(appOrderDir.getTotalMoney().intValue());
			// 判断订单是否含有跨境商品
			if (checkIsAcrossBorders(list)) {
				appOrderDir.setIsAcrossBorders(SystemConstant.TERMDATA.NOT_ACROSSBORDERS);
			} else{ appOrderDir.setIsAcrossBorders(SystemConstant.TERMDATA.IS_ACROSSBORDERS);}
				//3. 查询发票内容
			ReqData vocReqData = new  ReqData();
			vocReqData.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.INVOICE_SETTING, SystemConstant.REQ_PARAMETER_EQ);
			Vocabulary vocabulary = vocabularyMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(vocReqData));
			ReqData invReqData = new  ReqData();
			invReqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
			String invoceContents = appOrderExMapper.findInvoiceContent(ReqUtil.reqParameterToCriteriaParameter(invReqData));
			String[] Contents = invoceContents.split("--");
			appOrderDir.setInvoiceContents(java.util.Arrays.asList(Contents));
		} else {
			throw new BusinessException("订单商品不能为空"); 
		}
		if (ObjectUtil.isNotNull(userId)) {
			UserInfo userInfo=userInfoMapper.selectByPrimaryKey(userId);
			int userPoints=userInfo.getAccPoints();
			if(ObjectUtil.isNotNull(userPoints)&&userPoints<appOrderDir.getUsePoints()){
				throw new BusinessException("你的可用积分不足！");
			}
		}
		return appOrderDir;
	}
	
	@Override
	public void spendPoints(Long userId,Integer spendPoints){
		//1. 扣取用户积分
		UserInfo userInfo=userInfoMapper.selectByPrimaryKey(userId);
		if (ObjectUtil.isNotNull(userInfo)) {
			userInfo.setAccPoints(userInfo.getAccPoints()-spendPoints);
			userInfoMapper.updateByPrimaryKeySelective(userInfo);
		}
		//2. 插入积分消耗记录 
		UserSpendPoints usp=new UserSpendPoints();
		usp.setType(SystemConstant.userSpendPoints.TYPE_POINT_POINT);
		usp.setUserId(userInfo.getId());
		usp.setMoneyPoint(new BigDecimal(spendPoints));
		usp.setRemarks("购买商品消耗"+spendPoints+"积分！");
		usp.setCreatTime(new Date());
		usp.setIsDel(SystemConstant.userSpendPoints.STATUS_NODELETE);
		usp.setSpendType(SystemConstant.userSpendPoints.SPEND_TYPE_SPEND);
		userSpendPointsMapper.insert(usp);
	}
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> insertOrder(AppOrderVo vo,List<AppGoodsVo> list,Boolean isgenarateOrder){
		if(commitService.isCommitOrder()>0){
			throw new BusinessException("目前不能提交订单！");
		}
		Long userid = vo.getUserid(); 
		String userName = "匿名";
		if (ObjectUtil.isNull(userid)) {//用户id ，不登录时为空
			String devId = vo.getDeviceid();
			if (ObjectUtil.isNull(devId)) {
				throw new BusinessException("匿名下单必须传入设备标识号");
			} 
		}  else {
			User user = userMapper.selectByPrimaryKey(userid);
			if (ObjectUtil.isNotNull(user)) {
				userName = user.getName();
			}
		}
		//1.检查优惠劵信息
		Long couponId = vo.getCouponId();//优惠劵表id (couponUser表)
		//定义返回变量
		BigDecimal couponValue = new BigDecimal(0);
		Integer quota = 0;
		if (ObjectUtil.isNotNull(userid)) { //登录状态下才使用优惠劵
			if (ObjectUtil.isNotNull(couponId)) {
				System.out.println("app下单，优惠券id:"+couponId);
				// 检查是否是用户优惠劵
				CriteriaParameter parameter = new CriteriaParameter();
				Criteria criteria = parameter.createCriteria();
				criteria.equalTo("id", couponId);
				criteria.equalTo("user_id", userid);
				criteria.equalTo("status", SystemConstant.COUPON_USER.STATUS_NO_USE);
				List<CouponUser> couponUsers = couponUserMapper.selectByExample(parameter);
				if (CollectionUtil.isEmpty(couponUsers)) {
					throw new BusinessException("优惠劵不存在或是已经被使用了");
				}
				CouponUser couponUser = couponUsers.get(0);
				// TODO: 检查是否可用在这笔订单上
				
				// 查询优惠价劵，抵扣总金额
				//查询电子优惠劵种类表获取抵扣的金额 
				Coupon coupon = couponMapper.selectByPrimaryKey(couponUser.getCouponId());
				if (ObjectUtil.isNull(coupon)) {
					throw new BusinessException("优惠劵不存在");
				} 
				// 判断优惠券是否在使用时间范围类
				Date nowDate = new Date();
				Date effectiveStartTime = coupon.getEffectiveStartTime(); //优惠券使用的开始时间
				Date effectiveEndTime = coupon.getEffectiveEndTime(); //优惠券使用的结束时间
				boolean checkStartTime =   nowDate.after(effectiveStartTime);
				boolean checkEndTime = nowDate.before(effectiveEndTime);
				if (!checkStartTime || !checkEndTime) {
					throw new BusinessException("优惠券不在使用时间范围内");
				}
				couponValue = coupon.getCouponValue();
				quota = coupon.getQuota();
				
                couponUser.setStatus(SystemConstant.COUPON_USER.STATUS_USED);
                if (isgenarateOrder) {
					couponUserMapper.updateByPrimaryKeySelective(couponUser);
				}
			}
		}
		
		String province = vo.getProvince();
		String city = vo.getCity();
		String area = vo.getArea();
		String consigneeAddress = vo.getConsigneeAddress(); //收货人详细地址
		
		if (StringUtil.isEmpty(province)) {
			throw new BusinessException("收货人省份不能为空");
		}
        if (StringUtil.isEmpty(city)) {
        	throw new BusinessException("收货人市级不能为空");
		}
        if (StringUtil.isEmpty(area)) {
        	throw new BusinessException("收货人区级不能为空");
		}
		
		//生成订单
		Order order = new Order();
		order.setCreateTime(new Date());
		order.setPaymentName(userName);
		order.setUserId(userid);
		order.setStatus(SystemConstant.ORDER.STATUS_UNPAID);  // 待付款状态
		order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_UNPAID);
		order.setUpdateTime(new  Date());
		String no = appOrderExMapper.findMaxNum(null);
		if ( StringUtils.isEmpty(no)) { //生成订单编码  默认从000000100000 递增
			order.setNo(SystemConstant.ORDER.INIT_NO);
		} else {
			order.setNo(getNo(no));
		}
		order.setDeviceId(vo.getDeviceid());
		order.setIsDel(SystemConstant.ORDER.IS_DEL_NOT_DELETE);
		order.setConsigneeName(vo.getConsigneeName());
		order.setConsigneePhone(vo.getConsigneePhone());
		
		String fullAddress = province + city + area + consigneeAddress;
		order.setConsigneeAddress(fullAddress);
		order.setConsigneeIdcard(vo.getConsigneeIdcard());
		if (!StringUtil.isEmpty(vo.getSendTime())) {
		    order.setParsetTime(DateUtil.parse(vo.getSendTime()));
		}
		order.setOrderRemark(vo.getOrderRemark());
		order.setTotal(new BigDecimal(0));
		order.setInvoiceType(vo.getInvoiceType()); 	//发票类型 0:个人  1:公司
		order.setTaxpayerNum(vo.getTaxpayerNum());  //纳税人识别号
		order.setCompanyName(vo.getCompanyName());
		order.setInvoiceContent(vo.getInvoiceContent());
		order.setCouponId(couponId);
		order.setProvince(province);
		order.setCity(city);
		order.setArea(area);
		order.setOrderSource(SystemConstant.ORDER.ORDER_SOURCE_APP);
		//获取goodsid
		String goodsId = "0"; 
		for (AppGoodsVo appGoodsVo : list) {
			goodsId = goodsId +","+appGoodsVo.getId();
		}
		//满额减活动 优惠15块
		boolean checkOrder = commonPayOrderService.checkOrder(goodsId, userid);
		
		if (isgenarateOrder) {
			orderMapper.insertSelective(order);
		}
		List<FreightGoods> freightGoodss = new ArrayList<>();
		
		boolean isPgcGoods = false ;
		//2. 订单商品表
		for (AppGoodsVo appGoodsVo : list) {
			Goods goods = goodsMapper.selectByPrimaryKey(appGoodsVo.getId());
			if (ObjectUtil.isNull(goods)) {
				throw new BusinessException("包含不存在商品或者已经失效");
			}
			if (goods != null) {
				//检查是否支持全国配送 否 就只支持重庆主城
				String cqMainCity = FreightUtil.cqMainCity;
				if (SystemConstant.GoodsConstant.IS_DELIVERY_FALSE.equals(goods.getIsDelivery())
					&& !cqMainCity.contains(area)){
					throw new BusinessException("包含不支持全国配送的商品,请重新选择商品或选择重庆主城以内");
				}
				// 插入订单商品及计算订单总的商品金额和总的消耗积分
				insertOrderGoods(appGoodsVo,goods,order);
			}
			//获取邮费参数
			FreightGoods freightGoods = new FreightGoods();
			freightGoods.setGoods(goods);
			freightGoods.setNum(appGoodsVo.getNum());
			freightGoodss.add(freightGoods);
			
			//判断商品是否是免邮且单独购买的商品
			Long[] feeFreigthGoods = SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS;
			Long[] couponGoods = {SystemConstant.NEWACIVITY.COUPON_GOODS};
			Long[] alongBuyGoods = ArrayUtil.addAll(feeFreigthGoods,couponGoods);
			for (Long long1 : alongBuyGoods) {
				if (long1.equals(appGoodsVo.getId())) {
					isPgcGoods = true ;
				}
			}
		}
		
		//3. 开业打折活动
		BigDecimal valillaTotal = order.getTotal();//订单商品总额
		BigDecimal dicountPrice = new BigDecimal("0");
		if (ObjectUtil.isNotNull(userid)) {  //开业打折活动
			DiscountActivityDto dto = getDiscountPrice(list,vo.getProvince(),userid) ;
			//满额减活动 优惠15块
			if(checkOrder){
				dto.setDiscountPrice(dto.getDiscountPrice().add(new BigDecimal(15)));
				dto.setActivityRemark("满额减活动！");
			}
			dicountPrice = dto.getDiscountPrice(); //优惠的金额	
			 System.out.println("开业活动打折优惠的金额：=========>"+dicountPrice);
			 order.setDiscountPrice(dicountPrice);
			 order.setBusinessRemark(dto.getActivityRemark());
			 valillaTotal = valillaTotal.subtract(dicountPrice) ; //减去打折优惠的金额
		}
		//4.计算邮费
		double weight = FreightUtil.getWeightByGoods(freightGoodss); //获取重量
		BigDecimal freight = new BigDecimal("0.0");
		if (!isPgcGoods) { 
			freight = FreightUtil.getFreight(province, area, weight, valillaTotal);
		}
		//5.使用优惠劵
		BigDecimal totalAfterCoupon = valillaTotal;
		if (ObjectUtil.isNotNull(couponId) && ObjectUtil.isNotNull(userid)) { //抵用券面值不等于0
			if ( valillaTotal.doubleValue() >= quota) { //商品金额大于优惠劵起用金额才可用
				totalAfterCoupon = valillaTotal.subtract(couponValue);
			} else {
				throw new BusinessException("商品金额不满足抵用券抵用面值");
			}
		}
		if (totalAfterCoupon.doubleValue() < 0) {
			totalAfterCoupon = new BigDecimal(0);
		} 
		//增加邮费
		order.setTotal(totalAfterCoupon.add(freight));
		order.setFreight(freight);
		//6.更新订单
		if (isgenarateOrder) {
			Long totalAccPoints = new Double(Math.floor(order.getTotal().doubleValue())).longValue(); // 订单获得总积分  根据总金额  向下取整
			order.setAccPoints(totalAccPoints);
			orderMapper.updateByPrimaryKeySelective(order);  //主要是订单总金额的修改
		}
		//7.订单消耗积分 且插入积分记录表
		if (ObjectUtil.isNotNull(userid) && ObjectUtil.isNotNull(order.getUsedPoints()) && 0!= order.getUsedPoints() ) {
			spendPoints(userid,order.getUsedPoints());
		}
		
		//8. 清除购物车
		if (ObjectUtil.isNotNull(userid)) {   //匿名购买不用清除购物车
			ReqData shoppingReqData = new  ReqData();
			shoppingReqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
			shoppingReqData.putValue("user_id", vo.getUserid(), SystemConstant.REQ_PARAMETER_EQ);
			shoppingMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(shoppingReqData));
		}
		//9.发送短信
/*		try {
			SendSMSUtil.sendInsertOrderSuccess(order.getConsigneePhone(), order.getNo());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//10.返回数据
		Map<String, Object> result = new HashMap<String, Object>();
		if (isgenarateOrder) {
			result.put("orderId", order.getId());
		    result.put("orderNo", order.getNo());
		}else {
			result.put("freight", freight);
		}
		return result;
	}
	
	
	public boolean checkLimitActivityGoods(List<AppGoodsVo> list,Long userId){
		boolean flag = true ;
		for (AppGoodsVo appGoodsVo : list) {
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setGoodsId(appGoodsVo.getId());
			shoppingCart.setActivityId(appGoodsVo.getActivityId());
			shoppingCart.setActivityType(appGoodsVo.getActivityType());
			shoppingCart.setNum(appGoodsVo.getNum());
			shoppingCart.setUserId(userId);
			if (commonLimitActivityService.checkLimitActivity(shoppingCart) != 0) {
				flag = false;
			} 
		}
		return flag;
	}
	 
	@Override
	public BigDecimal getActivityGoodsPrice(long activityId,long goodsId,Integer activityType){
		BigDecimal prcie = new  BigDecimal(0);
		ReqData reqData = new  ReqData();
		switch (activityType) {
			case SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY:
				// 买即赠
				prcie=goodsMapper.selectByPrimaryKey(goodsId).getPrice();
				break;
			case SystemConstant.ACTIVITY.PROMOTION_ACTIVITY:
				// 专题促销
				break;
			case SystemConstant.ACTIVITY.LIMIT_ACTIVITY:
				LimitActivity limitivity = limitActivityMapper.selectByPrimaryKey(activityId);
				reqData.putValue("limit_id", limitivity.getId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
				List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if (list.size() > 0) {
					prcie=list.get(0).getPrice();
				}
				break;
			default:
				throw new BusinessException("查询活动错误");
		}
		return prcie;
	}
	
	
	/**
	 * 判断是否含有跨境商品
	 * @param list
	 * @return    
	 * @return boolean    false 表示有跨境商品
	 * Date:2017年8月1日 上午11:38:38 
	 * @author hexw
	 */
	public boolean checkIsAcrossBorders(List<AppGoodsVo> list){
		boolean flag = true ;
		ReqData reqData = new  ReqData();
		for (AppGoodsVo appGoodsVo : list) {
			reqData.clearValue();
			reqData.putValue("a.goods_id", appGoodsVo.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<String> categoryNames = appOrderExMapper.findCategoryName(ReqUtil.reqParameterToCriteriaParameter(reqData));
			for (String categoryName : categoryNames) {
				if (!StringUtils.isEmpty(categoryName)) {
					if(categoryName.indexOf(SystemConstant.TERMDATA.ACROSSBORDERS)!=-1){ //包含跨境两个字
						return false ;
					}
				}
			}
		}
		return flag;
	}	
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void refund(RefundInDto refundIn) {
		Long userid =  refundIn.getUserid();
		Long orderid = refundIn.getOrderId();
		String orderNo = refundIn.getOrderNo();
		String comment = refundIn.getComment();
		List<String> imgs = refundIn.getImgs();
		
		//1.验证
		if (!(!ObjectUtils.isEmpty(orderid) || !StringUtils.isEmpty(orderNo))) {
			throw new BusinessException("退款订单ID和订单编号必须输入一个");
		}
		if (ObjectUtils.isEmpty(userid)) {
			throw new BusinessException("退款人不能为空");
		}
		if (StringUtil.isEmpty(comment)) {
			throw new BusinessException("退货原因不能为空");
		}
		
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		
		criteria.equalTo("user_id", userid);
		if (!ObjectUtils.isEmpty(orderid)) {
			criteria.equalTo("id", orderid);
		}
		if (!StringUtils.isEmpty(orderNo)) {
			criteria.equalTo("no", orderNo);
		}
		
		
		List<Order> orders = orderMapper.selectByExample(para);
		if (CollectionUtils.isEmpty(orders)) {
			throw new BusinessException("订单不存在");
		}
		Order order = orders.get(0);
		if (SystemConstant.ORDER.STATUS_UNPAID.equals(order.getStatus())) {
			throw new BusinessException("订单没有支付无需退款");
		}
		if (SystemConstant.ORDER.RETURN_STATUS_REFUNDING.equals(order.getReturnStatus())) {
			throw new BusinessException("你的订单已经在处理中了，请耐心等待");
		}
		
		//2.退款处理
		order.setReturnReason(comment);//退款原因
		order.setReturnStatus(SystemConstant.ORDER.RETURN_STATUS_REFUNDING);
		order.setUpdateTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(order);
		
		if (!CollectionUtils.isEmpty(imgs)) {
			Date now = DateUtil.date();
			for (String string : imgs) {
				RefundImage refundImage = new RefundImage();
				refundImage.setCreateTime(now);
				refundImage.setImagePath(string);
				refundImage.setOrderId(order.getId());
				refundImageMapper.insertSelective(refundImage);
			}
			
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cancelOrder(CancelOrderInDto cancelOrderIn) {
		Long userid =  cancelOrderIn.getUserid();
		Long orderid = cancelOrderIn.getOrderId();
		
		if (ObjectUtils.isEmpty(orderid)) {
			throw new BusinessException("取消的订单不能为空");
		}
		if (ObjectUtils.isEmpty(userid)) {
			throw new BusinessException("取消人不能为空");
		}
		
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		
		criteria.equalTo("user_id", userid);
		criteria.equalTo("id", orderid);
		List<Order> orders = orderMapper.selectByExample(para);
		if (CollectionUtils.isEmpty(orders)) {
			throw new BusinessException("订单不存在");
		}
		Order order = orders.get(0);
		if (!order.getStatus().equals(SystemConstant.ORDER.STATUS_UNPAID)) {
			throw new BusinessException("未支付的订单才可以取消");
		}
		order.setIsDel(SystemConstant.ORDER.IS_DEL_DELETE); //删除就是取消
		order.setUpdateTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(order);
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void confirmReceipt(CancelOrderInDto cancelOrderIn) {
		
		Long userid =  cancelOrderIn.getUserid();
		Long orderid = cancelOrderIn.getOrderId();
		
		//1.校验输入
		if (ObjectUtils.isEmpty(orderid)) {
			throw new BusinessException("确认收货的订单不能为空");
		}
		if (ObjectUtils.isEmpty(userid)) {
			throw new BusinessException("确认收货人不能为空");
		}
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		
		criteria.equalTo("user_id", userid);
		criteria.equalTo("id", orderid);
		List<Order> orders = orderMapper.selectByExample(para);
		if (CollectionUtils.isEmpty(orders)) {
			throw new BusinessException("订单不存在");
		}
		Order order = orders.get(0);
		if (!SystemConstant.ORDER.STATUS_FINISH_DELIVERY.equals(order.getStatus()) ) {
			throw new BusinessException("订单在待收货状态才可以确认收货");
		}
		//2.确认收货
		order.setStatus(SystemConstant.ORDER.STATUS_WAITTING_COMMENT);
		order.setArrivalTime(DateUtil.date()); //确认收货时间
		order.setUpdateTime(DateUtil.date());
		orderMapper.updateByPrimaryKey(order);
		//确认收货添加积分
		commonUserPointsService.updateUserSpendPoints(order.getId());
	}

	@Override
	public JSONObject OrderDetail(CancelOrderInDto cancelOrderIn) {
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		
		Long orderId = cancelOrderIn.getOrderId();
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (ObjectUtil.isNull(order)) {
			throw new BusinessException("订单不存在");
		}
		
		JSONObject res = new JSONObject();
		res.put("status", order.getStatus());//订单状态
		res.put("payType", order.getPayType()); //支付方式
		res.put("consigneeName", order.getConsigneeName());//收货人名称
		res.put("consigneePhone", order.getConsigneePhone());//收货人联系方式
		res.put("consigneeAddress", order.getConsigneeAddress());//收货人地址
		
		//2.取出商品数据
		List<OrderGoods> orderGoods = null;
		List<Goods> goods = new ArrayList<Goods>(); //关联的所有商品
		
		criteria.equalTo("order_id", orderId);
		orderGoods = orderGoodsMapper.selectByExample(para);
		
		
		criteria.clearCriteria();
		criteria.in("id", getGoodsIds(orderGoods));
		goods = goodsMapper.selectByExample(para);
		
		BigDecimal goodsAllPrice = new BigDecimal(0);//原始商品总价
		
		Integer totalPoints = 0 ; //订单消耗总积分
		
		JSONArray goodslist = new JSONArray();
        for (OrderGoods ordergoods : orderGoods) {
        	for (Goods goods2 : goods) {
    			if (ordergoods.getGoodsId().equals(goods2.getId())) {
    				JSONObject goodsjo = new JSONObject();
    				goodsjo.put("goodsId", goods2.getId());
    				goodsjo.put("goodsName", goods2.getName());
    				goodsjo.put("imgurl", commonGoodsService.getFirstGoodsImg(goods2.getId()));
    				goodsjo.put("goodsNum", ordergoods.getNum());
    				goodsjo.put("price", ordergoods.getPrice());
    				goodsjo.put("weight", goods2.getStandard());
					goodsjo.put("presentGoodsName", ordergoods.getPresentGoodsName()); //赠品
    				goodsjo.put("presentGoodsId", ordergoods.getPresentGoodsId());
					goodsjo.put("points", ordergoods.getPoints()); // 积分
					goodsjo.put("sumPoints", ordergoods.getSumPoints());
					if (ObjectUtil.isNotNull(ordergoods.getSumPoints()) && 0 != ordergoods.getSumPoints() ) { //积分商品
						totalPoints = totalPoints + ordergoods.getSumPoints();
						ReqData reqData = new ReqData();
						reqData.putValue("goods_id", goods2.getId(), SystemConstant.REQ_PARAMETER_EQ);
						List<PointsGoods> list = goodsMapper2.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
						if(!ObjectUtils.isEmpty(list)){
							PointsGoods pointsGoods = list.get(0);
							goodsjo.put("pointsId", pointsGoods.getId());
						}
					} else {
						goodsAllPrice = goodsAllPrice.add(ordergoods.getPrice().multiply(new BigDecimal(ordergoods.getNum())));
					}
    				goodslist.add(goodsjo);
				}
    		}
		}
        //查询订单商品中是否含有跨境商品
		if (commonOrderService.checkOrderGoodsIsAcrossBorders(orderGoods)) {
			res.put("isAcrossBorders", SystemConstant.TERMDATA.NOT_ACROSSBORDERS);
		} else {
			res.put("isAcrossBorders", SystemConstant.TERMDATA.IS_ACROSSBORDERS);
		}
        res.put("goodsAllPrice", goodsAllPrice);//原始商品总价
        res.put("totalPoints", totalPoints); //订单消耗总积分
		res.put("goods", goodslist); //商品详情
		res.put("sendTime", order.getSendTime());//发货时间（配送日期）
		res.put("deliveryMethod", order.getDeliveryMethod());//配送方式
		if (ObjectUtil.isNotNull(order.getCouponId())) { //抵用券
			CouponUser couponUser = couponUserMapper.selectByPrimaryKey(order.getCouponId());
			if (ObjectUtil.isNotNull(couponUser)) {
				Coupon coupon = couponMapper.selectByPrimaryKey(couponUser.getCouponId());
				if (ObjectUtil.isNotNull(coupon)) {
					res.put("couponName", coupon.getName());  //优惠券名称
					res.put("couponQuta", coupon.getCouponValue()); // 抵扣金额
				}
			}
		}
		res.put("consigneeIdcard", order.getConsigneeIdcard());//收货人身份证信息
		res.put("hasInvoice", ObjectUtil.isNotNull(order.getInvoiceType())); //是否有发票信息
		res.put("companyName", order.getCompanyName()); //发票信息企业名称
		res.put("invoiceContent", order.getInvoiceType());//发票内容
		
		res.put("orderRemark", order.getOrderRemark()); //订单备注
		res.put("fee", order.getFee()); //税费
		res.put("discountPrice", order.getDiscountPrice()); //折扣
		res.put("total", order.getTotal()); //订单商品总额
//		res.put("orderRemark", order.getOrderRemark()); //订单备注
//		res.put("orderRemark", order.getOrderRemark()); //订单备注
//		res.put("orderRemark", order.getOrderRemark()); //订单备注
		BigDecimal actual = order.getActual();
		res.put("actual", ObjectUtil.isNull(actual)?0:actual) ;//第三方实付款
		BigDecimal actPayment = new BigDecimal("0.00");
		Integer isPaySuccess = commonPayOrderService.getIsPaySuccess(orderId);// 是否支付成功 0 支付成功 1 支付失败
		if (isPaySuccess == 0 ) { //支付成功了的
			if (ObjectUtil.isNotNull(order.getPay()) && ObjectUtil.isNotNull(order.getActual()) ) {//实际支付  第三方支付 和 余额/购物卡支付
				actPayment = order.getPay().add(order.getActual());
			}
		} else {
			if (ObjectUtil.isNotNull(order.getPay()) && order.getPay().compareTo(new BigDecimal(0)) == 1) { //货到付款余额支付
				actPayment = order.getPay();
			}
		}
		res.put("isCancleOrder", commonPayOrderService.checkPaySucessOrderIsCancle(orderId)); //判断这个订单是否能取消（针对待发货的订单）0 能取消 1不能取消
		res.put("actPayment", actPayment);  // 已付款
		res.put("residuePayment", order.getTotal().subtract(actPayment)); //应付款
		res.put("no", order.getNo());//订单编号
		res.put("createTime", order.getCreateTime());// 订单创建时间
		res.put("payTime", order.getPayTime()); //付款时间
		
		//自动关闭订单时间 + 1天
		Calendar cal = Calendar.getInstance();
		cal.setTime(order.getCreateTime());
		cal.add(Calendar.DATE, 1);
		res.put("closeTime",cal.getTime());// 自动关闭订单时间
		
		res.put("freight",order.getFreight());
		return res;
	}
	
	@Override
	public AppGoodsVo createAppGoodsVo(ShoppingCart shoppingCart){
		AppGoodsVo vo = new AppGoodsVo();
		vo.setId(shoppingCart.getGoodsId());
		vo.setNum(shoppingCart.getNum());
		if (ObjectUtil.isNotNull(shoppingCart.getActivityId())) {
			vo.setActivityId(shoppingCart.getActivityId());
		} else {
			vo.setActivityId(0);
		}
		if (ObjectUtil.isNotNull(shoppingCart.getActivityType())) {
			vo.setActivityType(shoppingCart.getActivityType());
		} else {
			vo.setActivityType(0);
		}
		if (ObjectUtil.isNotNull(shoppingCart.getPresentGoodsId())) {
			vo.setPresentGoodsId(shoppingCart.getPresentGoodsId());
		} else {
			vo.setPresentGoodsId(0);
		}
		if(ObjectUtil.isNotNull(shoppingCart.getActId())){
			vo.setPointsActId(shoppingCart.getActId().longValue());
		}else{
			vo.setPointsActId(new Long(0));
		}
		return vo;
	}
	
	@Override
	public Map<String,Object> getFreight(List<AppGoodsVo> list,String provinces,String area,Long userId){
		Map<String,Object> result = new HashMap<String,Object>();
		// 1. 香满园 6.8 折 活动
		BigDecimal disCountPrice = new BigDecimal("0");
		if (ObjectUtil.isNotNull(userId)) {
			BigDecimal goodsPrice = new BigDecimal("0");
			if (ObjectUtil.isNotNull(userId)) {
				String goodsId = "0";
				List<Object> ids = new ArrayList<Object>();
				for (AppGoodsVo vo : list) {
					ids.add(vo.getId());
					goodsId+=","+vo.getId();
				}
				goodsPrice = commonGoodsService.getDiscountSumPrice(ids, userId) ;  //不包含跨境专区和企业定制商品
				DiscountActivityDto dto = onLineService.getDiscountPrice(goodsPrice,userId,provinces); //优惠的价格
				//满额减活动 优惠15块
				boolean checkOrder = commonPayOrderService.checkOrder(goodsId, userId);
				if(checkOrder){
					dto.setDiscountPrice(dto.getDiscountPrice().add(new BigDecimal(15)));
				}
				disCountPrice = dto.getDiscountPrice();
			}
		}
		// 2. 计算商品总金额
		BigDecimal sumPrice = getGoodSumPrice(list);
		sumPrice = sumPrice.subtract(disCountPrice);
		// 3. 计算出商品总重量
		double weight = FreightUtil.getWeightByGoods(getFreightGoods(list));
		// 4. 计算出运费
		BigDecimal freight = FreightUtil.getFreight(provinces, area, weight, sumPrice);
		// 5. 判断商品是否是拼购抽商品是的话 将运费设为0
		for (AppGoodsVo appGoodsVo : list) {
			Long[] feeFreigthGoods = SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS;
			Long[] couponGoods = {SystemConstant.NEWACIVITY.COUPON_GOODS};
			Long[] aloneBuyGoods = ArrayUtil.addAll(feeFreigthGoods,couponGoods);//免邮商品
			for (Long long1 : aloneBuyGoods) {
				if (long1.equals(appGoodsVo.getId())) {
					freight = new BigDecimal("0.0");
				}
			}
		}
		result.put("freight", freight);
		result.put("disCountPrice", disCountPrice);
		return result;
	}

	public List<FreightGoods> getFreightGoods(List<AppGoodsVo> list){
		List<FreightGoods> allGoods = new ArrayList<FreightGoods>();
		for (AppGoodsVo appGoodsVo : list) {
			FreightGoods freightGoods = new FreightGoods();
			Goods goods = goodsMapper.selectByPrimaryKey(appGoodsVo.getId());
			if (ObjectUtil.isNotNull(goods)) {
				freightGoods.setGoods(goods);
				freightGoods.setNum(appGoodsVo.getNum());
				allGoods.add(freightGoods);
			}
		}
		return allGoods;
	}
	
	public DiscountActivityDto getDiscountPrice(List<AppGoodsVo> list,String province,Long userId){
		DiscountActivityDto dto = new DiscountActivityDto(); 
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		BigDecimal goodsPrice = new BigDecimal("0");
		if (ObjectUtil.isNotNull(userId)) {
			List<Object> ids = new ArrayList<Object>();
			for (AppGoodsVo vo : list) {
				ids.add(vo.getId());
				map.put(vo.getId(), vo.getNum());
			}
			goodsPrice = commonGoodsService.getDiscountSumPrice(ids, userId,map) ;  //不包含跨境专区和企业定制商品
			System.out.println("不包含跨境专区和企业定制商品的商品金额=====》"+goodsPrice);
			dto = onLineService.getDiscountPrice(goodsPrice,userId,province); //优惠的价格
		}
		return dto;
	}
	
	/**
	 *  计算商品总金额
	 */
	public BigDecimal getGoodSumPrice(List<AppGoodsVo> list){
		BigDecimal sumPrice = new BigDecimal("0.00");
		for (AppGoodsVo appGoodsVo : list) {
			if (ObjectUtil.isNotNull(appGoodsVo.getActivityId()) && 0 !=  appGoodsVo.getActivityId()) {
				BigDecimal singlePrice = getActivityGoodsPrice(appGoodsVo.getActivityId(), appGoodsVo.getId(), appGoodsVo.getActivityType()) ; //活动商品单价
				sumPrice = sumPrice.add(singlePrice.multiply(new BigDecimal(appGoodsVo.getNum())));
			} else {
				if (ObjectUtil.isNull(appGoodsVo.getPointsActId()) || 0 ==  appGoodsVo.getPointsActId()) {  //排除积分商品
					Goods goods = goodsMapper.selectByPrimaryKey(appGoodsVo.getId());
					if (ObjectUtil.isNotNull(goods)) {
						sumPrice = sumPrice.add(goods.getPrice().multiply(new BigDecimal(appGoodsVo.getNum())));
					}
				}
			}
		}
		return sumPrice;
	}
	
	
	/**
	 * 添加订单商品
	 * @param appGoodsVo
	 * @param goods
	 * @param order    
	 * @return void    
	 * Date:2017年12月16日 下午2:03:00 
	 * @author hexw
	 */
	public void insertOrderGoods(AppGoodsVo appGoodsVo,Goods goods,Order order){
		//下单
		OrderGoods orderGoods = new  OrderGoods();
		orderGoods.setOrderId(order.getId());
		orderGoods.setGoodsId(appGoodsVo.getId());
		orderGoods.setNum(appGoodsVo.getNum());
		orderGoods.setName(goods.getName());
		orderGoods.setNo(Long.parseLong(goods.getCode()));
		orderGoods.setCostPrice(goods.getCostPrice());
		//1. 计算商品价格
		if ( appGoodsVo.getActivityId() != 0 ){ //设置商品单价
			orderGoods.setPrice(getActivityGoodsPrice(appGoodsVo.getActivityId(), appGoodsVo.getId(), appGoodsVo.getActivityType()));
			orderGoods.setActivityType(appGoodsVo.getActivityType());
			orderGoods.setActivityId(appGoodsVo.getActivityId());
	   //2. 活动商品 修改活动商品交易完成数量
			commonLimitActivityService.updateActivityGoodsComplNum(appGoodsVo.getActivityId(), appGoodsVo.getId(), appGoodsVo.getNum());
		} else {
			orderGoods.setPrice(goods.getPrice());
		}
		//3. 计算积分商品 及订单总消耗积分和订单的总的商品金额
		if (orderGoods.getNum() != 0) {  //设置商品总积分
			if(!ObjectUtils.isEmpty(appGoodsVo.getPointsActId())&&appGoodsVo.getPointsActId()!=0){
				PointsGoods points=pointsGoodsMapper.selectByPrimaryKey(appGoodsVo.getPointsActId().longValue());
				if(!ObjectUtils.isEmpty(points)){
					orderGoods.setActivityType(SystemConstant.ACTIVITY.POINTS_ACTIVITY);
					orderGoods.setActivityId(appGoodsVo.getPointsActId().longValue());
					orderGoods.setPoints(points.getPoints());
					orderGoods.setSumPoints(orderGoods.getNum()*points.getPoints());
					order.setUsedPoints(orderGoods.getSumPoints()+(ObjectUtil.isNull(order.getUsedPoints())?0:order.getUsedPoints()));   //订单消耗的积分
				}
			}else{//设置商品总价
				Integer goodsNum = orderGoods.getNum();
				BigDecimal bigDecimalNum = new BigDecimal(Integer.toString(goodsNum));  
				BigDecimal sumPrice = orderGoods.getPrice().multiply(bigDecimalNum)	; 
				orderGoods.setSumPrice(sumPrice);
				order.setTotal(order.getTotal().add(sumPrice));  //计算订单总金额
			}
		}
		//4. 涉及到赠品的
		if (appGoodsVo.getPresentGoodsId() != 0 ){  
			Goods presentGoods = goodsMapper.selectByPrimaryKey(appGoodsVo.getPresentGoodsId());
			if (ObjectUtil.isNotNull(presentGoods)){
				orderGoods.setPresentGoodsId(presentGoods.getId());
				orderGoods.setPresentGoodsName(presentGoods.getName());
			}
		}
		orderGoodsMapper.insertSelective(orderGoods);
	}
	/**
	 * 根据订单编号查询订单信息
	 */
	@Override
	public List<Order> getOrderByOrderNo(String orderNo) {
		ReqData reqData = new ReqData();
		reqData.putValue("no", orderNo, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("pay_type", SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY, SystemConstant.REQ_PARAMETER_EQ);
		List<Order> selectByExample = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
	
}
