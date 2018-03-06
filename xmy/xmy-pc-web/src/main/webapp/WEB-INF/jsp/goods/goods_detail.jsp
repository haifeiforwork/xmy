<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="img"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>${goods.name }</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/goods/goods_detail.css">
	<!-- <link rel="stylesheet" href="resource/commons/css/main.css" /> -->
	<link rel="stylesheet" href="resource/commons/css/cartscroll.css" />
	<link rel="stylesheet" href="resource/commons/css/idangerous.swiper2.7.6.css" />
  </head>
  
  <body>
  	<%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
 	<!--内容部分-->
<div class="container-white">
	<input type="text" value="<fmt:formatDate value="${time }" pattern="yyyy/MM/dd HH:mm:ss"/>" style="display: none;" id="daoji"/>
	<input type="hidden" id="isLogin" value="${isLogin }">
	<input type="hidden" id="actiId" value="${activityId }"/>
	<input type="hidden" id="typeId" value="${typeId }"/> <!-- 活动类型 -->
	<input type="hidden" id="isby" value="${param.isby }"/>
	<input type="hidden" id="isPutway" value="${goods.isPutway }">
	<input type="hidden" id="activityTypeId" value="${goods.activityType }"><!--限时限量活动类型  判断如何倒计时的 -->
	<div class="content w-1250">
		<div class="head-top">
			<a href="elastic/goods?typeName=${goods.firstTypeName }" style="color:black;"><span class="generalities" style="cursor: pointer;">${goods.firstTypeName }</span></a>>
			<a href="elastic/goods?typeName=${goods.scendTypeName },${goods.firstTypeName }" style="color:black;"><span style="cursor: pointer;">${goods.scendTypeName }</span></a>>
			<a href="elastic/goods?typeName=${goods.thirdTypeName },${goods.scendTypeName },${goods.firstTypeName }" style="color:black;"><span style="cursor: pointer;">${goods.thirdTypeName }</span></a>
		</div>
		<div class="chunk">
			<div class="chunk-left">
				<div class="fdj-wrap">
					<div class="preview">
						<div class="state-img">
							<!-- 商品水印图片 -->
							<c:choose>
								<c:when test="${not empty goods.freezingActivityTime }">
									<!-- 冰点价 -->
									<div class="tl-time" style="background: url('resource/commons/images/date-img22.png') no-repeat;background-size: 100% auto;"><p>${goods.freezingActivityTime }</p><p>12:00</p></div>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty param.url }"><img  src="${param.url }"></c:when>
										<c:otherwise>
											<c:if test="${not empty goods.waterImg }"><img  src="${goods.waterImg }"></c:if>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="vertical" class="bigImg">

							<%-- <img src="images/01-datu.jpg" width="400" height="400" alt="" id="midimg" /> --%>
							<img src="${goods.imgPath}" alt="" width="400" height="400" style="margin: 10px;" id="midimg"  />

							<div style="display:none;" id="winSelector"></div>

						</div><!--bigImg end-->

						<div class="smallImg">

							<div class="scrollbutton smallImgUp disabled"></div>

							<div id="imageMenu">

								<ul>

									<c:forEach items="${goods.imageList }" var="image">
										<li><img src="${image}" msrc="${image }" bsrc="${image }" alt="" /></li>
									</c:forEach>

								</ul>

							</div>

							<div class="scrollbutton smallImgDown"></div>

						</div><!--smallImg end-->

						<div id="bigView" style="display:none;"><img width="800" height="800" alt="" src="" /></div>

					</div>
					
				</div>
				<div class="clearfix or-shop"><span id="bookMark" style="cursor: pointer; color: ${isCollection == 0? '#999':'#358912' };" value="${goods.id }" tname="${goods.categoryName }"><i class="iconfont">&#xe88b;</i> 收藏商品<span></span></span><span class="pull-right"></span></div>
			</div>
			<div class="chunk-center">
				<h2 class="titled" style="position:relative">
					<span id="goodsName" title="${goods.name }">${goods.name }</span>
					<c:if test="${!empty goods.activityName }">
						<span style="color:red;position:absolute;right:37px;top:6px;width:auto" id="activityName">${goods.activityName }</span>
						<input type="hidden" value="<fmt:formatDate value="${goods.endTime }" pattern="yyyy/MM/dd HH:mm:ss"/>" id="endTime"/>
					</c:if>
				</h2>
				<div class="pMsg">
					<ul class="clearfix">
						<li>
						<p style="font-size: 17px;">
							<span>销售价</span>￥
							<a id="activityPrice">
								<c:if test="${!empty goods.acitivityPrice }">${goods.acitivityPrice }</c:if>
								<c:if test="${ empty goods.acitivityPrice }">${goods.price }</c:if>
							</a>/${goods.unitName } 
						</p>
						</li>
						<c:if test="${!empty goods.acitivityPrice && goods.activityType!=4 && goods.activityType!=5}">
							<li style="margin-left:20px;margin-top:10px;">
							<p style="background: #40a231;padding: 5px 15px;font-size: 14px;-moz-border-radius: 15px;-webkit-border-radius: 15px;border-radius: 15px;" id="time"></p>
							</li>
						</c:if>
					</ul>
				</div>
				<div class="xsum">
					<ul class="clearfix">
						<li>
							<p class="tit">
							<c:if test="${goods.sumDeal==null }">0</c:if>
							<c:if test="${goods.sumDeal!=null }">${goods.sumDeal }</c:if>
							</p>
							<p>累计销量</p>
						</li>
						<li>
							<p class="tit">
								<c:if test="${goods.sumComment==null }">0</c:if>
								<c:if test="${goods.sumComment!=null }">${goods.sumComment }</c:if>
							</p>
							<p>累计评价</p>
						<div class="xinD"><span style="width:98%"></span></div>
						</li>
						<li class="end">
							<p class="tit">${goods.points }<%-- <fmt:formatNumber value="${goods.price }" pattern="#"/> --%></p><p>送香满圆积分</p>
						</li>
					</ul>
				</div>
				<div class="qss">
					<p><span class="tit">商品编码</span><span>${goods.code }</span></p>
					<p><span class="tit">商家</span><span><a href="elastic/goods?typeName=${fn:substring(fn:substringBefore(goods.name,"]"), 1, 9) }">${fn:substring(fn:substringBefore(goods.name,"]"), 1, 9) }   </a></span></p>
					<p><span class="tit">配送范围</span>
					<span style="color:red;margin-left:-3px;"><c:if test="${goods.isDelivery==0 }">全国配送</c:if><c:if test="${goods.isDelivery==1 }">重庆主城</c:if></span></p>
					<p class="mt5"><span class="tit">商品产地</span><span style="position:relative;left:1px;">${empty goods.area?'无':goods.area }</span></p>
					<%-- <c:if test="${fn:length(goods.goodsstandards)>'0'}"> --%>
							<div class="mt5 ${empty goods.goodsstandards?'':'spec' }">
								<span class="tit">商品规格</span>
									<c:if test="${empty goods.goodsstandards }">
										<span style="position:relative;left:-3px;">无</span>
									</c:if>
									<c:if test="${not empty goods.goodsstandards }">
									<div class="etalon-wrap">
									<c:forEach items="${goods.goodsstandards }" var="stan">
									<%-- 	<c:if test="${goods.standard==stan.standard }">
											<a href="goods/${stan.goodId }/0/0" class="cur etalon">${stan.standard }</a>
										</c:if>
										<c:if test="${goods.standard!=stan.standard }">
											<a href="goods/${stan.goodId }/0/0" class="etalon">${stan.standard }</a>
										</c:if> --%>
										
										<a href="goods/${stan.goodId }/0/0" class="etalon ${stan.goodId==goods.id? 'cur':''  }">${stan.standard }</a>
									</c:forEach>
									</div>
									</c:if>
							</div>
					<%-- </c:if> --%>

					<c:if test="${!empty goods.prentsentName }">
						<p><span class="tit">赠送商品</span><span style="color: red;">${goods.prentsentName }</span></p>
					</c:if>
				</div>
				<div class="buysum clearfix"><span class="tit">购买数量</span><div class="gw_num"><em class="jian">-</em><input id="goodsNum" type="text" value="1" class="num"/><em class="add"><img src="resource/commons/images/em-add.png" style="width:25px;height:25px;" /></em></div></div>
				<div class="ml20 mt15"><a href="javascript:void(0);" class="btn btn-success add-cart" id="goodsId" value="${goods.id }"><i class="icon icon12"></i>加入购物车</a><a href="javascript:void(0);" class="btn btn-danger buy-now ml10" id="shopping">立即购买</a></div>
			</div>
			
			<!-- 看了又看 -->
			<div class="chunk-right">
				<div class="seeAgin">
					<div class="title">
						<u>看了又看</u></div>
					<ul class="buyz mt15">
						<c:forEach items="${yourLike }" var="goods">
							<a href="goods/${goods.id }/0/0">
								<li><p class="img">
								 <c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.imgPath }"><img:imghandle w="150" imgurl="${goods.imgPath }" h="150"  cl="shop-img"></img:imghandle></c:if>
        
								<em>￥${goods.price }</em></p><p class="tit">${goods.name }</p></li>
							</a>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="content-details w-1250">
			<div class="clearfix">
				<div class="details-left">
					<div class="chunk-classify1">
						<div class="head-titled">相关品类</div>
						<div class="del">
							<c:forEach items="${typeNameList }" var="type">
								<a href="elastic/typeGoods?typeId=${type.id }" style="text-decoration: none;">${type.name }</a>
								<!-- <a>苹果</a>
								<a>橘子</a>
								<a>桃类</a>
								<a>柑橘/柚类</a>
								<a>车厘子</a>
								<a>热带水果</a> -->
							</c:forEach>
						</div>
					</div>
					<!-- 该用户浏览了 -->
					<div class="chunk-classify1">
						<div class="head-titled">浏览该商品的用户还浏览了</div>
						<ul class="list-ul">
							<c:forEach items="${newPutwayGoods }" var="goods">
							  <a href="goods/${goods.id }/0/0">
								<li class="list-li">
									<div class="img">
									 <c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.imgPath }"><img:imghandle w="158" imgurl="${goods.imgPath }" h="158"  cl="shop-img"></img:imghandle></c:if>
									</div>
									<p class="tit">${goods.name }</p>
									<p class="price">¥ ${goods.price }</p>
								</li>
							  </a>
							</c:forEach>
						</ul>
					</div>
					<div class="chunk-classify1">
						<div class="head-titled">分类销售排行榜</div>
						<ul class="list-ul">
							<c:forEach items="${typeSealGoods }" var="typeSeal">
							<a href="goods/${typeSeal.id }/0/0">
								<li class="list-li">
									<div class="img">
									<c:if test="${empty typeSeal.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty typeSeal.imgPath }"><img:imghandle w="158" imgurl="${typeSeal.imgPath }" h="158"  cl="shop-img"></img:imghandle></c:if>
									</div>
									<p class="tit">${typeSeal.name }</p>
									<p class="price">¥ ${typeSeal.price }</p>
								</li>
							</a>
							</c:forEach>
						</ul>
					</div>
					<div class="chunk-classify1">
						<div class="head-titled">总销售排行榜</div>
						<ul class="list-ul">
							<c:forEach items="${allSealGoods }" var="allSeal" >
								<a href="goods/${allSeal.id }/0/0">
									<li class="list-li">
										<div class="img">
										<c:if test="${empty allSeal.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty allSeal.imgPath }"><img:imghandle w="158" imgurl="${allSeal.imgPath }" h="158"  cl="shop-img"></img:imghandle></c:if>
										</div>
										<p class="tit">${allSeal.name }</p>
										<p class="price">¥ ${allSeal.price }</p>
									</li>
								</a>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="details-right">
					<div class="tab-navv">
						<ul>
							<li class="active">商品描述</li>
							<li onclick="getComment(${goods.id},1)">累计评价</li>
							<li onclick="getDeal(${goods.id},1)">最新成交记录</li>
						</ul>
					</div>
					<div class="tab-contentt">
						<div>
							<ul class="tab-con-top clearfix">
								<li class="ad-color-o" style="width:170px;">重庆水果市场品牌商品</li>
								<li style="margin-right:25px;">地址 : <span>菜园坝水果市场内香满圆</span></li>
								<li style="margin-right:40px;">商品 : <span>${goods.name }</span></li>
								<li>包装方式 : <span>包装</span></li>
								<li style="width:170px;">重量/规格 : <span id="guige"></span></li>
								<li style="margin-right:25px;">包装 : <span>${goods.unitName }</span></li>
								<li style="margin-right:40px;">存储方式 : <span>冷藏</span></li>
								<li>商家 : <span>${fn:substring(fn:substringBefore(goods.name,"]"), 1, 9) }</span></li>
							</ul>
							<div class="clearfix control-img">${goods.pcIntroduced }
								
								<div class="descript-img"> <!-- 分类描述图 -->
									<img src="${goods.descriptionImg } "/>
								</div>
								<div class="container-img">
								<div class="container-tab">
									<div class="tab-sec">
										<ul class="clearfix">
											<li class="active">
												<div class="img-wrap img-wrap1"></div>
												<p>品牌市场 精心挑选</p>
											</li>
											<li>
												<div class="img-wrap img-wrap2"></div>
												<p>实物拍摄 所见所得</p>
											</li>
											<li>
												<div class="img-wrap img-wrap3"></div>
												<p>专业配送 新鲜直达</p>
											</li>
											<li>
												<div class="img-wrap img-wrap4"></div>
												<p>本土国企 值得信赖</p>
											</li>
										</ul>
									</div>
									<div class="tab-content-sec">
										<div>
											<%-- <div class="clearfix">
												<div class="left-word">
													<p>品牌市场精心选购</p>
												</div>
												<div class="right-word">
													<p>远在天涯的美味，近在咫尺的体验,</p>
													<p>本地品牌市场专业直采，原滋原味全球精选，您的尊贵享受!</p>
												</div>
											</div>
											<ul class="item-img clearfix">
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
											</ul> --%>
											<img alt="" src="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/ta.png">
										</div>
										<div>
											<%-- <div class="clearfix">
												 <div class="left-word">
													<p>实物拍摄所见所得</p>
												</div>
												<div class="right-word">
													<p>所有商品实物拍摄，还原更真实的产品本质,</p>
													<p>确保更高的一致性，让您更直观选购满分商品!</p>
												</div> 
												
											</div>
											<ul class="item-img clearfix">
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
											</ul> --%>
											<img alt="" src="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/tb.png">
										</div>
										<div>
											<%-- <div class="clearfix">
												<div class="left-word">
													<p>专业配送新鲜直达</p>
												</div>
												<div class="right-word">
													<p>夜采朝配，保证商品优质，自有车队</p>
													<p>远在天涯的每位</p>
												</div>
											</div>
											<ul class="item-img clearfix">
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
											</ul> --%>
											<img alt="" src="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/tc.png">
										</div>
										<div>
											<%-- <div class="clearfix">
												<div class="left-word">
													<p>本土国企值得信赖</p>
												</div>
												<div class="right-word">
													<p>远在天涯的每位</p>
													<p>远在天涯的每位</p>
												</div>
											</div>
											<ul class="item-img clearfix">
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
												<li class="item"><img src="images/task_07.jpg" alt=""></li>
											</ul> --%>
											<img alt="" src="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/td.png">
										</div>
									</div>
								</div>
							</div>
							
							
							
							</div>		
						</div>
						<div>
							<div id="comment"></div>
							<div style="text-align: right;">
								<div class="table-fy-right">
									<ul class="paging-ul">
										<li class="paged active" value="1">1</li>
										<li><i onclick="getComment(${goods.id},2)" class="iconfont" style="font-size:15px;color:#666">&#xe65b;</i></li>
									</ul>
								</div>
							</div>
						</div>
						<div>
							<div id="deal"></div>
							<div style="text-align: right;">
								<div class="table-fy-right">
									<ul class="paging-ul">
										<li class="paged active" value="1">1</li>
										<li><i onclick="getDeal(${goods.id},2)" class="iconfont" style="font-size:15px;color:#666">&#xe65b;</i></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix">
				<div class="details-left ad-top-gutter">
					<div class="chunk-classify1 ccsifly">
						<!-- 最近浏览 -->
						<div class="head-titled2">最近浏览</div>
						<ul class="list-ul clearfix near-watch">
							<c:forEach items="${kGoods }" var="goods">
							<li class="list-li clearfix">
								<a href="goods/${goods.id }/0/0">
									<div class="img">
									 <c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.imgPath }"><img:imghandle w="59" imgurl="${goods.imgPath }" h="59"  cl="shop-img"></img:imghandle></c:if>
        
									</div>
									<p class="tit">${goods.name }</p>
									<p class="price">¥ ${goods.price }</p>
								</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="details-right ad-top-gutter">
					<div class="chunk-classify1 ccsifly">
						<div class="head-titled2">根据浏览猜你喜欢</div>
						<div class="you-like">
							<span class="before"><i class="iconfont">&#xe645;</i></span>
							<div class="swiper-container">
								<div class="swiper-wrapper list-ul">
								<c:forEach items="${huaGoods }" var="goods">
								<div class="swiper-slide list-li" data-goodsid="${goods.id }">
										<a href="goods/${goods.id }/0/0">
											<div class="img">
											 <c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.imgPath }"><img:imghandle w="132" imgurl="${goods.imgPath }" h="132"  cl="shop-img"></img:imghandle></c:if>
        
											</div>
											<p class="tit">${goods.name }</p>
											<p class="price">¥ ${goods.price }</p>
										</a>
										</div>
										</c:forEach>
								</div>
							</div>
							<span class="next"><i class="iconfont">&#xe6e8;</i></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
  <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  <%@include file="/WEB-INF/jsp/commons/nav/search_float_top.jsp" %>
  <script type="text/javascript" src="resource/js/common/goods_search.js"></script>
  <script type="text/javascript" src="resource/js/goods/goods_detail.js?v=20182231540"></script>
   <script type="text/javascript" src="resource/commons/js/cartscroll.js"></script>
   <script type="text/javascript" src="resource/commons/js/idangerous.swiper2.7.6.min.js"></script>
  <script src="resource/js/common/menu.js"></script>
  <script type="text/javascript">
  
  console.log("${isCollection}")
	var isLogin = "${isLogin}" ;
 	//收藏商品
	$("#bookMark").click(function(){
		if (isLogin == 1) {
			popMsg("登录过后才能收藏商品！");
			return ;
		}
		var goodsId = $(this).attr("value");//商品id
		var categoryName = $(this).attr("tname");//商品分类名称
		var activityType = $("#typeId").val(); //活动类型
		var activityId = $("#actiId").val(); //活动id
		$.ajax({
			url:"cart/addCollection",
			type:"post",
			data:{"goodsId":goodsId,"categoryName":categoryName,"activityType":activityType,"activityId":activityId},
			dataType: "JSON",
			success:function (data) {
				if (data == 1) {
					popMsg("收藏夹已存在该商品！");
				} else {
					$("#bookMark").css("color","#358912");
					popMsg("已加入收藏夹！");
				}
			}
		})
		
	})
	function popMsg(Msg){
		var btnFn = function(){
			easyDialog.close();
		}
		easyDialog.open({
			  container : {
			    header : '提示',
			    content : Msg,
			    yesFn : btnFn
			  }
		});
	}
	$(function(){
		var mySwiper = new Swiper ('.swiper-container', {

            direction: 'horizontal',
            height: 300,//你的slide高度
            width : 720,
            loop: true,
            slidesPerView : 4,
            simulateTouch : false,

            // 如果需要分页器
            //pagination: '.swiper-pagination',

            // 如果需要前进后退按钮

            // 如果需要滚动条
            //scrollbar: '.swiper-scrollbar',
        })
        $('.before').click(function(){
            mySwiper.swipePrev();
        })
        $('.next').click(function(){
            mySwiper.swipeNext();
        });
        /* 
        $(".smallImgDown").click(function(){
        	if($(this).prev().find("#imageMenu li").size()<6){
        		alert("s")
        	}
        }) */
        
        var goodsname="${goods.name}";
        var guige=goodsname.substr(goodsname.lastIndexOf(' '),goodsname.length);
        $("#guige").text(guige);
	})
  </script>
  </body>
</html>
