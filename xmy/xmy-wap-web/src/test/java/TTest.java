import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zfj.xmy.util.WechatLogin;



public class TTest {
	
	/**
	 *@author cj
	 *测试获取用户地址集合
	 * @throws ParseException 
	 */
	//@Test
	//public void test99() {
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:conf/spring/applicationContext-common.xml");
/*		WapUserService serv = (WapUserService) context.getBean("wapUserService");
		User user = new User();
		user.setId((long) 24);
		List<UserAddrees> queryAddress = serv.queryAddress(user);*/
	//}

	
	/*@Test
	public void test98() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String str = sdf.format(new Date());
		logger.info("当前日期:" + str);
		Long currTime = sdf.parse(str).getTime();
		logger.info("当前毫秒:" + currTime);
		
		//午夜的日期
		Date midnight = new Date(0, 0, 0, 0, 0, 0);
		logger.info("午夜的日期:" + sdf.format(midnight));
		Long midnightTime = sdf.parse(sdf.format(midnight)).getTime();
		logger.info("午夜的毫秒:" + midnightTime);
		
		//中午的日期
		Date noon = new Date(0, 0, 0, 12, 0, 0);
		logger.info("中午的日期:" + sdf.format(noon));
		Long noonTime = sdf.parse(sdf.format(noon)).getTime();
		logger.info("中午的毫秒:" + noonTime);
	}*/
	
/*	public static void main(String[] args) throws ParseException {
		 Date tommorow=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(tommorow);
		 calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		 tommorow=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("M月d日");
		 String tomorrowStr = formatter.format(tommorow);
		 System.out.println(tomorrowStr);
	}*/

/*	public static void main(String[] args) {
		String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		System.out.println(StringUtil.isMatch(reg, "611548249@qq.com"));
		
		String reg1 = "^1[3|4|5|7|8]\\d{9}$";
		System.out.println(StringUtil.isMatch(reg1, "17723541852"));
		
	}*/
	
	public static void main(String[] args) throws ParseException {
//		System.out.println(QQLogin.qqGetAuthorizationCodeFullUrl());
		System.out.println(WechatLogin.wechatGetCodeFullUrl());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd aHH:mm:ss");
		Date parse = sdf.parse("2017/11/15 下午11:37:04");
		System.out.println(parse.toLocaleString());
	}
	
}