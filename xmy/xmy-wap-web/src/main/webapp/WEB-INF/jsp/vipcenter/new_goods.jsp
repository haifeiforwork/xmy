<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--会员中心-->
<!DOCTYPE html>
<html>
<head>
    <title>神秘好礼</title>
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
        <div class="pull-center">神秘好礼</div>
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
        <div class="des_h">神秘好礼</div>
        <div class="des_title"></div>
        <div class="des_item">购物享神秘好礼，每月、每季度、每半年、每年度将对期间消费及订单数量排序靠前的用户赠送神秘好礼，最高可获得超千元礼品哟。</div>
    </div>
</div>
<!-- 公共js区域 --> 
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/vipcenter/js/new_goods.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>