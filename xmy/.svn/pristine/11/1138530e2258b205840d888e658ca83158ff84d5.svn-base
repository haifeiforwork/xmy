<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>按钮 - 在线演示 - layui</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<ul class="layui-tab-title site-title">
      <li class="layui-this">表格</li>
      <li class="">按钮提示</li>
      <li>表单元素</li>
      <li>树形</li>
      <li>上传</li>
    </ul>
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  		<div class="layui-tab-item layui-show">
	  		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
			  <legend>自定义文本 ： 上一页、下一页、首页、末页统统被替换</legend>
			</fieldset>
			<form id="myForm">
			<input type="text" class="q-rule" zfj-query="username:cn" value="${param.username}" name="username"/>
			<input type="submit" value="提交"/>
			</form>
			
			<div id="mytable">
				
			</div>
			<div id="mypager"></div>
		</div>
  		
  		<div class="layui-tab-item">
	  		<div>
				<button class="layui-btn layui-btn-primary" id="btn1">普通</button>
				<button class="layui-btn layui-btn-normal" id="btn2">带有操作的普通</button>
				<button class="layui-btn layui-btn-warm" id="btn3">警告</button>
				<button class="layui-btn layui-btn-danger" id="btn4">错误</button>
				<button class="layui-btn" id="btn5">询问</button>
				<button class="layui-btn" id="btn6">加载</button>
	  		</div>
		</div>
  		
  		<div class="layui-tab-item">
	  		<div style="width:50%;">
	  			<form class="layui-form">
	  				<div class="layui-form-item">
					    <label class="layui-form-label">输入框</label>
					    <div class="layui-input-block">
					      <input type="text" name="username" value="${param.username}" lay-verify="username" placeholder="请输入" autocomplete="off" class="layui-input q-rule" zfj-query="username:cn">
					    </div>
				  	</div>
	  				<div class="layui-form-item">
					    <label class="layui-form-label">请输入email</label>
					    <div class="layui-input-block">
					      <input type="text" name="email" value="" lay-verify="email" placeholder="请输入email" autocomplete="on" class="layui-input q-rule" zfj-query="email:cn">
					    </div>
				  	</div>
				  	<div class="layui-form-item">
					    <label class="layui-form-label">下拉选择框${param.interest }</label>
					    <div class="layui-input-block">
					      <select name="interest" lay-filter="inter" class="q-rule" zfj-query="username:cn">
					      	<option value="0" <c:if test="${param.interest == 0}">selected="selected"</c:if>>写作</option>
					      	<option value="1" <c:if test="${param.interest == 1}">selected="selected"</c:if>>阅读</option>
					      </select>
					    </div>
				  	</div>
				  	<div class="layui-form-item">
					    <label class="layui-form-label"></label>
					    <div class="layui-input-block">
					      <input type="file" name="file" id="test" class="layui-upload-file"> 
					    </div>
					    <img id="u-img" src=""/>
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
		
		<div class="layui-tab-item">
	  		<div style="width:20%">
	  		ddd
	  			<ul id="treeDemo"></ul>
	  		</div>
	  		<div style="width:80%;">
	  			sssss
	  		</div>
		</div>
		<div class="layui-tab-item">
	  		<form name=theform>
			<input type="radio" name="myradio" value="local_name" checked=true/> 上传文件名字保持本地文件名字
			<input type="radio" name="myradio" value="random_name" /> 上传文件名字是随机文件名
			<br/>
			上传到指定目录:<input type="text" id='dirname' placeholder="如果不填，默认是上传到根目录" size=50>
			</form>
			
			<h4>您所选择的文件列表：</h4>
			<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
			
			<br/>
			
			<div id="container">
				<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
				<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
			</div>
			
			<pre id="console"></pre>
			
			<p>&nbsp;</p>
			<form name=theform>
			<input type="radio" name="myradio" value="local_name" checked=true/> 上传文件名字保持本地文件名字
			<input type="radio" name="myradio" value="random_name" /> 上传文件名字是随机文件名
			</form>
			
			<h4>您所选择的文件列表：</h4>
			<div id="ossfile">上传2</div>
			
			<br/>
			
			<div id="container2">
				<a id="selectfiles2" href="javascript:void(0);" class='btn'>选择文件</a>
				<a id="postfiles2" href="javascript:void(0);" class='btn'>开始上传</a>
			</div>
			
			<pre id="console2"></pre>
			
			<p>&nbsp;</p>
		</div>
  	</div>
  </div>
 
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
  <script type="text/javascript" src="native/js/native.js"></script>
</div>
</body>
</html>