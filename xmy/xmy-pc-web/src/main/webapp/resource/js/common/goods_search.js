//商品搜索
$(function($){
	$("#searchContent").keyup(function(e){
		//e.stopPropagation();
		var val=$("#searchContent").val();
		if(val == ""){
			$("#searchList").hide();
		}else{
			$("#searchList").show();
			$.ajax({
				url:"commons/searchGoods",
				type:"post",
				data:{"goodsName":val},
				success:function(data){
					$("#searchList").html(data);
					$(".searchname").click(function(e){
						//e.stopPropagation();
						var text=$(this).text();
						window.location.href="/elastic/goods?typeName="+text;
					})
				}
			})
			//回车提交
			$(document).keyup(function(event){  
				  if(event.keyCode ==13){  
					  var val=$("#searchContent").val();
						window.location.href = "/elastic/goods?goodsName="+val;
				  }  
			});
		}
		//window.location.href = "search/goods/1/0?goodsName="+val;
	});
	$("#search").click(function(){
		var val=$("#searchContent").val();
		window.location.href = "/elastic/goods?goodsName="+val;
	});
	//底部搜索
	$("#sousuo").click(function(){
		var val=$("#sousuoContent").val();
		window.location.href = "/elastic/goods?goodsName="+val;
	})
	$("#sousuoContent").keyup(function(e){
		//回车提交
		$(document).keyup(function(event){  
			  if(event.keyCode ==13){  
				  var val=$("#sousuoContent").val();
					window.location.href = "/elastic/goods?goodsName="+val;
			  }  
		});
	});
	$("body").click(function(){
		$("#searchList").hide();
	});
})
function searchfun(data){
	//var val=$(this).val();
	alert(data);
}