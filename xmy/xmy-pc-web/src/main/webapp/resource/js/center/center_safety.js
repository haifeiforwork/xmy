$(document).ready(function () {
    $(".cate-table>div:gt(0)").hide();
   
    $(".cate-nav li").click(function () {
        $(this).addClass("active").siblings().removeClass("active")
        var index=$(".cate-nav li").index(this);
        $("div.cate-table>div").eq(index).show().siblings().hide()

    })
})