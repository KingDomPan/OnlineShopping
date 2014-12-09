<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="css/spbase.css">
<link rel="stylesheet" href="css/spindex.css">
<link rel="stylesheet" href="css/easyui.css">
<link rel="stylesheet" href="css/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/loginout.js"></script>
</head>
<body>
	<header class="mis-head"
		style="padding-left:0;margin:0 auto;width:960px;"> <article
		id="head" class="mis-clearfix"
		style="width:732px;padding-left:148px;background:url('images/pdd.jpg') 0 8px no-repeat;">
	<nav class="nv" style="height:32px;"> <span class="tab"
		style="top:4px;"> 
		<a href="index.jsp">胖嘟嘟首页</a>
		<s:if test="#session.user==null">
			<a href="login.html">登录</a>
			<a href="signup.html">免费注册</a>
		</s:if> <s:else>
			<a id="loginout" href="#">拜拜胖嘟嘟</a>
			<a href="update.jsp">我的胖嘟嘟</a>
			<a href="order.jsp">我的购物车(${sessionScope.order.items.size()})</a>
			<a href="salesorder/findFinishOrder">订单查看</a>
		</s:else> <a href="#">收藏本站</a><a href="#">网站导航</a> </span></nav>
	<form id="mini-fm" name="f" action="product/searchProduct" class="fm">
		<input name="pname" id="search" class="i" value=""
			placeholder="&lt;&lt;来胖嘟嘟宝贝吧&gt;&gt;" maxlength="100" required
			type="text"
			autocomplete="on" /> 
			<span class="btn_wr"> 
			<input
			type="submit" value="叮咚胖嘟嘟" id="su" class="btn"
			onmousedown="this.className='btn btn_h'"
			onmouseout="this.className='btn'"/> 
			</span>
	</form>
	</article> </header>
</body>
</html>
