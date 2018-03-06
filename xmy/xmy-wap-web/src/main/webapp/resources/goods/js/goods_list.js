require(["common"],function(){
	
	 //点击icon出现搜索栏以及其他
    $(".search").click(function () {
        $(".search-input").show();
        $(".title-word").hide();
        $(".header-top").addClass("header-top-bg");
        $(this).hide();
    })
    
    //初始化加载商品列表
    loadGoodsList() ;
    
    //搜索焦点失去返回原状态
    $(".search-input").blur(function () {
        $(".search-input").hide();
        $(".title-word").show();
        $(".header-top").removeClass("header-top-bg");
        $(".search").show();
    });

    //滚动加载
    var loading = false;  //状态标记
    $(".content").infinite().on("infinite", function() {
        if(loading) return;
        loading = true;
        if($("#pageIndex").val()*10 <= $(".goods-div").length){
        	$(".weui-loadmore").show() ;
            loadGoodsList() ;
        }
    });

    //导航
    $(".weui-flex__item").click(function () {
        $(this).addClass("active").siblings().removeClass("active")
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
    
    $(".img-wrap").click(function () {
        if($(this).hasClass("down")){
            $(this).removeClass("down").addClass("up")
            $(this).parent().siblings().slideDown();
        }else {
            $(this).removeClass("up").addClass("down")
            $(this).parent().siblings().slideUp();
        }
    });
    
    //加载商品列表
    function loadGoodsList(){
    	var data = {typeId : $("#typeId").val(), pageIndex : $("#pageIndex").length >= 1 ? parseInt($("#pageIndex").val(),10) + 1 : 1} ;
    	$.commonAjax(data,"goods/goodsPage","text",function(data){
    		$("#pageIndex").remove() ;
    		$(".goods-list").append(data) ;
    		$(".weui-loadmore").hide() ;
    	}) ;
    };
    
    //跳转商品详情页面
    $(".goods-list").on("click",".goods-div",function(){
    	if($.isPutaway($(this).attr("goods-id"))){
			$.alert("该商品已下架") ;
			return ;
		}
    	location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") ;
    }) ;
});
