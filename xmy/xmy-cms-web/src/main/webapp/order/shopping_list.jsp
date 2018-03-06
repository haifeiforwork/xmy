<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>全部订单</title>
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
  		<div class="layui-tab-item layui-show">
			<form id="myForm">
				<!-- <input type="text" class="q-rule" zfj-query="payment_name:cn" value="${params.payment_name}" name="payment_name"/>
				<input type="submit" value="提交"/> -->
			    <div class="layui-input-inline">
			    	 <input type="text" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" zfj-query="extend_time:ge" name="pay_time" value="${params.extendTimege}" placeholder="起始日期" class="layui-input q-rule">
			             </div>
			    <div class="layui-input-inline">
			    	 <input type="text" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" zfj-query="extend_time:le" name="pay_time" value="${params.extendTimele }" placeholder="起始日期" class="layui-input q-rule">
			    </div>
			    <input type="submit" value="查询" class="layui-btn btn-submit"/>
			    <input type="reset" value="清空" class="layui-btn"/>
			</form>
		</div>
				<div id="mytable"></div>
				<div id="mypager"></div>
				<input type="text" id="pageCount" hidden="hidden"  value="${countPage }"/>
				<div class="layui-form-item">
					<div class="layui-input-inline" style="width: 150px">
						<select name="deliveryMethod" id="sel">
						<option value="" selected="">全部商品</option>
						<option value="2">全部商品</option>
						<option value="1">农配中心</option>
						<option value="0">水果市场</option>
						</select>
					</div>
						<div class="layui-input-inline">
						<button class="layui-btn" id="btnExcel"  lay-submit="" lay-filter="demo2">导出Excel</button>
						</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-inline" style="width: 150px">
						<select name="deliveryMethod" id="lable">
						<option value="" selected="">标签类型</option>
						<option value="1">农配中心</option>
						<option value="0">水果市场</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<button class="layui-btn" id="btnLable" lay-submit="" lay-filter="demo2">导出标签</button>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-inline" style="width: 150px">
						<select name="deliveryMethod" id="order">
						<option value="" selected="">特殊订单类型</option>
						<option value="21">全国所有商品</option>
						<option value="01">全国水果商品</option>
						<option value="11">全国农配商品</option>
						<option value="22">主城所有商品</option>
						<option value="12">主城农配商品</option>
						<option value="02">主城水果商品</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<button class="layui-btn" id="btnOrder" lay-submit="" lay-filter="demo2">导出特殊订单</button>
					</div>
					<div class="layui-input-inline">
						<button class="layui-btn" id="btnWrite" lay-submit="" lay-filter="demo2">导出白标签</button>
					</div>
				</div>
				<input type="text" value="${orderIds }" id="orderid" style="display: none;"/>
	</div>
  </div>
  <!-- 弹出窗体，隐藏 -->
  <div id="supplyTime" style="display: none">
		<div class="layui-form-item" style="text-align: center;">
			 <br><br>
			 <input type="text" placeholder="文件名称 请用英文或数字" id="url" class="layui-input" style="width: 50%;margin-left: 100px;">
			<br><br>
			<button  class="layui-btn" id="supply">确定</button>
		</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
<script type="text/javascript" src="order/js/shopping_list.js"></script>
</div>
</body>
</html>