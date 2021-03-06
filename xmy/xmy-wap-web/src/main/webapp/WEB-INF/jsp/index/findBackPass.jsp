<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>
<!--制作lj-->
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
     <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/index/css/register.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<div class="content">
    <div class="container container-white">
        <div class="weui-msg">
            <div class="weui-msg__icon-area logo-area"><img src="resources/common/images/img-login-logo@3x.png" alt=""></div>
            <div class="weui-cells self-cell weui-cells_form weui-cells_checkbox">
            	<form action="index/register" method="post" id="register_form">
		              <div class="weui-cell">
		                  <div class="weui-cell__hd"><img src="resources/common/images/btn-login-phone@2x.png" alt="" class="hd-img"></div>
		                  <div class="weui-cell__bd"><input type="text" class="weui-input" placeholder="手机号" name="name" id="phone"></div>
		                  <div class="weui-cell__ft get-code"><button type="button" id="get_code">获取验证</button></div>
		              </div>
		              <div class="weui-cell">
		                  <div class="weui-cell__hd"><img src="resources/common/images/btn-reg-yzm@2x.png" alt="" class="hd-img"></div>
		                  <div class="weui-cell__bd"><input type="text" class="weui-input" id="code" name="code" placeholder="请输入验证码"></div>
		              </div>
		              <div class="weui-cell">
		                  <div class="weui-cell__hd"><img src="resources/common/images/btn-login-pw@2x.png" alt="" class="hd-img"></div>
		                  <div class="weui-cell__bd"><input type="password" class="weui-input" id="password" placeholder="请输入密码"></div>
		                  <div class="weui-cell__ft js-bd js-bd2"></div>
		              </div>
		              <div class="weui-cell">
		                  <div class="weui-cell__hd"><img src="resources/common/images/btn-login-pw@2x.png" alt="" class="hd-img"></div>
		                  <div class="weui-cell__bd"><input type="password" class="weui-input" id="con_password" name="password" placeholder="请确认密码"></div>
		                  <div class="weui-cell__ft js-bd js-bd2"></div>
		              </div>
		              <label class="weui-cell weui-check__label" for="s11">
		                  <div class="weui-cell__hd">
		                      <input type="checkbox" class="weui-check" id="agree" name="checkbox1" id="s11" checked="checked">
		                      <i class="weui-icon-checked"></i>
		                  </div>
		                  <div class="weui-cell__bd theme-color">
		                      <p class="text-left">同意《香满圆用户注册条款》</p>
		                  </div>
		              </label>
                </form>
            </div>
            <div class="weui-msg__opr-area">
                <p class="weui-btn-area self-btn-area">
                    <a href="javascript:;" class="weui-btn weui-btn_primary" id="register">注册</a>
                </p>
                <div class="container-wrap theme-color" id="login"><span>直接登录</span></div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<script type="text/javascript" src="resources/index/js/register.js"></script>
</body>
</html>
