<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>栏目管理</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
      <li class="layui-this">置顶通知管理</li>
      <li class="">添加置顶通知</li>
    </ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show">

				
						<div id="mytable" class="layui-form"></div>
						<div id="mypager"></div>
						<input type="hidden" id="pageCount" value="${pageCount }" />
				</div>
				
			
				
				<div class="layui-tab-item">
			  		<div  style="width:40%;">
								<form class="layui-form" action="content/notification/add" method="post">
									<div class="layui-form-item">
							    <label class="layui-form-label">开始时间</label>
							    <div class="layui-input-block">
							     <input class="layui-input" name="startTime" placeholder="请选择通知的开始时间" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
							    </div>
						  	</div>
						  	<div class="layui-form-item">
							    <label class="layui-form-label">结束时间</label>
							    <div class="layui-input-block">
							      <input class="layui-input" name="endTime" placeholder="请选择通知的结束时间" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
							    </div>
						  	</div>
						  		<div class="layui-form-item">
							    <label class="layui-form-label">创建人</label>
							    <div class="layui-input-block">
							      <input type="text" name="createAuthor" lay-verify="required" autocomplete="off" placeholder="请输入创建人" class="layui-input">
							    </div>
						  	</div>
			  				<div class="layui-form-item">
							    <label class="layui-form-label">通知内容</label>
							    <div class="layui-input-block">
							  		<textarea  id="articleEditor" name="content" style="width:1024px;height:500px;" ></textarea>
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
		<script type="text/javascript" src="article/js/notification.js"></script>
		
	</div>
</body>
<!-- ueditor富文本 -->
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="article/articleUeditor.js"></script>
</html>