require(["common"],function(){
	
	var repeat = false ;
	
	//初始化加载底部公共JS
	$.initFooterJS() ;
	
	//后退
	$(".page-return").click(function(){
		window.history.go(-1);
	}) ;
	
	$("#imgword-info .pull-left").click(function(){
		$.closePopup() ;
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
    	var isActivity = $.judgeActivityGoods($("#goodsId"));
    	if(isActivity){
    		return ;
    	}
        var n=$(this).prev().val();
        var num=parseInt(n)+1;
        if( ($("#limitNum").val() && $("#limitNum").val() <= 1) && (num > $("#limitNum").val() || num > $("#leftNum").val())){
        	$.alert("购买数量超限") ;
    		return ;
    	}
        if(num==0){ return;}
        $(this).prev().val(num);
    });
    
    //减的效果
    $(".jian").click(function(){
    	var isActivity = $.judgeActivityGoods($("#goodsId"));
    	if(isActivity){
    		return ;
    	}
        var n=$(this).next().val();
        var num=parseInt(n)-1;
        if( ($("#limitNum").val() && $("#limitNum").val() <= 1) && (num > $("#limitNum").val() || num > $("#leftNum").val())){
        	$.alert("购买数量超限") ;
    		return ;
    	}
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
    	var data = {goodsId : $("#goodsId").val(),goodsNum : $("#goodsNum").val(), num : $("#goodsNum").val(), 
    				activityId : "", activityType : "", actId : ""}, url = "shopping/addShopping" ;
    	if($("#activityId").val() != 0){ 
    		url = "shopping/addActivityShopping" ;
    		data.activityId = $("#activityId").val() ;
    		data.activityType = $("#activityType").val() ;
    	}
    	if($("#pointsId").val()) {
    		console.log("是积分商品");
    		data.actId = $("#pointsId").val() ;
    	} 
    	if(!repeat){
    		repeat = true ;
    		$.commonAjax(data,"shopping/judgeNewActivityGoods","json",function(datas){
    			if(datas.data){
    				$.alert(datas.data);
    				repeat = false ;
    			}else{
    				$.commonAjax(data,url,"json",function(data){
    	    			repeat = false ;
    	    			if(data.data == 0 ){
    			    		tag = $(".shoppingCartCount");
    			    		$.commonAjax(null, "shopping/getShoppingCartCount", 'json', function(datas) {
    							if(datas && datas.obj != 0) {
    								var obj = $("#shoppingCartCount") ;
    								if(obj.length) {
    									if(datas.obj < 99) {
    										$("#shoppingCartCount").html(datas.obj);
    									} else {
    										$("#shoppingCartCount").html(99);
    									}
    								} else {
    									tag = $(".shoppingCartCount");
    									if(tag)
    										tag.prepend($("<span id='shoppingCartCount' class='weui-badge' style='position: absolute;top: -.4em;right: 1.5em;'>" + (datas.obj > 99 ? 99 : datas.obj)  + "</span>"))
    								}
    							}
    						});
    	    				$.toast("成功加入购物车",500);
    	    			}else if(data.data == 1){
    	    				$.alert("购买次数超限") ;
    	    			}else if(data.data == 2){
    	    				$.alert("购买数量超限") ;
    	    			}else if(data.data == 3){
    	    				$.alert("购物车已存在该商品") ;
    	    			}
    	        	}) ;
    			}
    		});
    	}
    }) ;
    
    /* 2017-11-2*/
    $(".img-word").click(function () {
        $("#imgword-info").popup();
    });

   
    
    
    $(".lump .down").click(function () {
        $(this).toggleClass("active").find("img").toggleClass("open")
        $(this).parent().toggleClass("active").find(".lump-info").toggleClass("lump-infoac");
        $(".other-info").toggle() ;
    })
    
    //选择商品规格
    $(".message").on("click",".goods-standards",function(){
    	
	    if($.isPutaway($(this).attr("goods-id"))){
	    	$.alert("该商品已下架") ;
	    	return ;
	    }
    	
    	location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") ;
    }) ;
    
    //商品推荐
    $(".you-like li").click(function(){
    	if($.isPutaway($(this).attr("goods-id"))){
			$.alert("该商品已下架") ;
			return ;
		}
    	location.href = "goods/goodsInfo?goodsId=" + $(this).attr("goods-id") ;
    }) ;
    
    //冰点价（每日秒杀）
    limitTime() ;

    function limitTime(){
    	var leftTime, now = new Date(), endDate = new Date() ;
    	
    	if($("#nowTime").val() && $("#endTime").val()){
    		leftTime = $("#endTime").val() - $("#nowTime").val() ;
    	}else{
    		if(endDate.getHours()>=12){
    			var oneDay =  1000*60*60*24;
    			endDate=new Date(endDate+oneDay);//明天的时间
    			endDate.setHours(12, 0, 0, 0);
    		}else{
    			endDate.setHours(12, 0, 0, 0);
    		}
    		leftTime = endDate.getTime()-now.getTime();
    	}
    	window.setInterval(function(){
    		leftTime -= 1000 ;
    		var leftsecond = parseInt(leftTime/1000); 
    		var day1=Math.floor(leftsecond/(60*60*24)); 
    		var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
    		var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
    		var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
    		$(".time-esc").html('<img src="resources/common/images/ico-lock.png" alt="">优惠还剩   <span>'+ (hour >= 10 ? hour : ("0"+hour)) +' 小时 '+ (minute >= 10 ? minute : ("0"+minute)) +' 分 '+  (second >= 10 ? second : ("0"+second)) +'秒结束</span>');
    	}, 1000);
      }
    
    //天天特价
    resetTime();

    function resetTime(){
    	var leftTime=$("#resetTime").val();
    	window.setInterval(function(){
    		leftTime-=1000;
    		var leftsecond = parseInt(leftTime/1000);
    		var hour = Math.floor(leftsecond / 60 / 60 % 60);
    		var minute =Math.floor( leftsecond / 60 % 60);
    		var second = Math.floor(leftsecond % 60);
    		$(".time-esc1").html('<img src="resources/common/images/ico-lock.png" alt="">优惠还剩   <span>'+ (hour >= 10 ? hour : ("0"+hour)) +' 小时 '+ (minute >= 10 ? minute : ("0"+minute)) +' 分 '+  (second >= 10 ? second : ("0"+second)) +'秒结束</span>');
    	}, 1000);
    }


    //每周特价
    weekTime();

    function weekTime(){
    	var leftTime=$("#weekTime").val();
    	window.setInterval(function(){
    		leftTime-=1000;
    		var leftsecond = parseInt(leftTime/1000); 
    		var day1=Math.floor(leftsecond/60/60/24); 
    		var hour = Math.floor(leftsecond/60/60%24);
    		var minute =Math.floor( leftsecond/60%60);
    		var second = Math.floor(leftsecond%60);
    		$(".time-esc2").html('<img src="resources/common/images/ico-lock.png" alt="">优惠还剩   <span>'+ day1 +' 天 '+ (hour >= 10 ? hour : ("0"+hour)) +' 小时 '+ (minute >= 10 ? minute : ("0"+minute)) +' 分 '+  (second >= 10 ? second : ("0"+second)) +'秒结束</span>');
    	}, 1000);
    }
    
  //买及赠
    buyAndPresent();

    function buyAndPresent(){
    	if($(".buyAndPresent-endTime").length > 0){
    		var endTime = new Date($(".buyAndPresent-endTime").val());
    		var leftTime = endTime.getTime() - $("#nowTime").val();
    		window.setInterval(function(){
    			leftTime-=1000;
    			var leftsecond = parseInt(leftTime/1000); 
    			var day1=Math.floor(leftsecond/60/60/24); 
    			var hour = Math.floor(leftsecond/60/60%24);
    			var minute =Math.floor( leftsecond/60%60);
    			var second = Math.floor(leftsecond%60);
    			$(".time-esc3").html('<img src="resources/common/images/ico-lock.png" alt="">优惠还剩  <span>'+ day1 +'天'+ (hour >= 10 ? hour : ("0"+hour)) +'小时 '+ (minute >= 10 ? minute : ("0"+minute)) +'分'+  (second >= 10 ? second : ("0"+second)) +'秒结束</span>');
    		}, 1000);
    	}
    }
});