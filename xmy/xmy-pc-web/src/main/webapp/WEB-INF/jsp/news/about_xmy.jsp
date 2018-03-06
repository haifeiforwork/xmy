<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/news/about_xmy.css">
  </head>
  
  <body>
  	<%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
 <!--内容部分-->
<div class="container-white add-top-bottomm">
	<div class="content w-1250">
		<div class="new-left">
			<ul class="pleft">
            	<c:forEach items="${sessionScope.findDownColum }" var="termData" varStatus="te">
					<li class="pli">
						<div ${te.index==ter?'class="ptitle active"':'class="ptitle"' }>${termData.name } <i ${te.index==ter?'class="iconfont css3"':'class="iconfont"' }>&#xe63b;</i></div>
						<div ${te.index==ter?'style="display: block;"':'' } class="mo">
							<c:forEach items="${termData.childTermData }" var="childData" varStatus="ch">
		           			 	<c:if test="${te.index==ter}"><a ${ch.index==chd?'class="on"':'' }>${childData.name}</a></c:if>
		           			 	<c:if test="${te.index!=ter}"><a>${childData.name}</a></c:if>
		           			</c:forEach>
		                </div>
					</li>
				</c:forEach>
			</ul>			
		</div>
		<c:forEach items="${sessionScope.findDownColum }" var="termData" varStatus="terDes">
			<div class="new-right">
				<c:forEach items="${termData.childTermData }" var="childData" varStatus="des">
					<c:if test="${terDes.index==ter&&des.index==chd }">
						<div class="lijie" style="display: block;"}>
							${childData.des }
						</div>
					</c:if>
					<c:if test="${terDes.index!=ter||des.index!=chd }">
						<div class="lijie" style="display:none;">
							${childData.des }
						</div>
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
</div>
</div>
  <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  <script type="text/javascript" src="resource/js/common/menu.js"></script>
  <script type="text/javascript" src="resource/js/news/about_xmy.js"></script>
  <script type="text/javascript">
  
  </script>
  </body>
</html>
