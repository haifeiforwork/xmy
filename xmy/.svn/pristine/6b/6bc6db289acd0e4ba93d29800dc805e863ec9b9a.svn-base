<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>购物卡</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_shop_card.css">
  </head>
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">购物卡</div>
                <form action="center/addShopCard" method="post">
                	<div class="category-title category-title2 clearfix">
	                    <div class="input-group2 clearfix">
	                        <div class="label">卡号 :</div>
	                        <div class="item-input"><input name="cardNo" type="text" class="default2"></div>
	                    </div>
	                    <div class="input-group2 clearfix">
	                        <div class="label">密码 :</div>
	                        <div class="item-input"><input name="cardPwd" type="text" class="default2"></div>
	                    </div>
	                   <div class="btn-group btn-group2"><button style="cursor: pointer;line-height:20px;" type="submit" class="btn-theme">点击激活</button></div>
	                   <input type="text" value="${msg }" style="display: none;" id="msg"/>
	                </div>
                </form>
                <div class="category-content idetails idetails2">
                    <%-- <div class="cate-content-title clearfix">
                        <ul class="cate-nav">
                            <li ${status==3?'class="active"':'' } onclick="window.location='/center/toShopCard?pageIndex=1&set=10&status=3'">近三个月记录</li>
                            <li ${status==12?'class="active"':'' } onclick="window.location='/center/toShopCard?pageIndex=1&set=10&status=12'">近一年记录</li>
                        </ul>
                    </div> --%>
                    <div class="cate-table">
                        <div>
                            <table cellspacing="0">
                                <tr class="th">
                                    <th class="text-center first card-default">卡号</th>
                                    <th class="text-center card-default">名称</th>
                                    <th class="text-center card-default">可使用金额</th>
                                    <th class="text-center idetails-last-default">有效时间</th>
                                    <th class="text-center idetails-last-default">状态</th>
                                    <th class="text-center last card-last-default">激活时间</th>
                                </tr>
                                <c:if test="${empty pageBean.data }">
                                	<tr class="table-list">
                                		<td class="text-center" colspan="6" style="padding:20px 0">暂无数据！</td>
                                	</tr>
                                </c:if>
                                <c:if test="${!empty pageBean.data }">
	                                <c:forEach items="${pageBean.data }" var="shopCard">
	                                	<tr class="table-list">
		                                    <td class="text-center">${shopCard.cardNum }</td>
		                                    <td class="text-center"><fmt:formatNumber type="number" value="${shopCard.totalValue }" pattern="0" maxFractionDigits="0"/>元面值购物卡</td>
		                                    <td class="text-center"><fmt:formatNumber type="number" value="${shopCard.balance }" pattern="0.00" maxFractionDigits="2"/></td>
		                                    <td class="text-center">无限制</td>
		                                    <td class="text-center">${shopCard.status==1?'未激活':'已激活' }</td>
		                                    <td class="text-center"><fmt:formatDate value="${shopCard.activeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                                </tr>
	                                </c:forEach>
                                </c:if>
                            </table>
                        </div>
                    </div>
                    <c:if test="${pageBean.totalPage!=0 }">
                    <div class="paging btn-group">
                        <button type="button" style="cursor: pointer;" class="btn-default btn-top" onclick="window.location='/center/toShopCard?pageIndex=1&set=10&status=${status}'">首页</button>
                        <c:if test="${pageBean.pageIndex==1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                         <c:if test="${pageBean.pageIndex==0}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=0}">
                        <c:if test="${pageBean.pageIndex!=1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee" onclick="window.location='/center/toShopCard?pageIndex=${pageBean.pageIndex-1}&set=10&status=${status}'">上一页</button>
                        </c:if>
                        </c:if>
                        ${pageBean.pageIndex }/${pageBean.totalPage }
                        <c:if test="${pageBean.totalPage==0 || pageBean.pageIndex==pageBean.totalPage }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        
                        <c:if test="${pageBean.pageIndex!=pageBean.totalPage&&pageBean.totalPage!=0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt" onclick="window.location='/center/toShopCard?pageIndex=${pageBean.pageIndex+1}&set=10&status=${status}'">下一页</button>
                        </c:if>
                        <button type="button" style="cursor: pointer;" class="btn-default btn-last" onclick="window.location='/center/toShopCard?pageIndex=${pageBean.totalPage }&set=10&status=${status}'">末页</button>
                     </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<script>
$(document).ready(function () {
    $(".cate-table>div:gt(0)").hide();
    $(".cate-nav li").click(function () {
        $(this).addClass("active").siblings().removeClass("active")
        var index=$(".cate-nav li").index(this);
        $("div.cate-table>div").eq(index).show().siblings().hide()
    })
    var msg = $("#msg").val();
    if(msg!=""){
    	var btnFn = function(){
			easyDialog.close();
		  return false;
		};
		easyDialog.open({
		  container : {
		    header : '提示',
		    content : msg,
		    yesFn : btnFn
		  }
		});
    }
})
</script>
  </body>
</html>
