<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="This is my page">
	 <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
  </head>
  <body>
  
  </body>
  <script src="resources/common/js/jquery.min.js"></script>
  <script src="resources/common/js/jquery-weui.min.js"></script>
  <script type="text/javascript">
  	var appid = "${map.appId}" ,timeStamp = "${map.timeStamp}", 
  		nonceStr = "${map.nonceStr}", packageinfo = "${map.packageinfo}",
  		paySign = "${map.paySign}", msg = "", payState = "${payState}", orderId = "${orderId}" ;
  		//$.alert(appid + "----" + timeStamp+ "----" + nonceStr + "-----" + packageinfo + "----" + paySign) ;
  	function onBridgeReady(){
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', {
	           "appId":appid,     //公众号名称，由商户传入     
	           "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
	           "nonceStr":nonceStr, //随机串     
	           "package":packageinfo,     
	           "signType":"MD5",         //微信签名方式：     
	           "paySign":paySign //微信签名 
	       },
	       function(res){ 
	       	  // $.alert(JSON.stringify(res)) ;
	           if(res.err_msg == "get_brand_wcpay_request:ok" ) {// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                    location.href = "payment/paySuccess?orderId=" + orderId;
	           }else{
	           		msg = "支付失败，请重新支付" ;
	           		$.alert(msg,function(){
	                   location.href = "order/payOrder/" + orderId;
	                }) ; 
	           } 
	           
	       } 
	   ); 
	} 
	
	if(payState == 200){
		msg = "支付成功，请前往订单详情里查看" ;
	}else if(payState == -99){
		msg = "该订单号不存在" ;
	}else if(payState == 100){
		msg = "请勿重复提交订单" ;
	}
	
	if(msg){
		$.alert(msg,function(){
			location.href = "mine/center";
			return ;
		}) ;
	}else{
	   if (typeof WeixinJSBridge == "undefined"){
	       if( document.addEventListener ){
	           document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	       }else if (document.attachEvent){
	           document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	           document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	       }
	    }else{
	       onBridgeReady();
	    } 
	}
  </script>
</html>
