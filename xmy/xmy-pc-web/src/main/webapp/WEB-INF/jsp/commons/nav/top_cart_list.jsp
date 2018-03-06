<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty shoppingCart }">
<div class="details-list">
    <div class="list-tips">购物车还是空的</div>
</div>
</c:if>
<c:if test="${!empty shoppingCart }">
<div class="shop-list">
    <ul>
    	<c:forEach items="${shoppingCart }" var="cartGoods">
     <li class="clearfix">
         <div class="col-20"><img src="${cartGoods.imgPath }" alt=""></div>
         <div class="col-50">
	         <c:if test="${cartGoods.actPoints==0 }">
	         	<a href="goods/${cartGoods.id}/0/0">${cartGoods.name }</a>
	         </c:if>
	         <c:if test="${cartGoods.actPoints!=0 }">
	         	<a href="point/${cartGoods.id}/0/0?actId=${cartGoods.actId}">${cartGoods.name }</a>
	         </c:if>
         </div>
         <div class="col-30">
             <p class="item-des">
             <c:if test="${empty cartGoods.actId }">
             	<span class="price">¥<c:if test="${empty cartGoods.activityPrice }">${cartGoods.price }</c:if><c:if test="${!empty cartGoods.activityPrice }">${cartGoods.activityPrice }</c:if></span> x <span class="num">${cartGoods.cartNum }</span>
             </c:if>
             <c:if test="${!empty cartGoods.actId }">
             	<span class="price">${cartGoods.actPoints }积分</span> x <span class="num">${cartGoods.cartNum }</span>
             </c:if>
             </p>
             <p class="item-delect"><a href="javascript:void(0);" onclick="deleteCartGoods(${cartGoods.id })">删除</a></p>
         </div>
     </li>
     </c:forEach>
 </ul>
 <div class="list-settlement">
     <p class="settlement">共 <span class="price">${countCart }</span> 件 共计 <span class="price lg-price"> ¥${sumPrice }</span></p>
        <p><a href="cart/shoppingCart"><button type="button" class="btn-set">去购物车结算</button></a></p>
    </div>
</div>
</c:if>
 

