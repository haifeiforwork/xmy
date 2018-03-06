$(function(){
	$(".page-num").click(function () {
		$(this).addClass("active").siblings().removeClass("active")
	});
	$(".search-list-item li").click(function () {
		$(this).addClass("active").siblings().removeClass("active");
		var test = "";
		var search = "0";
		$(".search-list-item li").each(function () {
			if($(this).attr("class")){
				var text=$(this).parent().parent().children().eq(0).text();
				var id= $(this).attr("value");
				var parent = $(this).attr("parent");
				test+="&nbsp;&nbsp;<i class='iconfont seach' child='"+id+"' parent='"+parent+"' onclick='fun("+id+","+parent+")'>"+text+":"+$(this).text()+"&#xe676;</i>";
				search+=","+$(this).text();
			} 
		});
		$(".sec").html(test);
		//alert(search);
		var content=$("#searchContent").val();
		var pageIndex=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		$.ajax({
			url:"search/goods/list/1/"+orderMethod+"?goodsName="+content+"&searchName="+search+"&isDelvi="+isdelivery,
			type:"post",
			success:function(data){
				$("#goods").html(data);
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
	$("#searchContent").val(val);
	//排序方式效果添加
	$(".screen-btn").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		//$(this).css("background-color","#359812").siblings().css("background-color","");
	});
	
	//指定页数的跳转
	$(".page-num").click(function(){
		var content=$("#searchContent").val();
		var pageIndex=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		$.ajax({
			url:"search/goods/list/"+pageIndex+"/"+orderMethod+"?goodsName="+content+"&isDelvi="+isdelivery,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});
	});
	//下一页的点击事件
	$(".last").click(function(){
		var pageIndex=$("#pageNo").val();
		pageIndex = parseInt(pageIndex)+1;
		var content=$("#searchContent").val();
		var countPage=$("#countPage").val();
		if(pageIndex>countPage){
			alert("已经是最后一页了！")
			return false;
		}
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		$.ajax({
			url:"search/goods/list/"+pageIndex+"/"+orderMethod+"?goodsName="+content+"&isDelvi="+isdelivery,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});
	});
	var priceOrder = true;//价格排序标识
	//排序筛选
	$(".screen-btn").click(function(){
		//var orderMethod=$(this).attr("value");
		var orderMethod=orderMethods();
		var isdelivery = isDelivery();
		var text = $(this).text();
		if($(this).hasClass("select-price")){
			priceOrder = !priceOrder;
			alert(priceOrder);
		}
		$.ajax({
			url:"search/goods/list/1/"+orderMethod+"?goodsName="+val+"&isDelvi="+isdelivery+"&priceOrder="+priceOrder,
			type:"post",
			success:function(data){
				$("#goods").html(data);
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
		$.ajax({
			url:"search/goods/list/1/"+orderMethod+"?goodsName="+val+"&isDelvi="+isdelivery+"&beginPrice="+price+"&endPrice="+endPrice,
			type:"post",
			success:function(data){
				$("#goods").html(data);
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
		$.ajax({
			url:"search/goods/list/1/"+orderMethod+"?goodsName="+val+"&isDelvi="+isdelivery+"&endPrice="+price+"&beginPrice="+beginPrice,
			type:"post",
			success:function(data){
				$("#goods").html(data);
			}
		});
	});
	
	//页面加载商品信息
	$.ajax({
		url:"search/goods/list/1/0?goodsName="+val,
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});
	
	setHeight();
	//隐藏多余的筛选条件
	var searcCount = 0;
	$(".col-80").children().each(function(){
		searcCount++;
		if(searcCount>10){
			$(this).hide();
		}
		setHeight();
	})
	
	//显示全部的筛选条件
	var all = false;
	$(".col-10").click(function(){
		if(!all){
			$(this).parent().children().eq(1).find("li").show();
			all=true;
		}else{
			var searcCount = 0;
			$(".col-80").children().each(function(){
				searcCount++;
				if(searcCount>10){
					$(this).hide();
				}
			})
			all = false;
		}
		setHeight();
	});
});
function fun(text,parent){
	//alert(text+"-----"+parent);
	$(".seach").each(function(){
		var child =$(this).attr("child");
		var parents=$(this).attr("parent");
		if(child==text && parent == parents){
			$(this).empty();
		}
	});	
	var content=$("#searchContent").val();
	var ordeMethod=orderMethods();
	var isdelivery = isDelivery();
	$.ajax({
		url:"search/goods/list/1/"+ordeMethod+"?goodsName="+content+"&isDelvi="+isdelivery,
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});
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
}

