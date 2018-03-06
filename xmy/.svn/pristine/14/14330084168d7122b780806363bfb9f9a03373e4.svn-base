<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择商品分类</title>
</head>
<body>
<div class="layui-form" >
		<form class="layui-form" style="overflow:auto" >
			<div id="content" style="width: 50%;float:left"></div>
			<div id="wordseg" style="width: 50%;float:left">
				<span>搜索词汇：</span>
			</div>
		</form>
</div>
</body>
<script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">layui.config({base:"commons/layui/lay/modules/"}).use("global");</script>
<script type="text/javascript">
layui.use(["form","tool","layer"], function(){
	var form = layui.form() ,layer = layui.layer,tool = layui.tool,$ = layui.jquery;
	test();
	 var rownum = 0;
	 var num = 0;
	 function test(){
		var html = '';
		 $.ajax({
			url: "goods/categoryList",
			type: "POST",
			dataType: "JSON",
			async: false,
			success:function(data){
				   for(var i = 0; i< data.data.length; i++){
					    var category =  data.data[i]; 
						html+= '<div class="layui-form-item" style="margin-left:40px;"><label>'+category.name+'</label><table>';
						var child = category.children ;  //子集
						if( child.length > 0){
							num = child.length%4;
							rownum = num==0? child.length/4:parseInt(child.length/4)+1; //得到循环的行数
							 for(var r = 0; r < rownum-1; r++){
								html+= '<tr>';
								 for(var j = 0; j < 4; j++){
									html+= '<td><input type="checkbox" lay-filter="checked" data-name="'+child[r*4+j].name+'" data-id="'+child[r*4+j].id+'"  title="'+child[r*4+j].name+'"  lay-skin="primary"></td>';
								 }  
								html+= '</tr>';
							}
							if(	num>0 ){
								html+= '<tr>';
								for(var k = 0; k < num ;k++){
									html+= '<td><input type="checkbox" lay-filter="checked" data-name="'+child[r*4+k].name+'" data-id="'+child[r*4+k].id+'"  title="'+child[r*4+k].name+'"  lay-skin="primary"></td>';
								}
								html+= '</tr>';
							}
							if( num == 0 ){  //刚好四个子分类的情况
								html+= '<tr>';
								 for(var j = 0; j < 4; j++){
									html+= '<td><input type="checkbox" lay-filter="checked" data-name="'+child[r*4+j].name+'" data-id="'+child[r*4+j].id+'"  title="'+child[r*4+j].name+'"  lay-skin="primary"></td>';
								 }  
								html+= '</tr>';
							} 
							
						 }  
						html+= '</table></div><hr>';
					} 
				   $("#content").html(html) 
				   form.render();
			} 
		
		});  
	} 
	 
	 //选择分类下的规格
	 form.on('checkbox(checked)', function(data){
		 var id = $(this).data("id");
		 var flag = data.elem.checked ;
		 if (flag ==  true) {
			 var html = '<div id="'+id+'" >';
			 $.ajax({
					url: "goods/findCategoryWordSeg?id="+id,
					type: "POST",
					dataType: "JSON",
					async: false,
					success:function(data){
						   for(var i = 0; i< data.data.length; i++){
							    var parent =  data.data[i]; 
								html+= '<div class="layui-form-item" style="margin-left:40px;"><label>'+parent.wordSeg+'</label><table>';
								var child = parent.child ;  //子集
								if( child.length > 0){
									num = child.length%4;
									rownum = num==0? child.length/4:parseInt(child.length/4)+1; //得到循环的行数
									 for(var r = 0; r < rownum-1; r++){
										html+= '<tr>';
										 for(var j = 0; j < 4; j++){
											html+= '<td><input type="checkbox"  data-name="'+child[r*4+j].wordSeg+'" data-id="'+child[r*4+j].id+'"  title="'+child[r*4+j].wordSeg+'"  lay-skin="primary"></td>';
										 }  
										html+= '</tr>';
									}
									if(	num>0 ){
										html+= '<tr>';
										for(var k = 0; k < num ;k++){
											html+= '<td><input type="checkbox"  data-name="'+child[r*4+k].wordSeg+'" data-id="'+child[r*4+k].id+'"  title="'+child[r*4+k].wordSeg+'"  lay-skin="primary"></td>';
										}
										html+= '</tr>';
									}
									if( num == 0 ){  //刚好四个子分类的情况
										html+= '<tr>';
										 for(var j = 0; j < 4; j++){
											html+= '<td><input type="checkbox"  data-name="'+child[r*4+j].wordSeg+'" data-id="'+child[r*4+j].id+'"  title="'+child[r*4+j].wordSeg+'"  lay-skin="primary"></td>';
										 }  
										html+= '</tr>';
									} 
									
								 }  
								html+= '</table></div><hr>';
							} 
						   html+= '</div>';
						   $("#wordseg").append(html) 
						   form.render();
					} 
				 });
		 } else {  //取消选中类别
			  $("#"+id+"").remove(); 
			  form.render();
		 }
		
	 })
})
</script>
 
</html>