<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>  
<title>管理用户登陆</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
body{background-color:#c2c2c2 }
.big{margin-top: 10%;background-color: #fbfbfb;width: 30%;height: 600px}
.layui-form-item{width: 350px}
.layui-btn{width: 350px}
p{margin-left: 300px};
</style>
</head>
<body>
	<center>
		<div class="big">
			<div>我是logo图片<br><br><br><br><br><br><br><br><br><br></div>
			<div><i class="layui-icon" style="font-size: 30px; color: #393D49;">香满圆B2C商城管理平台</i></div>
			<br>
			<form class="layui-form" action="login">
			  <div class="layui-form-item">
			      <input type="text" name="username" lay-verify="name" autocomplete="off" placeholder="请输入用户名" class="layui-input">
			  </div>
			  <div class="layui-form-item">
			      <input type="password" name="pwd" lay-verify="password" autocomplete="off" placeholder="请输入密码" class="layui-input">
			  </div>
			  <button class="layui-btn" lay-submit="" lay-filter="demo1">登陆</button>
			  <br><br>
			  
			  <p>忘记密码？</p>
			  
			  </form>
		</div>
	</center>
	
	 <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
	 		<%@ include file="/commons/buttom.jsp"%>
			<script type="text/javascript" src="sysUser/js/sysUserLogin.js"></script>
	 </div>
</body>
</html>