<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--会员中心-->
<!DOCTYPE html>
<html>
<head>
    <title>积分商城</title>
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/vipcenter/css/vipcenter.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">积分商城</div>
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
        <div class="weui-panel head weui-panel_access">
            <div class="weui-panel__bd">
                <div class="weui-media-box weui-media-box_appmsg">
                	<c:if test="${empty wap_session.userInfo.avatar }">
                		<div class="weui-media-box__hd">
                        	<img class="weui-media-box__thumb" src="resources/common/images/cstars/defaultheadimg.png">
                    	</div>
                	</c:if>
                	<c:if test="${not empty wap_session.userInfo.avatar }">
	                    <div class="weui-media-box__hd">
	                        <img class="weui-media-box__thumb" src="resources/common/images/cstars/${wap_session.userInfo.avatar }">
	                    </div>
	                </c:if>
                    <div class="weui-media-box__bd">
                        <p class="weui-media-box__title phone">${wap_session.userInfo.realName }</p>
                        <p class="weui-media-box__title jf" style="overflow:visible"><span class="jf-count">积分  ：  ${wap_session.userInfo.accPoints }</span>
                        	<c:if test="${isSign }"><button type="button" class="sign-btn">已签到</button></c:if>
                        	<c:if test="${!isSign }"><button type="button" class="sign-btn sign">签到</button></c:if>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container vip-gutter">
        <div class="vip-title"><img src="resources/common/images/ico-hyzx-qy@2x.png" alt="">我的会员权益</div>
        <div class="weui-flex head-nav">
            <div class="weui-flex__item vip_prop" url="gotoPage/vipcenter/get_accpoint"><img src="resources/common/images/ico-hyzx-zjf-o@2x.png" alt="" class="vip-head-img">赚积分</div>
            <div class="weui-flex__item vip_prop" url="gotoPage/vipcenter/vip_day"><img src="resources/common/images/ico-hyzx-hyr-o@2x.png" alt="" class="vip-head-img">会员日</div>
            <div class="weui-flex__item vip_prop" url="gotoPage/vipcenter/new_goods"><img src="resources/common/images/ico-hyzx-smhl-o@2x.png" alt="" class="vip-head-img">神秘好礼</div>
            <div class="weui-flex__item vip_prop" url="gotoPage/vipcenter/scare_buying"><img src="resources/common/images/ico-hyqy-czqg-o@2x.png" alt="" class="vip-head-img">超值抢购</div>
            <div class="weui-flex__item vip_prop" url="gotoPage/vipcenter/carnival"><img src="resources/common/images/ico-hyqy-ztkh-o@2x.png" alt="" class="vip-head-img">专题狂欢</div>
        </div>
    </div>
    <div class="container banner">
       <img src="resources/common/images/vip-banner.jpg" alt="">
    </div>
    <div class="container">
        <div class="vip-title"><img src="resources/common/images/ico-hyzx-qy@2x.png" alt="">会员超值兑换</div>
        <div class="weui-flex tab-nav">
        	<c:forEach items="${activityList }" var="activity" varStatus="status">
        		<div points-id="${activity.id }" class="weui-flex__item  activity-div <c:if test='${status.index == 0 }'>active</c:if>">${activity.name }</div>
        	</c:forEach>
        </div>
        <div class="vip">
            
        </div>
    </div>
</div>
<!-- 公共js区域 --> 
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/vipcenter/js/vipcenter.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>