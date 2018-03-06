<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="img"%>
<ul class="clearfix">
    <c:forEach items="${pageBean.data }" var="goods">
    <li class="list-item">
     <a href="goods/${goods.id}/0/0">
        <div class="img-wrap">
        <c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.imgPath }"><img:imghandle w="218" imgurl="${goods.imgPath }" h="218"  cl="shop-img"></img:imghandle></c:if>
        </div>
        <div class="shop-price"><span class="price">¥ ${goods.price }</span>/<span>盒</span></div>
        <div class="shop-name">${goods.name }</div>
        <div class="item-last"><span class="saled">累计成交 <span>${empty goods.sumDeal?0:goods.sumDeal }笔</span></span><span class="fr">评价 <span>${empty goods.sumComment?0:goods.sumComment }</span></span></div>
     </a>
    </li>
    </c:forEach>
</ul>
<div class="pagingg clearfix">
    <span class="clearfix">
	    <input id="sort" type="hidden" value="${sort }">
    
        <a href="javascript:onPage(${pageBean.pageIndex==1?1:pageBean.pageIndex-1});" class="first active page-num">${pageBean.pageIndex==1?1:pageBean.pageIndex-1}</a>
        <a href="javascript:onPage(${pageBean.pageIndex==pageBean.totalPage ? pageBean.totalPage : pageBean.pageIndex+1});" class="page-num">${pageBean.pageIndex==pageBean.totalPage ? pageBean.totalPage : pageBean.pageIndex+1}</a>
        <a href="javascript:onPage(${pageBean.totalPage });" class="last"><i class="iconfont">&#xe65b;</i></a>
    </span>
</div>



