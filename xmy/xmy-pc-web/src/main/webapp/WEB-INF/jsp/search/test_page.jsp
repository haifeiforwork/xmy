<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:forEach items="${page }" var="pageIndex">
<c:if test="${pageIndex == 1 }">
  		<a href="javasprict:void(0);" value="${pageIndex }"  class="first active page-num">${pageIndex }</a>
</c:if>
<c:if test="${pageIndex!=1 }">
	<a href="javasprict:void(0);" value="${pageIndex }"  class="first page-num">${pageIndex }</a>
</c:if>
</c:forEach>
<a href="javasprict:void(0);" class="last"><i class="iconfont">&#xe65b;</i></a>
<%-- <input type="hidden" value="${pageIndex }" id="pageNo"/> --%>



