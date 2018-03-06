<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<table class="layui-table">
  <thead>
    <tr>
     <th>ID</th>
      <th>收支类别</th>
      <th>积分</th>
      <th>时间</th>
      <th>备注</th>
    </tr> 
  </thead>
  <tbody>
  	<c:forEach items="${userPointFlow}" var="pointFlow">
  	<tr >
  	  <td>${pointFlow.id}</td>
      <td>	
      		<c:if test="${pointFlow.spendType==1}">收入</c:if>
      		<c:if test="${pointFlow.spendType==2}">兑换</c:if>
      </td>
      <td>
      		<c:if test="${pointFlow.spendType==1}"> +${pointFlow.moneyPoint}</c:if>
      		<c:if test="${pointFlow.spendType==2}">-${pointFlow.moneyPoint}</c:if>
      </td>
      <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${pointFlow.creatTime}"></fmt:formatDate> </td>
       <td>${pointFlow.remarks}</td>
    </tr>
  	</c:forEach>
  </tbody>
</table>
<script type="text/javascript">
layui.use(["pager","form"],function(){
	
})
</script>
