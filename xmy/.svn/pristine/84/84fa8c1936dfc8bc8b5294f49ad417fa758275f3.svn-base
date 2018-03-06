<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题列表</title>
<%@ include file="/commons/comm_css.jsp"%>
</head>
<style type="text/css">
	.layui-table td{padding:2px 15px;font-size: 8px}
	.layui-table th{padding:2px 15px;font-size: 8px}
</style>
<body>
<div class="layui-layout layui-layout-admin">
<%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
		<ul class="layui-tab-title site-title">
	      	<li class="layui-this" >问题列表</li>
	      	<a href="onlineActivity/updateQuestion?activityId=${activityId }"><li >添加问题</li></a>
		 </ul>
		<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			<%-- <div class="layui-tab-item layui-tab-brief layui-show ">
				<form id="myForm">
					<div class="layui-input-inline">
							<input type="text" class="layui-input q-rule" zfj-query="name:cn" value="${params.name}" name="name" placeholder="活动名称" style="width: 150px;"/>
					</div>
					<div class="layui-input-inline">
					 	     <input type="text"  name="begin_time" zfj-query="begin_time:ge" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})"  placeholder="活动开始时间" autocomplete="off" class="layui-input q-rule" >
					</div>
					<div class="layui-input-inline">
					 	     <input type="text"   name="end_time" zfj-query="end_time:le" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})"  placeholder="活动结束时间" autocomplete="off" class="layui-input q-rule" >
					</div>
					<input type="submit" class="layui-btn btn-submit" value="提交"  />
				</form>
			</div>
			<input type="hidden" id="pageCount"  value="${totalPage }"/> --%> 
			<div id="mytable" class="layui-form" >
				<table class="layui-table"  >
				  <colgroup>
				   <col width="50">
				   <col width="150">
				   <col width="150">
				   <col width="150">
				   <col width="150">
				   <col width="150">
				 </colgroup>
				 <thead>
				   <tr>
				   	 <th>ID</th>
				     <th>活动ID</th>
				     <th>问题标题</th>
				     <th>答案A</th>
				     <th>答案B</th>
				     <th>答案C</th>
				     <th>答案D</th>
				     <th>正确答案</th>
				     <th>操作</th>
				   </tr> 
				 </thead>
				 <tbody>
				   <c:forEach items="${questionList }" var="item">
				    <tr>
				    	 <td>${item.id }</td>
					   	 <td>${item.activityId }</td>
					     <td>${item.activityQuestion }</td>
					     <td>${item.answerA }</td>
					     <td>${item.answerB }</td>
					     <td>${item.answerC }</td>
					     <td>${item.answerD }</td>
					     <td>${item.trueAnswer }</td>
					     <td>
					     	<button onclick="window.location='onlineActivity/updateQuestion?id=${item.id }'" class="layui-btn layui-btn-radius">编辑 </button>
					     </td>
				     </tr>
				   </c:forEach>
				  </tbody>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/commons/buttom.jsp"%>
</div>		   
</body>
</html>