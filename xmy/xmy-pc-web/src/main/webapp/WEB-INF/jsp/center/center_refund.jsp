<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>退款列表</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_refund.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">退款列表</div>
                <div class="category-content idetails">
                    <div class="cate-table">
                        <table cellspacing="0">
                            <tr class="th">
                                <th class="text-center first sm-default">序号</th>
                                <th class="text-center idetails-default">订单编号</th>
                                <th class="text-center idetails-default">退款金额</th>
                                <th class="text-center suging-default">支付方式</th>
                                <th class="text-center suging-default">状态</th>
                                <th class="text-center idetails-default">申请时间</th>
                                <th class="text-center idetails-default last">退款时间</th>
                            </tr>
                            <c:if test="${empty list }">
                            	<tr class="table-list">
                            		<td class="text-center" colspan="11" style="padding:20px 0">暂无数据！</td>
                            	</tr>
                            </c:if>
                            <c:if test="${!empty list }">
	                            <c:forEach items="${list }" var="order">
	                            	<tr class="table-list">
		                                <td class="text-center">${order.id }</td>
		                                <td class="text-center">${order.no }</td>
		                                <td class="text-center">${order.total }</td>
		                                <td style="display: ${order.payType==1?'':'none' };" class="text-center">支付宝</td>
		                                <td style="display: ${order.payType==2?'':'none' };" class="text-center">微信</td>
		                                <td style="display: ${order.payType==3?'':'none' };" class="text-center">银联</td>
		                                <td style="display: ${order.payType==4?'':'none' };" class="text-center">购物卡</td>
		                                <td style="display: ${order.payType==5?'':'none' };" class="text-center">货到付款</td>
		                                <td class="text-center">${order.returnStatus==1?'已退款':'退款中' }</td>
		                                <td class="text-center">2017-01-05 65:33:22</td>
		                                <td class="text-center">2017-01-06 11:08:17</td>
	                           	 	</tr>
	                            </c:forEach>
                            </c:if>
                        </table>
                    </div>
                    <c:if test="${pageBean.totalPage!=0 }">
                    <div class="paging btn-group">
                        <button type="button" style="cursor: pointer;" class="btn-default btn-top" onclick="window.location='/center/toRefund?pageIndex=1&set=10&status=${status}'">首页</button>
                        <c:if test="${pageBean.pageIndex==1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                         <c:if test="${pageBean.pageIndex==0}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=0}">
                        <c:if test="${pageBean.pageIndex!=1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee" onclick="window.location='/center/toRefund?pageIndex=${pageBean.pageIndex-1}&set=10&status=${status}'">上一页</button>
                        </c:if>
                        </c:if>
                        ${pageBean.pageIndex }/${pageBean.totalPage }
                        <c:if test="${pageBean.totalPage==0 || pageBean.pageIndex==pageBean.totalPage }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        
                        <c:if test="${pageBean.pageIndex!=pageBean.totalPage&&pageBean.totalPage!=0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt" onclick="window.location='/center/toRefund?pageIndex=${pageBean.pageIndex+1}&set=10&status=${status}'">下一页</button>
                        </c:if>
                        <button type="button" style="cursor: pointer;" class="btn-default btn-last" onclick="window.location='/center/toRefund?pageIndex=${pageBean.totalPage }&set=10&status=${status}'">末页</button>
                     </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
    <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>   
  </body>
</html>
