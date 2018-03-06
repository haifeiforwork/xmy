layui.use(["pager","form","upload","laydate","layer"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate;
	var form = layui.form(), layer = layui.layer ;

	
	pager.load({
		url:"push/labelList",//order/list
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render() ;
	}) ;
	
	
	//添加标签操作
	$("body").on("click","#add_label", function(){
		   var form = '<form id="AddForm">'
			              + '<div class="layui-form-item">'
							  + '<label class="layui-form-label">推送标签</label>'
							  + '<div class="layui-input-block">'
							  	+ '<textarea rows="3" cols="10" class="layui-textarea" id="push_label" ></textarea>'
							  + '</div>'
						  +	'</div>'
					  + '</form>'
		   var index = layer.open({
			   type: 1,
			   title: '添加推送标签',
			   shadeClose: true,
			   area: ['30%', '40%'],
			   content: form,
			   btn: ['确定添加', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //
				   var msg = $("#push_label").val();
				   try {
					   if (!msg) {
						   throw "请填写推送标签";
					   }
				   } catch (e) {
					  tool.error(e);
					  return;
				   }
				  
				   $.post("push/addLabel",{label:msg},function(data){
					   if(data.resultCode == 200){
						   tool.info("添加成功！")
						   window.location.href="push/labelList";
						   layer.close(index); 
					   }else {
						   tool.error("添加失败！"+data.data);
					   }
				   });
				   
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
		});
	//删除
	$("body").on("click",".push-label-del-btn", function(){
		var labelid = $(this).attr("data-labelid");
		tool.confirm("您确定要删除这个标签吗？",function(){
			$.get("push/delLabel",{id:labelid},function(data){
				if(data.resultCode == 200){
					   tool.info("删除成功！")
					   window.location.href="push/labelList";
					   layer.close(index); 
				   }else {
					   tool.error("删除失败！"+data.data);
				   }
			});
		}) ;
	});
	
	//修改标签操作
	$("body").on("click",".push-label-modify-btn", function(){
		   var labelOld = $(this).attr("data-labelname");
		   var labelid = $(this).attr("data-labelid");
		   
		   var form = '<form id="AddForm">'
			              + '<div class="layui-form-item">'
							  + '<label class="layui-form-label">推送标签</label>'
							  + '<div class="layui-input-block">'
							  	+ '<textarea rows="3" cols="10" class="layui-textarea" id="push_label"  >'+labelOld+'</textarea>'
							  + '</div>'
						  +	'</div>'
					  + '</form>'
		   var index = layer.open({
			   type: 1,
			   title: '修改推送标签',
			   shadeClose: true,
			   area: ['30%', '40%'],
			   content: form,
			   btn: ['修改', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //
				   var msg = $("#push_label").val();
				   try {
					   if (!msg) {
						   throw "请填写推送标签";
					   }
				   } catch (e) {
					  tool.error(e);
					  return;
				   }
				  
				   $.post("push/modifyLabel",{id:labelid,label:msg},function(data){
					   if(data.resultCode == 200){
						   tool.info("修改成功！")
						   window.location.href="push/labelList";
						   layer.close(index); 
					   }else {
						   tool.error("修改失败！"+data.data);
					   }
				   });
				   
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
		});
}) ;


