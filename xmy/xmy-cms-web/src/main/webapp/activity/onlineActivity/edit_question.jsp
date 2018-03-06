<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加问题</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
	<form class="layui-form" action="onlineActivity/addQuestion" method="post" id="activityForm">
	<fieldset class="layui-elem-field">
 		<legend>问题基本信息</legend>
			<div class="layui-form-item">
			    <label class="layui-form-label">问题标题：<i style="color:#F00">*</i></label>
			    <div class="layui-input-block">
			      <input type="text" id="name" name="activityQuestion" lay-verify="activityName" value="${data.activityQuestion }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<input type="hidden" id="actId" name="id" value="${data.id }">
			<input type="hidden" id="activityId" name="activityId" value="${data.activityId }">
			<div class="layui-form-item">
			    <label class="layui-form-label">答案A:</label>
			    <div class="layui-input-block">
			      <input type="text" name="answerA"  lay-verify="limitNum" value="${data.answerA }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">答案B:</label>
			    <div class="layui-input-block">
			      <input type="text" name="answerB"  lay-verify="limitNum" value="${data.answerB }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">答案C:</label>
			    <div class="layui-input-block">
			      <input type="text" name="answerC"  lay-verify="userLimitNum" value="${data.answerC }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">答案D:</label>
			    <div class="layui-input-block">
			      <input type="text" name="answerD"  lay-verify="limitNum" value="${data.answerD }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">正确答案:</label>
			    <div class="layui-input-block">
			      <input type="text" name="trueAnswer"  lay-verify="limitNum" value="${data.trueAnswer }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
	</fieldset>
	</form>
	<input type="submit" id="save" lay-submit=""  class="layui-btn" value="保存"  />
 	</div>
 </div>
 <%@ include file="/commons/buttom.jsp"%>
</div>			
</body>
<script type="text/javascript">
 layui.use(["form","upload","tool","layer","tree","laydate"], function(){
	var form = layui.form();
	//自定义验证规则
	  form.verify({
		 username: function(value){
	      if(value.length < 1){
	        return '收货人名称不能为空';
	      }else if(value.length > 20){
	    	  return '收货人名称过长';
	      }
	    }
	    ,content: function(value){
	      layedit.sync(editIndex);
	    },deliveryId:function(value){
	    	if(value.trim() == ""){
	    		return '配送人不能为空';
	    	}
	    },kdniaoExpCode:function(value){
	    	if(value.trim() == ""){
	    		return '快递公司不能为空';
	    	}
	    },logisticsNo:function(value){
	    	if(value.trim() == ""){
	    		return '快递单号不能为空!';
	    	}
	    }
	    
	  });
	$(function($){
		$("#save").click(function(){
			$("#activityForm").submit();
		})
	})
});
//保存商品 


</script>
</html>