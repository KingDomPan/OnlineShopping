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
<title>我的胖嘟嘟资料修改--胖嘟嘟网上商城OnlineShopping</title>
<link rel="stylesheet" type="text/css" href="css/update.css">
<link rel="stylesheet" href="css/easyui.css">
<link rel="stylesheet" href="css/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/updateeffect.js"></script>
<script type="text/javascript" src="js/update.js"></script>
</head>
<body id="home">
        <div class="rain">
          <div class="border start">
          <fieldset>
          	<legend>胖嘟嘟个人资料修改</legend>
          	 <form action="user/update!update" method="post" id="updateForm" name="updateForm">
              <label for="password">密码</label>
              <input type="password" name="udto.user.password" id="password"
							required pattern="^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,16}$"
							title="6 < > 16" maxLength="16" 
							value="${sessionScope.user.password }"
							/>
							
              <label for="repassword">确认密码</label>
              <input type="password" name="udto.repassword" id="repassword"
							required pattern="^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,16}$"
							title="6 < > 16" 
							value="${sessionScope.user.password }"
							/>
              
              <label for="phone">联系号码</label>
              <input type="text" name="udto.user.phone" id="phone" required
							pattern="((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)"
							title="请输入正确的电话号码" 
							value="${sessionScope.user.phone }"
							/>
              
              <label for="address">地址</label>
             <input type="text" name="udto.user.address" id="address" required 
             value="${sessionScope.user.address }"
             />
              <input type="submit" value="提交" id="submit"/>
            </form>
          </fieldset>
          </div>
        </div>
</body>
</html>
