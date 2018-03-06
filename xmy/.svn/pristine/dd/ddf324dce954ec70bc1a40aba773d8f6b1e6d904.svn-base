require(["common"],function(){
	
	var repeat = false ;
	
	//初始化加载底部公共JS
	$.initFooterJS() ;
	
	//后退
	$(".pull-left").click(function(){
		location.href = document.referrer ;
	}) ;
	
	//收藏
	$(".collect").click(function(){
		var data = {goodsId : $("#goodsId").val(), isCollect : 0}, msg = "收藏成功" ;
        if($(this).hasClass("active")){
            $(this).find(".img").attr("src","resources/common/images/btn-spxq-wsc@2x.png");
            $(this).removeClass("active");
            data.isCollect = 1 ;
            msg = "取消收藏" ;
        }else{
            $(this).find(".img").attr("src","resources/common/images/btn-spxq-sc@2x.png");
            $(this).addClass("active");
        }
        $.commonAjax(data,"collectionGoods/addOrCancelCollection","json",function(data){
        	$.alert(msg) ;
        }) ;
    });
	
    $(".chunk-ul").on("click",".chunk-li",function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
    
    //加的效果
    $(".add").click(function(){
        var n=$(this).prev().val();
        var num=parseInt(n)+1;
        if(num==0){ return;}
        $(this).prev().val(num);
    });
    
    //减的效果
    $(".jian").click(function(){
        var n=$(this).next().val();
        var num=parseInt(n)-1;
        if(num==0){ return}
        $(this).next().val(num);
    });
    
    //轮播图
    $('.flexslider').flexslider({
        animation       :   "slide",             // 动画效果类型，有"fade"：淡入淡出，"slide"：滑动"fade"
        easing          :   "swing",             // 内容切换时缓动效果，需要jquery easing插件支持"swing"
        direction       :   "horizontal",          // 内容滚动方向，有"horizontal"：水平方向 和"vertical"：垂直方向"horizontal"
        animationLoop   :   true,                // 是否循环滚动true
        startAt         :   0,                   // 初始滑动时的起始位置，定位从第几个开始滑动0
        slideshow       :   true,                // 是否自动滑动true
        slideshowSpeed  :   5000,                // 滑动内容展示时间（ms）7000
        animationSpeed  :   600,                 // 内容切换时间（ms）600
        initDelay       :   0,                   // 初始化时延时时间0
        pauseOnHover    :   false,               // 鼠标滑向滚动内容时，是否暂停滚动false
        touch           :   true,                // 是否支持触摸滑动true
        directionNav    :   true,                // 是否显示左右方向箭头按钮true
        keyboard        :   true,                // 是否支持键盘方向键操作true
        minItems        :   1,                   // 一次最少展示滑动内容的单元个数1
        maxItems        :   0,                   // 一次最多展示滑动内容的单元个数0
        move            :   0                    // 一次滑动的单元个数0
    });
    
    //添加进购物车
    $(".add-shopping").click(function(){
    	var data = {goodsId : $("#goodsId").val(),goodsNum : $("#goodsNum").val(),actId : $("#actId").val(),points : $("#point").val()};
    	if(!repeat){
    		repeat = true ;
    		$.commonAjax(data,"shopping/addPointsShopping","json",function(data){
    			repeat = false ;
    			if(data == 2){ 
    				$.alert("超过了活动的限购") ;
    				return ;
    			}
    			$.alert("商品已添加到购物车") ;
        	}) ;
    	}
    }) ;
    
    $(".addActivityShopping").click(function() {
    	data = {};
    	data["goodsId"] = $("#goodsId").val();
    	data["goodsNum"] = $("#goodsNum").val();
    	if($("#actId").val())
    		data["actId"] = $("#actId").val();
    	if($("#typeId").val())
    		data["activityType"] = $("#typeId").val();
    	
    	$.ajax({
    		url : "shopping/addPointsShopping",
    		data : data,
    		type : "post",
    		dataType : "text",
    		success : function(data) {
    			$.alert("添加成功");
    		},
    		error : function(msg) {
    			$.alert("服务器繁忙");
    		}
    	});
    	
    });
    
    //选择商品规格
    $(".message").on("click",".goods-standards",function(){
/*    	if($.isPutaway($(this).attr("goods-id"))){
			$.alert("该商品已下架") ;
			return ;
		}*/
    	location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") ;
    }) ;
    
});