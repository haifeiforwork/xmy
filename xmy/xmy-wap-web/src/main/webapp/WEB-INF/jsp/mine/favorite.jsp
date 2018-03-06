<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <title>收藏夹</title>
        <!-- 公共css区域 -->
        <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <link rel="stylesheet" href="resources/mine/css/favorite.css" />
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-back-w@2x.png"/></div>
    		<div class="pull-center">收藏夹</div>
    		<div class="pull-right">
    			<!-- <span class="shrink"><img src="resources/common/images/btn-index-share@2x.png"/></span> -->
    			<span class="shrink"><img src="resources/common/images/btn-index-more@2x.png"/>
    				<div class="modal-some">
                    
               	 	</div>
    			</span>
    		</div>
    	</div>
    	<div class="row">
    		<div class="ly-87">
    			<div class="weui-flex rank">
				  	<div class="weui-flex__item active all">
				  		<div>分类<i class="iconfont">&#xe60a;</i></div>
						<ul class="listing">
							
						</ul>
				  	</div>
				  	<div id="getAllGoods" class="weui-flex__item classify">全部(<span id="total" >0</span>)</div>
				  	<!-- <div class="weui-flex__item price">降价(<span>20</span>)<i class="iconfont">&#xe60a;</i></div> -->
				</div>
    		</div>
    		<div class="ly-13 edit">编辑</div>
    	</div>
    </header>
	<div class="content">
    	<div class="weui-panel__bd list">
		    <!-- <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
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

		<div class="lists">
            
        </div>
<!-- 		<div class="weui-loadmore">
		  <i class="weui-loading"></i>
		  <span class="weui-loadmore__tips">正在加载</span>
		</div> -->
   	</div>
    <!-- 公共js区域 --> 
   <%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <script type="text/javascript" src="resources/mine/js/favorite.js" ></script>
    <!-- 该页面应用js区域 -->
    </body>
</html>