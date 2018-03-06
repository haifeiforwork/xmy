$(function () {
	
	$(".outtop").slideDown(500);
	if ($(".outtop").show()){
	    setTimeout(function () {
            $(".outtop").slideUp(400)
        },4000)
	}
	$(".close-icons i").click(function () {
        $(".outtop").slideUp(400);
    });
	//菜单显示
	$("#one").show();
	$("#two").hide();
	$(".classify-ul").show();
	$(".tab-content>div:gt(0)").hide();
	$("#imageShow").show();
	$("#downShow").show();
    //冰点价倒计时
    window.setInterval(function(){ShowCountDown("00",'#freeActivityTime');}, interval);
    //天天特价倒计时
    window.setInterval(function(){dayshowtime("00",'#dayActivityTime');}, interval);
    //每周特价倒计时
    window.setInterval(function(){WeekTime($("#weekActivity").val(),'#weekActivityTime');}, interval);
    //买即赠循环倒计时
   $(".buyAndPersent").each(function(index){
    	window.setInterval(function(){ForDowTime($(".buyAndPersent").eq(index).val(),'.showTime',index);}, interval);
    });
    $(".tab-nav li").hover(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".tab-nav li").index(this);
        $("div.tab-content>div").eq(index).show().siblings().hide()
    });
    //新版货柜加入商品
    //加入大货柜第一个商品
    $(".bigul").each(function(){
    	var conid = $(this).parent().attr("value");
    	var elment = $(this);
    	$.ajax({
    		url:"container/"+conid+"/1?consize=0",
    		type:"post",
    		success:function(data){
    			elment.html(data);
    		}
    	});
    });
    //加入小货柜第一组商品
    $(".smallul").each(function(){
    	var conid=$(this).parent().attr("value");
    	var elment = $(this);
    	$.ajax({
    		url:"container/"+conid+"/1?consize=1",
    		type:"post",
    		success:function(data){
    			elment.html(data);
    		}
    	});
    })
    $(".bigContain").each(function(){
    	$(this).find(".chunk-d-li").hover(function(){
        	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
        },function(){
        	$(this).css({"background":"#FFF","color":"#666"});
        });
    })
    
    $(".smallContain").each(function(){
    	$(this).find(".chunk-d-li").hover(function(){
        	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
        },function(){
        	$(this).css({"background":"#FFF","color":"#666"});
        });
    })
   /* //样式
    $(".bigContain").eq(0).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.5)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    });
    $(".bigContain").eq(1).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })
     $(".bigContain").eq(2).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })
     $(".bigContain").eq(3).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })
     $(".bigContain").eq(4).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })
     $(".bigContain").eq(5).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })
     $(".bigContain").eq(6).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })
    $(".smallContain").eq(0).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })
    $(".smallContain").eq(1).find(".chunk-d-li").hover(function(){
    	$(this).css({"background":"rgba(0,0,0,0.2)","color":"#FFF"});
    },function(){
    	$(this).css({"background":"#FFF","color":"#666"});
    })*/
    //默认显示第一个
    $(".chunk-d-ul").each(function(){
    	//alert($(this).attr("value"));
    	$(this).children().first().css("color","red").siblings().css("color","#494A4A");
    	//var index=$(this).children().first().attr("value");
	    //$(this).parent().parent().children().eq(2).find("."+index).show().siblings().hide();
    });
    //货柜商品切换
    $(".chunk-d-li").click(function(){
    	//alert($(this).attr("pid"));
    	//alert($(this).text());
    	var typeName=$(this).attr("value");
    	if($(this).parent().hasClass("smallContain")){
    		window.location.href="elastic/goods?typeName="+$(this).text();
    	}else{
    		window.location.href="elastic/goods?typeName="+$(this).text()+","+typeName;
    	}
    	//$(this).css("color","red").siblings().css("color","#494A4A");
    	//var index=$(this).attr("value");
	    //$(this).parent().parent().parent().children().eq(2).find("."+index).show().siblings().hide();
    });
    //换一组的
    $(".big").click(function(){
    	var conid=$(this).attr("value");
    	var pageindex=parseInt($(this).attr("pageindex"))+1;
    	var co=$(this).parent().parent().children().eq(2).children().first();
    	var countpage=co.children().first().val();
    	var consize = 0;//大货柜
    	if(pageindex>countpage){
    		pageindex=1;
    	}
    	$(this).attr("pageindex",pageindex);
    	changeContainerGoods(conid,pageindex,co,consize);
    	/*$(this).parent().parent().children().eq(1).children().eq(0).children().css("color","#494A4A");
    	var length = $(this).parent().parent().children().eq(1).children().eq(0).children().length;
    	var round=Math.round(Math.random()*length+1);
    	$(this).parent().parent().children().eq(2).children().eq(round).show().siblings().hide();*/
    });
    
    //小货柜默认显示第一个
    $(".smallul").each(function(){
    	var index=$(this).children().first().attr("value");
    	$(this).parent().children().eq(2).find("."+index).show().siblings().hide();
    });
    
    //小货柜切换
    $(".small").click(function(){
    	var index=$(this).attr("value");
    	$(this).parent().parent().children().eq(2).find("."+index).show().siblings().hide();
    });
    //小货柜换一组
    $(".left").click(function(){
    	var conid=$(this).attr("value");
    	var pageindex=parseInt($(this).attr("pageindex"))+1;
    	var co=$(this).parent().parent().parent().children().eq(1).children().last().children().first();
    	var countpage=co.children().first().val();
    	var consize = 1;//小货柜
    	if(pageindex>countpage){
    		pageindex=1;
    	}
    	$(this).attr("pageindex",pageindex);
    	changeContainerGoods(conid,pageindex,co,consize);
    	/*$(this).parent().parent().parent().children().eq(1).children().eq(0).children().css("color","#494A4A");
    	var length = $(this).parent().parent().parent().children().eq(1).children().eq(0).children().length;
    	var round=Math.round(Math.random()*length+1);
    	$(this).parent().parent().parent().children().eq(1).children().eq(2).children().eq(round).show().siblings().hide();*/
    });
  //首页左侧二维码最开始显示5秒事件
	setTimeout("$('.pop-up').fadeOut(100)",5000);
	
	window.setInterval(function(){
		time+=1000;
	}
	, interval);
});
var interval = 1000;
var nowDate = $("#daoji").val();
var wocao = new Date(nowDate);
var time=wocao.getTime();
function ShowCountDown(date,divname) 
{ 	
	var now = new Date(time); 
	var endDate = new Date(time);
	if(endDate.getHours()>=12){
		var oneDay =  1000*60*60*24;
		//endDate = new Date(endDate+oneDay);//明天的时间
		endDate.setHours(12, 0, 0, 0);
	}else{
		endDate.setHours(12, 0, 0, 0);
	}
	//alert(date);
	var leftTime=endDate.getTime()-now.getTime();
	var leftsecond = parseInt(leftTime/1000); 
	//var day1=parseInt(leftsecond/(24*60*60*6)); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
	if(hour>9){
		$(divname).text(hour+"时"+minute+"分"+second+"秒");
	}else{
		$(divname).text("0"+hour+"时"+minute+"分"+second+"秒");
	}
	//"脚本之家提示距离"+year+"年"+month+"月"+day+"日还有："+day1+"天"+hour+"小时"+minute+"分"+second+"秒"
} 

