<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="layui-table">
  <colgroup>
  	<col width="50"> 
    <col width="100">
    <col width="150">
    <col width="150">
    <col width="100">
    <col width="80">
    <col width="80">
  </colgroup>
  <thead>
    <tr>
     <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
      <th>商品编码</th>
      <th>商品名称</th>
      <th>分类</th>
      <th>品牌</th>
      <th>供货商</th>
      <th>售价(元)</th>
    </tr> 
  </thead>
  <tbody>
  	<c:forEach items="${glist}" var="item">
  	<tr >
  	  <td><input type="checkbox" data-id="${item.id }" name="" lay-skin="primary"></td>
  	  <td>${item.code }</td>
      <td>${item.name}</td>
      <td>${item.categoryName}</td>
      <td>${item.brandName}</td>
      <td>${item.supplierName}</td>
      <td>${item.price}</td>
    </tr>
  	</c:forEach>
  </tbody>
</table>

<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	//全选 监听
	form.on('checkbox(allChoose)', function(data){
     	var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	     child.each(function(index, item){
	       item.checked = data.elem.checked;
	       console.log($(this).data("id"))
	     });
     	form.render('checkbox');
   });
	
	
	
})

</script>