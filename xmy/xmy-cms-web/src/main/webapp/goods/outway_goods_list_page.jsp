<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <colgroup>
  	<col width="35"> 
  	<col width="80">
    <col width="200">
   	<col width="80"> 
   	 
    <col width="100">
    <col width="80">
    <col width="50">
    <col width="100">
    <col width="150">
  </colgroup>
  <thead>
    <tr>
     <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
      <th>编码</th>
      <th>商品名称</th>
      <th>图片</th>
      <th>分类</th>
      <th>售价</th>
      <th>状态</th>
      <th>下架时间</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  	<c:forEach items="${glist}" var="item">
  	<tr >
  	  <td><input type="checkbox" data-id="${item.id }" name="" lay-skin="primary"></td>
  	   <td>${item.code}</td>
      <td>${item.name}</td>
      <td><img alt="" src="${item.imgPath}"  width="50" height="50"></td> 
      <td>${item.categoryName}</td>
      <td>${item.price}</td>
      <td><button id="oper" class="layui-btn layui-btn-radius" style="background-color: ${item.isPutway == 1? 'gray;':''}" data-id="${item.id }" data-status="${item.isPutway }" >${item.isPutway == 0 ? '上架':'下架'}</button></td>
      <td><fmt:formatDate value="${item.outwayTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      <td>
		    <div class="site-demo-button  " >
		      <button class="layui-btn"  >查看</button>
		      <button class="layui-btn" id="edit" data-id="${item.id }" >编辑</button>
		      <button class="layui-btn" id="del" data-id="${item.id }"  >删除</button>
		    </div>
      </td>
    </tr>
  	</c:forEach>
  </tbody>
</table>
 <div  class="layui-input-inline"  style="width: 150px;" >
   <select  lay-filter="batch" >
     <option value="0">批量上架</option>
    <!--  <option value="1">批量下架</option> -->
     <option value="2">批量删除</option>
   </select>
 </div>
<div class="layui-input-inline">
	<input type="button" id="exc" class="layui-btn" value="提交" />
</div>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	var idStr = "" ; //id字符串
	var status = "" ; //上下架状态
	$("table tr td #oper").click(function (){ //修上下架状态
		var status = $(this).data("status"), ts=$(this) ,idStr = "";
		if( status == 0 ){ status=1 , txt="下架"} 
		else status=0 , txt="上架"
		idStr = $(this).data("id");
		tool.load() ;
		 $.ajax({
			url: "goods/updatePutWayStatus",
			type: "POST",
			dataType: "JSON",
			data:{"idStr":idStr,"status":status},
			async: false,
			success:function(data){
				if(data.data >= 1 ){
					if(status == 0){ ts.css("background-color","") }
					else{ ts.css("background-color","gray") }
					ts.data("status",status);
					ts.text(txt)
					tool.unload() ;
				}
			}
		});   
	})
	
	$("table tr td #del").click(function(){ //删除商品
		idStr = $(this).data("id") , ts = $(this);
		tool.confirm("您真的要点击吗？",function(){
			tool.load() ;
			$.ajax({
				url: "goods/deleteGoods",
				type: "POST",
				dataType: "JSON",
				data:{"idStr":idStr},
				async: false,
				success:function(data){
					if(data.data >= 1 ){
						ts.parent().parent().parent().parent().remove();
						tool.unload() ;
					} 
				}
			}); 
		});
	})
	
	var pageIndex = "${pageIndex}";
	//编辑
	$("table tr td #edit").click(function(){
		var id = $(this).data("id");
		/* window.location.href="goods/toUpdateGoods?id="+id; */
		window.open("goods/toUpdateGoods?id="+id+"&pageIndex="+pageIndex);
	})
	
	var val = "0";
	form.on('select(batch)',function (data){  //监听选中状态
		val=data.value
	})
	
	//批量操作
	$("#exc").click(function(){
		tool.confirm("您真的要点击吗？",function(){
		  var child = $("table tbody input[type='checkbox']") ,idStr = "";
			 child.each(function(index, item){
				 if( item.checked ){
					 idStr = idStr+$(this).data("id")+","
				 }
			 });
			 if(idStr != "" ){
				    if( val != '2'){  //批量上下架
						updatePutWayStatus(idStr,val);
					} else{
						delGoods(idStr);
					}  
					updateHtml(child,val);  
			 } 
		}) ;
	})
	
	//全选 监听
	form.on('checkbox(allChoose)', function(data){
     	var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	     child.each(function(index, item){
	       item.checked = data.elem.checked;
	       console.log($(this).data("id"))
	     });
     	form.render('checkbox');
   });
	
	function delGoods(idStr){  //批量删除商品
		tool.load() ;
		$.ajax({
			url: "goods/deleteGoods",
			type: "POST",
			dataType: "JSON",
			data:{"idStr":idStr},
			async: false,
			success:function(data){
				if(data.data >= 1 ){
					tool.unload() ;
				} 
			}
		}); 
	}
	
	function updatePutWayStatus(idStr,status){ //批量修改上下架状态
		tool.load() ;
		  $.ajax({
			url: "goods/updatePutWayStatus",
			type: "POST",
			dataType: "JSON",
			data:{"idStr":idStr,"status":status},
			async: false,
			success:function(data){
				if(data.data >= 1 ){
					tool.unload() ;
				}
			}
		});    
	}
	
	function updateHtml(childHtml,val){ //动态修改html
		childHtml.each(function(index, item){
			 if( item.checked ){
				if(val != 2){ //批量修改状态
					var txt = '';
					var dom = $(this).parent().parent().find("#oper");
					dom.data("status",val);
					if(val == '0' ){ dom.text("上架"); dom.css("background-color","") }
					else{ dom.text("下架") ;dom.css("background-color","gray") } 
				} else{
					$(this).parent().parent().remove();
				}
			 }
		 });
	}
})

</script>