<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎登录页面</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
.layui-input-block{width: 200px}
.tableDiv{float: left;margin-left: 20px}
.title{background-color: #c2c2c2;color: white;}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  		 <blockquote class="layui-elem-quote">
				目前有123个用户 和 321 位游客在线。系统时间为：2017-07-11 16:23:23
		 </blockquote>
		 <div style="width: 20%" class="tableDiv">
			 <table class="layui-table" lay-skin="line" style="text-align: center;">
			  <thead>
			    <tr>
			      <td class="title">待处理事物</td>
			    </tr> 
			  </thead>
			  <tbody>
			    <tr>
			      <td>超期未确认的商品：0个</td>
			    </tr>
			    <tr>
			      <td>退货申请：${map.returnOrderCount }个</td>
			    </tr>
			    <tr>
			      <td>咨询投诉：0个</td>
			    </tr>
			    <tr>
			      <td>库存预警：${map.alarmCount }个</td>
			    </tr>
			    <tr>
			      <td>发货未确认的商品：0个</td>
			    </tr>
			  </tbody>
			</table>
		</div> 
		<div style="width: 20%" class="tableDiv">
			 <table class="layui-table" lay-skin="line" style="text-align: center;">
			  <thead>
			    <tr>
			      <td class="title">商城信息</td>
			    </tr> 
			  </thead>
			  <tbody>
			    <tr>
			      <td>上架商品：${map.putway }个</td>
			    </tr>
			    <tr>
			      <td>下架商品：${map.noPutway }个</td>
			    </tr>
			  </tbody>
			</table>
		</div>
		<div style="width: 20%" class="tableDiv">
			 <table class="layui-table" lay-skin="line" style="text-align: center;">
			  <thead>
			    <tr>
			      <td class="title">每日快报</td>
			    </tr> 
			  </thead>
			  <tbody>
			    <tr>
			      <td>昨日订单：${map.tomarrOrder }个</td>
			    </tr>
			    <tr>
			      <td>今日订单：${map.todayOrder }个</td>
			    </tr>
			  </tbody>
			</table>
		</div>  
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
</div>
</body>
</html>