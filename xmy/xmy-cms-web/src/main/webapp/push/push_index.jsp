<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- iframe 内层，需要自带样式 -->
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
.layui-input-block{width: 80% }
</style>

<form id="pushForm" class="layui-form" action="push/push" >
      <div class="layui-form-item">
	    <label class="layui-form-label" >推送方式</label>
	    <div class="layui-input-block" id="radio-type" >
	      <input type="radio" name="type" value="0" title="立即推送">
	      <input type="radio" name="type" value="1" title="定时推送" checked>
	    </div>
	  </div>
    <div class="layui-form-item" id="push-time-item">
	    <label class="layui-form-label">推送时间</label>
		<div class="layui-input-block">
			<input class="layui-input" id="time"  name="time" placeholder="请选择通知的推送时间" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">推送内容</label>
		<div class="layui-input-block">
			<textarea rows="3" cols="10" class="layui-textarea"  name="content" ></textarea>
		</div>	
	</div>
	<div class="layui-form-item">
	    <label class="layui-form-label">推送目标</label>
	    <div class="layui-input-block" id="radio-target" >
	      <input type="radio" name="target" value="0" title="全局推送"  >
	      <input type="radio" name="target" value="1" title="按标签推送" checked>
	    </div>
	 </div>
    <div class="layui-form-item" id="push-label-item" >
	    <label class="layui-form-label">推送标签</label>
	    <div class="layui-input-block">
	      <c:forEach items="${labels }" var="label">
	         <input type="checkbox" name="labelids" value="${label.id }" title="${label.label }">
		   </c:forEach>
	    </div>
    </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button type="button" class="layui-btn layui-btn-warm layui-form-btn "  id="push-broacast"　>推送</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
</form>
<!-- 底部 JS-->
<%@ include file="/commons/comm_footer_js.jsp"%>
 <script type="text/javascript" src="push/js/push_index.js"></script>
