<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>香满圆-西部农产品电商平台,重庆菜园坝水果市场荣誉出品</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/home/index.css">
  </head>
  <body>
  		<c:if test="${not empty galleryImage }">
			<div class="outtop">
				<div class="close-icons"><i class="iconfont">&#xe676;</i></div>
				<a href="${galleryImage.data }"><img src="${galleryImage.imgPath }" alt=""></a>
			</div>
		</c:if>
  	<%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
	
	<!--全屏滚动-->
<div class="ad" style="min-width: auto;">
	<div class="b-img">
	<c:forEach items="${findPcAd.topImages }" var="topImg">
	    <c:if test="${topImg.type==1 }"><a href="${topImg.data}" style="background:url(${topImg.imgPath }) center no-repeat;"></a></c:if>
	     <c:if test="${topImg.type==0 }"><a href="goods/${topImg.data }/0/0" style="background:url(${topImg.imgPath }) center no-repeat;"></a></c:if>
	</c:forEach>
	</div>
	<div class="b-list" style="margin-top:360px;padding-top:0;"></div>
	<!--<a class="bar-left" href="#"><em></em></a><a class="bar-right" href="#"><em></em></a>-->
</div>
<!--end 全屏滚动-->
 <!--内容部分-->
<div class="container" style="margin-bottom:0px;background:#EFEFEF url('resource/commons/images/page-bg.png') 50.2% no-repeat fixed;padding-top:25px;">
	<div class="tab-nav w-1250">
		<ul>
			<li class="active">冰点价</li>
			<li>天天特价</li>
			<li>买即赠</li>
			<li>每周特价</li>
			<li>热卖商品</li>
			<li>新品上市</li>
			<li>热销好货</li>
		</ul>
	</div>
	<input type="text" value="<fmt:formatDate value="${time }" pattern="yyyy/MM/dd HH:mm:ss"/>" style="display: none;" id="daoji"/>
	<div class="tab-content clearfix w-1250">
		<!-- 冰点价 -->
		<div class="point-all" style="height:330px;">
			<div class="time-esc"><img src="resource/commons/images/ico-kql-mx@2x.png" alt="" class="time-icon"><span class="cost" style="color: black;">距离活动结束还有 : <span id="freeActivityTime"></span><span class="date-cost">(每日<span>12:00</span>-次日<span>12:00</span>开抢)</span></div>
			<ul class="rob-list">
				<c:forEach items="${iceActivity.goodsList }" var="activityGoods">
					<c:if test="${activityGoods.isToday==0 }">
							<li class="list-item active">
							<c:if test="${activityGoods.allNum > activityGoods.completeNum }"> 
							<a href="goods/${activityGoods.goodsId}/2/${iceActivity.id }?time=${activityGoods.beginTimeStr }&url=">
						</c:if>
								<span class="date-img">
									<div class="tl-time" style="background: url('resource/commons/images/date-img22.png') no-repeat;background-size: 100% auto;"><p>${activityGoods.beginTimeStr }</p><p>12:00</p></div>
								</span>
								<div class="img-wrap">
									<c:if test="${empty activityGoods.goodsImg }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty activityGoods.goodsImg }"><img:imghandle w="173" imgurl="${activityGoods.goodsImg }" h="173"  cl="shop-img"></img:imghandle></c:if>
        
									
									<c:if test="${activityGoods.completeNum >=  activityGoods.allNum}"> 
					                   	<div class="item-overlay">
					                        <img src="resource/commons/images/buy-none.png" alt="">
					                    </div>
			                  		 </c:if>
								</div>
								<div class="rob-last">
									<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${activityGoods.activityPrice }" pattern="#.00"/></span> </div>
								</div>
								<div class="shop-name" title="${activityGoods.name }">${activityGoods.name }</div>
								<div class="rob-last">
									<c:if test="${activityGoods.allNum > activityGoods.completeNum }"> 
									<button type="button" class="btn-rob-g re">立即购买</button>
									</c:if>
									<c:if test="${activityGoods.allNum <= activityGoods.completeNum }">
									<button type="button" class="btn-rob-g">立即购买</button>
									</c:if> 
								</div>
							<c:if test="${activityGoods.allNum > activityGoods.completeNum }"> 
							</a>
							</c:if>
							</li>
					</c:if>
					<c:if test="${activityGoods.isToday!=0 }">
							<li class="list-item">
								<%-- <a href="goods/${activityGoods.goodsId}/2/${iceActivity.id }?time=${activityGoods.beginTimeStr }&url="> --%>
									<span class="date-img">
										<div class="tl-time" style="background: url('resource/commons/images/date-img22.png') no-repeat;background-size: 100% auto;"><p>${activityGoods.beginTimeStr }</p><p>12:00</p></div>
									</span>
									<div class="img-wrap">
										
									<c:if test="${empty activityGoods.goodsImg  }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty activityGoods.goodsImg  }"><img:imghandle w="173" imgurl="${activityGoods.goodsImg  }" h="173"  cl="shop-img"></img:imghandle></c:if>
        
									
										<img src="resource/commons/images/xqyg.png" alt="" class="xqyg">
									</div>
									<div class="rob-last">
										<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${activityGoods.activityPrice }" pattern="#.00"/></span> </div>
									</div>
									<div class="shop-name" title="${activityGoods.name }">${activityGoods.name }</div>
									<div class="rob-last">
										<button type="button" class="btn-rob-g">立即购买</button>
									</div>
								<!-- </a> -->
							</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<!-- 天天特价 -->
		<div class="point-all" style="height:330px;">
			<div class="time-esc"><img src="resource/commons/images/ico-kql-mx@2x.png" alt="" class="time-icon"><span class="cost" style="color: black;">距离活动结束 : <span id="dayActivityTime">01</span></span><span class="date-cost">(每天<span>0</span>点开抢)</span></div>
			<ul class="rob-list">
				<c:forEach items="${daydayActivity.goodsList }" var="activityGoods">
				<c:if test="${activityGoods.isToday == 0 }">
						<li class="list-item active">
						<c:if test="${activityGoods.allNum > activityGoods.completeNum }"> 
						<a href="goods/${activityGoods.goodsId}/2/${daydayActivity.id }?time=&url=${daydayActivity.imgPath}">
						</c:if> 
							<span class="date-img">
								<img src="${daydayActivity.imgPath}" alt="">
							</span>
							<div class="img-wrap">
								<c:if test="${empty activityGoods.goodsImg }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty activityGoods.goodsImg }"><img:imghandle w="173" imgurl="${activityGoods.goodsImg }" h="173"  cl="shop-img"></img:imghandle></c:if>
        
								
								<c:if test="${activityGoods.completeNum >=  activityGoods.allNum}"> 
				                   	<div class="item-overlay">
				                        <img src="resource/commons/images/buy-none.png" alt="">
				                    </div>
		                  		 </c:if>
							</div>
							<div class="rob-last">
								<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${activityGoods.activityPrice }" pattern="#.00"/></span> </div>
							</div>
							<div class="shop-name" title="${activityGoods.name }">${activityGoods.name }</div>
							<div class="rob-last">
								<c:if test="${activityGoods.allNum > activityGoods.completeNum }"> 
									<button type="button" class="btn-rob-g re">立即购买</button>
								</c:if>
								<c:if test="${activityGoods.allNum <= activityGoods.completeNum }">
									<button type="button" class="btn-rob-g">立即购买</button>
								</c:if> 
							</div>
						<c:if test="${activityGoods.allNum > activityGoods.completeNum }"> 
						</a>
						</c:if>
						</li>
				</c:if>
				<c:if test="${activityGoods.isToday != 0 }">
					<li class="list-item">
							<span class="date-img">
								<img src="${daydayActivity.imgPath}" alt="">
							</span>
							<div class="img-wrap">
								<c:if test="${empty activityGoods.goodsImg }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty activityGoods.goodsImg }"><img:imghandle w="173" imgurl="${activityGoods.goodsImg }" h="173"  cl="shop-img"></img:imghandle></c:if>
								<img src="resource/commons/images/xqyg.png" alt="" class="xqyg">
							</div>
							<div class="rob-last">
								<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${activityGoods.activityPrice }" pattern="#.00"/></span> </div>
							</div>
							<div class="shop-name" title="${activityGoods.name }">${activityGoods.name }</div>
							<div class="rob-last">
								<button type="button" class="btn-rob-g">立即购买</button>
							</div>
					</li>
				</c:if>
				</c:forEach>
			</ul>
		</div>
		<!-- 买即赠  -->
		<div class="point-all" style="height:330px;">
			<ul class="rob-list rob-list2">
				<c:forEach items="${buyAndPresentActivity }" var="buyAndPresent">
				<input type="hidden" value="<fmt:formatDate value="${buyAndPresent.endTime }" pattern="yyyy/MM/dd HH:mm:ss"/>" class="buyAndPersent"/>
					<a class="buyandparent" href="goods/${buyAndPresent.buyGoods.id}/0/${buyAndPresent.id }">
						<li class="list-item list-item2 active">
							<div class="p-text" style="color: black;">
								距离活动结束还有 : <span class="showTime"></span>
							</div>
							<div class="give-buy clearfix">
								<div class="img-wrap">
								<c:if test="${empty buyAndPresent.buyGoods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty buyAndPresent.buyGoods.imgPath }"><img:imghandle w="178" imgurl="${buyAndPresent.buyGoods.imgPath }" h="178"  cl="shop-img"></img:imghandle></c:if>
								<div class="robin"><span>买</span></div></div>
								<div class="img-wrap">
								<c:if test="${empty buyAndPresent.presentGoods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty buyAndPresent.presentGoods.imgPath }"><img:imghandle w="179" imgurl="${buyAndPresent.presentGoods.imgPath }" h="179"  cl="shop-img"></img:imghandle></c:if>
								<div class="robin robin2"><span>赠</span></div></div>
								<div class="shop-name col-50" title="${buyAndPresent.buyGoods.name }">${buyAndPresent.buyGoods.name }</div>
								<div class="shop-name col-50" title="${buyAndPresent.presentGoods.name }">${buyAndPresent.presentGoods.name }</div>
							</div>
							<div class="rob-last" style="text-align:center;padding:0">
								<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${buyAndPresent.buyGoods.price }" pattern="#.00"/></span> <small class="left-price-small add-lineh">节省了<fmt:formatNumber value="${buyAndPresent.presentGoods.price }" pattern="#.00"/></small></div><button type="button" class="btn-rob-g re">立即购买</button>
							</div>
						</li>
					</a>
				</c:forEach>
			</ul>
		</div>
		<!-- 每周特价 -->
		<div class="point-all" style="height:330px;">
			<input type="hidden" value="<fmt:formatDate value="${endDayOfWeek }" pattern="yyyy/MM/dd HH:mm:ss"/>" id="weekActivity"/>
			<div class="time-esc"><img src="resource/commons/images/ico-kql-mx@2x.png" alt="" class="time-icon"><span class="cost" style="color: black;">距离活动结束 : <span id="weekActivityTime">01</span></div>
			<ul class="rob-list">
				<c:forEach items="${weekActivity.goodsList }" var="activityGoods">
							<li class="list-item active">
								<c:if test="${activityGoods.allNum > activityGoods.completeNum }">
								<a href="goods/${activityGoods.goodsId}/2/${weekActivity.id }?time=&url=${weekActivity.imgPath }">
								</c:if>
										<span class="date-img">
											<c:if test="${empty weekActivity.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty weekActivity.imgPath }"><img:imghandle w="173" imgurl="${weekActivity.imgPath }" h="173"  cl="shop-img"></img:imghandle></c:if>
											
										</span>
										<div class="img-wrap">
											<c:if test="${empty activityGoods.goodsImg }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty activityGoods.goodsImg }"><img:imghandle w="173" imgurl="${activityGoods.goodsImg }" h="173"  cl="shop-img"></img:imghandle></c:if>
										<c:if test="${activityGoods.completeNum >=  activityGoods.allNum}"> 
						                   	<div class="item-overlay">
						                        <img src="resource/commons/images/buy-none.png" alt="">
						                    </div>
				                  		 </c:if>
										</div>
										<div class="rob-last">
											<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${activityGoods.activityPrice }" pattern="#.00"/></span> </div>
										</div>
										<div class="shop-name" title="${activityGoods.name }">${activityGoods.name }</div>
										<div class="rob-last">
											<c:if test="${activityGoods.allNum > activityGoods.completeNum }"> 
												<button type="button" class="btn-rob-g re">立即购买</button>
											</c:if>
											<c:if test="${activityGoods.allNum <= activityGoods.completeNum }">
												<button type="button" class="btn-rob-g">立即购买</button>
											</c:if> 
										</div>
								<c:if test="${activityGoods.allNum > activityGoods.completeNum }">		
								</a>
								</c:if>
							</li>
				</c:forEach>
			</ul>
		</div>
		<!-- 热卖商品 -->
		<div class="point-all" style="height:330px;">
			<ul class="rob-list rob-list-top">
				<c:forEach items="${hotGoods }" var="goods">
						<li class="list-item active" >
					<a href="goods/${goods.goodsId}/0/0?time=&url=resource/commons/images/rm.png">
							<span class="date-img">
								<img src="resource/commons/images/rm.png" alt=""><!-- 水印图片 -->
							</span>
							<div class="img-wrap">
								<c:if test="${empty goods.goodsImage }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.goodsImage }"><img:imghandle w="173" imgurl="${goods.goodsImage }" h="173"  cl="shop-img"></img:imghandle></c:if>
        
							</div>
							<div class="rob-last">
								<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${goods.goodsPrice }" pattern="#.00"/></span> </div>
							</div>
							<div class="shop-name" title="${goods.goodsName }">${goods.goodsName }</div>
							<div class="rob-last rob-last-top">
								<button type="button" class="btn-rob-g re">立即购买</button>
							</div>
					</a>
						</li>
				</c:forEach>
			</ul>
		</div>
		<!-- 新品上市 -->
		<div class="point-all" style="height:330px;">
			<ul class="rob-list rob-list-top">
				<c:forEach items="${newGoods }" var="goods">
						<li class="list-item active">
					<a href="goods/${goods.goodsId}/0/0?time=&url=resource/commons/images/xpss.png">
							<span class="date-img">
								<img src="resource/commons/images/xpss.png" alt=""><!-- 水印图片 -->
							</span>
							<div class="img-wrap">
								<c:if test="${empty goods.goodsImage }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.goodsImage }"><img:imghandle w="173" imgurl="${goods.goodsImage }" h="173"  cl="shop-img"></img:imghandle></c:if>
							</div>
							<div class="rob-last">
								<div class="left-price left-price-r"><span class="zh-money">¥</span> <span class="left-price-choos"><fmt:formatNumber value="${goods.goodsPrice }" pattern="#.00"/></span> </div>
							</div>
							<div class="shop-name" title="${goods.goodsName }">${goods.goodsName }</div>
							<div class="rob-last rob-last-top">
								<button type="button" class="btn-rob-g re">立即购买</button>
							</div>
					</a>
						</li>
				</c:forEach>
			</ul>
		</div>
		<!-- 热销好货 -->
		<div class="point-all" style="height: 330px;">
			<div class="wrap-point" >
				<a href="goods/${sealGoods0.goodsId}/0/0" style="color:#666666;text-decoration:none;" >
					<div class="point-price point1" style=" height:139px;background-image: url(${sealGoods0.goodsImage })">
						<div class="point-name" title="${sealGoods0.goodsName }" >${sealGoods0.goodsName }</div>
						<p class="price">¥ <span><fmt:formatNumber value="${sealGoods0.goodsPrice }" pattern="#.00"/></span></p>
					</div>
				</a>
				<a href="goods/${sealGoods1.goodsId}/0/0" style="color:#666666;text-decoration:none;">
					<div class="point-price point2" style="margin-top: 12px;height:139px;background-image: url(${sealGoods1.goodsImage })">
						<div class="point-name" title="${sealGoods1.goodsName }">${sealGoods1.goodsName }</div>
						<p class="price">¥ <span><fmt:formatNumber value="${sealGoods1.goodsPrice }" pattern="#.00"/></span></p>

					</div>
				</a>
			</div>
			<div class="wrap-point">
				<a href="goods/${sealGoods2.goodsId}/0/0" style="color:#666666;text-decoration:none;">
					<div class="point-price point3 lg-point-price" style="height: 310px;background-image: url(${sealGoods2.goodsImage })">
						<div class="point-name" title="${sealGoods2.goodsName }">${sealGoods2.goodsName }<p class="price">¥ <span><fmt:formatNumber value="${sealGoods2.goodsPrice }" pattern="#.00"/></span></p></div>
						
					</div>
				</a>
			</div>
			<div class="wrap-point">
				<a href="goods/${sealGoods3.goodsId}/0/0" style="color:#666666;text-decoration:none;">
					<div class="point-price point4" style="height:139px;background-image: url(${sealGoods3.goodsImage })">
						<div class="point-name" title="${sealGoods3.goodsName }">${sealGoods3.goodsName }</div>
						<p class="price">¥ <span><fmt:formatNumber value="${sealGoods3.goodsPrice }" pattern="#.00"/></span></p>
						
					</div>
				</a>
				<a href="goods/${sealGoods4.goodsId}/0/0" style="color:#666666;text-decoration:none;">
					<div class="point-price point5" style="margin-top: 12px;height:139px;background-image: url(${sealGoods4.goodsImage })">
						<div class="point-name" title="${sealGoods4.goodsName }">${sealGoods4.goodsName }</div>
						<p class="price">¥ <span><fmt:formatNumber value="${sealGoods4.goodsPrice }" pattern="#.00"/></span></p>
					</div>
				</a>
			</div>
			<div class="wrap-point">
				<a href="goods/${sealGoods5.goodsId}/0/0" style="color:#666666;text-decoration:none;">
					<div class="point-price point6 lg-point-price" style="height: 310px;background-image:url(${sealGoods5.goodsImage });">
						<div class="point-name" title="${sealGoods5.goodsName }">${sealGoods5.goodsName }<p class="price">¥ <span><fmt:formatNumber value="${sealGoods5.goodsPrice }" pattern="#.00"/></span></p></div>
						
					</div>
				</a>
			</div>
		</div>
	</div>
	<div class="activity-img w-1250">
		<div class="sing clearfix sing-bg" style="background-image:url('${firstImage8.imgPath }')">
			
		</div>
		<div class="chunk"><%--0商品，1跳转链接--%>
		<c:if test="${firstImage0.type==1}"><a href="${firstImage0.data}"><img src="${firstImage0.imgPath }"/></a></c:if>
		<c:if test="${firstImage0.type==0}"><a href="goods/${firstImage0.data }/0/0"><img src="${firstImage0.imgPath }"/></a></c:if>
		</div>
		<div class="chunk">
			<div class="top">
				<c:if test="${firstImage1.type==1}"><a href="${firstImage1.data}"><img src="${firstImage1.imgPath }"/></a></c:if>
	            <c:if test="${firstImage1.type==0}"><a href="goods/${firstImage1.data }/0/0"><img src="${firstImage1.imgPath }"/></a></c:if>
            </div>
			
			<div class="top">
				<c:if test="${firstImage2.type==1}"><a href="${firstImage2.data}"><img src="${firstImage2.imgPath }"/></a></c:if>
	            <c:if test="${firstImage2.type==0}"><a href="goods/${firstImage2.data }/0/0"><img src="${firstImage2.imgPath }"/></a></c:if></div>
		  </div>
		<div class="chunk">
			<div class="top">
				<c:if test="${firstImage3.type==1}"><a href="${firstImage3.data}"><img src="${firstImage3.imgPath }"/></a></c:if>
	             <c:if test="${firstImage3.type==0}"><a href="goods/${firstImage3.data }/0/0"><img src="${firstImage3.imgPath }"/></a></c:if>
	        </div>
	             
			<div class="top">
			     <c:if test="${firstImage4.type==1}"><a href="${firstImage4.data}"><img src="${firstImage4.imgPath }"/></a></c:if>
                <c:if test="${firstImage4.type==0}"><a href="goods/${firstImage4.data }/0/0"><img src="${firstImage4.imgPath }"/></a></c:if>
             </div>
		</div>
		<div class="chunk">
			<c:if test="${firstImage5.type==1}"><a href="${firstImage5.data}"><img src="${firstImage5.imgPath }"/></a></c:if>
	        <c:if test="${firstImage5.type==0}"><a href="goods/${firstImage5.data }/0/0"><img src="${firstImage5.imgPath }"/></a></c:if>
        </div>
		<div class="chunk">
			<div class="top">
				<c:if test="${firstImage6.type==1}"><a href="${firstImage6.data}"><img src="${firstImage6.imgPath }"/></a></c:if>
	            <c:if test="${firstImage6.type==0}"><a href="goods/${firstImage6.data }/0/0"><img src="${firstImage6.imgPath }"/></a></c:if>
            </div>
			<div class="top">
				<c:if test="${firstImage7.type==1}"><a href="${firstImage7.data}"><img src="${firstImage7.imgPath }"/></a></c:if>
	            <c:if test="${firstImage7.type==0}"><a href="goods/${firstImage7.data }/0/0"><img src="${firstImage7.imgPath }"/></a></c:if>
            </div>
		</div>
	</div>
	<!-- 货柜商品 -->
	<div class="lump w-1250">
	<c:forEach items="${container }" var="contain">
	<c:if test="${contain.type == 0 }">
	<div class="w-1250 classify">
			<div class="sing clearfix"><span class="big" value="${contain.id }" pageindex="1"><i class="iconfont"></i>换一组</span></div>
			<div class="chunk-d fenlei4" style="background:url(${contain.bgImg}) no-repeat;background-size: 100% 100%;">
				<ul class="chunk-d-ul bigContain">
					<c:forEach items="${contain.keyWordsList }" var="category" varStatus="i">
						<c:if test="${i.count<5&&!i.last}">
						  <li  class="chunk-d-li" value="${contain.name }">${category }</li>
						</c:if>
						<c:if test="${i.last}">
						  <li  class="chunk-d-li" value="${contain.name }">${category }</li><a href="elastic/searchTypeGoods?id=${contain.categoryId }" style="text-decoration:none;"><li  class="chunk-d-li" style="color: red;">更多</li></a>
                        </c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="chunk-z" value="${contain.id }">
				<ul class="chunk-z-ul bigul">
					
				</ul>
			</div>
			<div class="chunk-x">
				<c:forEach items="${contain.imageList }" var="imglist">
					<div class="chunk-x-top">
					<c:if test="${imglist.type==1}"><a href="${imglist.data}"><img src="${imglist.imgPath }"/></a></c:if>
					<c:if test="${imglist.type==0}"><a href="goods/${imglist.data }/0/0"><img src="${imglist.imgPath }"/></a></c:if>
					</div>
				</c:forEach>
			</div>
	</div>
	</c:if>
	<c:if test="${contain.type == 1 }">
		<div class="clearfix wrap-lump">
			<div class="exchange"><div class="sing"><span class="left" value="${contain.id }" pageindex="1"><i class="iconfont"></i>换一组</span></div></div>
			<div class="lump-left lump-content" style="background:url(${contain.bgImg}) no-repeat;background-size: 100% 100%;">
			<ul class="chunk-d-ul smallContain">
				<c:forEach items="${contain.keyWordsList }" var="category" varStatus="i">
						<c:if test="${i.count<5&&!i.last}">
                          <li  class="chunk-d-li" value="${contain.name }">${category }</li>
                        </c:if>
                        <c:if test="${i.last}">
                          <a href="elastic/searchTypeGoods?id=${contain.categoryId }" style="text-decoration:none;"><li  class="chunk-d-li" style="color: red;">更多</li></a>
                        </c:if>
				</c:forEach>
			</ul>
		
			<div class="content" value="${contain.id }">
				<ul class="list-ul smallul">
					
				</ul>
			</div>
		</div>
		</div>
	</c:if>
	</c:forEach>
	</div>
	   <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
