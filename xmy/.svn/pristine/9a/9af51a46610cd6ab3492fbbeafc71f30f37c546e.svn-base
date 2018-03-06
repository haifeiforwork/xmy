/**

 layui官网

*/

layui.define(["layer", "code", "form", "element", "util","cookie"], function(exports){
	var $      = layui.jquery ,
      	layer  = layui.layer,
      	form   = layui.form(),
      	util   = layui.util,
      	device = layui.device() ,
      	tree   = $('.site-tree-mobile'),
      	shade  = $('.site-mobile-shade') ,
      	cookie = layui.cookie ,
      	tIndex = cookie.get("nav-top-menu") ,
      	mIndex = cookie.get("mobile-top-menu") ,
      	lIndex = cookie.get("left-menu") ;
      
    //阻止IE7以下访问
	if(device.ie && device.ie < 8){
		layer.alert('Layui最低支持ie8，您当前使用的是古老的 IE'+ device.ie + '，为获得更好的体验效果，请更换浏览器！');
	}
    //手机设备的简单适配
	tree.on('click', function(){
		$('body').addClass('site-mobile');
	});
	shade.on('click', function(){
		$('body').removeClass('site-mobile');
	});
	//菜单选中
	tIndex = ("undefined" === tIndex || !tIndex) ? "1" : tIndex ;
	$(".top-menu").eq(parseInt(tIndex)-1).addClass("layui-this") ;
	cookie.set("nav-top-menu",tIndex) ;
	$(".layui-header .layui-nav").on("click",">li",function(){
		cookie.set("nav-top-menu",$(this).attr("index")) ;
	}) ;
	mIndex = ("undefined" === mIndex || !mIndex) ? "1" : mIndex ;
	$(".mobile-top-menu").eq(parseInt(mIndex)-1).addClass("layui-this") ;
	cookie.set("mobile-top-menu",mIndex) ;
	$(".lay-header .layui-nav-child").on("click",">dd",function(){
		cookie.set("mobile-top-menu",$(this).attr("index")) ;
	}) ;
	lIndex = ("undefined" === lIndex || !lIndex) ? "1" : lIndex ;
	$(".left-menu").eq(parseInt(lIndex)-1).addClass("layui-this") ;
	cookie.set("left-menu",tIndex) ;
	$(".layui-side .layui-nav-child").on("click",">dd",function(){
		cookie.set("left-menu",$(this).attr("index")) ;
	}) ;
	exports('global', {});
});