<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="layui-table">
	<thead>
		<tr>
			
			<th>ID</th>
			<th>策略名称</th>
			<th>类型</th>
			<th>消费</th>
			<th>积分</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${tacticsList}" var="tactics">
			<tr>
				<td>${tactics.id}</td>
				<td>${tactics.name}</td>
				<%--  <td><img alt="" src="${item.imgPath}"></td> --%>
				<%-- <td>${item.code}</td> --%>
				<td id="type"><c:if test="${tactics.type==1}"> 签到</c:if> <c:if
						test="${tactics.type==2}"> 购物</c:if></td>
				<td id="type"><c:if test="${tactics.type==1}">
					</c:if> <c:if test="${tactics.type==2}">${tactics.unit}元</c:if></td>
				<td>${tactics.value}分</td>
				<td>
					<div class="site-demo-button ">
						<a href="pointTactics/editPoint/${tactics.id}">
							<button class="layui-btn">编辑</button>
						</a>
						<button id="opr" class="layui-btn" data-id="${tactics.id }"
							data-status="${tactics.status }">${tactics.status=='0'? '禁用':'启用' }</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
	layui
			.use(
					[ "pager", "form", "upload" ],
					function() {
						var tool = layui.tool, $ = layui.jquery, pager = layui.pager, form = layui
								.form();
						var idStr = "";
						var val = "0";
						$("table tr td #opr").click(
								function() {
									var id = $(this).data("id");
									var status = $(this).data("status");
									var txt = "", ts = $(this);
									if (status == 0) {
										status = 1, txt = "启用"
									} else
										status = 0, txt = "禁用"
									tool.load();
									$.ajax({
										url : "pointTactics/updateStatus?id="
												+ id + "&status=" + status,
										type : "POST",
										dataType : "JSON",
										async : false,
										success : function(data) {
											if (data.status >= 1) {
												ts.data("status", status);
												ts.text(txt)
												tool.unload();
											}
										}
									})
								});
						form.on('select(batch)', function(data) { //监听选中状态
							val = data.value;
						});

					})
</script>
