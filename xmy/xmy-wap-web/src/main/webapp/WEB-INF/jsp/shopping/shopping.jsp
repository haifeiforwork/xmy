<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!-- 购物车页面 -->
<!DOCTYPE html>
<html>
    <head>
        <title>购物车</title>
	    <!-- 公共css区域 -->
	    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
	    <!-- 公共css完区域 -->
	    <!-- 该页面应用css区域 -->
	    <link href="resources/shopping/css/shopping.css" rel="stylesheet">
	    <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-back-w@2x.png"/></div>
    		<div class="pull-center shopping-num">购物车</div>
    		<div class="pull-right">
                <span class="edit" id="bianji">编辑</span>
    			<span class="shrink"><img src="resources/common/images/btn-more-w@2x.png"/>
    				<div class="modal-some">
                    
                	</div>
    			</span>
    		</div>
    	</div>
    </header>
	<div class="content">
		<input type="hidden" id="sessionInfo" value="${sessionInfo }">
    	<div class="hint">温馨提示:<span>全场满38元包邮，平台统一配送</span></div>
    	<c:if test="${fn:length(shoppingCart) <= 0}">
    		<div style="text-align:center;padding:2rem 0;color:#999;font-size:0.75rem;">
   				<img alt="" src="resources/common/images/empty-cart.png" style="margin:0 0 0.5rem;width: 3rem;"/>
   				<p>您还没有商品加入购物车中</p>
   			</div>
    	</c:if>
    	<c:if test="${fn:length(shoppingCart) > 0}">
        <div class="weui-panel__bd list">
        <c:set var="allPrice" value="0"></c:set>
        <c:set var="goodsAllNum" value="0"></c:set>
        	<c:forEach items="${shoppingCart }" var="cartGoods">
               	<c:if test="${cartGoods.isPutway == 0 || (cartGoods.activityType != null && cartGoods.activityId != null) }">
                 	<a url="goods/goodsInfo?goodsId=${cartGoods.id }" href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg active-goods toGoodsInfo" goods-id="${cartGoods.id }">
		                <div class="choose">
		                    <div class="yuan active"><input type="hidden" class="goodsId" value="${cartGoods.id }"></div>
		                </div>
		                <div class="weui-media-box__hd">
		                    <img class="weui-media-box__thumb" src="${cartGoods.imgPath }"/>
		                </div>
		                <div class="weui-media-box__bd">
		                    <p class="weui-media-box__desc" style="-webkit-line-clamp:1">${cartGoods.name }</p>
		                    <c:if test="${not empty cartGoods.presentGoods }">
		                    	<p class="weui-media-box__desc" style="color:red;-webkit-line-clamp:1">赠品：${cartGoods.presentGoods.name }</p>
		                    </c:if>
		                    <p class="etalon">规格:<span>${cartGoods.standard }</span></p>
		                    <div class="count goods-num-operate">
		                        <div class="gw_num">
		                            <em class="jian">-</em>
		                            <input type="text" value="${cartGoods.cartNum }" class="num" disabled="disabled"/>
		                            <em class="add">+</em>
		                        </div>
		                        <input type="hidden" class="goodsId" value="${cartGoods.id }">
		                        <input type="hidden" class="activityId" value="${cartGoods.activityId }">
		                        <input type="hidden" class="activityType" value="${cartGoods.activityType }">
		                        <input type="hidden" class="limitNum" value="${cartGoods.limitNum }">
		                        <input type="hidden" class="residueNum" value="${cartGoods.residueNum }">
		                    </div>
		                </div>
		                <div class="weui-cell__ft">
		                	 <c:if test="${not empty cartGoods.actPoints && cartGoods.actPoints != 0 }">
	                             	${cartGoods.actPoints }积分
                             </c:if>
                             <c:if test="${empty cartGoods.actPoints || cartGoods.actPoints == 0 }">
                             	¥<c:if test="${empty cartGoods.activityPrice }">
                             		${cartGoods.price }
                             		<c:set var="allPrice" value="${allPrice + cartGoods.price*cartGoods.cartNum }"></c:set>
	                             	<input type="hidden" class="goods-price" value="${cartGoods.price }">
                             	</c:if>
	                             <c:if test="${!empty cartGoods.activityPrice }">
	                             	${cartGoods.activityPrice }
	                             	<c:set var="allPrice" value="${allPrice + cartGoods.activityPrice*cartGoods.cartNum }"></c:set>
		                    		<input type="hidden" class="goods-price" value="${cartGoods.activityPrice }">
	                             </c:if>
                             </c:if>
		                </div>
		                <input type="hidden" class="actPoints" value="${cartGoods.actPoints }">
		            </a>
		            <c:set var="goodsAllNum" value="${goodsAllNum + 1 }"></c:set>
                 </c:if>
            </c:forEach>
        </div>
        <div class="weui-panel__bd listing">
        	<c:forEach items="${shoppingCart }" var="cartGoods">
               	<c:if test="${cartGoods.isPutway != 0 && cartGoods.activityId == null && cartGoods.activityType == null }">
		            <a goods-id="${cartGoods.id }" href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg putAway">
		                <span class="state">失效</span>
		                <div class="weui-media-box__hd">
		                    <img class="weui-media-box__thumb" src="${cartGoods.imgPath }"/>
		                </div>
		                <div class="weui-media-box__bd">
		                    <p class="weui-media-box__desc">${cartGoods.name }</p>
		                    <p class="hing">商品已下架，请选择其他商品</p>
		                </div>
		            </a>
                 </c:if>
            </c:forEach>
        </div>
        </c:if>
   	</div>
    <footer>
        <div class="row">
            <div class="ly-65">
	            <div class="choose1">
			           <div class="yuan1 active"></div>
			     </div>
			    <div class="choose-all" style="display: none;">
			    	全选
			    </div>
			    <div class="sumdiv">
            	合计:<span class="all-price">￥${empty allPrice?0.00:allPrice }</span>
            	<span class="reminder">不含运费</span>
            	</div>
            </div>
            <div class="ly-35 add-order">结算(<span class="all-num">${goodsAllNum }</span>)</div>
            <div class="ly-35 delete-order" style="display: none;">删除</div>
        </div>  
    </footer>
    <!-- 公共js区域 --> 
	<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <script type="text/javascript" src="resources/shopping/js/shopping.js"></script>
    <!-- 该页面应用js区域 -->
    </body>
</html>
