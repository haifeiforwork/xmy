layui.use(["pager","form","upload","laydate","layer"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate;
	var form = layui.form(), layer = layui.layer ;

	//添加推送按钮
	$("body").on("click","#btnAdd", function(){
		layer.open({
		    type: 2,
		    title: '推送消息',
			shadeClose: true,
			area: ['60%', '90%'],
		    content: "push/pushindex?task=true", 
		    btn: ['关闭'],
			btnAlign: 'c',
			yes:function(index, layero){ 
				   layer.close(index); 
				   window.location.href="push/list";
			   }
		});
	});
	//删除
	$("body").on("click",".del-btn", function(){
		var jobName = $(this).attr("data-jobName");
		var triggerName = $(this).attr("data-triggerName");
		tool.confirm("您确定要删除这个定时推送吗？",function(){
			$.get("push/del",{jobName:jobName,triggerName:triggerName},function(data){
				if(data.resultCode == 200){
					   tool.info("删除成功！")
					   window.location.href="push/list";
					   layer.close(index); 
				   }else {
					   tool.error("删除失败！"+data.data);
				   }
			});
		}) ;
	});
}) ;


