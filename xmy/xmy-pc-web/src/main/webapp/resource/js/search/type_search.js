$(function(){
	$(".page-num").click(function () {
		$(this).addClass("active").siblings().removeClass("active")
	});
	$(".search-list-item li").click(function () {
		$(this).addClass("active").siblings().removeClass("active");
		var test = "";
		var search;
		$(".search-list-item li").each(function () {
			if($(this).attr("class")){
				var text=$(this).parent().parent().children().eq(0).text();
				var id= $(this).attr("value");
				var parent = $(this).attr("parent");
				test+="<span class='sec'><i class='iconfont seach' child='"+id+"' parent='"+parent+"' onclick='fun("+id+","+parent+")' value='"+$(this).text()+"'>"+text+":"+$(this).text()+"&#xe676;</i></span>" +
						"<i class='iconfont icondel' child='"+id+"' parent='"+parent+"' >&#xe6e8;</i>";
				search=$(this).text();
			} 
		});
		$(".sec-wrap").show();
		$(".sec-wrap").html(test);
		//alert(search);
		var content=$("#goodsName").val();
		var pageIndex=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		/*$.ajax({
			url:"elastic/typeSearch/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+search,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});*/
		$.ajax({
			url:"elastic/typeGoodsWords?words="+lowSearch()+"&typeId="+content,
			type:"post",
			success:function(data){
				$(".search-list-item li").each(function () {
					if(data.typeNames.indexOf($(this).text())<0){
						$(this).hide();
					}else{
						$(this).show();
					} 
				});
				setHeight();
			}
		})
		//查询总页数
		$.ajax({
			url:"elastic/typeSearch/countPage/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+lowSearch()+"&typeId="+content+"&wordId="+wordid,
			type:"post",
			async:false,
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages: $("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/typeSearch/"+obj.curr+"/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+lowSearch()+"&typeId="+content+"&wordId="+wordid,
					type:"post",
					success:function(data){
						$("#goods").html(data);
					}
				});
				if(obj.curr>1){
					var topTop = $("#top").offset().top;
					$('html,body').animate({scrollTop:topTop}, 500);
				}
			}
		}); 
	});
	//点击搜索事件
	$("#lowSearch").click(function () {
		var search = $("#lowContent").val();
		//alert(search);
		var content=$("#goodsName").val();
		var pageIndex=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		$.ajax({
			url:"elastic/typeGoodsWords?words="+search+"&typeId="+content,
			type:"post",
			success:function(data){
				$(".search-list-item li").each(function () {
					if(data.typeNames.indexOf($(this).text())<0){
						$(this).hide();
					}else{
						$(this).show();
					} 
				});
			}
		})
		//查询总页数
		$.ajax({
			url:"elastic/typeSearch/countPage/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+search+"&typeId="+content+"&wordId="+wordid,
			type:"post",
			async:false,
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages: $("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/typeSearch/"+obj.curr+"/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+search+"&typeId="+content+"&wordId="+wordid,
					type:"post",
					success:function(data){
						$("#goods").html(data);
					}
				});
				if(obj.curr>1){
					var topTop = $("#top").offset().top;
					$('html,body').animate({scrollTop:topTop}, 500);
				}
			}
		}); 
	});
	
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
	//回显搜索内容
	var val=$("#goodsName").val();
	var wordid=$("#word").val();
	//$("#searchContent").val(val);
	//排序方式效果添加
	$(".screen-btn").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		//$(this).css("background-color","#359812").siblings().css("background-color","");
	});
	
	//指定页数的跳转
	var priceOrder = true;//价格排序标识
	//排序筛选
	$(".screen-btn").click(function(){
		//var orderMethod=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var text = $(this).text();
		var search=lowSearch();
		if($(this).hasClass("select-price")){
			priceOrder = !priceOrder;
		}
		/*$.ajax({
			url:"elastic/typeSearch/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&typeId="+val,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});*/
		//查询总页数
		$.ajax({
			url:"elastic/typeSearch/countPage/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&typeId="+val+"&search="+search+"&wordId="+wordid,
			type:"post",
			async:false,
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages: $("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/typeSearch/"+obj.curr+"/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&typeId="+val+"&search="+search+"&wordId="+wordid,
					type:"post",
					success:function(data){
						$("#goods").html(data);
					}
				});
				if(obj.curr>1){
					var topTop = $("#top").offset().top;
					$('html,body').animate({scrollTop:topTop}, 500);
				}
			}
		}); 
	});
	//价格筛选
	$("#beginPrice").keyup(function(){
		regNum = /^[0-9]+(.[0-9]{2})?$/;
		var price=$(this).val();
		var endPrice=$("#endPrice").val();
		if(!regNum.test(price)){
			return false;
		}
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var search=lowSearch();
		/*$.ajax({
			url:"elastic/typeSearch/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice+"&typeId="+val,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});*/
		//查询总页数
		$.ajax({
			url:"elastic/typeSearch/countPage/1/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice+"&typeId="+val+"&search="+search+"&wordId="+wordid,
			type:"post",
			async:false,
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages: $("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/typeSearch/"+obj.curr+"/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice+"&typeId="+val+"&search="+search+"&wordId="+wordid,
					type:"post",
					success:function(data){
						$("#goods").html(data);
					}
				});
				if(obj.curr>1){
					var topTop = $("#top").offset().top;
					$('html,body').animate({scrollTop:topTop}, 500);
				}
			}
		}); 
	});
	
	//价格筛选
	$("#endPrice").keyup(function(){
		regNum = /^[0-9]+(.[0-9]{2})?$/;
		var price=$(this).val();
		var beginPrice=$("#beginPrice").val();
		if(!regNum.test(price)){
			return false;
		}
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var search=lowSearch();
		/*$.ajax({
			url:"elastic/typeSearch/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&beginPrice="+beginPrice+"&endPrice="+price+"&typeId="+val,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});*/
		//查询总页数
		$.ajax({
			url:"elastic/typeSearch/countPage/1/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+beginPrice+"&endPrice="+price+"&typeId="+val+"&search="+search+"&wordId="+wordid,
			type:"post",
			async:false,
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages: $("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/typeSearch/"+obj.curr+"/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+beginPrice+"&endPrice="+price+"&typeId="+val+"&search="+search+"&wordId="+wordid,
					type:"post",
					success:function(data){
						$("#goods").html(data);
					}
				});
				if(obj.curr>1){
					var topTop = $("#top").offset().top;
					$('html,body').animate({scrollTop:topTop}, 500);
				}
			}
		}); 
	});
	//查询总页数
	$.ajax({
		url:"elastic/typeSearch/countPage/1/0?typeId="+val+"&wordId="+wordid,
		type:"post",
		async:false,
		success:function(data){
			$("#countPage").val(data);
		}
	});
	laypage({
		cont: $("#biuuu_city"),
		pages: $("#countPage").val(),
		skip:true,
		jump: function(obj){
			//页面加载商品信息
			$.ajax({
				url:"elastic/typeSearch/"+obj.curr+"/0?typeId="+val+"&wordId="+wordid,
				type:"post",
				success:function(data){
					$("#goods").html(data);
				}
			});
			if(obj.curr>1){
				var topTop = $("#top").offset().top;
				$('html,body').animate({scrollTop:topTop}, 500);
			}
		}
	}); 
	setHeight();
	//显示全部的筛选条件
	$(".col-10").click(function(){
		var num = $(this).attr("num");
		if(num==0){
			$(this).prev().css("height","auto")
			$(this).attr("num","1");
			setHeight();
		}else{
			$(this).prev().css("height","40px");
			$(this).attr("num","0");
			setHeight();
		}
	});
