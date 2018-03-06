;layui.define(["jquery","layer"],function(l){
	"use strict" ;
	var layer = layui.layer ,$ = layui.jquery ,obj = {
		getDate:function(date,split,simple){//日期工具
			if(!date) {
				return "" ;
			}
			if(date instanceof Date){
				date = date.getTime() ;
			}
			date = parseInt(date,10) ;
			if(isNaN(date)) {
				throw new Error("非日期数字!") ;
			}
			split = split || "-" ;
			var d       = new Date(date) ,
			    year    = d.getFullYear() ,
			    month   = d.getMonth() + 1 ,
			    day     = d.getDate() ,
			    hours   = d.getHours() ,
			    minutes = d.getMinutes() ,
			    second  = d.getSeconds() ;
			return year + split + _completeDate(month) + split + _completeDate(day) + " " + 
				(!simple ? "" : (_completeDate(hours) + ":" + _completeDate(minutes) + ":" + _completeDate(second))) ;
		},
		getFullDate:function(date,split){
			return this.getDate(date,split,true) ;
		},
		parse:function(str,pattern){//原日期date
			if(pattern && "string" !== typeof pattern){
				throw new Error("日期格式错误") ;
			}
			return _format(str,pattern || "yyyy-MM-dd") ;
		},
		info:function(msg,fx){//普通信息提示
			_alert(msg,"msg",fx,{icon:6,time:2000}) ;
		},
		operInfo:function(msg,fx){//带有点击操作的普通信息提示
			_alert(msg,"operInfo",fx,{icon:1}) ;
		},
		error:function(msg,fx){//错误信息提示
			_alert(msg,"msg",fx,{icon:2,time:2000}) ;
		},
		warning:function(msg,fx){//警告信息提示
			_alert(msg,"msg",fx,{icon:7,time:2000}) ;
		},
		confirm:function(msg,fx){//询问信息提示
			_alert(msg,"confirm",fx,{icon:3}) ;
		},
		load:function(){
			this.index = layer.load() ;
		},
		unload:function(){
			layer.close(this.index) ;
		}
		
	} ;
	l("tool",obj) ;
	
	function _alert(msg,type,fx,conf){
		if("msg" === type){
			layer.msg(msg,conf,function(){
				if(fx){
					fx.call(this) ;
				}
			}) ;
		}else if("operInfo" === type){
			layer.alert(msg,function(index){
				layer.close(index) ;
				if(fx){
					fx.call(this) ;
				}
			});
		}else if("confirm" === type){
			layer.confirm(msg,conf,function(index){
				layer.close(index);
				if(fx){
					fx.call(this) ;
				}
			});
		}
	}
	
	function _format(str,format){
		var year = 0 ,month = 0 ,day = 1 ,hours = 0 ,minutes = 0 ,seconds = 0 ;
		if(/^yyyy$/.test(format)){
			year = _pint(str) ;
		}else if(/^yyyy[-|\/]MM$/.test(format)){
			year = _pint(str.substring(0,4)) ;
			month = pint(str.substring(5,7)) - 1 ;
		}else if(/^yyyy[-|\/]MM[-|\/]dd$/.test(format)){
			year = _pint(str.substring(0,4)) ;
			month = _pint(str.substring(5,7)) - 1 ;
			day = _pint(str.substring(8,10)) ;
		}else if(/^yyyy[-|\/]MM[-|\/]dd[-|\/]HH$/.test(format)){
			year = _pint(str.substring(0,4)) ;
			month = _pint(str.substring(5,7)) - 1 ;
			day = _pint(str.substring(8,10)) ;
			hours = _pint(str.substring(11,13)) ;
		}else if(/^yyyy[-|\/]MM[-|\/]dd[-|\/]HH[:|\/]mm$/.test(format)){
			year = _pint(str.substring(0,4)) ;
			month = _pint(str.substring(5,7)) - 1 ;
			day = _pint(str.substring(8,10)) ;
			hours = _pint(str.substring(11,13)) ;
			minutes = _pint(str.substring(14,16)) ;
		}else if(/^yyyy[-|\/]MM[-|\/]dd HH[:|\/]mm[:|\/]ss$/.test(format)){
			year = _pint(str.substring(0,4)) ;
			month = _pint(str.substring(5,7)) - 1 ;
			day = _pint(str.substring(8,10)) ;
			hours = _pint(str.substring(11,13)) ;
			minutes = _pint(str.substring(14,16)) ;
			seconds = _pint(str.substring(17,19)) ;
		}else{
			throw new Error("日期格式不正确!") ;
		}
		var d = new Date() ;
		d.setFullYear(year) ;
		d.setMonth(month) ;
		d.setDate(day) ;
		d.setHours(hours) ;
		d.setMinutes(minutes) ;
		d.setSeconds(seconds) ;
		d.setMilliseconds(0) ;
		return d ;
	}
	function _pint(str){
		var num = parseInt(str,10) ;
		if(isNaN(num)) {
			throw new Error("非日期数字!") ;
		}
		return num ;
	}
	
	function _completeDate(num){
		num = parseInt(num,10) ;
		if(isNaN(num)) throw new Error("存在非数字的日期!") ;
		return num > 9 ? num : "0"+num ;
	}
}) ;