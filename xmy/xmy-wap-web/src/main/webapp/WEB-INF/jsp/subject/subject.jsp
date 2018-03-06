<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <title></title>
	    <!-- 公共css区域 -->
	    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
	    <!-- 公共css完区域 -->
    </head>
    <body ontouchstart>	
    <c:if test="${empty flag}">
	    <header  style="background-color: #fff;">
	    <div class="header-top">
	        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
	        <div class="pull-center">${not empty modal ? '企业定制' : '专题活动'}</div>
	        <%-- <div class="pull-right">
	            <span class="search"><img src="resources/common/images/btn-fenl-search@2x.png" alt=""></span>
	            <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"/></span>
	            <span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
	            	<div class="modal-some">
	                    
	                </div>
	            </span>
	        </div> --%>
	    </div>
		</header>
	</c:if>
	<div class="content" <c:if test="${flag == 'app' }"> style="top: 0px; bottom: 0px;"</c:if>>
        <jsp:include page="${url }"></jsp:include>
   	</div>
   	<c:if test="${empty flag}">
	    <%@include file="/WEB-INF/jsp/common/common_footer.jsp"%>
	    <!-- 该页面应用js区域 -->
	    <script type="text/javascript" src="resources/common/js/jquery.min.js"></script>
		<script type="text/javascript" src="resources/activity/js/html.js"></script>
		<!-- 该页面应用js区域 -->
	</c:if>
    </body>
</html>
