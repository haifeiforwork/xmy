<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
        <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th>
        <th>ID</th>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>数量</th>
        <th>单位</th>
        <th>单价（元）</th>
        <th>原售价（元）</th>
        <th>供货商</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${orderGoods }" var="goods">
      <tr>
        <td><input type="checkbox"  value="${goods.id }" name="" lay-skin="primary" class="t-checkbox orderid"></td>
      	<td>${goods.id }</td>
      	<td>${goods.code }</td>
      	<td>${goods.name }</td>
      	<td>${goods.sumGoodsNum }</td>
      	<td>${goods.unitName }</td>
      	<td>${goods.price }</td>
      	<td>${goods.costPrice }</td>
      	<td>${goods.supplierName }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

