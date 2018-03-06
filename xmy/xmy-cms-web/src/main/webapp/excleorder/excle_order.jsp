<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>导出销售总额</title>
<%@ include file="/commons/comm_css.jsp"%>
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
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">导出销售总额</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
			<form action="order/excleOrder" class="layui-form"> 
			    <div class="layui-input-inline">
			    	 <input type="text" onclick="layui.laydate({elem: this})" lay-verify="bginTime" zfj-query="pay_time:ge" name="bginTime" placeholder="订单备货时间（起始日期）" class="layui-input q-rule">
			    </div>
			    <div class="layui-input-inline">
			    	 <input type="text" onclick="layui.laydate({elem: this})" lay-verify="endTime" zfj-query="arrival_time:le" name="endTime" placeholder="订单备货时间（结束日期）" class="layui-input q-rule">
			    </div>
			    <input type="submit" value="导出" class="layui-btn"/>
			</form>
		</div>
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="excleorder/js/excle_order.js"></script>
</div>
</body>
</html>