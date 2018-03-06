<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!-- 分类主页面 -->
<!DOCTYPE html>
<html>
<head>
    <title>分类</title>
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/classfy/css/classfy.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">商品分类</div>
        <div class="pull-right">
            <span class="search"><img src="resources/common/images/btn-fenl-search@2x.png" alt=""></span>
            <!-- <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"/></span> -->
            <span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
            	<div class="modal-some">
                    
                </div>
            </span>
        </div>
    </div>
</header>
<div class="content">
    <div class="container">
        <div class="top-nav" style="z-index: 9999;">
         <div class="nav-title"><span>全部分类</span><span class="drow-up"><img src="resources/common/images/btn-sssx-down@2x.png"></span></div>
            <div class="weui-cells">
            	<c:forEach items="${navigation }" var="termData" varStatus="status">
					 <c:if test="${termData.isShow != 1 }">
						 <div class="classfy-first weui-cell <c:if test='${status.index == 0 }'>active</c:if>" termDat-id="${termData.id }">
			                  <div class="weui-cell__bd">
			                      <p><img src="${termData.appIcon}" alt="" class="nav-img icon-off" <c:if test='${status.index == 0 }'>style="display: none;"</c:if><c:if test='${status.index != 0 }'>style="display: inline;"</c:if>>
			                      	 <img src="${termData.appOnIcon}" alt="" class="nav-img icon-on" <c:if test='${status.index == 0 }'>style="display:inline;"</c:if><c:if test='${status.index != 0 }'>style="display: none;"</c:if>>
			                      </p>
			                      <p>${termData.name }</p>
			                  </div>
			             </div>
					 </c:if>
				</c:forEach>
            </div>
            <div class="drow drow-down"><img alt="" src="resources/common/images/btn-fenl-down@2x.png"></div>
        </div>
    </div>
</div>
<!-- 公共js区域 -->
<%@include file="/WEB-INF/jsp/common/common_footer.jsp"%>
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/classfy/js/classfy.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>