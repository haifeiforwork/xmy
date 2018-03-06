<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<c:if test="${fn:length(couponList) <= 0 }">
	<div style="text-align:center;padding:2rem 0;color:#999;font-size:0.75rem;">
		<img alt="" src="resources/common/images/empty.png" style="margin:0 0 0.5rem;width: 3rem;"/>
		<c:if test="${couponSearchInDto.useType == 0 }"><p>没有可使用的优惠券</p></c:if>
		<c:if test="${couponSearchInDto.useType == 1  }"><p>没有使用记录</p></c:if>
		<c:if test="${couponSearchInDto.useType == 2 }"><p>没有过期的优惠券</p></c:if>
	</div>
</c:if>
<c:if test="${fn:length(couponList) > 0 }">
<div class="weui-panel weui-panel_access">
	<div class="weui-panel__bd">
		<c:forEach items="${couponList }" var="coupon">
			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
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
			        <p class="weui-media-box__desc">${coupon.name }</p>
			        <div class="time">
			        	<p>
			        		<fmt:formatDate pattern="yyyy.MM.dd" value="${coupon.effectiveStartTime }"/>
			        		-
			        		<fmt:formatDate pattern="yyyy.MM.dd" value="${coupon.effectiveEndTime }"/>
			        	</p>
			        	<p>使用条件:<span>满${coupon.quota }可用</span></p>
			        </div>
			    </div>
		    </a>
		</c:forEach>
	</div>
</div>
</c:if>