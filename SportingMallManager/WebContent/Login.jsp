<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>欢迎登录后台管理系统</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<style type="text/css">
			.top{
				width: 100%;
			    height: 47px;
			    box-shadow: 10px 10px 10px rgba(111,111,111,.5);
			    position: relative;	
			    z-index: 100px;	
			    background:url(img/loginbg1.png) repeat-x;		
			}
			.bottom{
			    height: 50px;
			    line-height: 50px;
			    text-align: center;
			    background: url(img/loginbg2.png) repeat-x;
			    position: absolute;
			    bottom: 0;
			    width: 100%;				
			}			
		</style>
	</head>
	<body style="background-color:rgb(28, 119, 172);background-image: url(img/loginbg3.png);">
		<div class="top">
			<img src="img/loginsj.png" />
			<span>欢迎登录后台管理系统</span>
		</div>
		<div class="loginbody">
			<div class="login">
				<form name="myform" class="myform" action="${pageContext.request.contextPath}/AdminLoginServlet" method="post">
					<div class="user">
						<span class="logo">ZYF ADMIN</span>
					</div>
					<font color="red" id="error">${error}</font>
					<input type="text" name="username" id="username" placeholder="用户名"/>
					<input type="password" name="password" id="password" placeholder="密码"/>
					<input type="submit" name="submit" class="submit" value="登   录"/>
				</form>
			</div>
		</div>
		<div class="bottom">
		</div>
	</body>
</html>