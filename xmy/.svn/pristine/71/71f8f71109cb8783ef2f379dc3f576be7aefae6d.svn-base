<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!--修改或添加收货地址-->
    <body ontouchstart>	
    <header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">编辑地址</div>
            <div class="pull-right save-address">保存</div>
    	</div>
    </header>
	<div class="content">
    	<div class="list">
			<div class="weui-cells weui-cells_form">
				<form id="addAddressForm" method="post">
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><label for="" class="weui-label">收货人</label></div>
	                    <div class="weui-cell__bd">
	                        <input class="weui-input" type="text" name="name" value="${userAddress.name }" placeholder="请输入收货人名称">
	                    </div>
	                </div>
	                <div class="weui-cell">
	                    <div class="weui-cell__hd"><label for="" class="weui-label">联系电话</label></div>
	                    <div class="weui-cell__bd">
	                        <input class="weui-input" type="number" name="mobilePhone" value="${userAddress.mobilePhone }" placeholder="请输入收货人电话">
	                    </div>
	                </div>
	                <a class="weui-cell weui-cell_access address-choose-div" href="javascript:;">
	                    <div class="weui-cell__hd">
	                        <label for="" class="weui-label">地址</label>
	                    </div>
	                    <div class="weui-cell__bd">
	                    	<c:choose>
	                    		<c:when test="${not empty userAddress.province && not empty userAddress.city && not empty userAddress.area}">
	                    			<input class="weui-input area-chose" type="text" id="city-picker" placeholder="请选择地址" value="${fn:replace(userAddress.province,'市','') } ${userAddress.city } ${userAddress.area }">
	                    		</c:when>
	                    		<c:otherwise>
	                    			<input class="weui-input area-chose" type="text" id="city-picker" placeholder="请选择地址" value="">
	                    		</c:otherwise>
	                    	</c:choose>
	                    </div>
	                      <div class="weui-cell__ft"></div>
	                </a>
	                <a class="weui-cell weui-cell_access" href="javascript:;">
	                    <div class="weui-cell__hd">
	                      <label for="" class="weui-label">详细地址</label>
	                    </div>
	                    <div class="weui-cell__bd">
	                        <input class="weui-input" type="text" name="address" value="${userAddress.address }">
	                    </div>
	                </a>
	                <input type="hidden" id="addressId" name="id" value="${userAddress.id }">
	                <input type="hidden" name="province" id="province" value="${userAddress.province }">
	                <input type="hidden" name="city" id="city" value="${userAddress.city }">
	                <input type="hidden" name="area" id="area" value="${userAddress.area }">
	                <input type="hidden" id="sessionId" value="${wap_session.userId }">
	                <input type="hidden" name="type" value="0">
	                <input type="hidden" name="flag" value="saveOrUpdate">
	            </div>
            </form>
            <div class="delete-address">
                	删除地址
            </div>
    	</div>
   	</div>
    </body>
