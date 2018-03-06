<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!-- 积分商品列表 -->
<div class="clearfix shop-list row4">
    <c:forEach items="${pointList }" var="goods">
    	<div class="col-49 container-white goods-div" goods-id="${goods.goodsId}" points-id="${pointsId }">
	        <img src="${goods.imgPath }" alt="" class="shop-img">
	        <div class="clearfix">
	            <div class="shop-name">${goods.goodsName }</div>
	            <div class="badge">
	                <p>积分</p><p>${goods.points }</p>
	            </div>
	        </div>
	        <p class="shop-list-foot shop-list-foot3"><span class="jf-price">价值 : <span>¥ 
	        <fmt:formatNumber value="${goods.goodsPrice }"  pattern="0.00"/> 
	        </span></span><span><span>${goods.completeNum }</span>人兑换</span></p>
	    </div>
    </c:forEach>
</div>