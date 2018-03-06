//@author dengq cookie tools
;layui.define(function(l){
	"use strict" ;
	var i = {
		get:function(name){
			var cookieName  = encodeURIComponent(name) + "=" ,
				cookieStart = document.cookie.indexOf(cookieName) ,
				cookieValue = null ;
			if(-1 < cookieStart){
				var cookieEnd = document.cookie.indexOf(";",cookieStart) ;
				if(-1 === cookieEnd){
					cookieEnd = document.cookie.length ;
				}
				cookieValue = decodeURIComponent(document.cookie.substring(cookieStart+cookieName.length,cookieEnd)) ;
			}
			return cookieValue ;
		},
		set:function(name,value,expires,path,domain,secure){//名称，值，失效时间，路径，域，(安全标志(只带有https://的才能发送cookie))
			var cookieText = encodeURIComponent(name) + "=" + encodeURIComponent(value) ;
			if(expires instanceof Date){
				cookieText = cookieText + "; expires=" + expires.toGMTString() ;
			}
			if(path){
				cookieText = cookieText + "; path=" + path ;
			}
			if(domain){
				cookieText = cookieText + "; domain=" + domain ;
			}
			if(secure){
				cookieText = cookieText + "; secure" ;
			}
			document.cookie = cookieText ;
		},
		unset:function(name,path,domain,secure){
			this.set(name, "", new Date(0), path, domain, secure) ;
		},
		getSub:function(name,subName){
			var subCookies = this.getSubAll(name) ;
			return subCookies ? subCookies[subName] : null ;
		},
		getSubAll:function(name){
			var cookieName  = encodeURIComponent(name) + "=" ,
				cookieStart = document.cookie.indexOf(cookieName) ,
				cookieValue = null ,
				cookieEnd ,subCookies ,i ,parts ,result = {} ;
			if(-1 < cookieStart){
				cookieEnd = document.cookie.indexOf(";",cookieStart) ;
				if(-1 === cookieEnd){
					cookieEnd = document.cookie.length ;
				}
				cookieValue = document.cookie.substring(cookieStart+cookieName.length,cookieEnd) ;
				if(cookieValue.length){
					subCookies = cookieValue.split("&") ;
					for(i=0 ,len=subCookies.length ;i<len ;i++){
						parts = subCookies[i].split("=") ;
						result[decodeURIComponent(parts[0])] = decodeURIComponent(parts[1]) ;
					}
					return result ;
				}
			}
			return null ;
		},
		setSub:function(name,subName,value,expires,path,domain,secure){
			var subcookies = this.getSubAll(name) || {} ;
			subcookies[subName] = value ;
			this.setSubAll(name,subcookies,expires,path,domain,secure) ;
		},
		setSubAll:function(name,subcookies,expires,path,domain,secure){
			var cookieText     = encodeURIComponent(name) + "=" ,
				subcookieParts = [] ,subName ;
			for(subName in subcookies){
				if(subName.length && subcookies.hasOwnProperty(subName)){
					subcookieParts.push(encodeURIComponent(subName) + "=" + encodeURIComponent(subcookies[subName])) ;
				}
			}
			if(subcookieParts.length){
				cookieText = cookieText + subcookieParts.join("&") ;
				if(expires instanceof Date){
					cookieText = cookieText + "; expires=" + expires.toGMTString() ;
				}
				if(path){
					cookieText = cookieText + "; path=" + path ;
				}
				if(domain){
					cookieText = cookieText + "; domain=" + domain ;
				}
				if(secure){
					cookieText = cookieText + "; secure" ;
				}
			}else{
				cookieText = cookieText + "; expires=" + (new Date(0)).toGMTString() ;
			}
			document.cookie = cookieText ;
		},
		unsetSub:function(name,subName,path,domain,secure){
			var subcookies = this.getSubAll(name) ;
			if(subcookies){
				delete subcookies[subName] ;
				this.setAll(name,subcookies,null,path,domain,secure) ;
			}
		},
		unsetSubAll:function(name,path,domain,secure){
			this.setSubAll(name, null, new Date(0), path, domain, secure) ;
		}
	} ;
	l("cookie",i) ;
}) ;