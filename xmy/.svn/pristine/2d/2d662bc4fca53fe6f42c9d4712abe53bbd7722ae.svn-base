<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
  	 <!-- <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> -->

     <th>推送标签</th>
     <th>创建时间</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${labels }" var="label">
	   <tr>
	    <%--  <th><input type="checkbox" name="" value="${pcGoods.id }" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> --%>

	     <td>${label.label }</td>
	     <td><fmt:formatDate value="${label.createTimestamp }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td>
			　<button class="layui-btn push-label-modify-btn" data-labelid=${label.id } data-labelname=${label.label } lay-submit=""　lay-filter="" >修改</button>
	     　　　　<button class="layui-btn push-label-del-btn" data-labelid=${label.id }　>删除</button>
	     </td>
	   </tr>
   </c:forEach>
 </tbody>
</table>