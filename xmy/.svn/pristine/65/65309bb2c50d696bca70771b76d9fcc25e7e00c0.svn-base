$(function(){
	$(".pleft").on("click",".ptitle",function(){
		if($(this).hasClass("active")){
			$(this).siblings(".mo").slideUp();
			$(this).removeClass("active");
			$(this).find(".iconfont").removeClass("css3");
		}else{
			$(this).siblings(".mo").slideDown();
			$(this).addClass("active");
			$(this).find(".iconfont").addClass("css3");
		}
	});
	$(".mo").on("click","a",function(){
		$(this).addClass("on").siblings().removeClass("on");
		$(this).parents(".pli").siblings().find("a").removeClass("on");
	});
	
	$(".mo").children().each(function(index,item){
		$(this).click(function(){
			$(".lijie").hide();
			$(".lijie").eq(index).show();
		});
	});
	
});