</div>
<%@include file="/WEB-INF/jsp/commons/nav/search_float_top.jsp" %>
<!--2017-10-31 新增左部浮现搜索-->
<div class="float-icon">
<div class="float-icon-overlay"></div>
	<ul class="float-icon-content">
		<li class="float-icon-item"><span class="icon-item icon-item1"></span><span class="icon-word icon-word1">进口水果</span></li>
		<li class="float-icon-item"><span class="icon-item icon-item2"></span><span class="icon-word icon-word2">国产水果</span></li>
		<li class="float-icon-item"><span class="icon-item icon-item3"></span><span class="icon-word icon-word3">跨境专区</span></li>
		<li class="float-icon-item"><span class="icon-item icon-item4"></span><span class="icon-word icon-word4">干副干果</span></li>
		<li class="float-icon-item"><span class="icon-item icon-item5"></span><span class="icon-word icon-word5">小吃零食</span></li>
		<li class="float-icon-item"><span class="icon-item icon-item6"></span><span class="icon-word icon-word6">粮油副食</span></li>
		<li class="float-icon-item"><span class="icon-item icon-item7"></span><span class="icon-word icon-word7">生活用品</span></li>
		<li class="float-icon-item"><span class="icon-item icon-item8"></span><span class="icon-word icon-word8">酒水饮料</span></li>
	</ul>
