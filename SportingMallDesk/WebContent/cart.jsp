<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>购物车--体育用品商城</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link type="text/css" rel="stylesheet" href="css/cart.css" />
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
			
			//商品数量的加减
			$(document).ready(function(){
				$("[type='button']").click(function (){
					var v=$(this).val();
					var num=$("#number",this.parentNode);
					var cid=$("#cid",this.parentNode);
					var price=$("#price",this.parentNode);
					var id=cid.val();
					var n=num.val();
					var p=price.val();
					if('+'==v){
						num.val(parseInt(n)+1);
						btnClick1(id,p,$(this).parents("tr"));
					}else{
						if(parseInt(n)>=2){
							num.val(parseInt(n)-1);
							btnClick2(id,p,$(this).parents("tr"));
						}
					}
					showTotal();
				});
			});
			function  btnClick1(id,price,tr) {
		    	 //1.获取XMLHttpRequest对象
				$.ajax({
				    type : "POST",
					url:"${pageContext.request.contextPath}/CartServlet?method=change1&cid="+id+"&price="+price,
					async: false,
				    success : function(data) {
				    	$("#list-total",tr).text("￥"+data);
				    	//document.getElementById("list-total").innerHTML="￥"+data;
				    }  	
				  });
		    }
			function  btnClick2(id,price,tr) {
		    	 //1.获取XMLHttpRequest对象
				$.ajax({
				    type : "POST",
					url:"${pageContext.request.contextPath}/CartServlet?method=change2&cid="+id+"&price="+price,
					async: false,
				    success : function(data) {
				    	$("#list-total",tr).text("￥"+data);
				    	//document.getElementById("list-total").innerHTML="￥"+data;
				    }  	
				  });
		    }
			
			//批量删除商品
			function batchDelProductById(){
				var _checkProductId =document.getElementsByName('checkProductId');
				var url="${pageContext.request.contextPath}/CartServlet?method=deleteProducts";
				for(var i=0;i<_checkProductId.length;i++){
					if(_checkProductId[i].checked == true){
						url=url+"&cid="+_checkProductId[i].value;
					}
				}
				window.location.href=url;
			}
			
			//全选与全不选
			function selectdisplayall(currobj){
			    var flag =currobj.checked;
			    var _checkProductId = document.getElementsByName('checkProductId');
			    for(var i=0;i<_checkProductId.length;i++){
			    	_checkProductId[i].checked = flag; 
			    }
			    showTotal();
			}
			
			$(document).ready(function(){
				 $('.checkbox').on('click', function () {
			            // 选择多选框后,再重新计算
			            showTotal();
			            changechecked();
			        });

			});
			
			//更改商品状态
			function changechecked(){
				var _checkProductId =document.getElementsByName('checkProductId');
				var url="${pageContext.request.contextPath}/CartServlet?method=checked";
				var url1="${pageContext.request.contextPath}/CartServlet?method=unchecked";
				for(var i=0;i<_checkProductId.length;i++){
					if(_checkProductId[i].checked == true){
						url=url+"&cid="+_checkProductId[i].value;
						$.ajax({
						    type : "POST",
							url:url,  	
						 });
					}else if(_checkProductId[i].checked == false){
						url1=url1+"&cid="+_checkProductId[i].value;
						$.ajax({
						    type : "POST",
							url:url1, 	
						 });
					}
				}
			}
			
			//计算总价
			function showTotal(){
				var _checkProductId =document.getElementsByName('checkProductId');
				var url="${pageContext.request.contextPath}/CartServlet?method=showTotal";
				for(var i=0;i<_checkProductId.length;i++){
					if(_checkProductId[i].checked == true){
						url=url+"&cid="+_checkProductId[i].value;
					}
				}
				$.ajax({
				    type : "POST",
					url:url,
				    success : function(data) {
				    	if(data==null && "".equals(data)){
				    		document.getElementById("sumbit-total").innerHTML=data;
				    	}else{
				    		document.getElementById("sumbit-total").innerHTML=data;
				    	}
				    }  	
				 });
			}
			
			//结算
			function pure(){
				var total=document.getElementById("sumbit-total").innerHTML;
				var url="${pageContext.request.contextPath}/OrderServlet?method=pure";
				if(total==0){
					alert("请选择商品后进行结算!");
				}else{
					url=url+"&total="+total;
					window.location.href=url;
				}
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
					<a>我的购物车</a>
				</div>
			</div>
			<!--购物车-->
			<div class="main-cart">
				<div class="cart-header">
					<table class="cart-table">
						<tbody>
							<tr>
								<th class="cart-check"><input type="checkbox" class="select-all" onchange="selectdisplayall(this);"/><span>全选</span></th>
								<th class="cart-info">商品信息</th>
								<th class="cart-datils">商品详情</th>
								<th class="cart-price">单价</th>
								<th class="cart-count">数量</th>
								<th class="cart-total">合计</th>
								<th class="cart-opera">操作</th>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="cart-list">
					<table class="cart-table">
						<c:choose>
							<c:when test="${empty cart}">
								<tr>
									<td class="text-center"  colspan="8">购物车空空如也;<a href="${pageContext.request.contextPath}/ProductTypeServlet">先去购物吧</a></td>
								</tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${cart}" var="c">
								<tr>
									<td class="list-check"><input type="checkbox" class="checkbox" name="checkProductId" value="${c.ID}"/></td>
									<td class="list-img"><a href="#" target="_blank"><img src="/upload/${c.PICTURE}"/></a></td>
									<td class="list-info"><a href="#" target="_blank">${c.PRODUCTNAME}</a></td>
									<td class="list-datils">
										<ul>
										<c:if test="${c.COLOUR!=null}">
											<li>颜色:${c.COLOUR}</li>
										</c:if>
										<c:if test="${c.CLOTHINGSIZE!=null}">
											<li>尺寸:${c.CLOTHINGSIZE}</li>
										</c:if>
										<c:if test="${c.SHOTSIZE!=null}">
											<li>鞋码:${c.SHOTSIZE}</li>
										</c:if>
										</ul>
									</td>
									<td class="list-price">￥${c.PRICE}</td>
									<td class="list-count">
										<input type="button" class="reduce" value="-"/>
										<input class="p-count" value="${c.QUANTITY}" id="number" readonly="readonly">
										<input type="hidden" name="id" id="cid" value="${c.ID}"/>
										<input type="hidden" name="datilsid" id="datilsid" value="${c.DATILSID}"/>
										<input type="hidden" name="price" id="price" value="${c.PRICE}"/>
										<input type="hidden" name="productId" id="productId" value="${c.PRODUCTID}"/>  
										<input type="button" class="add" value="+"/>
									</td>
									<td class="list-total" id="list-total">￥${c.PRICE*c.QUANTITY}</td>
									<td class="list-opera"><a href="${pageContext.request.contextPath}/CartServlet?method=deleteProduct&cid=${c.ID}">删除</a></td>
								</tr>
							</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
				<div class="cart-footer">
					<div class="select-on">
						<label><input type="checkbox" class="select-all" onchange="selectdisplayall(this);"/><span>全选</span></label>
					</div>
					<div class="delete-on">
						<a href="javaScript:batchDelProductById();"><img src="img/laji.png"/>删除选中</a>
					</div>
					<div class="sumbit-on">
						<span>总价:</span>
						<span class="sumbit-total">￥<i id="sumbit-total">0</i></span>
						<a href="javaScript:pure();">去结算</a>
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