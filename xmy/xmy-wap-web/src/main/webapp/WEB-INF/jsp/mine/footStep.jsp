<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>足迹</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <link rel="stylesheet" href="resources/mine/css/footStep.css" />
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">足迹</div>
    		<div class="pull-right">
    			<span class="edit">编辑</span>
    			<!-- <span class="shrink"><img src="resources/common/images/btn-fenl-share@2x.png"/></span> -->
    			<span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
    				<div class="modal-some">
                    
                	</div>
    			</span>
    		</div>
    	</div>
    </header>
	<div class="content">
    	<div class="weui-panel__bd list">
<!-- 		    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			    <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="resources/common/images/sss.png"/>
			    </div>
			    <div class="weui-media-box__bd">
			        <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
			        <p class="money">￥<span>25.00</span></p>
			        <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
			    </div>
		    </a> -->
		   <!--  <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			    <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="resources/common/images/cp.png"/>
			    </div>
			    <div class="weui-media-box__bd">
			        <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
			        <p class="money">￥<span>25.00</span></p>
			        <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
			    </div>
		    </a>
		    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			    <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="resources/common/images/sss.png"/>
			    </div>
			    <div class="weui-media-box__bd">
			        <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
			        <p class="money">￥<span>25.00</span></p>
			        <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
			    </div>
		    </a>
		    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			    <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="resources/common/images/cp.png"/>
			    </div>
			    <div class="weui-media-box__bd">
			        <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
			        <p class="money">￥<span>25.00</span></p>
			        <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
			    </div>
		    </a>
            <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                <div class="weui-media-box__hd">
                    <img class="weui-media-box__thumb" src="resources/common/images/sss.png"/>
                </div>
                <div class="weui-media-box__bd">
                    <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
                    <p class="money">￥<span>25.00</span></p>
                    <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
                </div>
            </a>
            <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                <div class="weui-media-box__hd">
                    <img class="weui-media-box__thumb" src="resources/common/images/cp.png"/>
                </div>
                <div class="weui-media-box__bd">
                    <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
                    <p class="money">￥<span>25.00</span></p>
                    <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
                </div>
            </a>
            <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                <div class="weui-media-box__hd">
                    <img class="weui-media-box__thumb" src="resources/common/images/sss.png"/>
                </div>
                <div class="weui-media-box__bd">
                    <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
                    <p class="money">￥<span>25.00</span></p>
                    <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
                </div>
            </a>
            <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                <div class="weui-media-box__hd">
                    <img class="weui-media-box__thumb" src="resources/common/images/cp.png"/>
                </div>
                <div class="weui-media-box__bd">
                    <p class="weui-media-box__desc">[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套[德国生活馆] 荷兰MaxiCosi/迈可适安全座椅红色1套</p>
                    <p class="money">￥<span>25.00</span></p>
                    <div class="count"><span class="title">冰点价</span><span class="number">限量1000份</span></div>
                </div>
            </a> -->
		</div>
   	</div>
    <!-- 公共js区域 --> 
<!--     <script src="resources/common/js/jquery.min.js"></script>
	<script src="resources/common/js/jquery-weui.min.js"></script>
	<script src="resources/common/js/swiper.min.js"></script>
	<script src="resources/common/js/city-picker.min.js"></script> -->
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <script type="text/javascript" src="resources/mine/js/footStep.js"></script>
    <!-- 该页面应用js区域 -->
    </body>
</html>