<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<style type="text/css">
		*{
			padding: 0;
			margin: 0;
		}
		div{display: block;}
		a{text-decoration: none;}
		.panel{
			padding: 14px;
			border-bottom: 1px solid #e7e7e7;
			background:#f8f8f8;
		}
		.panel .logo{
			padding: 5px;
			font-size: 28px;
			color: #999;
			font-weight: 700;
			font-family: courier;
		}
		.panel .panel-title{
			float: right;
			margin-right: 18px;
			margin-top: 8px;
			font-size: 16px;
			font-family: "微软雅黑";
		}
		.panel-title a{
			display: inline-block;
			margin-left: 15px;
			margin-right: 15px;
		}
		.panel-title a:hover{color: #999999;}
	</style>
</head>
<body>
	<div class="panel">
			<span class="logo">ZYF ADMIN</span>
			<span class="panel-title">欢迎:${adminName}<a href="${pageContext.request.contextPath}/AdminLoginServlet?method=logout" target="_top">退出</a></span>
	</div>
</body>
</html>