<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>文章管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
/**下拉的显示优先级最高**/
.dropDown dl{
	z-index:9999;
}

</style>
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
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
      <li class="layui-this">文章列表</li>
      <li class="">添加文章</li>
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
								<form class="layui-form" action="content/article/add" method="post">
			  				<div class="layui-form-item">
							    <label class="layui-form-label">文章标题</label>
							    <div class="layui-input-block">
							      <input type="text" name="name" lay-verify="name"  autocomplete="off" class="layui-input q-rule" zfj-query="name:cn">
							    </div>
						  	</div>
						  	<div class="layui-form-item dropDown">
							    <label class="layui-form-label">所属栏目</label>
							    <div class="layui-input-block">
							    		<select name="parentId" lay-verify="parentId">
							    		<option value="">请选择栏目</option>
							    		<c:if test="${not empty pageBeanColumn.data}">
							    			<c:forEach items="${pageBeanColumn.data}" var="pageBeanColumn">
									     		
											  <option value="${pageBeanColumn.id}">${pageBeanColumn.name }</option>
											
											</c:forEach>
							    		</c:if>
									</select>
							    </div>
						  	</div>
			  				<div class="layui-form-item">
							    <label class="layui-form-label">权重</label>
							    <div class="layui-input-block">
							      <input type="text" name="weight" value="" lay-verify="weight" placeholder="权重越小越靠前哦" autocomplete="off" class="layui-input q-rule" zfj-query="weight:cn">
							    </div>
						  	</div>
						  	<div class="layui-form-item">
							    <label class="layui-form-label">状态</label>
							    <div class="layui-input-block">
							     <input type="radio" name="status" value="0" title="显示" checked>
      							 <input type="radio" name="status" value="1" title="关闭" >
							    </div>
						  	</div>
						  	 <div class="layui-form-item">
							    <label class="layui-form-label">文章内容</label>
							    <div class="layui-input-block">
							     <textarea  id="articleEditor" name="des" style="width:1024px;height:500px;" ></textarea>
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
		<script type="text/javascript" src="article/js/article.js"></script>
	</div>
</body>
<!-- ueditor富文本 -->
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="article/articleUeditor.js"></script>
</html>