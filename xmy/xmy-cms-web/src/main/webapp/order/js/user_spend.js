layui.use(["layer","form","pager","laydate"],function(){
	 var $ = layui.jquery, layer = layui.layer ,form = layui.form() ,tool = layui.tool,laydate=layui.laydate; 
	 //自定义验证规则
	  form.verify({
		 money: function(value){
			 regMoney = /^\d+(\.\d+)?$/;
		      if(!regMoney.test(value)){
		    	  return '金额填写不对！请核实！';
		      }
	    }
	  });
})