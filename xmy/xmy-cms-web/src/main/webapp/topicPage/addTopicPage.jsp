<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加专题页面</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<ul class="layui-tab-title site-title">
      <li class=""><a href="topicPage/list">专题活动列表</a></li>
      <li class="layui-this">添加专题</li>
    </ul>
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  		<div class="layui-tab-item ">
	  		
			<div id="mytable">
				
			</div>
			<div id="mypager"></div>
		</div>
  		
  		<div class="layui-tab-item layui-show">
	  		
				<form class="layui-form" action="topicPage/saveAddTopicPage" style="width:50%;">
					<div class="layui-form-item">
					    <label class="layui-form-label">专题名称：</label>
					    <div class="layui-input-block">
					      <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入专题名称" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">专题名称：</label>
					    <div class="layui-input-block">
					    <input type="hidden" id="toppicPagePath" name="filepath" lay-verify="required">
					    <input type="file" name="file" lay-type="file" class="layui-upload-file" lay-title="上传压缩文件"> 
					    </div>
					  </div>
					   <div class="layui-form-item">
					   		 <div class="layui-input-block">
						      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
						    </div>
					  </div>
	  			</form>
		</div>
  	</div>
  </div>
 
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
  <script type="text/javascript" src="topicPage/js/topicPage.js"></script>
</div>
</body>
</html>