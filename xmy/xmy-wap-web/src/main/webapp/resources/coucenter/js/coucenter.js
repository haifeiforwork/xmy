require(["common"],function(){
	
	//后退
	$(".pull-left").click(function(){
		location.href = "home/home" ;
	}) ;
	
/*	$(".col-33").click(function() {
		location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id");
	});*/
	
	//立即领取
	$(".get-coupon").click(function(){
		
		if($(this).hasClass("use")) {
			location.href = "home/home";
			return;
		}
		
		var that = $(this);
		var data = {id : $(this).attr("coupon-id")} ;
		$.commonAjax(data,"coucenter/getCoupon","json",function(sum){
			//$.alert(sum.data.map);
			if(sum.resultCode == 200) {
				$.alert("领取成功", function() {
					history.go(0);
				});
				
			}
			if(sum.resultCode == -99) {
				$.alert(sum.data, function() {
					history.go(0);
				});
			}
		}) ;
	}) ;
});
