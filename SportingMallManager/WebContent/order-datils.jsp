<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/order-datils.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function status(){
				//ajax技术实现发货
				var orderNo=document.getElementById("orderNo").innerHTML;
				if(confirm("确定要发货嘛？")){
					$.ajax({
					    type : "POST",
						url:"${pageContext.request.contextPath}/OrderServlet?method=deliverOrder&orderNo="+orderNo,
					    success : function(data) {
					    	if(data=="true"){
					    		document.getElementById("status").innerHTML="已发货";
					    		document.getElementById("deliver").style.display="none";
					    	}
					    }  	
					  });
					}
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">订单详情</div>
			<div class="panel-body">
				<div class="text-line">
					<div class="text-lable">订单号:</div>
					<div class="text" id="orderNo">${orderAddress.orderno}</div>
				</div>
				<div class="text-line">
					<div class="text-lable">创建时间:</div>
					<div class="text">${orderAddress.create_date}</div>
				</div>	
				<div class="text-line">
					<div class="text-lable">收件人:</div>
					<div class="text">${orderAddress.addressname}</div>
				</div>
				<div class="text-line">
					<div class="text-lable">收件地址:</div>
					<div class="text">${orderAddress.province}&nbsp;${orderAddress.city}&nbsp;${orderAddress.addressdatils}</div>
				</div>				
				<div class="text-line">
					<div class="text-lable">订单状态:</div>
					<c:if test="${orderAddress.orderstatus==0}">
						<div class="text2" id="status">
							已取消
						</div>
					</c:if>
					<c:if test="${orderAddress.orderstatus==1}">
						<div class="text2" id="status">
							未支付
						</div>
					</c:if>
					<c:if test="${orderAddress.orderstatus==2}">
						<div class="text1" id="status">
							已支付
							<!--运用ajax技术与后台交互-->
							<a href="javaScript:status();" target="rightFrame" class="btn" id="deliver">立即发货</a>
						</div>
					</c:if>
					<c:if test="${orderAddress.orderstatus==3}">
						<div class="text2" id="status">
							已发货
						</div>
					</c:if>
					<c:if test="${orderAddress.orderstatus==4}">
						<div class="text2" id="status">
							已收货
						</div>
					</c:if>
				</div>
				<div class="text-line">
					<div class="text-lable">订单金额:</div>
					<div class="text">¥${orderAddress.total}</div>
				</div>
				<div class="table-list">
					<table class="product-list">
						<thead>
							<tr>
								<th width="10%" style="text-align: center;">商品图片</th>
								<th width="40%" style="text-align: center;">商品信息</th>
								<th width="20%" style="text-align: center;">商品详情</th>
								<th width="10%" style="text-align: center;">单价</th>
								<th width="10%" style="text-align: center;">数量</th>
								<th width="10%" style="text-align: center;">总价</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderDatils}" var="od">
							<tr>
								<td><img src="/upload/${od.PICTURE}"/></td>
								<td>${od.PRODUCTNAME}</td>
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
								<td>¥${od.PRICE}</td>
								<td>${od.QUANTITY}</td>
								<td>¥${od.PRICE*od.QUANTITY}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>										
				</div>
			</div>
		</div>
	</body>
</html>