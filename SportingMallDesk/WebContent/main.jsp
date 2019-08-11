<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>ZYF--体育用品商城</title>
		<link type="text/css"  rel="stylesheet" href="css/main.css"/>
		<link rel="shortcut icon" href="img/X.png"/>
		<style type="text/css">
			.main-bird{
				width:1160px;
				margin:0 auto;
			}
		</style>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/sildeshow.js"></script>
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

		</script>
	</head>
	<body class="body">
		<!--头部-->
		<div class="top">
			<div class="head">
			<c:choose>
				<c:when test="${empty username}">
					<a href="Login.jsp">登录</a>
					<a href="register.jsp">注册</a>
				</c:when>
				<c:otherwise>
					<p>欢迎您:<font color="red">${username}</font> <a href="${pageContext.request.contextPath}/LoginServlet?method=logout">退出</a></p>
				</c:otherwise>
			</c:choose>
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
				<a href="javaScript:search();" class="submit" target="_blank">搜索</a>
			</div>
			<!--商品分类头部-->
			<div class="header-main">
				<!--商品分类-->
				<div class="list-main">
					<c:forEach items="${FirstType}" var="ft">
					<ul>
						<li>
							<a href="${pageContext.request.contextPath}/ProductServlet?method=find&firstTypeId=${ft.firsttypeid}" target="_blank"><p>${ft.firsttypename}</p></a>
						</li>
					</ul>
					</c:forEach>
				</div>
				<!--商品展示大图-->
				<div class="big-main">
					<div class="slideBox">
						<ul>
							<li><a href="#"><img src="img/1.jpg" alt="" width="890" height="364"></a></li>
							<li><a href="#"><img src="img/2.jpg" alt="" width="890" height="364"></a></li>
							<li><a href="#"><img src="img/3.jpg" alt="" width="890" height="364"></a></li>
							<li><a href="#"><img src="img/4.jpg" alt="" width="890" height="364"></a></li>
							<li><a href="#"><img src="img/5.jpg" alt="" width="890" height="364"></a></li>
						</ul>
						<div class="spanBox">
							<a class="active"></a>
							<a></a>
							<a></a>
							<a></a>
							<a></a>
						</div>
					</div>		
				</div>
			</div>
			<!--商品详细分类展示-->
			<div class="center-main">
				<div class="main-bird">
					<!--网球装备分类-->
					<div class="tennis-main">
						<div class="title">
							<p>1F</p>
							<span>网球系列</span>
							<hr style="background-color: #d58717;height:1.5px;border: none;">
						</div>
						<div class="tennis-list">
						<c:forEach items="${SecondType}" var="st">
							<c:if test="${st.firsttypeid==100001}">
								<ul>
									<li><a href="${pageContext.request.contextPath}/ProductServlet?method=find&secondTypeId=${st.secondtypeid}" target="_blank">
											<div class="list-title">${st.secondtypename}</div>
											<div class="list-img"><img src="/upload/${st.picture}"/></div>
										</a>
									</li>
								</ul>
							</c:if>
						</c:forEach>
						</div>
					</div>
				</div>
				<div class="main-bird">
					<!--羽毛球装备分类-->
					<div class="bird-main">
						<div class="title">
							<p>2F</p>
							<span>羽毛球系列</span>
							<hr style="background-color: #3b838c;height:1.5px;border: none;">						
						</div>
						
						<div class="bird-list">
							<c:forEach items="${SecondType}" var="st">
							<c:if test="${st.firsttypeid==100002}">
								<ul>
									<li><a href="${pageContext.request.contextPath}/ProductServlet?method=find&secondTypeId=${st.secondtypeid}" target="_blank">
											<div class="list-title">${st.secondtypename}</div>
											<div class="list-img"><img src="/upload/${st.picture}"/></div>
										</a>
									</li>
								</ul>
							</c:if>
						</c:forEach>						
						</div>
					</div>
				</div>
				<div class="main-bird">
					<!--篮球装备分类-->
					<div class="basketball-main">
						<div class="title">
							<p>3F</p>
							<span>篮球系列</span>
							<hr style="background-color: #a93931;height:1.5px;border: none;">							
						</div>
						<div class="basketball-list">
							<c:forEach items="${SecondType}" var="st">
							<c:if test="${st.firsttypeid==100003}">
								<ul>
									<li><a href="${pageContext.request.contextPath}/ProductServlet?method=find&secondTypeId=${st.secondtypeid}" target="_blank">
											<div class="list-title">${st.secondtypename}</div>
											<div class="list-img"><img src="/upload/${st.picture}"/></div>
										</a>
									</li>
								</ul>
							</c:if>
						</c:forEach>							
						</div>
					</div>
				</div>
				<div class="main-bird">
					<!--户外装备分类-->
					<div class="outdoors-main">
						<div class="title">
							<p>4F</p>
							<span>户外装备系列</span>
							<hr style="background-color: #488bad;height:1.5px;border: none;">								
						</div>
						<div class="outdoors-list">
							<c:forEach items="${SecondType}" var="st">
							<c:if test="${st.firsttypeid==100005}">
								<ul>
									<li><a href="${pageContext.request.contextPath}/ProductServlet?method=find&secondTypeId=${st.secondtypeid}" target="_blank">
											<div class="list-title">${st.secondtypename}</div>
											<div class="list-img"><img src="/upload/${st.picture}"/></div>
										</a>
									</li>
								</ul>
							</c:if>
						</c:forEach>							
						</div>
					</div>
				</div>
				<div class="main-bird">
					<!--运动配件分类-->
					<div class="exercise-main">
						<div class="title">
							<p>5F</p>
							<span>健身系列</span>
							<hr style="background-color:#642663;height:1.5px;border: none;">							
						</div>
						<div class="exercise-list">
							<c:forEach items="${SecondType}" var="st">
							<c:if test="${st.firsttypeid==100008}">
								<ul>
									<li><a href="${pageContext.request.contextPath}/ProductServlet?method=find&secondTypeId=${st.secondtypeid}" target="_blank">
											<div class="list-title">${st.secondtypename}</div>
											<div class="list-img"><img src="/upload/${st.picture}"/></div>
										</a>
									</li>
								</ul>
							</c:if>
						</c:forEach>							
						</div>
					</div>
				</div>
			</div> 
		</div>
		<!--尾部-->
		<div class="bottom">
			<div class="lianjie">
				<div class="bottom-lianjie">
				<hr style="margin-top: 10px;padding-top: 10px;border-left: none;border-bottom: none;">
				<a href="#">张永峰</a>|<a href="#">毕业设计</a>|<a href="#">关于本人</a>
				<hr style="margin-top: 10px;padding-bottom:5px;border-left: none;border-bottom: none;">
				<p>版本所有:<span>张永峰</span>&nbsp;此版本为毕业设计</p>
				</div>
			</div>
		</div>
	</body>
</html>