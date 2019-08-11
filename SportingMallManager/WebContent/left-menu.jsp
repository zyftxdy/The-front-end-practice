<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/left-menu.css"/>
	<style type="text/css">
		.nav a.active {
			background:#e7e7e7;
		}
		i{
			cursor: pointer;
		}
		.nav-list{
			display: none;
		}
	</style>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var _this=this;
			$(".nav a").click(function(){
				$(".nav a.active").removeClass("active");
				$(this).addClass("active");
			});
			$(document).on('click','.menu',function(){
				_this.data=$(this).data('id');
				var authority=_this.data;
				var one=$(this);
				$.ajax({
				    type : "POST",
					url:"${pageContext.request.contextPath}/AuthorityServlet?method=checkAuthority&authority="+authority,
				    success : function(data) {
				    	if(data=="true"){
				    		one.parent().find('ul').slideToggle();
				    	}else{
				    		alert("您没有当前权限!");
				    	}
				    }  	
				  });
			});
		})
	</script>
</head>
<body class="body">
	<div class="panel">
		<ul class="nav">
			<li class="title">
				<a href="index.jsp" target="rightFrame">
					<img src="img/home.png"/>
					<span>Home</span>
				</a>
			</li>
			<li class="title">
				<i class="menu" data-id="1">
					<img src="img/列表 (1).png" />
					<span>会员</span>
				</i>
				<ul class="nav-list">
					<li>
						<a href="${pageContext.request.contextPath}/UserServlet" target="rightFrame">
							<img src="img/用户管理.png" />
							<span>会员管理</span>
						</a>
					</li>
				</ul>
			</li>
			<li class="title">
				<i class="menu" data-id="2">
					<img src="img/列表 (1).png" />
					<span>商品</span>
				</i>										
				<ul class="nav-list">
					<li>
						<a href="${pageContext.request.contextPath}/ProductServlet" target="rightFrame">
							<img src="img/商品_p.png" />
							<span>商品管理</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/ProductTypeServlet?method=findFirstType" target="rightFrame">
							<img src="img/类目.png" />
							<span>品类管理</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/CommentServlet" target="rightFrame">
							<img src="img/评论 (1).png" />
							<span>评论管理</span>
						</a>
					</li>
				</ul>
			</li>
			<li class="title">
				<i class="menu" data-id="3">
					<img src="img/列表 (1).png"/>
					<span>订单</span>
				</i>
				<ul class="nav-list">
					<li>
						<a href="${pageContext.request.contextPath}/OrderServlet" target="rightFrame">
							<img src="img/订单.png" />
							<span>订单管理</span>
						</a>
					</li>
				</ul>
			</li>
			<li class="title">
				<i class="menu" data-id="4">
					<img src="img/列表 (1).png"/>
					<span>权限</span>
				</i>
				<ul class="nav-list">
					<li>
						<a href="${pageContext.request.contextPath}/AuthorityServlet" target="rightFrame">
							<img src="img/权限.png" />
							<span>权限管理</span>
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>