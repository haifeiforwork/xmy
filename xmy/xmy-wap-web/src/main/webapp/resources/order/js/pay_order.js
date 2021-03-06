require(["common"],function(){
	
	var payType = 0 ;
	
	//选择支付方式
    $(".list").on("click",".other-type",function(){
       if($("#cost-money").val() < $("#total-money").val() || !$(".list .balance-type .yuan ").hasClass("active")){
    	   var $this = $(this).find(".yuan"),$this_find = $(this).parent().siblings().find(".other-type .yuan");
    	   if($this.hasClass("active"))  $this.removeClass("active");
    	   else{
    	   	$this.addClass("active");
    	   	$this_find.removeClass("active");
    	   }	
       }
    });
    
    //选择余额
    $(".list").on("click",".balance-type",function(){
    	tag = $(this).children("div");
    	if(parseFloat(tag.parent().prev().val()) > 0.00) {
    		if($(this).find(".yuan").hasClass("active")){
    			$(this).find(".yuan").removeClass("active") ;
    			$("#cost-money").attr("disabled","disabled") ;
    		}else{
    			if(parseFloat($("#cost-money").val()) >= parseFloat($("#total-money").val()) ){
    				 $(".list .other-type .yuan").removeClass("active") ;
    			}
    			$(this).find(".yuan").addClass("active") ;
    			$("#cost-money").removeAttr("disabled") ;
    		}
    	}
    });
    
    //后退
    $(".pull-left").click(function(){
    	if($("#confirm").val()){
    		$.confirm("订单已经生成，是否前往订单列表中查看",function(){
    			location.href = "mine/center" ;
    		},
    		function(){
    			location.href = "shopping/shopping" ;
    		}
    		) ;
    	}else{
    		window.history.back(-1) ;
    	}
    	
    });
    
    //激活购物卡
    $("#active-card").click(function(){
    	location.href = "gotoPage/mine/shoppingCard" ;
    }) ;
    
    //手动输入金额验证
    $("#cost-money").keyup(function(){
    	if($("#cost-money").val() && !/^\d*\.?\d*$/.test($("#cost-money").val())){
    		$.alert("支付金额无效") ;
    		$("#cost-money").val("0.00") ;
    	}
    	if(parseFloat($("#cost-money").val()) >= parseFloat($("#total-money").val()) ){
    		 $(".list .other-type .yuan").removeClass("active") ;
    	}
    	if(parseFloat($("#lost-money").val()) < parseFloat($("#cost-money").val())){
    		$("#cost-money").val($("#lost-money").val() ? parseFloat($("#lost-money").val()).toFixed(2) : "0.00") ;
    	}
    });
    
    //确认支付
    $(".ok-pay").click(function(){
    	var url = "" ,data = {orderid : $("input[name='orderid']").val() ,payAmount : ""}, cost = 0 ;
    	if($(".list .yuan").filter(".active").length <= 0){
    		$.alert("请选择支付方式") ;
			return ;
    	}
    	if($(".my-balance").find(".yuan").hasClass("active")){//余额支付
    		if(!$("#cost-money").val()){
    			$.alert("请输入金额") ;
    			return ;
    		}
    		if($("#cost-money").val() && !/^\d*\.?\d*$/.test($("#cost-money").val())){
        		$.alert("支付金额无效") ;
    			return ;
        	}
    		if(parseFloat($("#lost-money").val()) < parseFloat($("#cost-money").val())){
    			$.alert("个人账户余额不足") ;
    			return ;
    		}
    		if(parseFloat($("#cost-money").val()) < parseFloat($("#total-money").val()) && $(".list .other-type .active").length <=0){
    			$.alert("请选择其他支付方式") ;
    			return ;
    		}
    		url = "payment/payment/11" ;
    	}
    	if($(".account").hasClass("active")){//选择用余额支付时
    		cost = parseFloat($("#cost-money").val()) ? parseFloat($("#cost-money").val()) : 0 ;
    	}
    	if($(".wx-pay").find(".yuan").hasClass("active")){//微信支付
    		if($("#micromessenger").val() != "micromessenger"){
    			/*payType = 6 ;
    			url = "payment/payment/6" ;*/
    			$.alert("请在微信浏览器中打开") ;
    			return ;
    		}else{
    			location.href = "payment/wxJsapi?orderid=" + $("input[name='orderid']").val() + "&payAmount=" + cost ;
    			return ;
    		}
    	}
    	if($(".brand-card").find(".yuan").hasClass("active")){//银联支付
    		payType = 4;
    		url = "payment/payment/4" ;
    	}
    	if($(".ali-payment").find(".yuan").hasClass("active")){//支付宝支付
    		payType = 10 ;
    		url = "payment/payment/10" ;
    	}
    	if($(".self-payment").find(".yuan").hasClass("active")){//货到付款
    		if($("#out").val() == 0){
    			$.alert("跨境商品不支持货到付款") ;
    			return ;
    		}
    		if($("#out").val() == 1){
    			payType = 5 ;
        		url = "payment/payment/5" ;
    		}
    	}
    	data.payAmount = cost ;
    	$.commonAjax(data,url,"json",function(data){
    		if(data.data == "success"){//只用余额支付
    			/*$.alert("支付成功，请前往个人中心查看", function() {
    				location.href = "mine/center";
    			});*/
    			location.href = "payment/paySuccess?orderId=" + $("input[name='orderid']").val();
    			return;
    			/*$.alert("支付成功，请前往个人中心查看",function(){
    				location.href = "mine/center" ;
    			}) ;*/
    		}
    		if(payType == 6){
    			/*if (typeof WeixinJSBridge == "undefined"){
				   if( document.addEventListener ){
				       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
				   }else if (document.attachEvent){
				       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
				       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				   }
				}else{
				   onBridgeReady(data);
				} */
    			location.href = data.mweburl;
    		}else if(payType == 10){
    			$("#toPaySuccessPageForm").remove() ;
    			$("form[name='punchout_form']").remove() ;
    			$("body").append(data.alipay) ;
    		}else if(payType == 5){
    			if(data.resultCode == 200) {
    				order = data.data;
    				noTag = $("<input type='hidden' name='no' />").attr("value", order.no);
    				createTime = new Date(order.createTime).toLocaleString();
    				payTimeTag = $("<input type='hidden' name='createTime' />").attr("value", createTime);
    				totalTag = $("<input type='hidden' name='total' />").attr("value", order.total);
    				payTag = $("<input type='hidden' name='pay' />").attr("value", order.pay);
    				$("#toPaySuccessPageForm").append(noTag).append(payTimeTag).append(totalTag).append(payTag);
    				$("#toPaySuccessPageForm").submit();
    			}
    		} else if(payType = 4) {
    			unionPayCallBack(data)
    		}
    	}) ;
    }) ;
    
    unionPayCallBack = function(data) {
    	$("html").empty();
    	$("html").append(data.html);
    }
   
	function onBridgeReady(map){
	  var appid = map.appid ,timeStamp = map.timestamp, 
		  nonceStr = map.noncestr, packageinfo = map.packageinfo, paySign = map.sign ;
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', {
	           "appId":appid,     //公众号名称，由商户传入     
	           "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
	           "nonceStr":nonceStr, //随机串     
	           "package":packageinfo,     
	           "signType":"MD5",         //微信签名方式：     
	           "paySign":paySign //微信签名 
	       },
	       function(res){ 
	       	   var msg = "" ;
	           if(res.err_msg == "get_brand_wcpay_request:ok" ) {// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
					msg = "支付成功，请前往订单详情里查看" ;
	           }else{
	           		msg = "支付失败，请重新支付" ;
	           } 
	           $.alert(msg,function(){
			   		location.href = "mine/center";
			   }) ;
	       } 
	   ); 
	} 

    
 });