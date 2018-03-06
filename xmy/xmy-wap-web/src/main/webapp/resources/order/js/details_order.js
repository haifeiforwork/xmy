require(["common"],function(){
	
	$(function () {
        $(".foot-flex .weui-flex__item").click(function () {
            $(this).find(".tab-moadl").fadeToggle();
        });
        $(".esc-order").click(function () {
            $.confirm({
                title: '',
                text: '确定取消订单吗?',
                onOK: function () {
                    //点击确认
                },
                onCancel: function () {
                }
            });
        });
    });
	
	//一键拨号
	$(".call").click(function(){
		var now = new Date(),tell = "";
		time = now.getHours() * 60 * 60 * 1000 + now.getMinutes() * 60 * 1000 + now.getSeconds() * 1000;
		if(parseInt(time) >= 32400000 && parseInt(time) < 64800000 )
			tell = "tel:400-822-3936";
		if(parseInt(time) >= 64800000 || parseInt(time) < 32400000)
			tell = "tel:17723156790";
		location.href = tell;
	});
	
	//后退
	$(".pull-left").click(function(){
		window.location.href = document.referrer;
	});
	
    $(".tiket-pick").click(function () {
        $("#tiket-pop").popup();
    });
    
/*    $(".nav-list .nav-item").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var ind = $(".nav-list .nav-item").index(this);
        alert("${invoiceType}");
        $(".nav-content>div").eq(ind).show().siblings().hide();
    })*/
    
/*    $("body").on("click", ".check-circle", function() {
    	ticketKind = $(this).attr("value");
    	if($(this).attr("value") == "0")
    		$("#ticket-info").val("个人");
    	if($(this).attr("value") == "1")
    		$("#ticket-info").val("单位");
    	changeTicketInfo({'ticketKind' : ticketKind});
    });*/
    
/*    changeTicketInfo = function(data) {
    	id = $(".orderId").val();
    	data['id'] = id;
		$.ajax({
			url : 'order/modifyTicketInfo',
			data : data,
			type : 'post',
			dataType : 'json',
			error : function(msg) {
				$.alert("服务器繁忙" + msg);
			}
		});
    }*/
    
/*    $(".taxpayerNum").blur(function() {
    	if(companyName = $(".company-name").val())
    		if(taxpayerNum = $(".taxpayerNum").val()) {
    			$.alert("正在发单位信息和编号");
    			changeTicketInfo({'companyName' : companyName, 'taxpayerNum' : taxpayerNum});
    		}
    });*/
	
	
	$(".btn-wrap").each(function(){
		//为下面的每一个元素赋值
		$(this).children().click(function(){
			var this_obj = $(this),
				orderId = $(".orderId").attr("value"), 
				temp = this_obj.attr("value") ;
			//订单支付
			if(this_obj.hasClass("pay")){
				window.location.href = "order/payOrder/"+orderId;
			}
			if(this_obj.hasClass("cancel")) {
				$.confirm("确认取消订单？", function() {
					var orderId = {orderId : $(".orderId").attr("value"),url:window.location.href};
					    //点击确认后的回调函数
						$.commonAjax(orderId,"order/delete","json",function(data){
							//window.location.href = data.data;
							$(".order-status").html("订单已取消") ;
							$(".content").css("bottom","0px") ;
							$("footer").remove() ;
						}) ;
				});
			}
			//确认收货
			if(this_obj.hasClass("confirm")){
				var orderId = {orderId : this_obj.parents('.chunk').find('.oreder a').attr("value"),url:window.location.href};
				$.confirm("是否确认收货",function(){
					$.commonAjax(orderId,"order/confirmOrder","json",function(data){
						window.location.href = data.data;
					}) ;
				});
			}
			//查看物流
			if($(this).hasClass("look")){
				window.location.href = "order/logisticsOrder/"+orderId;
			}
		});
	});
	
	
});