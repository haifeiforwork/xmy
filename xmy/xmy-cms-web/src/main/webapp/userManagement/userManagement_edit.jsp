<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>会员资料</title>
<%@ include file="/commons/comm_css.jsp"%>
<link rel="stylesheet" href="native/css/style.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
<!-- 公共layout -->
  <%@ include file="/commons/common_layout.jsp"%>
	<div class="layui-tab layui-tab-brief layui-form" >
		 <ul class="layui-tab-title site-title">
			      <li class="layui-this">会员资料</li>
			      <li>用户流水</li>
			      <li>积分流水</li>
		 </ul>
		
		<div class="layui-body layui-tab-content site site-body site-custom-body layui-form-item">
			
			<div class="layui-tab-item layui-show  layui-form-item">
			  	<div  style="width:50%;">
			  					
			  					<div style="width:100%;height:50px;background:#F2F2F2;color:#E54B66;line-height:50px;font-size:17px;padding-left:30px;" >
			  					    	 当前余额:¥${UserInfo.balance };当前积分:${UserInfo.accPoints};
			  					    	 当前会员组:
			  					    	 <c:if test="${UserInfo.level==0}">普通会员</c:if>
			  					    	 <c:if test="${UserInfo.level==1}">白金会员</c:if>
			  					    	 <c:if test="${empty UserInfo.level}">未知会员</c:if>
			  					</div>
								<form class="layui-form" action="userManagement/updateUser" method="post">
									 <input type="hidden" name="id" id="id" value="${UserInfo.id }">
									<div class="layui-form-item">
											    <label class="layui-form-label">用户名称</label>
											    <div class="layui-input-block">
											      <input type="text" name="name" value="${UserInfo.name }" class="layui-input">
											    </div>
									</div>
									<div class="layui-form-item">
											    <label class="layui-form-label">真实姓名</label>
											    <div class="layui-input-block">
											      <input type="text" name="realName" value="${UserInfo.realName }"  class="layui-input">
											    </div>
									</div>
					  				<div class="layui-form-item">
									    <label class="layui-form-label">用户手机号</label>
									    <div class="layui-input-block">
									      <input type="text" name="mobilePhone" value="${UserInfo.mobilePhone }"  class="layui-input">
									    </div>
								  	</div>
								  	<div class="layui-form-item">
									    <label class="layui-form-label">常用收货地址</label>
									    <div class="layui-input-block">
									    <c:if test="${empty userAddrees}">用户没有设置收货地址</c:if>
									     <c:if test="${not empty userAddrees}">
									     	  <select>
									     		<c:forEach items="${userAddrees}" var="address">
									     		 <c:choose>
									     		 	<c:when test="${address.isDefault==0}"> <option value="0" selected>${address.address }</option></c:when>
									     		 	<c:otherwise>
									     		 		<option value="1">${address.address }</option>
									     		 	</c:otherwise>
									     		 </c:choose>
									     	</c:forEach>
									    	</select>
									     </c:if>
									     	
									    </div>
								  	</div>
									<div class="layui-form-item">
										    <label class="layui-form-label">性别</label>
										    <div class="layui-input-block">
										    	<input type="radio" name="sex" value="0" title="男"  <c:if test="${UserInfo.sex==0}">checked</c:if> >
										    	 <input type="radio" name="sex" value="1" title="女"  <c:if test="${UserInfo.sex==1}">checked</c:if> >
										    	
										    	<%--  <input type="text" name="sex" value=" ${UserInfo.sex}"  class="layui-input"> --%>
										    </div>
									</div> 
								   	<div class="layui-form-item">
									    <label class="layui-form-label">生日</label>
									    <div class="layui-input-block">
									   		 <input class="layui-input" name="birthday" value="<fmt:formatDate value="${UserInfo.birthday}" pattern="yyyy-MM-dd"/>" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD'})"> 
									    	 
									    </div>
								  	</div>
								 	<div class="layui-form-item">
									    <label class="layui-form-label">邮件</label>
									    <div class="layui-input-block">
									      <input type="text" name="email" value="${UserInfo.email }" class="layui-input">
									    </div>
								  	</div>
									<div class="layui-form-item">
									    <label class="layui-form-label">座机</label>
									    <div class="layui-input-block">
									      <input type="text" name="phone" value="${UserInfo.phone }" placeholder="无" class="layui-input">
									    </div>
								  	</div>
								     <div class="layui-form-item">
									    <label class="layui-form-label">备注</label>
									    <div class="layui-input-block">
									     <textarea id="remark" name="remark" style="display: none;">${UserInfo.remark }</textarea>
									    </div>
								  	  </div> 
								 	<div class="layui-form-item">
									    <label class="layui-form-label">是否启用</label>
									    <div class="layui-input-block">
									       <input type="radio" name="status" value="0" title="启用"  <c:if test="${UserInfo.status==0}">checked</c:if> >
										    <input type="radio" name="status" value="1" title="禁用"  <c:if test="${UserInfo.status==1}">checked</c:if> >
									    </div>
								  	</div>
					  				 	<div class="layui-form-item">
									    <label class="layui-form-label">用户密码</label>
									    <div class="layui-input-block">
									      <input type="password" name="password" placeholder="不需要重置密码请留空" class="layui-input" >
									    </div>
								  	</div>
								  	
									<div class="layui-form-item">
									    <div class="layui-input-block">
									      <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
									      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
									    </div>
								  	</div>
			  			</form>
			  		</div>
				</div>
				
				<div class="layui-tab-item">
					<div style="width:100%;height:50px;background:#F2F2F2;color:#E54B66;line-height:50px;font-size:17px;" >
										<input type="hidden" id="billPageCount" value="${billPageCount }">
			  					    	<div style="display:inline-block;margin-left:30px;"> 用户名称:${UserInfo.name }; 当前余额:¥ ${UserInfo.balance };
			  					    	  累计消费:¥ ${UserInfo.totalCost}</div>
			  					    	<div style="display:inline-block;float:right;"> <span><button class="layui-btn" id="btn_Recharge">充值</button></span></div>
			  		</div>
			  		<form id="myBalanceForm">
			  		 <div class="layui-inline" style="">
				  				  <div class="layui-input-inline">
						  				<input type="hidden" name="id" class="q-rule layui-input " value="${UserInfo.id}"  zfj-query="user_id:eq" style="width:180px;"   lay-verify="required" autocomplete="off" >
						  		  </div>
					  			  <br/>
					  			  <div class="layui-input-inline" style="width:150px;">
								      	<select name="spend_type"  class="q-rule" zfj-query="spend_type:eq" >
									        <option value="" >请选择类别</option>
									        <option value="1"  <c:if test="${param.spend_type==1 }">selected</c:if>>存入</option>
									        <option value="2"  <c:if test="${param.spend_type==2 }">selected</c:if>>消费</option>
									      </select>
						  		 </div>
						  		 <div class="layui-input-inline" style="width:400px;">
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" placeholder="起始日期" zfj-query="creat_time:ge" name="billStartTime" value="${param.billStartTime}" class="q-rule layui-input" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD hh:mm:ss'})">
									    </div>
									    <div class="layui-form-mid">-</div>
									    <div class="layui-input-inline" style="width:160px;">
									      <input type="text" placeholder="结束日期" zfj-query="creat_time:le" name="billEndTime" value="${param.billEndTime}" class="q-rule layui-input" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD hh:mm:ss'})">
									    </div>
								 </div>
						  		   <div class="layui-input-inline" style="width:200px;">
								    	<input type="submit" class="layui-btn" id="btn_submit" value="查询"  />
										<input type="button" class="layui-btn" id="reset" value="重置"  />
								   </div>
						</div>
			  		</form>
			  		<br/><br/>
			  		<div id="mytable" class="layui-form" ></div>
					<div id="mypager"></div>
				</div>
				<div class="layui-tab-item">
					<div style="width:100%;height:50px;background:#F2F2F2;color:#E54B66;line-height:50px;font-size:17px;" >
										<input type="hidden" id="pointPageCount" value="${pointPageCount }">
			  					    	<div style="display:inline-block;margin-left:30px;"> 用户名称:${UserInfo.name }; 当前积分: ${UserInfo.balance };
			  					    	  累计兑换积分:${totalCostPoint}</div>
			  					    	<div style="display:inline-block;float:right;"> <span><button class="layui-btn" id="btn_Recharge_Point">充值</button></span></div>
			  		</div>
			  		<form id="myPointForm">
			  		 <div class="layui-inline" style="">
				  				  <div class="layui-input-inline">
						  				<input type="hidden" name="id" class="q-rule layui-input " value="${UserInfo.id}"  zfj-query="user_id:eq" style="width:180px;"   lay-verify="required" autocomplete="off" >
						  		  </div>
					  			  <br/>
					  			  <div class="layui-input-inline" style="width:150px;">
								      	<select name="spend_type"  class="q-rule" zfj-query="spend_type:eq" value="c">
									        <option value="">请选择类别</option>
									        <option value="1"  <c:if test="${param.spend_type==1 }">selected</c:if>>收入</option>
									        <option value="2"  <c:if test="${param.spend_type==2 }">selected</c:if>>兑换</option>
									      </select>
						  		 </div>
						  		 <div class="layui-input-inline" style="width:400px;">
									    <div class="layui-input-inline" style="width: 160px;">
									      <input type="text" placeholder="起始日期" zfj-query="creat_time:ge" name="pointStartTime" value="${param.pointStartTime}" class="q-rule layui-input" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD hh:mm:ss'})">
									    </div>
									    <div class="layui-form-mid">-</div>
									    <div class="layui-input-inline" style="width:160px;">
									      <input type="text" placeholder="结束日期" zfj-query="creat_time:le" name="pointEndTime" value="${param.pointEndTime}" class="q-rule layui-input" onclick="layui.laydate({elem: this, istime: false, format: 'YYYY-MM-DD hh:mm:ss'})">
									    </div>
								 </div>
						  		   <div class="layui-input-inline" style="width:200px;">
								    	<input type="submit" class="layui-btn" id="btn_submit" value="查询"  />
										<input type="button" class="layui-btn" id="reset" value="重置"  />
								   </div>
						</div>
			  		</form>
			  		<br/><br/>
			  		<div id="mytablepoint" class="layui-form" ></div>
					<div id="mypagger2"></div>
				</div>
		</div>	
	</div>
	<!-- 底部 -->
  <%@ include file="/commons/buttom.jsp"%>
  <script type="text/javascript">
  layui.use(["pager","form",],function(){
	  
	  var tool = layui.tool ,$ = layui.jquery ,pager = layui.pager ,form = layui.form();
	  console.log(pager.reqSerialize("#myForm","creat_time desc")) ;
	  //加载账单流水
	  pager.load({
			url:"userManagement/userBill",
			pages:$("#billPageCount").val(),
			selector:"#myBalanceForm"
		},function(data){
			$("#mytable").html(data) ;
			form.render();
		}) ;
	  //加载积分流水
	  pager.load({
			url:"userManagement/userPoint",
			pages:$("#pointPageCount").val(),
			selector:"#myPointForm",
			cont:"mypagger2"
		},function(data){
			$("#mytablepoint").html(data) ;
			form.render();
		}) ;
	  $("#reset").click(function(){ //重置按钮
			$(".q-rule").val("")
		})
	  var content="<input name='count' id='count' class='layui-input' placeholder='请输入充值金额'/>例:充值:100；扣款-100<br/>"
	// "<div class='layui-input-inline'>"+
	// "<input class='layui-btn' id='btn_submit_recharge' value='充值' style='width:100px;' />"+
	// "<input class='layui-btn layui-btn-primary' id='btn_cancle_recharge' value='取消' style='width:100px;' /></div>";
	 
	$("#btn_Recharge").click(function(){
		  var id=$("#id").val();
		  layer.open({
			  title: '为${UserInfo.name}充值金额'
			  ,content: content,
			  btn:['充值','取消'],
			  yes:function(index, layero){
				  var count=$("#count").val();
				  var spendType=count>0?1:2;
				  var remarks="";
				  var mgs="";
				  if(count>0)
					  {
					 	 remarks="为${UserInfo.name}充值"+count+"元"
					 	 msg="成功充值"+count+"元";
					  }
				  else
					  {
					  		remarks="为${UserInfo.name}扣款"+count+"元"
					  		 msg="成功扣款"+count+"元";
					  }
					tool.load() ;
					 $.ajax({
						url: "userManagement/updateBillPoint",
						type: "POST",
						dataType: "JSON",
						data:{"spendType":spendType,"type":1,"moneyPoint":count,"userId":id,"remarks":remarks},
						async: false,
						success:function(data){
							if(data.state >= 1 ){
								tool.unload() ;
								tool.info(msg);
								form.render();
							}
							else{
								tool.unload() ;
								tool.error("充值失败");
							}
						}
					});   
			  },
			  btn2:function(index, layero)
			  {
				  tool.error("取消充值");
			  }
			});    
	  })
	  $("#btn_Recharge_Point").click(function(){
		  var id=$("#id").val();
		  layer.open({
			  title: '为${UserInfo.name}充值积分'
			  ,content: content,
			  btn:['充值','取消'],
			  yes:function(index, layero){
				  var count=$("#count").val();
				  var spendType=count>0?1:2;
				  var remarks="";
				  var mgs="";
				  if(count>0)
					  {
					 	 remarks="为${UserInfo.name}充值"+count+"积分"
					 	 msg="成功充值"+count+"的积分";
					  }
				  else
					  {
					  		remarks="为${UserInfo.name}扣款"+count+"积分"
					  		 msg="成功扣款"+count+"的积分";
					  }
					tool.load() ;
					 $.ajax({
						url: "userManagement/updateBillPoint",
						type: "POST",
						dataType: "JSON",
						data:{"spendType":spendType,"type":2,"moneyPoint":count,"userId":id,"remarks":remarks},
						async: false,
						success:function(data){
							if(data.state >= 1 ){
								tool.unload() ;
								tool.info(msg);
								form.render();
							}
							else{
								tool.unload() ;
								tool.error("充值失败");
							}
						}
					});   
			  },
			  btn2:function(index, layero)
			  {
				  tool.error("取消充值");
			  }
			});    
	  })
	
  })
  layui.use('layedit', function(){
	  var layedit = layui.layedit;
	  layedit.build('remark'); //建立编辑器
	});
//日期时间选择器
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	});
	
  </script>
</div>
</body>
</html>