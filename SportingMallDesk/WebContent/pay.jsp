<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>订单支付--体育用品商城</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link rel="stylesheet" type="text/css" href="css/pay.css"/>		
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
			
			//支付订单
			function pay(){
				var orderNo=document.getElementById("orderNo").innerHTML;	
				var url="${pageContext.request.contextPath}/OrderServlet?method=pay&orderNo="+orderNo;
				window.location.href=url;
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
				<li><a href="${pageContext.request.contextPath}/UserServlet?method=checkUserId">个人中心</a></li>
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
					<a>订单支付</a>
				</div>
				<div class="pay">
					<div class="pay-child">
						<div class="text-line">
							<span>订单提交成功，请您尽快支付！</span>
						</div>
						<div class="text-line">
							<div class="text-label">订单号&nbsp;:</div>
							<div class="text" id="orderNo">${orderNo}</div>
						</div>
						<div class="text-line">
							<div class="text-label">应付金额:</div>
							<div class="text" id="price">￥<span id="total">${total}</span></div>
						</div>
						<div class="text-line">
							<div class="text-label">支付方式:</div>
							<div class="text">在线支付</div>
						</div>
						<div class="text-pay">
							<a href="javaScript:pay();" class="btn">立即付款</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>