package com.zfj.xmy.order.service.pc.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.LogicType;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.enu.BaseProp;
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
import com.zfj.xmy.common.persistence.dao.ShoppingCardRecordMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.dao.UserAddreesMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.persistence.pojo.app.DiscountActivityDto;
import com.zfj.xmy.common.service.CommonCouponService;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.common.service.CommonPayOrderService;
import com.zfj.xmy.common.service.CommonShopingCardService;
import com.zfj.xmy.common.service.CommonUserPointsService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppGoodsVo;
import com.zfj.xmy.order.persistence.common.dao.OrderExMapper;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.pc.dao.PcShoppCartExMapper;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcDateTime;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcOrderGoodsDto;
import com.zfj.xmy.order.service.cms.OrderService;
import com.zfj.xmy.order.service.pc.PcOrderService;
import com.zfj.xmy.order.service.pc.ShoppingCartService;
import com.zfj.xmy.util.SendSMSUtil;
/**
 * @author lij
 */
@Service
@Transactional
public class PcOrderServiceImpl extends BaseService implements PcOrderService {
	
	@Autowired
	private OrderExMapper orderExMapper;
	
	@Autowired
	private OrderGoodsMapper goodsMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
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
	private CouponUserMapper couponUserMapper;
	
	@Autowired
	private CommonPayOrderService commonPayOrderService;
	
	@Autowired
	private OnLineActivityService onLineService;
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Autowired
	private ShoppingCardRecordMapper shoppingCardRecordMapper;
	
