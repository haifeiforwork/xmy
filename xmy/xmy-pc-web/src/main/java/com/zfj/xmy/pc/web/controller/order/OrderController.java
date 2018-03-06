package com.zfj.xmy.pc.web.controller.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.annotation.CheckLogin;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.service.pc.PcCouponService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.UUIDUtil;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonNewActitvityService;
import com.zfj.xmy.common.service.CommonPayOrderService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.common.service.OrderCommitService;
import com.zfj.xmy.goods.service.pc.PcGoodsService;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcDateTime;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.service.pc.PcOrderService;
import com.zfj.xmy.order.service.pc.ShoppingCartService;
import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.pc.web.controller.BaseController;
import com.zfj.xmy.user.service.pc.PcUserAddressService;
import com.zfj.xmy.user.service.pc.PcUserService;

/**
 * @author lij
 */
@RequestMapping("/order")
@CheckLogin
@RestController
public class OrderController extends BaseController{

	@Autowired
	private PcOrderService orderService;
	
	@Autowired
	private PcUserAddressService userAddressService;
	
	@Autowired
	private PcCouponService pcCouponSrevice;
	
	@Autowired
	private PcUserService pcUserService;
	
	@Autowired
	private PcGoodsService pcGoodsService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private PayBase payBase;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private OnLineActivityService onLineActivityService;
	
	@Autowired
	private OrderCommitService orderCommitService;
	
	@Autowired
	private CommonNewActitvityService actitvityService;
	
	@Autowired
	private CommonPayOrderService commonPayOrderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * @param pageIndex
	 * @param status
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月8日 下午5:44:40
	 * 查询我的订单
	 */
	@RequestMapping("/myOrder/{status}/{pageIndex}")
	public ModelAndView findAllOrder(Integer set,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("status") Integer status ,
			HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		List<Integer> pageList = new ArrayList<Integer>();
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(5);
		pageBean.setPageIndex(pageIndex);
		//1.查询订单
		List<OrderDto> findAllOrderByUserId = orderService.findAllOrderByUserId(status,sessionInfo.getUserId(), pageBean);
		//2.查询总页数
		Integer countPage = orderService.findAllOrderCountPage(status, sessionInfo.getUserId(), 5);
		//3.查询待支付订单的总页数
		Integer unpaidCount = orderService.findUnpaidOrderCount(sessionInfo.getUserId(),status);
		for(Integer i =1 ; i<=countPage; i++){
			pageList.add(i);
		}
		Long userId = sessionInfo.getUserId();
		request.setAttribute("userId", userId);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("pageList", pageList);
		request.setAttribute("countPage", countPage);
		request.setAttribute("unpaidCount", unpaidCount);
		request.setAttribute("status", status);
		request.setAttribute("allOrder", findAllOrderByUserId);
		request.getSession().setAttribute("set", set);
		return new ModelAndView("/center/center_myorder");
	}
	
