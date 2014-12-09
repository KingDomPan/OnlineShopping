<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link type="text/css" href="css/base.css" rel="stylesheet">
<link type="text/css" href="css/bought.css" rel="stylesheet">
<title>我的订单</title>
</head>

<body id="list-bought-items" class="mytaobao-v2" data-spm="2">
<%--<jsp:include page="header.jsp"></jsp:include>--%>
<%@ include file="header.jsp" %>
	<div id="page" style="width:990px;">
		<div id="content">
			<div id="mytaobao-panel" class="grid-c2">
				<div class="col-main">
					<div class="main-wrap">
						<div id="main-content">
							<div class="bought-list">
								<form action="" method="post" id="J_BoughtListForm">
									<table class="bought-table" id="J_BoughtTable" data-spm="9">
										<thead>
											<tr class="col-name">
												<th></th>
												<th class="baobei">宝贝</th>
												<th class="price">单价(元)</th>
												<th class="quantity">数量</th>
												<th class="amount">实付款(元)</th>
												<th class="trade-status">
													<div class="trade-status">
														<select id="J_TradeStatusHandle">
															<option>这是一些过滤操作</option>
															<option>具体就不实现啦</option>
														</select>
													</div>
												</th>
											</tr>
											<tr class="sep-row">
												<td colspan="9"></td>
											</tr>
											<tr class="toolbar skin-gray">
												<td colspan="7"></td>
												<td class="last-col" colspan="2"><div
														class="paginator-top">
														<div class="paginator-previous-grey g-u" >
															<span class="paginator-arrow paginator-arrow-left-grey"></span>
														</div>
														<a class="paginator-next g-u J_MakePoint" href=""> 下一页
															<span
															class="paginator-arrow paginator-arrow-right-orange"></span>
														</a>
													</div></td>
											</tr>
										</thead>
										
										<s:iterator value="#request.forders" var="var" status="status">
											<tbody class=" combo-order success-order xcard">
												<tr class="sep-row">
													<td colspan="9"></td>
												</tr>
												<tr class="order-hd">
													<td colspan="9"><span class="no"> <label>
																订单编号：<span class="order-num"><s:property value="#var.oid"/></span> </label> </span> <span
														class="deal-time">成交时间：<s:property value="#var.odate"/></span></td>
												</tr>
												<s:iterator value="#var.items" var="item" status="istatus">
													<tr id="item183058477706611" class="order-bd">
														<td class="baobei" colspan="2"><a target="_blank"
															title="" href="" class="pic s50"> <img
																alt="查看宝贝详情" src=" <s:property value="#item.product.path"/>"> </a>
															<div class="desc">
																<a class="baobei-name" target="_blank" href="">
																	<s:property value="#item.product.name"/><br/>
																	<s:property value="#item.product.description"/> </a>
															</div></td>
														<td class="price" title="108.00"><s:property value="#item.unitPrice"/></td>
														<td class="quantity" title="1"><s:property value="#item.count"/></td>
														<s:if test="#istatus.index==0">
															<td class="amount" rowspan='<s:property value="#var.items.size()"/>'><strong><s:property value="#var.totalPrice"/></strong>
															</td>
															<td class="trade-status" rowspan="<s:property value="#var.items.size()"/>"><a href=""
																target="_blank" class="J_MakePoint status success">交易成功
															</a></td>
														</s:if>
													</tr>
												</s:iterator>
											</tbody>
										</s:iterator>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
