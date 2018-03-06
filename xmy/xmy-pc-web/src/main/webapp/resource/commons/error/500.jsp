<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
    <style type="text/css">
        .dNav{height: 46px;background-color: #fff;}
        .dNav .list{width: 1250px;height: 100%;margin: 0 auto;min-width: 1250px;}
        .dNav .list .list-ul{list-style-type: none;background-color: #fff;height: 100%;line-height: 45px;position: relative;}
        .dNav .list .list-ul .rr{width: 122px;height: 63px;position: absolute;top: -20px;right: 90px;}
        .dNav .list .list-ul .rr img{width: 100%;height: 100%;}
        .dNav .list .list-ul .list-li{float: left;font-size: 16px;padding: 0 20px;}
        .dNav .list .list-ul .front{background-color: #358912;color: #fff;width: 185px;position: relative;}
        .dNav .list .list-ul .front .all i{font-size: 26px;position:absolute;top: 0px;right: 0px;}
        .dNav .list .list-ul .front .classify-ul{z-index: 9999;background-color: #3EA600;position: absolute;top: 45px;left: 0px;width: 100%;font-size: 13px;display:none;}
                .dNav .list .list-ul .front .classify-ul .classify-li{height: 39px;line-height: 39px;padding: 0 0 0 40px;}
        .dNav .list .list-ul .front .classify-ul .classify-li:hover{background-color: #21650B;cursor: pointer;}
        .dNav .list .list-ul .cross{color: #358912;}
        .icon{background: url(images/icon.png) no-repeat;display: inline-block;vertical-align: middle;}
        .icon1{background-position: 7px 4px;width: 25px;height: 25px;margin-right: 10px;}
        .icon2{background-position: 7px -32px;width: 25px;height: 25px;margin-right: 10px;}
        .icon3{background-position: 7px -70px;width: 25px;height: 25px;margin-right: 10px;}
        .icon4{background-position: 7px -105px;width: 25px;height: 25px;margin-right: 10px;}
        .icon5{background-position: 7px -140px;width: 25px;height: 25px;margin-right: 10px;}
        .icon6{background-position: 7px -178px;width: 25px;height: 25px;margin-right: 10px;}
        .icon7{background-position: 7px -216px;width: 25px;height: 25px;margin-right: 10px;}
        .icon8{background-position: 7px -249px;width: 25px;height: 25px;margin-right: 10px;}
        .icon9{background-position: 7px -285px;width: 25px;height: 25px;margin-right: 10px;}
        .icon10{background-position: 7px -321px;width: 25px;height: 25px;margin-right: 10px;}
        .icon11{background-position: 7px -357px;width: 25px;height: 25px;margin-right: 10px;}

        /* 新增头部样式*/
        nav .outer .header .second-tell img{margin-top: 10px;}
        .dNav .list .list-ul .out-cross{color: #FCFE14;}

        /*新增导航*/
        .add-bg-theam2{background-color: #358812!important;color: #FFF;}
        .dNav .list .list-ul .front2{background-color: #24690c;}

        .text-center{text-align: center;}
        /*内容部分*/
        .no-container{width: 1250px;height: 600px;background: #FFFFFF;margin: 0 auto;}
        .no-container .no-content{width: 400px;height: 360px;position: absolute;top: 50%;left: 50%;margin-left: -200px;margin-top: -180px;}
    	.no-container .no-content .no-content-text{margin-top:10px ;text-align: center;}
    	.dNav .list .list-ul .list-li{padding:0;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/commons/nav/nav_homepage.jsp" %>
<!--内容部分-->
<div class="no-container">
	<div class="no-content">
		<img src="resource/commons/images/no.png" />
		<div class="no-content-text">没有找到符合条件的结果哦!</div>
	</div>
</div>
<!--底部部分-->
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<%@include file="/WEB-INF/jsp/commons/nav/search_float_top.jsp" %>
<script type="text/javascript" src="resource/js/common/goods_search.js"></script>
    <script src="resource/js/common/menu.js"></script>
<script>
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
</script>
</body>
</html>

