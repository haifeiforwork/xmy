package com.zfj.xmy.app.web.controller.activity;  

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.ws.rs.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.app.pojo.dto.AppActivityInDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.AppCouponOutDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.AppQuestionInDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.coupon.CouponCenterOutDto;
import com.zfj.xmy.activity.persistence.common.pojo.dto.AppBuyAndPresentOutDto;
import com.zfj.xmy.activity.persistence.common.pojo.dto.LimitActivityDir;
import com.zfj.xmy.activity.service.app.PointsService;
import com.zfj.xmy.activity.service.cms.BuyAndPresentService;
import com.zfj.xmy.activity.service.cms.CouponService;
import com.zfj.xmy.activity.service.common.LimitActivityService;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.ActivityQuestion;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.app.AppAdInfoDto;
import com.zfj.xmy.common.persistence.pojo.app.AppAdvertisementOutDto;
import com.zfj.xmy.common.persistence.pojo.app.AppClassificationTopImgDto;
import com.zfj.xmy.common.persistence.pojo.app.AppCouponInDto;
import com.zfj.xmy.common.persistence.pojo.app.AppGoodsCondense;
import com.zfj.xmy.common.persistence.pojo.common.couponReceiveByPhoneDto;
import com.zfj.xmy.common.service.AppAdvertisementService;
import com.zfj.xmy.common.service.CommonCouponService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsOut;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppTermDataDir;
import com.zfj.xmy.goods.service.app.AppGoodsService;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.user.persistence.pojo.dto.app.MobilePhoneDto;
import com.zfj.xmy.util.StringUtils;

/** 
 * @Title: AppActivityController.java 
 * @Package com.zfj.xmy.app.web.controller.activity 
 * @Description: 
 * @author hexw
 * @date 2017年7月31日 下午1:59:45 
 */
@RequestMapping(value="/activity",params=SystemConstant.MOBILE_VERSION_V10)
@RestController
public class AppActivityController {

	@Autowired
	private LimitActivityService limitActivityService;
	
	@Autowired
	private CouponService couponService;
	@Autowired
	private CommonCouponService commonCouponService;
	
	@Autowired
	private AppAdvertisementService advertisementService;
	@Autowired
	private BuyAndPresentService buyAndPresentService;
	@Autowired
	private CmsGoodsService cmsGoodsService;
	@Autowired
	private PointsService pointsService;
	@Autowired
	private OnLineActivityService activityService;
	/**
	 * 开抢了活动查询
	 * @return    
	 * @return ResponseEntity<List<AppLimitActivityDir>>    
	 * Date:2017年7月31日 下午2:09:20 
	 * @author hexw
	 */
	@RequestMapping("/findActivity")
	@Exclusion
	public ResponseEntity<AppResp> findActivity(){
		return AppResp.get(limitActivityService.findLimitActivityList(null)) ;
	}
	/**
	 * 根据type类型查找活动  活动类型 （1冰点价，2天天特价，3每周特价，4整件惠）
	 * @Description 
	 * @param type
	 * @return
	 * @Author liuw
	 * @Date 2017年8月17日下午4:37:28
	 */
	@RequestMapping("/findActivityByCondition")
	@Exclusion
	public ResponseEntity<AppResp> findActivityByCondition(@RequestBody AppActivityInDto activityInDto){
		return AppResp.get(limitActivityService.findLimitActivityList(activityInDto)) ;
	}
	
