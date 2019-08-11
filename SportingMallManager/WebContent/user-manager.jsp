<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/user-manager.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			var _userid;
			$(document).ready(function(){  
	
			  $(".sure").click(function(){
			  	$(".tip").fadeOut(100,function(){
			  		//限制登录操作
			  		var url="${pageContext.request.contextPath}/UserServlet?method=updateStatus&userid="+_userid;
			  		window.location = url;
			  	});
			  	
			  });
	
			  $(".cancel").click(function(){
			  		$(".tip").fadeOut(100);
			  });
			  
			});
	
			//根据商品编号删除商品信息
			function updateStatus(userid){
				  $(".tip").fadeIn(200);
				  _userid = userid;	  
			}
			
			$(document).ready(function(){  
				
				  $(".sure2").click(function(){
				  	$(".tip2").fadeOut(100,function(){
				  		//限制登录操作
				  		var url="${pageContext.request.contextPath}/UserServlet?method=recoverStatus&userid="+_userid;
				  		window.location = url;
				  	});
				  	
				  });
		
				  $(".cancel2").click(function(){
				  		$(".tip2").fadeOut(100);
				  });
				  
				});
		
				//根据商品编号删除商品信息
				function recoverStatus(userid){
					  $(".tip2").fadeIn(200);
					  _userid = userid;	  
				}
			//查询条件
			function search(){
				var userid=document.getElementById("name").value;
				var url="${pageContext.request.contextPath}/UserServlet";
				if(userid!=""){
					url=url+"?userid="+userid;
				}else{
					url=url;
				}
				window.location.href=url;
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>会员管理</span>
			</div>
			<div class="panel-body">
				<div class="from-search">
					<div class="from-group">
						<select name="search" id="search">
							<option value="按会员ID查询">按会员ID查询</option>
						</select>
					</div>
					<input type="text" id="name" placeholder="关键字"/>
					<a href="javaScript:search();" target="rightFrame"><span>查询</span></a>
				</div>
				<div class="table-list">
					<table class="product-list">
						<thead>
							<tr>
								<th width="10%">会员ID</th>
								<th width="15%">会员账号</th>
								<th width="30%">联系方式</th>
								<th width="20%">注册时间</th>
								<th width="10%">会员权限</th>
								<th width="15">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty users}">
		                            <tr>
										<td class="text-center" colspan="6">暂无结果</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${users}" var="u">
										<tr>
											<td>${u.USERID}</td>
											<td>${u.USERNAME}</td>
											<td>${u.TELEPHONE}</td>
											<td>${u.CD}</td>
											<td>${u.STATUS}</td>
											<td>
												<a href="javaScript:updateStatus('${u.USERID }');" target="rightFrame">限制登录</a>
												<a href="javaScript:recoverStatus('${u.USERID }');" target="rightFrame">恢复正常</a>
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
				        	<a href="${pageContext.request.contextPath}/UserServlet?currentpage=1&userid=${userid}">首页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/UserServlet?currentpage=${currentpage-1<=0?1:(currentpage-1)}&userid=${userid}">上页</a>
				        </li>			     
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/UserServlet?currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}&userid=${userid}">下页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/UserServlet?currentpage=${totalpages}&userid=${userid}">末页</a>
				        </li>
			        </ul>
			    </div>
			    
			    <div class="tip">
			    	<div class="tiptop">
			    		<span>提示信息</span>
			    	</div>
			     	<div class="tipinfo">
				        <div class="tipright">
				        	<p>是否要限制此会员登录?</p>
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
				        	<p>是否允许此会员登录?</p>
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