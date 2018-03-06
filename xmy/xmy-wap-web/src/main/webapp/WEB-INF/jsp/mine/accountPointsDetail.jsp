<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>积分明细</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <link rel="stylesheet" href="resources/mine/css/accountPointsDetail.css" />
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">积分明细</div>
    	</div>
    </header>
	<div class="content">
    	<div class="weui-form-preview">
    		<div id="content" class="weui-form-preview__bd">
<!-- 			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">积分</label>
			      	<span class="weui-form-preview__value">550</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">类型</label>
			      	<span class="weui-form-preview__value">获得</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">状态</label>
			      	<span class="weui-form-preview__value">有效</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">备注</label>
			      	<span class="weui-form-preview__value">000000000270447订单消费</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">创建时间</label>
			      	<span class="weui-form-preview__value">2017-05-03</span>
			    </div>
			</div>
			<div class="weui-form-preview__bd">
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">积分</label>
			      	<span class="weui-form-preview__value">550</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">类型</label>
			      	<span class="weui-form-preview__value">获得</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">状态</label>
			      	<span class="weui-form-preview__value">有效</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">备注</label>
			      	<span class="weui-form-preview__value">000000000270447订单消费</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">创建时间</label>
			      	<span class="weui-form-preview__value">2017-05-03</span>
			    </div>
			</div>
			<div class="weui-form-preview__bd">
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">积分</label>
			      	<span class="weui-form-preview__value">550</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">类型</label>
			      	<span class="weui-form-preview__value">获得</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">状态</label>
			      	<span class="weui-form-preview__value">有效</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">备注</label>
			      	<span class="weui-form-preview__value">000000000270447订单消费</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">创建时间</label>
			      	<span class="weui-form-preview__value">2017-05-03</span>
			    </div>
			</div>
			<div class="weui-form-preview__bd">
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">积分</label>
			      	<span class="weui-form-preview__value">550</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">类型</label>
			      	<span class="weui-form-preview__value">获得</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">状态</label>
			      	<span class="weui-form-preview__value">有效</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">备注</label>
			      	<span class="weui-form-preview__value">000000000270447订单消费</span>
			    </div>
			    <div class="weui-form-preview__item">
			      	<label class="weui-form-preview__label">创建时间</label>
			      	<span class="weui-form-preview__value">2017-05-03</span>
			    </div>
			</div> -->
    	</div>
   	</div>
    <!-- 公共js区域 --> 
    <script src="resources/common/js/jquery.min.js"></script>
	<script src="resources/common/js/jquery-weui.min.js"></script>
	<script src="resources/common/js/swiper.min.js"></script>
	<script src="resources/common/js/city-picker.min.js"></script>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <script src="resources/mine/js/accountPointsDetail.js" ></script
    <!-- 该页面应用js区域 -->
    </body>
</html>