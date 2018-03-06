$(function () {
    $(".tab-content").each(function () {
        $(this).children("div:gt(0)").hide()
    });
    $(".all-the").on("click",".tab-card li",function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".tab-card li").index(this);
        $("div.tab-content>div").eq(index).show().siblings().hide()
    });
    $(".front2").click(function(){
        if($(this).hasClass("add-add-css")){
            $(this).removeClass("add-add-css");
            $(".classify-ul").stop();
            $(".classify-ul").slideUp();
        }else{
            $(this).addClass("add-add-css");
            $(".classify-ul").slideDown();
        }
    });
});
$('#R1').fdatepicker({
    format: 'yyyy 年 mm 月 dd',
});
$(document).ready(function(){
	$(".datepicker.dropdown-menu").css({"display":"block","z-index":"9666"})
})
