<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty dealList }">
	<center>暂无相关数据！</center>
</c:if>
<c:if test="${!empty dealList }">
	<table style="width: 80%; border-spacing: 0px;border:1px solid #F0F0F0;">
	  <tr style="background-color: #F0F0F0;font-size: 12px;height:37px;">
	    <th>买家</th>
	    <th>价格</th>
	    <th>数量</th>
	    <th>付款时间</th>
	  </tr>
	  <c:forEach items="${dealList }" var="deal">
		  <tr style="text-align: center;font-size: 12px;height:47px;">
		    <td style="border-bottom:1px solid #F0F0F0;">${deal.paymentName }</td>
		    <td style="border-bottom:1px solid #F0F0F0;color: red;">￥<fmt:formatNumber value="${deal.price }" pattern="#.00"/></td>
		    <td style="border-bottom:1px solid #F0F0F0;">${deal.goodsNum }</td>
		    <td style="border-bottom:1px solid #F0F0F0;"><fmt:formatDate value="${deal.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		  </tr>
	  </c:forEach>
	</table>
	<input type="hidden" value="${pageIndex }" id="pageNo"/>
</c:if> 


