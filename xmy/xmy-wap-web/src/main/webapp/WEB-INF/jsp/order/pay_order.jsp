<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>支付方式</title>
	 	<!-- 公共css区域 -->
        <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <link href="resources/order/css/pay_order.css" rel="stylesheet">
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    
    <form id="toPaySuccessPageForm" method="post" action="payment/paySuccess">
    	
    </form>
    
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">支付方式</div>
    		<div class="pull-right">
    			<span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
    				<div class="modal-some">
                    
                	</div>
    			</span>
    		</div>
    	</div>
    </header>
	<div class="content">
    	<div class="head-top"><span>订单金额</span><span class="head-right"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${payOrder.total}" /></span></div>
        <div class="weui-cells list">
        	<c:if test="${not empty wap_session && not empty wap_session.userId }">
	            <div class="weui-cell my-balance">
	            	<input type="hidden" id="lost-money" value="${balance }">
	            	<input type="hidden" id="total-money" value="${payOrder.total }">
	                <div class="weui-cell__hd balance-type">
	                	<c:if test="${not empty wap_session }">
	                		<div class="account yuan ${userInfo.balance > 0 ? 'active' : '' }"><img alt="" src="resources/order/images/xz.png"></div>
	                	</c:if>
	                </div>
	                <div class="weui-cell__bd">
	                    <p>个人账户(余额:<span><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${balance }"/></span>)</p>
	                    <p class="pay">支付
	                    <input type="number" id="cost-money" <c:if test="${userInfo.balance <= 0}">disabled="disabled"</c:if> value='<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2">${payOrder.total > userInfo.balance ? userInfo.balance : payOrder.total}</fmt:formatNumber>' />
	                                                           元</p>
	                </div>
	                <div class="weui-cell__ft shop-card"><button type="button" id="active-card">激活购物卡</button></div>
	            </div>
            </c:if>
            <c:if test="${micromessenger == 'micromessenger' }">
	            <div class="weui-cell wx-pay">
	            	<input type="hidden" id="micromessenger" value="${micromessenger }">
	                <div class="weui-cell__hd other-type">
	                	<div class="yuan <c:if test="${(empty wap_session || empty wap_session.userId  || userInfo.balance <= 0) && not empty micromessenger}">active</c:if>">
	                		<img alt="" src="resources/order/images/xz.png">
	                	</div></div>
	                <div class="weui-cell__bd">
	                    <p><img src="resources/common/images/img-wxpay@2x.png"/></p>
	                <%-- </div>
	                
	                	<div class="weui-cell__ft wx">浏览器不支持微信支付，请在微信浏览器中打开</div>
	                </c:if> --%>
	                </div>
	            </div>
            </c:if>
            <c:if test="${micromessenger != 'micromessenger'}">
            	<div class="weui-cell ali-payment">
	                <div class="weui-cell__hd other-type">
	                <div class="yuan <c:if test="${(empty wap_session || empty wap_session.userId || userInfo.balance <= 0) && empty micromessenger }">active</c:if>">
	                	<img alt="" src="resources/order/images/xz.png">
	                </div></div>
	                <div class="weui-cell__bd">
	                    <p><img src="resources/common/images/img-alipay@2x.png"/></p>
	                </div>
	            </div>
            </c:if>
            <div class="weui-cell brand-card">
                <div class="weui-cell__hd other-type"><div class="yuan"><img alt="" src="resources/order/images/xz.png"></div></div>
                <div class="weui-cell__bd">
                    <p><img src="resources/common/images/img-unionpay@2x.png"/></p>
                </div>
            </div>
            <div class="weui-cell self-payment">
                <div class="weui-cell__hd other-type"><div class="yuan"><img alt="" src="resources/order/images/xz.png"></div></div>
                <div class="weui-cell__bd">
                    <p><img src="resources/common/images/img-hdfk@2x.png"/></p>
                </div>
                <input type="hidden" id="out" value="${out }">
                <div class="weui-cell__ft">跨境商品不支持货到付款</div>
            </div>
        </div>
        <div class="ok-pay"><button type="button">确认支付</button></div>
   	</div>
   	<input type="hidden" name="orderid" value="${payOrder.id }"/>
   	<input type="hidden" name="orderno" value="${payOrder.no }"/>
   	<input type="hidden" id="confirm" value="${confirm }"/>
    <!-- 公共js区域 --> 
   <%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <script type="text/javascript" src="resources/order/js/pay_order.js"></script>
    <!-- 该页面应用js区域 -->
    </body>
</html>