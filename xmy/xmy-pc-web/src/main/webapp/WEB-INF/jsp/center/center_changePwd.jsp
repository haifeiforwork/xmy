<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>修改密码</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_changePwd.css">
  </head>
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">密码修改</div>
                <div class="category-content safe-check">
                    <div class="step-all clearfix">
                        <div class="step">
                            <div class="step1 iconn">1</div>
                            <p class="add-theme-color">验证身份</p>
                            <div class="line line-active"></div>
                        </div>
                        <div class="step">
                            <div class="step1 iconn">2</div>
                            <p class="change add-theme-color">修改密码</p>
                            <div class="line" id="xian"></div>
                        </div>
                        <div class="step last-step">
                            <div class="step2 iconn" id="num">3</div>
                            <p>完成</p>
                        </div>
                    </div>
                    <form action="center/changePwd" method="post">
                    	<div class="cate-table">
                        <div class="data-div">
                            <div class="input-group clearfix">
                                <div class="label label3">当前密码 :</div>
                                <div class="item-input item-input3"><input id="oldPwd" name="password" type="password" onblur="checkOld(this)" class="default"><span id="old" style="color: red;font-size:13px;margin-left:5px;"></span></div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label3">新的密码 :</div>
                                <div class="item-input item-input3"><input id="newPwd" name="pwd" type="password" onblur="checkNew(this)" class="default"><span id="new" style="color: red;font-size:13px;margin-left:5px;"></span></div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label3">确认新的密码 :</div>
                                <div class="item-input item-input3"><input type="password" onblur="checkVerify(this)" class="default"><span id="verify" style="color: red;font-size:13px;margin-left:5px;"></span></div>
                            </div>
                            <div class="btn-group add-distance3">
                                <button type="submit" class="btn-theme3 btn-default" onclick="return check(this.form)">确定</button>
                                <span id="msg" style="display: none;" >${msg }</span>
                            </div>
                        </div>
                        
                  			<!-- 成功后提示 -->
                        <div class="lsatstep" style="display: none;">
                        	<p><i class="iconfont">&#xe6ba;</i> 修改成功,请牢记新的登录密码</p>
                        </div> 
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<script type="text/javascript" src="resource/js/center/center_changePwd.js"></script>
<script type="text/javascript">
//密码修改成功
/* var result ="${msg}";
if (result != '') {
	easyDialog.open({
		  container : {
		    header : '提示',
		    content : result
		  }
	});
} */
</script>
</body>
</html>
