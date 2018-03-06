/**
 * 
 */
require(["common"], function() {
	
	loadMesasge = function() {}
	
	//后退
	$(".pull-left").click(function(){
		window.history.go(-1);
	}) ;
	
	//查看消息详情
	$(".message-div").click(function(){
		location.href = "mine/messageInfo?messageId=" + $(this).attr("message-id") + "&readstatus=" +$(this).find("input[name='readstatus']").val() ;
	}) ;
	
});