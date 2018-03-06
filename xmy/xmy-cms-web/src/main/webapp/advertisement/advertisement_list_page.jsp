<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <colgroup>
    <col width="100">
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
    <col width="150">
  </colgroup>
  <thead>
    <tr>
      <th>广告名称</th>
      <th>广告位置</th>
      <th>关联分类名称</th>
      <th>广告图片数量</th>
      <th>最后更新时间</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  	<c:forEach items="${data}" var="item">
  	<tr >
      <td>${item.name}</td>
      <td>${item.positionName}</td>
      <td>${not empty item.categoryName? item.categoryName:'-' }</td>
      <td>${item.imgNum}</td>
      <td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      <td>
		    <div class="site-demo-button  " >
		      <button class="layui-btn" id="edit" data-id="${item.id }" >编辑</button>
		    </div>
      </td>
    </tr>
  	</c:forEach>
  </tbody>
</table>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	
	
	$("tbody td #edit").click(function(){
		window.location.href="advertisement/toEditAdvertisement?id="+$(this).data("id"); 
	})
})

</script>