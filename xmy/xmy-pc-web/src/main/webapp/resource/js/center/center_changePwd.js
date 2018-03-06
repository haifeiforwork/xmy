var a=0;
var b=0;
var c=0;


function checkOld(input) {
    var old = input.value;
    var reg = /^[a-zA-Z0-9_]{6,20}$/;
    var $tip = $("#old");
    if(old.match(reg) == null){
        $tip.get(0).innerHTML = "密码为6-20位，由字母、数字和符号组成！";
        a=0;
    }else{
    	$tip.get(0).innerHTML = "";
 		a=1;
    }
}
function checkNew(input) {
    var nw = input.value;
    var reg = /^[a-zA-Z0-9_]{6,20}$/;
    var $tip = $("#new");
    var old=$("#oldPwd").val();
    if(nw.match(reg) == null){
        $tip.get(0).innerHTML = "密码为6-20位，由字母、数字和符号组成！";
        b=0;
    }else if(nw==old){
    	$tip.get(0).innerHTML = "新密码不能与旧密码一样！";
        b=0;
    }else{
    	$tip.get(0).innerHTML = "";
    	b=1;
    }
}
function checkVerify(input) {
    var verify = input.value;
    var reg = /^[a-zA-Z0-9_]{6,20}$/;
    var $tip = $("#verify");
    var newPwd=$("#newPwd").val();
    if(verify.match(reg) == null){
        $tip.get(0).innerHTML = "密码为6-20位，由字母、数字和符号组成！";
        c=0;
    }else if(verify!=newPwd){
    	$tip.get(0).innerHTML = "两次密码输入不一致！";
    	c=0;
    }else{
    	$tip.get(0).innerHTML = "";
    	c=1;
    }
}
function check(form){
	var n=a+b+c;
	if(n==3){
		return true;
	}
	return false;
}

//lijie
$(function($){
	//修改顶部样式作为修改完成
	var text=$("#msg").text();
	if(text=="修改成功！"){
		$("#xian").addClass("line-active");
		$("#num").attr("class","step1 iconn");
		$("#num").parent().css("color","#359812");
		$(".data-div").hide();
		$(".lsatstep").show();
	}else if(text=="原始密码不正确！"){
		var btnFn = function(){
			easyDialog.close();
		  return false;
		};
		easyDialog.open({
		  container : {
		    header : '提示',
		    content : text,
		    yesFn : btnFn
		  }
		});
	}
});