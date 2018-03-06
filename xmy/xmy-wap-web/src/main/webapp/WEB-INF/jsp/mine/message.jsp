<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>消息列表</title>
	<!-- 公共css区域 -->
	<%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link rel="stylesheet" href="resources/mine/css/message.css" /。
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">消息列表</div>
    </div>
</header>
<div class="content">
    <div class="list">
        <div class="weui-cells">
        	<c:if test="${fn:length(massageList) == 0 }">
        		<a class="weui-cell weui-cell_access" >
        			<p style="margin: auto auto;">暂无消息内容!</p>
        		</a>
        	</c:if>
        	<c:forEach items="${massageList }" var="message">
        		<a class="weui-cell weui-cell_access message-div" href="javascript:;" message-id="${message.id }">
        			<input type="hidden" name="readstatus" value="${message.readstatus }"/>
        			<div class="weui-cell__hd"></div>
	                <div class="weui-cell__bd">
	                	<p class="item-title">${message.title }</p>
	                    <p class="item-sub">${message.body }</p>
	                    <p class="item-after"><fmt:formatDate value="${message.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>	
	                </div>
	                <div class="weui-cell__ft">
	                   
	                </div>
	            </a>
        	</c:forEach>
        </div>
    </div>
</div>
<!-- 公共js区域 -->
<%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/mine/js/message.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>