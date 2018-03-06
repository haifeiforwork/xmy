$(function(){
	var conpage=0;
	$(".page-num").click(function () {
		$(this).addClass("active").siblings().removeClass("active")
	});
	//点击当个分页
	$(".search-list-item li").click(function () {
		$(this).addClass("active").siblings().removeClass("active");
		var test = "";
		var search;
		$(".search-list-item li").each(function () {
			if($(this).attr("class")){
				var text=$(this).parent().parent().children().eq(0).text();
				var id= $(this).attr("value");
				var parent = $(this).attr("parent");
				test+="<span class='sec'><i class='iconfont seach' child='"+id+"' chid='"+$(this).attr("chid")+"' parent='"+parent+"' onclick='fun("+id+","+parent+")' value='"+$(this).text()+"'>"+text+":"+$(this).text()+"&#xe676;</i></span>" +
						"<i class='iconfont icondel' child='"+id+"' parent='"+parent+"' >&#xe6e8;</i>";
				search=$(this).text();
			} 
		});
		$(".sec-wrap").show();
		$(".sec-wrap").html(test);
		var content=$("#searchContent").val();
		var pageIndex=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var typeName = $("#typeName").val();
		var mianLan=$("#mianLan").val();//是否全国配送
		if(mianLan == 0 && mianLan!=""){
			isdelivery = 0;
		}
		$.ajax({
			url:"elastic/goods/searchDto?goodsName="+val+"&mainLand="+isdelivery,
			type:"post",
			data: {typeName:lowSearch()},
			success:function(data){
				console.log(data.typeNames);
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
			url:"elastic/goods/countPage/1/"+orderMethod+"?mianLand="+isdelivery,
			type:"post",
			async:false,
			data: {"goodsName":val,"typeName":typeName,"wordSeg":lowSearch()},
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages:$("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/goods/list/"+obj.curr+"/"+orderMethod+"?mianLand="+isdelivery,
					type:"post",
					data: {"goodsName":val,"typeName":typeName,"wordSeg":lowSearch()},
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
	
	
	//点击当个分页
	$("#lowSearch").click(function () {
		var search = $("#lowContent").val();
		var content=$("#searchContent").val();
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var typeName = $("#typeName").val();
		var mianLan=$("#mianLan").val();//是否全国配送
		if(mianLan == 0 && mianLan!=""){
			isdelivery = 0;
		}
		$.ajax({
			url:"elastic/goods/searchDto?goodsName="+val+"&mainLand="+isdelivery,
			type:"post",
			data: {typeName:search},
			success:function(data){
				console.log(data.typeNames);
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
			url:"elastic/goods/countPage/1/"+orderMethod+"?mianLand="+isdelivery,
			type:"post",
			async:false,
			data: {"goodsName":val,"typeName":typeName,"wordSeg":search},
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages:$("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/goods/list/"+obj.curr+"/"+orderMethod+"?mianLand="+isdelivery,
					type:"post",
					data: {"goodsName":val,"typeName":typeName,"wordSeg":search},
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
	var typeName=$("#typeName").val();
	var mianLan=$("#mianLan").val();//是否全国配送
	$("#searchContent").val(val);
	//排序方式效果添加
	$(".screen-btn").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		//$(this).css("background-color","#359812").siblings().css("background-color","");
	});
	
	var priceOrder = true;//价格排序标识
	//排序筛选
	$(".screen-btn").click(function(){
		//var orderMethod=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var search=lowSearch();
		var text = $(this).text();
		if($(this).hasClass("select-price")){
			priceOrder = !priceOrder;
		}
		if(mianLan == 0 && mianLan!=""){
			isdelivery = 0;
		}
		//查询总页数
		$.ajax({
			url:"elastic/goods/countPage/1/"+orderMethod,
			type:"post",
			async:false,
			data: {"goodsName":val,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery},
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages:$("#countPage").val(),
			skip:true,
			jump: function(obj){
				//页面加载商品信息
				$.ajax({
					url:"elastic/goods/list/"+obj.curr+"/"+orderMethod,
					type:"post",
					data: {"goodsName":val,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery,"priceOrder":priceOrder},
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
		if(mianLan == 0 && mianLan!=""){
			isdelivery = 0;
		}
/*		$.ajax({
			url:"elastic/goods/list/1/"+orderMethod+"?goodsName="+val+"&isDelvi="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});
*/		//查询总页数
		$.ajax({
			url:"elastic/goods/countPage/1/"+orderMethod,
			type:"post",
			async:false,
			data: {"goodsName":val,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery,"beginPrice":price,"endPrice":endPrice},
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages:$("#countPage").val(),
			skip:true,
			jump: function(obj){
				var orderMethod=orderMethods();
				var isdelivery = isDelivery();
				if(mianLan == 0 && mianLan!=""){
					isdelivery = 0;
				}
				//页面加载商品信息
				$.ajax({
					url:"elastic/goods/list/"+obj.curr+"/"+orderMethod,
					type:"post",
					data: {"goodsName":val,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery,"beginPrice":price,"endPrice":endPrice},
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
		if(mianLan == 0 && mianLan!=""){
			isdelivery = 0;
		}
		/*$.ajax({
			url:"elastic/goods/list/1/"+orderMethod+"?goodsName="+val+"&isDelvi="+isdelivery+"&endPrice="+price+"&beginPrice="+beginPrice,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});*/
		$.ajax({
			url:"elastic/goods/countPage/1/"+orderMethod,
			type:"post",
			async:false,
			data: {"goodsName":val,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery,"beginPrice":beginPrice,"endPrice":price},
			success:function(data){
				$("#countPage").val(data);
			}
		});
		laypage({
			cont: $("#biuuu_city"),
			pages:$("#countPage").val(),
			skip:true,
			jump: function(obj){
				var orderMethod=orderMethods();
				var isdelivery = isDelivery();
				if(mianLan == 0 && mianLan!=""){
					isdelivery = 0;
				}
				//页面加载商品信息
				$.ajax({
					url:"elastic/goods/list/"+obj.curr+"/"+orderMethod,
					type:"post",
					data: {"goodsName":val,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery,"beginPrice":beginPrice,"endPrice":price},
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
	//筛选条件
	var orderMethod=orderMethods();
	var isdelivery = isDelivery();
	if(mianLan == 0 && mianLan!=""){
		isdelivery = 0;
	}
	//查询总页数
	$.ajax({
		url:"elastic/goods/countPage/1/"+orderMethod+"?mianLand="+isdelivery,
		type:"post",
		data:{"goodsName":val,"typeName":typeName},
		async:false,
		success:function(data){
			$("#countPage").val(data);
		}
	});
	laypage({
		cont: $("#biuuu_city"),
		pages:$("#countPage").val(),
		skip:true,
		jump: function(obj){
			//页面加载商品信息
			$.ajax({
				url:"elastic/goods/list/"+obj.curr+"/"+orderMethod+"?mianLand="+isdelivery,
				type:"post",
				data:{"goodsName":val,"typeName":typeName},
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
	
	/*$("#sousuo").click(function(data){
		alert("00.");
	})*/
	setHeight();
	
	/*var searcCount = 0;
	$(".col-80").children().each(function(){
		searcCount++;
		if(searcCount>10){
			$(this).hide();
		}
		setHeight();
	})*/
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
	
	$(".page-num").click(function () {
	    $(this).addClass("active").siblings().removeClass("active")
	    var text=alert(this).text();
	})
	$(".search-list-item li").click(function () {
	    $(this).addClass("active").siblings().removeClass("active")
	})
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
	$(".add-key").each(function () {
	    var a = $(this).siblings(".col-80").height();
	    $(this).css({"height":a,"line-height":a+'px'})
	})
	/*//筛选条件
	$(".testClass").click(function(){
		var id=$(this).attr("searchId");
		alert(id);
	});*/
});
function fun(text,parent){
	//alert(text+"-----"+parent);
	/*$(".seach").each(function(){
		var child =$(this).attr("child");
		var parents=$(this).attr("parent");
		if(child==text && parent == parents){
			$(this).empty();
		}
	});*/
	delSearch(text,parent);
	var search=lowSearch();
	//剩下的分类
	var content=$("#searchContent").val();
	var ordeMethod=orderMethods();
	var isdelivery = isDelivery();
	var typeName = $("#typeName").val();
	var mianLan=$("#mianLan").val();//是否全国配送
	if(mianLan == 0 && mianLan!=""){
		isdelivery = 0;
	}
	$.ajax({
		url:"elastic/goods/searchDto?goodsName="+content+"&mainLand="+isdelivery,
		type:"post",
		data: {typeName:lowSearch()},
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
		url:"elastic/goods/countPage/1/"+ordeMethod+"?goodsName="+content+"&mianLand="+isdelivery,
		type:"post",
		async:false,
		data: {"goodsName":content,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery},
		success:function(data){
			$("#countPage").val(data);
		}
	});
	laypage({
		cont: $("#biuuu_city"),
		pages:$("#countPage").val(),
		skip:true,
		jump: function(obj){
			//页面加载商品信息
			$.ajax({
				url:"elastic/goods/list/"+obj.curr+"/"+ordeMethod+"?goodsName="+content+"&mianLand="+isdelivery,
				type:"post",
				data: {"goodsName":content,"typeName":typeName,"wordSeg":search,"mianLand":isdelivery},
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

//获取剩余的筛选条件
function lowId(){
	var text = 0;
	$(".seach").each(function(){
		if($(this).attr("value").trim()!=-1 && $(this).attr("value").trim()!="undefined"){
			if($(this).attr("chid") != ""){
				text=$(this).attr("chid");
			}
		}
	});
	return text;
}
//移除样式并且删除上方标签
function delSearch(text,parent){
	//alert(text+"-----"+parent);
	var labdel=1;
	$(".seach").each(function(){
		if($(this).attr("value")=="-1"){
			labdel+=1;
		}
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
	});
	/*if($(".seach").size()==labdel){
		$(".sec").hide();
	}*/
	$(".col-80").children().each(function(){
		var child = $(this).attr("value");
		var parents=$(this).attr("parent");
		//alert(parents+"---"+child);
		if(child==text && parent == parents){
			$(this).removeClass("active");
		}
	})
	
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
	var val=$("#goodsName").val();
	//页面加载商品信息
	$.ajax({
		url:"elastic/goods/list/"+index+"/0?goodsName="+val+"&mianLand=0",
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});
};

