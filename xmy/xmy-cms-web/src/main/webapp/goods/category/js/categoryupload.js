var prefix = "goods",dir = "category/"+nowtime() ;
layui.use(["pager","form","upload","plupload"],function(){
	var picUpload = layui.plupload, $ = layui.jquery ; 
	//图标上传
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
	//一级分类app图标上传
	picUpload.oss({
		containerId:"container2",
		selectfilesId:"selectfiles2",
		postfilesId:"postfiles2",
		
		FilesAdded:function(up,files){ //添加上传文件
			plupload.each(files, function(file) {
				 var html  = '<input type="text"  autocomplete="off" placeholder="'+file.name+'" class="layui-input" readonly="readonly">';
				 $("#appImgdiv").html(html);
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
				$("#appImgdiv").html(html);
				$("#appIcon").val(file.filePath);
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
	
	//一级分类app选中图标上传
	picUpload.oss({
		containerId:"container3",
		selectfilesId:"selectfiles3",
		postfilesId:"postfiles3",
		
		FilesAdded:function(up,files){ //添加上传文件
			plupload.each(files, function(file) {
				 var html  = '<input type="text"  autocomplete="off" placeholder="'+file.name+'" class="layui-input" readonly="readonly">';
				 $("#appOnImgdiv").html(html);
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
				$("#appOnImgdiv").html(html);
				$("#appOnIcon").val(file.filePath);
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
	
	
	
	
	//pc一级分类背景图
	picUpload.oss({
		containerId:"container4",
		selectfilesId:"selectfiles4",
		postfilesId:"postfiles4",
		FilesAdded:function(up,files){ //添加上传文件
			plupload.each(files, function(file) {
				 var html  = '<input type="text"  autocomplete="off" placeholder="'+file.name+'" class="layui-input" readonly="readonly">';
				 $("#pcBackgroudImgdiv").html(html);
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
				$("#pcBackgroudImgdiv").html(html);
				$("#pcBackgroudImg").val(file.filePath);
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
	
	//分类描述图上传
	picUpload.oss({
		containerId:"container5",
		selectfilesId:"selectfiles5",
		postfilesId:"postfiles5",
		FilesAdded:function(up,files){ //添加上传文件
			plupload.each(files, function(file) {
				 var html  = '<input type="text"  autocomplete="off" placeholder="'+file.name+'" class="layui-input" readonly="readonly">';
				 $("#descriptionImgDiv").html(html);
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
				$("#descriptionImgDiv").html(html);
				$("#descriptionImg").val(file.filePath);
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

function nowtime(){//将当前时间转换成yyyymmdd格式
    var mydate = new Date();
    var str = "" + mydate.getFullYear();
    var mm = mydate.getMonth()+1
    if(mydate.getMonth()>9){
      str += mm;
    }
    else{
      str += "0" + mm;
    }
    if(mydate.getDate()>9){
      str += mydate.getDate();
    }
    else{
      str += "0" + mydate.getDate();
    }
    return str;
}