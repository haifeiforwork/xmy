<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
    	<!--  <th><input type="checkbox" name="selectAll" lay-skin="primary" lay-filter="allChoose"></th> -->
       
        <th>ID</th>
        <th>邮寄方式</th>
        <th>收费规则（重量KG）</th>
        <th>排序</th>
        <th>状态</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${pageBean.data }" var="data">
      <tr>
      <!--  <td><input type="checkbox" name="selectAll" lay-skin="primary"></td> -->
        <td>${data.id }</td>
        <td>${data.name }</td>
        <td>${data.des }</td>
        <td>${data.weight }</td>
        <td>
        	 <div class="layui-form-item">
			   <div class="layui-input-block">
         		<c:if test="${data.status == 0 }"> <input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用" checked disabled></c:if>
         		<c:if test="${data.status eq 1 }"> <input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用" disabled></c:if>
         	 </div>
  			</div>
        </td>
        <td>
        		<a href="mallSetting/deliveryMode/edit/${data.id}"><button class="layui-btn">编辑</button></a>
        		<a href="javascript:0"><button class="layui-btn  layui-btn-danger" onClick="delCheck('mallSetting/deliveryMode/del/${data.id }')">删除</button></a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
