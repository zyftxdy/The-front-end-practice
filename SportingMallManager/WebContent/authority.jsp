<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/product-manger.css"/>
		<style type="text/css">
			.tipright p {
			    left: 38px;
			}
		</style>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function search(){
				var one=$(".from-group");
				var searchValue=one.find("#search").val();
				var name=document.getElementById("name").value;
				var url="${pageContext.request.contextPath}/AuthorityServlet";
				if(name==""){
					alert("请输入查询权限!");
				}else{
					if(name=="超级管理员"){
						url=url+"?authority=4";
					}else if(name=="会员管理"){
						url=url+"?authority=1";
					}else if(name=="商品管理"){
						url=url+"?authority=2";
					}else if(name=="订单管理"){
						url=url+"?authority=3";
					}
					window.location.href=url;
				}
			}
			
			var _authorityid;
			$(document).ready(function(){  

			  $(".sure").click(function(){
			  	$(".tip").fadeOut(100,function(){
			  		//下架操作
			  		var url="${pageContext.request.contextPath}/AuthorityServlet?method=deleteAuthority&authorityId="+_authorityid;
			  		window.location.href = url;
			  	});
			  	
			  });
	
			  $(".cancel").click(function(){
			  		$(".tip").fadeOut(100);
			  });
			  
			});
	
			//根据商品编号删除商品信息
			function deleteAuthority(id){
				  $(".tip").fadeIn(200);
				  _authorityid = id;	  
			}

		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>权限管理</span>
					<a href="addAuthority.jsp" target="rightFrame">
					<img src="img/add.png"/>
					<span class="btn">添加权限人员</span></a>
			</div>
			
			<div class="panel-body">
				<div class="from-search">
					<div class="from-group">
						<select name="search" id="search">
							<option value="按权限查询">按权限查询</option>
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
								<th width="10%">管理员账号</th>
								<th width="35%">登记时间</th>
								<th width="30%">权限</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty authoritys}">
		                            <tr>
										<td class="text-center" colspan="5">暂无结果</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${authoritys}" var="a">
										<tr>
											<td>${a.ID}</td>
											<td>${a.ADMINNAME}</td>
											<td>${a.CD}</td>
											<td>
												<c:if test="${a.AUTHORITY==1}">会员管理</c:if>
												<c:if test="${a.AUTHORITY==2}">商品管理</c:if>
												<c:if test="${a.AUTHORITY==3}">订单管理</c:if>
												<c:if test="${a.AUTHORITY==4}">超级管理员</c:if>
											</td>
											<td>
												<a href="${pageContext.request.contextPath}/AuthorityServlet?method=gotoAuthority&authority=${a.ID}&authorityName=${a.ADMINNAME}" target="rightFrame">修改</a>
												<a href="javaScript:deleteAuthority('${a.ID}')" target="rightFrame">删除</a>
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
				        	<a href="${pageContext.request.contextPath}/AuthorityServlet?currentpage=1&authority=${obj}">首页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/AuthorityServlet?currentpage=${currentpage-1<=0?1:(currentpage-1)}&authority=${obj}">上页</a>
				        </li>			     
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/AuthorityServlet?currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}&authority=${obj}">下页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/AuthorityServlet?currentpage=${totalpages}&authority=${obj}">末页</a>
				        </li>
			        </ul>
			    </div>
			   	<div class="tip">
			    	<div class="tiptop">
			    		<span>提示信息</span>
			    	</div>
			     	<div class="tipinfo">
				        <div class="tipright">
				        	<p>是否要删除该管理员权限?</p>
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