<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="img"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>搜索 |香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/search/type_search.css">
  </head>
  
  <body>
  	<%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
 <!--内容部分-->
<div class="container container-white">
    <div class="list-content recomed w-1250">
        <div class="recomed-title">热卖推荐</div>
        <ul class="clearfix">
            <c:forEach items="${topGoods }" var="topgoods">
            	<a href="goods/${topgoods.id }/0/0">
		            <li class="col-25 clearfix">
		                <div class="col-40 img-wrap ">
		                 <c:if test="${empty topgoods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
              <c:if test="${!empty topgoods.imgPath }"><img:imghandle w="122" imgurl="${topgoods.imgPath }" h="122"  cl="shop-img"></img:imghandle></c:if>
        
		                </div>
		                <div class="col-60">
		                    <div class="shop-name">${topgoods.name }</div>
		                    <div class="recomed-des color-r"><span class="special-price">特价 : </span><span class="zh-money">¥</span> <span class="price">${topgoods.price }</span></div>
		                    <button type="button" class="recomed-btn">立即抢购</button>
		                </div>
		            </li>
	            </a>
            </c:forEach>
        </ul>
    </div>
    <input type="hidden" value="${typeId }" id="goodsName" />
    <input type="hidden" value="${woid }" id="word" />
    <div class="list-content w-1250">
        <div class="search-content">
            <span class="first-search"><i class="iconfont">&#xe602;</i></span>
            <div class="result">
	            <div>
	            <span onclick="shanchu(果)">全部结果</span> 
	            <i class="iconfont">&#xe6e8;</i> 
	            </div> 
	            <div hidden="hidden">
		           	<span class="keywords">${topName }</span><i class="iconfont">&#xe6e8;</i>
		           	<span class="keywords">${typeName }</span><i class="iconfont">&#xe6e8;</i>
		           	<c:if test="${!empty wordName }">
		           		<span class="keywords">${wordName }</span> <i class="iconfont">&#xe6e8;</i>
		           	</c:if>
		            <span class="sec-wrap"></span>
	            </div>
            </div>
            <div class="input-wap">
            	<input type="text" id="lowContent"><i class="iconfont" id="lowSearch" style="cursor: pointer;">&#xe602;</i>
            </div>
            <!-- <div class="fr" hidden="hidden">共<span>22</span>件相关商品</div> -->
        </div>
        <div class="search-list">
            <ul>
            	<c:forEach items="${searchList }" var="parentList" varStatus="b">
                <li class="search-list-item clearfix">
                    <div class="add-key">${parentList.searchName }</div>
                    <ul class="col-80">
						<c:forEach items="${parentList.childList }" var="childList" varStatus="i">                    
	                        <li value="${i.index }" parent="${b.index }">${childList.searchName }</li>
                        </c:forEach>
                    </ul>
                    <div class="col-10" id="more" num="0">更多 <i class="iconfont">&#xe63b;</i></div>
                </li>
                </c:forEach>
                <!--  <li class="search-list-item clearfix">
                    <div class="add-key">产地</div>
                    <ul class="col-80">
                        <li>北京</li>
                        <li>上海</li>
                        <li>广州</li>
                        <li>天津</li>
                    </ul>
                    <div class="col-10">更多 <i class="iconfont">&#xe63b;</i></div>
                </li>
                <li class="search-list-item clearfix">
                    <div class="add-key">包装</div>
                    <ul class="col-80">
                        <li>尝鲜装 ( 20 )</li>
                        <li>分享装 ( 10 )</li>
                        <li>家庭装 ( 6 )</li>
                        <li>原装 ( 4 )</li>
                        <li>袋装 ( 4 )</li>
                    </ul>
                    <div class="col-10">更多 <i class="iconfont">&#xe63b;</i></div>
                </li> -->
            </ul>
        </div>
    </div>
    <div class="list-content screen-all w-1250">
        <div class="search-content">
            <span>排序 : </span>
            <div class="screen">
                <button type="button" class="screen-btn active" value="0">综合 <i class="iconfont">&#xe649;</i></button>
                <button type="button" class="screen-btn" value="1">人气 <i class="iconfont">&#xe649;</i></button>
                <button type="button" class="screen-btn" value="2">销量 <i class="iconfont">&#xe649;</i></button>
                <button type="button" class="screen-btn select-price" value="3">价格 <i class="iconfont top">&#xe629;</i> <i class="iconfont bottom">&#xe603;</i></button>
                <button type="button" class="screen-btn" value="4">新品 <i class="iconfont">&#xe649;</i></button>
            </div>
            <div class="area"><span>配送范围 : </span> <button type="button" class="screen-btn" value="1">重庆主城</button> <button type="button" class="screen-btn" value="0">全国配送</button></div>
            <div class="area">
                <span>价格区间 : </span><input type="text" id="beginPrice"> <span>-</span> <input type="text" id="endPrice">
            </div>
        </div>
        <div class="shop-content-list">
            <ul class="clearfix shop-con" id="goods">
            	<!-- 存放商品信息 -->
            </ul>
            <div id="biuuu_city" style="float:right"></div>
            <%--
            <div class="pagingg clearfix">
                <span class="clearfix">
                	<c:forEach items="${page }" var="pageIndex">
                		<c:if test="${pageIndex == 1 }">
                    		<a href="javasprict:void(0);" value="${pageIndex }"  class="first active page-num">${pageIndex }</a>
                		</c:if>
                		<c:if test="${pageIndex!=1 }">
                			<a href="javasprict:void(0);" value="${pageIndex }"  class="first page-num">${pageIndex }</a>
                		</c:if>
                	</c:forEach>
                   		<a href="javasprict:void(0);" class="last"><i class="iconfont">&#xe65b;</i></a>
                </span>
            </div> --%>
        </div>
    </div>
    <input type="hidden" value="5" id="countPage"/>
    <div class="list-content add-bg w-1250">
        <div class="search2">
            <input type="text" id="sousuoContent">
            <button type="button" id="sousuo">搜索</button>
        </div>
    </div>
</div>
  <%@include file="/WEB-INF/jsp/commons/nav/search_float_top.jsp" %>
  <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  <script type="text/javascript" src="resource/js/common/goods_search.js"></script>
  <script type="text/javascript" src="resource/js/common/menu.js"></script>
  <script type="text/javascript" src="resource/js/search/type_search.js"></script>
  </body>
</html>
