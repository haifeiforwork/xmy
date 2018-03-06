<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>
	
<c:forEach items="${couponList }" var="coupon">
	<c:set var="effectiveStartTime">  
	  <fmt:formatDate value="${coupon.effectiveStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>  
	</c:set>  
	<c:set var="effectiveEndTime">  
	  <fmt:formatDate value="${coupon.effectiveEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>  
	</c:set> 
	<c:if test="${coupon.quota <= salePrice && nowLong >= effectiveStartTime && nowLong <= effectiveEndTime }">
	<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg coupon-div" coupon-id="${coupon.id }" coupon-price="${coupon.couponValue }">
	    <div class="weui-media-box__hd">
	        <div class="money">￥<span>${coupon.couponValue }</span></div>
	        <p>
	        	<c:if test="${coupon.useRange eq 1}">全场通用</c:if>
	        	<c:if test="${coupon.useRange eq 2}">分类使用</c:if>
	        	<c:if test="${coupon.useRange eq 3}">限定商品</c:if>
	        	<c:if test="${coupon.useRange eq 4}">排队商品</c:if>
	        </p>
	    </div>
	    <div class="weui-media-box__bd">
	        <p class="weui-media-box__desc coupon-name">${coupon.name }</p>
	        <div class="times">
	        	<p>
	        		<fmt:formatDate pattern="yyyy.MM.dd" value="${coupon.effectiveStartTime }"/>
	        		-
	        		<fmt:formatDate pattern="yyyy.MM.dd" value="${coupon.effectiveEndTime }"/>
	        	</p>
	        	<p>使用条件:<span>满${coupon.quota }可用</span></p>
	        </div>
	    </div>
	   </a>
	  </c:if>
	</c:forEach>
