<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
        <th>专题页面名称</th>
        <th>访问路径</th>
        <th>创建时间</th>
        <th>状态</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${data }" var="data">
      <tr>
        <td>${data.name }</td>
         <td>${data.unFilepath } </td>
         <td>
         <fmt:formatDate value="${data.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
         	
          </td>
          <td>	
          	<c:if test="${data.status eq 1}"><span class="status_ok">已解压</span></c:if>
          	
          	
          	<c:if test="${data.status eq 0}"><span class="status_error">解压失败</span></c:if>
          	
          	</td>
          </td>
        <td>
        		<a href="javascript:0"><button class="layui-btn  layui-btn-danger" onClick="delCheck('topicPage/delTopicPagex/${data.id }')">删除</button></a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
