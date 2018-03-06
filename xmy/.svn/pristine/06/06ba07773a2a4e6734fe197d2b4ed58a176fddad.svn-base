$(function($){
	$(".front2").click(function(){
	    if($(this).hasClass("add-add-css")){
	        $(this).removeClass("add-add-css");
	        $(".classify-ul").stop();
	        $(".classify-ul").slideUp();
	    }else{
	        $(this).addClass("add-add-css");
	        $(".classify-ul").slideDown();
	    }
	});
	

	
	
	//买即赠
	$(".buyAndPresentEndTime").each(function(index){
    	window.setInterval(function(){buyAndPresentTime($(".buyAndPresentEndTime").eq(index).val(),'.buyAndPresentTime',index);}, interval);
    });
	
	//每周特价
	//window.setInterval(function(){activityTime($("#weekActivityEndTime").val(),'#weekActivityTime');}, interval);

});




var interval = 1000; 
function icActivityTime(date,divname) 
{ 
	var now = new Date(); 
	var endDate = new Date(date);
	var leftTime=endDate.getTime()-now.getTime();
	var leftsecond = parseInt(leftTime/1000); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
	$(divname).html('<span class="time-item">'+day1+'</span> 天<span class="time-item">'+hour+'</span> 时 <span class="time-item">'+minute+'</span> 分 <span class="time-item">'+second+'</span> 秒');

} 

function activityTime(date,divname) 
{ 
	var now = new Date(); 
	var endDate = new Date(date);
	var leftTime=endDate.getTime()-now.getTime();
	var leftsecond = parseInt(leftTime/1000); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
	$(divname).html(day1+"天"+hour+"时"+minute+"分"+second+"秒");
} 

function buyAndPresentTime(date,className,index) 
{ 
	var now = new Date(); 
	var endDate = new Date(date);
	var leftTime=endDate.getTime()-now.getTime();
	var leftsecond = parseInt(leftTime/1000); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
	$(".buyAndPresentTime").eq(index).html(day1+"天"+hour+"时"+minute+"分"+second+"秒");
} 

