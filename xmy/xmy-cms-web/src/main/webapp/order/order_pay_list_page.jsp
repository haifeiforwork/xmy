<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
        <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th>
        <th>ID</th>
        <th>订单号</th>
        <th>用户账号</th>
        <th>下单时间</th>
        <th>订单状态</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${pageBean.data }" var="data">
      <tr>
        <td><input type="checkbox"  value="${data.id }" name="" lay-skin="primary" class="t-checkbox orderid"></td>
        <td>${data.id }</td>
        <td>${data.no }</td>
        <td>${data.paymentName }</td>
        <td><fmt:formatDate value="${data.createTime }" type="date" dateStyle="full"/></td>
        <td>
        	<c:if test="${data.shipStatus==1 }"><i class="layui-icon" style="font-size: 16px; color: #009688;">●待审核</i> </c:if>
        	<c:if test="${data.shipStatus==2 }"><i class="layui-icon" style="font-size: 16px; color: #009688;">●待支付</i> </c:if>
        	<c:if test="${data.shipStatus==3 }">供货确认</c:if>
        	<c:if test="${data.shipStatus==4 }">备货</c:if>
        	<c:if test="${data.shipStatus==5 }">待发货</c:if>
        	<c:if test="${data.shipStatus==6 }"><i class="layui-icon" style="font-size: 16px; color: #FF5722;">●已发货</i></c:if>
        	<c:if test="${data.shipStatus==7 }"><i class="layui-icon" style="font-size: 16px; color: #c2c2c2;">●已完成</i></c:if>
        </td>
        <td style="text-align: center;">
        	<%-- <a href="order/spend/${data.id }"><button class="layui-btn">个人账户</button></a> --%>
	        <a href="order/userShoppingCard/${data.id }"><button class="layui-btn">卡号明细</button></a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

