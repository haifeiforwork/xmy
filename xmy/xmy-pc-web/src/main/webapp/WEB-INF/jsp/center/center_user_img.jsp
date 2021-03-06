<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/comm_cons_tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>个人信息</title>
	<%@include file="/WEB-INF/jsp/commons/comm_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="resource/css/center/center_userinfo.css">
	<link href="resource/commons/css/foundation-datepicker.css" rel="stylesheet" type="text/css">
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
                            <li onclick="window.location='/center/toUpdate?set=5'">基本资料</li>
                            <li class="active">头像照片</li>
                        </ul>
                    </div>
                        <div class="am-modal-dialog up-frame-parent up-frame-radius">
                            <div class="am-modal-bd  up-frame-body">
                                <form action="center/updateImg" method="post">
                                <div class="am-g am-fl clearfix">
                                    <div class="col-50 add-dd">
                                        <div class="up-pre-before up-frame-radius">
                                            <img alt="" src="" id="image" >
                                            <div class="default-img">
                                            <input id="imgs" name="imgs" value="" type="hidden">
                                                <img id="img1" src="resource/commons/images/cstars/${empty userinfo.avatar?'head.png':userinfo.avatar }" alt="">
                                            </div>
                                        </div>
                                        <div class="suport-head">
                                            <div class="suport-title">推荐头像</div>
                                            <div class="head-list">
                                                <ul class="clearfix" id="parent">
                                                    <li><img src="resource/commons/images/cstars/1.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/2.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/3.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/4.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/5.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/6.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/7.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/8.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/9.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/10.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/11.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/12.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/13.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/14.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/15.png" alt="" ></li>
                                                    <li><img src="resource/commons/images/cstars/16.png" alt="" ></li>
                                                </ul>
                                            </div>
                                            <div class="btn-group add-distance6">
                                                <button type="submit" class="btn-default btn-person">保存</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-50">
                                        <div class="wrap-head">
                                            <p class="wrap-title">
                                            	    效果预览
                                            </p>
                                            <div class="up-pre-after up-frame-radius">
												<img id="img2" src="resource/commons/images/cstars/${empty userinfo.avatar?'head.png':userinfo.avatar }" height="100px" width="100px" style="margin-left:20px">
                                            </div>
                                            <p class="text-px">100*100像素</p>
                                        </div>
                                        <div class="wrap-head">
                                            <div class="up-pre-after up-frame-radius wh-50">
												<img id="img3" src="resource/commons/images/cstars/${empty userinfo.avatar?'head.png':userinfo.avatar }" height="50px" width="50px" style="margin-left:20px">
                                            </div>
                                            <p class="text-px px50">50*50像素</p>
                                        </div>
                                    </div>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/comm_buttom.jsp" %>
<script type="text/javascript" src="resource/commons/js/foundation-datepicker.js"></script>
<script type="text/javascript" src="resource/commons/js/fileUpload.js"></script>
<script type="text/javascript" src="resource/js/center/center_userinfo.js"></script>
<script type="text/javascript">
var listImg=document.getElementById("parent").getElementsByTagName("img");
for(var i=0;i<listImg.length;i++){
	listImg[i].onclick=function(){
	$("#img1").attr('src',this.getAttributeNode("src").nodeValue); 
	$("#img2").attr('src',this.getAttributeNode("src").nodeValue); 
	$("#img3").attr('src',this.getAttributeNode("src").nodeValue); 
	$("#imgs").val(this.getAttributeNode("src").nodeValue);
	}
}
</script>
</body>
</html>
