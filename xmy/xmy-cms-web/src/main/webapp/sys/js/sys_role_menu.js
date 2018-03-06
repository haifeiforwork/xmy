layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate , form = layui.form();
	form.render();
	$(function($){
		$(".oldmenuSn").each(function(index,item){
			$(".menusn").each(function(index,xx){
				if(item.value==xx.value){
					$(xx).attr("checked",true);
				}
			});
		});
		$("#btn").click(function(){
			//遍历cekeckbox
			var menus="0";
			$(".menusn").each(function(index,item){
				if(item.checked){
					menus+=","+item.value;
				}
			});
			//添加到隐藏的文本框中
			$("#menusid").val(menus);
			
			if($(".chstatus")[0].checked){
				//启用
				$("#status").val(0);
			}else{
				//禁用
				$("#status").val(1);
			};
			$("#myForm").submit();
			
		});
		
	})
}) ;