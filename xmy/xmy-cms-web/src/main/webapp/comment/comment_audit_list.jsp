<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>评论审核</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief layui-form" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">评论审核</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
				<!-- <input type="text" class="q-rule" zfj-query="payment_name:cn" value="${param.payment_name}" name="payment_name"/>
				<input type="submit" value="提交"/> -->
		</div>
				<div id="mytable" class="layui-form">
				</div>
				<div class="layui-form-item">
					<div class="layui-input-inline" style="width: 150px">
						<select name="deliveryMethod" id="sel">
						<option value="" selected="">批量操作</option>
						<option value="0">批量审核通过</option>
						<option value="1">批量审核不通过</option>
						</select>
					</div>
						<div class="layui-input-inline">
						<button class="layui-btn" id="btnAubit" lay-submit="" lay-filter="demo2">执行</button>
						</div>
				</div>
				<div id="mypager"></div>
				<input type="text" value="${message}" id="message" style="display: none"/>
				<input type="text" id="pageCount" hidden="hidden"  value="${countPage }"/>
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="comment/js/comment_audit_list.js"></script>
</div>
</body>
</html>