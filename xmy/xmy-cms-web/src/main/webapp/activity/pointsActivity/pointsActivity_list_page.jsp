<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table"  >
  <colgroup>
   <col width="150">
   <col width="150">
   <col width="150">
   <col width="150">
   <col width="150">
   <col width="150">
 </colgroup>
 <thead>
   <tr>
     <th>促销名称</th>
     <th>促销类型</th>
     <th>开始时间</th>
     <th>结束时间</th>
     <th>促销状态</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${alist }" var="item">
    <tr>
	   	 <td>${item.name }</td>
	     <td>积分促销</td>
	     <td><fmt:formatDate value="${item.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td><fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td>${item.saleStatus }</td>
	     <td>
	     	<button id="edit" data-id="${item.id }" class="layui-btn layui-btn-radius">编辑 </button>
	     	<button id="del" data-id="${item.id }" class="layui-btn layui-btn-radius">删除 </button>
	     </td>
     </tr>
   </c:forEach>
  </tbody>
</table>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager,form = layui.form() ;
	
	//删除活动
	$("tbody tr #del").click(function(){
		var _this = $(this), id = _this.data("id");
		$.ajax({
			url: "pointsActivity/delActivity?id="+id,
			type: "POST",
			dataType: "JSON",
			async: false,
			success:function(data){
				 if( data.data >0 ){
					_this.parent().parent().remove();
				} 
			}	
		}); 
	})
	
	//编辑活动
	$("tbody tr #edit").click(function(){
		var id = $(this).data("id");
		window.location.href="pointsActivity/toUpdateActivity?id="+id;
	})
}) 

</script>
