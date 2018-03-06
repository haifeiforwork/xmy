require(["common"],function(){
	
	//初始化加载商品
	loadActivityGoods();
	
	//后退
	$(".pull-left").click(function(){
		location.href = "home/home" ;
	}) ;
	
	//tab切换
    var tabItem =  $(".tab-nav .weui-flex__item")
    $(".vip .shop-list:gt(0)").hide();
    tabItem.click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        loadActivityGoods() ;
    })//切换结束
    
    //签到
    $(".sign").click(function(){
    	$.commonAjax({},"vipcenter/sign","json",function(data){
    		$(".sign").removeClass("sign").html("已签到") ;
    		$.alert(data.data, function() {
    			location.href = "vipcenter/vipcenter";
    		}) ;
    	}) ;
    }) ;
    
    //加载活动商品
    function loadActivityGoods(){
    	var data = {pointsId : $(".activity-div").filter(".active").attr("points-id")} ;
    	$.commonAjax(data,"vipcenter/vipcenterGoods","text",function(data){
    		$(".vip").html(data) ;
    	}) ;
    }
    
    //查看商品详情
    $(".vip").on("click",".goods-div",function(){
    	location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") + "&pointsId=" + $(this).attr("points-id") ;
    });
    
    //跳转会员权益页面
    $(".vip_prop").click(function(){
    	location.href = $(this).attr("url") ;
    }) ;
}) ;
