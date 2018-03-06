<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--制作lj-->
<!DOCTYPE html>
<html>
<head>
    <title>物流信息</title>
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/order/css/logistics_order.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top detel-border">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">物流信息</div>
    </div>
</header>
<div class="content container">
	<div class="weui-loadmore" style="display: block;margin-top: 7rem">
	  <i class="weui-loading"></i>
	  <span class="weui-loadmore__tips">正在加载</span>
	</div>
</div>

<input type="hidden" id="orderId" value="${orderId }">
<!-- 公共js区域 -->
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<script src="resources/common/js/jquery.min.js"></script>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/order/js/logistics_order.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>