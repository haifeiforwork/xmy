<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty commentList }">
	<center>暂无相关记录！</center>
</c:if>
<c:if test="${!empty commentList }">
	<c:forEach items="${commentList }" var="comment">
		&nbsp;&nbsp;${comment.commentConten }
		<br><br>
		&nbsp;&nbsp;<fmt:formatDate value="${comment.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/>
		<hr style="border:1px dashed #000; height:1px">
	</c:forEach>
	<input type="hidden" value="${pageIndex }" id="pageIndex"/>
</c:if>


