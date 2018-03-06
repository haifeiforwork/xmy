<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:forEach items="${couponList }" var="coupon">
	<li class="bimmg-li add">
		<p class="item-word clearfix">
			<span class="num" value="${coupon.couponValue }"
				quota="${coupon.quota }">¥ ${coupon.couponValue }</span> <span
				class="fw"> <c:if test="${coupon.useRange==1 }">全场通用</c:if> <c:if
					test="${coupon.useRange==2 }">分类使用</c:if> <c:if
					test="${coupon.useRange==3 }">限定商品</c:if> <c:if
					test="${coupon.useRange==4 }">排除商品</c:if>
			</span> <input type="hidden" name="couponid" value="${coupon.id }" />
			<input type="hidden" name="isUse" value="${coupon.status }">
		</p>
		<p class="item-des">
			使用条件 :
			<c:if test="${coupon.quota==0 }">不限金额</c:if>
			<c:if test="${coupon.quota!=0 }">消费满${coupon.quota }元有效</c:if>
		</p>
		<p class="item-des">
			有限时间 :
			<fmt:formatDate value="${coupon.effectiveStartTime }"
				pattern="yyyy-MM-dd" />
			至
			<fmt:formatDate value="${coupon.effectiveEndTime }"
				pattern="yyyy-MM-dd" />
		</p> <span class="icons"><i class="iconfont">&#xe607;</i></span>
	</li>
</c:forEach>
<input type="hidden" id="totalPage" value="${totalPage }">
<input type="hidden" id="pageIndex" value="${pageIndex }">
