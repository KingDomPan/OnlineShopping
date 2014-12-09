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
<meta charset="utf-8">
<title>胖嘟嘟网上商城OnlineShopping|商品展示</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/spbase.css">
<link rel="stylesheet" href="css/spindex.css">
<link rel="stylesheet" href="css/easyui.css">
<link rel="stylesheet" href="css/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="des-item3">
	<section class="mis-body"
		style="width:960px;margin:17px auto 0 auto;padding-left:0;">
		<div id="maincontent">
			<div id="normal-shopping-list">
				<div class="shopping-list">
					<ul>
						<s:iterator value="#request.products" var="var" status="status">
							<s:if test="#status.index%4==3">
								<li class="four" style="padding-right:0;">
									<div class="pro-pic-container">
										<a href="" class="pro-pic"><img
											src="<s:property
														value="#var.path" />"
											title="" /> </a>
									</div>
									<div class="item-detail">
										<div class="pro-title">
											<a href="" target="_blank">胖嘟嘟 <em><s:property
														value="#var.name" /> </em> <s:property
													value="#var.description" /> </a>
										</div>
										<p class="pro-price">
											<span class="return" title=""></span> <span class="mail"
												title=""></span><del> <em>￥普通价</em><strong><s:property
														value="#var.normalPrice" />  </strong> </del><br /> <em>￥会员价</em><strong><s:property
													value="#var.memberPrice" /> <a class="addItemToOrder"
												param="<s:property
													value="#var.id" />&<s:property
													value="#var.memberPrice" />">
													<img src="images/buycar.jpg" /> </a> </strong>
										</p>
									</div></li>
								<li class="div-line"></li>
							</s:if>
							<s:else>
								<li>
									<div class="pro-pic-container">
										<a href="" class="pro-pic"><img
											src="<s:property
														value="#var.path" />"
											title="" /> </a>
									</div>
									<div class="item-detail">
										<div class="pro-title">
											<a href="" target="_blank">胖嘟嘟 <em><s:property
														value="#var.name" /> </em> <s:property
													value="#var.description" /> </a>
										</div>
										<p class="pro-price">
											<span class="return" title=""></span> <span class="receive"
												title=""></span> <span class="mail" title=""></span> <del><em>￥普通价</em><strong><s:property
													value="#var.normalPrice" /> </strong></del> <br /> <em>￥会员价</em><strong><s:property
													value="#var.memberPrice" /> <a class="addItemToOrder"
												param="<s:property
													value="#var.id" />&<s:property
													value="#var.memberPrice" />">
													<img src="images/buycar.jpg" /> </a> </strong>
										</p>
									</div></li>
							</s:else>
						</s:iterator>
					</ul>
					<div class="mis-clearfix"></div>
				</div>
			</div>
			<article class="mis-pager" id="pager"
				style="border-top:none;margin-top: 20px">
				<div id="bg-wrap"></div>
				<div>
					<a href="product/findNewers?pageNo=1" class="n">首页</a>
					
					<s:if test="%{pageNo>1}">
						<a class="n"
						href="product/findNewers?pageNo=${requestScope.currentPage-1}">&lt;上一页</a>
					</s:if>
					
					<s:if test="%{pageNo<totalPage}">
					<a href="product/findNewers?pageNo=${requestScope.currentPage+1}" class="n">下一页&gt;</a> 
					</s:if>
					<a
						class="n"
						href="product/findNewers?pageNo=${requestScope.totalPage}">尾页</a>
				</div>
			</article>
		</div>
	</section>
	<footer class="mis-foot">
		<div>
			&copy;2012 胖嘟嘟网上商城OnlineShopping <span>此站点为胖嘟嘟所有,代表胖嘟嘟网上商城立场</span>
		</div>
	</footer>
	<script type="text/javascript" src="js/addtocar.js"></script>
</body>

</html>