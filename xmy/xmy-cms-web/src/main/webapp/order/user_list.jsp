<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>客户清单</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
.layui-btn {margin-left: 70%}
.layui-input-inline{margin-left: 20%}
.text {text-align:right}
.ok { font-size: 16px}
li{margin-left: 40px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
   <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
 	 	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<i class="layui-icon" style="font-size: 20px; color: #009688;">客户清单</i><hr>
			<!--startprint-->
			<center><i class="layui-icon" style="font-size: 20px;">重庆香满圆 - 香满圆网上商城 客户清单(客户清单)</i></center>
			<br>
			<table border="1" style="width: 100%;">
				<tr>
					<td colspan="5">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲爱的${orderMap.order.paymentName}，非常感谢您本次使用香满圆旗下的香满圆网上商城~~我们衷心希望您此次购物旅程愉快！希望您能够再次光临香满圆~~ 您本次订单明细如下：
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">订单号：</td>
					<td>${orderMap.order.no}</td>
					<td style="text-align: right;">收货人：</td>
					<td>${orderMap.order.consigneeName}</td>
				</tr>
				<tr>
					<td style="text-align: right;">订购日期：</td>
					<td><fmt:formatDate value="${orderMap.order.payTime }" pattern="yyyy-MM-dd hh:mm:ss"/> </td>
					<td style="text-align: right;">收货人地址：</td>
					<td>${orderMap.order.consigneeAddress }</td>
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
						<c:forEach items="${orderMap.goods }" var="good">
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
							<td style="text-align: right;">￥${orderMap.order.total/100 }&nbsp;</td>
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
							<td style="text-align: right;">￥${orderMap.order.total/100 }&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: right;">已付款（元）：</td>
							<td style="text-align: right;">￥${orderMap.order.total/100 }&nbsp;</td>
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
				<tr>
					<td colspan="5" style="text-align: center;">
						<button onclick="preview()">打印</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button>返回</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
</div>
<script type="text/javascript">
	function preview()    
	{    
	   bdhtml=window.document.body.innerHTML;    
	   sprnstr="<!--startprint-->";    
	   eprnstr="<!--endprint-->";    
	   prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17);    
	   prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
	   window.document.body.innerHTML=prnhtml;    
	   window.print();    
	} 
</script>
</body>
</html>