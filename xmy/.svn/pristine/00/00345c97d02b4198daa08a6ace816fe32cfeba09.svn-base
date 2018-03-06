<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
    	
        <th>通知内容</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>创建人</th>
        <th>创建时间</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${pageBean.data }" var="data">
      <tr>
        <td>${data.content}</td>
        <td><fmt:formatDate value="${data.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td><fmt:formatDate value="${data.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
     	<td>${data.createAuthor}</td>
        <td><fmt:formatDate value="${data.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>
        	
        	<%-- 	<a href="article/notification/edit/${data.id}"><button class="layui-btn">编辑</button></a> --%>
        		<a href="javascript:;" class="layui-btn  layui-btn-danger" onClick="delCheck('content/notification/del/${data.id }')">删除</a>
        	
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
