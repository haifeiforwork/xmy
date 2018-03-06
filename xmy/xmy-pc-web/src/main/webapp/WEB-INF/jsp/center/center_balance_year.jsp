<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>账户余额</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_balance.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">账户余额</div>
                <div class="category-title category-title2">
                    <i class="iconfont control-i">&#xe60a;</i>余额(元)<span class="add-theme-color"><c:if test="${empty balance }">0.00</c:if><c:if test="${!empty balance }"><fmt:formatNumber type="number" value="${balance }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></c:if></span>
                </div>
                <div class="category-content idetails idetails2">
                    <!-- <div class="cate-content-title clearfix">
                        <ul class="cate-nav">
                            <a style="color: #767676" href="center/toBalance?pageIndex=1&set=9"><li>近三个月记录</li></a>
                            <li class="active">近一年记录</li>
                        </ul>
                    </div> -->
                    <div class="cate-table">
                        <div>
                            <table cellspacing="0">
                                <tr class="th">
                                    <th class="text-center first card-default">序号</th>
                                    <th class="text-center card-default">创建时间</th>
                                    <th class="text-center card-default">涉及订单号</th>
                                    <th class="text-center idetails-last-default">消费额扣减</th>
                                    <th class="text-center idetails-last-default">消费额增加</th>
                                    <th class="text-center last card-last-default">备注</th>
                                </tr>
                                <c:if test="${empty year }">
                                	<tr class="table-list">
                                		<td class="text-center" colspan="6" style="padding:20px 0">暂无数据！</td>
                                	</tr>
                                </c:if>
                                <c:if test="${!empty year }">
										<c:forEach items="${year }" var="oneyear" varStatus="i">
											<tr class="table-list">
												<td class="text-center">${i.count}</td>
												<td class="text-center">
													<c:if test="${not empty oneyear.useTime}"><fmt:formatDate value="${oneyear.useTime}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if>
			                                    	<c:if test="${empty oneyear.useTime}">--</c:if>
		                                    	</td>
												<td class="text-center">${empty oneyear.orderNo ?'--':oneyear.orderNo}</td>
												<td class="text-center">${oneyear.type==2?'--':oneyear.value }</td>
												<td class="text-center">${oneyear.type==1?'--':oneyear.value }</td>
												<td class="text-center">${oneyear.remark }</td>
											</tr>
										</c:forEach>
									</c:if>
                            </table>
                        </div>
                    </div>
                    <div class="paging btn-group">
                        <button type="button" style="cursor: pointer;" class="btn-default btn-top" onclick="window.location='/center/toBalanceYear?pageIndex=1&set=9'">首页</button>
                        <c:if test="${pageBean.pageIndex==1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee" onclick="window.location='/center/toBalanceYear?pageIndex=${pageBean.pageIndex-1}&set=9'">上一页</button>
                        </c:if>
                        ${pageBean.pageIndex }/${pageBean.totalPage }
                        <c:if test="${pageBean.pageIndex==pageBean.totalPage }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        <c:if test="${pageBean.totalPage==0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=pageBean.totalPage&&pageBean.totalPage!=0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt" onclick="window.location='/center/toBalanceYear?pageIndex=${pageBean.pageIndex+1}&set=9'">下一页</button>
                        </c:if>
                        <button type="button" style="cursor: pointer;" class="btn-default btn-last" onclick="window.location='/center/toBalanceYear?pageIndex=${pageBean.totalPage }&set=9'">末页</button>
                     </div>
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
})
</script>
</body>
</html>
