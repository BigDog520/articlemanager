<%@page import="Bean.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8" %>

<!-- 导入mysql驱动包 -->
<%@ page import="jdbcUtil.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.mysql.jdbc.Driver.*"%>

<html lang="zh-CN" ng-app="myModule">

<head>
	<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>文章管理系统</title>

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="kindeditor/themes/default/default.css" /> 

</head>
<%
	Connection conn =null; 
	Statement sta = null;
	ResultSet re = null;
	
	conn = jdbcUtil.getConn();
	sta = conn.createStatement();
	%><!-- 
	re = sta.executeQuery("select * from user_table");
	List<User> userlist;
	while(re.next())
	{
		User user = new User();
		user.setUser_id(Integer.parseInt(re.getString("user_id")));
		user.setName(re.getString("name"));
		user.setPassword(re.getString("password"));
		user.setPhone(re.getString("phone"));
		user.setSex(Integer.parseInt(re.getString("sex")));
		user.setAddress(re.getString("address"));
	}
-->
<body>
    <div ng-controller="login">
        <div ng-if="!islogin" ng-include="' tpl/login_navbar.html '"></div>
        <div ng-if="islogin" ng-include="' tpl/index_navbar.html '"></div>
    </div>
    <div ng-view></div>
	<input type="hidden">


    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/angular.js"></script>
    <script src="js/angular-route.js"></script>
    <script src="js/index.js"></script>
    <script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>	 
</body>

</html>