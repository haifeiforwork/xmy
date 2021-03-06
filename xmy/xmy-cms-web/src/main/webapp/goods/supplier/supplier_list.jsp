<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商列表</title>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
	<ul class="layui-tab-title site-title">
      	<li class="layui-this" >供应商列表</li>
      	<a href="supplier/toAddSupplier"><li >添加供应商</li></a>
	 </ul>
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
	<input type="text" id="pageCount" hidden="hidden"  value="${totalPage }"/>
		<div class="layui-tab-item layui-show ">
			<form id="myForm">
				<div class="layui-input-inline">
					<!-- <label class="layui-form-label" style="width: 100px">供应商名称：</label> --> 
					<input type="text" class="q-rule layui-input" zfj-query="name:cn" value="${params.name}" name="name" placeholder="供应商名称" style="width: 150px;"/>
				</div>
				<div class="layui-input-inline">
					<!-- <label class="layui-form-label" style="width: 100px">供应商名称：</label> --> 
					<input type="text" class="q-rule layui-input" zfj-query="sn:cn" value="${params.sn}" name="name" placeholder="供应商编号" style="width: 150px;"/>
				</div>
				<input type="submit" class="layui-btn btn-submit" value="提交"  />
			</form> 
			<div id="mytable" class="layui-form" ></div>
			<div id="mypager"></div>
			
		</div>
	</div>
	</div>
	<%@ include file="/commons/buttom.jsp"%>
</div>		
</body>
<script type="text/javascript">
var totalPage = "${totalPage}";  //总页数
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"supplier/supplierList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
})

</script>
</html>