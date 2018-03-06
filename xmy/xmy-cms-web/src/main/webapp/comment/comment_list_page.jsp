<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
        <th><input type="checkbox" name="" value="0" id="ch" lay-skin="primary" lay-filter="allChoose" class="t-checkbox"></th>
        <th>ID</th>
        <th>订单id</th>
        <th>商品id</th>
        <th>用户id</th>
        <th>评论内容</th>
        <th>评论星级</th>
        <th>评论类型</th>
        <th>审核状态</th>
        <th>创建时间</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${commentList }" var="comment">
      <tr>
        <td><input type="checkbox"  value="${comment.id }" name="" lay-skin="primary" class="t-checkbox orderid"></td>
        <td>${comment.id }</td>
        <td>${comment.orderId }</td>
        <td>${comment.goodsId }</td>
        <td>${comment.userId }</td>
        <td>${comment.commentConten }</td>
        <td>${comment.commentStar }</td>
        <td>
        	<c:if test="${comment.commentType==0 }">订单评论</c:if>
        	<c:if test="${comment.commentType==1 }">商品评论</c:if>
        </td>
        <td>
        	<c:if test="${comment.commentStatus==0 }">审核通过</c:if>
        	<c:if test="${comment.commentStatus==1 }">待审核</c:if>
        	<c:if test="${comment.commentStatus==2 }">审核未通过</c:if>
        </td>
        <td><fmt:formatDate value="${comment.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
        <td>
        	<a href="comment/detail/${comment.id }"><button class="layui-btn">详情</button></a>
        	<c:if test="${comment.commentStatus==1 }">
				<a href="comment/update/${comment.id }/0"><button class="layui-btn">审核通过</button></a>
				<a href="comment/update/${comment.id }/1"><button class="layui-btn">审核不通过</button></a>
			</c:if>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

