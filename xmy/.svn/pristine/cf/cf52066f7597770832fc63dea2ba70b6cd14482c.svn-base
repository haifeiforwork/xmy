/**
 * 
 */
require(["common"], function() {
	
    $(function () {
        //tab切换
        var tabItem =  $(".tab-nav .weui-flex__item")
        $(".vip .shop-list:gt(0)").hide();
        tabItem.click(function () {
            $(this).addClass("active").siblings().removeClass("active");
            var indexx = tabItem.index(this);
            $(".vip .shop-list").eq(indexx).show().siblings().hide();
        })//切换结束
    })
	
});