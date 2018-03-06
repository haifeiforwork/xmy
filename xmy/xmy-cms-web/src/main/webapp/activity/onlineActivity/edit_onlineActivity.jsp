<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加活动</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
	<form class="layui-form" action="onlineActivity/addOnlineActivity" method="post" id="activityForm">
	<fieldset class="layui-elem-field">
 		<legend>活动基本信息</legend>
			<div class="layui-form-item">
			    <label class="layui-form-label">活动名称<i style="color:#F00">*</i></label>
			    <div class="layui-input-block">
			      <input type="text" id="name" name="activityName" lay-verify="activityName" value="${data.activityName }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<input type="hidden" id="actId" name="id" value="${data.id }">
			<div class="layui-form-item">
		    <label class="layui-form-label">是否启用</label>
		    <div class="layui-input-block">
		    	 <c:if test="${data.status==0 }">
			     	 <input type="checkbox" checked="checked" name="open" class="chstatus" lay-skin="switch" lay-text="ON|OFF">
		    	 </c:if>
		    	  <c:if test="${data.status==1 }">
			     	 <input type="checkbox" name="close" class="chstatus" lay-skin="switch" lay-text="ON|OFF">
		    	 </c:if>
		    	 <c:if test="${empty data.status}">
			     	 <input type="checkbox" name="close" class="chstatus" lay-skin="switch" lay-text="ON|OFF">
		    	 </c:if>
		      	<input type="hidden" name="status" id="status" name="status"/>
		     </div>
		    </div>
			<div class="layui-form-item">
			    <label class="layui-form-label">新用户开始ID</label>
			    <div class="layui-input-block">
			      <input type="text" name="appNewUserid"  lay-verify="limitNum" value="${data.appNewUserid }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">赠送优惠卷ID</label>
			    <div class="layui-input-block">
			      <input type="text" name="presentCouponid"  lay-verify="limitNum" value="${data.presentCouponid }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">猜猜看送券ID</label>
			    <div class="layui-input-block">
			      <input type="text" name="presentCaicaikanCouponid"  lay-verify="limitNum" value="${data.presentCaicaikanCouponid }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">限购数量</label>
			    <div class="layui-input-block">
			      <input type="text" name="userLimitNum"  lay-verify="userLimitNum" value="${data.userLimitNum }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">每天参与订单总量</label>
			    <div class="layui-input-block">
			      <input type="text" name="dayLimitNum"  lay-verify="limitNum" value="${data.dayLimitNum }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">全国折扣</label>
			    <div class="layui-input-block">
			      <input type="text" name="qgDiscount"  lay-verify="limitNum" value="${data.qgDiscount }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">重庆折扣</label>
			    <div class="layui-input-block">
			      <input type="text" name="cqDiscount"  lay-verify="limitNum" value="${data.cqDiscount }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">最高优惠金额</label>
			    <div class="layui-input-block">
			      <input type="text" name="limitDiscountPrice"  lay-verify="limitNum" value="${data.limitDiscountPrice }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
	     <div class="layui-inline">
		      <label class="layui-form-label">全国开始时间<i style="color:#F00">*</i></label>
		      <div class="layui-input-inline">
		        <input type="text" id="beginTime" value="<fmt:formatDate value="${data.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="beginTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="begindate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
		      </div>
	     </div>
		 <div class="layui-inline">
		      <label class="layui-form-label">全国结束时间<i style="color:#F00">*</i></label>
		      <div class="layui-input-inline">
		        <input type="text" id="endTime" value="<fmt:formatDate value="${data.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="endTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="enddate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
		      </div>
	     </div>
	     <br>
	     <div class="layui-inline">
		      <label class="layui-form-label">重庆开始时间<i style="color:#F00">*</i></label>
		      <div class="layui-input-inline">
		        <input type="text" id="cqBeginTime" value="<fmt:formatDate value="${data.cqBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="cqBeginTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="enddate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
		      </div>
	     </div>
	     <div class="layui-inline">
		      <label class="layui-form-label">重庆结束时间<i style="color:#F00">*</i></label>
		      <div class="layui-input-inline">
		        <input type="text" id="cqEndTime" value="<fmt:formatDate value="${data.cqEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="cqEndTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="enddate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
		      </div>
	     </div>
	     <br>
	     <div class="layui-inline">
		      <label class="layui-form-label">送券开始时间<i style="color:#F00">*</i></label>
		      <div class="layui-input-inline">
		        <input type="text" id="presentCouponBeginTime" value="<fmt:formatDate value="${data.presentCouponBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="presentCouponBeginTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="enddate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
		      </div>
	     </div>
	     <div class="layui-inline">
		      <label class="layui-form-label">送券结束时间<i style="color:#F00">*</i></label>
		      <div class="layui-input-inline">
		        <input type="text" id="presentCouponEndTime" value="<fmt:formatDate value="${data.presentCouponEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="presentCouponEndTime" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" pattern="YYYY-MM-DD hh:mm:ss" lay-verify="enddate" placeholder="yyyy-mm-dd hh:mm" autocomplete="off" class="layui-input" >
		      </div>
	     </div>
	   	<div class="layui-form-item">
		    <label class="layui-form-label">活动描述</label>
		    <div class="layui-input-block">
		      <input type="text" style="width:40%;"  name="presentCouponDes"  value="${data.presentCouponDes }" placeholder="单行输入" autocomplete="off"  class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">订单活动备注</label>
		    <div class="layui-input-block">
		      <input type="text" style="width:40%;"  name="activityRemark"  value="${data.activityRemark }" placeholder="单行输入" autocomplete="off"  class="layui-input">
		    </div>
		</div>
	</fieldset>
	</form>
	<input type="submit" id="save" lay-submit=""  class="layui-btn" value="保存"  />
 	</div>
 </div>
 <%@ include file="/commons/buttom.jsp"%>
</div>			
</body>
<script type="text/javascript">
layui.use(["form","upload","tool","layer","tree","laydate"], function(){
	var form = layui.form();
	//自定义验证规则
	  form.verify({
		 username: function(value){
	      if(value.length < 1){
	        return '收货人名称不能为空';
	      }else if(value.length > 20){
	    	  return '收货人名称过长';
	      }
	    }
	    ,content: function(value){
	      layedit.sync(editIndex);
	    },deliveryId:function(value){
	    	if(value.trim() == ""){
	    		return '配送人不能为空';
	    	}
	    },kdniaoExpCode:function(value){
	    	if(value.trim() == ""){
	    		return '快递公司不能为空';
	    	}
	    },logisticsNo:function(value){
	    	if(value.trim() == ""){
	    		return '快递单号不能为空!';
	    	}
	    }
	    
	  });
	$(function($){
		$("#save").click(function(){
			if($(".chstatus")[0].checked){
				//启用
				$("#status").val(0);
			}else{
				//禁用
				$("#status").val(1);
			};
			$("#activityForm").submit();
		})
	})
});
//保存商品


</script>
</html>