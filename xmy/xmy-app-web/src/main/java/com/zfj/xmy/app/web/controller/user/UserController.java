package com.zfj.xmy.app.web.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.enu.BaseProp;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.app.pojo.dto.CouponSearchInDto;
import com.zfj.xmy.activity.service.cms.CouponService;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.common.ValidateCode;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.BrowsedGoods;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.EmailLog;
import com.zfj.xmy.common.persistence.pojo.EntityCoupon;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.persistence.pojo.app.AppShoppingCardOutDto;
import com.zfj.xmy.common.service.CommonPushUtilService;
import com.zfj.xmy.common.service.CommonShopingCardService;
import com.zfj.xmy.common.service.EmailLogService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppActivityInfoDir;
import com.zfj.xmy.goods.service.app.AppGoodsService;
import com.zfj.xmy.goods.service.app.TermDataExService;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.openim.OpenImManager;
import com.zfj.xmy.order.persistence.app.pojo.dto.BrowsedGoodsIdsInDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.BrowsedGoodsOutDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.GuessLikeGoodsOutVO;
import com.zfj.xmy.order.service.app.BrowsedGoodsService;
import com.zfj.xmy.order.service.app.CollectionGoodsService;
import com.zfj.xmy.order.service.cms.UserAddreesService;
import com.zfj.xmy.order.service.cms.UserSpendPointsService;
import com.zfj.xmy.redis.TokenManager;
import com.zfj.xmy.user.persistence.pojo.dto.UserInfoDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.AppUserInDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.AppUserScancodeDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.MobilePhoneDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.MsgInDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.SendEmailValueDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.UserLoginInDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.UserRegisterInDto;
import com.zfj.xmy.user.service.common.OpenImService;
import com.zfj.xmy.user.service.common.ShoppingCardRecordService;
import com.zfj.xmy.user.service.common.UserInfoService;
import com.zfj.xmy.user.service.common.UserService;
import com.zfj.xmy.user.service.pc.PcUserService;
import com.zfj.xmy.user.service.pc.UserCenterService;
import com.zfj.xmy.util.EmailUtil;

@RequestMapping(value="/user")
@RestController
public class UserController extends BaseController{

	
	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserAddreesService userAddreesService;

	@Autowired
	private CollectionGoodsService collectionGoodsService;
	@Autowired
	private TermDataExService termDataExService;
	@Autowired
	private BrowsedGoodsService browsedGoodsService;

	@Autowired
	private TokenManager tokenManager ;

	@Autowired
	private CmsGoodsService goodsService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserSpendPointsService userSpendPointsService;
	@Autowired
	private ShoppingCardRecordService serviceShoppingCardRecordService;
	@Autowired
	private CommonShopingCardService shoppingCardService;
	
	
	@Autowired
	private OpenImManager openImManager;
	
	@Autowired
	private OpenImService openImService;
	@Autowired
	private AppGoodsService appGoodsService;
	@Autowired
	private EmailLogService emailLogService;
	
	@Autowired
	private OnLineActivityService onLineActivityService;
	
	@Autowired
	private PcUserService pcUserService;
	
	@Autowired
	private CommonPushUtilService commonPushUtilService;
	
