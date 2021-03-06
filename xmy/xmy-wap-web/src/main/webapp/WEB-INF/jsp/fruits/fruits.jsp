<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--制作lj-->
<!DOCTYPE html>
<html>
<head>
<title>精选水果</title>
<!-- 公共css区域 -->
<%@ include file="/WEB-INF/jsp/common/common_css.jsp"%>
<!-- 公共css完区域 -->
<!-- 该页面应用css区域 -->
<link href="resources/fruits/css/fruits.css" rel="stylesheet">
<!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
	<header>
		<div class="header-top">
			<div class="pull-left">
				<img src="resources/common/images/btn-fenl-back@2x.png" />
			</div>
			<div class="pull-center">
				<img src="resources/fruits/images/jx-title.png" alt=""
					class="jx-title">
			</div>
			<div class="pull-right">
				<!-- <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"/></span> -->
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
					<c:forEach items="${data.topAdInfo }" var="img">
						<div class="swiper-slide">
							<img src="${img.imgPath }" alt="" class="img-link"
								data="${img.data }" type="${img.type }">
						</div>
					</c:forEach>
				</div>
				<!-- If we need pagination -->
				<div class="swiper-pagination"></div>
			</div>
		</div>
		<div class="container">
			<div class="clearfix shop-list jx-row">
				<c:forEach items="${data.firstAdInfo }" var="firstAdInfo">
					<div class="col-33">
						<img src="${firstAdInfo.imgPath }" alt=""
							class="shop-img img-link" data="${firstAdInfo.data }"
							type="${firstAdInfo.type }">
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="container">
			<c:forEach items="${data.containers }" var="list" varStatus="idx">
				<div class="jx">
					<div class="title-line">
						<span class="center-des center-des-g"><img
							src="${list.iconImg }" alt="" style="margin-right: 0.3rem;">${list.name }</span>
						<span class="more" category-id="${list.categoryId }">更多<img
							src="resources/common/images/btn-index-right2@2x.png" alt=""></span>
					</div>
					<div class="banner">
						<img src="${list.containerAd[0].imgPath }" alt="" class="img-link"
							data="${list.containerAd[0].data }"
							type="${list.containerAd[0].type }">
					</div>
					<div class="clearfix shop-list row4">
						<c:forEach items="${list.goods }" var="goods">
							<div class="col-49 container-white goods-div"
								goods-id="${goods.id }">
								<img src="${goods.imgPath }" alt="" class="shop-img">
								<p class="shop-name">${goods.fullName }</p>
								<p class="shop-list-foot">
									<span class="price">¥ ${goods.phonePrice } </span>
								</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="banner">
			<img src="resources/common/images/img-index-bt@2x.png" alt="">
		</div>
	</div>
	<!-- 公共js区域 -->
	<%@include file="/WEB-INF/jsp/common/common_js.jsp"%>
	<!-- 公共js区域完 -->
	<!-- 该页面应用js区域 -->
	<script type="text/javascript" src="resources/fruits/js/fruits.js"></script>
	<!-- 该页面应用js区域 -->
</body>
</html>