<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%-- 个人中心 公共左边菜单页面 --%>
<div class="content-left">
		<ul class="menu-ul">
			<li class="menu-li" onclick="window.location='/center/centerIndex?set=1'"><span class="${set==1?'menu-li-span active':'menu-li-span' }">我的首页</span></li>
			<li class="menu-li" onclick="window.location='/cart/shoppingCart'"><span class="menu-li-span">我的购物车</span></li>
			<li class="menu-li" onclick="window.location='/order/myOrder/0/1?set=3'"><span class="${set==3?'menu-li-span active':'menu-li-span' }">我的订单</span></li>
		</ul>
		<ul class="menu-ul menu-uls">
			<li class="menu-li bold"><span class="menu-li-span">账户中心</span></li>
			<li class="menu-li" onclick="window.location='/center/toUpdate?set=5'"><span class="${set==5?'menu-li-span active':'menu-li-span' }">个人中心</span></li>
			<li class="menu-li" onclick="window.location='/center/toAddress?set=6'"><span class="${set==6?'menu-li-span active':'menu-li-span' }">收货地址管理</span></li>
			<li class="menu-li" onclick="window.location='/center/toChangePwd?set=7'"><span class="${set==7?'menu-li-span active':'menu-li-span' }">密码修改</span></li>
			<li class="menu-li" onclick="window.location='/center/toSafetyPhone?set=8'"><span class="${set==8?'menu-li-span active':'menu-li-span' }">安全验证</span></li>
			<li class="menu-li" onclick="window.location='/center/toBalanceYear?pageIndex=1&set=9'"><span class="${set==9?'menu-li-span active':'menu-li-span' }">账户余额</span></li>
			<li class="menu-li active" onclick="window.location='/center/toShopCard?pageIndex=1&set=10&status=0'"><span class="${set==10?'menu-li-span active':'menu-li-span' }">购物卡 <span class="hot">HOT</span></span></li>
			<li class="menu-li" onclick="window.location='/center/toCoupon?pageIndex=1&set=11&status=1'"><span class="${set==11?'menu-li-span active':'menu-li-span' }">优惠券信息<span class="hot">HOT</span></span></li>
			<li class="menu-li" onclick="window.location='/center/toRefund?pageIndex=1&set=12'"><span class="${set==12?'menu-li-span active':'menu-li-span' }">退款列表</span></li>
			<li class="menu-li" onclick="window.location='/center/toPointsDetail?pageIndex=1&set=13'"><span class="${set==13?'menu-li-span active':'menu-li-span' }">积分明细</span></li>
			<li class="menu-li" onclick="window.location='/center/toCollect?pageIndex=1&set=14&category=null'"><span class="${set==14?'menu-li-span active':'menu-li-span' }">我的收藏</span></li>
			<li class="menu-li" onclick="window.location='/center/toAppraise?pageIndex=1&status=99&set=15'"><span class="${set==15?'menu-li-span active':'menu-li-span' }">评价管理</span></li>
			<li class="menu-li" onclick="window.location='/center/toAdvise?pageIndex=1&set=16'"><span class="${set==16?'menu-li-span active':'menu-li-span' }">投诉与建议</span></li>
		</ul>
</div> 