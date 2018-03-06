<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 编辑地址页面 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>编辑地址</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
		<link rel="stylesheet" href="resources/mine/css/editAddress.css" />
        <!-- 该页面应用css区域 -->
    </head>
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">编辑地址</div>
            <div class="pull-right">保存</div>
    	</div>
    </header>
    <div class="content">
		<div class="list">
			<div class="weui-cells weui-cells_form">
				<form id="editAddress" action="mine/address/edit" method="post" >
					<input type="hidden" name="userId" value="${addr.userId }" />
					<input type="hidden" id="id" name="id" value="${addr.id }" />
					<input type="hidden" id="type" name="type" value="${addr.type }" />
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><label class="weui-label">收货人</label></div>
	                    <div class="weui-cell__bd">
	                    	<input class="weui-input" name="name" value="${addr.name }"  type="text" />
	                    </div>
	                </div>
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><label class="weui-label">联系电话</label></div>
	                    <div class="weui-cell__bd">
	                        <input class="weui-input" name="mobilePhone" type="text" value="${addr.mobilePhone }">
	                    </div>
	                </div>
	                <a class="weui-cell weui-cell_access address-choose" href="javascript:;">
	                    <div class="weui-cell__bd">
	                        <p>所在地区</p>
	                    </div>
	                    <div class="weui-cell__ft">
	                        <input class="weui-input place-pick address" type="text" value="${fn:replace(addr.province,'市','') } ${addr.city } ${addr.area }">
	                    </div>
	                    <input type="hidden" name="isDefault" value="${addr.isDefault }" />
	                    <input type="hidden" name="province" value="${addr.province }"  class="province" />
	                    <input type="hidden" name="city" value="${addr.city }" class="city" />
	                    <input type="hidden" name="area" value="${addr.area }" class="area" />
	                </a>
	                <div class="text">
	                   <textarea class="weui-textarea addressDetails" name="address" placeholder="请输入详细地址">${addr.address }</textarea>
	                </div>
                </form>
            </div>
            <div class="delete-address del">删除地址</div>
    	</div>
   	</div>
   	<!-- 公共js区域 --> 
    <script src="resources/common/js/jquery.min.js"></script>
	<script src="resources/common/js/jquery-weui.min.js"></script>
	<script src="resources/common/js/swiper.min.js"></script>
	<script src="resources/common/js/city-picker.min.js"></script>
    <!-- 公共js区域完 -->
    <!-- 该页面应用js区域 -->
    <script>
    
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
    	form = $("#editAddress");
    	editForm(form, 'mine/address/edit');
    });
    
    editForm = function(form, url) {
    	values = $(form).serializeJson();
    	$.ajax({
    		url : url,
    		dataType : 'json',
    		type : 'post',
    		data : values,
    		success : function(data) {
    			if(data.status == 1) {
    				/* $.modal({
    					text : '修改成功',
    					button : [
    						{
    							text : '我已知晓',
    							className : 'default',
    							onClick : function() {
    								console.info("aaa") ;
    								location.href = "mine/address";
    							}
    						}
    					]
    				}); */
    				$.alert("修改成功",function(){
    					location.href = "mine/address";
    				}) ;
    			} else if(data.status == -1) {
    				$.alert(data.msg);
    			}
    		},
    		error : function(msg) {
    			
    		}
    	});
    }
    
    //失焦
   	$(".address-choose").click(function(){
  		 $("input").blur();
   	});
    
    $(".delete-address").click(function() {
    	id = $("#id").val();
    	$.ajax({
    		type : 'post',
    		url : 'mine/address/del',
    		data : {'addressId' : id },
    		dataType : 'json',
    		success : function(data) {
				if(data.status == 1) {
					$.alert("删除成功", function() {
						location.href = "mine/address";
					});
				}    			
    		},
    		error : function(msg) {
    			
    		}
    	});
    });
    </script>
    <!-- 该页面应用js区域 -->
    </body>
</html>