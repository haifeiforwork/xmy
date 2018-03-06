<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="img"%>
<c:if test="${empty goodsList.list }">
 <li class="list-item" style="text-align: center;">
 	暂无相关数据
 </li>
</c:if>
<c:if test="${!empty goodsList.list }">
	<c:forEach items="${goodsList.list }" var="goods">
		<c:if test="${empty goods.activityType }">
			<a href="goods/${goods.id }/0/0">
		</c:if>
		<c:if test="${!empty goods.activityType }">
			<a href="goods/${goods.id }/${goods.activityType }/${goods.activityId}?url=${goods.activityImg }">
		</c:if>
		 <li class="list-item">
			<c:if test="${!empty goods.activityType }">
				 <div class="active-wrap">
	       			 <img src="${goods.activityImg }" alt="">
	    		 </div>
			</c:if>
		     <div class="img-wrap">
		      <c:if test="${empty goods.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="shop-img"></c:if>
        <c:if test="${!empty goods.imgPath }"><img:imghandle w="218" imgurl="${goods.imgPath }" h="218"  cl="shop-img"></img:imghandle></c:if>
		     </div>
		     <div class="shop-price"><span class="price">¥ ${goods.price/100 }</span>/<span>${goods.unitName }</span></div>
		     <div class="shop-name">${goods.fullName }</div>
		     <div class="item-last"><span class="saled">累计成交 <span>${empty goods.sumDeal?0:goods.sumDeal }笔</span></span><span class="fr">评价 <span>${empty goods.sumComment?0:goods.sumComment }</span></span></div>
		 </li>
		</a>
	</c:forEach>
</c:if>
<%-- <div class="pagingg clearfix">
    <span class="clearfix">
    	<c:forEach items="${page }" var="pageIndex">
    		<c:if test="${pageIndex == goodsList.pageNum }">
        		<a href="javasprict:void(0);" onclick="nextPage(${pageIndex})" value="${pageIndex }"  class="first active page-num">${pageIndex }</a>
    		</c:if>
    		<c:if test="${pageIndex!=goodsList.pageNum }">
    			<a href="javasprict:void(0);" onclick="nextPage(${pageIndex})" value="${pageIndex }"  class="first page-num">${pageIndex }</a>
    		</c:if>
    	</c:forEach>
       		<a href="javasprict:void(0);" class="last"><i class="iconfont" onclick="nextPage(${goodsList.pageNum+1})">&#xe65b;</i></a>
    </span>
</div> --%>
<%-- <input type="text" value="${goodsList.pages }" id="countPage"/> --%>
<script type="text/javascript">
/* laypage({
	cont: $("#biuuu_city"),
	pages: $("#countPage").val(),
	skip:true,
	jump: function(obj){
		//改变页数
		//$("#pageNo").val(obj.curr);
		//nextPage(obj.curr);
	}
}); */
</script>


