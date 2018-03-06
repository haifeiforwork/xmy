<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人账户</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
.layui-input-inline{margin-left: 20%}
.text {text-align:right}
.ok { font-size: 16px}
.layui-table td{padding:2px 15px;font-size: 12px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
   <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
 	 	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
 	 		<i class="layui-icon" style="font-size: 20px; color: #009688;">个人账户</i>
 	 		<hr>
		 	 <blockquote class="layui-elem-quote">
				${shoppingCard.cardNum }的面额为：${shoppingCard.totalValue }元,可用金额为：${shoppingCard.balance }元 购物卡密码：${shoppingCard.cardPassword }
			</blockquote>
			<!-- 订单商品信息 -->
			<fieldset class="layui-elem-field site-demo-button">
				<legend>账户信息&nbsp;&nbsp;</legend>
	  			<div style="width: 90%" >
	  				<table class="layui-table" lay-even="" lay-skin="row" style="margin-left: 70px">
					  <colgroup>
					    <col width="150">
					    <col width="150">
					    <col width="200">
					    <col>
					  </colgroup>
					  <thead>
					    <tr>
					      <th>卡号名称</th>
					      <th>卡号</th>
					      <th>金额</th>
					      <th>状态</th>
					      <th>类型</th>
					      <th>时间</th>
					      <th>备注</th>
					    </tr> 
					  </thead>
					  <tbody>
					 <c:forEach items="${cardRecord }" var="userSpend">
					  	<tr>
					  		<td>${shoppingCard.name }</td>
					  		<td>${shoppingCard.cardNum }</td>
					  		<td>${userSpend.value }</td>
					  		<td>有效</td>
					  		<td>
					  			<c:if test="${userSpend.type==1 }">消费</c:if>
					  			<c:if test="${userSpend.type==2 }">退还</c:if>
					  		</td>
					  		<td><fmt:formatDate value="${userSpend.useTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
					  		<td>${userSpend.remark }</td>
					  	</tr>
					  </c:forEach>
					  </tbody>
					</table>  
	  			</div>
  			</fieldset>
  			<fieldset class="layui-elem-field site-demo-button">
				<legend>账户操作&nbsp;&nbsp;</legend>
				<br><br><br>
				<form class="layui-form" action="order/updateCard">
					 <div class="layui-form-item">
					  <label class="layui-form-label">类型</label>
					  <div class="layui-input-block" style="width: 10%">
					    <select name="spendType" lay-verify="required" >
					      <option value="">消费</option>
					      <option value="1">存入</option>
					      <option value="2">消费</option>
					    </select>
					  </div>
					</div>
					<div class="layui-form-item">
					  <label class="layui-form-label">金额</label>
					  <div class="layui-input-block" style="width: 10%">
					    <input type="text" name="money" required  lay-verify="money" placeholder="单行输入" autocomplete="off" class="layui-input">
					  </div>
					</div>
					<div class="layui-form-item layui-form-text">
					  <label class="layui-form-label">备注</label>
					  <div class="layui-input-block" style="width: 18%">
					    <textarea name="remark" placeholder="多行输入" class="layui-textarea"></textarea>
					  </div>
					  <input type="hidden" value="${shoppingCard.id }" name="id" >
					</div>
					<div class="layui-form-item">
					  <div class="layui-input-block">
					    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
					    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					  </div>
					</div>
				</form>
			</fieldset>
  			</div>
  	</div>
	<!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="order/js/user_spend.js"></script>
</div>
</body>
</html>