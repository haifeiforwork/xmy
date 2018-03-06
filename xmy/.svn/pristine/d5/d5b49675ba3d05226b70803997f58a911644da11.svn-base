layui.use(["pager","form","upload","laydate","layer"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate;
	var form = layui.form(), layer = layui.layer ;
	$("#page").hide();
	console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;
	pager.load({
		url:"sysUser/menuList",//order/list
		pages:$("#page").val()
	},pager.reqSerialize("#myForm","id desc"),function(data){
		$("#mytable").html(data) ;
	}) ;
		
	$("#btn1").click(function(){
		var orders;
		//定义判断用户选中的父级个数
		var count = 0;
		$(".t-checkbox").each(function(index,item){
			if(item.checked){
				orders=item.value;
				count+=1;
			}
		});
		//如果是多个就提示操作错误
		if(count>1){
			tool.info("你选中的父级过多请核实！默认是第一个！")
			return false;
		}else{
			
			$("#parentid").val(orders);
		}
	})
	$("#btn2").click(function(){
		
		$("#parentid").val(0);
		
	})
	//自定义验证规则
	  form.verify({
	    title: function(value){
	      if(value.length < 1){
	        return '名称不能为空';
	      }
	    }
	  	,par: function(value){
	  		if(value.length < 1){
	  			return '这是添加子级菜单父级不能为空！'
	  		}
	  	}
	    ,seq: [/^\d{1,}$/, '显示顺序请输入数字']
	    ,content: function(value){
	      layedit.sync(editIndex);
	    }
	  });
	  //弹窗
	var active = {
	 offset: function(othis){
	      var type = othis.data('type')
	      ,text = othis.text();
	      
	      layer.open({
	        type: 1
	        ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
	        ,id: 'LAY_demo'+type //防止重复弹出
	        ,content: $("#addMenu")
	        ,btnAlign: 'c' //按钮居中
	        ,shade: 0 //不显示遮罩
	        ,area: '400px;'
	        ,yes: function(){
	          layer.closeAll();
	        }
	      });
	    }
	};
	 $('.layui-btn').on('click', function(){
		    var othis = $(this), method = othis.data('method');
		    active[method] ? active[method].call(this, othis) : '';
	});

}) ;
//删除菜单的提示
function del(id){
	layui.use(["pager"],function(){
		var tool = layui.tool;
		tool.confirm("您确定要删除这个菜单吗？点击确定后该角色下的所有菜单也将被清空！",function(){
			window.location.href="sysUser/deleteMenu/"+id;
		}) ;
	})
}
