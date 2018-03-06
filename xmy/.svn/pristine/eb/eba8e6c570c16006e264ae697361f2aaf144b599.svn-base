<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table"  >
  <colgroup>
   <col width="50"> 
   <col width="150">
   <col width="150">
   <col width="150">
   <col width="200">
   <col width="150">
 </colgroup>
 <thead>
   <tr>
  	 <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
     <th>品牌名称</th>
     <th>品牌编码</th>
     <th>图片</th>
     <th>状态</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${tlist }" var="item">
    <tr>
    	 <td><input type="checkbox" name="" lay-skin="primary" data-id="${item.id }"></td> 
	   	 <td>${item.name }</td>
	     <td>${item.sn }</td>
	     <td><img alt="" src="${item.icon }"  width="50" height="50"> </td>
	     <td>
	     	<button id="opr" class="layui-btn"  data-id="${item.id }" data-status="${item.status }" >${item.status=='0'? '禁用':'启用' }</button>
	     </td>
	     <td>
	     	<button id="edit" data-id="${item.id }" class="layui-btn layui-btn-radius">编辑 </button>
	     </td>
     </tr>
   </c:forEach>
  </tbody>
</table>
	 <div  class="layui-input-inline"  style="width: 150px;" >
	    <select  lay-filter="batch" >
	      <option value="1">批量禁用</option>
	      <option value="0" >批量启用</option>
	    </select>
	</div>
	<div class="layui-input-inline">
		<input type="button" id="exc" class="layui-btn" value="提交" />
	</div>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager,form = layui.form() ;
	$("table tr td #opr").click(function(){ //修改品牌状态
		var id = $(this).data("id");
		var status = $(this).data("status");
		var txt = "" ,ts = $(this);
		if(status==0) {status = 1, txt = "启用"}
		else  status = 0, txt = "禁用"
		tool.load() ;
		 $.ajax({
			url: "brand/updateStatus?id="+id+"&status="+status,
			type: "POST",
			dataType: "JSON",
			async: false,
			success:function(data){
				if(data.data >= 1 ){
					ts.data("status",status);
					ts.text(txt)
					tool.unload() ;
				}
			}
		})  
	});
	
	$("table tr td #edit").click(function(){ //编辑品牌
		var id = $(this).data("id");
		window.location.href="brand/toUpdateBrand?id="+id;
	});
	
	//全选 监听
   form.on('checkbox(allChoose)', function(data){
     	var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	     child.each(function(index, item){
	       item.checked = data.elem.checked;
	       console.log($(this).data("id"))
	     });
     	form.render('checkbox');
   });
	
	var idStr = "";
	var status = "1"
	$("#exc").click(function (){  //批量操作
		tool.confirm("您真的要点击吗？",function(){
			var child = $("table tbody input[type='checkbox']") ,idStr = "" ;
			 child.each(function(index, item){
				 if( item.checked ){
					 console.log($(this).data("id"))
					 idStr = idStr+$(this).data("id")+","
				 }
			 });
			 if(idStr != ""){
				 batcheExc(idStr);
			  } 
		}) ;
	})
	
	form.on('select(batch)',function (data){
		status=data.value
	})
	
	function batcheExc(idStr){
		 tool.load() ;
		  $.ajax({
			url: "brand/batchUpdateStatus",
			type: "POST",
			data: {"idStr":idStr,"status":status},
			dataType: "JSON",
			async: false,
			success:function(data){
				if(data.data >= 1 ){
					tool.unload() ;
					location.reload()
				}
			}
		})    
	}
}) 

</script>
