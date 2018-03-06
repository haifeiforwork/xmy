<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
     <th>ID</th>
     <th>用户名称</th>
     <th>角色</th>
     <th>状态</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${user }" var="user">
	   <tr>
	    <td>${user.id }</td>
	     <td>${user.name }</td>
	     <td>
	     	<c:if test="${user.roles!=null }">
	     		<c:forEach items="${user.roles }" var="roles">
	     			${roles.name },
	     		</c:forEach>
	     	</c:if>
	     </td>
	     <td>
	     	<c:if test="${user.status==0 }"><i class="layui-icon" style="font-size: 14px; color: #009688;">&#xe605;</i> </c:if>
	     	<c:if test="${user.status==1 }"><i class="layui-icon" style="font-size: 14px; color: #dddddd;">&#x1006;</i></c:if>
	     	<c:if test="${user.status==99 }"><i class="layui-icon" style="font-size: 14px; color: #dddddd;">已删除</i></c:if>
	     </td>
	     <td>
	     	<a href="sysUser/getUserRole/${user.id }"><button class="layui-btn">编辑</button></a>&nbsp;&nbsp;&nbsp;
			<button class="layui-btn" onclick="delet(${user.id})" class="delete" value="${user.id }">删除</button>
	     </td>
	   </tr>
	</c:forEach>
 </tbody>
</table>
