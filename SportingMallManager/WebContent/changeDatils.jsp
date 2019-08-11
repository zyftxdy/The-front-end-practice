<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/changeDatils.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			
			//图片的回显
			function showPreview(img){
				var file = img.files[0];
				//获取一个指向该元素的地址
				var path = window.URL.createObjectURL(file);
				document.getElementById("previewing").innerHTML=
				"<img src='"+path+"'/>"
			}
			
			//校验
			function check(){
				var picture=$("#picture").val();
				var price=document.getElementById("price").value;
				var nums=document.getElementById("nums").value;
				if(price==""){
					alert("请添加商品价格!");
					return false;
				}else if(picture.length==0){
					alert("请添加图片!");
					return false;
				}else if(nums==""){
					alert("请填添加商品库存!");
					return false;
				}else{
					alert("修改成功!");
					return true;
				}
			}	
		</script>		
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>商品管理--商品详情</span>
			</div>
			<div class="panel-body">
				<div class="picture" id="previewing">
					<img src="/upload/${picture}"  id="picture"/>
				</div>
				<form action="${pageContext.request.contextPath}/AddUpdateDatilsServlet" method="post" enctype="multipart/form-data" onsubmit="return check()">
					<input type="hidden" name="method" value="updateDatils">
					<div class="text-line">
						<input type="hidden" id="productid" name="productid" value="${productid}"/>
						<input type="hidden" id="datilsId" name="datilsId" value="${datilsId}"/>
						<div class="text-lable">商品名称:</div>
						<div class="text" id="productName">
							<input type="text"  id="productname" name="productname" value="${productName}" readonly="readonly"/>
						</div>
					</div>
					<c:choose>
						<c:when test="${empty clothingsize}">
							<div class="text-list" id="size-list" style="display:none;">
								<input type="text"  id="cloth" name="cloth" value="" readonly="readonly"/>
							</div>
						</c:when>
						<c:otherwise>
					<div class="text-list" id="size-list" style="display:block;">
						<div class="text-lable">商品尺码:</div>
						<div class="text">
							<input type="text"  id="cloth" name="cloth" value="${clothingsize}" readonly="readonly"/>
						</div>
					</div>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${empty shotsize}">
							<div class="text-list" id="shot-list" style="display:none;">
								<input type="text"  id="shot" name="shot" value="" readonly="readonly"/>
							</div>
						</c:when>
						<c:otherwise>
							<div class="text-list" id="shot-list" style="display:block;">
								<div class="text-lable">商品鞋码:</div>
								<div class="text">
									<input type="text"  id="shot" name="shot" value="${shotsize}" readonly="readonly"/>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${empty color}">
							<div class="text-list" id="color-list" style="display:none;">
								<input type="text"  id="color" name="color" value="" readonly="readonly"/>
							</div>
						</c:when>
						<c:otherwise>
							<div class="text-list" id="color-list" style="display:block;">
									<div class="text-lable">商品颜色:</div>
									<div class="text">
										<input type="text"  id="color" name="color" value="${color}" readonly="readonly"/>
									</div>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="text-list">
						<div class="text-lable">商品图片:</div>
						<div class="text">
							<input type="file" id="picture" name="picture" onchange="showPreview(this)"/>
						</div>
					</div>
					<div class="text-line">
						<div class="text-lable">商品价格:</div>
						<div class="text">
							<input type="text" name="price" id="price" placeholder="请输入商品价格">
						</div>
					</div>
					<div class="text-line">
						<div class="text-lable">&nbsp;商品库存:</div>
						<div class="text">
							<input type="text" name="nums" id="nums" placeholder="请输入商品库存">						
						</div>
					</div>	
					<div class="text-line" id="btn">
					<input type="submit" value="提交" class="btn"/>
					</div>	
					</form>		
				</div>
		</div>
	</body>
</html>