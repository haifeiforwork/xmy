<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>消息详情</title>
	<!-- 公共css区域 -->
	<%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/mine/css/message_info.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
</head>
  
  <body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">消息详情</div>
    </div>
</header>
	<div class="content" style="margin-top: 60px;">
		<div class="weui-cell wrap">
			<div class="weui-cell__bd">
				<p class="item-title">${message.title }</p>
				<p class="item-after"><fmt:formatDate value="${message.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
			</div>
		</div>
		<div class="wrap wrap-content">
			${message.body }
		</div>
	</div>
  </body>
   <!-- 公共js区域 --> 
   <%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
   <!-- 公共js区域 --> 
   <!-- 该页面应用js区域 -->
   <script type="text/javascript" src="resources/mine/js/message_info.js"></script>
   <!-- 该页面应用js区域 -->
   
</html>
