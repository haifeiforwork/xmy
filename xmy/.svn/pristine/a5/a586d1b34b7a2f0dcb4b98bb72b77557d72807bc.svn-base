<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- iframe 内层，需要自带样式 -->
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
.layui-input-block{width: 200px}
</style>

<div class="layui-tab-item layui-show">
		    <form id="myForm">
		           <div class="layui-input-inline" style="display: none"  >
				   		<label class="layui-form-label" style="width:120px;text-align:left;left: 10px;"></label>
				   		 <div class="layui-input-block" style="margin-left: 150px;">
					      	<input type="text" name="userid" class=" layui-input " value="${userid}"  style="width:180px;"    >
					     </div>  	
				   </div>
				   <div class="layui-input-inline">
				   		<label class="layui-form-label" style="width:120px;text-align:left;left: 10px;">标签搜索:</label>
				   		 <div class="layui-input-block" style="margin-left: 150px;">
					      	<input type="text" name="search" class=" layui-input " value="${seach}"   style="width:180px;"  >
					     </div>  	
				   </div>
				    <div class="layui-input-inline" style="width:200px;left:150px;">
				    	<input type="submit" class="layui-btn btn-submit" id="btn_submit" value="查询"  />
						<input type="button" class="layui-btn btn-reset" id="reset" value="重置"  />
				    </div>
				</form> 
		</div>

<table class="layui-table" lay-skin="line">
 <thead>
   <tr>
  	 <!-- <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> -->

     <th>推送标签</th>
     <th>创建时间</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
 	<c:forEach items="${labels }" var="label">
	   <tr>
	    <%--  <th><input type="checkbox" name="" value="${pcGoods.id }" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> --%>
	     <td>${label.label }</td>
	     <td><fmt:formatDate value="${label.createTimestamp }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td>
	         <button class="layui-btn push-label-user-add-btn" data-labelid=${label.id }  data-userid="${userid}"　>添加</button><!-- 给用户添加标签 -->
			　<%-- <button class="layui-btn push-label-modify-btn" data-labelid=${label.id } data-labelname=${label.label } lay-submit=""　lay-filter="" >修改</button>
	     　　　　<button class="layui-btn push-label-del-btn" data-labelid=${label.id }　>删除</button> --%>
	     </td>
	   </tr>
   </c:forEach>
 </tbody>
</table>
<!-- 底部 JS-->
<%@ include file="/commons/comm_footer_js.jsp"%>
 <script type="text/javascript" src="push/js/push_user_label_list_add_page.js"></script>
