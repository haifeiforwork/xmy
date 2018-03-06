<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%-- 个人中心nav公共部分：具体的相关动态变化部分，要参照制作，有些需要在控制层控制返回值来显示结果，
	变化最大的数收藏-商品收藏和支付完成-货到付款这两个页面的显示内容与其他的页面要少些，注意灵活控制 --%>
<nav>
    <%@include file="/WEB-INF/jsp/commons/nav/comm_top.jsp" %>
    <div class="navigation">
		<div class="w-1250">
			<div class="navigation-left">
				<a href="index">
					<div class="left-chunk1" style="cursor: pointer;"><img src="resource/commons/images/gr-logo.jpg"/></div>
				</a>
				<div class="left-chunk2">
					<p class="my-xmy">我的香满圆</p>
					<a href="index" style="text-decoration:none;">
						<p class="return-index" style="cursor: pointer;">返回香满圆首页</p>
					</a>
				</div>
				<div class="left-chunk3">
					<!-- <ul>
						<a href="index" style="color: white;">
							<li class="index" style="cursor: pointer;">首页</li>
						</a>
						<li class="hit">账户设置<i class="iconfont">&#xe63b;</i></li>
					</ul> -->
				</div>
			</div>
			<div class="navigation-right">
				<a href="cart/shoppingCart">
					<div class="right-chunk1"><i class="iconfont cart">&#xe64f;</i>我的购物车<i class="iconfont pull-left">&#xe6e8;</i><div class="number">${sessionScope.countCart }</div></div>
				</a>
				<div class="right-chunk2">
				    <form action="/elastic/goods" method="post" id="searchForm">
					<input type="text" name="goodsName" placeholder="搜索" id="navSearchContent">
					<button type="submit" id="navSearch">搜索</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</nav>
