<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="layui-tab-item layui-show ">
	<form id="myForm">
		<div class="layui-input-inline">
			<label class="layui-form-label">名称：</label> <input type="text" class="layui-input q-rule" zfj-query="name:cn" value="${params.name}" name="name" style="width: 150px;"/>
			<c:if test="${vid!=3 || vid!=4 }">
				<input type="hidden" class="layui-input q-rule" name="vid" zfj-query="vid:eq"   value="${params.vid }">
			</c:if>
			<c:if test="${vid==3 || vid==4 }">
				<input type="hidden" class="layui-input q-rule" name="vid" zfj-query="vid:eq"   value="${vid }">
			</c:if>
		</div>
		<input type="submit" class="layui-btn btn-submit" value="提交"  />
	</form> 
	<div id="mytable" class="layui-form" ></div>
	<div id="mypager"></div>
</div>

</body>
<script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">layui.config({base:"commons/layui/lay/modules/"}).use("global");</script>
<script type="text/javascript">
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form() ;
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"goods/chooseList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
})


</script>

</html>