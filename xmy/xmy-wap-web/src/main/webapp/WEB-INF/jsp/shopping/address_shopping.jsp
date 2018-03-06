<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>

<!-- 收货地址弹窗 -->
<body ontouchstart>	
	<header>
    	<div class="header-top">
    		<div class="pull-left"><img src="resources/common/images/btn-fenl-back@2x.png"/></div>
    		<div class="pull-center">收货地址</div>
    	</div>
    </header>
	<div class="content">
		<c:if test="${empty addressList || fn:length(addressList) <= 0}">
			<div style="text-align:center;padding:2rem 0;color:#999;font-size:0.75rem;">
   				<img alt="" src="resources/common/images/empty.png" style="margin:0 0 0.5rem;width: 3rem;"/>
   				<p>您还没有添加收货地址</p>
   			</div>
		</c:if>
		<c:if test="${not empty addressList && fn:length(addressList) > 0}">
    	<div class="list">
    		<c:forEach items="${addressList }" var="address">
    			<form>
	            	<div class="chunk">
		                <div class="chunk-title"><span>${address.name }</span><span class="phone">${address.mobilePhone }</span></div>
		                <div class="address">${address.province }${address.city }${address.area }${address.address }</div>
		                <div class="lump">
		                    <div class="sigin <c:if test='${address.isDefault != 0 }'>active</c:if>">
		                         <!--  <div class="yuan"></div><span>默认地址</span> -->
		                    </div>
		                    <div class="handle">
		                        <span class="edit"><img src="resources/common/images/btn-gldz-edit@2x.png"/><span class="text">编辑</span></span>
		                        <span class="del"><img src="resources/common/images/btn-gldz-del@2x.png"/><span class="text">删除</span></span>
		                    </div> 
		                </div>
			            <input type="hidden" name="id" value="${address.id }" >
			            <input type="hidden" name="name" value="${address.name }" >
			            <input type="hidden" name="mobilePhone" value="${address.mobilePhone }" >
			            <input type="hidden" name="province" value="${address.province }" >
			            <input type="hidden" name="city" value="${address.city }" >
			            <input type="hidden" name="address" value="${address.address }" >
			            <input type="hidden" name="area" value="${address.area }" >
			            <input type="hidden" id="sessionId" value="${wap_session.userId }">
		            </div>
	            </form>
           </c:forEach>
    	</div>
    	</c:if>
   	</div>
    <footer>
        <button class="add-address" type="button">添加新地址</button>
    </footer>
</body>