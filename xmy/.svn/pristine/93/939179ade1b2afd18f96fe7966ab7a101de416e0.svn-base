<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="img"%>
 <c:forEach items="${freshGoods }" var="item">
	<a href="goods/${item.id }/0/0" style="text-decoration:none;" >
 	<li>
         <div class="img-wrap">
             <c:if test="${empty item.imgPath }"><img src="resource/commons/images/defaultgoods.jpg" class="item"></c:if>
        <c:if test="${!empty item.imgPath }"><img:imghandle w="169" imgurl="${item.imgPath }" h="169"  cl="item"></img:imghandle></c:if>
        
             <span class="price"  >¥ ${item.price }</span>
         </div>
         <p style="color: black;">${item.name }</p>
     </li>
	</a>
</c:forEach>



