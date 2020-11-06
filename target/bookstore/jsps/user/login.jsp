<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>登录</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<!-- target="_top"属性表示：action目标地址将在整个窗口中打开。  -->
<form action="<c:url value='/User/login'/>" method="post" target="_top">
	用户名：<input type="text" name="username" value="${form.username }"/><br/>
	密　码：<input type="password" name="password" value="${form.password }"/><br/>
	<input type="submit" value="登录"/>
</form>
  </body>
</html>
