<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订单评价</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <!-- 公共css区域 -->
    <link rel="stylesheet" href="resources/common/css/weui.min.css">
    <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
    <link rel="stylesheet" href="resources/common/css/style.css">
	<link rel="stylesheet" href="resources/common/css/pygments.css">
    <!--<link rel="stylesheet" type="text/css" href="resources/common/css/comment_stars.css"/>-->
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link rel="stylesheet" href="resources/mine/css/comment.css" />
    <!-- 该页面应用css区域 -->
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">订单评价</div>
    </div>
</header>

	<div class="content eval">
		<div class="weui-cells cells-mar0">
			<div class="weui-cell weui-cell-pad">
				<!-- <div class="weui-cell__hd"><img src="resources/common/images/t-head1.jpg"/></div> -->
				<div class="weui-cell__bd" style="height: 3rem;">
					<div id="star" style="width: 150px;line-height: 3rem;"></div>
					<div id="hint" style="line-height: 3rem;"></div>
				</div>
			</div>
		</div>
		<div class="weui-cells weui-cells_form cells-mar0">
			<div class="weui-cell">
				<div class="weui-cell__bd fsize-07">
				<form id="addCommentForm" method="post" action="comment/addComment">
					<input type="hidden" name="orderId" value="${data.orderId }" />
					<textarea name="detail" class="weui-textarea" rows="3" placeholder="说说该商品的优点和美中不足的地方吧"></textarea>
					<input type="hidden" name="userId" value="${data.userId }" />
					<c:forEach items="${data.goodsId }" var="goodsId" >
						<input type="hidden" name="goodsId" value="${goodsId }" />
					</c:forEach>
					<input type="hidden" id="starLevel" name="starLevel" value="0" />
				</form>
					<button type="button" id="addComment">提交</button>
				</div>
			</div>
		</div>
	</div>
<!-- 公共js区域 -->
<script src="resources/common/js/jquery.min.js"></script>
<script src="resources/common/js/jquery-weui.min.js"></script>
<script src="resources/common/js/swiper.min.js"></script>
<script src="resources/common/js/city-picker.min.js"></script>
<script src="resources/common/js/jquery.raty.min.js" ></script>
<!--<script src="resources/common/js/startScore.js"></script>-->
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript">
$('#star').raty({

	  cancel    : false,

	  target    : '#hint',

	  targetKeep: true,

		path: 'resources/common/images',
	    starOn: 'star_on24.png',
	    starOff: 'star_off_24.png',
	});
	
$(".pull-left").click(function() {
	location.href = "order/myOrder/4";
});
	
$("#star").on("click", "img", function() {
	$("#starLevel").val($(this).attr("alt"));
});

$("#addComment").click(function() {
	if($("#starLevel").val() == "0") {
		$.alert("请选择星级");
		return;
	} else {
		$.ajax({
			url : 'comment/addComment',
			data : $("#addCommentForm").serialize(),
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if(data.status == 1) {
					$.alert(data.msg, function() {
						location.href = "order/myOrder/4";
					});
				}
			}
		});
	}
});
</script>
<script src="resources/mine/js/comment.js" />
<!-- 该页面应用js区域 -->
</body>
</html>