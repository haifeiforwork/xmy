<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--会员中心-->
<!DOCTYPE html>
<html>
<head>
    <title>专题狂欢</title>
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/vipcenter/css/vipcenter.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
    <style type="text/css">
        .content{background-color: #FFF;}
        .des_word{font-size: 0.65rem;color: #333;padding: 0.5rem;}
        .des_h{font-size: 0.75rem;font-weight: 700;}
        .des_title{font-size: 0.7rem;font-weight: 600}
        .des_item{word-break: break-all;}
    </style>
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">专题狂欢</div>
        <div class="pull-right">
            <!-- <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"/></span> -->
            <span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
            	<div class="modal-some">
                    
                </div>
            </span>
        </div>
    </div>
</header>
<div class="content" style="bottom: 0;top: 51px;">
    <div class="des_word">
        <div class="des_h">专题狂欢</div>
        <div class="des_title"></div>
        <div class="des_item">每月根据节假日将会有更多类型的专题活动，包含赠券、打折、抽奖、猜拳、分享等多种活动等你参与！</div>
    </div>
</div>
<!-- 公共js区域 --> 
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/vipcenter/js/carnival.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>