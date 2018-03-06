layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate , form = layui.form();
	form.render();
	$(function($){
		$(".roleId").each(function(index,item){
			$(".check").each(function(index,xx){
				if(item.value==xx.value){
					$(xx).attr("checked",true);
				}
			})
		})
		$("#btn").click(function(){
			//遍历角色的多选框 如果选中就取出他的value放入页面隐藏的文本框中
			var rolesid = "0";
			$(".check").each(function(index,xx){
				if(xx.checked){
					rolesid+=","+xx.value;
				}
			})
			//放入文本框
			$("#roleids").val(rolesid);
			
			//判断是否启用
			if($(".chstatus")[0].checked){
				$("#status").val(0);
			}else{
				$("#status").val(1);
			};
			$("#myForm").submit();
		})
	})
}) ;