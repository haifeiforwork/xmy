<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加促销</title>
 <%@ include file="/commons/comm_css.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
 <%@ include file="/commons/common_layout.jsp"%>
 <div class="layui-tab layui-tab-brief layui-form" >
	<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
	<fieldset class="layui-elem-field">
 		<legend>添加专题促销</legend>
	  	<form class="layui-form"  method="post" id="activityForm">
			<div class="layui-form-item" style="width:50%;">
			    <label class="layui-form-label">专题名称<i style="color:#F00">*</i></label>
			    <div class="layui-input-block">
			      <input type="text" id="name" name="name" lay-verify="name" value="${param.name }" placeholder="单行输入" autocomplete="off"  class="layui-input">
			    </div>
			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">选择方式</label>
			    <div class="layui-input-inline" >
			      <select id="proType"  lay-filter="type" name="proType">
			        <option value="商品" >商品</option>
			        <option value="分类" >分类</option>
			        <option value="供应商">供应商</option>
			      </select>
			 	</div>
  			</div>
  			<div class="layui-form-item" id="code" style="display: none;">
			    <label class="layui-form-label">选择分类</label>
			    <div class="layui-input-inline" >
						<input type="hidden"  name="codeIds" id="codeIds" readonly="readonly" required  lay-verify="required" autocomplete="off" class="layui-input">
						<input type="text" style="width:400px;display:inline;" name="codeNames" id="codeNames" readonly="readonly" required  lay-verify="required" placeholder="请选择分类"  autocomplete="off" class="layui-input">
			 	</div>
  			</div>
  			<div class="layui-form-item" id="supplierId" style="display: none;">
			    <label class="layui-form-label">选择供应商</label>
			    <div class="layui-input-block" style="width: 50%">
					<input type="hidden"  name="supplierIds" id="supplierIds" readonly="readonly" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input my-input">
					<input type="text" style="width:400px;display:inline;" name="supplierNames" id="supplierNames" readonly="readonly" required  lay-verify="required" placeholder="请选择供应商" autocomplete="off" class="layui-input my-input">
				</div>
  			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">端口</label>
			    <div class="layui-input-block" id="selectPort">
			      <input type="checkbox" class="my-port" title="PC"  checked>
			      <input type="checkbox" class="my-port" title="WAP" checked>
			      <input type="checkbox"  class="my-port" title="APP" checked>
			    </div>
			    <input type="hidden" name="port" id="port" value="1,2,3,"/>
  			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">参与条件</label>
			    <div class="layui-input-block" id="selectJoinCondition">
			      <input type="checkbox"  value="1" class="joinCondition" title="会员" checked>
			      <input type="checkbox"  value="2" class="joinCondition" title="匿名">
			    </div>
			      <input type="hidden" name="joinCondition" id="joinCondition" value="1,"/> 
  			</div>
		    <div class="layui-inline">
			      <label class="layui-form-label">开始时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			        <input type="text"  name="beginTime" zfj-query="begin_time:ge" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})"  placeholder="活动开始时间" autocomplete="off" class="layui-input q-rule" >
			      </div>
		     </div>
			
			 <div class="layui-inline">
			      <label class="layui-form-label">结束时间<i style="color:#F00">*</i></label>
			      <div class="layui-input-inline">
			      <input type="text"  name="endTime" zfj-query="begin_time:ge" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})"  placeholder="活动开始时间" autocomplete="off" class="layui-input q-rule" >
			      </div>
		     </div>
			
			<div class="layui-form-item" style="width: 50%">
			    <label class="layui-form-label">水印图片</label>
			    <div class="layui-input-block">
			      <input type="text" name="imgPath" id="imgPath" lay-verify="imgPath" value="${param.imagePath }" autocomplete="off" placeholder="选择水印图片" class="layui-input" readonly="readonly">
			      <input type="hidden" name="imgId" id="imgId" value="">
			      <label id="choosePicture" ><a >请选择<i style="color:#F00">*</i></a></label>	
			    </div>
			</div>
		</form>
	</fieldset>
	
	<fieldset class="layui-elem-field" id="proGoods">
		<legend>促销商品</legend>
		<div>
			 <div class="layui-input-inline" style="margin-left: 85%;">
				<input type="button" class="layui-btn" id="addGoods" value="添加商品"  />
			 </div>
		</div><div id="tablediv" class="layui-form"  style="display: none;">
			<table class="layui-table" >
			 <colgroup>
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="150">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			    <col width="100">
			  </colgroup>
			  <thead>
			    <tr>
			      <th>商品编号</th>
			      <th>商品名称</th>
			      <th>卖价(元)</th>
			      <th>供货商</th>
			      <th>品牌</th>
			      <th>前台分类</th>
			      <th>促销单价</th>
			      <th>用户限购数量</th>
			      <th>总数量</th>
			      <th>交易完成量</th>
			      <th>操作</th>
			    </tr> 
			  </thead>
			  <tbody id="content"></tbody>
			</table>		
		</div>
	</fieldset>
	<input type="button" id="save" class="layui-btn" value="保存"/>
 	</div>
 </div>
 <%@ include file="/commons/buttom.jsp"%>
</div>			   
</body>
<script type="text/javascript" src="activity/promotion/js/promotion.js"></script>
</html>