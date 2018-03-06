/**
 * 
 */
require(["common"],function(){
	
	//是否冰点
	bingDian = false;
	
	//currPage = 1;
	
	categoryData = null;
	
	goodsData = null;
	
	guessLikeData = null;
	
	contentTag = $("div .weui-panel__bd");
	
	categoryTag = $(".listing");
	
	liTemplate = $("<li></li>");
	
	contentTemplate = $("<div class='weui-media-box__bd'><p class='weui-media-box__desc'></p><p class='money'>￥<span></span></p><div class='count' ><span class='title'></span><span class='number'></span></div></div>")
	
	imgTemplate = $("<div class='weui-media-box__hd'><img class='weui-media-box__thumb'  onerror='this.src=\"resources/common/images/defaultgoods.jpg\"'></img></div>");
	
	detTemplate = $("<div class='weui-media-box__ft removeBtn' style='z-index: 999;'><span class='remove' style='background:#358812;color:#FFF;font-size:0.6rem;padding:1px 0.2rem;display:none;float:right'>删除</span></div>")
	
	aTemplate = $("<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg'></a>")
	
	idTemplate = $("<input class='goodsId' type='hidden' />");
	
	$(".list").on("click",".removeBtn",function(e) {
		e.stopPropagation();
		id = $(this).siblings("input").val();
		aTag = $(this).parents("a");
		$.ajax({
			url : 'collectionGoods/delCollectionGoods',
			data : {'id' : id},
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data.status == 1) {
					reqCategorys();
					aTag.remove();
					$(".listing").empty();
					loadCategorys(categoryTag);
				} else {
					$.toptip(data.msg, "msg");
				}
			},
			error:function(error){
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
			}
		});
	});
	
	$(".pull-left").click(function() {
		window.history.go(-1);
	});
	
	$("body").infinite();
	checkBingDian();
	reqCategorys();
	if(categoryData)
		loadCategorys(categoryTag);
	reqGoods();
	if(goodsData)
		loadGoods(contentTag);
	reqGuessLike();
	if(guessLikeData) {
		$(".lists").append($("<div class='list-title'><img src='resources/common/images/gr-title.png'/></div><ul id='getGuessLike' class='list-ul'></ul>"))
		guessLikeTag = $("#getGuessLike");
		loadGuessLike(guessLikeTag);
	}
	
	$("body").on("click", ".toGoodsInfo", function() {
		if($.isPutaway($(this).attr("goods-id"))){
			$.alert("该商品已下架") ;
			return ;
		}
		location.href = $(this).attr("url");
	});
	
	$(".rank").on("click",".weui-flex__item",function(){
		$(this).addClass("active").siblings().removeClass("active");
	});
	var content_w = $(".content").width();
	var content_h = $(".content").height();
	$(".listing").width(content_w);
	$(".listing").height(content_h);
	$(window).resize(function() {
	 	var content_w = $(".content").width();
    	var content_h = $(".content").height();
    	$(".listing").width(content_w);
    	$(".listing").height(content_h);
	});
	$(".all").click(function(){
		if($(this).find(".listing").hasClass("add-css")){
			$(this).find(".listing").removeClass("add-css").fadeOut(200);
		}else{
			$(this).find(".listing").addClass("add-css").slideDown();
		}
	});
	$(".listing").on("click","li",function(){
		$(this).addClass("active").siblings().removeClass("active");
		name = $(this).attr("truevalue");
		reqGoods(name);
		$(contentTag).empty();
		loadGoods(contentTag);
	});
	$("#getAllGoods").click(function() {
		reqGoods();
		$(contentTag).empty();
		loadGoods(contentTag);
	});
	$(".edit").click(function(){
		$(this).toggleClass("active");
		if($(this).hasClass("active")){
			if($(".list a").length <= 0){
				$.alert("暂未收藏商品") ;
				return ;
			}
			$(".remove").fadeIn();
			$(this).html("完成");
		}else{
			$(".remove").fadeOut();
			$(this).html("编辑");
		}
		
	});
	
});
	
	loadGoods = function(tag) {
		if(goodsData.length <= 0){
			var html = '<div style="text-align:center;padding:2rem 0;color:#999;font-size:0.75rem;">' +
			'<img alt="" src="resources/common/images/empty.png" style="margin:0 0 0.5rem;width: 3rem;"/>' +
			'<p>您还没有收藏过商品</p>' +
			'</div>';
			tag.html(html) ;
		}else{
			$.each(goodsData, function(index, value) {
				one = aTemplate.clone();
				one.attr("class", one.attr("class") + " toGoodsInfo");
				one.attr("goods-id",value.goodsId);
				one.attr("url", "goods/goodsInfo/?goodsId=" + value.goodsId);
				two = imgTemplate.clone();
				two.children("img").attr("src", value.imgPath);
				three = contentTemplate.clone();
				console.log("长度:" + value.name.length);
				three.children("p:first").html(value.name);
				three.children("p:last").children("span").html(value.phonePriN);
				/*if(bingDian)
					three.children("div").children("span:first").html("冰点价");
				else*/
				three.children("div").children("span:first").remove();
				/*three.children("div").children("span:last").html("根本就不想限量");*/
				one.append(two);
				one.append(three);
				one.append(detTemplate.clone());
				idTag = idTemplate.clone();
				idTag.val(value.id);
				one.append(idTag);
				tag.append(one);
			});
		}
	}
	
	loadCategorys = function(tag) {
		$.each(categoryData, function(index, value) {
			li = liTemplate.clone();
			li.attr("trueValue", value.cateName);
			showValue = value.cateName;
			showValue = showValue.replace(",", "");
			showValue = showValue.replace(",", "");
			li.html(showValue + "(" + value.amount + ")");
			tag.append(li);
		});
	}
	
	checkBingDian = function() {
		$.ajax({
			url : 'checkBingDian',
			dataType : 'json',
			type : 'post',
			async : false,
			success : function(data) {
				if(data.obj == 1) {
					bingDian = true;
				} 
			},
			error:function(error){
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
			}
		});
	}
	
