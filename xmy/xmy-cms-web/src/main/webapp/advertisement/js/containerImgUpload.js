var prefix = "advertisement",dir = "" ;
layui.use(["pager","form","upload","plupload"],function(){
	var picUpload = layui.plupload , $ = layui.jquery; 
	
// 广告图标上传
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
			var html = '<img  width="400" height="400" src="'+file.filePath+'" /> ' ;
			$("#imgdiv").html(html);
			$("#iconImg").val(file.filePath);
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


//广告背景图上传
picUpload.oss({
		containerId:"container2",
		selectfilesId:"selectfiles2",
		postfilesId:"postfiles2",
		FilesAdded:function(up,files){
			plupload.each(files, function(file) {
				 var html  = '<input type="text"  autocomplete="off" placeholder="'+file.name+'" class="layui-input" readonly="readonly">';
				 $("#imgdiv2").html(html);
			});
		},
		BeforeUpload:function(up,file,params){
			console.log("before---upload---->" + params.key)
		},
		UploadProgress:function(up,file){

		},
		FileUploaded:function(up,file,info){
			console.log(file) ;
			if (info.status == 200){
				var html = '<img  width="100" height="100" src="'+file.filePath+'" /> ' ;
				$("#imgdiv2").html(html);
				$("#bgImg").val(file.filePath);
			}
			else
			{
			    document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
			    }
		},
		Error:function(up,err){
			document.getElementById('console2').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
}) ;
}) ;