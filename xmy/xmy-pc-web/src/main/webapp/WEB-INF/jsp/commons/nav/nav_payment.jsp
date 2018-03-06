<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%-- 购物车、结算页、支付页nav公共部分 --%>
<nav>
    <%@include file="/WEB-INF/jsp/commons/nav/comm_top.jsp" %>
    <div class="navigation">
        <div class="w-1250">
            <div class="navigation-left">
                <a href="index">
                	<div class="left-chunk1" style="cursor: pointer;"><img src="resource/commons/images/gr-logo.jpg"/></div>
                </a>
                <div class="left-chunk2" id="orderCommit" style="display: none;">
                    <p class="my-xmy logo-near">结算页</p>
                </div>
            </div>
            <div class="navigation-right">
                <div class="balances clearfix">
                    <div class="balancess-step step4 active"><div class="circle">4</div><div class="text">支付</div></div>
                    <div class="balancess-step"><div class="circle">3</div><div class="text">成功提交订单</div></div>
                    <div class="balancess-step"><div class="circle">2</div><div class="text">填写核对订单信息</div></div>
                    <div class="balancess-step step1"><div class="circle">1</div><div class="text">我的购物车</div></div>
                </div>
            </div>
        </div>
    </div>
</nav>
