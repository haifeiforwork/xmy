layui.use('laydate', function(){
	 var laydate = layui.laydate;
})

layui.use(["pager","form","layedit"	], function(){
	 var layedit = layui.layedit,$ = layui.jquery, tool = layui.tool;
	 pager=layui.pager;
	 //console.log(pager.reqSerialize("#myForm","id desc")) ;
		pager.load({
			url:"buyAndPresent/buyAndPresent/list",
			sort:"id desc",
			selector:"#myForm"
		},function(data){
			$("#mytable").html(data) ;
		}) ;
		
		 //构建一个默认的编辑器
		layedit.set({
			  uploadImage: {
			    url: 'fileUpload/layuiEditUploadImage' //接口url
		,type: '' //默认post
				  }
		}); 
	
	  var des = layedit.build('promotionMark');
	  //选中了添加主商品的这个按钮框
	  $("#btn_addMainGoods").click(function(){
		  qualifying();
	  })
	   //选中了添加赠品的这个按钮框
	  $("#btn_addGiftGoods").click(function(){
		  giftSelect();
	  })
	  //选择商品
	  function qualifying(){
	  	var idStr =""; //商品id字符串
	  	   var cnameStr = ""; //商品名称字符串
	  	   var index = layer.open({
		  		   type: 2,
		  		   title: '请选择商品',
		  		   shadeClose: true,
		  		   area: ['1100px', '80%'],
		  		   content: 'coupon/chooseGoodList',
		  		   btn: ['确定', '取消'],
		  		   btnAlign: 'c',
		  		   yes:function(index, layero){  
		  		   var body = layer.getChildFrame('body', index);
		  		   var data="";
		  		   var gCode= body.find("#my-tbody .gId");
		 		   gCode.each(function(){
		 		   idStr+=$(this).text()+",";
		 		  })
	  			  var couponGoodsHtml="";
	  			  couponGoodsHtml+=body.find("#my-tbody").html();
	  			   $("#selectMainGoodsTbody").html(couponGoodsHtml);
	  			   $("#mainGoodsIds").val(idStr);
	  			   layer.close(index); 
	  		   },
	  		   btn2:function(index, layero){
	  			   layer.close(index); 
	  		   }
	  	   })
	  }
	  //选择的赠品
	  function giftSelect(){
	  	var idStr =""; //商品id字符串
	  	var index = layer.open({
		  		   type: 2,
		  		   title: '请选择商品',
		  		   shadeClose: true,
		  		   area: ['1100px', '80%'],
		  		   content: 'coupon/chooseGoodList',
		  		   btn: ['确定', '取消'],
		  		   btnAlign: 'c',
		  		   yes:function(index, layero){  
		  		   var body = layer.getChildFrame('body', index);
		  		   var data="";
		  		   var gCode= body.find("#my-tbody .gId");
		 		   gCode.each(function(){
		 		   idStr+=$(this).text()+",";
		 		  })
	  			  var couponGoodsHtml="";
	  			  couponGoodsHtml+=body.find("#my-tbody").html();
	  			   $("#selectGiftGoodsTbody").html(couponGoodsHtml);
	  			   $("#giftGoodsIds").val(idStr);
	  			   layer.close(index); 
	  		   },
	  		   btn2:function(index, layero){
	  			   layer.close(index); 
	  		   }
	  	   })
	  }
	  //删除优惠券事件
	  $(document).on("click","#btn_del",function(){
		  var _this=$(this);
		  layer.confirm('确定要删除吗？该操作是不可逆的', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				var id=_this.data("id");
				_this.parent().parent().parent().remove();
				  var data={id:id}
				  $.ajax({
					  url:"buyAndPresent/delBuyAndPresent",
					  dataType:"json",
					  type:"post",
					  data:data,
					  success:function(data){
						 tool.info(data.data);
					  },
					  error:function(xhr){
						  tool.error("删除失败:"+xhr.status+xhr.statusText);
					  }
				  })
					
			}, function(){
			  layer.msg('取消成功')
			  });
	  })
	  //监听主商品的移除按钮
	 $(document).on("click","#selectMainGoods #remove",function(){
		$(this).parent().parent().remove();
		setCouponGoodsValue("mainGoods");
	})
	//监听赠品的移除按钮
	 $(document).on("click","#selectGiftGoods #remove",function(){
		$(this).parent().parent().remove();
		setCouponGoodsValue("giftGoods");
	})
	
	//重新设置商品的Ids
	function setCouponGoodsValue(type)
	  {
			var idStr =""; 
			var cnameStr = "";
			    if(type=="mainGoods")//选中的是主商品移除
			    	{
			    		var body=$("#selectMainGoodsTbody");

					    var gCode= body.find(".gCode");
						    gCode.each(function(){
						    idStr+=$(this).text()+",";
					    })
					  $("#mainGoodsIds").val(idStr);
			    	}else//选中的是赠品移除
			    		{
			    		 var body=$("#selectGiftGoodsTbody");

			 		    var gCode= body.find(".gCode");
			 			    gCode.each(function(){
			 			    idStr+=$(this).text()+",";
			 		    })
			 		  $("#giftGoodsIds").val(idStr);
			    		}
	  }
})