	/**
	 * 用户注册
	 * @param request
	 * @param jo
	 * @return
	 * @Description 
	 * @date 2017年7月21日  上午10:49:27
	 * @author wy
	 * 2017
	 * @return RespData
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> register(HttpServletRequest request,@RequestBody JSONObject jo){
		UserRegisterInDto registerInDto = JSONObject.toJavaObject(jo, UserRegisterInDto.class);
		userService.register(registerInDto);
		return AppResp.get();
	}
	
	/**
	 * 图形验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException    
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年11月23日 下午2:15:12 
	 * @author hexw
	 */
	@RequestMapping(value="/imageCode")
	@Exclusion
	public ModelAndView imageCode(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="deviceid",required=true) String  deviceid) throws IOException{
		
		response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        ValidateCode vCode = new ValidateCode(100,25,4,10); 
        if (StringUtil.isEmpty(deviceid)) {
        	throw new BusinessException("手机识别号deviceid不能为空");
        }
        tokenManager.setKey(SystemConstant.IMAGECODE_DEVID+deviceid, vCode.getCode());
        System.out.println("获取图形验证码deviceid："+deviceid+"图形验证码："+vCode.getCode());
        vCode.write(response.getOutputStream());  
		return null;
	}
	
	/**
	 * 登录
	 * @param request
	 * @param jo
	 * @return
	 * @Description 
	 * @date 2017年7月21日  下午2:35:34
	 * @author wy
	 * 2017
	 * @return RespData
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> login(HttpServletRequest request,@RequestBody JSONObject jo){
		UserLoginInDto loginInDto = JSONObject.toJavaObject(jo, UserLoginInDto.class);
		return AppResp.get(userService.login(loginInDto));
	}
	
	/**
	 * 开业活动赠送 8.8 折优惠券
	 * @param request
	 * @return    
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年11月22日 下午5:03:29 
	 * @author hexw
	 */
	@RequestMapping(value="/presentCoupon",method=RequestMethod.POST)
	public ResponseEntity<AppResp> presentCoupon(HttpServletRequest request){
		Long userid = AppLocalInfo.getUserId();
		Coupon coupon = onLineActivityService.appPresentCoupon(userid);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("name", coupon.getName());
		return AppResp.get(result);
	}
	
	/**
	 * 判断赠送优惠券活动是否在活动时间内
	 * @return  0：不在活动范围时间内 1.在活动范围时间内
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年11月25日 上午10:08:23 
	 * @author hexw
	 */
	@RequestMapping(value="/isOnlineActivity",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> isOnlineActivity(){
		Map<String,Object> result = onLineActivityService.checkPresenCoupontDate();
		Integer status = (Integer)result.get("status");
		return AppResp.get(status);
	}
	
	/**
	 * 返回赠送优惠券图片接口
	 * @return    
	 * @return ResponseEntity<AppResp>    
	 * Date:2018年1月18日 下午3:03:02 
	 * @author hexw
	 */
	@RequestMapping(value="/getPresentCouponActivityImg",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> getPresentCouponImg(){
		return AppResp.get(onLineActivityService.checkPresenCoupontDate());
	}
	
	/**
	 * 找回密码
	 * @param request
	 * @param jo
	 * @return
	 * @Description 
	 * @date 2017年8月2日  下午8:59:48
	 * @author wy
	 * 2017
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/retrieve",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> retrieve(HttpServletRequest request,@RequestBody JSONObject jo){
		UserRegisterInDto retrieveInDto = JSONObject.toJavaObject(jo, UserRegisterInDto.class);
		userService.retrievePass(retrieveInDto);
		return AppResp.get();
	}
	
	/**
	 * 校验手机号是否已被绑定
	 * @Description 
	 * @param request
	 * @param phone
	 * @return
	 * @Author hexw
	 * @Date 2017年11月21日下午3:55:29
	 */
	@RequestMapping(value="/verifyBindPhone",method=RequestMethod.POST)
	public ResponseEntity<AppResp> verifyBindPhone(@RequestBody UserInfoDto dto ){
		userService.verifyPhone(dto.getPhone());
		return AppResp.get();
	}
	
	/**
	 * 校验旧密码是否输入正确
	 * @param oldPassword
	 * @return    
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年11月25日 下午2:05:10 
	 * @author hexw
	 */
	@RequestMapping(value="/verifyOldPassword",method=RequestMethod.POST)
	public ResponseEntity<AppResp> verifyOldPassword(HttpServletRequest request,@RequestBody AppUserInDto  dto){
		Long userid = AppLocalInfo.getUserId();
		userService.verifyOldPassword(userid, dto.getOldPassword());
		return AppResp.get();
	}
	
	/**
	 * 换绑手机号
	 * @param request
	 * @param jo
	 * @return
	 * @Description 
	 * @date 2017年9月5日  下午5:53:35
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/bindPhone",method=RequestMethod.POST)
	public ResponseEntity<AppResp> bindPhone(HttpServletRequest request,@RequestBody UserRegisterInDto bindPhoneDto){
		Long userid = AppLocalInfo.getUserId();
		userService.bindNewPhone(bindPhoneDto, userid);
		return AppResp.get();
	}
	
	
	/**
	 * 修改密码
	 * @Description 
	 * @param request
	 * @param jo
	 * @return
	 * @Author liuw
	 * @Date 2017年9月5日下午5:37:58
	 */
	@RequestMapping(value="/modifyPassword",method=RequestMethod.POST)
	public ResponseEntity<AppResp> modifyPassword(HttpServletRequest request,@RequestBody AppUserInDto  user){
		Long userId = AppLocalInfo.getUserId();
		user.setUserId(userId);
		userService.modifyPassword(user);
		return AppResp.get();
	}
	/**
	 * 发送短信验证码
	 * @param request
	 * @param phoneDto
	 * @return
	 * @Description 
	 * @date 2017年8月7日  下午4:52:54
	 * @author wy
	 * 2017
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/sendsms",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> sendsms(HttpServletRequest request,@RequestBody MobilePhoneDto phoneDto){
		String devId = request.getHeader("deviceid"); //手机识别号
		if (!StringUtil.isEmpty(devId) && !StringUtil.isEmpty(phoneDto.getImagCode())) {
			String imageCodeResdisKey = tokenManager.get(SystemConstant.IMAGECODE_DEVID+devId);
			System.out.println("发送短信验证码deviceid："+devId+"参数imagecode:"+phoneDto.getImagCode()+"redis验证码："+imageCodeResdisKey);
			if (StringUtil.isEmpty(imageCodeResdisKey)) {
				throw new BusinessException("未查询到redis的图形验证码");
			}
			if (!imageCodeResdisKey.equals(phoneDto.getImagCode())) {
				throw new BusinessException("图形验证码错误");
			}
		}
		userService.sendsms(phoneDto);
		return AppResp.get();
	}
	
	/**
	 * 发送邮件 (绑定邮箱)
	 * @param request
	 * @param phoneDto
	 * @return
	 * @Description 
	 * @date 2017年9月7日  下午4:11:24
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/sendEmailBybind",method=RequestMethod.POST)
	public ResponseEntity<AppResp> sendBindEmail(HttpServletRequest request,@RequestBody SendEmailValueDto emailValue){
		userService.sendBindinEmail(emailValue.getEmail());
		return AppResp.get();
	}
	
	/**
	 * 用户消息列表
	 * @param request
	 * @return
	 * @Description 
	 * @date 2017年7月31日  下午4:11:59
	 * @author wy
	 * 2017
	 * @return ResponseEntity<List<MsgPushInfo>>
	 */
	@RequestMapping(value="/msglist",method=RequestMethod.POST)
	public ResponseEntity<AppResp> msglist(HttpServletRequest request){
		
		Long userid = AppLocalInfo.getUserId();
		return AppResp.get(userService.findMsgInfoByUser(userid));
	}
	
	/**
	 * 设置消息为已读
	 * @param request
	 * @return
	 * @Description 
	 * @date 2017年7月31日  下午4:12:33
	 * @author wy
	 * 2017
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/makeMsgRead",method=RequestMethod.POST)
	public ResponseEntity<AppResp> makeMsgRead(@RequestBody MsgInDto msgInDto){
		Long userid = AppLocalInfo.getUserId();
		userService.makeMsgRead(userid, msgInDto.getMsgid());
		return AppResp.get();
	}
	
	/**
	 * 获取 云旺 登录帐号信息
	 * @param request
	 * @param jo
	 * @return
	 * @Description 
	 * @date 2017年8月29日  下午5:06:32
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/getim",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getopenim(HttpServletRequest request){
		Long userId=AppLocalInfo.getUserId();
		com.xiaoleilu.hutool.json.JSONObject jo = new com.xiaoleilu.hutool.json.JSONObject();
		jo.put("user", openImService.getOpenImUsers(userId));
		jo.put("appkey", openImService.getOpenImAppkey());
		jo.put("customid", openImService.getOpenImCustomid());
		return AppResp.get(jo);
	}
	
	/**
	 * 
	 * @Description 获取用户信息
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年7月26日下午8:57:41
	 */
	@RequestMapping(value="/userInfo",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getUserInfo(){
	    //获取userId
		Long userId=AppLocalInfo.getUserId();
		ReqData reqData = new ReqData() ;
		reqData.putValue(SystemConstant.USERINFO.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		return AppResp.get(userInfoService.getUserInfoDto(userId)) ;
	}
	/**
	 * 
	 * @Description 获取用户地址信息列表
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年7月27日下午1:54:10
	 */
	@RequestMapping(value="/userAddressList",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getUserAddressList(){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		ReqData reqData = new ReqData() ;
		reqData.putValue(SystemConstant.USERINFO.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		List<UserAddrees> userAddreesList = userAddreesService.finAllUserAddrees(reqData);
		return AppResp.get(userAddreesList);
	}
	/**
	 * 
	 * @Description 添加用户地址
	 * @param userAddrees
	 * @return
	 * @Author liuw
	 * @Date 2017年7月27日下午1:54:28
	 */
	@RequestMapping(value="/addUserAddress",method=RequestMethod.POST)
	public ResponseEntity<AppResp> addUserAddress(@RequestBody UserAddrees userAddrees){
		
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		userAddrees.setUserId(userId);
		userAddrees.setIsDefault(SystemConstant.USERADDRESS.IS_NOT_DEFAULT_AADDRESS);
		userAddrees.setType(0);
		userAddreesService.insertUserAddress(userAddrees);
		return AppResp.get(null);
	}
	/**
	 * 
	 * @Description 根据收货地址id，查询单条收货地址
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月27日下午2:57:15
	 */
	@RequestMapping(value="/userAddress/{id}",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getUserAddress(@PathVariable("id") Long id){
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.USERADDRESS.userAddressId, id, SystemConstant.REQ_PARAMETER_EQ);
		return AppResp.get(userAddreesService.getUserAddrees(reqData));
		
	}
	/**
	 * 
	 * @Description 
	 * @param userAddrees 更新用户地址信息
	 * @return
	 * @Author liuw
	 * @Date 2017年7月27日下午3:24:34
	 */
	@RequestMapping(value="/updateUserAddress",method=RequestMethod.POST)
	public ResponseEntity<AppResp> updateUserAddress(@RequestBody UserAddrees userAddrees){
		userAddreesService.updateUserAddress(userAddrees);
		return AppResp.get(null);
	}
	/**
	 * 
	 * @Description 根据收货地址id删除单条收货地址
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月27日下午4:17:56
	 */
	@RequestMapping(value="/delUserAddress",method=RequestMethod.POST)
	public ResponseEntity<AppResp> delUserAddress(@RequestBody UserAddrees userAddrees){
		Long userId = AppLocalInfo.getUserId();
		userAddrees.setUserId(userId);
		userAddreesService.delUserAddress(userAddrees);
		return AppResp.get(null);
	}
	/**
	 * 
	 * @Description 按照有值的更新用户信息
	 * @param userInfoDto
	 * @return
	 * @Author liuw
	 * @Date 2017年7月27日下午4:39:05
	 */
	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	public ResponseEntity<AppResp> updateUserInfo(@RequestBody UserInfoDto userInfoDto){
		Long userId = AppLocalInfo.getUserId();
		userInfoDto.setId(userId);
		if(ObjectUtil.isNotNull(userInfoDto.getAvatar())){
			userInfoDto.setAvatar(userInfoDto.getAvatar().substring(userInfoDto.getAvatar().lastIndexOf("/")+1));
		}
		userInfoService.updateUserInfo(userInfoDto);
		return AppResp.get();
	}
	/**
	 * 
	 * @Description 设置默认收货地址
	 * @param userAddrees
	 * @return
	 * @Author liuw
	 * @Date 2017年7月28日下午5:09:47
	 */
	@RequestMapping(value="/setDefaultUserAddress",method=RequestMethod.POST)
	public ResponseEntity<AppResp> setDefaultUserAddress(@RequestBody UserAddrees userAddrees){
		Long userId = AppLocalInfo.getUserId();
		userAddrees.setUserId(userId);
		userAddreesService.updateSetDefaultUserAddress(userAddrees);
		return AppResp.get(null);
	}
	/**
	 * 
	 * @Description 获取收藏夹分类数据（收藏夹中有哪些一级分类以及该分类的数量）
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年7月28日下午5:52:25
	 */
	@RequestMapping(value="/getCollectionCategorys",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getCollectionCategorys(){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		//1 查找所有商品
		List<GoodsWithBLOBs> findCollectionGoods = collectionGoodsService.findCollectionGoods(reqData);
		//2 查找商品对应的分类
		List<Map<String, Object>> findCollectionCategorys=new ArrayList<Map<String, Object>>();
		if(!ObjectUtils.isEmpty(findCollectionGoods)){
			findCollectionCategorys = termDataExService.findCollectionCategorys(findCollectionGoods);
		}
		 return AppResp.get(findCollectionCategorys);
	}
	/**
	 * 
	 * @Description 获取收藏夹商品数据（可带条件查询分类下的收藏夹商品）
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日上午9:26:27
	 */
	@RequestMapping(value="/getCollectionGoods",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getCollectionGoods(@RequestBody CollectionGoods reqCollectionGoods){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.COLLECTIONGOODS.GOODS_CATEGORY_NAME, reqCollectionGoods.getGoodsCategoryName(), SystemConstant.REQ_PARAMETER_CN);
		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		//1 查找所有商品
		List<GoodsWithBLOBs> findCollectionGoods = collectionGoodsService.findCollectionGoods(reqData);
		for (GoodsWithBLOBs goodsWithBLOBs : findCollectionGoods) {
			Long activityId = goodsWithBLOBs.getActivityId();
			Integer activityType = goodsWithBLOBs.getActivityType();
			Long id = goodsWithBLOBs.getId();
			//是否是活动商品
			if(!ObjectUtils.isEmpty(activityId)&&!ObjectUtils.isEmpty(activityType))
			{
				AppActivityInfoDir activityInfo = appGoodsService.getActivityInfo(activityId, id, activityType);
				goodsWithBLOBs.setActivityName(activityInfo.getActivityName());
				goodsWithBLOBs.setLimitTotalNum(activityInfo.getLimitTotalNum());
			}
		}
		 return AppResp.get(findCollectionGoods);
	}
	/**
	 * 
	 * @Description 删除收藏夹商品数据
	 * @param collectionGoods
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日上午11:40:59
	 */
	@RequestMapping(value="/delCollectionGoods",method=RequestMethod.POST)
	public ResponseEntity<AppResp> delCollectionGoods(@RequestBody CollectionGoods collectionGoods){
		
		Long userId = AppLocalInfo.getUserId();
		collectionGoodsService.delAndCheckCollectionGoods(userId, collectionGoods.getId());
		return AppResp.get();
	}
	/**
	 * 
	 * @Description 获取足迹表中的信息
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日下午3:25:28
	 */
	@RequestMapping(value="/getBrowsedGoods",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getBrowsedGoods(){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		ReqData reqData=new ReqData();
		reqData.setSortorder(" browsed_time desc limit 8 ");
		reqData.putValue(SystemConstant.BROWSEDGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		//1 先查找足迹表中的数据
		 List<BrowsedGoods> findBrowsedGoods = browsedGoodsService.findBrowsedGoods(reqData);
		//2 查找所有商品
		List<BrowsedGoodsOutDto> browsedGoodsToBrowsedGoodsDto = browsedGoodsService.browsedGoodsToBrowsedGoodsDto(findBrowsedGoods);
		return AppResp.get(browsedGoodsToBrowsedGoodsDto);
	}
	/**
	 * 
	 * @Description 批量删除足迹记录
	 * @param delIdsDto
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日下午7:16:48
	 */
	@RequestMapping(value="/delBrowsedGoods",method=RequestMethod.POST)
	public ResponseEntity<AppResp> delBrowsedGoods(@RequestBody BrowsedGoodsIdsInDto delIdsDto){
		browsedGoodsService.delBrowsedGoods(delIdsDto.getIds().toArray());
		return AppResp.get();
	}
	
	
	/***
	 * 用户个人优惠劵 统计信息
	 * @return
	 * @Description 
	 * @date 2017年11月16日  下午2:58:47
	 * @author 
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/getCouponsHead",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getCouponsHead(){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		return AppResp.get(couponService.getCouponsHead(userId));
		
		
		/*//未使用
		Map<String, Object> noUse=new HashMap<String, Object>();
		//使用记录
		Map<String, Object> useRecord=new HashMap<String, Object>();
		//已过期
		Map<String, Object> expired=new HashMap<String, Object>();
		List<Map<String, Object>> couponsHead=new ArrayList<Map<String,Object>>();
		
		//1  计算已过期的个数
		ReqData reqDataExpired=new ReqData();
		Integer exprideCount=0;
		reqDataExpired.putValue(SystemConstant.COUPON.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		reqDataExpired.putValue(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_NO_USE, SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> noUseCoupon = couponService.findCoupons(reqDataExpired);
		for (CouponUser couponUser : noUseCoupon) {
			Long couponId = couponUser.getCouponId();
			Coupon findCouponById = couponService.findCouponById(couponId);
			////说明已经过期
			int compareTo = findCouponById.getEffectiveEndTime().compareTo(new Date());
			if(compareTo<=SystemConstant.COUPON.DATA_HAD_EXPIRED){
				exprideCount++;
			}
		}
		expired.put(SystemConstant.COUPON.NAME,SystemConstant.COUPON.EXPIRED_STRING);
		expired.put(SystemConstant.COUPON.COUNT, exprideCount);
		
		//2 计算使用记录的个数
		ReqData reqDataUseRecord=new ReqData();
		reqDataUseRecord.putValue(SystemConstant.COUPON.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		reqDataUseRecord.putValue(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_USED, SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> findCouponsUsed = couponService.findCoupons(reqDataUseRecord);
		Integer usedCoupon=findCouponsUsed.size();
		useRecord.put(SystemConstant.COUPON.NAME,SystemConstant.COUPON.USE_RECORD_STRING);
		useRecord.put(SystemConstant.COUPON.COUNT, usedCoupon);
		
		//3 计算未使用的个数
		ReqData reqDataNoUse=new ReqData();
		reqDataNoUse.putValue(SystemConstant.COUPON.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		reqDataNoUse.putValue(SystemConstant.COUPON.STATUS, SystemConstant.COUPON.STATUS_NO_USE, SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> findCoupons = couponService.findCoupons(reqDataNoUse);
		Integer noUseCount=findCoupons.size();
		//未使用的=总的未使用的-过期的
		noUseCount=noUseCount-exprideCount;
		noUse.put(SystemConstant.COUPON.NAME,SystemConstant.COUPON.NO_USE_STRING);
		noUse.put(SystemConstant.COUPON.COUNT, noUseCount);
		
		couponsHead.add(noUse);
		couponsHead.add(useRecord);
		couponsHead.add(expired);
		return AppResp.get(couponsHead);*/
	}
	/**
	 *  查询优惠券列表
	 * @Description 
	 * @param couponSearchInDto
	 * @return
	 * @Author liuw
	 * @Date 2017年8月2日上午11:29:49
	 */
	
	
//	@Deprecated
//	@RequestMapping(value="/getCouponsGoods",method=RequestMethod.POST)
//	public ResponseEntity<AppResp> getCoupons(@RequestBody CouponSearchInDto couponSearchInDto){
//		//获取userId
//		Long userId=AppLocalInfo.getUserId();
//		
//		/*	// 1 先获取电子优惠券的
//		List<EntityCoupon> findCoupon = couponService.findCoupon(userId,couponSearchInDto);
//		List<EntityCoupon> coupons=new ArrayList<>();
//		for (Coupon coupon : findCoupon) {
//			EntityCoupon entityCoupon=new EntityCoupon();
//			BeanUtils.copyProperties(coupon, entityCoupon);
//			entityCoupon.setType(SystemConstant.COUPON.TYPE_ECOUPON_ELECTRONICS);
//			coupons.add(entityCoupon);
//		}
//		// 2 再获取实体纸质优惠券*/
//		//全部获取到了(电子优惠券+纸质优惠券)
//		return AppResp.get( couponService.findCoupon(userId,couponSearchInDto));
//		//return AppResp.get(couponService.findUserCoupon(userId,couponSearchInDto.getOrder()));
//	}
	
	
	/********
	 * 查询用户优惠券列表
	 * 
	 * @return
	 * @Description 
	 * @date 2017年10月24日  下午2:45:37
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/getAvailbleCoupons",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getAvailbleCoupons(@RequestBody CouponSearchInDto couponSearchInDto){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		
//		CouponSearchInDto couponSearchInDto = new CouponSearchInDto();
//		couponSearchInDto.setUseType(3); //未使用
//		couponSearchInDto.setOrder(2); //排序 最优惠
		// 1 再获取实体纸质优惠券*/
		//全部获取到了(电子优惠券+纸质优惠券)
		//List<EntityCoupon> findCoupon = couponService.findCoupon(userId,couponSearchInDto);
		
		return AppResp.get(couponService.findUserCoupon(userId,couponSearchInDto));
	}
	
	
	/**
	 * 
	 * @Description 猜你喜欢
	 * @return
	 * @Author liuw
	 * @Date 2017年8月2日下午1:47:37
	 */
	@RequestMapping(value="/getGuessLikeGoods",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getGuessLikeGoods(){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(8);
		List<Goods> list=goodsService.findYouLike(pageBean);
		//List<GuessLikeGoodsOutVO> findGuessLikeGoods = browsedGoodsService.findGuessLikeGoods(userId);
		return AppResp.get(list);
	}
	/**
	 * 账单流水APP
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月3日上午11:38:06
	 */
	@RequestMapping(value="/getUserBillOrPointFlow",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getUserBillFlow(@RequestBody UserSpendPoints userSpendPoint){
		//获取userId
		Long userId = AppLocalInfo.getUserId();
		ReqData reqData=new ReqData();
		reqData.setSortname("creat_time");
		reqData.setSortorder("DESC");
		reqData.putValue(SystemConstant.userSpendPoints.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		// 设置类型为账单（金额）或者积分
		reqData.putValue(SystemConstant.userSpendPoints.TYPE, userSpendPoint.getType(), SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> userSpendPoints = userSpendPointsService.findUserSpendPoints(reqData);
		return AppResp.get(userSpendPoints);
	}
	/**
	 * 余额明细（APP，其实就是购物卡的消费记录）
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月3日下午8:31:43
	 */
	@RequestMapping(value="/getUserBalanceFlow",method=RequestMethod.POST)
	public ResponseEntity<AppResp> getUserBalanceFlow(){
		//获取userId
		Long userId = AppLocalInfo.getUserId();
		CriteriaParameter criteriaParameter=new CriteriaParameter();
		Criteria createCriteria = criteriaParameter.createCriteria();
		createCriteria.equalTo(SystemConstant.SHOPPING_CARD.USER_ID, userId);
		criteriaParameter.setOrderByClause("use_time desc");
		List<ShoppingCardRecord> findUserShoppingCardRecord = serviceShoppingCardRecordService.findUserShoppingCardRecord(criteriaParameter);
		return AppResp.get(findUserShoppingCardRecord);
	}
	/**
	 * 优惠券实体券绑定
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月4日下午1:52:54
	 */
	@RequestMapping(value="/EntityCouponBinding",method=RequestMethod.POST)
	public ResponseEntity<AppResp> setEntityCouponBinding(@RequestBody EntityCoupon entityCoupon){
		//获取userId
		Long userId = AppLocalInfo.getUserId();
		CouponUser couponUser=new CouponUser();
		couponUser.setUserId(userId);
		//couponService.setEntityCouponBinding(entityCoupon, couponUser);
		couponService.bindingEntityCoupon(userId,entityCoupon.getCouponCode());
		return AppResp.get();
	}
	/**
	 * app购物卡绑定
	 * @Description 
	 * @param shoppingCard
	 * @return
	 * @Author liuw
	 * @Date 2017年8月21日下午5:25:52
	 */
	@RequestMapping(value="/shoppingCardBinding",method=RequestMethod.POST)
	public ResponseEntity<AppResp> setShoppingCardBinding(@RequestBody ShoppingCard shoppingCard){
		//获取userId
		Long userId=AppLocalInfo.getUserId();
		if(StringUtil.isEmpty(shoppingCard.getCardNum())||StringUtil.isEmpty(shoppingCard.getCardPassword())){
			throw new BusinessException("必须传购物卡号和密码!");
		}
		Integer result = shoppingCardService.addShopingCardWithUserId(shoppingCard.getCardNum(), shoppingCard.getCardPassword(), userId);
		if(result==SystemConstant.SHOPPING_CARD.RESULTERROR){
			throw new BusinessException("错误,卡号和密码不对应!");
		}else if(result==SystemConstant.SHOPPING_CARD.RESULTBOUND){
			throw new BusinessException("错误,该卡已经绑定!");
		}
		return AppResp.get();
		
	}
	/**
	 * 购物卡激活明细
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月25日上午10:12:32
	 */
	@RequestMapping(value="/activateDetail",method=RequestMethod.POST)
	public ResponseEntity<AppResp> activateDetail(){
		Long userId = AppLocalInfo.getUserId();
		List<AppShoppingCardOutDto> activateDetail = shoppingCardService.activateDetail(userId);
		return AppResp.get(activateDetail);
	}
	/**
	 * 商品推荐
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月7日下午3:22:31
	 */
	@Exclusion
	@RequestMapping(value="/goodsRecommend",method=RequestMethod.POST)
	public ResponseEntity<AppResp> goodsRecommend(@RequestBody(required=false) GuessLikeGoodsOutVO goods){
		/*Long id=null;
		if(!ObjectUtils.isEmpty(goods)&&!StringUtil.isEmpty(goods.getId())){
			id = goods.getId();
		}
		List<GuessLikeGoodsOutVO> goodsRecommend = browsedGoodsService.goodsRecommend(id);*/
		List<GuessLikeGoodsOutVO> goodsRecommend = new ArrayList<GuessLikeGoodsOutVO>();
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(10);
		List<Goods> list=goodsService.findYouLike(pageBean);
		for (Goods goods2 : list) {
			GuessLikeGoodsOutVO goodsOutVO = new GuessLikeGoodsOutVO();
			goodsOutVO.setId(goods2.getId());
			goodsOutVO.setImgPath(goods2.getImgPath());
			goodsOutVO.setName(goods2.getName());
			goodsOutVO.setPhoneprice(goods2.getPhonePrice());
			goodsOutVO.setPrice(goods2.getPrice());
			goodsOutVO.setVipPrice(goods2.getVipPrice());
			goodsRecommend.add(goodsOutVO);
		}
		return AppResp.get(goodsRecommend);
	}
	
	/***
	 * 查询用户默认头像接口
	 * @param request
	 * @return    
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年9月6日 下午3:57:56 
	 * @author hexw
	 * @throws IOException 
	 */
	@Exclusion
	@RequestMapping(value="/findHeadImage",method=RequestMethod.POST )
	public ResponseEntity<AppResp> findheadImage(HttpServletRequest request){
		List<String> headImages = new ArrayList<String>();
		String basePath = request.getSession().getServletContext().getRealPath("") ;
		basePath.replaceAll("\\\\\\\\","/").replaceAll("\\\\","/") ;
		String relative = "common/headimage/";
		File file = new File(basePath+File.separator+relative);
		File[] files = file.listFiles(); 
		for (File headImg : files) {
			String path = SystemConstant.USER.HTYP_XMY_APP+relative+headImg.getName();
			headImages.add(path);
		}
		return AppResp.get(headImages);
	}
	
	/**
	 * 上传图片
	 * @return    
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年9月6日 下午6:04:18 
	 * @author hexw
	 */
	@Exclusion
	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	public ResponseEntity<AppResp> uploadImage(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		Map<String,Object> map = FileUploadUtil.upload(file,request,true,"上传的文件类型不正确，只能上传图片！") ;
		return AppResp.get(map);
	}
	/**
	 * 会员中心中间广告图
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月12日上午9:52:31
	 */
	@RequestMapping(value="/memberCenterMidImg",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> memberCenterMidImg(){
		Map<String, String> data=new HashMap<String, String>();
		String img="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/goods/watermark/20170912/membercenter.jpg";
		data.put("midImg", img);
		return AppResp.get(data);
	}
	/**
	 * 发送邮箱绑定验证码
	 * @Description 
	 * @param emailLog
	 * @return
	 * @Author liuw
	 * @Date 2017年9月18日下午4:45:31
	 */
	@RequestMapping(value="/sendEmailBindCode",method=RequestMethod.POST)
	public ResponseEntity<AppResp> sendEmailBindCode(@RequestBody EmailLog emailLog){
		
		emailLogService.sendEmail(emailLog);
		
		return AppResp.get();
	}
	@RequestMapping(value="/emailBind",method=RequestMethod.POST)
	public ResponseEntity<AppResp> emailBind(@RequestBody EmailLog emailLog){
		
		UserInfo userInfo=new UserInfo();
		userInfo.setId(AppLocalInfo.getUserId());
		
		Integer useraccpoint = ObjectUtil.isNull(userInfo.getAccPoints())?0:userInfo.getAccPoints();
		
		userInfo.setAccPoints(useraccpoint+SystemConstant.USER.PRESENT_POINTS);
		userInfoService.updateEmail(userInfo, emailLog);
		return AppResp.get();
	}
	
	/**
	 * 扫码确认登录
	 * @param scancodeDto
	 * @return
	 * @Description 
	 * @date 2017年9月29日  上午9:50:11
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/bindscancode",method=RequestMethod.POST)
	public ResponseEntity<AppResp> scancodebind(@RequestBody AppUserScancodeDto scancodeDto){
		userService.updateScancode(AppLocalInfo.getUserId(), scancodeDto.getCode());
		return AppResp.get();
	}
	
	/**
	 * 获取第三方帐号登录配置
	 * @return
	 * @Description 
	 * @date 2017年12月28日  上午10:56:13
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/getLoginConfig",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> getLoginConfig(){
		com.xiaoleilu.hutool.json.JSONObject jsonObject = new com.xiaoleilu.hutool.json.JSONObject();
		jsonObject.put("wechat_login_appid", BaseProp.BASE.getValue("WECHAT_LOGIN_APPID"));
		jsonObject.put("qq_login_appid", BaseProp.BASE.getValue("QQ_LOGIN_APPID"));
		return AppResp.get(jsonObject);
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> getUser(@RequestBody User user){
		User user2 = userService.getUser(user.getName(),null,false);
		if(ObjectUtils.isEmpty(user2)){
			throw new BusinessException("用户名不存在，请核实！");
		}else{
			UserInfo userInfo = pcUserService.getUserInfo(user2.getId());
			return AppResp.get(userInfo);
		}
	}
	
	/**
	 * 推送的消息记录 - 分页查询 
	 * @param pb
	 * @return
	 * @Description 
	 * @date 2018年2月5日  下午2:51:35
	 * @author wy
	 * 2018
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/pushlog",method=RequestMethod.POST)
	public ResponseEntity<AppResp> pushLog(@RequestBody PageBean pb){
		Long userid = AppLocalInfo.getUserId(); //221189L
		//pageIndex
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pb.getPageIndex());
		commonPushUtilService.findRecordsByUser(userid, pageBean);
		return AppResp.get(pageBean);
	}
}
