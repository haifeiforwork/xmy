layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate;
	var form = layui.form();
	$("#page").hide();
	console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;
	pager.load({
		url:"sysUser/sysRoleList",//order/list
		pages:$("#page").val()
	},pager.reqSerialize("#myForm","id desc"),function(data){
		$("#mytable").html(data) ;
	}) ;
	
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
}) ;
//删除角色的提示
function del(id){
	layui.use(["pager"],function(){
		var tool = layui.tool;
		tool.confirm("您确定要删除这个角色吗？点击确定后该角色下的所有菜单也将被清空！",function(){
			window.location.href="sysUser/deleteRole/"+id;
		}) ;
	})
}