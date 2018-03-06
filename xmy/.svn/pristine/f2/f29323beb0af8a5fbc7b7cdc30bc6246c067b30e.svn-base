<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<title>评论详情</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
<style type="text/css">
.layui-input-block{width: 400px}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this">评论详情</li>
		  </ul>
		  <div class="layui-tab-content" >
		  	<!-- 用户授权 -->
		    <div class="layui-tab-item layui-show">
		    	<form class="layui-form" action="sys/updateMenu">
		    	
		    		 <div class="layui-form-item">
					    <label class="layui-form-label">用户名称</label>
					    <div class="layui-input-block">
					      <input type="text" name="name" lay-verify="title" autocomplete="off" value="${commentMap.user.name }" class="layui-input">
					    </div>
					 </div>
		    		<div class="layui-form-item">
					    <label class="layui-form-label">商品名称</label>
					    <div class="layui-input-block">
					      <input type="text" name="des" lay-verify="title" autocomplete="off" value="${commentMap.goods.name }" class="layui-input">
					    </div>
					 </div>
					 
					 <div class="layui-form-item">
					    <label class="layui-form-label">订单编号</label>
					    <div class="layui-input-block">
					      <input type="text" name="des" lay-verify="title" autocomplete="off" value="${commentMap.order.no }" class="layui-input">
					    </div>
					 </div>
					 
					 <div class="layui-form-item">
					    <label class="layui-form-label">评论星级</label>
					    <div class="layui-input-block">
					      <input type="text" name="targetUrl" lay-verify="title" autocomplete="off" value="${commentMap.comment.commentStar }" class="layui-input">
					    </div>
					 </div>
					 
					 <div class="layui-form-item">
					    <label class="layui-form-label">评论图片</label>
					    <div class="layui-input-block">
					     <c:if test="${!empty commentMap.imageList}">
					     	<c:forEach items="${commentMap.imageList }" var="images">
					     		<img src="${images.imagePath }">
					    	</c:forEach>
					     </c:if>
					     <c:if test="${empty commentMap.imageList}">
					     	用户未上传图片
					     </c:if>
					    </div>
					 </div>
					 
					 <div class="layui-form-item">
					    <label class="layui-form-label">评论内容</label>
					    <div class="layui-input-block">
					      <textarea name="desc" class="layui-textarea">${commentMap.comment.commentConten }</textarea>
					    </div>
					 </div>
		    	</form>
		    </div>
		    <c:if test="${commentMap.comment.commentStatus == 1 }">
			   <a href="comment/update/${commentMap.comment.id }/0"><button class="layui-btn" lay-submit="" lay-filter="demo1" style="margin-left: 100px">审核通过</button></a>
			    <a href="comment/update/${commentMap.comment.id }/1"><button class="layui-btn" lay-submit="" lay-filter="demo1" style="margin-left: 100px">审核不通过</button></a>
		    </c:if>
		  </div>
		</div> 
  	</div>
  </div>
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
</div>
</body>
</html>