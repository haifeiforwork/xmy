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
<form class="layui-form"  >
	<div  id="content"></div>
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
						html+= '<div class="layui-form-item" style="margin-left:40px;color:red"><input type="radio" name="category"   data-name="'+category.name+'" data-id="'+category.id+'"  title="'+category.name+'"  lay-skin="primary"><table>';
						var child = category.children ;  //子集
						if( child.length > 0){
							num = child.length%4;
							rownum = num==0? child.length/4:parseInt(child.length/4)+1; //得到循环的行数
							 for(var r = 0; r < rownum-1; r++){
								html+= '<tr>';
								 for(var j = 0; j < 4; j++){
									html+= '<td><input type="radio" name="category" data-name="'+child[r*4+j].name+'" data-id="'+child[r*4+j].id+'"  title="'+child[r*4+j].name+'"  lay-skin="primary"></td>';
								 }  
								html+= '</tr>';
							}
							if(	num>0 ){
								html+= '<tr>';
								for(var k = 0; k < num ;k++){
									html+= '<td><input type="radio" name="category" data-name="'+child[r*4+k].name+'" data-id="'+child[r*4+k].id+'"  title="'+child[r*4+k].name+'"  lay-skin="primary"></td>';
								}
								html+= '</tr>';
							}
							if(num == 0) {
								html+= '<tr>';
								 for(var j = 0; j < 4; j++){
									html+= '<td><input type="radio" name="category" data-name="'+child[r*4+j].name+'" data-id="'+child[r*4+j].id+'"  title="'+child[r*4+j].name+'"  lay-skin="primary"></td>';
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
	
})
</script>
 
</html>