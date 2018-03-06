require(["common"],function(){
	
	url = "https://graph.qq.com/oauth2.0/authorize?response_type=code&redirect_uri=http%3A%2F%2Fm.xmy365.com%2Findex%2FqqLogin&state=" + new Date().getTime() + "&client_id=100490441";
	
	//提示错误信息
	if($("#msg").val()){
		$.alert($("#msg").val()) ;
		$("#msg").val("") ;
	}
	
	$(".pull-left").click(function() {
		location.href = "gotoPage/mine/accountSecurity";
	});
	
	$(".findBack").click(function() {
		location.href = "gotoPage/index/findBack";
	});
	
	//QQ登录
	$("#qqLoginBtn").click(function() {
		location.href = url;
	});
	
	passEqual = function(neu, reNeu) {
		if(!neu.val())
			return "请输入密码";
		if(!reNeu.val())
			return "请输入密码";
		if(!(neu.val() == reNeu.val()))
			return "两次输入不相同"
		return null;
	}
	
	$("#findBackBtn").click(function() {
		if(msg = passEqual($("#pass"), $("#rePass"))) {
			$.alert(msg);
			return;
		}
		$.ajax({
			url : $("#findBackForm").attr("action"),
			data : $("#findBackForm").serialize(),
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if(data && data.status && data.status == 1) {
					$.alert("修改成功", function() {
						location.href = "index/login";
					});
				} else {
					$.alert("验证码错误");
				}
			},
			error : function(msg) {
				$.alert("验证码错误");
			}
		});
	});
	
	$("#sendSms").click(function() {
    	var  _obj = $(this), data = {phone:$("#phone").val()} ; ;
    	if(!$("#phone").val()){
    		$.alert("请输入手机号码") ;
    		return false ;
    	}
    	if(!_obj.hasClass("runing")){
    		$.commonAjax(data,"index/sendSms","json",function(data){
    			if(!(data || data.status)){
    				$.alert("验证码错误");
    				return false ;
    			}else{//发送手机验证
    				var i = 60 ;
    	    		_obj.addClass("runing") ;
    	    		_obj.html("重新发送（60s）") ;
    	    		var code = window.setInterval(function(){
    	    			i--;
    	    			if(i == 0){
    	    				_obj.html("获取验证码") ;
    	    				_obj.removeClass("runing") ;
    	    				window.clearInterval(code) ;
    	    			}else{
    	    				_obj.html("重新发送（"+ i + "s）") ;
    	    			}
    	    		}, 1000) ;
    			}
    		}) ; 
    	}
	});
	
	
	$("#bindEmail").click(function() {
		if(!$("#emailCode").val()) {
			$.alert("请输入验证码");
			return;
		}
		if(!$("#email").val()) {
			$.alert("请输入邮箱");
			return;
		}
		values = $("#bindEmailForm").serialize();
		$.commonAjax(values, "index/bindEmail", 'json', function(data) {
			if(data.status == 1) {
				$.alert("绑定成功", function() {
					location.href = "mine/center";
				});
			} else {
				$.alert(data.msg);
			}
		});
	});
	
	$("#getEmailValidationCode").click(function() {
		values = $("#bindEmailForm").serialize();
		_obj = $(this);
		if(!_obj.hasClass("running")) {
			$.commonAjax(values, 'index/sendEmail', 'json', function(data) {
				if(data.status == -1) {
					$.alert("未登录", function() {
						location.href = "index/login";
					});
				} else if(data.status == 1){
					var i = 60 ;
		    		_obj.addClass("runing") ;
		    		_obj.html("重新发送（60s）") ;
		    		var code = window.setInterval(function(){
		    			i--;
		    			if(i == 0){
		    				_obj.html("获取验证码") ;
		    				_obj.removeClass("runing") ;
		    				window.clearInterval(code) ;
		    			}else{
		    				_obj.html("重新发送（"+ i + "s）") ;
		    			}
		    		}, 1000) ;
				} else {
					$.alert(data.msg);
				}
			});
		}
	});
	
	$("#bindPhone").click(function() {
		if(!$("#phoneCode").val()) {
			$.alert("请输入验证码");
			return;
		}
		if(!$("#phone").val()) {
			$.alert("请输入手机号");
			return;
		}
		_obj = $(this);
    	//判断验证码是否存在
		values = $("#bindPhoneForm").serialize();
		if(!_obj.hasClass("runing")){
	    	$.commonAjax(values,"index/bindPhone","json",function(data){
	    		if(data.status == -1){
	    			$("#code").val("") ;
	    			$.alert(data.msg);
	    			location.href = "index/login";
	    		} else if(data.status == 0) {
	    			$.alert(data.msg);
	    		}else{
	    			$.alert("绑定成功", function() {
	    				location.href = "mine/center";
	    			});
	    		}
	    	});
		}
	});
	
	$("#getPhoneValidationCode").click(function() {
    	var  _obj = $(this), data = {phone:$("#phone").val(), 'password' : $("#password").val()} ;
    	if(!$("#phone").val()){
    		$.alert("请输入手机号码") ;
    		return false ;
    	}
    	if(!_obj.hasClass("runing")){
    		$.commonAjax(data,"index/checkPhone","json",function(data){
    			if(data.resultCode != 200){
    				
    				if(data.resultCode == 250) {
    					$.alert("密码错误");
    					return false;
    				}
    				
    				$.alert("手机号码已被注册");
    				return false ;
    			}else{//发送手机验证
    				var i = 60 ;
    	    		_obj.addClass("runing") ;
    	    		_obj.html("重新发送（60s）") ;
    	    		var code = window.setInterval(function(){
    	    			i--;
    	    			if(i == 0){
    	    				_obj.html("获取验证码") ;
    	    				_obj.removeClass("runing") ;
    	    				window.clearInterval(code) ;
    	    			}else{
    	    				_obj.html("重新发送（"+ i + "s）") ;
    	    			}
    	    		}, 1000) ;
    			}
    		}) ; 
    	}
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
    
    //登录
    $("#login").click(function(){
    	if(!$("#username").val()){
    		$.alert("用户名不能为空") ;
    	}
    	if(!$("#passwords").val()){
    		$.alert("请输入密码") ;
    	}
    	$("#type").val("1") ;//普通登录
    	$("#login_form").submit() ;
    });
    
    //新用户注册
    $("#register").click(function(){
    	location.href = "gotoPage/index/register" ;
    }) ;
    
    //找回密码
    /*$("#register").click(function(){
    	location.href = "gotoPage/index/findBackPass" ;
    }) ;*/
    
    //微信登录
   $("#wechat_redirect").click(function(){
	  /* window.open("weChat/getWeChatLogin") ;*/
	   window.location.href="index/weChat/login";
   });
   
   
   $(".logo-area").click(function(){
	   window.location.href="home/home";
   });
});
