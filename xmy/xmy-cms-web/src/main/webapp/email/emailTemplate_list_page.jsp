<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
    	<!--  <th><input type="checkbox" name="selectAll" lay-skin="primary" lay-filter="allChoose"></th> -->
       
        <th>模板名称</th>
        <th>邮件内容</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${pageBean.data }" var="data">
      <tr>
      <!--  <td><input type="checkbox" name="selectAll" lay-skin="primary"></td> -->
       
        <td>${data.name }</td>
         <td>${data.des }</td>
        <td>
        		<a href="mallSetting/emailTemplate/edit/${data.id}"><button class="layui-btn">编辑</button></a>
        		<a href="javascript:0"><button class="layui-btn  layui-btn-danger" onClick="delCheck('mallSetting/emailTemplate/del/${data.id }')">删除</button></a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
