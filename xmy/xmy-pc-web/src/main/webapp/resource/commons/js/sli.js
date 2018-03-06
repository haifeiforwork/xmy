//2017-11-8
$(function(){
    var n=0;
    var imgLength=$(".b-img a").length;
    var ctWidth=imgLength*100;
    var itemWidth=1/imgLength*100;
    $(".b-img").width(ctWidth+"%");
    $(".b-img a").width(itemWidth+"%");
    $(".b-list").width(imgLength*30);
    if(imgLength>1)
    {
        for(var i=0;i<imgLength;i++)
        {
            var listSpan=$("<span></span>")
            $(".b-list").append(listSpan);
        }
    }
    $(".b-list span:eq(0)").addClass("spcss").siblings("span").removeClass("spcss");
    $(".b-list span").hover(function(){
        var lsIndex=$(this).index();
        n=lsIndex;
        var ctPosit=n*100;
        $(".b-img").stop().animate({"left":"-"+ctPosit+"%"},500);
        $(this).addClass("spcss").siblings("span").removeClass("spcss");
    })
    function rollEnvent(){
        if(n==imgLength-1)
        {
            var ctPosit=(n+1)*100;
            $(".ad").append($(".b-img").clone());
            $(".b-img:last").css("left","100%");
            $(".b-img:first").animate({"left":"-"+ctPosit+"%"},500);
            $(".b-img:last").animate({"left":"0"},500);
            var setTime0=setTimeout(function () {
                $(".ad .b-img:first").remove();
            }, 500);
            n=0;
            $(".b-list span:eq(0)").addClass("spcss").siblings("span").removeClass("spcss");
        }
        else
        {
            n++;
            var ctPosit=n*100;
            $(".b-img").animate({"left":"-"+ctPosit+"%"},500);
            $(".b-list span:eq("+n+")").addClass("spcss").siblings("span").removeClass("spcss");
        }
    }
    if (imgLength > 1) {
        var slidesetInterval = setInterval(rollEnvent, 4000);
        $(".ad").hover(function () {
            clearInterval(slidesetInterval);
        }, function () {
            slidesetInterval = setInterval(rollEnvent, 4000);
        });
    }
    $(".bar-left").mouseover(function(){
        $(this).css("background","url(images/arr-bg.png)");
        $(this).find("em").addClass("emcss");
    }).mouseleave(function(){
        $(this).css("background","none");
        $(this).find("em").removeClass("emcss");
    });
    $(".bar-right").mouseover(function(){
        $(this).css("background","url(images/arr-bg.png)");
        $(this).find("em").addClass("emcss");
    }).mouseleave(function(){
        $(this).css("background","none");
        $(this).find("em").removeClass("emcss");
    });
});
