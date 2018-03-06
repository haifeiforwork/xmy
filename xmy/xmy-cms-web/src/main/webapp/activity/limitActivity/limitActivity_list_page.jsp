<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table"  >
  <colgroup>
   <col width="50">
   <col width="150">
   <col width="150">
   <col width="150">
   <col width="150">
   <col width="150">
 </colgroup>
 <thead>
   <tr>
   	 <th>活动id</th>
     <th>促销名称</th>
     <th>促销类型</th>
     <th>活动描述</th>
     <th>活动状态</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${alist }" var="item">
    <tr>
    	 <td>${item.id }</td>
	   	 <td>${item.name }</td>
	     <td>${item.typeName }</td>
	     <td>${item.description }</td>
	     <td>
		 	<c:if test="${item.status == 0 }">活动进行中</c:if>
		 	<c:if test="${item.status == 1 }">活动已结束</c:if>
	    </td>
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
			url: "limitActivity/delActivity?id="+id,
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
		window.location.href="limitActivity/toUpdateActivity?id="+id;
	})
}) 

</script>
