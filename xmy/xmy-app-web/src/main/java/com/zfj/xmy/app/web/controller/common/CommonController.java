package com.zfj.xmy.app.web.controller.common;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.common.pojo.dto.ContainerOutDto;
import com.zfj.xmy.activity.service.cms.ContainerService;
import com.zfj.xmy.activity.service.cms.IndexSettingService;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.comment.service.app.FeedbackService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.common.persistence.pojo.Feedback;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.common.persistence.pojo.app.AppGoodsCondense;
import com.zfj.xmy.common.persistence.pojo.app.HtmlInDto;
import com.zfj.xmy.common.persistence.pojo.app.HtmlOut;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.goods.service.cms.TermDataService;
import com.zfj.xmy.goods.service.cms.VocabularyService;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.redis.RedisTokenManager;
import com.zfj.xmy.redis.TokenManager;
import com.zfj.xmy.order.service.cms.AdvertisementService;
import com.zfj.xmy.order.service.common.PayService;
import com.zfj.xmy.user.service.common.CommonService;
import com.zfj.xmy.util.StringUtils;

@RestController
@RequestMapping(value="/common")
public class CommonController extends BaseController{
	@Autowired
	private TermDataService termDataService;
	@Autowired
	private VocabularyService vocabularyService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private PayService payService;
	@Autowired
	private IndexSettingService indexSettingService;
	@Autowired
	private ContainerService containerService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private CmsGoodsService cmsGoodsService;
	@Autowired
	private RedisTokenManager redisTokenManager;
	
