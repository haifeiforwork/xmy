<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table"  >
 <thead>
   <tr>
     <th>序号</th>
     <th>商品编号</th>
     <th>商品名称</th>
     <th>二级分类</th>
     <th>卖价（元）</th>
     <th>供货商</th>
     <th>品牌</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${glist }" var="item">
    <tr>
	   	 <%-- <td><input type="checkbox" data-id="${item.id }" data-name="${item.name }" name="supplyStrategy" value="1" lay-skin="primary" title="${item.name }"></td> --%>
	   	 <td class="gId">${item.id }</td> 
	   	 <td class="gCode">${item.code }</td> 
	   	 <td class="gName">${item.name }</td> 
	   	 <td>${item.categoryName }</td> 
	   	 <td>${item.price }</td> 
	   	 <td>${item.supplierName }</td> 
	   	 <td>${item.brandName }</td> 
	   	 <td><button id="add" class="layui-btn">添加</button></td> 
     </tr>
   </c:forEach>
  </tbody>
</table>
<script type="text/javascript">
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form() ;
	
	$('tbody tr #add').click(function(){
		var td=$(this).parent().parent().html();
		td=td.replace("添加","移除").replace("add","remove");
		$("#my-tbody").append("<tr>"+td+"</tr>");
		$(this).parent().parent().remove();
	})
	$("#my-tbody").on("click","#remove",function(){
		$(this).parent().parent().remove();
	})
	
})
</script>