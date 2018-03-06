layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form() ;
	/*console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;*/
	pager.load({
		url:"order/delivery",
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
	form.on('select', function(data){
		  //console.log(data.value); //得到被选中的值
		  //$("#send").val("亲：您的订单将于今日配送，配送人："+data.value+"，请保持电话畅通，如有疑问，请及时与配送人联系。祝您购物愉快！【香满园】");
		  //tool.info(data.elem.name);
		  $('#sel option').each(function () {
			    var $option = $(this);
			    var html = $option.attr("data");
			    var value = $option.val();
			    if(data.value==value){
			    	$("#send").val("亲：您的订单将于今日配送，配送人："+html+"，请保持电话畅通，如有疑问，请及时与配送人联系。祝您购物愉快！【香满园】");
			    	return false;
			    }
			});
	});
	//修改成功提示
	$(function($){
		if($("#message").val()!=""){
			tool.info($("#message").val());
		}
	})
	//批量操作
	$("#delivery").click(function(){
		var orders="0";
		var sel=$("#sel");
		$(".orderid").each(function(index,item){
			if(item.checked){
				orders+=","+item.value;
			}
		});
		$("#or").val(orders);
		if(orders=="0" || sel.val()==""){
			tool.warning("您未选中要发货的订单，或者没有选择配送人！")
			return false;
		}else if (orders!="0" && sel.val()!=""){
			form.on('submit',function(){
			});
		}
	});
	
	//批量操作不发送短信
	$("#btn1").click(function(){
		$("#isSendSMS").val(0);
		var orders="0";
		var sel=$("#sel");
		$(".orderid").each(function(index,item){
			if(item.checked){
				orders+=","+item.value;
			}
		});
		$("#or").val(orders);
		if(orders=="0" || sel.val()==""){
			tool.warning("您未选中要发货的订单，或者没有选择配送人！")
			return false;
		}else if (orders!="0" && sel.val()!=""){
			
			
			form.on('submit',function(){
			});
		}
	});
	
}) ;