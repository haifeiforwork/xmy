package com.zfj.xmy.wap.web.controller.mine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.app.pojo.dto.CouponSearchInDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.coupon.GetAvailbleCouponOutDto;
import com.zfj.xmy.activity.service.cms.CouponService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.BrowsedGoods;
import com.zfj.xmy.common.persistence.pojo.EntityCoupon;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.persistence.pojo.app.AppShoppingCardOutDto;
import com.zfj.xmy.common.service.CommonCouponService;
import com.zfj.xmy.common.service.CommonShopingCardService;
import com.zfj.xmy.order.persistence.app.pojo.dto.BrowsedGoodsIdsInDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.BrowsedGoodsOutDto;
import com.zfj.xmy.order.service.wap.WapBrowsedGoodsService;
import com.zfj.xmy.order.service.wap.WapUserSpendPointsService;
import com.zfj.xmy.user.service.wap.WapShoppingCardRecordService;
import com.zfj.xmy.user.service.wap.WapUserService;
import com.zfj.xmy.wap.web.common.AjaxResult;
import com.zfj.xmy.wap.web.common.SessionUtils;
import com.zfj.xmy.wap.web.controller.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private WapUserService userService;
	
	@Autowired 
	private WapShoppingCardRecordService shoppingCardRecordService;
	
	@Autowired
	private WapUserSpendPointsService userSpendPointsService;
	
	@Autowired
	private WapBrowsedGoodsService browsedGoodsService;
	
	@Autowired
	private CommonShopingCardService shoppingCardService;
	
	@Autowired
	private CommonCouponService commonCouponService;
		
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@Autowired
	private CouponService couponService;
	
