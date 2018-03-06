<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>
<!--制作lj-->
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/index/css/login.css" rel="stylesheet">
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
<div class="content">
	<input type="hidden" id="msg" value="${msg }">
    <div class="container container-white">
        <div class="weui-msg">
            <div class="weui-msg__icon-area logo-area"><img src="resources/common/images/img-login-logo@3x.png" alt=""></div>
            <div class="weui-cells self-cell weui-cells_form">
            	<form action="index/login" id="login_form" method="post">
            		<input type="hidden" name="requestUri" value="${requestUri }" />
            		<%-- <input type="hidden" name="backurl1" value="${backurl1 }" /> --%>
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><img src="resources/common/images/btn-tab-my-g@2x.png" alt="" class="hd-img"></div>
	                    <div class="weui-cell__bd"><input type="text" id="username" name="name" class="weui-input" placeholder="用户名"></div>
	                </div>
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><img src="resources/common/images/btn-login-pw@2x.png" alt="" class="hd-img"></div>
	                    <div class="weui-cell__bd"><input type="password" id="passwords" name="password" class="weui-input" placeholder="密码"></div>
	                    <div class="weui-cell__ft js-bd js-bd2"></div>
	                </div>
	                <input type="hidden" name="type" id="type">
                </form>
            </div>
            <div class="weui-msg__opr-area">
                <p class="weui-btn-area self-btn-area">
                    <a href="javascript:;" class="weui-btn weui-btn_primary" id="login">登录</a>
                </p>
                <div class="container-wrap theme-color"><span class="reg" id="register">新用户注册</span><span class="findBack" class="reg-right" style="float: right;margin-right: 10px;">找回密码</span></div>
            </div>
            <div class="weui-flex self-flex">
                <div class="weui-flex__item">
	                <ul>
	                	<li id="wechat_redirect">
	                		<img src="resources/common/images/btn-login-wx@2x.png" alt="" class="login-icon"><p>微信登录</p>
	                	</li>
	                </ul>
	            </div>
                <div class="weui-flex__item" style="top:50px;"><img id="qqLoginBtn" src="resources/common/images/btn-login-qq@2x.png" alt="" class="login-icon"><p class="login">QQ登录</p></div>
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