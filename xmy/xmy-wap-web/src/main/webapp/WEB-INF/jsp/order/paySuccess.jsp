<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>提交成功</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <link rel="stylesheet" href="resources/order/css/paySuccess.css" />
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">提交成功</div>
    		<div class="pull-right">
    			<span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/></span>
    			<div class="modal-some">
                    
               	</div>
    		</div>
    	</div>
    </header>
	<div class="content">
    	<div class="head-top">
            <div class="head-img"><img src="resources/common/images/img-zfjg@2x.png"/></div>
            <div class="head-text">
                <p class="b">您的订单已提交成功，感谢亲的惠顾哦！</p>
                <p>创建时间:<span><fmt:formatDate value="${order.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            </div>
        </div>
        <div class="weui-cells list">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>订单号</p>
                </div>
                <div class="weui-cell__ft">${order.no }</div>
            </div>
            
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>已付金额</p>
                </div>
                <c:if test="${order.status == 5 }" >
	                <div class="weui-cell__ft">￥${order.total}</div>
                </c:if>
                <c:if test="${order.status != 5 }" >
                	<c:if test="${order.payType == 5 }" >
                		<div class="weui-cell__ft">￥${order.pay}</div>
                	</c:if>
                	<c:if test="${order.payType != 5 }" >
                		 <div class="weui-cell__ft">￥<fmt:formatNumber pattern="0.00" value="${order.pay + order.actual }" /></div>
                	</c:if>
                </c:if>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>应付金额</p>
                </div>
                <c:if test="${order.status == 5 }" >
	                <div class="weui-cell__ft">￥0.00</div>
                </c:if>
                <c:if test="${order.status != 5 }" >
                	<c:if test="${order.payType == 5 }" >
                		<div class="weui-cell__ft">￥${order.actual}</div>
                	</c:if>
                	<c:if test="${order.payType != 5 }" >
                		 <div class="weui-cell__ft">￥0.00</div>
                	</c:if>
                </c:if>
                <%-- <div class="weui-cell__ft">￥${order.actual}</div> --%>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>支付状态</p>
                </div>
                <div class="weui-cell__ft green">供货确认</div>
            </div>
        </div>
        <div class="weui-flex chunk">
            <div class="weui-flex__item orange"><button class="continue" type="button">继续购物</button></div>
            <div class="weui-flex__item"><button class="back" type="button">返回订单列表</button></div>
        </div>
   	</div>
    <!-- 公共js区域 --> 
    <%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <script type="text/javascript" src="resources/order/js/paySuccess.js"></script>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <!-- 该页面应用js区域 -->
    </body>
</html>