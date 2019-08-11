<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品详情--体育用品商城</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link type="text/css" rel="stylesheet" href="css/productDetails.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			
			//根据商品名称查询商品
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
			
			//增减商品数量
			function add()
		    {
		        var num = parseInt(document.getElementById("number").value);
		           document.getElementById("number").value = ++num;
		    }
		    function reduce()
		    {
		        var num = parseInt(document.getElementById("number").value);
		        if(num>1)
		        {
		           document.getElementById("number").value = --num;
		        }
		    }
		    
		    //重新查询商品价格图片库存
		    $(function(){
		    	var _color;
		    	var _shot;
		    	var _cloth;
				var productid=document.getElementById("productId").value;
				var colorstatus=document.getElementById("color-list").style.display;
				var clothstatus=document.getElementById("cloth-list").style.display;
				var shotstatus=document.getElementById("shot-list").style.display;
				if(colorstatus=="block" && clothstatus=="none" && shotstatus=="none"){
					$(".color-list").on("click",".color",function(){
						$(this).addClass('active').siblings('.color').removeClass('active');
						_color=this.innerHTML;
						$.ajax({
						    type : "POST",
							url:"${pageContext.request.contextPath}/ProductServlet?method=findDatilsByCd&productId="+productid+"&color="+_color,
						    success : function(data) {
						    	var str=data.split("#");
							    document.getElementById("price").innerHTML="¥"+str[0];
							    document.getElementById("nums").innerHTML=str[1];
							    document.getElementById("picture").src="/upload/"+str[2];
						    }  	
						  }); 
					});
				}else if(colorstatus=="block" && clothstatus=="block" && shotstatus=="none"){
					$(".cloth-list").on("click",".cloth",function(){
						$(this).addClass('active').siblings('.cloth').removeClass('active');
						_cloth=this.innerHTML;
						$(".color-list").on("click",".color",function(){
							$(this).addClass('active').siblings('.color').removeClass('active');
							_color=this.innerHTML;
						});
						if(_color!=null&&_color.length>0){
							$.ajax({
							    type : "POST",
								url:"${pageContext.request.contextPath}/ProductServlet?method=checkCount&productId="+productid+"&color="+_color+"&clothingsize="+_cloth,
							    async: false,
							    success : function(data){
							    	if(data=="true"){
							    		document.getElementById("buy").style.display="block";
							    		document.getElementById("notbuy").style.display="none";
							    		$.ajax({
										    type : "POST",
											url:"${pageContext.request.contextPath}/ProductServlet?method=findDatilsByCd&productId="+productid+"&color="+_color+"&clothingsize="+_cloth,
										    success : function(data) {
										    	var str=data.split("#");
											    document.getElementById("price").innerHTML="¥"+str[0];
											    document.getElementById("nums").innerHTML=str[1];
											    document.getElementById("picture").src="/upload/"+str[2];
										    }  	
										  });
							    	}else{
							    		document.getElementById("buy").style.display="none";
							    		document.getElementById("notbuy").style.display="block";
							    		$("#notbuy").css("pointer-events","none");
							    		
							    	}
							    }
							});		
						}
					});
					$(".color-list").on("click",".color",function(){
						$(this).addClass('active').siblings('.color').removeClass('active');
						_color=this.innerHTML;
						if(_cloth!=null&&_cloth.length>0){
							$.ajax({
							    type : "POST",
								url:"${pageContext.request.contextPath}/ProductServlet?method=checkCount&productId="+productid+"&color="+_color+"&clothingsize="+_cloth,
							    async: false,
							    success : function(data){
							    	if(data=="true"){
							    		document.getElementById("buy").style.display="block";
							    		document.getElementById("notbuy").style.display="none";
							    		$.ajax({
										    type : "POST",
											url:"${pageContext.request.contextPath}/ProductServlet?method=findDatilsByCd&productId="+productid+"&color="+_color+"&clothingsize="+_cloth,
										    success : function(data) {
										    	var str=data.split("#");
											    document.getElementById("price").innerHTML="¥"+str[0];
											    document.getElementById("nums").innerHTML=str[1];
											    document.getElementById("picture").src="/upload/"+str[2];
										    }  	
										  });
							    	}else{
							    		document.getElementById("buy").style.display="none";
							    		document.getElementById("notbuy").style.display="block";
							    		$("#notbuy").css("pointer-events","none");
							    	}
							    }
							});		
						}
					});
				}else if(colorstatus=="block" && clothstatus=="none" && shotstatus=="block"){
					$(".shot-list").on("click",".shot",function(){
						$(this).addClass('active').siblings('.shot').removeClass('active');
						_shot=this.innerHTML;
						$(".color-list").on("click",".color",function(){
							$(this).addClass('active').siblings('.color').removeClass('active');
							_color=this.innerHTML;
						});
						if(_color!=null&&_color.length>0){
							$.ajax({
							    type : "POST",
								url:"${pageContext.request.contextPath}/ProductServlet?method=checkCount&productId="+productid+"&color="+_color+"&shotsize="+_shot,
							    async: false,
							    success : function(data){
							    	if(data=="true"){
							    		document.getElementById("buy").style.display="block";
							    		document.getElementById("notbuy").style.display="none";
							    		$.ajax({
										    type : "POST",
											url:"${pageContext.request.contextPath}/ProductServlet?method=findDatilsByCd&productId="+productid+"&color="+_color+"&shotsize="+_shot,
										    success : function(data) {
										    	var str=data.split("#");
											    document.getElementById("price").innerHTML="¥"+str[0];
											    document.getElementById("nums").innerHTML=str[1];
											    document.getElementById("picture").src="/upload/"+str[2];
										    }  	
										  });
							    	}else{
							    		document.getElementById("buy").style.display="none";
							    		document.getElementById("notbuy").style.display="block";
							    		$("#notbuy").css("pointer-events","none");
							    		
							    	}
							    }
							});
						}
					});
					$(".color-list").on("click",".color",function(){
						$(this).addClass('active').siblings('.color').removeClass('active');
						_color=this.innerHTML;
						if(_shot!=null&&_shot.length>0){
							$.ajax({
							    type : "POST",
								url:"${pageContext.request.contextPath}/ProductServlet?method=checkCount&productId="+productid+"&color="+_color+"&shotsize="+_shot,
							    async: false,
							    success : function(data){
							    	if(data=="true"){
							    		document.getElementById("buy").style.display="block";
							    		document.getElementById("notbuy").style.display="none";
							    		$.ajax({
										    type : "POST",
											url:"${pageContext.request.contextPath}/ProductServlet?method=findDatilsByCd&productId="+productid+"&color="+_color+"&shotsize="+_shot,
										    success : function(data) {
										    	var str=data.split("#");
											    document.getElementById("price").innerHTML="¥"+str[0];
											    document.getElementById("nums").innerHTML=str[1];
											    document.getElementById("picture").src="/upload/"+str[2];
										    }  	
										  });
							    	}else{
							    		document.getElementById("buy").style.display="none";
							    		document.getElementById("notbuy").style.display="block";
							    		$("#notbuy").css("pointer-events","none");
							    		
							    	}
							    }
							});
						}
					});	
				}
		    });
		    
		    //购买商品
		    function buy(){
		    	var productName=document.getElementById("productName").innerHTML;
		    	var productId=document.getElementById("productId").value;
		    	var number=document.getElementById("number").value;
		    	var nums=document.getElementById("nums").innerHTML;
		    	var url="${pageContext.request.contextPath}/ProductServlet?method=buyProduct&productId="+productId+"&productName="+productName+"&number="+number;
		    	var colorstatus=document.getElementById("color-list").style.display;
				var clothstatus=document.getElementById("cloth-list").style.display;
				var shotstatus=document.getElementById("shot-list").style.display;
				var cloth=$(".cloth-list li.active").text();
		    	var color=$(".color-list li.active").text();
		    	var shot=$(".shot-list li.active").text();
				if(colorstatus=="block" && clothstatus=="none" && shotstatus=="none"){
					if(color==""){
						alert("您还没选择商品颜色!");
					}else{
						url=url+"&color="+color;
						window.location.href=url;
					}
				}else if(colorstatus=="block" && clothstatus=="block" && shotstatus=="none"){
					if(cloth==""){
						alert("您还没选择商品尺寸!");
					}else if(color==""){
						alert("您还没选择商品颜色!");
					}else{
						url=url+"&color="+color+"&cloth="+cloth;
						window.location.href=url;
					}
				}else if(colorstatus=="block" && clothstatus=="none" && shotstatus=="block"){
					if(shot==""){
						alert("您还没选择商品鞋码!");
					}else if(color==""){
						alert("您还没选择商品颜色!");
					}else{
						if(nums==0){
							alert("该商品当前库存不足!");
						}else{
							url=url+"&color="+color+"&shot="+shot;
							window.location.href=url;
						}
					}
				}
		    }
		    
		    //改变图片
		    function change(){
		    	var picture=document.getElementById("small-img").src;
		    	document.getElementById("picture").src=picture;
		    }
		</script>
	</head>
	<body>
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
				<input type="text" id="search" class="search" placeholder="请输入商品名称"/>
				<a href="javaScript:search();" class="submit" target="_blank">搜索</a>
			</div>
			<!--导航-->
			<div class="nav-main">
				<div class="w">
					<a href="${pageContext.request.contextPath}/ProductTypeServlet">ZYF</a>
					<span>></span>
					<a>商品详情</a>
				</div>
			</div>
			<!--商品详情-->
			<div class="details">
				<div class="img-wrap">
					<div class="big-img" >
						<img src="/upload/${picture}" id="picture"/>
					</div>
					<div class="small-img">
						<img src="/upload/${picture}" id="small-img" onclick="javaScript:change();"/>
					</div>
				</div>
				<div class="info-wrap">
					<p class="p-name">【毕业设计测试】<span id="productName">${productName}</span></p>
					<input type="hidden" name="productId" id="productId" value="${productId}"/>
					<div class="p-desc">
						<p>详情:<span>${productDesc}</span></p>
					</div>
					<div class="p-price">
						<c:if test="${maxPrice==minPrice}">
							<p>价格:<span id="price">¥${minPrice}</span></p>
						</c:if>
						<c:if test="${maxPrice!=minPrice}">
							<p>价格:<span id="price">¥${minPrice}～¥${maxPrice}</span></p>
						</c:if>
					</div>
					<c:choose>
					<c:when test="${empty shots.get(0)}">
						<div class="text-list" id="shot-list" style="display: none;"></div>
					</c:when>
					<c:otherwise>
						<div class="text-list" id="shot-list" style="display:block;">
							<div class="text-label">鞋码:</div>
							<div class="text">
								<ul class="shot-list" id="shot-data">
									<c:forEach items="${shots}" var="shot">
										<li class="shot">${shot}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${empty cloths.get(0)}">
							<div class="text-list" id="cloth-list" style="display:none;"></div>
						</c:when>
						<c:otherwise>
						<div class="text-list" id="cloth-list" style="display:block;">
							<div class="text-label">尺寸:</div>
							<div class="text">
								<ul class="cloth-list" id="cloth-data">
									<c:forEach items="${cloths}" var="cloth">
										<li class="cloth">${cloth}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${empty colours.get(0)}">
							<div class="text-list" id="color-list" style="display:none;"></div>
						</c:when>
						<c:otherwise>
						<div class="text-list" id="color-list" style="display:block;">
							<div class="text-label">颜色:</div>
							<div class="text">
								<ul class="color-list" id="color-data">
									<c:forEach items="${colours}" var="color">
										<li class="color">${color}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:otherwise>
					</c:choose>
					<div class="p-amount">
						<p>数量:</p>
						<input class="p-count" value="1" id="number" name="number" readonly="readonly">
						<button class="add" onclick="add()">+</button><button class="reduce" onclick="reduce()">-</button>
						<p class="p-invo">（库存:<span id="nums">${nums}</span>）</p>
					</div>
					<div class="buy" id="buy" style="display:block;">
						<a href="javaScript:buy();">加入购物车</a>
					</div>
					<div class="notbuy" id="notbuy" style="display: none;">
						<p>该商品暂时没货哦!</p>
					</div>
				</div>
			<!--商品评论-->
			<div class="comment">
				<div class="comment-tab">
					<p>累计评价<span>${rows}</span></p>
				</div>
				<div class="comment-main">
					<c:choose>
						<c:when test="${empty comments}">
								<ul>
									<li>
										<i>&nbsp;</i>
										<p>该商品暂时还没人评论呢(づ￣3￣)づ╭❤～</p>
										<hr style="margin-top: 15px;">
									</li>
								</ul>
								
						</c:when>
						<c:otherwise>
							<c:forEach items="${comments}" var="c">
								<ul>
									<li>
										<i>${c.USERNAME}:</i>
										<p>${c.COMMENTDATILS}</p>
										<hr style="margin-top: 15px;">
									</li>
								</ul>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<div class="comment-page">
						<a href="#"><span class="pagepre">下一页</span></a>
						<a href="#"><span class="pagepre">上一页</span></a>
					</div>
				</div>
			</div>
			</div>
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