<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>收藏夹</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_goods_collection.css">
  </head>
  <body>
    <nav>
    	<%@include file="/WEB-INF/jsp/commons/nav/comm_top.jsp" %>
	</nav>
<div class="navigation">
    <div class="w-1250">
        <div class="navigation-left">
            <a href="index"><div class="left-chunk1"><img src="resource/commons/images/gr-logo.jpg"/></div></a>

            <div class="left-chunk2">
                <p class="my-xmy logo-near">收藏夹</p>
            </div>
        </div>
        <div class="navigation-right">
            <div class="right-chunk2 L-search">
                <input type="text" id="content" placeholder="搜索">
                <button type="text" id="search">搜索</button>
            </div>
        </div>
    </div>
</div>
<!--内容部分-->
<div class="content">
    <div class="w-1250 category">
        <div class="houese-top clearfix">
            <ul class="house-list-top">
                <li class="active">全部商品${count}</li>
            </ul>
            <div class="btn-input">
                <div class="line-btn-group">
                 	<button type="button" class="btn-default op-det" style="display:none">删除</button>
                    <button type="button" class="btn-default op-all">批量管理</button>
                </div>
                <div class="input-ad-btn clearfix">
                    <input type="text" name="goodsName" placeholder="商品搜索"><button type="submit"><i class="iconfont">&#xe602;</i></button>
                </div>
            </div>
        </div>
        <div class="category-title houese-top-title">
            <div>
                <ul class="houese-list">
                    <li onclick="window.location='/center/toCollect?pageIndex=1&set=14&category=null'" ${category eq 'null'?'class="add-bor-l active "':'class="add-bor-l"' }>全部商品(${count})</li>
                    <c:forEach items="${map }" var="map">
                   		<li ${category eq map.key?'class="active"':'' } onclick="window.location='/center/toCollect?pageIndex=1&set=14&category=${map.key}'">${map.key}(${map.value})</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="houese-content">
            <ul class="clearfix">
            	<c:if test="${empty pageGoods }">
	           		<li class="house-item" style="width: 100%;">
	                    <p style="text-align: center;padding:20px 0">暂无数据！</p>
	               	</li>
               	</c:if>
               	<c:if test="${!empty pageGoods }">
	                <c:forEach items="${pageGoods }" var="collect">
	                	<li class="house-item">
	                		<div class="check-circle" data-goodsid="${collect.goodsId }"><img src="resource/commons/images/col-xz.png" /></div>
		                    <div class="img-wrap">
		                    	<c:choose>
		                    		<c:when test="${not empty collect.activityId && collect.activityType == 2 }">
		                    			<a href="goods/${collect.goodsId}/${collect.activityType }/${collect.activityId}?time=${collect.activityGoodsTimeStr}&url=${collect.waterImg}"><img src="${collect.goodsImg }" alt=""></a>
		                    		</c:when>
		                    		<c:otherwise>
		                    			 <a href="goods/${collect.goodsId}/0/0"><img src="${collect.goodsImg }" alt=""></a>
		                    		</c:otherwise>
		                    	</c:choose>
		                    </div>
		                    <p class="house-word">${collect.name }</p>
		                    <p class="price text-center">
		                        ¥ ${collect.goodsPrice }
		                    </p>
	                	</li>
	                </c:forEach>
                </c:if>
            </ul>
        </div>
        <div class="one-paging clearfix" style="display:${pageBean.totalPage ==0? 'none':'block'} ">
            <div class="paging-all">
            	<c:if test="${pageBean.pageIndex==1}">
	               	<button style="cursor: pointer;" type="button" class="pre">上一页</button>
	            </c:if>
	            <c:if test="${pageBean.pageIndex!=1}">
                	<button type="button" style="cursor: pointer;" class="pre" onclick="window.location='/center/toCollect?pageIndex=${pageBean.pageIndex==1?1:pageBean.pageIndex-1}&set=14&category=${category }'">上一页</button>
                </c:if>
                	<a class="active">${pageBean.pageIndex }</a>
                <c:if test="${pageBean.pageIndex==pageBean.totalPage }">
                	<button type="button" style="cursor: pointer;" class="pre">下一页</button>
                </c:if>
                <c:if test="${pageBean.totalPage==0 }">
                	<button type="button" style="cursor: pointer;" class="pre">下一页</button>
                </c:if>
                <c:if test="${pageBean.pageIndex!=pageBean.totalPage&&pageBean.totalPage!=0 }">
                	<button type="button" style="cursor: pointer;" class="pre" onclick="window.location='/center/toCollect?pageIndex=${pageBean.pageIndex==pageBean.totalPage ? pageBean.totalPage : pageBean.pageIndex+1}&set=14&category=${category }'">下一页</button>
                </c:if>
            </div>
            <input type="hidden" id="countPage" value="${pageBean.totalPage }">
            <input type="hidden" value="${category }" id="category"/>
            <span>共${pageBean.totalPage }页</span>
            <div class="jump-paging">到 <input type="text" maxlength="4" name=""> 页 <button style="cursor: pointer;" type="button">确定</button></div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<%@include file="/WEB-INF/jsp/commons/comm_footer_js.jsp" %>
