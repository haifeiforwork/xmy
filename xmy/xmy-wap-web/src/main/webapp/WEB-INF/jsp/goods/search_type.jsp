<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<c:forEach items="${searchList }" var="searchDto">
 	 <div class="page-item search-type" type-id="${searchGoodsDto.typeId }">
       <div class="item-title"><span>${searchDto.searchName }</span> <span class="img-wrap down"></span></div>
       <ul class="page-list" style="display: none">
           <c:forEach items="${searchDto.childList }" var="child">
           		<c:set value="0" var="isChoose"></c:set>
           		<c:forEach items="${typeName }" var="name">
           			<c:if test="${name == child.searchName }">
           				<c:set var="isChoose" value="1"></c:set>
           			</c:if>
           		</c:forEach>
           	    <li search="${child.searchName }" class="<c:if test="${child.searchName == '全部'}">all-type </c:if><c:if test="${isChoose == 1}">choose-type</c:if>">${child.searchName }</li>
           </c:forEach>
        </ul>
     </div>
</c:forEach>
