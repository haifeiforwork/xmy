/**
 * 
 */

require(["common"], function() {
	
	$("img").click(function() {
		$.ajax({
			url : 'mine/changeAvatar',
			data : {'imgPatho' : $(this).attr("trueSrc")},
			type : 'post',
			dataType : 'json',
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
	});
	
});