package com.zfj.xmy.app.web.controller;

import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.appdev.db.common.pojo.PageBean;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimImmsgPushRequest.ImMsg;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.encryption.DESUtil;
import com.zfj.base.util.mail.MailUtil;
import com.zfj.base.util.mail.model.JMail;
import com.zfj.xmy.activity.service.cms.CouponService;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.BrowsedGoods;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.goods.service.app.TermDataExService;
import com.zfj.xmy.goods.service.cms.VocabularyService;
import com.zfj.xmy.openim.OpenImManager;
import com.zfj.xmy.order.service.app.BrowsedGoodsService;
import com.zfj.xmy.order.service.app.CollectionGoodsService;
import com.zfj.xmy.order.service.cms.PayLogService;
import com.zfj.xmy.user.persistence.pojo.dto.UserInfoDto;
import com.zfj.xmy.user.service.common.CommonService;
import com.zfj.xmy.user.service.common.UserInfoService;
import com.zfj.xmy.user.service.common.UserService;
import com.zfj.xmy.util.PushUtil;
import com.zfj.xmy.util.template.TemplateUtil;

import freemarker.core.Configurable;
import freemarker.core.FreeMarkerTree;
import freemarker.template.Configuration;
import freemarker.template.Template;

@RequestMapping("/test")
@RestController
public class TestController {

	@Autowired
	private PayLogService payLogService ;
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private VocabularyService vocabularyService;
	
	@Autowired
	private CollectionGoodsService collectionGoodsService;
	@Autowired
	private TermDataExService termDataExService;
	
	@Autowired
	private BrowsedGoodsService browsedGoodsService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private OpenImManager openImManager;
	
	
	
	@RequestMapping("/index")
	public RespData index(){
		RespData respData = new RespData() ;
		respData.setData("ok") ;
		return respData ;
	}
	
//	public static void main(String[] args) {
//		String patternUrl = "/user/edit/**" ;
//		String reqUrl = "/user/authorize" ;
//		PathMatcher matcher = new AntPathMatcher() ;
//		System.out.println(matcher.match(patternUrl,reqUrl)) ;
//		
//		System.out.println(matcher.match("/????", "/bala")) ;
//	}
	
	@RequestMapping("/resp")
	@Exclusion
	public ResponseEntity<AppResp> testAppResp(){
//		int i = 0 ;
//		System.out.println(5 / i) ;
		return AppResp.get(null) ;
	}
	
