<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>
<c:if test="${!empty allOrder }">
	<c:forEach items="${allOrder }" var="order">
		<form method="post" class="doComment" action="comment/toAddComment">
			<input type="hidden" name="userId" value="${userId}" class="userId">
			<c:if test="${order.id != null }">
				<input type="hidden" name="orderId" value="${order.id }" />
			</c:if>
			<div class="chunk">
				<div class="title">
					<span class="oreder"
						onclick="window.location.href='order/detailsOrder?orderId=${order.id}'">订单号:<a
						value="${order.id }">${order.no }</a></span>
					<c:if test="${order.isDel==0 }">
						<span class="state">已取消</span>
					</c:if>
					<c:if test="${order.isDel!=0 }">
						<c:if test="${order.status==1 && order.isDel==1  }">
							<span class="state">待付款</span>
						</c:if>
						<c:if test="${order.status==2 }">
							<span class="state">待发货</span>
						</c:if>
						<c:if test="${order.status==3 }">
							<span class="state">待收货</span>
						</c:if>
						<c:if test="${order.status==4 }">
							<span class="state">待评价</span>
						</c:if>
						<c:if test="${order.status==5 }">
							<span class="state">已完成</span>
						</c:if>
					</c:if>
				</div>
				<c:set var="sumGoods" value="0"></c:set>
				<c:forEach items="${order.orderGoods }" var="goods">
					<input type="hidden" value="${goods.goodsId }" name="goodsId" />
				</c:forEach>
				<c:forEach items="${order.orderGoodsDto }" var="orderGoods">
					<div class="weui-panel weui-panel_access">
						<div class="weui-panel__bd">
							<a class="weui-media-box weui-media-box_appmsg">
								<div class="weui-media-box__hd">
									<img class="weui-media-box__thumb toGoodsInfo"
										url="goods/goodsInfo?goodsId=${orderGoods.goodsId }&activityId=${orderGoods.activityId}&activityType=${orderGoods.activityType }&pointsId=${orderGoods.pointsId }"
										src="${orderGoods.imgPath }" goods-id="${orderGoods.goodsId }" />
								</div>
								<div class="weui-media-box__bd">
									<p class="weui-media-box__desc titled">${orderGoods.name }</p>
									<c:if test="${not empty orderGoods.presentName }">
										<p class="weui-media-box__desc"
											style="color:red;-webkit-line-clamp:1">赠品：${orderGoods.presentName }</p>
									</c:if>
									<p class="weui-media-box__desc money">
										<c:if
											test="${not empty orderGoods.sumPoints && orderGoods.sumPoints != 0}">
								        	${orderGoods.sumPoints }积分
								        </c:if>
										<c:if
											test="${empty orderGoods.sumPoints || orderGoods.sumPoints == 0}">
								        	￥<fmt:formatNumber maxFractionDigits="2"
												minFractionDigits="2">${orderGoods.sumPrice }</fmt:formatNumber>
									</p>
</c:if>
</div>
<c:set var="sumGoods" value="${sumGoods + orderGoods.num }"></c:set>
</a>
</div>
</div>
</c:forEach>
<div class="sum">
	<p class="text">
		共<span>${sumGoods }</span>件商品 合计：<a class="red"><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2">${order.total }</fmt:formatNumber></a>
		(含运费￥
		<c:if test="${empty order.freight }">
			<a>0.00</a>
		</c:if>
		<c:if test="${not empty order.freight }">
			<a><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2">${order.freight }</fmt:formatNumber></a>
		</c:if>
		)
	</p>
</div>
<c:if test="${order.isDel==1}">
	<c:if test="${order.status==1}">
		<div class="handle">
			<button type="button" class="cancel">取消订单</button>
			<button type="button" class="evaluate pay">去支付</button>
		</div>
	</c:if>
	<c:if test="${order.status == 2 && order.shipStatus < 5 && order.cancelEndTime > order.nowTime}">
	    	<div class="handle" >
	         <button type="button" class="cancel" style="margin-top: 0.5rem;">取消订单</button>
	     </div>
	</c:if>
	<c:if test="${order.status==3 }">
		<div class="handle">
			<button type="button" class="look">查看物流</button>
			<button type="button" class="evaluate confirm" value="确认收货">确认收货</button>
		</div>
	</c:if>
	<c:if test="${order.status==4 }">
		<div class="handle">
			<button type="button" class="good">默认好评</button>
			<button type="button" class="evaluate appraise">评价</button>
		</div>
	</c:if>
</c:if>
</div>
</form>
</c:forEach>
</c:if>
<input type="hidden" id="orderCount" value="${fn:length(allOrder) }" />
<input type="hidden" id="pageIndex" value="${pageIndex }" />