//	//底部搜索
//	$("#sousuo").click(function(){
//		var val=$("#sousuoContent").val();
//		window.location.href = "elastic/goods?goodsName="+val;
//	})
});
function fun(text,parent){
	//alert(text+"-----"+parent);
	$(".seach").each(function(){
		var child =$(this).attr("child");
		var parents=$(this).attr("parent");
		if(child==text && parent == parents){
			$(this).parent().remove();
			$(this).parent().next().remove();
			$(this).attr("value","-1");
		}
	});
	
	$(".icondel").each(function(){
		var child =$(this).attr("child");
		var parents=$(this).attr("parent");
		if(child==text && parent == parents){
			$(this).remove();
		}
	})
	$(".col-80").children().each(function(){
		var child = $(this).attr("value");
		var parents=$(this).attr("parent");
		//alert(parents+"---"+child);
		if(child==text && parent == parents){
			$(this).removeClass("active");
		}
	})
	var content=$("#searchContent").val();
	var ordeMethod=orderMethods();
	var isdelivery = isDelivery();
	var search=lowSearch();
	var val=$("#goodsName").val();
	var wordid=$("#word").val();
	/*$.ajax({
		url:"elastic/typeSearch/1/"+ordeMethod+"?typeId="+val+"&search="+search,,
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});*/
	if(search == null){
		$(".search-list-item li").each(function () {
			$(this).show();
			setHeight();
		});
	}else{
		$.ajax({
			url:"elastic/typeGoodsWords?words="+search+"&typeId="+val,
			type:"post",
			success:function(data){
				$(".search-list-item li").each(function () {
					if(data.typeNames.indexOf($(this).text())<0){
						$(this).hide();
					}else{
						$(this).show();
					} 
				});
				setHeight();
			}
		})
	}
	//查询总页数
	$.ajax({
		url:"elastic/typeSearch/countPage/1/"+ordeMethod+"?typeId="+val+"&search="+search+"&wordId="+wordid,
		type:"post",
		async:false,
		success:function(data){
			$("#countPage").val(data);
		}
	});
	laypage({
		cont: $("#biuuu_city"),
		pages: $("#countPage").val(),
		skip:true,
		jump: function(obj){
			//页面加载商品信息
			$.ajax({
				url:"elastic/typeSearch/1/"+ordeMethod+"?typeId="+val+"&search="+search+"&wordId="+wordid,
				type:"post",
				success:function(data){
					$("#goods").html(data);
				}
			});
			if(obj.curr>1){
				var topTop = $("#top").offset().top;
				$('html,body').animate({scrollTop:topTop}, 500);
			}
		}
	}); 
}

