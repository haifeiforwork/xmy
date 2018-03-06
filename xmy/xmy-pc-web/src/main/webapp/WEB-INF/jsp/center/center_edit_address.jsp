<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>收货地址</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_address.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">收货地址管理</div>
                <div class="category-content safe-check">
                    	<div class="cate-table">
                        <form action="center/updateAddress" method="post">
                        	<div class="input-group clearfix">
                            <div class="label">*收货人 :</div>
                            <input type="hidden" name="id" value="${userAddrees.id }">
                            <div class="item-input"><input type="text" name="name" onblur="checkName(this)" class="default" value="${userAddrees.name }"><span id="name" style="color: red"></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*地区 :</div>
	                            <div class="item-input select-all" >
	                                <select id="s_province" name="province" class="default" onblur="checkAdd();">
	                                	<option>--请选择--</option>
	                                </select>
	                                <select id="s_city" name="city" class="default" onblur="checkAdd();">
	                                	<option>--请选择--</option>
	                                </select>
	                                <select id="s_county" name="area" class="default" onblur="checkAdd();">
	                                	<option>--请选择--</option>
	                                </select>
	                            <span id="msg" style="color: red">
	                            </div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*详细地址 :</div>
	                            <div class="item-input"><input type="text" name="address" onblur="checkAddress(this)" class="default" value="${userAddrees.address }"><span id="address" style="color: red"></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*手机号码 :</div>
	                            <div class="item-input"><input type="text" name="mobilePhone" onblur="checkPhone(this)" class="default" value="${userAddrees.mobilePhone }"><span id="phone" style="color: red"></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">备用电话 :</div>
	                            <div class="item-input"><input type="text" name="phone" onblur="checkCall(this)" class="default" value="${userAddrees.phone }"><span id="call" style="color: red"></div>
	                        </div>
	                        <div class="btn-group add-distance">
	                            <button type="submit" class="btn-theme2 btn-default" onclick="return check(this.form)">保存收货人信息</button><span id="ms" style="color: red">
	                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="province" value="${userAddrees.province }">
    <input type="hidden" id="city" value="${userAddrees.city }">
    <input type="hidden" id="county" value="${userAddrees.area }">
</div>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>   
<script type="text/javascript" src="resource/js/center/center_edit_address.js"></script> 
<script type="text/javascript" src="resource/js/common/area.js"></script> 
<script type="text/javascript">_init_area();</script>
<script type="text/javascript">
$(function (){
	var _province = $("#province").val(),_city = $("#city").val(),_county= $("#county").val();
	$("#s_province  option[value='"+_province+"'] ").attr("selected",true);
	change(1); //渲染第二级
	$("#s_city  option[value='"+_city+"'] ").attr("selected",true);
	change(2); //渲染第三级
	$("#s_county  option[value='"+_county+"'] ").attr("selected",true)
})
</script>
</body>
</html>
