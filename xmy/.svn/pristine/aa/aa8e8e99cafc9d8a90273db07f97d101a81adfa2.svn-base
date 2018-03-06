/**
 * 
 */
require(["common"],function() {
	
	$(".pull-left").click(function() {
		location.href = "gotoPage/mine/accountSecurity";
	});
	
	//显示或隐藏密码
    $(".js-bd").click(function () {
        if ($(this).hasClass("js-bd1")){
            $(this).removeClass("js-bd1").addClass("js-bd2");
            $(this).prev().find("input").attr("type","password")
        }else {
            $(this).removeClass("js-bd2").addClass("js-bd1")
            $(this).prev().find("input").attr("type","text")
        }
    });
	
	$("#submit").click(function() {
		
		old = $("#old").val();
		
		neu = $("#new").val();
		
		re = $("#re").val();
		
		if(!old) {
			$.alert("请输入旧密码");
			return;
		}
		
		if(!neu) {
			$.alert("请输入新密码");
			return;
		}
		
		if(!re) {
			$.alert("请重复输入密码");
			return;
		}
		
		$.ajax({
			url : 'user/modifyPassword',
			data : {'old' : old, 'neu' : neu, 're' : re },
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if(data.status == 1) {
					$.alert("修改成功", function() {
						location.href = "mine/center";
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
	});
	
});