<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>投诉建议管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
	.layui-table td{padding:2px 15px;font-size: 12px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief layui-form" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">投诉与建议</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
			<form id="myForm">
				<div class="layui-input-inline">
			    	<select name="pay_type" lay-filter="pay_type" class="q-rule" zfj-query="f.genre:eq">
			    	<option value="" selected="selected">类型</option>
			        <option value="0" <c:if test="${params['f.genre'] == 0}">selected="selected"</c:if>>投诉</option>
			        <option value="1" <c:if test="${params['f.genre'] == 1}">selected="selected"</c:if>>建议</option>
			      </select>
			    </div>
			    <div class="layui-input-inline">
			    	<select name="pay_type" lay-filter="pay_type" class="q-rule" zfj-query="f.type:eq">
			    	<option value="" selected="selected">来源</option>
			        <option value="1" <c:if test="${params['f.type'] == 1}">selected="selected"</c:if>>APP</option>
			        <option value="2" <c:if test="${params['f.type'] == 2}">selected="selected"</c:if>>PC</option>
			        <option value="3" <c:if test="${params['f.type'] == 3}">selected="selected"</c:if>>WAP</option>
			      </select>
			    </div>
			    <div class="layui-input-inline">
			    	 <input type="text" class="q-rule layui-input" zfj-query="u.name:cn" value="${params['u.name']}" placeholder="用户名称" name="name"/>
			    </div>
			    <div class="layui-input-inline">
			    	 <input type="text" class="q-rule layui-input" zfj-query="f.no:cn" value="${params['f.no']}" placeholder="订单编号" name="card_num"/>
			    </div>
			    <div class="layui-input-inline">
			    	 <input type="text" class="q-rule layui-input" zfj-query="f.phone_num:cn" value="${params['f.phoneNum']}" placeholder="用户名称" name="card_num"/>
			    </div>
			    <input type="submit" value="查询" class="layui-btn btn-submit"/>
			    <input type="reset" value="清空" id="clear_btn" class="layui-btn"/>
			</form>
		</div>
				<div id="mytable" class="layui-form"></div>
				<div id="mypager"></div>
				<!-- <div class="layui-form-item">
					<div class="layui-input-inline" style="width: 150px">
						<select name="deliveryMethod" id="sel">
						<option value="" selected="">批量操作</option>
						<option value="1">热卖商品</option>
						<option value="2">新品上市</option>
						<option value="3">热销商品</option>
						</select>
					</div>
					<div class="layui-input-inline">
					<button class="layui-btn" id="btnAdd" lay-submit="" lay-filter="demo2">添加</button>
					<button class="layui-btn" id="btnDel" lay-submit="" lay-filter="demo2">删除</button>
					</div>
				</div> -->
				<input type="text" value="${message}" id="message" style="display: none"/>
				<input type="text" id="pageCount" hidden="hidden" value="${countPage }"/>
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="feedback/js/feedback.js"></script>
</body>
</html>