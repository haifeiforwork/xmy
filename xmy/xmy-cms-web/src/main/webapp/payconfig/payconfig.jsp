<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<form id="modify_Form">
	<table class="layui-table" lay-skin="line">
	 <thead>
	   <tr>
	     <th>ID</th>
	     <th>字段</th>
	     <th>字段描述</th>
		 <th>支付渠道</th>
		 <th>创建时间</th>
		 <!-- <th>商户类型</th> -->
		 <th>值</th>
	   </tr> 
	 </thead>
	 <tbody>
	    <div id="modify_paytype" class="layui-form" >
	    
	        <div class="layui-form-item">
			 	<c:forEach items="${configs }" var="config">
				   <tr>
				     <td>${config.id }</td>
				     <td>${config.ke }</td>
				     <td>${config.keDes }</td>
				     <td>${config.payType}</td>
				     <td><fmt:formatDate value="${config.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				     <%-- <td>${config.merchantType}</td> --%>
				     <td>
				         <input name="${config.id }" type="text" class="layui-input" value="${config.val }" />
				     </td>
				   </tr>
			   </c:forEach>
		   </div>
		 
	   </div>
	 </tbody>
	</table>
</form>