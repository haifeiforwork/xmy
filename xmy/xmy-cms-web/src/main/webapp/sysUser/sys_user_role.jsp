<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<title>用户角色编辑</title>
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
		    <li class="layui-this">角色编辑</li>
		  </ul>
		  <div class="layui-tab-content" >
		  	<!-- 用户授权 -->
		    <div class="layui-tab-item layui-show">
			    <form class="layui-form" id="myForm" action="sysUser/updateUserRole">
				  <div class="layui-form-item">
				    <label class="layui-form-label">角色名称</label>
				    <div class="layui-input-block">
				      <input type="text" name="name" value="${userRole.name }" class="layui-input">
				      <!-- 隐藏的用户ID -->
				      <input type="hidden" name="id" value="${userRole.id }">
				      <!-- 用于回现，遍历用户的所有角色 -->
				      <c:forEach items="${userRole.roles }" var="rol">
				     	 <input type="hidden" value="${rol.id }" class="roleId">
				      </c:forEach>
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">角色标识</label>
				    <div class="layui-input-block">
				      <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
				    </div>
		  		  </div>
		  		   <div class="layui-form-item">
				    <label class="layui-form-label">是否启用</label>
				    <div class="layui-input-block">
				    	<c:if test="${userRole.status==0 }">
				     	 <input type="checkbox" checked="checked" class="chstatus" lay-skin="switch" lay-text="ON|OFF">
				      	</c:if>
				      	<c:if test="${userRole.status==1 }">
				     	 <input type="checkbox"  lay-skin="switch" class="chstatus" lay-text="ON|OFF">
				      	</c:if>
				      	<c:if test="${userRole.status==99 }">
				     	 <input type="checkbox"  lay-skin="switch" class="chstatus" lay-text="ON|OFF">
				      	</c:if>
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
				 					<c:if test="${i.count%5 == 0 }">
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
  <script type="text/javascript" src="sysUser/js/sys_user_role.js"></script>
</div>
</body>
</html>