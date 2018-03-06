$(function(){
	var timer = (function fx(callback1,callback2,duration){
		fx.time = fx.time || duration || 60 ;
		setTimeout(function(){
			console.log(fx.time)
			if(fx.time <= 1){
				timer.start = false ;
				callback1.call(this,fx.time) ;
				fx.time = 60; 
				return ;
			}
			timer.start = true ;
			fx.time = fx.time - 1 ;
			callback2.call(this,fx.time) ;
			fx(callback1,callback2,fx.time) ;
		},1000) ;	
	}) ,$vget = $(".btn-get") ,$ic = $("#img_code") ,$vcode = $("#vcode") ,$mphone = $("#mobile_phone") ,$form = $("#myform") ;
	$("#drag").drag();
    $vget.click(function () {
    	if(timer.start){
    		return ;
    	}
    	if(!$mphone.hasClass("valid")){
    		return ;
    	}
    	$vcode.val("") ;
    	generateCode() ;
        $(".check-coded").fadeIn();
    });
    $(".close").click(function () {
        $(".check-coded").fadeOut();
    }) ;
    $(".trans").click(function(){
    	generateCode() ;
    }) ;
    
    function generateCode(){
    	$ic.html("<img src='user/imagecode?t="+new Date().getTime()+"' style='width:120px;border:1px solid #555;'/>") ;
    }
    
    $("#code_confirm").click(function(){
    	if(!$vcode.val()){
    		alert("输入验证码") ;
    		return ;
    	}
		$.ajax("user/valid/code?vcode=" + $vcode.val() + "&mobilePhone=" + $mphone.val()).done(function(data){
			if(200 !== data.resultCode){
				alert(data.data || "图形验证码不正确") ;
				return ;
			}
			vcodeSuccess() ;
		}).fail(function(){
			alert("验证错误") ;
		}) ;
    }) ;
    
    function vcodeSuccess(){
    	timer(function(time){
    		$vget.removeClass("grey").html("获取校验码") ;
    	},function(time){
    		$vget.addClass("grey").html(time + "s后重新获取") ;
    	}) ;
		$(".check-coded").fadeOut() ;
		$vcode.val("") ;
    }
    
    $.validator.addMethod("mobilePhone", function(value, element) {
		return this.optional(element) || /^1[3|4|5|7|8]\d{9}$/.test($.trim(value));
	}, "请输入正确的手机号");
    $.validator.addMethod("regexp", function(value, element, params) {
		return this.optional(element) || new RegExp(params).test($.trim(value));
	}, "请输入正确的值");
    $form.validate({
    	rules:{
    		name:{
    			required:true,
    			mobilePhone:true,
    			remote:{url:"user/valid/0/exist"}
    		},
    		password:{
    			required:true,
    			minlength:6
    		},
    		againPassword:{
    			required:true,
    			equalTo:"#password"
    		},
    		mobilePhone:{
    			required:true,
    			mobilePhone:true,
    			equalTo:"#name",
    			remote:{url:"user/valid/1/exist"}
    		},
    		mobileCode:{
    			required:true
    		}
    	},
    	messages:{
    		name:{
    			required:"用户名称不能为空",
    			mobilePhone:"该用户名不符合手机号规范",
    			remote:"用户名已存在"
    		},
    		password:{
    			required:"登录密码不能为空",
    			minlength:"密码至少为6位"
    		},
    		againPassword:{
    			required:"确认密码不能为空",
    			equalTo:"密码不一致"
    		},
    		mobilePhone:{
    			required:"手机号码不能为空",
    			mobilePhone:"手机号码不正确",
    			equalTo:"用户名与手机号码不一致",
    			remote:"该手机号码已经被注册"
    		},
    		mobileCode:{
    			required:"手机校验码不能为空"
    		}
    	},
    	submitHandler:function(form){
    		$(form).ajaxSubmit({
    			url:"user/register",
    			type:"POST",
    			dataType:"json",
    			beforeSubmit:function(){
	    			if(!$.dragpass){
	    	    		$("#v_drag").html("请拖动验证").show() ;
	    	    		return false ;
	    	    	}
	    	    	if(!$("#agree")[0].checked){
	    	    		alert("您没有同意《香满圆服务协议》") ;
	    	    		return false ;
	    	    	}
	    	    	return true ;
	    		},
				success: function (data) { //表单提交后更新页面显示的数据
					if(200 !== data.resultCode){
						alert(data.data || "用户注册失败") ;
						return ;
					}
					location.href = "/user/register/success" ;
			  	}
			 });
    	}
    }) ;
    $("#register").click(function(){
    	$form.submit() ;
    }) ;
    
    
}) ;

function regText(){
	text=$(".text").html();
	tips_modal(text,"香满圆服务协议")
}

function tips_modal(msg,head){
	var btnFn = function(){
		easyDialog.close();
		 return false;
		};
		easyDialog.open({
		  container : {
		    header : head||"提示",
		    content : msg,
		    yesFn : btnFn
		  }
		});
}
