<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>积分策略修改</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css" />
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form">
			<ul class="layui-tab-title site-title">
				<li class="layui-this">积分策略</li>
			</ul>

			<div
				class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">

				<div class="layui-tab-item layui-show  layui-form-item">
					<div style="width:50%;">
						<form class="layui-form" action="pointTactics/updateTactics"
							method="post">
							<input type="hidden" name="id" value="${pointTactics.id }">
							<div class="layui-form-item">
								<label class="layui-form-label">策略名称</label>
								<div class="layui-input-block">
									<input type="text" name="name" value="${pointTactics.name }"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">策略类型</label>
								<div class="layui-input-block">
									<input class="layui-input" type="hidden" name="type" value="${pointTactics.type}">
									<input class="layui-input" type="text" name="type1" value="${pointTactics.type==1?'签到':'购物'}" readonly="readonly">
								</div>
							</div>
							<div class="layui-form-item" style="${pointTactics.type==1?'display: none':'' }">
								<label class="layui-form-label">消费</label>
								<div class="layui-input-block">
									<input type="text" name="unit" value="${pointTactics.unit }"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">积分</label>
								<div class="layui-input-block">
									<input class="layui-input" name="value"
										value="${pointTactics.value }">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">是否启用</label>
								<div class="layui-input-block">
									<input type="radio" name="status" value="0" title="启用"
										<c:if test="${pointTactics.status==0}">checked</c:if>>
									<input type="radio" name="status" value="1" title="禁用"
										<c:if test="${pointTactics.status==1}">checked</c:if>>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
									<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="layui-tab-item">
					<div></div>
				</div>
				<div class="layui-tab-item">
					<div></div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<%@ include file="/commons/buttom.jsp"%>
	</div>
</body>
</html>