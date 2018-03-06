<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加品牌</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
 		 <ul class="layui-tab-title site-title">
	      	<a href="brand/brandList"><li class="" >品牌列表</li></a>
	      	<li class="layui-this">添加品牌</li>
	 	</ul>
	  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
		<form class="layui-form"  action="brand/addtBrand" method="post" id="form">
			<div class="layui-form-item">
			    <label class="layui-form-label">品牌名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="name" lay-verify="name"  autocomplete="off" placeholder="单行输入" class="layui-input">
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">品牌编号</label>
			    <div class="layui-input-block">
			      <input type="text" name="sn" lay-verify="sn"  autocomplete="off" placeholder="单行输入" class="layui-input">
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">排序</label>
			    <div class="layui-input-block">
			      <input type="text" name="weight" lay-verify="number"  autocomplete="off" placeholder="值越小越靠前" class="layui-input">
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">店铺地址</label>
			    <div class="layui-input-block">
			      <input type="text" name="url"   autocomplete="off" placeholder="单行输入" class="layui-input" >
			    </div>
			</div>
			
			<!-- 品牌图片 -->
			<div class="layui-form-item">
			    <label class="layui-form-label">图标</label>
				<div id="imgdiv" class="layui-input-block"  ></div>
				<div id="container"style="margin-left: 100px;">
					<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
					<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
					<input type="hidden" name="icon" id="icon" value="">
				</div>
				<pre id="console"></pre>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">是否启用</label>
			    <div class="layui-input-block">
			      <input type="checkbox"   lay-skin="switch" lay-filter="status" lay-text="ON|OFF">
			      <input type="hidden" name="status" id="status" value="1">
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
<script type="text/javascript" src="goods/supplier/js/supplierupload.js"></script>
<script type="text/javascript">
layui.use(["form","tool","upload"], function(){
	var layer = layui.layer,tool = layui.tool,$ = layui.jquery,form = layui.form() ;
	 form.on("switch(status)", function(data){
	 	if(data.value == 'on' ){
	 		$("#status").val(0);
	 	}else{
	 		$("#status").val(1);
	 	}
	});
	form.verify({  //表单校验
		name:function(value,item){
			if(!value){
				return "品牌名称不能为空" ;
			}
		},
		sn:function(value,item){
			if(!value){
				return "品牌编码不能为空" ;
			}
		}
	}) ;

	//品牌图片上传
	layui.upload({

	});

	//监听提交
  form.on('submit(form)', function(data){
		$("#form").submit();
  });
	
})


</script>
</html>