//	$("body").infinite().on("infinite", function() {
//		
//	});
	
	
	reqGoods = function(goodsCategoryName) {
		value = {};
		value['goodsCategoryName'] = goodsCategoryName;
		$.ajax({
			url : 'collectionGoods/getGoods',
			async : false,
			data : value,
			dataType : 'json',
			type : 'post',
			success : function(data) {
				console.info(data) ;
				goodsData = data.obj;
			},
			error:function(error){
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
			}
		});
	}

	
	reqCategorys = function() {
		$.ajax({
			url : 'collectionGoods/getCategorys',
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(data) {
				total = 0;
				$.each(data.obj, function(index, value) {
					total += value.amount;
				});
				$("#total").html(total);
				categoryData = data.obj;
			},
			error:function(error){
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
			}
		});
	}
	
	reqGuessLike = function() {
		$.ajax({
			url : 'collectionGoods/getGuessLike',
			dataType : 'json',
			type : 'post',
			async : false,
			success : function(data) {
				guessLikeData = data.obj;
			},
			error:function(error){
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
			}
		});
	}
	
	loadGuessLike = function(tag) {
		$.each(guessLikeData, function(index, value) {
//			console.log(value);
			li = liTemplate.clone() ;
			li.attr("class", "toGoodsInfo");
			li.attr("goods-id",value.id);
			li.attr("url", "goods/goodsInfo?goodsId=" + value.id);
			img = $("<img></img>");
			img.attr("src", value.imgPath);
			p = $("<p class='money' style='color: red;'></money>");
			p.html('￥' + value.phonePrice);
			p1 =$("<p class='details' style='fon'></p>");
			//name = value.name.length > 20 ? value.name.substring(0, 20) + "..." : value.name;
			p1.html(value.name);
			li.append(img);
			li.append(p1);
			li.append(p);
			//li.append(idTag);
			tag.append(li);
		});
	}
