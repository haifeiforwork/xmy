<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--制作lj-->
<!DOCTYPE html>
<html>
<head>
    <title>领券中心</title>
    <!-- 公共css区域 -->
   <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/coucenter/css/coucenter.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">领券中心</div>
        <div class="pull-right">
            <!-- <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"/></span> -->
            <span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
            	<div class="modal-some">
                    
                </div>
            </span>
        </div>
    </div>
</header>
<div class="content">
    <div class="container">
        <div class="banner"><img src="resources/common/images/img-hyzx-banner@2x.png" alt=""></div>
    </div>
    <div class="container">
    	<c:if test="${fn:length(findsAllUsableCoupon) <= 0 }">
       	 	<span style="color: #444;display:block;padding:8rem 0 0;text-align:center;">暂无可领取的抵用券</span>
        </c:if>
        <c:if test="${fn:length(findsAllUsableCoupon) > 0  }">
        	<div class="lq">
        	<c:forEach items="${findsAllUsableCoupon }" var="coupon">
	        	<div class="lq-title lq-title1 clearfix">
	        	<c:if test="${coupon.isUserGet == 1 }">
	        		<img src="resources/common/images/lq-ready.png" class="redy"/>
	        	</c:if>
	                <div class="col-69" style="margin-right: 3%;">
	                    <p class="one-stence"><%-- ${coupon.name } --%>¥ <span class="lq-price"><fmt:formatNumber pattern="##.#" value="${coupon.couponValue }"/></span><span class="lq-limit">${coupon.name }</span></p>
	                    <div class="two-stence"><p class="use-condition">使用条件 : <span>满${coupon.quota }可用</p></div> 
	                    <div class="last-time">使用时间 : 
	                    <fmt:formatDate pattern="yyyy.MM.dd" value="${coupon.effectiveStartTime }"/>
	                    -
	                    <fmt:formatDate pattern="yyyy.MM.dd" value="${coupon.effectiveEndTime }"/>
	                    </div>
	                </div>
	                <div class="col-28">
	                    <p class="floor">
	                    	<span class="floor-bar" style="width: ${coupon.usePercent }%"></span>
	                    	<span class="floor-pro" style="width:100%">已领<fmt:formatNumber type="number" pattern="0.0" value="${coupon.usePercent }" ></fmt:formatNumber>%</span>
	                    </p> 
	                    <c:if test="${coupon.allAvable == 0 }" >
	                    	<p class="lq-btn1 get-coupon" coupon-id="${coupon.id }">已领完</p>
	                    </c:if>
	                    <c:if test="${coupon.isUserGet == 0 && coupon.allAvable == 1 }">
		                    <p class="lq-btn get-coupon available" coupon-id="${coupon.id }">立即领取</p>
	                    </c:if>
	                    <c:if test="${coupon.isUserGet == 1 && coupon.allAvable == 1 }">
		                    <p class="lq-btn1 get-coupon use" coupon-id="${coupon.id }">去用券</p>
	                    </c:if>
	                </div>
	            </div>
	            <div class="lq-row">
	                <div class="clearfix shop-list container-white">
	                	<c:forEach items="${coupon.goodsList }" var="goods">
	                		<div goods-id="${goods.id }" class="col-33">
		                        <img src="${goods.imgPath }" alt="" class="shop-img">
		                        <%-- <p class="price"><fmt:formatNumber value="${goods.price }" pattern="￥0.00"/></p> --%>
		                    </div>
	                	</c:forEach>
	                </div>
	            </div>
        	</c:forEach>
        	</div>
        	</c:if>
</div>
<!-- 公共js区域 -->
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/coucenter/js/coucenter.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>