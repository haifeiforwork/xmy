layui.use(["pager","form","layedit","upload"	], function(){
	 var layedit = layui.layedit,$ = layui.jquery, tool = layui.tool,pager = layui.pager;
	 var form = layui.form() ;
	 pager=layui.pager;
	/* console.log(tool.getFullDate(new Date())) ;
		console.log(pager.reqSerialize("#myForm","id desc")) ;*/
		//table加载
		pager.load({
			url:"topicPage/list",
			sort:"id desc",
			selector:"#myForm"
		},function(data){
			//alert(data);
			$("#mytable").html(data) ;
			form.render();
		}) ;
		
	//上传压缩包
	 layui.upload({
		    url: 'fileUpload/uploadZip' //上传接口
		    ,success: function(res){ //上传成功后的回调
		    	
		    	if(res.data.success){
		    		layer.msg('上传文件成功!', {icon: 1}); 
		    		$("#toppicPagePath").val(res.data.filePath)
		    	}else{
		    		layer.msg(res.data.msg, {icon: 2}); 
		    	}
		      console.log(res)
		    }
		  });
	
})
 //删除检查
	 function delCheck(url){
			//询问框
			layer.confirm('确定要删除吗？该操作是不可逆的', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				  window.location.href=url;
			}, function(){
			  layer.msg('取消成功')
			  });
				  
		}