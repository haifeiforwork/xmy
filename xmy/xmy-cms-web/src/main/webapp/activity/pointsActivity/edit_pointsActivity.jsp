<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改活动</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
	<fieldset class="layui-elem-field">
 		<legend>活动基本信息</legend>
	  	<form class="layui-form"  method="post" id="activityForm">
			<div class="layui-form-item" style="width: 50%">
			    <label class="layui-form-label">活动名称<i style="color:#F00">*</i></label>
			    <div class="layui-input-block">
			      <input type="text" id="name" name="name" lay-verify="name" value="${data.name }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			   	  
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
			<input type="hidden" id="actId" name="id" value="${data.id }">
			<input type="hidden" name="imgId" value="${data.imgId }">
			<input type="hidden" name="status" value="${data.status }">
		    <div class="layui-form-item">
			      <label class="layui-form-label">开始时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			        <input type="text" id="beginTime" name="beginTime" value="<fmt:formatDate value="${data.beginTime }" pattern="yyyy-MM-dd HH:mm"/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" lay-verify="begindate" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off" class="layui-input" >
			      </div>
		     </div>
			
			 <div class="layui-form-item">
			      <label class="layui-form-label">结束时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			        <input type="text" id="endTime"  name="endTime" value="<fmt:formatDate value="${data.endTime }" pattern="yyyy-MM-dd HH:mm"/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})" lay-verify="enddate" placeholder="YYYY-MM-DD hh:mm:ss" autocomplete="off" class="layui-input" >
			      </div>
		     </div>
			
			<div class="layui-form-item" style="width: 50%">
			    <label class="layui-form-label">水印图片</label>
			    <div class="layui-input-block">
			      <input type="text" name="imgPath" id="imgPath" lay-verify="imgPath" value="${data.imgPath }" autocomplete="off" placeholder="选择水印图片" class="layui-input" readonly="readonly">
			      <input type="hidden" name="imgId" id="imgId" value="${data.imgId }">
			      <label id="choosePicture" ><a >请选择<i style="color:#F00">*</i></a></label>	
			    </div>
			</div>
		</form>
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
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			  </colgroup>
			  <thead>
			    <tr>
			      <th>商品编号</th>
			      <th>商品名称</th>
			      <th>卖价(元)</th>
			      <th>供货商</th>
			      <th>品牌</th>
			      <th>前台分类</th>
			      <th>限购数量</th>
			      <th>总数量</th>
			      <th>交易完成量</th>
			      <th>积分</th>
			      <th>操作</th>
			    </tr> 
			  </thead>
			  <tbody id="content">
			  	<c:if test="${not empty goodsLit }">
				  	<c:forEach items="${goodsLit }" var="item">
				  		<tr data-id="${item.goodsId }">
				  			<td>${item.goodsCode }</td>
				  			<td>${item.goodsName }</td>
				  			<td>${item.goodsPrice }</td>
				  			<td>${item.supplierName }</td>
				  			<td>${item.brandName }</td>
				  			<td>${item.categoryName }</td>
				  			<td><input class="layui-input" type="text" id="limitNum" value="${item.limitNum }"/></td>
				  			<td><input class="layui-input" type="text" id="allNum" value="${item.allNum }" /></td>
				  			<td><input class="layui-input" type="text" id="completeNum" value="${item.completeNum }" /></td>
				  			<td><input class="layui-input" type="text" id="points" value="${item.points }" /></td>
				  			<td><a data-id="${item.goodsId }" id="del">移除</a></td>
				  		</tr>
				  	</c:forEach>
			  	</c:if>
			  </tbody>
			</table>		
		</div>
	</fieldset>
	<input type="button" id="save" class="layui-btn" value="保存"  />
 	</div>
 </div>
 <%@ include file="/commons/buttom.jsp"%>
