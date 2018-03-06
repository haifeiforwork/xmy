<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>订单提交</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/order/commit_order.css">
  </head>
  
  <body>
  	<%@include file="/WEB-INF/jsp/commons/nav/nav_payment.jsp" %>
<!--内容部分-->
<div class="content">
    <div class="w-1250 category">
        <div class="balances-title">填写并核对订单信息</div>
        <div class="balances-wrap">
            <div class="balances-wrap-title">收货人信息</div>
            <div class="balances-list">
                <ul class="clearfix" style="margin-top:-25px;">
                	<c:forEach items="${userAddress }" var="address">
                		<c:if test="${address.isDefault==0 }">
		                    <li class="item  active1" data-province="${address.province }" data-city="${address.city }" data-area="${address.area }"  data-id="${address.id }" data-name="${address.name }">
		                    	<input type="hidden" class="address" value="${address.id }"/>
		                        <div class="item-title">
		                        	<span class="per-ower">收货人</span> : <span class="per-name" title="${address.name }">${address.name }</span>
		                        	<div style="float: right;" class="console" >
			                        	<span data-province="${address.province }" data-city="${address.city }" data-area="${address.area }"  data-id="${address.id }" data-address="${address.address }">编辑</span>
			                        	<span style="margin-left: 8px">删除</span>
		                        	</div>
		                        </div>
		                        <div class="item-ad-ph"><p class="adress">${address.province }${address.city }${address.area }${address.address }</p><p class="phone">${address.mobilePhone }</p></div>
		                        <input type="hidden" class="area" value="${address.area }">
		                    </li>
	                    </c:if>
	                    <c:if test="${address.isDefault!=0 }">
		                    <li class="item " data-province="${address.province }" data-city="${address.city }" data-area="${address.area }" data-id="${address.id }" data-name="${address.name }"   >
		                    	<input type="hidden" class="address" value="${address.id }"/>
		                        <div class="item-title">
		                        	<span class="per-ower">收货人</span> : <span class="per-name" title="${address.name }">${address.name }</span>
		                        	<div style="float: right;" class="console">
			                        	<span data-sparePhone="${address.phone}" data-address="${address.address }"  data-province="${address.province }" data-city="${address.city }" data-area="${address.area }"  data-id="${address.id }" >编辑</span>
			                        	<span style="margin-left: 8px">删除</span>
		                        	</div>
		                        </div>
		                        <div class="item-ad-ph"><p class="adress">${address.province }${address.city }${address.area }${address.address }</p><p class="phone">${address.mobilePhone }</p></div>
		                        <input type="hidden" class="area" value="${address.area }">
		                    </li>
	                    </c:if>
                    </c:forEach>
                    <li class="item active" id="newAddress" style="display: none;">
                    	<input type="hidden" id="conProvince" value="">
                        <div class="item-title"><span class="per-ower">收货人</span> : <span class="per-name" id="conName" style="width:90px;">张三</span><span id="console" style="display:none">编辑</span></div>
                        <div class="item-ad-ph"><p class="adress" id="conAddress">地址</p><p class="phone" id="conPhone">电话</p></div>
                    </li>
                </ul>
                <div class="ad-list">+ 添加收货人信息</div>
                <div id="address" class="category-content safe-check" style="display: none;">
                    <form action="order/adduserAddress" method="post" id="addressFrom">
	                    <div class="cate-table">
	                        <div class="input-group clearfix">
	                            <div class="label">*收货人 :</div>
	                            <div class="item-input"><input id="name" type="text" name="name" class="default"></div>
	                        </div>
	                        <input type="hidden" id="addressid" name="id" value="0">
	                        <div class="input-group clearfix">
	                            <div class="label">*地区 :</div>
	                            <div class="item-input select-all">
	                                <select id="s_province" name="province" class="default">
	                                    <option>--请选择--</option>
	                                </select>
	                                <select id="s_city" name="city" class="default">
	                                    <option>--请选择--</option>
	                                </select>
	                                <select id="s_county" name="area" class="default">
	                                    <option>--请选择--</option>
	                                </select>
	                            </div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*详细地址 :</div>
	                            <div class="item-input"><input id="adrress" type="text" name="address" class="default"></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*手机号码 :</div>
	                            <div class="item-input"><input type="text" id="phone" name="mobilePhone" class="default"></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">备用电话 :</div>
	                            <div class="item-input"><input type="text" id="sparePhone" name="phone" class="default"></div>
	                            <div class="item-input"><input type="hidden" name="goodsId" id="goodsId"></div>
	                        </div>
	                        <div class="btn-group add-distance">
	                            <button type="button" class="btn-theme2 btn-default">保存收货人信息</button>
	                        </div>
	                    </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="balances-wrap">
            <div class="balances-wrap-title">配送方式 <span class="price">(全场满38元包邮,平台统一配送)</span></div>
            <div class="ins clearfix">
                <div class="ins-left">指定日期</div>
                <div id="timeDiv" class="ins-right" style="display: ${out ==0? 'none' : 'block'};">
                    <ul class="clearfix">
                    	<c:forEach items="${dateList }" var="date"> 
                    		<c:if test="${toomarror==date.day }">
	                        	<li class="item active2" data-sendtime="${date.dateTime }" value="${date.dateTime }">${date.month }月${date.day }日 
	                        		<c:if test="${date.week==1 }">星期日</c:if>
	                        		<c:if test="${date.week==2 }">星期一</c:if>
	                        		<c:if test="${date.week==3 }">星期二</c:if>
	                        		<c:if test="${date.week==4 }">星期三</c:if>
	                        		<c:if test="${date.week==5 }">星期四</c:if>
	                        		<c:if test="${date.week==6 }">星期五</c:if>
	                        		<c:if test="${date.week==7 }">星期六</c:if> 
	                        	</li>
                        	</c:if>
                        	<c:if test="${toomarror!=date.day }">
	                        	<li class="item" data-sendtime="${date.dateTime }" value="${date.dateTime }">${date.month }月${date.day }日 
	                        		<c:if test="${date.week==1 }">星期日</c:if>
	                        		<c:if test="${date.week==2 }">星期一</c:if>
	                        		<c:if test="${date.week==3 }">星期二</c:if>
	                        		<c:if test="${date.week==4 }">星期三</c:if>
	                        		<c:if test="${date.week==5 }">星期四</c:if>
	                        		<c:if test="${date.week==6 }">星期五</c:if>
	                        		<c:if test="${date.week==7 }">星期六</c:if> 
	                        	</li>
                        	</c:if>
                        </c:forEach>
                    </ul>
                </div>
                <div id="deliveryTime" style="display: ${out == 0? 'block' : 'none'};">
                	<span id="reminderContent">您的订单包含跨境商品，暂不支持指定配送时间。</span>
                </div>
            </div>
        </div>
        <c:if test="${out == 0 }">
	        <div class="balances-wrap">
	            <div class="balances-wrap-title">跨境商品</div>
	            <div class="ins clearfix">
	                <div class="ins-left">身份证信息</div>
	                <div class="ins-right clearfix">
	                    <div class="input-group clearfix input-group-blances">
	                        <div class="label">身份证姓名</div>
	                        <div class="item-input"><input type="text" id="userName" class="default"></div>
	                    </div>
	                    <div class="input-group clearfix input-group-blances">
	                        <div class="label">身份证号码</div>
	                        <div class="item-input item-input2"><input id="card" type="text" class="default"></div>
	                    </div>
	                </div>
	            </div>
	        </div>
        </c:if>
        <input type="hidden" id="out" value="${out }"/>
        <input type="hidden" id="isDelivery" value="${isDelivery }">
        <input type="hidden" id="userOrcount" value="0"/>
         <div class="balances-wrap" id="showInvoice" style="margin-bottom: 0;text-align: center;">
	       <div class="ly-30">更多<i class="iconfont">&#xe616;</i></div>
         </div>
        <div class="balances-wrap"  id="invoice" style="border-top:0;display: none;">
            <div class="balances-wrap-title balances-wrap-title2">发票信息<i class="iconfont notice">&#xe63a;</i>
                <div class="notice-text">个人账户支付金额在购卡是已经开具发票，不再重复开具；因国家政策要求跨境商品不开具发票。</div>
            </div>
            <div class="row">
                <div class="ly-30">抬头类型:</div>
                <div class="ly-70"><input type="radio" class="invType" checked="checked" value="0" name="invoce"/>个人 <input type="radio" class="invType" value="1" name="invoce"/>公司</div>
            </div>
            <div class="row">
                <div class="ly-30">发票内容:</div>
                <div class="ly-70">
                    <select>
	                      <option>详情</option>
                    </select>
                </div>
            </div>
            <div class="row" id="countName" style="display: none;">
                <div class="ly-30">公司名称:</div>
                <div class="ly-70">
                     <input type="text" id="companyName" class="invoce">
                </div>
            </div>
            <div class="row" id="invoce" style="display: none;" >
                <div class="ly-30">纳税人识别号:</div>
                <div class="ly-70">
                     <input type="text" id="taxpayerNo" class="invoce">
                </div>
            </div>
        </div>
        <div class="balances-wrap clearfix" style="margin-top: 15px;">
            <div class="balances-wrap-title">商品清单<span onclick="window.location='/cart/shoppingCart'" class="back-some">返回修改购物车</span></div>
            <table class="shoped">
                <tr class="table-head">
                    <td class="shoped-info">商品信息</td>
                    <td class="text-center">单价</td>
                    <td class="text-center">购买数量</td>
                    <td class="text-center">税费</td>
                    <td class="text-center">总金额</td>
                    <td class="text-center">配送范围</td>
                    <td class="text-center">积分</td>
                </tr>
                <c:forEach items="${cartList }" var="goods">
                <tr class="shoped-con" data-goodsid="${goods.id }">
                	<input type="hidden" value="${goods.id }"/>
                    <td class="shoped-info2" >
                        <div class="img-wrapa"><img src="${goods.imgPath }" alt=""></div>
                        <div class="info-des">
                            <p class="info-des-title">${goods.name } 
                            	<c:if test="${!empty goods.presentGoods.name }">
	                             		<br/>
	                             		<p class="info-des-title" style="color: red;">赠品：${goods.presentGoods.name }</p>
	                             </c:if>
                            </p>
                            <c:if test="${!empty goods.activityName }">
                           		<p class="price">${goods.activityName }</p>
                            </c:if>
                        </div>
                    </td>
                    <td class="text-center">
                    	<c:if test="${goods.actPoints!=null && goods.actPoints!=0 }">
                         	${goods.actPoints }积分
                         </c:if>
                         <c:if test="${goods.actPoints==null || goods.actPoints==0 }">
                         	¥<c:if test="${empty goods.activityPrice }">${goods.price }</c:if>
                          		<c:if test="${!empty goods.activityPrice }">${goods.activityPrice }</c:if>
                         </c:if>
                    </td>
                    <td class="text-center">${goods.cartNum }</td>
                    <td class="text-center"><s>¥ 0.00</s></td>
                    <td class="text-center price"><c:if test="${goods.actPoints==null || goods.actPoints==0 }">
	                     	${goods.sumPrice }
	                     </c:if>
	                     <c:if test="${goods.actPoints!=null && goods.actPoints!=0 }">
	                     	${goods.sumPoints }积分
	                     </c:if></td>
                    <td class="text-center">
                    	<c:if test="${goods.isDelivery==0 }">全国配送</c:if>
                    	<c:if test="${goods.isDelivery==1 }">重庆主城</c:if>
                    </td>
                    <td class="text-center"><fmt:formatNumber value="${goods.actPoints==0?goods.sumPrice:0 }" pattern="#"/></td>
                </tr>
                </c:forEach>
            </table>
            <div class="balances-bottom clearfix">
                <div class="col-60">
                    <div class="input-group clearfix">
                        <div class="label">备注 :</div>
                        <div class="item-input"><textarea name="bz" id="remark" cols="30" rows="10"></textarea></div>
                    </div>
                </div>
                <div class="col-40">
                	<!-- 商品的总金额 sumPrice -->
                    <div class="clearfix">
                        <p class="col-50-r">商品总金额(含税) :</p>
                        <p class="col-50-r sumPrice" value="${sumPrice }">¥${sumPrice }</p>
                        <input type="hidden" id="totalMoney" value="${sumPrice }"> <!--订单商品金额   -->
                    </div>
                    <div class="clearfix">
                        <p class="col-50-r">活动优惠 :</p>
                        <p class="col-50-r" id="feeyMoney">¥0.00</p>
                    </div>
                    <div class="clearfix">
                        <p class="col-50-r">可获积分 :</p>
                        <p class="col-50-r"><fmt:formatNumber value="${sumPrice }" pattern="#"/></p>
                    </div>
                    <div class="clearfix">
                        <p class="col-50-r">消耗积分 :</p>
                        <p class="col-50-r">-${sumPoints }</p>
                    </div>
                    <div class="clearfix">
                        <p class="col-50-r">运费 :</p>
                        <p class="col-50-r freight">¥0.00</p>
                    </div>
                    <div class="clearfix">
                        <p class="col-50-r">总税费 :</p>
                        <p class="col-50-r">¥0.00</p>
                    </div>
                    <div class="clearfix">
                        <p class="col-50-r">应付金额(含运费) :</p>
                        <p class="col-50-r totalMoney" >¥${sumPrice }</p>
                        <input type="hidden" id="freight" value="0"> <!-- 计算出的运费  -->
                    </div>
                    <div class="clearfix seclect">
                        <p class="col-50-r">优惠抵用券 :</p>
                        <p class="col-50-r clearfix">
                            <input type="text" id="coupon" class="quan text-center" value="${not empty couponList ? '选择使用优惠券':'暂无优惠卷信息' }"><i class="iconfont"></i>
                            <span id="nv">￥-0.00</span>
                            <input type="hidden" id="selectedquta" value="0"><!-- 选着使用的优惠券满多少能使用 -->
                        </p>
                    </div>
                    <div class="last-balances">
                    	 <input type="hidden" id="sumMoney" value="${sumPrice }"> <!-- 订单总金额 -->
                        <span>应付总金额 : </span><span class="price sumPrice payMoney">¥${sumPrice }</span><button type="submit" class="btn-balances">提交订单</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--内容部分完-->
  <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  <script type="text/javascript" src="resource/js/order/commit_order.js"></script>
  <script type="text/javascript" src="resource/js/common/area.js?v=201802081123"></script> 
  <script type="text/javascript">_init_area();</script>
