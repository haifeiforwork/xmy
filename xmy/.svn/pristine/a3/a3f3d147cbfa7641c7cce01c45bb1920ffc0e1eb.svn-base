$(document).ready(function(){ 
	$.ajax({
		url:"nationwide/list?pageIndex=1&sort="+"",
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});
}); 
$(".screen-btn").click(function(){
	$(this).addClass("active").siblings().removeClass("active");
});
//排序筛选
$(".screen-btn").click(function(){
	var sort=$(this).attr("value");
	$.ajax({
		url:"nationwide/list?pageIndex=1&sort="+sort,
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});
})

function onPage(page){
	var sort=$("#sort").val()
	$.ajax({
		url:"nationwide/list?pageIndex="+page+"&sort="+sort,
		type:"post",
		success:function(data){
			$("#goods").html(data);
		}
	});
}