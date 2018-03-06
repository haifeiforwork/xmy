<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>推送标签管理</title>
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
  	    <i class="layui-icon" style="font-size: 20px; color: #009688;">推送标签管理</i>
		<hr>
		<div class="layui-input-inline">
			<button class="layui-btn" id="add_label" lay-submit=""
				lay-filter="demo2">添加标签</button>
		</div>
		<!-- <div class="layui-input-inline">
			<button class="layui-btn" id="btn-push-now" lay-submit=""
				lay-filter="demo2">立即推送</button>
		</div> -->
		<div class="layui-tab-item layui-show"></div>
		<!-- 数据 -->
		<div id="mytable" class="layui-form">
		</div>
		<div id="mypager"></div>
		<input type="text" id="pageCount" hidden="hidden"  value="${countPage }"/>			
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="push/js/push_label_list.js"></script>
</body>
</html>