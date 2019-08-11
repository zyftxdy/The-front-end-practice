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
			.panel-body{
				margin-top: 40px;
			}
			.typeId{
				font-family: "微软雅黑";
				font-size: 14px;
				position: relative;
				top: -20px;				
			}
		</style>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>品类管理</span>
				<a href="productType-add.jsp" target="rightFrame">
					<img src="img/add.png"/>
					<span>添加品类</span></a>
			</div>
			<div class="panel-body">
				<div class="from-search">

				</div>
				<div class="table-list">
					<div class="typeId">
						<p>当前主品类ID:<span>${firstTypeId}</span></p>
					</div>
					<table class="product-list">
						<thead>
							<tr>
								<th width="30%">子品类ID</th>
								<th width="30%">子品类名称</th>
								<th width="40%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty TypeList}">
		                            <tr>
										<td class="text-center" colspan="3">暂无结果</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${TypeList}" var="t">
										<tr>
											<td>${t.typeId}</td>
											<td>${t.typeName}</td>
											<td>
												<a href="${pageContext.request.contextPath}/ProductTypeServlet?method=changeName2&secondTypeId=${t.typeId}&secondTypeName=${t.typeName}&firstTypeId=${firstTypeId}" target="rightFrame">修改</a>
												<!--<a href="${pageContext.request.contextPath}/ProductTypeServlet?method=deleteType&secondTypeId=${t.typeId}&firstTypeId=${firstTypeId}" target="rightFrame">删除</a>  -->
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>					
				</div>				
			</div>
		</div>		
	</body>
</html>