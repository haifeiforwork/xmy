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
            <div class="left-chunk1"><a href="index"><img src="resource/commons/images/gr-logo.jpg"/></a></div>
            <div class="left-chunk2">
                <p class="my-xmy">我的香满圆</p>
				<a href="index" style="text-decoration:none;">
					<p class="return-index" style="cursor: pointer;">返回香满圆首页</p>
				</a>
            </div>
        </div>
        <div class="navigation-right">
	        <a href="cart/shoppingCart">
	            <div class="right-chunk1"><i class="iconfont cart">&#xe64f;</i>我的购物车<i class="iconfont pull-left">&#xe6e8;</i><div class="number">0</div></div>
	        </a>
        </div>
    </div>
</div>
  	</nav>
<!--内容部分-->
<div class="content">
    <div class="w-1250">
		<div class="successful-tip" style="display: none;">
			<div class="matter">
				<div class="state"><i class="iconfont">&#xe607;</i>感谢您，订单已提交成功</div>
				<div class="tag">您已提交成功，您还可以<span class="button">查看订单详情</span><span class="button">继续购买</span></div>
			</div>
		</div>
		<div class="successful-tip" style="display: none;">
			<div class="matter">
				<div class="state"><i class="iconfont">&#xe607;</i>您的订单已提交成功，感谢亲的惠顾哦！</div>
				<div class="tag">您可以<span class="button">查看订单详情</span><span class="button">继续购买</span></div>
			</div>
		</div>
		<div class="successful-tip">
			<div class="matter">
				<div class="state"><i class="iconfont">&#xe607;</i>
					<c:choose>
						<c:when test="${payType == 5 }">您的订单已提交成功，感谢亲的惠顾哦！</c:when>
						<c:otherwise>您的订单已支付成功，感谢亲的惠顾哦！</c:otherwise>
					</c:choose>
				</div>
				<div class="tag">您可以<a href="order/detail/${orderId }" style="color: #6C6C6C;"><span class="button">查看订单详情</span></a><a href="index" style="color: #6C6C6C;"><span class="button">继续购买</span></a></div>
			</div>
		</div>
    </div>
</div>
<!--内容部分完-->
  <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  <script type="text/javascript" src="resource/js/test/success.js"></script>
  <script type="text/javascript">
  	findCartNum();
  	function findCartNum(){
  		//商品总数
  		$.ajax({
  			url:"commons/cart/count",
  			type:"post",
  			dataType:"json",
  			success:function(data){
  				$(".number").html(data);
  			}
  		});
  	}
  
  </script>
  
  
</body>
</html>
