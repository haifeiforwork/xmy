<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table" style="border-collapse:collapse;" >
<colgroup>
   <col width="150">
   <col width="150">
</colgroup>
<thead>
   <tr>
     <th>缩略图</th>
     <th>上传时间</th>
   </tr> 
</thead>
<tbody>
	<c:forEach items="${data }" var="item">
	 	<tr data-id="${item.id }" data-path="${item.path }" class="" >
			<td><img alt="" src="${item.path }" width="50px;" height="50px;"></td> 
			<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td> 	
	 	</tr>
 	</c:forEach>
</tbody>
</table>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager,form = layui.form() ;
	$("table tbody tr").click(function() {
		$("table tbody tr").removeAttr("style");
		$(this).css({"border":"2px solid #66CD00"});
		$(this).attr("class","choose");
	})
})
</script>