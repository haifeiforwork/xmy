<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>推送管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
.layui-input-block{width: 200px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief layui-form" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  	<i class="layui-icon" style="font-size: 20px; color: #009688;">推送管理</i>
 	 		<hr>
  		<div class="layui-tab-item layui-show">
			<form id="myForm">
			
				<!-- <input type="text" class="q-rule" zfj-query="payment_name:cn" value="${params.payment_name}" name="payment_name"/>
				<input type="submit" value="提交"/> -->
				
				<%-- <div class="layui-input-inline">
			      <select name="type" lay-filter="type" class="q-rule" zfj-query="type:eq" >
			        <option value="" <c:if test="${params.type == 0}">selected="selected"</c:if>>设置类型</option>
			        <option value="1" <c:if test="${params.type == 1}">selected="selected"</c:if>>热卖商品</option>
			        <option value="2" <c:if test="${params.type == 2}">selected="selected"</c:if>>新品上市</option>
			        <option value="3" <c:if test="${params.type == 3}">selected="selected"</c:if>>热销商品</option>
			      </select>
			    </div> --%>
			    <!-- <input type="submit" value="查询" class="layui-btn btn-submit"/>
			    <input type="reset" value="清空" id="clear_btn" class="layui-btn"/> -->
			</form>
		</div>
		        
					<div class="layui-input-inline">
						<button class="layui-btn" id="btnAdd" lay-submit="" lay-filter="demo2">添加推送</button>
						<!-- <button class="layui-btn" id="btnDel" lay-submit="" lay-filter="demo2">删除</button> -->
					</div>
					<!-- <div class="layui-input-inline">
					    <button class="layui-btn" id="btn-push-now" lay-submit="" lay-filter="demo2">立即推送</button>
					</div> -->
				
				<div id="mytable" class="layui-form">
				<table class="layui-table" lay-skin="line">
					 <thead>
					   <tr>
					  	 <!-- <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> -->
					     <!-- <th>jobName</th>
					     <th>triggerName</th> -->
					     <th>定时推送时间(待推送)</th>
					     <th>标签</th>
					     <th>推送内容</th>
					     <th>操作</th>
					   </tr> 
					 </thead>
					 <tbody>
					 	<c:forEach items="${pushs }" var="push">
						   <tr>
						    <%--  <th><input type="checkbox" name="" value="${pcGoods.id }" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th> --%>
						     <td style="display: none;" >${push.jobName }</td>
						     <td style="display: none;" >${push.triggerName }</td>
						     <td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss"  value = "${push.time}" /></td>
						     <td>
						         <c:forEach var="labelid" items="${push.extData['labelids']}" >
						             
						             <c:forEach items="${labels }" var="label" >
						                  <c:if test="${labelid eq label.id }">
						                      <button class="layui-btn layui-btn-mini " data-labelid="${label.id }"  >${label.label }</button>
						                  </c:if>
						              </c:forEach>
						         </c:forEach>
						     </td>
						     <td>${push.extData['msg']}</td>
						     <td><button class="layui-btn del-btn" data-jobName="${push.jobName }" data-triggerName="${push.triggerName }" >删除</button></td>
						   </tr>
					   </c:forEach>
					 </tbody>
					</table>
					</div>
				
	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="push/js/push_list.js"></script>
</body>
</html>