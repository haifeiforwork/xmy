/**
 * 
 */
    	phoneRegex = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
    
    	validate = function(form) {
    		
    	}
    	
    	//失焦
    	$(".address-choose").click(function(){
   		 	$("input").blur();
    	});
    	
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
    
    	$(".pull-left").click(function() {
    		location.href = "mine/address";
    	});
        $(".place-pick").cityPicker({
            title: "请选择地址",
            onChange : function(p, values, displayValues) {
            	$(".province").val(displayValues[0]);
            	$(".city").val(displayValues[1]);
            	$(".area").val(displayValues[2]);
            }
        });
        
        $(".pull-right").click(function() {
        	form = $("#addAddress");
        	saveForm(form, 'mine/address/add');
        });
        
        saveForm = function(form, url) {
        	values = $(form).serializeJson();
        	$.ajax({
        		url : url,
        		data : values,
        		dataType : 'json',
        		type : 'post',
        		success : function(data) {
        			if(data.status == 1) {
        				$.alert("添加成功", function() {
        					location.href = "mine/address";
        				});
        			} else if(data.status == -1) {
        				$.alert(data.msg);
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
        }