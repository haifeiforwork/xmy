layui
		.use(
				[ "form", "upload", "tool", "layer", "tree", "laydate" ],
				function() {
					var form = layui.form(), layer = layui.layer, tool = layui.tool, $ = layui.jquery, laydate = layui.laydate;

					form.on("select(type)", function(data) {
						var selectValue = $("select[name='proType']").val();
						if (selectValue == '商品') {
							$("#code").hide();
							$("#proGoods").show();
							$("#supplierId").hide();
						}
						if (selectValue == '分类') {
							$("#code").show();
							$("#proGoods").hide();
							$("#supplierId").hide();
						}
						if (selectValue == '供应商') {
							$("#code").hide();
							$("#proGoods").hide();
							$("#supplierId").show();
						}
					});
					// 选择端口
					$("#selectPort").click(function() {
						var myPort = $("#selectPort").find(".my-port");
						var strPort = "";
						myPort.each(function(index, item) {

							if (item.checked == true)
								strPort += (index + 1) + ",";// index默认是从0开始，但是数据库设置的是从1开始
						});
						$("#port").val(strPort);// 重新设置隐藏的端口字段值
					})
					// 选中条件
					$("#selectJoinCondition").click(
							function() {
								var sleectJoinCondition = $(
										"#selectJoinCondition").find(
										".joinCondition");
								var strJoinCondition = "";
								sleectJoinCondition.each(function(index, item) {
									if (item.checked == true)
										strJoinCondition += (index + 1) + ",";// index默认是从0开始，但是数据库设置的是从1开始
								});
								$("#joinCondition").val(strJoinCondition);// 重新设置隐藏的条件字段值
							})

					//选择水印图片按钮
					$("#choosePicture").click(function(){
						var index = layer.open({
							   type: 2,
							   title: '请选择',
							   shadeClose: true,
							   area: ['30%', '80%'],
							   content: 'waterMark/chooseWatermarkList',
							   btn: ['确定', '取消'],
							   btnAlign: 'c',
							   yes:function(index, layero){  //获取选择分类的值
								   var tt = layer.getChildFrame('body', index).find("table tbody tr.choose");
								   $("#imgPath").val(tt.data("path"));
								   $("#imgId").val(tt.data("id"));
								   layer.close(index); 
							   },
							   btn2:function(index, layero){
								   layer.close(index); 
							   }
						})
					})
					// 选择商品
					$('#addGoods')
							.on(
									'click',
									function() {
										var idStr = "";
										var index = layer
												.open({
													type : 2,
													title : '请选择商品的分类',
													shadeClose : true,
													area : [ '60%', '80%' ],
													content : 'promotion/goodsList',
													btn : [ '确定', '取消' ],
													btnAlign : 'c',
													yes : function(index,
															layero) { // 获取选择分类的值
														var body = layer
																.getChildFrame(
																		'body',
																		index);
														var tt = body
																.find("table input[type='checkbox']");
														tt
																.each(function(
																		index,
																		item) {
																	if (item.checked) {
																		if(typeof($(this).data("id")) != "undefined"){
																			 idStr = idStr+$(this).data("id")+",";
																		 }
																	}
																});
														addGoods(idStr);
														layer.close(index);
													},
													btn2 : function(index,
															layero) {
														layer.close(index);
													}
												})
									});

					// 添加商品
					function addGoods(idStr) {
						var html = '';
						$
								.ajax({
									url : "promotion/findGoods",
									type : "POST",
									dataType : "JSON",
									data : {
										"idStr" : idStr
									},
									async : false,
									success : function(data) {
										if (data.data.length > 0) {
											for (var i = 0; i < data.data.length; i++) {
												var goods = data.data[i];
												html += '<tr data-id="'
														+ goods.id
														+ '"><td>'
														+ goods.code
														+ '</td><td>'
														+ goods.name
														+ '</td><td>'
														+ goods.price
														+ '</td><td>'
														+ goods.supplierName
														+ '</td><td>'
														+ goods.brandName
														+ '</td><td>'
														+ goods.categoryName
														+ '</td><td><input class="layui-input" type="text" id="price" value="0"  /></td><td><input class="layui-input" type="text" id="limitNum" value="0" /></td><td><input class="layui-input" type="text" id="allNum" value="0" /></td><td><input class="layui-input" type="text" id="completeNum" value="0" /></td><td><a data-id="'
														+ goods.id
														+ '" id="del">移除</a></td></tr>'
											}
											$("#content").append(html);
											$("#tablediv").show();
											form.render();
										}
									}
								})
					}

					// 移除商品
					$(document).on("click", "tbody tr td #del", function() {
						$(this).parent().parent().remove();
					})
					// 表单校验
					function verify() {
						var flag = true;
						var name = $("#name").val(), beginTime = $("#beginTime")
								.val(), endTime = $("#endTime").val();
						if (name == '' || beginTime == '' || endTime == '') {
							flag = false
						}
						return flag;
					}

					// 保存
					$("#save").click(function() {
						if (verify() == false) {
							tool.warning("必填项不能为空！");
						} else {
							$.ajax({
								cache : true,
								type : "POST",
								url : "promotion/addPromotion",
								data : $("#activityForm").serialize(),// 你的formid
								async : false,
								error : function(request) {
									tool.error("保存时出错！")
								},
								success : function(data) {
									var pt=$("#proType").val();
									if(pt=='商品'){
										saveGoods(data.data);
									}
									if(pt=='分类'){
										saveCodes(data.data);
									}
									if(pt=='供应商'){
										saveSuppliers(data.data);
									}
								}
							});
						}
					})
					function saveCodes(proId) {
						var codeIds = $("#codeIds").val();
						$.ajax({
							url : "promotion/addCodeGoods",
							type : "POST",
							dataType : "JSON",
							data : {
								"proId" : proId,
								"codeIds" : codeIds
							},
							async : false,
							success : function(data) {
							}
						});
						window.location.href = "promotion/promotionList"
					}
					
					function saveSuppliers(proId) {
						var supIds = $("#supplierIds").val();
						$.ajax({
							url : "promotion/addSupGoods",
							type : "POST",
							dataType : "JSON",
							data : {
								"proId" : proId,
								"supIds" : supIds
							},
							async : false,
							success : function(data) {
							}
						});
						window.location.href = "promotion/promotionList"
					}

					var activityGoodsArray = new Array();
					var activityGoods = '', goodsId = '', price = '', limitNum = '', allNum = '', completeNum = '';
					function saveGoods(proId) {
						var child = $("table tbody tr");
						child.each(function(index, item) {
							goodsId = $(this).data("id"), price = $(this).find(
									"#price").val(), limitNum = $(this).find(
									"#limitNum").val(), allNum = $(this).find(
									"#allNum").val(), completeNum = $(this)
									.find("#completeNum").val()
							proGoods = goodsId + ',' + price + ',' + limitNum
									+ ',' + allNum + ',' + completeNum;
							$.ajax({
								url : "promotion/addPromotionGoods",
								type : "POST",
								dataType : "JSON",
								data : {
									"proId" : proId,
									"proGoods" : proGoods
								},
								async : false,
								success : function(data) {
								}
							});
							activityGoodsArray.push(activityGoods);
						});
						window.location.href = "promotion/promotionList"
					}
				});