	/**
	 * 领券中心 
	 * @return
	 * @Description 
	 * @date 2017年11月22日  下午2:56:45
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/couponCenters")
	public ResponseEntity<AppResp> getAllCoupons20(){
		Long userId = AppLocalInfo.getUserId();
		
		//1.查询结果
		List<CouponCenterOutDto> centerOuts = couponService.couponCenter(userId);
		//2.修改app端商品图片大小 200x200
		for (CouponCenterOutDto couponCenterOutDto : centerOuts) {
			 List<AppGoodsOut> appGoodsOuts = couponCenterOutDto.getGoodsList();
			 for (AppGoodsOut appGoodsOut : appGoodsOuts) {
				appGoodsOut.setImgPath(appGoodsOut.getImgPath()+"?x-oss-process=image/resize,m_fixed,h_200,w_200");
			}
		}
		return AppResp.get(centerOuts);
	}
	
//	/**
//	 * 领券中心
//	 * @Description 
//	 * @return
//	 * @Author liuw
//	 * @Date 2017年8月8日下午3:03:46
//	 */
//	@Deprecated
//	@RequestMapping(value="/couponCenter")
//	public ResponseEntity<AppResp> getAllCoupons(){
//		Long userId = AppLocalInfo.getUserId();
//		List<AppCouponOutDto> findsAllUsableCoupon = couponService.findsAllUsableCoupon(userId);
//		return AppResp.get(findsAllUsableCoupon);
//	}
	
	
	/**
	 * 领取优惠券
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月11日下午4:17:38
	 */
	@RequestMapping("/couponReceive")
	public ResponseEntity<AppResp> couponReceive(@RequestBody AppCouponInDto coupon){
		Long userId = AppLocalInfo.getUserId();
		coupon.setUserId(userId);
		//couponService.updateCouponReceive(coupon);
		commonCouponService.toReceiveCoupon(coupon, false);		
		return AppResp.get();
	}
	
	/**
	 * 使用任意手机号领取优惠券
	 * (一个手机号只能领取一次)
	 * @param phoneDto
	 * @return
	 * @Description 
	 * @date 2018年1月2日  下午3:31:54
	 * @author wy
	 * 2018
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping("/receiveByPhone")
	@Exclusion
	public ResponseEntity<AppResp> couponReceiveByPhone(@RequestBody couponReceiveByPhoneDto couponReceiveByPhoneDto){
		commonCouponService.couponReceiveByPhone(couponReceiveByPhoneDto);		
		return AppResp.get();
	}
	
	/**
	 * 取出app开枪了首页top图
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月8日下午1:55:30
	 */
	@RequestMapping("/grabTopImg")
	@Exclusion
	public ResponseEntity<AppResp> getGrabTopImg(){
		AppAdInfoDto grabTopImg = advertisementService.getGrabTopImg();
		return AppResp.get(grabTopImg);
	}
	
