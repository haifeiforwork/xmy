layui.use(["pager","form","upload","laydate","layer"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate;
	var form = layui.form(), layer = layui.layer ;
	
	//给用户添加标签-操作
	$("body").on("click",".push-label-user-add-btn", function(){
		var labelid = $(this).attr("data-labelid"),
		    userid = $(this).attr("data-userid"); 
		   $.post("push/userlabelList/add",{userid:userid,labelid:labelid},function(data){
		   if(data.resultCode == 200){
			   tool.info("添加成功！")
			   //window.location.href="push/userlabelList";
			   //layer.close(index); 
		   }else {
			   tool.error("添加失败！"+data.data);
		   }
	   });
	});
});