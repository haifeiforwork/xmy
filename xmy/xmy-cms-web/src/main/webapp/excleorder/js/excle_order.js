layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form() ;
	//console.log(tool.getFullDate(new Date())) ;
	//console.log(pager.reqSerialize("#myForm","id desc")) ;
	/*pager.load({
		url:"order/page",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render() ;
	}) ;
	form.on('checkbox(allChoose)', function(data){
	    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	    child.each(function(index, item){
	      item.checked = data.elem.checked;
	    });
	    form.render('checkbox');
	  });*/
	$(function(){
		//自定义验证规则
		form.verify({
			bginTime: function(value){
				if(value==""){
					return '开始时间不能为空';
				}
			},
			endTime: function(value){
				if(value==""){
					return '结束时间不能为空';
				}
			}
		});
	})
}) ;