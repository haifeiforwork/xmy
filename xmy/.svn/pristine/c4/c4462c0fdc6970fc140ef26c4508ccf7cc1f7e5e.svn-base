var prefix = "goods",dir = "category" ;
layui.use(["pager","form","upload","plupload"],function(){
	var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ;
	console.log(tool.getFullDate(new Date())) ;
	console.log(pager.reqSerialize("#myForm","id desc")) ;
	pager.load({url:"test/paylog/list",pages:10},pager.reqSerialize("#myForm","id desc"),function(data){
		$("#mytable").html(data) ;
	}) ;
	
	$("#btn1").click(function(){
		tool.info("普通信息提示")
	})
	$("#btn2").click(function(){
		tool.operInfo("带有操作的信息提示",function(){
			tool.info("操作完成")
		})
	})
	$("#btn3").click(function(){
		tool.warning("警告信息提示")
	})
	$("#btn4").click(function(){
		tool.error("错误信息提示")
	})
	$("#btn5").click(function(){
		tool.confirm("您真的要点击吗？",function(){
			tool.info("ok") ;
		}) ;
	})
	$("#btn6").click(function(){
		tool.load() ;
		setTimeout(function(){
			tool.unload() ;
		},5000) ;
	}) ;
	
	var form = layui.form() ;
	form.on("select(inter)", function(data){
	  console.log(data);
	});
	form.verify({
		username:function(value,item){
			if(!value){
				return "用户名称不能为空" ;
			}
		},
		password:function(value,item){
			if(!value){
				return "email不能为空" ;
			}
		}
	}) ;
	console.log("encodeURI--->" + encodeURI("99fbc1e6056e11e7ba9500163e001541.png")) ;
	console.log("encodeURI--->" + encodeURI("美食.jpg")) ;
	
	var picUpload = layui.plupload ; 
	picUpload.oss({
		/*PostInit:function(){
			
		},*/
		FilesAdded:function(up,files){
			plupload.each(files, function(file) {
				document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
				+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
				+'</div>';
			});
		},
		BeforeUpload:function(up,file,params){
			console.log("before---upload---->" + params.key)
		},
		UploadProgress:function(up,file){
			var d = document.getElementById(file.id);
			d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
			var progBar = prog.getElementsByTagName('div')[0]
			progBar.style.width= 2*file.percent+'px';
			progBar.setAttribute('aria-valuenow', file.percent);
		},
		FileUploaded:function(up,file,info){
			console.log(file) ;
			if (info.status == 200){
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + file.filePath;
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
	
	picUpload.oss({
		containerId:"container2",
		selectfilesId:"selectfiles2",
		postfilesId:"postfiles2",
		/*PostInit:function(){
			
		},*/
		FilesAdded:function(up,files){
			plupload.each(files, function(file) {
				document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
				+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
				+'</div>';
			});
		},
		BeforeUpload:function(up,file,params){
			console.log("before---upload---->" + params.key)
		},
		UploadProgress:function(up,file){
			var d = document.getElementById(file.id);
			d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
			var progBar = prog.getElementsByTagName('div')[0]
			progBar.style.width= 2*file.percent+'px';
			progBar.setAttribute('aria-valuenow', file.percent);
		},
		FileUploaded:function(up,file,info){
			console.log(file) ;
			if (info.status == 200){
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + file.filePath;
            }else{
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            }
		},
		Error:function(up,err){
			document.getElementById('console2').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}) ;
	
	
	/*layui.upload({
	    url: 'test/upload',
	    elem: '#test', //指定原始元素，默认直接查找class="layui-upload-file"
	    method: 'post', //上传接口的http类型
	    success: function(res){
	    	$("#u-img")[0].src = res.data;
	      	console.log(res) ;
	    }
	 });*/
	//var up1 = new lp.oss("oss/getWebUploadParams") ;
//	var _init_arg = {
//		browse_button : 'selectfiles', 
//	    //multi_selection: false,
//		container: document.getElementById('container'),
//		init: {
//			PostInit: function() {
//				document.getElementById('ossfile').innerHTML = '';
//				document.getElementById('postfiles').onclick = function() {
//					alert("post") ;
//					up1.start() ;
//		            return false;
//				};
//			},
//			BoforeUpload:function(up,file){
//				alert("before") ;
//				console.log(file) ;
//				up1.start(file.name) ;
//			},
//			FilesAdded: function(up, files) {
//				plupload.each(files, function(file) {
//					document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
//					+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
//					+'</div>';
//				});
//			},
//			UploadProgress: function(up, file) {
//				var d = document.getElementById(file.id);
//				d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
//	            var prog = d.getElementsByTagName('div')[0];
//				var progBar = prog.getElementsByTagName('div')[0]
//				progBar.style.width= 2*file.percent+'px';
//				progBar.setAttribute('aria-valuenow', file.percent);
//			},
//			FileUploaded: function(up, file, info) {
//	            if (info.status == 200)
//	            {
//	                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + file.name;
//	            }
//	            else
//	            {
//	                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
//	            } 
//			},
//
//			Error: function(up, err) {
//				document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
//			}
//		}
//	} ;
	
//	up1.upload({
//				browse_button : 'selectfiles', 
//	    //multi_selection: false,
//				container: document.getElementById('container'),
//				init: {
//					PostInit: function() {
//						document.getElementById('ossfile').innerHTML = '';
//						document.getElementById('postfiles').onclick = function() {
//							alert("post") ;
//							up1.start() ;
//				            return false;
//						};
//					},
//			BoforeUpload:function(up,file){
//				alert("before") ;
//				console.log(file) ;
//				up1.start(file.name) ;
//			},
//			FilesAdded: function(up, files) {
//				plupload.each(files, function(file) {
//					document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
//					+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
//					+'</div>';
//				});
//			},
//			UploadProgress: function(up, file) {
//				var d = document.getElementById(file.id);
//				d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
//	            var prog = d.getElementsByTagName('div')[0];
//				var progBar = prog.getElementsByTagName('div')[0]
//				progBar.style.width= 2*file.percent+'px';
//				progBar.setAttribute('aria-valuenow', file.percent);
//			},
//			FileUploaded: function(up, file, info) {
//	            if (info.status == 200)
//	            {
//	                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + file.name;
//	            }
//	            else
//	            {
//	                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
//	            } 
//			},
//
//			Error: function(up, err) {
//				document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
//			}
//		}
//	},function(up, file, info){
//		if (info.status == 200)
//        {
//            document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + file.name;
//        }
//        else
//        {
//            document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
//        } 
//	},function(up, err){
//		document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
//	}) ;
	
	//获取签名
	

	/*var picUpload = (function(){
		//oss上传参数
		var params ,init_opts ;
		$.ajax("oss/getWebUploadParams?prefix=&dir=").done(function(data){
			params = data ;
			console.log("参数获取成功--------------------------") ;
			console.log(params) ;
			params.OSSAccessKeyId = params.accessid ;
			params.success_action_status = "200" ;
			params.key = params.dir ;
		}).fail(function(){
			tool.error("上传参数获取失败") ;
		}) ;
		return function(browse_buton,container,postfiles){
			if(!params){
				tool.error("") ;
			}
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'selectfiles', 
			    //multi_selection: false,
				container: document.getElementById('container'),
				flash_swf_url : 'native/plupload-2.1.2/js/Moxie.swf',
				silverlight_xap_url : 'native/plupload-2.1.2/js/Moxie.xap',
			    url : 'http://oss.aliyuncs.com',

				init: {
					PostInit: function() {
						document.getElementById('ossfile').innerHTML = '';
						document.getElementById('postfiles').onclick = function() {
							alert("postfiles") ;
							//var new_multipart_params = setParams() ;
							console.log("postInit--->") ;
							console.log(params) ;
							console.log("postInit--end--->" + params.key) ;
						    uploader.setOption({
						       // 'url': "http://cqzfj.oss-cn-qingdao.aliyuncs.com",
						        'url': params.host,
						        'multipart_params': params
						    });
						    uploader.start();
							
				            return false;
						};
					},

					FilesAdded: function(up, files) {
						plupload.each(files, function(file) {
							document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
							+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
							+'</div>';
						});
					},

					BeforeUpload: function(up, file) {
			            //check_object_radio();
			            //get_dirname();
			            //set_upload_param(up, file.name, true);
						//var new_multipart_params = setParams(file.name) ;
						params.key = params.key + "/" + file.name ;
						console.log("before---upload---->" + params.key) ;
						uploader.setOption({
					       // 'url': "http://cqzfj.oss-cn-qingdao.aliyuncs.com",
					        'url': params.host,
					        'multipart_params': params
					    });
					    uploader.start();
			        },

					UploadProgress: function(up, file) {
						var d = document.getElementById(file.id);
						d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
			            var prog = d.getElementsByTagName('div')[0];
						var progBar = prog.getElementsByTagName('div')[0]
						progBar.style.width= 2*file.percent+'px';
						progBar.setAttribute('aria-valuenow', file.percent);
					},

					FileUploaded: function(up, file, info) {
			            if (info.status == 200)
			            {
			                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + file.name;
			            }
			            else
			            {
			                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
			            } 
					},

					Error: function(up, err) {
						document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
					}
				}
			});
			uploader.init();
		}
	})() ;
	
	var params ;
	getParams("test") ;
	function getParams(prefix,dir){
		$.ajax({
			url:"oss/getWebUploadParams?prefix="+prefix+"&dir=" + (dir||""),
			dataType:"json",
			success:function(data){
				params = data ;
				console.log("参数获取成功--------------------------") ;
				console.log(params) ;
				params.OSSAccessKeyId = params.accessid ;
				params.success_action_status = "200" ;
				params.key = params.dir ;
				console.log("参数获取成功--------------------------") ;
			}
		}) ;
	}
	
	
	function setParams(filename){
		var key = filename ? ("test/" + filename) : "/test" ;
		var param = {
	        'key' : key ,
	        'policy': "eyJleHBpcmF0aW9uIjoiMjAyMC0wMS0wMVQxMjowMDowMC4wMDBaIiwiY29uZGl0aW9ucyI6W1siY29udGVudC1sZW5ndGgtcmFuZ2UiLDAsMTA0ODU3NjAwMF1dfQ==",
	        'OSSAccessKeyId': "LTAIfZFOqUI4Tcoz", 
	        'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
	        'signature': "MIi+YUGPsbiXGv9f897JdH47bj4="
	    } ;
		//console.log(param) ;
		return param ;
	}
	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : 'selectfiles', 
	    //multi_selection: false,
		container: document.getElementById('container'),
		flash_swf_url : 'native/plupload-2.1.2/js/Moxie.swf',
		silverlight_xap_url : 'native/plupload-2.1.2/js/Moxie.xap',
	    url : 'http://oss.aliyuncs.com',

		init: {
			PostInit: function() {
				document.getElementById('ossfile').innerHTML = '';
				document.getElementById('postfiles').onclick = function() {
					alert("postfiles") ;
					//var new_multipart_params = setParams() ;
					console.log("postInit--->") ;
					console.log(params) ;
					console.log("postInit--end--->" + params.key) ;
				    uploader.setOption({
				       // 'url': "http://cqzfj.oss-cn-qingdao.aliyuncs.com",
				        'url': params.host,
				        'multipart_params': params
				    });
				    uploader.start();
					
		            return false;
				};
			},

			FilesAdded: function(up, files) {
				plupload.each(files, function(file) {
					document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
					+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
					+'</div>';
				});
			},

			BeforeUpload: function(up, file) {
	            //check_object_radio();
	            //get_dirname();
	            //set_upload_param(up, file.name, true);
				//var new_multipart_params = setParams(file.name) ;
				params.key = params.key + "/" + file.name ;
				console.log("before---upload---->" + params.key) ;
				uploader.setOption({
			       // 'url': "http://cqzfj.oss-cn-qingdao.aliyuncs.com",
			        'url': params.host,
			        'multipart_params': params
			    });
			    uploader.start();
	        },

			UploadProgress: function(up, file) {
				var d = document.getElementById(file.id);
				d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
	            var prog = d.getElementsByTagName('div')[0];
				var progBar = prog.getElementsByTagName('div')[0]
				progBar.style.width= 2*file.percent+'px';
				progBar.setAttribute('aria-valuenow', file.percent);
			},

			FileUploaded: function(up, file, info) {
	            if (info.status == 200)
	            {
	                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + file.name;
	            }
	            else
	            {
	                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
	            } 
			},

			Error: function(up, err) {
				document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
			}
		}
	});

	uploader.init();*/
	
}) ;