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
        <th>支付方式</th>
        <th>配送方式</th>
        <th>订单应付总额（元）</th>
        <th>下单时间</th>
        <th>收货人/电话</th>
        <th>收货地址</th>
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
        <td>
        	<c:if test="${data.payType==1 }">支付宝</c:if>
        	<c:if test="${data.payType==2 }">微信</c:if>
        	<c:if test="${data.payType==3 }">银联</c:if>
        	<c:if test="${data.payType==4 }">购物卡</c:if>
        	<c:if test="${data.payType==5 }">货到付款</c:if>
        	<c:if test="${data.payType==6 }">混合支付</c:if>
        </td>
        <td>
        	<c:if test="${data.deliveryMethod==1 }">平台配送</c:if>
        	<c:if test="${data.deliveryMethod==0 }">买家自提</c:if>
        </td>
        <td>${data.total }元</td>
        <td><fmt:formatDate value="${data.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${data.consigneeName }/<br>${data.consigneePhone }</td>
        <td>${data.consigneeAddress }</td>
        <td>
        	<c:if test="${data.shipStatus==1 }"><i class="layui-icon" style="font-size: 16px; color: #009688;">●待审核</i> </c:if>
        	<c:if test="${data.shipStatus==2 }"><i class="layui-icon" style="font-size: 16px; color: #009688;">●待支付</i> </c:if>
        	<c:if test="${data.shipStatus==3 }">供货确认</c:if>
        	<c:if test="${data.shipStatus==4 }">备货中</c:if>
        	<c:if test="${data.shipStatus==5 }">待发货</c:if>
        	<c:if test="${data.shipStatus==6 }"><i class="layui-icon" style="font-size: 16px; color: #FF5722;">●已发货</i></c:if>
        	<c:if test="${data.shipStatus==7 }"><i class="layui-icon" style="font-size: 16px; color: #c2c2c2;">●已完成</i></c:if>
        </td>
        <td>
	        <a href="order/detail/${data.id }"><button class="layui-btn">作废</button></a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

