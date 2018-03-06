<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/comm_css.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类规格</title>
</head>
<body>
<div class="layui-form" >
	<form class="layui-form" action="category/addSpec" method="post" >
		<div class="layui-form-item"  >
		    <div class="layui-inline">
		      <label class="layui-form-label">规格名称</label>
		      <div class="layui-input-inline">
		        <input type="text" name="wordSeg" lay-verify="required"  id="name"  lay-filter="name" value="${data.wordSeg }"   autocomplete="off" class="layui-input">
		      </div>
		    </div>
		   
		    <input type="hidden" name="cid" value="${cid }">
		    <input type="hidden" name="id" value="${data.id }">
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">排序</label>
		      <div class="layui-input-inline">
		        <input type="text" name="seq" lay-verify="number" lay-filter="seq" value="${data.seq }"   autocomplete="off" class="layui-input">
		      </div>
	    	</div>
 		 </div>
 		 <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">项目可选值</label>
			    <div class="layui-input-block">
			      <textarea placeholder="注意:用英文逗号隔开" name="val" class="layui-textarea">${data.val }</textarea>
			    </div>
		</div>
 		 <div class="layui-form-item">
		    <label class="layui-form-label">是否搜索</label>
		    <div class="layui-input-block">
		      <input type="checkbox"  name="isSearch" id="isSearch" lay-skin="switch" value="${not empty data.isSearch? data.isSearch:1 }" ${data.isSearch == 0? 'checked':'' } lay-filter="isSearch" lay-text="ON|OFF">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">是否显示</label>
		    <div class="layui-input-block">
		      <input type="checkbox"   name="isShow" id="isShow" lay-skin="switch"  value="${not empty data.isShow? data.isShow:1 }" ${data.isShow == 0? 'checked':'' } lay-filter="isShow" lay-text="ON|OFF">
		    </div>
		 </div>
	</form>
</div>
</body>
<script type="text/javascript" src="commons/layui/layui.js"></script>
<script type="text/javascript">
layui.use(["form"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	form.on("switch(isSearch)", function(data){
	   	if(data.value == '1' ){ $("#isSearch").val(0);}
	   	else{ $("#isSearch").val(1)	   	}	
	});
	form.on("switch(isShow)", function(data){
	   	if(data.value == '1' ){ $("#isShow").val(0);}
	   	else{ $("#isShow").val(1)	   	}	
	});
	
	form.verify({  //表单校验
		name:function(value,item){
			if(!value){
				return "规格名称不能为空" ;
			}
		}
	});
	
	
})	
</script>
</html>