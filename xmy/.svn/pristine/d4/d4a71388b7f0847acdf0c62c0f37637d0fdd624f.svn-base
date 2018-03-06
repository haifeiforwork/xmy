<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>配送方式编辑</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form"
			lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
			      <li >配送方式列表</li>
			      <li class="layui-this">配送方式编辑</li>
			    </ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item">
					<div id="mytable" class="layui-form"></div>
					<div id="mypager"></div>
					<input type="hidden" id="pageCount" value="${pageCount }" />
				</div>
				
			
				
				<div class="layui-tab-item  layui-show">
			  		<div  style="width:40%;">
								<form class="layui-form" action="mallSetting/deliveryMode/update" method="post">
									
								  	
					  				<div class="layui-form-item">
									    <label class="layui-form-label" >配送名称</label><input type="hidden" name="id" value="${termData.id }">
									    <div class="layui-input-block" style="">
									      <input type="text" name="name" lay-verify="name" value="${termData.name }" autocomplete="off" class="layui-input q-rule" zfj-query="name:cn">
									    </div>
								  	</div>
								  	<div class="layui-form-item" >
									    <label class="layui-form-label">权重</label>
									    <div class="layui-input-block">
									      <input type="text" name="weight" lay-verify="weight"  value="${termData.weight }" autocomplete="off" class="layui-input q-rule" zfj-query="weight:cn" placeholder="权重越小越靠前哦">
									    </div>
								  	</div>
							  		  <div class="layui-form-item">
										    <label class="layui-form-label">状态</label>
										    <div class="layui-input-block">
										    	 <input type="radio" name="status" value="0" title="显示"  <c:if test="${termData.status==0}">checked</c:if> >
										    	  <input type="radio" name="status" value="1" title="关闭"  <c:if test="${termData.status==1}">checked</c:if> >
										    </div>
									  </div>
								  	  <div class="layui-form-item">
									    <label class="layui-form-label">规则</label>
									     <div class="layui-input-block">
									     <textarea id="des" name="des" style="display: none;">${termData.des }</textarea>
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
		<script type="text/javascript" src="deliveryMode/js/deliveryMode.js"></script>
		
	</div>
</body>
</html>