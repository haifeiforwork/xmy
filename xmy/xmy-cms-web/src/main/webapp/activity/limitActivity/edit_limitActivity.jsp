<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加活动</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
	<form class="layui-form" action="limitActivity/updateActivity" method="post" id="activityForm">
	<fieldset class="layui-elem-field">
 		<legend>活动基本信息</legend>
			<div class="layui-form-item">
			    <label class="layui-form-label">活动名称<i style="color:#F00">*</i></label>
			    <div class="layui-input-block">
			      <input type="text" id="name" name="name" lay-verify="name" value="${data.name }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			   	  
			    </div>
			</div>
			
			<input type="hidden" id="actId" name="id" value="${data.id }">
			<input type="hidden" name="imgId" value="${data.imgId }">
			<input type="hidden" name="status" value="${data.status }">
			<input type="hidden" name="type1" value="${data.type }">
			
			<div class="layui-form-item">
			    <label class="layui-form-label">促销类型</label>
			    <div class="layui-input-inline" >
			      <select  lay-filter="type" name="type" id="activityType" disabled="true">
			        <option value="1" ${data.type == '1'? 'selected':'' } >冰点价</option>
			        <option value="2" ${data.type == '2'? 'selected':'' } >天天特价</option>
			        <option value="3" ${data.type == '3'? 'selected':'' } >每周特价</option>
			        <option value="4" ${data.type == '4'? 'selected':'' } >整件惠</option>
			        <option value="5" ${data.type == '5'? 'selected':'' } >专题促销</option>
			      </select>
			      <input type="hidden" id="typeName" name="typeName" value="${data.typeName }" >
			 	</div>
  			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">端口</label>
			    <div class="layui-input-block" id="selectPort">
			      <input type="checkbox" class="my-port" id="my-port-pc" title="PC"  >
			      <input type="checkbox" class="my-port" id="my-port-wap" title="WAP" >
			      <input type="checkbox"  class="my-port" id="my-port-app" title="APP" >
			    </div>
			    <input type="hidden" name="port" id="port" value="${data.port}"/>
  			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">参与条件</label>
			    <div class="layui-input-block" id="selectJoinCondition">
			      <input type="checkbox"  value="1" id="vip" class="joinCondition" title="会员">
			      <input type="checkbox"  value="2" id="anonymous" class="joinCondition" title="匿名">
			    </div>
			      <input type="hidden" name="joinCondition" value="${data.joinCondition}" id="joinCondition"/> 
  			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">限购数量</label>
			    <div class="layui-input-block">
			      <input type="text" name="limitNum"  lay-verify="limitNum" value="${data.limitNum }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="timeDiv" style="display:${data.type =='5'?'':'none'}">
		     <div class="layui-inline">
			      <label class="layui-form-label">开始时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			        <input type="text" id="beginTime" value="<fmt:formatDate value="${data.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="beginTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="begindate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
			      </div>
		     </div>
			 <div class="layui-inline">
			      <label class="layui-form-label">结束时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			        <input type="text" id="endTime" value="<fmt:formatDate value="${data.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="endTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="enddate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
			      </div>
		     </div>
		     </div>
<%-- 		   
		    <div class="layui-inline">
			      <label class="layui-form-label">开始时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			        <input type="text" id="beginTime" name="beginTime" value="<fmt:formatDate value="${data.beginTime }" pattern="yyyy-MM-dd HH:mm"/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" lay-verify="begindate" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off" class="layui-input" >
			      </div>
		     </div>
			
			 <div class="layui-inline">
			      <label class="layui-form-label">结束时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			        <input type="text" id="endTime"  name="endTime" value="<fmt:formatDate value="${data.endTime }" pattern="yyyy-MM-dd HH:mm"/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" lay-verify="enddate" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off" class="layui-input" >
			      </div>
		     </div> --%>
		     
		    <div class="layui-form-item">
			    <label class="layui-form-label">活动图标</label>
			     <div id="imgdiv" class="layui-input-block"  ><img width="100" height="100" src="${not empty data.icon? data.icon : 'goods/image/defaultImage.png' }" /></div>
					<div id="container"style="margin-left: 100px;">
						<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
						<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
						<input type="hidden" name="icon" id="iconImg" value="${data.icon }">
					 </div>
			</div>
		   
		   	<div class="layui-form-item">
			    <label class="layui-form-label">活动描述</label>
			    <div class="layui-input-block">
			      <input type="text" style="width:40%;"  name="description"  value="${data.description }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">水印图片</label>
			   <div class="layui-input-block">
			      <input type="text" name="imgPath" id="imgPath" lay-verify="imgPath" value="${data.imgPath }" autocomplete="off" placeholder="选择水印图片" class="layui-input" readonly="readonly">
			      <input type="hidden" name="imgId" id="imgId" value="${data.imgId }">
			      <label id="choosePicture" ><a >请选择<i style="color:#F00">*</i></a></label>	
			    </div>
			</div>
	</fieldset>
	<fieldset class="layui-elem-field">
		<legend>促销商品</legend>
		<div>
			 <div class="layui-input-inline" style="margin-left: 85%;">
				<input type="button" class="layui-btn" id="addGoods" value="添加商品"  />
			 </div>
		</div>
		<div id="tablediv" class="layui-form"  style="display: ${not empty goodsLit? 'block':'none' };">
			<table class="layui-table" >
			 <colgroup>
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="150">
			    <col style="display: ${data.type =='5'?'none':''}" width="150">
			    <col width="80">
			    <col width="80">
			    <col width="80">
			    <col width="80">
			    <col width="80">
			  </colgroup>
			  <thead>
			    <tr>
			      <th>商品编号</th>
			      <th>商品名称</th>
			      <th>卖价(元)</th>
			      <th>供货商</th>
			      <th>品牌</th>
			      <th>前台分类</th>
			      <th style="display: ${data.type =='5'?'none':''}">商品活动日期</th>
			      <th>促销单价</th>
			      <th>用户限购数量</th>
			      <th>总数量</th>
			      <th>交易完成量</th>
			      <th>操作</th>
			    </tr> 
			  </thead>
			  <tbody id="content">
			  	<c:if test="${not empty goodsLit }">
				  	<c:forEach items="${goodsLit }" var="item">
				  		<tr data-id="${item.goodsId }">
				  			<td><input type="hidden" name="goodsId" value="${item.goodsId }">${item.goodsCode }</td>
				  			<td>${item.goodsName }</td>
				  			<td>${item.goodsPrice }</td>
				  			<td>${item.supplierName }</td>
				  			<td>${item.brandName }</td>
				  			<td>${item.categoryName }</td>
				  			<td style="display: ${data.type =='5'?'none':''}"><input type="text" id="activityTime" name="activityTime" value="<fmt:formatDate value="${item.activityTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" pattern="yyyy-MM-dd HH:mm:ss"  placeholder="YYYY-MM-DD hh:mm:ss"  /></td>
				  			<td><input type="text" id="price" name="price" value="${item.price }"  /></td>
				  			<td><input type="text" id="limitNum" name="limNum" value="${item.limitNum }" /></td>
				  			<td><input type="text" id="allNum" name="allNum" value="${item.allNum }" /></td>
				  			<td><input type="text" readonly="readonly" id="completeNum" name="completeNum" value="${item.completeNum }" /></td>
				  			<td><a data-id="${item.goodsId }" id="del">移除</a></td>
				  		</tr>
				  	</c:forEach>
			  	</c:if>
			  </tbody>
			</table>		
		</div>
	</fieldset>
	</form>
	<input type="button" id="save" lay-submit=""  class="layui-btn" value="保存"  />
 	</div>
 </div>
 <%@ include file="/commons/buttom.jsp"%>
 <script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="advertisement/js/containerImgUpload.js"></script>
</div>			
</body>
<script type="text/javascript">
layui.use(["form","upload","tool","layer","tree","laydate"], function(){
	 var form = layui.form(),layer = layui.layer,tool = layui.tool
	 ,$ = layui.jquery,laydate=layui.laydate;
	 var strPort=$("#port").val();
	var strJoinCondition=$("#joinCondition").val();
	//端口拼装字段
	if(strPort.indexOf("1")!=-1)
	{
		$("#my-port-pc").attr("checked","true");
	}
	if(strPort.indexOf("2")!=-1)
	{
		$("#my-port-wap").attr("checked","true");
	}
	if(strPort.indexOf("3")!=-1)
	{
		$("#my-port-app").attr("checked","true");
	}
	//条件拼装
	if(strJoinCondition.indexOf("1")!=-1)
	{
		$("#vip").attr("checked","true");
	}
	if(strJoinCondition.indexOf("2")!=-1)
	{
			$("#anonymous").attr("checked","true");
	}
	form.render();
form.on("select(type)", function(data){
	 $("#typeName").val($(this).html());
});	
var type="${data.type}";
form.verify({ 
	name:function(value,item){
		if(!value){
			return "活动不能为空" ;
		}
	},
	begindate:function(value,item){
		if(type==5&&!value){
			return "活动开始时间不能为空" ;
		}
	},
	enddate:function(value,item){
		if(type==5&&!value){
			return "活动结束时间不能为空" ;
		}
	} 
}) ; 


//广告图标上传
layui.upload({
   url: "fileUpload/uploadImage" //上传接口
   ,success: function(res){ //上传成功后的回调
   	 var result = res.data;
      console.log(result.success);
      if(result.success == true){
    	  $("#pro_Preview").attr('src',result.filePath);
    	  $("#icon").val(result.filePath);
      }else{
    	  tool.warning(result.msg)
      }
   }
});
 
	
//选择端口
 $("#selectPort").click(function(){
 	var myPort=$("#selectPort").find(".my-port");
 	var strPort="";
 	myPort.each(function(index,item)
 	{
 		
 		if(item.checked==true)
 			strPort+=(index+1)+",";//index默认是从0开始，但是数据库设置的是从1开始
 	});
 	$("#port").val(strPort);//重新设置隐藏的端口字段值
 })
 //选中条件
 $("#selectJoinCondition").click(function(){
 	var sleectJoinCondition=$("#selectJoinCondition").find(".joinCondition");
 	var strJoinCondition="";
 	sleectJoinCondition.each(function(index,item)
 	{
 		
 		if(item.checked==true)
 			strJoinCondition+=(index+1)+",";//index默认是从0开始，但是数据库设置的是从1开始
 	});
 	$("#joinCondition").val(strJoinCondition);//重新设置隐藏的条件字段值
 })
 
 
//选择商品	 	
$('#addGoods').on('click', function(){ 
   var idStr = "";
   var index = layer.open({
	   type: 2,
	   title: '请选择商品的分类',
	   shadeClose: true,
	   area: ['60%', '80%'],
	   content: 'limitActivity/goodsList',
	   btn: ['确定', '取消'],
	   btnAlign: 'c',
	   yes:function(index, layero){  //获取选择分类的值
		   var body = layer.getChildFrame('body', index);
		   var tt = body.find("table input[type='checkbox']");
		   tt.each(function(index, item){
			 if( item.checked ){
				 idStr = idStr+$(this).data("id")+",";
			 }
		   });
		   addGoods(idStr);
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
			   $("#imgPath").val(tt.data("path"));
			   $("#imgId").val(tt.data("id"));
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	})
})

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
					var type=$('#activityType option:selected').val();
					var goods = data.data[i];
					if(type==5){
						html+= '<tr data-id="'+goods.id+'"><td><input type="hidden" name="goodsId" value="'+goods.id+'">'+goods.code+'</td>'
						+  '<td>'+goods.name+'</td><td>'+goods.price+'</td><td>'+goods.supplierName+'</td>'
						+  '<td>'+goods.brandName+'</td><td>'+goods.categoryName+'</td>'
						+  '<td><input type="text" name="price" id="limitNum" value="0" /></td>'
						+   '<td><input type="text" name="limNum" id="price" value="0"  /></td>'
						+  '<td><input type="text" name="allNum" id="allNum" value="0" /></td>'
						+  '<td><input type="text" readonly="readonly" name="completeNum" id="completeNum" value="0" /></td>'
						+  '<td><a data-id="'+goods.id+'" id="del">移除</a></td></tr>';
					}else{
						html+= '<tr data-id="'+goods.id+'"><td><input type="hidden" name="goodsId" value="'+goods.id+'">'+goods.code+'</td>'
						+  '<td>'+goods.name+'</td><td>'+goods.price+'</td><td>'+goods.supplierName+'</td>'
						+  '<td>'+goods.brandName+'</td><td>'+goods.categoryName+'</td>'
						+  '<td><input type="text" name="activityTime" id="activityTime" onclick="layui.laydate({elem: this, istime: true, format: \'YYYY-MM-DD hh:mm:ss\'})" pattern="YYYY-MM-DD hh:mm:ss"  placeholder="yyyy-mm-dd hh:mm:ss" autocomplete="off" class="layui-input" ></td>'
						+  '<td><input type="text" name="price" id="limitNum" value="0" /></td>'
						+   '<td><input type="text" name="limNum" id="price" value="0"  /></td>'
						+  '<td><input type="text" name="allNum" id="allNum" value="0" /></td>'
						+  '<td><input type="text" readonly="readonly" name="completeNum" id="completeNum" value="0" /></td>'
						+  '<td><a data-id="'+goods.id+'" id="del">移除</a></td></tr>';
					}
				
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
	var goodsId = $(this).data("id");
	var actId = $("#actId").val();
	$.ajax({
		url: "limitActivity/delActivityGoods?goodsId="+goodsId+"&actId="+actId,
		type: "POST",
		dataType: "JSON",
		async: false,
		success:function(data){
		}	
	});
	$(this).parent().parent().remove();
})


//保存
$("#save").click(function(){
	$("#activityForm").submit();
})



});

//保存商品


</script>
</html>