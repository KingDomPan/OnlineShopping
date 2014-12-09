<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String total=request.getParameter("total");
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>收货信息---胖嘟嘟网上商城OnlineShopping</title>
<link href="css/receive.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="css/easyui.css">
<link rel="stylesheet" href="css/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/receive.js"></script>
</head>
<body>
<div id="contact">
	<h1>胖嘟嘟收货地址</h1>
	<form action="salesorder/submitOrder" method="post" id="receiveForm">
		<fieldset>
			<label for="name">收货地址:</label>
			<input type="text" id="address" placeholder="<<您的收货地址>>"  required
				value="${sessionScope.user.address}"
				name="order.address"
			/>
			
			<label for="phone">联系电话:</label>
			<input type="text" id="phone" placeholder="<<您的联系电话>>"  required
			name="order.phone"
				value="${sessionScope.user.phone }"
			/>
			
			<label for="total">商品总价:</label>
			<input type="text" id="total" readonly="readonly" name="order.totalPrice"
				value="<%=total %>"
			/>
			
			<label for="remark">备注信息:</label>
			<textarea id="remark" 
			name="order.remark"
			placeholder="<<您需要的备注信息>>"></textarea>
			
			<input type="submit" value="胖嘟嘟啦~~" />
			
		</fieldset>
	</form>
</div>
</body>
</html>