	/**
	 * 分类页top图
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月8日下午2:35:32
	 */
	@RequestMapping("/classificationTopImg")
	@Exclusion
	public ResponseEntity<AppResp> getclassificationTopImg(){
		List<AppClassificationTopImgDto> classificationTopImgDto = advertisementService.getClassificationTopImgDto(SystemConstant.AdInfoImage.TYPE_CLASSIFICATIONTOPIMG);
		return AppResp.get(classificationTopImgDto);
	}
	/**
	 * 首页top图广告
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月9日下午3:37:12
	 */
	@RequestMapping("/indexTopImg")
	public ResponseEntity<AppResp> getIndexTopImg(){
		List<AppAdvertisementOutDto> advertisement = advertisementService.getAdvertisement(SystemConstant.AdInfoImage.TYPE_ADVERTISEMENT,SystemConstant.AdInfoImage.POSITION_ID_INDEX_TOP,null);
		return AppResp.get(advertisement);
	}
	/**
	 * APP买即赠活动返回
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月18日下午3:47:37
	 */
	@RequestMapping("/bugAndPresent")
	@Exclusion
	public ResponseEntity<AppResp>  getBuyAndPresent(){
		ReqData reqData=new ReqData();
		reqData.putValue("end_time", new Date(), SystemConstant.REQ_PARAMETER_LE);
		List<BuyAndPresent> findBuyAndPresentS = buyAndPresentService.findBuyAndPresentS(reqData);
		List<AppBuyAndPresentOutDto> buyAndPresents=new ArrayList<AppBuyAndPresentOutDto>();
		for (BuyAndPresent buyAndPresent : findBuyAndPresentS) {
			AppBuyAndPresentOutDto appBuyAndPresentOutDto=new AppBuyAndPresentOutDto();
			//主商品
			AppGoodsCondense mainGoods=new AppGoodsCondense();
			//赠品
			AppGoodsCondense giftGoods=new AppGoodsCondense();
			
			//通过主商品id获取到主商品
			String mainGoodsIds = buyAndPresent.getMainGoodsIds();
			List<Object> exchangeSplit = StringUtils.exchangeSplit(mainGoodsIds);
			List<Goods> findsGoodsWithIds = cmsGoodsService.findsGoodsWithIds(exchangeSplit);
			//防止添加多个商品，只取一个
			if(findsGoodsWithIds.size()>0){
				Goods goods = findsGoodsWithIds.get(0);
				BeanUtils.copyProperties(goods, mainGoods);
			}
			appBuyAndPresentOutDto.setMainGoods(mainGoods);
			
			appBuyAndPresentOutDto.setActivityType(SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY);
			
			//通过赠品id获取到赠品
			String giftGoodsIds = buyAndPresent.getGiftGoodsIds();
			List<Object> giftGoodsIdsList = StringUtils.exchangeSplit(giftGoodsIds);
			List<Goods> giftGoodsList = cmsGoodsService.findsGoodsWithIds(giftGoodsIdsList);
			//防止添加多个商品，只取一个
			if(giftGoodsList.size()>0){
				Goods goods = giftGoodsList.get(0);
				BeanUtils.copyProperties(goods, giftGoods);
			}
			
			//返回下买即赠的开始时间，结束时间
			appBuyAndPresentOutDto.setId(buyAndPresent.getId());
			appBuyAndPresentOutDto.setStartTime(buyAndPresent.getStartTime());
			appBuyAndPresentOutDto.setEndTime(buyAndPresent.getEndTime());
			
			appBuyAndPresentOutDto.setGiftGoods(giftGoods);
			appBuyAndPresentOutDto.setActivityType(SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY);  //用于区分那张表
			buyAndPresents.add(appBuyAndPresentOutDto);	
		}
		return AppResp.get(buyAndPresents);
	}
	/**
	 * 企业定制中间的图片广告
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月12日上午9:43:05
	 */
	@RequestMapping(value="/enterpriseCustomizationImg",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> enterpriseCustomizationImg(){
		Map<String, String> data=new HashMap<String, String>();
		String img="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/goods/watermark/20170912/card-banner1.jpg";
		data.put("midImg", img);
		return AppResp.get(data);
	}
	/**
	 * 会员中心（赚积分、会员日、神秘好礼、超值抢购、专题狂欢）详细页面
	 * @Description 
	 * @param termData
	 * @return
	 * @Author liuw
	 * @Date 2017年9月18日上午11:07:00
	 */
	@RequestMapping(value="/memberRight",method=RequestMethod.POST)
	public ResponseEntity<AppResp> memberRight(@RequestBody TermData termData){
		TermData memberRight = pointsService.memberRight(termData);
		Map<String, Object> data=new HashMap<>();
		data.put("name", memberRight.getName());
		data.put("desc", memberRight.getDes());
		return AppResp.get(data);
	}
	/**
	 * 获取猜猜看问题
	 * @return ResponseEntity<AppResp>
	 * @author lij
	 * @date 2018年1月30日 下午4:31:20
	 */
	@RequestMapping(value="/getQuestion",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getQuestion(){
		ActivityQuestion findOnlineActvity = activityService.findOnlineActvity();
		return AppResp.get(findOnlineActvity);
	}
	/**
	 * 0：答案正确送券成功 1：答案错误送券失败
	 * @param inDto
	 * @return ResponseEntity<AppResp>
	 * @author lij
	 * @date 2018年1月30日 下午5:00:55
	 */
	@RequestMapping(value="/submitQuestion",method=RequestMethod.POST)
	public ResponseEntity<AppResp> submitQuestion(@RequestBody AppQuestionInDto inDto){
		Long userId = AppLocalInfo.getUserId();
		int submitAnswer = activityService.submitAnswer(inDto.getUserAnswer(), userId, inDto.getQuestionId());
		return AppResp.get(submitAnswer);
	}
	
}
  