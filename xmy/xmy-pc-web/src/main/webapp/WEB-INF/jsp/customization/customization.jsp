<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/customization/customization.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
    <div class="container det-mar">
    <div class="all-the">
        <div class="card-imgwrap">
            <img src="resource/commons/images/qydz-top.png" alt="">
        </div>
        <div class="card-imgwrap card-imgwrap2">
            <img src="resource/commons/images/card-banner1.jpg" alt="">
        </div>
        <%-- <div class="card-list card-imgwrap">
            <p class="text-center"><span class="title title3"></span></p>
        </div>
        <c:forEach items="${map }" var="map">
        	<div>
               <div class="tab-content">
                   <div class="add-gutter">
                       <ul class="card-items">
                       <c:forEach items="${map.value }" var="card">
                       		<li>
                       		<a href="goods/${card.goodsId}/0/0">
                               <img src="${card.imgPath }" alt="">
                               <span class="card-word">${card.goodsName }</span>
                             </a>
                           </li>
                       </c:forEach>
                       </ul>
                   </div>
               </div>
               <c:forEach items="${map.value }" var="card" step="4">
	              <div class="card-imgwrap m-ban">
	                  <img src="resource/commons/images/card-ban.png" alt="">
	              </div>
               </c:forEach>
           </div>
        </c:forEach> --%>
        
        <div class="card-list card-imgwrap" style="margin-top:50px;">
            <div class="tab-content">
                <div class="add-gutter">
                <c:forEach items="${map }" var="map" varStatus="m">
                	<c:if test="${m.count==1 }">
                		<p class="text-center" style="margin-bottom: 40px;"><span class="title title3"></span></p>
                	</c:if>
                	<c:if test="${m.count==2 }">
                		<p class="text-center" style="margin-bottom: 40px;"><span class="title title4"></span></p>
                	</c:if>
                    <ul class="card-items card-ul">
                 		<c:forEach items="${map.value }" var="card" varStatus="j" begin="0" end="11">
                        <li>
                            <a href="goods/${card.goodsId}/0/0"><img src="${card.imgPath }" alt=""></a>
                            <span class="card-word">${card.goodsName }</span>
                        </li>
                        <c:if test="${j.count%4 == 0 }">
	 						</ul>
	 						<ul class="card-items card-ul">
	 					</c:if>
                  		</c:forEach>
                    </ul>
                  </c:forEach>
                </div>
            </div>
        </div>
    </div>
     <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
</div>
 	 <%@include file="/WEB-INF/jsp/commons/nav/search_float_top.jsp" %>
    <script type="text/javascript" src="resource/js/common/goods_search.js"></script>
    <script src="resource/js/common/menu.js"></script>
  </body>
</html>
