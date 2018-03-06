<%@ page language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/commons/error/500.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 公共的底部 --%>
<div class="base">
    <div class="virtue">
        <ul class="virtue-ul">
            <li class="virtue-li"><div class="xta"><p class="tit">香满圆</p><p class="details">100%保证商品新鲜度，及时送达顾客手中</p></div></li>
            <li class="virtue-li"><div class="xtb"><p class="tit">所有商家经官方认证</p><p class="details">官方认证，所有商家具有合法个体经营身份或企业资质</p></div></li>
            <li class="virtue-li remove"><div class="xtc"><p class="tit">网站担保交易</p><p class="details">网站官方担保交易，收到商品后再结算，有问题随时投诉</p></div></li>
            <div style="clear: both;"></div>
        </ul>
    </div>
    <div class="detailss">
        <ul class="detailss-ul">
        	<c:forEach items="${sessionScope.findDownColum }" var="termData" varStatus="ter">
	            <li class="detailss-li">
	           		<p class="tit">${termData.name }</p>
	           		<c:forEach items="${termData.childTermData }" var="childData" varStatus="chd">
	           			<a href="news/index?ter=${ter.index}&chd=${chd.index}" style="text-decoration:none;" class="detailss-lia"><p>${childData.name}</p></a>
	           		</c:forEach>
	            </li>
            </c:forEach>
            <li class="detailss-li last-li clearfix">
                <p class="img-wrapp first"><img src="resource/commons/images/B2Cwx.jpg"><span class="color-green text-center">扫码下载APP</span></p>
                <p class="img-wrapp"><img src="resource/commons/images/B2Cpc.png"><span class="color-green">扫码进入移动端</span></p>
            </li>
            <div style="clear: both;"></div>
        </ul>
      <!--   <div class="logo-wrap">
            <img src="resource/commons/images/logopic.png" alt="">
        </div> -->
    </div>

</div>
<footer>
    <div class="footer-auto">
       	<p>香满圆旗下：重庆菜园坝农产品市场集群（线下） |  B2C电商平台 | 社区超市订购平台（线上）</p>
		 <p class="mt10">Copyright  2009-2013 网上购物 All Rights Reserved 重庆香满圆农产品有限公司</p>
		 <p class="mt10">增值电信业务经营许可证：渝B2-20120004</p>
		 <p class="mt10"><a href="http://www.miitbeian.gov.cn" style="color: white;text-decoration: underline">渝ICP14001106号-8</a></p>
    </div>
   <div class="beian">
        <ul class="beian-ul">
           <a href="http://www.cqgseb.cn/ztgsgl/WebSiteMonitoring/WebUI/XFWQ/Index.aspx?xh=17" target="_blank"> <li class="beian-li"><img src="resource/commons/images/biean1.png"/></li></a>
           <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=50010302000205" target="_blank"><li class="beian-li"><img src="resource/commons/images/biean2.png"/></li></a>
           <a href="http://113.207.120.45:7010/wljyzbs/index.html?sfdm=120150115151516296646#/index.html?sfdm=120150115151516296646" target="_blank"><li class="beian-li"><img src="resource/commons/images/biean3.png"/></li></a>
           <a href="http://www.cqtransit.com/" target="_blank"><li class="beian-li"><img src="resource/commons/images/biean4.png"/></li></a>
           <a href="http://www.cqgyjt.com/" target="_blank"><li class="beian-li"><img src="resource/commons/images/biean5.png"/></li></a>
            <div style="clear: both;"></div>
        </ul>
    </div> 
</footer>
<%@include file="/WEB-INF/jsp/commons/comm_footer_js.jsp" %>
