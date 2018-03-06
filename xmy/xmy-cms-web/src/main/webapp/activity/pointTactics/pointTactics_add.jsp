<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>积分策略添加</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css" />
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form">
			<ul class="layui-tab-title site-title">
				<a href="pointTactics/tacticsList"><li >积分策略列表</li></a>
				<li class="layui-this">添加积分策略</li>
			</ul>

			<div
				class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show  layui-form-item">
					<div style="width:50%;">
						<form class="layui-form" action="pointTactics/addTactics"
							method="post">
							<input type="hidden" name="id" value="">
							<div class="layui-form-item">
								<label class="layui-form-label">策略名称</label>
								<div class="layui-input-block">
									<input type="text" name="name" value="" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">策略类型</label>
								<div class="layui-input-block">
									<select id="type" lay-filter="type" name="type">
										<option value="1">签到</option>
										<option value="2">购物</option>
									</select>
								</div>
							</div>
							<div id="unit" class="layui-form-item" style="display: none;">
								<label class="layui-form-label">消费</label>
								<div class="layui-input-block">
									<input type="text" value="0" name="unit" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">积分</label>
								<div class="layui-input-block">
									<input class="layui-input" name="value" value="">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">是否启用</label>
								<div class="layui-input-block">
									<input type="radio" name="status" value="0" title="启用">
									<input type="radio" name="status" value="1" title="禁用">
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
<script type="text/javascript">
layui.use(["form","upload","tool","layer","tree","laydate"], function(){
	var form = layui.form(),layer = layui.layer,tool = layui.tool,$ = layui.jquery,laydate=layui.laydate;
	form.on("select(type)", function(data){
		var selectValue= $("select[name='type']").val();
		if(selectValue==1){
			$("#unit").hide();
		}
		if(selectValue==2){
			$("#unit").show();
		}
	})
});
	
</script>
</html>