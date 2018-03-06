<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%> 
<%@ include file="/commons/comm_css.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品图片关联</title>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form"  >
	<ul class="layui-tab-title site-title">
     	<li class="layui-this" >商品图片关联</li>
	</ul>
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item" >
		<form id="form" action="goods/relevanceGoodsImg" method="post" >	
			<div class="layui-tab-item layui-show "   >
				<blockquote class="layui-elem-quote">请上传ZIP压缩包,系统将自动为您解压文件</blockquote>
					<div class="layui-input-inline" style="margin-top:30px;" >
						<input type="file" name="file" id="uploadFile" class="layui-upload-file" lay-title="上传zip压缩包"> 
					</div>
			</div>
			<div class="layui-form" style="margin-top: 120px;display: none;" id="filePathDiv">
				<input type="text" name="zipFile" id="zipFile" value="" readonly="readonly">
			</div>
			<div class="layui-form"  style="margin-top: 120px;">
				<div class="layui-input-inline" style="width: 400px;">
				    <label class="layui-form-label">oss图片路径(请确保路径正确)<i style="color:#F00">*</i></label>
					<div class="layui-input-block choose">
						  <input type="text" name="ossPath"  placeholder="格式:/goods/goods/20170830/" id="osspath" class="layui-input">
					</div>   
				</div>	
			
				<div class="layui-input-inline" style="width: 400px;">
					<input type="button" id="save" class="layui-btn" value="确认" /> 
				</div>	
			</div>
		</form>
	</div> 
 </div>
 <%@ include file="/commons/buttom.jsp"%>
</div> 
	
</body>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	console.log(tool.getFullDate(new Date())) ;
	
	layui.upload({
	    url: 'fileUpload/uploadZip' //上传接口
	    ,ext: 'zip'
	    ,success: function(res){ //上传成功后的回调]
	    	var result = res.data;
	      	if(  result.success == true ){
	      		 $("#zipFile").val(result.filePath);
	      		 $("#uploadFile").attr('lay-title',"上传成功");
	      		 $("#filePathDiv").css("display","block");
	      		 tool.info("上传成功 ， 点击确认将会自动关联图片");
	      	}else{
	      		 tool.warning(result.msg);
	      	}
	    }
	 });
	
	
	$("#save").click(function (){
	    if($("#zipFile").val() == ''){
			tool.warning("请先上传图片压缩包");
			return ;
		}
		var ossPath = $("#osspath").val();
		if ( ossPath == '') {
			tool.warning("oss文件路径不能为空");
			return ;
		} 
		 layer.msg('关联中', {
			  icon: 16,
			  shade: 0.05,
			  time:20000
		});
		 $.ajax({
	        type: "POST",
	        dataType: "html",
	        url: "goods/relevanceGoodsImg",
	        data: $("#form").serialize(),
	        dataType: "JSON",
	        success: function (data) {
	       		 var result = data.data;
	       		 if( result.success == true ){
	       			 tool.info("图片关联成功");
	       			 layer.closeAll('loading');
	       		} else {
	       			tool.warning(result.message);
	       		}  
	        }
		})  
		
	});
   	 
});
</script>

</html>