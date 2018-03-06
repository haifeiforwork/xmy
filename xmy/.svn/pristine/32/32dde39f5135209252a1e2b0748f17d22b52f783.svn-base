layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form() ;
	//console.log(tool.getFullDate(new Date())) ;
	//console.log(pager.reqSerialize("#myForm","id desc")) ;
	pager.load({
		url:"order/finish",
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
	  });
	//修改成功提示
	$(function($){
		if($("#message").val()!=""){
			tool.info($("#message").val());
		}
	})
	//批量操作
	$("#btnAubit").click(function(){
		var orders="0";
		var sel=$("#sel");
		$(".orderid").each(function(index,item){
			if(item.checked){
				orders+=","+item.value;
			}
		});
		if(orders=="0" || sel.val()==""){
			tool.warning("您未选中要完成的订单，或者没有选这操作类型！")
			return false;
		}else if (orders!="0" && sel.val()!=""){
			tool.operInfo("您确认要批量完成改批订单吗？",function(){
				window.location.href="order/updateAllStatus/"+orders+"/"+sel.val();
			})
		}
	})
}) ;