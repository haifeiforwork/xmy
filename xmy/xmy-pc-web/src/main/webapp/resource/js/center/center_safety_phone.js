$(function(){
	
	var flag = true;  //密码是否正确
	var mobilePhone = true ; //手机号是否被绑定过
	var timerstart=false;
	var timer = (function fx(callback1,callback2,duration){
		fx.time = fx.time || duration || 60 ;
		setTimeout(function(){
			if(fx.time <= 1){
				timerstart = false ;
				callback1.call(this,fx.time) ;
				return ;
			}
			timerstart = true ;
			fx.time = fx.time - 1 ;
			callback2.call(this,fx.time) ;
			fx(callback1,callback2,fx.time) ;
		},1000) ;	
	}) ,$vget = $(".btn-getcode") ,$ic = $("#img_code") ,$vcode = $("#vcode") ,$mphone = $("#mobile_phone") ,$form = $("#myform") ;
	$("#drag").drag();
  
	$vget.click(function () {
    	if(timerstart){
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
		var phone=$("#mobile_phone").val();
		if(phone.trim() == ""){
			//弹窗
			var btnFn = function(){
				easyDialog.close();
			  return false;
			};
			easyDialog.open({
			  container : {
			    header : '提示',
			    content : '手机号码不能为空！',
			    yesFn : btnFn
			  }
			});
			return false;
		}
		regPhone=/^1[3|4|5|7|8]\d{9}$/;
		if(!regPhone.test(phone)){
			var btnFn = function(){
				easyDialog.close();
			  return false;
			};
			easyDialog.open({
			  container : {
			    header : '提示',
			    content : '手机号码不正确！',
			    yesFn : btnFn
			  }
			});
			return false;
		}
		if(timerstart){
			return ;
		}
		if (!flag) {  //旧密码是否正确
    		return ;
    	}
		if (!mobilePhone) { //手机号是否已被绑定
			return ;
		}
		
		//发送验证码
		$.ajax("center/valid?mobilePhone=" + $mphone.val()).done(function(data){
			vcodeSuccess() ;
		}).fail(function(){
			//弹窗
			var btnFn = function(){
				easyDialog.close();
			  return false;
			};
			easyDialog.open({
			  container : {
			    header : '提示',
			    content : '验证错误！',
			    yesFn : btnFn
			  }
			});
		}) ;
		
    }) ;
    
    function vcodeSuccess(){
    	timer(function(time){
    		$vget.removeClass("grey").html("获取验证码") ;
    	},function(time){
    		$vget.addClass("grey").html(time + "s后重新获取") ;
    	}) ;
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
    		mobileCode:{
    			required:true
    		}
    	},
    	messages:{
    		mobileCode:{
    			required:"手机校验码不能为空"
    		}
    	},
    	submitHandler:function(form){
    		$(form).ajaxSubmit({
    			url:"center/safetyPhone",
    			type:"POST",
    			dataType:"json",
				success: function (data) { //表单提交后更新页面显示的数据
					if(200 !== data.resultCode){
						alert(data.data || "用户注册失败") ;
						return ;
					}
					location.href = "/center/toSafetyPhone?set=8" ;
			  	}
			 });
    	}
    }) ;
    $("#changePhone").click(function(){
    	$form.submit() ;
    }) ;
   
    //监听输入密码
    $("#password").blur(function(){
    	var password = $("#password").val();
    	if (password != '') {
    		$.ajax({
    			url:"center/checkPassword",
    			type:"POST",
    			data:{"password":password},
    			dataType:"json",
    			success:function (data){
    				if (data.data == 1) {
    					flag = false;
    					$("#msg").text("输入密码错误");
    				} else {
    					flag = true ;
    					$("#msg").text("");
    				}
    			}
    		})
    	} else {
    		flag = false;
			$("#msg").text("密码不能为空");
    	}
    });
    
    //监听手机号
    $("#mobile_phone").blur(function(){
    	var phone = $("#mobile_phone").val();
    	if (phone != '') {
    		var	regPhone=/^1[3|4|5|7|8]\d{9}$/;
    		if(!regPhone.test(phone)){
    			mobilePhone = false;
    			$("#phonemsg").text("手机号码不符合规范");
    		} else {
    			$.ajax({
        			url:"center/voildMobilePhone",
        			type:"POST",
        			data:{"phone":phone},
        			dataType:"json",
        			success:function (data){
        				if (data.data == 1) {
        					mobilePhone = false;
        					$("#phonemsg").text("手机号码已被绑定了");
        				} else {
        					mobilePhone = true ;
        					$("#phonemsg").text("");
        				}
        			}
        		})
    		}
    	} else {
    		$("#phonemsg").text("手机号码不能为空");
    	}
		
    });
    
    
}) ;