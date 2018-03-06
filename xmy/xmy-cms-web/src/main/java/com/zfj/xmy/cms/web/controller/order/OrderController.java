package com.zfj.xmy.cms.web.controller.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.repository.query.Param;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.xmy.activity.service.cms.CouponService;
import com.zfj.xmy.activity.service.cms.ShoppingCardService;
import com.zfj.xmy.cms.web.common.PoiUtil;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.KdniaoExpCodeMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.KdniaoExpCode;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.OrderPath;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonPushUtilService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.order.persistence.cms.pojo.dto.OrderExcleDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.service.cms.OrderGoodsService;
import com.zfj.xmy.order.service.cms.OrderPathService;
import com.zfj.xmy.order.service.cms.OrderService;
import com.zfj.xmy.order.service.cms.UserAddreesService;
import com.zfj.xmy.order.service.cms.UserSpendPointsService;
import com.zfj.xmy.order.service.common.CommonOrderService;
import com.zfj.xmy.redis.RedisTokenManager;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.user.persistence.pojo.dto.UserInfoDto;
import com.zfj.xmy.user.service.common.ShoppingCardRecordService;
import com.zfj.xmy.user.service.common.UserInfoService;
import com.zfj.xmy.util.PushUtil;

/**
 * 
 * @author lijie 6.110
 * 
 */

