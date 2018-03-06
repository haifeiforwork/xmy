<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 测试图片 --%>
<!-- <div>
	<img alt="" src="resource/commons/images/top.png" style="width: 100%">
</div> -->
<%--不需要通知内容,前台不显示（香满圆要求） --%>
 <%-- <c:if test="${not empty notifications }">
	       	<div style="width:100%;height:20px;text-align: center;">
	       		<span style="color:red;">香满圆通知:</span>
	       		<c:forEach items="${notifications }" var="notification">
	       			${notification.content}
	       		</c:forEach>
	       	</div>
       	</c:if> --%>
<%-- 导航的最顶部 --%>
<div class="wrap-roof">
   <div class="roof">
       <div class="left">
     
       		<c:if test="${empty sessionScope.userName }">
	           <span>您好，欢迎来到香满圆</span>
	           <ul class="login">
	               <a href="user/login" style="color: gray; float: left;"><li>登录</li></a>
	               <a href="user/register" style="color: gray;float: left;"><li>注册</li></a>
	                
	               <div style="clear: both;"></div>
	            </ul>
            </c:if>
            <c:if test="${!empty sessionScope.userName }">
	           <span>您好${userName }，欢迎来到香满圆</span>
	           <ul class="login">
	               <a href="user/out" style="color: gray;"><li>退出</li></a>
	               <div style="clear: both;"></div>
	            </ul>
            </c:if>
        </div>
        <div class="right">
            <ul class="right-list clearfix">
                <li class="net-all"><i class="iconfont">&#xe600;</i>网站导航
                    <div class="net-nav">
                        <ul>
                        	<c:forEach items="${sessionScope.goodsNavigated }" var="termData" >
                        		 <c:if test="${termData.isShow!=1 }">
		                            <li>
		                                <p class="title" style="background:url(${termData.icon}) no-repeat;background-postion:-20px;"><a href="category/findCategoryGoods?id=${termData.id}" style="color: red;">${termData.name }</a></p>
		                                <c:forEach items="${termData.childTermData }" var="child">
		                                	<p class="title"><a href="elastic/typeGoods?typeId=${child.id }">${child.name }</a></p>
		                                </c:forEach>
		                            </li>
	                            </c:if>
                            </c:forEach>
                            <li>
                                <p class="title" style="background:url() no-repeat;background-postion:-20px;"><a href="activity/findActivity?show=2" style="color: red;">热销活动</a></p>
                               	<p class="title"><a href="activity/findActivity#bing?show=2">冰点价</a></p>
                               	<p class="title"><a href="activity/findActivity#dayday?show=2">限时抢购</a></p>
                               	<p class="title"><a href="activity/findActivity#buy?show=2">买即赠</a></p>
                               	<p class="title"><a href="activity/findActivity#week?show=2">每周特惠</a></p>
                               	<p class="title"><a href="activity/findActivity#all?show=2">整件惠</a></p>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="phone-all"><i class="iconfont phone">&#xe634;</i><span class="phone-span">手机香满圆</span>
                    <div class="phone-code">
                        <div class="img-wrap"><img src="resource/commons/images/B2Cwx.jpg" alt=""><p>扫码关注微信端</p></div>
                        <div class="img-wrap"><img src="resource/commons/images/B2Cpc.png" alt=""><p>扫码进入移动端</p></div>
                        <!-- <div style="width:130px;margin-top:-5px;"><img src="resource/commons/images/new-yh.png" style="width:100%;display:block"></div> -->
                    </div>
                </li>
                <li class="collect">收藏夹<i class="iconfont down">&#xe63b;</i>
                    <ul class="collect-ul">
                        <li class="collect-li"><a href="center/toCollect?pageIndex=1&set=14&category=null">收藏宝贝</a></li>
                    </ul>
                </li>
                <a href="cart/shoppingCart" style="color: gray;"><li class="details-all"><i class="iconfont cart">&#xe64f;</i>购物车<b id="cartCount"></b>件
                    <div class="shop-details" id="shoppingcart">
                        
                    </div>
                </li></a>
                <a href="center/centerIndex?set=1" style="color: gray;"><li>我的香满圆</li></a>
            </ul>
        </div>
    </div>
</div>
