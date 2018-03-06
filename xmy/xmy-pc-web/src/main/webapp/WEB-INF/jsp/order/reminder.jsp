<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>订单提交</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/test/success.css">
  </head>
  <body>
  	<nav>
  	<%@include file="/WEB-INF/jsp/commons/nav/comm_top.jsp" %>
  	<div class="navigation">
    <div class="w-1250">
        <div class="navigation-left">
            <div class="left-chunk1"><img src="resource/commons/images/gr-logo.jpg"/></div>
            <div class="left-chunk2">
                <p class="my-xmy">我的香满圆</p>
				<a href="index" style="text-decoration:none;">
					<p class="return-index" style="cursor: pointer;">返回香满圆首页</p>
				</a>
            </div>
        </div>
        <div class="navigation-right">
	        <a href="cart/shoppingCart">
	            <div class="right-chunk1"><i class="iconfont cart">&#xe64f;</i>我的购物车<i class="iconfont pull-left">&#xe6e8;</i><div class="number">${sessionScope.countCart }</div></div>
	        </a>
        </div>
    </div>
</div>
  	</nav>
<!--内容部分-->
<div class="content">
    <div class="w-1250">
		<div class="successful-tip" >
			<div class="matter">
				<div class="state"><i class="iconfont">&#xe607;</i>亲，订单已生成，不能重复提交订单哦。请到我的订单中完成支付</div>
				<div class="tag"><span class="button" id="rollbackpay">返回支付界面</span></div>
			</div>
		</div>
    </div>
</div>
<!--内容部分完-->
  <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  <script type="text/javascript" src="resource/js/test/success.js"></script>
  <script type="text/javascript" src="resource/commons/js/js.cookie.js"></script>
  <script type="text/javascript">
  	var orderId = Cookies.get('lastOrderId'); 
  	$("#rollbackpay").click(function (){
  		if (orderId != '' && orderId != 'undefined') {
  			window.location.href="/order/payOrder/"+orderId;
  		}
  	});
  </script>
</body>
</html>
