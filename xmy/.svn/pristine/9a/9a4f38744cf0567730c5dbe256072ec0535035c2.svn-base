<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑分类</title>
</head>
 <%@ include file="/commons/comm_css.jsp"%>
<body>

<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
	  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<blockquote class="layui-elem-quote">分类基本信息</blockquote>
			<form class="layui-form" action="category/updateCategory"   method="post" id="form">
						<div class="layui-form-item">
						    <label class="layui-form-label">上级  </label>
						    <div class="layui-input-block" >
						     <select name="parentId" lay-filter="parentId" >
					     	 	<option value="0" <c:if test="${t.parentId == 0}">selected</c:if>>作为一级菜单</option>
						     	 <c:forEach items="${list }" var="item">
						      		<option value="${item.id }" <c:if test="${t.parentId==item.id }">selected</c:if>>${item.name }</option>
						      	 </c:forEach>  
						      </select> 
						 	</div>
			  			</div>
						<input type="hidden" name="id" id="cid"  value="${t.id }"  >
						<input type="hidden" name="vid"  value="${t.vid }"  >
						<input type="hidden" name="status"  value="${t.status }"  >
						<input type="hidden" name="parentId" id="parentId" value=""  >
						<div class="layui-form-item">
						    <label class="layui-form-label">分类名称</label>
						    <div class="layui-input-block">
						      <input type="text" name="name" lay-verify="name" value="${t.name }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						<div class="layui-form-item">
						    <label class="layui-form-label">分类编码</label>
						    <div class="layui-input-block">
						      <input type="text" name="sn" lay-verify="sn" value="${t.sn }"  autocomplete="off" placeholder="单行输入" class="layui-input">
						    </div>
						</div>
						<div class="layui-form-item layui-form-text">
						    <label class="layui-form-label">排序</label>
						   <div class="layui-input-block">
						      <input type="text" placeholder="值越小越靠前"  name="weight" value="${t.weight }"  lay-filter="weight" lay-verify="number" class="layui-input" >
						    </div>
						 </div>
						 
			  			<div class="layui-form-item" id="showDiv"  style="display: ${t.parentId != 0? 'none':'block' } ">
						    <label class="layui-form-label">是否首页显示</label>
						    <div class="layui-input-block">
						      <input type="checkbox"  lay-skin="switch" value="${t.isShow }"  class="isShow"  lay-filter="isShow" lay-text="ON|OFF" ${t.isShow ==0? 'checked':'' }>
						      <input type="hidden" name="isShow"  class="isShow" id="isShow"  value="${t.isShow }" >
						    </div>
						</div>
					
					<div class="layui-form-item">
						<div class="layui-input-inline" style="width: 400px;">
						    <label class="layui-form-label">图标上传</label>
							<div id="imgdiv" class="layui-input-block"  ><img id="pro_Preview" width="100" height="100" src="${not empty t.icon? t.icon:'goods/image/defaultImage.png' }" /></div>
							<div id="container"style="margin-left: 100px;">
								<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="icon" id="icon" value="${t.icon }">
							</div>
							<pre id="console"></pre>
						</div>
						
						<!-- app一级分类图标 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;display: ${t.parentId != 0? 'none':'block' }" >
						    <label class="layui-form-label">app图标上传</label>
							<div id="appImgdiv" class="layui-input-block"  ><img id="pro_Preview" width="100" height="100" src="${not empty t.appIcon? t.appIcon:'goods/image/defaultImage.png' }" /></div>
							<div id="container2"style="margin-left: 100px;">
								<a id="selectfiles2" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles2" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="appIcon" id="appIcon" value="${t.appIcon }">
							</div>
							<pre id="console"></pre>
						</div>
						
						<!-- app 一级分类选中图标 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;display: ${t.parentId != 0? 'none':'block' }" >
					    <label class="layui-form-label">app选中图标上传</label>
						<div id="appOnImgdiv" class="layui-input-block"  ><img id="pro_Preview" width="100" height="100" src="${not empty t.appOnIcon? t.appOnIcon:'goods/image/defaultImage.png' }" /></div>
						<div id="container3"style="margin-left: 100px;">
							<a id="selectfiles3" href="javascript:void(0);" class='btn'>选择文件</a>
							<a id="postfiles3" href="javascript:void(0);" class='btn'>开始上传</a>
							<input type="hidden" name="appOnIcon" id="appOnIcon" value="${t.appOnIcon }">
						</div>
						<pre id="console"></pre>
						</div>
						
						<!-- pc一级分类背景图 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;display: ${t.parentId != 0? 'none':'block' }">
						    <label class="layui-form-label">pc一级分类背景图上传</label>
							<div id="pcBackgroudImgdiv" class="layui-input-block"  ><img id="pro_Preview" width="100" height="100" src="${not empty t.pcBackgroudImg? t.pcBackgroudImg:'goods/image/defaultImage.png' }" /></div>
							<div id="container4"style="margin-left: 100px;">
								<a id="selectfiles4" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles4" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="pcBackgroudImg" id="pcBackgroudImg" value="${t.pcBackgroudImg }">
							</div>
							<pre id="console"></pre>
						</div>
						
						<!-- 一级分类描述图 -->
						<div class="layui-input-inline appIconDiv" style="width: 400px;display: ${t.parentId != 0? 'none':'block' }">
						    <label class="layui-form-label">分类描述图</label>
							<div id="descriptionImgDiv" class="layui-input-block"  ><img id="pro_Preview" width="100" height="100" src="${not empty t.descriptionImg? t.descriptionImg:'goods/image/defaultImage.png' }" /></div>
							<div id="container5"style="margin-left: 100px;">
								<a id="selectfiles5" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles5" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="descriptionImg" id="descriptionImg" value="${t.descriptionImg }">
							</div>
							<pre id="console"></pre>
						</div>
						
					</div>
					
					<div class="layui-form-item layui-form-text">
						    <label class="layui-form-label">描述</label>
						    <div class="layui-input-block">
						      <textarea placeholder="请输入内容" name="des"  class="layui-textarea">${t.des }</textarea>
						    </div>
					</div>
						
					<div class="layui-form-item appIconDiv" style="width: 400px;display:${t.parentId != 0? 'none':'block' } ">
					    <label class="layui-form-label">一级分类背景图跳转地址</label>
					   <div class="layui-input-block">
					      <input type="text" placeholder="输入背景图跳转链接"  name="backgroundImgUrl"  value="${t.backgroundImgUrl }"  class="layui-input" >
					    </div>
					</div>
			  			
		  			<div class="layui-form-item">
					    <div class="layui-input-block">
					      <button class="layui-btn" lay-submit="" lay-filter="form">保存</button>
					    </div>
					</div>
			</form>
		
		
			<!-- 规格分类 -->
			<div style="display: ${t.parentId == 0?  'none':'block'}" id="spec">
				<blockquote class="layui-elem-quote">分类规格信息      <button class="layui-btn" style="margin-left: 85%;" id="addspec" >添加规格</button></blockquote>
				<table class="layui-table"  >	
				<colgroup>
				   <col width="150">
				   <col width="150">
				   <col width="150">
				   <col width="150">
				   <col width="200">
				   <col width="150">
				</colgroup>
				<thead>
				   <tr>
				     <th>规格名称</th>
				     <th>规格项目</th>
				     <th>排序</th>
				     <th>是否搜索</th>
				     <th>是否显示</th>
				     <th>操作</th>
				   </tr> 
				</thead>
				<tbody id="content">
					<c:if test="${ slist != null}">
						<c:forEach items="${slist }" var="item">
							<tr>
								<td>${item.wordSeg }</td>
								<td>${item.val }</td>
								<td>${item.seq }</td>
								<td><i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">${item.isSearch == 0? '&#xe605;':'&#x1006;'}</i></td>
								<td><i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">${item.isShow == 0? '&#xe605;':'&#x1006;'}</i></td>
								<td>
									<button id="edit"  data-id="${item.id }"  class="layui-btn layui-btn-radius">编辑</button>
									<button id="del"  data-id="${item.id }"  class="layui-btn layui-btn-radius">删除</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<!-- 类别分词 -->
		<div style="display: ${t.parentId == 0?  'none':'block'}" id="wordSeg">
				<blockquote class="layui-elem-quote">类别分词信息      <button class="layui-btn" style="margin-left: 85%;" id="addWordSeg" >添加分词</button></blockquote>
				<table class="layui-table" width="700" id="wordSegTab" >
				<colgroup>
			      <col width="80">
			      <col width="300">
			      <col width="100">
			    </colgroup>
			    <thead>
			     <tr>
			        <th>分词名称</th>
			        <th>图片</th>
			        <th>操作</th>
			      </tr> 
			    </thead>
				<tbody >
				<c:if test="${not empty  wordSegList}">
					<c:forEach items="${wordSegList }" var="item">
						<tr>
							<td>${item.wordSeg }</td>
							<td><img alt="" src="${item.imgPath }" id="img"  width="50" height="50"></td>
							<td>
							<button id="edit"  data-id="${item.id }"  class="layui-btn layui-btn-radius">编辑</button>
							<button id="del"  data-id="${item.id }"  class="layui-btn layui-btn-radius">删除</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
		</div>
