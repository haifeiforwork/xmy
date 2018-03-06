<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>优惠券管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<style type="text/css">
.switchOn{
	display:inline-block;
	color:#FFF;
	height:20px;
	width:60px;
	background:#009688;
	text-align: center;
}
.switchOff{
	display:inline-block;
	color:#FFF;
	height:20px;
	width:60px;
	background:#FF5722;
	text-align: center;
}
</style>
<link rel="stylesheet" href="coupon/css/style.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<ul class="layui-tab-title site-title">
      <li class="layui-this">优惠卷列表</li>
      <li >添加优惠卷</li>
    </ul>
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  		<div class="layui-tab-item layui-show">
	  			<div id="mytable" class="layui-form"></div>
				<div id="mypager"></div>
				<input type="hidden" id="pageCount" value="${pageCount }" />
		</div>
  		
  		<div class="layui-tab-item ">
	  		<div style="width:100%">
	  			<div style="background:#F2F2F2;height:40px;line-height:40px;">
	  				<p style="font-size:1.3em;margin-left:20px;">优惠券基础信息</p>
	  			</div>
	  			<div>
	  				<form class="layui-form" action="coupon/saveCoupon" method="POST">
	  					 <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券名称</label>
						    <div class="layui-input-block">
						      <input type="text" name="name"  required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">是否启用</label>
						    <div class="layui-input-block">
						       <input type="radio" name="status" value="0" title="启用"  checked >
							   <input type="radio" name="status" value="1" title="禁用"   >
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券数量</label>
						    <div class="layui-input-block">
						      <input type="number" name="couponCount"  required  lay-verify="number" placeholder="" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券面值</label>
						    <div class="layui-input-block">
						      <input type="text" name="couponValue"  required  placeholder="" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						   <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">满多少减</label>
						    <div class="layui-input-block">
						      <input type="text" name="quota"  required  placeholder="" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券图片</label>
						    <div class="layui-input-block">
						      <!-- <input type="file" name="couponImg"  required  placeholder="" autocomplete="off" class="layui-input my-input"> -->
						       <input type="text" name="couponImg" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">是否在领券中心显示</label>
						    <div class="layui-input-block">
						       <input type="radio" name="showInCouponCenter" value="0" title="不显示"  checked >
							   <input type="radio" name="showInCouponCenter" value="1" title="显示"   >
						    </div>
						  </div>
						  
						   <div class="layui-form-item my-form-item">
								    <div class="layui-inline">
									    <label class="layui-form-label">有效时间</label>
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" name="effectiveStartTime" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly="readonly">
									    </div>
									    <div class="layui-form-mid">-</div>
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" name="effectiveEndTime" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly="readonly">
									    </div>
									</div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">使用策略一</label>
						    <div class="layui-input-block">
						       <input type="radio" name="useStrategy" value="1" title="一笔交易仅使用一张优惠券"  checked >
							   <input type="radio" name="useStrategy" value="2" title="一笔交易可使用多张优惠券"   >
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">使用策略二</label>
						    <div class="layui-input-block">
						       <input type="radio" name="useWithOthers" value="1" title="不能和其他类型券一起使用"  checked >
							   <input type="radio" name="useWithOthers" value="2" title="能和其他类型券一起使用"   >
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">优惠券介绍</label>
						    <div class="layui-input-block" style="width:600px;">
						     	<textarea class="layui-textarea" id="des" name="des" style="display: none;">  
									请输入优惠券介绍
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
								      <input type="hidden"  name="supplierIds" id="supplierIds" readonly="readonly" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input my-input">
								      <input type="text" id="supplierNames" readonly="readonly" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input my-input">
								    </div>
								 </div>
					  	  </div>
					  	  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">使用范围</label>
						    <div class="layui-input-block" id="btnClassify" >
						       <input type="radio" name="useRange" value="1" title="全场通用"  checked >
							   <input type="radio" name="useRange" value="2" title="分类使用"   >
							   <input type="radio" name="useRange" value="3" title="限定商品"   >
							   <input type="radio" name="useRange" value="4" title="排除商品"   >
						    </div>
						  </div>
						   <div class="layui-form-item my-form-item">
						    	<div id="couponGoods">
						    			<label class="layui-form-label">详情</label>
									    <div class="layui-input-block">
									      <input type="hidden"  name="useRangeIds" id="useRangeIds" readonly="readonly" required  lay-verify="required" autocomplete="off" class="layui-input">
										  <input type="text" style="width:400px;display:inline;" id="useRangeNames" readonly="readonly" required  lay-verify="required" placeholder="请选择上面的使用范围，并按照需求选择"  autocomplete="off" class="layui-input">
										<!--   <button type="button" id="btnCleanUseRange" class="layui-btn">清空</button> -->
									    </div>
						    	</div>
						    	<div id="couponGoods2">
						  			
						  		</div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    	<button type="submit" class="layui-btn">提交</button>
						    	<button type="reset" class="layui-btn layui-btn-danger">清空</button>
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
	  
	  //导出优惠劵
	  $("body").on("click",".btn_export",function(){
	      var couponid = $(this).data("id");
	      console.log(couponid);
	      $("#export_coupon_form_"+couponid).submit();
	  });
})

  </script>
</div>
</body>
</html>