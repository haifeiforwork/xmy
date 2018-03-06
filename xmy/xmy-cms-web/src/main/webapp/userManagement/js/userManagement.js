layui.use(["pager","form",],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	pager.load({
		url:"userManagement/userList",
		sort:"create_time desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	}) ;
	pager.formRest() ;//重置按钮
	
	//全选 监听
	form.on('checkbox(allChoose)', function(data){
     	var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	     child.each(function(index, item){
	       item.checked = data.elem.checked;
	       console.log($(this).data("id"))
	     });
     	form.render('checkbox');
   });

	
});
