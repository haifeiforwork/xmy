<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>APP版本管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
.layui-input-block{width: 200px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief layui-form" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">版本管理</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
  		    
			<form id="myForm" class="layui-form my-form" action="version/update" enctype="multipart/form-data" method="post" >
				<!-- <input type="text" class="q-rule" zfj-query="payment_name:cn" value="${params.payment_name}" name="payment_name"/>
				<input type="submit" value="提交"/> -->
				
				<%-- <div class="layui-input-inline">
			      <select name="type" lay-filter="type" class="q-rule" zfj-query="type:eq" >
			        <option value="" <c:if test="${params.type == 0}">selected="selected"</c:if>>设置类型</option>
			        <option value="1" <c:if test="${params.type == 1}">selected="selected"</c:if>>热卖商品</option>
			        <option value="2" <c:if test="${params.type == 2}">selected="selected"</c:if>>新品上市</option>
			        <option value="3" <c:if test="${params.type == 3}">selected="selected"</c:if>>热销商品</option>
			      </select>
			    </div> --%>
			    <!-- <input type="submit" value="查询" class="layui-btn btn-submit"/>
			    <input type="reset" value="清空" id="clear_btn" class="layui-btn"/> -->
			    <div class="layui-form-item " >
				    <label class="layui-form-label">Android APK上传:</label>
					    <div class="layui-input-block">
					      <input type="file" class="q-rule layui-input"  value="" placeholder="apk文件" name="file"/>
					    </div>
					<label class="layui-label layui-red" style="color: red" >${param.msg }</label>
			    </div>
			    
			    <div class="layui-form-item " >
				    <label class="layui-form-label">最新版本号:</label>
					    <div class="layui-input-block">
					      <input type="text" class="q-rule layui-input"  value="${version.lastVerCode}" placeholder="最新版本号" name="lastVerCode"/>
					    </div>
				</div>
				<div class="layui-form-item " >
				    <label class="layui-form-label">最新版本名称:</label>
					    <div class="layui-input-block">
					      <input type="text" class="q-rule layui-input"  value="${version.lastVerName}" placeholder="最新版本名称" name="lastVerName"/>
					    </div>
				</div>
				<div class="layui-form-item " >
				    <label class="layui-form-label">最低版本号:</label>
					    <div class="layui-input-block">
					      <input type="text" class="q-rule layui-input"  value="${version.lowestVerCode}" placeholder="最低版本号" name="lowestVerCode"/>
					    </div>
				</div>
				<div class="layui-form-item " >
				    <label class="layui-form-label">版本更新描述:</label>
					    <div class="layui-input-block">
					      <input type="text" class="q-rule layui-input"  value="${version.description}" placeholder="描述" name="description"/>
					    </div>
				</div>
				<div class="layui-form-item " >
				    <label class="layui-form-label">Apk下载地址:</label>
					    <div class="layui-input-block">
					      <input type="text" class="q-rule layui-input"  value="${version.androidUrl}" placeholder="Apk下载地址" name="androidUrl"/>
					    </div>
				</div>
				<div class="layui-form-item " >
				    <label class="layui-form-label">ios商店地址:</label>
					    <div class="layui-input-block">
					      <input type="text" class="q-rule layui-input"  value="${version.iosUrl}" placeholder="ios app商店地址" name="iosUrl"/>
					    </div>
				</div>
				<div class="layui-form-item " >
				    <label class="layui-form-label">ios最新版本号:</label>
					    <div class="layui-input-block">
					      <input type="text" class="q-rule layui-input"  value="${version.iosLastVerCode}" placeholder="ios 最新版本号" name="iosLastVerCode"/>
					    </div>
				</div>
				<div class="layui-form-item layui-input-block" >
					<input type="submit" value="提交" class="layui-btn btn-submit" />     
				</div>
			</form>
		</div>			
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="push/js/push_list.js"></script>
</body>
</html>