<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<ul  >
	<c:if test="${empty keysList}">
		<li>暂无相关记录</li>
	</c:if>
	<c:if test="${!empty keysList }">
		<c:forEach items="${keysList }" var="keywords">
			<li class="searchname" >${keywords }</li>
		</c:forEach>
	</c:if>
</ul>
<%-- <div class="all-li">总记录数 : <span style="color:red">${goodsList.total }</span></div> --%>



