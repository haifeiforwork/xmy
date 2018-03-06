<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--搜索页面-->
<!DOCTYPE html>
<html>
    <head>
        <title>商品列表</title>
	    <!-- 公共css区域 -->
	    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
	    <!-- 公共css完区域 -->
	    <!-- 该页面应用css区域 -->
	    <link href="resources/goods/css/search_goods.css" rel="stylesheet">
	    <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top header-top-bg">
    		<div class="pull-left" onclick="self.href=document.referrer"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">
    			<form action="goods/searchGoodsByName" method="post" >
    				<input type="search" placeholder="搜索商品" class="search-input" name="goodsName" value="${searchGoodsDto.goodsName }" style="outline:none;">
    			</form>
    		</div>
    		<div class="pull-right">
                <!-- <span class="search"><img src="resources/common/images/btn-fenl-search@2x.png" alt=""></span> -->
    			<span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
    				<div class="modal-some">
                    
                	</div>
    			</span>
    		</div>
    	</div>
        <div class="row">
            <div class="col-80">
                <div class="weui-flex">
                    <div class="weui-flex__item down order-usual active"><span class="down1">综合排序</span></div>
                    <div class="sort">
						<div class="weui-cells">
							<div class="weui-cell active search-sort"
							 <c:if test="${searchGoodsDto.orderMethod == 4 }">sort-method="4"</c:if>
							 <c:if test="${searchGoodsDto.orderMethod != 4 }">sort-method="0"</c:if>
							 >
								<a href="javascript:;">综合排序</a>
							</div>
							<div class="weui-cell search-sort" sort-method="4">
								<a href="javascript:;">新品上架</a>
							</div>
							<div class="weui-cell search-sort" sort-method="2">
								<a href="javascript:;">销量排序</a>
							</div>
							<div class="weui-cell search-sort" sort-method="1">
								<a href="javascript:;">评论数量排序</a>
							</div>
							<div class="weui-cell search-sort" sort-method="3">
								<a href="javascript:;">价格排序</a>
							</div>
						</div>
					</div>
                    <div class="weui-flex__item sale"><span>销量优先</span></div>
                    <div class="weui-flex__item price-down"><span class="price-down11">价格</span></div>
                    <div class="weui-flex__item sequencing"><span class="sequencingOne"><img src="resources/goods/images/btn-qgps-qh@2x.png"></span></div>
                </div>
            </div>
            <div class="col-20">筛选 <img src="resources/common/images/btn-qgps-sx@2x.png" alt=""></div>
        </div>	
    </header>
	<div class="content">
        <!--  -->
   	</div>
   	<div class="paging show" style="display: none;"><span class="curr"></span> / <span class="total" ></span></div>
   	<div class="paging already-last" style="display: none; margin-left: -55px; width: 120px;"><p>已经是最后一页了</p></div>
    <div class="page">
        <div class="overlay"></div>
        <div class="page-content">
        	<c:forEach items="${searchList }" var="searchDto">
        		 <div class="page-item search-type" type-id="${searchGoodsDto.typeId }">
	                <div class="item-title"><span>${searchDto.searchName }</span> <span class="img-wrap down"></span></div>
	                <ul class="page-list" style="display: none">
	                    <c:forEach items="${searchDto.childList }" var="child">
		                	<li typeId="${searchDto.searchId }" search="${child.searchName }" <c:if test="${child.searchName == '全部'}">class="all-type"</c:if>>${child.searchName }</li>
		                </c:forEach>
	                </ul>
	             </div>
        	</c:forEach>
        	<c:if test="${empty searchGoodsDto.mianLand && empty searchGoodsDto.isDivle }">
	            <div class="page-item post-area">
	                <div class="item-title"><span>配送范围</span> <span class="img-wrap"></span></div>
	                <ul class="page-list">
	                    <li <%-- <c:if test="${searchGoodsDto.isDivle == '1' || searchGoodsDto.mianLand == '1' }">class="choose-type"</c:if> --%> isDivle="1">重庆主城</li>
	                    <li <%-- <c:if test="${searchGoodsDto.isDivle == '0' || searchGoodsDto.mianLand == '0' }">class="choose-type"</c:if> --%> isDivle="0">全国配送</li>
	                </ul>
	            </div>
            </c:if>
            <div class="page-item prcie-area">
                <div class="item-title"><span>价格区间</span></div>
                <ul class="page-list">
                    <li>
                        <input type="number" class="price-input low-price" placeholder="最低价（元）"><span class="f-line">-</span><input type="number" class="price-input most-price" placeholder="最高价（元）">
                    </li>
                </ul>
            </div>
            <div class="weui-flex fix-btn">
                <div class="weui-flex__item esc">重置</div>
                <div class="weui-flex__item finish">完成${searchType }</div>
            </div>
        </div>
    </div>
    <form id="searchForm">
    	<input type="hidden" id="gateWay" value="${gateWay }" />
    	<input type="hidden" id="typeId" name="typeId" value="<c:if test='${searchGoodsDto.typeId != null }'>${searchGoodsDto.typeId }</c:if><c:if test='${searchGoodsDto.oneId != null }'>${searchGoodsDto.oneId }</c:if>">
    	<input type="hidden" name="priceOrder" value="${searchGoodsDto.priceOrder }">
    	<input type="hidden" name="orderMethod" value="${searchGoodsDto.orderMethod }">
    	<input type="hidden" name="mianLand" value="${searchGoodsDto.mianLand }">
    	<%-- <input type="text" name="wordSeg" value="${searchGoodsDto.wordSeg }"> --%>
    	<input type="hidden" name="wordSeg" value="${searchGoodsDto.wordSeg }">
    	<input type="hidden" name="beginPrice" value="${searchGoodsDto.beginPrice }">
    	<input type="hidden" name="endPrice" value="${searchGoodsDto.endPrice }">
    	<input type="hidden" name="wordId" value="${searchGoodsDto.wordId }">
    	<input type="hidden" name="search" value="${searchGoodsDto.search }">
    	<input type="hidden" name="isDivle" value="${searchGoodsDto.isDivle }">
    	<input type="hidden" name="pageIndex" id="pageIndex" value="${pageBean.pageIndex }">
    	<input type="hidden" id="searchType" value="${searchType }">
    	<input type="hidden" name="goodsName" value="${searchGoodsDto.goodsName }">
    	<input type="hidden" id="needReload" value="${needReload }" />
    	<input type="hidden" id="flag" name="flag" />
    </form>
    <!-- 公共js区域 -->
	<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
	<!-- 公共js区域完 -->
	<!-- 该页面应用js区域 -->
	<script type="text/javascript" src="resources/goods/js/search_goods.js"></script>
	<!-- 该页面应用js区域 -->
    </body>
</html>
