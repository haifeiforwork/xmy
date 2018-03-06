<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>PC首页配置</title>
	<%@ include file="/commons/comm_css.jsp"%>
	<link rel="stylesheet" href="native/css/style.css"/>
	<style type="text/css">
	.advertisementStyle-none{
		display: none;
	}
	.myImg{
		width:100px;height:100px;
	}
	.chooseAddContainer{
	width:50%;
	}
	.my-del{
	float:right;
	margin-top:-40px;
	}
	.my-input{
	width:100px;
	float:left;
	}
	</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
 		<ul class="layui-tab-title site-title">
	      <a href="##"><li >首页设置</li></a>
		 </ul>
 	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
 		<form class="layui-form"  action="mallSetting/setIndexConfig" method="post" id="form">
 		<blockquote class="layui-elem-quote">基础信息设置</blockquote>
 				<div class="layui-form-item" style="width: 400px;">
 				<input type="hidden" name="id" value="${indexConfig.id }">
 				</div>
 				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">Top图广告：</label>
				    <div class="layui-input-block" id="chooseTopAd" >
				    	 <input type="hidden" name="topAdId" id="topAdId"  value="${indexConfig.topAdId }" required  autocomplete="off"  class="layui-input"  >
				      	 <input type="text" name="topAdName" readonly="readonly"value="${indexConfig.topAdName }"  id="topAdName" required autocomplete="off"  class="layui-input"  >
				    	 <label ><a >请选择<i style="color:#F00">*</i></a></label>
				    </div>  
				    
				</div>
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">1号广告位：</label>
				    <div class="layui-input-block"  id="chooseFirstAd">
				    	 <input type="hidden" name="firstAdId" id="firstAdId"  value="${indexConfig.firstAdId }"  required autocomplete="off"  class="layui-input"  >
				      	 <input type="text" name="firstAdName" readonly="readonly" value="${indexConfig.firstAdName }" id="firstAdName" required  autocomplete="off"  class="layui-input"  >
				    	 <label ><a >请选择<i style="color:#F00">*</i></a></label>
				    </div>  
				    
				</div>
				
				<%-- <blockquote class="layui-elem-quote" >货柜设置
					<input type="button" style="position:relative;left:30%;" class="layui-btn layui-btn-warm" id="addContainer"  lay-submit="" lay-filter="addContainer" value="添加货柜" />
				</blockquote>
				
				<div class="layui-form-item" style="width: 400px;">
					<input type="hidden" name="containerIds" id="containerIds" value="${containerIds}">
				</div>
			
			 <c:forEach items="${containers}" var="container">
				<div class="layui-form-item" style="width: 400px;">
				    <label class="layui-form-label">货柜名称：</label>
				    <div class="layui-input-block chooseAddContainer" >
				       <input type="hidden" id="containerId" value="${container.id }"/>
				       <input type="hidden" id="categoryId"  class="layui-input"  value="${container.categoryId }">
				       <input type="text" id="categoryName"  value="${container.name }"  required  class="layui-input my-input"  readonly="readonly">
				       
				       <label  ><a >请选择<i style="color:#F00">*</i></a></label>
				    </div> 
				    <input type="button"  data-id="${containerId}" class="layui-btn layui-btn-danger delContainer my-del" value="删除">
				</div>
			 </c:forEach>
		 --%>
			<input type="hidden" name="isIndex" value="2">	
			<div id="last"></div>	
 		</form>
		<div class="layui-input-block" style="margin-top:20px;">
		<input type="button"  class="layui-btn "  lay-submit="" lay-filter="forminfo" value="确认提交" />
		</div>
		<br><br><br><br>
		<form class="layui-form"  action="advertisement/pcIndexSearchSetting" method="post">
			<div class="layui-form-item" style="width: 400px;">
			    <label class="layui-form-label"  style="font-size: 13px;">关键字配置：</label>
			    <div class="layui-input-block" >
			    	 <input type="hidden" name="id" value="${term.id }" required  autocomplete="off"  class="layui-input"  >
			      	 <input type="text" name="name" value="${term.name }"  placeholder="搜索关键字配置请用英文逗号分隔" class="layui-input"  >
			    </div>  
			</div>
			<div class="layui-input-block" style="margin-top:20px;">
				<input type="submit"  class="layui-btn "  lay-submit="" value="确认提交" />
			</div>
		</form>
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
	//选择 Top图 按钮  	 	
	$('#chooseTopAd').on('click', function(){ 
 	   var id = ''; //分类id字符串
	   var name = ''; //分类名称字符串
	   var index = layer.open({
		   type: 2,
		   title: '请选择Top图广告',
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
			   $("#topAdName").val(name);
			   $("#topAdId").val(id);
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   }) 
	});

	//选择 1号广告位	 	
	$('#chooseFirstAd').on('click', function(){ 
	   var id = ''; //分类id字符串
	   var name = ''; //分类名称字符串
	   var index = layer.open({
		   type: 2,
		   title: '请选择商品的分类',
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
			   $("#firstAdName").val(name);
			   $("#firstAdId").val(id);
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   })
	});
	//选择货柜新增
	 $('body').on("click",".chooseAddContainer",function(){
		   var this_ = $(this);
		   
		   var _this_container_id=$(this).find("#containerId").val();
		   var index = layer.open({
			   type: 2,
			   title: '设置货柜',
			   shadeClose: true,
			   area: ['90%', '90%'],
			   content: 'advertisement/toAddContainer?containerId='+_this_container_id,
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){ //获取货柜的id和name
				   var body = layer.getChildFrame('body', index);
				   var form = body.find("#form");//添加货柜那边的form表单
				   var containerId = body.find("#containerId").val();
				   var name = body.find("#name").val();
				   this_.find("#categoryName").val(name);
				  // var ids=$('body').find("#containerIds").val();//获取首页包含的总的货柜集合
				//   ids=ids+containerId+","
				 //  $('body').find("#containerIds").val(ids);//更新首页包含的总的货柜集合
				   
				   
				   
				    var url;
					var urlAdd="mallSetting/saveAddContainer";
					var urlUpdate="mallSetting/saveUpdateContainer";
					
					//如果有货柜id，说明只需要更新，否则就是新增
					if(body.find("#containerId").val()){
						url=urlUpdate;
					}else{
						url=urlAdd;
					}
					_this=this;
					 $.ajax({
							url: url ,
							type: "POST",
							dataType: "JSON",
							data:form.serialize(),
							async: false,
							success:function(data){
								//给新增加的货柜id放到对应的货柜id
								(this_).find("#containerId").val(data.data.containerId);
								layer.msg('保存成功', {icon: 1}); 
								
								 var ids="";
								  //重新计算货柜的总货柜id集合,并设置回去
								   var chooseAddContainer=$(".chooseAddContainer");
									  chooseAddContainer.each(function(index,item){
										 
										  var containerId=$(this).find("#containerId").val();
										  ids+=containerId+",";
									  })
									  $('body').find("#containerIds").val(ids);  
							}
						}) 
				   
				   layer.close(index);
				  
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   }) 
	}) 
	//添加货柜
	$('body').on("click","#addContainer",function(){
		var htmlContainer='<div class="layui-form-item" style="width: 400px;">'+
		   ' <label class="layui-form-label">货柜名称：</label>'+
		   ' <div class="layui-input-block chooseAddContainer"  >'+
		   ' <input type="hidden" id="containerId"/>'+
		    ' <input type="hidden" id="categoryId"  name="categoryId"  class="layui-input"  >'+
		     '  <input type="text" id="categoryName"  required  class="layui-input my-input my-input"  readonly="readonly">'+
		   
		    '  <label  ><a >请选择<i style="color:#F00">*</i></a></label>'+
		    '</div>'+
		    '<input type="button" class="layui-btn layui-btn-danger delContainer my-del" value="删除">'+
		    '</div>'
		$("#last").before(htmlContainer);
	})
	//删除货柜
	$(document).on("click",".delContainer",function(event){
		  $(this).parent().remove();
		 event.stopPropagation();
		 var ids="";
		   var chooseAddContainer=$(".chooseAddContainer");
		  chooseAddContainer.each(function(index,item){
			  var containerId=$(this).find("#containerId").val();
			  ids+=containerId+",";
		  })
		  $('body').find("#containerIds").val(ids);  
	})

	//表单提交
	form.on('submit(forminfo)', function(data){
		 $("#form").submit();
	});

})
</script>
</html> 	