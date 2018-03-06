<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>优惠卡明细管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
.layui-table td{padding:2px 15px;font-size: 12px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
   <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
 	 	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
 	 		<i class="layui-icon" style="font-size: 20px; color: #009688;">购物卡管理</i>
 	 		<hr>
			<!-- 订单商品信息 -->
			<fieldset class="layui-elem-field site-demo-button">
				<legend>购物卡信息&nbsp;&nbsp;</legend>
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
					      <th>面额</th>
					      <th>可用金额</th>
					      <th>状态</th>
					      <th>使用时间</th>
					      <th>操作</th>
					    </tr> 
					  </thead>
					  <tbody>
					  	<c:forEach items="${cardList }" var="card">
							  <tr>
						  		<td>${card.name }</td>
						  		<td>${card.cardNum }</td>
						  		<td><fmt:formatNumber type="number" value="${card.totalValue }" pattern="0.00"/></td>
						  		<td><fmt:formatNumber type="number" value="${card.balance }" pattern="0.00"/></td>
						  		<td>
						  			<c:if test="${card.status==1 }">未激活</c:if>
						  			<c:if test="${card.status==2 || card.status==3 }">已激活</c:if>
						  		</td>
						  		<td><fmt:formatDate value="${card.activeTime }" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
						  		<td><a href="order/cardSpend/${card.id }"><button class="layui-btn layui-btn-small layui-btn-normal">操作</button></a></td>
						  	 </tr>
					  	 </c:forEach>
					  </tbody>
					</table>  
	  			</div>
  			</fieldset>
  			<%-- <fieldset class="layui-elem-field site-demo-button">
				<legend>账户操作&nbsp;&nbsp;</legend>
				<br><br><br>
				<form class="layui-form" action="order/updateSpend">
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
					    <input type="text" name="moneyPoint" required  lay-verify="required" placeholder="单行输入" autocomplete="off" class="layui-input">
					  </div>
					</div>
					<div class="layui-form-item layui-form-text">
					  <label class="layui-form-label">备注</label>
					  <div class="layui-input-block" style="width: 18%">
					    <textarea name="remarks" placeholder="多行输入" class="layui-textarea"></textarea>
					  </div>
					  <input type="hidden" value="${user.id }" name="userId" >
					</div>
					<div class="layui-form-item">
					  <div class="layui-input-block">
					    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
					    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					  </div>
					</div>
				</form>
			</fieldset> --%>
  			</div>
  	</div>
	<!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
</div>
</body>
</html>