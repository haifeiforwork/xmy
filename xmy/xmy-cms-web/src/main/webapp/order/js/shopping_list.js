layui.use(["pager","form","upload","laydate"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form();
	/*console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;*/
	pager.load({
		url:"orderGoods/list",//order/list
		sort:"id desc",
		selector:"#myForm"
	},function(data){
		$("#mytable").html(data) ;
		form.render() ;
	}) ;
	form.on('checkbox(allChoose)', function(data){
	    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
	    child.each(function(index, item){
	      item.checked = data.elem.checked;
	    });
	    form.render('checkbox');
	  });
	$(function($){
		//弹窗
		 var active = {
		 offset: function(othis){
		      var type = othis.data('type')
		      ,text = othis.text();
		      layer.open({
		        type: 1
		        ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
		        ,id: 'LAY_demo'+type //防止重复弹出
		        ,content: $("#supplyTime")
		        ,btn: '关闭窗口'
		        ,btnAlign: 'c' //按钮居中
		        ,shade: 0 //不显示遮罩
		        ,area: '400px;'
		        ,yes: function(){
		          layer.closeAll();
		        }
		      });
		    }
		  };
		//监听按钮点击弹窗
		$('.layui-btn').on('click', function(){
		    var othis = $(this), method = othis.data('method');
		    active[method] ? active[method].call(this, othis) : '';
		  });
		$("#btnExcel").click(function(){
			var type=$("#sel").val();
			var orderId=$("#orderid").val();
			var val=$("#url").val();
			if(type==""){
				tool.warning("您未选择导出类型！");
				return false;
			}
			/*$.ajax({
				url:"exlShoppingGoods?type="+type+"&orderIds="+orderId+"&url="+val.trim(),
				type:"post",
				success:function(data){
					if(data == 1){
						tool.info("文件导出成功请注意检查！");
					}
				}
			})*/
			window.location.href="exlShoppingGoods?type="+type+"&orderIds="+orderId+"&url="+val.trim();
		});
		
		$("#btnLable").click(function(){
			var type=$("#lable").val();
			var orderId=$("#orderid").val();
			var val=$("#url").val();
			if(type==""){
				tool.warning("您未选择导出类型！");
				return false;
			}
			/*$.ajax({
				url:"exlShoppingGoods?type="+type+"&orderIds="+orderId+"&url="+val.trim(),
				type:"post",
				success:function(data){
					if(data == 1){
						tool.info("文件导出成功请注意检查！");
					}
				}
			})*/
			window.location.href="exlLable?type="+type+"&orderIds="+orderId+"&url="+val.trim();
		});
		//导出特殊订单
		$("#btnOrder").click(function(){
			var type=$("#order").val();
			var del=$("#order").attr("adr");
			var orderId=$("#orderid").val();
			var val=$("#url").val();
			if(type==""){
				tool.warning("您未选择导出类型！");
				return false;
			}
			window.location.href="exlOrder?type="+type+"&orderIds="+orderId+"&url="+val.trim();
		});
		$("#btnWrite").click(function(){
			var orderId=$("#orderid").val();
			window.location.href="exlWhriteLable?orderIds="+orderId;
		})
	});
}) ;