</div>			
</body>
<script type="text/javascript">
layui.use(["form","upload","tool","layer","tree","laydate"], function(){
	 var form = layui.form(),layer = layui.layer,tool = layui.tool
	 ,$ = layui.jquery,laydate=layui.laydate;
	 var strPort=$("#port").val();
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
		form.render();
 	form.on("select(type)", function(data){
	 $("#typeName").val($(this).html());
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
	 
//选择商品	 	
$('#addGoods').on('click', function(){ 
   var idStr = "";
   var index = layer.open({
	   type: 2,
	   title: '请选择商品的分类',
	   shadeClose: true,
	   area: ['60%', '80%'],
	   content: 'pointsActivity/goodsList',
	   btn: ['确定', '取消'],
	   btnAlign: 'c',
	   yes:function(index, layero){  //获取选择分类的值
		   var body = layer.getChildFrame('body', index);
		   var tt = body.find("table input[type='checkbox']");
		   tt.each(function(index, item){
			 if( item.checked ){
				 if(typeof($(this).data("id")) != "undefined"){
					 idStr = idStr+$(this).data("id")+",";
				 }
			 }
		   });
		   addGoods(idStr);
		   layer.close(index); 
	   },
	   btn2:function(index, layero){
		   layer.close(index); 
	   }
   });
});

//添加商品
function addGoods(idStr){
 var html = ''; 
 $.ajax({
		url: "pointsActivity/findGoods",
		type: "POST",
		dataType: "JSON",
		data:{"idStr":idStr},
		async: false,
		success:function(data){
			 if(data.data.length > 0){
				for(var i = 0; i<data.data.length ; i++){
					var goods = data.data[i];
					html+= '<tr data-id="'+goods.id+'"><td>'+goods.code+'</td><td>'+goods.name+'</td><td>'+goods.price+'</td><td>'+goods.supplierName+'</td><td>'+goods.brandName+'</td><td>'+goods.categoryName+'</td><td><input class="layui-input" type="text" id="limitNum" value="0"  /></td><td><input class="layui-input" type="text" id="allNum" value="0" /></td><td><input class="layui-input" type="text" id="completeNum" value="0" /></td><td><input class="layui-input" type="text" id="points" value="0" /></td><td><a data-id="'+goods.id+'" id="del">移除</a></td></tr>';
				}
				$("#content").append(html);
				$("#tablediv").show();
				form.render();
			}
		}
	});  
}
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
//移除商品
$(document).on("click","tbody tr td #del",function (){
	var goodsId = $(this).data("id");
	var actId = $("#actId").val();
	$.ajax({
		url: "pointsActivity/delActivityGoods?goodsId="+goodsId+"&actId="+actId,
		type: "POST",
		dataType: "JSON",
		async: false,
		success:function(data){
		}	
	});
	$(this).parent().parent().remove();
});
//表单校验
function verify(){
	var flag = true ;
	var name = $("#name").val(), beginTime = $("#beginTime").val(), endTime = $("#endTime").val();
	if(name == '' || beginTime == '' || endTime == ''){flag = false;}
	return flag;
}

//修改
$("#save").click(function(){
    if(verify() == false){
		 tool.warning("必填项不能为空！");
	}else{ 
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"pointsActivity/updateActivity",
	        data:$("#activityForm").serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	           tool.error("修改时出错！");
	        },
	        success: function(data) {
	        	saveGoods(data.data);
	        }
	    }); 
	}  
});

var activityGoods = '' ,goodsId = '' , limitNum = '' , allNum = '' , completeNum = '' , points = '';
function saveGoods(actId){
	var child = $("table tbody tr");
	child.each(function(index, item){
		goodsId = $(this).data("id"), limitNum = $(this).find("#limitNum").val(), allNum = $(this).find("#allNum").val(), completeNum = $(this).find("#completeNum").val(), points = $(this).find("#points").val();
		activityGoods = goodsId+','+limitNum+','+allNum+','+completeNum+','+points;
		$.ajax({
			url: "pointsActivity/addActivityGoods",
			type: "POST",
			dataType: "JSON",
			data:{"activityId":actId,"pointsGoods":activityGoods},
			async: false,
			success:function(data){
			}	
		});
	});
	window.location.href="pointsActivity/activityList";
}

});

//保存商品


</script>
</html>