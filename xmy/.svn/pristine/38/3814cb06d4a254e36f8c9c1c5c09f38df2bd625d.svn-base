$(function () {
    $(".tab-content>div:gt(0)").hide();
    $(".tab-nav li").hover(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".tab-nav li").index(this);
        $("div.tab-content>div").eq(index).show().siblings().hide()
    });
});