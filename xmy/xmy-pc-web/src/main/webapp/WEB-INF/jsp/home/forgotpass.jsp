<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>找回密码 | 香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" href="resource/commons/css/drag.css">
	<link rel="stylesheet" type="text/css" href="resource/css/home/register.css">
	<style type="text/css">
	.grey{color:#999;}
	.form-group{height:auto;}
	 label.error ,label.ext-error{color: red;text-align:left;width:100%;}
	 
	</style>
  </head>
  
  <body>
  	<%@include file="/WEB-INF/jsp/commons/nav/nav_blank.jsp" %>
  	<div class="ad-img"></div>
 	<div class="container">
	    <!--第一步-->
	    <div class="register w-1250">
	        <div class="step clearfix  gutter-mar">
	            <div class="col-50 active"><span><i class="iconfont">&#xe62e;</i></span>填写信息</div>
	            <div class="col-50"><span><i class="iconfont">&#xe62f;</i></span>完成</div>
	        </div>
	    </div>
	    <form method="post" id="myform">
	    <div class="register clearfix w-1250">
	       
	        <div class="form-group col-50 clearfix">
	            <label for="mobile_phone">手机号码</label><div class="wrap-input clearfix"><input type="text" placeholder="请输入手机号码" name="mobilePhone" id="mobile_phone" required></div>
	        </div>
	        <div class="form-group col-50 clearfix">
	            <label for="mobile_code">手机验证码</label><div class="wrap-input clearfix"><input type="text" placeholder="请输入验证码" name="mobileCode" id="mobile_code"><button type="button" class="btn-get">获取验证码</button></div>
	        </div>
	         <div class="form-group col-50 clearfix">
	            <label for="password">新密码</label><div class="wrap-input clearfix"><input type="password" placeholder="请输入登录密码" name="password" id="password" required></div>
	        </div>
	        <div class="form-group col-50 clearfix">
	            <label for="again_password">确认新密码</label><div class="wrap-input clearfix"><input type="password" placeholder="请确认密码" name="againPassword" id="again_password" required></div>
	        </div>
	        <div class="form-group col-50 clearfix">
	            <label>验证</label><div class="wrap-input clearfix"><div id="drag"></div><label class="ext-error" id="v_drag"></label></div>
	        </div>
	        <div class="form-group col-50 clearfix">
	            <div class="wrap-input wp-add"><input type="checkbox" class="checkbox" id="agree"><label for="agree">同意</label><a href="#">《香满圆服务协议》</a></div>
	        </div>
	        <div class="form-group col-50 clearfix">
	            <div class="wrap-input wp-add"><button type="button" class="toregister" id="register">找回密码</button></div>
	        </div>
	    </div>
	    <input type="hidden" name="token" value="${token}" />
	    </form>
	    <!--第二步-->
	</div>
	<div class="modal check-coded">
	    <div class="over-lay"></div>
	    <div class="modal-content">
	        <div class="modal-title clearfix"><span>验证码</span><span class="close" style="cursor:pointer;"><i class="iconfont">&#xe676;</i></span></div>
	        <div class="check-check"><div id="img_code" style="height:35px;width:122px;"><img src="user/imagecode" style="width:120px;border:1px solid #555;"/></div><span class="trans">换一个</span></div>
	        <div class="input-group clearfix">
	            <div class="label label5">验证码 </div>
	            <div class="item-input item-input5"><input type="text" class="default" id="vcode"></div>
	        </div>
	        <div class="btn-group add-distance6">
	            <button type="button" class="btn-theme4 btn-default" id="code_confirm">确定</button>
	        </div>
	    </div>
	</div>
  	<%@include file="/WEB-INF/jsp/commons/comm_buttom_noright.jsp" %>
  	<script type="text/javascript" src="resource/commons/js/drag.js"></script>
  	<script type="text/javascript" src="resource/commons/js/jquery.validate.min.js"></script>
  	<script type="text/javascript" src="resource/commons/js/additional-methods.min.js"></script>
  	<script type="text/javascript" src="resource/commons/js/jquery-form.js"></script>
	<script type="text/javascript" src="resource/js/home/forgotpass.js"></script>
  </body>
</html>
