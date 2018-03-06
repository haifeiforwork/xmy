<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="xmy-wap-web" tagdir="/WEB-INF/tags"%>
<xmy-wap-web:base></xmy-wap-web:base>

<footer>
    <div class="weui-flex footer">
        <div class="weui-flex__item <c:if test='${model == "home" }'>active</c:if>" url="home/home">
	       <div class="img">
	       		<c:if test='${model == "home" }'><img src="resources/common/images/btn-tab-home-g@2x.png"/></c:if>
	       		<c:if test='${model != "home" }'><img src="resources/common/images/btn-tab-home@2x.png"/></c:if>
	       </div>
        <p>首页</p></div>
        <div class="weui-flex__item <c:if test='${model == "classfy" }'>active</c:if>" url="classfy/classfy">
        <div class="img">
        	<c:if test='${model == "classfy" }'><img src="resources/common/images/btn-tab-fl-g@2x.png"/></c:if>
	       	<c:if test='${model != "classfy" }'><img src="resources/common/images/btn-tab-fl@2x.png"/></c:if>
        </div>
        <p>分类</p></div>
        <div class="weui-flex__item <c:if test='${model == "shopping" }'>active</c:if>" url="shopping/shopping">
        <div class="img shoppingCartCount">
        
        	<c:if test='${model == "shopping" }'><img src="resources/common/images/btn-tab-car-g@2x.png"/></c:if>
	       	<c:if test='${model != "shopping" }'><img src="resources/common/images/btn-tab-car@2x.png"/></c:if>
        </div>
        <p>购物车</p></div>
        <div class="weui-flex__item ${empty wap_session ? 'mine' : '' } <c:if test='${model == "mine" }'>active</c:if>" url="mine/center">
        <div class="img">
        	<c:if test='${model == "mine" }'><img src="resources/common/images/btn-tab-my-g@2x.png"/></c:if>
	       	<c:if test='${model != "mine" }'><img src="resources/common/images/btn-tab-my@2x.png"/></c:if>
	    </div>
        <p>我的香满圆</p></div>
        <div class="weui-flex__item ${empty wap_session ? 'service' : '' } <c:if test='${model == "service" }'>active</c:if>" url="service/qian" id="qianniu">
        <div class="img">
        	<c:if test='${model == "service" }'><img src="resources/common/images/btn-tab-kf-g@2x.png"/></c:if>
	       	<c:if test='${model != "service" }'><img src="resources/common/images/btn-tab-kf@2x.png"/></c:if>
        </div>
        <p>在线客服</p></div>
    </div>
</footer>