</div>
</div>
<!-- 底部 -->
<%@ include file="/commons/buttom.jsp"%>
<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="goods/category/js/categoryupload.js"></script>
</div>
</body>
<script type="text/javascript">
layui.use(["form","tool","upload"], function(){
	var layer = layui.layer,tool = layui.tool,$ = layui.jquery,form = layui.form() ;
	form.on("select(parentId)", function(data){ 
		 if(data.value != 0){
			 $("#showDiv").hide(); //是否首页显示
			 $("#isShow").val("");
			 $("#spec").show(); //添加规格
			 $("#wordSeg").show(); //添加分词
			 $(".appIconDiv").hide(); //一级分类app图标
		 }else{
			 $("#parentId").val(data.value);
			 $("#showDiv").show(); 
			 $("#spec").hide(); 
			 $("#wordSeg").hide();
			 $(".appIconDiv").show();
		 }
	});
	
	form.on("switch(isShow)", function(data){
	   	if(data.value == '1' ){$(".isShow").val(0)}
	   	else{ $(".isShow").val(1) }	
	});
	
	form.verify({  //表单校验
		name:function(value,item){
			if(!value){
				return "商品名称不能为空" ;
			}
		},
		sn:function(value,item){
			if(!value){
				return "商品编码不能为空" ;
			}
		}
	}) ;
	
	//监听提交
	form.on('submit(form)', function(data){
	   $("#form").submit();
	});
	
	var cid = $("#cid").val();
	//添加规格
	$("#addspec").click(function(){  
		layer.open({
	         type: 2
	        ,content: 'category/editSpec?cid='+cid
	        ,btn: ['确定', '取消']
	        ,btnAlign: 'c' //按钮居中
	        ,shade: 0 //不显示遮罩
		    ,area: ['600px', '50%']
	        ,yes: function(index, layero){
	        	var body = layer.getChildFrame('body', index) ,form = body.find("form"), name = body.find("form #name").val();
	        	if(name == ''){
	        		tool.warning("规格名称不能为空");
	        	}else{
	        		$.ajax({
	                    type: "POST",
	                    dataType: "JSON",
	                    url: "category/addSpec",
	                    data: form.serialize(),
	                    success: function (result) {
	                    	location.reload();
	                    }
	                });
	        	}
	        },cancel:function(){
	        	 layer.closeAll();
	        }
	      });
		
	});
	
	//分类规格
	function addRow(data){
		var html = '<tr><td>'+data.name+'</td><td>'+data.val+'</td><td>'+data.seq+'</td>'
			+ '<td>';
			if(data.isSearch == 0){
				html+= '<i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">&#xe605;</i>';
			}else{
				html+= '<i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>';
			}
			html+= '</td><td>';
			if(data.isShow == 0){
				html+= '<i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">&#xe605;</i>';
			}else{
				html+= '<i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>';
			}
			html+= '</td><td><button id="edit"  data-id="${item.id }"  class="layui-btn layui-btn-radius">编辑</button><button id="del"  data-id="${item.id }"  class="layui-btn layui-btn-radius">删除</button> </td></tr>';
	     return html;
	}
	
	//编辑规格
	$("#spec table tr td #edit").click(function(){
		var id = $(this).data("id") ;
		layer.open({
	         type: 2
	        ,content: 'category/editSpec?cid='+cid+'&id='+id
	        ,btn: ['确定', '取消']
	        ,btnAlign: 'c' //按钮居中
	        ,shade: 0 //不显示遮罩
		    ,area: ['600px', '50%']
	        ,yes: function(index, layero){
	        	var body = layer.getChildFrame('body', index) ,form = body.find("form"), name = body.find("form #name").val();
	        	if(name == ''){
	        		tool.warning("规格名称不能为空");
	        	}else{
	        		$.ajax({
	                    type: "POST",
	                    dataType: "JSON",
	                    url: "category/updateSpec",
	                    data: form.serialize(),
	                    success: function (result) {
	                    	location.reload() 
	                    }
	                });
	        	}
	        },cancel:function(){
	        	 layer.closeAll();
	        }
	      });
	})
	
	//删除规格
	$("#spec table tr td #del").click(function(){
		var id = $(this).data("id");
		$.ajax({
            type: "POST",
            dataType: "JSON",
            url: "category/deleteSpec?id="+id,
            success: function (result) {
            	if(result.data >0 ){
            		location.reload() 
            	}
            }
        });
	})
	
	//                                                   添加规格结束
	//添加分词
	$("#addWordSeg").click(function(){  
		layer.open({
	         type: 2
	        ,content: 'category/editWordSeg?cid='+cid
	        ,btn: ['确定', '取消']
	        ,btnAlign: 'c' //按钮居中
	        ,shade: 0 //不显示遮罩
		    ,area: ['600px', '40%']
	        ,yes: function(index, layero){
	        	var body = layer.getChildFrame('body', index) ,form = body.find("form"), name = body.find("form #name").val();
	        	if(name == ''){
	        		tool.warning("规格名称不能为空");
	        	}else{
	        		$.ajax({
	                    type: "POST",
	                    dataType: "JSON",
	                    url: "category/addWordSeg",
	                    data: form.serialize(),
	                    success: function (result) {
	                    	location.reload();
	                    }
	                });
	        	}
	        },cancel:function(){
	        	 layer.closeAll();
	        }
	      });
		
	});
	
	//编辑分词
	$("#wordSeg table tr td #edit").click(function(){
		var id = $(this).data("id") ;
		layer.open({
	         type: 2
	        ,content: 'category/editWordSeg?cid='+cid+'&id='+id
	        ,btn: ['确定', '取消']
	        ,btnAlign: 'c' //按钮居中
	        ,shade: 0 //不显示遮罩
		    ,area: ['600px', '50%']
	        ,yes: function(index, layero){
	        	var body = layer.getChildFrame('body', index) ,form = body.find("form"), name = body.find("form #name").val();
	        	if(name == ''){
	        		tool.warning("规格名称不能为空");
	        	}else{
	        		$.ajax({
	                    type: "POST",
	                    dataType: "JSON",
	                    url: "category/updateWordSeg",
	                    data: form.serialize(),
	                    success: function (result) {
	                    	location.reload() 
	                    }
	                });
	        	}
	        },cancel:function(){
	        	 layer.closeAll();
	        }
	      });
	})
	
	//删除分词
	$("#wordSeg table tr td #del").click(function(){
		var id = $(this).data("id");
		$.ajax({
            type: "POST",
            dataType: "JSON",
            url: "category/deleteWordSeg?id="+id,
            success: function (result) {
            	if(result.data >0 ){
            		location.reload() 
            	}
            }
        });
	})
	
})

</script>
</html>