	@Autowired
	private CommonUserPointsService commonUserPointsService;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private CommonCouponService commonCouponService;
	/**
	 * 根据用户id分页查询全部订单
	 */
	@Override
	public List<OrderDto> findAllOrderByUserId(Integer status,Long userId, PageBean pageBean) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderDto> selectByExampleAndPage =null;
		if(status.equals(0)){
			selectByExampleAndPage = orderExMapper.findOrderDto(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		}else{
			reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("status", status, SystemConstant.REQ_PARAMETER_EQ);
			selectByExampleAndPage = orderExMapper.findOrderDto(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		}
		reqData.clearValue();
		//查询订单关联的商品信息
		for (OrderDto orderDto : selectByExampleAndPage) {
			List<PcOrderGoodsDto> goodsDto = new ArrayList<>();
			reqData.putValue("order_id", orderDto.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<OrderGoods> selectByExample = goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			for (OrderGoods orderGoods : selectByExample) {
				PcOrderGoodsDto orderGoodsDto = new PcOrderGoodsDto();
				orderGoodsDto.setGoodsId(orderGoods.getGoodsId());
				orderGoodsDto.setImgPath(commonGoodsService.getFirstGoodsImg(orderGoods.getGoodsId()));
				orderGoodsDto.setName(orderGoods.getName());
				orderGoodsDto.setNum(orderGoods.getNum());
				orderGoodsDto.setTypeName(orderGoods.getTypeName());
				orderGoodsDto.setSupplier(orderGoods.getSupplier());
				if (ObjectUtil.isNotNull(orderGoods.getPoints()) && 0 != orderGoods.getPoints()) { // 积分商品
					orderGoodsDto.setPoints(orderGoods.getPoints());
					orderGoodsDto.setSumPoints(orderGoods.getSumPoints());
					orderGoodsDto.setGoodsDetailUrl("point/"+orderGoods.getGoodsId()+"/0/0?actId="+orderGoods.getActivityId());
				} else {
					Integer actType = orderGoods.getActivityType();
					Long actId = orderGoods.getActivityId() ;
					//商品详情跳转链接
					orderGoodsDto.setGoodsDetailUrl("goods/"+orderGoods.getGoodsId()+"/"+(ObjectUtil.isNull(actType)?0:actType)+"/"+(ObjectUtil.isNull(actId)? 0:actId));
					orderGoodsDto.setPrice(orderGoods.getPrice());
					orderGoodsDto.setSumPrice(orderGoods.getSumPrice());
				}
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
					orderGoodsDto.setActivityId(orderGoods.getActivityId());
					orderGoodsDto.setActivityType(orderGoods.getActivityType());
				}
				goodsDto.add(orderGoodsDto);
			}
			orderDto.setOrderGoods(selectByExample);
			orderDto.setOrderGoodsDto(goodsDto);
			reqData.clearValue();
		}
		return selectByExampleAndPage;
	}
	/**
	 * 查询订单总页数
	 */
	@Override
	public Integer findAllOrderCountPage(Integer status, Long userId, Integer pageSize) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		Integer count = 0;
		if(status.equals(0)){
			count = orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		}else{
			reqData.putValue("status", status, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
			count = orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		}
		int countPage;
		if (count % pageSize == 0) {
			countPage = count / pageSize;
		} else {
			countPage = count / pageSize + 1;
		}
		return countPage;
	}
	/**
	 * 查询待支付订单总数量
	 */
	@Override
	public Integer findUnpaidOrderCount(Long userId,Integer status) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		if(!status.equals(0)){
			reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("status", status, SystemConstant.REQ_PARAMETER_EQ);
		}
		return orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	/**
	 * 筛选订单
	 */
	@Override
	public List<OrderDto> findScreenOrder(Integer status, Long userId, String no,PageBean pageBean) {
		Map<String,Object> param = new HashMap<>();
		param.put("userid", userId);
		param.put("search", "%"+no+"%");
		List<OrderDto> selectByExampleAndPage =null;
		if(status.equals(0)){
			selectByExampleAndPage = orderExMapper.findOrderDtoByCondition(param, pageBean.getRowBounds());
		}else{
			param.put("status", status);
			selectByExampleAndPage = orderExMapper.findOrderDtoByCondition(param, pageBean.getRowBounds());
		}
		ReqData reqData = new ReqData();
		reqData.clearValue();
		//查询订单关联的商品信息
		for (OrderDto orderDto : selectByExampleAndPage) {
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
			reqData.clearValue();
		}
		return selectByExampleAndPage;
	}
	/**
	 * 获取前台选择时间
	 */
	@Override
	public List<PcDateTime> getPcDateTime() {
		List<DateTime> peisong = new ArrayList<>();
		List<PcDateTime> pcDateList = new ArrayList<>();
		DateTime date = DateUtil.parse(DateUtil.date().toString(),"yyyy-MM-dd");
		for(int i = 1; i<=7;i++){
			peisong.add(DateUtil.offsetDay(date, i));
		}
		for (DateTime dateTime : peisong) {
			PcDateTime pcDateTime = new PcDateTime();
			pcDateTime.setMonth(dateTime.toString().substring(5, 7));//得到月份
			pcDateTime.setDay(dateTime.toString().substring(8, 10));//得到日期
			pcDateTime.setWeek(dateTime.dayOfWeek());//得到周几
			pcDateTime.setDateTime(dateTime);
			pcDateList.add(pcDateTime);
		}
		return pcDateList;
	}
	/**
	 * 查询发票类容 
	 */
	@Override
	public List<String> getInvoice() {
		List<String> invoiceList = new ArrayList<>();
		ReqData reqData = new ReqData();
		reqData.putValue("vid", 7, SystemConstant.REQ_PARAMETER_EQ);//7词汇表筛选条件
		List<TermData> selectByExample = dataMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData), reqData.getRowBounds());
		TermData termData = selectByExample.get(0);
		String des = termData.getDes();
		String[] split = des.split("--");
		for (String string : split) {
			invoiceList.add(string);
		}
		
