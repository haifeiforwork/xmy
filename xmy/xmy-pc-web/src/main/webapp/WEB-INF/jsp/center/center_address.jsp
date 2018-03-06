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
                        <form action="center/addAddress" method="post">
                        	<div class="input-group clearfix">
                            <div class="label">*收货人 :</div>
                            <div class="item-input"><input type="text" name="name" onblur="checkName(this)" class="default"><span id="name" style="color: red"></span></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*地区 :</div>
	                            <div class="item-input select-all">
	                                <select id="s_province" name="province" class="default" onblur="checkAdd();">
	                                    <option>--请选择--</option>
	                                </select>
	                                <select id="s_city" name="city" onblur="checkAdd();" class="default">
	                                    <option>--请选择--</option>
	                                </select>
	                                <select id="s_county" name="area" onblur="checkAdd();" class="default">
	                                    <option>--请选择--</option>
	                                </select>
	                            <span id="msg" style="color: red"></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*详细地址 :</div>
	                            <div class="item-input"><input type="text" name="address" onblur="checkAddress(this)" class="default"><span id="address" style="color: red"></span></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">*手机号码 :</div>
	                            <div class="item-input"><input type="text" name="mobilePhone" onblur="checkPhone(this)" class="default"><span id="phone" style="color: red"></div>
	                        </div>
	                        <div class="input-group clearfix">
	                            <div class="label">备用电话 :</div>
	                            <div class="item-input"><input type="text" name="phone" onblur="checkCall(this)" class="default"><span id="call" style="color: red"></div>
	                        </div>
	                        <div class="btn-group add-distance">
	                            <button type="submit" class="btn-theme2 btn-default" onclick="return check(this.form)">保存收货人信息</button><span id="ms" style="color: red">
	                        </div>
                        </form>
                        <div class="wrap-table">
                            <table cellspacing="0">
                                <tr class="th">
                                    <th class="adress-sm-default first">收货人</th>
                                    <th class="adress-lg-default">所在地区</th>
                                    <th class="adress-most-default">详细地址</th>
                                    <th class="adress-default">电话/手机</th>
                                    <th class="adress-xs-default">操作</th>
                                    <th class="adress-default last"></th>
                                </tr>
                                <c:if test="${empty userAddrees }">
                                	<tr class="table-list">
                                		<td class="text-center" colspan="7" style="padding:20px 0">暂无数据！</td>
                                	</tr>
                                </c:if>
                                <c:forEach items="${userAddrees}" var="addrees">
                                	<tr class="table-list">
	                                    <td class="">${addrees.name }</td>
	                                    <td class="region">${addrees.province }&nbsp;${addrees.city }&nbsp;${addrees.area }</td>
	                                    <td class="adress-des">${addrees.province }&nbsp;${addrees.city }&nbsp;${addrees.area }${addrees.address }</td>
	                                    <td class="">${addrees.mobilePhone }</td>
	                                    <td class="add-color-blue"><span><a style="text-decoration: none;color: #3861D5" href="center/findAddress?id=${addrees.id }">修改</a></span> | <span><a style="text-decoration: none;color: #3861D5" href="center/delAddress?id=${addrees.id }">删除</a></span></td>
	                                    <td class=""><button type="button" class="adress-btn ${addrees.isDefault==1?'active-adress-btn':'default-adress-btn' }" onclick="window.location='/center/default?aId=${addrees.id }'">默认地址</button></td>
	                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--内容部分完-->
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>   
<script type="text/javascript" src="resource/js/center/center_address.js"></script> 
<script type="text/javascript" src="resource/js/common/area.js"></script> 
<script type="text/javascript">_init_area();</script>
</body>
</html>
