package com.zfj.xmy.wap.web.controller.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.common.pojo.dto.AppBuyAndPresentOutDto;
import com.zfj.xmy.activity.persistence.common.pojo.dto.LimitActivityDir;
import com.zfj.xmy.activity.service.common.LimitActivityService;
import com.zfj.xmy.common.DateUtils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.pojo.ActivityQuestion;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.service.CommonPresentCouponActivityService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.wap.web.common.SessionInfo;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	private LimitActivityService limitActivityService;
	
	@Autowired
	private CommonPresentCouponActivityService presentCouponActivityService;
	
	@Autowired
	private OnLineActivityService activityService;
	
	@Autowired
	private UserMapper userMapper;
	/**
	 * 开抢啦
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/findActivity")
	public ModelAndView findActivity(HttpServletRequest request) throws ParseException{
		List<LimitActivityDir> list = limitActivityService.findLimitActivityList(null);
		LimitActivityDir  iceActivity = new  LimitActivityDir();  //每日秒杀
		LimitActivityDir  daydayActivity = new  LimitActivityDir();  //天天特价
		LimitActivityDir  weekActivity = new  LimitActivityDir();  //每周特价
		LimitActivityDir  woleActivity = new  LimitActivityDir();  //整件惠
		for (LimitActivityDir limitActivityDir : list) {
			if (limitActivityDir.getType() == SystemConstant.LIMITACTIVITY.FREEZING) {
				iceActivity = limitActivityDir;
			} 
			if (limitActivityDir.getType() == SystemConstant.LIMITACTIVITY.DAYDAY) {
				daydayActivity = limitActivityDir;
			} 
			if (limitActivityDir.getType() == SystemConstant.LIMITACTIVITY.WEEKWEEK) {
				weekActivity = limitActivityDir;
			} 
			if (limitActivityDir.getType() == SystemConstant.LIMITACTIVITY.WOLE) {
				woleActivity = limitActivityDir;
			} 
		}
		request.setAttribute("iceActivity", iceActivity);
		request.setAttribute("daydayActivity", daydayActivity);
		request.setAttribute("weekActivity", weekActivity);
		request.setAttribute("woleActivity", woleActivity);
		List<AppBuyAndPresentOutDto> buyAndPresent = limitActivityService.findBuyAndPersentActivity(); //买即赠
		request.setAttribute("buyAndPresent", buyAndPresent);
		List<AdImage> topImg = limitActivityService.findActivityImage(SystemConstant.AdInfoImage.BIG_IMAGE); //大图
		request.setAttribute("topImg", topImg);
		request.setAttribute("twelveOfTomorrow", DateUtils.getTwelveOfTomorrow().getTime());  //明天12点的结束时间
		request.setAttribute("dayEnd", DateUtils.getDayEnd().getTime()); //当天的结束时间
		request.setAttribute("endDayOfWeek", DateUtils.getEndDayOfWeek().getTime()); //本周的结束时间
		
		Map<String, Object> data=new HashMap<String, Object>();
		data.putAll(getLimitData());
		
		//存入当前时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");

		long mill = 0;
		int hours = date.getHours();
		mill += hours * 60 * 60 * 1000;
		int minutes = date.getMinutes();
		mill += minutes * 60 * 1000;
		int seconds = date.getSeconds();
		mill += seconds * 1000;
		System.out.println("当前时间毫秒数:" + mill);
		request.setAttribute("currMill", mill);
		String curr = sdf.format(date);
		request.setAttribute("curr", curr);
		
		Date yesterday=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(yesterday);
		calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
		yesterday=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat formatter = new SimpleDateFormat("M月d日");
		String yesterdayStr = formatter.format(yesterday);
		request.setAttribute("yesterday", yesterdayStr);
		
		request.setAttribute("data", data);
		request.setAttribute("millis", getDate());
		request.setAttribute("sum", getWeek());
		
		return new ModelAndView("/activity/activity");
	}
	
	/**
	 * 每日秒杀的当前与结束时间
	 * @return
	 */
	private Map<String,Long> getLimitData(){
		
		Map<String,Long> map = new HashMap<String,Long>();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		if(cal.getTime().getTime() < date.getTime()){
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		map.put("nowTime", date.getTime());
		map.put("endTime", cal.getTime().getTime());
		return map;
	}
	
	/**
	 * 天天特价的当天剩余时间
	 * @return
	 */
	private long getDate(){
		
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();

		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		long millis = c.getTimeInMillis() - now;
		return millis;
	}
	
	
	/**
	 * 每周特价倒计时
	 * @return
	 * @throws ParseException 
	 */
	private long getWeek() throws ParseException{
	     Calendar c = Calendar.getInstance();  
	     long dayWeek = c.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
	     if (dayWeek==1) {  
	    	 dayWeek=7; 
	     }else{
	    	 dayWeek=dayWeek-1;
	     }
	     long day=7-dayWeek;
	     long sum1;
	     sum1=day*24*60*60*1000+getDate();
	     return sum1;
	}
	
	/**
	 * 专题
	 * @param url
	 * @param model
	 * @param flag
	 * @return
	 */
	@RequestMapping("/{url}")
	public String toActivityDetails(@PathVariable("url") String url, ModelMap model, String flag, String modal) {
		if(!StringUtil.isEmpty(flag) && "app".equals(flag)){
			url = url.replace("wap", "app") ;
		}
		model.addAttribute("url", "/WEB-INF/jsp/subject/" + url+"/"+url+".jsp");
		model.addAttribute("flag", flag);
		model.addAttribute("modal", modal);
		return "subject/subject";
	}
	
	/**
	 * 查询活动商品是否是在可购买范围
	 * @param goodsId
	 * @return Integer
	 * @author lij
	 * @date 2018年1月9日 下午4:04:10
	 */
	@RequestMapping("/selaGoods")
	@ResponseBody
	public Integer selaGoods(@Param("goodsId") Long goodsId){
		
		Integer integer = limitActivityService.isSelaGoodsByGoodsId(goodsId);
		
		return integer;
	}
	
	/**
	 * 活动赠送优惠卷
	 * @param userId
	 * @return String
	 * @author lij
	 * @date 2018年1月15日 上午9:26:12
	 */
	@RequestMapping("persentCoupon")
	@ResponseBody
	public Integer persentCoupon(){
		int i = 1;
		SessionInfo sessionInfo = SessionInfo.get();
		if(ObjectUtils.isEmpty(sessionInfo)){
			i=2;//请登录
		}else{
			try {
				presentCouponActivityService.presentCoupon(sessionInfo.getUserId());
				i=3;//领取成功
			} catch (BusinessException e) {
				i=e.getCode();
			}
		}
		return i;
	}
	
	/**
	 * 用户参与猜猜看活动
	 * @return Object 1:现在没有开启该活动！2:该活动不是猜猜看活动！3:答案太少了，请多设置几个不重复的答案！4:重复答案过多！5:该活动没有设置猜猜看的送券ID
	 * @author lij
	 * @date 2018年1月30日 下午5:35:57
	 */
	@RequestMapping("getQuestion")
	@ResponseBody
	public Object getQuestion(){
		ActivityQuestion question=null;
		try {
			question = activityService.findOnlineActvity();
		} catch (BusinessException e) {
			return e.getCode();
		}
		return question;
	}
	
	/**
	 * 用户提交答案
	 * @return Object -1:用户未登录不能参加活动 0:提交成功答案正确 1：提交成功答案不正确 2：数据库中不存在这个题目，请核实！3：活动不存在！4：送券失败 
	 * 5:该用户已经超过了这次活动的答题次数！6:该用户已经参与了该活动，并已经获得了奖品无需再参加！
	 * @author lij
	 * @date 2018年1月30日 下午5:35:57
	 */
	@RequestMapping("submitQuestion")
	@ResponseBody
	public Object submitQuestion(@Param("questionId") Long questionId,@Param("userAnswer") String userAnswer,@Param("token") String token){
		Long userId=null;
		if(ObjectUtils.isEmpty(token)){
			SessionInfo sessionInfo = SessionInfo.get();
			
			if(ObjectUtils.isEmpty(sessionInfo)){
			    return -1;
			}
			userId = sessionInfo.getUserId();
		}else{
			ReqData reqData = new ReqData();
			reqData.putValue("app_token", token, SystemConstant.REQ_PARAMETER_EQ);
			List<User> list = userMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			userId = list.get(0).getId();
		}
		if(ObjectUtils.isEmpty(userId)){
			return -1;
		}
		int i  = 0;
		try {
			i = activityService.submitAnswer(userAnswer, userId, questionId);
		} catch (BusinessException e) {
			return e.getCode();
		}
		if(i==0){
			return i;
		}else{
			return i;
		}
	}
}
