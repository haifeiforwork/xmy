/**
 * 这是设置页面的js
 */
	$(".quit").click(function() {
		$.confirm("确认退出登录?",function(){
			$.ajax({
				url : 'mine/logout',
				success : function(data) {
					location.href = "mine/center";
				},
				error:function(error){
					if(error.responseText == "SESSION_LOST"){
						$.alert("未登录或登录超时",function(){
							location.reload() ;
						}) ;
					}
					else if(error.responseText == "BUSINESS_EXCEPTION"){
						$.alert("系统内部繁忙",function(){
							location.reload() ;
						}) ;
					}else{
						$.alert(error.responseText,function(){
							location.reload() ;
						}) ;
					}
				}
			});
		}) ;
	});
	
	$("#about").click(function() {
		location.href = "gotoPage/mine/about";
	});
	
	$(".pull-left").click(function() {
		location.href = "mine/center";
	});
	
	$(function() {
		now = new Date();
		time = now.getHours() * 60 * 60 * 1000 + now.getMinutes() * 60 * 1000 + now.getSeconds() * 1000;
		if(parseInt(time) >= 32400000 && parseInt(time) < 64800000 )
			$("#oneClickCall").attr("href", "tel:400-822-3936");
		if(parseInt(time) >= 64800000 || parseInt(time) < 32400000)
			$("#oneClickCall").attr("href", "tel:17723156790");
	});