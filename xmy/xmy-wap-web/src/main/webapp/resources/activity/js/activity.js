require(["common"],function(){

//初始化加载底部公共JS
$.initFooterJS() ;
//每日秒杀
limitTime() ;

function limitTime(){
	var leftTime, now = new Date(), endDate = new Date() ;
	
	//后退
	$(".pull-left").click(function(){
		location.href = "home/home" ;
	}) ;
	
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
	window.setInterval(function(){
		leftTime -= 1000 ;
		var leftsecond = parseInt(leftTime/1000); 
		var day1=Math.floor(leftsecond/(60*60*24)); 
		var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
		var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
		var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
		$(".time-esc").html('<span class="hours">'+ (hour >= 10 ? hour : ("0"+hour)) +'</span> : <span class="miu">'+ (minute >= 10 ? minute : ("0"+minute)) +'</span> : <span class="s">'+  (second >= 10 ? second : ("0"+second)) +'</span>');
	}, 1000);
  }
//天天特价
resetTime();

function resetTime(){
	var leftTime=$("#resetTime").val();
	window.setInterval(function(){
		leftTime-=1000;
		var leftsecond = parseInt(leftTime/1000);
		var hour = Math.floor(leftsecond / 60 / 60 % 60);
		var minute =Math.floor( leftsecond / 60 % 60);
		var second = Math.floor(leftsecond % 60);
		$(".time-esc1").html('<span class="hours">'+ (hour >= 10 ? hour : ("0"+hour)) +'</span> : <span class="miu">'+ (minute >= 10 ? minute : ("0"+minute)) +'</span> : <span class="s">'+  (second >= 10 ? second : ("0"+second)) +'</span>');
	}, 1000);
}

//买及赠
buyAndPresent();

function buyAndPresent(){
	if($(".buyAndPresent-endTime").length > 0){
		var endTime = new Date($(".buyAndPresent-endTime").eq(0).val());
		var leftTime = endTime.getTime() - $("#nowTime").val();
		window.setInterval(function(){
			leftTime-=1000;
			var leftsecond = parseInt(leftTime/1000); 
			var day1=Math.floor(leftsecond/60/60/24); 
			var hour = Math.floor(leftsecond/60/60%24);
			var minute =Math.floor( leftsecond/60%60);
			var second = Math.floor(leftsecond%60);
			$(".time-esc3").html('<span class="day1" style="margin-right:0.2rem;">'+ day1 +'</span>天<span class="hours" style="margin-left:0.2rem;">'+ (hour >= 10 ? hour : ("0"+hour)) +'</span> : <span class="miu">'+ (minute >= 10 ? minute : ("0"+minute)) +'</span> : <span class="s">'+  (second >= 10 ? second : ("0"+second)) +'</span>');
		}, 1000);
	}
}


//每周特价
weekTime();

function weekTime(){
	var leftTime=$("#weekTime").val();
	window.setInterval(function(){
		leftTime-=1000;
		var leftsecond = parseInt(leftTime/1000); 
		var day1=Math.floor(leftsecond/60/60/24); 
		var hour = Math.floor(leftsecond/60/60%24);
		var minute =Math.floor( leftsecond/60%60);
		var second = Math.floor(leftsecond%60);
		$(".time-esc2").html('<span class="day1" style="margin-right:0.2rem;">'+ day1 +'</span>天<span class="hours" style="margin-left:0.2rem;">'+ (hour >= 10 ? hour : ("0"+hour)) +'</span> : <span class="miu">'+ (minute >= 10 ? minute : ("0"+minute)) +'</span> : <span class="s">'+  (second >= 10 ? second : ("0"+second)) +'</span>');
	}, 1000);
}
$(".swiper-container").swiper({
    autoplay: 2000,
    speed: 400,
    spaceBetween: 100,
    pagination: '.swiper-pagination',
    paginationClickable: true
    
});

//跳转商品详情
$(".goods-div,.buy-present").click(function(){
	if($.isPutaway($(this).attr("goods-id"))){
		$.alert("该商品已下架") ;
		return ;
	}
	location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") + "&activityId=" + $(this).attr("activity-id") + "&activityType=" + $(this).attr("activity-type");
}) ;


});


