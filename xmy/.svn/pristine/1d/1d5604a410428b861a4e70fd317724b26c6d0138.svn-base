<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="layui-table">
  <thead>
    <tr>
    	<!--  <th><input type="checkbox" name="selectAll" lay-skin="primary" lay-filter="allChoose"></th> -->
       
        <th>ID</th>
        <th>优惠券名称</th>
        <th>金额（元）</th>
        <th>数量</th>
        <th>已领取的数量</th>
        <th>使用范围</th>
        <th>使用有效期</th>
        <th>状态</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach items="${couponList }" var="coupon">
      <tr>
        <td data-id="${coupon.id }">${coupon.id }</td>
        <td>${coupon.name }</td>
        <td>${coupon.couponValue }</td>
        <td>${coupon.couponCount }</td>
        <td>${coupon.useNum }</td>
        <td>
        	<c:if test="${coupon.useRange==1 }">全场通用</c:if>
        	<c:if test="${coupon.useRange==2 }">指定分类</c:if>
        	<c:if test="${coupon.useRange==3 }">限定商品</c:if>
        	<c:if test="${coupon.useRange==4 }">排除商品</c:if>
        </td>
        <td>
        		<fmt:formatDate value="${coupon.effectiveStartTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
        			至
        		<fmt:formatDate value="${coupon.effectiveEndTime }" pattern="yyyy-MM-dd hh:mm:ss"/>
        		
        </td>
         <td>
	        <div class="layui-form-item">
			  <div class="layui-input-block">
	        		<span class="${coupon.status==0 ? 'switchOn':'switchOff'} "> ${coupon.status==0 ? '已启用':'已禁用' } </span> 
	       	  </div>
	       </div>	
        </td>
        <td>
<%--         		<a href="coupon/editCoupon/${coupon.id}"><button data-id="${coupon.id }" class="layui-btn">编辑</button></a> --%>
            <a href="javascript:void(0);"><button data-id="${coupon.id }" class="layui-btn  layui-btn-danger append-more-coupon-action-btn" >追加</button></a>
        	<a href="javascript:void(0);"><button data-id="${coupon.id }" class="layui-btn  layui-btn-danger btn_export" >导出</button></a>
        	<a href="javascript:void(0);"><button data-id="${coupon.id }" class="layui-btn  layui-btn-danger" id="btn_del">删除</button></a> 

        	<form action="coupon/ExportCoupon" id="export_coupon_form_${coupon.id }" style="display: none;" >
        	    <input value="${coupon.id }" name="id" />
        	</form>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
