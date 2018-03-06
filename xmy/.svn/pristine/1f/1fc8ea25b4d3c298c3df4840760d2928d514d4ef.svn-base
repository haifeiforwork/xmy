<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>分类Top图设置</title>
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
    		 		 <li class="layui-this">APP分类Top图列表</li>
    		 		 <li>新增APP分类Top图</li>
  			 	</ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show">
					  <table class="layui-table" class="topImgTable" style="width:70%;">
						  <colgroup>
						    <col width="150">
						    <col width="200">
						    <col>
						  </colgroup>
						  <thead>
						    <tr>
						      <th>分类ID</th>
						      <th>分类名称</th>
						      <th>Top图预览</th>
						      <th>操作</th>
						    </tr> 
						  </thead>
						  <tbody>
						  <c:forEach items="${adInfoImagesList}" var="adInfoImage">
						  		  <tr>
								      <td>${adInfoImage.adInfo.id }</td>
								      <td>${adInfoImage.adInfo.name }</td>
								      <td><img style="width:600px;" src="${adInfoImage.adImage.imgPath }"></td>
								      <td> <a href="appSetting//editClassificationTopImg/${adInfoImage.adInfo.id }" class="layui-btn">编辑</a>
								      		<a href="appSetting/delClassificationTopImg/${adInfoImage.adInfo.id }" class="layui-btn layui-btn-danger">删除</a>
								      </td>
								    </tr>
						  </c:forEach>
						  </tbody>
						</table>
      
				</div>
				<div class="layui-tab-item">
					 <form class="layui-form" action="appSetting/addClassificationTopImg" style="width:50%;" method="POST">
					 	 <div class="layui-form-item">
						    <label class="layui-form-label">名称</label>
						    <div class="layui-input-block">
						      	<input type="text" class="layui-input" name="name" />
						    </div>
						  </div>
						  <div class="layui-form-item">
						    <label class="layui-form-label">分类</label>
						    <div class="layui-input-block">
						       <select name="categoryId" lay-verify="required">
						        <option value=""></option>
						        <c:forEach items="${categoryList }" var="category">
						        	 <option value="${category.id }">${category.name }</option>
						        </c:forEach>
						      </select>
						    </div>
						  </div>
						   <div class="layui-form-item">
						    <label class="layui-form-label">Top图</label>
						     <div id="imgdiv" class="layui-input-block"  ><img width="400" height="400" src="${not empty adImage.imgPath? adImage.imgPath : 'goods/image/defaultImage.png' }" /></div>
						    <div id="container"style="margin-left: 100px;">
									<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
									<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									<input type="hidden" name="imgPath" id="iconImg">
							  </div>
						   <!--  <div class="layui-input-block">
						      	<input type="file" name="file" class="layui-upload-file" />
						      	<img id="CategoryTopImage" >
						      	<input type="hidden" name="imgPath" id="categoryTopImageUrl">
						    </div> -->
						    <img id="CategoryTopImage" >
						  </div>
						   <div class="layui-form-item">
						    <label class="layui-form-label">跳转地址</label>
						    <div class="layui-input-block">
						      	<input type="text" class="layui-input" name="data" />
						    </div>
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
});
</script>
	</div>
</body>
</html>