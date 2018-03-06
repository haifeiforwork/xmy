<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table"  >
  <colgroup>
   <col width="150">
 </colgroup>
 <thead>
   <tr>
     <th>品牌名称</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${list }" var="item">
    <tr>
	   	 <td><input type="radio" data-id="${item.id }" data-name="${item.name }" name="supplyStrategy" value="1" lay-skin="primary" title="${item.name }"></td>
     </tr>
   </c:forEach>
  </tbody>
</table>