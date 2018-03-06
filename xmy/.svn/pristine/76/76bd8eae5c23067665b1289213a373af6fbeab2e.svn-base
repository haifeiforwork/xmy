/**
 * 
 */

	var formObj = {model:"<form action='index/login' method='post' id='urlForm'><input type='hidden' name='requestUri' value='URL'></form>",url:"/"} ;
	
	$.extend({
		//普通的Ajax请求
		commonAjax : function(data,url,dataType,callback){
			var result ;
			$.ajax({
				data:data,
				dataType:dataType,
				url:url,
				type:"post",
				async:false, 
				cache:false,
				success:function(d){
					if(d == "SESSION_LOST"){
						sessionLost() ;
					}else if(d == "BUSINESS_EXCEPTION"){
						$.alert("系统内部繁忙") ;
					}else{
						return result = d ;
					}
					//return result = d ;
				},
				error:function(error){
					if(error.responseText == "SESSION_LOST")
						sessionLost() ;
					else if(error.responseText == "BUSINESS_EXCEPTION"){
						$.alert("系统内部繁忙",function(){
							location.reload() ;
						}) ;
					}else{
						$.alert(error.responseText,function(){
							location.reload() ;
						}) ;
					}
				}
			});
			//执行回调
			if(result && callback) callback(result);
			
		},
		
		initFooterJS : function(){
			//导航
		    $(".top-nav .weui-cell").click(function () {
		        $(this).addClass("active").siblings().removeClass("active") ;
		    }) ;
		    //底部模块跳转
		    $(".footer").on("click",".weui-flex__item",function(){
		    	location.href = $(this).attr("url") ;
		    }) ;
		},
		
		initHeadJs : function(){
			$(".modal-some").html('<ul>'+
					'<li url="home/home"><img src="resources/common/images/btn-more-zy@2x.png" alt="" style="position: relative;">首页</li>'+
                    '<li url="mine/findMessage"><img src="resources/common/images/btn-more-xx@2x.png" alt="" style="position: relative;">消息</li>'+
                    '<li url="goods/searchGoodsByName"><img src="resources/common/images/btn-more-ss@2x.png" alt="" style="position: relative;">搜索</li>'+
                    '<li url="gotoPage/mine/favorite"><img src="resources/common/images/btn-more-gz@2x.png" alt="" style="position: relative;">收藏</li>'+
                    '<li url="gotoPage/mine/footStep"><img src="resources/common/images/btn-more-zj@3x.png" alt="" style="position: relative;">足迹</li>'+
               		'</ul>') ;
			$(".modal-some li").click(function(e){
				e.stopPropagation() ;
				location.href = $(this).attr("url") ;
			}) ;
			$(".shrink").click(function(e){
				e.stopPropagation() ;
				$(".modal-some").toggle() ;
			}) ;
			$("body").click(function(){
				$(".modal-some").hide() ;
			}) ;
		},
		isPutaway : function(goodsId){
			var isPutaway = false ;
			$.ajax({
				data:{goodsId : goodsId},
				dataType:"json",
				url:"goods/isPutaway",
				type:"post",
				async:false, 
				cache:false,
				success:function(d){
					return isPutaway = d.data ;
				}
			});
			return isPutaway ;
		}
		
	});
	
	$(".pull-left").click(function() {
		
		history.go(-1);
		
	});
	
	$(".search").click(function() {
		location.href = "goods/searchGoodsByName";
	});
	
	//加载购物车红圈圈
	loadShoppingCartAcount = function() {
		if($(".shoppingCartCount")) {
			tag = $(".shoppingCartCount");
			$.commonAjax(null, "shopping/getShoppingCartCount", 'json', function(data) {
				if(data.obj && data.obj.count && data.obj.count != 0) {
					tag.prepend($("<span id='shoppingCartCount' class='weui-badge' style='position: absolute;top: -.4em;right: 1em;'>" + (data.obj.count > 99 ? 99 : data.obj.count)  + "</span>"))
				}
			});
		}
	}
	
	commonConfirmMessageBox = function(msg, confirm, cancel) {
		$.modal({
			text : msg,
			button : [{
				text : '确定',
				className : 'default',
				onClick : confirm
			}, {
				test : '取消',
				className : 'default',
				onClick : cancel
			}]
		})
	}
	
	commonErrorMessageBox = function(callback) {
		$.modal({
			text : '哎呀，出错啦',
			button : [{
				text : '好的',
				className : 'default',
				onClick : callback
			}]
		})
	}
	
	//初始化加载头部js和Html
	$.initHeadJs() ;
	
	//ajax请求时添加虚拟表单
	function createForm(requestUri){
		var url = formObj.url.replace(/\/xmy-wap-web/,"") ;
		return formObj.model.replace(/(value=').*(')/,"value='"+ url +"'");
	}
	
	$.fn.serializeJson=function(){  
		var serializeObj={};  
		var array=this.serializeArray(true);
		$(array).each(function(){  
	 	   if(serializeObj[this.name]){  
	  	      if($.isArray(serializeObj[this.name])){  
	  	          serializeObj[this.name].push(this.value);  
	  	      }else{  
	  	          serializeObj[this.name]=[serializeObj[this.name],this.value];  
	 	       }  
	 	   }else{  
	  	      serializeObj[this.name]=this.value;   
	 	   }  
		});  
		return serializeObj;  
	};
	
	//加载购物车数量
	loadShoppingCartAcount();
	
	//登录超时或未登录
	function sessionLost(){
		formObj.url = window.location.pathname + window.location.search ;
		$("body").append(createForm()) ;
		$.alert("未登录或登录超时",function(){
			$("#urlForm").submit() ;
		}) ;
	}

	$(function() {
		
		//初始化加载底部公共JS
		$.initFooterJS() ;
		
		url = $("#targetHtml").val();
		
/*		if(url) {
			$.ajax({
				url : 'activity/html/' + url,
				dataType : 'text',
				success : function(data) {
					
					head = data.substring(data.indexOf("<head>") + 6, data.indexOf("</head>"));
					contents = data.substring(data.indexOf("<style>") - 1 < 0 ? data.indexOf("<body>") + 6 : data.indexOf("<style>") - 1, data.indexOf("</body>"));
					
					$("head").append(head);
					
					$("#targetHtml").before(contents);
					
				},
				error : function(msg) {
					
				}
			});
		}*/
	});