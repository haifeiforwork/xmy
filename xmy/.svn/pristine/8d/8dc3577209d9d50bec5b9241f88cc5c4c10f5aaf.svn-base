<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>专题页面管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
.status_error{
display:inline-block;
width:100px;
height: 30px;
background: orange;
text-align: center;
}
.status_ok{
display:inline-block;
width:100px;
height: 30px;
background: #98FB98;
text-align: center;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
			       <li class="layui-this">专题活动列表</li>
			      <li class=""><a href="topicPage/addTopicPage">添加专题</a></li>
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
				
			
				
				
			</div>
			
		
		</div>

		<!-- 底部 -->
		<%@ include file="/commons/buttom.jsp"%>
		<script type="text/javascript" src="topicPage/js/topicPage.js"></script>
		
	</div>
</body>
</html>