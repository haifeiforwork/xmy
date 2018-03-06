<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>分页测试</title>
<link rel="stylesheet" href="/commons/layui/css/layui.css">
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
 	 	<legend>分页测试</legend>
	</fieldset>
	<div id="demo5"></div>
</body>
<script type="text/javascript" src="/commons/layui/layui.js"></script>
<script type="text/javascript">
	var currpage="${page}";
	layui.use(['laypage', 'layer'], function(){
	  var laypage = layui.laypage
	  ,layer = layui.layer;
	  laypage({
		    cont: 'demo5'
		    ,pages: 100
		    ,curr:currpage
		    ,jump: function (obj, first) {//触发分页后的回调
                if (!first) {//点击跳页触发函数自身，并传递当前页：obj.curr
                	window.location.href="/test/testLayPage?page="+obj.curr;
                }
            }
		  });
	})
</script>
</html>