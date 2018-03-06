<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%> 
<%@ include file="/commons/comm_css.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>水印图片列表</title>
</head>
<body>

<div class="layui-layout layui-layout-admin">
	 <%@ include file="/commons/common_layout.jsp"%>
		 <div class="layui-tab layui-tab-brief layui-form" >
		 <div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
				<div class="layui-tab-item layui-show ">
				<blockquote class="layui-elem-quote">请上传PNG水印图片</blockquote>
					<form action="waterMark/addWaterImage" method="post" id="form">
						<div class="layui-input-inline">
						    <label class="layui-form-label">图标</label>
							<div id="imgdiv" class="layui-input-block" style="width: 100px;" ></div>
							<div id="container"style="margin-left: 100px;width: 500px;">
								<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
								<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
								<input type="hidden" name="path"  id="path" lay-verify="path" value="${param.path }">
							</div>
							<pre id="console"></pre>
						</div>
						
						<div class="layui-input-inline" style="margin-left: 80px;">
						 <button class="layui-btn" lay-submit="" lay-filter="form"  >确认提交</button> 
						 </div>
					</form>
				</div>
				<hr style="margin-top: 150px;">
					<div id="mytable" style="margin-top:30px;"></div>
					<div id="mypager"></div>
		</div>
		</div>
<%@ include file="/commons/buttom.jsp"%>
</div>	
</body>
<script type="text/javascript" src="native/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="goods/watermark/js/watermarkupload.js"></script>
<script type="text/javascript">
layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	/* console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ; */
	pager.load({
		url:"waterMark/waterMarkList",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render();
	});
	
	form.verify({ 
		path:function(value,item){
			 if(!value){
				return "请先上传图片" ;
			} 
		} 
	}) ; 

	//监听提交
	  form.on('submit(form)', function(data){
			$("#form").submit();
	  });
});

</script>

</html>