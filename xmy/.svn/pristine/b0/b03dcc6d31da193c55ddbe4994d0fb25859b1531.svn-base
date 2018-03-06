/**
 * 
 */

 contentTag = $("#content");
 
	Date.prototype.toLocaleString = function() {
	    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + "         " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
	};
 
 $(function() {
	 loadData();
 });
 
 loadData = function() {
	 
	 recordData = null;
	 
	 $.ajax({
		url : 'user/getUserBalaneFlow', 
		dataType : 'json',
		async : false,
		type : 'post',
		success : function(data) {
			recordData = data.obj;
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
	 
	 $.each(recordData, function(index, value) {
		 
		 
		 oneTag = $("<div class='weui-form-preview__bd'></div>");
		 idTag = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>序号</label><span class='weui-form-preview__value'></span></div>");
		 valueTag = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>金额</label><span class='weui-form-preview__value'></span></div>");
		 typeTag = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>类型</label><span class='weui-form-preview__value'></span></div>");
		 statussTag = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>状态</label><span class='weui-form-preview__value'></span></div>");
		 remarkTag = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>备注</label><span class='weui-form-preview__value'></span></div>");
		 useTimeTag = $("<div class='weui-form-preview__item'><label class='weui-form-preview__label'>创建时间</label><span class='weui-form-preview__value'></span></div>");
		 
		 idTag.children("span").html(value.id);
		 valueTag.children("span").html(value.value);
		 typeTag.children("span").html("消费");
		 statussTag.children("span").html("有效");
		 remarkTag.children("span").html(value.remark);
		 time = new Date(value.useTime);
		 timeStr = time.Format("yyyy-MM-dd hh:mm:ss");
		 useTimeTag.children("span").html(timeStr);
		 
		 oneTag.append(idTag).append(valueTag).append(typeTag).append(statussTag).append(remarkTag).append(useTimeTag);
		 contentTag.append(oneTag);
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
 
 	$(".pull-left").click(function() {
 		location.href = "mine/center";
 	});
 