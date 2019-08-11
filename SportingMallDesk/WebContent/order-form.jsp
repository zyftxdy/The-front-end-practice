<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link type="text/css" rel="stylesheet" href="css/order-form.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function details(orderName){
				_orderName=orderName;
				//alert(_orderName);
				var url="${pageContext.request.contextPath}/OrderServlet?method=findOrderDatils&orderNo="+_orderName;
				//alert(url);
				window.location.href=url;
			}
		</script>
	</head>
	<body class="body">
		<div class="panel">
			<div class="panel-title">
				<span>我的订单</span>
			</div>
			<div class="panel-body">
				<div class="order-list">
					<table class="list-header">
						<tbody>
							<tr>
								<th class="list-header-img">&nbsp;</th>
								<th class="list-header-info">商品信息</th>
								<th class="list-header-datils">商品详情</th>
								<th class="list-header-price">单价</th>
								<th class="list-header-count">数量</th>
								<th class="list-header-total">合计</th>
							</tr>
						</tbody>
					</table>
					<c:choose>
					<c:when test="${empty order}">
						<table class="list-body">
							<tr>
								<td style="text-align: center;line-height: 40px;color: #666;">暂无订单信息</td>
							</tr>
						</table>
					</c:when>
					<c:otherwise>
						<c:forEach items="${order}" var="o">
							<table class="list-body">
								<tbody>
									<tr>
										<td class="order-info" colspan="6">
											<span class="order-text">
												<span>订单号:</span>
												<i id="orderName">${o.ORDERNO}</i>
											</span>
											<span class="order-text">${o.CD}</span>
											<span class="order-text">收件人:${o.ADDRESSNAME}</span>
											<span class="order-text">
												<c:if test="${o.ORDERSTATUS==0}">
													订单状态:已取消
												</c:if>
												<c:if test="${o.ORDERSTATUS==1}">
													订单状态:未支付
												</c:if>
												<c:if test="${o.ORDERSTATUS==2}">
													订单状态:已支付
												</c:if>
												<c:if test="${o.ORDERSTATUS==3}">
													订单状态:已发货
												</c:if>
												<c:if test="${o.ORDERSTATUS==4}">
													订单状态:已收货
												</c:if>
											</span>
											<span class="order-text">订单总价:<span class="total">￥${o.TOTAL}</span></span>
											<span class="order-text"><a href="javaScript:details('${o.ORDERNO}')" target="rightframe">查看详情></a></span>
										</td>
									</tr>
									<c:forEach items="${orderDatils}" var="od">
										<c:if test="${o.ORDERNO==od.ORDERNO}">
											<tr>
												<td class="list-img"><a href="#"><img src="/upload/${od.PICTURE}"/></a></td>
												<td class="list-info"><a href="#">${od.PRODUCTNAME}</a></td>
												<td class="list-datils">
													<ul>
														<c:if test="${od.COLOUR!=null}">
															<li>颜色:${od.COLOUR}</li>
														</c:if>
														<c:if test="${od.CLOTHINGSIZE!=null}">
															<li>尺寸:${od.CLOTHINGSIZE}</li>
														</c:if>
														<c:if test="${od.SHOTSIZE!=null}">
															<li>鞋码:${od.SHOTSIZE}</li>
														</c:if>
													</ul>
												</td>
												<td class="list-price">￥${od.PRICE}</td>
												<td class="list-count">${od.QUANTITY}
												</td>
												<td class="list-total">￥${od.PRICE*od.QUANTITY}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>						
							</table>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</div>
				<!--分页-->
			    <div class="pagin">
			        <ul class="paginList">
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/OrderServlet?method=findOrder&currentpage=${currentpage-1<=0?1:(currentpage-1)}">上页</a>
				        </li>			     
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/OrderServlet?method=findOrder&currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}">下页</a>
				        </li>
			        </ul>
			    </div>
			</div>
		</div>
	</body>
</html>