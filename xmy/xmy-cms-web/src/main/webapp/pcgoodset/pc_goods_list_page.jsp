<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
  	 <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th>
     <th>ID</th>
     <th>热销名称</th>
     <th>商品id</th>
     <th>商品名称</th>
     <th>商品价格</th>
     <th>商品图片</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${pcGoodsList }" var="pcGoods">
	   <tr>
	     <th><input type="checkbox" name="" value="${pcGoods.id }" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th>
	     <td>${pcGoods.id }</td>
	     <td>${pcGoods.name }</td>
	     <td>${pcGoods.goodsId }</td>
	     <td>${pcGoods.goodsName }</td>
	     <td>${pcGoods.goodsPrice }</td>
	     <td>${pcGoods.goodsImage}</td>
	     <td><button class="layui-btn" onclick="del(${pcGoods.goodsId},${pcGoods.type })">删除</button></td>
	   </tr>
   </c:forEach>
 </tbody>
</table>