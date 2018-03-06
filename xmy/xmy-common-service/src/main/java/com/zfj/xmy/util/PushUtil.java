
package com.zfj.xmy.util;

import java.util.Collection;
import java.util.List;

import jxl.common.BaseUnit;

import com.zfj.base.enu.BaseProp;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.device.DeviceClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;

/**
 * 推送公共类 
 * @author wy
 *
 */
public class PushUtil {

//	private static String appKey =  BaseProp.BASE.getValue("JPUSH_APPKEY");
//	private static String masterSecret = BaseProp.BASE.getValue("JPUSH_MASTERSECRET");
	
//	private static String appKey = "99b64d40ce4fc95173aaf02b";
//	private static String masterSecret = "e53b5eef89308127cd2e5635";
	
	//xmy正式
	private static String appKey = BaseProp.BASE.getValue("JPUSH_APPKEY") ;
	private static String masterSecret = BaseProp.BASE.getValue("JPUSH_MASTERSECRET") ;
	
	
	public static void main(String[] args) {
		//sendBroadcasts("画碴副螺蛳中斩腥叫朵必理的在车顶碭亿肌而与樱唇屋顶？");

		//sendToUserByToken("244707DC0DD0430CAE4C5C803D8A5B1E","顾甄枯干坧");
		
		//sendToUserByToken("05787AB131854662A8A2F0F22B59FEC7","淡淡淡淡淡炒之之之之之2333");
		
		push(testgetPushPayloadByAlias("A3EE896AF9DF4BF894B4DCCBED33EE00", "pushpush画碴副螺蛳中斩腥叫朵必理的在车顶碭亿肌而与樱唇屋顶？"));
		
//		get();
	}
	
	
	
	
	/**
	 * 发送广播
	 * @param msg
	 * @Description 
	 * @date 2017年8月28日  下午2:51:38
	 * @author wy
	 * 2017
	 * @return 
	 * @return void
	 */
	public static String sendBroadcasts(String msg){
		
		return push(getPushPayloadBroadcasts(msg));
	}
	
	/**
	 * 推送给单一用户 
	 * @param msg 推送信息
	 * @param registrationId 极光 app 端的 注册ID
	 * @Description 
	 * @date 2017年8月28日  下午3:59:33
	 * @author wy
	 * 2017
	 * @return void
	 */
	public static void sendToUser(String msg,String registrationId){
		push(getPushPayloadByRegistrationId(registrationId));
	}
	
	/**
	 * 推送给单一用户 
	 * @param appToken 用户 token
	 *  @param msg 推送信息
	 * @Description 
	 * @date 2017年8月28日  下午4:00:33
	 * @author wy
	 * 2017
	 * @return 
	 * @return void
	 */
	public static String sendToUserByToken(String appToken,String msg){
		return push(getPushPayloadByAlias(appToken, msg));
	}
	
	/**
	 * 推送给多个用户
	 * @param appTokens
	 * @param msg
	 * @return
	 * @Description 
	 * @date 2018年1月29日  下午2:59:56
	 * @author wy
	 * 2018
	 * @return String
	 */
	public static String sendToUserByTokens(List<String> appTokens,String msg){
		return push(getPushPayloadByAlias(appTokens, msg));
	}
	
	/**
	 * 推送主方法
	 * @param pushPayload
	 * @Description 
	 * @date 2017年8月28日  下午2:54:57
	 * @author wy
	 * 2017
	 * @return 
	 * @return void
	 */
	private static String push(PushPayload pushPayload){
		

		
		JPushClient client = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
		try {
			PushResult result = client.sendPush(pushPayload);
			client.close();
			System.out.println(result.toString());
			return result.toString();
		} catch ( APIRequestException e) {
			e.printStackTrace();
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
			
			System.out.println("response: " + e.getMessage());
		} catch (APIConnectionException e) {
			e.printStackTrace();
			System.out.println("Connection error, should retry later");
		}
		return null;
	}
	
	
	private static PushPayload getPushPayloadByRegistrationId(String registrationId){
		return PushPayload.newBuilder()
				.setAudience(Audience.registrationId(registrationId))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true) //True 表示推送生产环境，False 表示要推送开发环境；
						.build())
				.build();
	}
	
	private static PushPayload getPushPayloadByRegistrationIds(Collection<String> registrationIds){
		return PushPayload.newBuilder()
				.setAudience(Audience.registrationId(registrationIds))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true) //True 表示推送生产环境，False 表示要推送开发环境；
						.build())
				.build();
	}
	
	private static PushPayload getPushPayloadByAlias(String alias,String alert){
		return PushPayload.newBuilder()
				.setAudience(Audience.alias(alias))
				.setPlatform(Platform.all())
				.setNotification(Notification.alert(alert))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true) //True 表示推送生产环境，False 表示要推送开发环境；
						.build())
				.build();
	}
	
	private static PushPayload getPushPayloadByAlias(List<String> alias,String alert){
		return PushPayload.newBuilder()
				.setAudience(Audience.alias(alias))
				.setPlatform(Platform.all())
				.setNotification(Notification.alert(alert))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true) //True 表示推送生产环境，False 表示要推送开发环境；
						.build())
				.build();
	}
	
	private static PushPayload getPushPayloadBroadcasts(String alert){
		return PushPayload.newBuilder()
				.setAudience(Audience.all())
				.setPlatform(Platform.all())
				.setNotification(Notification.alert(alert))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true) //True 表示推送生产环境，False 表示要推送开发环境；
						.build())
				.build();
	}
	
	
	/**
	 * 测试
	 * @param alias
	 * @param alert
	 * @return
	 * @Description 
	 * @date 2018年1月5日  下午2:43:17
	 * @author wy
	 * 2018
	 * @return PushPayload
	 */
	private static PushPayload testgetPushPayloadByAlias(String alias,String alert){
		return PushPayload.newBuilder()
				.setAudience(Audience.alias(alias))
				.setPlatform(Platform.all())
				.setMessage(Message.newBuilder()
						.setMsgContent("自定义消息正文")
						.addExtra("aa", "aaaa")
						.addExtra("bb", "bbbb")
						.build())
				.setNotification(Notification.alert(alert))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true) //True 表示推送生产环境，False 表示要推送开发环境；
						.build())
				.build();
	}
	
	
	
	/**
	 * 根据alias查询 registration_id
	 * 
	 * @Description 
	 * @date 2017年12月25日  下午3:21:07
	 * @author wy
	 * 2017
	 * @return void
	 */
	private static void get(){
		DeviceClient deviceClient = new DeviceClient(masterSecret, appKey);
		try {
			AliasDeviceListResult aliasDeviceListResult = deviceClient.getAliasDeviceList("05787AB131854662A8A2F0F22B59FEC7", null);
			System.out.println(aliasDeviceListResult);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
