package com.zfj.xmy.order.service.wap.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.alipay.api.domain.CityFunction;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.FreightUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.UUIDUtil;
import com.zfj.xmy.common.persistence.dao.BuyAndPresentMapper;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.dao.UserAddreesMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.app.DiscountActivityDto;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.common.service.CommonPayOrderService;
import com.zfj.xmy.common.service.CommonShopingCardService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.common.service.impl.CommonNewActitvityServiceImpl;
import com.zfj.xmy.common.service.impl.OnLineServiceImpl;
import com.zfj.xmy.order.persistence.common.dao.OrderExMapper;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderGoodsDto;
import com.zfj.xmy.order.persistence.pc.dao.PcShoppCartExMapper;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcOrderGoodsDto;
import com.zfj.xmy.order.persistence.wap.dao.WapOrderExMapper;
import com.zfj.xmy.order.persistence.wap.pojo.dto.WapOrderQueryDto;
import com.zfj.xmy.order.service.app.AppOrderService;
import com.zfj.xmy.order.service.cms.OrderService;
import com.zfj.xmy.order.service.pc.PcShoppingCartService;
import com.zfj.xmy.order.service.pc.ShoppingCartService;
import com.zfj.xmy.order.service.wap.WapOrderService;

@Service
@Transactional
public class WapOrderServiceImple extends BaseService implements WapOrderService{
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderExMapper orderExMapper;
	
	@Autowired
	private OrderGoodsMapper goodsMapper;
	
	@Autowired
	private TermDataMapper dataMapper;
	
	@Autowired
	private PcShoppCartExMapper shoppCartExMapper;
	
	@Autowired
	private UserAddreesMapper addreesMapper;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserSpendPointsMapper userSpendPointsMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private CommonShopingCardService commonShopingCardService;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GoodsMapper gMapper;
	
	@Autowired
	private BuyAndPresentMapper andPresentMapper;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private CommonLimitActivityService commonLimitActivityService;
	
	@Autowired
	private OnLineActivityService onLineActivityService;
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Autowired
	private WapOrderExMapper wapOrderExMapper;
	
	@Autowired
	private AppOrderService appOrderService;
	
	@Autowired
	private OnLineServiceImpl onLineServiceImpl;
	
	@Autowired
	private PcShoppingCartService pcShoppingCartService;
	
	@Autowired
	private CommonNewActitvityServiceImpl commonNewActitvityService;
	
	@Autowired
	private CommonPayOrderService commonPayOrderService;
	
	
	public void insertOrderGoods(PcGoodsDto pcGoodsDto,Long orderId){
		OrderGoods orderGoods = new OrderGoods();
		orderGoods.setGoodsId(pcGoodsDto.getId());
		orderGoods.setName(pcGoodsDto.getName());
		orderGoods.setNo(Long.parseLong(pcGoodsDto.getCode()));
		orderGoods.setNum(pcGoodsDto.getCartNum());
		orderGoods.setPoints(pcGoodsDto.getActPoints());
		orderGoods.setSumPoints(pcGoodsDto.getSumPoints());
		orderGoods.setOrderId(orderId);
		orderGoods.setSupplier(pcGoodsDto.getSupplierName());
		orderGoods.setSumPrice(pcGoodsDto.getSumPrice());
		orderGoods.setTypeName(pcGoodsDto.getCategoryName());
		orderGoods.setActivityId(pcGoodsDto.getActivityId());
		orderGoods.setActivityType(pcGoodsDto.getActivityType());
		if (!ObjectUtils.isEmpty(pcGoodsDto.getActivityType()) && !ObjectUtils.isEmpty(pcGoodsDto.getActivityId())) {
			if (pcGoodsDto.getActivityType().equals(SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY)) { //买即赠活动
				orderGoods.setPresentGoodsId(pcGoodsDto.getPresentGoods().getId());
				orderGoods.setPresentGoodsName(pcGoodsDto.getPresentGoods().getName());
			} else { //限时限量 活动
				//修改活动商品交易完成量
				commonLimitActivityService.updateActivityGoodsComplNum(pcGoodsDto.getActivityId(), pcGoodsDto.getId(),pcGoodsDto.getCartNum());  
			}
			orderGoods.setPrice(pcGoodsDto.getActivityPrice());
		} else {
			orderGoods.setPrice(pcGoodsDto.getPrice());
		}
		if (!ObjectUtils.isEmpty(pcGoodsDto.getActId())) { //积分活动商品
			//修改积分活动商品的交易成交量
			commonLimitActivityService.updatePointsGoodsComplNum(pcGoodsDto.getActId(), pcGoodsDto.getCartNum());
		}
		// 保存商品成本价
		Goods goods = gMapper.selectByPrimaryKey(pcGoodsDto.getId()); 
		if (ObjectUtil.isNotNull(goods)) {
			orderGoods.setCostPrice(goods.getCostPrice());
		}
		goodsMapper.insertSelective(orderGoods);
	}
	
