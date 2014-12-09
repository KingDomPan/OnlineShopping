<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>404你懂的</title>
  </head>
  
  <body>
    <center>
    <img src="images/404.jpg" alt="404你懂的" onclick="javascript:history.go(-1)"/>
    </center>
  </body>
</html>
