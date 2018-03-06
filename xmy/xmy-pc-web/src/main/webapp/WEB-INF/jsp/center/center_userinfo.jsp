<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>个人信息</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link href="resource/commons/css/jedate.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_userinfo.css">
	<!-- <link href="resource/commons/css/foundation-datepicker.css" rel="stylesheet" type="text/css">
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet"> -->
	
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">个人信息</div>
                <div class="category-content safe-check">
                    <div class="cate-content-title clearfix">
                        <ul class="cate-nav add-pad">
                            <li class="active" >基本资料</li>
                            <li onclick="window.location='/center/toUpdateImg?set=5'">头像照片</li>
                        </ul>
                    </div>
                    <form action="center/update" method="post">
                    	<div class="cate-table">
                        <div class="add-distance5 clearfix">
                            <div class="col-70">
                                <%-- <div class="input-group clearfix">
                                    <div class="label label2">用户名:</div>
                                    <input name="id" value="${userinfo.id}" type="hidden">
                                    <div class="item-input item-input2"><input name="realName" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px;height:30px;border: 1px solid #ddd;padding-left:10px;' value="${userinfo.realName }"></div>
                                </div> --%>
                                 <div class="input-group clearfix">
                                    <div class="label label2">手机号码 :</div>
                                    <div class="item-input item-input2"><input type="text" class="default" name="mobilePhone" value="${userinfo.mobilePhone }" readonly="readonly"> <!-- <button  onclick="window.location='center/toSafetyPhone?set=8'" type="hidden" class="btn-default binding">绑定</button> --></div>
                                </div>
                                <div class="input-group clearfix">
                                    <div class="label label2">性别 :</div>
                                    <div class="item-input item-input2">
                                        <label for="complaint" class="item-radio"><input value="0" type="radio" ${userinfo.sex==0?'checked':'' } name="sex"  id="complaint" >男</label>
                                        <label for="proposal"  class="item-radio"><input value="1" type="radio" ${userinfo.sex==1?'checked':'' }  name="sex"  id="proposal">女</label>
                                        <label for="secit"  class="item-radio"><input value="2" type="radio" ${userinfo.sex==2?'checked':'' } name="sex"  id="secit">保密</label>
                                    </div>
                                </div>
                                <div class="input-group clearfix">
                                    <div class="label label2">生日 :</div>
                                    <div class="item-input item-input2 canda">
                                        <input type="text"  class="default" name="birthday" value="<fmt:formatDate value="${userinfo.birthday }" pattern="yyyy 年MM 月dd"/>" placeholder="请选择" id="Rl"><span class="item-input-word item-input-word5">填生日有惊喜哦~</span>
                                    </div>
                                </div>
                               <!--  <div class="input-group clearfix">
                                    <div class="label label2">用户邮箱:</div>
                                    <div class="item-input item-input2"><p class="item-input-word item-input-word3">立即验证</p></div>
                                </div> -->
                                <div class="input-group clearfix">
                                    <div class="label label2">座机号码:</div>
                                    <div class="item-input item-input2"><input type="text" name="phone" value="${userinfo.phone }" class="default"></div>
                                </div>
                                <div class="btn-group add-distance2">
                                    <button type="submit" class="btn-default btn-person">提交</button>
                                </div>
                            </div>
                            <div class="col-30">
                                <div class="person-des clearfix">
                                    <div class="col-30"><img src="resource/commons/images/cstars/${empty userinfo.avatar?'defaultheadimg.png':userinfo.avatar }" alt=""></div>
                                    <div class="col-70">
                                        <p class="person-name">${userinfo.realName }</p>
                                        <p>绑定手机 : <span>${userinfo.mobilePhone }</span></p>
                                        <p>绑定邮箱 : <span>${userinfo.email }</span></p>
                                        <p>账户安全 : <span class="progress"><span class="lt"></span><span class="gt"></span></span><span class="progress-color">较高</span></p>
                                    </div>
                                </div>
                                <div class="tips-b">注 : 修改手机和邮箱请到 <span class="add-theme-color" style="cursor: pointer;" onclick="window.location='/center/toSafetyPhone?set=8'">账户安全</span></div>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
   	<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
   	<!-- <script type="text/javascript" src="resource/commons/js/foundation-datepicker.js"></script> -->
   	<script type="text/javascript" src="resource/commons/js/jquery.jedate.min.js"></script>
  <script type="text/javascript" src="resource/commons/js/fileUpload.js"></script>
  <script type="text/javascript" src="resource/js/center/center_userinfo.js"></script>
  </body>

</html>
