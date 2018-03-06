require(["common"],function(){
	$(".refer").click(function(){
		var number = /^\d*$/, price = /^\d*\.?\d*$/ ;
		if(!$("#no").val()){
			$.alert("请输入订单号") ;
			return ;
		}else{
			if(!number.test($("#no").val())){
				$.alert("输入订单号不正确") ;
				$("#no").val("") ;
				return ;
			}
		}
		if(!$("#refundMoney").val()){
			$.alert("请输入退款金额") ;
			$("#refundMoney").val("") ;
			return ;
		}else{
			if(!price.test($("#refundMoney").val())){
				$.alert("输入退款金额不正确") ;
				return ;
			}
		}
		if(!$("#returnReason").val()){
			$.alert("请输入退款说明") ;
			return ;
		}
		var refundMoney={refundMoney:$("#refundMoney").val(),url:window.location.href,no:$("#no").val(),returnReason:$("#returnReason").val()};
		$.confirm("确认提交退款申请?",function(){
			$.commonAjax(refundMoney,"order/applyRefund","json",function(data){
				if(data.data == 1){
					$.alert("你的退款申请已经提交成功，我们将尽快为您处理",function(){
						location.href = "mine/center" ;
					});
				}else{
					$.alert("该订单不存在");
				}
			}) ;
		});
	})
	$(".pull-left").click(function(){
		window.location.href="gotoPage/mine/center";
	});
});