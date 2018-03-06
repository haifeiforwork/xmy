<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>优惠券信息</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_coupon.css">
  </head>
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">优惠券信息</div>
                <div class="category-content">
                    <div class="cate-content-title clearfix">
                        <ul class="cate-nav cate-nav-left">
                        	<li onclick="window.location='/center/toCoupon?pageIndex=1&set=11&status=1'" ${status==1?'class="active"':'' }>可用抵用券  ${list[0] }张</li>
                        	<li onclick="window.location='/center/toCoupon?pageIndex=1&set=11&status=2'" ${status==2?'class="active"':'' }>已用抵用券  ${list[1] }张</li>
                        	<li onclick="window.location='/center/toCoupon?pageIndex=1&set=11&status=3'" ${status==3?'class="active"':'' }>过期抵用券  ${list[2] }张</li>
                        </ul>
                        <ul class="cate-nav cate-nav-right">
                        	<li onclick="window.location='/center/toCouponSort?pageIndex=1&set=11&sort=4'" ${status==4?'class="active"':'' }>新到账</li>
                        	<li onclick="window.location='/center/toCouponSort?pageIndex=1&set=11&sort=5'" ${status==5?'class="active"':'' }>即将过期</li>
                        	<li onclick="window.location='/center/toCouponSort?pageIndex=1&set=11&sort=6'" ${status==6?'class="active"':'' }>最优惠</li>
                        </ul>
                    </div>
                    <div class="category-content-block">
                        <div>
                            <ul class="bimg clearfix">
                               <c:forEach items="${pageBean.data }" var="coup">
                               <li>
                                   <p class="item-word clearfix"><span class="num">¥ ${coup.couponValue }</span>
                                   <span style="display: ${coup.useRange==1?'':'none'};" class="fw">全店通用</span>
                                   <span style="display: ${coup.useRange==2?'':'none'};" class="fw">分类使用</span>
                                   <span style="display: ${coup.useRange==3?'':'none'};" class="fw">限定商品</span>
                                   <span style="display: ${coup.useRange==4?'':'none'};" class="fw">排队商品</span></p>
                                   <p class="item-des">使用条件 : 满${coup.quota }元可用</p>
                                   <p class="item-des">有限时间 : <fmt:formatDate value="${coup.effectiveStartTime}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${coup.effectiveEndTime}" pattern="yyyy-MM-dd"/></p>
                               </li>
                               </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <c:if test="${pageBean.totalPage!=0 }">
                    <div class="paging btn-group">
                        <button type="button" style="cursor: pointer;" class="btn-default btn-top" onclick="window.location='/center/toCoupon?pageIndex=1&set=11&status=${status}'">首页</button>
                        <c:if test="${pageBean.pageIndex==1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee" onclick="window.location='/center/toCoupon?pageIndex=${pageBean.pageIndex-1}&set=11&status=${status}'">上一页</button>
                        </c:if>
                        ${pageBean.pageIndex }/${pageBean.totalPage }
                        <c:if test="${pageBean.pageIndex==pageBean.totalPage }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        <c:if test="${pageBean.totalPage==0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=pageBean.totalPage&&pageBean.totalPage!=0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt" onclick="window.location='/center/toCoupon?pageIndex=${pageBean.pageIndex+1}&set=11&status=${status}'">下一页</button>
                        </c:if>
                        <button type="button" style="cursor: pointer;" class="btn-default btn-last" onclick="window.location='/center/toCoupon?pageIndex=${pageBean.totalPage }&set=11&status=${status}'">末页</button>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<script type="text/javascript">
$(document).ready(function () {
    $(".category-content-block>div:gt(0)").hide();
    $(".cate-nav-left li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".cate-nav-left li").index(this);
        $(".category-content-block>div").eq(index).show().siblings().hide()
    })
});
$(".cate-nav-right li").click(function () {
    $(this).addClass("active").siblings().removeClass("active")
});
$(".bimg li:nth-of-type(4n)").css("margin-right","0")
</script>
  </body>
</html>