	@RequestMapping("/paylog/1")
	public RespData payLogTest(){
		RespData respData = new RespData() ;
		ReqData reqData = new ReqData() ;
		reqData.putValue("user_name","张",SystemConstant.REQ_PARAMETER_CN) ;
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(1) ;
		pageBean.setPageSize(10) ;
		payLogService.findTest2(pageBean, reqData) ;
		respData.setData(pageBean) ;
		return respData ;
	}
	@RequestMapping("/getUserInfo/{id}")
	public RespData getUserInfo(@PathVariable Long id){
		RespData respData=new RespData();
		UserInfoDto userInfoDto = userInfoService.getUserInfoDto(id);
		respData.setData(userInfoDto);
		return respData;
	}
	@RequestMapping(value="/getUserAddress/{id}",method=RequestMethod.POST)
	public RespData getUserAddress(@PathVariable Long id,HttpServletRequest request,@RequestBody JSONObject jsonObj){
		RespData respData=new RespData();
		List<Object> ls=new ArrayList<Object>();
		
		List<UserAddrees> userAddress = userInfoService.getUserAddress(id);
		ls.add(userAddress);
		ls.add(jsonObj);
 		respData.setData(ls);
		return respData;
	}
	/**
	 * 
	 * @Description APP关于香满园H5页面
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午4:37:07
	 */
	@RequestMapping(value="/getAppAboutXmyH5")
	public RespData getAppAboutXmyH5(){
		RespData respData=new RespData();
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.APP_ABOUTXMY, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		
		TermData appAboutXmyH5 = commonService.getAppAboutXmyH5ByVid( vocabulary.getId());
		respData.setData(appAboutXmyH5);
		return respData;
	}
	@RequestMapping("/getAllCollectionGoods/{userId}")
	public RespData getAllCollectionGoods(@PathVariable Long userId){
		RespData respData=new RespData();
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		List<CollectionGoods> findsAllCollectionGoods = collectionGoodsService.findsAllCollectionGoods(reqData);
		respData.setData(findsAllCollectionGoods);
		return respData;
	}
	@RequestMapping("/getAllCollectionGoodsAndCategorys/{userId}")
	public RespData getAllCollectionGoodsAndCategorys(@PathVariable Long userId){
		Map<String,Object> data=new HashMap<>();
		RespData respData=new RespData();
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		//1 查找所有商品
		List<GoodsWithBLOBs> findCollectionGoods = collectionGoodsService.findCollectionGoods(reqData);
		data.put("collectionGoods", findCollectionGoods);
		//2 查找商品对应的分类
	//	Map<String, Integer> findCollectionCategorys = termDataExService.findCollectionCategorys(findCollectionGoods);
	//	data.put("collectionCategorys", findCollectionCategorys);
		respData.setData(data);
		return respData;
	}
	@RequestMapping("/getAllBrowsedGoods/{userId}")
	public RespData getAllBrowsedGoods(@PathVariable Long userId){
		Map<String,Object> data=new HashMap<>();
		RespData respData=new RespData();
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		//1 先查找足迹表中的数据
		 List<BrowsedGoods> findBrowsedGoods = browsedGoodsService.findBrowsedGoods(reqData);
		//2 查找所有商品
		 List<GoodsWithBLOBs> findBrowsedGoodsWithGoods = browsedGoodsService.findBrowsedGoodsWithGoods(findBrowsedGoods);
		data.put("browsedGoods", findBrowsedGoodsWithGoods);
		
		respData.setData(data);
		return respData;
	}
	@RequestMapping("/getCoupons/{userId}")
	public RespData getCoupons(@PathVariable Long userId){
		Map<String,Object> data=new HashMap<>();
		RespData respData=new RespData();
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> findCoupons = couponService.findCoupons(reqData);
		List<Coupon> transformByCouponUser = couponService.transformByCouponUser(findCoupons);
		respData.setData(transformByCouponUser);
		return respData;
	}
	@RequestMapping("/html")
	@Exclusion
	public ModelAndView indexHtml(){
		
		//PushUtil.sendBroadcasts("1111222233334444");
		
//		String user = openImManager.getUser("CAD61BD26E774548A66A5B3CD7A62E41"); -
		//"34eo9c6k96cv3ulb7xon7mcn2s8oy7ph" -> 24e10adc3949ba59abbe56e057f20f883e
		//"09C48F8305024B938FED2D7D853B2286" -> 27df718b884a040f0d43edd88a2f4677b1
		//openImManager.addUser(27l);
		//String user = openImManager.immsgPush(immsg);
//		Userinfos userinfos = new Userinfos();
//		userinfos.setUserid("34eo9c6k96cv3ulb7xon7mcn2s8oy7ph");
//		userinfos.setNick("用户1");
//		String user = openImManager.updateUser(userinfos);
		
		//更新用户
//		Userinfos userinfos = new Userinfos();
//		userinfos.setUserid("34eo9c6k96cv3ulb7xon7mcn2s8oy7ph");
//		userinfos.setIconUrl("http://tool.oschina.net/img/logo/encode.gif");
//		String user = openImManager.updateUser(userinfos);
		
		//openImManager.getUser(userid);
		
		///发送服务器消息
//		List<String> list = new ArrayList<String>();
//		list.add("09C48F8305024B938FED2D7D853B2286");
//		
//		ImMsg immsg = new ImMsg();
//		immsg.setMsgType(0L);
//		immsg.setContext("这个方村");
//		immsg.setFromUser("34eo9c6k96cv3ulb7xon7mcn2s8oy7ph");
//		immsg.setToUsers(list);
//		com.xiaoleilu.hutool.json.JSONObject jsonObject = new com.xiaoleilu.hutool.json.JSONObject(immsg);
//		System.out.println(jsonObject.toString());
//		openImManager.immsgPush(immsg);
		
		//openImManager.getUser(1l);

		//couponService.findUserCoupon(21L,1);
		
//		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();
//		for (int i = 0; i < 15; i++) {
//			System.out.println(generator.generate(12)); //12位
//		}
		
		
//		JSONArray array = new JSONArray(couponService.getCouponsHead(21L));
//		System.out.println(array.toString());
		
		String a = "B2C" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS")+RandomUtil.randomNumbers(4);
		System.out.println(a);
		
		return new ModelAndView("/test/index") ;

	}
	
	
	
