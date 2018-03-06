//@author dengq plupload 上传工具，依赖于plupload-2.1.2
;layui.define(["tool"],function(l){
	"use strict" ;
	var $ = layui.jquery ,tool = layui.tool ;
	//具体的设置可以见文档：http://www.phpin.net/tools/plupload/
		//oss上传参数
	var params ,pk ;
	$.ajax("oss/getWebUploadParams?prefix="+(("undefined"===typeof prefix)?"":prefix)+"&dir=" + (("undefined"===typeof dir)?"":dir)).done(function(data){
		params = data ;
		console.log("参数获取成功--------------------------") ;
		console.log(params) ;
		params.OSSAccessKeyId = params.accessid ;
		params.success_action_status = "200" ;
		params.key = params.dir ;
		if(params.key && "/" === params.key.charAt(params.key.length-1)){
			params.key = params.key.substring(0,params.key.length-1) ;
		}
		pk = params.key ;
		
	}).fail(function(){
		tool.error("上传参数获取失败") ;
	}) ;
	//这里的opts的参数并非全部参数，只有部分参数，后续若有需求，自行扩展
	var picUpload = function(opts){
		var uploader = new plupload.Uploader({
			runtimes : 'html5,flash,silverlight,html4',
			browse_button : opts.selectfilesId || 'selectfiles', 
		    //multi_selection: false,
			container: document.getElementById(opts.containerId || "container"),
			flash_swf_url : 'native/plupload-2.1.2/js/Moxie.swf',
			silverlight_xap_url : 'native/plupload-2.1.2/js/Moxie.xap',
		    url : 'http://oss.aliyuncs.com',

			init: {
				PostInit: function() {
					opts.PostInit && opts.PostInit() ;
					document.getElementById(opts.postfilesId || "postfiles").onclick = function() {
						if(!params){
							tool.error("上传的参数不能为空") ;
							return ;
						}
					    uploader.setOption({
					        'url': params.host,
					        'multipart_params': params
					    });
					    uploader.start();
			            return false;
					};
				},

				FilesAdded: function(up, files) {
					opts.FilesAdded && opts.FilesAdded(up,files) ;
				},

				BeforeUpload: function(up, file) {
					if(!params){
						tool.error("上传的参数不能为空") ;
						return ;
					}
					params.key = (pk ? pk + "/" : "") + file.name ;
					opts.BeforeUpload && opts.BeforeUpload(up,file,params) ;
					uploader.setOption({
				        'url': params.host,
				        'multipart_params': params
				    });
				    uploader.start();
		        },

				UploadProgress: function(up, file) {
					opts.UploadProgress && opts.UploadProgress(up,file) ;
				},

				FileUploaded: function(up, file, info) {
					var filePath = params.host ;
					if(pk){
						filePath = filePath + "/" + pk ;
					}
					file.filePath = encodeURI(filePath + "/" + file.name) ;
					opts.FileUploaded && opts.FileUploaded(up,file,info) ;
				},

				Error: function(up, err) {
					opts.Error && opts.Error(up,err) ;
				}
			}
		});
		uploader.init();
	}
	l("plupload",{oss:picUpload}) ;
}) ;