<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table class="layui-table">
  <thead>
    <tr>
    	<!--  <th><input type="checkbox" name="selectAll" lay-skin="primary" lay-filter="allChoose"></th> -->
       
        <th>ID</th>
        <th>促销名称</th>
        <td>赠送数量</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>状态</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${buyAndPresent.data }" var="buyAndPresent">
      <tr>
        <td data-id="${buyAndPresent.id }">${buyAndPresent.id }</td>
        <td>${buyAndPresent.name }</td>
        <td>${buyAndPresent.giftCount }</td>
        <td>
        		<fmt:formatDate value="${buyAndPresent.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
        <td>
        	<fmt:formatDate value="${buyAndPresent.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
         <td>
         		<c:if test="${buyAndPresent.status==0}"><span class="status">启用</span></c:if>
        		<c:if test="${buyAndPresent.status==1}"><span class="status">禁用</span></c:if>
        </td>
        <td>
        		<a href="buyAndPresent/buyAndPresent/edit/${buyAndPresent.id}"><button data-id="${buyAndPresent.id }" class="layui-btn">编辑</button></a>
        		<a href="javascript:void(0);"><button data-id="${buyAndPresent.id }" class="layui-btn  layui-btn-danger" id="btn_del">删除</button></a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
