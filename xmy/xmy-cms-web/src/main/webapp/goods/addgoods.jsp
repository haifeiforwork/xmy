<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/commons/comm_cons_tag.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
 	 <ul class="layui-tab-title site-title">
      	<a href="goods/goodsList" ><li >商品列表</li></a>
      	<li class="layui-this">添加商品</li>
	 </ul>
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<blockquote class="layui-elem-quote">商品基本信息</blockquote>
				<form class="layui-form" action="goods/addGoods"  method="post" id="form">
					<div class="layui-form-item">
					    <label class="layui-form-label">商品分类<i style="color:#F00">*</i></label>
					    <div class="layui-input-block" id="chooseCategory" >
					      <input type="text" name="categoryName"  id="cname" lay-verify="cid" value="${param.cid }" autocomplete="off"  class="layui-input" readonly="readonly" placeholder="点击选择"  >
					    </div>
					</div>
					<!-- 商品搜索词汇 -->
					<div style="margin-left: 100px;">
					<fieldset class="layui-elem-field" >
					  <legend>搜索词汇</legend>
					  <div class="layui-field-box" id="specContainer">
						
					  </div>
					</fieldset>
					</div>
					
					<input type="hidden" id="cidStr" name="categoryId" value="">
					<input type="hidden" name="isRecycle" value="1">
					<input type="hidden" name="specIdStr" id="specIdStr" value="">
					<div class="layui-form-item">
					    <label class="layui-form-label">商品名称<i style="color:#F00">*</i></label>
					    <div class="layui-input-block">
					      <input type="text" name="name" lay-verify="name" value="${param.name }" placeholder="单行输入" autocomplete="off"  class="layui-input">
					    </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">商品名称全称</label>
					    <div class="layui-input-block">
					      <input type="text" name="fullName" lay-verify="fullName" value="${param.fullName }" placeholder="单行输入"  autocomplete="off"  class="layui-input"  >
					    </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">商品名称简称</label>
					    <div class="layui-input-block">
					      <input type="text" name="shortName" lay-verify="shortName" value="${param.shortName }" placeholder="单行输入"  autocomplete="off"  class="layui-input"  >
					    </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">商品关键字</label>
					    <div class="layui-input-block">
					      <input type="text" name="keyword" lay-verify="keyword" value="${param.keyword }" autocomplete="off" placeholder="单行输入" class="layui-input">
					    </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">商品编码<i style="color:#F00">*</i></label>
					    <div class="layui-input-block">
					      <input type="text" name="code" lay-verify="code" value="${param.code }" autocomplete="off" placeholder="单行输入" class="layui-input">
					    </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">商品称编码<i style="color:#F00">*</i></label>
					    <div class="layui-input-block">
					      <input type="text" name="weighCode" value="${param.weighCode }" autocomplete="off" placeholder="单行输入" class="layui-input">
					    </div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">品牌<i style="color:#F00">*</i></label>
						    <div class="layui-input-block choose"  data-vid="3" >
						      <input type="text" id="brandName" name="brandName" lay-verify="brandName" value="${param.brandName }" autocomplete="off" placeholder="请选择" class="layui-input" readonly="readonly">
						      <input type="hidden" id="brandId" name="brandId" value="">
						 	</div>
						 </div>
						 <div class="layui-input-inline"  style="width: 400px;">
							<label class="layui-form-label">供应商<i style="color:#F00">*</i></label>
						    <div class="layui-input-block choose"  data-vid="4" >
						      <input type="text" id="supplierName" name="supplierName" lay-verify="supplierName" value="${param.supplierName }" autocomplete="off" placeholder="请选择" class="layui-input" readonly="readonly">
						      <input type="hidden" id="supplierId" name="supplierId" value="">
						 	</div>
						 </div>
						 <div class="layui-input-inline"  style="width: 400px;">
								<label class="layui-form-label">采购市场</label>
							    <div class="layui-input-block">
							      <select name="market" lay-filter="market">
							        <option value=""></option>
							        <option value="0" <c:if test="${param.market == 0}">selected="selected"</c:if>>水果市场</option>
							        <option value="1" <c:if test="${param.market == 1}">selected="selected"</c:if>>农配中心</option>
							      </select>
							 	</div>
						 </div>
						 <div class="layui-input-inline"  style="width: 400px;">
								<label class="layui-form-label">商品类型</label>
							    <div class="layui-input-block">
							      <select name="typeName" lay-filter="typeName">
							        <option value="0" <c:if test="${param.type == 0}">selected="selected"</c:if>>春季商品</option>
							        <option value="1" <c:if test="${param.type == 1}">selected="selected"</c:if>>夏季商品</option>
							        <option value="2" <c:if test="${param.type == 2}">selected="selected"</c:if>>秋季商品</option>
							        <option value="3" <c:if test="${param.type == 3}">selected="selected"</c:if>>冬季商品</option>
							      </select>
							 	</div>
						 </div>
		  			</div>
		  			
		  			<div class="layui-form-item" pane="">
					    <label class="layui-form-label">供货策略</label>
					    <div class="layui-input-block">
					      <input type="radio" name="supplyStrategy" value="0" lay-skin="primary" title="自动供货" checked="">
					      <input type="radio" name="supplyStrategy" value="1" lay-skin="primary" title="库存确认">
					      <input type="radio" name="supplyStrategy" value="2" lay-skin="primary" title="人工确认"  >
					    </div>
		  			</div> 
		  					  			
		  			
		  			<div class="layui-form-item">
		  				 <div class="layui-input-inline"  style="width: 400px;">
		  				 	<label class="layui-form-label">重量<i style="color:#F00">*</i></label>
						    <div class="layui-input-block">
						      <input type="text" name="weight" lay-verify="weight" value="${param.weight }" autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
		  				 </div>
						<div class="layui-input-inline"  style="width: 800px;">
							<label class="layui-form-label">商品单位<i style="color:#F00">*</i></label>
						    <div class="layui-input-inline">
						    	<input type="text" id="unitName" name="unitName" lay-verify="unitName" value="${param.unitName }" autocomplete="off" placeholder="单行输入" class="layui-input" width="100px;">
						    </div>
						    <div class="layui-input-inline" style="width: 100px;">
						      <select  lay-filter="unit">
						        <option value="请选择"></option>
						        <option value="个" >个</option>
						        <option value="件" >件</option>
						        <option value="台" >台</option>
						        <option value="瓶" >瓶</option>
						        <option value="罐" >罐</option>
						        <option value="盒" >盒</option>
						        <option value="袋" >袋</option>
						        <option value="斤" >斤</option>
						        <option value="千克" >千克</option>
						        <option value="克" >克</option>
						        <option value="毫克" >毫克</option>
						      </select>
						 	</div>
						</div>
					</div>
		  			
		  			<div class="layui-form-item">
			  			<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">库存<i style="color:#F00">*</i></label>
						    <div class="layui-input-block">
						      <input type="text" name="num" lay-verify="number" value="${param.num }" autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
			  			
						
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">库存报警值<i style="color:#F00">*</i></label>
						    <div class="layui-input-block">
						      <input type="text" name="alarmNum" lay-verify="number" value="${param.alarmNum }" autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
			  			
			  			<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">成本价<i style="color:#F00">*</i></label>
						    <div class="layui-input-block">
						      <input type="text" name="costPrice" lay-verify="price" value="${param.costPrice }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						
			  			<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">销售价<i style="color:#F00">*</i></label>
						    <div class="layui-input-block">
						      <input type="text" name="price" lay-verify="price" value="${param.price }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">vip价格</label>
						    <div class="layui-input-block">
						      <input type="text" name="vipPrice" lay-verify="vipPrice" value="${param.vipPrice }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">市场价<i style="color:#F00">*</i></label>
						    <div class="layui-input-block">
						      <input type="text" name="marketPrice" lay-verify="price" value="${param.marketPrice }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						
			  			<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">手机端销售价</label>
						    <div class="layui-input-block">
						      <input type="text" name="phonePrice" lay-verify="phonePrice" value="${param.phonePrice }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">商品积分</label>
						    <div class="layui-input-block">
						      <input type="text" name="points" lay-verify="number" value="${param.points }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">产地</label>
						    <div class="layui-input-block">
						      <input type="text" name="area" lay-verify="area" value="${param.area }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						
										
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">存储方式</label>
						    <div class="layui-input-block">
						      <input type="text" name="storageMethod" lay-verify="storageMethod" value="${param.storageMethod }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">包装方式</label>
						    <div class="layui-input-block">
						      <input type="text" name="packMethod" lay-verify="packMethod" value="${param.packMethod }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">默认加入购物车量<i style="color:#F00">*</i></label>
						    <div class="layui-input-block">
						      <input type="text" name="defaultBuynum" lay-verify="defaultBuynum" value="${param.defaultBuynum }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
		  			</div>
		  			<div class="layui-form-item">
		  				 <div class="layui-input-inline"  style="width: 200px;">
		  				 	<label class="layui-form-label">是否上架销售</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="1"  class="isPutway"  lay-filter="isPutway" lay-text="ON|OFF">
						      <input type="hidden" name="isPutway" class="isPutway"   value="1" >
						    </div>
		  				 </div>
		  				 <div class="layui-input-inline"  style="width: 200px;">
			  				<label class="layui-form-label">是否非卖品</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="1"  class="isSale" lay-filter="isSale" lay-text="ON|OFF">
						      <input type="hidden" name="isSale" class="isSale" value="1" >
						    </div>
		  				 </div>
		  				 <div class="layui-input-inline"  style="width: 200px;">
		  				 	<label class="layui-form-label">是否被搜索</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch"  value="1" class="isSearch" lay-filter="isSearch" lay-text="ON|OFF"> 
						      <input type="hidden" name="isSearch" class="isSearch" value="1"  >
						    </div>
		  				 </div>
		  				 <div class="layui-input-inline"  style="width: 200px;">
			  				<label class="layui-form-label">是否全国陪送</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="1" class="isDelivery"   lay-filter="isDelivery" lay-text="ON|OFF">
						      <input type="hidden" name="isDelivery"  class="isDelivery" value="1"> 
						    </div>
		  				 </div>
						 <div class="layui-input-inline"  style="width: 200px;">
						 	<label class="layui-form-label">是否支持上门提货</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="1" class="isUd"   lay-filter="isUd" lay-text="ON|OFF">
						      <input type="hidden" name="isUd"  class="isUd" value="1"> 
						    </div>
						 </div>
						 
						 <div class="layui-input-inline"  style="width: 200px;">
						 	<label class="layui-form-label">是否国产</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="0" class="isChina"   lay-filter="isChina" lay-text="ON|OFF" checked >
						      <input type="hidden" name="isChina"  class="isChina" value="0"> 
						    </div>
						 </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">当前商品规格值</label>
					    <div class="layui-input-block">
					      <input type="text" name="standard" lay-verify="standard" value="${param.standard }"  autocomplete="off" placeholder="单行输入" class="layui-input">
					    </div>
					</div>
					
		  			
		  			<div class="layui-form-item">
					    <label class="layui-form-label">水印图片</label>
					    <div class="layui-input-block" id="choosePicture">
					      <input type="text" id="waterImg" value="${param.imagePath }" autocomplete="off" placeholder="点击选择" class="layui-input" readonly="readonly">
					      <input type="hidden" name="wmId" id="wmId" value="">
					    </div>
					</div>
							  			
		  			<!-- 选择商品图片 -->
					<div class="layui-form-item">
					    <label class="layui-form-label">商品图片</label>
						<div id="imgdiv" class="layui-input-block"  >	<!-- 选择的文件 -->
						</div>
						<div id="container"style="margin-left: 100px;">
							<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
							<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
						</div>
						<pre id="console"></pre>
					</div>
					
					<!-- 图片表格 -->
		  		 	<div class="layui-form-item" style="width:500px;margin-left:100px;display:none" id="goodsImagetab" >
		  				<table class="layui-table" width="300">
			  				 <colgroup>
						      <col width="80">
						      <col width="100">
						      <col width="100">
						      <col width="100" >
						    </colgroup>
						    <thead>
						     <tr>
						        <th>删除</th>
						        <th>图片路径</th>
						        <th>预览</th>
						        <th>排序</th>
						      </tr> 
						    </thead>
		   					<tbody id="goodsImage">
		   					</tbody>
		  				</table>
		  				<input type="button" id=removeImage value="删除" >
		  			</div> 
							
					<blockquote class="layui-elem-quote">商品详细信息</blockquote>
					
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">商品描述</label>
					    <div class="layui-input-block">
					      <textarea placeholder="请输入内容" name="des" class="layui-textarea"></textarea>
					    </div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">是否使用描述模板</label>
					    <div class="layui-input-block">
					      <input type="checkbox"  lay-skin="switch" value="1"  class="isDesmodel" lay-filter="isDesmodel" lay-text="ON|OFF">
					      <input type="hidden" name="isDesmodel" value="1"  class="isDesmodel" >
					      
					    </div>
					</div>
					
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">网页端详细介绍</label>
					    <div class="layui-input-block">
					       <textarea  id="pcEditor" name="pcIntroduced" style="width:1024px;height:300px;" >${param.pcIntroduced }</textarea>
					    </div>
					 </div>
					
					 <div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">手机端详细介绍</label>
					    <div class="layui-input-block">
					      <textarea id="phoneeEditor"  name="phIntroduced"  style="width:1024px;height:300px;" >${param.phIntroduced }</textarea>
					    </div>
					 </div>
					
					 <div class="layui-form-item">
					    <div class="layui-input-block">
					      <button class="layui-btn" lay-submit=""  id="save" >确认提交</button>
					    </div>
					 </div>

				</form>
		</div>
		</div>	