</div>
<!--2017-10-31 新增左部浮现搜索end-->
  <script type="text/javascript" src="resource/js/home/index.js"></script>
  <script type="text/javascript" src="resource/js/common/goods_search.js"></script>
  <script src="resource/commons/js/sli.js"></script>
  <script type="text/javascript">
  $(function(){
	  $(".floattop-logo").hover(function(){
		 $(this).find(".net-nav").fadeIn(300); 
	  },function(){
		  
		  $(this).find(".net-nav").hide(); 
	  });
      $(".float-icon-item").mouseover(function (e) {
          e.stopPropagation();
          $(this).find(".icon-item").hide().next().show();
      });
      $(".float-icon-item").mouseout(function (e) {
          e.stopPropagation();
          $(this).find(".icon-item").show().next().hide();
      });

     /*  var lump = $(".lump").offset().top; */
      var classify0 = $(".classify").eq(0).offset().top;
      var classify1 = $(".classify").eq(1).offset().top;
      var classify2 = $(".classify").eq(2).offset().top;
      var classify3 = $(".classify").eq(3).offset().top;
      var classify4 = $(".classify").eq(4).offset().top;
      var classify5 = $(".classify").eq(5).offset().top;
      var classify6 = $(".classify").eq(6).offset().top;
      var lumpp = $(".wrap-lump").offset().top;
          
          $('.float-icon .icon-word1').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:classify0-50}, 500);});
          $('.float-icon .icon-word2').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:classify1-50}, 500);});
          $('.float-icon .icon-word3').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:classify2-50}, 500);});
          $('.float-icon .icon-word4').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:classify3-50}, 500);});
          $('.float-icon .icon-word5').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:lumpp-50}, 500);});
          $('.float-icon .icon-word6').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:classify4-50}, 500);});
          $('.float-icon .icon-word7').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:classify5-50}, 500);});
          $('.float-icon .icon-word8').click(function(e){e.stopPropagation();$('html,body').animate({scrollTop:classify6-50}, 500);});
  })
  
  
  
  </script>
  </body>
</html>