	/**
	 * 查询购物车详细信息
	 */
	@Override
	public List<PcGoodsDto> getShippingCart(Long userId,String goodsId) {
		ReqData reqData = new ReqData();
		reqData.putValue("sc.user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("sc.goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		List<PcGoodsDto> findShoppingCart = shoppCartExMapper.findShoppingCart(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (PcGoodsDto pcGoodsDto : findShoppingCart) {
			if(!ObjectUtils.isEmpty(pcGoodsDto.getActivityId())){
					if(!pcGoodsDto.getActivityId().equals(0)){
						if(pcGoodsDto.getActivityType() != null && SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY == pcGoodsDto.getActivityType()){
							Goods goods = gMapper.selectByPrimaryKey(pcGoodsDto.getPresentId());
							goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
							pcGoodsDto.setPresentGoods(goods);
						}
					}
					pcGoodsDto.setSumPrice(pcGoodsDto.getActivityPrice().multiply(BigDecimal.valueOf(Long.parseLong(pcGoodsDto.getCartNum().toString()))));
			}
			pcGoodsDto.setImgPath(commonGoodsService.getFirstGoodsImg(pcGoodsDto.getId()));
		}
		return findShoppingCart;
	}
	
	@Override
	public Map<String,Object> getFreight(String province,String area,String goodsId,String sumPrice,Long userId) {
		Map<String,Object> result = new HashMap<String, Object>();
		int isSuccess = 0;
		double weight = 0;
		List<PcGoodsDto> findShoppingCart = null;
		//1.算出订单商品的总总量
		if (ObjectUtil.isNull(userId)) {
			findShoppingCart = cartService.findUnLoadShoppingCart(goodsId);
		}else{
			findShoppingCart = getShippingCart(userId, goodsId);
		}
	    for (PcGoodsDto pcGoodsDto : findShoppingCart) {
			Goods goods = gMapper.selectByPrimaryKey(pcGoodsDto.getId());
			if (ObjectUtil.isNotNull(goods)) {
				if (NumberUtil.isNumber(pcGoodsDto.getWeight())) {
					weight =weight + Double.parseDouble(goods.getWeight())*pcGoodsDto.getCartNum();
				} else {
					isSuccess = 1;
					//throw new BusinessException("商品重量不合规则");
				}
			}
		}
	    BigDecimal freight = FreightUtil.getFreight(province,area, weight,new BigDecimal(sumPrice));
	    BigDecimal toltalPrice = new BigDecimal(sumPrice).add(freight);
	    DecimalFormat deicmalFormat =new DecimalFormat("#.00");
	    result.put("isSuccess", isSuccess);
	    result.put("freight", freight);
	    result.put("toltalPrice", deicmalFormat.format(toltalPrice));
		return result;
	}

	@Override
	public Integer changeTicketType(Long id, Integer type) {
		Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(id);
		selectByPrimaryKey.setInvoiceType(type);
		int updateByPrimaryKey = orderMapper.updateByPrimaryKey(selectByPrimaryKey);
		return updateByPrimaryKey;
	}
	
	@Override
	public Integer changeTicketInfo(Long id, String companyName, String content) {
		Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(id);
		selectByPrimaryKey.setCompanyName(companyName);
		selectByPrimaryKey.setTaxpayerNum(content);
		int updateByPrimaryKey = orderMapper.updateByPrimaryKey(selectByPrimaryKey);
		return updateByPrimaryKey;
	}
	
	@Override
	public Integer updateApplyRefund(Order order, String refundMoney, Long userId) {
		int i = 0;
		ReqData reqData = new ReqData();
		reqData.putValue("no", order.getNo(), SystemConstant.REQ_PARAMETER_EQ);
		List<Order> oldOrderList = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!ObjectUtils.isEmpty(oldOrderList)){
			Order oldOrder = oldOrderList.get(0);
			String returnReason = oldOrder.getReturnReason();
			returnReason += refundMoney;
			oldOrder.setReturnReason(returnReason);
			oldOrder.setReturnStatus(SystemConstant.ORDER.RETURN_STATUS_REFUNDING);
			i = orderMapper.updateByPrimaryKey(oldOrder);
		}
		return i;
	}
	
	/**
	 * @param maxNo
	 * @return String
	 * @author 生成订单编号
	 * @date 2017年9月8日 上午10:21:58
	 */
	public String getNo(String maxNo){
			//订单编码规则      长度12位默认从000000100000 递增
			if(StringUtil.isEmpty(maxNo)){
				maxNo = "0";
			}
			String no = String.valueOf(Long.parseLong(maxNo)+1);
			int sort =  (SystemConstant.ORDER.NO_LENGTH-no.length());
			if ( no.length() < SystemConstant.ORDER.NO_LENGTH ){
				for (int i = 0 ; i< sort ; i++){
					no = "0"+no;
				}
			}
			return no ;
		}
	
	
	/**
	 * 添加订单
	 */
	@Override
	public Order addOrder(HttpServletRequest request, HttpServletResponse response,String phone,String address,String conName,String taxperNo,String idCard,Long addressId, 
			String dateTime, String invoice, String goodsIds, String remark, String conponId,String provinceAddress,String countyAddress,UserInfo userInfo, String companyName, Integer invoiceType,Long accPoints, String area) {
		BigDecimal sumPrice = new BigDecimal("0.00");
		BigDecimal goodsPrice = new BigDecimal("0.00");
		int sumPoints=0;
		Order order = new Order();
		List<PcGoodsDto> findShoppingCart = null;
		UserAddrees userAddrees = null ;
		BigDecimal saleMoney  = new BigDecimal(0.00);
		//1.查询用户收货地址
		ReqData reqData = new ReqData();
		if("匿名".equals(userInfo.getRealName())){
			order.setConsigneeName(conName);
			order.setConsigneeIdcard(idCard);
			order.setProvince(provinceAddress);
			order.setCity(countyAddress);
			order.setArea(area);
			String consigneeName = "";
			consigneeName += provinceAddress + countyAddress + area + address;
			order.setConsigneeAddress(consigneeName);
			order.setConsigneePhone(phone);
		}else{
			userAddrees = addreesMapper.selectByPrimaryKey(addressId);
			order.setConsigneeName(userAddrees.getName());
			String consigneeAddress = "";
			if (!StringUtil.isEmpty(userAddrees.getProvince()))	{
				consigneeAddress = consigneeAddress+userAddrees.getProvince();
			}
			if (!StringUtil.isEmpty(userAddrees.getCity()))	{
				consigneeAddress = consigneeAddress+userAddrees.getCity();
			}
			if (!StringUtil.isEmpty(userAddrees.getArea()))	{
				consigneeAddress = consigneeAddress+userAddrees.getArea();
			}
			if (!StringUtil.isEmpty(userAddrees.getAddress())) {
				consigneeAddress = consigneeAddress+userAddrees.getAddress();
			}
			order.setProvince(userAddrees.getProvince());
			order.setCity(userAddrees.getCity());
			order.setArea(userAddrees.getArea());
			order.setConsigneeAddress(consigneeAddress);
			order.setConsigneePhone(userAddrees.getMobilePhone());
			order.setConsigneeIdcard(idCard);
		}
		//2.添加用户指定的配送时间
		order.setParsetTime(DateUtil.parse(dateTime));
		//3.发票
		order.setInvoiceContent(invoice);
		order.setInvoiceType(invoiceType);
		order.setCompanyName(companyName);
		order.setTaxpayerNum(taxperNo);
		//4.设置备注信息
		order.setOrderRemark(remark);
		//5.设置用户
		order.setPaymentName(userInfo.getRealName());
		order.setUserId(userInfo.getId());
		String serialNumber = UUIDUtil.generateToken(); //生成序列号 - 每次支付支成一次
		order.setSerialNumber(serialNumber);
		//wap标识
		order.setOrderSource(3);
		//6.计算订单价格
		//6.2查询购物车商品信息
		if("匿名".equals(userInfo.getRealName())){
			findShoppingCart = cartService.findUnLoadShoppingCart(goodsIds);
		}else{
			findShoppingCart = getShippingCart(userInfo.getId(), goodsIds);
		}
		//lij 2018年1月7日11:59:00 新增
		if(ObjectUtils.isEmpty(findShoppingCart)){
			throw new BusinessException("订单商品为空不能提交订单！");
		}
		order.setDiscountPrice(saleMoney);
		//6.3计算未扣除优惠卷的购物车总金额
		for (PcGoodsDto pcGoodsDto : findShoppingCart) {
			if(pcGoodsDto.getSumPoints()==null||pcGoodsDto.getSumPoints()==0){
				sumPrice = sumPrice.add(pcGoodsDto.getSumPrice());
			}
			if(!StringUtil.isEmpty(userInfo.getRealName()) && !userInfo.getRealName().equals("匿名") && (pcGoodsDto.getSumPoints()!=null && pcGoodsDto.getSumPoints()!=0)){
				sumPoints = sumPoints + pcGoodsDto.getSumPoints();
			}
			
		}
		order.setAccPoints(sumPrice.longValue());
		if(userInfo != null && userAddrees != null){
			List<Object> ids = new ArrayList<Object>();
			String[] idArray =  goodsIds.split("\\,");
			for (String id:idArray) {
				ids.add(Long.parseLong(id));
			}
			goodsPrice = commonGoodsService.getDiscountSumPrice(ids, userInfo.getId()) ;
			DiscountActivityDto discountActivityDto = onLineServiceImpl.getDiscountPrice(goodsPrice, userInfo.getId(), userAddrees.getProvince());
			//满额减活动优惠15块
			boolean checkOrder = commonPayOrderService.checkOrder(goodsIds, userInfo.getId());
			if(checkOrder){
				discountActivityDto.setActivityRemark("满额减活动！");
				discountActivityDto.setDiscountPrice(discountActivityDto.getDiscountPrice().add(new BigDecimal(15)));
			}
			//saleMoney = changeActivityPrice(request, userAddrees, findShoppingCart, userInfo.getId()).setScale(2, RoundingMode.HALF_UP);
			saleMoney = discountActivityDto.getDiscountPrice();
			order.setDiscountPrice(saleMoney);
			order.setBusinessRemark(discountActivityDto.getActivityRemark());
			//addOrderMark( userInfo.getId(),  order) ;
		}
		//7.订单消耗积分 且插入积分记录表
		if(!StringUtil.isEmpty(userInfo.getRealName()) && !userInfo.getRealName().equals("匿名") && sumPoints > 0){
			appOrderService.spendPoints(userInfo.getId(),sumPoints);
		}
		sumPrice = sumPrice.subtract(saleMoney).setScale(2, RoundingMode.HALF_UP);
		if(sumPrice.doubleValue() < 0) sumPrice = new BigDecimal(0.00);
		Long userId = null;
		if (ObjectUtil.isNotNull(userInfo.getId())) {
			userId = userInfo.getId();
		}
		if(!StringUtil.isEmpty(goodsIds) && goodsIds.split(",").length == 1 && onLineActivityService.judgeLuckWap(Long.parseLong(goodsIds))){
			order.setFreight(new BigDecimal(0.00));
		}else{
			Map<String,Object> reslut = getFreight(provinceAddress, countyAddress, goodsIds, sumPrice.toString(), userId); //计算运费
			if (Integer.parseInt(reslut.get("isSuccess").toString()) == 0) {  //计算运费成功
				sumPrice = new BigDecimal(reslut.get("toltalPrice").toString());
				order.setFreight(new BigDecimal(reslut.get("freight").toString()));  //设置运费
			}
		}
		//6.4计算扣除优惠卷的总金额
		if(!ObjectUtils.isEmpty(conponId)){
			//修改优惠券状态
			ReqData couponReqData = new ReqData();
			couponReqData.putValue("user_id", userInfo.getId(), SystemConstant.REQ_PARAMETER_EQ);
			couponReqData.putValue("coupon_id", conponId, SystemConstant.REQ_PARAMETER_EQ);
			couponReqData.putValue("status", SystemConstant.COUPON_USER.STATUS_NO_USE, SystemConstant.REQ_PARAMETER_EQ);
			List<CouponUser> couponList = couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(couponReqData));
			if (couponList.size() > 0 ) {
				CouponUser couponUser = couponList.get(0);
				Coupon coupon = couponMapper.selectByPrimaryKey((couponUser.getCouponId())); //查询优惠券
				sumPrice = sumPrice.subtract(coupon.getCouponValue());
				if (sumPrice.compareTo(new BigDecimal("0")) == -1) {  //抵用券的金额大于订单商品金额  将订单金额置为0
					sumPrice = new BigDecimal("0");
				}
				couponUser.setStatus(SystemConstant.COUPON_USER.STATUS_USED); // 修改优惠券使用状态
				couponUserMapper.updateByPrimaryKey(couponUser);
				order.setCouponId(couponUser.getId()); //设置优惠券id
			}
		}
		
		order.setTotal(sumPrice);
		order.setUsedPoints(sumPoints);
		//7.设置订单默认状态
		order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_UNPAID);
		order.setStatus(SystemConstant.ORDER.STATUS_UNPAID);
		order.setIsDel(SystemConstant.ORDER.IS_DEL_NOT_DELETE);
		order.setCreateTime(DateUtil.date());
		order.setReturnStatus(SystemConstant.ORDER.RETURN_STATUS_NO_REFUND);
		order.setNo(getNo(orderExMapper.findMaxNum()));
		orderMapper.insertSelective(order);
		//8.添加到订单商品表
		for (PcGoodsDto pcGoodsDto : findShoppingCart) {
			/*if (!ObjectUtils.isEmpty(pcGoodsDto.getActivityType()) && !ObjectUtils.isEmpty(pcGoodsDto.getActivityId())) {
				if (pcGoodsDto.getActivityType().equals(SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY)) { //买即赠活动
					//插入赠品商品信息
					PcGoodsDto parentGoods = new PcGoodsDto();
					parentGoods.setActId(pcGoodsDto.getActId());
					parentGoods.setActivityName(pcGoodsDto.getActivityName());
					parentGoods.setActivityPrice(new BigDecimal("0.00"));
					parentGoods.setActPoints(pcGoodsDto.getActPoints());
					parentGoods.setAlarmNum(pcGoodsDto.getAlarmNum());
					parentGoods.setNum(pcGoodsDto.getNum());
					parentGoods.setId(pcGoodsDto.getPresentId());
					parentGoods.setSumPoints(pcGoodsDto.getSumPoints());
					parentGoods.setSumPrice(new BigDecimal("0.00"));
					Goods goods = gMapper.selectByPrimaryKey(pcGoodsDto.getPresentId());
					parentGoods.setName(goods.getFullName()+"(买即赠赠送商品)");
					parentGoods.setCategoryName(goods.getCategoryName());
					parentGoods.setCode(goods.getCode());
					insertOrderGoods(parentGoods,order.getId());
				}
			}*/
			insertOrderGoods(pcGoodsDto,order.getId());
		}
		//9.删除购物车相关的商品信息
		if("匿名".equals(userInfo.getRealName())){
			cartService.deleteAllShoppingCart(response,goodsIds);
		}else{
			reqData.putValue("user_id", userInfo.getId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", goodsIds,SystemConstant.REQ_PARAMETER_IN);
			shoppingCartMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		}
		return order;
	}
	