<!-- 底部 -->
<%@ include file="/commons/buttom.jsp"%>
</div>		
</body>
<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="goods/goodsupload.js"></script>
<!-- ueditor富文本 -->
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="goods/js/pcUeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="goods/js/phoneUeditor.js"></script>
<script type="text/javascript">
var html='';
 layui.use(["form","upload","tool","layer","tree","layedit"], function(){
	 var form = layui.form(),layer = layui.layer,tool = layui.tool
	 ,$ = layui.jquery,layedit = layui.layedit;
	 //创建一个编辑器
	 
	layedit.set({
	  uploadImage: {
	    url: 'fileUpload/layuiEditUploadImage' //接口url
	    ,type: '' //默认post
	  }
	}); 
	 
	var pcEdit = layedit.build('pc_editor');
	var phoneEdit = layedit.build('phone_editor');
	 form.on("select()", function(data){ //监听所有的select，去掉过滤器(filter)即可
	 console.log(data);
	});
	form.on("switch(isPutway)", function(data){
	 	if(data.value == '1' ){ $(".isPutway").val(0);}
	 	else { $(".isPutway").val(1); 	} 
	});
	form.on("switch(isDelivery)", function(data){
	   	if(data.value == '1' ){$(".isDelivery").val(0)}
	   	else{ $(".isDelivery").val(1) }	
	});
	form.on("switch(isSearch)", function(data){
	   	if(data.value == '1' ){ $(".isSearch").val(0);}
	   	else{ $(".isSearch").val(1)}	
	});
	form.on("switch(isSale)", function(data){
	    if(data.value == '1' ){ $(".isSale").val(0);}
	   	else{$(".isSale").val(1) }	 
	});
	form.on("switch(isDesmodel)", function(data){
	   	if(data.value == '1' ){ $(".isDesmodel").val(0);}
	   	else{ $(".isDesmodel").val(1)	   	}	
	});
	form.on("switch(isUd)", function(data){
	   	if(data.value == '1' ){ $(".isUd").val(0);}
	   	else{ $(".isUd").val(1)	   	}	
	});
	form.on("switch(isChina)", function(data){
	   	if(data.value == '1' ){ $(".isChina").val(0);}
	   	else{ $(".isChina").val(1)	   	}	
	});
	form.on("select(unit)", function(data){
	   	if(data.value != '' ){  $("#unitName").val(data.value);}
	});
	
	form.verify({ 
	  	cid:function(value,item){
			if(!value){
				return "商品分类不能为空" ;
			}
		},
		name:function(value,item){
			if(!value){
				return "商品名称不能为空" ;
			}
		},
		code:function(value,item){
			var haveCode=0;
			if(!value){
				return "商品编码不能为空" ;
			}
			$.ajax({ 
		          type : "post", 
		          url : "goods/findGoodsCode", 
		          data : "code="+value, 
		          async : false, 
		          success : function(data){ 
		        	  haveCode=data.data;
		          } 
		    }); 
			if(haveCode==1){
				return "商品编码已经存在" ;
			}
		}, 
		price:function(value,item){
			if(!value){
				return "金额不能为空" ;
			}
		} 
	}) ; 

	 
 	//移除商品
	$("#removeImage").click(function() {
		 $("#goodsImage input[type='checkbox']:checked").each(function() { // 遍历选中的checkbox
	          rows = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
	          $("#goodsImage").find("tr:eq("+rows+")").remove();
	     });
	 	if( $("#goodsImage input").length == 0 ){
	 		$("#goodsImagetab").hide();
	 	}
		
	}) 
	
	
	//选择 分类 按钮  	 	
	$('#chooseCategory').on('click', function(){ 
	   var idStr = ','; //分类id字符串
	   var cnameStr = ','; //分类名称字符串
	   var specIdStr = ','; //分类分词的id字符串
	   var specName = ''; //分类分词名称
	   var html = '';
	   var index = layer.open({
		   type: 2,
		   title: '请选择商品的分类',
		   shadeClose: true,
		   area: ['60%', '60%'],
		   content: 'goods/toChooseCategoryWordSeg',
		   btn: ['确定', '取消'],
		   btnAlign: 'c',
		   yes:function(index, layero){  //获取选择分类的值
			   $("#specContainer").html('');
			   var body = layer.getChildFrame('body', index);
		   	   //获取二级分类的id 和 分类名称
		   	   var tt = body.find("#content table input[type='checkbox']");
			   tt.each(function(index, item){
				 if( item.checked ){
					 idStr = idStr+$(this).data("id")+",";
					 cnameStr = cnameStr+$(this).data("name")+",";
				 }
			   });
			   $("#cidStr").val(idStr);
			   $("#cname").val(cnameStr);
			   //获取二级分类规格id
			   var  spec = body.find("#wordseg table input[type='checkbox']");
			   spec.each(function(index, item){
				 if( item.checked ){
					 specIdStr = specIdStr+$(this).data("id")+",";
					 specName = $(this).data("name");
					 html=' <button class="layui-btn layui-btn-primary" disabled >'+specName+'</button>';
					 $("#specContainer").append(html);
				 }
				});
			   $("#specIdStr").val(specIdStr);
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   })
	});
 	
 	
	//选择品牌,供应商按钮
	$('.choose').on('click', function(){ 
	   var vid = $(this).data("vid");
	   var index = layer.open({
		   type: 2,
		   title: '请选择',
		   shadeClose: true,
		   area: ['400px', '50%'],
		   content: 'goods/chooseList?vid='+vid,
		   btn: ['确定', '取消'],
		   btnAlign: 'c',
		   yes:function(index, layero){  //获取选择分类的值
			   var tt = layer.getChildFrame('body', index).find("table input[type='radio']");
		 	   tt.each(function(index, item){
				 if( item.checked ){
					 if(vid == 3) { $("#brandName").val($(this).data("name")); $("#brandId").val($(this).data("id")) } //品牌
					 if(vid == 4) { $("#supplierName").val($(this).data("name")); $("#supplierId").val($(this).data("id")) } //供应商
				 }
			   });
			  
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   })
	});
	
	//选择水印图片按钮
	$("#choosePicture").click(function(){
		var index = layer.open({
			   type: 2,
			   title: '请选择',
			   shadeClose: true,
			   area: ['30%', '80%'],
			   content: 'waterMark/chooseWatermarkList',
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //获取选择分类的值
				   var tt = layer.getChildFrame('body', index).find("table tbody tr.choose");
				   $("#waterImg").val(tt.data("path"));
				   $("#wmId").val(tt.data("id"));
				   layer.close(index); 
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		})
	})
	
	//保存商品
	$("#save").click(function (){
		$("#form").submit();
	})

});		
 </script>
</html>