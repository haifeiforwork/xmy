$(document).ready(function () {
    $(".cate-table>div:gt(0)").hide();
    $(".cate-nav li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".cate-nav li").index(this);
        $("div.cate-table>div").eq(index).show().siblings().hide()

    })
});
/*$('#Rl').fdatepicker({
    format: 'yyyy 年 mm 月 dd'
});*/

Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
var time2 = new Date().Format("yyyy-MM-dd");
$("#Rl").jeDate({
    format:"YYYY 年MM 月DD",
    //是否开启时间选择
    isClear:false,                               //是否显示清空
    isToday:false,
    onClose:false,
    maxDate : time2,
});
$("#inputImage").click(function () {
    $(".default-img").hide();
});
$(" .head-list li:nth-of-type(8n)").css("margin-right","0")