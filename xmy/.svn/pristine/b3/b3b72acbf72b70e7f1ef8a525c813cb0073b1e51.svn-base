<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table"  >
  <colgroup>
   <col width="150">
   <col width="250">
   <col width="150">
   <col width="150">
   <col width="150">
   <col width="150">
 </colgroup>
 <thead>
   <tr>
     <th>商品分类</th>
     <th>父级名称</th>
     <th>分类编码</th>
     <th>排序</th>
     <th>图标</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${dList }" var="item">
	    <tr>
		   	 <td>${item.name }</td>
		     <td>${item.parentName }</td>
		     <td>${item.sn }</td>
		     <td>${item.weight }</td>
		     <td><img alt="" src="${item.icon }"  width="50" height="50"></td>
		     <td>
		     	<button id="edit"  data-id="${item.id }"  class="layui-btn layui-btn-radius">编辑</button> 
		     	<button id="del" data-id="${item.id }"  data-status="${item.status }" data-parentid="${item.parentId }" class="layui-btn layui-btn-radius" >
		     			<c:if test="${item.status == 0 }">禁用</c:if><c:if test="${item.status == 1 }">启用</c:if>
		     	</button>
		     </td>
	     </tr>
	 	<c:if test="${not empty item.children }">
	     	<c:forEach items="${item.children }" var="childItem">
		     	<tr>
				   	 <td><i class="layui-icon" style="font-size: 15px; color: gray;">&#xe625;</i>${childItem.name }</td>
				     <td>${childItem.parentName }</td>
				     <td>${childItem.sn }</td>
				     <td>${childItem.weight }</td>
				     <td><img alt="" src="${childItem.icon }"  width="50" height="50"></td>
				     <td>
				     	<button id="edit"  data-id="${childItem.id }"  class="layui-btn layui-btn-radius">编辑</button> 
				     	<button id="del" data-id="${childItem.id }"  data-status="${childItem.status }" data-parentid="${childItem.parentId }" class="layui-btn layui-btn-radius" >
				     			<c:if test="${childItem.status == 0 }">禁用</c:if><c:if test="${childItem.status == 1 }">启用</c:if>
				     	</button>
				     </td>
			     </tr>
	     	</c:forEach>
	     </c:if>  
   </c:forEach>
  </tbody>
</table> 
<script type="text/javascript">
layui.use(["pager","form"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ;
	$("table tr td #del").click(function(){
		var _this = $(this), id = _this.data("id"), status = _this.data("status");
		tool.confirm("您真的要点击吗？",function(){
			if( status == 0 ){ //禁用
				updateStatus(id,_this,1) ;
				//checkHtml(id,1,"启用"); //凡是子集分类不为空的 子集分类也全部被禁用
			} else {  //启用
				updateStatus(id,_this,0);
			}
		}) ;
	})
	
	$("table tr td #edit").click(function(){  //编辑
		var id = $(this).data("id");
		window.location.href="category/toEditCategory?id="+id;
	});
	
	var _txt ;
	function updateStatus(id,_this,status){
		if (status == 0 ){
			_txt = "禁用";
		} else {
			_txt = "启用";
		}
		$.ajax({
			url: "category/updateStatus?id="+id+"&status="+status,
			type: "POST",
			dataType: "JSON",
			async: false,
			success:function(data){
				 if(data.data >= 0 ){
					updateThis(_this,status,_txt)
				}  
			}
		})
		
		
	}
	
	function delCategory(id,_this){  //禁用
		$.ajax({
			url: "category/deleteCategory?id="+id,
			type: "POST",
			dataType: "JSON",
			async: false,
			success:function(data){
				if(data.data >= 1 ){
					updateThis(_this,1,'启用')  ;
				}
			}
		})
	}
	
	function reoverCategory(id,_this){ //启用
		$.ajax({
			url: "category/reoverCategory?id="+id,
			type: "POST",
			dataType: "JSON",
			async: false,
			success:function(data){
				if(data.data == 0 ){
					updateThis(_this,0,'禁用')
				} else{
					tool.info("请先启用父级分类然后再启用子级分类") ;	
				}
			}
		})
	}
	 
  function checkHtml(id,val,txt){  //动态修改html
		var child = $("table tbody button#del ")  ;
		 child.each(function(index, item){
			 var parentId = $(this).data("parentid");
			if( id == parentId ){
				$(this).text(txt);
				$(this).data("status",val);
			}
		 });
	}   
	 
	function updateThis(_this,val,txt){
		_this.data("status",val);
		_this.text(txt);
		
	}   
})

</script>