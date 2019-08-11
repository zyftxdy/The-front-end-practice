<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/product-manger.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			var _productid;
			$(document).ready(function(){  

			  $(".sure").click(function(){
			  	$(".tip").fadeOut(100,function(){
			  		//下架操作
			  		var url="${pageContext.request.contextPath}/ProductServlet?method=updateStatus&productid="+_productid;
			  		window.location = url;
			  	});
			  	
			  });
	
			  $(".cancel").click(function(){
			  		$(".tip").fadeOut(100);
			  });
			  
			});
	
			//根据商品编号删除商品信息
			function delproductbyid(productid){
				  $(".tip").fadeIn(200);
				  _productid = productid;	  
			}
			
			$(document).ready(function(){  
				
				  $(".sure2").click(function(){
				  	$(".tip2").fadeOut(100,function(){
				  		//限制登录操作
				  		var url="${pageContext.request.contextPath}/ProductServlet?method=recoverStatus&productid="+_productid;
				  		window.location = url;
				  	});
				  	
				  });
		
				  $(".cancel2").click(function(){
				  		$(".tip2").fadeOut(100);
				  });
				  
				});
		
				//根据商品编号删除商品信息
				function revproductbyid(productid){
					  $(".tip2").fadeIn(200);
					  _productid = productid;	  
				}
			
			//查找条件
			function search(){
				var one=$(".from-group");
				var searchValue=one.find("#search").val();
				var name=document.getElementById("name").value;
				var url="${pageContext.request.contextPath}/ProductServlet";
				//alert(searchValue);
				if(name==""){
					window.location.href=url;
				}else{
					if(searchValue=="按商品ID查询"){
						var reg = /^\d+$/;
						if(!reg.test(name)){
							alert("请输入商品ID");
						}else{
							url=url+"?productId="+name;
							window.location.href=url;
						}	
					}else if(searchValue=="按商品名称查询"){
						var reg = /^\d+$/;
						if(reg.test(name)){
							alert("请输入商品名称");
						}else{
							url=url+"?productName="+name;
							window.location.href=url;
						}

					}
				}
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>商品管理</span>
				<a href="productAdd.jsp" target="rightFrame">
					<img src="img/add.png"/>
					<span>添加商品</span></a>
			</div>
			<div class="panel-body">
				<div class="from-search">
					<div class="from-group">
						<select name="search" id="search">
							<option value="按商品ID查询">按商品ID查询</option>
							<option value="按商品名称查询">按商品名称查询</option>
						</select>
					</div>
					<input type="text" id="name" placeholder="关键字"/>
					<a href="javaScript:search();" target="rightFrame"><span>查询</span></a>
				</div>
				<div class="table-list">
					<table class="product-list">
						<thead>
							<tr>
								<th width="10%">id</th>
								<th width="40%">商品名称</th>
								<th width="20%">创建时间</th>
								<th width="15%">状态</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty products}">
		                            <tr>
										<td class="text-center" colspan="5">暂无结果</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${products}" var="p">
										<tr>
											<td>${p.PRODUCTID}</td>
											<td>${p.PRODUCTNAME}</td>
											<td>${p.CD}</td>
											<td>${p.PRODUCTSTATUS}</td>
											<td>
												<a href="${pageContext.request.contextPath}/ProductServlet?method=update&productid=${p.PRODUCTID}" target="rightFrame">编辑</a>
												<a href="javaScript:delproductbyid('${p.PRODUCTID}')" target="rightFrame">下架</a>
												<a href="javaScript:revproductbyid('${p.PRODUCTID}')" target="rightFrame">上架</a>
												<a href="${pageContext.request.contextPath}/ProductServlet?method=datils&productid=${p.PRODUCTID}&productName=${p.PRODUCTNAME}" target="rightFrame">详情</a>
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>					
				</div>
				<!--分页-->
			    <div class="pagin">
			    	<div class="message">
			    		总计<i class="blue">${totalRecords}</i>条记录，共<i class="blue">${totalpages}</i>页，当前显示第<i class="blue">${currentpage}</i>页
			    	</div>
			        <ul class="paginList">
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?currentpage=1&productId=${productId}&productName=${productName}">首页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?currentpage=${currentpage-1<=0?1:(currentpage-1)}&productId=${productId}&productName=${productName}">上页</a>
				        </li>			     
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}&productId=${productId}&productName=${productName}">下页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?currentpage=${totalpages}&productId=${productId}&productName=${productName}">末页</a>
				        </li>
			        </ul>
			    </div>
			    <div class="tip">
			    	<div class="tiptop">
			    		<span>提示信息</span>
			    	</div>
			     	<div class="tipinfo">
				        <div class="tipright">
				        	<p>是否要下架该商品?</p>
				        </div>
			       </div>
			        <div class="tipbtn">
				        <input name="" type="button"  class="sure" value="确定" />&nbsp;
				        <input name="" type="button"  class="cancel" value="取消" />
			        </div>
    			</div>
    			
    			<div class="tip2">
			    	<div class="tiptop2">
			    		<span>提示信息</span>
			    	</div>
			     	<div class="tipinfo2">
				        <div class="tipright2">
				        	<p>是否重新上架该商品?</p>
				        </div>
			       </div>
			        <div class="tipbtn2">
				        <input name="" type="button"  class="sure2" value="确定" />&nbsp;
				        <input name="" type="button"  class="cancel2" value="取消" />
			        </div>
    			</div>					
			</div>
		</div>
	</body>
</html>