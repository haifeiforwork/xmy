<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>添加货柜</title>
	<%@ include file="/commons/comm_css.jsp"%>
	<link rel="stylesheet" href="native/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="advertisement/jqueryColour/spectrum.css">
	<style type="text/css">
	.advertisementStyle-none{
		display: none;
	}
	.myImg{
		width:100px;height:100px;
	}
	
	</style>
</head>
<body>
		<form class="layui-form" method="post" id="form">
				<div class="layui-form-item" style="width: 400px;">
					<input type="hidden" value="${container.id }" name="id" id="containerId"/>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-inline"  style="width: 400px;">
						<label class="layui-form-label">货柜名称：</label>
					   	<div class="layui-input-block" >
					      <input type="hidden" id="categoryId" value="${container.categoryId }" name="categoryId"  class="layui-input"  >
					      <input type="text" id="name" name="name" value="${container.name }" required  class="layui-input"  >
					      <label id="chooseCategory"  ><a >请选择<i style="color:#F00">*</i></a></label>
					    </div> 
					</div>
					<div class="layui-input-inline">
						<div style='text-align:center;'>
					    	<input id="full"  />
					    </div>
					</div>
				</div>
				
				<input type="hidden" name="fontColor" id="fontColor" value="${container.fontColor }">
				
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">口号：</label>
				    <div class="layui-input-block">
				    <input type="text" name="slogan" value="${container.slogan }" lay-verify="slogan"  class="layui-input"  > 
				    </div>  
				</div>
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">关键字：</label>
				    <div class="layui-input-block">
				    <input type="text" name="keyWords" value="${container.keyWords }" lay-verify="keyWords"  class="layui-input" placeholder="关键词之间以英文逗号隔开"  > 
				    </div>  
				</div>
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">权重：</label>
				    <div class="layui-input-block">
				     <input type="number" id="weight" value="${container.weight }" name="weight"  class="layui-input"> 
				    </div>  
				</div>
				
				<div class="layui-input-inline"  style="width: 400px;">
					<label class="layui-form-label">货柜类型</label>
				    <div class="layui-input-block">
				      <select name="type" lay-filter="type">
				        <option value="0" <c:if test="${container.type == 0}">selected="selected"</c:if>>大货柜</option>
				        <option value="1" <c:if test="${container.type == 1}">selected="selected"</c:if>>小货柜</option>
				      </select>
				 	</div>
				 </div>
				
				<div class="layui-form-item">
				    <label class="layui-form-label">图标</label>
					<div id="imgdiv" class="layui-input-block"  ><img width="100" height="100" src="${not empty container.iconImg? container.iconImg : 'goods/image/defaultImage.png' }" /></div>
					<div id="container"style="margin-left: 100px;">
						<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
						<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
						<input type="hidden" name="iconImg" id="iconImg" value="${container.iconImg }">
					</div>
					<pre id="console"></pre>
				</div>
			
							
				<div class="layui-form-item">
				    <label class="layui-form-label" >背景</label>
					<div id="imgdiv2" class="layui-input-block"  ><img width="100" height="100" src="${not empty container.bgImg? container.bgImg : 'goods/image/defaultImage.png' }" /></div>
					<div id="container2"style="margin-left: 100px;">
						<a id="selectfiles2" href="javascript:void(0);" class='btn'>选择文件</a>
						<a id="postfiles2" href="javascript:void(0);" class='btn'>开始上传</a>
						<input type="hidden" name="bgImg" id="bgImg" value="${container.bgImg }">
					</div>
					<pre id="console"></pre>
				</div>
			
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">APP货柜广告：</label>
				    <div class="layui-input-block">
				    	 <input type="hidden" name="adId" id="containerAdId"  value="${container.adId }"   required autocomplete="off"  class="layui-input"  >
				      	 <input type="text" name="adName" readonly="readonly" value="${container.adName }" id="containerAdName" required  autocomplete="off"  class="layui-input"  >
				    	 <label id="chooseContainerAd" ><a >请选择<i style="color:#F00">*</i></a></label>
				    </div>  
				</div>
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">PC货柜广告：</label>
				    <div class="layui-input-block">
				    	 <input type="hidden" name="pcAdId" id="containerPcAdId"  value="${container.pcAdId }"   required autocomplete="off"  class="layui-input"  >
				      	 <input type="text" name="pcAdName" readonly="readonly" value="${container.pcAdName }" id="containerPcAdName" required  autocomplete="off"  class="layui-input"  >
				    	 <label id="choosePcContainerAd" ><a >请选择<i style="color:#F00">*</i></a></label>
				    </div>  
				</div>
				<div class="layui-form-item" style="width: 400px;">
				    <input type="hidden" id="goodsIds" value="${container.goodsIds}" name="goodsIds">
				</div>
				<div class="layui-form-item" style="width: 70%;">
						<fieldset class="layui-elem-field">
							<legend>货柜商品</legend>
							<div>
								 <div class="layui-input-inline" style="margin-left:100%;">
									<input type="button" class="layui-btn" id="addGoods" value="添加商品"  />
								 </div>
							</div>
							<div id="tablediv" class="layui-form"  <c:if test="${empty goods}">style='display: none;'</c:if> >
								<table class="layui-table" >
								  <thead>
								    <tr>
								      <th>商品编号</th>
								      <th>商品名称</th>
								      <th>卖价(元)</th>
								      <th>供货商</th>
								      <th>品牌</th>
								      <th>前台一级分类</th>
								      <th>操作</th>
								    </tr> 
								  </thead>
								  <tbody id="content">
								    <c:forEach items="${goods }" var="data">
										  <tr data-id='${data.id }'>
											  <td><input type="hidden" name="goodsId" value="${data.id}">${data.code}</td>
											  <td>${data.name }</td>
											  <td>${data.price}</td>
											  <td>${data.supplierName}</td>
											  <td>${data.brandName}</td>
											  <td>${data.categoryName}</td>
											  <td><a data-id='${data.id }' id="del">移除</a></td>
										  </tr>
								  </c:forEach>
								  </tbody>
								
								</table>		
							</div>
					</fieldset>
				<!-- 	<div class="layui-input-block">
					<input type="button"  class="layui-btn"  lay-submit="" id="forminfo" lay-filter="forminfo" value="保存" />
					</div> -->
				</div>
	</form>
	<input type="hidden" id="containerId" >
