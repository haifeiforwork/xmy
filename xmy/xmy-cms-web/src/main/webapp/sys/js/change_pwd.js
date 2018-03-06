layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate, form = layui.form();;
	//自定义验证规则
	  form.verify({
	    ok: function(value){
	      var oldpwd = $("#pwd").val();
	      if(value != oldpwd){
	        return '两次输入的新密码不一致！';
	      }
	    }
	  });  
}) ;