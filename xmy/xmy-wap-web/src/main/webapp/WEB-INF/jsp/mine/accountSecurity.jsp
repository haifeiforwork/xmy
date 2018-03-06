<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>账户安全</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <link rel="stylesheet" href="resources/mine/css/accountSecurity.css" />
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">账户安全</div>
    	</div>
    </header>
	<div class="content">
    	<div class="list">
			<div class="weui-cells">
				<c:if test="${not empty wap_session.userInfo.mobilePhone }">
					<a class="weui-cell weui-cell_access" href="gotoPage/mine/modifyPassword">
					    <div class="weui-cell__bd">
					      	<p>登录密码</p>
					      	<p class="hint">建议您定期更改密码以保证账户安全</p>
					    </div>
					    <div class="weui-cell__ft">
	
					    </div>
					</a>
					<a class="weui-cell weui-cell_access bindPhone" href="javascript:;">
					    <div class="weui-cell__bd">
					      	<p>手机验证</p>
					      	<!-- <p class="hint1"></p> -->
					      	<p class="hint">若手机更换请尽快修改</p>
					    </div>
					    <div class="weui-cell__ft" style="font-size:0.65rem;">
					    	${wap_session.userInfo.mobilePhone }
					    </div>
					</a>
				</c:if>
				<a class="weui-cell weui-cell_access bindEmail" href="javascript:;">
				    <div class="weui-cell__bd">
				      	<p>邮箱绑定</p>
				      <!-- 	<p class="hint1"></p> -->
				      	<p class="hint">若邮箱更换请尽快修改</p>
				    </div>
				    <div class="weui-cell__ft" style="font-size:0.65rem;">
				    	${wap_session.userInfo.email }
				    </div>
				</a>
			</div>
    	</div>
   	</div>
   	<!-- 公共js区域 --> 
    <script src="resources/common/js/jquery.min.js"></script>
	<script src="resources/common/js/jquery-weui.min.js"></script>
	<script src="resources/common/js/swiper.min.js"></script>
	<script src="resources/common/js/city-picker.min.js"></script>
    <!-- 公共js区域完 -->
   	<script type="text/javascript" src="resources/mine/js/accountSecurity.js"></script>
</body>
</html>