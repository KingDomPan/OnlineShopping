<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>胖嘟嘟网上商城OnlineShopping|商品展示</title>
<link rel="stylesheet" type="text/css" href="css/homepage.css">
</head>
<body>
		<%@ include file="header.jsp" %>
<%--	<jsp:include page="header.jsp"></jsp:include>--%>

	<div style="margin-top: 100px">
		<s:action name="category!findCategories" namespace="/category"
			executeResult="true">
		</s:action>
	</div>

	<s:action name="findNewers" namespace="/product"
		executeResult="true">
	</s:action>
</body>
</html>
