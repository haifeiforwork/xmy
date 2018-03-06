layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form() ;
/*	console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;*/
	pager.load({
		url:"order/prepare",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render() ;
	}) ;
	$(function($){
		if($("#message").val()!=""){
			tool.info($("#message").val());
		}
	})
	form.on('checkbox(allChoose)', function(data){
	    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	    child.each(function(index, item){
	      item.checked = data.elem.checked;
	    });
	    form.render('checkbox');
	  });
	//批量操作
	$("#btnPre").click(function(){
		var orders = "0";
		var sel = $("#sel");
		$(".orderid").each(function(index,item){
			if(item.checked){
				orders += ","+item.value;
			}
		});
		if(orders=="0" || sel.val()==""){
			tool.warning("您未选中要批量备货的订单，或者没有选这操作类型！")
			return false;
		}else if (orders != "0" && sel.val() == 5){
			tool.operInfo("您确认要批量备货改批订单吗？",function(){
				window.location.href="order/updateAllStatus/"+orders+"/"+sel.val();
			})
		}else if(orders != "0" && sel.val() == "print"){
			tool.operInfo("您确认要批量打印改批订单的客户清单吗？",function(){
				window.location.href="order/print/"+orders;
			})
		}
	})
}) ;