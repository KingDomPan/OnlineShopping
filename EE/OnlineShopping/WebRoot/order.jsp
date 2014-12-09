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
<title>胖嘟嘟网上商城OnlineShopping|我的购物车</title>
<link rel="stylesheet" href="css/car_one.css" />
<link rel="stylesheet" href="css/car_two.css" />
<link rel="stylesheet" href="css/easyui.css">
<link rel="stylesheet" href="css/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#J_Go").click(function(){
			var total=$(this).attr("total");
			var params=total.split("&");
			if(params[1]==null||params[1]==""){
				$.messager.alert("信息提示","购物车中暂无订单项","info");
			}else{
			location.replace("receive.jsp?total="+params[0]);
			}
		});
	});
</script>
</head>
<body>
<%--<jsp:include page="header.jsp"></jsp:include>--%>
<%@ include file="header.jsp" %>
	<div id="page">
		<div id="content" class="grid-c">
			<div id="cart">
				<div class="status-wrapper">
					<table cellspacing="0" cellpadding="0" class="order-table"
						id="J_CartEnable">
						<tbody class="J_Order">
						<s:set var="total" value="0"/>
						<s:set var="tcount" value="#session.order.items.size()" />
							<s:iterator value="#session.order.items" var="var">
								<s:set var="cost" value="#var.count * #var.unitPrice" />
								<tr class="J_ItemBody uncod selected ">
									<td class="s-title"><a href="#" target="_blank"> <img
											src="<s:property
												value="#var.product.path" />" class="itempic"> <s:property
												value="#var.product.name" /><br /> <s:property
												value="#var.product.description" /> </a></td>
									<td class="s-price  "><em class="s-old-price">普通价:<s:property
												value="#var.product.normalPrice" /> </em><em class="J_Price" style="font-size:16px"
										tabindex="0">会员价:<s:property
												value="#var.product.memberPrice" /> </em><em
										class="s-change-price-text">拍下价:<s:property
												value="#var.unitPrice" /> </em></td>
									<td class="s-amount "> <input
type="number" min=1 style="width:45px" readonly="readonly"
value="<s:property
value="#var.count" />"
class="text" autocomplete="off"> </td>
									<td class="s-total"><em tabindex="0"><s:property
												value="#cost" /> </em></td>
<%--									<td class="s-del"><a href="javascript:void(0)" class="J_Del">删除</a>--%>
<%--									</td>--%>
								</tr>
								<s:set var="total" value="#total + #cost" />
							</s:iterator>
						</tbody>
					</table>

					<div id="float-bar" class="float-bar clearfix"
						style="position: fixed;">
						<button class="btn" type="button" id="J_Go" total="<s:property
												value="#total" />&<s:property
												value="#tcount" />">结 算</button>
						<span class="total-price g_price">商品总价：<span>¥</span><em
							class="total" id="J_Total" tabindex="0"><s:property
												value="#total" /></em> </span>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