//选择分类
$("#code").click(function() {
	var idStr = ""; //分类id字符串
	var cnameStr = ""; //分类名称字符串
	$("#codeIds").val(idStr);
	$("#codeNames").val(cnameStr);
	var index = layer.open({
		type : 2,
		title : '请选择商品',
		shadeClose : true,
		area : [ '500px', '50%' ],
		content : 'goods/toCategoryList',
		btn : [ '确定', '取消' ],
		btnAlign : 'c',
		yes : function(index, layero) { //获取选择分类的值
			var body = layer.getChildFrame('body', index);
			var tt = body.find("table input[type='checkbox']");
			tt.each(function(index, item) {
				if (item.checked) {
					idStr = idStr + $(this).data("id") + ",";
					cnameStr = cnameStr + $(this).data("name") + ",";
				}
			});
			$("#codeIds").val(idStr);
			$("#codeNames").val(cnameStr);
			layer.close(index);

		},
		btn2 : function(index, layero) {
			layer.close(index);
		}
	})
})
layui.use('layedit', function() {
	var layedit = layui.layedit, $ = layui.jquery, tool = layui.tool;
	//构建一个默认的编辑器
	var des = layedit.build('des');
	//选中了选中供应商的的输入框后需要弹出选择供应商的
	$("#supplierNames").click(function() {
		var idStr = ''; //分类id字符串
		var cnameStr = ''; //分类名称字符串
		var index = layer.open({
			type : 2,
			title : '请选择供应商',
			shadeClose : true,
			area : [ '500px', '50%' ],
			content : 'coupon/chooseSupplierList?vid=4',
			btn : [ '确定', '取消' ],
			btnAlign : 'c',
			yes : function(index, layero) { //获取选择分类的值
				var body = layer.getChildFrame('body', index);
				var tt = body.find("table input[type='checkbox']");
				tt.each(function(index, item) {
					if (item.checked) {
						idStr = idStr + $(this).data("id") + ",";
						cnameStr = cnameStr + $(this).data("name") + ",";
					}
				});
				$("#supplierIds").val(idStr);
				$("#supplierNames").val(cnameStr);
				layer.close(index);
			},
			btn2 : function(index, layero) {
				layer.close(index);
			}
		})

	})
})