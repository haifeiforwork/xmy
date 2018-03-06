<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>优惠券编辑</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="coupon/css/style.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<ul class="layui-tab-title site-title">
      <li >优惠卷列表</li>
      <li class="layui-this">编辑优惠卷</li>
    </ul>
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  		<div class="layui-tab-item ">
	  			<div id="mytable" class="layui-form"></div>
				<div id="mypager"></div>
				<input type="hidden" id="pageCount" value="${pageCount }" />
		</div>
  		
  		<div class="layui-tab-item layui-show">
	  		<div style="width:100%">
	  			<div style="background:#F2F2F2;height:40px;line-height:40px;">
	  				<p style="font-size:1.3em;margin-left:20px;">优惠券基础信息</p>
	  			</div>
	  			<div>
	  				<form class="layui-form" action="coupon/editSaveCoupon" method="POST">
	  					 <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券名称</label>
						    <div class="layui-input-block">
						      <input type="hidden" name="id"  required  lay-verify="required" value="${coupon.id }" autocomplete="off" class="layui-input my-input">
						      <input type="text" name="name"  required  lay-verify="required" value="${coupon.name }" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						   <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">是否启用</label>
						    <div class="layui-input-block">
						       <input type="radio" name="status" value="0" title="启用"  <c:if test="${coupon.status==0 }">checked</c:if> >
							   <input type="radio" name="status" value="1" title="禁用"  <c:if test="${coupon.status==1 }">checked</c:if> >
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券数量</label>
						    <div class="layui-input-block">
						      <input type="number" name="couponCount"  required  lay-verify="number" value="${coupon.couponCount }" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券面值</label>
						    <div class="layui-input-block">
						      <input type="text" name="couponValue"  required  value="${coupon.couponValue }" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">满多少减</label>
						    <div class="layui-input-block">
						      <input type="text" name="quota"  required  value="${coupon.quota }" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券图片</label>
						    <div class="layui-input-block">
						      <!-- <input type="file" name="couponImg"  required  placeholder="" autocomplete="off" class="layui-input my-input"> -->
						       <input type="text" name="couponImg" value="${coupon.couponImg }"  class="layui-input my-input">
						    </div>
						  </div>
						   <div class="layui-form-item my-form-item">
								    <div class="layui-inline">
									    <label class="layui-form-label">有效时间</label>
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" name="effectiveStartTime" autocomplete="off" class="layui-input" value="<fmt:formatDate value='${coupon.effectiveStartTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly="readonly">
									    </div>
									    <div class="layui-form-mid">-</div>
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" name="effectiveEndTime" autocomplete="off" class="layui-input" value="<fmt:formatDate value='${coupon.effectiveEndTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly="readonly">
									    </div>
									</div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">使用策略一</label>
						    <div class="layui-input-block">
						       <input type="radio" name="useStrategy" value="1" title="一笔交易仅使用一张优惠券"  <c:if test="${coupon.useStrategy==1 }">checked</c:if> >
							   <input type="radio" name="useStrategy" value="2" title="一笔交易可使用多张优惠券"  <c:if test="${coupon.useStrategy==2 }">checked</c:if>>
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">使用策略二</label>
						    <div class="layui-input-block">
						       <input type="radio" name="useWithOthers" value="1" title="不能和其他类型券一起使用"  <c:if test="${coupon.useWithOthers==1 }">checked</c:if> >
							   <input type="radio" name="useWithOthers" value="2" title="能和其他类型券一起使用"   <c:if test="${coupon.useWithOthers==2 }">checked</c:if>>
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券介绍</label>
						    <div class="layui-input-block" style="width:600px;">
						     	<textarea class="layui-textarea" id="des" name="des"  style="display: none;">  
									${coupon.des }
								</textarea>
						     </div>
						  </div>
						  <div style="width:100%">
					  			<div style="background:#F2F2F2;height:40px;line-height:40px;">
					  				<p style="font-size:1.3em;margin-left:20px;">优惠券扩展信息</p>
					  			</div>
					  			 <div class="layui-form-item my-form-item">
								    <label class="layui-form-label">供应商</label>
								    <div class="layui-input-block">
								      <input type="hidden"  name="supplierIds" id="supplierIds" value="${coupon.supplierIds }" readonly="readonly" required  lay-verify="required"  class="layui-input my-input">
								      <input type="text" id="supplierNames" readonly="readonly" value="${supplierNames }" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input my-input">
								    </div>
								 </div>
					  	  </div>
					  	  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">使用范围</label>
						    <div class="layui-input-block" id="btnClassify" >
						       <input type="radio" name="useRange" value="1" title="全场通用"  <c:if test="${coupon.useRange==1 }">checked</c:if> >
							   <input type="radio" name="useRange" value="2" title="分类使用"  <c:if test="${coupon.useRange==2 }">checked</c:if> >
							   <input type="radio" name="useRange" value="3" title="限定商品"  <c:if test="${coupon.useRange==3 }">checked</c:if> >
							   <input type="radio" name="useRange" value="4" title="排除商品"  <c:if test="${coupon.useRange==4 }">checked</c:if> >
						    </div>
						  </div>
						   <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">详情</label>
						    <div class="layui-input-block">
						      <input type="hidden"  name="useRangeIds" id="useRangeIds" value="${coupon.useRangeIds }" readonly="readonly" required  lay-verify="required" autocomplete="off" class="layui-input">
							  <input type="text" style="width:400px;display:inline;" id="useRangeNames" value="${useRangeNames }" readonly="readonly" required  lay-verify="required" placeholder="请选择上面的使用范围，并按照需求选择"  autocomplete="off" class="layui-input">
							  <button type="button" id="btnCleanUseRange" class="layui-btn">清空</button>
						    </div>
						    <div id="couponGoods2">
						  			 <c:if test="${not empty goodsData }">
										    <div id="selectCouponGoods">
												<table class="layui-table" >
													 <thead>
													   <tr>
													     <th>序号</th>
													     <th>商品编号</th>
													     <th>商品名称</th>
													     <th>二级分类</th>
													     <th>卖价（元）</th>
													     <th>供货商</th>
													     <th>品牌</th>
													   </tr> 
													 </thead>
													  <tbody id="my-tbody">
													  		<c:forEach items="${goodsData }" var="goods">
													  				 <tr>
																	   	 <td>${goods.id }</td> 
																	   	 <td class="gCode">${goods.code }</td> 
																	   	 <td class="gName">${goods.name }</td> 
																	   	 <td>${goods.categoryName }</td> 
																	   	 <td>${goods.price }</td> 
																	   	 <td>${goods.supplierName }</td> 
																	   	 <td>${goods.brandName }</td> 
																     </tr>
													  		</c:forEach>
													  </tbody>
												</table>
											</div>
						 			 </c:if>
						  	</div>
						   
						  </div>
						  <div class="layui-form-item my-form-item">
						    	<button type="submit" class="layui-btn">提交</button>
						    	<!-- <button type="reset" class="layui-btn layui-btn-danger">清空</button> -->
						  </div>
	  				</form>
	  			</div>
	  		</div>
		</div>
  	</div>
  </div>
 
  <!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript" src="coupon/js/coupon.js"></script>
    <script type="text/javascript">
  layui.use(["pager","form","upload"],function(){
	  var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ;
	  form=layui.form();
	  pager.load({
			url:"coupon/couponList",
			sort:"id desc",
			selector:"#myForm"
		},function(data){
			$("#mytable").html(data) ;
			form.render();
		}) ;
})

  </script>
</div>
</body>
</html>