layui.use('laydate', function(){
	 var laydate = layui.laydate;
})

layui.use('layedit', function(){
	 var layedit = layui.layedit,$ = layui.jquery, tool = layui.tool;
	 //构建一个默认的编辑器
	 
		layedit.set({
			  uploadImage: {
			    url: 'fileUpload/layuiEditUploadImage' //接口url
			    ,type: '' //默认post
			  }
			});
	  var des = layedit.build('des');
	  //选中了选中供应商的的输入框后需要弹出选择供应商的
	  $("#supplierNames").click(function(){
		  var idStr = ''; //分类id字符串
		   var cnameStr = ''; //分类名称字符串
		   var index = layer.open({
			   type: 2,
			   title: '请选择供应商',
			   shadeClose: true,
			   area: ['500px', '50%'],
			   content: 'coupon/chooseSupplierList?vid=4',
			   btn: ['确定', '取消'],
			   btnAlign: 'c',
			   yes:function(index, layero){  //获取选择分类的值
				   var body = layer.getChildFrame('body', index);
				   var tt = body.find("table input[type='checkbox']");
				   tt.each(function(index, item){
					 if( item.checked ){
						 idStr = idStr+$(this).data("id")+",";
						 cnameStr = cnameStr+$(this).data("name")+",";
					 }
				   });
				   $("#supplierIds").val(idStr);
				   $("#supplierNames").val(cnameStr);
				   layer.close(index); 
			   },
			   btn2:function(index, layero){
				   layer.close(index); 
			   }
		   })
	  })
	  //选中了分类使用的这个按钮框
	  $("#btnClassify").click(function(){
		  var names=$("#btnClassify").find("input[name='useRange']");
		 names.each(function(index,item){
			 if(item.checked)//当复选框被选中后
				 {
				 	var checkedValue=item.value;
				 	if(checkedValue==1)//选中的全场通用
				 		{
				 		 $("#useRangeNames").val("全场通用");
				 		 $("#useRangeIds").val("");
				 		 var  selectCouponGoods=$("body #couponGoods2")
			  			  selectCouponGoods.hide();//如果选中的安装分类就隐藏掉商品的
				 		}
				 	else if(checkedValue==2)//选择的分类使用
			 		{
				 		classify();
			 		}
				 	else if(checkedValue==3)//选择的限定商品
			 		{
				 		qualifying();
			 		}
				 	else if(checkedValue==4)//选择的排他商品
				 	{
				 		qualifying();
				 	}
				 }
		 })
	  })
	  //清空优惠券详情输入框
	  $("#btnCleanUseRange").click(function(){
		  $("#useRangeNames").val("");
		  $("#useRangeIds").val("");
		  $("#couponGoods2").hide();
	  })
	  
	  
	//选择的分类使用
	  function classify(){
	  	 var idStr =""; //分类id字符串
	  	   var cnameStr = ""; //分类名称字符串
	  	   $("#useRangeNames").val(idStr)
	    	   $("#useRangeNames").val(cnameStr);
	  	   var index = layer.open({
	  		   type: 2,
	  		   title: '请选择商品',
	  		   shadeClose: true,
	  		   area: ['500px', '50%'],
	  		   content: 'goods/toCategoryList',
	  		   btn: ['确定', '取消'],
	  		   btnAlign: 'c',
	  		   yes:function(index, layero){  //获取选择分类的值
	  			   var body = layer.getChildFrame('body', index);
	  			   var tt = body.find("table input[type='checkbox']");
	  			   tt.each(function(index, item){
	  				 if( item.checked ){
	  					 idStr = idStr+$(this).data("id")+",";
	  					 cnameStr = cnameStr+$(this).data("name")+",";
	  				 }
	  			   });
	  			   $("#useRangeIds").val(idStr);
	  			   $("#useRangeNames").val(cnameStr);
	  			  
	  			  var  selectCouponGoods=$("body #couponGoods2")
	  			  selectCouponGoods.hide();//如果选中的安装分类就隐藏掉商品的
	  			   layer.close(index); 
	  			  
	  		   },
	  		   btn2:function(index, layero){
	  			   layer.close(index); 
	  		   }
	  	   })
	  }
	  //选择的限定商品
	  function qualifying(){
	  	var idStr =""; //商品id字符串
	  	   var cnameStr = ""; //商品名称字符串
	  	   	$("#useRangeNames").val(idStr)
	   		$("#useRangeNames").val(cnameStr);
	  	   var index = layer.open({
		  		   type: 2,
		  		   title: '请选择商品',
		  		   shadeClose: true,
		  		   area: ['1100px', '80%'],
		  		   content: 'coupon/chooseGoodList',
		  		   btn: ['确定', '取消'],
		  		   btnAlign: 'c',
		  		   yes:function(index, layero){  //获取选择分类的值
		  		   var body = layer.getChildFrame('body', index);
		  		   var data="";
		  		   var gCode= body.find("#my-tbody .gCode");
		 		   gCode.each(function(){
		 		   idStr+=$(this).text()+",";
		 		  })
		 		   var gName= body.find("#my-tbody .gName");
		 		   gName.each(function(){
		 		    cnameStr+=$(this).text()+",";
		 		  })
	  			  var couponGoodsHtml="";
	  			  couponGoodsHtml+=body.find("#selectCouponGoods").html();
	  			   $("#couponGoods2").html(couponGoodsHtml);
	  			   $("#useRangeIds").val(idStr);
	  			   $("#useRangeNames").val(cnameStr);
	  			 var  selectCouponGoods=$("body #couponGoods2")
	  			  selectCouponGoods.show();//如果选中的商品类就显示出来
	  			   layer.close(index); 
	  		   },
	  		   btn2:function(index, layero){
	  			   layer.close(index); 
	  		   }
	  	   })
	  }
	  //删除优惠券事件
	  $(document).on("click","#btn_del",function(){
		  var _this = $(this);
		  layer.confirm('确定要删除吗？该操作是不可逆的', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				var id=_this.data("id");
				  $(_this).parent().parent().parent().remove();
				  var data={"id":id};
				  $.ajax({
					  url:"coupon/delCoupon",
					  dataType:"json",
					  type:"post",
					  data:data,
					  success:function(data){
						 tool.info(data.data);
					  }
				  })
					
			}, function(){
			  layer.msg('取消成功')
			  });
	  })
	 $(document).on("click","#remove",function(){
		$(this).parent().parent().remove();
		setCouponGoodsValue();
	})
	
	//重新设置商品的Ids和Names
	function setCouponGoodsValue()
	  {
			var idStr =""; 
			var cnameStr = "";
		    var body=$("#couponGoods2");

		    var gCode= body.find("#my-tbody  .gCode");
			    gCode.each(function(){
			    idStr+=$(this).text()+",";
		    })
		   var gName= body.find("#my-tbody .gName");
		   gName.each(function(){
		   cnameStr+=$(this).text()+",";
		  
		  })
		  $("#useRangeIds").val(idStr);
		   $("#useRangeNames").val(cnameStr);
	  }
	  
	  //追加优惠劵事件(在已有的优惠劵类别上 添加更多数量的劵)
	  $(document).on("click",".append-more-coupon-action-btn",function(){
		  var $this = $(this);
		  var id=$this.data("id");
		  var countent = '<label class="layui-form-label">追加数量</label>'+
					     '<div class="layui-input-block">'+
					       '<input type="text" id="count"  required  lay-verify="required" placeholder="请输入追加数量"  class="layui-input my-input">'+
					     '</div>';
		  layer.open({
			  title:'追加优惠劵',
			  type: 1, 
			  content: countent,
			  shadeClose: true,
			  btn: ['确定', '取消'],
			  yes: function(index, layero){
				      var count = $("#count").val();
					  $.post("coupon/appendMoreCoupon",{couponid:id,count:count},function(data){
						  if (data.resultCode == 200) {
							  layer.close(index);
							  layer.msg('添加成功');
							  location.href = "coupon/couponList";
						  }else {
							  layer.msg(data.data);
						  }
					  })
				  }
			});
		  
	  })
})