<!-- 弹窗优惠卷部分 -->
<div class="popup blances-modal">
    <div class="model">
        <div class="modal-title clearfix">
            <span>优惠券信息</span><span class="close"><i class="iconfont">&#xe676;</i></span>
        </div>
        <div class="modal-content">
            <div class="orz-line">
                <label class="yh-label">优惠券码 : </label><input type="text" class="yh-input" id="couponCode"><button type="button" class="btn-theme6" id="couBtn">点击绑定</button>
            </div>
            <div class="category-content-block">
                <ul class="bimmg clearfix coupon-list" style="max-height:317px;overflow:hidden;">

                </ul>
            </div>
            <div class="modal-bottom bimmg">
                <span class="before-p">< 上一页</span> <span class="next-p">下一页 ></span><button type="button" class="btn-theme6 btn-theme7">确认</button>
            </div>
        </div>
    </div>
</div>
<div id="hideDiv" style="display: none;" >
	<form id="form" action="order/addOrder" method="post">
		<input type="text" name="adress" id="addressId"  />
		<input type="text" name="dateTime" id="datetime"/>
		<input type="text" name="invocie" id="invocie"/>
		<input type="text" name="goodsIds" id="goodsIds"/>
		<input type="text" name="remark" id="remarks"/>
		<input type="text" name="conponId" id="conponId"/>
		<input type="text" name="idCard" id="idCard"/>
		<input type="text" name="taxpayerNo" id="taxNo"/><!-- 纳税人识别号 -->
		<input type="text" name="companyName" id="comName"> <!-- 纳税 公司名称 -->
		<input type="text" name="consigneeName" id="consigneeName"/>
		<input type="text" name="consigneePhone" id="consigneePhone"/>
		<input type="text" name="consigneeAddress" id="consigneeAddress"/>
		<input type="text" name="provinceAddress" id="provinceAddress"> <!-- 收货地址省市 计算运费用到 -->
		<input type="text" name="cityAddress" id="cityAddress"> <!-- 收货地址 市 计算运费用到 -->
		<input type="text" name="countyAddress" id="countyAddress"> <!-- 收货地址 区县  计算运费用到-->
		<input type="text" name="accPoints" id="accPoints" value="${accPoints }">
		<input type="text" name="sumPoints" id="sumPoints" value="${sumPoints }">
		<input type="text" name="isCrossGoods" value="${out }">
		<input type="text" id="isLogin" value="${isLogin }">
	</form>
