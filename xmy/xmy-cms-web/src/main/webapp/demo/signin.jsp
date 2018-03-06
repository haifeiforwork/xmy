<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录 - cms后台模板</title>
    <%@ include file="/commons/comm_css.jsp"%>
</head>

<body style="background-color: #f5f5f5;">
    <fieldset class="layui-elem-field" style="width: 380px; margin:0 auto; margin-top: 10%; box-shadow: 0 0 10px #d9edf7;">
        <legend>
            登录
        </legend>
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    账号
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="username" required lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    密码
                </label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="signin">
                        立即提交
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary">
                        重置
                    </button>
                </div>
            </div>
        </form>
    </fieldset>
    <%@ include file="/commons/comm_footer_js.jsp"%>
    <script type="text/javascript">
        /**
         * 对layui进行全局配置
         */
        layui.config({
            base: 'demo/js/'
        });

        layui.use('form', function() {
            //监听提交
            layui.form().on('submit(signin)', function(data){
                layer.msg(JSON.stringify(data.field));
                //这里可以发起ajax请求进行登录验证
                return false;
            });

            //修正登录框margin
            var fieldset = layui.jquery("fieldset").eq(0);
            fieldset.css("margin-top", (layui.jquery(window).height() - fieldset.height()) * 0.3 + "px");
        });
    </script>
</body>
</html>