<script type="text/javascript">
$(document).ready(function () {
    $(".houese-top-title>div:gt(0)").hide();
    $(".house-list-top li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".house-list-top li").index(this);
        $(".houese-top-title>div").eq(index).show().siblings().hide()

    });
    $(".houese-content .house-item:nth-of-type(5n)").css("margin-right","0");
    $(".houese-content>ul:gt(0)").hide();
    $(".houese-list li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(".houese-list li").index(this);
        $(".houese-content>ul").eq(index).show().siblings().hide()
    });
    //搜索
    $("#search").click(function(){
    		var val=$("#content").val();
    		window.location.href = "/elastic/goods?goodsName="+val;
    });
    //跳转指定页数
    $("button").last().click(function(){
    	var text = $("input").last().val();
    	var category = $("#category").val();
    	var countPage = $("#countPage").val();
    	if(text.trim() == ""){
    		popWarn("不能为空")
			return false;
    	}
    	regNum = /^[0-9]*$/;
    	if(!regNum.test(text)){
    		popWarn("请输入数字")
			return false;
    	}
    	if (text > countPage) {
    		popWarn("输入的不能大于总页数")
    		return false;
    	}
    	window.location.href = "/center/toCollect?pageIndex="+text+"&set=14&category="+category;
    	
    });
    //根据商品名称搜索
   $(".input-ad-btn").find("button[type='submit']").click(function (){
	   var searchContent = $(".input-ad-btn").find("input[name='goodsName']").val();
    	window.location.href ="/center/toCollect?pageIndex=1&set=14&category=null&goodsName="+searchContent;
    })
    
    $(".jump-paging").keydown(function(e){
		if(e.keyCode==13){
			var text = $("input").last().val();
	    	var category = $("#category").val();
	    	var countPage = $("#countPage").val();
	    	if(text.trim() == ""){
	    		popWarn("不能为空")
				return false;
	    	}
	    	regNum = /^[0-9]*$/;
	    	if(!regNum.test(text)){
	    		popWarn("请输入数字")
				return false;
	    	}
	    	if (text > countPage) {
	    		popWarn("输入的不能大于总页数")
	    		return false;
	    	}
	    	window.location.href = "/center/toCollect?pageIndex="+text+"&set=14&category="+category;			
		}
	});
	$("body").on("click",".op-all",function(){
    	$(".houese-content .house-item .check-circle").toggle();
    	$(this).prev().toggle();
    });
    $(".houese-content").on("click",".check-circle",function(){
    	$(this).toggleClass("active");
    });
    
    // 删除收藏商品
    $(".op-det").click(function (){
    	//获取勾选的商品
    	var goodsid = "";
    	$(".houese-content ul li .active").each(function() {
    		goodsid = goodsid+ $(this).data("goodsid")+ ",";
    	})
    	if (goodsid != '') {
   		   $.get("center/batchCollectionGoods",{idStr:goodsid},function(data){
   	           location.reload();
   	       });
    	} 
    });
})

function popWarn(txt){
	var btnFn = function(){
		easyDialog.close();
		return false;
	}
	easyDialog.open({
	  container : {
	    header : "提示",
	    content : txt,
	    yesFn : btnFn
	  }
	});
}

</script>
  </body>
</html>
