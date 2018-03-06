<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty shoppingCart }">
	<li class="cart-li">购物车还是空的!!</li>
</c:if>
<c:if test="${!empty shoppingCart }">
	<c:forEach items="${shoppingCart }" var="cartGoods">
		<div class="zui-news">
			<div class="commodity">
				<div class="ly-75">
					<div class="sp-img"><img src="${cartGoods.imgPath }"/></div>
				</div>
				<div class="ly-155">${cartGoods.name }</div>
				<div class="ly-80">
					<p>
					<c:if test="${empty cartGoods.actId }">
						<b class="red"><c:if test="${empty cartGoods.activityPrice }">${cartGoods.price }</c:if><c:if test="${!empty cartGoods.activityPrice }">${cartGoods.activityPrice }</c:if></b>
					</c:if>
					<c:if test="${!empty cartGoods.actId }">
						<b class="red">${cartGoods.actPoints }</b>积分
					</c:if>
					×${cartGoods.cartNum }</p>
					<p class="del"><a href="javascript:void(0);" onclick="deleteCartGoods(${cartGoods.id })" style="color: #388913;">删除</a></p>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class="js">
		<p class="jisuan">共 <span>${countCart }</span> 件 共计 <span class="da">¥${sumPrice }</span></p>
		<p class="mt10"><a href="cart/shoppingCart" style="cursor: pointer;"><button type="button" class="btn-small">去购物车结算</button></a></p>
	</div>
</c:if>