</div>
</body>
<script type="text/javascript">
	var i = 0;
	$("#coupon").click(function (){
		if(i==0){
			$.ajax({
				url:"order/couponListPopup",
				type:"POST",
				dataType:"html",
				data:{"pageIndex":1},
				success:function(data){
					$(".coupon-list").html(data);
				}
			});
			i=1;
		}
	})

	$(".before-p").click(function (){
		var pageIndex=$("#pageIndex").val();
		var totalPage=$("#totalPage").val();
		if(pageIndex>1){
			$.ajax({
				url:"order/couponListPopup",
				type:"POST",
				data:{"pageIndex":pageIndex-1},
				dataType:"html",
				success:function(data){
					$(".coupon-list").html(data);
				}
			});
		} 
	})
	
	$(".next-p").click(function (){
		var pageIndex=$("#pageIndex").val();
		var totalPage=$("#totalPage").val();
		pageIndex = parseInt(pageIndex)+1;
		if(pageIndex<=parseInt(totalPage)){
			$.ajax({
				url:"order/couponListPopup",
				type:"POST",
				data:{"pageIndex":pageIndex},
				dataType:"html",
				success:function(data){
					$(".coupon-list").html(data);
				}
			});
		} else {
			pop("已经到底了亲")
		}
		
	})

</script>
</html>
