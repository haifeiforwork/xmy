<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>订单支付</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/order/pay_order.css">
  </head>
  
  <body>
<!--内容部分-->
<div class="content" id="content">
</div>
<div style="display: none;">
	<form action="order/pay" method="post" id="payOrder">
		<input type="text" id="orderId" value="${orderid }"/>
		<input type="text" id="paytype" value="${paytype }"/>
		<input type="text" id="payAmount" value="${payAmount }"/>
	</form>
</div>
	<script type="text/javascript" src="resource/commons/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    	$(function(){
			var orderId=$("#orderId").val();
			var paytype=$("#paytype").val();
			var payAmount=$("#payAmount").val();
			if(paytype==8){//支付宝
				$.ajax({
	    			url:"pay/goods",
	    			type:"post",
	    			data:"orderid="+orderId+"&paytype=8&payAmount="+payAmount,
	    			success:function(data){
	    				$("#content").html(data.alipay);
	    			}
		    	});
			}
			if(paytype==5){
				$.ajax({
	    			url:"pay/goods",
	    			type:"post",
	    			data:"orderid="+orderId+"&paytype=5&payAmount="+payAmount,
	    			success:function(data){
	    				$("#content").html(data.html);
	    			}
		    	});
			}
    	});
    </script>
</body>
</html>
