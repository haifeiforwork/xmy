<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="layui-tab-item layui-show ">
	<form id="myForm">
		<div class="layui-input-inline">
			<label class="layui-form-label">关键字：</label> <input type="text" class="layui-input q-rule" zfj-query="name:cn" value="${params.name}" name="name" style="width: 100px;"/>
		</div>
		<div class="layui-input-inline">
			<label class="layui-form-label">商品编号：</label> <input type="text" class="layui-input q-rule" zfj-query="code:cn" value="${params.code}" name="code" style="width: 150px;"/>
		</div>
		<div class="layui-input-inline">
			<label class="layui-form-label">分类：</label> <input type="text" class="layui-input q-rule" zfj-query="category_name:cn" value="${params.categoryName}" name="categoryName" style="width: 150px;"/>
		</div>
		<div class="layui-input-inline">
			<label class="layui-form-label">供货商：</label> <input type="text" class="layui-input q-rule" zfj-query="supplier_name:cn" value="${params.supplierName}" name="supplierName" style="width: 150px;"/>
		</div>
		<div class="layui-input-inline">
			<label class="layui-form-label">品牌：</label> <input type="text" class="layui-input q-rule" zfj-query="brand_name:cn" value="${params.brandName}" name="brandName" style="width: 150px;"/>
		</div>
		<input type="submit" class="layui-btn btn-submit" value="提交"  />
	</form> 
	<div id="mytable" class="layui-form" ></div>
	<div id="mypager"></div>
	<div>
		<div style="width:100%;background:#F2F2F2;height:40px;line-height:40px;padding-left:20px;>
			<p style="font-size:1.3em;">已选择商品</p>
		</div>
		<div id="selectCouponGoods">
			<table class="layui-table" >
				 <thead>
				   <tr>
				     <th>序号</th>
				     <th>商品编号</th>
				     <th>商品名称</th>
				     <th>二级分类</th>
				     <th>卖价（元）</th>
				     <th>供货商</th>
				     <th>品牌</th>
				     <th>操作</th>
				   </tr> 
				 </thead>
				  <tbody id="my-tbody">
				  		
				  </tbody>
			</table>
		</div>
	</div>
</div>

</body>
<script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">layui.config({base:"commons/layui/lay/modules/"}).use("global");</script>
<script type="text/javascript">
layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form() ;
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"coupon/chooseGoodList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
})


</script>

</html>