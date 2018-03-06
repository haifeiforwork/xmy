<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类列表</title>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
	<ul class="layui-tab-title site-title">
      	<li class="layui-this" >分类列表</li>
      	<a href="category/toAddCategory"><li >添加分类</li></a>
	 </ul>
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
		<input type="hidden" value="${totalPage}" id="pageCount">
		<div class="layui-tab-item layui-show">
		<form id="myForm">
			<div class="layui-input-inline">
				<!-- <label class="layui-form-label">类别名称：</label> --> <input type="text" class="layui-input q-rule" zfj-query="name:cn" value="${param.name}" name="name" placeholder="父级类别名称" style="width: 150px;"/>
			</div>
			<div class="layui-input-inline">
				<!-- <label class="layui-form-label">类别编码：</label>  --><input type="text" class="layui-input q-rule" zfj-query="sn:cn" value="${param.sn}" name="sn" placeholder="父级类别编码" style="width: 150px;"/>
			</div>
			<input type="submit" class="layui-btn" value="提交"/> 
			
		</form> 
		
		<div id="mytable">
			
		</div>
		<div  style="display:none;" >
			<div id="mypager" ></div>  
		</div>
		</div>
	    
	</div>
	</div>
	<%@ include file="/commons/buttom.jsp"%>
</div>		
</body>

<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ;
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"category/categoryList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
	}) ;	
});

</script>
</html>