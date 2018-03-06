<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>邮箱验证</title>
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/index/css/register.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<!--<header>
    <div class="header-top">
        <div class="pull-left"><img src="images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">企业定制</div>
        <div class="pull-right">
            <span class="share"><img src="images/btn-fenl-share@2x.png"/></span>
            <span class="shrink"><img src="images/btn-fenl-more@2x.png"/></span>
        </div>
    </div>
</header>-->
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    	</div>
    </header>
<div class="content">
    <div class="container container-white">
        <div class="weui-msg">
            <div class="weui-msg__icon-area logo-area"><img src="resources/common/images/img-login-logo@3x.png" alt=""></div>
            <div class="weui-cells self-cell weui-cells_form">
            	<form action="mine/bindEmail" id="bindEmailForm" method="post">
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><img src="resources/common/images/email-icon.png" alt="" class="hd-img"></div>
	                    <div class="weui-cell__bd"><input type="text" id="email" name="email" class="weui-input" placeholder="邮箱"></div>
	                    <div class="weui-cell__ft get-code"><button type="button" id="getEmailValidationCode">获取验证码</button></div>
	                </div>
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><img src="resources/common/images/btn-reg-yzm@2x.png" alt="" class="hd-img"></div>
	                    <div class="weui-cell__bd"><input type="text" id="emailCode" name="code" class="weui-input" placeholder="请输入验证码"></div>
	                </div>
                </form>
            </div>
            <div class="weui-msg__opr-area">
                <p class="weui-btn-area self-btn-area">
                    <a href="javascript:;" class="weui-btn weui-btn_primary" id="bindEmail">绑定</a>
                </p>
            </div>
            <div class="weui-flex self-flex">
            </div>
            <div class="weui-msg__extra-area">
                <div class="weui-footer self-footer">
                    <p class="weui-footer__links">
                        <a href="javascript:void(0);" class="weui-footer__link theme-color">服务热线 : 400-822-3936 夜间电话 : 177-2315 -6790</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<script type="text/javascript" src="resources/index/js/login.js"></script>
</body>
</html>