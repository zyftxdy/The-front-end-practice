<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/addDatils.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			//图片的回显
			function showPreview(img){
				var file = img.files[0];
				//获取一个指向该元素的地址
				var path = window.URL.createObjectURL(file);
				document.getElementById("previewing").style.display="block";
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
					alert("添加成功!");
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
				<form action="${pageContext.request.contextPath}/AddUpdateDatilsServlet" method="post" enctype="multipart/form-data" onsubmit="return check()">
					<input type="hidden" name="method" value="addDatils">
					<div class="text-line">
						<input type="hidden"  id="productid" name="productid" value="${productid}"/>
						<div class="text-lable">商品名称:</div>
						<div class="text">
						<input type="text"  id="productname" name="productname" value="${productName}" readonly="readonly"/>
						</div>
					</div>
					<div class="text-list" id="size-list">
						<div class="text-lable">商品尺码:</div>
						<div class="text">
							<select name="cloth" id="cloth">
								<option value>请选择尺码</option>
								<option value="S">S</option>
								<option value="M">M</option>
								<option value="L">L</option>
								<option value="xl">xl</option>
								<option value="2xl">2xl</option>
								<option value="3xl">3xl</option>
								<option value="4xl">4xl</option>
							</select>
						</div>
					</div>
					<div class="text-list" id="shot-list">
						<div class="text-lable">商品鞋码:</div>
						<div class="text">
							<select name="shot" id="shot">
								<option value>请选择鞋码</option>
								<option value="38">38</option>
								<option value="39">39</option>
								<option value="40">40</option>
								<option value="41">41</option>
								<option value="42">42</option>
								<option value="43">43</option>
								<option value="44">44</option>
							</select>
						</div>
					</div>	
					<div class="text-list" id="color-list">
						<div class="text-lable">商品颜色:</div>
						<div class="text">
							<select name="color" id="color">
								<option value>请选择颜色</option>
								<option value="红">红</option>
								<option value="橙">橙</option>
								<option value="黄">黄</option>
								<option value="绿">绿</option>
								<option value="蓝">蓝</option>
								<option value="靛">靛</option>
								<option value="紫">紫</option>
							</select>
						</div>
					</div>
					<div class="text-line">
						<div class="text-lable">商品图片:</div>
						<div class="text">
							<input type="file" id="picture" name="picture" onchange="showPreview(this)"/>
						</div>
						<div id="previewing" style="display: none;">
						</div>
					</div>
					<div class="text-line">
						<div class="text-lable">商品价格:</div>
						<div class="text">
							<input type="text" name="price" id="price" value="" placeholder="请输入价格"/>
						</div>
					</div>
					<div class="text-line">
						<div class="text-lable">商品库存:</div>
						<div class="text">
							<input type="text" name="nums" id="nums" value="" placeholder="请输入库存"/>
						</div>
					</div>	
					<div class="text-line">
						<input type="submit" value="提交" class="btn"/>
					</div>
				</form>
			</div>	
		</div>
	</body>
</html>