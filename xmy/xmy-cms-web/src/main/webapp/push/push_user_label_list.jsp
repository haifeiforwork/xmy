<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>推送用户标签管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
.layui-input-block{width: 200px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief layui-form" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  	    <i class="layui-icon" style="font-size: 20px; color: #009688;">推送用户标签管理</i>
		<hr>
		<!-- <div class="layui-input-inline">
			<button class="layui-btn" id="add_label" lay-submit=""
				lay-filter="demo2">添加标签</button>
		</div> -->
		<!-- <div class="layui-input-inline">
			<button class="layui-btn" id="btn-push-now" lay-submit=""
				lay-filter="demo2">立即推送</button>
		</div> -->
		
		
		<div class="layui-tab-item layui-show">
		    <form id="myForm">
				   <div class="layui-input-inline">
				   		<label class="layui-form-label" style="width:120px;text-align:left;left: 10px;">请输入会员账号:</label>
				   		 <div class="layui-input-block" style="margin-left: 150px;">
					      	<input type="text" name="name" class="q-rule layui-input " value="${params.name}"  zfj-query="name:cn" style="width:180px;"   lay-verify="required" autocomplete="off" >
					     </div>  	
				   </div>
				   <div class="layui-input-inline" style="margin-left: 120px;">
				   		<label class="layui-form-label" style="width:50px;text-align:left;left: 10px;">手机号:</label>
				   		 <div class="layui-input-block" style="margin-left: 80px;">
					      	<input type="text" name="mobilePhone" class="q-rule layui-input " value="${params.mobilePhone}"  zfj-query="mobile_phone:cn" style="width:180px;"   lay-verify="required" autocomplete="off" >
					     </div>  	
				   </div>
				    <div class="layui-input-inline" style="width:200px;left:150px;">
				    	<input type="submit" class="layui-btn btn-submit" id="btn_submit" value="查询"  />
						<input type="button" class="layui-btn btn-reset" id="reset" value="重置"  />
				    </div>
				</form> 
		</div>
		<!-- 数据 -->
		<div id="mytable" class="layui-form">
		</div>
		<div id="mypager"></div>
		<input type="text" id="pageCount" hidden="hidden"  value="${countPage }"/>			
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
<!--   <script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">layui.config({base:"commons/layui/lay/modules/"}).use("global");</script> -->
  <script type="text/javascript" src="push/js/push_user_label_list.js"></script>

</body>
</html>