<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>栏目管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
.switchOn{
	display:inline-block;
	color:#FFF;
	height:20px;
	width:60px;
	background:#009688;
	text-align: center;
}
.switchOff{
	display:inline-block;
	color:#FFF;
	height:20px;
	width:60px;
	background:#FF5722;
	text-align: center;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"	lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
			      <li class="layui-this">栏目列表</li>
			      <li class="">添加栏目</li>
			    </ul>
				<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show">

					<!-- <form id="myForm" >
						<input type="submit" value="查询" class="layui-btn" /> <input
							type="reset" value="清空" id="clear_btn" class="layui-btn" />
					</form>
					<br /> <input type="submit" value="添加" class="layui-btn" /> -->
						<div id="mytable" class="layui-form"></div>
				<div id="mypager"></div>
				<input type="hidden" id="pageCount" value="${pageCount }" />
				</div>
				
			
				
				<div class="layui-tab-item">
			  		<div  style="width:40%;">
								<form class="layui-form" action="content/column/add" method="post">
			  				<div class="layui-form-item">
							    <label class="layui-form-label">栏目名称</label>
							    <div class="layui-input-block">
							      <input type="text" name="name" lay-verify="name"  autocomplete="off" class="layui-input q-rule" zfj-query="name:cn">
							    </div>
						  	</div>
			  				<div class="layui-form-item">
							    <label class="layui-form-label">权重</label>
							    <div class="layui-input-block">
							      <input type="text" name="weight" value="" lay-verify="weight"  autocomplete="off" class="layui-input q-rule" zfj-query="weight:cn">
							    </div>
						  	</div>
						  	<div class="layui-form-item">
							    <label class="layui-form-label">状态</label>
							    <div class="layui-input-block">
							      <select name="status" lay-filter="inter" class="q-rule">
							      	<option value="0" selected>启用</option>
							      	<option value="1" >禁止</option>
							      </select>
							    </div>
						  	</div>
					
						  	
							<div class="layui-form-item">
							    <div class="layui-input-block">
							      <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
							      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
							    </div>
						  	</div>
			  			</form>
			  		</div>
				</div>
			 </div>
			
			
		</div>

		<!-- 底部 -->
		<%@ include file="/commons/buttom.jsp"%>
		<script type="text/javascript" src="article/js/column.js"></script>
		
	</div>
</body>
</html>