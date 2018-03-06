<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>按钮 - 在线演示 - layui</title>
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
		    <li class="layui-this">用户授权</li>
		    <li>添加用户</li>
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
		    	<form class="layui-form" id="myForm" action="sysUser/addSysUserRole">
				  <div class="layui-form-item">
				    <label class="layui-form-label">角色名称</label>
				    <div class="layui-input-block">
				      <input type="text" name="name" class="layui-input">
				    </div>
				  </div>
		  		   <div class="layui-form-item">
				    <label class="layui-form-label">是否启用</label>
				    <div class="layui-input-block">
				      <input type="checkbox" checked="checked" class="chstatus" lay-skin="switch" lay-text="ON|OFF">
				      <input type="hidden" name="status" id="status" name="status"/>
				    </div>
				  </div>
				  <input type="hidden" id="roleids" name="roleids">
		  		</form>
		  		<table class="layui-table" lay-skin="line">
				 <thead>
				   <tr>
				     <th colspan="5">用户角色</th>
				   </tr> 
				 </thead>
				 <tbody>
				 		<tr>
				 		<c:forEach items="${roles }" var="role" varStatus="i">
						 			<td><input type="checkbox" value="${role.id }" class="check"/>${role.name }</td>
				 					<c:if test="${role.id%5==0 }">
				 						</tr>
				 						<tr>
				 					</c:if>
				 		</c:forEach>
				 		<c:forEach items="${td }" var="td">
				 			<td></td>
				 		</c:forEach>
				 		</tr>
				 </tbody>
				</table>
				<button class="layui-btn" id="btn">立即提交</button>
		    </div>
		  </div>
		</div> 
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="sysUser/js/sys_user_list.js"></script>
  <script type="text/javascript">
  </script>
</div>
</body>
</html>