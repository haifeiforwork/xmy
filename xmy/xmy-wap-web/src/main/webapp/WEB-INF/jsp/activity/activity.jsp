<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--制作lj-->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>开抢了</title>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<meta name="format-detection" content="telephone=no">
<!-- 公共css区域 -->
<%@ include file="/WEB-INF/jsp/common/common_css.jsp"%>
<!-- 公共css完区域 -->
<!-- 该页面应用css区域 -->
<link href="resources/activity/css/activity.css" rel="stylesheet">
<!-- 该页面应用css区域 -->
</head>
<body ontouchstart>

	<header>
		<div class="header-top">
			<div class="pull-left">
				<img src="resources/common/images/btn-fenl-back@2x.png" />
			</div>
			<div class="pull-center">
				<img src="resources/common/images/start-sto.png" alt=""
					class="start-sto">
			</div>
			<div class="pull-right">
				<!--  <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"/></span> -->
				<span class="shrink"><img
					src="resources/common/images/btn-fenl-more@2x.png" />
					<div class="modal-some"></div> </span>
			</div>
		</div>
	</header>
	<div class="content">
		<div class="container">
			<div class="swiper-container" data-space-between='10'
				data-pagination='.swiper-pagination' data-autoplay="1000">
				<div class="swiper-wrapper">
					<c:forEach items="${topImg }" var="img">
						<div class="swiper-slide">
							<img src="${img.imgPath }" alt="">
						</div>
					</c:forEach>
				</div>
				<!-- If we need pagination -->
				<div class="swiper-pagination"></div>
			</div>
		</div>

		<div class="container">
			<c:if test="${not empty iceActivity}">
				<div class="rob">
					<input type="hidden" id="nowTime" value="${data.nowTime }">
					<input type="hidden" id="endTime" value="${data.endTime }">
					<input type="hidden" id="resetTime" value="${millis }"> <input
						type="hidden" id="weekTime" value="${sum }">
					<div class="seckill clearfix start-bg">
						<div class="col-35">
							<div class="inlin-block name">
								<img src="resources/common/images/ico-kql-mx@2x.png" alt=""
									class="more-icon">冰点价
							</div>
						</div>
						<div class="col-65 text-right">
							<div class="inlin-block word-des">距离活动结束</div>
							<div class="inlin-block time-esc"></div>
						</div>
					</div>

					<div class="weui-panel self-pannel">
						<div class="weui-panel__bd">
							<c:forEach items="${iceActivity.goodsList }" var="item" begin="0"
								end="5">
								<fmt:formatDate value="${item.beginTime }" var="beginDate"
									pattern="M月d日" />
								<c:choose>
									<c:when
										test="${item.completeNum < item.allNum && ((beginDate == curr && currMill >= 43200000) || (beginDate == yesterday && currMill < 43200000))   }">
										<div class="weui-media-box weui-media-box_appmsg goods-div"
											goods-id="${item.goodsId }" activity-id="${iceActivity.id }"
											activity-type="2">
											<div class="weui-media-box__hd">
												<div class="tdate">
													<span style="top:4px;">${beginDate }</span>
													<span style="top:4px;">12:00</span>
												</div>
												<img class="weui-media-box__thumb"
													src="${not empty item.goodsImg? item.goodsImg:'resource/commons/images/defaultgoods.jpg' }">
											</div>
											<div class="weui-media-box__bd">
												<p class="weui-media-box__desc">${item.name }</p>
												<p class="text-right">
													<span class="price">¥ ${item.activityPrice }</span>
													<button type="button" class="start-bg start-btn">去抢购</button>
												</p>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div
											class="weui-media-box weui-media-box_appmsg goods-div-gray"
											activity-type="2">
											<c:if test="${item.completeNum >= item.allNum }">
												<img src="resources/common/images/buy-none.png" alt="" style="position:absolute;left:4%;top:23%;width:25%;z-index:666">
											</c:if>
											<div class="weui-media-box__hd">
												<div class="tdate tdate-g">
													<span>${beginDate }</span>
												</div>
												<img class="weui-media-box__thumb" src="${not empty item.goodsImg? item.goodsImg:'resource/commons/images/defaultgoods.jpg' }">
													<img src="resources/home/images/xqyg.png" class="xqyg-icon">
											</div>
											
											<div class="weui-media-box__bd">
												<p class="weui-media-box__desc">${item.name }</p>
												<p class="text-right">
													<span class="price color-grey">¥
														${item.activityPrice }</span>
													<button type="button" class="start-bg-g start-btn">去抢购</button>
												</p>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:if>
		</div>
		<c:if test="${not empty daydayActivity}">
			<div class="rob">
				<div class="seckill clearfix center-bgdes-y">
					<div class="col-35">
						<div class="inlin-block name">
							<img src="resources/common/images/ico-kql-tj@2x.png" alt=""
								class="more-icon">天天特价
						</div>
					</div>
					<div class="col-65 text-right" style="text-align: right;">
						<div class="inlin-block word-des">距离活动结束</div>
						<div class="inlin-block time-esc1"></div>
					</div>
				</div>
				<div class="clearfix shop-list row4">
					<c:forEach items="${daydayActivity.goodsList }" var="item"
						begin="0" end="5">
						<fmt:formatDate value="${item.beginTime }" var="beginDate"
							pattern="M月d日" />
						<c:choose>
							<%-- <c:when test="${item.completeNum < item.allNum && ((beginDate == curr && currMill >= 43200000) || (beginDate == yesterday && currMill < 43200000))   }"> --%>
							<c:when
								test="${item.completeNum < item.allNum && beginDate == curr }">
								<div class="col-49 container-white goods-div"
									goods-id="${item.goodsId }" activity-id="${daydayActivity.id }"
									activity-type="2">
									<div class="day-sign"><img src="resources/common/images/img-tttj@2x.png"/></div>
									<img
										src="${not empty item.goodsImg? item.goodsImg :'resource/commons/images/defaultgoods.jpg' }"
										alt="" class="shop-img">
									<p class="shop-name">${item.name }</p>
									<p class="shop-list-foot">
										<span class="price">¥ ${item.activityPrice } </span>
										<button type="button" class="start-bg start-btn de-start-btn">立即抢购</button>
									</p>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-49 container-white" activity-type="2">
								<c:if test="${item.completeNum >= item.allNum }">
									<img src="resources/common/images/buy-none.png" alt="" style="position:absolute;left:6%;top:10%;width:88%;z-index:666">
								</c:if>
								<div class="day-sign"><img src="resources/common/images/img-tttj@2x.png"/></div>
								<img src="resources/home/images/xqyg.png" class="xqyg-icon">
									<img
										src="${not empty item.goodsImg? item.goodsImg :'resource/commons/images/defaultgoods.jpg' }"
										alt="" class="shop-img">
									<p class="shop-name">${item.name }</p>
									<p class="shop-list-foot">
										<span class="price color-grey">¥ ${item.activityPrice }
										</span>
										<button type="button"
											class="start-bg-g start-btn de-start-btn">立即抢购</button>
									</p>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty buyAndPresent }">
			<div class="rob">
				<div class="seckill clearfix center-bgdes-qr">
					<div class="col-35">
						<div class="inlin-block name">
							<img src="resources/activity/images/ico-kql-mjz.png" alt=""
								class="more-icon mjz-icon">买即赠
						</div>
					</div>
					<div class="col-65 text-right" style="text-align: right;">
						<div class="inlin-block word-des">距离活动结束</div>
						<div class="inlin-block time-esc3">
							<!-- <span class="hours">03</span> : <span class="miu">09</span> : <span
								class="s">09</span> -->
						</div>
					</div>
				</div>
				<div class="shop-list">
					<c:forEach items="${buyAndPresent }" var="item" begin="0" end="2">
						<input type="hidden" class="buyAndPresent-endTime" value='<fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />'>
						<div class="shop-item goods-div" goods-id="${item.mainGoods.id }"
							activity-id="${item.id }" activity-type="0">
							<div class="clearfix">
								<div class="col-50 buy-present" goods-id="${item.mainGoods.id }">
									<div class="half-circle">
										<img alt="" src="resources/activity/images/rob-song.png">
									</div>
									<img
										src="${not empty item.mainGoods.imgPath? item.mainGoods.imgPath:'resource/commons/images/defaultgoods.jpg' }"
										alt="" class="shop-img">
									<p class="shop-name">${item.mainGoods.fullName }</p>
								</div>
								<div class="col-50 buy-present" goods-id="${item.giftGoods.id }">
									<div class="half-circle2">
										<img alt="" src="resources/activity/images/rob-buy.png">
									</div>
									<img
										src="${not empty item.giftGoods.imgPath? item.giftGoods.imgPath:'resource/commons/images/defaultgoods.jpg' }"
										alt="" class="shop-img">
									<p class="shop-name">${item.giftGoods.fullName }</p>
								</div>
							</div>
							<div class="shop-list-foot2">
								<span class="prcie">¥ ${item.mainGoods.price }</span><span
									class="word-des">节省了${item.giftGoods.price }</span>
								<button type="button" class="start-bg start-btn lg-start-btn">立即抢购</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty weekActivity}">
			<div class="rob">
				<div class="seckill clearfix center-bgdes-g">
					<div class="col-35">
						<div class="inlin-block name">
							<img src="resources/common/images/ico-kql-mztj@2x.png" alt=""
								class="more-icon">每周特价
						</div>
					</div>
					<div class="col-65 text-right" style="text-align: right;">
						<div class="inlin-block word-des">距离活动结束</div>
						<div class="inlin-block time-esc2"></div>
					</div>
				</div>
				<div class="clearfix shop-list row4">
					<c:forEach items="${weekActivity.goodsList }" var="item" begin="0"
						end="5">
						<div class="col-33 container-white goods-div"
							goods-id="${item.goodsId }" activity-id="${weekActivity.id }"
							activity-type="2">
							<img
								src="${not empty item.goodsImg? item.goodsImg:'resource/commons/images/defaultgoods.jpg' }"
								alt="" class="shop-img">
							<p class="shop-name">${item.name }</p>
							<p
								class="shop-list-foot clearfix ${item.allNum <= item.completeNum? '':'left-price-r' }">
								<span class="price">¥ ${item.activityPrice } </span>
							</p>
							<p class="text-center">
								<button type="button" class="start-bg start-btn sm-start-btn">立即抢购</button>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty woleActivity}">
			<div class="rob">
				<div class="seckill clearfix center-bgdes-q">
					<div class="col-35">
						<div class="inlin-block name">
							<img src="resources/common/images/ico-kql-zjh@2x.png" alt=""
								class="more-icon">整件惠
						</div>
					</div>
				</div>
				<div class="clearfix shop-list row4">
					<c:forEach items="${woleActivity.goodsList }" 
						var="item">
						<div class="col-33 container-white goods-div"
							goods-id="${item.goodsId }" activity-id="${woleActivity.id }"
							activity-type="2">
							<img
								src="${not empty item.goodsImg?  item.goodsImg:'resource/commons/images/defaultgoods.jpg' }"
								alt="" class="shop-img">
							<p class="shop-name">${item.name }</p>
							<p class="shop-list-foot clearfix ">
								<span class="price">¥${item.activityPrice } </span>
							</p>
							<p class="text-center">
								<button type="button" class="start-bg start-btn sm-start-btn">立即抢购</button>
							</p>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<div class="banner">
			<img src="resources/common/images/img-index-bt@2x.png" alt="">
		</div>
	</div>
	<!-- 公共js区域 -->
	<%@include file="/WEB-INF/jsp/common/common_js.jsp"%>
	<!-- 公共js区域完 -->
	<!-- 该页面应用js区域 -->
	<script type="text/javascript" src="resources/activity/js/activity.js"></script>
	<!-- 该页面应用js区域 -->
</body>
</html>