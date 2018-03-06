<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--会员中心-->
<!DOCTYPE html>
<html>
<head>
    <title>赚积分</title>
    <!-- 公共css区域 -->
    <%@ include file="/WEB-INF/jsp/common/common_css.jsp" %>
    <!-- 公共css完区域 -->
    <!-- 该页面应用css区域 -->
    <link href="resources/vipcenter/css/vipcenter.css" rel="stylesheet">
    <!-- 该页面应用css区域 -->
    <style type="text/css">
        .content{background-color: #FFF;}
        .des_word{font-size: 0.65rem;color: #333;padding: 0.5rem;}
        .des_h{font-size: 0.75rem;font-weight: 700;}
        .des_title{font-size: 0.7rem;font-weight: 600}
        .des_item{word-break: break-all;}
    </style>
</head>
<body ontouchstart>
<header>
    <div class="header-top">
        <div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
        <div class="pull-center">赚积分</div>
        <div class="pull-right">
            <!-- <span class="share"><img src="resources/common/images/btn-fenl-share@2x.png"/></span> -->
            <span class="shrink"><img src="resources/common/images/btn-fenl-more@2x.png"/>
            	<div class="modal-some">
                    
                </div>
            </span>
        </div>
    </div>
</header>
<div class="content" style="bottom: 0;top: 51px;">
    <div class="des_word">
        <div class="des_h">赚积分</div>
        <div class="des_title">香满圆提供几种方式获得积分：</div>
        <div class="des_item">（1）购物：购物在完成订单时可获得积分，积分数量与支付金额比例为1元=1分 。 </div>
        <div class="des_item">（2）评论商品：评论订单可获得积分，好评获得5积分，中评获得3积分，差评获得1积分。   </div>
        <div class="des_item">（3）每日签到：在积分商城签到可获得积分，第一日签到获得5积分，第二日签到获得10积分，第三日签到获得15积分以此循环，每过7日重新循环。   </div>
        <div class="des_item">（4）安全绑定：手机及邮箱绑定可获得50积分奖励。    </div>
        <div class="des_item">（5）专题活动：参与有积分奖励的相应专题活动可获得积分。</div>
    </div>
</div>
<!-- 公共js区域 --> 
<%@include file="/WEB-INF/jsp/common/common_js.jsp" %>
<!-- 公共js区域完 -->
<!-- 该页面应用js区域 -->
<script type="text/javascript" src="resources/vipcenter/js/get_accpoint.js"></script>
<!-- 该页面应用js区域 -->
</body>
</html>