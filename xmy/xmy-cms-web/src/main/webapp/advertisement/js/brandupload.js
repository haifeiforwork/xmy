var prefix = "brand",dir = "" ;
layui.use(["pager","form","upload","plupload"],function(){
	var picUpload = layui.plupload ; 
	picUpload.oss({
		FilesAdded:function(up,files){ //添加上传文件
			plupload.each(files, function(file) {
				 var html  = '<input type="text"  autocomplete="off" placeholder="'+file.name+'" class="layui-input" readonly="readonly">';
				 $("#imgdiv").html(html);
			});
		},
		BeforeUpload:function(up,file,params){  //文件上传前
			console.log("before---upload---->" + params.key)
		},
		UploadProgress:function(up,file){ //文件上传中

		},
		FileUploaded:function(up,file,info){ //文件上传成功的回调
			console.log(file) ;
			if (info.status == 200){
				var html = '<img  width="100" height="100" src="'+file.filePath+'" /> ' ;
				$("#imgdiv").html(html);
				$("#icon").val(file.filePath);
            }
            else
            {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            }
		},
		Error:function(up,err){
			document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}) ;
}) ;