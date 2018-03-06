<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类规格</title>
</head>
<body>
<div class="layui-form" >
	<form class="layui-form" action="category/addWordSeg" method="post" >
		<div class="layui-form-item"  >
		    <div class="layui-inline">
		      <label class="layui-form-label">分词名称</label>
		      <div class="layui-input-inline">
		        <input type="text" name="wordSeg" lay-verify="required"  id="name"  lay-filter="name" value="${data.wordSeg }"   autocomplete="off" class="layui-input">
		      </div>
		    </div>
		   
		    <!-- 分词图片 -->
			<div class="layui-form-item">
			    <label class="layui-form-label">图标</label>
				<div id="imgdiv" class="layui-input-block"  ><img width="100" height="100" src="${not empty data.imgPath? data.imgPath : 'goods/image/defaultImage.png' }" /></div>
				<div id="container"style="margin-left: 100px;">
					<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
					<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
					<input type="hidden" name="imgPath" id="icon" value="${data.imgPath }">
				</div>
				<pre id="console"></pre>
			</div>
		   
		    <input type="hidden" name="cid" value="${cid }">
		    <input type="hidden" name="id" value="${data.id }">
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">排序</label>
		      <div class="layui-input-inline">
		        <input type="text" name="seq" lay-verify="number" lay-filter="seq" value="${data.seq }"   autocomplete="off" class="layui-input">
		      </div>
	    	</div>
 		 </div>
	</form>
</div>
</body>
<script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">layui.config({base:"commons/layui/lay/modules/"}).use("global");</script>
<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="goods/category/js/categoryupload.js"></script>
<script type="text/javascript">
layui.use(["form"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	form.verify({  //表单校验
		name:function(value,item){
			if(!value){
				return "类别名称不能为空" ;
			}
		}
	});
	
})	
</script>
</html>