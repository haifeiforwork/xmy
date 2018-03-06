layui.use(["pager","form","upload","laydate","layer"],function(){
	var tool = layui.tool , layer = layui.layer ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate ,form = layui.form() ;
	/*console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;*/
	pager.load({
		url:"order/supply",
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
		if($("#message").val()!=""){
			tool.info($("#message").val());
		}
	})
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
	 	//批量操作
		$("#supply").click(function(){
			var orders="0";
			var sel=$("#sel");
			$(".orderid").each(function(index,item){
				if(item.checked){
					orders+=","+item.value;
				}
			});
			$("#or").val(orders);
			if(orders=="0" || sel.val()==""){
				tool.warning("您未选中要扩展时间的订单，或者没有选这操作类型！")
				return false;
			}else if (orders!="0" && sel.val()!=""){
				tool.operInfo("您确认要批量扩展该批订单吗？",function(){
				})
			}
		})
}) ;