	/**
	 * 拼购抽奖和三免一条件判断
	 * @param sessionInfo
	 * @param shopping
	 */
	@Override
	public RespData judgeNewActivityGoods(Long userId, String goodsId){
		RespData resp = new RespData();
		String msg = null;
		Long[] ids = SystemConstant.XMYWAP.ACTIVITY_ID;
		//Long[] ids = new Long[]{(long) 7158,(long) 7159,(long) 7160,(long) 7161,(long) 7162,(long) 7163,(long) 7164,(long) 7165,(long) 7166,(long) 7167};
		String[] gid = goodsId.split(",");
		boolean containActivityGoods = false ;
		boolean containOtherGoods = false ;
		List<OrderDto> orderList = null;
		List<PcGoodsDto>  findShoppingCart = null;
		if(userId != null){
			orderList = getOrderByGoodsId(userId, goodsId);
			findShoppingCart = getShippingCart(userId, goodsId);
		}else{
			for (Long l : ids) {
				for(String s : gid){
					if(Long.parseLong(s) == l.longValue()){
						//未登录用户
						throw new BusinessException(999, "亲，您未登录香满圆，请登陆后进行购买。");
						//msg = "亲，您未登录香满圆，请登陆后进行购买。";
					}
				}
			}
		}
		if(userId != null){
			for (OrderDto orderDto : orderList) {//查找订单中的商品
				if(orderDto.getOrderGoods() != null){
					for (OrderGoods goods : orderDto.getOrderGoods()) {
						for (Long l : ids) {
							if(goods.getId().longValue() == l.longValue()){//该商品是3免1或抽奖时
								if(onLineActivityService.judgeLuckWap(goods.getId())){//为抽奖商品
									msg = "您已经购买过此商品，请购买其他商品！";
									resp.setData(msg);
									return resp;
								}else{
									msg = "您已经购买过该商品，不能重复购买哦！";
									resp.setData(msg);
									return resp;
								}
							}
						}
					}
				}
			}
			if(findShoppingCart != null){
				for (PcGoodsDto pcGoodsDto : findShoppingCart) {
					for (Long l : ids) {
						if(pcGoodsDto.getId().longValue() == l.longValue()){
							Map<String, Integer> activtyMap = commonNewActitvityService.chekFeeFerightGodos();
							if( activtyMap.get(pcGoodsDto.getId()) != null && activtyMap.get(pcGoodsDto.getId()) >= 100){
								msg = "此商品已售罄，请购买其他商品！";
								resp.setData(msg);
								return resp;
							}
							if(onLineActivityService.judgeLuckWap(pcGoodsDto.getId())){
								containActivityGoods = true;
							}
							if(pcGoodsDto.getCartNum() > 1){
								msg = "您购买活动商品数量超限！";
								resp.setData(msg);
								return resp;
							}
						}
					}
					if(!containActivityGoods){
						containOtherGoods = true;
					}
				}
			}
		/*	if(containOtherGoods && containActivityGoods){
				//throw new BusinessException("拼购商品只能单独购买，请先结算其他商品后继续购买。");
				msg = "该商品只能单独购买，请先结算其他商品后继续购买。";
				resp.setData(msg);
				return resp;
			}*/
		}
		resp.setData(msg);
		return resp;
	};
	
