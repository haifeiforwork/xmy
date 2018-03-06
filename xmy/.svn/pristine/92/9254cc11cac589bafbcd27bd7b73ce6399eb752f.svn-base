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
    		 		 <li class="layui-this">开抢啦To图列表</li>
    		 		 <li>新增一张</li>
  			 	</ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show">
					  <table class="layui-table" class="topImgTable" style="width:70%;">
						  <thead>
						    <tr>
						      <th>ID</th>
						      <th>权重</th>
						      <th >Top图预览</th>
						      <th>操作</th>
						    </tr> 
						  </thead>
						  <tbody>
						  <c:forEach items="${adImageList}" var="adImage">
						  		  <tr>
								      <td>${adImage.id }</td>
								      <td>${adImage.weight }</td>
								      <td ><img style="width:600px;height: 200px; " src="${adImage.imgPath }" ></td>
								      <td> 
								      		<a href="appSetting//editOpenGrabTopImg/${adImage.id }" class="layui-btn">编辑</a>
								      		<a href="appSetting/delOpenGrabTopImg/${adImage.id }" class="layui-btn layui-btn-danger">删除</a>
								      </td>
								    </tr>
						  </c:forEach>
						  </tbody>
						</table>
				</div>
				<div class="layui-tab-item">
					
					<form class="layui-form" action="appSetting/addOpenGrabTopImg" style="width:60%;" method="post">
						<div class="layui-form-item">
							<input type="hidden" name="adId" value="${adInfo.id }"/> 
							 <label class="layui-form-label">权重</label>
							  <div class="layui-input-block">
							  <input type="number" class="layui-input" required  name="weight" lay-verify="required">
							  </div>
						</div>
						<div class="layui-form-item">
							 <label class="layui-form-label">跳转url</label>
							  <div class="layui-input-block">
							  <input type="text" class="layui-input" required  name="data" placeholder="商品图输入id即可  广告图输入跳转地址" lay-verify="required">
							  </div>
						</div>
						
						<div class="layui-input-inline"  >
		  				 	<label class="layui-form-label">是否是商品广告图</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="1"  class="isGoodsAd"  lay-filter="isGoodsAd" lay-text="ON|OFF" >
						      <input type="hidden" name="type" class="isGoodsAd"   value="1" >
						    </div>
		  				</div>
		  				
		  						  										
						<div class="layui-input-inline"  >
		  				 	<label class="layui-form-label">是否是大图</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch"  value="0" class="isBig"  lay-filter="isBig" lay-text="ON|OFF"  checked >
						      <input type="hidden" name="imgType" class="isBig"   value="0" }" >
						    </div>
		  				</div>
		  				 
						 <div class="layui-form-item">
						    <label class="layui-form-label">Top图</label>
						    <div id="imgdiv" class="layui-input-block"  ><img width="400" height="400" src="${not empty container.iconImg? container.iconImg : 'goods/image/defaultImage.png' }" /></div>
								<div id="container"style="margin-left: 100px;">
									<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
									<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									<input type="hidden" name="imgPath" id="iconImg" value="">
							</div>
						 <!--    <img id="CategoryTopImage" > -->
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