<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
     <th>活动名称</th>
     <th>全国开始时间</th>
     <th>全国开始时间</th>
     <th>重庆开始时间</th>
     <th>重庆结束时间</th>
     <th>送券开始时间</th>
     <th>送券结束时间</th>
     <th>用户限购数量</th>
     <th>每天订单总量</th>
     <th>赠送的优惠卷ID</th>
     <th>猜猜看优惠卷ID</th>
     <th>新用户开始Id</th>
     <th>全国折扣</th>
     <th>重庆折扣</th>
     <th>最多打折金额</th>
     <th>赠送优惠卷备注</th>
     <th>活动订单备注</th>
     <th>活动状态</th>
     <th>操作</th>
   </tr> 
 </thead>
 <tbody>
   <c:forEach items="${alist }" var="item">
    <tr>
    	 <td>${item.id }</td>
	   	 <td>${item.activityName }</td>
	     <td><fmt:formatDate value="${item.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td><fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td><fmt:formatDate value="${item.cqBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td><fmt:formatDate value="${item.cqEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td><fmt:formatDate value="${item.presentCouponBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td><fmt:formatDate value="${item.presentCouponEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	     <td>${item.userLimitNum }</td>
	     <td>${item.dayLimitNum }</td>
	     <td>${item.presentCouponid }</td>
	     <td>${item.presentCaicaikanCouponid }</td>
	     <td>${item.appNewUserid }</td>
	     <td>${item.qgDiscount }</td>
	     <td>${item.cqDiscount }</td>
	     <td>${item.limitDiscountPrice }</td>
	     <td>${item.presentCouponDes }</td>
	     <td>${item.activityRemark }</td>
	     <td>
			<c:if test="${item.status==0 }"><i class="layui-icon" style="font-size: 12px; color: #red;">进行中</i></c:if>
			<c:if test="${item.status==1 }"><i class="layui-icon" style="font-size: 12px; color: #009688;">未进行</i></c:if>
		</td>
		<td>
			<button id="edit" data-id="${item.id }" class="layui-btn layui-btn-radius">编辑 </button>
		</td>
		<td>
			<button data-id="${item.id }" class="layui-btn question">添加问题</button>
		</td>
     </tr>
   </c:forEach>
  </tbody>
</table>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager,form = layui.form() ;
	
	//编辑活动
	$("tbody tr #edit").click(function(){
		var id = $(this).data("id");
		window.location.href="onlineActivity/updateActvity?id="+id;
	})
	
	$(".question").click(function(){
		var id = $(this).data("id");
		window.location.href="onlineActivity/questionList?id="+id;
	})
}) 

</script>
