<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>添加投诉与建议</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_add_advise.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">我的投诉与建议</div>
                <form action="center/addAdvise" method="post">
                	<div class="category-content">
	                    <div class="input-group clearfix">
	                        <div class="label">标题 :</div>
	                        <div class="item-input"><input name="title" type="text" class="default"></div>
	                    </div>
	                    <div class="input-group clearfix">
	                        <div class="label">类型 :</div>
	                        <div class="item-input">
	                            <label for="complaint" class="item-radio"><input type="radio" checked="checked" name="genre" value="0"  id="complaint" >投诉</label>
	                            <label for="proposal"  class="item-radio"><input type="radio"  name="genre" value="1"  id="proposal">建议</label>
	                        </div>
	                    </div>
	                    <div class="input-group clearfix">
	                        <div class="label">订单编号 :</div>
	                        <div class="item-input"><input name="no" type="text" class="default" value="${orderNo }"></div>
	                    </div>
	                    <div class="input-group clearfix">
	                        <div class="label">内容 :</div>
	                        <div class="item-input"><textarea name="content" class="content-block"></textarea></div>
	                    </div>
	                    <div class="btn-group add-distance">
	                        <button type="submit" class="btn-theme btn-default3 btn-default">提交投诉与建议</button><button type="reset" class="btn-default3 btn-default">取消</button>
	                    </div>
                	</div>
                </form>
            </div>
        </div>
    </div>
</div>
    <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
  </body>
</html>