//author dengq 分页
;layui.define(["laypage","tool"],function(l){
	var laypage = layui.laypage ,tool = layui.tool ,$ = layui.jquery ,ret = {
		load:function(opts,fx){
			var pages = opts.pages || "#pageCount" ,data = opts.data || {} ;//data为额外特殊的查询条件字段
			var pageIndex=$("#pageIndex").val();
			var _default = {first:1,prev:"<em><</em>",
					curr:pageIndex||1,
					next:"<em>></em>",
					cont:opts.cont || "mypager",
					pages:$(pages).val(),
					skip:true,
					last:$("#pageCount").val() || opts.pages,
					selector:opts.selector || "#qForm"} ,arg ;
			$("#" + _default.cont).hide() ;
			_default.fx = fx ;
			_default.jump = function(obj,first){
				var arg = ret.reqSerialize(_default.selector,opts.sort) || {} ;
				$.extend(arg,data) ;
				var _settings = {
					url:opts.url + "/" + obj.curr,
					dataType:"html",
					data: arg,
					beforeSend:function(){
						tool.load() ;
						return true ;
					},
					success:function(data){
						_default.fx.call(this,data) ;
					},
					error:function(){
						tool.error("网络繁忙，请稍后再试") ;
					},
					complete:function(){
						tool.unload() ;
						$("#" + _default.cont).show() ;
					}
				}
				$.ajax(_settings) ;
			}
			laypage(_default) ;
			//注册查询事件
			$("body").on("click",".btn-submit",function(e){
				e.preventDefault() ;
				var formParams = ret.reqSerializeForm(_default.selector) ;
				if (data) {
					var params = "" ;
					for (prop in data) {
						params += "&" + prop + "=" + data[prop] ;
					} 
					params && (params = params.substring(1,params.length)) && (formParams = (formParams ? formParams + "&" : "?") + params) ;
				}
				location.href = opts.url + (formParams ? ("?" + formParams) : "") ;
			}) ;
		},
		reqSerialize:function(mark,sortmix){
			var $this = $(mark) ,req = {} ;
			if(!$this.length){
				return req ;
			}
			req.rule = [] ;
			$this.find(".q-rule").each(function(){
				var _this = $(this) ,zq = _this.attr("zfj-query") ,val = $.trim(_this.val()) ;
				if(zq){
					if(val){
						var arr = zq.split(":") ;
						req.rule.push({field:arr[0],op:arr[1],value:val}) ;
					}
				}
			}) ;
			if(sortmix){
				req.sortmix = sortmix ;
			}
			return req ;
		},
		reqSerializeForm:function(mark,sortmix){
			var req = this.reqSerialize(mark,sortmix) ,rules = req.rule ,sortmix = req.sortmix ,params = "" ;
			console.log(req) ;
			if(rules){
				var temp ,index = 0 ;
				for(var i=0 ,len=rules.length ;i<len ;i++){
					temp = rules[i] ;
					if(temp.value){
						params += "&rule["+index+"]['field']=" + temp.field + "&rule["+index+"]['op']=" + temp.op + "&rule["+index+"]['value']=" + temp.value ;
						index++ ;
					}
				}
			}
			sortmix && (params = params + "&sortmix=" + sortmix) ;
			params && (params = params.substring(1,params.length)) ;
			return params ;
		},
		formRest:function(fx){
			$("body").on("click",".btn-reset",function(){
				fx ? fx.call(this) : $(".q-rule").val("") ;
			}) ;
		}
		
	} ;
	l("pager",ret) ;
}) ;