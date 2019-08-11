<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户中心--体育用品商城</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link type="text/css"  rel="stylesheet" href="css/personal-center.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function search(){
				var name=document.getElementById("search").value;
				var url="${pageContext.request.contextPath}/ProductServlet?method=find";
				if(name!=""){
					url=url+"&productName="+name;
					window.location.href=url;
				}else{
					alert("请输入您想查找的商品!");
				}
				//提交给servlet
			}
			//rightframe自适应高度
			function iframeLoad()
			{
			    document.getElementById("rightframe").height=0;
			    document.getElementById("rightframe").height=document.getElementById("rightframe").contentWindow.document.body.scrollHeight;
			}
		</script>
	</head>
	<body class="body">
		<!--头部-->
		<div class="top">
			<div class="head">
			<!-- 判断用户名SESSION-->
			<p>欢迎您:<font color="red">${username}</font> <a href="${pageContext.request.contextPath}/LoginServlet?method=logout">退出</a></p>
			<ul>
				<li><a href="${pageContext.request.contextPath}/CartServlet?method=findCart"><img src="img/cart.png"/>购物车</a></li>
				<li><a href="person-center.jsp">个人中心</a></li>
				<li><a href="#">关于网站</a></li>
			</ul>
			</div>
		</div>
		<!--主体-->
		<div class="main">
			<!--搜索框 -->
			<div class="search-main">
				<a href="${pageContext.request.contextPath}/ProductTypeServlet" class="logo"><p>ZYF</p></a>
				<input type="text" id="search" class="search" placeholder="请输入商品名称"/>
				<a href="javaScript:search();" class="submit">搜索</a>
			</div>
			<!--导航-->
			<div class="nav-main">
				<div class="w">
					<a href="${pageContext.request.contextPath}/ProductTypeServlet">ZYF</a>
					<span>></span>
					<a>个人中心</a>
				</div>
			</div>
			<!--主体-->
			<div class="personal-main">
				<!--菜单选择-->
				<div class="left-con">
					<iframe src="menu.jsp" width="200" scrolling="no" align="left" frameborder="0" noresize="noresize" border="0" framespacing="0"></iframe>
				</div>
				<div class="right-con">
					<iframe src="welcome.jsp" width="935"
							onload="iframeLoad()" 
							id="rightframe" name="rightframe" scrolling="no" 
							frameborder="0" noresize="noresize"></iframe>
				</div>
			</div>
		</div>
	</body>
</html>