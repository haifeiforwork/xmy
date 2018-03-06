<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%-- 菜单选项 --%>
<div class="dNav">
	<div class="list">
		<div class="Lnav">
			<ul class="list-ul clearfix">
				<li class="front list-li">
					<span class="all"><i class="iconfont" style="font-weight:100;display: none;" id="one">&#xe600;</i><i class="iconfont"  id="two" style="display: none;">&#xe60d;</i>全部商品分类</span>
					<ul class="classify-ul classify-ul-all" style="display:none;">
					<c:forEach items="${sessionScope.goodsNavigated }" var="termData" >
						 <c:if test="${termData.isShow!=1 }">
								<li class="classify-li"><div class="publiccategory" value="${termData.id }"><i class="icon icon1" style="background:url(${termData.icon}) no-repeat;"></i>${termData.name }</div>
									<div class="meau">
										<div class="overlay"></div>
										<ul class="meau-content clearfix">
											<c:forEach items="${termData.childTermData }" var="child">
												<c:if test="${child.status==0 }">
				                                	<li class="meau-item">
				                                		<a href="elastic/typeGoods?typeId=${child.id }" style="text-decoration: none;color: black;display: block;width: 190px;height: 35px;"><div class="meau-item-title"><img src="${child.icon }" alt="">${child.name }</div></a> 
														<div class="meau-item-list">
															<c:forEach items="${child.childCategory }" var="category">
				                                				<a href="elastic/typeGoods?typeId=${child.id }&wordId=${category.id }">${category.wordSeg }</a>
				                               				</c:forEach>
														</div>
				                                	</li>
			                                	</c:if>
			                                </c:forEach>
										</ul>
										<a href="elastic/topGoods?oneId=${termData.id}" class="all-thetype">全部分类 > ></a>
										<c:if test="${not empty termData.backgroundImgUrl }">
											<a href="goods/${termData.backgroundImgUrl }/0/0"><span class="thepic" style="background:url(${termData.pcBackgroudImg}) no-repeat"></span></a>
										</c:if>
									</div>
								</li>
						</c:if>
					</c:forEach>
					</ul>
				</li>
				<li class="nav-section">
					<span class="right">></span>
					<c:if test="${(param.show==null||param.show==1) }">
						<ul class="clearfix">
							<li class="list-li btn menu cross"><a href="index?show=1">首页</a></li>
							<li class="list-li btn menu"><a href="activity/findActivity?show=2">开抢啦</a></li>
							<li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
							<!-- <li class="list-li btn menu" onclick="window.location='fruits/index?show=4'">果琳专区</li> -->
							<li class="list-li btn menu"><a href="elastic/goods?goodsName=&mainLand=0&show=3">全国配送</a></li>
							<li class="list-li btn menu"><a href="point/pointStore?show=6">积分商城</a></li>
							<li class="list-li btn menu"><a href="news/zt/20171118secondpage">企业定制</a></li>
							<li class="list-li btn menu"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
					<c:if test="${param.show==2 }">
						<ul class="clearfix">
							<li class="list-li btn menu"><a href="index?show=1">首页</a></li>
                            <li class="list-li btn menu cross"><a href="activity/findActivity?show=2">开抢啦</a></li>
                            <li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
                            <!-- <li class="list-li btn menu" onclick="window.location='fruits/index?show=4'">果琳专区</li> -->
                            <li class="list-li btn menu"><a href="elastic/goods?goodsName=&mainLand=0&show=3">全国配送</a></li>
                            <li class="list-li btn menu"><a href="point/pointStore?show=6">积分商城</a></li>
                            <li class="list-li btn menu"><a href="news/zt/20171118secondpage">企业定制</a></li>
                            <li class="list-li btn menu"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
					<c:if test="${param.show==3 }">
						<ul class="clearfix">
							<li class="list-li btn menu"><a href="index?show=1">首页</a></li>
                            <li class="list-li btn menu"><a href="activity/findActivity?show=2">开抢啦</a></li>
                            <li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
                            <!-- <li class="list-li btn menu" onclick="window.location='fruits/index?show=4'">果琳专区</li> -->
                            <li class="list-li btn menu cross"><a href="elastic/goods?goodsName=&mainLand=0&show=3&show=3">全国配送</a></li>
                            <li class="list-li btn menu"><a href="point/pointStore?show=6">积分商城</a></li>
                            <li class="list-li btn menu"><a href="news/zt/20171118secondpage">企业定制</a></li>
                            <li class="list-li btn menu"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
					<c:if test="${param.show==4 }">
						<ul class="clearfix">
							<li class="list-li btn menu"><a href="index?show=1">首页</a></li>
                            <li class="list-li btn menu"><a href="activity/findActivity?show=2">开抢啦</a></li>
                            <li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
                            <li class="list-li btn menu cross"><a href="elastic/goods?goodsName=&mainLand=0&show=3">全国配送</a></li>
                            <li class="list-li btn menu"><a href="point/pointStore?show=6">积分商城</a></li>
                            <li class="list-li btn menu"><a href="news/zt/20171118secondpage">企业定制</a></li>
                            <li class="list-li btn menu"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
					<c:if test="${param.show==5 }">
						<ul class="clearfix">
							<li class="list-li btn menu"><a href="index?show=1">首页</a></li>
                            <li class="list-li btn menu"><a href="activity/findActivity?show=2">开抢啦</a></li>
                            <li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
                            <!-- <li class="list-li btn menu" onclick="window.location='fruits/index?show=4'">果琳专区</li> -->
                            <li class="list-li btn menu cross"><a href="elastic/goods?goodsName=&mainLand=0&show=3">全国配送</a></li>
                            <li class="list-li btn menu"><a href="point/pointStore?show=6">积分商城</a></li>
                            <li class="list-li btn menu"><a href="news/zt/20171118secondpage">企业定制</a></li>
                            <li class="list-li btn menu"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
					<c:if test="${param.show==6 }">
						<ul class="clearfix">
							<li class="list-li btn menu"><a href="index?show=1">首页</a></li>
                            <li class="list-li btn menu"><a href="activity/findActivity?show=2">开抢啦</a></li>
                            <li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
                            <!-- <li class="list-li btn menu" onclick="window.location='fruits/index?show=4'">果琳专区</li> -->
                            <li class="list-li btn menu"><a href="elastic/goods?goodsName=&mainLand=0&show=3">全国配送</a></li>
                            <li class="list-li btn menu cross"><a href="point/pointStore?show=6">积分商城</a></li>
                            <li class="list-li btn menu"><a href="news/zt/20171118secondpage">企业定制</a></li>
                            <li class="list-li btn menu"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
					<c:if test="${param.show==7 }">
						<ul class="clearfix">
							<li class="list-li btn menu"><a href="index?show=1">首页</a></li>
                            <li class="list-li btn menu"><a href="activity/findActivity?show=2">开抢啦</a></li>
                            <li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
                            <!-- <li class="list-li btn menu" onclick="window.location='fruits/index?show=4'">果琳专区</li> -->
                            <li class="list-li btn menu"><a href="elastic/goods?goodsName=&mainLand=0&show=3">全国配送</a></li>
                            <li class="list-li btn menu"><a href="point/pointStore?show=6">积分商城</a></li>
                            <li class="list-li btn menu cross"><a href="news/zt/20171118secondpage">企业定制</a></li>
                            <li class="list-li btn menu"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
					<c:if test="${param.show==8 }">
						<ul class="clearfix">
							<li class="list-li btn menu"><a href="index?show=1">首页</a></li>
                            <li class="list-li btn menu"><a href="activity/findActivity?show=2">开抢啦</a></li>
                            <li class="list-li btn menu"><a href="category/findCategoryGoods?id=180">跨境专区</a></li>
                            <!-- <li class="list-li btn menu" onclick="window.location='fruits/index?show=4'">果琳专区</li> -->
                            <li class="list-li btn menu"><a href="elastic/goods?goodsName=&mainLand=0&show=3">全国配送</a></li>
                            <li class="list-li btn menu"><a href="point/pointStore?show=6">积分商城</a></li>
                            <li class="list-li btn menu"><a href="news/zt/20171118secondpage">企业定制</a></li>
                            <li class="list-li btn menu cross"><a href="news/zt/aixinshop">爱心购</a></li>
						</ul>
					</c:if>
				</li>
			</ul>
			<a href="center/toShopCard?pageIndex=1&set=10&status=3">
				<div class="rr" hidden="hidden" id="imageShow"><img src="resource/commons/images/rr.png"/></div>
			</a>
		</div>
	</div>
</div>
