<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--优惠券页面-->
<!DOCTYPE html>
<html>
    <head>
        <title>优惠券</title>
	    <!-- 公共css区域 -->
	    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
	    <!-- 公共css完区域 -->
	    <!-- 该页面应用css区域 -->
	    <link href="resources/mine/css/coupon.css" rel="stylesheet">
	    <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">优惠券</div>
    		<div class="pull-right">绑定实体券</div>
    	</div>
    </header>
	<div class="content">
    	<div class="weui-flex state">
		  	<div class="weui-flex__item active chunk1" int-val="0">未使用(<span class="notUseNumber">${fn:length(resultMap.notUse) }</span>)</div>
		  	<div class="weui-flex__item chunk2" int-val="1">使用记录(<span>${fn:length(resultMap.used) }</span>)</div>
		  	<div class="weui-flex__item chunk3" int-val="2">已过期(<span>${fn:length(resultMap.expired) }</span>)</div>
		</div>
		<div class="lump">
			<div class="weui-flex status">
				<div class="weui-flex__item use-range active" use-range="1">
				<!-- 全部类型<span><img src="resources/common/images/btn-yhq-qblx@2x.png"/></span> -->
					<div class="weui-flex__item sort-type-x">
						<span id="rangeType">全部类型</span>
						<span>
							<img src="resources/common/images/btn-yhq-qblx@2x.png" class="img-off" style="display: inline;"/>
							<img  src="resources/common/images/btn-yhq-qblx@2u.png" class="img-on" style="display: none;"/>
						</span>
					</div>
				</div>
			  	<div class="weui-flex__item order-method" int-val="1">过期时间<span><img src="resources/common/images/btn-yhq-gqsj@2x.png"/></span></div>
			  	<div class="weui-flex__item order-method" int-val="2">最优惠</div>
			</div>
			<div class="sort">
				<div class="weui-cells">
					<div class="weui-cell active use-range">
						<a href="javascript:;" use-range="">全部类型</a>
					</div>
					<div class="weui-cell use-range">
						<a href="javascript:;" use-range="1">全场通用</a>
					</div>
					<div class="weui-cell use-range">
						<a href="javascript:;" use-range="2">分类使用</a>
					</div>
					<div class="weui-cell use-range">
						<a href="javascript:;" use-range="3">限定商品</a>
					</div>
					<div class="weui-cell use-range">
						<a href="javascript:;" use-range="4">排队商品</a>
					</div>
				</div>
			</div>
			<div class="lump1">
				
			</div>
		</div>
   	</div>
   	
   	<!-- 修改地址弹窗 -->
	<!-- <div id="coupon_popup" class="weui-popup__container popup-bottom" style="z-index: 999">
	  <div class="weui-popup__overlay"></div>
	  <div class="weui-popup__modal open-popup add_address_content" data-target="" style="height: 15%;background-color: #fff" >
	  		<div class='titled' style="margin-top: 0.5rem;">优惠券码：<input type='text' style="height: 1.1rem;width: 80%;" id="couponCode"></div>
	  		<div class='bound' style="width: 100%;height: 2.0rem;text-align: center;border: 1px solid;line-height: 2rem;margin-top: 0.5rem;">立即绑定</div>
	  </div>
	</div> -->
	
	<div class="m-frame">
   		<div class="m-frame_con">
   			<div class="weui-cells">
   				<div class="weui-cell">
   					<div class="weui-cell__hd"><p>优惠券码 : </p></div>
   					<div class="weui-cell__bd"><input type="number" class="weui-input" id="couponCode"/></div>
   				</div>
   			</div>
   			<div class="weui-cells">
   				<div class="weui-cell">
   					<div class="weui-cell__bd">
   						<a href="javascript:;" class="weui-btn weui-btn_primary bound">立即绑定</a>
   					</div>
   				</div>
   			</div>
   		</div>
   	</div>
   	
    <!-- 公共js区域 --> 
	<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
	<!-- 公共js区域 --> 
    <!-- 该页面应用js区域 -->
    <script type="text/javascript" src="resources/mine/js/coupon.js"></script>
    <!-- 该页面应用js区域 -->
    </body>
</html>