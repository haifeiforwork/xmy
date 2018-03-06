<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单批量打印</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
  body {
 margin: 0;
 padding: 0;
 background-color: #FAFAFA;
 font: 12pt "Tahoma";
}
* {
 box-sizing: border-box;
 -moz-box-sizing: border-box;
}
.page {
 width: 21cm;
 min-height: 29.7cm;
 padding: 2cm;
 margin: 1cm auto;
 border: 1px #D3D3D3 solid;
 border-radius: 5px;
 background: white;
 box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
@page {
 size: A4;
 margin: 0;
}
@media print {
 .page {
  margin: 0;
  border: initial;
  border-radius: initial;
  width: initial;
  min-height: initial;
  box-shadow: initial;
  background: initial;
  page-break-after: always;
 }
}

</style>
  </head>
  
  <body>
    <div class="book">
    <c:forEach items="${allOrder }" var="order">	
	 <div class="page">
	  	<div class="subpage">
	  		<table border="1" style="width: 100%;">
				<tr>
					<td style="text-align: right;">订单号：</td>
					<td>${order.no}</td>
					<td style="text-align: right;">收货人：</td>
					<td>${order.consigneeName}</td>
				</tr>
				<tr>
					<td style="text-align: right;">订购日期：</td>
					<td><fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd hh:mm:ss"/> </td>
					<td style="text-align: right;">收货人地址：</td>
					<td>${order.consigneeAddress }</td>
				</tr>
				<tr>
					<td style="text-align: right;">发货单号：</td>
					<td></td>
					<td style="text-align: right;">收货人邮编：</td>
					<td></td>
				</tr>
				<tr>
					<table style="width: 100%" border="1">
						<tr style="text-align: center;background-color: #e2e2e2;">
							<th>序号</th>
							<th>商品名称</th>
							<th>数量</th>
							<th>售价（元）</th>
							<th>金额（元）</th>
						</tr>
						<c:forEach items="${order.orderGoods }" var="good">
							<tr>
								<td style="text-align: center;">${good.id }</td>
								<td>[${good.typeName }]${good.name }</td>
								<td style="text-align: right;">${good.num}</td>
								<td style="text-align: right;">￥${good.price }&nbsp;</td>
								<td style="text-align: right;">￥${good.sumPrice }&nbsp;</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4" style="text-align: right;">商品金额合计（元）：</td>
							<td style="text-align: right;">￥${order.total/100 }&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: right;">附加费（已保价+运费）：</td>
							<td style="text-align: right;">￥7.00&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: right;">满88免运费（元）：</td>
							<td style="text-align: right;">￥-7.00&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: right;">总计（元）：</td>
							<td style="text-align: right;">￥${order.total/100 }&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: right;">已付款（元）：</td>
							<td style="text-align: right;">￥${order.total/100 }&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: right;">应付款（元）：</td>
							<td style="text-align: right;">￥0.00&nbsp;</td>
						</tr>
				</tr>
				<tr>
					<td colspan="5">
						<P>如果您收到物品后发现任何问题或有任何疑问，您可以：</P>
						<P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.您可以拨打客服电话023-63668686,023-63668687</P>
						<P>感谢您的惠顾，我们期待能够再次为您服务</P>
					</td>
				</tr>
				<!--endprint-->
			</table>
	  	</div> 
	 </div>
	</c:forEach>
</div>
  </body>
</html>