//	@RequestMapping("/sendSms")
//	@ResponseBody
//	public AjaxResult sendSms(String phone) {
//		AjaxResult result = userService.sendSms(phone);
//		return result;
//	}
	
	@RequestMapping("/modifyPassword")
	@ResponseBody
	public AjaxResult modifyPassword(HttpServletRequest req, String old, String neu, String re) {
		User user = SessionUtils.getUser(req);
		int result = userService.modifyPassword(user, old, neu, re);
		if(result == -1) {
			return new AjaxResult(-1, "密码不正确", null);
		} else if(result == -2) {
			return new AjaxResult(-2, "两次输入不匹配", null);
		} else if(result == 0) {
			return new AjaxResult(0, "服务器繁忙", null);
		}
		return new AjaxResult(1, "修改成功", null);
	}
	
	/**
	 * 购物卡激活明细
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月25日上午10:12:32
	 */
	@RequestMapping("/activateDetail")
	@ResponseBody
	public AjaxResult activateDetail(HttpServletRequest req){
		Long id = SessionUtils.getUser(req).getId();
		List<AppShoppingCardOutDto> activateDetail = shoppingCardService.activateDetail(id);
		return new AjaxResult(1, null, activateDetail);
	}
	
	@RequestMapping("/getCouponsGoods")
	@ResponseBody
	public AjaxResult getCoupons(HttpServletRequest req, CouponSearchInDto couponSearchInDto){
		//获取userId
		Long userId = SessionUtils.getUser(req).getId();
		
		/*	// 1 先获取电子优惠券的
		List<EntityCoupon> findCoupon = couponService.findCoupon(userId,couponSearchInDto);
		List<EntityCoupon> coupons=new ArrayList<>();
		for (Coupon coupon : findCoupon) {
			EntityCoupon entityCoupon=new EntityCoupon();
			BeanUtils.copyProperties(coupon, entityCoupon);
			entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
			coupons.add(entityCoupon);
		}
		// 2 再获取实体纸质优惠券*/
		//全部获取到了(电子优惠券+纸质优惠券)
		List<GetAvailbleCouponOutDto> findCoupon = couponService.findUserCoupon(userId, couponSearchInDto);
		return new AjaxResult(1, null, findCoupon);
	}
	
	/**
	 * app购物卡绑定
	 * @Description 
	 * @param shoppingCard
	 * @return
	 * @Author liuw
	 * @Date 2017年8月21日下午5:25:52
	 */
	@RequestMapping("/shoppingCardBinding")
	@ResponseBody
	public AjaxResult setShoppingCardBinding(HttpServletRequest req, ShoppingCard shoppingCard){
		//获取userId
		Long id = SessionUtils.getUser(req).getId();
		if(StringUtil.isEmpty(shoppingCard.getCardNum())||StringUtil.isEmpty(shoppingCard.getCardPassword())){
			return new AjaxResult(0, "请输入卡号和密码!", null);
		}
		Integer result = shoppingCardService.addShopingCardWithUserId(shoppingCard.getCardNum(), shoppingCard.getCardPassword(), id);
		if(result==SystemConstant.SHOPPING_CARD.RESULTERROR){
			return new AjaxResult(0, "错误,卡号和密码不对应!", null);
		}else if(result==SystemConstant.SHOPPING_CARD.RESULTBOUND){
			return new AjaxResult(0, "错误,该卡已经绑定!", null);
		}
		SessionUtils.reSetUserInfo(req.getSession(), userService);
		return new AjaxResult(1, "绑定成功", null);
		
	}
	
	/**
	 * 
	 * @Description 批量删除足迹记录
	 * @param delIdsDto
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日下午7:16:48
	 */
	@RequestMapping("/delBrowsedGoods")
	@ResponseBody
	public AjaxResult delBrowsedGoods(BrowsedGoodsIdsInDto delIdsDto){
		browsedGoodsService.delBrowsedGoods(delIdsDto.getIds().toArray());
		return new AjaxResult(1, null, null);
	}
	
	/**
	 * 
	 * @Description 获取足迹表中的信息
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日下午3:25:28
	 */
	@RequestMapping("/getBrowsedGoods")
	@ResponseBody
	public AjaxResult getBrowsedGoods(HttpServletRequest req){
		//获取userId
		Long id = SessionUtils.getUser(req).getId();
		ReqData reqData=new ReqData();
		reqData.setSortorder(" browsed_time desc limit 8 ");
		reqData.putValue(SystemConstant.BROWSEDGOODS.USER_ID, id, SystemConstant.REQ_PARAMETER_EQ);
		//1 先查找足迹表中的数据
		 List<BrowsedGoods> findBrowsedGoods = browsedGoodsService.findBrowsedGoods(reqData);
		//2 查找所有商品
		List<BrowsedGoodsOutDto> browsedGoodsToBrowsedGoodsDto = browsedGoodsService.browsedGoodsToBrowsedGoodsDto(findBrowsedGoods);
		return new AjaxResult(1, null, browsedGoodsToBrowsedGoodsDto);
	}
	
	
	@RequestMapping("/getUserBillOrPointFlow")
	@ResponseBody
	public AjaxResult getUserBillFlow(Integer type, HttpServletRequest req){
		//获取userId
		Long id = SessionUtils.getUser(req).getId();
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.userSpendPoints.USER_ID, id, SystemConstant.REQ_PARAMETER_EQ);
		// 设置类型为账单（金额）或者积分
		reqData.putValue(SystemConstant.userSpendPoints.TYPE, type, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_del", SystemConstant.userSpendPoints.STATUS_NODELETE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("creat_time");
		reqData.setSortorder("desc");
		try {
			List<UserSpendPoints> userSpendPoints = userSpendPointsService.findUserSpendPoints(reqData);
			return new AjaxResult(1, null, userSpendPoints);
		}catch(Exception e) {
			e.printStackTrace();
			return new AjaxResult(0, null, null);
		}
	}
	
	@RequestMapping("/getUserBalaneFlow")
	@ResponseBody
	public AjaxResult getUserBalanceFlow(HttpServletRequest req) {
		Long id = SessionUtils.getUser(req).getId();
		try {
			CriteriaParameter cari = new CriteriaParameter();
			Criteria ca = cari.createCriteria();
			ca.equalTo(SystemConstant.SHOPPING_CARD.USER_ID, id);
			cari.setOrderByClause("use_time desc");
			List<ShoppingCardRecord> findUserShoppingCardRecord = shoppingCardRecordService.findUserShoppingCardRecord(cari);
			return new AjaxResult(1, null, findUserShoppingCardRecord);
		}catch(Exception e) {
			return new AjaxResult(-1, null, null);
		}
	}
	
	@RequestMapping("/setting/modify/sex")
	@ResponseBody
	public AjaxResult setSex(HttpServletRequest req, Long id, Integer sex) {
		int result = userService.updateSex(id, sex);
		SessionUtils.reSetUserInfo(req.getSession(), userService);
		return new AjaxResult(result, null, null);
	}
	
	@RequestMapping("/setting/modify/nick")
	@ResponseBody
	public AjaxResult setNick(HttpServletRequest req, Long id, String nick) {
		int result = userService.updateName(id, nick);
		SessionUtils.reSetUser(req.getSession(), userService);
		return new AjaxResult(result, null, null);
	}
	
	@RequestMapping("/setting/modify/birthday")
	@ResponseBody
	public AjaxResult setBirthday(HttpServletRequest req, Long id, Date birthday) {
		int result = userService.updateBirthday(id, birthday);
		SessionUtils.reSetUserInfo(req.getSession(), userService);
		return new AjaxResult(result, null, null);
	}
	
	@RequestMapping("/setting/modify/phone")
	@ResponseBody
	public AjaxResult setPhone(HttpServletRequest req, Long id, String phone) {
		int result = 0;
		result = userService.updatePhone(id, phone);
		if(result == -1) {
			return new AjaxResult(-1, "请输入正确的座机号", null);
		}
		SessionUtils.reSetUserInfo(req.getSession(), userService);
		return new AjaxResult(result, null, null);
	}
	
	
	
}
