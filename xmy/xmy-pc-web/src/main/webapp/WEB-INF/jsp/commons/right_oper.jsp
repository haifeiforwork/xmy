<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%-- 始终居右的悬浮操作项 --%>
<div class="floatleft">
	<ul class="floatleft-ul">
		<li class="floatleft-li floatleft-lis cart"><span class="cart"><img src="resource/commons/images/left-cart.jpg"/></span><p onclick="window.location='/cart/shoppingCart'" id="cartnum">购物车0</p>
			<ul class="cart-ul" id="downCart">
				
			</ul>
		</li>
		<li class="floatleft-li collects-li"><span class="floatleft-img"><img onclick="window.location='/center/toCollect?pageIndex=1&set=14&category=null'" src="resource/commons/images/sc.png"/></span>
			<div class="collects">我的收藏</div>
		</li>
		<li class="floatleft-li my-xmy"><span class="floatleft-img"><img onclick="window.location='/center/centerIndex?set=1'" src="resource/commons/images/wd.png"/></span>
			<div class="my-xmys">我的香满圆</div>
		</li>
		<a href="center/toShopCard?pageIndex=1&set=10&status=3">
			<li class="floatleft-li recharge"><span class="floatleft-img"><img src="resource/commons/images/c.png"/></span>
				<div class="recharges">立即充值</div>
			</li>
		</a>
		<li class="floatleft-li QQ" id="qianniu"><span class="floatleft-img"><img src="resource/commons/images/information.png"/></span>
			<div class="QQs">在线客服</div>
		</li>
	</ul>
	<ul class="floatleft-bottom-ul">
		<li class="floatleft-bottom-li hot-line"><span class="floatleft-img"><img src="resource/commons/images/dh.jpg"/></span>
			<div class="hot-lines"><img src="resource/commons/images/tb_ipone_number.jpg"/></div>
		</li>
		<li class="floatleft-bottom-li ewm">
			<span class="floatleft-img"><img src="resource/commons/images/ewm.jpg"/></span>
			<div class="pop-up">
				<div class="pop-up-over"></div>
				<div class="app">
					<div class="img"><img src="resource/commons/images/B2Cwx.jpg"/></div><p class="text">扫码关注微信端<br/>
				</div>
				<div class="app yd">
					<div class="img"><img src="resource/commons/images/B2Cpc.png"/></div><p class="text">扫码进入移动端</p>
				</div>
			</div>
		</li>
		<li class="floatleft-bottom-li return-top"><span><i class="iconfont">&#xe629;</i></span><a href="javascript:scroll(0,0)" style="text-decoration:none;"><p style="color: white;">返回顶部</p></a></li>
	</ul>
</div>
