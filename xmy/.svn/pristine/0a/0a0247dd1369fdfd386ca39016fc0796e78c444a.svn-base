package com.zfj.xmy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendSMSUtil {

	/**
	 * 短信签名
	 */
	public static final String sign = "【香满圆】"; 
	/**
	 * 发送验证码短信
	 * @param phone
	 * @param code
	 * @return
	 * @Description 
	 * @date 2017年8月8日  上午9:38:19
	 * @author wy
	 * 2017
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendSMS(String phone,String code) throws UnsupportedEncodingException{

		String content = sign+"您的验证码为" + code + "请在60秒内使用，请勿将验证码告诉任何人以免造成不必要损失！";

		return sendFullSMS(phone, URLEncoder.encode(content,"utf-8"));
	}
	
	
	/**
	 * 商品提货码
	 * @param phone
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException    
	 * @return String    
	 * Date:2017年11月5日 上午1:40:53 
	 * @author hexw
	 */
	public static String sendInsertOrderSuccess(String phone,String no) throws UnsupportedEncodingException{
		String content = sign+"尊敬的香满圆用户，您的订单已经生成成功，订单编号"+no+"。";
		return sendFullSMS(phone, URLEncoder.encode(content,"utf-8"));
	}
	
	/**
	 * 发送自定义内容短信
	 * @param phone
	 * @param content
	 * @return
	 * @Description 
	 * @date 2017年8月8日  上午9:37:32
	 * @author wy
	 * 2017
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
    public static String sendFullSMSOld(String phone,String content) throws UnsupportedEncodingException{
		
		//String url="http://sdk.entinfo.cn:8061/mdsmssend.ashxx";	//测试
		String url="http://sdk.entinfo.cn:8061/mdsmssend.ashx";   //正式
		
		
		String sn = "sn=SDK-SKY-010-02646";
		String pwd = "&pwd=8EFA085EFB13DBC28B95EEB17A618C41"; //规则 ：strtoupper(md5('SDK-SKY-010-02646'.'137710'));
		String mobile = "&mobile="+phone;
		String contents = "&content=" + URLEncoder.encode(content,"utf-8") + "";
		String attach = "&ext=&stime=&rrid=&msgfmt=";
		String httpArg = sn+pwd+mobile+contents+attach;
		return request(url, httpArg);
	}
	
    /***
     * 发送自定义内容短信 （新接口）
     * @param phone
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     * @Description 
     * @date 2017年11月1日  下午2:55:50
     * @author wy
     * 2017
     * @return String
     */
    public static String sendFullSMS(String phone,String content)  {
    	String url="http://106.3.37.99:7799/sms.aspx";//正式 - 变更
    	
		String other = "action=send&userid=812&account=xmy&password=123456"; //规则 ：strtoupper(md5('SDK-SKY-010-02646'.'137710'));
		String mobile = "&mobile="+phone;
		//String contents = "&content=" + URLEncoder.encode(content,"utf-8") + "";
		String contents = "&content=" + content + "";
		String attach = "&sendTime=&extno=";
		String httpArg = other+mobile+contents+attach;
		return request(url, httpArg);
    }
	private static String request(String httpUrl, String httpArg ) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;
			connection.connect();
			InputStream is = connection.getInputStream();
			
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			connection.disconnect() ;
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != reader){
				try {
					reader.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		try {
			String res = sendSMS("15922577949", "555555");
//			String res = sendFullSMS("18502332954", "【香满园】今日订单：114单，需要配送的订单：113单，自提的订单：1单");
			System.out.println(res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
