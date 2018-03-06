/**
 * 
 */
    $(function () {
        //显示或隐藏密码
        $(".js-bd").click(function () {
            if ($(this).hasClass("js-bd1")){
                $(this).removeClass("js-bd1").addClass("js-bd2");
                $(this).prev().find("input").attr("type","password")
            }else {
                $(this).removeClass("js-bd2").addClass("js-bd1")
                $(this).prev().find("input").attr("type","text")
            }
        })
    })