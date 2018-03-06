<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单扩展</title>
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
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">订单扩展</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
			<form id="myForm">
				<!-- <input type="text" class="q-rule" zfj-query="payment_name:cn" value="${params.payment_name}" name="payment_name"/>
				<input type="submit" value="提交"/> -->
				<div class="layui-input-inline">
			      <select name="ship_status" lay-filter="ship_status" class="q-rule" zfj-query="ship_status:ge" disabled>
			        <option value="" <c:if test="${params.shipStatus == 0}">selected="selected"</c:if>>订单状态</option>
			        <option value="1" <c:if test="${params.shipStatus == 1}">selected="selected"</c:if>>待审核</option>
			        <option value="2" <c:if test="${params.shipStatus == 2}">selected="selected"</c:if>>代付款</option>
			        <option value="3" <c:if test="${params.shipStatus == 3}">selected="selected"</c:if> selected="selected">供货确认</option>
			        <option value="4" <c:if test="${params.shipStatus == 4}">selected="selected"</c:if>>已供货</option>
			        <option value="5" <c:if test="${params.shipStatus == 5}">selected="selected"</c:if>>已备货</option>
			        <option value="6" <c:if test="${params.shipStatus == 6}">selected="selected"</c:if>>待发货</option>
			        <option value="7" <c:if test="${params.shipStatus == 7}">selected="selected"</c:if>>已发货</option>
			      </select>
			    </div>
			    <div class="layui-input-inline">
			    	<select name="pay_type" lay-filter="pay_type" class="q-rule" zfj-query="pay_type:eq">
			        <option value="" <c:if test="${params.payType ==  0}">selected="selected"</c:if>>支付方式</option>
			        <option value="1" <c:if test="${params.payType == 1}">selected="selected"</c:if>>支付宝</option>
			        <option value="2" <c:if test="${params.payType == 2}">selected="selected"</c:if>>微信</option>
			        <option value="3" <c:if test="${params.payType == 3}">selected="selected"</c:if>>银联</option>
			        <option value="4" <c:if test="${params.payType == 4}">selected="selected"</c:if>>购物卡</option>
			        <option value="5" <c:if test="${params.payType == 5}">selected="selected"</c:if>>货到付款</option>
			        <option value="6" <c:if test="${params.payType == 6}">selected="selected"</c:if>>混合支付</option>
			      </select>
			    </div>
			    <div class="layui-input-inline">
			    	 <input type="text" onclick="layui.laydate({elem: this})" zfj-query="update_time:ge" name="pay_time" value="${params.updateTime}" placeholder="供货时间" class="layui-input q-rule">
			    </div>
			   <%--  <div class="layui-input-inline">
			    	 <input type="text" onclick="layui.laydate({elem: this})" zfj-query="update_time:le" name="arrival_time" value="${params.updateTime}" placeholder="结束日期" class="layui-input q-rule">
			    </div> --%>
			    <div class="layui-input-inline">
			    	 <input type="text" class="q-rule layui-input" zfj-query="payment_name:cn" value="${params.paymentName}" placeholder="请输入用户账号" name="payment_name"/>
			    </div>
			    <div class="layui-input-inline">
			    	 <input type="text" class="q-rule layui-input" zfj-query="no:cn" value="${params.no}" placeholder="订单编号" name="no"/>
			    </div>
			    <input type="submit" value="查询" class="layui-btn btn-submit"/>
			    <input type="reset" value="清空" id="clear_btn" class="layui-btn"/>
			</form>
		</div>
				<div id="mytable" class="layui-form">
				</div>
				<div class="layui-form-item">
					<div class="layui-input-inline" style="width: 150px">
						<select name="deliveryMethod" id="sel">
						<option value="" selected="">批量操作</option>
						<option value="4">批量扩展供货时间</option>
						</select>
					</div>
						<div class="layui-input-inline">
						<button class="layui-btn" data-method="offset" id="btn1" lay-submit="" lay-filter="demo2">执行</button>
						</div>
				</div>
				<div id="mypager"></div>
				<input type="text" value="${message }" id="message" style="display: none"/>
				<input type="text" id="pageCount" hidden="hidden"  value="${countPage }"/>
	</div>
  </div>
  <!-- 弹出窗体，隐藏 -->
  <div id="supplyTime" style="display: none">
  		 <form class="layui-form" action="order/updateAllOrderDate" >
				<div class="layui-form-item" style="text-align: center;">
					 <br><br>
					 <input type="text" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" style="width: 60%;margin-left: 20%" name="updateTime" placeholder="供货时间确认" class="layui-input">
					<input type="text" id="or" style="display: none" name="ids"/>
					<br><br>
					<button  class="layui-btn" id="supply">确定</button>
				</div>
		</form>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="order/js/order_supply_list.js"></script>
</div>
</body>
</html>