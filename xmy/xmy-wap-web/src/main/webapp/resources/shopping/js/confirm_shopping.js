require(["common"],function(){
	
	//失焦
	$("#add_address_popup").on("click",".address-choose-div",function(){
		 $("input").blur();
	});
	
	//业务功能区***************************************************************************************
	$(".save").click(function(){
		var num=$(".nav-list").find(".active").find("span").attr("value");
		var companyName=$(".company-name").val();
		if(num==0){
			$("#noteName").val("个人");
		}
		if(num==1){
			$("#noteName").val(companyName);
		}
		$("#invoiceContentInput").val("明细");
		$("#companyName").val(companyName);
		$("#taxpayerNum").val($(".taxpayerNum").val());
		$("#invoiceType").val(num);
		$.closePopup();
	});

    $(".close-popup").click(function(){
    	if($(this).hasClass("coupon")){
    		$("#companyName").val("");
    		$("#taxpayerNum").val("");
    	}else{
    		$("#invoiceType").val("");
    		$("#noteName").val("");
    		$("#invoiceContentInput").val("");
    	}
    });
    
    //业务区结束******************************************************************************************
	
	$(".titket-pick").click(function(){
		$("#tiket-pop").popup();
	});
	
	
	
	checkIfIsCq = function(area) {
		
		if(area == "渝中区")
			return true;
		if(area == "江北区")
			return true;
		if(area == "沙坪坝区")
			return true;
		if(area == "南岸区")
			return true;
		if(area == "九龙坡区")
			return true;
		if(area == "渝北区")
			return true;
		if(area == "北碚区")
			return true;
		if(area == "大渡口区")
			return true;
		if(area == "巴南区")
			return true;
		return false;
		
	}
	
	enableCalendar = function() {
		$("#postTime").val($("#nextDay").val());
		try {
			$("#postTime").off("click");
			$(".time").calendar("destroy");
		} catch(e) {}
		var area = $("#chooseArea").val();
		var flag = checkIfIsCq(area);
		console.log("flag:" + flag);
		if(flag) {
			var maxDate = new Date($("#today").val());
			maxDate.setDate(maxDate.getDate() + 7);
			$(".time").calendar({
				value : [$("#nextDay").val()],
				minDate : $("#today").val(),
				maxDate : maxDate
			});
		} else {
			$("#postTime").click(function() {
				$.alert("重庆主城外不能选择配送时间");
			});
		}
	};
	
	$(function() {
		enableCalendar();
	});
	
    
    $(".nav-list .nav-item").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var ind = $(".nav-list .nav-item").index(this);
        $(".nav-content>div").eq(ind).show().siblings().hide();
    });
	
	//后退
	$(".goback").click(function(){
		location.href = "shopping/shopping" ;
	}) ;
	
	//选择地址
	$(".address").on("click",".address-shopping",function(){
		$.commonAjax($("#confirmOrderForm").serialize(),"shopping/addressShopping","text",function(data){
			$("#address_popup .address_content").html(data) ;
			$("#address_popup").popup() ;
		}) ;
	}) ;
    
    //删除地址
    $("#address_popup").on("click",".del",function(e){
    	e.stopPropagation() ;
    	var obj = $(this).parents("form"), addressId = $(this).parents("form").find("input[name='id']").val() ;
    	var datas = '' ;
    	$.confirm("确定删除该地址吗?",function(){
    		if($("#add_address_popup #sessionId").val()){
        		//登录后删除地址
        		url = "shopping/deleteAddress" ;
        	}else{
        		//未登录时删除地址
        		url = "shopping/addressShopping" ;
        	}
    		$.commonAjax(obj.serialize(),"shopping/deleteAddress","text",function(data){
    			if(!$("#add_address_popup #sessionId").val()){
    				clearAddress() ;
    			}else{
    				if($("#chooseId").val() && $("#chooseId").val() == addressId){
    					clearAddress() ;
    				}
    			}
    			datas = data ;
    		}) ;
    		$("#address_popup .address_content").html(datas) ;
    	}) ;
    });
    
    //隐藏弹窗
    $("#address_popup").on("click",".pull-left",function(){
    	$.closePopup() ;
    }) ;
    
  //隐藏弹窗
    $("#add_address_popup").on("click",".pull-left",function(){
    	$.closePopup() ;
    	$("#address_popup").popup() ;
    }) ;
    
    //编辑或添加新地址
    $("#address_popup").on("click",".add-address,.edit",function(e){
    	e.stopPropagation() ;
    	$.closePopup() ;
    	var data = {} ;
    	if($(this).hasClass("edit")){
    		data = $(this).parents("form").serialize() ; //编辑地址
    	}
    	$.commonAjax(data,"shopping/addAddressShopping","text",function(data){
			$("#add_address_popup .add_address_content").html(data) ;
			$("#city-picker").cityPicker({
			    title: "请选择地址",
			    onChange:function(p,values,displayValues){
					$("#province").val(displayValues[0]) ;
					$("#city").val(displayValues[1]) ;
					$("#area").val(displayValues[2]) ;
				}
			});
			$("#add_address_popup").popup() ;
		}) ;
    }) ;
    
    //保存新地址
    $("#add_address_popup").on("click",".save-address",function(){
    	var reg = /^1[34578]\d{9}$/, url ;
    			
    	if(!$("#add_address_popup input[name='name']").val()){
    		$.alert("请填写收货人!") ;
    		return ;
    	}
    	if(!reg.test($("#add_address_popup input[name='mobilePhone']").val())){
    		$.alert("请输入正确的联系电话!") ;
    		return ;
    	}
    	if(!$("#add_address_popup input[name='province']").val() || !$("#add_address_popup input[name='city']").val() 
    			|| !$("#add_address_popup input[name='area']").val() || !$("#add_address_popup input[name='address']").val()){
    		$.alert("请完善收货地址!") ;
    		return ;
    	}
    	$.closePopup() ;
    	if($("#add_address_popup #sessionId").val()){
    		//登录后添加或修改地址
    		url = "shopping/saveOrUpdateAddress" ;
    	}else{
    		//未登录时添加或修改地址
    		url = "shopping/addressShopping" ;
    	}
    	$.commonAjax($("#addAddressForm").serialize(),url,"text",function(data){
			$("#address_popup .address_content").html(data) ;
			$("#address_popup").popup() ;
		}) ;
    }) ;
    
    //删除地址
    $("#add_address_popup").on("click",".delete-address",function(){
    	var data = {}, url, addressId = $(this).parents("#add_address_popup").find("#addressId").val() ;
    	if($("#add_address_popup #sessionId").val()){
    		//登录后删除地址
    		url = "shopping/deleteAddress" ;
    		data = $("#addAddressForm").serialize();
    	}else{
    		//未登录时删除地址
    		url = "shopping/addressShopping" ;
    	}
    	$.confirm("确定删除该地址吗?",function(){
    		$.commonAjax(data,url,"text",function(data){
    			$.closePopup() ;
    			if(!$("#add_address_popup #sessionId").val()){
    				clearAddress() ;
    			}else{
    				if($("#chooseId").val() && $("#chooseId").val() == addressId){
    					clearAddress() ;
    				}
    			}
    			$("#address_popup .address_content").html(data) ;
    			$("#address_popup").popup() ;
    		}) ;
    	}) ;
    }) ;
    
    //选择地址
    $("#address_popup").on("click",".chunk",function(){
    	var province = $(this).find("input[name='province']").val(), city = $(this).find("input[name='city']").val(),
    		area = $(this).find("input[name='area']").val(), name = $(this).find("input[name='name']").val(), 
    		address = $(this).find("input[name='address']").val(), mobilePhone = $(this).find("input[name='mobilePhone']").val(),
    		addressId = $(this).find("input[name='id']").val();
    	if(province == "香港" || province == "澳门" || province == "台湾"){
    		$.alert("暂时不支持该区域") ;
    		return ;
    	}
    	$("#consigneePhone,#addressPhone").val(mobilePhone) ;
    	$("#consigneeName,#addressName").val(name) ;
    	$("#chooseProvince").val(province) ;
    	$("#chooseCity").val(city) ;
    	$("#chooseArea").val(area) ;
    	$("#chooseAddress").val(address) ;
    	var data = $("#confirmOrderForm").serialize(),url = "shopping/chooseAddress" ;
    	$.commonAjax(data,url,"json",function(data){
    		$.closePopup() ;
    		$(".address-info").html('<p class="consignee">收货人:<span>'+ name +'</span><span class="phone">'+ mobilePhone +'</span></p>'+
            '<p class="del">收货地址:<span>'+ province + city + area + address +'</span></p>') ;
    		console.log("新地址变更后的运费:" + parseFloat(data.data.postPrice).toFixed(2));
    		$(".postPrice").html("￥" + parseFloat(data.data.postPrice).toFixed(2)) ;
    		$("#postHiddenPrice").val(parseFloat(data.data.postPrice).toFixed(2)) ;
    		if(data.data.saleMoney > 0 ){
    			$(".sale-money").parents(".weui-form-preview__item").show();
    			$(".sale-money").html("-￥" + (parseFloat(data.data.saleMoney).toFixed(2) > 68 ? 68 : parseFloat(data.data.saleMoney).toFixed(2))) ;
    		}else{
    			$(".sale-money").parents(".weui-form-preview__item").hide();
    		}
    		$("#saleHiddenMoney").val((parseFloat(data.data.saleMoney).toFixed(2) > 68 ? 68 : parseFloat(data.data.saleMoney).toFixed(2))) ;
    		$("#chooseId,#addressId").val(addressId) ;
    		countPrice() ;
    		
		}) ;
    	//重新加载优惠券列表
    	//loadCouponList(data) ;
    	
    	enableCalendar();
    }) ;
    
    //加载优惠券列表
    function loadCouponList(data){
    	$.commonAjax(data,"shopping/findCouponList","text",function(data){
    		$(".coupon-list").html(data) ;
		}) ;
    }
    
    //添加订单
    $(".row").on("click",".addorder",function(){
    	if(!$("#consigneePhone").val() || !$("#consigneeName").val()){
    		$.alert("请填写收货地址") ;
    		return ;
    	}
    	if($("#goodsAllpoint").val() && parseInt($("#accpoint").val(),10) <  parseInt($("#goodsAllpoint").val())){
    		$.alert("积分不足以兑换商品") ;
    		return ;
    	}
    	//此处0表示重庆主城
    	if($("#isDelivery").val()==0){
    		var area=["渝中区","江北区","南岸区","九龙坡区","沙坪坝区","大渡口区","北碚区","渝北区","巴南区"];
    		var i = area.length;
    		var temp=0;
    	    while (i--) {  
    	        if ($("#chooseArea").val()!=area[i] ) {  
    	        	temp++;
    	        }
    	    }
    	    if(temp==9){
    	    	$.alert("包含不支持全国配送的商品，请重新选择");
    	    	return;
    	    }
		}
    	if($("#postTime").length > 0 && !$("#postTime").val()){
    		$.alert("请填写配送时间") ;
    		return ;
    	}
    	if($("#isCrossGoods").val() == 0){
    		if(!$("#idCardName").val() || !$("#consigneeIdcard").val()){
    			$.alert("请输入身份证信息") ;
    			return ;
    		}
    		if($("#idCardName").val() != $("#consigneeName").val()){
    			$.alert("身份证姓名与收货人姓名不匹配") ;
    			return ;
    		}
    		if($("#consigneeIdcard").val()!=null || $("#consigneeIdcard").val()!=""){
        		var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//15位身份证验证
        		var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;//18位身份证验证
        		if($("#consigneeIdcard").val().length!=15 && $("#consigneeIdcard").val().length!=18){
        			$.alert("身份证不合法");
        			return;
        		}
        		if($("#consigneeIdcard").val().length==15){
        			if(!isIDCard1.exec($("#consigneeIdcard").val())){
        				$.alert("身份证不合法");
        				return;
        			}
        		}else if($("#consigneeIdcard").val().length==18){
        			if(!isIDCard2.exec($("#consigneeIdcard").val())){
        				$.alert("身份证不合法");
        				return;
        			}
        		}
        	}
    	}
      //判断是否可以提交订单
	  $.commonAjax({},"order/isCommit","json",function(data){
		if(!data){
			$.alert("暂时不能提交订单");
			return;
		}else{
			 //拼购抽奖、三免一活动
			 $.commonAjax({goodsId:$("input[name='goodsId']").val()},"order/judgeNewActivityGoods","json",function(data){
				 if(data.data){
					 $.alert(data.data) ;
				 }else{
					 $("#confirmOrderForm").submit() ;
				 }
			 }) ;
		}
	  }) ;
    });
    
    //删除地址时查看当前地址是否被选中
    function clearAddress(){
    	$(".address-info").html('<p style="text-align: right;color:#666;">请输入收货地址</p>') ;
		$(".postPrice").html("￥0.00") ;
		$("#consigneePhone,#addressPhone").val("") ;
    	$("#consigneeName,#addressName").val("") ;
    	$("#chooseProvince").val("") ;
    	$("#chooseCity").val("") ;
    	$("#chooseArea").val("") ;
    	$("#chooseAddress").val("") ;
    	$("#chooseId,#addressId").val("") ;
    	//countPrice() ;
    }
    
    //优惠卷列表
    $(".address").on("click",".coupon",function(){
    	var couponHtml = '<div style="text-align:center;padding:2rem 0;color:#999;font-size:0.75rem;">'+
		'<img alt="" src="resources/common/images/empty.png" style="margin:0 0 0.5rem;width: 3rem;"/>'+
		'<p>没有可使用的优惠券</p>'+
	'</div>' ;
    	//加载优惠券列表
    	loadCouponList($("#confirmOrderForm").serialize()) ;
    	if($(".coupon-list .coupon-div").length <= 0){
    		$(".coupon-list").html(couponHtml) ;
    	}
    	$("#coupon_popup").popup() ;
    }) ;
    
    //查看可用优惠券
    $("#coupon_popup").on("click",".pull-left",function(){
    	$.closePopup() ;
    }) ;
    
    //选择优惠券
    $("#coupon_popup").on("click",".coupon-div",function(){
    	$(".choose-coupon").html($(this).find(".coupon-name").html()) ;
    	$("#couponId").val($(this).attr("coupon-id")) ;
    	$("#couponHiddenPrice").val($(this).attr("coupon-price")) ;
    	$(".coupon-show").html("-￥" + parseFloat($(this).attr("coupon-price")).toFixed(2)) ;
    	countPrice() ;
    	$.closePopup() ;
    }) ;
    
    //选择不适用优惠券
    $(".notUse").click(function() {
    	$(".choose-coupon").html("不使用");
    	$("#couponId").val("");
    	$("#couponHiddenPrice").val("");
    	$(".coupon-show").html("￥0.00");
    	countPrice();
    });
    
    //计算显示价格
    function countPrice(){
    	var couponPrice = parseFloat($("#couponHiddenPrice").val() ? $("#couponHiddenPrice").val() : "0.00"), 
    		price = parseFloat($("#sumPrice").val()),
    		postPrice = parseFloat($("#postHiddenPrice").val() ? $("#postHiddenPrice").val() : "0.00");
    		saleMoney = parseFloat($("#saleHiddenMoney").val() ? $("#saleHiddenMoney").val() : "0.00");
    	$(".sumPriceShow").html(((price - couponPrice - saleMoney) >= 0 ?(price - couponPrice + postPrice - saleMoney) : postPrice).toFixed(2)) ;
    }
    
    //发票
    $(".invoiceDiv").click(function(){
    	if($("#invoiceContent").css("display") == "none"){
    		$(".near-content").slideToggle() ;
    	}else{
    		$(".near-content").slideToggle() ;
    	}
    }) ;
});