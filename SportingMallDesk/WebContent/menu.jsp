<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			ul{list-style: none;}
			a{text-decoration: none;}
			a{
				position: relative;
				text-decoration:none;
				color:#999;
			}
			.menu{
			    margin: 10px 10px 10px 10px;				
			}
			 ul li{
			    font-size: 16px;
			    color: #666;
			    padding: 5px 5px 0px 5px;
			    margin-left: 15px;
			}
			 ul li a{
				line-height: 25px;
			}
			.choose.active a{color: red;}
			 ul li a:hover{color:red;}			
		</style>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			$(function(){
				var _color;
				$(document).on('click','.choose',function(){
					$(this).addClass('active').siblings('.choose').removeClass('active');
					
				});
			})
		</script>
	</head>
	<body>
		<ul class="menu">
		<li class="choose"><a href="${pageContext.request.contextPath}/UserServlet?method=findUser" target="rightframe">个人资料</a></li>
		<li class="choose"><a href="password-change.jsp" target="rightframe">修改密码</a></li>
		<li class="choose"><a href="${pageContext.request.contextPath}/OrderServlet?method=findOrder" target="rightframe">我的订单</a></li>
		<li class="choose"><a href="${pageContext.request.contextPath}/UserServlet?method=findAddress" target="rightframe">收货地址</a></li>
		</ul>
	</body>
</html>