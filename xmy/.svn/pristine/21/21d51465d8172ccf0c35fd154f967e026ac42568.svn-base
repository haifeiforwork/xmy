<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%-- 与首页雷同的nav公共部分 --%>
<nav>
    <%@include file="/WEB-INF/jsp/commons/nav/comm_top.jsp" %>
    <div class="outer" id="top">
        <div class="header">
        	<a href="index">
            	<div class="logo" style="cursor: pointer;"><img src="resource/commons/images/logo.png"/></div>
            </a>
            <div class="max-search">
                <div class="search">
                    <input type="text" id="searchContent">
                    <button type="button" id="search">搜索</button>
                    <div class="search-box" id="searchList" style="display:none;">
                    	<!-- <ul>
                    		<li>dajidjaijdsaoijiad</li>
                    	</ul>
                    	<div class="all-li">总记录数 : <span style="color:red">4564</span></div> -->
                    </div>
                </div>
                <div class="kis"><span>热门搜索 : </span>
                	<c:forEach items="${sessionScope.keyWords }" var="str">
	                	<a href="elastic/goods?goodsName=${str }">${str }</a>
                	</c:forEach>
	               <!--  <a href="elastic/goods?goodsName=脐橙">脐橙</a>
	                <a href="elastic/goods?goodsName=福临门">福临门</a>
	                <a href="elastic/goods?goodsName=葡萄酒">葡萄酒</a>
	                <a href="elastic/goods?goodsName=洗衣液">洗衣液</a> -->
	                <a href="elastic/goods?goodsName=" class="add-dominant-color">所有商品</a>
                </div>
            </div>
            <div class="tell"><img src="resource/commons/images/400.png"/></div>
        </div>
    </div>
</nav>


