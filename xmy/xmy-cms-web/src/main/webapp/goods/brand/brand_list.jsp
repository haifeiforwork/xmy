<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌列表</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
		<ul class="layui-tab-title site-title">
	      <li class="layui-this">品牌列表</li>
	      <a href="brand/toAddBrand"><li class="">添加品牌</li></a>
	 	</ul>
		<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<input type="hidden" value="${totalPage}" id="pageCount">
			<div class="layui-tab-item layui-tab-brief layui-show ">
				<form id="myForm">
					<div class="layui-input-inline">
						<!-- <label class="layui-input-inline">品牌名称：</label> --> <input type="text" class="layui-input q-rule" zfj-query="name:cn" value="${param.name}" name="name" placeholder="品牌名称" style="width: 150px;"/>
					</div>
					<div class="layui-input-inline">
						<!-- <label class="layui-form-label">品牌编码：</label> --> <input type="text" class="layui-input q-rule" zfj-query="sn:cn" value="${param.sn}" name="sn" placeholder="品牌编码" style="width: 150px;"/>
					</div>
					<input type="submit" class="layui-btn" value="提交"  />
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
var totalPage = "${totalPage}";  //总页数
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form() ;
	pager.load({
		url:"brand/brandList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
	
})

</script>
</html>