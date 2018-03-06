<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>我的账户</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
		<link rel="stylesheet" href="resources/mine/css/account.css" />        
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">账户管理</div>
    	</div>
    </header>
	<div class="content">
		<input type="hidden" id="userId" value="${wap_session.user.id }" />
    	<div class="list">
			<div class="weui-cells">
				<a id="avatar" class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      	<p>头像</p>
				    </div>
				    <div class="weui-cell__ft avatar">
					    <c:if test="${empty wap_session.userInfo.avatar }">
	                        <img class="weui-media-box__thumb" id="changeAvatar" src="resources/common/images/cstars/defaultheadimg.png">
	                	</c:if>
	                	<c:if test="${not empty wap_session.userInfo.avatar }">
	                    	<img id="changeAvatar" src="resources/common/images/cstars/${wap_session.userInfo.avatar }" />
	                    </c:if>
				    </div>
				</a>
				<a class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      	<p>昵称</p>
				    </div>
				    <div style="font-size: 0.7rem;color: #999;padding-right: 13px;position: relative;">
				    	<input id="nick" type="text" placeholder="请输入" value="${wap_session.user.name }" class="weui-input" style="text-align:right"/>
				    </div>
				</a>
				<a id="gender" class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      	<p>性别</p>
				    </div>
				    <div id="genderDisplay" class="weui-cell__ft" />
				    	<c:if test="${wap_session.userInfo.sex == 0}">男</c:if>
				    	<c:if test="${wap_session.userInfo.sex == 1}">女</c:if>
				    	<c:if test="${empty wap_session.userInfo.sex}">请选择</c:if>
				    </div>
				</a>
				<a id="birthday" class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      	<p>生日</p>
				    </div>
				    <div id="birthdayDisplay" class="weui-cell__ft">
				    
				    <input type="text" id="birth-time" placeholder="请选择" class="weui-input" value="<c:if test='${wap_session.userInfo.birthday != null }'><fmt:formatDate value='${wap_session.userInfo.birthday }' pattern='yyyy-MM-dd' /></c:if>" style="text-align:right"/>	
				    	
				    </div>
				</a>
				<a class="weui-cell weui-cell_access" href="javascript:;">
				    <div class="weui-cell__bd">
				      	<p>座机号码</p>
				    </div>
				    <div id="phoneDisplay" style="font-size: 0.7rem;color: #999;padding-right: 13px;position: relative;">
				    	<input id="phone" type="text" placeholder="请输入" value="${wap_session.userInfo.phone }" class="weui-input" style="text-align:right"/>
				    </div>
				</a>
				<a class="weui-cell weui-cell_access safety" href="gotoPage/mine/accountSecurity">
				    <div class="weui-cell__bd">
				      	<p>账户安全</p>
				    </div>
				    <div id="contriner" class="weui-cell__ft">
				    </div>
				</a>
			</div>
    	</div>
   	</div>
    <!-- 该页面应用js区域 -->
    <!-- 公共js区域 --> 
    <script src="resources/common/js/jquery.min.js"></script>
	<script src="resources/common/js/jquery-weui.min.js"></script>
	<script src="resources/common/js/swiper.min.js"></script>
	<script src="resources/common/js/city-picker.min.js"></script>
    <!-- 公共js区域完 -->
    <script type="text/javascript" src="resources/mine/js/account.js" ></script>
    <!-- 该页面应用js区域 -->
    </body>
</html>