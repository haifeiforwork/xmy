<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>反馈</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <!-- 公共css区域 -->
    <link rel="stylesheet" href="resources/common/css/weui.min.css">
    <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
    <link rel="stylesheet" href="resources/common/css/style.css">
    <!--<link rel="stylesheet" type="text/css" href="resources/common/css/comment_stars.css"/>-->
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link rel="stylesheet" href="resources/mine/css/feedback.css" />
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">反馈</div>
    </div>
</header>
<div class="content">
	<form id="feedBackForm" method="post" action="comment/addFeedBack">
    <div class="weui-cells ad-gutter-sm">
        <div class="weui-cell">
            <div class="weui-cell__bd">
            	<input type="hidden" name="userId" value="${wap_session.user.id }" />
                <input class="weui-input phoneNum" name="phoneNum" type="number" placeholder="请输入您的电话号码">
            </div>
        </div>
    </div>
    <div class="weui-cells weui-cells_form ad-gutter-sm">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <textarea class="weui-textarea" name="content" placeholder="请输入您需要反馈的内容" rows="3"></textarea>
            </div>
        </div>
    </div>
    </form>

    <a id="submit" href="javascript:;" class="weui-btn weui-btn_primary btn-back">反馈</a>
</div>
<!-- 公共js区域 -->
<script src="resources/common/js/jquery.min.js"></script>
<script src="resources/common/js/jquery-weui.min.js"></script>
<script src="resources/common/js/swiper.min.js"></script>
<script src="resources/common/js/city-picker.min.js"></script>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
<script type="text/javascript" src="resources/mine/js/feedback.js" ></script>
<!-- 该页面应用js区域 -->
</body>
</html>