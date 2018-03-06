<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传广告图片</title>
</head>
<body>
<div class="layui-form" >
	<form class="layui-form"  >
		<div class="layui-form-item">
		    <label class="layui-form-label">广告跳转链接</label>
			<div class="layui-input-block" style="width: 60%;">
				<input type="text" name="url" id="url" value="${imgUrl }"  autocomplete="off"  class="layui-input" >
			</div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">图片顺序</label>
			<div class="layui-input-block" style="width: 60%;">
				<input type="text" name="weight" id="weight" value="${imgWeight }" lay-verify="number" autocomplete="off" class="layui-input" >
			</div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">是否是商品图片</label>
		    <div class="layui-input-block">
		      <input type="checkbox"  lay-skin="switch" value="${not empty type? type:'1' }"  class="isGoods" lay-filter="isGoods" lay-text="ON|OFF"  ${type == '0'? 'checked':'' }>
		      <input type="hidden" name="type" id="type" value="${not empty type? type:'1' }"  class="isGoods" >
		    </div>
		</div>
		
		
		<!-- 上传广告图片 -->			   
		<div class="layui-form-item">
		    <label class="layui-form-label">图标</label>
			<div id="imgdiv" class="layui-input-block"  ><img width="100" height="100" src="${not empty imgPath? imgPath : 'goods/image/defaultImage.png' }" /></div>
			<div id="container"style="margin-left: 100px;">
				<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
				<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
				<input type="hidden" name="imgPath" id="imgPath" value="${imgPath}">
			</div>
			<pre id="console"></pre>
		</div>
		
	</form>
</div>
</body>
<script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">layui.config({base:"commons/layui/lay/modules/"}).use("global");</script>
<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="advertisement/js/adImgupload.js"></script>
<script type="text/javascript">
layui.use(["pager","form"],function(){
	 var form = layui.form(),layer = layui.layer,tool = layui.tool
	 ,$ = layui.jquery;
	 
	form.on("switch(isGoods)", function(data){
	 	if(data.value == '1' ){ $(".isGoods").val(0);}
	 	else { $(".isGoods").val(1); 	} 
	});
	
	
})


</script>
</html>