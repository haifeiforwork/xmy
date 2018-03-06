<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>配送管理</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
      <li class="layui-this">配送方式</li>
      <li class="">添加配送方式</li>
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
						<form class="layui-form my-form" action="mallSetting/deliveryMode/add" method="post">
					  				<div class="layui-form-item">
									    <label class="layui-form-label" >配送名称</label>
									    <div class="layui-input-block" style="">
									      <input type="text" name="name" lay-verify="name"  autocomplete="off" class="layui-input q-rule" zfj-query="name:cn">
									    </div>
								  	</div>
								  	<div class="layui-form-item" >
									    <label class="layui-form-label">权重</label>
									    <div class="layui-input-block">
									      <input type="text" name="weight" lay-verify="weight"  autocomplete="off" class="layui-input q-rule" zfj-query="weight:cn" placeholder="权重越小越靠前哦">
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
									    <label class="layui-form-label">规则</label>
									     <div class="layui-input-block">
									     <textarea id="des" name="des" style="display: none;">模板:0~2KG:10元;2~5KG:16元;5~10KG:35元</textarea>
									   </div>
								  	  </div> 
								  	   
									<div class="layui-form-item" >
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
		<script type="text/javascript" src="deliveryMode/js/deliveryMode.js"></script>
		
	</div>
</body>
</html>