@RestController
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderGoodsService orderGoodsService;

	@Autowired
	private UserAddreesService userAddreesService;

	@Autowired
	private OrderPathService orderPathService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UserSpendPointsService spendPointsService;
	
	@Autowired
	private ShoppingCardService cardService;
	
	@Autowired
	private ShoppingCardRecordService cardRecordService;
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private KdniaoExpCodeMapper kdniaoMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderPathService pathService;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private CommonOrderService commonOrderService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	@Autowired
	private CommonPushUtilService commonPushUtilService;
	

	@Autowired
	private RedisTokenManager redisTokenManager;
	

	
	@Autowired
	private OnLineActivityService onLineActivityService;
	
	// 初始化分页模型
	private static PageBean getPageBean() {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		return pageBean;
	}

	// 自定义类型转换器
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	// 根据条件查询分页总数
	private int getCountPage(ReqData reqData, HttpServletRequest request, int pageSize) {
		String payType = request.getParameter("pay_type") ;// null ? "" : request.getParameter("pay_type");
		String shipStatus = request.getParameter("ship_status") ;// null ? "" : request.getParameter("ship_status");
		String payTime = request.getParameter("pay_time") ;// null ? "" : request.getParameter("pay_time");
		String arrivalTime = request.getParameter("arrival_time") ;// null ? "" : request.getParameter("arrival_time");
		String payName = request.getParameter("payment_name") ;// null ? "" : request.getParameter("payment_name");
		String no = request.getParameter("no") ;// null ? "" : request.getParameter("no");
		if (ObjectUtils.isEmpty(payType)) {

			reqData.putValue("1", 1, SystemConstant.REQ_PARAMETER_EQ);
		} else {
			reqData.removeValue("1");
			reqData.putValue("pay_type", payType, SystemConstant.REQ_PARAMETER_EQ);
		}
		if (ObjectUtils.isEmpty(shipStatus)) {
			reqData.putValue("2", 2, SystemConstant.REQ_PARAMETER_EQ);
		} else {
			reqData.removeValue("2");
			reqData.putValue("ship_status", shipStatus, SystemConstant.REQ_PARAMETER_EQ);
		}
		if (ObjectUtils.isEmpty(payTime)) {
			reqData.putValue("3", 3, SystemConstant.REQ_PARAMETER_EQ);
		} else {
			reqData.removeValue("3");
			reqData.putValue("pay_time", payTime, SystemConstant.REQ_PARAMETER_GE);
		}
		if (ObjectUtils.isEmpty(arrivalTime)) {
			reqData.putValue("4", 4, SystemConstant.REQ_PARAMETER_EQ);
		} else {
			reqData.removeValue("4");
			reqData.putValue("arrival_time", arrivalTime, SystemConstant.REQ_PARAMETER_LE);
		}
		if (ObjectUtils.isEmpty(payName)) {
			reqData.putValue("5", 5, SystemConstant.REQ_PARAMETER_EQ);
		} else {
			reqData.removeValue("5");
			reqData.putValue("payment_name", payName, SystemConstant.REQ_PARAMETER_LE);
		}
		if (ObjectUtils.isEmpty(no)) {
			reqData.putValue("6", 6, SystemConstant.REQ_PARAMETER_EQ);
		} else {
			reqData.removeValue("6");
			reqData.putValue("no", no, SystemConstant.REQ_PARAMETER_LE);
		}
		int count = orderService.findAllorderCount(reqData);
		int countPage;
		if (count % pageSize == 0) {
			countPage = count / pageSize;
		} else {
			countPage = count / pageSize + 1;
		}
		return countPage;
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:36:15 查询订单总页数
	 */
	@RequestMapping("/order/page")
	public ModelAndView findOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_list");

	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:36:28 分页查询全部订单
	 */
	@RequestMapping("/order/page/{pageIndex}")
	public ModelAndView findAllOrders(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("pageBean", pageBean);
		return new ModelAndView("/order/order_list_page");
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:36:47 查询待支付订单总页数
	 */
	@RequestMapping("/order/unpaid")
	public ModelAndView findUnpaidOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		/*reqData.putValue("pay_type", SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY, SystemConstant.REQ_PARAMETER_NE);*/
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_UNPAID, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_unpaid_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:37:03 分页查询待支付的订单
	 */
	@RequestMapping("/order/unpaid/{pageIndex}")
	public ModelAndView findOrderByPayType(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		/*reqData.putValue("pay_type", SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY, SystemConstant.REQ_PARAMETER_NE);*/
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_UNPAID, SystemConstant.REQ_PARAMETER_EQ);
		
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("pageBean", pageBean);

		return new ModelAndView("/order/order_unpaid_page");
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:37:18 查询待审核订单总页数
	 */
	@RequestMapping("/order/audit")
	public ModelAndView findAuditOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_AUDIT, SystemConstant.REQ_PARAMETER_EQ);
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_audit_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:37:52 分页查询待审核订单
	 */
	@RequestMapping("/order/audit/{pageIndex}")
	public ModelAndView findOrderByPayTypeNo(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_AUDIT, SystemConstant.REQ_PARAMETER_EQ);
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("pageBean", pageBean);
		return new ModelAndView("/order/order_unpaid_page");
	}

	/**
	 * @param id
	 * @param status
	 * @param reqData
	 * @param request
	 * @param attributes
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:38:05 根据订单ID和传入状态修改订单状态
	 */
	@RequestMapping("/order/updetaStatus/{id}/{status}")
	public ModelAndView updateOrderStatus(@PathVariable("id") long id, @PathVariable("status") Integer status, ReqData reqData, HttpServletRequest request, RedirectAttributes attributes) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		reqData.putValue("id", id, SystemConstant.REQ_PARAMETER_EQ);
		Order order = orderService.getOrder(reqData);
		order.setShipStatus(status);

		orderService.updateOneStatus(reqData, order, status, sysUser);

		attributes.addAttribute("message", "修改成功！");

		return new ModelAndView("redirect:/order/detail/"+order.getId());
	}

	/**
	 * @param ids
	 * @param status
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:39:08 
	 * @Description:批量修改订单状态
	 */
	@RequestMapping("/order/updateAllStatus/{ids}/{status}")
	public ModelAndView updateOrdersStatus(@PathVariable("ids") String ids, @PathVariable("status") Integer status, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		ModelAndView model = new ModelAndView();
		// 跳转页面
		if (status == SystemConstant.ORDER.SHIP_STATUS_SUPPLY) {
			model.setViewName("/order/order_audit_list");
		}
		if (status == SystemConstant.ORDER.SHIP_STATUS_DELIVERY) {
			model.setViewName("/order/order_prepare_list");
		}
		if (status == SystemConstant.ORDER.SHIP_STATUS_ORDER_FINISH) {
			model.setViewName("/order/order_finish");
		}
		orderService.updateAllStatus(reqData, ids, status, sysUser);
		request.setAttribute("message", "修改成功！");
		return model;
	}

	/**
	 * @param order
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:39:40 修改收货人信息
	 */
	@RequestMapping("/order/updetaConsignee")
	public ModelAndView updateOrderConsignee(@ModelAttribute Order order, ReqData reqData, HttpServletRequest request) {

		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");

		orderService.updateConsignee(reqData, order, sysUser);

		return new ModelAndView("redirect:/order/detail/"+order.getId());
	}

	/**
	 * @param order
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:39:54 修改配送方式
	 */
	@RequestMapping("/order/deliveryMethod")
	public ModelAndView updateOrDeliveryMethod(@ModelAttribute Order order, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");

		orderService.updateDeliveryMethod(reqData, order, sysUser);

		return new ModelAndView("redirect:/order/detail/"+order.getId());
	}
	/**
	 * 修改发票类容
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月27日 下午2:37:21
	 */
	@RequestMapping("/order/updateInvoice")
	public ModelAndView updateOrderInvoice(@ModelAttribute Order order,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		orderService.updateInvoice(reqData, order, sysUser);
		return new ModelAndView("redirect:/order/detail/"+order.getId());
	}
	
	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:40:05 根据订单id查询订单详情
	 */
	@RequestMapping("/order/detail/{id}")
	public ModelAndView findOrderDetail(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		Map<String, Object> map = new HashMap<String, Object>();
		reqData.putValue("id", id, SystemConstant.REQ_PARAMETER_EQ);
		ReqData reqData1 = new ReqData();
		reqData1.putValue("order_id", id, SystemConstant.REQ_PARAMETER_EQ);
		ReqData reqData2 = new ReqData();
		reqData2.putValue("type", 1, SystemConstant.REQ_PARAMETER_EQ);
		// 查询配送人员
		List<UserAddrees> list = userAddreesService.finAllUserAddrees(reqData2);
		// 查询订单商品
		List<OrderGoods> orderGoodsByOrderId = orderGoodsService.findOrderGoodsByOrderId(reqData1,false);
		// 查询单个订单
		Order order = orderService.getOrder(reqData);
		reqData.clearValue();
		// 根据订单ID查询订单轨迹
		List<OrderPath> orderPathByOrderId = orderPathService.findOrderPathByOrderId(reqData1);
		//查询快递信息
		List<KdniaoExpCode> kdList = kdniaoMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		//优惠券使用
		if (ObjectUtil.isNotNull(order.getCouponId()) && 0 != order.getCouponId()) {
			CouponUser couponUser = couponUserMapper.selectByPrimaryKey(order.getCouponId());
			if(!ObjectUtils.isEmpty(couponUser)){
				Coupon coupon = orderService.getCoupon( couponUser.getCouponId()) ;
				if (ObjectUtil.isNotNull(coupon)) {
					//订单金额加上使用的优惠卷金额
					order.setTotal(order.getTotal().add(coupon.getCouponValue()));
					request.setAttribute("coupon", coupon);
				}
			}
		}
		map.put("orderPath", orderPathByOrderId);
		map.put("goods", orderGoodsByOrderId);
		map.put("order", order);
		request.setAttribute("message", request.getParameter("message"));
		request.setAttribute("orderMap", map);
		request.setAttribute("kdList", kdList);
		request.getSession().setAttribute("deliveryUser", list);
		//第三方支付方式 对比
		request.setAttribute("tradeType", PayBaseImpl.getTradeType());
		return new ModelAndView("/order/order_detail");
	}
	
	@RequestMapping("/order/wuliu")
	public JSONObject orderWuliu(@Param("id") Long id){
		JSONObject orderLogisticsInfo = null;
		try {
			orderLogisticsInfo = commonOrderService.getOrderLogisticsInfo(id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return orderLogisticsInfo;
	} 
	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:40:30 查询扩展订单的总页数
	 */
	@RequestMapping("/order/supply")
	public ModelAndView findSupplyOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_SUPPLY, SystemConstant.REQ_PARAMETER_GE);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_PREPARE, SystemConstant.REQ_PARAMETER_LE);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_supply_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:40:45 分页查询扩展订单
	 */
	@RequestMapping("/order/supply/{pageIndex}")
	public ModelAndView findOrderBySupply(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_SUPPLY, SystemConstant.REQ_PARAMETER_GE);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_PREPARE, SystemConstant.REQ_PARAMETER_LE);
		orderService.findAllorders(pageBean, reqData);
		List<Order> data = (List<Order>) pageBean.getData();
		for (Order order : data) {
			if(!ObjectUtils.isEmpty(order.getParsetTime())){
				order.setParsetTime(DateUtil.date(order.getParsetTime()));
				if(ObjectUtils.isEmpty(order.getPayTime())){
					order.setPayTime(order.getCreateTime());
				}
				Long days=DateUtil.between(DateUtil.beginOfDay(order.getPayTime()), order.getParsetTime(), DateUnit.DAY);
	            order.setAccPoints(days);
			}
			
		}
		pageBean.setData(data);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("now",DateUtil.beginOfDay(new Date()));
		return new ModelAndView("/order/order_supply_page");
	}
	/**
	 * @param order
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:41:35 订单扩展供货时间
	 */
	@RequestMapping("/order/updateOrderDate")
	public ModelAndView updateOrderDate(@ModelAttribute Order order,String updateTime, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		DateTime dateTime = null;
		if(ObjectUtils.isEmpty(updateTime)){
			dateTime = new DateTime();
		}else{
			dateTime = DateUtil.parse(updateTime);
		}
		order.setUpdateTime(dateTime);
		order.setExtendTime(dateTime);//扩展时间
		orderService.updateOrderDate(reqData, order, sysUser);
		
		return new ModelAndView("redirect:/order/detail/"+order.getId());
	}

	/**
	 * @param order
	 * @param request
	 * @param reqData
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:41:47 批量扩张订单供货时间
	 */
	@RequestMapping("/order/updateAllOrderDate")
	public ModelAndView updateAllOrderDate(@ModelAttribute Order order,String updateTime, HttpServletRequest request, ReqData reqData) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		String ids = request.getParameter("ids");
		DateTime dateTime = DateUtil.parse(updateTime);
		order.setUpdateTime(dateTime);
		order.setExtendTime(dateTime);//扩展时间
		orderService.updateAllOrderDate(reqData, order, ids, sysUser);
		request.setAttribute("message", "操作成功！");
		return new ModelAndView("/order/order_supply_list");
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:42:00 查询配货确认订单总页数
	 */
	@RequestMapping("/order/prepare")
	public ModelAndView findPrepareOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_PREPARE, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_prepare_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:42:47 分页查询备货确认的订单
	 */
	@RequestMapping("order/prepare/{pageIndex}")
	public ModelAndView findPrepareOrders(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_PREPARE, SystemConstant.REQ_PARAMETER_EQ);
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("pageBean", pageBean);
		return new ModelAndView("/order/order_prepare_page");
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @throws Exception 
	 * @date 2017年7月11日 下午3:43:45 查询客户清单
	 */
	@RequestMapping("/order/userList/{id}")
	public String findUserList(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request,HttpServletResponse response) throws Exception {
		reqData2Params(reqData);
		Map<String, Object> map = new HashMap<String, Object>();
		reqData.putValue("id", id, SystemConstant.REQ_PARAMETER_EQ);
		ReqData reqData1 = new ReqData();
		reqData1.putValue("order_id", id, SystemConstant.REQ_PARAMETER_EQ);
		// 查询订单商品
		List<OrderGoods> orderGoodsByOrderId = orderGoodsService.findOrderGoodsByOrderId(reqData1,true);
		// 查询单个订单
		Order order = orderService.getOrder(reqData);
		//查询优惠卷金额(加入优惠卷金额)
		if (ObjectUtil.isNotNull(order.getCouponId()) && 0 != order.getCouponId()) {
			CouponUser couponUser = couponUserMapper.selectByPrimaryKey(order.getCouponId());
			if(!ObjectUtils.isEmpty(couponUser)){
				Coupon coupon = orderService.getCoupon( couponUser.getCouponId()) ;
				if (ObjectUtil.isNotNull(coupon)) {
					//订单金额加上使用的优惠卷金额
					order.setTotal(order.getTotal().add(coupon.getCouponValue()));//为总金额加入优惠卷金额
					order.setPay(order.getPay().add(coupon.getCouponValue()));//为已付款加入优惠卷金额
					order.setDiscountPrice(coupon.getCouponValue());//折扣价暂存优惠卷金额
				}else{
					order.setDiscountPrice(new BigDecimal("0.00"));//没有抵用金时为0
				}
			}else{
				order.setDiscountPrice(new BigDecimal("0.00"));//没有抵用金时为0
			}
		}else{
			order.setDiscountPrice(new BigDecimal("0.00"));//没有抵用金时为0
		}
		reqData.clearValue();
		map.put("isAPPfirst", false);
		/*if(!ObjectUtils.isEmpty(order.getUserId())){
			//判断是不是新用户
			Integer checkDate = onLineActivityService.checkAppDate(order.getUserId());
			if(checkDate>0){
				reqData.putValue("user_id", order.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("order_source", SystemConstant.ORDER.ORDER_SOURCE_APP, SystemConstant.REQ_PARAMETER_EQ);
				int cout = orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if(cout==1){//APP第一单
					map.put("isAPPfirst", true);
				}else{
					map.put("isAPPfirst", false);
				}
			}else{
				map.put("isAPPfirst", false);
			}
		}else{
			map.put("isAPPfirst", false);
		}*/
		// 根据订单ID查询订单轨迹
		List<OrderPath> orderPathByOrderId = orderPathService.findOrderPathByOrderId(reqData1);
		map.put("orderPath", orderPathByOrderId);
		map.put("goods", orderGoodsByOrderId);
		map.put("order", order);
		
		PoiUtil util = new PoiUtil();
		util.exlWithUserList(map, response);
		//request.setAttribute("orderMap", map);
		return null;
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:44:48 查询待发货的总页数,查询所有配送人名称
	 */
	@RequestMapping("/order/delivery")
	public ModelAndView findDeliveryOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_DELIVERY, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		ReqData reqData2 = new ReqData();
		reqData2.putValue("type", SystemConstant.USERADDRESS.DELIVERY, SystemConstant.REQ_PARAMETER_EQ);
		reqData2.setSortname("name");
		reqData2.setSortorder("desc");
		List<UserAddrees> list = userAddreesService.finAllUserAddrees(reqData2);

		request.getSession().setAttribute("deliveryUser", list);
		return new ModelAndView("/order/order_delivery_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:44:59 分页查询待发货的订单
	 */
	@RequestMapping("order/delivery/{pageIndex}")
	public ModelAndView findDeliveryOrders(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_DELIVERY, SystemConstant.REQ_PARAMETER_EQ);
		orderService.findAllorders(pageBean, reqData);

		request.setAttribute("pageBean", pageBean);

		return new ModelAndView("/order/order_list_page");
	}

	/**
	 * @param reqData
	 * @param request
	 * @param rAttributes
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:45:21 批量为订单配置送货人（即批量发货）
	 */
	@RequestMapping("order/updateOrderDelivery")
	public ModelAndView updateOrderDeliviery(ReqData reqData, HttpServletRequest request, RedirectAttributes rAttributes) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		boolean flag = true;
		String ids = request.getParameter("ids");
		//是否发送短信
		String isSendSMS=request.getParameter("isSendSMS");
		
		Long deliveryid = Long.parseLong(request.getParameter("deliveryId"));
		String sendContent = request.getParameter("sendContent");
		List<Order> findAll = orderService.updateOrderDelivery(reqData, ids, deliveryid, sysUser,sendContent,isSendSMS);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		
		String[] split = ids.split(",");
		for (String string : split) {
			if(string.equals("0")){
				//列表页
				flag = false;
			}
		}
		if(flag){
			return new ModelAndView("redirect:/order/detail/"+findAll.get(0).getId());
		}else{
			model.setView(new RedirectView("/order/delivery"));
		}
		rAttributes.addAttribute("message", "发货成功！");
		return model;
	}
	
	/**
	 * @param reqData
	 * @param request
	 * @param rAttributes
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年12月14日15:36:52 第三方快递发货
	 */
	@RequestMapping("order/updateSanOrder")
	public ModelAndView updateSanOrder(ReqData reqData, HttpServletRequest request, RedirectAttributes rAttributes) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		String id = request.getParameter("ids");
		String kdCode = request.getParameter("kdniaoExpCode");//快递公司编码
		String logisticsNo = request.getParameter("logisticsNo");//物流单号
		Order order = orderMapper.selectByPrimaryKey(Long.parseLong(id));
		order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_FINISH_DELIVERY);
		order.setKdniaoExpCode(kdCode);
		order.setLogisticsNo(logisticsNo);
		order.setStatus(3);//代发货
		order.setLogisticsType(SystemConstant.ORDER.LOGISTICS_TYPE_OTHER);
		orderMapper.updateByPrimaryKey(order);
		orderService.updateOneStatus(reqData, order, 99, sysUser);
		pathService.addOrderPath(order.getId(), "发货", sysUser);
		commonGoodsService.updateGoodsSumDealByOrderId(order.getId());
		if(order.getUserId() !=0 && !ObjectUtils.isEmpty(order.getUserId())){
			String msg = "亲~订单编号为"+order.getNo()+"的订单已经发货，运单号"+logisticsNo+"，请保持电话畅通以便快递员与您联系。";
			commonPushUtilService.Push(order.getUserId(), msg);
		}
		return new ModelAndView("redirect:/order/detail/"+id);
	}
	
	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:46:32 分页查询已发货订单的总页数
	 */
	@RequestMapping("/order/finish")
	public ModelAndView findFinishOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_FINISH_DELIVERY, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_finish");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:46:47 分页查询已发货的订单
	 */
	@RequestMapping("/order/finish/{pageIndex}")
	public ModelAndView findFinishOrders(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_FINISH_DELIVERY, SystemConstant.REQ_PARAMETER_EQ);
		ModelAndView model = new ModelAndView("/order/order_unpaid_page");
		orderService.findAllorders(pageBean, reqData);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		return model;
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:47:04 查询未作废的订单的总页数
	 */
	@RequestMapping("/order/delete")
	public ModelAndView findDeleteOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_delete_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:47:28 分页查询未作废的订单
	 */
	@RequestMapping("/order/delete/{pageIndex}")
	public ModelAndView findNodeleteOrders(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		if (reqData.getValue("ship_status") == null) {
			reqData.putValue("ship_status", SystemConstant.ORDER.SHIP_STATUS_ORDER_FINISH, SystemConstant.REQ_PARAMETER_NE);
		}
		ModelAndView model = new ModelAndView("/order/order_delete_list_page");
		orderService.findAllorders(pageBean, reqData);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		return model;
	}

	/**
	 * @param ids
	 * @param isDel
	 * @param reqData
	 * @param request
	 * @param rAttributes
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:47:47 批量作废订单
	 */
	@RequestMapping("/order/deleteOrders/{ids}/{isDel}")
	public ModelAndView deleteOrders(@PathVariable("ids") String ids, @PathVariable("isDel") Integer isDel, ReqData reqData, HttpServletRequest request, RedirectAttributes rAttributes) {

		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");

		List<Order> all = orderService.delteOrders(reqData, ids, sysUser, isDel);

		ModelAndView model = new ModelAndView();
		if (all.size() <= 1) {
			rAttributes.addAttribute("message", "修改成功");
			model.setView(new RedirectView("/order/detail/" + ids));
		}
		if (all.size() > 1) {
			rAttributes.addAttribute("message", "修改成功");
			model.setView(new RedirectView("/order/delete"));// "/order/order_delete_list");
		}
		return model;
	}
	
	/**
	 * @param ids
	 * @param isDel
	 * @param reqData
	 * @param request
	 * @param rAttributes
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:47:47 批量退款处理订单
	 */
	@RequestMapping("/order/retern/{ids}")
	public ModelAndView reternOrder(@PathVariable("ids") Long ids, ReqData reqData, HttpServletRequest request, RedirectAttributes rAttributes) {

		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		Order order = orderMapper.selectByPrimaryKey(ids);
		order.setReturnStatus(1);//已退货
		orderMapper.updateByPrimaryKey(order);
		pathService.addOrderPath(order.getId(), "退货处理", sysUser);
		ModelAndView model = new ModelAndView();
		rAttributes.addAttribute("message", "修改成功");
		model.setView(new RedirectView("/order/detail/" + ids));
		return model;
	}
	/**
	 * 修改为支付状态
	 * @param ids
	 * @param reqData
	 * @param request
	 * @param rAttributes
	 * @return ModelAndView
	 * @author lij
	 * @date 2018年1月8日 下午3:57:20
	 */
	@RequestMapping("/order/updatePayStatus/{ids}")
	public ModelAndView updatePayStatus(@PathVariable("ids") Long ids, ReqData reqData, HttpServletRequest request, RedirectAttributes rAttributes) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		Order order = orderMapper.selectByPrimaryKey(ids);
		order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_SUPPLY);
		order.setStatus(SystemConstant.ORDER.STATUS_DELIVERY);
		order.setPayType(SystemConstant.ORDER.PAY_TYPE_GIFTCARD);
		orderMapper.updateByPrimaryKey(order);
		pathService.addOrderPath(order.getId(), "改为已支付状态", sysUser);
		ModelAndView model = new ModelAndView();
		rAttributes.addAttribute("message", "修改成功");
		model.setView(new RedirectView("/order/detail/" + ids));
		return model;
	}
	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:48:12 查询支付处理的总页数
	 */
	@RequestMapping("/order/pay")
	public ModelAndView findPayOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		reqData.putValue("pay_type", SystemConstant.ORDER.PAY_TYPE_GIFTCARD, SystemConstant.REQ_PARAMETER_GE);
		reqData.putValue("pay_type", SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY, SystemConstant.REQ_PARAMETER_NE);
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_pay_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:48:29 分页查询支付处理的订单
	 */
	@RequestMapping("/order/pay/{pageIndex}")
	public ModelAndView findPayOrders(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		ModelAndView model = new ModelAndView("/order/order_pay_list_page");
		reqData.putValue("pay_type", SystemConstant.ORDER.PAY_TYPE_GIFTCARD, SystemConstant.REQ_PARAMETER_GE);
		reqData.putValue("pay_type", SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY, SystemConstant.REQ_PARAMETER_NE);
		orderService.findAllorders(pageBean, reqData);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		return model;
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:48:41 查询申请退款的总页数
	 */
	@RequestMapping("/order/refund")
	public ModelAndView findRefundOrderPage(ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("return_status", 1, SystemConstant.REQ_PARAMETER_GE);
		PageBean pageBean = getPageBean();
		orderService.findAllorders(pageBean, reqData);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/order/order_refund_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:49:04 分页查询申请退款的订单
	 */
	@RequestMapping("/order/refund/{pageIndex}")
	public ModelAndView findRefundOrders(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		PageBean pageBean = getPageBean();
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("return_status", 1, SystemConstant.REQ_PARAMETER_GE);
		orderService.findAllorders(pageBean, reqData);
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		request.setAttribute("pageBean", pageBean);
		return new ModelAndView("/order/order_refund_list_page");
	}

	/**
	 * @param orders
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @throws Exception 
	 * @date 2017年7月11日 下午3:49:23 批量打印送货清单
	 */
	@RequestMapping("/order/print/{orders}")
	public String printOrders(@PathVariable("orders") String orders, HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReqData reqData = new ReqData();
		reqData.putValue("id", orders, SystemConstant.REQ_PARAMETER_IN);
		List<OrderDto> findAll = orderService.findOrderDto(reqData);
		reqData.removeValue("id");
		for (OrderDto order : findAll) {
			PoiUtil util = new PoiUtil();
			Map<String, Object> map = new HashMap<>();
			map.put("order", order);
			reqData.putValue("order_id", order.getId(), SystemConstant.REQ_PARAMETER_EQ);
			order.setOrderGoods(orderGoodsService.findOrderGoodsByOrderId(reqData,true));
			map.put("goods", orderGoodsService.findOrderGoodsByOrderId(reqData,true));
			util.exlWithUserList(map, response);
			reqData.removeValue("order_id");
		}
		request.setAttribute("allOrder", findAll);
		//return new ModelAndView("/order/prints");
		return null;
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午3:50:04 根据ID查询单个用户的消费记录
	 */
	@RequestMapping("/order/spend/{id}")
	public ModelAndView findSpendByUserid(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request) {
		reqData2Params(reqData);
		reqData.putValue("user_info.id", id, SystemConstant.REQ_PARAMETER_EQ);
		UserInfoDto userInfo = userInfoService.findUserInfo(reqData);
		reqData.removeValue("user_info.id");
		reqData.putValue("user_id", id, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", SystemConstant.userSpendPoints.TYPE_SPEND, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("creat_time");
		reqData.setSortorder("desc");
		List<UserSpendPoints> userSpendPoints = spendPointsService.findUserSpendPoints(reqData);
		
		request.setAttribute("user", userInfo);
		request.setAttribute("userSpendPoints", userSpendPoints);
		return new ModelAndView("/order/user_spend");
	}

	/**
	 * @param userSpendPoints
	 * @param request
	 * @param reqData
	 * @return String
	 * @author lij
	 * @date 2017年7月11日 下午3:50:21 操作用户的消费记录
	 */
	@RequestMapping("/order/updateSpend")
	public ModelAndView updateSpend(UserSpendPoints userSpendPoints, HttpServletRequest request, ReqData reqData) {
		reqData2Params(reqData);
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		userSpendPoints.setIsDel(SystemConstant.userSpendPoints.STATUS_NODELETE);
		userSpendPoints.setType(SystemConstant.userSpendPoints.TYPE_SPEND);
		userSpendPoints.setCreatTime(new Date());
		userSpendPoints.setConsole(sysUser.getName());
		spendPointsService.saveSpendPoints(userSpendPoints);
		reqData.putValue("user_info.id", userSpendPoints.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
		UserInfoDto userInfo = userInfoService.findUserInfo(reqData);
		if (userSpendPoints.getSpendType() == SystemConstant.userSpendPoints.SPEND_TYPE_SAVE) {
			userInfo.setBalance(userInfo.getBalance().add(userSpendPoints.getMoneyPoint()) );//+ userSpendPoints.getMoneyPoint());
		} else {
			userInfo.setBalance(userInfo.getBalance().subtract(userSpendPoints.getMoneyPoint()));//- userSpendPoints.getMoneyPoint());
		}
		
		userInfoService.updateUserInfo(reqData, userInfo);
		
		return new ModelAndView("redirect:/order/spend/"+userSpendPoints.getUserId());
	}
	/**
	 * 修改订单总金额
	 * @param orderid
	 * @param total
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月27日 下午4:01:47
	 */
	@RequestMapping("/order/updateTotal")
	public ModelAndView updateOrderTotal(@Param("orderId") Long orderId,@Param("total") BigDecimal total,HttpServletRequest request){
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		orderService.updateOrderTotal(sysUser, orderId, total);
		
		return new ModelAndView("redirect:/order/detail/"+orderId);
	}
	/**
	 * 查询用户绑定的所有购物卡
	 * @param userId
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月27日 下午5:03:20
	 */
	@RequestMapping("/order/userShoppingCard/{orderId}")
	public ModelAndView findShoppingCard(@PathVariable("orderId") Long orderId,HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		List<ShoppingCard> shoppingCardList = cardService.findShoppingCardByUserId(orderId);
		request.setAttribute("cardList", shoppingCardList);
		return new ModelAndView("/order/user_card");
	}
	/**
	 * 查询单个 购物卡的消费记录
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月28日 下午3:08:20
	 */
	@RequestMapping("/order/cardSpend/{id}")
	public ModelAndView findCardSpend(@PathVariable("id") Long id,HttpServletRequest request){
		ShoppingCard shoppingCard = shoppingCardMapper.selectByPrimaryKey(id);
		ReqData reqData = new ReqData();
		reqData.putValue("shopping_card_id", id, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCardRecord> cardRecord = cardRecordService.findUserShoppingCardRecord(ReqUtil.reqParameterToCriteriaParameter(reqData));
		request.setAttribute("cardRecord", cardRecord);
		request.setAttribute("shoppingCard", shoppingCard);
		return new ModelAndView("/order/user_spend");
	}
	/**
	 * 修改购物卡金额
	 * @param spendType 1.存入 2.消费
	 * @param money
	 * @param remark
	 * @param id
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月28日 下午6:15:29
	 */
	@RequestMapping("order/updateCard")
	public ModelAndView updateCard(Integer spendType,BigDecimal money,String remark,Long id){
		
		cardService.updateShoppingCard(id, spendType, money);
		if(spendType==1){
			spendType=2;
		}else{
			spendType=1;
		}
		cardRecordService.addShoppingCardRecord(remark, id, money,spendType);
		return new ModelAndView("redirect:/order/cardSpend/"+id);
	}
	/**
	 * 根据用户选择的月份导出销售总额
	 * @param dateTime
	 * @return String
	 * @author lij
	 * @throws Exception 
	 * @date 2017年12月20日 下午2:03:35
	 */
	@RequestMapping("order/excle")
	public ModelAndView excle(String bginTime,String endTime,HttpServletResponse response) throws Exception{
		
		return new ModelAndView("/excleorder/excle_order");
	}
	
	
	/**
	 * 根据用户选择的月份导出销售总额
	 * @param dateTime
	 * @return String
	 * @author lij
	 * @throws Exception 
	 * @date 2017年12月20日 下午2:03:35
	 */
	@RequestMapping("order/excleOrder")
	public String excleOrder(String bginTime,String endTime,HttpServletResponse response) throws Exception{
		PoiUtil poiUtil = new PoiUtil();
		List<OrderExcleDto> excleOrder = orderService.getExcleOrder(bginTime, endTime);
		poiUtil.exlAllOrder(excleOrder, response,bginTime,endTime);
		return "00";
	}
	
	/**
	 * app订单横幅设置页面
	 * @param response
	 * @return
	 * @Description 
	 * @date 2018年1月8日  上午11:25:46
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("order/appbanner")
	public ModelAndView orderBanner(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("banner", redisTokenManager.get(SystemConstant.REDIS_B2C_APP_PREORDER_BANNER));
		return new ModelAndView("/order/order_appbanner");
	}
	/**
	 * 设置app订单横幅
	 * @param bginTime
	 * @param endTime
	 * @param response
	 * @return
	 * @throws Exception
	 * @Description 
	 * @date 2018年1月8日  上午11:27:44
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("order/setAppBanner")
	public ModelAndView setAppBanner(HttpServletRequest request,String banner,HttpServletResponse response) {
		redisTokenManager.setKey(SystemConstant.REDIS_B2C_APP_PREORDER_BANNER, banner);
		request.setAttribute("banner", banner);
		return new ModelAndView("/order/order_appbanner");
	}
}
