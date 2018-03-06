/**
 * 这是address.jsp addAddress.jsp editAddress.jsp所使用到的js
 */
    $(function(){
    	//这是设置默认的球球点点击效果，及点击球球设置默认的事件
        $(".list").on("click",".chunk",function(e){
        	e.stopPropagation();
            $(this).find(".sigin").addClass("active");
            $(this).siblings().find(".sigin").removeClass("active");
            id = $(this).children(".addressId").val();
            $.ajax({
            	url : 'mine/address/setDefault',
            	dataType : 'json',
            	type : 'post',
            	data : {'id' : id },
            	success : function(data) {
            		if(data.status != 1 ) {
            			$.toptip("操作失败", "error");
            		}
            	},
            	/*error : function(msg) {
            		$.toptip("操作失败", "error");
            	}*/
            	error:function(error){
					if(error.responseText == "SESSION_LOST"){
						$.alert("未登录或登录超时",function(){
							location.reload() ;
						}) ;
					}
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
        });
        //这是删除地址的点击事件
        $(".del").click(function(e){
        	e.stopPropagation();
        	var addressId = $(this).parents(".chunk").children(".addressId").val() ;
            $.modal({
                text: "确定删除该地址吗？",
                buttons: [
                    { text: "取消", className: "default", onClick: function(){ } },
                    { text: "确定", className: "default", onClick: function(){
                        $.ajax({
                        	url : 'mine/address/del',
                        	dataType : 'json',
                        	type : 'post',
                        	data : {'addressId' : addressId},
                        	success : function(data) {
                        		if(data.status == 1) {
	                        		$.toptip("删除成功", "success");
	                        		location.href = "mine/address";
                        		} else {
                        			$.toptip("操作失败", "error");
                        		}
                        	},
                        	error:function(error){
            					if(error.responseText == "SESSION_LOST"){
            						$.alert("未登录或登录超时",function(){
            							location.reload() ;
            						}) ;
            					}
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
                    } },
                ]
            });
        });
        $(".edit").click(function(e){
        	e.stopPropagation();
            addressId = $(this).parents(".chunk").children(".addressId").val();
            $("#modify").attr("action", "mine/editAddress");
            $("#modify input").val(addressId);
            $("#modify").submit();
        });
        $(".add-address").click(function() {
        	location.href = "mine/addAddress";
        });
		$(".pull-left").click(function() {
			location.href = "mine/center";
		});
    });