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
 </colgroup>
 <thead>
   <tr>
  	 <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
     <th>缩略图</th>
     <th>上传时间</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${wList }" var="item">
    <tr>
    	 <td><input type="checkbox" data-id="${item.id }"  name="" lay-skin="primary" ></td> 		
	   	 <td><img alt="" src="${item.path }"  width="50" height="50"></td>
	     <td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td><button class="layui-btn layui-btn-radius" data-id="${item.id }" id="del">删除</button></td>
     </tr>
   </c:forEach>
  </tbody>
</table> 
 <div  class="layui-input-inline"  style="width: 150px;" >
   <select  lay-filter="batch" >
     <option>批量删除</option>
   </select>
 </div>
<div class="layui-input-inline">
	<input type="button" id="exc" class="layui-btn" value="提交" />
</div>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager,form = layui.form() ;
	//全选 监听
  
	form.on('checkbox(allChoose)', function(data){
     	var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	     child.each(function(index, item){
	       item.checked = data.elem.checked;
	       console.log($(this).data("id"))
	     });
     	form.render('checkbox');
   });
	
	var  idStr = "";
	$("table tr td #del").click(function(){ //删除
		idStr = $(this).data("id") , ts = $(this);
		 $.ajax({
			url: "waterMark/deleteImg",
			type: "POST",
			data: {"idStr":idStr},
			async: false,
			success:function(data){
				if(data.data >= 1 ){
					ts.parent().parent().remove();
				}
			}
		}) 
	})
	
	$("#exc").click(function(){   //批量删除
		tool.confirm("您真的要点击吗？",function(){
			 var child = $("table tbody input[type='checkbox']") ,idStr = "";
			 child.each(function(index, item){
				 if( item.checked ){
					 idStr = idStr+$(this).data("id")+","
				 }
			 });
			 $.ajax({
				url: "waterMark/deleteImg",
				type: "POST",
				dataType: "JSON",
				data: {"idStr":idStr},
				async: false,
				success:function(data){
					if(data.data >= 1 ){ updateHtml(child) }
				}
			})
		}) ;
	})

	function updateHtml(childHtml){  //动态修改html
		childHtml.each(function(index, item){
			 if( item.checked ){
				$(this).parent().parent().remove();
			 }
		 });
	}
});

</script>