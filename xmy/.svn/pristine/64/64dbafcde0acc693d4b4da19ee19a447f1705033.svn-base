<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>积分明细</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_points.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">积分明细</div>
                <div class="category-title category-title2">您当前可使用的积分为<span class="add-theme-color"><c:if test="${empty points }">0</c:if><c:if test="${!empty points }">${points }</c:if></span></div>
                <div class="category-content idetails idetails2">
                    <div class="cate-table">
                        <table cellspacing="0">
                            <tr class="th">
                                <th class="text-center first sm-default">序号</th>
                                <th class="text-center sug-default">获得积分</th>
                                <th class="text-center sug-default">使用积分</th>
                                <th class="text-center idetails-default">涉及订单号</th>
                                <th class="text-center idetails-default">获得时间</th>
                                <th class="text-center idetails-default">使用时间</th>
                                <th class="text-center last idetails-last-default">备注</th>
                            </tr>
                            <c:if test="${empty list }">
                            	<tr class="table-list">
                            		<td class="text-center" colspan="7" style="padding:20px 0">暂无数据！</td>
                            	</tr>
                            </c:if>
                            <c:if test="${!empty list }">
	                           <c:forEach items="${list}" var="point" varStatus="indexNum">
	                          	 <tr class="table-list">
	                            	<td class="text-center">${indexNum.count }</td>
	                                <td class="text-center">${point.spendType==1? point.moneyPoint : '--'}</td>
	                                <td class="text-center">${point.spendType==2? point.moneyPoint : '--'}</td>
	                                <td class="text-center">${point.no }</td>
	                                <td class="text-center"><fmt:formatDate value="${point.spendType==1? point.creatTime : ''}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                <td class="text-center"><fmt:formatDate value="${point.spendType==2? point.creatTime : ''}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                <td class="text-center">${point.remarks }</td>
	                              </tr>
	                            </c:forEach>
                            </c:if>
                        </table>
                    </div>
                    <c:if test="${pageBean.totalPage!=0}">
                    <div class="paging btn-group">
                        <button type="button" style="cursor: pointer;" class="btn-default btn-top" onclick="window.location='/center/toPointsDetail?pageIndex=1&set=13'">首页</button>
                        <c:if test="${pageBean.pageIndex==1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee" onclick="window.location='/center/toPointsDetail?pageIndex=${pageBean.pageIndex-1}&set=13'">上一页</button>
                        </c:if>
                        ${pageBean.pageIndex }/${pageBean.totalPage }
                        <c:if test="${pageBean.pageIndex==pageBean.totalPage }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        <c:if test="${pageBean.totalPage==0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=pageBean.totalPage&&pageBean.totalPage!=0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt" onclick="window.location='/center/toPointsDetail?pageIndex=${pageBean.pageIndex+1}&set=13'">下一页</button>
                        </c:if>
                        <button type="button" style="cursor: pointer;" class="btn-default btn-last" onclick="window.location='/center/toPointsDetail?pageIndex=${pageBean.totalPage }&set=13'">末页</button>
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
