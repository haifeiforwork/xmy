<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>找回密码成功 | 香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" href="resource/commons/css/drag.css">
	<link rel="stylesheet" type="text/css" href="resource/css/home/register.css">
	<style type="text/css">
	.step2{margin: 30px 312px;}
    .step2 .col-30{width: 30%;text-align: right;}
    .step2 .col-30 i{font-size: 60px;color: #359812;}
    .step2 .col-70{width: 68%;float: right;}
    .step2 .col-70 p{margin: 15px;}
    .step2 .col-70 p.first-setent{font-size: 18px;}
    .step2 .col-70 p.first-setent span{color: #359812;}
    .step2 .col-70 p.second-setent{font-size: 14px;color: #999;}
    .step2 .col-70 p.third-setent button{background-color: #F6F6F6;height: 31px;border: 1px solid #dddddd;width: 105px;}
	</style>
  </head>
  
  <body>
  	<%@include file="/WEB-INF/jsp/commons/nav/nav_blank.jsp" %>
  	<div class="ad-img"></div>
 	<div class="container">
	    <div class="register w-1250">
	        <div class="step clearfix  gutter-mar">
	            <div class="col-50"><span><i class="iconfont">&#xe62e;</i></span>手机号验证</div>
	            <div class="col-50 active"><span><i class="iconfont">&#xe62f;</i></span>完成</div>
	        </div>
	    </div>
	    <div class="register clearfix w-1250">
	        <div class="col-50 clearfix step2">
	            <div class="col-30"><i class="iconfont">&#xe623;</i></div>
	            <div class="col-70">
	                <p class="first-setent">恭喜 , <span class="color-green">${name}</span> 找回密码成功 !</p>
	                <p class="second-setent" onclick="window.location='/center/toUpdate?set=5'">完善个人资料 , 让账户更安全 , 用户更放心</p>
	                <p class="third-setent"><button type="button">完善个人资料</button></p>
	            </div>
	        </div>
	    </div>
	</div>
  	<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
	<script type="text/javascript">
	</script>
  </body>
</html>
