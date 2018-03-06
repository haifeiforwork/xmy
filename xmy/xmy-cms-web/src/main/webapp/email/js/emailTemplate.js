layui.use(["pager","form","upload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ;
	//console.log(tool.getFullDate(new Date())) ;
	//alert($("#pageCount").val());
	//console.log(pager.reqSerialize("#myForm","id desc")) ;
	pager.load({
		url:"mallSetting/emailTemplate/list",
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		//alert(data);
		$("#mytable").html(data) ;
		form.render();
	}) ;
	
	$("#btn1").click(function(){
		tool.info("普通信息提示")
	})
	$("#btn2").click(function(){
		tool.operInfo("带有操作的信息提示",function(){
			tool.info("操作完成")
		})
	})
	$("#btn3").click(function(){
		tool.warning("警告信息提示")
	})
	$("#btn4").click(function(){
		tool.error("错误信息提示")
	})
	$("#btn5").click(function(){
		tool.confirm("您真的要点击吗？",function(){
			tool.info("ok") ;
		}) ;
	})
	$("#btn6").click(function(){
		tool.load() ;
		setTimeout(function(){
			tool.unload() ;
		},5000) ;
	}) ;
	
	var form = layui.form() ;
	form.on("select(inter)", function(data){
	  console.log(data);
	});
	form.verify({
		name:function(value,item){
			if(!value){
				return "栏目名称不能为空 ！" ;
			}
		},
		weight:function(value,item){
			
			var reg=/^\+?[1-9][0-9]*$/;
			if(!value){
				return "权重不能为空 ！" ;
			}
			else if(!reg.test(value)){
				return "权重必须为整数!"
			}
				
		
			
		}
	}) ;
	form.on('checkbox(filter)', function(data){
		alert(2)
		alert(data);
	});
}) ;

layui.use('element', function(){
	  var element = layui.element();
	  
	  //一些事件监听
	  element.on('tab(demo)', function(data){
	    console.log(data);
	  });
	});

function delCheck(url){
	console.log("xx:"+url);
	//询问框
	layer.confirm('确定要删除吗？该操作是不可逆的', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		  window.location.href=url;
	}, function(){
	  layer.msg('取消成功')
	  });
		  
}
layui.use('layedit', function(){
	  var layedit = layui.layedit;
	  layedit.build('des'); //建立编辑器
	});