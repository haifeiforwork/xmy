require(["common"],function(){
	
	$(".pull-left").click(function(){
		window.location.href="home/home";
	});
	
	//初始化加载底部公共JS
	$.initFooterJS() ;
	
	//初始化子分类
	loadClassfySecond($(".weui-cells .classfy-first").filter(".active").attr("termDat-id")) ;
	
/*	$(".class-advise").click(function() {
		if($(this).attr("type") && parseInt($(this).attr("type"))) {
			location.href = "activity/" + $(this).attr("data");
		} else {
			location.href = "goods/goodsInfo?goodsId=" + $(this).attr("data");
		}
	});*/
	
	//选择一级分类
	$(".weui-cells").on("click",".classfy-first",function(){
		$(this).addClass("active").siblings().removeClass("active") ;
		$(".icon-off").show() ;
		$(".icon-on").hide() ;
		$(this).find(".icon-on").show() ;
		$(this).find(".icon-off").hide() ;
		loadClassfySecond($(this).attr("termDat-id")) ;
	}) ;
	
	//加载分类二级菜单
	function loadClassfySecond(id){
		var data = {parentId : id} ;
		$.commonAjax(data,"classfy/classfySecond","text",function(data){
			$(".banner").remove() ;
			$(".classfy-second").remove() ;
			$(".container").append(data) ;
			$(".classfy-second").off('click', ".goods-classfy");
			//分类点击事件
			$(".classfy-second").on("click",".goods-classfy",function(){
				location.href = "goods/searchGoods?wordId=" + $(this).attr("word-id") + "&typeId=" + $(this).parent().prev().children(".more").attr("category_id") ;
			}) ;
		}) ;
	}
	
	//分类点击事件
	$(".classfy-second").on("click",".goods-classfy",function(){
		location.href = "goods/searchGoods?wordId=" + $(this).attr("word-id") + "&typeId=" + $(this).parent().prev().children(".more").attr("category_id") ;
	}) ;
	

    $(function () {
        //导航
        var navCell = $(".top-nav .weui-cell");
        $(navCell).click(function () {
            $(this).addClass("active").siblings().removeClass("active")
            $(".top-nav").removeClass("js-top-nav");
            $(".top-nav .nav-title").hide();
            $(".drow").show();
            $(".top-nav .weui-cells").removeClass("js-cells")
            navCell.removeClass("col-33")
        })
        //初始状态点击下拉
        $(".drow").click(function () {
            $(".top-nav").addClass("js-top-nav");
            $(".top-nav .nav-title").show();
            $(this).hide();
            $(".top-nav .weui-cells").addClass("js-cells")
            navCell.addClass("col-33").removeClass("active")
        })
        //点击收起下拉导航
        $(".drow-up").click(function () {
            $(".top-nav").removeClass("js-top-nav");
            $(".top-nav .nav-title").hide();
            $(".drow").show();
            $(".top-nav .weui-cells").removeClass("js-cells")
            navCell.removeClass("col-33")
        })//导航结束

        /*选取大于1的列表负上边距*/
      /*  $(".list-wrap .container-wrap:gt(0)").css("margin-top","-0.5rem")*/
    }) ;
    
    //搜索商品
	$(".search").click(function(){
		location.href = "goods/searchGoodsByName" ;
	}) ;
	
	//加载更多
	$(".content").on("click",".more",function(){
		location.href = "goods/searchGoods?typeId=" + $(this).attr("category_id") ;
	}) ;
	
	//广告链接
	$(".content").on("click",".class-advise",function(){
		if($(this).attr("type") == 0){
			if($.isPutaway($(this).attr("url"))){
				$.alert("该商品已下架") ;
				return ;
			}
			location.href = "goods/goodsInfo?goodsId=" + $(this).attr("url") ;
			return ;
		}else{
			location.href = $(this).attr("url") ;
			return ;
		}
	}) ;
	
});