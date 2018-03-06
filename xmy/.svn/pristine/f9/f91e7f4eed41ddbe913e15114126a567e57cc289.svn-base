<%@ page language="java" pageEncoding="UTF-8" errorPage="/commons/error/500.jsp"%>
<%@ taglib prefix="xmy-cms" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="layui-header header header-global">
  <div class="layui-main">
    <a class="logo" href="/">
      <img src="commons/images/logo.png"/>
    </a>
    <!-- <div class="layui-form component">
      <select lay-search lay-filter="component">
        <option value="">搜索组件或模块</option>
        <option value="element/color.html">color 颜色</option>
        <option value="element/icon.html">iconfont 字体图标</option>
        <option value="element/button.html">button 按钮</option>
        <option value="element/form.html">form 表单组</option>
        <option value="element/form.html#input">input 输入框</option>
        <option value="element/form.html#select">select 下拉选择框</option>
        <option value="element/form.html#checkbox">checkbox 复选框</option>
        <option value="element/form.html#switch">switch 开关</option>
        <option value="element/form.html#radio">radio 单选框</option>
        <option value="element/form.html#textarea">textarea 文本域</option>
        <option value="element/nav.html">nav 导航菜单</option>
        <option value="element/nav.html#breadcrumb">breadcrumb 面包屑</option>
        <option value="element/tab.html">tabs 选项卡</option>
        <option value="element/table.html">table 表格</option>
        <option value="element/progress.html">progress 进度条</option>
        <option value="element/collapse.html">collapse 折叠面板/手风琴</option>
        <option value="element/auxiliar.html#blockquote">blockquote 引用块</option>
        <option value="element/auxiliar.html#fieldset">fieldset 字段集</option>
        <option value="modules/layer.html">layer 弹出层/弹窗综合</option>
        <option value="modules/layim.html">layim 即时通讯/聊天</option>
        <option value="modules/laydate.html">laydate 日期时间选择器</option>
        <option value="modules/laypage.html">laypage 分页</option>
        <option value="modules/laytpl.html">laytpl 模板引擎</option>
        <option value="modules/form.html">form 表单模块</option>
        <option value="modules/element.html">element 常用元素操作</option>
        <option value="modules/layedit.html">layedit 富文本编辑器</option>
        <option value="modules/upload.html">upload 文件/图片上传</option>
        <option value="modules/tree.html">tree 树形菜单</option>
        <option value="modules/util.html">util 工具集</option>
        <option value="modules/flow.html">flow 信息流/图片懒加载</option>
        <option value="modules/code.html">code 代码修饰</option>
      </select>
    </div> -->
    <ul class="layui-nav" pc>
    <c:forEach items="${sessionScope.findListDto }" var="menu">
    	<c:if test="${!empty menu.childMenu}">
    		<c:if test="${menu.parentId==0 }">
    			<c:if test="${menu.id == param.topMenuid}">
			    	<li class="layui-nav-item layui-this" index="${menu.seq }">
			        	<a href="sys/findChilMenu/${menu.id }?topMenuid=${menu.id}">${menu.name }</a>
			        </li>
		         </c:if>
		         <c:if test="${menu.id != param.topMenuid}">
			    	<li class="layui-nav-item" index="${menu.seq }">
			        	<a href="sys/findChilMenu/${menu.id }?topMenuid=${menu.id}">${menu.name }</a>
			        </li>
		         </c:if>
	         </c:if>
        </c:if>
    </c:forEach>
    <script type="text/javascript" src="commons/js/jquery-3.2.1.js"></script>
	<script type="text/javascript">
	
	/* 	if (typeof jQuery != 'undefined') {
		alert("jquery已经被加载");
		}
		else
		{
		alert("jquery没有被加载");
		} */ 
		function saveTopMenu(id){
			$.ajax({
				url: "sys/loadTopMenu/"+id,
				type: "get",
			});
		}
	</script>
	<li class="layui-nav-item">
	    <a class="javascript:;" href="javascript:;">用户名：${sessionScope.sysUser.name }</a>
	    <dl class="layui-nav-child">
	     <dd class="left-menu" index="6">
	        <a href="sys/change_pwd.jsp">修改密码</a>
	      </dd>
	      <dd class="left-menu" index="5">
	        <a href="sys/login">安全退出</a>
	      </dd>
	    </dl>
  	</li>
    </ul>
      <%--<li class="layui-nav-item top-menu" index="1">
        <a href="javascript:;">商品管理</a>
      </li>
      <li class="layui-nav-item top-menu" index="2">
        <a href="javascript:;">订单管理</a>
      </li>
      <li class="layui-nav-item top-menu" index="3" pc>
        <a href="javascript:;">用户管理</a>
      </li>
      <li class="layui-nav-item top-menu" index="4" pc>
        <a href="javascript:;">商城设置</a>
      </li>
      <%--<li class="layui-nav-item layui-this">
        <a href="/demo/">示例</a>
      </li>
      <!--<li class="layui-nav-item" pc>
        <a href="/xx/" target="_blank">后台</a>
      </li>-->
      <li class="layui-nav-item" pc>
        <a href="http://fly.layui.com/" target="_blank">社区</a>
      </li>
      <li class="layui-nav-item" pc>
        <a href="javascript:;">周边</a>
        <dl class="layui-nav-child">
          <dd><a href="http://layim.layui.com/" target="_blank">即时聊天</a></dd>
          <dd><a href="http://fly.layui.com/jie/8157.html" target="_blank">社区模板</a></dd>
          <dd><a href="http://fly.layui.com/jie/9842.html" target="_blank">Axure组件</a></dd>
        </dl>
      </li> --%>
	    </ul>
  </div>
</div>
