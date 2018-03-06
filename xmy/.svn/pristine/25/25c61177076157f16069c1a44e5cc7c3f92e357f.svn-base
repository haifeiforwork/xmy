<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>富文本编辑测试</title>
<link rel="stylesheet" href="/commons/layui/css/layui.css">
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
	  <legend>完整功能（没错，目前工具栏只有这么多）</legend>
	</fieldset>
	<textarea class="layui-textarea" id="LAY_demo1" style="display: none">  
		  把编辑器的初始内容放在这textarea即可
	</textarea>
	<div class="site-demo-button" style="margin-top: 20px;">
	  <button class="layui-btn site-demo-layedit" data-type="content">获取编辑器内容</button>
	  <button class="layui-btn site-demo-layedit" data-type="text">获取编辑器纯文本内容</button>
	  <button class="layui-btn site-demo-layedit" data-type="selection">获取编辑器选中内容</button>
	</div>
</body>
<script type="text/javascript" src="/commons/layui/layui.js"></script>
<script type="text/javascript">
	layui.use('layedit', function(){
		  var layedit = layui.layedit
		  ,$ = layui.jquery;
		  
		  //构建一个默认的编辑器
		  var index = layedit.build('LAY_demo1');
		  
		  //编辑器外部操作
		  var active = {
		    content: function(){
		      alert(layedit.getContent(index)); //获取编辑器内容
		    }
		    ,text: function(){
		      alert(layedit.getText(index)); //获取编辑器纯文本内容
		    }
		    ,selection: function(){
		      alert(layedit.getSelection(index));
		    }
		  };
		  
		  $('.site-demo-layedit').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
		  
		  layedit.set({
			  uploadImage: {
			  url: '' //接口url
			  ,type: '' //默认post
			  ,success:function (res){
		    	  alert(1111)
		      }
			  }
		});
	});
</script>
</html>