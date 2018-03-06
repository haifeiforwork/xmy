<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
  	 <th><input type="checkbox" readonly="readonly"/></th>
     <th>ID</th>
     <th>菜单名称</th>
     <th>菜单编号</th>
     <th>菜单描述</th>
     <th>菜单链接</th>
     <th>菜单图片路径</th>
     <th>显示顺序</th>
     <th>类型</th>
     <th>状态</th>
     <th>创建人</th>
     <th>创建时间</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${menuList }" var="menu">
	   <tr>
	   	 <td><input type="checkbox"  value="${menu.id }"  lay-skin="primary" class="t-checkbox"></td>
	     <td>${menu.id }</td>
	     <td>${menu.name }</td>
	     <td>${menu.sn }</td>
	     <td>${menu.des }</td>
	     <td>${menu.targetUrl }</td>
	     <td>${menu.iconUrl }</td>
	     <td>${menu.seq }</td>
	     <td>
	     	<c:if test="${menu.type==0 }">菜单</c:if>
	     	<c:if test="${menu.type==1 }">按钮</c:if>
	     </td>
	     <td>
	     	<c:if test="${menu.status==0 }"><i class="layui-icon" style="font-size: 14px; color: #009688;">&#xe605;</i> </c:if>
	     	<c:if test="${menu.status==1 }"><i class="layui-icon" style="font-size: 14px; color: #dddddd;">&#x1006;</i></c:if>
	     </td>
	     <td>${menu.creator }</td>
	     <td><fmt:formatDate value="${menu.createTime }" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
	     <td>
	     	<a href="sysUser/getMenu/${menu.id }"><button class="layui-btn">编辑</button></a>&nbsp;&nbsp;&nbsp;
			<button class="layui-btn" onclick="del(${menu.id})">删除</button>
	     </td>
	   </tr>
   </c:forEach>
 </tbody>
</table>