	/**
	 * @param status
	 * @param pageIndex
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月3日 上午9:37:51
	 * 根据订单编号，商品编码，商品名称筛选订单或商品
	 */
	@RequestMapping("/myOrder/screen/{status}/{pageIndex}")
	public ModelAndView screenOrder(Integer set,@PathVariable("status") Integer status,@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		List<Integer> pageList = new ArrayList<Integer>();
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(5);
		pageBean.setPageIndex(pageIndex);
		// 查询订单数据
		String no = request.getParameter("no");  //条件搜索
		List<OrderDto> findScreenOrder = orderService.findScreenOrder(status, sessionInfo.getUserId(), no, pageBean);
		// 总页数
		for(Integer i =1 ; i<=findScreenOrder.size(); i++){
			pageList.add(i);
		}
		request.setAttribute("unpaidCount", findScreenOrder.size());
		request.setAttribute("pageList", pageList);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("status", status);
		request.setAttribute("allOrder", findScreenOrder);
		request.getSession().setAttribute("set", set);
		return new ModelAndView("/center/center_myorder");
	}
	/**
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月3日 上午11:18:12
	 * 提交订单页面
	 */
	@RequestMapping("/commitOrder/{goodsId}")
	public ModelAndView commitOrder(@PathVariable("goodsId") String goodsId ,HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		BigDecimal sumPrice = new BigDecimal("0.00");
		BigDecimal discountSumPrice = new BigDecimal("0.00");  //不包含跨境商品和企业定制的商品总价格
		int accPoints=0;//用户拥有的积分
		int sumPoints=0;
		int isLogin = 1; //是否登录
		int out = 0;//是否包含跨境商品 0:包含跨境商品 1:不包含跨境商品
		int isDelivery = 0; //是否包含不支持全国配送的商品
		List<UserAddrees> findAddressByUserId = null;//用户地址
		List<Coupon> couponList = null;//用户优惠卷
		List<PcGoodsDto> shoppingCart = null;//购物车商品
		//1.查询出用户的所有收货地址
		if(ObjectUtils.isEmpty(sessionInfo)){
			shoppingCart = cartService.findUnLoadShoppingCart(goodsId);
		}else{
			isLogin = 0;
			findAddressByUserId = userAddressService.findAddressByUserId(sessionInfo.getUserId());//获取用户地址
			couponList = pcCouponSrevice.findCouponByUserId(sessionInfo.getUserId());//获取用户优惠卷信息
			shoppingCart = orderService.getShippingCart(sessionInfo.getUserId(),goodsId);//获取用户购物车信息
			//获取用户积分（历史数据，没有userinfo记录，此处报空指针）
			UserInfo userInfo=userInfoMapper.selectByPrimaryKey(sessionInfo.getUserId());
			if(userInfo!=null){
			    accPoints=userInfo.getAccPoints();
			}else{
			    accPoints=0;
			}
		}
		//2.获取当前日期开始的后五天的时间
		List<PcDateTime> pcDateTime = orderService.getPcDateTime();
		//3.获取发票类容
		List<String> invoice = orderService.getInvoice();
		List<Object> ids = new ArrayList<Object>();
		//计算订单总价格
		for (PcGoodsDto pcGoodsDto : shoppingCart) {
			if(pcGoodsDto.getSumPoints()==null||pcGoodsDto.getSumPoints()==0){
				sumPrice = sumPrice.add(pcGoodsDto.getSumPrice());
			}
			if(sessionInfo!=null&&(pcGoodsDto.getSumPoints()!=null && pcGoodsDto.getSumPoints()!=0)){  //积分商品
				sumPoints=sumPoints+pcGoodsDto.getSumPoints();
			}
			ids.add(pcGoodsDto.getId());
		}
		
		// 计算不包含 跨境商品,和企业定制商品的总价格
		if (!ObjectUtils.isEmpty(sessionInfo)) {
			discountSumPrice = commonGoodsService.getDiscountSumPrice(ids,sessionInfo.getUserId());
		} 
		
		//明天的日期时间(默认选中指定配送时间为明天)
		String tomarror = DateUtil.tomorrow().toString().substring(8, 10);
		//查询是否包含跨境商品
		boolean findGoodsIsOut = pcGoodsService.findGoodsIsOut(goodsId);
		if (!findGoodsIsOut) {
			out = 1 ; //不包含跨境商品
		}
		isDelivery = pcGoodsService.findNotIsDelivery(goodsId);
		request.setAttribute("toomarror", tomarror);
		request.setAttribute("sumPrice", sumPrice);
		request.setAttribute("sumPoints", sumPoints);
		request.setAttribute("accPoints", accPoints);
		request.setAttribute("cartList", shoppingCart);
		request.setAttribute("couponList", couponList);
		request.setAttribute("invoiceList", invoice);
		request.setAttribute("dateList", pcDateTime);
		request.setAttribute("userAddress", findAddressByUserId);
		request.setAttribute("out",out);
		request.setAttribute("isDelivery", isDelivery);
		request.setAttribute("isLogin", isLogin);
		return new ModelAndView("/order/commit_order");
	}
	
	@RequestMapping("/adduserAddress")
	public ModelAndView addUserAddress(UserAddrees address,HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		address.setUserId(sessionInfo.getUserId());
		String goodsId = request.getParameter("goodsId");
		if (address.getId() == 0) {
			userAddressService.addUserAddrees(address);
		} else {
			userAddressService.updateAddrees(address);
		}
		return new ModelAndView("redirect:/order/commitOrder/"+goodsId);
	}
	
