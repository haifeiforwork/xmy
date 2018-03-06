$(function($){
	window.setInterval(function(){ShowCountDown($("#endTime").val(),'#time');}, interval);
	
	var isPutway = $("#isPutway").val(); //商品上下架状态
	$(".front").click(function(){
    	if($(".classify-ul").hasClass("show")){
    		$(".classify-ul").slideUp().removeClass("show");
    	}else{
    		$(".classify-ul").slideDown().addClass("show");
    	}
    });
    $(".spec").on("click",".etalon",function(){
    	$(this).addClass("cur").siblings().removeClass("cur");
    });
    $(document).ready(function(){
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
	})
    //商品描述以及旁边的tab切换
    $(".tab-contentt>div:gt(0)").hide();
    $(".tab-navv li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".tab-navv li").index(this);
        $("div.tab-contentt>div").eq(index).show().siblings().hide()
    });

	//第二个tab切换
    $(".tab-content-sec>div:gt(0)").hide();
    $(".tab-sec li").hover(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".tab-sec li").index(this);
        $("div.tab-content-sec>div").eq(index).show().siblings().hide();
    });

    //点击左右切换
	/*var n = 1, m = n , str = ""
		//list2 = [],
		item = $(".you-like .list-li");
	$(".next").click(function () {
	    if (n+4 > item.length) return ;
	    var m = n , str = "";
	    for(m;m<n+4;m++){
            str += "<li class='list-li'>" + item.eq(m).html() +"</li>";
		}
        n+4 >= item.length ? item.length : n++;
		$(".you-like .list-ul").html(str) ;
    });

    $(".before").click(function () {
        if (n < 0) return ;
        var m = n , str = "";
        for(m;m<n+4;m++){
            str += "<li class='list-li'>" + item.eq(m).html() +"</li>";
        }
        n <= 0 ? 0 : n--;
        $(".you-like .list-ul").html(str) ;
    })*/
	$(".front2").click(function(){
        if($(this).hasClass("add-add-css")){
            $(this).removeClass("add-add-css");
            $(".classify-ul").stop();
            $(".classify-ul").slideUp();
        }else{
            $(this).addClass("add-add-css");
            $(".classify-ul").slideDown();
        }
    });
    //加入购物车
    $(".add-cart").click(function(){
    	if (isPutway == 1) {
    		warnPop("该商品已下架暂不能购买！");
    		return ;
    	}
    	var goodsnum = $("#goodsNum").val();//得到要加入购物车的数量
    	var reg = /^\+?[1-9]\d*$/;
		if (!reg.test(goodsnum)) {
			$("#goodsNum").val(1)
			warnPop("请输入正确的数量");
			return ;
		}
    	var goodsId = $(this).attr("value");
    	var activityId=$("#actiId").val();//活动id
    	var actId=$("#actId").val();
    	var points=$("#points").val(); //商品积分
    	var pointsGoodsId=$("#pointsGoodsId").val();//积分商品id
    	var relust=$("#relust").val();
    	var accPoints=$("#accPoints").val();  //用户积分
    	var date=$("#endTime").val();
    	var endTime=new Date(date);
    	if(relust==1){
    		warnPop("需登录才能购买");
    	}else if(parseInt(accPoints)<parseInt(points)*goodsnum){
    		warnPop("积分不足");
    	}else if(activityId==0){//该商品没有活动的时候
    		$.ajax({
    			url : "cart/addPoints/"+goodsnum+"/"+pointsGoodsId,
    			type : "get",
    			success:function(data){
    				if (data == 0 ) {  //添加成功
    					shoppingCart();
    					warnPop("商品已添加到购物车！");
    				}
    				if (data == 1) {  
    					warnPop("购物车已存在该商品");
    				}
    				if (data  == 2) {
    					warnPop("您购买的数量超出了活动的限购！");
    				}
    				if(data==52){
    					warnPop("拼购商品只能单独购买，请先结算购物车其他商品后继续购买。");
    				}else if(data==53){
    					warnPop("购物车中包含拼购商品，请先结算购物车商品后再购买。");
    				}else if(data==54){
    					warnPop("亲~您已经购买过或购物车已包含买三免一商品，不能重复购买哦！");
    				}else if(data==77){
    					warnPop("该用户已经购买了拼购商品！");
    				}else if(data==78){
    					warnPop("该用户已经购买了三免一商品！");
    				}
    			}
    		});
    	}
    });
    //直接购买
    $("#shopping").click(function(){
    	if (isPutway == 1) {
    		warnPop("该商品已下架暂不能购买！");
    		return ;
    	}
    	var goodsnum = $("#goodsNum").val();//得到要加入购物车的数量
    	var goodsId = $("#goodsId").attr("value");
    	var points=$("#points").val();
    	var pointsGoodsId=$("#pointsGoodsId").val();//积分商品id
    	var relust=$("#relust").val();
    	var actId=$("#actId").val();
    	var accPoints=$("#accPoints").val();
    	if(relust==1){
    		warnPop("请先登录");
    	}else if(parseInt(accPoints)<parseInt(points)*goodsnum){
    		warnPop("积分不足");
    	}else{
    		$.ajax({
        		url : "cart/addPoints/"+goodsnum+"/"+pointsGoodsId,
        		type : "get",
        		success:function(data){
    				if(data == 0){//添加成功
    					window.location.href = "/cart/shoppingCart";
    				}else if(data==52){
    					warnPop("拼购商品只能单独购买，请先结算购物车其他商品后继续购买。");
    				}else if(data==53){
    					warnPop("购物车中包含拼购商品，请先结算购物车商品后再购买。");
    				}else if(data==54){
    					warnPop("亲~您已经购买过或购物车已包含买三免一商品，不能重复购买哦！");
    				}else if(data==77){
    					warnPop("该用户已经购买了拼购商品！");
    				}else if(data==78){
    					warnPop("该用户已经购买了三免一商品！");
    				}
    			}
        	});
    	}
    });
    
});
//异步请求评论类容
function getComment(goodsId,pageIndex){
	if(pageIndex != 1){
		pageIndex =parseInt($("#pageIndex").val())+1;
	}
	 $.ajax({
 		url: "goods/comment/list/"+goodsId+"/"+pageIndex,
 		type: "get",
 		success: function(data){
 					$("#comment").html(data);
 		   		}
 	});
}

//提示弹框
function warnPop(text) {
	var btnFn = function(){
		easyDialog.close();
		return false;
	}
	easyDialog.open({
	  container : {
	    header : "提示",
	    content : text,
	    yesFn : btnFn
	  }
	});
}

//异步请求销售记录
function getDeal(goodsId,pageIndex){
	if(pageIndex != 1){
		pageIndex =parseInt($("#pageNo").val())+1;
	}
	 $.ajax({
 		url: "goods/deal/list/"+goodsId+"/"+pageIndex,
 		type: "get",
 		success: function(data){
 					$("#deal").html(data);
 		   		}
 	});
}
//倒计时
var interval = 1000; 
function ShowCountDown(date,divname) 
{ 
	var now = new Date(); 
	var endDate = new Date(date);
	//alert(date);
	var leftTime=endDate.getTime()-now.getTime();
	var leftsecond = parseInt(leftTime/1000); 
	//var day1=parseInt(leftsecond/(24*60*60*6)); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
	$(divname).text("优惠还剩"+day1+"天"+hour+"时"+minute+"分"+second+"秒结束");
	//"脚本之家提示距离"+year+"年"+month+"月"+day+"日还有："+day1+"天"+hour+"小时"+minute+"分"+second+"秒"
} 