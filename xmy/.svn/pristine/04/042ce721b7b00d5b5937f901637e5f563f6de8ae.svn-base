$(document).ready(function () {
		$("#orderCommit").show();
		//查询默认选中的地址  算出运费
		$(".balances-list ul li").each(function(){
			if ($(this).hasClass('active1')) {
				getFreight($(this).data('province'),$(this).data("city"),$(this).data('area'));
			}
		})
		
		checkDefaultArea();  //判断默认的地址是否在九城区内
		
	 	var isDelivery = $("#isDelivery").val();  //是否包含不支持全国配送的商品 0 包含 1不包含
		$.ajax({
			url:"commons/user",
			type:"get",
			success:function(data){
				if(data == 1){//未登录删除
					$("#address").show();
				}
			}
		});
		//顶部状态控制
		$(".balancess-step").eq(2).addClass("active").siblings().removeClass("active");
        
		//选择收货地址
		$(".balances-list .item").click(function () {
            $(this).addClass("active1").siblings().removeClass("active1");
            var province = $(this).data("province");
            var city = $(this).data("city");
            var area = $(".balances-list").find(".active1").children().last().val();  //收货地址区县
            checkdeliveryTime(area);//判断配送时间
            getFreight(province,city,area);
        });
        $(".ins-right .item").click(function () {
            $(this).addClass("active2").siblings().removeClass("active2")
        });
        $(".notice").hover(
            function(){
                $(".notice-text").stop();
                $(".notice-text").slideDown();
            },
            function(){
                $(".notice-text").stop();
                $(".notice-text").slideUp();
            }
        );
        $(".bimmg").on("click",".bimmg-li",function(){
        	if($(this).find(".icons").css('display')=='none'||$(this).find(".icons").css('display')=='inline'){
        		 $(this).addClass("add-bg").removeClass("add").find(".icons").show();
                 $(this).siblings().removeClass("add-bg").addClass("add").find(".icons").show();
        	}else{
        		$(this).addClass("add").removeClass("add-bg").find(".icons").hide();
                $(this).siblings().removeClass("add-bg").addClass("add").find(".icons").hide();
        	}
        });
        
        $(".balances-list .item").hover(
			function(){
	        	$(this).addClass("active");
	        	$(this).find(".console").show();
	        },
	        function(){
	        	$(this).removeClass("active");
	        	$(this).find(".console").hide();
	        }
        );
        
        $(".ins-right .item").hover(
    			function(){
    	        	$(this).addClass("active3");
    	        	$(this).find(".console").show();
    	        },
    	        function(){
    	        	$(this).removeClass("active3");
    	        	$(this).find(".console").hide();
    	        }
            );
        
        // 点击编辑地址
        $(".console").each(function(){
        	$(this).children().first().click(function(){
        		var name = $(this).parent().parent().children().eq(1).text();
        		var addr =　$(this).parent().parent().parent().children().eq(2).children().eq(0).text();
        		var phone =　$(this).parent().parent().parent().children().eq(2).children().eq(1).text();
        		var _province = $(this).data("province"),_city = $(this).data("city"),_county=$(this).data("area"),id=$(this).data("id"),_address=$(this).data("address");
        		$("#addressid").val(id);
        		$("#name").val(name);
        		$("#adrress").val(_address);
        		$("#phone").val(phone);
        		$("#sparePhone").val($(this).attr("data-sparePhone"));
        		$("#s_province  option[value='"+_province+"'] ").attr("selected",true);
        		change(1); //渲染第二级
        		$("#s_city  option[value='"+_city+"'] ").attr("selected",true);
        		change(2); //渲染第三级
        		$("#s_county  option[value='"+_county+"'] ").attr("selected",true)
        		$("#address").show();
        		
        	});
        	
        	$(this).children().last().click(function(){
        		var adresid = $(this).parent().parent().parent().find(".address").val();
        		//获取商品的全部ID
    	    	var goodsIds = "0";
            	$(".shoped-con").each(function(){
            		goodsIds += ","+$(this).data("goodsid");
            	});
            	window.location.href ="/order/deleteAddresss/"+adresid+"/"+goodsIds;
        	});
        });
        
        //发票内容
        $("#showInvoice").click(function(){
        	$("#invoice").toggle();
        })
        //收货地地址console
        $("#newAddress").hover(function(){
        	$("#console").toggle();
        });
        //编辑按钮点击事件
        $("#console").click(function(){
        	$("#address").show();
        })
        //显示填写收货地址的区域
        $(".ad-list").click(function(){
        	$("#address").toggle();
        })
	    $(".adress-btn").click(function () {
	        $(this).addClass("default-adress-btn").removeClass("active-adress-btn");
	        $(this).parent().parent().siblings().find(".adress-btn").hide().removeClass("default-adress-btn").addClass("active-adress-btn")
	    })
	    //点击选择优惠券
	    $(".quan").click(function(){
	    	$(".blances-modal").fadeIn();
	    	/*if($(".isUse").val()==$("#conponId").val()){
	    		$(this).addClass("add-bg").find(".icons").show();
                $(this).siblings().removeClass("add-bg").find(".icons").show();
	    	}*/
	    });
	    
	    //点击保存收货地址
	    $(".btn-default").click(function(){
	    	//获取商品的全部ID
	    	var goodsIds = "0";
        	$(".shoped-con").each(function(){
        		goodsIds += ","+$(this).data("goodsid");
        	});
        	$("#goodsId").val(goodsIds);
        	//收货人姓名
        	var name=$("#name").val();
        	$("#consigneeName").val(name);
        	$("#conName").text(name);
        	//收货地址
        	var address=$("#adrress").val(); //详细地址
        	var s_province = $("#s_province").val(),s_city = $("#s_city").val(),s_county=$("#s_county").val();
        	var allAddress = s_province + s_city + s_county+address;
        	$("#conProvince").val(s_province);
        	$("#consigneeAddress").val(allAddress); //表单数据
        	$("#conAddress").text(allAddress); //界面显示
        	//校验收货人姓名和地址是否为空
        	if (name == '' || address == '') {
        		pop("收货人姓名和详细地址不能为空！");
        		return false;
        	}
        	//收货人电话
        	var phone=$("#phone").val();
        	regPhone = /^1[34578]\d{9}$/;
        	if(!regPhone.test(phone)){
        		pop("你输入的收货人的电话号码格式不对！");
        		return false;
        	}else{
        		$("#consigneePhone").val(phone);
        		$("#conPhone").text(phone);
        	}
        	$.ajax({
    			url:"commons/user",
    			type:"get",
    			success:function(data){
    				if(data == 1){//未登录删除
    					pop("收货地址添加成功!");
    					$("#address").hide();
    					getFreight(s_province,s_city,s_county);
    					$("#newAddress").show();
    					checkdeliveryTime(s_county);//判断配送时间
    				}else{
    					$("#addressFrom").submit();
    				}
    			}
    		});
	    })
	    
        //添加选择优惠卷后修改应付金额
        $("button").last().click(function(){
        	if ($(".add-bg").length >0 ) {
        		var couponMenoy = $(".add-bg").children().first().children().first().attr("value");//得到优惠卷的金额
            	var quota = $(".add-bg").children().first().children().first().attr("quota");//需要满足多少元此优惠券才生效
            	var isUse = $(".add-bg").find("input[name='isUse']").val();  //是否可用
            	var conponId = $(".add-bg").find("input[name='couponid']").val();
            	var freight = $("#freight").val();//运费
            	var sumPrice =$("#sumMoney").val();//订单总金额(折后的金额含运费)
            	sumPrice = parseFloat(sumPrice) - parseFloat(freight); //减去运费
            	if(sumPrice <= 0 ) {
            		pop("亲，您的订单金额除运费外为0不能使用优惠券哟！");
            		return;
            	}
            	if (parseFloat(quota) >　parseFloat(sumPrice)) {
            		pop("亲，订单金额必须满"+quota+"元，才能使用该优惠券！");
            		return;
            	}
            	//计算总金额减去优惠卷金额后的应付金额
            	var newPrice = parseFloat(sumPrice) - parseFloat(couponMenoy);
            	if (newPrice < 0 ) {  //当抵用金额大于订单金额 付款金额默认为0
            		newPrice = 0;
            	}
            	newPrice = parseFloat(newPrice) + parseFloat(freight); //抵用券不能抵用运费
            	//为各个地方赋值
            	$("#selectedquta").val(quota); //优惠券满多少能使用
            	$(".quan").val(couponMenoy+"抵用券"); //页面显示
            	$("#nv").text("￥-"+couponMenoy);  //页面显示 抵用的金额
            	$(".payMoney").text("￥"+ newPrice.toFixed(2));   //总金额
            	$("#conponId").val(conponId);
        	}else{
        		$("#selectedquta").val(0); //优惠券满多少能使用
            	$(".quan").val('选择使用优惠券'); //页面显示
            	$("#nv").text("￥-0.00");  //页面显示 抵用的金额
            	$(".payMoney").text("￥"+ $("#sumMoney").val());   //总金额
            	$("#conponId").val("");
        	}
        	//隐藏弹出的优惠卷选择框
        	$(".popup").hide();
        });
      
        //发票选择
        $(".invType").last().click(function(){
        	var val=$("#userOrcount").val();
        	$("#invoce").show();
        	$("#countName").show();
        	$("#userOrcount").val(1);
        })
        $(".invType").first().click(function(){
        	var val=$("#userOrcount").val();
        	$("#invoce").hide();
        	$("#countName").hide();
        	$("#userOrcount").val(0);
        })
        
        //提交
        $(".btn-balances").click(function(){
        	//验证身份证输入
        	var consigneeName = getConsigneeName() ; //获取收货人姓名
        	var out=$("#out").val();
        	if(out == 0){//含有跨境商品
        		var userName=$("#userName").val().trim();
        		var card=$("#card").val().trim();
        		if(userName == "" || card == ""){
        			var content = "跨境商品用户名和身份证号码不能为空!";
        			pop(content);
        			return false;
        		}else{
        			regIdCard =/^\d{15}|\d{18}$/;
        			if(!regIdCard.test(card)){
        				pop("身份证输入不正确！");
        				return false;
        			}
        			if (userName != consigneeName ) {
        				pop("身份证姓名必须与收货人姓名一致！");
        				return false;
        			}
        			$("#idCard").val(card);
        		}
        	}else{
        		$("#idCard").val("");
        	}
        	//1.获取收货地址信息的id
        	var addressId = $(".balances-list").find(".active1").children().first().val();
        	var area = $(".balances-list").find(".active1").children().last().val();  //收货地址区县
        	$("#addressId").val(addressId);
        	if($("#addressId").val() == ""){
        		if($("#consigneeAddress").val() == "" || $("#consigneePhone").val() == ""){
		        	//弹窗
		        	pop("收货地址不能为空");
		        	return false;
        		}
        		area = $("#s_county").val();
        	} 
        	//2.获取用户指定配送时间
        	var datetime = $(".ins-right").first().find(".active2").data("sendtime");
        	$("#datetime").val(datetime);
        	//3.获取发票类容信息
        	var invocie = $("select").last().val();
        	$("#invocie").val(invocie);
        	var taxNo=$("#taxpayerNo").val().trim();   //纳税人识别号
        	var comName = $("#companyName").val();
        	var val=$("#userOrcount").val();
        	//判断发票类型
        	if(val == 1){
        		//验证纳锐人识别号
        		if(taxNo == ""){
        			pop("纳税人识别号不能为空！");
        			return false;
        		}else{
        			$("#taxNo").val(taxNo); //纳税人识别号
        			$("#comName").val(comName);
        		}
        	}else{
        		$("#taxNo").val("");
        		$("#comName").val("");
        	}
        	//4.获取商品ids
        	var goodsIds = "0";
        	$(".shoped-con").each(function(){
        		goodsIds += ","+$(this).data("goodsid");
        	})
        	$("#goodsIds").val(goodsIds);
        	//5.获取备注信息
        	var remark = $("#remark").val();
        	$("#remarks").val(remark);
        	var accPoints=$("#accPoints").val();
        	var sumPoints=$("#sumPoints").val();
        	if(parseInt(accPoints)<parseInt(sumPoints)){
    			pop("你的可用积分不足！");
    			return false;
        	}
        	//7. 判断订单中是否含有不支持全国配送的  配送地址又在主城区以外的
        	if (isDelivery == 0) {
        		if(!checkConsigneeAddress(area)) {
        			pop("您的订单里面包含仅限庆主城配送商品，请修改地址或者调整购物车商品后再行提交！");
        			return false;
        		}
        	}
        	//之前判断是否购买过三免一，拼购商品
    		$.ajax({
    			url:"order/isContantSpeicleGoods?goodsIds="+goodsIds,
    			type:"post",
    			success:function(data){
    				if(data == 3){//未登录
    					pop("您未登录不能购买活动商品！");
    				}
    				if(data == 1){//
    					pop("您已经购买过了拼购商品！");
    				}
    				if(data == 2){
    					pop("您已经购买过了三免一商品！");
    				}
    				if(data == 0){
    					$("#form").submit();
    					$(".btn-balances").attr("disabled", true);  //提交订单按钮失效
    				}
    			}
    		});
        });
        
        //绑定优惠卷
        $("#couBtn").click(function(){
        	var code=$("#couponCode").val().trim();
        	$.ajax({
        		url:"order/userBindCoupon",
        		type:"post",
        		data:{"paperCode":code},
        		dataType:"json",
        		success:function(data){
        			if (data == 0) {
        				pop("未登录不能绑定优惠券！");
        			}
        			if(data == 1){//优惠卷不存在
        				pop("您输入的优惠卷编码不存在请您核实！")
        			}
        			if(data == 2){//该优惠卷已经被人使用过
        				pop("改优惠卷已经使用了请核实！")
        			}
        			if(data == 3){
        				pop("您已经绑定了相同类型的优惠卷请使用后在绑定!")
        			}
        			if(data == 4){
        				pop("绑定成功！");
        				$.ajax({
        					url:"order/couponListPopup",
        					type:"POST",
        					dataType:"html",
        					data:{"pageIndex":1},
        					success:function(data){
        						$(".coupon-list").html(data);
        					}
        				});
        			} 
        		}
        	})
        })
});
    
