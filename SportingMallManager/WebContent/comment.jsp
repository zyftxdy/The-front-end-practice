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
			function search(){
				var one=$(".from-group");
				var searchValue=one.find("#search").val();
				var name=document.getElementById("name").value;
				var url="${pageContext.request.contextPath}/CommentServlet";
				//alert(searchValue);
				if(searchValue=="按评论ID查询"){
					url=url+"?commentId="+name;
				}else if(searchValue=="按商品ID查询"){
					url=url+"?productId="+name;
				}
				window.location.href=url;
			}
			
			var _commentId;
			$(document).ready(function(){  

			  $(".sure").click(function(){
			  	$(".tip").fadeOut(100,function(){
			  		//下架操作
			  		var url="${pageContext.request.contextPath}/CommentServlet?method=deleteComment&commentId="+_commentId;
			  		window.location = url;
			  	});
			  	
			  });
	
			  $(".cancel").click(function(){
			  		$(".tip").fadeOut(100);
			  });
			  
			});
	
			//根据商品编号删除商品信息
			function deleteComment(commentId){
				  $(".tip").fadeIn(200);
				  _commentId = commentId;	  
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>评论管理</span>
			</div>
			<div class="panel-body">
				<div class="from-search">
					<div class="from-group">
						<select name="search" id="search">
							<option value="按评论ID查询">按评论ID查询</option>
							<option value="按商品ID查询">按商品ID查询</option>
						</select>
					</div>
					<input type="text" id="name" placeholder="关键字"/>
					<a href="javaScript:search();" target="rightFrame"><span>查询</span></a>
				</div>
				<div class="table-list">
					<table class="product-list">
						<thead>
							<tr>
								<th width="5%">评论ID</th>
								<th width="10%">商品ID</th>
								<th width="10%">会员名</th>
								<th width="40%">评论详情</th>
								<th width="20%">评论时间</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty comments}">
		                            <tr>
										<td class="text-center" colspan="6">暂无结果</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${comments}" var="c">
										<tr>
											<td>${c.COMMENTID}</td>
											<td>${c.PRODUCTID}</td>
											<td>${c.USERNAME}</td>
											<td>${c.COMMENTDATILS}</td>
											<td>${c.CD}</td>
											<td>
												<a href="javaScript:deleteComment('${c.COMMENTID}')" target="rightFrame">删除</a>
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
				        	<a href="${pageContext.request.contextPath}/CommentServlet?currentpage=1&commentId=${c.COMMENTID}&productId=${c.PRODUCTID}">首页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/CommentServlet?currentpage=${currentpage-1<=0?1:(currentpage-1)}&commentId=${c.COMMENTID}&productId=${c.PRODUCTID}">上页</a>
				        </li>			     
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/CommentServlet?currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}&commentId=${c.COMMENTID}&productId=${c.PRODUCTID}">下页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/CommentServlet?currentpage=${totalpages}&commentId=${c.COMMENTID}&productId=${c.PRODUCTID}">末页</a>
				        </li>
			        </ul>
			    </div>	
			     <div class="tip">
			    	<div class="tiptop">
			    		<span>提示信息</span>
			    	</div>
			     	<div class="tipinfo">
				        <div class="tipright">
				        	<p>是否要删除该评论?</p>
				        </div>
			       </div>
			        <div class="tipbtn">
				        <input name="" type="button"  class="sure" value="确定" />&nbsp;
				        <input name="" type="button"  class="cancel" value="取消" />
			        </div>
    			</div>			
			</div>
		</div>
	</body>
</html>

