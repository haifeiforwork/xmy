package com.zfj.xmy.order.service.cms.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.SmsLogMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonPushUtilService;
import com.zfj.xmy.common.service.CommonUserPointsService;
import com.zfj.xmy.order.persistence.cms.dao.OrderExcleMapper;
import com.zfj.xmy.order.persistence.cms.pojo.dto.OrderExcleDto;
import com.zfj.xmy.order.persistence.common.dao.OrderExMapper;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.service.cms.OrderPathService;
import com.zfj.xmy.order.service.cms.OrderService;
import com.zfj.xmy.order.service.cms.UserAddreesService;
import com.zfj.xmy.order.service.pc.PcOrderService;
import com.zfj.xmy.util.LogisticsUtil;
import com.zfj.xmy.util.PushUtil;
import com.zfj.xmy.util.SendSMSUtil;
import com.zfj.xmy.util.logistics.GoodsVo;
import com.zfj.xmy.util.logistics.LogisticsOrder;
/**
 * 
 * 
 * @author ljie
 *6.14
 */
@Service
public class OrderServiceImpl extends BaseService implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderPathService pathService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserAddreesService userAddreesService;
	
	@Autowired
	private OrderExMapper orderExMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private SmsLogMapper smsLogMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderExcleMapper orderExcleMapper;
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	@Autowired
	private PcOrderService pcOrderService;
	
	@Autowired
	private CommonPushUtilService commonPushUtilService;
	
	@Autowired
	private CommonUserPointsService commonUserPointsService;
	
	//分页查询全部订单
	@Override
	public void findAllorders(PageBean pageBean, ReqData reqData) {
		CriteriaParameter parameter=ReqUtil.reqParameterToCriteriaParameter(reqData);
		List<Order> data = orderMapper.selectByExampleAndPage(parameter, pageBean.getRowBounds());
		pageBean.setData(data);
		pageBean.setTotalCount(orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
	}
	//查询订单全部总记录数
	@Override
	public int findAllorderCount(ReqData reqData) {
		
		return orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	//查询单个订单
	@Override
	public Order getOrder(ReqData reqData) {
		Order order = orderMapper.selectByPrimaryKey((long)reqData.getValue("id"));
		return order;
	}
	//修改单个订单状态
	@Override
	public int updateOneOrdeStatus(ReqData reqData,Order order) {
		
		int i = orderMapper.updateByExampleSelective(order, ReqUtil.reqParameterToCriteriaParameter(reqData));
		return i;
	}
	//根据条件查询全部订单
	@Override
	public List<Order> findAll(ReqData reqData) {
		List<Order> list = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	/**
	 * 修改订单状态并添加订单轨迹
	 */
	@Override
	public void updateOneStatus(ReqData reqData, Order order, int status,SysUser sysUser) {
		switch (status) {
		case SystemConstant.ORDER.SHIP_STATUS_SUPPLY:
			pathService.addOrderPath(order.getId(), "审核通过", sysUser);
			//订单前台显示状态设置为待发货
			order.setStatus(2);
			break;
		case SystemConstant.ORDER.SHIP_STATUS_PREPARE:
			pathService.addOrderPath(order.getId(), "扩展时间", sysUser);
			break;
		case SystemConstant.ORDER.SHIP_STATUS_DELIVERY:
			order.setPrepareTime(new Date());//备货确认时间
			pathService.addOrderPath(order.getId(), "备货确认", sysUser);
			break;
		case SystemConstant.ORDER.SHIP_STATUS_ORDER_FINISH:
			pathService.addOrderPath(order.getId(), "订单完成", sysUser);
			//订单前台显示状态显示为待评价
			order.setStatus(4);
			//订单完成后添加对应的积分
			commonUserPointsService.updateUserSpendPoints(order.getId());
			break;
		default:
			break;
		}
		//orderMapper.updateByExampleSelective(order, ReqUtil.reqParameterToCriteriaParameter(reqData));
		orderMapper.updateByPrimaryKey(order);
	}
	/**
	 * 批量修改订单装并添加订单轨迹
	 */
	@Override
	public void updateAllStatus(ReqData reqData, String orderIds, int status, SysUser sysUser) {
		reqData.putValue("id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		List<Order> all = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)) ;
		reqData.removeValue("id");
		for (Order order : all) {
			order.setShipStatus(status);
			orderService.updateOneStatus(reqData, order, status, sysUser);
		}
		
	}
	/**
	 * 订单修改收货人信息
	 */
	@Override
	public void updateConsignee(ReqData reqData, Order order, SysUser sysUser) {
		reqData.putValue("id", order.getId(), SystemConstant.REQ_PARAMETER_EQ);
		Order oldOrder = orderService.getOrder(reqData);
		oldOrder.setConsigneeAddress(order.getConsigneeAddress());
		oldOrder.setConsigneeName(order.getConsigneeName());
		oldOrder.setConsigneePhone(order.getConsigneePhone());
		oldOrder.setOrderRemark(order.getOrderRemark());
		orderService.updateOneOrdeStatus(reqData, oldOrder);
		pathService.addOrderPath(order.getId(), "修改收货人地址", sysUser);
	}
	/**
	 * 修改配送方式
	 */
	@Override
	public void updateDeliveryMethod(ReqData reqData, Order order, SysUser sysUser) {
		reqData.putValue("id", order.getId(), SystemConstant.REQ_PARAMETER_EQ);
		Order oldOrder = orderService.getOrder(reqData);
		oldOrder.setDeliveryMethod(order.getDeliveryMethod());
		oldOrder.setBusinessRemark(order.getBusinessRemark());
		orderService.updateOneOrdeStatus(reqData, oldOrder);
		pathService.addOrderPath(order.getId(), "修改配送方式", sysUser);
	}
	/**
	 * 修改发票信息
	 */
	@Override
	public void updateInvoice(ReqData reqData, Order order, SysUser sysUser) {
		reqData.putValue("id", order.getId(), SystemConstant.REQ_PARAMETER_EQ);
		Order oldOrder = orderService.getOrder(reqData);
		oldOrder.setInvoiceType(order.getInvoiceType());
		oldOrder.setInvoiceContent(order.getInvoiceContent());
		oldOrder.setCompanyName(order.getCompanyName());
		oldOrder.setTaxpayerNum(order.getTaxpayerNum());
		orderService.updateOneOrdeStatus(reqData, oldOrder);
		pathService.addOrderPath(order.getId(), "修改发票信息", sysUser);
	}
	
	/**
	 * 扩展订单时间
	 */
	@Override
	public void updateOrderDate(ReqData reqData, Order order, SysUser sysUser) {
		reqData.putValue("id", order.getId(), SystemConstant.REQ_PARAMETER_EQ);
		Order oldOrder = orderService.getOrder(reqData);
		if(ObjectUtils.isEmpty(order.getUpdateTime())){
			oldOrder.setUpdateTime(new Date());
			oldOrder.setExtendTime(new Date());
		}else{
			oldOrder.setUpdateTime(order.getUpdateTime());
			oldOrder.setExtendTime(order.getUpdateTime());
		}
		oldOrder.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_PREPARE);
		oldOrder.setDeliveryMethod(order.getDeliveryMethod());
		orderService.updateOneOrdeStatus(reqData, oldOrder);
		pathService.addOrderPath(order.getId(), "扩展订单时间", sysUser);
	}
	/**
	 * 批量扩展订单时间
	 */
	@Override
	public void updateAllOrderDate(ReqData reqData, Order order, String orderIds, SysUser sysUser) {
		reqData.putValue("id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		List<Order> all = orderService.findAll(reqData);
		reqData.removeValue("id");
		for (Order orders : all) {
			orders.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_PREPARE);
			orders.setUpdateTime(order.getUpdateTime());
			orders.setExtendTime(order.getUpdateTime());
			orderService.updateOneStatus(reqData, orders, 99, sysUser);
			pathService.addOrderPath(orders.getId(), "扩展订单时间", sysUser);
		}
	}
	/**
	 * @author lij
	 * 订单发货
	 */
	@Override
	public List<Order> updateOrderDelivery(ReqData reqData, String orderIds, long deliveryId, SysUser sysUser,String sendContent,String isSendMessge) {
		//1.查询出前台请求发货的所有订单
		reqData.putValue("id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		List<Order> findAll = orderService.findAll(reqData);
		reqData.removeValue("id");
		//2.根据前台的配送人查询出单个配送人信息
		reqData.putValue("id", deliveryId, SystemConstant.REQ_PARAMETER_EQ);
		UserAddrees delivery = userAddreesService.getUserAddrees(reqData);
		reqData.removeValue("id");
		//3.为每个订单配置送货人信息
		for (Order order : findAll) {
			order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_FINISH_DELIVERY);
			order.setDeliveryName(delivery.getName());
			order.setDeliveryPhone(delivery.getMobilePhone());
			order.setLogisticsType(SystemConstant.ORDER.LOGISTICS_TYPE_NOMARL);
			
			//计算需要的金额
			BigDecimal DSHK = null;//应代收货款
			BigDecimal HKZE = order.getTotal(); //货款总额
			if (SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY.equals(order.getPayType())) {
				DSHK = order.getTotal().subtract(order.getPay());
			}else {
				DSHK = new BigDecimal(0);
			}
			
			
			//3.1 提交物流信息 并添加客户订单
			
			String logisticsCsmNo = "B2C"+ order.getNo(); //LogisticsUtil.generatecsmNo();//订单标号-客户单号
			
			LogisticsOrder logisticsOrder = new LogisticsOrder();
			logisticsOrder.setOrderNo(logisticsCsmNo); //订单标号-客户单号
			logisticsOrder.setOrderTime(order.getCreateTime());
			logisticsOrder.setDSHK(DSHK);
			logisticsOrder.setHKZE(HKZE);
			
			CriteriaParameter parameter = new CriteriaParameter();
			Criteria criteria = parameter.createCriteria();
			criteria.equalTo("order_id", order.getId());
			List<OrderGoods> orderGoodss = orderGoodsMapper.selectByExample(parameter);
			
			criteria.clearCriteria();
			criteria.in("id", getGoodsIdsFromOrderGoodss(orderGoodss));
			List<Goods> goods = goodsMapper.selectByExample(parameter);
			
			List<GoodsVo> orderdetail = new ArrayList<GoodsVo>();
			for (OrderGoods orderGoods : orderGoodss) {
				for (Goods innergoods : goods) {
					if (innergoods.getId().equals(orderGoods.getGoodsId())) {
						GoodsVo goodsVo = new GoodsVo();
						goodsVo.setGoodsCode(innergoods.getCode()); //商品编号
						goodsVo.setGoodsName(innergoods.getName());//商品名
						goodsVo.setGoodsNum(orderGoods.getNum()+"");//商品数量
						goodsVo.setGoodsUnit(innergoods.getUnitName()); //商品单位
						orderdetail.add(goodsVo);
					}
				}
			}
			
			logisticsOrder.setOrderdetail(orderdetail);
			logisticsOrder.setConsigneePhone(order.getConsigneePhone());
			logisticsOrder.setConsigneeName(order.getConsigneeName());
			logisticsOrder.setConsigneeAddress(order.getConsigneeAddress());
			
			JSONObject pa = new JSONObject(logisticsOrder);
			//System.out.println("参数："+pa.toString());
			String res = LogisticsUtil.insertStoreOrder(logisticsOrder);
			System.out.println("物流订单提交(业务方法返回):"+res);
			order.setLogisticsNo(logisticsCsmNo);
			//3.2订单显示状态显示为待收货
			order.setStatus(3);
			order.setSendTime(new Date());
			orderService.updateOneStatus(reqData, order, 99, sysUser);
			pathService.addOrderPath(order.getId(), "发货", sysUser);
			commonGoodsService.updateGoodsSumDealByOrderId(order.getId());
			if(!ObjectUtils.isEmpty(order.getUserId())){
				if(order.getUserId() !=0){
					commonPushUtilService.Push(order.getUserId(), "亲~订单编号为"+order.getNo()+"的订单已经发货，请保持电话畅通以便快递员与您联系。");
				}
			}
			if(Integer.parseInt(isSendMessge)>0){
				SendSMSUtil.sendFullSMS(order.getConsigneePhone(), sendContent);
			}
			String isSendSMS=reqData.getValue("isSendSMS")+"";
			if("1".equals(isSendSMS)){
			    SendSMSUtil.sendFullSMS(order.getConsigneePhone(), sendContent);
			}
		}
		
		return findAll;
	}
	
	private List<Object> getGoodsIdsFromOrderGoodss(List<OrderGoods> orderGoodss){
		List<Object> idObjects = new ArrayList<>();
		for (OrderGoods orderGoods : orderGoodss) {
			idObjects.add(orderGoods.getGoodsId());
		}
		return idObjects;
	}
	
	/**
	 * 订单作废
	 */
	@Override
	public List<Order> delteOrders(ReqData reqData, String orderIds, SysUser sysUser,int isDel) {
		reqData.putValue("id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		List<Order> all = orderService.findAll(reqData);
		reqData.removeValue("id");
		for (Order order : all) {
			order.setIsDel(isDel);
			orderService.updateOneStatus(reqData, order, 99, sysUser);
			pathService.addOrderPath(order.getId(), "作废", sysUser);
			//不是匿名的时候掉退款方法
			if(!ObjectUtils.isEmpty(order.getUserId())){
				if(order.getUserId()!=0){
					pcOrderService.deleteOrderByOrder(order.getId());
				}
			}
		}
		return all;
	}
	/**
	 * 根据条件查询扩展订单信息
	 */
	@Override
	public List<OrderDto> findOrderDto(ReqData reqData) {
		List<OrderDto> list = orderExMapper.findOrderDto(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	/**
	 * 修改订单总金额
	 */
	@Override
	public void updateOrderTotal(SysUser sysUser, Long orderId, BigDecimal total) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		order.setTotal(total);
		orderMapper.updateByPrimaryKey(order);
		pathService.addOrderPath(orderId, "修改订单总金额", sysUser);
	}
	
	@Override
	public Coupon getCoupon(Long id) {
		return couponMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询订单总数
	 */
	@Override
	public List<OrderExcleDto> getExcleOrder(String beginTime,String endTime) {
		DateTime begin = DateUtil.parse(beginTime);
		DateTime end = DateUtil.parse(endTime);
		ReqData reqData = new ReqData();
		reqData.putValue("o.prepare_time", begin, SystemConstant.REQ_PARAMETER_GE);
		reqData.putValue("o.prepare_time", end, SystemConstant.REQ_PARAMETER_LE);
		reqData.putValue("o.is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderExcleDto> excleDto = orderExcleMapper.findOrderExcleDto(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		//查询优惠卷具体金额使用的
		for (OrderExcleDto orderExcleDto : excleDto) {
			if(!ObjectUtils.isEmpty(orderExcleDto.getCouponId())){
				CouponUser couponUser = couponUserMapper.selectByPrimaryKey(orderExcleDto.getCouponId());
				if(!ObjectUtils.isEmpty(couponUser)){
					Coupon coupon = couponMapper.selectByPrimaryKey(couponUser.getCouponId());
					if(!ObjectUtils.isEmpty(coupon)){
						orderExcleDto.setCouponMoney(coupon.getCouponValue());
						orderExcleDto.setTotal(orderExcleDto.getTotal().add(coupon.getCouponValue()));
					}
				}
			}
		}
		return excleDto;
	}
	
	
}
