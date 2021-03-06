require(["common"],function(){
	
	$(".activity").click(function(){
		window.location.href="activity/findActivity";
	});
	$(".border").click(function(){
		window.location.href="border/findBorderGoodsAll";
	});
	$(".fruits").click(function(){
		window.location.href="fruits/findFruits";
	});
	//初始化加载底部公共JS
	$.initFooterJS() ;
	
	//加载图片
	if($(".home_img").attr("url")){
		$(".home_img").attr("src",$(".home_img").attr("url").replace(".png","_app.png"))
	}
/*	$(function() {
		$.cookie("isBrowsedDownload", "1", {'path' : '/'});
	});*/
	
	//点击下载
	$(".download").click(function(){
		location.href = "home/download";
	});
	
	//今日秒杀
	limitTime() ;
	
	//客服电话
	$(".head-phone").click(function(){
		var now = new Date(),tell = "";
		time = now.getHours() * 60 * 60 * 1000 + now.getMinutes() * 60 * 1000 + now.getSeconds() * 1000;
		if(parseInt(time) >= 32400000 && parseInt(time) < 64800000 )
			tell = "tel:400-822-3936";
		if(parseInt(time) >= 64800000 || parseInt(time) < 32400000)
			tell = "tel:17723156790";
		location.href = tell;
	}) ;
	
	function limitTime(){
		var leftTime, now = new Date(), endDate = new Date() ;
		if($("#nowTime").val() && $("#endTime").val()){
			leftTime = $("#endTime").val() - $("#nowTime").val() ;
		}else{
			if(endDate.getHours()>=12){
				var oneDay =  1000*60*60*24;
				endDate=new Date(endDate+oneDay);//明天的时间
				endDate.setHours(12, 0, 0, 0);
			}else{
				endDate.setHours(12, 0, 0, 0);
			}
			leftTime = endDate.getTime()-now.getTime();
		}
		var inter = window.setInterval(function(){
			if(leftTime <= 0){
				window.clearInterval(inter) ;
			} else {
				leftTime -= 1000 ;
				var leftsecond = parseInt(leftTime/1000); 
				var day1=Math.floor(leftsecond/(60*60*24)); 
				var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
				var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
				var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
				$(".time-esc").html('<span class="hours">'+ (hour >= 10 ? hour : ("0"+hour)) +'</span> : <span class="miu">'+ (minute >= 10 ? minute : ("0"+minute)) +'</span> : <span class="s">'+  (second >= 10 ? second : ("0"+second)) +'</span>');
			}
		}, 1000);
	}
	
	//加载更多
	$(".content").on("click",".more",function(){
/*		if($(this).attr("level") == 1) {
			location.href = "goods/searchGoodsByTop?oneId=" + $(this).attr("category_id") ;
			return;
		}
		location.href = "goods/searchGoods?typeId=" + $(this).attr("category_id") ;*/
		
		location.href = "goods/searchGoods/" + $(this).attr("category_id") ;
		
	});
	
	//广告图片link
	$(".content").on("click",".img-link",function(){
		if($(this).attr("type") == 0){
			if($.isPutaway($(this).attr("data"))){
				$.alert("该商品已下架") ;
				return ;
			}
			location.href = "goods/goodsInfo?goodsId=" + $(this).attr("data") ;
			return ;
		}
		if($(this).attr("type") == 1){
			location.href = $(this).attr("data");
			return ;
		}
	}) ;
	
	//专题链接
	$(".content").on("click",".top-img",function(){
		if($(this).attr("type") == 0){
			location.href = "goods/goodsInfo?goodsId=" + $(this).attr("data") ;
		}else{
			location.href = $(this).attr("data") ;
		}
	}) ;
	
	//进入商品详情
	$(".content").on("click",".goods-div",function(){
		if($.isPutaway($(this).attr("goods-id"))){
			$.alert("该商品已下架") ;
			return ;
		}
		location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") ;
	});
	
	//今日秒杀
	$(".content").on("click",".goods-activity-div",function(){
		location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") + "&activityId=" + $(this).attr("activity-id") + "&activityType=2" ;
	}) ;
	
	$(".swiper-container").swiper({
        autoplay: 2000,
        speed: 400,
        spaceBetween: 100,
        pagination: '.swiper-pagination',
        paginationClickable: true
        
    });
	
	//会员中心
	$(".vipCenter").click(function() {
		location.href = "vipcenter/vipcenter";
	});
	
	//搜索商品
	$(".search").click(function(){
		location.href = "goods/searchGoodsByName" ;
	}) ;
	
	//新品尝鲜
	$(".newGoods").click(function(){
		location.href = "goods/searchGoodsByName?orderMethod=4" ;
	}) ;
	
	//全国配送
	$(".allPost").click(function(){
		location.href = "goods/searchGoodsByName?mianLand=0" ;
	}) ;
	
	//企业定制
	$(".company").click(function(){
		location.href = "activity/wap_20171118secondpage?modal=company" ;
	}) ;
	
	//领卷中心
	$(".coucenter").click(function(){
		location.href = "coucenter/coucenter" ;
	}) ;
	
	$(".packet-look-btn").click(function() {
    	$(".newcomer-packet").fadeOut();
    	$(".content").addClass("content-100");
    	$("header").addClass("header-100");
    	$(".down-app").show();
	});
	
	 $(".packet-close").on("click", "img", function(){
	    	$(".newcomer-packet").fadeOut();
	    	$(".content").addClass("content-100");
	    	$("header").addClass("header-100");
	    	$(".down-app").show();
	    });
	    $(".down-app-close").click(function () {
	       $(this).parent().parent().hide();
	        $(".content").removeClass("content-100");
	        $("header").removeClass("header-100");
	});
	    $("#totop").click(function() {
	        $('.content').animate({
	          scrollTop: 0
	        },
	        500);
	        return false;
	      });
});
