package com.zfj.xmy.pc.web.controller.cart;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.activity.service.common.LimitActivityService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.persistence.pojo.app.AppCouponInDto;
import com.zfj.xmy.common.persistence.pojo.app.CommonActivityGoodsOutDto;
import com.zfj.xmy.common.persistence.pojo.app.CommonActvivtyGoodsInDto;
import com.zfj.xmy.common.service.CommonActivityService;
import com.zfj.xmy.common.service.CommonCouponService;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCartDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCollectionGoodsDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCouponDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.service.pc.PcShoppingCartService;
import com.zfj.xmy.order.service.pc.ShoppingCartService;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.pc.web.controller.BaseController;
import com.zfj.xmy.user.service.common.UserInfoService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController extends BaseController{
	@Autowired
	private PcShoppingCartService pcShoppingCartService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private CommonLimitActivityService commonLimitActivityService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private CommonCouponService commonCouponService;
	
	@Autowired
	private CommonActivityService commonActivityService;
	
	@Autowired
	private ShoppingCartMapper cartMapper;
	
	/**
	 * @param goodsId
	 * @param goodsNum
	 * @return Integer
	 * @author lij
	 * @date 2017年8月9日 上午9:56:40
	 * 添加到购物车
	 */
	@RequestMapping("/add/{goodsId}/{goodsNum}")
	public Integer addCart(@PathVariable("goodsId") Long goodsId,@PathVariable("goodsNum") Integer goodsNum,
			HttpServletResponse response){
		SessionInfo sessionInfo = SessionInfo.get();
		Integer isDel = 0;
		PcCartDto cartDto = new PcCartDto();
		cartDto.setGoodsId(goodsId);
		cartDto.setCartNum(goodsNum);
		cartDto.setPoints(0);
		if(ObjectUtils.isEmpty(sessionInfo)){//未登录用户添加购物车
			cartService.addShoppingCart(cartDto, response);
		}else{//已登录用户添加到购物车
			try {
				pcShoppingCartService.addShoppingCart(sessionInfo.getUserId(), goodsId, goodsNum);
			} catch (BusinessException e) {
				isDel=e.getCode();
			}
		}
		return isDel;
	}
	
	/**
	 * @param goodsId
	 * @param goodsNum
	 * @return Integer
	 * @author lij
	 * @date 2017年8月9日 上午9:56:40
	 * 积分商品添加到购物车
	 */
	@RequestMapping("/addPoints/{goodsNum}/{pointsGoodsId}")
	public Integer addPointsCart(@PathVariable("goodsNum") Integer goodsNum,
			@PathVariable("pointsGoodsId") Long pointsGoodsId,HttpServletResponse response){
		SessionInfo sessionInfo = SessionInfo.get();
		Integer isDel = 0;
		PointsGoods pointsGoods = pcShoppingCartService.getpointsGoods(pointsGoodsId);
		if (ObjectUtil.isNotNull(pointsGoods)) {
			PcCartDto cartDto = new PcCartDto();
			cartDto.setGoodsId(pointsGoods.getGoodsId());
			cartDto.setCartNum(goodsNum);
			cartDto.setPoints(pointsGoods.getPoints());
			int result = pcShoppingCartService.checkPointGoods(pointsGoodsId,sessionInfo.getUserId(), pointsGoods.getGoodsId(), goodsNum); //判断购买的商品数量是否符合活动规则
			if (result == 1) {
				isDel = 2;  //超过了活动的限购
			} else {
				try {
					pcShoppingCartService.addPointsShoppingCart(sessionInfo.getUserId(), pointsGoods.getGoodsId(), goodsNum,pointsGoods.getPoints(),pointsGoodsId.intValue());
				} catch (BusinessException e) {
					isDel=e.getCode();
				}
			}
		}
		
		return isDel;
	}
	/** 添加活动商品
	 * @param request
	 * @param shoppingCart
	 * @return Integer 
	 * @author lij
	 * @date 2017年10月14日 下午3:18:22
	 */
	@RequestMapping("/addActivityGoods")
	public Integer addActivityGoods(HttpServletRequest request,ShoppingCart shoppingCart){
		int result = 0;
		boolean flag = true;
		SessionInfo sessionInfo = SessionInfo.get();
		if (ObjectUtil.isNotNull(sessionInfo) && ObjectUtil.isNotNull(shoppingCart.getActivityType())) {
			//判断是否添加免邮商品
			ReqData reqData = new ReqData();
			reqData.putValue("user_id", sessionInfo.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
			List<ShoppingCart> selectByExample = cartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			for(ShoppingCart carts:selectByExample){
				if(Arrays.asList(SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS).contains(carts.getGoodsId())){
					result = 3;//存在单独购买的商品
					flag = false;
				}
			}
			if(flag){
				shoppingCart.setUserId(sessionInfo.getUserId());
				shoppingCart.setCreateTime(new Date());
				if (shoppingCart.getActivityType() == SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY) { //买即赠
					pcShoppingCartService.addActivityCart(shoppingCart); 
				}
				if (shoppingCart.getActivityType() == SystemConstant.ACTIVITY.LIMIT_ACTIVITY) { //限时限量活动
					ShoppingCart cart = pcShoppingCartService.findShoppingCart(shoppingCart.getUserId(), shoppingCart.getGoodsId(),shoppingCart.getActivityId());
					if (ObjectUtil.isNotNull(cart)) {  //如果购物车已存在该活动商品 只判断添加商品的个数 是否超过了限制的个数
						result = pcShoppingCartService.checkActivityGoodsNum(shoppingCart.getActivityId(), shoppingCart.getGoodsId(), shoppingCart.getNum()+cart.getNum());
						if (result == 0) {
							pcShoppingCartService.addActivityCart(shoppingCart); 
						} 
					} else {  //新增商品 即判断 参与次数 也判断商品数量
						result =  commonLimitActivityService.checkLimitActivity(shoppingCart);
						if (result == 0) {
							pcShoppingCartService.addActivityCart(shoppingCart); 
						} 
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月8日 上午9:35:16
	 * 查询购物车
	 */
	@RequestMapping("/shoppingCart")
	public ModelAndView findShoppingCart(HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		List<PcGoodsDto> findShoppingCartByUserId = null;
		Integer accPoints = 0;  //积分
		if(ObjectUtils.isEmpty(sessionInfo)){
			request.setAttribute("islogin",1);
			findShoppingCartByUserId = cartService.findUnLoadShoppingCart(null);
		}else{
			request.setAttribute("islogin",0);
			findShoppingCartByUserId = pcShoppingCartService.findShoppingCartByUserId(sessionInfo.getUserId());
			accPoints = userInfoService.getUserInfo(sessionInfo.getUserId()).getAccPoints();
			
		}
		List<PcCouponDto> couponDtos=cartService.findCoupon(findShoppingCartByUserId);
		if (!ObjectUtils.isEmpty(sessionInfo)) {
			List<CouponUser> userCoupon=commonCouponService.findCouponUserByUserId(sessionInfo.getUserId());
			for(PcCouponDto couponDto:couponDtos){
				Integer integer = commonCouponService.countCoup(couponDto.getId());
				if(integer>=couponDto.getCouponCount()){
					couponDto.setIsGet(2);
				}else{
					couponDto.setIsGet(0);
				}
				for(CouponUser couponUser:userCoupon){
					if(couponDto.getId().equals(couponUser.getCouponId())){
						couponDto.setIsGet(1);
					}
				}
			}
		}
		request.setAttribute("shoppingCart", findShoppingCartByUserId);
		request.setAttribute("couponData",couponDtos);
		request.setAttribute("accPoints",accPoints);
		return new ModelAndView("/cart/shopping_cart");
	}
	
	/**
	 * @param goodsId
	 * @param num
	 * @return ModelAndView
	 * @author 修改购物车商品数量
	 * @date 2017年8月8日 上午9:34:37
	 */
	@RequestMapping("/updateShoppingCart/{goodsId}/{num}")
	public ModelAndView updateShoppingCart(@PathVariable("goodsId") Long goodsId,@PathVariable("num") Integer num,
			HttpServletResponse response){
		SessionInfo sessionInfo = SessionInfo.get();
		PcCartDto cartDto = new PcCartDto();
		cartDto.setCartNum(num);
		cartDto.setGoodsId(goodsId);
		if(ObjectUtils.isEmpty(sessionInfo)){
			cartService.addShoppingCart(cartDto, response);
		}else{
			pcShoppingCartService.updateShoppingCartNum(sessionInfo.getUserId(),num,goodsId);
		}
		return new ModelAndView("redirect:/cart/shoppingCart");
	}
	/**
	 * @param goodsId
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月8日 上午9:34:52
	 * 移除购物车商品
	 */
	@RequestMapping("/delShoppingCart/{goodsId}")
	public ModelAndView deleteShoppingCart(@PathVariable("goodsId") Long goodsId,HttpServletResponse response,HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		PcCartDto pcCartDto = new PcCartDto();
		pcCartDto.setGoodsId(goodsId);
		if(ObjectUtils.isEmpty(sessionInfo)){
			cartService.deleteShoppingCart(pcCartDto, response);
		}else{
			pcShoppingCartService.deleteShoppingCartGoods(sessionInfo.getUserId(), goodsId);
		}
		//return new ModelAndView("redirect:/cart/shoppingCart");
		return null;
	}
	
	
	/**
	 * 批量删除商品
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年10月20日 下午4:05:18 
	 * @author 
	 */
	@RequestMapping("/batchDelGoods")
	public ModelAndView batchDelGoods(String goodsIds,HttpServletResponse response){
		SessionInfo sessionInfo = SessionInfo.get();
		if(ObjectUtils.isEmpty(sessionInfo)){
			cartService.deletGoods(goodsIds,null,response);
		}else{
			cartService.deletGoods(goodsIds,sessionInfo.getUserId(),response);
		}
		return null;
	}
	
	
	/**
	 * @param goodsId
	 * @param categoryName
	 * @param request
	 * @return Integer
	 * @author lij
	 * @date 2017年8月8日 下午3:12:57
	 * 添加到收藏夹
	 */
	@RequestMapping("/addCollection")
	public Integer addCollectionGoods(PcCollectionGoodsDto dto, HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		//1. 判断是否已收藏过该商品
		Integer isDel = pcShoppingCartService.findCollectinoGoodsIsDel(sessionInfo.getUserId(), dto.getGoodsId());
		if(isDel <= 0){
		//2. 收藏商品	
			pcShoppingCartService.addCollectionGoods(sessionInfo.getUserId(),dto);
		}
		return isDel;
	}
	
	/**
	 * 批量收藏商品
	 * @param goodsIds
	 * @return    
	 * @return Integer    
	 * Date:2017年10月20日 下午4:57:44 
	 * @author hexw
	 */
	@RequestMapping("/addBatchCollectionGoods")
	public RespData addBatchCollectionGoods(String goodsIds){
		RespData respData = new RespData();
		SessionInfo sessionInfo = SessionInfo.get();
		pcShoppingCartService.addBatchCollectionGoods(sessionInfo.getUserId(),goodsIds);
		return respData;
	}
	
	/**
	 * 领取优惠券
	 * @param couponId
	 * @param request
	 * @return    
	 * @return RespData    
	 * Date:2017年10月10日 下午4:56:54 
	 * @author hexw
	 */
	@RequestMapping("/getCoupon")
	public RespData getCoupon(long couponId,HttpServletRequest request){
		RespData respData = new RespData();
		SessionInfo sessionInfo = SessionInfo.get();
		AppCouponInDto coupon = new AppCouponInDto();
		coupon.setId(couponId);
		if (ObjectUtil.isNotNull(sessionInfo)) {
			coupon.setUserId(sessionInfo.getUserId());
		}
		try {
			commonCouponService.toReceiveCoupon(coupon, true);
		} catch (BusinessException be) {
			// TODO: handle exception
			respData.setResultCode(be.getCode());
			respData.setData(be.getMessage());
		}
		return respData;
	}
	
}