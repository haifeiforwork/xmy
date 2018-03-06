<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<title>菜单编辑</title>
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
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this">编辑</li>
		  </ul>
		  <div class="layui-tab-content" >
		  	<!-- 用户授权 -->
		    <div class="layui-tab-item layui-show">
		    	<form class="layui-form" action="sysUser/updateMenu">
		    	
		    		 <div class="layui-form-item">
					    <label class="layui-form-label">名称</label>
					    <div class="layui-input-block">
					      <input type="text" name="name" lay-verify="title" autocomplete="off" value="${sysMenu.name }" placeholder="请输入名称" class="layui-input">
					    </div>
					 </div>
					 <input type="hidden" name="id" value="${sysMenu.id }">
					 <input type="hidden" id="pid" value="${sysMenu.parentId }">
		    		<div class="layui-form-item">
					    <label class="layui-form-label">描述</label>
					    <div class="layui-input-block">
					      <input type="text" name="des" lay-verify="title" autocomplete="off" value="${sysMenu.des }" placeholder="请输入描述" class="layui-input">
					    </div>
					 </div>
					 
					 <div class="layui-form-item">
					    <label class="layui-form-label">链接</label>
					    <div class="layui-input-block">
					      <input type="text" name="targetUrl" lay-verify="title" autocomplete="off" value="${sysMenu.targetUrl }" placeholder="请输入链接" class="layui-input">
					    </div>
					 </div>
					 
					 <div class="layui-form-item">
					    <label class="layui-form-label">图片</label>
					    <div class="layui-input-block">
					      <input type="text" name="iconUrl" lay-verify="title" autocomplete="off" value="${sysMenu.iconUrl }" placeholder="图片" class="layui-input">
					    </div>
					 </div>
					 
					 <div class="layui-form-item">
					    <label class="layui-form-label">显示顺序</label>
					    <div class="layui-input-block">
					      <input type="text" name="seq" lay-verify="title" autocomplete="off" value="${sysMenu.seq }" placeholder="请输入显示顺序" class="layui-input">
					    </div>
					 </div>
					 
					<div class="layui-form-item">
					    <label class="layui-form-label">类型</label>
					    <div class="layui-input-block">
					      <select name="type" lay-filter="aihao">
					        <option value=""></option>
					        <option value="0" <c:if test="${sysMenu.type == 0 }">selected=""</c:if> >菜单</option>
					        <option value="1" <c:if test="${sysMenu.type == 1 }">selected=""</c:if> >按钮</option>
					      </select>
					    </div>
					  </div>
					 
					  <div class="layui-form-item">
					    <label class="layui-form-label">状态</label>
					    <div class="layui-input-block">
					     	<c:if test="${sysMenu.status==0 }">
					     	 <input type="checkbox" checked="checked" name="status" value="1" lay-skin="switch" lay-text="ON|OFF">
					      	</c:if>
					      	<c:if test="${sysMenu.status==1 }">
					     	 <input type="checkbox" value="1" name="status" lay-skin="switch" lay-text="ON|OFF">
					      	</c:if>
					    </div>
					  </div>
					  <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
		    	</form>
		    </div>
		  </div>
		</div> 
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="sysUser/js/menu_detail.js"></script>
</div>
</body>
</html>