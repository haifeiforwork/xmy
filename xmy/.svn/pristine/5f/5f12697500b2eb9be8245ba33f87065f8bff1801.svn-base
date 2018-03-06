<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<table class="layui-table">
  <%-- <colgroup>
  	<col width="50"> 
    <col width="100">
    <col width="80">
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="100">
    <col width="80">
    <col width="80">
    <col width="80">
    <col width="80">
    <col width="80">
    <col width="150">
    <col width="200">
  </colgroup> --%>
  <thead>
    <tr>
     <th>ID</th>
      <th>收支类别</th>
      <th>金额</th>
      <th>时间</th>
      <th>备注</th>
    </tr> 
  </thead>
  <tbody>
  	<c:forEach items="${userBillFlow}" var="billFlow">
  	<tr >
  	  <td>${billFlow.id}</td>
      <td>	
      		<c:if test="${billFlow.spendType==1}">存入</c:if>
      		<c:if test="${billFlow.spendType==2}">消费</c:if>
      </td>
      <td>
      		<c:if test="${billFlow.spendType==1}">¥ +${billFlow.moneyPoint}</c:if>
      		<c:if test="${billFlow.spendType==2}">¥ -${billFlow.moneyPoint}</c:if>
      </td>
      <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${billFlow.creatTime}"></fmt:formatDate> </td>
       <td>${billFlow.remarks}</td>
    </tr>
  	</c:forEach>
  </tbody>
</table>
<script type="text/javascript">
layui.use(["pager","form"],function(){
	
})
</script>
