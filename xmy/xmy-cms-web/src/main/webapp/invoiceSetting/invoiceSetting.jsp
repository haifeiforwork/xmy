<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>发票设置</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
      <li class="layui-this">发票设置</li>
    </ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show">
			  		<div  style="width:60%;">
						<form class="layui-form my-form" action="mallSetting/invoiceSetting/update" method="post">
					  			
								  	<input type="hidden" name="id" value="${terData.id }">
								  	<input type="hidden" name="status" value="${terData.status }">
								  	<input type="hidden" name="name" value="${terData.name }">
								  	<input type="hidden" name="vid" value="${terData.vid }">
								  	  <div class="layui-form-item">
									    <label class="layui-form-label" id="a">发票内容</label>
									     <div class="layui-input-block" >
									     <textarea  id="content" name="des" onfocus="myfocus()" style="display: none;">${terData.des }</textarea>
									    
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
		<script type="text/javascript" src="invoiceSetting/js/invoiceSetting.js"></script>
		<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
		</script>
		<script type="text/javascript">
		       
			     
			$(function(){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  
					  layer.tips('以--分隔每个类别', '#a',{tips:3});
					});       
				
			})
		</script>
	</div>
</body>
</html>