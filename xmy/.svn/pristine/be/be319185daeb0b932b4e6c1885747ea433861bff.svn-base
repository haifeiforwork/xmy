layui.use(["pager","form","upload","laydate","layer"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate;
	var form = layui.form(), layer = layui.layer ;
		
	
	//根据推送目标 显示和隐藏 推送标签
	$("body").on('click',"#radio-target",function(){
		var targetVal = $("input[name='target']:checked").val();
		var $push_label_div = $("#push-label-item");
		if (targetVal == 0) {
			$push_label_div.hide();
		}
		if (targetVal == 1) {
			$push_label_div.show();
		}
	});
	//根据推送方式 显示和隐藏 推送时间
	$("body").on('click',"#radio-type",function(){
		var typeVal = $("input[name='type']:checked").val();
		var $push_time_div = $("#push-time-item");
		if (typeVal == 0) {
			$push_time_div.hide();
		}
		if (typeVal == 1) {
			$push_time_div.show();
		}
	});
	
	$("body").on("click","#push-broacast", function(){
		$.post("push/push",$("#pushForm").serialize(),function(data) {
			if (data.resultCode == 200) {
				tool.info("添加任务或推送成功！",8000);
			} else {
				tool.error("添加任务或推送失败！"+ data.data);
			}
		});
	});
	
});