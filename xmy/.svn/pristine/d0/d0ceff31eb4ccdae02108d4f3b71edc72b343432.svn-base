$(function(){
	//计算高度
	$(".commodity-table").each(function(){
		var num =$(this).children(".table-tr").children().children(".commodity-list").length;
		var height = num*105;
		$(this).children().not($(this).children(".table-order")).children().height(height);
	});
	$(".chunks1").click(function(){
		$(".chunk1").show();
		$(".chunk2").hide();
		$(".chunk3").hide();
		$(".chunk4").hide();
		$(".chunk5").hide();
	});
	$(".chunks2").click(function(){
		$(".chunk1").hide();
		$(".chunk2").show();
		$(".chunk3").hide();
		$(".chunk4").hide();
		$(".chunk5").hide();
	});
	$(".chunks3").click(function(){
		$(".chunk1").hide();
		$(".chunk2").hide();
		$(".chunk3").show();
		$(".chunk4").hide();
		$(".chunk5").hide();
	});
	$(".chunks4").click(function(){
		$(".chunk1").hide();
		$(".chunk2").hide();
		$(".chunk3").hide();
		$(".chunk4").show();
		$(".chunk5").hide();
	});
	$(".chunks5").click(function(){
		$(".chunk1").hide();
		$(".chunk2").hide();
		$(".chunk3").hide();
		$(".chunk4").hide();
		$(".chunk5").show();
	});
	$(".paged").click(function(){
		window.location.href = "/order/myOrder/"+$("#stu").val()+"/"+$(this).val()+"?set=3";
	})
	//控制显示 $("#stu").val() 0:全部订单 1.待支付 2.待发货 3.待收货 4.待评价
	if($("#stu").val() == 1){
		$(".chunk1").hide();
		$(".chunk2").show();
		$(".chunk3").hide();
		$(".chunk4").hide();
		$(".chunk5").hide();
	}
	if($("#stu").val() == 2){
		$(".chunk1").hide();
		$(".chunk2").hide();
		$(".chunk3").show();
		$(".chunk4").hide();
		$(".chunk5").hide();
	}
	if($("#stu").val() == 3){
		$(".chunk1").hide();
		$(".chunk2").hide();
		$(".chunk3").hide();
		$(".chunk4").show();
		$(".chunk5").hide();
	}
	if($("#stu").val() == 4){
		$(".chunk1").hide();
		$(".chunk2").hide();
		$(".chunk3").hide();
		$(".chunk4").hide();
		$(".chunk5").show();
	}
	//控制标题状态的链接跳转
	$(".order-tab-ul").children().eq(0).click(function(){
		window.location.href = "/order/myOrder/"+"0/"+1+"?set=3";
	})
	$(".order-tab-ul").children().eq(1).click(function(){
		window.location.href = "/order/myOrder/"+"1/"+1+"?set=3";
	})
	$(".order-tab-ul").children().eq(2).click(function(){
		window.location.href = "/order/myOrder/"+"2/"+1+"?set=3";
	})
	$(".order-tab-ul").children().eq(3).click(function(){
		window.location.href = "/order/myOrder/"+"3/"+1+"?set=3";
	})
	$(".order-tab-ul").children().eq(4).click(function(){
		window.location.href = "/order/myOrder/"+"4/"+1+"?set=3";
	})
	//顶部上一页
	$(".btnup").click(function(){
		var countPage = $("#countPage").val();//总页数
		var pageIndex = $("#pageIndex").val();//当前页
		if(pageIndex == 1){
			$(this).addClass("active");
			$(".btndown").removeClass("active");
			var text = "已经是第一页了！";
			pop(text);
		}else{
			$(".btndown").removeClass("active");
			$(this).removeClass("active");
			pageIndex = parseInt(pageIndex) - 1 ;
			window.location.href = "/order/myOrder/"+$("#stu").val()+"/"+pageIndex+"?set=3";
		}
	});
	//顶部下一页
	$(".btndown").click(function(){
		$(".btnup").removeClass("active");
		var countPage = $("#countPage").val();//总页数
		var pageIndex = $("#pageIndex").val();//当前页
		if(pageIndex == countPage){
			$(this).addClass("active");
			var text = "已经是最后一页了！";
			pop(text);
		}else{
			$(this).removeClass("active");
			pageIndex = parseInt(pageIndex) + 1 ;
			window.location.href = window.location.href = "/order/myOrder/"+$("#stu").val()+"/"+pageIndex+"?set=3";
		}
	});
	
	//分页上一页下一页的跳转
	$(".paging-ul").each(function(){
		var countPage = $("#countPage").val();//总页数
		var pageIndex = $("#pageIndex").val();//当前页
		$(this).children().first().click(function(){
			if(pageIndex == 1){
				var text = "已经是第一页了！";
				pop(text);
			}else{
				pageIndex = parseInt(pageIndex) - 1 ;
				window.location.href = window.location.href = "/order/myOrder/"+$("#stu").val()+"/"+pageIndex+"?set=3";
			}
		})
		$(this).children().last().click(function(){
			if(pageIndex == countPage){
				var text = "已经是最后一页了！";
				pop(text);
			}else{
				pageIndex = parseInt(pageIndex) + 1 ;
				window.location.href = window.location.href = "/order/myOrder/"+$("#stu").val()+"/"+pageIndex+"?set=3";
			}
		})
	})
	
	$('.skip').keydown(function(e){
		var _this = $(this);
		if(e.keyCode==13){
			var countPage = $("#countPage").val();//总页数
				var page = $(this).find("input").val();
				regNum=/^[0-9]*$/;
				if(!regNum.test(page)){
					pop("跳转页数请输入数字！")
					return false;
				}
				if(page < 1){
					var text = "跳转指定页数请输入整数不能为负数！";
					pop(text);
					return;
				}else if(parseInt(page) > parseInt(countPage)){
					var text = "跳转指定页数不能大于总页数！";
					pop(text);
					return;
				}else{
					window.location.href = "/order/myOrder/"+$("#stu").val()+"/"+page+"?set=3";
				}
		}
	});
	
	//页数跳转
	$(".skip").each(function(){
		var _this = $(this);
		skipPage(_this);
	});
	
	
	
	//订单搜索
	$(".commodity-search").each(function(){
		 regChanice=/^[\u4e00-\u9fa5]{0,}$/;
		 regNumber=/^[0-9]*$/;
		 regLetter=/^[A-Za-z]+$/;
		$(this).children().last().click(function(){
			var content = $(this).parent().children().first().val();//搜索内容
			if (content != '') {
				window.location.href = "/order/myOrder/screen/"+$("#stu").val()+"/"+1+"?no="+content+"&set=3";
			} else {
				pop("搜索条件不能为空！");
			}
			
		})
	})
	
	//订单搜索
	$(".commodity-search").find("input[name='searchOrder']").keydown(function (e){
		if(e.keyCode==13){ 
			 regChanice=/^[\u4e00-\u9fa5]{0,}$/;
			 regNumber=/^[0-9]*$/;
			 regLetter=/^[A-Za-z]+$/;
			var content = $(".commodity-search").find("input[name='searchOrder']").val();//搜索内容
			if(regChanice.test(content)){
				var text = "订单编号不能为汉字！";
				pop(text);
			}else if(regNumber.test(content)){
				window.location.href = "/order/myOrder/screen/"+$("#stu").val()+"/"+1+"?no="+content+"&set=3";
			}else if(regLetter.test(content)){
				var text = "订单编号不能为字母！";
				pop(text);
			}else{
				var text = "订单编号为数字的组合！";
				pop(text);
			}
		}
	})
	
	
	//订单操作
	$(".appraise-li").each(function(){
		//为下面的每一个元素赋值
		$(this).children().click(function(){
			var orderId = $(this).parent().parent().parent().find("input").val();//点击的订单id
			var console = $(this).attr("value");//点击的操作内容
			//订单支付
			if(console == "付款"){
				window.location.href = "/order/payOrder/"+orderId;
			}
			if(console == "取消订单"){
				//弹窗的确定按钮
				var btnFn = function(){
					$.ajax({
						url:"order/delete/"+orderId,
						type:"get",
						success:function(){
							var text = "取消订单成功！";
							pop(text);
							window.location.href = "/order/myOrder/"+$("#stu").val()+"/1?set=3";
						}
					});
				  return false;
				};
				easyDialog.open({
				  container : {
				    header : '提示',
				    content : '您确定要取消该订单吗？',
				    yesFn : btnFn,
				    noFn:true
				  }
				});
			}
		})
	});
	//订单详情
	$(".del-li").each(function(){
		var text=$(this).text();
		$(this).click(function(){
			if(text=="订单详情"){
				var string = $(this).parent().parent().attr("value").trim();//获取订单编号
				window.location.href="/order/detail/"+string;
				
			}
		})
	});
	
	//确认收货
	$(".confirm-receipt").click(function (){
		var orderId = $(this).data("id");
		$.ajax({
			url:"order/receive/"+orderId,
			type:"get",
			success:function(data){
				window.location.href = "/order/myOrder/"+$("#stu").val()+"/1?set=3";
			}
		})
	})
	
	//批量确认收货
	$(".relust").click(function(){
		var orderId = "0";
		$(this).parent().parent().parent().find(".order").each(function(){
			if(this.checked){
				var status=$(this).attr("sta");
				if(status == 3){
					orderId += ","+$(this).val();
				}
			}
		})
		if(orderId=="0"){
			pop("没有待收货的订单请您核实！")
			return false;
		}else{
			var btnFn = function(){
				//确认收货的事件
				$.ajax({
					url:"order/receive/"+orderId,
					type:"get",
					success:function(data){
						window.location.href = "/order/myOrder/"+$("#stu").val()+"/1?set=3";
					}
				})
			}
			//去登陆的按钮事件
			var colse = function(){
				easyDialog.close();
				return false;
			}
			//弹窗
			easyDialog.open({
			  container : {
			    header : '提示',
			    content : '您确定要批量操作该订单吗？',
			    yesFn : btnFn,
			    noFn:colse,
			    noText:"取消"
			  }
			});
		}
	});
	//确认收货点击事件
	$(".take-li").each(function(){
		$(this).children().click(function(){
			var orderId = $(this).parent().parent().parent().find("input").val();//点击的订单id
			var console = $(this).val();//点击的操作内容
		})
	});
	/*//批量操作
	$(".table").children().find("input[type='checkbox']").eq(0).each(function(){
		$(this).click(function(){
			var mes = this.checked;
			$(".table").children().find(".order").each(function(){
				this.checked = mes;
			})
		})	
	})*/
	//批量
	$(".all").click(function(){
		var mes = this.checked;
		$(this).parent().parent().parent().find(".order").each(function(){
			this.checked = mes;
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

function skipPage(_this) {
	var countPage = $("#countPage").val();//总页数
	_this.children().last().click(function(){
		var page = $(this).parent().children().first().val();
		regNum=/^[0-9]*$/;
		if(!regNum.test(page)){
			pop("跳转页数请输入数字！")
			return false;
		}
		if(page < 1){
			var text = "跳转指定页数请输入整数不能为负数！";
			pop(text);
		}else if(parseInt(page) > parseInt(countPage)){
			var text = "跳转指定页数不能大于总页数！";
			pop(text);
		}else{
			window.location.href = "/order/myOrder/"+$("#stu").val()+"/"+page+"?set=3";
		}
	})
}