	/**
	 * 
	 * @Description 获得APP关于香满园H5页面
	 * @return
	 * @Author liuw
	 * @Date 2017年7月28日下午4:28:44
	 */
	@RequestMapping(value="/XmyH5Content",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> getXmyH5Content(){
		
		//1 先从词汇表中查找出关于香满园的vid

		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.APP_ABOUTXMY, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		//2 根据vid查出APP关于香满园的内容
		TermData appAboutXmyH5 = commonService.getAppAboutXmyH5ByVid( vocabulary.getId());
		return AppResp.get(appAboutXmyH5);
	}
	
	@RequestMapping(value="/addFeedback",method=RequestMethod.POST)
	public ResponseEntity<AppResp> addFeedback(@RequestBody Feedback feedback){
		Long userId = AppLocalInfo.getUserId();
		feedback.setUserId(userId);
		//设置反馈来源为app
		feedback.setType(SystemConstant.FEEDBACK.TYPE_APP);
		//设置创建时间
		feedback.setCreateTime(new Date());
		//默认新增反馈的时候设置为未解决
		feedback.setStatus(SystemConstant.FEEDBACK.STATUS_NO_SOLVE);
		feedbackService.insertFeedback(feedback);
		return AppResp.get(null);
	}
	
	/**
	 * 获取Html地址
	 * @param content
	 * @return
	 * @Description 
	 * @date 2017年8月3日  下午4:43:14
	 * @author wy
	 * 2017
	 * @return ResponseEntity<HtmlOut>
	 */
	@RequestMapping(value="/getweb",method=RequestMethod.POST)
	public ResponseEntity<AppResp> modelDispat(@RequestBody HtmlInDto htmlIn){
		HtmlOut htmlOut = commonService.modelDispat(htmlIn);
		return AppResp.get(htmlOut);
	}
	
	/**
	 * 用户协议页面
	 * @param reqData
	 * @param request
	 * @return
	 * @Description 
	 * @date 2017年8月3日  下午5:15:23
	 * @author wy
	 * 2017
	 * @return ModelAndView
	 */
	@RequestMapping("/protocol")
	@Exclusion
	public ModelAndView protocol(ReqData reqData, HttpServletRequest request) {
		///request.setAttribute("countPage", countPage);
		return new ModelAndView("/protocol/index");

	}
	
	
	/**
	 * 微信 支付 回调  (app 和 jsapi)
	 * @param request
	 * @param response
	 * @return
	 * @Description 
	 * @date 2017年8月11日  下午4:13:59
	 * @author wy
	 * 2017
	 * @return ModelAndView
	 */
	@RequestMapping("/paynotify/wechat")
	@Exclusion
    public ModelAndView wechatPayNotify(HttpServletRequest request,HttpServletResponse response){
		payService.payNotify(request, response, PayBaseImpl.TRADETYPE_WX_APP);
		return null;
	}
	
	/**
	 * 支付宝  支付 回调
	 * @param request
	 * @param response
	 * @return
	 * @Description 
	 * @date 2017年8月11日  下午4:16:10
	 * @author wy
	 * 2017
	 * @return ModelAndView
	 */
	@RequestMapping("/paynotify/alipay")
	@Exclusion
    public ModelAndView alipayNotify(HttpServletRequest request,HttpServletResponse response){
		payService.payNotify(request, response, PayBaseImpl.TRADETYPE_ALIPAY_APP);
		return null;
	}

	/**
	 * 银联 APP 支付回调
	 * @param request
	 * @param response
	 * @return
	 * @Description 
	 * @date 2017年8月17日  下午4:34:03
	 * @author wy
	 * 2017
	 * @return ModelAndView
	 */
	@RequestMapping("/paynotify/unionpay")
	@Exclusion
    public ModelAndView unionpayNotify(HttpServletRequest request,HttpServletResponse response){
		payService.payNotify(request, response, PayBaseImpl.TRADETYPE_UNIONPAY_APP);
		return null;
	}
	
	@RequestMapping("/index")
	@Exclusion
	public ResponseEntity<AppResp> getIndex(){
		//1 .优先从缓存中取redis缓存
		String indexData = redisTokenManager.get(SystemConstant.REDIS_B2C_APP_INDEX_DATA);
		if (!StringUtil.isEmpty(indexData)) {
			return AppResp.get(new com.xiaoleilu.hutool.json.JSONObject(indexData));
		}
		return refeshIndex();
	}
	
	/**
	 * 更新redis首页配置App
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日下午8:19:53
	 */
	@RequestMapping("/refreshIndex")
	@Exclusion
	 public ResponseEntity<AppResp> refeshIndex(){
		// .优先从缓存中取redis缓存
		
		//1.业务
		Map<String, Object> data=new HashMap<String, Object>();
		IndexConfig indexConfig = indexSettingService.getIndexConfig();
		//获取top轮播图
		Long topAdId = indexConfig.getTopAdId();
		List<AdImage> adInfo = advertisementService.findAdImage(String.valueOf(topAdId));
		data.put("topAdInfo", adInfo);
		//1号广告位
		Long firstAdId = indexConfig.getFirstAdId();
		List<AdImage> firstAdInfo = advertisementService.findAdImage(String.valueOf(firstAdId));
		data.put("firstAdInfo", firstAdInfo);
		//获取货柜
		String containerIds = indexConfig.getContainerIds();
		List<Container> containers = containerService.findsContainerWithContainerIdString(containerIds);
		
		List<ContainerOutDto> containerOuts=new ArrayList<ContainerOutDto>();
		for (Container container : containers) {
			ContainerOutDto containerOutDto=new ContainerOutDto();
			BeanUtils.copyProperties(container, containerOutDto);
			//货柜的广告
			Long adId = container.getAdId();
			List<AdImage> adImage = advertisementService.findAdImage(String.valueOf(adId));
			containerOutDto.setContainerAd(adImage);
			//货柜的商品
			String goodsIdsString = container.getGoodsIds();
			List<Object> goodsIds = StringUtils.exchangeSplit(goodsIdsString);
			List<Goods> goods = cmsGoodsService.findsGoodsWithIds(goodsIds);
			List<AppGoodsCondense> goodsCondenses=new ArrayList<>();
			for (Goods goods2 : goods) {
				AppGoodsCondense appGoodsCondense=new AppGoodsCondense();
				BeanUtils.copyProperties(goods2, appGoodsCondense);
				//修改商品图片尺寸;				
				appGoodsCondense.setImgPath(appGoodsCondense.getImgPath()+"?x-oss-process=image/resize,m_fixed,h_200,w_200");
				goodsCondenses.add(appGoodsCondense);
			}
			containerOutDto.setGoods(goodsCondenses);
			containerOuts.add(containerOutDto);
		}
		
		data.put("containers", containerOuts);
		
		//2.保存更新 redis
		JSONObject indexJson = new JSONObject(data);
		redisTokenManager.setKey(SystemConstant.REDIS_B2C_APP_INDEX_DATA, indexJson.toString());
		
		System.out.println();
		return AppResp.get(data);
	 }
	
	/**
	 * app精选水果配置
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月30日下午2:40:38
	 */
	@RequestMapping("/choiceFruit")
	@Exclusion
	 public ResponseEntity<AppResp> getChoiceFruitSetting(){
		Map<String, Object> data=new HashMap<String, Object>();
		IndexConfig indexConfig = indexSettingService.getChoiceFruitSetting();
		//获取top轮播图
		Long topAdId = indexConfig.getTopAdId();
		List<AdImage> adInfo = advertisementService.findAdImage(String.valueOf(topAdId));
		data.put("topAdInfo", adInfo);
		//1号广告位
		Long firstAdId = indexConfig.getFirstAdId();
		List<AdImage> firstAdInfo = advertisementService.findAdImage(String.valueOf(firstAdId));
		data.put("firstAdInfo", firstAdInfo);
		//获取货柜
		String containerIds = indexConfig.getContainerIds();
		List<Container> containers = containerService.findsContainerWithContainerIdString(containerIds);
		
		List<ContainerOutDto> containerOuts=new ArrayList<ContainerOutDto>();
		for (Container container : containers) {
			ContainerOutDto containerOutDto=new ContainerOutDto();
			BeanUtils.copyProperties(container, containerOutDto);
			//货柜的广告
			Long adId = container.getAdId();
			List<AdImage> adImage = advertisementService.findAdImage(String.valueOf(adId));
			containerOutDto.setContainerAd(adImage);
			//货柜的商品
			String goodsIdsString = container.getGoodsIds();
			List<Object> goodsIds = StringUtils.exchangeSplit(goodsIdsString);
			List<Goods> goods = cmsGoodsService.findsGoodsWithIds(goodsIds);
			List<AppGoodsCondense> goodsCondenses=new ArrayList<>();
			for (Goods goods2 : goods) {
				AppGoodsCondense appGoodsCondense=new AppGoodsCondense();
				BeanUtils.copyProperties(goods2, appGoodsCondense);
				goodsCondenses.add(appGoodsCondense);
			}
			containerOutDto.setGoods(goodsCondenses);
			containerOuts.add(containerOutDto);
		}
		
		data.put("containers", containerOuts);
		return AppResp.get(data);
	 }
	/**
	 * APP跨境专区
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月31日下午1:59:23
	 */
	@RequestMapping("/crossBorder")
	@Exclusion
	 public ResponseEntity<AppResp> getCrossBorderSetting(){
		Map<String, Object> data=new HashMap<String, Object>();
		IndexConfig indexConfig = indexSettingService.getCrossBorderSetting();
		//获取top轮播图
		Long topAdId = indexConfig.getTopAdId();
		List<AdImage> adInfo = advertisementService.findAdImage(String.valueOf(topAdId));
		data.put("topAdInfo", adInfo);
		//1号广告位
		Long firstAdId = indexConfig.getFirstAdId();
		List<AdImage> firstAdInfo = advertisementService.findAdImage(String.valueOf(firstAdId));
		data.put("firstAdInfo", firstAdInfo);
		//获取货柜
		String containerIds = indexConfig.getContainerIds();
		List<Container> containers = containerService.findsContainerWithContainerIdString(containerIds);
		
		List<ContainerOutDto> containerOuts=new ArrayList<ContainerOutDto>();
		for (Container container : containers) {
			ContainerOutDto containerOutDto=new ContainerOutDto();
			BeanUtils.copyProperties(container, containerOutDto);
			//货柜的广告
			Long adId = container.getAdId();
			List<AdImage> adImage = advertisementService.findAdImage(String.valueOf(adId));
			containerOutDto.setContainerAd(adImage);
			//货柜的商品
			String goodsIdsString = container.getGoodsIds();
			List<Object> goodsIds = StringUtils.exchangeSplit(goodsIdsString);
			List<Goods> goods = cmsGoodsService.findsGoodsWithIds(goodsIds);
			List<AppGoodsCondense> goodsCondenses=new ArrayList<>();
			for (Goods goods2 : goods) {
				AppGoodsCondense appGoodsCondense=new AppGoodsCondense();
				BeanUtils.copyProperties(goods2, appGoodsCondense);
				goodsCondenses.add(appGoodsCondense);
			}
			containerOutDto.setGoods(goodsCondenses);
			containerOuts.add(containerOutDto);
		}
		
		data.put("containers", containerOuts);
		return AppResp.get(data);
	 }
	/**
	 * APP端获取客服号码
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月4日下午2:18:18
	 */
	@RequestMapping("/customerServicePhonenum")
	@Exclusion
	 public  ResponseEntity<AppResp> getCustomerServicePhonenum(){
		Map<String, Object> data=new HashMap<>();
		 //夜间客服号码
		 String phoneNumNight="17723156790";
		 //白天客服号码
		 String phoneNumDay="4008223936";
		 
		 Calendar calendar=Calendar.getInstance();
		 
		 Date currentTime = calendar.getTime();  //当前时间
		 calendar.set(Calendar.HOUR_OF_DAY, 9);//设置上午9点
		 calendar.set(Calendar.MINUTE, 0);//0分
		 calendar.set(Calendar.SECOND, 0);//0秒
		 Date timeAm = calendar.getTime();
		 
		 calendar.set(Calendar.HOUR_OF_DAY, 18);//下午18点
		 calendar.set(Calendar.MINUTE, 0);//0分
		 calendar.set(Calendar.SECOND, 0);//0秒
		 Date timePm = calendar.getTime();
		 String phoneNum=null;
		 //判断是否在早上9点到下午18点之前，在就是白天客服号码
		if( currentTime.after(timeAm)&&currentTime.before(timePm)){
			phoneNum=phoneNumDay;
		}else{
			phoneNum=phoneNumNight;
		}
		data.put("phoneNum", phoneNum);
		return AppResp.get(data);
	 }
	/**
	 * APP绑定购物卡页面图片
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月5日上午10:05:26
	 */
	@RequestMapping("/shoppingCardBindingImg")
	@Exclusion
	 public  ResponseEntity<AppResp> getShoppingCardBindingImg(){
		Map<String, Object> data=new HashMap<>();
		//图片地址
		String imgPath="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/goods/watermark/20170905/shoppingCardBindingImg.png";
		data.put("imgPath", imgPath);
		return AppResp.get(data);
	 }
}
