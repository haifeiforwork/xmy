<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>积分策略列表</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
		 <ul class="layui-tab-title site-title">
		<li class="layui-this">积分策略列表</li>
		<a href="pointTactics/toAddTactics"><li >添加积分策略</li></a>
		 </ul>
		<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<input type="hidden" value="${pageCount}" id="pageCount">
			<div class="layui-tab-item layui-show ">
			<div id="mytable" class="layui-form" ></div>
			<div id="mypager"></div>
			<input type="hidden" id="pageCount" value="${pageCount }" />
			</div>
		</div>	
	</div>
	<!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
<script type="text/javascript">
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id")) ; */
	pager.load({
		url:"pointTactics/pointList",
		sort:"id desc"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
	
	$("#reset").click(function(){ //重置按钮
		$(".q-rule").val("");
	});
});
</script>
</div>
</body>
</html>