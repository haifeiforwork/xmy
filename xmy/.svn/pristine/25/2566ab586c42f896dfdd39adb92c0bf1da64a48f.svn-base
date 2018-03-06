<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>安全验证</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_safety.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/commons/nav/nav_personal.jsp" %>
    <div class="content">
    <div class="w-1250">
        <%@include file="/WEB-INF/jsp/commons/nav/personal_left_menu.jsp" %>
        <div class="content-right">
            <div class="category">
                <div class="category-title">安全验证</div>
                <div class="category-content safe-check">
                    <div class="cate-content-title clearfix">
                        <ul class="cate-nav">
                            <li onclick="window.location='/center/toSafetyPhone?set=8'">手机验证</li>
                            <li onclick="window.location='/center/toSafetyEmail?set=8'">邮箱验证</li>
                            <li class="active">安全问答验证</li>
                        </ul>
                    </div>
                    <div class="cate-table">
                        <div class="add-distance444">
                       	 <form action="center/safety" method="post">
                            <div class="input-group clearfix">
                                <div class="label label2">安全问题设置1 :</div>
                                <div class="item-input item-input2">
                                    <select class="default" id="one" name="one">
                                    	<option value="">--请选择--</option>
	                                    <c:forEach items="${one }" var="o">
	                                    	<option value="${o.id }">${o.question }</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label2">安全问题答案1 :</div>
                                <div class="item-input item-input2"><input type="text" id="oneAn" name="oneAn" class="default"></div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label2">安全问题设置2 :</div>
                                <div class="item-input item-input2">
                                    <select class="default" id="two" name="two">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${two }" var="t">
	                                    	<option value="${t.id }">${t.question }</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label2">安全问题答案2 :</div>
                                <div class="item-input item-input2"><input type="text" id="twoAn" name="twoAn" class="default"></div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label2">安全问题设置3 :</div>
                                <div class="item-input item-input2">
                                    <select class="default" id="three" name="three">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${three }" var="th">
	                                    	<option value="${th.id }">${th.question }</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="input-group clearfix">
                                <div class="label label2">安全问题答案3 :</div>
                                <div class="item-input item-input2"><input type="text" id="threeAn" name="threeAn" class="default"></div>
                            </div>
                            <div class="btn-group add-distance2">
                                <button type="submit" class="btn-theme2 btn-default" onclick="return check(this.form)">提交设置</button><span id="error" style="color: red"></span>
                            </div>
                        	</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
    <script type="text/javascript" src="resource/js/center/center_safety.js"></script>
    <script type="text/javascript">
    	function check(form){
    		var one=$("#one").val();
    		var two=$("#two").val();
    		var three=$("#three").val();
    		
    		var oneAn=$("#oneAn").val();
    		var twoAn=$("#twoAn").val();
    		var threeAn=$("#threeAn").val();
    		if(one==""||two==""||three==""||oneAn==""||twoAn==""||threeAn==""){
    			$("#error").get(0).innerHTML="选择全部问题并填写答案！";
    			return false;
    		}
    		return true;
    	}
    </script>
 </body>
</html>
