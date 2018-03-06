$(function($){
	
	//隐藏菜单
	
	//点击是显示出来
	$(".front").hover(function(){
		$(".classify-ul-all").toggle();
	});
	//添加背景色
	$(".dNav").css("background-color","#358912");
	$(".dNav .list .list-ul").css("background-color","#358912").css("color","#fff");
	$(".dNav .list .list-ul .cross").css("color","#d6da1d");
	$(".dNav .list .list-ul .front").css("background-color","#2d770e");
	
	$(".menu").hover(function(){
		$(this).css("background","rgba(104, 208, 61, 0.5)").siblings().css("background","rgb(53, 137, 18)");
	})
	
	$(".list-content .count-title").each(function(){
		var wrapW = $(this).children().first().width();
		var res = $(this).width() - wrapW;
		$(this).find(".ct1-line").css("width" , res-50)
	})
	var eq = 0;
	// 点击添加到购物车
	$(".gcar").click(function (event) {
			eq+=1;
			var goodsId = $(this).data("id");
			if(eq==1){
				$.ajax({
					url : "cart/add/"+goodsId+"/"+1,
					type : "get",
					success:function(data){
						if(data == 0){//添加成功
							shoppingCart();
							eq = 0;
							//弹窗的确定按钮
							var btnFn = function(){
								easyDialog.close();
							  return false;
							};
							easyDialog.open({
							  container : {
							    header : '成功',
							    content : '商品已添加到购物车！',
							    yesFn : btnFn
							  }
							});
						}else{//商品已存在
							var btnFn = function(){
									easyDialog.close();
									eq = 0;
								  return false;
								};
								easyDialog.open({
								  container : {
								    header : '成功',
								    content : '商品已添加到购物车！',
								    yesFn : btnFn
								  }
								});
						}
					}
				});
			}else{
				var btnFn = function(){
						easyDialog.close();
						eq = 0;
					  return false;
					};
					easyDialog.open({
					  container : {
					    header : '成功',
					    content : '商品已添加到购物车！',
					    yesFn : btnFn
					  }
					});
			}
		
	return false;
	})
});