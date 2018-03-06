/**
 * 
 */
require(["common"],function(){
	
	detailsTag = $(".recharge-msg");

    $(".container .recharge-msg:gt(0)").hide()
    $(" .recharge-nav .item").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var indexx = $(".recharge-nav .item").index(this);
        $(".recharge-msg").eq(indexx).show().siblings().hide();
    }) ;
    
    $(".pull-left").click(function() {
    	window.history.go(-1);
    });
    
    doBind = function(cardNum, cardPassword) {
    	$.ajax({
    		url : 'user/shoppingCardBinding',
    		data : {'cardNum' : cardNum, 'cardPassword' : cardPassword},
    		dataType : 'json',
    		type : 'post',
    		success : function(data) {
    			if(data.status == 1) {
    				$.alert("绑定成功",function(){
    					location.reload() ;
    				});
    			} else {
    				$.alert(data.msg);
    			}
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
    }
    
    $(".bind").click(function() {
    	doBind($("#cardNum").val(), $("#cardPassword").val());
    });
    
    loadDetails = function() {
    	$.ajax({
    		url : 'user/activateDetail',
    		type : 'post',
    		dataType : 'json',
    		success : function(data) {
    			
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
    }
}) ;