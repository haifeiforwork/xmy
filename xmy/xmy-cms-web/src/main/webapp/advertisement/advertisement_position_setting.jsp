<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>广告位置设置</title>
	<%@ include file="/commons/comm_css.jsp"%>
	<link rel="stylesheet" href="native/css/style.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
 	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
		<fieldset class="layui-elem-field">
		  <div class="layui-field-box">
		  	<form class="layui-form" id="form"  action="advertisement/addAdvertisementPosition" method="post" id="form">
		  		<div class="layui-form-item">
				    <label class="layui-form-label">广告位置名称<i style="color:#F00">*</i></label>
				    <div class="layui-input-block" style="width: 250px;">
				      <input type="text" name="name"  id="name" lay-verify="name" value="${param.name }" autocomplete="off"  class="layui-input" >
				    </div>
				</div>
		  		
		  		<div class="layui-form-item">
				    <label class="layui-form-label">该位置广告图片数量<i style="color:#F00">*</i></label>
				    <div class="layui-input-block" style="width: 250px;">
				      <input type="text" name="imgNum"  id="imgNum" lay-verify="number" value="${param.imgNum }" autocomplete="off"  class="layui-input" >
				    </div>
				</div>
		  		
		  		<input type="hidden" name="showWays" id="showWays"  value="1">
		  		
		  		<div class="layui-form-item"  >
		  			<label class="layui-form-label">选择展示方式<i style="color:#F00">*</i></label>
		  			 <div class="layui-input-inline choose " data-ways="1" style="border:1px solid #66CD00; width: 200px;">
		  			 	<img alt="" src="advertisement/image/one.png">
		  			 </div>
		  			 <div class="layui-input-inline choose" data-ways="2" style="border:1px solid #ADADAD; width: 200px;">
		  			 	<img alt="" src="advertisement/image/left_top_down.png">
		  			 </div>
		  			 <div class="layui-input-inline choose" data-ways="3" style="border:1px solid #ADADAD; width: 200px;">
		  			 	<img alt="" src="advertisement/image/top_down_right.png">
		  			 </div>
		  			 <div class="layui-input-inline choose" data-ways="4" style="border:1px solid #ADADAD; width: 200px;">
		  			 	<img alt="" src="advertisement/image/top_down.png">
		  			 </div>
		  			 <div class="layui-input-inline choose" data-ways="5" style="border:1px solid #ADADAD; width: 200px;">
		  			 	<img alt="" src="advertisement/image/three.png">
		  			 </div>
				</div>
		  	</form>
		  </div>
		</fieldset>
		<div class="layui-input-inline">
			<input type="button"  class="layui-btn"  lay-submit="" lay-filter="forminfo" value="确认提交" />
		</div>
 	</div>
 </div>
 <!-- 底部 -->
<%@ include file="/commons/buttom.jsp"%>
</div>
</body>
<script type="text/javascript">
layui.use(["form","tool","layer"], function(){
	 var form = layui.form(),layer = layui.layer,tool = layui.tool ,$ = layui.jquery ;
	 form.verify({  //表单校验
			name:function(value,item){
				if(!value){
					return "广告位置名称不能为空" ;
				}
			}
		}) ;
	
	$(".choose").click(function(){
		$(".choose").css("border","1px solid #ADADAD");
		$(this).css("border","1px solid #66CD00")
		$("#showWays").val($(this).data("ways"));
	})
	
	//表单提交
	form.on('submit(forminfo)', function(data){
		 $("#form").submit();
	});
})

</script>
</html>