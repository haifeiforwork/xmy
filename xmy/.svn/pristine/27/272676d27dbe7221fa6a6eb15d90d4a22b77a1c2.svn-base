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
     <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
     <th>ID</th>
      <th>用户账号</th>
      <th>手机</th>
      <th>余额</th>
      <th>积分</th>
      <th>累计消费</th>
      <th>注册日期</th>
      <th>用户状态</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  	<c:forEach items="${userList}" var="user">
  	<tr >
  	  <td><input type="checkbox" data-id="${user.id }" name="" lay-skin="primary"></td>
  	  <td>${user.id}</td>
      <td>${user.name}</td>
     <%--  <td><img alt="" src="${item.imgPath}"></td> --%>
      <%-- <td>${item.code}</td> --%>
      <td>${user.mobilePhone}</td>
      <td>¥ ${user.balance}</td>
      <td>${user.accPoints}</td>
      <td>¥ ${user.totalCost}</td>
      <td><fmt:formatDate pattern="yyyy-MM-dd" value="${user.createTime}"></fmt:formatDate> </td>
      <td id="oper">
      		
      		<c:if test="${user.status==0}"><i class="layui-icon" style="color: #009688;">&#xe617;</i> 启用</c:if>
      		<c:if test="${user.status==1}"><i class="layui-icon" style="color:#c2c2c2;" >&#xe617;</i> 禁用</c:if>
      </td>
      <td>
		    <div class="site-demo-button " >
		    	 <a href="userManagement/editUser/${user.id}"> <button class="layui-btn">编辑</button></a>
		      <button class="layui-btn" id="del" data-id="${user.id }"  >删除</button>
		    </div>
      </td>
    </tr>
  	</c:forEach>
  </tbody>
</table>
 <div  class="layui-input-inline"  style="width: 150px;" >
   <select  lay-filter="batch" >
     <option value="0">批量启用</option>
     <option value="1">批量禁用</option>
     <option value="2">批量删除</option>
   </select>
 </div>
<div class="layui-input-inline">
	<input type="button" id="exc" class="layui-btn" value="提交" />
</div>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	var val = "0";
	form.on('select(batch)',function (data){  //监听选中状态
		val=data.value
	})
	$("#exc").click(function(){
		 var child = $("table tbody input[type='checkbox']") ,idStr = "";
		 child.each(function(index, item){
			 if( item.checked ){
				 idStr = idStr+$(this).data("id")+",";
			 }
		 });
		 if(idStr != "" ){
			    if( val != '2'){  //批量更新用户状态
					updateStatus(idStr,val);
			    	
				} else{
					delUsers(idStr);
				}  
				updateHtml(child,val);  
		 }
	})
	
	function updateStatus(idStr,status){ //批量修改用户状态
		tool.load() ;
		  $.ajax({
			url: "userManagement/updateUserBitch",
			type: "POST",
			dataType: "JSON",
			data:{"idStr":idStr,"status":status},
			async: false,
			success:function(data){
				if(data.state >= 1 ){
					tool.info("成功修改"+data.state+"条状态");
					tool.unload() ;
				}
			}
		});    
	}
	function updateHtml(childHtml,val){ //动态修改html
		childHtml.each(function(index, item){
			 if( item.checked ){
				if(val != 2){ //批量修改状态
					var txt = '';
					var dom = $(this).parent().parent().find("#oper");
					dom.data("status",val);
					if(val == '0' ){ dom.html("<i class='layui-icon' style='color: #009688;'>&#xe617;</i> 启用"); }
					else{dom.html("<i class='layui-icon' style='color: #c2c2c2;'>&#xe617;</i> 禁用"); } 
				} else{
					$(this).parent().parent().remove();
				}
			 }
		 });
	}
	function delUsers(idStr){  //批量删除用户
		tool.load() ;
		$.ajax({
			url: "userManagement/deleteUsers",
			type: "POST",
			dataType: "JSON",
			data:{"idStr":idStr},
			async: false,
			success:function(data){
				if(data.num >= 1 ){
					tool.unload() ;
				} 
			}
		}); 
	}
	$("table tr td #del").click(function(){ //删除单条用户
		idStr = $(this).data("id") , ts = $(this);
		tool.load() ;
		$.ajax({
			url: "userManagement/deleteUsers",
			type: "POST",
			dataType: "JSON",
			data:{"idStr":idStr},
			async: false,
			success:function(data){
				if(data.num >= 1 ){
					ts.parent().parent().parent().parent().remove();
					tool.unload() ;
				} 
			}
		}); 
	})
})
</script>
