<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>		
<div class="layui-tab-item layui-show ">
	<input type="hidden" id="pageCount" value="${totalPage }" />
	<form id="myForm">
	   <div class="layui-input-inline">
	     	<input type="text" class="q-rule layui-input " id="category" zfj-query="category_name:cn" value="${param.categoryName}" placeholder="点击选择分类" name="category_name" readonly="readonly">
    </div>
		
		<div class="layui-input-inline">
     <input type="text" class="q-rule layui-input choose" data-vid="3" id="brandName" zfj-query="brand_name:cn" value="${param.brandName}" placeholder="点击选择品牌" name="brand_name" readonly="readonly"/>
    </div>
  
    <div class="layui-input-inline">
      <input type="text" class="q-rule layui-input choose" data-vid="4" id="supplierName" zfj-query="supplier_name:cn" value="${param.supplierName}" placeholder="点击选择供应商" name="supplier_name" readonly="readonly"/>
    </div>
     
	<div class="layui-input-inline">
   	 	<input type="text" class="q-rule layui-input" zfj-query="code:cn" value="${params.code}" placeholder="编码" name="code"/>
    </div>
    	
	<div class="layui-input-inline">
 		<input type="text" class="q-rule layui-input" zfj-query="name:cn" value="${param.name}" placeholder="商品名称" name="name"/>
    </div>
		
		<input type="submit" class="layui-btn btn-submit" value="提交"  />
		<input type="button" class="layui-btn" id="reset" value="重置"  />
	</form> 
</div>	
	<div id="mytable" class="layui-form" ></div>
	<div id="mypager"></div>
</body>
<script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">layui.config({base:"commons/layui/lay/modules/"}).use("global");</script>
<script type="text/javascript">
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"pointsActivity/goodsList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
	
	$('#category').on('click', function(){ //选择 分类 按钮  
		   var cnameStr = ''; //分类名称字符串
		   var index = layer.open({
			   type: 2,
			   title: '请选择商品的分类',
			   shadeClose: true,
			   area: ['500px', '50%'],
			   content: 'goods/toCategoryList',
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //获取选择分类的值
				   var body = layer.getChildFrame('body', index);
				   var tt = body.find("table input[type='checkbox']");
				   tt.each(function(index, item){
					 if( item.checked ){
						 cnameStr = cnameStr+$(this).data("name")+",";
					 }
				   });
				   $("#category").val(cnameStr);
				   layer.close(index); 
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
		});
	
	$('.choose').on('click', function(){ //选择按钮
		   var vid = $(this).data("vid");
		   var index = layer.open({
			   type: 2,
			   title: '请选择',
			   shadeClose: true,
			   area: ['400px', '50%'],
			   content: 'goods/chooseList?vid='+vid,
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //获取选择分类的值
				   var tt = layer.getChildFrame('body', index).find("table input[type='radio']");
			 	   tt.each(function(index, item){
					 if( item.checked ){
						 if(vid == 3) { $("#brandName").val($(this).data("name")); } //品牌
						 if(vid == 4) { $("#supplierName").val($(this).data("name")); } //供应商
					 }
				   });
				  
				   layer.close(index); 
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
	});
	
	
	$("#reset").click(function(){ //重置按钮
		$(".q-rule").val("")
	})
	
})


</script>
</html>