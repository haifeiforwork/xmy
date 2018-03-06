<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--首页-->
<!DOCTYPE html>
<html>
<head>
    <title>在线客服</title>
    <!-- 公共css区域 -->
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/home/css/home.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
    <style>
    	.wkit-msg-content{top:50px!important;}
    	.wkit-soft-input-wrap .wkit-img-wrap,.wkit-soft-input-wrap .wkit-emot-wrap{margin-top:0.2rem;}
    	.wkit-soft-input-wrap .wkit-emot-wrap .wkit-emot-trigger,.wkit-soft-input-wrap .wkit-img-wrap .wkit-img-trigger{width:0.8rem!important;}
    
    </style>
</head>
<body>
<%-- 在线客服聊天客户样式 --%>
<div>
<!-- <div class="top-content" style="width:100%;height:50px;position:fixed;left:0;top:0;background:#FFF;z-index:99999;line-height:50px;text-align:center"></div> -->
<header>
    <div class="header-top" style="height:50px;">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"></div>
        <div class="pull-center">在线客服</div>
        <div class="pull-right">
            <!-- <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"></span>
            <span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"></span> -->
        </div>
    </div>
</header>
<div class="dialog-box" id="qian" style="border:1px solid #358812;">

</div>
</div>


<%-- 客服通讯的js --%>
<script src="https://g.alicdn.com/aliww/??h5.openim.sdk/1.0.6/scripts/wsdk.js,h5.openim.kit/0.3.3/scripts/kit.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/common/js/jquery.min.js"></script>
<!-- 该页面应用js区域 -->
<script type="text/javascript">
	 $(function($){

		$.ajax({
			url:"service/qianniu",
			type:"post",
			success:function(data){
				WKIT.init({
					titleBar:false,
					uid:data.users.userid,
					appkey:data.appkey ,
					credential:data.users.password,
					touid:data.customid,
					sendMsgToCustomService:true,
					container:  document.getElementById('qian'),
					width: 400,
					height: 400,
					avatar:"resources/common/images/cstars/" + data.sessionInfo.userInfo.avatar,
					toAvatar:"resources/common/images/app_logo.png",
					sendBtn:true
				});
			}
		});
		//关闭客户聊天窗口
		 $(".pull-left").click(function(){
			window.location.href="mine/center";
		 }); 
 });
</script>
<!-- 该页面应用js区域 -->
</body>
</html>