	/**
	 * 活动期间时，传递活动标识
	 * @param userId
	 * @param order    
	 * @return void    
	 * Date:2017年11月13日 上午8:00:26 
	 * @author hexw
	 */
	private void addOrderMark(Long userId, Order order){
		String str = "";
		Integer checkUserOrder = onLineActivityService.checkUserOrder(userId);
		if(checkUserOrder>0){//满足条件了
			Integer checkDate = onLineActivityService.checkDate();
			if(checkDate>0){//在活动时间内
				//2.判断是否是重庆主城
				if("重庆市".equals(order.getProvince()) || "重庆".equals(order.getProvince())){//是大重庆范围的
					//判断是否在重庆活动时间内
					Integer checkCQDate = onLineActivityService.checkCQDate();
					if(checkCQDate>0){//在时间内 6.8折
						str+="全场优惠活动：重庆地区6.8折(请勿删除)";
						order.setBusinessRemark(str);
					}else{//不在时间内 8.8折
						str+="全场优惠活动：重庆地区非重庆活动时间8.8折(请勿删除)";
						order.setBusinessRemark(str);
					}
				}else{//不在重庆范围内的
					str = order.getBusinessRemark();
					str+="全场优惠活动：非重庆地区8.8折(请勿删除)";
					order.setBusinessRemark(str);
				}
			}
		}
	}
	
