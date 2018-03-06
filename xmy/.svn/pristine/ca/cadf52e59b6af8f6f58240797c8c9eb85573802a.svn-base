<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>ID</th>
      <th>名称</th>
    </tr> 
  </thead>
  <tbody>
  	<c:forEach items="${dList}" var="data">
  	<tr>
      <td>${data.id}</td>
      <td>${data.name}</td>
    </tr>
  	</c:forEach>
  </tbody>
</table>