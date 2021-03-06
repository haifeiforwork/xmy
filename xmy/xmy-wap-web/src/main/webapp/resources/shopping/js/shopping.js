require(["common"],function(){
	
	//后退
	$(".pull-left").click(function(){
		location.href = "home/home" ;
	}) ;
	
    //购物车数量
//    $(".shopping-num").html("购物车(" + $(".content .active-goods").length + ")") ;
	
	//加的效果
    $(".add").click(function(e){
    	e.stopPropagation();
    	var obj = $(this).parents(".count"), n = $(this).prev().val() ;
    	var isActivity = $.judgeActivityGoods(obj.find(".goodsId"));
    	if(isActivity){
    		return ;
    	}
    	var num = parseInt(n)+1;
    	if(obj.find(".activityId").val() && obj.find(".activityId").val() != 0){//判断活动商品数量
    		if((obj.find(".residueNum").val() || parseInt(obj.find(".limitNum").val()) <= 1) && (obj.find(".limitNum").val() < num || obj.find(".residueNum").val() < num)){
    			$.alert("购买数量超限") ;
    			return ;
    		}
    		data = {num : 1 ,goodsId : obj.find(".goodsId").val() , activityId : obj.find(".activityId").val(),activityType : obj.find(".activityType").val()}
    		url = "shopping/addActivityShopping" ;
    	}else{
        	data = {goodsId : obj.find(".goodsId").val(), goodsNum : 1}
    		url = "shopping/addShopping" ;
        }
        if(num==0){ return;}
        //使用change事件更改数量
//        addShopping(data,url,"json") ;
        $(this).prev().attr("value", num);
        updateNum($(this).prev());
        countAllPrice() ;
    });
    
    //减的效果
    $(".jian").click(function(e){
    	e.stopPropagation();
    	var obj = $(this).parents(".count"), n=$(this).next().val(), data = {}, url = "" ;
    	var isActivity = $.judgeActivityGoods(obj.find(".goodsId"));
    	if(isActivity){
    		return ;
    	}
        var num=parseInt(n)-1;
        if(obj.find(".activityId").val() && obj.find(".activityId").val() != 0){//判断活动商品数量
    		if((obj.find(".residueNum").val() || parseInt(obj.find(".limitNum").val()) <= 1) && (obj.find(".limitNum").val() < num || obj.find(".residueNum").val() < num)){
    			$.alert("购买数量超限") ;
    			return ;
    		}
    		data = {goodsId : obj.find(".goodsId").val(), goodsNum : 1, activityId : obj.find(".activityId").val(),activityType : obj.find(".activityType").val()}
    		url = "shopping/addActivityShopping" ;
        }else{
        	data = {goodsId : obj.find(".goodsId").val(), goodsNum : 1}
    		url = "shopping/addShopping" ;
        }
        if(num==0){ return}
        //使用change事件更改
        /*addShopping(data,url,"json") ;*/
        $(this).next().attr("value", num);
        updateNum($(this).next());
        countAllPrice() ;
    });
    
    //跳转进商品详情
    $(".content").on("click", ".toGoodsInfo", function(e) {
    	e.stopPropagation();
/*    	if($.isPutaway($(this).attr("goods-id"))){
    		$.alert("该商品已下架") ;
    		return ;
    	}*/
    	location.href = $(this).attr("url");
    });
    
    //选择
    $(".list").on("click",".choose",function(e){
    	e.stopPropagation();
        var $this = $(this).find(".yuan");
        if($this.hasClass("active")){
            $this.removeClass("active");
            var num1=$(".list .yuan").length;
            var num2=$(".list .active").length;
            if(num2!=num1){
            	$(".ly-65 .yuan1").removeClass("active");
            }
        }else{
            $this.addClass("active");
            var num1=$(".list .yuan").length;
            var num2=$(".list .active").length;
            if(num2==num1){
            	$(".ly-65 .yuan1").addClass("active");
            }
        }
        countAllPrice() ;
    });
    
    //选择
    $(".listing").on("click",".choose",function(e){
    	e.stopPropagation();
        var $this = $(this).find(".yuan");
        if($this.hasClass("active")){
            $this.removeClass("active");
            var num1=$(".list .yuan").length;
            var num2=$(".list .active").length;
            if(num2!=num1){
            	$(".ly-65 .yuan1").removeClass("active");
            }
        }else{
            $this.addClass("active");
            var num1=$(".list .yuan").length;
            var num2=$(".list .active").length;
            if(num2==num1){
            	$(".ly-65 .yuan1").addClass("active");
            }
        }
        countAllPrice() ;
    });
    
    //tianjia jin gouwu che 
    function addShopping(data,url){
    	$.commonAjax(data,url,"json") ;
    }
    
    updateNum = function(tag) {
    	val = {};
    	val["goodsId"] = tag.parents("a").attr("goods-id");
    	val["num"] = tag.val();
    	$.ajax({
    		url : 'shopping/updateNum',
    		data : val,
    		type : 'post',
    		dataType : 'json',
    		success : function(data) {
    			if(data.status == 0) {
    				$.alert("服务器繁忙", function() {
    					location.href = "shopping/shopping";
    				});
    			}
    			if(data.status ==2) {
    				$.alert("该活动商品只能购买一个", function() {
    					location.href = "shopping/shopping";
    				});
    			}
    		},
    		error : function(msg) {
				$.alert("服务器繁忙", function() {
					location.href = "shopping/shopping";
				});
    		}
    	});
    }
    
    //修改购买商品数量重新计算商品价格
    function countAllPrice(){
    	var obj = $(".active-goods"), num = 0, price = 0.00 ;
    	$.each(obj,function(i,n){
    		if(obj.eq(i).find(".yuan").hasClass("active")){
    			if(obj.eq(i).find(".goods-price").val()){
    				price += parseFloat(obj.eq(i).find(".goods-price").val()).toFixed(2)*parseInt(obj.eq(i).find(".num").val(),10) ;
    			}
    			num ++ ;
    		}
    	}) ;
    	console.info(price) ;
    	$(".all-price").html("￥" + parseFloat(price).toFixed(2)) ;
    	$(".all-num").html(num) ;
    }
    
    //购物车结算
    $(".row").on("click",".add-order",function(){
    	var obj = $(".active-goods .yuan").filter(".active"), goodsId = "" ;
    	if($(".active-goods .yuan").filter(".active").length <= 0){
    		$.alert("请选择需要购买的商品!") ;
    		return ;
    	}
    	$.each(obj,function(i,n){
    		goodsId += obj.eq(i).parents(".active-goods").attr("goods-id") + "," ;
    	}) ;
    	if(goodsId) goodsId = goodsId.substring(0, goodsId.length-1);
    	
    	if(!$("#sessionInfo").val()){
    		
    		$.modal({
    			//title: "Hello",
    			text: "是否确定匿名购买该商品？",
    			buttons: [
    			          { text: "去登陆",
    			        	onClick: function(){ 
    			        	  console.log(1)
    			        	  window.location.href="shopping/toLogin?goodsId="+goodsId;
    			        	  //window.history.back();
    			          } 
    			          },
    			          { text: "确定", onClick: function(){
    			        	  console.log(2)
    			        	  location.href = "shopping/confirmShopping?goodsId=" + goodsId ;
    			          } 
    			          },
    			          { text: "取消", className: "default", onClick: function(){
    			        	  console.log(3)
    			        	  
    			          } 
    			          },
    			          ]
    		});
    	}
    	if($("#sessionInfo").val()){
    		
    		location.href = "shopping/confirmShopping?goodsId=" + goodsId ;
    	}
    	
    }) ;
    
    //编辑
    $(".edit").click(function(){
    	if($(this).html()=="编辑"){
    		if($(".weui-panel__bd").length <= 0){
    			$.alert("购物车中没有商品") ;
    			return ;
    		}
    		$(".choose").find(".yuan").removeClass("active");
    		$(".yuan1").removeClass("active");
    		$.each($(".putAway"), function(index, value) {
    			$(this).children("span").remove();
    			$(this).prepend("<div class='choose' ><div class='yuan'><input type='hidden' class='goodsId' value='" + $(this).attr("goods-id") + "' /></div></div>");
    		});
    		$(".choose-all").show();
    		$(".sumdiv").hide();
    		$(".edit").html("完成");
    		$(".add-order").css("display","none");
    		$(".delete-order").css("display","block");
    		$(".delete-order").click(function(){
    			var params = [];
    			var temp=0;
    			$('.yuan').each(function(index, e) {
    				if($(this).hasClass('active')) {
    					params.push($(this).find('.goodsId').val());
    					temp++;
    				}
    			});
    			var num=$(".content .active-goods").length;
    			var sum=num-temp;
    			var goodsId = {goodsId:params.join(',')} ;
    			$.ajax({
    				   type: "POST",
    				   url: "shopping/delete",
    				   data: goodsId,
    				   async:false,
    				   success: function(data){
    					   $(".yuan").filter(".active").parent().parent().remove();
    					   countAllPrice() ;
    				   },error:function(){
    					   alert("删除数据失败！");
    				   }
    				});
    		});
    	}else{
    		$(".edit").html("编辑");
    		$(".choose-all").hide();
    		$(".sumdiv").show();
    		$(".add-order").css("display","block");
    		$(".delete-order").css("display","none");
    		window.location.href="shopping/shopping";
    	}
    	
    });
    
    $(".ly-65").on("click",".choose1",function(e){
    	e.stopPropagation();
        var $this = $(this).find(".yuan1");
        if($this.hasClass("active")){
            $(".yuan").removeClass("active");
        	$(".yuan1").removeClass("active");
        	
        }else{
        	
        	$(".yuan").addClass("active");
        	$(".yuan1").addClass("active");
            
        }
        countAllPrice() ;
    });
    
    
    
});
