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
    		 		 <li><a href="appSetting/classificationTopImg"> APP分类Top图列表</a></li>
    		 		 <li class="layui-this">编辑APP分类Top图</li>
  			 	</ul>
			<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item ">
					 
				</div>
				<div class="layui-tab-item layui-show">
					 <form class="layui-form" action="appSetting/updateClassificationTopImg" style="width:50%;" method="POST">
					 	 <div class="layui-form-item">
						    <label class="layui-form-label">名称</label>
						    <div class="layui-input-block">
						      	<input type="text" class="layui-input" name="name" value="${adInfoImage.name }"/>
						      	<input type="hidden" name="adInfo.id" value="${adInfoImage.id }"/>
						      	<input type="hidden" name="adImage.id" value="${adInfoImage.adImageId }"/>
						    </div>
						  </div>
						  <div class="layui-form-item">
						    <label class="layui-form-label">分类</label>
						    <div class="layui-input-block">
						       <select name="categoryId" lay-verify="required">
						        <option value=""></option>
						        <c:forEach items="${categoryList }" var="category">
						        	 <option value="${category.id }" <c:if test="${category.id == adInfoImage.categoryId}">selected</c:if> >${category.name }</option>
						        </c:forEach>
						      </select>
						    </div>
						  </div>
						   <div class="layui-form-item">
						    <label class="layui-form-label">Top图</label>
						      <div id="imgdiv" class="layui-input-block"  ><img width="400" height="400" src="${not empty adInfoImage.imgPath? adInfoImage.imgPath : 'goods/image/defaultImage.png' }" /></div>
						  	  <div id="container"style="margin-left: 100px;">
									<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
									<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									<input type="hidden" name="imgPath" id="iconImg" value="${adInfoImage.imgPath }">
							  </div>
							  <pre id="console"></pre>
						  <%--   <div class="layui-input-block">
						    <input type="hidden" name="adImage.id" value="${adInfoImage.adImageId }" />
						      	<input type="file" name="file" class="layui-upload-file" />
						      	<img id="CategoryTopImage" src="${adInfoImage.imgPath }">
						      	<input type="hidden" name="imgPath" value="${adInfoImage.imgPath }" id="categoryTopImageUrl">
						    </div> 
						    <img id="CategoryTopImage" >--%>
						  </div>
	
						   <div class="layui-form-item">
						    <label class="layui-form-label">跳转地址</label>
						    <div class="layui-input-block">
						      	<input type="text" class="layui-input" name="data" value="${adInfoImage.data }"/>
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