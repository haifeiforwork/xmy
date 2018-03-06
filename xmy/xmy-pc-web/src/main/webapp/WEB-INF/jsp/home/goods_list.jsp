<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="img"%>
<input type="hidden" class="countpage" value="${countPage }"/>
<c:if test="${consize==0 }">
	<c:forEach items="${goodsList }" var="goods">
		<a href="goods/${goods.id}/0/0">
		<li class="chunk-z-li"><div class="img"><c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
									<c:if test="${!empty goods.imgPath }"><img:imghandle w="160" imgurl="${goods.imgPath }" h="160"  cl="shop-img"></img:imghandle></c:if></div><p class="del" title="${goods.name }">${goods.name }</p><p class="money">￥${goods.price }</p></li>
		</a>
	</c:forEach>
</c:if>
<c:if test="${consize==1 }">
	<c:forEach items="${goodsList }" var="goods">
		 <a href="goods/${goods.id}/0/0">
			 <li class="list-li chunk-z-li"><div class="img"><c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
									<c:if test="${!empty goods.imgPath }"><img:imghandle w="160" imgurl="${goods.imgPath }" h="160"  cl="shop-img"></img:imghandle></c:if></div><p class="del"  title="${goods.name }" >${goods.name }</p><p class="money">￥${goods.price }</p></li>
		 </a>
	</c:forEach>
</c:if>