	@RequestMapping("/deleteAddresss/{addressId}/{goodsId}")
	public ModelAndView deleteAddress(@PathVariable("addressId") Long addressId,@PathVariable("goodsId") String goodsId){
		userAddressService.deleteAddrees(addressId);
		return new ModelAndView("redirect:/order/commitOrder/"+goodsId);
	}
	
	/**
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月8日 下午5:38:56
	 * 提交订单
	 */
	@RequestMapping("/addOrder")
	public ModelAndView addOrder(HttpServletRequest request,HttpServletResponse response){
		String addressId = request.getParameter("adress");//订单收货地址
		String dateTime = request.getParameter("dateTime");//用户指定配送时间
		String invoice = request.getParameter("invocie");//发票信息
		String goodsId = request.getParameter("goodsIds");//订单商品的ID
		String remark = request.getParameter("remark");//订单用户备注
		String couponId = request.getParameter("conponId");//优惠卷id
		String idCard = request.getParameter("idCard");//身份证号码
		String taxperNo = request.getParameter("taxpayerNo");//纳税人识别号
		String companyName = request.getParameter("companyName"); //纳税人  企业名称
		String conName = request.getParameter("consigneeName");//收货人姓名
		String phone = request.getParameter("consigneePhone");//收货人联系电话
		String address = request.getParameter("consigneeAddress");//收货地址
		String sumPoints=request.getParameter("sumPoints");//总消耗积分
		String isCrossGoods = request.getParameter("isCrossGoods") ;//是否含有跨境商品  0含有  1 没有
		String provinceAddress = request.getParameter("provinceAddress"); //收货地址 省市 计算运费用到
		String cityAddress = request.getParameter("cityAddress"); //收货地址市
		String countyAddress = request.getParameter("countyAddress") ;//收货地址 区县的地址 计算运费用到
		SessionInfo sessionInfo = SessionInfo.get();
		UserInfo user = new UserInfo();
		if(ObjectUtils.isEmpty(sessionInfo)){
			user.setRealName("匿名");
			addressId="0";
		}else{
			user.setId(sessionInfo.getUserId());
			user.setRealName(sessionInfo.getUserName());
		}
		int commit = orderCommitService.isCommitOrder();
		if(commit>0){
			throw new BusinessException("暂时不能提交订单");
		}
		Order addOrder = orderService.addOrder(response,phone,address,conName,taxperNo,companyName,idCard,Long.parseLong(addressId), dateTime, invoice, goodsId, remark, couponId,provinceAddress,cityAddress,countyAddress,user);
		if (ObjectUtil.isNull(addOrder)) {
			return new ModelAndView("/order/reminder");  // 重复下了订单
		}
		if(!ObjectUtils.isEmpty(sessionInfo)){  //扣除积分
			UserInfo userInfo = pcUserService.getUserInfo(sessionInfo.getUserId());
			int accPoints=userInfo.getAccPoints();
			userInfo.setAccPoints(accPoints-Integer.parseInt(sumPoints));
			userInfoMapper.updateByPrimaryKeySelective(userInfo);
			userInfo = pcUserService.getUserInfo(sessionInfo.getUserId());
			request.setAttribute("userInfo", userInfo);
			if (!StringUtil.isEmpty(sumPoints) && Integer.parseInt(sumPoints) != 0) {
				pcUserService.insertOrderUserSpendPoints(sessionInfo.getUserId(), addOrder.getId(), Integer.parseInt(sumPoints));
				request.setAttribute("userInfo", userInfo);
			}
		}
		/*if(!ObjectUtils.isEmpty(sessionInfo)){
			boolean checkOrder = commonPayOrderService.checkOrder(addOrder, sessionInfo.getUserId());
			if(checkOrder){//订单满足满额减条件
				addOrder.setTotal(addOrder.getTotal().subtract(new BigDecimal(15)));
				orderMapper.updateByPrimaryKey(addOrder);
			}
		}*/
		request.setAttribute("payOrder", addOrder);
		request.setAttribute("isCrossGodods", isCrossGoods);
		return new ModelAndView("redirect:/order/payOrder/"+addOrder.getId()); 
	}
	
	
	
