<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择商品分类</title>
</head>
<body>
	
<table class="layui-table">
  <colgroup>
    <col width="30">
    <col width="250">
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th></th>
      <th>广告名称</th>
      <th>广告位置</th>
      <th>关联分类名称</th>
      <th>广告图片数量</th>
      <th>最后更新时间</th>
    </tr> 
  </thead>
  <tbody id="content">
  	<c:forEach items="${data}" var="item">
  	<tr>
  	   <td>
  	  	 <input type="radio"  name="ad" data-id="${item.id}"  data-name="${item.name}" value="${item.id}" title=" " lay-skin="primary">
  	   </td>
  	   <td>${item.name}</td>
      <td>${item.positionName}</td>
      <td>${not empty item.categoryName? item.categoryName:'-' }</td>
      <td>${item.imgNum}</td>
      <td>
      		<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
      </td>
    </tr>
  	</c:forEach>
  </tbody>
</table>
	
</body>
<%@ include file="/commons/comm_footer_js.jsp"%>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	

	
	
	$("tbody td #edit").click(function(){
		window.location.href="advertisement/toEditAdvertisement?id="+$(this).data("id"); 
	})
})

</script>