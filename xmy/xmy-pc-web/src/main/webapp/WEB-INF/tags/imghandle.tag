<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ attribute name="imgurl" type="java.lang.String" required="true" description="图片地址"%>
<%@ attribute name="w" type="java.lang.Integer" required="true" description="图片宽度"%>
<%@ attribute name="h" type="java.lang.Integer" required="true" description="图片高度"%>
<%@ attribute name="cl" type="java.lang.String"  description="图片样式"%>
<img alt="" src="${imgurl}?x-oss-process=image/resize,m_fixed,h_${h},w_${w}" class="${cl}">