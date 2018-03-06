require(["common"],function(){
	
	//初始化加载优惠券列表
	loadCouponList() ;
	
	//后退
	$(".pull-left").click(function(){
		location.href = "mine/center" ;
	}) ;
	
	//选择使用状态
	$(".state").on("click",".weui-flex__item",function(){
		$(this).addClass("active").siblings().removeClass("active");
		loadCouponList() ;
	});
	
	//选择排序
	$(".status").on("click",".order-method",function(){
		$(this).addClass("active").siblings(".order-method").removeClass("active");
		loadCouponList() ;
	});
	
	//加载优惠券列表
	function loadCouponList(){
		var data = {
					useType : $(".state .active").attr("int-val"),
					useRange : $(".use-range").filter(".active").find("a").attr("use-range"), 
					order : $(".order-method").filter(".active").attr("int-val")
					} ;
		$.commonAjax(data,"mine/findCouponList","text",function(data){
			$(".lump1").html(data) ;
		}) ;
	}
	
	//绑定优惠券
	$(".bound").click(function(){
		if($("#couponCode").val()){
			$.commonAjax({couponCode : $("#couponCode").val()},"mine/bindCoupon","json",function(data){
				$.alert("绑定成功",function(){
					$(".m-frame").fadeOut();
					loadCouponList() ;
					$(".notUseNumber").html(parseInt($(".notUseNumber").html()) + 1) ;
				}) ;
			}) ;
		}else{
			$.alert("请输入优惠券码") ;
		}
	}) ;
	
	//立即绑定
	$(".pull-right").click(function(){
		$("#couponCode").val("");
		$(".m-frame").fadeIn();
	});
	$(".m-frame").click(function(){
		$(".m-frame").fadeOut();
	});
	$(".m-frame_con").click(function(e){
		e.stopPropagation();
	});
	
	//排序下拉
	$(".sort-type-x").click(function(){
		if($(".img-off").is(":visible")){
			$(".img-on").show() ;
			$(".img-off").hide() ;
			$(".sort").stop().slideDown();
		}else{
			$(".img-on").hide() ;
			$(".img-off").show() ;
			$(".sort").stop().slideUp();
		}
	});
	$(".sort").on("click",".use-range",function(){
		$(".sort").stop().slideUp();
		$(".img-on").hide() ;
		$(".img-off").show() ;
		$(this).addClass("active").siblings().removeClass("active") ;
		$("#rangeType").html($(this).find("a").html()) ;
		loadCouponList() ;
	}) ;
}) ;
