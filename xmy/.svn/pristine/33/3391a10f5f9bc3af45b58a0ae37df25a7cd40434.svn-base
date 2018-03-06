<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common_tag.jsp"%>


	<c:if test="${not empty json}">
    <div class="item-title container-wrap add-distance2 container-white">
        <!-- <p>承运来源 : <span>顺丰快递</span></p> -->
        <c:if test="${json.logisticsType == 0}">
        	<p>承运来源 : <span>香满园物流</span></p>
        </c:if>
        <c:if test="${json.logisticsType == 1}">
        	<p>承运来源 : <span>${json.logisticsData.name}</span></p>
        </c:if>
        <p>物流编号: <span>${json.logisticsData.logisticsNo}</span></p>
    </div>
    <div class="logistics">
    		<ul>
	        	<c:if test="${json.logisticsType == 0}">
	        		<c:if test="${not empty json.logisticsData.address }">
	        			<li class="logistics-item">
			                <div class="left-inner col-20"><div class="circle active" style="background-color: #348811;height:0.9rem;width: 0.9rem;border-radius: 0.45rem;"></div><div class="line"></div></div>
			                <div class="right-inner col-80"><p class="item-title">${json.logisticsData.driver }</p><p class="item-after">${json.logisticsData.address }</p></div>
			            </li>
	        		</c:if>
	        		<c:if test="${empty json.logisticsData.address }">
	        			<div style="text-align: center;color:#666;">暂无地址信息</div>
	        		</c:if>
	        	</c:if>
	        	<c:if test="${json.logisticsType == 1}">
	        		<c:if test="${fn:length(json.logisticsData.Traces) <= 0 }">
	        			<div style="text-align: center;color:#666;">暂无地址信息</div>
	        		</c:if>
	        		<c:set var="index" value="${fn:length(json.logisticsData.Traces) }"></c:set>
	        		<c:forEach items="${json.logisticsData.Traces }" var="traces">
	        			<c:set var="index" value="${index - 1 }"></c:set>
	        			<li class="logistics-item">
			                <div class="left-inner col-20"><div class="circle active" <c:if test="${index == (fn:length(json.logisticsData.Traces) - 1)}">style="background-color: #348811;height:0.9rem;width: 0.9rem;border-radius: 0.45rem;"</c:if>></div><div class="line"></div></div>
			                <div class="right-inner col-80"><p class="item-title">${json.logisticsData.Traces[index].AcceptTime }</p><p class="item-after">${json.logisticsData.Traces[index].AcceptStation }</p></div>
			            </li>
	        		</c:forEach>
	        	</c:if>
	           <!--  <li class="logistics-item">
	                <div class="left-inner col-20"><div class="circle"></div><div class="line"></div></div>
	                <div class="right-inner col-80"><p class="item-title">快件已到达【重庆市】</p><p class="item-after">快件已到达【重庆市】</p></div>
	            </li>
	            <li class="logistics-item">
	                <div class="left-inner col-20"><div class="circle"></div><div class="line"></div></div>
	                <div class="right-inner col-80"><p class="item-title">快件已到达【重庆市】</p><p class="item-after">快件已到达【重庆市】</p></div>
	            </li>
	            <li class="logistics-item">
	                <div class="left-inner col-20"><div class="circle"></div><div class="line"></div></div>
	                <div class="right-inner col-80"><p class="item-title">快件已到达【重庆市】</p><p class="item-after">快件已到达【重庆市】</p></div>
	            </li> -->
	        </ul>
    </div>
    </c:if>
    <c:if test="${empty json}">
    	<div style="text-align: center;color:#666;margin-top: 7rem;">暂无物流信息</div>
    </c:if>
