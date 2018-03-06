layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate , form = layui.form();
	var len=0;
	//表单简单验证
	form.verify({
		name: function(value){
	      if(value.length < 1){
	    	  len=value.length;
	    	  return '用户名不能为空'
	      }else{
	    	  if($.trim(value) != value){
		    	  return '用户名不能有空格'
		      }
	      }
	      
	    }
	  	,password: function(value){
	  		if(value.length < 1){
	  			return '密码不能为空'
	  		}else{
	  			if($.trim(value) != value){
			    	  return '密码含有空格'
			      }
	  		}
	  	}
	});
}) ;