	@Override
	public OrderDto findOderyDetailById(Long orderId) {
		PageBean pageBean = new PageBean();
		ReqData reqData = new ReqData();
		reqData.putValue("id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderDto> findOrderDto = orderExMapper.findOrderDto(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		reqData.clearValue();
		OrderDto orderDto = findOrderDto.get(0);
		List<PcOrderGoodsDto> goodsDto = new ArrayList<>();
		reqData.putValue("order_id", orderDto.getId(), SystemConstant.REQ_PARAMETER_EQ);
		List<OrderGoods> selectByExample = goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (OrderGoods orderGoods : selectByExample) {
			PcOrderGoodsDto orderGoodsDto = new PcOrderGoodsDto();
			orderGoodsDto.setGoodsId(orderGoods.getGoodsId());
			orderGoodsDto.setImgPath(commonGoodsService.getFirstGoodsImg(orderGoods.getGoodsId()));
			orderGoodsDto.setName(orderGoods.getName());
			orderGoodsDto.setNum(orderGoods.getNum());
			orderGoodsDto.setPrice(orderGoods.getPrice());
			orderGoodsDto.setSumPrice(orderGoods.getSumPrice());
			orderGoodsDto.setTypeName(orderGoods.getTypeName());
			orderGoodsDto.setSupplier(orderGoods.getSupplier());
			if(!ObjectUtils.isEmpty(orderGoods.getActivityId())){
				//买即赠活动
				if(orderGoods.getActivityType().equals(SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY)){
					orderGoodsDto.setPresentName(orderGoods.getPresentGoodsName());
					BuyAndPresent activity = andPresentMapper.selectByPrimaryKey(orderGoods.getActivityId());
					orderGoodsDto.setActivityName(activity.getName());
				}
				//限时活动
				if(orderGoods.getActivityType().equals(SystemConstant.ACTIVITY.LIMIT_ACTIVITY)){
					LimitActivity limitactivity = limitActivityMapper.selectByPrimaryKey(orderGoods.getActivityId());
					orderGoodsDto.setActivityName(limitactivity.getName());
				}
			}
			goodsDto.add(orderGoodsDto);
		}
		orderDto.setOrderGoods(selectByExample);
		orderDto.setOrderGoodsDto(goodsDto);
		return orderDto;
	}
	
	/**
	 * 活动打折后价格
	 * @Description 
	 * @return
	 * @Author cj
	 * @Date 2017年11月2日上午3:30:07
	 */
	@Override
	public BigDecimal changeActivityPrice(HttpServletRequest request, UserAddrees userAddress, List<PcGoodsDto> shoppingCart, Long userId){
		
		BigDecimal sumSalePrice = new BigDecimal(0.00); 
		BigDecimal saleMoney = new BigDecimal(0.00);
		Double sale = 0.0;
		Integer integer = onLineActivityService.checkUserOrders(userId);//判断用户是否符合条件
		if(integer>0){
			Integer chekActiviDate = onLineActivityService.chekActiviDate();//判断时间是否属于活动时间
			if(chekActiviDate>0){
				//8.8折
				sale = 0.12;
				Integer cqDate = onLineActivityService.chekCqActiviDate();//判断是否是重庆活动时间
				if(cqDate>0){
					//重庆活动时间
					if("重庆市".equals(userAddress.getProvince()) || "重庆".equals(userAddress.getProvince())){
						//6.8折
						sale = 0.32;
					}
				}
				for(PcGoodsDto goods : shoppingCart){
					//查询是否包含跨境商品
					boolean online = commonGoodsService.findOnlineActivityGoods(goods.getId());
					if(online){
						ShoppingCart shoppingCarts = null;
						BigDecimal goodsSumPrice = new BigDecimal(0.00);
						ReqData reqData = new ReqData();
						reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
						reqData.putValue("goods_id", goods.getId(), SystemConstant.REQ_PARAMETER_EQ);
						// 2. 查询购物车	
						List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
						if (shoppingCartList.size() > 0) {
							shoppingCarts = shoppingCartList.get(0);
							// 3. 活动商品取活动价格
							if(ObjectUtil.isNotNull(shoppingCarts.getActivityId()) && 0 != shoppingCarts.getActivityId()) {
								goodsSumPrice = shoppingCarts.getActivityPrice().multiply(new BigDecimal(shoppingCarts.getNum()));
							} else{
								// 4. 积分商品不参与活动			
								if (ObjectUtil.isNull(shoppingCarts.getActId()) || 0 == shoppingCarts.getActId()) {
									goodsSumPrice = goods.getPrice().multiply(new BigDecimal(shoppingCarts.getNum()));
								}
							}
							saleMoney = goodsSumPrice.multiply(new BigDecimal(sale));
							sumSalePrice = sumSalePrice.add(saleMoney);
						}
					}
				}
				if(sumSalePrice.doubleValue() >= 68) sumSalePrice = new BigDecimal(68);
			}
		}
		
		return sumSalePrice;
	}

	@Override
	public Order findOrderByNo(String no) {
		
		ReqData reqData = new ReqData();
		reqData.putValue("no", no, SystemConstant.REQ_PARAMETER_EQ);
		List<Order> order = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(order.size() > 0)
			return order.get(0);
		else
			throw new BusinessException("该订单不存在");
	}

	@Override
	public BigDecimal getUserBalance(Long id) {

		//用户余额
		BigDecimal balance = new BigDecimal(0.00);
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", id, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.SHOPPING_CARD.ACTIVE, SystemConstant.REQ_PARAMETER_EQ);//已激活的
		List<ShoppingCard> card = shoppingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!ObjectUtils.isEmpty(card)){
			for (ShoppingCard shopingCard : card) {
				if(shopingCard.getBalance() != null)
					balance = balance.add(shopingCard.getBalance());
			}
		}
		
		return balance;
	}

