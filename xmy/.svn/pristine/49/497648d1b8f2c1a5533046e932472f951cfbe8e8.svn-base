<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>短信模板管理</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
      <li class="layui-this">短信模板列表</li>
      <li class="">添加短信模板</li>
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
			  		<div  style="width:50%;">
								<form class="layui-form" action="mallSetting/smsTemplate/add" method="post">
			  				<div class="layui-form-item">
							    <label class="layui-form-label">模板名称</label>
							    <div class="layui-input-block">
							      <input type="text" name="name" lay-verify="name"  autocomplete="off" class="layui-input q-rule" zfj-query="name:cn">
							    </div>
						  	</div>
						  	 <div class="layui-form-item">
							    <label class="layui-form-label">短信内容</label>
							    <div class="layui-input-block">
							     <textarea id="des" name="des" style="display: none;"></textarea>
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
		<script type="text/javascript" src="smsTemplate/js/smsTemplate.js"></script>
		
	</div>
</body>
</html>