//天天特价
function dayshowtime(date,divname) 
{  	
	var now = new Date(time); 
	var endDate = new Date(time);
	//alert(now);
	endDate.setHours(23, 59, 59, 59);
	//alert(date);
	var leftTime=endDate.getTime()-now.getTime();
	var leftsecond = parseInt(leftTime/1000); 
	//var day1=parseInt(leftsecond/(24*60*60*6)); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
	if(hour>9){
		$(divname).text(hour+"时"+minute+"分"+second+"秒");
	}else{
		$(divname).text("0"+hour+"时"+minute+"分"+second+"秒");
	}
	//"脚本之家提示距离"+year+"年"+month+"月"+day+"日还有："+day1+"天"+hour+"小时"+minute+"分"+second+"秒"
} 


function WeekTime(date,divname) 
{  
	var now = new Date(time); 
	var endDate = new Date(date);
	var leftTime=endDate.getTime()-now.getTime();
	var leftsecond = parseInt(leftTime/1000); 
	//var day1=parseInt(leftsecond/(24*60*60*6)); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
	$(divname).text(day1+"天"+hour+"时"+minute+"分"+second+"秒");
	//"脚本之家提示距离"+year+"年"+month+"月"+day+"日还有："+day1+"天"+hour+"小时"+minute+"分"+second+"秒"
} 


function ForDowTime(date,className,index) 
{ 
	var now = new Date(time); 
	var endDate = new Date(date);
	var leftTime=endDate.getTime()-now.getTime();
	//var leftTime=Math.floor(endDate.getTimezoneOffset()-now.getTimezoneOffset());
	var leftsecond = parseInt(leftTime/1000); 
	//var day1=parseInt(leftsecond/(24*60*60*6)); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
	if(day1<0){
		$(".showTime").eq(index).text("活动时间已经结束");
		$(".buyandparent").eq(index).attr("href","javascript:void(0)");
	}else{
		$(".showTime").eq(index).text(day1+"天"+hour+"时"+minute+"分"+second+"秒");
	}
	//"脚本之家提示距离"+year+"年"+month+"月"+day+"日还有："+day1+"天"+hour+"小时"+minute+"分"+second+"秒"
}

function changeContainerGoods(conid,pageindex,element,consize){
	$.ajax({
		url:"container/"+conid+"/"+pageindex+"?consize="+consize,
		type:"post",
		success:function(data){
			element.html(data);
		}
	})
}


