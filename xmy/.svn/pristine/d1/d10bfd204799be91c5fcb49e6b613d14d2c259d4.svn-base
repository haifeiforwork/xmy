<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<title>按钮 - 在线演示 - layui</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<ul class="layui-tab-title site-title">
      <li class="layui-this">预览</li>
      <li class="">查看代码</li>
      <li>帮助</li>
    </ul>
  	<div class="layui-body layui-tab-content site site-body site-custom-body">
  		<div class="layui-tab-item layui-show">
	  		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
			  <legend>自定义文本 ： 上一页、下一页、首页、末页统统被替换</legend>
			</fieldset>
			<div id="demo3"></div>
		</div>
  		<div class="layui-tab-item">
	  		<div>查看代码页面。。。。。。。。。。。。。</div>
		</div>
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript">
	layui.use(["jquery","tool","laypage", "layer"],function(){
		var tool = layui.tool ;
		console.log(tool.getFullDate(new Date())) ;
		//
		var laypage = layui.laypage, layer = layui.layer ;
		laypage({
		    cont: 'demo3',
		    pages: 1000,
		    first: 1,
		    last: 100,
		    prev: '<em><</em>',
		    next: '<em>></em>',
		    jump:function(obj,first){
		    }
		});
	}) ;
  </script>
</div>
</body>
</html>