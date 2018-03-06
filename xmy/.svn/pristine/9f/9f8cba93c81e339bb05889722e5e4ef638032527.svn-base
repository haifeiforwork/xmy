<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加分类</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
	<ul class="layui-tab-title site-title">
      	<a href="category/categoryList" ><li >分类列表</li></a>
      	<li class="layui-this">添加分类</li>
	 </ul>
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
		<form class="layui-form"  action="category/addCategory" method="post" id="form">
					<div class="layui-form-item">
					    <label class="layui-form-label">上级</label>
					    <div class="layui-input-block" >
					     <select name="parentId" lay-filter="parentId" >
					     	 <option value="0">作为一级菜单</option>
						     	 <c:forEach items="${cList }" var="item">
						      		<option value="${item.id }">${item.name }</option>
						      	 </c:forEach> 
					      </select> 
					 	</div>
		  			</div>
					
					<input type="hidden" name="parentId" id="parentId" value="">
					
					<div class="layui-form-item">
					    <label class="layui-form-label">分类名称</label>
					    <div class="layui-input-block">
					      <input type="text" name="name" lay-verify="name"  autocomplete="off" placeholder="单行输入" class="layui-input">
					    </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">分类编码</label>
					    <div class="layui-input-block">
					      <input type="text" name="sn" lay-verify="sn"  autocomplete="off" placeholder="单行输入" class="layui-input">
					    </div>
					</div>
					
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">排序</label>
					   <div class="layui-input-block">
					      <input type="text" placeholder="值越小越靠前"  name="weight"  lay-filter="weight" lay-verify="number" class="layui-input" >
					    </div>
					 </div>
							  			
		  			<div class="layui-form-item" id="showDiv">
					    <label class="layui-form-label">是否首页显示</label>
					    <div class="layui-input-block">
					      <input type="checkbox"  lay-skin="switch" value="0"  class="isShow"  lay-filter="isShow" lay-text="ON|OFF" checked>
					      <input type="hidden" name="isShow"  class="isShow" id="isShow"  value="0" >
					    </div>
					</div>
							
					<div class="layui-form-item">
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">图标上传</label>
							<div id="imgdiv" class="layui-input-block"  ></div>
							<div id="container"style="margin-left: 100px;">
								<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="icon" id="icon" value="">
							</div>
							<pre id="console"></pre>
						</div>
						
						<!-- app一级分类图标 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;" >
						    <label class="layui-form-label">app图标上传</label>
							<div id="appImgdiv" class="layui-input-block"  ></div>
							<div id="container2"style="margin-left: 100px;">
								<a id="selectfiles2" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles2" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="appIcon" id="appIcon" value="">
							</div>
							<pre id="console"></pre>
						</div>
						
						<!-- app 一级分类选中图标 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;">
					    <label class="layui-form-label">app选中图标上传</label>
						<div id="appOnImgdiv" class="layui-input-block"  ></div>
						<div id="container3"style="margin-left: 100px;">
							<a id="selectfiles3" href="javascript:void(0);" class='btn'>选择文件</a>
							<a id="postfiles3" href="javascript:void(0);" class='btn'>开始上传</a>
							<input type="hidden" name="appOnIcon" id="appOnIcon" value="">
						</div>
						<pre id="console"></pre>
						</div>
						
						<!-- pc一级分类背景图 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;">
						    <label class="layui-form-label">pc一级分类背景图上传</label>
							<div id="pcBackgroudImgdiv" class="layui-input-block"  ></div>
							<div id="container4"style="margin-left: 100px;">
								<a id="selectfiles4" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles4" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="pcBackgroudImg" id="pcBackgroudImg" value="">
							</div>
							<pre id="console"></pre>
						</div>
						
						<!-- 一级分类描述图 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;">
						    <label class="layui-form-label">分类描述图</label>
							<div id="descriptionImgDiv" class="layui-input-block"  ></div>
							<div id="container5"style="margin-left: 100px;">
								<a id="selectfiles5" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles5" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="descriptionImg" id="descriptionImg" value="">
							</div>
							<pre id="console"></pre>
						</div>
						
					</div>
					
					<div class="layui-form-item appIconDiv" style="width: 400px;">
					    <label class="layui-form-label">背景图跳转地址</label>
					   <div class="layui-input-block">
					      <input type="text" placeholder="输入背景图跳转链接"  name="backgroundImgUrl"   class="layui-input" >
					    </div>
					 </div>
					
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">描述</label>
					    <div class="layui-input-block">
					      <textarea placeholder="请输入内容" name="des" class="layui-textarea"></textarea>
					    </div>
					</div>
					 
		  			<div class="layui-form-item">
					    <div class="layui-input-block">
					      <button class="layui-btn" lay-submit="" lay-filter="form">保存</button>
					    </div>
					 </div>
		</form>
	</div>
</div>
<%@ include file="/commons/buttom.jsp"%>
</div>
</body>
<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="goods/category/js/categoryupload.js"></script>
<script type="text/javascript">
layui.use(["form","tool","upload"], function(){
	var layer = layui.layer,tool = layui.tool,$ = layui.jquery,form = layui.form() ;
	 form.on("select(parentId)", function(data){ 
		 if(data.value != 0){
			 $("#showDiv").hide(); //是否首页显示
			 $("#isShow").val("");
			 $(".appIconDiv").hide(); //一级分类app图标
			 $("#appIcon").val("");
		 }else{
			 $("#parentId").val(data.value);
			 $("#showDiv").show(); //是否首页显示
			 $(".appIconDiv").show();
		 }
	});
	 
	form.on("switch(isShow)", function(data){
	   	if(data.value == '1' ){$(".isShow").val(0)}
	   	else{ $(".isShow").val(1) }	
	});
	form.verify({  //表单校验
		name:function(value,item){
			if(!value){
				return "商品名称不能为空" ;
			}
		},
		sn:function(value,item){
			if(!value){
				return "商品编码不能为空" ;
			}
		}
	}) ;
	
	
	//监听提交
	form.on('submit(form)', function(data){
	    $("#form").submit();
	});

})

</script>

</html>