		return invoiceList;
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
					// 活动商品
					pcGoodsDto.setSumPrice(pcGoodsDto.getActivityPrice().multiply(BigDecimal.valueOf(Long.parseLong(pcGoodsDto.getCartNum().toString()))));
			}
			pcGoodsDto.setImgPath(commonGoodsService.getFirstGoodsImg(pcGoodsDto.getId()));
		}
		return findShoppingCart;
	}
	
	@Override
	public Map<String,Object> getFreight(String province,String area,String goodsId,String sumPrice,Long userId) {
		int isSuccess = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		String activityRemark = "" ; //活动描述
		// 1. 获得活动优惠价格
		BigDecimal disCountPrice = new BigDecimal("0");
		if (ObjectUtil.isNotNull(userId)) {
			BigDecimal goodsPrice = new BigDecimal("0");
			if (ObjectUtil.isNotNull(userId)) {
				List<Object> ids = new ArrayList<Object>();
				String[] idArray =  goodsId.split("\\,");
				for (String id:idArray) {
					ids.add(Long.parseLong(id));
				}
				goodsPrice = commonGoodsService.getDiscountSumPrice(ids, userId) ;  //不包含跨境专区和企业定制商品
				DiscountActivityDto dto = onLineService.getDiscountPrice(goodsPrice,userId,province); 
				disCountPrice = dto.getDiscountPrice();
				activityRemark = dto.getActivityRemark();
			}
		}
		// 2. 计算商品总金额
		BigDecimal goodsAllMoney = new BigDecimal(sumPrice);
		goodsAllMoney = goodsAllMoney.subtract(disCountPrice);
		// 3. 计算商品总重量
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
					throw new BusinessException("商品信息错误");
				}
			}
		}
	    BigDecimal freight = FreightUtil.getFreight(province,area, weight,goodsAllMoney);
	    //拼购抽奖活动运费为0
	    if(goodsId.contains(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.toString())){
	    	freight = new BigDecimal(0);
	    }
	    //免邮商品运费为0
	    String[] split = goodsId.split(",");
	    for (String string2 : split) {
	    	if(Arrays.asList(SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS).contains(Long.parseLong(string2))){
	    		freight = new BigDecimal(0);
	    	}
		}
	    BigDecimal toltalPrice = goodsAllMoney.add(freight);
	    DecimalFormat deicmalFormat =new DecimalFormat("#.0");
	    if(!ObjectUtils.isEmpty(userId)){
	    	boolean checkOrder = commonPayOrderService.checkOrder(goodsId, userId);
	    	if(checkOrder){
	    		toltalPrice = toltalPrice.subtract(new BigDecimal(15));
	    		disCountPrice = disCountPrice.add(new BigDecimal(15));
	    		activityRemark="满额减活动！";
	    	}
	    }
	    result.put("isSuccess", isSuccess);
	    result.put("freight", freight);
	    result.put("toltalPrice", deicmalFormat.format(toltalPrice));
	    result.put("disCountPrice", disCountPrice);
	    result.put("activityRemark", activityRemark);
		return result;
	}
	
	
	/**
	 * 添加订单
	 */
	@Override
	public Order addOrder(HttpServletResponse response,String phone,String address,String conName,String taxperNo,String companyName,String idCard,Long addressId, 
			String dateTime, String invoice, String goodsIds, String remark, String conponId,String provinceAddress,String cityAddress,String countyAddress,UserInfo userInfo) {
		BigDecimal sumPrice = new BigDecimal("0.00");
		int sumPoints=0;
		Order order = new Order();
		List<PcGoodsDto> findShoppingCart = null;
		//1.查询用户收货地址
		ReqData reqData = new ReqData();
		if("匿名".equals(userInfo.getRealName())){
			order.setConsigneeName(conName);
			order.setConsigneeIdcard(idCard);
			order.setConsigneeAddress(address);
			order.setConsigneePhone(phone);
		}else{
			UserAddrees userAddrees = addreesMapper.selectByPrimaryKey(addressId);
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
			order.setConsigneeAddress(consigneeAddress);
			order.setConsigneePhone(userAddrees.getMobilePhone());
			order.setConsigneeIdcard(idCard);
		}
		order.setParsetTime(DateUtil.parse(dateTime));
		order.setInvoiceContent(invoice);
		order.setTaxpayerNum(taxperNo);  //纳税人识别号
		order.setCompanyName(companyName);
		if(!ObjectUtils.isEmpty(companyName)){
			order.setInvoiceType(1);//发票公司
		}
		order.setOrderRemark(remark);
		order.setPaymentName(userInfo.getRealName());
		order.setUserId(userInfo.getId());
		String serialNumber = UUIDUtil.generateToken(); //生成序列号 - 每次支付支成一次
		order.setSerialNumber(serialNumber);
		order.setProvince(provinceAddress);
		order.setCity(cityAddress);
		order.setArea(countyAddress);
		//2.查询购物车商品信息
		if("匿名".equals(userInfo.getRealName())){
			findShoppingCart = cartService.findUnLoadShoppingCart(goodsIds);
		}else{
			findShoppingCart = getShippingCart(userInfo.getId(), goodsIds);
		}
		if (findShoppingCart.size() > 0) {
		//3. 计算订单商品的总金额和消耗的总积分
			for (PcGoodsDto pcGoodsDto : findShoppingCart) {
				if(pcGoodsDto.getSumPoints()==null||pcGoodsDto.getSumPoints()==0){
					sumPrice = sumPrice.add(pcGoodsDto.getSumPrice());
				}
				if(!"匿名".equals(userInfo.getRealName())&&(pcGoodsDto.getSumPoints()!=null&&pcGoodsDto.getSumPoints()!=0)){
					sumPoints=sumPoints+pcGoodsDto.getSumPoints();
				}
			}
		//4. 计算订单6.8折 优惠价格及运费
			Map<String,Object> reslut = getFreight(provinceAddress, countyAddress, goodsIds, sumPrice.toString(), userInfo.getId()); //计算运费
			if (Integer.parseInt(reslut.get("isSuccess").toString()) == 0) { 
				BigDecimal disCountPrice = new BigDecimal("0");
				disCountPrice = new BigDecimal(reslut.get("disCountPrice").toString());
				if (disCountPrice.doubleValue() > 0) {
					order.setBusinessRemark(reslut.get("activityRemark").toString());
				}
				order.setDiscountPrice(new BigDecimal(reslut.get("disCountPrice").toString()));
				order.setFreight(new BigDecimal(reslut.get("freight").toString()));  //设置运费
				sumPrice = sumPrice.subtract(disCountPrice) ;//订单总额减去优惠的金额
			}
		//5. 计算扣除优惠卷的总金额 （注* 优惠券不能抵用运费）
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
					couponUser.setUseTime(new Date());
					couponUser.setStatus(SystemConstant.COUPON_USER.STATUS_USED); // 修改优惠券使用状态
					couponUserMapper.updateByPrimaryKey(couponUser);
					order.setCouponId(couponUser.getId()); //设置优惠券id *存的是coupon_user的id 
				}
			}
			//6. 增加邮费
			sumPrice = sumPrice.add(order.getFreight());
			order.setTotal(sumPrice);
			order.setUsedPoints(sumPoints);
			//7. 设置订单默认状态
			order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_UNPAID);
			order.setStatus(SystemConstant.ORDER.STATUS_UNPAID);
			order.setIsDel(SystemConstant.ORDER.IS_DEL_NOT_DELETE);
			order.setCreateTime(DateUtil.date());
			order.setReturnStatus(SystemConstant.ORDER.RETURN_STATUS_NO_REFUND);
			String orderNo = orderExMapper.findMaxNum() ;
			if (StringUtil.isEmpty(orderNo)) {
				orderNo = SystemConstant.ORDER.INIT_NO;
			}
			order.setNo(getNo(orderNo));
			order.setOrderSource(SystemConstant.ORDER.ORDER_SOURCE_PC);
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
						parentGoods.setNum(pcGoodsDto.getCartNum());
						parentGoods.setPrice(new BigDecimal("0.00"));
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
			//10校验商品是否正确
			String[] split = goodsIds.split(",");
			for (String string : split) {
				int i = 0;//代表出现的次数
				if(!"0".equals(string)){
					for (PcGoodsDto pcGoodsDto : findShoppingCart){
						if(string.equals(pcGoodsDto.getId().toString())){
							i++;
						}
					}
					if(i!=1){
						throw new BusinessException("订单商品异常请重新提交！");
					}
				}
			}
		} else {
			order = null; //订单商品为空
		}
		return order;
	}
	
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
		if (0 < pcGoodsDto.getActPoints()) { //积分商品
			orderGoods.setActivityId(pcGoodsDto.getActId().longValue());
			orderGoods.setActivityType(SystemConstant.ACTIVITY.POINTS_ACTIVITY);
		} else {
			orderGoods.setActivityId(pcGoodsDto.getActivityId());
			orderGoods.setActivityType(pcGoodsDto.getActivityType());
		}
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
	
	
	
	@Override
	public Order updateOrder(Long orderId, Integer payMethod, BigDecimal balancePay,Long userId) {
		//1.修改订单相关信息 
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if(order.getShipStatus()>=SystemConstant.ORDER.SHIP_STATUS_SUPPLY){
			throw new BusinessException("该订单已支付请勿多次支付！");
		}
		if(order == null) throw new BusinessException("未查询到该订单");
		order.setPay(balancePay);
		order.setActual(order.getTotal().subtract(balancePay));
		order.setStatus(SystemConstant.ORDER.STATUS_DELIVERY);
		order.setAccPoints(order.getTotal().longValue());//赠送的积分
		if(payMethod.equals(SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY)){//货到付款
			if (!FreightUtil.checkMainCity(order.getProvince(), order.getArea())) {
				throw new BusinessException("重庆主城区外的地址不支持货到付款！");
			}
			if(order.getShipStatus() == 1) throw new BusinessException("该订单已提交");
			ReqData reqData = new ReqData();
			reqData.putValue("goods_id", SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("order_id", order.getId(), SystemConstant.REQ_PARAMETER_EQ);
			int countByExample = orderGoodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			if(countByExample>=1){
				throw new BusinessException("此活动商品不支持货到付款！");
			}
			order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_AUDIT);//待审核
		}else{//购物卡支付
			order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_SUPPLY);//供货确认
			order.setPayTime(new Date());
		}
		order.setPayTime(new Date());
		order.setPayType(payMethod);
		orderMapper.updateByPrimaryKey(order);
		if(userId != 0){
			//2.修改用户的余额
			UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
			//2.1修改用户购物卡余额
			commonShopingCardService.updateShopingCardBalance(userId, balancePay,orderId);
			//3.添加到用户流水
			UserSpendPoints userSpendPoints = new UserSpendPoints();
			userSpendPoints.setConsole(userInfo.getRealName());
			userSpendPoints.setUserId(userId);
			userSpendPoints.setMoneyPoint(balancePay);
			userSpendPoints.setType(SystemConstant.userSpendPoints.TYPE_SPEND);
			userSpendPoints.setSpendType(SystemConstant.userSpendPoints.SPEND_TYPE_SPEND);
			userSpendPoints.setRemarks("购买商品订单编号为："+order.getNo());
			userSpendPoints.setCreatTime(new Date());
			userSpendPoints.setNo(order.getNo());
			userSpendPointsMapper.insertSelective(userSpendPoints);
		}//10. 发送下单成功的短信
		//赠送优惠卷
		commonCouponService.bindBuyCouponGoods(orderId);
		try {
			SendSMSUtil.sendInsertOrderSuccess(order.getConsigneePhone(), order.getNo());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return order;
	}
	
	
	@Override
	public void shoppingCardPay(Long orderId,BigDecimal payTotalPrice,Long userId){
		// 1. 查询用户
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (ObjectUtil.isNotNull(userInfo)) {
			Order order = orderMapper.selectByPrimaryKey(orderId);
			if (ObjectUtil.isNotNull(order)) {
		// 2. 判断用户余额是否大于订单金额(支付金额小于等于用户余额 支付金额大于等于订单总金额 )		
				if (payTotalPrice.compareTo(userInfo.getBalance())<= 0 && payTotalPrice.compareTo(order.getTotal()) >= 0) {
					order.setPay(payTotalPrice);
					order.setStatus(SystemConstant.ORDER.STATUS_DELIVERY);
					order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_SUPPLY);//供货确认
					order.setPayTime(new Date());
					order.setPayType(SystemConstant.ORDER.PAY_TYPE_GIFTCARD);
					orderMapper.updateByPrimaryKey(order);
		// 3. 修改用户购物卡余额
					commonShopingCardService.updateShopingCardBalance(userId, payTotalPrice,orderId);
		// 4.添加到用户流水
					UserSpendPoints userSpendPoints = new UserSpendPoints();
					userSpendPoints.setConsole(userInfo.getRealName());
					userSpendPoints.setUserId(userId);
					userSpendPoints.setMoneyPoint(payTotalPrice);
					userSpendPoints.setType(SystemConstant.userSpendPoints.TYPE_SPEND);
					userSpendPoints.setSpendType(SystemConstant.userSpendPoints.SPEND_TYPE_SPEND);
					userSpendPoints.setRemarks("购买商品订单编号为："+order.getNo());
					userSpendPoints.setCreatTime(new Date());
					userSpendPoints.setNo(order.getNo());
					userSpendPointsMapper.insertSelective(userSpendPoints);
				}
			}
		}
		
	}
	
	@Override
	public Integer findOrderStatusCount(Long userId, int status) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", status,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		int count=orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return count;
	}
	@Override
	public Integer findOrderDeliveryCount(Long userId) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.ORDER.STATUS_DELIVERY,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		int count=orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return count;
	}
	/**
	 * 根据id查询单个订单
	 */
	@Override
	public Order findOrderByOrderId(Long orderId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		String serialNumber = UUIDUtil.generateToken(); //生成序列号 - 每次支付支成一次
		order.setSerialNumber(serialNumber);
		orderMapper.updateByPrimaryKeySelective(order);
		return orderMapper.selectByPrimaryKey(orderId);
	}
	@Override
	public List<Order> findRefundOrder(Long userId) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("return_status", SystemConstant.ORDER.RETURN_STATUS_NO_REFUND,SystemConstant.REQ_PARAMETER_NE);
		return orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	@Override
	public void findRefundOrderByPage(Long userId, PageBean pageBean) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", userId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("return_status", SystemConstant.ORDER.RETURN_STATUS_NO_REFUND,SystemConstant.REQ_PARAMETER_NE);
		List<Order> list=orderMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list);
		int count=orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}
	/**
	 * 查询筛选条件的总条数
	 */
	@Override
	public Integer findScreenOrderCount(Long userId, Integer status, String no) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("no", no, SystemConstant.REQ_PARAMETER_CN);
		if(!status.equals(0)){
			reqData.putValue("status", status, SystemConstant.REQ_PARAMETER_EQ);
		}
		int count = orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return count;
	}
	/**
	 * 作废订单
	 */
	@Override
	public void deleteOrderByOrder(Long orderId) {
		ReqData reqData = new ReqData();
		reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		Order order = orderMapper.selectByPrimaryKey(orderId);
		reqData.clearValue();
		order.setUpdateTime(new Date());
		order.setIsDel(SystemConstant.ORDER.IS_DEL_DELETE);
		orderMapper.updateByPrimaryKey(order);
		BigDecimal pay = order.getPay();
		if(order.getShipStatus()>=SystemConstant.ORDER.SHIP_STATUS_SUPPLY || SystemConstant.ORDER.SHIP_STATUS_AUDIT==order.getShipStatus() ){
			if(pay != null && pay.compareTo(new BigDecimal(0))==1){
				reqData.putValue("user_id", order.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("status", SystemConstant.SHOPPING_CARD.ACTIVE, SystemConstant.REQ_PARAMETER_EQ);
				List<ShoppingCard> selectByExample = shoppingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				for (ShoppingCard shopingCard : selectByExample) {
					//该购物卡能充值的金额
					BigDecimal addValue = shopingCard.getTotalValue().subtract(shopingCard.getBalance());
					//1.订单购物卡支付的金额是与能充值的金额比较状态
					int to = pay.compareTo(addValue);//-1小于 0 等于 1 大于
					if(to ==-1 || to == 0){//购物卡消费金额小于或等于了改购物卡能充值的金额
						shopingCard.setBalance(shopingCard.getBalance().add(pay));
						shoppingCardMapper.updateByPrimaryKey(shopingCard);
						ShoppingCardRecord shoppingCardRecord = new ShoppingCardRecord();
						shoppingCardRecord.setShoppingCardId(shopingCard.getId());
						shoppingCardRecord.setUserId(order.getUserId());
						shoppingCardRecord.setUseTime(new Date());
						shoppingCardRecord.setValue(pay);
						shoppingCardRecord.setOrderId(orderId);
						shoppingCardRecord.setRemark("订单作废购物卡金额返还"+pay+"元");
						shoppingCardRecord.setType(2);//返回
						shoppingCardRecordMapper.insertSelective(shoppingCardRecord);
						break;
					}else{
						if(pay.compareTo(new BigDecimal(0))==1){
							pay = pay.subtract(addValue);//修改应该充值的购物卡金额
							shopingCard.setBalance(shopingCard.getBalance().add(addValue));
							shoppingCardMapper.updateByPrimaryKey(shopingCard);
							ShoppingCardRecord shoppingCardRecord = new ShoppingCardRecord();
							shoppingCardRecord.setShoppingCardId(shopingCard.getId());
							shoppingCardRecord.setUserId(order.getUserId());
							shoppingCardRecord.setUseTime(new Date());
							shoppingCardRecord.setValue(addValue);
							shoppingCardRecord.setOrderId(orderId);
							shoppingCardRecord.setType(2);//返回
							shoppingCardRecord.setRemark("订单作废购物卡金额返还"+addValue+"元");
							shoppingCardRecordMapper.insertSelective(shoppingCardRecord);
						}
					}
				}
			}
		}
		//3.为存在积分消费的退回积分
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(order.getUserId());
		//3.1判断是否是匿名支付
		if(!ObjectUtils.isEmpty(userInfo)){
			//3.2不是匿名购买是返还积分
			Integer usedPoints = order.getUsedPoints();
			if(!ObjectUtils.isEmpty(usedPoints)){
				if(usedPoints>0){
					//3.3存在积分购买时
					userInfo.setAccPoints(userInfo.getAccPoints()+usedPoints);
					userInfo.setUpdateTime(new Date());
					userInfoMapper.updateByPrimaryKey(userInfo);
					UserSpendPoints spendPoints = new UserSpendPoints();
					spendPoints.setUserId(order.getUserId());
					spendPoints.setMoneyPoint(new BigDecimal(order.getTotal().intValue()));
					spendPoints.setCreatTime(new Date());
					spendPoints.setNo(order.getNo());
					spendPoints.setSign(1);
					spendPoints.setType(2);//积分
					spendPoints.setRemarks("订单作废返还积分："+order.getTotal().intValue());
					userSpendPointsMapper.insert(spendPoints);
				}
			}
		}
		
		//orderMapper.deleteByPrimaryKey(orderId);
	}
	/**
	 * @param maxNo
	 * @return String
	 * @author 生成订单编号
	 * @date 2017年9月8日 上午10:21:58
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
	/**
	 * 批量确认收货
	 */
	@Override
	public void updateOrderByOrderId(String orderIds) {
		ReqData reqData = new ReqData();
		reqData.putValue("id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		List<Order> selectByExample = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (Order order : selectByExample) {
			order.setStatus(SystemConstant.ORDER.STATUS_WAITTING_COMMENT);
			order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_ORDER_FINISH);
			orderMapper.updateByPrimaryKey(order);
			commonUserPointsService.updateUserSpendPoints(order.getId());
		}
	}
	@Override
	public OrderDto findOderyDetailById(Long orderId,Long userId) {
		PageBean pageBean = new PageBean();
		ReqData reqData = new ReqData();
		reqData.putValue("id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderDto> findOrderDto = orderExMapper.findOrderDto(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		reqData.clearValue();
		if(findOrderDto.size() <= 0){
			throw new BusinessException("该订单不存在");
		}
		OrderDto orderDto = findOrderDto.get(0);
		if(orderDto.getStatus().intValue() == 2 && orderDto.getShipStatus().intValue() < 5){
			Calendar cal = Calendar.getInstance();
			cal.setTime(orderDto.getPayTime() == null ? orderDto.getCreateTime() : orderDto.getPayTime());
			cal.add(Calendar.DAY_OF_YEAR, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			orderDto.setCancelEndTime(cal.getTime());
			orderDto.setNowTime(new Date());
		}
		List<PcOrderGoodsDto> goodsDto = new ArrayList<>();
		reqData.putValue("order_id", orderDto.getId(), SystemConstant.REQ_PARAMETER_EQ);
		List<OrderGoods> selectByExample = goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		BigDecimal goodsTotalPrice = new BigDecimal(0);
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
			orderGoodsDto.setPoints(orderGoods.getPoints());
			orderGoodsDto.setSumPoints(orderGoods.getSumPoints());
			if(!ObjectUtils.isEmpty(orderGoods.getActivityId())  ){ 
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
			if(!ObjectUtils.isEmpty(orderGoodsDto.getSumPoints())){
				if (orderGoodsDto.getSumPoints() == 0) {
					goodsTotalPrice = goodsTotalPrice.add(orderGoods.getSumPrice());
				}
			}
			goodsDto.add(orderGoodsDto);
		}
		orderDto.setGoodsTotalPrice(goodsTotalPrice);//订单商品总金额
		orderDto.setOrderGoods(selectByExample);
		orderDto.setOrderGoodsDto(goodsDto);
		orderDto.setIsPaySuccess(commonPayOrderService.getIsPaySuccess(orderId)); // 是否支付成功 0 支付成功 1 支付失败
		if (ObjectUtil.isNotNull(orderDto.getCouponId())) {
			CouponUser couponUser = couponUserMapper.selectByPrimaryKey(orderDto.getCouponId());
			if(!ObjectUtils.isEmpty(couponUser)){
				Coupon coupon = couponMapper.selectByPrimaryKey(couponUser.getCouponId());
				if (ObjectUtil.isNotNull(coupon)) {
					orderDto.setCouponName(coupon.getName()); //优惠券名称
					orderDto.setCouponValue(coupon.getCouponValue()); //优惠券面值
				}
			}
		}
		return orderDto;
	}
	/**
	 * 订单商品ID
	 */
	@Override
	public String getOrderGoodsId(Long orderId) {
		String goodsIds = "0";
		ReqData reqData = new ReqData();
		reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderGoods> list = goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (OrderGoods orderGoods : list) {
			goodsIds += ","+orderGoods.getGoodsId();
		}
		return goodsIds;
	}
	
	@Override
	public Order getOrderById(Long id){
		return orderMapper.selectByPrimaryKey(id);
	}
	@Override
	public Integer isContainsSpecileGoods(Long orderId) {
		ReqData reqData = new ReqData();
		reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID, SystemConstant.REQ_PARAMETER_EQ);
		int countByExample = orderGoodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(countByExample>=1){
			return 0;
		}
		return 1;
	}

	
}
