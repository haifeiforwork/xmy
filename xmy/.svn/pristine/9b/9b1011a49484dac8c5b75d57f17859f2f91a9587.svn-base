<%@ page language="java" pageEncoding="UTF-8" errorPage="/commons/error/500.jsp"%>
<%@ taglib prefix="xmy-cms" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="layui-side layui-bg-black">
<div class="layui-side-scroll">
<ul class="layui-nav layui-nav-tree site-demo-nav">
 <!--  <li class="layui-nav-item">
    <a class="javascript:;" href="javascript:;">商品管理</a>
    <dl class="layui-nav-child">
      <dd class="left-menu" index="1">
        <a href="javascript:;">商品列表</a>
      </dd>
      <dd class="left-menu" index="2">
        <a href="javascript:;">添加商品</a>
      </dd>
      <dd class="left-menu" index="3">
        <a href="javascript:;">商品图片</a>
      </dd>
      <dd class="left-menu" index="4">
        <a href="javascript:;">品牌管理</a>
      </dd>
    </dl>
  </li> -->
 <!--  <li class="layui-nav-item layui-nav-itemed">
    <a class="javascript:;" href="javascript:;">内容管理</a>
    <dl class="layui-nav-child">
     <dd class="left-menu" index="6">
        <a href="article/column/list">栏目管理</a>
      </dd>
      <dd class="left-menu" index="5">
        <a href="article/article/list">文章管理</a>
      </dd>
     
      <dd class="left-menu" index="7">
        <a href="article/notification/list">网站置顶通知</a>
      </dd>
    </dl>
  </li> -->
  <li class="layui-nav-item layui-nav-itemed">
    <a class="javascript:;" href="sys/welcome.jsp">欢迎界面</a>
  </li>
  <c:forEach items="${sessionScope.childList }" var="menu">
  <li class="layui-nav-item layui-nav-itemed">
  		<c:if test="${!empty menu.childMenu}">
  			<c:if test="${menu.parentId!=0 }">
	  			<a class="javascript:;" href="javascript:;"><i class="layui-icon" style="font-size: 18px; color: #fbfbfb;">&#xe60a;</i>${menu.name }</a>
	  			<dl class="layui-nav-child">
	  				<c:forEach items="${menu.childMenu }" var="menus">
	  					<c:if test="${menus.id == param.leftMenuid}">
				  			<dd class="layui-this" index="${menus.seq }">
						        <a href="${menus.targetUrl}">${menus.name }</a>
						    </dd>
					    </c:if>
					    <c:if test="${menus.id != param.leftMenuid}">
				  			<dd index="${menus.seq }">
						        <a href="${menus.targetUrl}?topMenuid=${param.topMenuid }&leftMenuid=${menus.id}">${menus.name }</a>
						    </dd>
					    </c:if>		
				    </c:forEach>
	  			</dl>
  			</c:if>
  		</c:if>
  </li>
  </c:forEach>
  <!-- <li class="layui-nav-item" style="height: 30px; text-align: center"></li> -->
</ul>
</div>
</div>
<script type="text/javascript" src="commons/js/jquery-3.2.1.js"></script>
<script type="text/javascript">

	/*  if (typeof jQuery != 'undefined') {
	alert("jquery已经被加载");
	}
	else
	{
	alert("jquery没有被加载");
	}  */
	function saveLeftMenu(sn){
		$.ajax({
			url: "sys/loadLeftMenu/"+sn,
			type: "get",
		});
	}
</script>