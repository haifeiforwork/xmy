<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告列表</title>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
		<ul class="layui-tab-title site-title">
	      	<li class="layui-this" >广告列表</li>
	      	<a href="advertisement/toAddAdvertisement"><li >添加广告</li></a>
		 </ul>
		<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<input type="hidden" value="${totalPage}" id="pageCount">
			<div class="layui-tab-item layui-show ">
				<form id="myForm">
				   <div class="layui-input-inline">
				     	<input type="text" class="q-rule layui-input " lay-skin="primary"  zfj-query="name:cn" value="${params.name}" placeholder="输入广告名" name="name" >
				    </div>
					
					<input type="submit" class="layui-btn btn-submit" value="提交"  />
					<input type="button" class="layui-btn adPosition"  value="广告位置设置"  />
				</form> 
			</div>	
				<div id="mytable" class="layui-form" ></div>
				<div id="mypager"></div>
		</div>	
	</div>
	<%@ include file="/commons/buttom.jsp"%>
</div>
</body>
<script type="text/javascript">
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"advertisement/advertisementList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
	
	//广告位置设置
	$(".adPosition").click(function(){
		window.location.href="advertisement/toAddAdvertisementPosition";
	})
	
})


</script>
</html>