/**
 * 
 */

require([ "common" ], function() {
	
	//初始化加载底部公共JS
	$.initFooterJS() ;
	
	$(function() {
		now = new Date();
		time = now.getHours() * 60 * 60 * 1000 + now.getMinutes() * 60 * 1000 + now.getSeconds() * 1000;
		if(parseInt(time) >= 32400000 && parseInt(time) < 64800000 )
			$("#oneClickCall").attr("href", "tel:400-822-3936");
		if(parseInt(time) >= 64800000 || parseInt(time) < 32400000)
			$("#oneClickCall").attr("href", "tel:17723156790");
	});
	
	$("#changeAvatar").click(function() {
		location.href = "gotoPage/mine/avatar";
	});

	$(".chunk").on("click", "#toAddress", function() {
		location.href = "mine/address";
	});
	$(".chunk").on("click", "#toAccount", function() {
		location.href = "mine/account";
	});
	$(".chunk").on("click", "#toSetting", function() {
		location.href = "mine/setting";
	});
	$(".chunk").on("click", "#toMessage", function() {
		location.href = "mine/findMessage";
	});
	$(".favorite").click(function() {
		location.href = "gotoPage/mine/favorite";
	});

	$("#pointsBalance").click(function() {
		location.href = "gotoPage/mine/accountPointsDetail";
	});

	$("#accBalance").click(function() {
		location.href = "gotoPage/mine/accountBalanceDetail";
	});
	$(".footStep").click(function() {
		location.href = "gotoPage/mine/footStep";
	});
	$(".shopping-card").click(function() {
		location.href = "mine/shoppingCard";
	});
	$(".orderQuery").click(function() {
		location.href = $(this).attr("src");
	});

	liTemplate = $("<li></li>");

	reqGuessLike = function() {
		$.ajax({
			url : 'collectionGoods/getGuessLike',
			dataType : 'json',
			type : 'post',
			success : function(data) {
				guessLikeData = data.obj;
				if(guessLikeData) {
					$(".list").append($("<div class='list-title'><img src='resources/common/images/gr-title.png'/></div><ul id='getGuessLike' class='list-ul'></ul>"))
					guessLikeTag = $("#getGuessLike");
					$.each(guessLikeData, function(index, value) {
						li = liTemplate.clone();
						li.attr("class", "toGoodsInfo");
						li.attr("goods-id",value.id);
						li.attr("url", "goods/goodsInfo?goodsId=" + value.id);
						img = $("<img  onerror='this.src=\"resources/common/images/defaultgoods.jpg\"' src=''></img>");
						img.attr("src", value.imgPath);
						p = $("<p class='money'></money>");
						p.html('￥' + value.phonePrice);
						p1 = $("<p class='details'></p>");
						p1.html(value.name);
						li.append(img);
						li.append(p1);
						li.append(p);
						guessLikeTag.append(li);
					});
				}
			},
			/*error:function(error){
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
			}*/
		});
	}
	
	$(".list").on("click", ".toGoodsInfo", function() {
		if($.isPutaway($(this).attr("goods-id"))){
			$.alert("该商品已下架") ;
			return ;
		}
		location.href = $(this).attr("url");
	});
	
	loadCount = function() {
		tags = $(".fixation");
		$.each(tags, function(index, value) {
			if(tags.eq(index).attr("url")) {
				$.ajax({
					url : tags.eq(index).attr("url"),
					type : 'post',
					dataType : 'json',
					success : function(data) {
						if(data.status != 1) {
						} else {
							if(tags.eq(index).hasClass("balance")){
								tags.eq(index).html(parseFloat(data.obj).toFixed(2)) ;
							}else{
								if(data.obj != 0){
									resultTag = $("<div class='number'></div>");
									resultTag.html(data.obj > 99 ? 99 : data.obj);
									tags.eq(index).append(resultTag);
								}
							}
							
						}
					},
					/*error:function(error){
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
					}*/
				});
			}
		});
	}
	
	
	$(function() {
		loadCount();
		reqGuessLike();
	});

	$(".Paymentpending").click(function() {
		window.location.href = "order/myOrder/1";
	});
	$(".waitGood").click(function() {
		window.location.href = "order/myOrder/3";
	});
	$(".toEvaluate").click(function() {
		window.location.href = "order/myOrder/4";
	});
	$(".apply_refund").click(function(){
		window.location.href="order/apply";
	})

	// 优惠券
	$(".coupon").click(function(e) {
		e.stopPropagation() ;
		location.href = "mine/findCoupon";
	});
});