</body>
<%@ include file="/commons/comm_footer_js.jsp"%>
<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="advertisement/js/containerImgUpload.js"></script>
<script type="text/javascript" src="advertisement/jqueryColour/docs/jquery-2.1.0.js"></script>
<script type="text/javascript" src="advertisement/jqueryColour/spectrum.js"></script>
<script type="text/javascript" src="advertisement/jqueryColour/docs/docs.js"></script>
<script type="text/javascript">

layui.use(["form","upload","tool","layer"], function(){
	 var form = layui.form(),layer = layui.layer,tool = layui.tool
	 ,$ = layui.jquery,layedit = layui.layedit ;

	 //选择 货柜广告	 	
		$('#chooseContainerAd').on('click', function(){ 
		   var id = ''; //分类id字符串
		   var name = ''; //分类名称字符串
		   var index = layer.open({
			   type: 2,
			   title: '请选择货柜的广告',
			   shadeClose: true,
			   area: ['1000px', '70%'],
			   content: 'advertisement/toChooseAdvertisementList',
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //获取选择广告的值
				   var body = layer.getChildFrame('body', index);
				  var tt = body.find("#content input[type='radio']");
				   tt.each(function(index, item){
					 if( item.checked ){
						 id = $(this).data("id");
						 name = $(this).data("name");
					 }
				   }); 
				   $("#containerAdName").val(name);
				   $("#containerAdId").val(id);
				   layer.close(index); 
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
		});
		 //选择 PC货柜广告	 	
		$('#choosePcContainerAd').on('click', function(){ 
		   var id = ''; //分类id字符串
		   var name = ''; //分类名称字符串
		   var index = layer.open({
			   type: 2,
			   title: '请选择货柜的广告',
			   shadeClose: true,
			   area: ['1000px', '70%'],
			   content: 'advertisement/toChooseAdvertisementList',
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //获取选择广告的值
				   var body = layer.getChildFrame('body', index);
				  var tt = body.find("#content input[type='radio']");
				   tt.each(function(index, item){
					 if( item.checked ){
						 id = $(this).data("id");
						 name = $(this).data("name");
					 }
				   }); 
				   $("#containerPcAdName").val(name);
				   $("#containerPcAdId").val(id);
				   layer.close(index); 
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
		});
	
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
			   $("#name").val(name);
			   $("#categoryId").val(id);
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   })
	});
	
	
	//选择商品	 	
	$('#addGoods').on('click', function(){ 
	   var idStr = "";
	   var index = layer.open({
		   type: 2,
		   title: '请选择商品',
		   shadeClose: true,
		   area: ['90%', '90%'],
		   content: 'limitActivity/goodsList',
		   btn: ['确定', '取消'],
		   btnAlign: 'c',
		   yes:function(index, layero){  //获取选择分类的值
			   var body = layer.getChildFrame('body', index);
			   var tt = body.find("tbody input[type='checkbox']");
			   tt.each(function(index, item){
				 if( item.checked ){
					 idStr = idStr+$(this).data("id")+",";
				 }
			   });
			   var originalGoodsIds=$("#goodsIds").val();
			   $("#goodsIds").val(originalGoodsIds+idStr);
			   addGoods(idStr);
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   })
	});
	
	
	//添加商品
	function addGoods(idStr){
	 var html = ''; 
	 $.ajax({
			url: "limitActivity/findGoods",
			type: "POST",
			dataType: "JSON",
			data:{"idStr":idStr},
			async: false,
			success:function(data){
				 if(data.data.length > 0){
					for(var i = 0; i<data.data.length ; i++){
						var goods = data.data[i];
						html+= '<tr data-id="'+goods.id+'"><td><input type="hidden" name="goodsId" value="'+goods.id+'">'+goods.code+'</td><td>'+goods.name+'</td><td>'+goods.price+'</td><td>'+goods.supplierName+'</td><td>'+goods.brandName+'</td><td>'+goods.categoryName+'</td><td><a data-id="'+goods.id+'" id="del">移除</a></td></tr>'
					}
					$("#content").append(html);
					$("#tablediv").show();
					form.render();
				}
			}
		})  
	}
	//移除商品
	$(document).on("click","tbody tr td #del",function (){
		
		$(this).parent().parent().remove();
		var trs=$(document).find("#content tr");
		//最后一个移除了为空
		if(trs.length==0){
			$("#goodsIds").val("");
		}
		var ids="";
		trs.each(function(index,item){
			//取出id，并加起来
			ids+=$(item).data("id")+',';
			$("#goodsIds").val(ids);
		})
		
	})
	
	//表单提交(本方法已经抽到设置页面)
	form.on('submit(forminfo)', function(data){
		if($("#forminfo").hasClass("layui-btn-disabled")){
			layer.msg('已经保存一次！请不要再次点击', {icon: 2}); 
			return;
		}
		var url;
		var urlAdd="mallSetting/saveAddContainer";
		var urlUpdate="mallSetting/saveUpdateContainer";
		//如果有货柜id，说明只需要更新，否则就是新增
		if($("#containerId").val()){
			url=urlUpdate;
		}else{
			url=urlAdd;
		}
		_this=this;
		 $.ajax({
				url: url ,
				type: "POST",
				dataType: "JSON",
				data:$("#form").serialize(),
				async: false,
				success:function(data){
					$("#containerId").val(data.data.containerId)
					layer.msg('保存成功,请点击确定按钮关闭', {icon: 1}); 
					$("#forminfo").addClass("layui-btn-disabled");
				}
			}) 
		//$.ajax
		// $("#form").submit();
	});
	
})
</script>
</html> 	