//获取剩余的筛选条件
function lowSearch(){
	var text;
	$(".seach").each(function(){
		if($(this).attr("value").trim()!=-1 && $(this).attr("value").trim()!="undefined"){
			text+=","+$(this).attr("value").trim();
		}
	});
	return text;
}

//获取排序方式的方法
function orderMethods(){
	var orderMthod;
	$(".screen>button").each(function(){
		if($(this).hasClass("active")){
			orderMthod=$(this).attr("value");
		}
	});
	return orderMthod;
}
//获取配送方法
function isDelivery(){
	var delivery = -1;
	$(".area>button").each(function(){
		if($(this).hasClass("active")){
			delivery=$(this).attr("value");
		}
	});
	return delivery;
}

//计算高度
function setHeight(){
	$(".add-key").each(function () {
		var a = $(this).siblings(".col-80").height();
		$(this).css({"height":a,"line-height":a+'px'})
	});
	$(".col-80").each(function(){
		var count = 0;
		$(this).children().each(function(){
			var css=$(this).css("display");
			if(css!="none"){
				count++;
			}
		})
		if(count<=9){
			$(this).parent().children().last().hide();
		}else{
			$(this).parent().children().last().show();
		}
		if(count==0){
			$(this).parent().hide();
		}else{
			$(this).parent().show();
		}
	});
}
//下一页事件绑定
function nextPage(index){
	//页面加载商品信息
	$.ajax({
		url:"elastic/typeSearch/"+index+"/0",
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});
};
