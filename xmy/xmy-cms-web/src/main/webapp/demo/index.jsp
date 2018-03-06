<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>首页测试</title>
    <%@ include file="/commons/comm_css.jsp"%>
</head>

<body>
    <!-- 布局容器 -->
    <div class="layui-layout layui-layout-admin">
        <!-- 头部 -->
        <%@ include file="/commons/nav.jsp"%>
        <!-- 侧边栏 -->
        <%@ include file="/commons/left_menu.jsp"%>
        <!-- 主体 -->
        <div class="layui-body">
            <!-- 顶部切换卡 -->
            <div class="layui-tab layui-tab-brief" lay-filter="top-tab" lay-allowClose="true" style="margin: 0;">
                <ul class="layui-tab-title"></ul>
                <div class="layui-tab-content"></div>
            </div>
        </div>
        <!-- 底部 -->
        <%@ include file="/commons/buttom.jsp"%>
    </div>

    <%@ include file="/commons/comm_footer_js.jsp"%>
    <script type="text/javascript">
	   /**
	    * 对layui进行全局配置
	    */
        layui.config({
            base: 'demo/js/'
        });

        /**
         * 初始化整个cms骨架
         */
        layui.use(['cms'], function() {
            var cms = layui.cms('left-nav', 'top-tab');

            cms.addNav([
                {id: 1, pid: 0, node: '主页', url: 'demo/main.jsp'},
                {id: 7, pid: 0, node: '登录', url: 'demo/signin.jsp'},
                {id: 2, pid: 0, node: '搜索引擎', url: ''},
                {id: 3, pid: 2, node: '百度', url: 'https://www.baidu.com/'},
                {id: 4, pid: 2, node: '必应', url: 'http://cn.bing.com/'},
                {id: 5, pid: 2, node: '360', url: 'https://www.so.com/'},
                {id: 6, pid: 2, node: '搜狗', url: 'https://www.sogou.com/'},
            ], 0, 'id', 'pid', 'node', 'url');

            cms.bind(60 + 41 + 20 + 44); //头部高度 + 顶部切换卡标题高度 + 顶部切换卡内容padding + 底部高度

            cms.clickLI(0);
        });
    </script>
</body>
</html>