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
<title>商品类别</title>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/menu.css">
</head>

<body topmargin="0" leftmargin="0" rightmargin="0">
			<div id="menu">
				<ul id="navigation">
					<s:iterator value="#request.categories" var="var" status="vstatus">
						<li onMouseOver="displaySubMenu(this)"
							onMouseOut="hideSubMenu(this)"><a href="#"><s:property
									value="#var.name" /> </a>
							<ul style="display: none; ">
								<s:iterator value="#var.children" var="category" status="status">
									<s:if test="#status.index==0">
										<li style="margin-top:6px;"><a href="product/findProductsByCategiryId?cid=<s:property
													value="#category.id" />"> <s:property
													value="#category.name" /> </a>
										</li>
									</s:if>
									<s:else>
										<li><a href="product/findProductsByCategiryId?cid=<s:property
													value="#category.id" />"> <s:property
													value="#category.name" /> </a>
										</li>
									</s:else>
								</s:iterator>
							</ul>
						</li>
					</s:iterator>
				</ul>
	</div>
</body>
</html>
