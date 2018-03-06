require(["common"],function(){
	
	//后退
	$(".pull-left").click(function(){
		location.href = "home/home" ;
	}) ;
	
	//查看商品详情
	$(".container").on("click",".goods-div",function(){
		if($.isPutaway($(this).attr("goods-id"))){
			$.alert("该商品已下架") ;
			return ;
		}
		location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") ;
	}) ;
	
});
