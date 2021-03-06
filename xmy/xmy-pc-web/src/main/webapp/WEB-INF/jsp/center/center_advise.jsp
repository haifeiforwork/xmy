<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>投诉与建议</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_advise.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">我的投诉与建议</div>
                <div class="category-content">
                    <div class="cate-content-title clearfix">
                        <span class="title-des">投诉服务记录</span>
                        <div class="btn-group add-float-r">
                            <button type="button" class="btn-theme btn-default btn-default3" onclick="window.location='/center/toAddAdvise?set=16'">投诉与建议</button>
                        </div>
                    </div>
                    <div class="cate-table">
                        <table cellspacing="0">
                            <tr class="th">
                                <th class="text-center default first">投诉编号</th>
                                <th class="text-center lg-default">订单编码</th>
                                <th class="text-center default">被投诉商家</th>
                                <th class="text-center most-default">投诉/建议原因</th>
                                <th class="text-center default">被投类型</th>
                                <th class="text-center default">被投状态</th>
                                <th class="text-center lg-default">申请时间</th>
                                <th class="text-center default last">操作</th>
                            </tr>
                            <c:if test="${empty list }">
                            	<tr class="table-list">
                            		<td class="text-center" colspan="9" style="padding:20px 0">暂无数据！</td>
                            	</tr>
                            </c:if>
                            <c:if test="${!empty list }">
	                            <c:forEach items="${list }" var="list">
	                            	<tr class="table-list">
		                                <td class="text-center">${list.id }</td>
		                                <td class="text-center">${list.no }</td>
		                                <td class="text-center">香满园</td>
		                                <td class="td-content">${list.content }</td>
		                                <td class="text-center">${list.genre==0?'投诉':'建议' }</td>
		                                <td class="text-center">${list.status==0?'未解决':'已解决' }</td>
		                                <td class="text-center"><fmt:formatDate value="${list.createTime}" pattern="yyyy年MM月dd日  HH:mm:ss"/></td>
		                                <td class="add-theme-color text-center" style="cursor: pointer;" onclick="window.location='/center/delAdvise?id=${list.id }'">删除</td>
	                            	</tr>
	                            </c:forEach>
                        	</c:if>
                        </table>
                     <c:if test="${pageBean.totalPage!=0 }">
                     <div class="paging btn-group">
                        <button type="button" style="cursor: pointer;" class="btn-default btn-top" onclick="window.location='/center/toAdvise?pageIndex=1&set=10&status=${status}'">首页</button>
                        <c:if test="${pageBean.pageIndex==1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                         <c:if test="${pageBean.pageIndex==0}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee">上一页</button>
                        </c:if>
                        <c:if test="${pageBean.pageIndex!=0}">
                        <c:if test="${pageBean.pageIndex!=1}">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-prevee" onclick="window.location='/center/toAdvise?pageIndex=${pageBean.pageIndex-1}&set=10&status=${status}'">上一页</button>
                        </c:if>
                        </c:if>
                        ${pageBean.pageIndex }/${pageBean.totalPage }
                        <c:if test="${pageBean.totalPage==0 || pageBean.pageIndex==pageBean.totalPage }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt">下一页</button>
                        </c:if>
                        
                        <c:if test="${pageBean.pageIndex!=pageBean.totalPage&&pageBean.totalPage!=0 }">
                        	<button type="button" style="cursor: pointer;" class="btn-default btn-nextt" onclick="window.location='/center/toAdvise?pageIndex=${pageBean.pageIndex+1}&set=10&status=${status}'">下一页</button>
                        </c:if>
                        <button type="button" style="cursor: pointer;" class="btn-default btn-last" onclick="window.location='/center/toAdvise?pageIndex=${pageBean.totalPage }&set=10&status=${status}'">末页</button>
                     </div>
                     </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  </body>
</html>
