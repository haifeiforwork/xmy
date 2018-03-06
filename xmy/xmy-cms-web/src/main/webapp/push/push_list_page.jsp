<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
  	 <!-- <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> -->
     <th>jobName</th>
     <th>triggerName</th>
     <th>推送时间</th>
     <th>推送内容</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${pushs }" var="push">
	   <tr>
	    <%--  <th><input type="checkbox" name="" value="${pcGoods.id }" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> --%>
	     <td>${push.jobName }</td>
	     <td>${push.triggerName }</td>
	     <td>${push.time }</td>
	     <td>${push.extData[msg] }</td>
	     <%-- <td><button class="layui-btn" onclick="del(${pcGoods.goodsId},${pcGoods.type })">删除</button></td> --%>
	   </tr>
   </c:forEach>
 </tbody>
</table>