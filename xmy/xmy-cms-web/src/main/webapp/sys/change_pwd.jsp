<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎登录页面</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
		 <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			  <ul class="layui-tab-title">
			    <li class="layui-this">修改密码</li>
			  </ul>
			  <div class="layui-tab-content" >
				  	<form class="layui-form" action="sys/updatePwd">
					  	<div class="layui-form-item">
						    <label class="layui-form-label">初始密码</label>
						    <div class="layui-input-inline">
						      <input type="password" name="oldpwd" required lay-verify="required" placeholder="请输入初始密码" autocomplete="off" class="layui-input">
						    </div>
						</div>
						<div class="layui-form-item">
						    <label class="layui-form-label">新密码</label>
						    <div class="layui-input-inline">
						      <input type="password" name="newpwd" id="pwd" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
						    </div>
						</div>
						<div class="layui-form-item">
						    <label class="layui-form-label">确认新密码</label>
						    <div class="layui-input-inline">
						      <input type="password" name="okpwd" lay-verify="ok" placeholder="再次请输入新密码" autocomplete="off" class="layui-input">
						    </div>
						</div>
						<br><br><br>
						<div class="layui-form-item" style="margin-left: 80px;">
						 <button class="layui-btn" lay-submit lay-filter="formDemo">确认提交</button>
						</div>
				  	</form>
			  </div>
		  </div>
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="sys/js/change_pwd.js"></script>
</div>
</body>
</html>