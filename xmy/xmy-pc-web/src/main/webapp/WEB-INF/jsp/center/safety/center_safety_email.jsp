<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>安全验证</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_safety.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">安全验证</div>
                <div class="category-content safe-check">
                    <div class="cate-content-title clearfix">
                        <ul class="cate-nav">
                            <li onclick="window.location='/center/toSafetyPhone?set=8'">手机验证</li>
                            <li class="active">邮箱验证</li>
                            <li onclick="window.location='/center/toSafetyQuestion?set=8'">安全问答验证</li>
                        </ul>
                    </div>
                    <div class="cate-table"  style="display: ${not empty userInfo.email ? 'block':'none' }">
                        <div class="add-distance4" id="nochange">
                            <div class="input-group clearfix">
                                <div class="label label2">${not empty userInfo.email ? '已验证邮箱':'点击更新绑定邮箱' }</div>
                                <div class="item-input item-input2"><p class="item-input-word">${userInfo.email }</p></div>
                            </div>
                            <div class="btn-group add-distance2">
                                <button type="button" class="btn-theme2 btn-default" onclick="change();">更新邮箱</button>
                            </div>
                        </div>
                    </div>
                    <div class="cate-table"  >
                        <div class="add-distance4" id="change"  style="display: ${not empty userInfo.email ? 'none':'block'}"  >
                            <div class="input-group clearfix">
                                <div class="label label2">邮箱地址:</div>
                                <div class="item-input item-input2"><input name="email" id="email" type="text" class="default"><span id="msg"></span></div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label2">校验码 :</div>
                                <div class="item-input item-input2"><input name="emailCode" id="email_code" type="text" class="default check-code">
                                <button type="button" class="btn-getcode" id="sendEmail" >获取验证码</button></div>

                            </div>
                            <div class="btn-group add-distance2">
                                <button type="button" class="btn-theme2 btn-default" id="checkCode">确认验证</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<!-- <script type="text/javascript" src="resource/js/center/center_safety.js"></script> -->
<script type="text/javascript">
function change(){
	$("#change").show();
	$("#nochange").hide();
}

$(function () {
	 $(".cate-nav li").click(function () {
        $(this).addClass("active").siblings().removeClass("active")
        var index=$(".cate-nav li").index(this);
        $("div.cate-table>div").eq(index).show().siblings().hide()

	  })
	
    $('#sendEmail').click(function () {
    	var email=$("#email").val();
    	var re=/^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(\.[0-9A-Za-z]+)+$/;
    	if(re.test(email)){
    		$("#msg").text("");
    		$.ajax({
       			type:'post',
       			data:{email:email},
       			url:"center/sendEmail",
       			success:function(data){
       			}
       		});
        	wait();
    	}else{
    		$("#msg").text("邮箱格式不正确！！");
    	}
    })
    
    function wait(){
    	var count = 60;
        var countdown = setInterval(CountDown, 1000);
        function CountDown() {
        	flag=true;
            $("#sendEmail").attr("disabled", true);
            $("#sendEmail").html("重新发送(" + count + ")");
            if (count == 0) {
                $("#sendEmail").html("获取验证码").removeAttr("disabled");
                flag=false;
                clearInterval(countdown);
            }
            count--;
        }
    }
    
    $('#checkCode').click(function(){
    	var email=$("#email").val();
    	var code=$("#email_code").val();
    	$.ajax({
   			type:'post',
   			data:{"code":code,"email":email},
   			url:"center/checkCode",
   			success:function(data){
   				if(data==1){
   				//弹窗
   					var btnFn = function(){
   						easyDialog.close();
   					  return false;
   					};
   					easyDialog.open({
   					  container : {
   					    header : '提示',
   					    content : '邮箱修改成功！',
   					    yesFn : btnFn
   					  }
   					});
   					window.location='/center/toSafetyEmail?set=8';
   				}else{
   					//弹窗
   					var btnFn = function(){
   						easyDialog.close();
   					  return false;
   					};
   					easyDialog.open({
   					  container : {
   					    header : '提示',
   					    content : '验证码输入错误！',
   					    yesFn : btnFn
   					  }
   					});
   				}
   			}
   		});
    });
});
</script>
 </body>
</html>
