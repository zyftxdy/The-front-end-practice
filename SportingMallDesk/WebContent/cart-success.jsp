<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>操作结果--体育用品商城</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link type="text/css" rel="stylesheet" href="css/cart-style.css"/>
	</head>
	<body>
		<div class="login-top">
			<a href="#"><img src="img/logo.png" /></a>
		</div>
		<div class="login-center">
			<div class="center-login">
				<div class="success">您的商品已成功加入购物车</div>
				<div class="sumbit">
					<a href="${pageContext.request.contextPath}/ProductTypeServlet">继续购物</a>
				</div>
				<div class="sumbit2">
					<a href="${pageContext.request.contextPath}/CartServlet?method=findCart">去购物车查看</a>
				</div>
			</div>
		</div>
		<div class="login-bottom">
			
		</div>
	</body>
</html>
