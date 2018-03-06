/**
 * 
 */
	$(function() {
		loadData(2);
	});

contentTag = $("#content");

	Date.prototype.toLocaleString = function() {
	    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + "         " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
	};
	
	$(".pull-left").click(function() {
		location.href = "mine/center";
	});
	
	callback = function(data) {
		$.each(data, function(index, value) {
			if(value.moneyPoint > 0){
				one = $("<div class='weui-form-preview__bd'></div>");
				moneyPoints = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>积分</label><span class='weui-form-preview__value'></span></div>");
				spendType = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>类型</label><span class='weui-form-preview__value'></span></div>");
				statuss = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>状态</label><span class='weui-form-preview__value'></span></div>");
				remarks = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>备注</label><span class='weui-form-preview__value'></span></div>");
				creatTime = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>创建时间</label><span class='weui-form-preview__value'></span></div>");
				moneyPoints.children("span").html(value.moneyPoint);
				if(value.spendType != 2)
					spendType.children("span").html("获得");
				else if(value.spendType == 2)
					spendType.children("span").html("花费");
				statuss.children("span").html("有效");
				remarks.children("span").html(value.remarks);
				time = new Date(value.creatTime);
				timeStr = time.Format("yyyy-MM-dd hh:mm:ss");
				creatTime.children("span").html(timeStr);
				
				one.append(moneyPoints);
				one.append(spendType);
				one.append(statuss);
				one.append(remarks);
				one.append(creatTime);
				
				contentTag.append(one);
			}
		});
		
			
	}
	
	Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	loadData = function(type) {
		$.ajax({
			url : 'user/getUserBillOrPointFlow',
			type : 'post',
			data : {'type' : type},
			dataType : 'json',
			success : function(data) {
				callback(data.obj);
				if(data.obj.length==0){
					 oneTag = $("<div class='weui-form-preview__bd' style='text-align:center;'>暂时没有余额明细！</div>");
					 contentTag.append(oneTag);
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

	