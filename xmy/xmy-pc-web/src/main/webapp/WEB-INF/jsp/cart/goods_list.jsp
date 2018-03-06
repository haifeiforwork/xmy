<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table class="shoped shop-car">
<tr class="table-head">
     <td class="text-center"><input class="all" type="checkbox"></td>
     <td class="shoped-info"><span>全选</span>商品信息</td>
     <td class="text-center one-price">单价</td>
     <td class="text-center">购买数量</td>
     <td class="text-center">税费</td>
     <td class="text-center">总金额</td>
     <td class="text-center one-area">配送范围</td>
     <td class="text-center">积分</td>
     <td class="text-center">操作</td>
 </tr>
 <c:if test="${empty shoppingCart }">
 	<tr>
 		<td colspan="9" style="text-align: center;color:gay;">数据为空!</td>
 	</tr>
 </c:if>
 <c:forEach items="${shoppingCart }" var="cartGoods">
   <tr class="shoped-con">
       <td class="text-center"><div class="table-item"><input class="goods" value="${cartGoods.id }" type="checkbox"></div></td>
       <td class="shoped-info2">
           <div class="img-wrapa"><img src="${cartGoods.imgPath }" alt=""></div>
           <div class="info-des">
               <p class="info-des-title">${cartGoods.name }
               	<c:if test="${!empty cartGoods.presentGoods.name }">
               		<br/>
               		<p class="info-des-title" style="color: red;">赠品：${cartGoods.presentGoods.name }</p>
               	</c:if>
               </p>
           </div>
       </td>
       <td class="text-center">
           <div class="table-item">
               <p>
               <c:if test="${cartGoods.actPoints!=0 }">
               	${cartGoods.actPoints }积分
               </c:if>
               <c:if test="${cartGoods.actPoints==0 }">
               	¥<c:if test="${empty cartGoods.activityPrice }">${cartGoods.price }</c:if>
                <c:if test="${!empty cartGoods.activityPrice }">${cartGoods.activityPrice }</c:if>
               </c:if>
               </p>
               <c:if test="${!empty cartGoods.activityName }">
               <div class="c-price">
                   <button type="button" class="c-btn">${cartGoods.activityName }<i class="iconfont">&#xe63b;</i></button>
                   <div class="c-price-des">
                  	     ${cartGoods.activityName },每日<span>12 : 00</span>-次日<span>12 : 00</span>开抢
                   </div>
               </div>
               </c:if>
           </div>
       </td>
       <td class="text-center">
           <div class="table-item" value="${cartGoods.id }">
               <button type="button" class="crease">-</button>
               <input type="text" class="nuum" value="${cartGoods.cartNum }" maxlength="3">
               <button type="button" class="add">+</button>
           </div>
       </td>
       <td class="text-center"><div class="table-item"><s>¥ 0.00</s></div></td>
       <td class="text-center price"><div class="table-item">
       <c:if test="${cartGoods.actPoints==0 }">
       	${cartGoods.sumPrice }
       </c:if>
       <c:if test="${cartGoods.actPoints!=0 }">
       	${cartGoods.sumPoints }积分
       </c:if>
       </div></td>
       <td class="text-center">
        <div class="table-item">
        		<c:if test="${cartGoods.isDelivery==0 }">全国配送</c:if>
     				<c:if test="${cartGoods.isDelivery==1 }">重庆主城</c:if>
        </div>
       </td>
       <td class="text-center"><div class="table-item"><fmt:formatNumber value="${cartGoods.actPoints==0?cartGoods.sumPrice:0 }" pattern="#"/></div></td>
       <td class="option"><div class="table-item"><p value="${cartGoods.id }" tname="${cartGoods.categoryName }" class="bookMark">移入收藏夹</p><p value="${cartGoods.id }" class="delete">删除</p></div></td>
   </tr>
  </c:forEach>
</table>
 


