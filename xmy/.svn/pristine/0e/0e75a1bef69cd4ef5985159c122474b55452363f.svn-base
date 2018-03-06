<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
  	 <!-- <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> -->
     <th>推送方式</th>
     <th>推送用户</th>
     <th>推送标签</th>
     <th>推送内容</th>
     <th>推送时间</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${records }" var="record">
	   <tr>
	    <%--  <th><input type="checkbox" name="" value="${pcGoods.id }" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> --%>
	     <td>
	         <c:if test="${record.type eq 0 }">
	                        广播
	         </c:if>
	         <c:if test="${record.type eq 1 }">
	                        按标签推送
	         </c:if>
	         <c:if test="${record.type eq 2 }">
	                         单点推送
	         </c:if>
	     </td>
	     <td>
	         ${record.username }
	     </td>
	     <td>
	         <c:if test="${not empty record.label }">
	             <button class="layui-btn layui-btn-mini "  >${record.label }</button>
	         </c:if>
	     </td>
	     <td>${record.content }</td>
	     <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${record.createTimestamp }"></fmt:formatDate></td>
	     <%-- <td><button class="layui-btn" onclick="del(${pcGoods.goodsId},${pcGoods.type })">删除</button></td> --%>
	   </tr>
   </c:forEach>
 </tbody>
</table>