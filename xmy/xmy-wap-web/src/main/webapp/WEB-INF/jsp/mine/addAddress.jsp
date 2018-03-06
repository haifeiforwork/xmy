<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>添加地址</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 增加地址css -->
        <link rel="stylesheet" href="resources/mine/css/addAddress.css" /> 
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">添加地址</div>
            <div class="pull-right">保存</div>
    	</div>
    </header>
	<div class="content">
    	<div class="list">
			<div class="weui-cells weui-cells_form">
				<form id="addAddress" action="mine/addAddress" method="post" >
					<input type="hidden" name="userId" value="${wap_session.userInfo.id }" />
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><label class="weui-label">收货人</label></div>
	                    <div class="weui-cell__bd">
	                        <input class="weui-input" name="name"  type="text" placeholder="请输入收货人名称"/>
	                    </div>
	                </div>
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><label class="weui-label">联系电话</label></div>
	                    <div class="weui-cell__bd">
	                        <input class="weui-input" name="mobilePhone" type="number" placeholder="请输入收货人电话"/>
	                    </div>
	                </div>
	                <a class="weui-cell weui-cell_access address-choose" href="javascript:;">
	                    <div class="weui-cell__bd">
	                        <p>所在地区</p>
	                    </div>
	                    <div class="weui-cell__ft">
	                        <input class="weui-input place-pick address" type="text" placeholder="请选择">
	                    </div>
	                    <input type="hidden" name="isDefault" value="1" />
	                    <input type="hidden" name="province"  class="province" />
	                    <input type="hidden" name="city" class="city" />
	                    <input type="hidden" name="area" class="area" />
	                </a>
	                <div class="text">
	                   <textarea class="weui-textarea addressDetails" name="address" placeholder="请填写详细地址"></textarea>
	                </div>
                </form>
            </div>
    	</div>
   	</div>
    <!-- 公共js区域 --> 
    <%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <!-- 公共js区域 --> 
    <script src="resources/common/js/jquery.min.js"></script>
	<script src="resources/common/js/jquery-weui.min.js"></script>
	<script src="resources/common/js/swiper.min.js"></script>
	<script src="resources/common/js/city-picker.min.js"></script>
    <!-- 公共js区域完 -->
    <script type="text/javascript" src="resources/mine/js/addAddress.js" ></script>
    <!-- 公共js区域完 -->
    </body>
</html>
