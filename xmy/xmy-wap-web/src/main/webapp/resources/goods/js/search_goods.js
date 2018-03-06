require(["common"],function(){
	
	var search = "", repeat = false ;
	
	gateWay = parseInt($("#gateWay").val());
	
	//后退
	$(".pull-left").click(function(){
		window.history.go(-1);
	}) ;
	
	//初始化加载商品
	loadGoodsList() ;
	
	//筛选条件折叠
	$(".page").on("click",".search-type .item-title",function(){
		if($(this).find(".img-wrap").hasClass("up")){
			$(this).find(".img-wrap").removeClass("up").addClass("down") ;
			$(this).next("ul").slideUp() ;
		}else{
			$(this).find(".img-wrap").removeClass("down").addClass("up") ;
			$(this).next("ul").slideDown() ;
		}
	})
	
	//底下分页的灰色坨坨事件
	var action = null;
	
    //滚动加载
    var loading = false;  //状态标记
    $(".content").infinite().on("infinite", function() {
        if(loading) return;
        loading = true;
        if($("#pageIndexPage").val()*20 <= $(".goods-div").length && parseInt($("#pageIndexPage").val()) < parseInt($("#totalPage").val())){
        	 $("#pageIndex").val(parseInt($("#pageIndexPage").val(),10) + 1) ; 
        	 $(".weui-loadmore").show() ;
        	 loadGoodsList("append") ;
        }else{
        	/*$(".already-last").fadeIn();
        	action = setTimeout(function(){
        		$(".already-last").fadeOut();
        	}, 3000);*/
        	var thml = '<div class="last-pagetips">' +
	        	'<div class="last-pageword">抱歉,没有更多商品啦~</div>' +
	        	'<div class="last-pageimg"><img src="resources/common/images/img-index-bt@2x.png"/></div>' +
	        	'</div>'
        	$(".content").append(thml) ;
        }
    });

    //点击筛选右侧滑出
    $(".col-20").click(function () {
        $(".page").fadeIn();
        $(".page-content").animate({"left":"30%"})
    });
    
    $(".overlay").click(function () {
        $(".page").fadeOut();
        $(".page-content").animate({"left":"100%"})
    });
    
   /* $(".img-wrap").click(function () {
        if($(this).hasClass("down")){
            $(this).removeClass("down").addClass("up")
            $(this).parent().siblings().slideDown();
        }else {
            $(this).removeClass("up").addClass("down")
            $(this).parent().siblings().slideUp();
        }
    });*/
    
    words = "";
    
    //点击筛选添加
    $(".page-content").on("click",".search-type li",function(){
    	
    	if(gateWay && $(this).hasClass("choose-type")) {
    		tags = $(".search-type .choose-type");
    		tags.splice(tags.length - 1);
    		val = tags.length = 0 ? null : $(tags[tags.length - 1]).attr("search");
    		words = val;
    	}
    	
    	if($(this).hasClass("all-type")){ //判断是否选择全部类型
    		$(".search-type li").removeClass("choose-type") ;
    	}else{
    		$(".search-type .all-type").removeClass("choose-type") ;
    	}
    	if($(this).hasClass("choose-type")){
    		$(this).removeClass("choose-type") ;
    	}else{
    		$(this).parent().find("li").removeClass("choose-type");
    		$(this).addClass("choose-type") ;
    	}
    	
    	var choose_obj = $(".search-type li").filter(".choose-type") ;
    	$.each(choose_obj,function(i,n){
    		search += choose_obj.eq(i).attr("search") + "," ;
    	}) ;
    	if(search) search = search.substring(0, search.length - 1) ;
    	
    	if(gateWay) {
    		
    		if($(this).hasClass("choose-type")) {
    			words = $(this).attr("search");
    		}
    		
    		$(this).siblings().removeClass("choose-type");
    		
    		thisTag = $(this);
    		typeId = $("#typeId").val();
    		if(words == "") {
    			words = $(this).attr("search");
    		}
    		if(words == undefined) {
    			words = "";
    		}
    		data = {};
    		data["typeId"] = typeId;
    		data["words"] = words;
    		
    		if(!data.words && data.typeId) {
    			loadChildTypeByTypeID(data.typeId);
    			return;
    		}
    		
    		$.ajax({
    			type : 'post',
    			url : 'goods/searchDtoByWordsAndTypeId',
    			data : data,
    			async : false,
    			success : function(data) {
    				if(data && data.obj) {
    					result = data.obj;
    					tags = $(".search-type li");
        				$.each(tags, function(index, value) {
        					flag = result.indexOf($(this).text()) < 0 ? true : false;
        					if(flag) {
        						if(!$(this).hasClass("choose-type"))
        							$(this).hide();
        					} else {
        						$(this).show();
        					}
        				});
    				}
    			}
    		});
    		return;
    	}
    	
    	loadChildType(search, $("searchForm input[name='goodsName']").val()) ;
    }) ;
    
    //通过typeId加载子类分类
    function loadChildTypeByTypeID(typeId){
    	
    	values = {};
    	values['typeId'] = typeId;
    	
    	$.commonAjax(values,"goods/searchDtoByTypeId","text",function(data){
    		$(".search-type").remove() ;
    		$(".page-content").prepend(data) ;
    	}) ;
    }
    
    //加载子类分类
    function loadChildType(searchs, goodsName){
    	
    	data = {};
    	data['typeName'] = searchs;
    	if(goodsName) {
    		data['goodsName'] = goodsName;
    	}
    	
    	$.commonAjax(data,"goods/searchDto","text",function(data){
    		search = "" ;
    		$(".search-type").remove() ;
    		$(".page-content").prepend(data) ;
    		$.each($(".search-type li"),function(i,n){
    			if($(".search-type li").eq(i).hasClass("choose-type") && $(".search-type li").eq(i).html() != '全部'){
    				$(".search-type li").eq(i).parents("ul").css("display","block");
    				$(".search-type li").eq(i).parents(".search-type").find(".img-wrap").removeClass("down").addClass("up") ;
    			}
    		});
    	}) ;
    }
    
    //选择配送地址
    $(".post-area").on("click","li",function(e){
    	e.stopPropagation() ;
    	if(!$(this).hasClass("choose-type")){
    		$(this).addClass("choose-type").siblings().removeClass("choose-type") ;
    	}else{
    		$(this).removeClass("choose-type") ;
    	}
    }) ;
    
    //销量排序
    $(".sale").click(function(){
    	$(this).addClass("active").siblings().removeClass("active") ;
    	$("input[name='orderMethod']").val(2) ;
    	$("#pageIndex").val(1) ; 
    	loadGoodsList() ;
    }) ;
    
    //价格排序（降序）
    $(".price-down").click(function(){
    	if($(this).find("span").hasClass("price-down1")){
    		$(this).addClass("active").siblings().removeClass("active") ;
    		$("input[name='orderMethod']").val(3) ;
    		$("input[name='priceOrder']").val(true) ;
    		$("#pageIndex").val(1) ;
    		loadGoodsList();
    		$(this).find("span").addClass("price-down11");
    		$(this).find("span").removeClass("price-down1");
    	}else{
    		$(this).addClass("active").siblings().removeClass("active") ;
    		$("input[name='orderMethod']").val(3) ;
    		$("input[name='priceOrder']").val(false) ;
    		$("#pageIndex").val(1) ;
    		loadGoodsList();
    		$(this).find("span").removeClass("price-down11");
    		$(this).find("span").addClass("price-down1");
    	}
    	
    } ) ;
    
    //更改页面布局
    $(".sequencing").click(function(){
    	if($(this).find("span").hasClass("active")==false){
    		$(this).find("span").addClass("active");
    		$(".sequencingOne").find("img").attr("src","resources/goods/images/ico-qgps-up.png");
    		$("#pageIndex").val(1) ; 
    		loadGoodsList();
    	}else{
    		$(this).find("span").removeClass("active");
    		$("#pageIndex").val(1) ; 
    		loadGoodsList();
    		$(".sequencingOne").find("img").attr("src","resources/goods/images/btn-qgps-qh@2x.png");
    	}
    	
    });
    
    //综合排序
    $(".order-usual").click(function(){
    	$(".sort").slideToggle() ;
    	$(this).addClass("active").siblings().removeClass("active") ;
    	/*$("input[name='orderMethod']").val(1) ;
    	$("input[name='priceOrder']").val(true) ;
    	$("#pageIndex").val(1) ; 
    	loadGoodsList() ;*/
    }) ;
    
    //选择排序方式
    $(".search-sort").click(function(){
    	$(this).addClass("active").siblings().removeClass("active") ;
    	$(".down1").html($(this).find("a").html()) ;
    	$(".sort").slideToggle() ;
    	$("input[name='orderMethod']").val($(this).attr("sort-method")) ;
    	$("input[name='priceOrder']").val(false) ;
    	$("#pageIndex").val(1) ; 
    	loadGoodsList() ;
    }) ;
    
    //搜索框
/*    $('.search-input').keydown(function(e){
		if(e.keyCode==13){
			
		   if($("#needReload").val() == "1") {
		   		location.href = "goods/searchGoodsByName?goodsName=" + $(this).val();
		   		return;
		   }
			
		 $("input[name='goodsName']").val($(this).val()) ;	
		 loadGoodsList() ;
		}
	});*/
    
    
    $('.search-input').keydown(function(e){
		if(e.keyCode==13){
			
		   	location.href = "goods/searchGoodsByName?goodsName=" + $(this).val();
		   	
		}
	});
    
    //重置
    $(".esc").click(function(){
    	
    	if(gateWay) {
    		loadChildTypeByTypeID($("#typeId").val());
    	} else {
    		goodsName = $("#searchForm input[name='goodsName']").val();
    		if(goodsName) {
    			loadChildType(null, goodsName);
    		} else {
    			loadChildType("全部", null);
    		}
    	}
    	
    	$("#searchForm input[name='mianLand']").val("");
    	$("#searchForm input[name='isDivle']").val("");
    	$("#searchForm input[name='beginPrice']").val("");
    	$("#searchForm input[name='endPrice']").val("");
    	$(".low-price").val("") ;
    	$(".most-price").val("") ;
    	$(".post-area li").removeClass("choose-type") ;
    }) ;
    
    //选择筛选条件完成
    $(".finish").click(function(){
    	var pass = true ;
    	wordSeg = "";
    	
    	var choose_obj = $(".search-type li").filter(".choose-type") ;
    	$.each(choose_obj,function(i,n){
    		wordSeg += choose_obj.eq(i).attr("search") + "," ;
    	}) ;
    	
    	//验证价格
        $.each($(".prcie-area .price-input"),function(i,n){
        	var reg = /^(\d)*\.?(\d)*$/ , obj = $(".prcie-area .price-input").eq(i) ;
        	if(obj.val() && !reg.test(obj.val())){
        		obj.val("") ;
        		pass = false;
        	}
        }) ;
        if(!pass){
        	$.alert("输入价格无效") ;
        	return ;
        }
    	if($("#searchType") == "name"){
    		$("input[name='search']").attr("value", wordSeg) ;
    	}else{
    		$("input[name='wordSeg']").attr("value", wordSeg) ;
    	}
    	if($(".post-area li").filter(".choose-type").length > 0){
    		$("input[name='isDivle']").val($(".post-area li").filter(".choose-type").attr("isDivle")) ;
    		$("input[name='mianLand']").val($(".post-area li").filter(".choose-type").attr("isDivle")) ;
    	}
    	if($(".low-price").val()){
    		$("input[name='beginPrice']").val($(".low-price").val()) ;
    	}else{
    		$("input[name='beginPrice']").val("") ;
    	}
    	if($(".most-price").val()){
    		$("input[name='endPrice']").val($(".most-price").val()) ;
    	}else{
    		$("input[name='endPrice']").val("") ;
    	}
    	
    	$("searchForm input[name='goodsName']").val($(".search-input").val() ? $(".search-input").val() : $("searchForm input[name='goodsName']").val());
    	
    	$(".page").fadeOut();
        $(".page-content").animate({"left":"100%"}) ;
        $("#pageIndex").val(1);
    	loadGoodsList() ;
    }) ;
    
    //验证价格
    $(".prcie-area").on("keyup",".price-input",function(){
    	var reg = /^(\d)*\.?(\d)*$/ ;
    	if($(this).val() && !reg.test($(this).val())){
    		$(this).val("") ;
    		$.alert("输入价格无效") ;
    	}
    }) ;

    //添加进购物车
    $(".add-shopping").click(function(e){
    	e.stopPropagation() ;
    	var data = {goodsId : $(this).attr("goods_id"),goodsNum : 1};
    	if(!repeat){
    		repeat = true ;
    		$.commonAjax(data,"shopping/judgeNewActivityGoods","json",function(datas){
    			if(datas.data){
    				$.alert(datas.data) ;
    				repeat = false ;
    			}else{
    				$.commonAjax(data,"shopping/addShopping","json",function(data){
    	    			repeat = false ;
    	    			$.toast("成功加入购物车",500);
    	        	}) ;
    			}
    		});
    		
    	}
    }) ;
    
    //查看商品详情
    $(".content").on("click",".goods-div",function(){
    	var activityId = $(this).attr("activityId");
    	var activityType = $(this).attr("activityType");
    	//location.href = "goods/goodsInfo?goodsId=" + $(this).find(".add-shopping").attr("goods_id") + "&activityId=" + activityId + "&activityType=" + activityType ;
    	location.href = "goods/goodsInfo?goodsId=" + $(this).find(".add-shopping").attr("goods_id") ;
    }) ;
    
    //加载商品
    function loadGoodsList(flag){
    	if(flag && $("#pageIndexPage").length > 0 && $("#totalPage").length > 0 && parseInt($("#pageIndexPage").val()) > parseInt($("#totalPage").val()) ){
    		$(".weui-loadmore").hide() ;
        	/*$(".already-last").fadeIn();
        	action = setTimeout(function(){
        		$(".already-last").fadeOut();
        	}, 3000);*/
    		return ;
    	}
    	$("#flag").val(flag) ;
    	$(".weui-loadmore").remove() ;
    	var url="goods/searchGoodsPage/0";
    	if($(".sequencing").find("span").hasClass("active")){
    		url="goods/searchGoodsPage/1";
    	}
    	$.commonAjax($("#searchForm").serialize(), url, "text",function(data){
    		if(flag){
    			$("#pageIndexPage").remove();
    			$("#totalPage").remove();
    			$("#pageFlag").remove();
    			$(".content").append(data);
    		}else{
    			$(".content").html(data) ;
    		}
    		//$(".weui-loadmore").hide() ;
    		if($("#totalPage").val() != 0){
    			//显示当前页数
        		//console.info($("#totalPage").val()) ;
                $(".show").find(".curr").html($("#pageIndexPage").val());
                $(".show").find(".total").html($("#totalPage").val());
                if(action) {
                	clearTimeout(action);
                }
                if(parseInt($(".show").find(".curr").html()) <= parseInt($(".show").find(".total").html())) {
                	$(".show").fadeIn();
                	action = setTimeout(function() {
                		$(".show").fadeOut();
                	}, 3000);
                }
    		}
    		loading = false ;
    	}) ;
    	
    	/*if(!parseInt($("#goodsCount").val())) {
			$.alert("未找到任何商品");
		}*/
    }
    
});

