/**
 * 
 */
id = $("#userId").val();

baseModify = function(field, value) {
	data = {'id' : id};
	data[field] = value;
	$.ajax({
		url : 'user/setting/modify/' + field,
		data : data,
		dataType : 'json',
		type : 'post',
		success : function(data) {
			if(data.status != 1) {
				$.alert("错误", function() {location.href = "mine/account";});
			}
		},
		error:function(error){
			if(error.responseText == "SESSION_LOST"){
				$.alert("未登录或登录超时",function(){
					location.reload() ;
				}) ;
			}else if(error.responseText == "BUSINESS_EXCEPTION"){
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

$(".avatar").click(function() {
	location.href = "gotoPage/mine/avatar";
});

formatDate = function(date) {
	return date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();
}

$("#gender").select({
	title : '选择性别',
	items : [{
		title : '男',
		value : '0'
	}, {
		title : '女',
		value : '1'
	}],
	onChange : function(value) {
		$("#genderDisplay").html(value.titles);
		baseModify("sex", value.values);
	}
});

maxDate = formatDate(new Date());


$("#birth-time").calendar({
	maxDate : maxDate,
	onChange : function(p, value, displayValue) {
		$.ajax({
			url : 'user/setting/modify/birthday',
			data : {'id' : id, 'birthday' : value[0] },
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if(data.status != 1) {
					$.alert(data.msg);
				}
			},
			error:function(error){
				if(error.responseText == "SESSION_LOST"){
					$.alert("未登录或登录超时",function(){
						location.reload() ;
					}) ;
				}else if(error.responseText == "BUSINESS_EXCEPTION"){
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
});

$("#phone").blur(function() {
	
	oldValue = $(this).val();
	
	$.ajax({
		url : 'user/setting/modify/phone',
		data : {'id' : id, 'phone' : $(this).val() },
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.status != 1) {
				$.alert("请输入正确的手机号");
				$(this).val(oldValue);
			}
		},
		/*error : function(msg) {
			$.alert("请填写座机号码");
			$(this).val(oldValue);
		}*/
		error:function(error){
			if(error.responseText == "SESSION_LOST"){
				$.alert("未登录或登录超时",function(){
					location.reload() ;
				}) ;
			}else if(error.responseText == "BUSINESS_EXCEPTION"){
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

$("#phone").click(function() {
	if($(this).val() == "请输入") {
		$(this).val("");
	}
});

$("#nick").blur(function() {
	
	oldValue = $(this).val();
	
	$.ajax({
		url : 'user/setting/modify/nick',
		data : {'id' : id, 'nick' : $(this).val() },
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.status != 1) {
				$.alert("请输入正确的昵称", function() {
					$(this).val(oldValue);
				});
			}
		},
		/*error : function(msg) {
			$.alert("出错啦！", function() {
				$(this).val(oldValue);
			});
			
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

$("#nick").click(function() {
	if($(this).val() == "请输入") {
		$(this).val("");
	}
});

$(".pull-left").click(function() {
	location.href = "mine/center";
});