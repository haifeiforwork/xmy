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
	<div class="layui-tab-item layui-show ">
		<form id="myForm">
		   <div class="layui-input-inline">
		     	<input type="text" class="q-rule layui-input " lay-skin="primary"  zfj-query="name:cn" value="${params.name}" placeholder="输入广告名" name="name" >
		    </div>
			
			<input type="submit" class="layui-btn btn-submit" value="提交"  />
			<input type="button" class="layui-btn adPosition"  value="广告位置设置"  />
		</form> 
	</div>	
	<div id="mytable" class="layui-form"></div>
	<div id="mypager"></div>
	<input type="hidden" id="pageCount" value="${pageCount }" />
				
</body>
<%@ include file="/commons/comm_footer_js.jsp"%>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	
	
	//console.log(pager.reqSerialize("#myForm","id desc")) ;
	pager.load({
		url:"advertisement/toChooseAdvertisementList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		//alert(data);
		$("#mytable").html(data) ;
		form.render();
	}) ;
	
	
	
	
	
	$("tbody td #edit").click(function(){
		window.location.href="advertisement/toEditAdvertisement?id="+$(this).data("id"); 
	})
})

</script>