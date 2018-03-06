<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单列表</title>
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
		    <li class="layui-this">菜单编辑</li>
		  </ul>
		  <div class="layui-tab-content" >
		  	<!-- 用户授权 -->
		    <div class="layui-tab-item layui-show">
		    	<button class="layui-btn" data-method="offset" id="btn2" >新增根目录</button>&nbsp;&nbsp;&nbsp;&nbsp;
		    	<button class="layui-btn" id="btn1" data-method="offset">新增子目录</button>
		    	<div id="mytable"></div>
				<div id="mypager"></div>
				<input type="text" id="pageCount" hidden="hidden" value="${countPage }"/>
		    </div>
		</div> 
  	</div>
  	<!-- 隐藏添加菜单的div -->
  	<div id="addMenu" style="display: none">
		<form class="layui-form" action="sys/addMenu">
    		 <div class="layui-form-item">
			    <label class="layui-form-label">名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="name" lay-verify="title" placeholder="请输入描述" autocomplete="off" class="layui-input">
			    </div>
			 </div>
    		<div class="layui-form-item">
			    <label class="layui-form-label">描述</label>
			    <div class="layui-input-block">
			      <input type="text" name="des" autocomplete="off"  placeholder="请输入描述" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">链接</label>
			    <div class="layui-input-block">
			      <input type="text" name="targetUrl" autocomplete="off" placeholder="请输入链接" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">图片</label>
			    <div class="layui-input-block">
			      <input type="file" name="file" class="layui-upload-file"> 
			      <input type="hidden" name="iconUrl" autocomplete="off" id="img"  placeholder="图片" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">显示顺序</label>
			    <div class="layui-input-block">
			      <input type="text" name="seq" lay-verify="seq" autocomplete="off" placeholder="请输入显示顺序" class="layui-input">
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
			     	 <input type="checkbox" checked="checked" name="status" value="1" lay-skin="switch" lay-text="ON|OFF">
			 	 </div>
			 	 <input type="hidden" id="parentid" name="parentId" lay-verify="par"/>
			  <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
    	</form>
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="sys/js/sys_menu_list.js"></script>
</div>
</body>
</html>