	@RequestMapping("/chat2")
	@Exclusion
	public ModelAndView chat2(){
		String user = openImManager.getUserByOpenimUserid("5DAD392AFCAE44E18240B09FB35C6A3E");
		System.out.println(user);
		return new ModelAndView("/test/chat2") ;
	}
	
	
	public static void main(String[] args) throws Exception {

		
//		String retStr = "access_token=5BDA416BE0FB140990F72CF4604963C3&expires_in=7776000&refresh_token=21E0AF83294B85D5E357B750380608C0";
//		Map<String, List<String>> map =  HttpUtil.decodeParams(retStr, "utf-8");
//		System.out.println(map);
//		
//		JMail jMail = new JMail();
//		jMail.setCharSet("utf-8");
//		jMail.setContent("fdfskjfkpppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//		jMail.setHost("smtp.mail.yahoo.com");
//		jMail.setSSL(true);
//		jMail.setRecipientEmail("blambinneng@gmail.com");
//		jMail.setSendEmail("blambinneng@yahoo.com");
//		jMail.setSendEmailPassword("");
//		jMail.setSendName("blambinneng");
//		jMail.setSendTitle("pppppppppppppppppppppppppppp");
//		jMail.setSmtpPort(465);
		//MailUtil.send(jMail);
		

//		JMail jMail = new JMail();
//		jMail.setCharSet("utf-8");
//		jMail.setContent("香满园验证码:123");
//		jMail.setHost("smtp.mxhichina.com");
//		jMail.setSSL(true);
//		jMail.setRecipientEmail("1012234043@qq.com");
//		jMail.setSendEmail("noreply@time-cost.com");
//		jMail.setSendEmailPassword("Coolong9898");
//		jMail.setSendName("blambinneng");
//		jMail.setSendTitle("pppppppppppppppppppppppppppp");
//		jMail.setSmtpPort(465);
//		MailUtil.send(jMail);

		//String json="{\"shoppingCart\":[{\"goodsId\":67,\"num\":2}]}";


		//String json="{\"shoppingCart\":[{\"goodsId\":20,\"num\":2}]}";

		//String json="{\"appGoodsVo\":[{\"id\":67,\"num\":2}]}";


		//String json = "[{\"goodId\":20,\"num\":1}]";
		//String json = "{\"orderid\":5,\"paytype\":9}";
//		String json = "{\"orderid\":1676,\"paytype\":3,\"payAmount\":19}";
//		
//		try {
//			String param = DESUtil.encryptDES(json);
//			System.out.println(param);
////			String des = "t/yk+ARIbAd3c410RmzuD3Lbzznr6s0CMhKKXMnIMck=";
////			String decryptDES = DESUtil.decryptDES(des);
////			System.out.println(decryptDES);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("mimi", "熮");
		
		String tempfromStr = "我我我我${mimi}了了";
		
	 	String out = TemplateUtil.format(tempfromStr, root);
	 	System.out.println(out);
//		FreeMarkerTree freeMarkerTree = new FreeMarkerTree(template);
//		
		//OpenImManager.deleteUser("5DAD392AFCAE44E18240B09FB35C6A3E");
		
		//System.out.println("3jyII1GduE38IVAlXwaS0UtXJIiBOXCpRvnnuvrXU6qtEzbZQG4og".substring(0, 32));;
		
//		Userinfos userinfo = new Userinfos();
//		userinfo.setUserid("904acc5a27914b139b069d6c70e68c0c");
//		userinfo.setPassword("123");
//		OpenImManager.updateUser(userinfo);

	}
}
