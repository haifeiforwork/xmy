<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页商品设置</title>
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
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">全部订单</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
			<form id="myForm">
				<!-- <input type="text" class="q-rule" zfj-query="payment_name:cn" value="${params.payment_name}" name="payment_name"/>
				<input type="submit" value="提交"/> -->
				<div class="layui-input-inline">
			      <select name="type" lay-filter="type" class="q-rule" zfj-query="type:eq" >
			        <option value="" <c:if test="${params.type == 0}">selected="selected"</c:if>>设置类型</option>
			        <option value="1" <c:if test="${params.type == 1}">selected="selected"</c:if>>热卖商品</option>
			        <option value="2" <c:if test="${params.type == 2}">selected="selected"</c:if>>新品上市</option>
			        <option value="3" <c:if test="${params.type == 3}">selected="selected"</c:if>>热销商品</option>
			      </select>
			    </div>
			    <input type="submit" value="查询" class="layui-btn btn-submit"/>
			    <input type="reset" value="清空" id="clear_btn" class="layui-btn"/>
			</form>
		</div>
				<div id="mytable" class="layui-form"></div>
				<div id="mypager"></div>
				<div class="layui-form-item">
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
				</div>
				<input type="text" value="${message}" id="message" style="display: none"/>
				<input type="text" id="pageCount" hidden="hidden" value="${countPage }"/>
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="pcgoodset/js/pc_goods_list.js"></script>
</body>
</html>