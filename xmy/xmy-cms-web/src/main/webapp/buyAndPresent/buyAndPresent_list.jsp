<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>买即赠管理</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="buyAndPresent/css/style.css"/>

<body>
<div class="layui-layout layui-layout-admin">
  <!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
  <div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
  	<ul class="layui-tab-title site-title">
      <li  class="layui-this">买即赠列表</li>
      <li>添加买即赠促销</li>
    </ul>
  	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
  		<div class="layui-tab-item  layui-show">
	  			<div id="mytable" class="layui-form"></div>
				<div id="mypager"></div>
				<input type="hidden" id="pageCount" value="${pageCount }" /> 
		</div>
  		
  		<div class="layui-tab-item">
	  		<div style="width:100%">
	  			<div style="background:#F2F2F2;height:40px;line-height:40px;">
	  				<p style="font-size:1.3em;margin-left:20px;">基本信息</p>
	  			</div>
	  			<div>
	  				<form class="layui-form" action="buyAndPresent/buyAndPresent/addSave" method="POST">
	  					 <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">促销名称</label>
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
								    <div class="layui-inline">
									    <label class="layui-form-label">促销时间段</label>
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" name="startTime" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
									    </div>
									    <div class="layui-form-mid">-</div>
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" name="endTime" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
									    </div>
									</div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">水印图片</label>
						    <div class="layui-input-block" id="watermarkImgDiv">
						      <!-- <input type="file" name="couponImg"  required  placeholder="" autocomplete="off" class="layui-input my-input"> -->
						       <input type="text" style="width:50%;" name="watermarkImg" readonly="readonly" id="watermarkImg"  class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">赠送数量</label>
						    <div class="layui-input-block">
						      <input type="number" style="width:50%;" name="giftCount"  required  lay-verify="number" placeholder="" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">详情页地址</label>
						    <div class="layui-input-block">
						      <input type="text" style="width:50%;" name="activityDescUrl"  required  placeholder="" autocomplete="off" class="layui-input my-input">
						    </div>
						  </div>
						   <div class="layui-form-item my-form-item">
						    <label class="layui-form-label">促销备注</label>
						    <div class="layui-input-block" style="width:50%;">
						   		 <textarea class="layui-textarea" id="promotionMark" name="promotionMark" style="display: none;">  
								 </textarea>
						    </div>
						  </div>
						 
						  <div style="background:#F2F2F2;height:40px;line-height:40px;">
					  				<p style="font-size:1.3em;margin-left:20px;">促销商品</p>
					  	  </div>
						
						  <div class="layui-form-item my-form-item">
						    <label class="layui-form-label"><button type="button" id="btn_addMainGoods" class="layui-btn layui-btn-normal">选择商品</button></label>
						    <div class="layui-input-block">
						    	<input type="hidden"  name="mainGoodsIds" id="mainGoodsIds" readonly="readonly" required  lay-verify="required" autocomplete="off" class="layui-input">
						      	<div id="selectMainGoods">
						      		<table class="layui-table"  >
										 <thead>
										   <tr>
										     <th>序号</th>
										     <th>商品编号</th>
										     <th>商品名称</th>
										     <th>二级分类</th>
										     <th>卖价（元）</th>
										     <th>供货商</th>
										     <th>品牌</th>
										     <th>操作</th>
										   </tr> 
										 </thead>
										 <tbody id="selectMainGoodsTbody">
										  		<tr><td colspan="8">暂时没有选中商品</td></tr>
										  </tbody>
									</table>
						      	</div>
						    </div>
						  </div>
						
						  <div style="width:100%">
					  			<div style="background:#F2F2F2;height:40px;line-height:40px;">
					  				<p style="font-size:1.3em;margin-left:20px;">促销策略&赠品</p>
					  			</div>
					  			  <div class="layui-form-item my-form-item">
								    <label class="layui-form-label">是否叠加</label>
								    <div class="layui-input-block">
								       <input type="radio" name="isSuperposition" value="1" title="叠加"  checked >
									   <input type="radio" name="isSuperposition" value="2" title="不叠加"   >
								    </div>
								  </div>
					  			 <div class="layui-form-item my-form-item">
								     <label class="layui-form-label"><button type="button" id="btn_addGiftGoods" class="layui-btn layui-btn-normal">选择赠品</button></label>
									    <div class="layui-input-block">
									    	<input type="hidden"  name="giftGoodsIds" id="giftGoodsIds" readonly="readonly" required  lay-verify="required" autocomplete="off" class="layui-input">
									      	<div id="selectGiftGoods">
									      		<table class="layui-table"  >
													 <thead>
													   <tr>
													     <th>序号</th>
													     <th>商品编号</th>
													     <th>商品名称</th>
													     <th>二级分类</th>
													     <th>卖价（元）</th>
													     <th>供货商</th>
													     <th>品牌</th>
													     <th>操作</th>
													   </tr> 
													 </thead>
													 <tbody id="selectGiftGoodsTbody">
													  		<tr><td colspan="8">暂时没有选中赠品</td></tr>
													  </tbody>
												</table>
									      	</div>
									    </div>
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
  <script type="text/javascript" src="buyAndPresent/js/buyAndPresent.js"></script>
  <script type="text/javascript">
  layui.use(["pager","form","upload"],function(){
		//选择水印图片按钮
	  $("#watermarkImgDiv").click(function(){
	  	var index = layer.open({
	  		   type: 2,
	  		   title: '请选择',
	  		   shadeClose: true,
	  		   area: ['30%', '80%'],
	  		   content: 'waterMark/chooseWatermarkList',
	  		   btn: ['确定', '取消'],
	  		   btnAlign: 'c',
	  		   yes:function(index, layero){  //获取选择分类的值
	  			   var tt = layer.getChildFrame('body', index).find("table tbody tr.choose");
	  			   $("#watermarkImg").val(tt.data("path"));
	  			   layer.close(index); 
	  		   },
	  		   btn2:function(index, layero){
	  			   layer.close(index); 
	  		   }
	  	})
	  })
})

  </script>
</div>
</body>
</html>