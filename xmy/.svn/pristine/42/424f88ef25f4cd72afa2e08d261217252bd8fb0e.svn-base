<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人地址</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <link rel="stylesheet" href="resources/mine/css/address.css" />
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">管理地址</div>
    	</div>
    </header>
	<div class="content">
		<c:if test="${empty wap_session.userAddress || fn:length(wap_session.userAddress) <= 0}">
			<div style="text-align:center;padding:2rem 0;color:#999;font-size:0.75rem;">
   				<img alt="" src="resources/common/images/empty.png" style="margin:0 0 0.5rem;width: 3rem;"/>
   				<p>您还没有添加收货地址</p>
   			</div>
		</c:if>
		<c:if test="${not empty wap_session.userAddress && fn:length(wap_session.userAddress) > 0}">
    	<div class="list">
            <c:forEach items="${wap_session.userAddress }" var="addr" varStatus="index" >
				<div class="chunk">
					<input type="hidden" name="addressId" class="addressId" value="${addr.id }" />
	                <div class="chunk-title"><span>${addr.name }</span><span class="phone">${addr.mobilePhone }</span></div>
	                <div class="address">${addr.province }&nbsp${addr.city }&nbsp${addr.area }&nbsp${addr.address }</div>
	                <div class="lump">
                   	<c:if test="${addr.isDefault == 0 }" >
	                    <div class="sigin active">
		                	<div class="yuan"></div><span>默认地址</span>
	                    </div>
	                </c:if>
	                <c:if test="${addr.isDefault == 1 }" >
	                	<div class="sigin">
		                	<div class="yuan"></div><span>默认地址</span>
		                </div>
	                </c:if>
                    <div class="handle">
                        <span class="edit"><img src="resources/common/images/btn-gldz-edit@2x.png"/><span class="text">编辑</span></span>
                        <span class="del"><img src="resources/common/images/btn-gldz-del@2x.png"/><span class="text">删除</span></span>
                    </div>
	                </div>
	            </div>
            </c:forEach>
            <form id="modify" method="post">
            	<input type="hidden" name="addressId" />
            </form>
    	</div>
    	</c:if>
   	</div>
    <footer>
        <button class="add-address" type="button">添加新地址</button>
    </footer>
    <!-- 公共js区域 --> 
    <script src="resources/common/js/jquery.min.js"></script>
	<script src="resources/common/js/jquery-weui.min.js"></script>
	<script src="resources/common/js/swiper.min.js"></script>
	<script src="resources/common/js/city-picker.min.js"></script>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <script type="text/javascript" src="resources/mine/js/address.js" >

    </script>
    <!-- 该页面应用js区域 -->
    </body>
</html>