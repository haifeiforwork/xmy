<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--下载页面-->
<!DOCTYPE html>
<html>
<head>
<title>下载页面</title>
<!-- 公共css区域 -->
<%@ include file="/WEB-INF/jsp/common/common_css.jsp"%>
<!-- 公共css完区域 -->
</head>
<!-- 你的html代码 -->
<header class="bar bar-nav">
  <a class="icon icon-left pull-left back" href="#"></a>
  <a class="icon icon-home pull-right" onclick='gotoPage("/");'></a>
</header>
<div class="content">
</div>
</block>
<script type="text/javascript" src="resources/common/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/common/js/jquery-weui.min.js"></script>
<script type="text/javascript">
var fromWap = "${fromWap}" ;
// 获取终端的相关信息
var Terminal = {
    // 辨别移动终端类型
    platform : function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
			//是否为微信
			isWeixin: u.indexOf('MicroMessenger')> -1,
            // android终端或者uc浏览器
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
            // 是否为iPhone或者QQHD浏览器
            iPhone: u.indexOf('iPhone') > -1 ,
            // 是否iPad
            iPad: u.indexOf('iPad') > -1
        };
    }(),
    // 辨别移动终端的语言：zh-cn、en-us、ko-kr、ja-jp...
    language : (navigator.browserLanguage || navigator.language).toLowerCase()
}

// 根据不同的终端，跳转到不同的地址
var theUrl = 'http://m.xmy365.com';
if(Terminal.platform.isWeixin){
    theUrl = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.app.xmy';//你的iPhone APP对应下载地址：APP Store地址
}else if(Terminal.platform.android){
    theUrl = 'http://m.xmy365.com/resources/app/xmy1.2.3.apk';//你的Android APP对应下载地址：apk文件地址
}else if(Terminal.platform.iPhone){
    theUrl = 'https://itunes.apple.com/cn/app/%E9%A6%99%E6%BB%A1%E5%9C%86/id1219870775?mt=8';//你的iPhone APP对应下载地址：APP Store地址
}else{
    $.toast("你的设备不在支持的范围内");
}
//alert(theUrl);
if(fromWap && fromWap == 'fromWap'){
	$.alert("您的app版本较低，请更新到最新版本",function(){
		location.href = theUrl;
	}) ;
}else{
	location.href = theUrl;
}


/*
else if(Terminal.platform.iPad){
    // 还可以通过language，区分开多国语言版
    switch(Terminal.language){
        case 'en-us':
            theUrl = '你的iPad APP（英文版）对应下载地址：APP Store地址';
            break;
        case 'ko-kr':
            theUrl = '你的iPad APP（韩语版）对应下载地址：APP Store地址';
            break;
        case 'ja-jp':
            theUrl = '你的iPad APP（日文版）对应下载地址：APP Store地址';
            break;
        default:
            theUrl = '你的iPad APP（中文版-默认）对应下载地址：APP Store地址';
    }
}
*/

</script>
