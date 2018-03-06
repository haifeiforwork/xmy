<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!-- 分页二级菜单页面 -->

<div class="banner">
	<c:forEach items="${imgList }" var="img">
		<img src="${img.imgPath }" alt="" class="class-advise" url="${img.url }" type="${img.type }">
	</c:forEach>
</div>
<div class="list-wrap classfy-second">
	<c:forEach items="${child }" var="child">
		<c:if test="${child.status==0 }">
			<div class="container-wrap">
				<div class="weui-loadmore weui-loadmore_line">
			        <span class="weui-loadmore__tips article content">${child.name }</span><span class="more" category_id = "${child.id }">更多 <img src="resources/common/images/btn-fenl-more-right@2x.png" alt=""></span>
			    </div>
		        <ul class="list-content">
		        	<c:forEach items="${child.childCategory }" var="category">
		                   <li class="item goods-classfy" word-id="${category.id }"><img src="${category.imgPath }" alt="" class="shop-list-img"><p class="fl-name">${category.wordSeg }</p></li>
		             </c:forEach>
			    </ul>
		    </div>
	    </c:if>
	</c:forEach>
</div>
