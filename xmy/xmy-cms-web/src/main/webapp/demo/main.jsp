<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>主页 - cms后台模板</title>
    <%@ include file="/commons/comm_css.jsp"%>
</head>

<body>
    <blockquote class="layui-elem-quote">
        下载： <a href="https://github.com/buexplain/layuicms/archive/master.zip" target="_blank">点击下载</a>
        <hr>
        说明：
        <br>
        这是一个简单的后台模板布局
        <br>
        侧边栏导航只能支持到二级，什么时候支持到三级就看 <a href="https://github.com/sentsin/">贤心</a> 大神了。
    </blockquote>
    <%@ include file="/commons/comm_footer_js.jsp"%>
    <script type="text/javascript">
        /**
         * 对layui进行全局配置
         */
        layui.config({
            base: 'demo/js/'
        });
    </script>
</body>
</html>