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
				var name=document.getElementById("name").value;
				var url="${pageContext.request.contextPath}/OrderServlet";
				if(name==""){
					alert("请输入订单号!");
				}else{
					url=url+"?orderNo="+name;
					window.location.href=url;
				}
				
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>订单管理</span>
			</div>
			<div class="panel-body">
				<div class="from-search">
					<div class="from-group">
						<select name="search" id="search">
							<option value="按商品ID查询">按订单号查询</option>
						</select>
					</div>
					<input type="text" id="name" placeholder="订单号"/>
					<a href="javaScript:search();" target="rightFrame"><span>查询</span></a>
				</div>
				<div class="table-list">
					<table class="product-list">
						<thead>
							<tr>
								<th width="30%">订单号</th>
								<th width="10%">收件人</th>
								<th width="10%">订单状态</th>
								<th width="10%">订单总价</th>
								<th width="30%">创建时间</th>
								<th width="10">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${empty order}">
		                            <tr>
										<td class="text-center" colspan="6">暂无结果</td>
									</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${order}" var="o">
									<tr>
										<td>${o.ORDERNO}
											<input type="hidden" name="orderId" id="orderId" value="${o.ORDERID}"/>
										</td>
										<td>${o.ADDRESSNAME }</td>
										<td>
											<c:if test="${o.ORDERSTATUS==0}">已取消</c:if>
											<c:if test="${o.ORDERSTATUS==1}">未支付</c:if>
											<c:if test="${o.ORDERSTATUS==2}">已支付</c:if>
											<c:if test="${o.ORDERSTATUS==3}">已发货</c:if>
											<c:if test="${o.ORDERSTATUS==4}">已收货</c:if>
										</td>
										<td>¥${o.TOTAL}</td>
										<td>${o.CD}</td>
										<td>
											<a href="${pageContext.request.contextPath}/OrderServlet?method=findDatils&orderNo=${o.ORDERNO}" target="rightFrame">查看</a>
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
				        	<a href="${pageContext.request.contextPath}/OrderServlet?currentpage=1&orderNo=${orderNo}">首页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/OrderServlet?currentpage=${currentpage-1<=0?1:(currentpage-1)}&orderNo=${orderNo}">上页</a>
				        </li>			     
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/OrderServlet?currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}&orderNo=${orderNo}">下页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/OrderServlet?currentpage=${totalpages}&orderNo=${orderNo}">末页</a>
				        </li>
			        </ul>
			    </div>				
			</div>
		</div>		
	</body>
</html>