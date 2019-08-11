<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link type="text/css" rel="stylesheet" href="css/order-from-details.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function comment(productId){
				_productId=productId;
				var status=document.getElementById("status").innerText;
				var url="${pageContext.request.contextPath}/OrderServlet?method=comment";
				if(status=="已支付" || status=="已发货" || status=="已收货"){
					url=url+"&productId="+_productId;
					window.location.href=url;
					//交给servlet
				}else{
					alert("请付款后再评论！");
				}
			}
			
			//支付订单
			function pay(){
				var orderNo=document.getElementById("orderNo").value;
				var total=document.getElementById("total").value;
				var url="${pageContext.request.contextPath}/OrderServlet?method=payOrder&orderNo="+orderNo+"&total="+total;
				window.top.location.href=url;
			}
			
			//取消订单
			function cancel(){
				var orderNo=document.getElementById("orderNo").value;
				if(confirm("确定要取消该订单嘛？")){
					$.ajax({
					    type : "POST",
						url:"${pageContext.request.contextPath}/OrderServlet?method=cancelOrder&orderNo="+orderNo,
					    success : function(data) {
					    	if(data=="true"){
					    		document.getElementById("status").innerHTML="已取消";
					    		document.getElementById("operate").innerHTML="";
					    	}
					    }  	
					  });  	
				}
			}
			
			//确认收货
			function get(){
				var orderNo=document.getElementById("orderNo").value;
				if(confirm("确定要收货嘛？")){
					$.ajax({
					    type : "POST",
						url:"${pageContext.request.contextPath}/OrderServlet?method=getOrder&orderNo="+orderNo,
					    success : function(data) {
					    	if(data=="true"){
					    		document.getElementById("status").innerHTML="已收货";
					    		document.getElementById("operate").innerHTML="";
					    	}
					    }  	
					  });  	
				}
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">订单信息</div>
			<div class="panel-body">
				<div class="order-info">
					<div class="text-line">
						<span class="text-label">订单号:</span>
						<span class="text">${orderAddress.orderno}</span>
						<input type="hidden" name="orderNo" id="orderNo"  value="${orderAddress.orderno}"/>
						<input type="hidden" name="total" id="total"  value="${orderAddress.total}"/>
						</div>
					<div class="text-line">
						<span class="text-label">创建时间:</span>
						<span class="text">${orderAddress.create_date}</span> </div>
					<div class="text-line">
						<span class="text-label">收件人:</span>
						<span class="text">${orderAddress.addressname}</span></div>
					<div class="text-line">
						<span class="text-label">收件地址:</span>
						<span class="text">${orderAddress.province}${orderAddress.city}${orderAddress.addressdatils}</span></div>
					<div class="text-line">
						<span class="text-label">订单状态:</span>
						<c:if test="${orderAddress.orderstatus==0}">
							<span class="text" id="status">已取消</span>
						</c:if>
						<c:if test="${orderAddress.orderstatus==1}">
							<span class="text" id="status">未支付</span>
						</c:if>
						<c:if test="${orderAddress.orderstatus==2}">
							<span class="text" id="status">已支付</span>
						</c:if>
						<c:if test="${orderAddress.orderstatus==3}">
							<span class="text" id="status">已发货</span>
						</c:if>
						<c:if test="${orderAddress.orderstatus==4}">
							<span class="text" id="status">已收货</span>
						</c:if>
					</div>
					<!--判断订单状态是否支付-->
					<c:if test="${orderAddress.orderstatus==1}">
						<div class="text-line" id="operate">
								<a href="javaScript:pay();" target="_top">去支付</a>
								<a href="javaScript:cancel();">取消订单</a>
						</div>
					</c:if>
					<c:if test="${orderAddress.orderstatus==3}">
						<div class="text-line" id="operate">
								<a href="javaScript:get();" class="get">确认收货</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
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
								<th class="list-header-operate">&nbsp;</th>
							</tr>
						</tbody>
					</table>
					<table class="list-body">
						<tbody>
							<c:forEach items="${orderDatils}" var="od">
								<tr>
									<td class="list-img"><a href="#" target="_blank"><img src="/upload/${od.PICTURE}"/></a></td>
									<td class="list-info"><a href="#" target="_blank">${od.PRODUCTNAME}</a></td>
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
									<input type="hidden" id="prodcutId" value="${od.PRODUCTID}">
									<input type="hidden" id="prodcutName" value="${od.PRODUCTNAME}">
									</td>
									<td class="list-total">￥${od.PRICE*od.QUANTITY}</td>
									<td class="list-operate">
										<a href="javaScript:comment('${od.PRODUCTID}');" target="rightframe">前往评论</a>
									</td>
								</tr>
							</c:forEach>							
						</tbody>						
					</table>
				</div>
			</div>
			<p class="total-price">
				<span>订单总价:</span>
				<span class="enhance">￥${orderAddress.total}</span>
			</p>
		</div>
	</body>
</html>