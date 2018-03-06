var s;
var timeid;
$(function($){
	showDiv(0);
	$("#first").click(function(){
		if($("#vcode").val().trim()==""){
			warnPop("输入验证码") ;
    		return ;
    	}else{
    		$.ajax({
    			url:"user/valid/codes?codes="+$("#vcode").val()+"&userName=zfj_dq",
    			type:"post",
    			success:function(data){
    				if(data==1){
    					warnPop("验证码不对！请核实！");
    				}
    				if(data==0){
    					$.ajax({
    						url:"user/valid/user?userName="+$("#username").val(),
    						type:"post",
    						success:function(data){
    							if(data.mobilePhone==null){
    								warnPop("您输入的用户明不存在请核实！");
    							}else{
    								$(".phone").text(data.mobilePhone);
    								$("#userid").val(data.id);
    								showDiv(1);
    							}
    						}
    					})
    				}
    			}
    		})
    	}
	});
	$("#getphone").click(function(){
		if(s>0){
			return false;
		}
		s=60;//60秒后重新获取
		var text=$(".phone").first().text();
		timeid=window.setInterval(function(){daojishi()}, 1000);
		$.ajax({
			url:"user/send/msg?phone="+text,
			type:"post",
			success:function(data){
				if(data==0){
					warnPop("短信发送成功请注意及时查收并输入！")
				}else{
					warnPop("短信发送异常！");
				}
			}
		});
	});
	$("#two").click(function(){
		var name=$("#username").val();
		$("#twoName").text(name);
		var phone=$(".phone").first().text();
		var msg=$("#msg").val().trim();
		$.ajax({
			url:"user/valid/msg?phone="+phone+"&code="+msg,
			type:"post",
			success:function(data){
				if(data==0){
					//alert("验证通过！");
					showDiv(2);
				}
			}
		})
	});
	$("#three").click(function(){
		var pwd=$("#pwd").val().trim();//新密码
		var newPwd=$("#surePwd").val().trim();//确认新密码
		var usrid=$("#userid").val();//用户id
		if(pwd=="" || newPwd==""){
			return ;
		}
		if(pwd!=newPwd){
			warnPop("您输入的两次密码不一致！");
			return ;
		}else{
			$.ajax({
				url:"user/updata/pwd?userid="+usrid+"&pwd="+pwd,
				type:"post",
				success:function(data){
					if(data>0){
						$(".na").text($("#username").val());
						showDiv(3);
					}
				}
			})
		}
	});
	
	$("#forget").click(function(){
		warnPop("请拨打电话致电香满圆客服：400-822-3936（白天）   17723156790（夜间）");
	})
	$("#code").click(function(){
		$("#imagecode").html("<img src='user/imagecode?t="+new Date().getTime()+"' style='margin-left:8px;border:8px solid #555;'/>");
	});
});
function showDiv(i){
	$(".step-item").each(function(index,item){
		if(index==i){
			$(this).show();
		}else{
			$(this).hide();
		}
	});
	$(".col-25").each(function(index,item){
		if(index==i){
			$(this).addClass("active");
		}
	})
}
function daojishi(){
	$("#getphone").text(s+"后重新获取");
	if(s<=0){
		window.clearInterval(timeid);
		$("#getphone").text("重新获取");;
	}else{
		s--
	}
}
//提示弹框
function warnPop(text) {
	var btnFn = function(){
		easyDialog.close();
		return false;
	}
	easyDialog.open({
	  container : {
	    header : "提示",
	    content : text,
	    yesFn : btnFn
	  }
	});
}
