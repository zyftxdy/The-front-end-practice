<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/person.css"/>
	</head>
	<body class="body">
		<div class="panel">
			<div class="panel-title">
				<span>个人资料</span>
			</div>
			<div class="panel-body">
			<div class="user-pro">
				<div class="text-line">
					<span class="text-label">用户名:</span>
					<span class="text">${user.username}</span>
				</div>
				<div class="text-line">
						<span class="text-label">电话:</span>
						<span class="text">${user.telephone}</span>									
				</div>
				<div class="text-line">
						<span class="text-label">邮箱:</span>
						<span class="text">${user.email}</span>									
				</div>
				<div class="text-line">
						<span class="text-label">密保问题:</span>
						<span class="text">${user.question}</span>									
				</div>
				<div class="text-line">
					<span class="text-label">密保答案:</span>
					<span class="text">${user.answer}</span>									
				</div>
				<div class="text-line">
					<a href="${pageContext.request.contextPath}/UserServlet?method=findInfo" target="rightframe">编辑</a>								
				</div>
			</div>
		</div>
		</div>		
	</body>
</html>