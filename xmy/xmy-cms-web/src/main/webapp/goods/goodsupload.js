var prefix = "goods",dir = "goods/"+nowtime() ;
layui.use(["pager","form","upload","plupload"],function(){
	var picUpload = layui.plupload ; 
	picUpload.oss({
		FilesAdded:function(up,files){ //添加上传文件
			plupload.each(files, function(file) {
				 var html ='<span>'+file.name+'</span>';
				 $("#imgdiv").append(html);
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
				var html='<tr><td><input type="checkbox"  ></td><td><input type="text"  name="goodsImgPath" value="'+file.filePath+'" readonly="readonly"></td><td><img alt="" src="'+file.filePath+'" id="img"  width="50" height="50"></td><td><input type="text" name="sort" value="0"></td></tr>';
				$("#goodsImage").append(html);
				$("#goodsImagetab").show();
				$("#imgdiv").html(''); //清除选择的文件
				layui.use(["form"], function(){
						var form = layui.form();
						form.render();
				});
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