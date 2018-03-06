<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>开抢啦Top图设置</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
.topImgTable tbody tr td img{
		width:100px;
		height:100px;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<!-- 公共layout -->
		<%@ include file="/commons/common_layout.jsp"%>
		<div class="layui-tab layui-tab-brief layui-form" lay-filter="demoTitle">
				<ul class="layui-tab-title site-title">
    		 		 <li ><a href="appSetting/openGrabTopImg">开抢啦To图列表</a></li>
    		 		 <li class="layui-this">新增一张</li>
  			 	</ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item ">
					  33
				</div>
				<div class="layui-tab-item layui-show">
					
					<form class="layui-form" action="appSetting/updateOpenGrabTopImg" style="width:60%;" method="post">
						<div class="layui-form-item">
							<input type="hidden" name="id" value="${adImage.id }"/> 
							 <label class="layui-form-label">权重</label>
							  <div class="layui-input-block">
							  <input type="number" class="layui-input" value="${adImage.weight }" required  name="weight" lay-verify="required">
							  </div>
						</div>
						<div class="layui-form-item">
							 <label class="layui-form-label">跳转url</label>
							  <div class="layui-input-block">
							  <input type="text" class="layui-input" required value="${adImage.data }" name="data" lay-verify="required">
							  </div>
						</div>
						
						
						
						<div class="layui-input-inline"  >
		  				 	<label class="layui-form-label">是否是商品广告图</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="${adImage.type }"  class="isGoodsAd"  lay-filter="isGoodsAd" lay-text="ON|OFF" ${adImage.type == '0'? 'checked':'' } >
						      <input type="hidden" name="type" class="isGoodsAd"   value="${adImage.type }" >
						    </div>
		  				</div>
		  				
		  										
						<div class="layui-input-inline"  >
		  				 	<label class="layui-form-label">是否是大图</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="${adImage.imgType }"  class="isBig"  lay-filter="isBig" lay-text="ON|OFF" ${adImage.imgType == '0'? 'checked':'' } >
						      <input type="hidden" name="imgType" class="isBig"   value="${adImage.imgType }" >
						    </div>
		  				</div>
						
						
						  <div class="layui-form-item">
						    <label class="layui-form-label">Top图</label>
						      <div id="imgdiv" class="layui-input-block"  ><img width="400" height="400" src="${not empty adImage.imgPath? adImage.imgPath : 'goods/image/defaultImage.png' }" /></div>
								<div id="container"style="margin-left: 100px;">
									<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
									<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									<input type="hidden" name="imgPath" id="iconImg" value="${adImage.imgPath }">
							  </div>
						   <%--  <div class="layui-input-block">
						    <input type="hidden" name="adImage.id" />
						      	<input type="file" name="file" class="layui-upload-file" />
						      	<img id="CategoryTopImage" src="${adImage.imgPath }">
						      	<input type="hidden" name="imgPath" value="${adImage.imgPath }" id="categoryTopImageUrl">
						    </div> --%>
						    <img id="CategoryTopImage" >
						  </div> 
						     <div class="layui-form-item">
						   		<div class="layui-input-block">
						  	 		<button type="submit" class="layui-btn">提交</button>
						  	 	</div>
						   </div>
					</form>
				</div>
			</div>
		</div>

		<!-- 底部 -->
		<%@ include file="/commons/buttom.jsp"%>
		<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="advertisement/js/containerImgUpload.js"></script>
	<!-- 	<script type="text/javascript" src="article/js/article.js"></script> -->
			<script>
layui.use(['upload','form'], function(){
	form=layui.form();
  layui.upload({
    url: 'fileUpload/uploadImage' //上传接口
    ,success: function(res){ //上传成功后的回调
      console.log(res);
      var result = res.data;
      console.log(result.success);
      if(result.success == true){
    	  	
    		$("#CategoryTopImage").attr("src",result.filePath);
			
			$("#categoryTopImageUrl").val(result.filePath);
      }
    }
  });
  
	//是否是商品广告图
	form.on("switch(isGoodsAd)", function(data){
	   	if(data.value == '1' ){$(".isGoodsAd").val(0)}
	   	else{ $(".isGoodsAd").val(1) }	
	});
	//图片是否是大图
	form.on("switch(isBig)", function(data){
	   	if(data.value == '1' ){$(".isBig").val(0)}
	   	else{ $(".isBig").val(1) }	
	});
});
</script>
	</div>
</body>
</html>