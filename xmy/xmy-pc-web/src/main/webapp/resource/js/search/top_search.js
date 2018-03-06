$(function(){
	$(".page-num").click(function () {
		$(this).addClass("active").siblings().removeClass("active")
	});
	$(".search-list-item li").click(function () {
		$(this).addClass("active").siblings().removeClass("active");
		var test = "";
		$(".search-list-item li").each(function () {
			if($(this).attr("class")){
				var text=$(this).parent().parent().children().eq(0).text();
				var id= $(this).attr("value");
				var parent = $(this).attr("parent");
				test+="<span class='sec'><i class='iconfont seach' child='"+id+"' parent='"+parent+"' onclick='fun("+id+","+parent+")' type='"+text+"' value='"+$(this).text()+"'>"+text+":"+$(this).text()+"&#xe676;</i></span>" +
						"<i class='iconfont icondel' child='"+id+"' parent='"+parent+"' >&#xe6e8;</i>";
				search=$(this).text();
			} 
		});
		$(".sec-wrap").show();
		$(".sec-wrap").html(test);
		var content=$("#goodsName").val();
		var pageIndex=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var string=typeSearch();//二级分类的id
		var search=lowSearch();
		
		$.ajax({
			url:"elastic/typeGoodsWords?words="+lowSearch()+"&oneId="+content,
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
		});
		//查询总页数
		$.ajax({
			url:"elastic/topSearchCountPage/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+search+"&oneId="+content+"&typeName="+string,
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
					url:"elastic/topSearch/"+obj.curr+"/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+search+"&oneId="+content+"&typeName="+string,
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
	//点击搜索
	$("#lowSearch").click(function () {
		var content=$("#goodsName").val();
		var pageIndex=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var string=typeSearch();//二级分类的id
		var search=$("#lowContent").val();
		//查询总页数
		$.ajax({
			url:"elastic/topSearchCountPage/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+search+"&oneId="+content+"&typeName="+string,
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
					url:"elastic/topSearch/"+obj.curr+"/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&search="+search+"&oneId="+content+"&typeName="+string,
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
	var val=$("#goodsName").val();//一级分类ID
	
	//$("#searchContent").val(val);
	//排序方式效果添加
	$(".screen-btn").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		//$(this).css("background-color","#359812").siblings().css("background-color","");
	});
	
	//价格排序标识
	var priceOrder = true;
	//排序筛选
	$(".screen-btn").click(function(){
		//var orderMethod=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var text = $(this).text();
		var search=lowSearch();
		var string=typeSearch();//二级分类的id
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
			url:"elastic/topSearchCountPage/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&oneId="+val+"&search="+search+"&typeName="+string,
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
					url:"elastic/topSearch/"+obj.curr+"/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&oneId="+val+"&search="+search+"&typeName="+string,
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
		var string=typeSearch();//二级分类的id
		/*$.ajax({
			url:"elastic/typeSearch/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice+"&typeId="+val,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});*/
		//查询总页数
		$.ajax({
			url:"elastic/topSearchCountPage/1/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice+"&oneId="+val+"&search="+search+"&typeName="+string,
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
					url:"elastic/topSearch/"+obj.curr+"/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice+"&oneId="+val+"&search="+search+"&typeName="+string,
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
		var string=typeSearch();//二级分类的id
		/*$.ajax({
			url:"elastic/typeSearch/1/"+orderMethod+"?priceOrder="+priceOrder+"&isDivle="+isdelivery+"&beginPrice="+beginPrice+"&endPrice="+price+"&typeId="+val,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});*/
		//查询总页数
		$.ajax({
			url:"elastic/topSearchCountPage/1/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+beginPrice+"&endPrice="+price+"&oneId="+val+"&search="+search+"&typeName="+string,
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
					url:"elastic/topSearch/"+obj.curr+"/"+orderMethod+"?isDivle="+isdelivery+"&beginPrice="+beginPrice+"&endPrice="+price+"&oneId="+val+"&search="+search+"&typeName="+string,
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
		url:"elastic/topSearchCountPage/1/0?oneId="+val,
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
				url:"elastic/topSearch/"+obj.curr+"/0?oneId="+val,
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
	setHeight();
//	//底部搜索
//	$("#sousuo").click(function(){
//		var val=$("#sousuoContent").val();
//		window.location.href = "elastic/goods?goodsName="+val;
//	})
});
function fun(text,parent){
	$(".seach").each(function(){
		var child =$(this).attr("child");
		var parents=$(this).attr("parent");
		if(child==text && parent == parents){
			$(this).parent().remove();
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
	var string=typeSearch();//二级分类的id
	var val=$("#goodsName").val();//一级ID
	$.ajax({
		url:"elastic/typeGoodsWords?words="+lowSearch()+"&oneId="+val,
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
	});
	
	//查询总页数
	$.ajax({
		url:"elastic/topSearchCountPage/1/"+ordeMethod+"?oneId="+val+"&search="+search+"&typeName="+string,
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
				url:"elastic/topSearch/1/"+ordeMethod+"?oneId="+val+"&search="+search+"&typeName="+string,
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
	var text
	$(".seach").each(function(){
		if($(this).attr("value").trim()!=-1){
			text += ","+$(this).attr("value");
		}
	});
	return text;
}
//二级分类
function typeSearch(){
	var text
	$(".seach").each(function(){
		if($(this).attr("value").trim()!=-1){
			if($(this).attr("type").trim()=="分类"){
				text = $(this).attr("value");
			}
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
