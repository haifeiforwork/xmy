<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>角色编辑</title>
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
		    <li class="layui-this">角色授权</li>
		    <li>添加角色</li>
		  </ul>
		  <div class="layui-tab-content" >
		  	<!-- 用户授权 -->
		    <div class="layui-tab-item layui-show">
		    	<div id="mytable"></div>
				<div id="mypager"></div>
				<input type="text" id="page"  value="${countPage }"/>
		    </div>
		    <!-- 添加用户 -->
		    <div class="layui-tab-item">
		    	<form class="layui-form" id="myForm" action="sysUsre/addRoleMenus">
				  <div class="layui-form-item">
				    <label class="layui-form-label">角色名称</label>
				    <div class="layui-input-block">
				      <input type="text" name="name"  id="name" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">角色标识</label>
				    <div class="layui-input-block">
				      <input type="text" name="mark" id="mark" class="layui-input">
				    </div>
		  		  </div>
		  		   <div class="layui-form-item">
				    <label class="layui-form-label">是否启用</label>
				    <div class="layui-input-block">
				     	 <input type="checkbox" checked="checked" class="chstatus" lay-skin="switch" lay-text="ON|OFF">
				      	<input type="hidden" name="status" id="status" name="status"/>
				    </div>
				  </div>
				  <input type="hidden" id="menusid" name="menusid">
				  <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">角色备注</label>
				    <div class="layui-input-block">
				      <textarea placeholder="请输入内容" class="layui-textarea" name="des"></textarea>
				    </div>
				  </div>
		  		</form>
		  		<table class="layui-table" lay-skin="line">
				 <thead>
				   <tr>
				     <th colspan="5">角色菜单</th>
				   </tr> 
				 </thead>
				 <tbody>
				 		<c:forEach items="${sysMenus }" var="menu" varStatus="i">
				 			<c:if test="${!empty menu.childMenu}">
				 				<tr>
				 				<td colspan="5"><input type="checkbox" value="${menu.id }" />
				 				<i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">${menu.name }</i> 
				 				</td>
				 				</tr>
				 				<tr>
				 				<c:forEach items="${menu.childMenu }" var="men">
				 					<td><input type="checkbox" value="${men.id }" class="menusn"/>${men.name }</td>
				 				</c:forEach>
				 				</tr>
				 			</c:if>
				 		</c:forEach>
				 </tbody>
				</table>
				<button class="layui-btn" id="btn" lay-filter="demo2" >立即提交</button>
		    </div>
		  </div>
		</div> 
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="sysUser/js/sys_role_list.js"></script>
</div>
</body>
</html>