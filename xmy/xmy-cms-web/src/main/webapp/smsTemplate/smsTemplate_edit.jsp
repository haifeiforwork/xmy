<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>文章编辑</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
			      <li >短信模板列表</li>
			      <li class="layui-this">短信模板编辑</li>
			    </ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item">
					<div id="mytable" class="layui-form"></div>
					<div id="mypager"></div>
					<input type="hidden" id="pageCount" value="${pageCount }" />
				</div>
				
			
				
				<div class="layui-tab-item  layui-show">
			  		<div  style="width:40%;">
								<form class="layui-form" action="mallSetting/smsTemplate/update" method="post">
									
								  	
					  				<div class="layui-form-item">
									    <label class="layui-form-label">模板名称</label>
									    <div class="layui-input-block">
									     <input  name="id" value="${termData.id }" lay-verify="id" type="hidden" autocomplete="off" class="layui-input q-rule" zfj-query="id:cn">
									      <input type="text" name="name" value="${termData.name }" lay-verify="name"  autocomplete="off" class="layui-input q-rule" zfj-query="name:cn">
									    </div>
						  			</div>
						 		  	<div class="layui-form-item">
									    <label class="layui-form-label">短信内容</label>
									    <div class="layui-input-block">
									      <textarea id="des" name="des" style="display: none;">${termData.des}</textarea>
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