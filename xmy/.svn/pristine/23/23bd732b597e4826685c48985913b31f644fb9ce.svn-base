layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ;
	$("#page").hide();
	/*console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;*/
	pager.load({
		url:"sys/list",//order/list
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
	}) ;
	$(function($){
		//添加用户
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
		});
		//href="sysUser/deleteSysUser/${user.id }"
	});
}) ;
//删除提示信息
function delet(userid){
	layui.use(["pager"],function(){
		var tool = layui.tool;
		tool.confirm("您确定要删除这个用户吗？点击确定后该用户下的所有角色也将被清空！",function(){
			window.location.href="sys/deleteSysUser/"+userid;
		}) ;
	})
}