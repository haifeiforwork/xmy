<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="img"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/nationwide/nationwide.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
    <div class="container container-white">
    <div class="list-content recomed w-1250">
        <div class="recomed-title">热卖推荐</div>
        <ul class="clearfix">
            <c:forEach items="${topGoods }" var="topgoods">
	            <li class="col-25 clearfix">
	            <a href="goods/${topgoods.id}/0/0">
	                <div class="col-40 img-wrap ">
	                 <c:if test="${empty topgoods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg"></c:if>
        <c:if test="${!empty topgoods.imgPath }"><img:imghandle w="122" imgurl="${topgoods.imgPath }" h="122"></img:imghandle></c:if>
        
	                </div>
	                <div class="col-60">
	                    <div class="shop-name">${topgoods.name }</div>
	                    <div class="recomed-des color-r"><span class="special-price">特价 : </span><span class="zh-money">¥</span> <span class="price">${topgoods.price }</span></div>
	                    <button type="button" class="recomed-btn" style="cursor:pointer;">立即抢购</button>
	                </div>
	             </a>
	            </li>
            </c:forEach>
        </ul>
    </div>
    <input type="hidden" value="${goodsName }" id="goodsName" />
    <div class="list-content w-1250">
        <div class="search-content">
            <span class="first-search"><i class="iconfont" style="margin-left: 5px;">&#xe602;</i></span>
            <div class="result">
	            <div>
	            <span>全部结果</span> 
	            <i class="iconfont">&#xe6e8;</i> 
	            </div> 
	            <div>
		            <span class="sec"></span> 
		            <i class="iconfont">&#xe6e8;</i>
	            </div>
            </div>
            <div class="input-wap">
            	<input type="text"><i class="iconfont">&#xe602;</i>
            </div>
            <div class="fr">共<span>22</span>件相关商品</div>
        </div>
        <div class="search-list">
            <ul>
                 <li class="search-list-item clearfix">
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
                </li>
            </ul>
        </div>
    </div>
    <div class="list-content screen-all w-1250">
        <div class="search-content">
            <span>排序 : </span>
            <div class="screen">
                <button type="button" class="screen-btn active" value="">综合 <i class="iconfont">&#xe649;</i></button>
                <button type="button" class="screen-btn" value="sum_comment">人气 <i class="iconfont">&#xe649;</i></button>
                <button type="button" class="screen-btn" value="sum_deal">销量 <i class="iconfont">&#xe649;</i></button>
                <button type="button" class="screen-btn select-price" value="price">价格 <i class="iconfont top">&#xe629;</i> <i class="iconfont bottom">&#xe603;</i></button>
                <button type="button" class="screen-btn" value="putway_time">新品 <i class="iconfont">&#xe649;</i></button>
            </div>
            <div class="area"><span>配送范围 : </span> <button type="button" class="screen-btn">重庆主城</button> <button type="button" class="screen-btn">全国配送</button></div>
            <div class="area">
                <span>价格区间 : </span><input type="text"> - <input type="text">
            </div>
        </div>
        <div class="shop-content-list" id="goods">
            
        </div>
    </div>
    <div class="list-content add-bg w-1250">
        <div class="search2">
            <input type="text">
            <button type="button">搜索</button>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/nav/search_float_top.jsp" %>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<script type="text/javascript" src="resource/js/common/menu.js"></script>
<script type="text/javascript" src="resource/js/nationwide/nationwide.js"></script>
</body>
</html>
