<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品列表--体育用品商城</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link type="text/css" rel="stylesheet" href="css/productList.css"/>
		<style type="text/css">
			.body{
				 background-color: #f6f6f6;
   				 min-width: 1080px;
				}
			.sorter-item.active a{
				color:white;	
				}
			.sorter-con a{
				color:#333;
				}
		</style>
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
			}
			
			//排序
			/* $(function(){
			    var _this=this;
			    $('.sorter-item').on('click',function(){
			    	var $this=$(this);
			    	
			    	if($this.data('type')=='default'){
			    		//已经是active样式
			    		if($this.hasClass('active')){
			    			return;
			    		}else{
			    			$this.addClass('active').siblings('.sorter-item').removeClass('active asc desc');
			    			document.getElementById("sort-price").innerHTML="价格排序";
			    		}
			    	}else if($this.data('type')=='price'){
			    		$this.addClass('active').siblings('.sorter-item').removeClass('active asc desc');
			    		if(!$this.hasClass('asc')){
			    			$this.addClass('asc').removeClass('desc');
			    			document.getElementById("sort-price").innerHTML="价格(低->高)";
			    			
			    		}else{
			    			$this.addClass('desc').removeClass('asc');
			    			document.getElementById("sort-price").innerHTML="价格(高->低)";
			    		}
			    	}
			    });

		    }); */
		</script>
	</head>
	<body class="body">
		<!--头部-->
		<div class="top">
			<div class="head">
			<!-- 判断用户名SESSION-->
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
				<input type="text" id="search" class="search"  value="${productName}" placeholder="请输入商品名称"/>
				<a href="javaScript:search();" class="submit" target="_blank">搜索</a>
			</div>
			<!--导航-->
			<div class="nav-main">
				<div class="w">
					<a href="${pageContext.request.contextPath}/ProductTypeServlet">ZYF</a>
					<span>></span>
					<a>商品列表</a>
				</div>
			</div>
			<!--商品列表-->
			<div class="productList">
				<ul class="sorter-con">
					<c:if test="${Sorting == 1}">
						<li class="sorter-item active" data-type="default"><a href="${pageContext.request.contextPath}/ProductServlet?method=find&Sorting=1&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}">推荐排序</a></li>
						<li class="sorter-item" id="sort-price" data-type="price"><a href="${pageContext.request.contextPath}/ProductServlet?method=find&Sorting=2&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}">价格排序</a></li>
					</c:if>
					<c:if test="${Sorting == 2}">
						<li class="sorter-item" data-type="default"><a href="${pageContext.request.contextPath}/ProductServlet?method=find&Sorting=1&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}">推荐排序</a></li>
						<li class="sorter-item active asc" id="sort-price" data-type="price"><a href="${pageContext.request.contextPath}/ProductServlet?method=find&Sorting=3&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}">价格(低->高)</a></li>
					</c:if>
					<c:if test="${Sorting == 3}">
						<li class="sorter-item" data-type="default"><a href="${pageContext.request.contextPath}/ProductServlet?method=find&Sorting=1&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}">推荐排序</a></li>
						<li class="sorter-item active desc" id="sort-price" data-type="price"><a href="${pageContext.request.contextPath}/ProductServlet?method=find&Sorting=2&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}">价格(高->低)</a></li>
					</c:if>
				</ul>
				<ul class="list-con">
				<c:choose>
					<c:when test="${empty products}">
						<p class="err-tip">很抱歉,没有找到您需要的商品。</p>
					</c:when>
					<c:otherwise>
						<c:forEach items="${products}" var="p">
							<li>
								<a href="${pageContext.request.contextPath}/ProductServlet?method=findDatils&productId=${p.PRODUCTID}&
								productName=${p.PRODUCTNAME}&productDesc=${p.PRODUCTDESC}&picture=${p.PICTURE}" target="_blank">
								<div class="img"><img src="/upload/${p.PICTURE}"/></div>
								<div class="price">¥${p.PRICE}</div>
								<div class="desc">${p.PRODUCTNAME}</div>
								</a>
								<input type="hidden" name="productDesc" value="${p.PRODUCTDESC}">
							</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</ul>
			</div>
			<c:if test="${totalRecords>10}">
			<!--分页容器 -->
			<div class="pagin">
				<ul class="pageList">
					<li class="pageItem">
						<a href="${pageContext.request.contextPath}/ProductServlet?method=find&currentpage=1&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}&Sorting=${Sorting}"><span class="pagepre">首页</span></a>
					</li>
					<li class="pageItem">
						<a href="${pageContext.request.contextPath}/ProductServlet?method=find&currentpage=${currentpage-1<=0?1:(currentpage-1)}&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}&Sorting=${Sorting}"><span class="pagepre">上页</span></a>
					</li>
					<li class="pageItem">
						<a href="${pageContext.request.contextPath}/ProductServlet?method=find&currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}&Sorting=${Sorting}"><span class="pagepre">下页</span></a>
					</li>
					<li class="pageItem">
						<a href="${pageContext.request.contextPath}/ProductServlet?method=find&currentpage=${totalpages}&firstTypeId=${firstTypeId}&secondTypeId=${secondTypeId}&productName=${productName}&Sorting=${Sorting}"><span class="pagepre">末页</span></a>
					</li>		
				</ul>
				<i>当前所在第<span>${currentpage}</span>页</i>
			</div>
			</c:if>
		</div>
		<!--尾部-->
		<div class="bottom">
			<div class="lianjie">
				<div class="bottom-lianjie">
				<a href="#">张永峰</a>|<a href="#">毕业设计</a>|<a href="#">关于本人</a>
				<hr style="margin-top: 10px;padding-bottom:5px;border-left: none;border-bottom: none;">
				<p>版本所有:<span>张永峰</span>&nbsp;此版本为毕业设计</p>
				</div>
			</div>
		</div>
	</body>
</html>