	/**
	 * @param orderId
	 * @param request
	 * @return ModelAndView
	 * @author 个人中心支付订单
	 * @date 2017年8月9日 上午11:29:15
	 */
	@RequestMapping("/payOrder/{orderId}")
	public ModelAndView payOrder(@PathVariable("orderId") Long orderId,HttpServletRequest request){
		int out = 0,isLogin = 1;
		SessionInfo sessionInfo = SessionInfo.get();
		Order order = orderService.findOrderByOrderId(orderId);
		if (ObjectUtil.isNotNull(sessionInfo)) {
			UserInfo userInfo = pcUserService.getUserInfo(sessionInfo.getUserId());
			request.setAttribute("userInfo", userInfo);
			isLogin = 0 ;
		}
		String goodsId = orderService.getOrderGoodsId(orderId);
		//查询是否包含跨境商品
		boolean findGoodsIsOut = pcGoodsService.findGoodsIsOut(goodsId);
		if (!findGoodsIsOut) {
			out = 1 ; //不包含跨境商品
		}
		Integer containsSpecileGoods = orderService.isContainsSpecileGoods(orderId);
		request.setAttribute("address", order.getArea());
		request.setAttribute("isCrossGodods", out);
		request.setAttribute("payOrder", order);
		request.setAttribute("isLogin", isLogin); //是否登录 1 未登录 0 已登录
		request.setAttribute("containsSpecileGoods", containsSpecileGoods);//0:包含 1：不包含
		return new ModelAndView("/order/pay_order");
	}
	
	/**
	 *  处理货到付款的从定向成功页面
	 * @param orderId
	 * @param balancePay
	 * @param payMethod
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月9日 上午11:27:16
	 * 订单支付
	 */ 
	@RequestMapping("/pay")
	public ModelAndView testadd(@Param("orderId") Long orderId,@Param("balancePay") BigDecimal balancePay,
			@Param("payMethod") Integer payMethod,HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		if(ObjectUtils.isEmpty(sessionInfo)){
			orderService.updateOrder(orderId, payMethod, balancePay, new Long("0"));//匿名购买的用户ID为0
		}else{
			orderService.updateOrder(orderId, payMethod, balancePay, sessionInfo.getUserId());
		}
		return new ModelAndView("redirect:/order/success?orderId="+orderId);
	}
	
	/**
	 * 根据订单id跳转成功页面
	 * @param orderId
	 * @param balancePay 
	 * @param payMethod
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月9日 上午11:27:16
	 */ 
	@RequestMapping("/success")
	public ModelAndView success(@Param("orderId") Long orderId,HttpServletRequest request){
		
		Order order = orderService.getOrderById(orderId);
		request.setAttribute("orderId", orderId);
		request.setAttribute("payType", order.getPayType());
		return new ModelAndView("/test/success");
	}
	/**
	 * @param orderId
	 * @param request
	 * @return ModelAndView
	 * @author 订单作废
	 * @date 2017年9月1日 下午2:55:23
	 */
	@RequestMapping("/delete/{orderId}")
	public ModelAndView deleteOrder(@PathVariable("orderId") Long orderId,HttpServletRequest request){
		
		orderService.deleteOrderByOrder(orderId);
		
		return null;
	}
	/**
	 * @param orderIds
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年9月13日 下午8:14:11
	 * 确认收货
	 */
	@RequestMapping("/receive/{orderId}")
	public ModelAndView receiveOrder(@PathVariable("orderId") String orderIds,HttpServletRequest request){
		
		orderService.updateOrderByOrderId(orderIds);
		
		return null;
	}
		
	@RequestMapping("/detail/{orderId}")
	public ModelAndView orderDetail(@PathVariable("orderId") Long orderId,HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		if(ObjectUtils.isEmpty(sessionInfo)){
			throw new BusinessException("用户未登录！");
		}
		OrderDto findOderyDetailById = orderService.findOderyDetailById(orderId,sessionInfo.getUserId());
		request.setAttribute("orderDto", findOderyDetailById);
		request.setAttribute("tradeType", PayBaseImpl.getTradeType()); //支付方式
		return new ModelAndView("/center/center_order_detail");
	}
	
