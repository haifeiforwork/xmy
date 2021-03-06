<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!-- 商品详情页面 -->
<!DOCTYPE html>
<html>
    <head>
        <title>${goods.name }</title>
	    <!-- 公共css区域 -->
	    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
	    <!-- 公共css完区域 -->
	    <!-- 该页面应用css区域 -->
	    <link href="resources/goods/css/goods_info.css" rel="stylesheet">
	    <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><div class="return"><img src="resources/common/images/btn-back-w@2x.png"/></div></div>
    		<div class="pull-center"></div>
    		<div class="pull-right">
    			<!-- <span class="shrink"><img src="resources/common/images/btn-index-share@2x.png"/></span> -->
                <span class="shrink"><img src="resources/common/images/btn-more-w@2x.png"/>
                	<div class="modal-some">
                    
                	</div>
                </span>
    		</div>
    	</div>
    </header>
    <div class="add-shop-cart addActivityShopping">
        <img src="resources/common/images/btn-spxq-tjgwc@2x.png"/>
    </div>
	<div class="content">
    	<div class="flexslider">
            <ul class="slides">
                <c:forEach items="${goods.imageList }" var="image">
                	<li><img src="${image }"></li>
                </c:forEach>
            </ul>
        </div>
        <div class="head-top">
            <div class="title">${goods.name }</div>
            <div class="money">
                <span>积分
	                <a id="activityPrice">
						${pointsGoods.points }
						<input type="hidden" value="<fmt:formatDate value="${pointsActivity.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="endTime"/>
					</a>
				</span>
                <div class="count">
                    <div class="gw_num">
                        <em class="jian">-</em>
                        <input type="text" value="1" class="num" id="goodsNum" readonly="readonly"/>
                        <em class="add">+</em>
                    </div>
                </div>
            </div>
            <div class="line">
                <span class="left">销量:<span>
                	<c:if test="${goods.sumDeal==null }">0</c:if>
					<c:if test="${goods.sumDeal!=null }">${goods.sumDeal }</c:if>
				</span></span>
                <span class="right">赠送积分:<span>0</span></span>
            </div>
            <div class="line">
                <span class="left green">配送范围:<span><c:if test="${goods.isDelivery==0 }">全国配送</c:if><c:if test="${goods.isDelivery==1 }">重庆主城</c:if></span></span>
                <c:if test="${not empty wap_session && isCollect != 1 }"><span class="right collect active"><img class="img" src="resources/common/images/btn-spxq-sc@2x.png"/>收藏</span></c:if>
                <c:if test="${empty wap_session || isCollect == 1 }"><span class="right collect"><img class="img" src="resources/common/images/btn-spxq-wsc@2x.png"/>收藏</span></c:if>
            </div>
        </div>
        <div class="message">
            <div class="norms">
                <div class="ly-20">规格</div>
                <div class="ly-80">
                    <ul class="chunk-ul">
                        <c:forEach items="${goods.goodsstandards }" var="stan">
							<c:if test="${goods.standard == stan.standard }">
								<li class="chunk-li goods-standards active" goods-id="${stan.goodId }">${stan.standard }</li>
							</c:if>
							<c:if test="${goods.standard != stan.standard }">
								<li class="chunk-li goods-standards" goods-id="${stan.goodId }">${stan.standard }</li>
							</c:if>
						</c:forEach>
                    </ul>
                </div>
            </div>
            <div class="norms">
                <div class="ly-20">信息</div>
                <div class="ly-80 lump">
                    <p class="carry"><span>商品：<a>${goods.name }</a></span><span>商家：<a>[${fn:substring(fn:substringBefore(goods.name,"]"), 1, 9) }]</a></span></p>
                    <div class="down"><img src="resources/common/images/btn-fenl-down@2x.png"/></div>
                </div>
            </div>
            <div class="weui-cells list">
                <a class="weui-cell weui-cell_access" href="comment/${goods.goodsId }">
                    <div class="weui-cell__bd">
                        <p>商品评价</p>
                    </div>
                    <div class="weui-cell__ft">
                    <span>${empty sumComment ? 0 : sumComment }</span>好评
                    </div>
                </a>
                <a class="weui-cell weui-cell_access" href="javascript:;">
                    <div class="weui-cell__bd">
                        <p>图文详情(建议在WIFI下查看)</p>
                    </div>
                    <div class="weui-cell__ft">
                    </div>
                </a>
            </div>
        </div>
        <div class="recommend">
            <div class="title">商品推荐</div>
            <ul class="list-ul">
                <c:forEach items="${yourLike }" var="goods">
					<li class="lsit-li">
	                    <img src="${goods.imgPath }"/>
	                    <p class="titled">${goods.name }</p>
	                    <p class="money">￥${goods.price }</p>
	                </li>
				</c:forEach>
            </ul>
        </div>
   	</div>
   	<input type="hidden" id="goodsId" value="${goods.id }">
   	<input type="hidden" id="point" value="${pointsGoods.points }">
   	<input type="hidden" id="actId" value="${activityId }">
   	<input type="hidden" id="typeId" value="${typeId }" />
    <%@include file="/WEB-INF/jsp/common/common_footer.jsp"%>
    <!-- 公共js区域 --> 
	<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <script type="text/javascript" src="resources/goods/js/goods_points_info.js"></script>
    <!-- 该页面应用js区域 -->
    </body>
</html>
