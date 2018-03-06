<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动管理</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
		<ul class="layui-tab-title site-title">
	      	<li class="layui-this" >活动列表</li>
	      	<a href="limitActivity/toAddActivity"><li >添加活动</li></a>
		 </ul>
		<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<div class="layui-tab-item layui-tab-brief layui-show ">
				<form id="myForm">
					<div class="layui-input-inline">
							<input type="text" class="layui-input q-rule" zfj-query="name:cn" value="${params.name}" name="name" placeholder="活动名称" style="width: 150px;"/>
					</div>
					<div class="layui-input-inline">
					 	     <input type="text"  name="begin_time" zfj-query="begin_time:ge" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})"  placeholder="活动开始时间" autocomplete="off" class="layui-input q-rule" >
					</div>
					<div class="layui-input-inline">
					 	     <input type="text"   name="end_time" zfj-query="end_time:le" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})"  placeholder="活动结束时间" autocomplete="off" class="layui-input q-rule" >
					</div>
					<input type="submit" class="layui-btn btn-submit" value="提交"  />
				</form>
			</div>
			<input type="hidden" id="pageCount"  value="${totalPage }"/> 
			<div id="mytable" class="layui-form" ></div>
			<div id="mypager"></div>
		</div>
	</div>
	<%@ include file="/commons/buttom.jsp"%>
</div>		   
</body>
<script type="text/javascript">
layui.use(["pager","form","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form() ;
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"limitActivity/activityList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
})

</script>
</html>