	@Override
	public List<OrderDto> findAllOrderByUserId(Integer status, Long id,
			PageBean pageBean) {
		List<OrderDto> list = wapOrderExMapper.findOrders(new WapOrderQueryDto(id, status, pageBean.startRow(), pageBean.endRow()));
		Calendar cal = Calendar.getInstance();
		for (OrderDto order : list) {
			if(order.getStatus().intValue() == 2 && order.getShipStatus().intValue() < 5){
				cal.setTime(order.getPayTime() == null ? order.getCreateTime() : order.getPayTime());
				cal.add(Calendar.DAY_OF_YEAR, 1);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				order.setCancelEndTime(cal.getTime());
				order.setNowTime(new Date());
			}
		}
		return wapOrderExMapper.findOrders(new WapOrderQueryDto(id, status, pageBean.startRow(), pageBean.endRow()));
		
	}

	@Override
	public Order findOrderById(Long orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public Order updateOrder(Order order) {
		orderMapper.updateByPrimaryKey(order);
		return orderMapper.selectByPrimaryKey(order.getId());
	}

	@Override
	public List<OrderDto> getOrderByGoodsId(Long userId, String goodsId) {
		
		List<OrderDto> orderList = wapOrderExMapper.getOrderByGoodsId(userId,goodsId);
		
		return orderList;
	}
	

}
