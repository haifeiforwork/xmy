<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!-- 商品列表展示页面 -->
<c:forEach items="${goodsList }" var="goods">
	<div class="weui-media-box weui-media-box_appmsg goods-div" goods-id="${goods.id }">
	    <div class="weui-media-box__hd">
	        <img class="weui-media-box__thumb" src="${goods.imgPath }">
	    </div>
	    <div class="weui-media-box__bd">
	        <p class="weui-media-box__desc">${goods.name }</p>
	        <p class="text-right price">¥ ${goods.price }</p>
	        <p class="sale-say">
	            <span class="sale">累计成交<span> ${empty goods.sumDeal?'0':goods.sumDeal } </span>笔</span> <span>评价 <span>${empty goods.sumComment?'0':goods.sumComment }</span></span>
	            <img src="resources/common/images/btn-tjgwc@2x.png" alt="">
	        </p>
	    </div>
	</div>
</c:forEach>
<input type="hidden" id="pageIndex" value="${pageNum }">
