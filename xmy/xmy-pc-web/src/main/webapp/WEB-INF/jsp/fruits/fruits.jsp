<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/border/border.css">
  </head>
  
  <body>
  <%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
  <div class="ad">
		<div id="flex" class="slideBox">
		<ul class="items">
			<c:forEach items="${topImg }" var="item">
         		<li><a href="${item.data }"><img src="${item.imgPath }"></a></li>
           	</c:forEach>
		</ul>
	</div>
	<!-- top图 -->
</div>
  <div class="container add-g-b" style="margin-bottom:44px;">
    <c:forEach items="${list }" var="list" varStatus="idx">
	<div class="list-content">
      <div class="count-title ct1">
        <span class="wrap-ct-word">
	        <span class="ad-word-l title-name">${list.adDto.name }</span>
        </span>
       	 <div class="ct1-line"></div>
        </div>
        <ul class="clearfix add-bg-w">
            <li ${list.adDto.type==0?'':'style="display: none;"' } class="first-show list-item"><a href="goods/${list.adDto.data}/0/0"><img src="${list.adDto.img }"></a></li>
            <li ${list.adDto.type==1?'':'style="display: none;"' } class="first-show list-item"><a href="${list.adDto.data}"><img src="${list.adDto.img }"></a></li>
            <c:forEach items="${list.goodsDirs }" var="goods">
            <li class="list-item">
            <a href="goods/${goods.goodsId}/0/0">
                <div class="list-show">
                    <img src="${goods.imgPath }" alt="">
                    <p class="text-elips" style="color: black;">${goods.goodsName }</p>
                    <div class="clearfix price price-dex"><span>${goods.price }</span><img data-id="${goods.goodsId }" src="resource/commons/images/gcar.png" alt="" class="gcar"></div>
                </div>
            </a>
            </li>
            </c:forEach>
        </ul>
    </div>    
    </c:forEach>
    <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
</div>
  
  <%@include file="/WEB-INF/jsp/commons/nav/search_float_top.jsp" %>
  <script type="text/javascript" src="resource/js/common/goods_search.js"></script>
  <script src="resource/commons/js/jquery.flexslider-min.js"></script>
  <script src="resource/js/common/menu.js"></script>
  <script src="resource/commons/js/sli.js"></script>
  <script type="text/javascript">
  $(window).load(function(){
		$('#flex').slideBox({

	        duration : 0.3,//滚动持续时间，单位：秒

	        easing : 'linear',//swing,linear//滚动特效

	        delay : 5,//滚动延迟时间，单位：秒

	        hideClickBar : false,//不自动隐藏点选按键

	        clickBarRadius : 10

	    });
	});
  </script>
  </body>
</html>
