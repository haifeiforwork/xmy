<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<!--2017-10-31 新增顶部浮现搜索-->
<div class="floattop fadein">
	<div class="floattop-overlay"></div>
	<div class="floattop-content clearfix">
		<div class="floattop-logo">
			<img src="resource/commons/images/minlogo.png" alt="">
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
		</div>
		<div class="floattop-search">
			<input type="text" id="topSearchContent">
			<button type="submit" id="topSearch">搜索</button>
		</div>
		<div class="commidi-icon"><img src="resource/commons/images/ad-1.png" alt=""></div>
	</div>
</div>
<!--2017-10-31 新增顶部浮现搜索end-->