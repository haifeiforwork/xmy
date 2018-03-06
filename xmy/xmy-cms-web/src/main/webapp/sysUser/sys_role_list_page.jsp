<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
     <th>ID</th>
     <th>角色名称</th>
     <th>角色标识</th>
     <th>角色描述</th>
     <th>状态</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${roles }" var="role">
	   <tr>
	     <td>${role.id }</td>
	     <td>${role.name }</td>
	     <td>${role.mark }</td>
	     <td>${role.des }</td>
	     <td>
	     	<c:if test="${role.status==0 }"><i class="layui-icon" style="font-size: 14px; color: #009688;">&#xe605;</i> </c:if>
	     	<c:if test="${role.status==1 }"><i class="layui-icon" style="font-size: 14px; color: #dddddd;">&#x1006;</i></c:if>
	     </td>
	     <td>
	     	<a href="sysUser/getRoleMenu/${role.id }"><button class="layui-btn">编辑</button></a>&nbsp;&nbsp;&nbsp;
			<button onclick="del(${role.id})" class="layui-btn">删除</button>
	     </td>
	   </tr>
	</c:forEach>
 </tbody>
</table>