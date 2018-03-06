<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>支付配置管理</title>
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
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">支付配置管理</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
			<form id="myForm">
			</form>
		</div>
				<div id="mytable" class="layui-form">
				<table class="layui-table" lay-skin="line">
					 <thead>
					   <tr>
					  	 <!-- <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> -->
					     
					     <th>id</th>
					     <th>渠道编码</th>
					     <th>渠道名称</th>
					     <th>状态</th>
					     <th>操作</th>
					     <!-- <th>字段描述</th>
					     
					     <th>支付渠道</th>
					     <th>创建时间</th>
					     <th>商户类型</th>
					     <th>值</th> -->
					   </tr> 
					 </thead>
					 <tbody>
					 	<c:forEach items="${channels }" var="channel">
						   <tr>
						    <%--  <th><input type="checkbox" name="" value="${pcGoods.id }" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> --%>
						     <td>${channel.id }</td>
						     <td>${channel.ccode }</td>
						     <td>${channel.cname }</td>
						     <%-- <td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss"  value = "${channel.createdTime}" /></td> --%>
						     <td>
						         <c:if test="${channel.status eq 0}">启用</c:if>
						         <c:if test="${channel.status eq 1}">停用</c:if>
						     </td>
						     <td>
						         <button  value="${channel.ccode }"  class="layui-btn channels-modify">修改</button>
						     </td>
						     <%-- <td>${channel.val }</td> --%>
						   </tr>
					   </c:forEach>
					 </tbody>
					</table>
					</div>
				
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="payconfig/js/payconfig_list.js"></script>
</body>
</html>