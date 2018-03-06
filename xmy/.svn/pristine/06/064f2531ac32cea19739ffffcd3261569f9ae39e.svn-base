<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>APP关于香满圆页面</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
      <li class="layui-this">APP关于香满圆页面设置</li>
    </ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show">
			  		<div  style="width:60%;">
						<form class="layui-form my-form" action="content/appAboutXmyH5/update" method="post">
					  			
								  	<input type="hidden" name="id" value="${appAbouXmyH5.id }">
								  	<input type="hidden" name="status" value="${appAbouXmyH5.status }">
								  	<input type="hidden" name="name" value="${appAbouXmyH5.name }">
								  	<input type="hidden" name="vid" value="${appAbouXmyH5.vid }">
								  	  <div class="layui-form-item">
									    <label class="layui-form-label" id="a">页面内容</label>
									     <div class="layui-input-block" >
									   	 <textarea  id="articleEditor" name="des" style="width:1024px;height:500px;" >${appAbouXmyH5.des }</textarea>
									   </div>
								  	  </div> 
								  	   
									<div class="layui-form-item" >
									    <div class="layui-input-block">
									      <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
									      <button type="reset" class="layui-btn layui-btn-primary" id="a">重置</button>
									     
									    </div>
								  	</div>
			  			</form>
			  		</div>
				</div>
			</div>
			
			
		</div>

		<!-- 底部 -->
		<%@ include file="/commons/buttom.jsp"%>
		<script type="text/javascript" src="appAboutXmyH5/js/appAboutXmyH5.js"></script>
		<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	</div>
</body>
<!-- ueditor富文本 -->
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="article/articleUeditor.js"></script>
</html>