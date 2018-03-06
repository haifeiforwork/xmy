<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
    	<!--  <th><input type="checkbox" name="selectAll" lay-skin="primary" lay-filter="allChoose"></th> -->
       
        <th>文章标题</th>
        <th>所属栏目</th>
        <th>是否显示</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${pageBean.data }" var="data">
      <tr>
      <!--  <td><input type="checkbox" name="selectAll" lay-skin="primary"></td> -->
       
        <td>${data.name }</td>
         <td>
         		<c:forEach items="${paBeanColumn.data}" var="dataColumn">
         			
         			<c:if test="${dataColumn.id ==data.parentId }">${dataColumn.name}</c:if>
         		</c:forEach>  
         		
          </td>
         <td>
           <div class="layui-form-item">
			   <div class="layui-input-block">
         		<c:if test="${data.status == 0 }"><span class="switchOn"> 已启用 </span> <!-- <input type="checkbox" name="staus" lay-skin="switch" lay-text="启用|禁用" checked disabled> --></c:if>
         		<c:if test="${data.status eq 1 }"><span class="switchOff"> 已禁用 </span><!-- <input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用"  disabled> --></c:if>
         	 </div>
  			</div>
         	
         </td>
        <td>
        		<a href="content/article/edit/${data.id}"><button class="layui-btn">编辑</button></a>
        		<a href="javascript:void(0)"><button class="layui-btn  layui-btn-danger" onClick="delCheck('content/article/del/${data.id }')">删除</button></a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
