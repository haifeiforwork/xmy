<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品评价</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta name="format-detection" content="telephone=no">
<!--     公共css区域
    <link rel="stylesheet" href="../css/weui.min.css">
    <link rel="stylesheet" href="../css/jquery-weui.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/comment_stars.css"/> -->
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <style type="text/css">
        header .header-top{border-bottom: 1px solid #C3C3C3;}
        .content{bottom: 0;}
        .fsize-07{font-size: 0.7rem;margin-top: 0;}
        .eval .weui-cells:before{left: 0}
        .all-cell{background-color: #FFF;padding: 0;font-size: 0.7rem;}
        .all-cell .weui-cell__bd{padding: 0.5rem 0.5rem 0.2rem 0.5rem;}
        .all-count{color: #999;font-size: 0.75rem;}
        .good-centence{float: right;color: #333;}
        .raty-leval{position: relative;padding-left: 0.2rem;line-height: 1;height: 20px;margin-top: 0.35rem}
        .raty-leval>span{display: inline-block;margin-left: -0.3rem;background-image: url("resources/common/images/star_off_24.png");background-repeat: no-repeat;background-size: 100%;width: 20px;height: 20px;}
        .raty-leval>span.active{background-image: url("resources/common/images/star_on24.png")}
        .say-list{padding: 0.5rem;}
        .say-list:before{left: 0;} 
        .say-list .item-title{color: #333;margin-top: 0.35rem;}
        .say-list .item-after{color: #999;}
        .say-list .item-after-time{float: right}
        .say-list .item-after-name{width: 5rem;display: inline-block;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;}
    </style>
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">商品评价</div>
    </div>
</header>
<div class="content eval">
    <div class="weui-cell all-cell">
        <div class="weui-cell__bd">
            <p><span class="all-count">总评分</span> <span class="good-centence"><span>${fn:length(comments.comments) }</span>评论</span></p>
            <c:if test="${fn:length(comments.comments) > 0 }">
	            <c:set var="starSum"><fmt:formatNumber value="${(empty comments.starSum ? 0 : comments.starSum)/fn:length(comments.comments) - 0.4}" pattern="#"/></c:set>
	            <div class="raty-leval">
	                <span <c:if test="${starSum >= 1 }">class="active"</c:if>></span>
	                <span <c:if test="${starSum >= 2  }">class="active"</c:if>></span>
	                <span <c:if test="${starSum >= 3  }">class="active"</c:if>></span>
	                <span <c:if test="${starSum >= 4  }">class="active"</c:if>></span>
	                <span <c:if test="${starSum >= 5  }">class="active"</c:if>></span>
	            </div>
            </c:if>
            <c:if test="${fn:length(comments.comments) <= 0 }">
	            <div class="raty-leval">
	                <span></span>
	                <span></span>
	                <span></span>
	                <span></span>
	                <span></span>
	            </div>
            </c:if>
        </div>
    </div>
    <div class="weui-cells fsize-07">
    	<c:forEach items="${comments.comments }" var="comment" >
			    	
	        <div class="weui-cell say-list">
	            <div class="weui-cell__bd">
	                <p><span class="item-after item-after-name"><c:if test="${not empty comment.realName }">${comment.realName }</c:if><c:if test="${empty comment.realName }">${fn:substring(comment.mobilePhone,0,3)}****${fn:substring(comment.mobilePhone,7,11)}</c:if></span><span class="item-after item-after-time"><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${comment.createTime }"/></span></p>
	                <p class="item-title">${comment.commentConten }</p>
	            </div>
	        </div>
			    	
    	</c:forEach>
        <!-- <div class="weui-cell say-list">
            <div class="weui-cell__bd">
                <p><span class="item-after item-after-name">盛先v盛先生盛先生盛先生盛先生生</span> <span class="item-after item-after-time">2017年08月26日 19:20:22</span></p>
                <p class="item-title">天</p>
            </div>
        </div>
        <div class="weui-cell say-list">
            <div class="weui-cell__bd">
                <p><span class="item-after item-after-name">盛先v盛先生盛先生盛先生盛先生生</span> <span class="item-after item-after-time">2017年08月26日 19:20:22</span></p>
                <p class="item-title">天</p>
            </div>
        </div>
        <div class="weui-cell say-list">
            <div class="weui-cell__bd">
                <p><span class="item-after item-after-name">盛先v盛先生盛先生盛先生盛先生生</span> <span class="item-after item-after-time">2017年08月26日 19:20:22</span></p>
                <p class="item-title">天</p>
            </div>
        </div> -->
    </div>
</div>
<!-- 公共js区域 -->
<script src="resources/common/js/jquery.min.js"></script>
<script src="resources/common/js/jquery-weui.min.js"></script>
<script src="resources/common/js/swiper.min.js"></script>
<script src="resources/common/js/city-picker.min.js"></script>
<script type="text/javascript">

	$(".pull-left").click(function() {
		
		history.go(-1);
		
	});
	
</script>
<!-- <script src="resources/common/js/jquery.raty.min.js" ></script> -->
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<!-- <script>
    //星星评分
    $('#star').raty({

        cancel    : false,

        target    : '#hint',

        targetKeep: true,

        path: 'resources/common/images',
        starOn: 'star_on24.png',
        starOff: 'star_off_24.png',
    });
</script> -->
<!-- 该页面应用js区域 -->
</body>
</html>