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
 		<ul class="layui-tab-title site-title">
	      <a href="advertisement/advertisementList"><li >广告列表</li></a>
	      <li class="layui-this">添加广告</li>
		 </ul>
 	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
 		<form class="layui-form"  action="advertisement/addAdvertisement" method="post" id="form">
 		<blockquote class="layui-elem-quote">广告基本信息</blockquote>
 				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">广告名称</label>
				    <div class="layui-input-block">
				      <input type="text" name="name"  lay-verify="name" value="${param.name }" autocomplete="off"  class="layui-input"  >
				    </div>  
				</div>
				<!-- 首页广告 -->
				<input type="hidden" name="type" value="1">
				
				<div class="layui-form-item">
				    <label class="layui-form-label">广告位置选择<i style="color:#F00">*</i></label>
				  	<input type="hidden" name="positionId" id="positionId" value="">
				    <div class="layui-input-inline" style="width: 200px;">
				      <select lay-filter="positionName">
				        <option value="请选择"></option>
				        <c:forEach items="${data }" var="item">
				        	<option class="adPosition" value="${item.id }" >${item.name } </option>
				        </c:forEach>
				      </select>
				 	</div>
		  		</div>
		  		
		  		<div style="display: none;">
		  			<c:forEach items="${data }" var="item">
				         <input type="hidden" id="pos${item.id }" data-num="${item.imgNum }" data-name="${item.name }" >
				     </c:forEach>
		  		</div>
		  		
		  		<div class="layui-form-item">
				    <label class="layui-form-label">是否分类广告</label>
				    <div class="layui-input-block">
				      <input type="checkbox"  lay-skin="switch"  lay-filter="isCategory" class="isCategory" value="1"  lay-text="ON|OFF" >
				      <input type="hidden" name="isCategory"  class="isCategory" value="1" >
				    </div>
				</div>
				
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">分类名称<i style="color:#F00">*</i></label>
				    <div class="layui-input-block">
				      <input type="text" id="categoryName" lay-verify="categoryName" value="${param.categoryName }" autocomplete="off"  class="layui-input" readonly="readonly" >
				      <input type="hidden" id="categoryId" name="categoryId"  value="">
				      <label id="chooseCategory"  ><a >请选择<i style="color:#F00">*</i></a></label>
				    </div>
				</div>
 			
 		<blockquote class="layui-elem-quote">广告图片 <button class="layui-btn " style="margin-left: 85%;" >添加图片</button></blockquote>
 		<div id="adImage">
 		</div>
 		</form>
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
layui.use(["form","upload","tool","layer"], function(){
	 var form = layui.form(),layer = layui.layer,tool = layui.tool
	 ,$ = layui.jquery,layedit = layui.layedit ;
	
	form.on("switch(isCategory)", function(data){
	 	if(data.value == '1' ){ $(".isCategory").val(0);}
	 	else { $(".isCategory").val(1); 	} 
	});
	
	var imgNum = 0 , positionName = '';
	form.on("select(positionName)", function(data){
		   if(data.value != '' ){  $("#positionId").val(data.value); imgNum = $("#pos"+data.value+"").data("num") ; positionName = $("#pos"+data.value+"").data("name") }
		    adImage();
	});
	form.verify({  //表单校验
	  name:function(value,item){
			if(!value){
				return "广告名称不能为空" ;
			}
		}
	}) ;
	//选择 分类 按钮  	 	
	$('#chooseCategory').on('click', function(){ 
	   var id = ''; //分类id字符串
	   var name = ''; //分类名称字符串
	   var index = layer.open({
		   type: 2,
		   title: '请选择商品的分类',
		   shadeClose: true,
		   area: ['500px', '50%'],
		   content: 'advertisement/toCategoryList',
		   btn: ['确定', '取消'],
		   btnAlign: 'c',
		   yes:function(index, layero){  //获取选择分类的值
			   var body = layer.getChildFrame('body', index);
			   var tt = body.find("#content input[type='radio']");
			   tt.each(function(index, item){
				 if( item.checked ){
					 id = $(this).data("id");
					 name = $(this).data("name");
				 }
			   });
			   $("#categoryName").val(name);
			   $("#categoryId").val(id);
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   })
	});
	
	function adImage(){
		var html = '';
		 for( var i = 0; i< imgNum ; i++){
			 html+= '<div class="layui-form-item"><div class="layui-inline uploadImg">'
			 		+ '<label class="layui-form-label"> 位置 ： '+positionName+'</label> <div class="layui-input-inline">'
					+ '<input type="text" name="imgPath" id="imgPath" class="layui-input" placeholder="点击上传图片" readonly="readonly" >'
					+ '<input type="hidden" name="imgUrl" id="imgUrl">'
					+ '<input type="hidden" name="imgWeight" id="imgWeight">'
					+ '<input type="hidden" name="imgType" id="type">'
			     	+ '</div></div></div>' ;

		 }
		$("#adImage").html(html) ;
	}
	
	//上传广告图片
	$(document).on("click",".uploadImg",function (){
		var _this = $(this);
		var imgPath = _this.find("#imgPath").val();
		var imgUrl = _this.find("#imgUrl").val();
		var imgWeight = _this.find("#imgWeight").val();
		var type = _this.find("#type").val();
		var index = layer.open({
			   type: 2,
			   title: '上传广告图片',
			   shadeClose: true,
			   area: ['500px', '50%'],
			   content: 'advertisement/toAdImageUpload?imgPath='+imgPath+'&imgUrl='+imgUrl+'&imgWeight='+imgWeight+'&type='+type,
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){ 
				   //获取图片信息
				   var body = layer.getChildFrame('body', index);
				   _this.find("#imgPath").val(body.find("#imgPath").val()); //图片地址
				   _this.find("#imgUrl").val(body.find("#url").val()); //图片跳转链接
				   _this.find("#imgWeight").val(body.find("#weight").val()); //图片排序 
				   _this.find("#type").val(body.find("#type").val()); //广告类型
				   layer.close(index); 
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
	})
	
	 //点击添加图片
    $(".layui-elem-quote button").click(function(){
     	  var html = '<div class="layui-form-item"><div class="layui-inline uploadImg">'
	 		+ '<label class="layui-form-label">上传图片</label> <div class="layui-input-inline">'
			+ '<input type="text" name="imgPath" id="imgPath" class="layui-input" placeholder="点击上传图片" readonly="readonly" >'
			+ '<input type="hidden" name="imgUrl" id="imgUrl">'
			+ '<input type="hidden" name="imgWeight" id="imgWeight">'
			+ '<input type="hidden" name="imgType" id="type">'
	     	+ '</div></div>'
	     	+ '<div class="layui-inline"><input type="button" class="layui-btn layui-btn-danger delImg " value="删除"></div></div>';
	     	$("#adImage").append(html) ;
		 return false;
	 }) 
	
	 //删除图片
	 $(document).on("click",".delImg",function (){
		$(this).parent().parent().remove();
		//防止事件再冒泡
		return false;
	 })
	
	//表单提交
	form.on('submit(forminfo)', function(data){
		 $("#form").submit();
	});
})

</script>
</html> 	