	/**
	 * 计算出运费
	 * @param province
	 * @param goodsIds
	 * @param sumPrice
	 * @return    
	 * @return RespData    
	 * Date:2017年10月17日 下午9:01:59 
	 * @author hexw
	 */
	@RequestMapping("/getFreight")
	public RespData getFreight(String province,String goodsIds,String sumPrice,String area){
		Map<String,Object> result = new HashMap<String,Object>();
		SessionInfo sessionInfo = SessionInfo.get();
		if (ObjectUtil.isNull(sessionInfo)) {
			result = orderService.getFreight(province, area, goodsIds, sumPrice, null);
		} else {
			result = orderService.getFreight(province,area, goodsIds, sumPrice, sessionInfo.getUserId());
		}
		RespData respData = new RespData();
		respData.setData(result);
		return respData;
	}
	
	/**
	 * 优惠券弹窗
	 * @param request
	 * @param pageBean
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年11月1日 下午4:07:14 
	 * @author hexw
	 */
	@RequestMapping("/couponListPopup")
	public ModelAndView couponListPopup(HttpServletRequest request,PageBean pageBean){
		pageBean.setPageSize(6);
		SessionInfo sessionInfo = SessionInfo.get();
		if (ObjectUtil.isNotNull(sessionInfo)) {
			pcCouponSrevice.findCouponByUserId(sessionInfo.getUserId(),pageBean);
		}
		request.setAttribute("couponList", pageBean.getData());
		request.setAttribute("totalPage", pageBean.getTotalPage());
		request.setAttribute("pageIndex", pageBean.getPageIndex());
		return new ModelAndView("/order/user_coupon_list");
	}
	/**
	 * 用户通过优惠卷编号绑定优惠卷
	 * @return Integer
	 * @author lij
	 * @date 2017年11月20日 下午3:22:53
	 */
	@RequestMapping("/userBindCoupon")
	public Integer userBindCoupon(@Param("paperCode") String paperCode){
		SessionInfo sessionInfo = SessionInfo.get();
		Integer result = 0 ;
		if (ObjectUtil.isNotNull(sessionInfo)) {
			Long userId = sessionInfo.getUserId();
			//请求后的返回值1:优惠卷不存在 2.改优惠卷已经被绑定 3.已经绑定了相同类型的优惠卷 4.绑定成功
			result = pcCouponSrevice.userBindPaperCoupon(userId, paperCode);
		}
		return result;
	}
	/*@RequestMapping("/shoppingCardPay")
	public ModelAndView shoppingCardPay(HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		if (ObjectUtil.isNotNull(sessionInfo)) {
			
		}
		return new ModelAndView("/test/success");
	}
	*/
	/**
	 * 判断用户以前是否购买过
	 * @param goodsId
	 * @return Integer
	 * @author lij
	 * @date 2018年1月3日 上午10:57:31
	 */
	@RequestMapping("/isContantSpeicleGoods")
	public Integer isContantSpeicleGoods(@Param("goodsIds") String goodsIds){
		SessionInfo sessionInfo = SessionInfo.get();
		Integer integer = 0;
		if(ObjectUtils.isEmpty(sessionInfo)){
			String[] split = goodsIds.split(",");
			for (String string : split) {
				/*//1.1判断是否包含了拼购商品
				if(string.contains(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.toString())){
					integer = 3;//匿名不能购买
				}*/
				//1.1判断是否包含了三免一商品
				if(Arrays.asList(SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS).contains(Long.parseLong(string))){
					integer = 3;
				}
			}
		}else{
			try {
				integer = actitvityService.OrderContainSpeicleGoods(sessionInfo.getUserId(), goodsIds);
			} catch (BusinessException e) {
				int code = e.getCode();
				if(code == 77){//77.表示商品已经购买了拼购商品
					integer = 1;//免邮商品已经购买完了
				}
				if(code == 78){
					integer = 2;//表示商品已经购买了拼购商品
				}
			}
		}
		return integer;
	}
	
}
