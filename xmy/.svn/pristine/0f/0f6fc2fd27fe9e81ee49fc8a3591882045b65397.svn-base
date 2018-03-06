require(["common"],function(){
	
	var orderId = $("#orderId").val() ;
	
	//后退
	$(".pull-left").click(function() {
		window.history.back(-1) ;
	});
	
	//加载信息
	$.ajax({
		data:{},
		dataType:"text",
		url:"order/logisticsContent/"+orderId,
		type:"post",
		async:false, 
		cache:false,
		success:function(data){
			console.info(data) ;
			$(".weui-loadmore").hide() ;
			$(".container").html(data) ;
		},
		/*error:function(error){
			$(".weui-loadmore").hide() ;
		}*/
		error:function(error){
			$(".weui-loadmore").hide() ;
			if(error.responseText == "SESSION_LOST"){
				$.alert("未登录或登录超时",function(){
					location.reload() ;
				}) ;
			}
			else if(error.responseText == "BUSINESS_EXCEPTION"){
				$.alert("系统内部繁忙",function(){
					location.reload() ;
				}) ;
			}else{
				$.alert(error.responseText,function(){
					location.reload() ;
				}) ;
			}
		}
	});
});