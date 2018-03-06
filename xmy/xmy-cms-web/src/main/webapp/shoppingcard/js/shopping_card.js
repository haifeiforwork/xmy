layui.use(["pager","form","upload","laydate","layer"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,laydate=layui.laydate;
	var form = layui.form(), layer = layui.layer ;
	/*console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;*/
	pager.load({
		url:"card/shoppingCardList",//order/list
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
	
	//上传章并导入购物卡
	$("body").on("click","#import_card",function(){
		$("#import_card_file").trigger("click");
	});
	$("body").on("change","#import_card_file",function(){
		console.log("上传文件");
		//$("#upload_card_form").submit();
		//$("#imgWait").show();
        var formData = new FormData();
        formData.append("file", document.getElementById("import_card_file").files[0]);   
        $.ajax({
            url: "card/import",
            type: "POST",
            data: formData,
            /**
            *必须false才会自动加上正确的Content-Type
            */
            contentType: false,
            /**
            * 必须false才会避开jQuery对 formdata 的默认处理
            * XMLHttpRequest会对 formdata 进行正确的处理
            */
            processData: false,
            success: function (data) {
                if (data.resultCode == 200) {
                    //alert("上传成功！");
                    tool.warning("上传成功！");
                }
                if (data.resultCode != 200) {
                    tool.warning(data.data);
                }
                //$("#imgWait").hide();
            },
            error: function () {
                tool.warning("上传失败！");
                //$("#imgWait").hide();
            }
        });
		
        
	});
	
	
	
	/*$("#btnDel").click(function(){
		var id=0;
		$(".t-checkbox").each(function(){
			id+=","+$(this).val();
		})
		if(id==0){
		   tool.warning("您未选中要删除的商品！")
		   return false;
		}
		layui.use(["pager"],function(){
			var tool = layui.tool;
			tool.confirm("您确定要删除这些商品吗？",function(){
				$.ajax({
					url:"delPcGood?ids="+id,
					type:"post",
					success:function(data){
						window.location.href="pcgoodslist";
					}
				})
			}) ;
		})
	})*/
	/*//选择商品	 	
	$('#btnAdd').on('click', function(){
	   var type = $("#sel").val();
	   if(type == ""){
		   tool.warning("您为选择需要设置的类型！")
		   return false;
	   }
	   var idStr = "0";
	   var index = layer.open({
		   type: 2,
		   title: '请选择商品的分类',
		   shadeClose: true,
		   area: ['60%', '80%'],
		   content: 'limitActivity/goodsList',
		   btn: ['确定', '取消'],
		   btnAlign: 'c',
		   yes:function(index, layero){  //获取选择分类的值
			   var body = layer.getChildFrame('body', index);
			   var tt = body.find("tbody input[type='checkbox']");
			   tt.each(function(index, item){
				 if( item.checked ){
					 //idStr = idStr+","+$(this).data("id");
					 add(type,$(this).data("id"))
				 }
			   });
			   tool.info("添加成功！")
			   window.location.href="pcgoodslist";
			   layer.close(index); 
		   },
		   btn2:function(index, layero){
			   layer.close(index); 
		   }
	   })
	});*/
}) ;
/*//删除菜单的提示
function del(id,type){
	layui.use(["pager"],function(){
		var tool = layui.tool;
		tool.confirm("您确定要删除这个商品吗？",function(){
			$.ajax({
				url:"delPcGoods/"+id+"/"+type,
				type:"post",
				success:function(data){
					window.location.href="pcgoodslist";
				}
			})
		}) ;
	})
}
function add(type,goodsid){
	$.ajax({
		url:"addPcgoods/"+type+"/"+goodsid,
		type:"get"
	})
	
}*/