//公共弹窗方法 简单的 
function pop(text){
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

//判断收货人地址是否在重庆主城以内
function checkConsigneeAddress(area){
	if (area == '渝中区') {
		return true;
	}
	if (area == '江北区') {
		return true;
	}
	if (area == '沙坪坝区') {
		return true;
	}
	if (area == '南岸区') {
		return true;
	}
	if (area == '九龙坡区') {
		return true;
	}
	if (area == '渝北区') {
		return true;
	}
	if (area == '北碚区') {
		return true;
	}
	if (area == '大渡口区') {
		return true;
	}
	if (area == '巴南区') {
		return true;
	}
}

//判断配送时间
function checkdeliveryTime(area){
	var out=$("#out").val();  //是否含有跨境商品
	if (checkConsigneeAddress(area) && out!=0 ) {
		$("#timeDiv").css("display","block");
		$("#deliveryTime").css("display","none");
	} else {
		$("#timeDiv").css("display","none");
		$("#deliveryTime").css("display","block");
		if (!checkConsigneeAddress(area)) {
			$("#reminderContent").text("您的订单收货地址为重庆主城以外，暂不支持指定配送时间!")
		}
		if (out == 0) {
			$("#reminderContent").text("您的订单包含跨境商品，暂不支持指定配送时间!")
		}
	}
}

var _sumPrice = $("#totalMoney").val(); //订单商品总金额
var _goodsIds = '';
$(".shoped-con").each(function(){  //获取商品id字符串
	_goodsIds += $(this).data("goodsid")+",";
})

//计算运费
function getFreight(province,city,area){
    $.ajax({ 
		url: "order/getFreight",
		type: "POST",
		data:{"province":province,"goodsIds":_goodsIds,"sumPrice":_sumPrice,"area":area},
		dataType: "JSON",
		async: false,
		success:function(result){
			 if (result.data.isSuccess == 0) {
				 $(".freight").text("￥"+result.data.freight.toFixed(1)); //运费
				 $("#freight").val(result.data.freight.toFixed(1));
				 $("#feeyMoney").text("￥"+result.data.disCountPrice); //6.8折优惠的金额
				 $("#sumMoney").val(result.data.toltalPrice);
				 $(".totalMoney").text("￥"+result.data.toltalPrice); //计算过后的金额含运费
				 $(".payMoney").text("￥"+result.data.toltalPrice); //应付总金额
				 $("#provinceAddress").val(province); //收货地址 省市
				 $("#cityAddress").val(city);  //收货地址 市
				 $("#countyAddress").val(area); //收货地址  区县
				 var sumMoney = result.data.toltalPrice,freight = result.data.freight,selectedquta = $("#selectedquta").val();
				 var notIncludeFreight = parseFloat(sumMoney) - parseFloat(freight); //不包含运费的折后金额
				 if (parseFloat(notIncludeFreight) < parseFloat(selectedquta) ) { //如果折后的金额小于选择优惠券满多少的金额 则将选择的优惠券信息清空
					 clearCouponInfo();
				 }
			 } else {
				 pop("商品信息错误");
			 }
		}
	})
}

//清除优惠券记录
function clearCouponInfo(){
	//为各个地方赋值
	$(".quan").val("选择使用优惠券"); 
	$("#nv").text("￥-0.0");
	$("#conponId").val("");
}

//查询默认地址是否在九城区以内
function checkDefaultArea(){
	var area = $(".balances-list").find(".active1").children().last().val();  //收货地址区县
	checkdeliveryTime(area);
}


//取出收货人姓名
function getConsigneeName(){
	var isLogin = $("#isLogin").val(); //是否登录
	var name = "";
	//查询默认选中的地址  
	if (isLogin == 0) {
		$(".balances-list ul li").each(function(){
			if ($(this).hasClass('active1')) {
				name = $(this).data("name");
				$("#consigneeName").val(name)
			}
		});
	} else {
		name = $("#conName").text();
		$("#consigneeName").val(name)
	}
	return name;
}





