require(["common"],function(){

	//初始化加载底部公共JS
	$.initFooterJS() ;
	
	//后退
	$(".pull-left").click(function() {
		history.go(-1);
	});
});



