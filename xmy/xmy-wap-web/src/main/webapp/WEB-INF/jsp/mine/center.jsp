<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 个人中心 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <!-- 个人中心css文件 -->
		<link rel="stylesheet" href="resources/mine/css/center.css" >
        <!-- 公共css区域 -->
        <link rel="stylesheet" href="resources/common/css/weui.min.css">
        <link rel="stylesheet" href="resources/common/css/jquery-weui.min.css">
        <link rel="stylesheet" href="resources/common/css/style.css">
        <!-- 公共css完区域 -->
        <!-- 该页面应用css区域 -->
        <!-- 该页面应用css区域 -->
</head>
    <body ontouchstart>	
	<div class="content" style="top:0;">
    	<div class="head">
            <div class="weui-flex chunk">
                <div id="toAccount" class="weui-flex__item lump"><img src="resources/common/images/btn-grzx-zh@2x.png"/><p>账户</p></div>
                <div id="toAddress" class="weui-flex__item lump"><img src="resources/common/images/btn-grzx-dz@2x.png"/><p>地址</p></div>
                <div class="weui-flex__item tou">
                	<c:if test="${empty wap_session.userInfo.avatar }">
                        	<img class="weui-media-box__thumb" id="changeAvatar" src="resources/common/images/cstars/defaultheadimg.png">
                	</c:if>
                	<c:if test="${not empty wap_session.userInfo.avatar }">
                    	<img id="changeAvatar" src="resources/common/images/cstars/${wap_session.userInfo.avatar }" />
                    </c:if>
                    <%-- <c:if test="${wap_session.userInfo.mobilePhone == null }">
	                    <div class="not-bound">手机未绑定</div>
	                    <div style="margin-top: 0.8rem;">${wap_session.userInfo.realName }</div>
                    </c:if> --%>
                    <%-- <c:if test="${wap_session.userInfo.mobilePhone != null }">
	                    <div class="not-bound">手机已绑定</div> -->
	                    <div class="phone"><c:if test="${not empty wap_session.user.name }">${wap_session.user.name }</c:if><c:if test="${empty wap_session.user.name }">${wap_session.userInfo.mobilePhone }</c:if></div>
	                    <div>${wap_session.userInfo.realName }</div>
                    </c:if> --%>
                </div>
                <div id="toSetting" class="weui-flex__item lump"><img src="resources/common/images/btn-grzx-sz@2x.png"/><p>设置</p></div>
                <div id="toMessage" class="weui-flex__item lump">
                <div class="fixation" url="mine/getUnpaidCount/4">
                	<img src="resources/common/images/btn-grzx-xx@2x.png"/>
	                <!-- <div class="number">20</div> -->
                <p>消息</p>
                </div>
                </div>
            </div>
            <c:if test="${wap_session.userInfo.mobilePhone == null }">
             <div class="not-bound">手机未绑定</div>
             <div style="margin-top: 0.8rem;color:#FFF;font-size:0.6rem;text-align:center">${wap_session.userInfo.realName }</div>
            </c:if>
             <c:if test="${wap_session.userInfo.mobilePhone != null }">
             <!--  <div class="not-bound">手机已绑定</div> -->
             <div class="phone" style="color:#FFF;font-size:0.6rem;text-align:center"><c:if test="${not empty wap_session.user.name }">${wap_session.user.name }</c:if><c:if test="${empty wap_session.user.name }">${wap_session.userInfo.mobilePhone }</c:if></div>
             <div style="color:#FFF;font-size:0.6rem;text-align:center">${wap_session.userInfo.realName }</div>
              </c:if>
        </div>
        <div class="weui-flex gr-message">
            <div id="accBalance" class="weui-flex__item left">
                <div class="number red">￥
                	<span class="fixation balance" url="mine/getUnpaidCount/5"></span>
	                <%-- <c:if test="${wap_session.userInfo.balance == null}">0.00</c:if>
	                <c:if test="${wap_session.userInfo.balance == '0.0000'}">0.00</c:if>
	                <c:if test="${wap_session.userInfo.balance != null && wap_session.userInfo.balance != '0.0000' }">${wap_session.userInfo.balance }</c:if>--%>
                </div> 
                <div class="title red-bg">余额</div>
            </div>
            <div id="pointsBalance" class="weui-flex__item right">
                <div class="number green"><c:if test="${wap_session.userInfo.accPoints == null}">0</c:if>${wap_session.userInfo.accPoints }</div>
                <div class="title green-bg">积分</div>
            </div>
        </div>
        <div class="weui-flex menu">
            <div class="weui-flex__item pj Paymentpending">
            	<div url="mine/getUnpaidCount/1" class="fixation">
            		<img src="resources/common/images/btn-grzx-dfk@2x.png"/>
<%-- 	            	<c:if test="${unpaidCount1 ==0}"><div></div></c:if>
	            	<c:if test="${unpaidCount1 !=0}"><div class="number">${unpaidCount1 }</div></c:if> --%>
            	</div>
            	<p>待付款</p>
            </div>
            <div class="weui-flex__item pj waitGood">
            	<div url="mine/getUnpaidCount/2" class="fixation">
	            	<img src="resources/common/images/btn-grzx-dsh@2x.png"/>
<%-- 	            	<c:if test="${unpaidCount2 ==0}"><div></div></c:if>
	            	<c:if test="${unpaidCount2 !=0}"><div class="number">${unpaidCount2 }</div></c:if> --%>
	            </div>
	            <p>待收货</p>
            </div>
            <div class="weui-flex__item pj toEvaluate">
                <div url="mine/getUnpaidCount/3" class="fixation">
                	<img src="resources/common/images/btn-grzx-dpj@2x.png"/>
<%--                 	<c:if test="${unpaidCount3 ==0}"><div></div></c:if>
	            	<c:if test="${unpaidCount3 !=0}"><div class="number">${unpaidCount3 }</div></c:if> --%>
                	
                </div>
                <p>待评价</p>
            </div>
            <div class="weui-flex__item pj apply_refund">
            
            	<div class="fixation"><img src="resources/common/images/btn-grzx-thsh@2x.png"/></div><p>退换/售后</p>
            
            </div>
            <div src="order/myOrder/0" class="weui-flex__item pj orderQuery"><div class="fixation"><img src="resources/common/images/btn-grzx-wddd@2x.png"/></div><p>我的订单</p></div>
        </div>
        <div class="area row">
            <div class="ly-33 shopping-card">
                <img src="resources/common/images/ico-grzx-gwk@2x.png"/>
                <p>购物卡</p>
                <div class="hot"><img src="resources/common/images/hot.png"/></div>
            </div>
            <div class="ly-33">
                <div class="ly-100 coupon"><img src="resources/common/images/ico-grzx-yhq@2x.png"/><span>优惠券</span></div>
                <div class="ly-100 qx zj footStep"><img src="resources/common/images/ico-grzx-zj@2x.png"/><span>足迹</span></div>
            </div>
            <div class="ly-33">
                <div class="ly-100 favorite"><img src="resources/common/images/ico-grzx-scj@2x.png"/><span>收藏夹</span></div>
                <a id="oneClickCall" style="color:#999;" class="ly-100 qx bh"><img src="resources/common/images/ico-grzx-kf@2x.png"/><span>一键拨号</span></a>
            </div>
        </div>
        <div class="list">
        
        </div>
   	</div>
    <!-- 该页面应用js区域 -->
   	<%@ include file="/WEB-INF/jsp/common/common_js.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/common_footer.jsp" %>
    <script type="text/javascript" src="resources/mine/js/center.js" ></script>
    
    

    </body>
</html>