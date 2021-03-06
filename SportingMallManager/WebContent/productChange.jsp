<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/productAdd.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			
			//select二级级联菜单
			$(document).ready(function(){  
				init(); 
			});
			function init(){
				$.ajax({
				    type : "POST",
					url:"${pageContext.request.contextPath}/ProductTypeServlet?method=TypeChange",
				    dataType:"json",
				    success : function(data) {
				    	$("#firstType").append($("<option value>请选择一级分类</option>"));
				    	//把返回的数据使用each进行遍历读取
				   		for(var i=0;i<data.length;i++){
				   			$("#firstType").append($("<option value=\""+data[i].typeId+"\">"+data[i].typeName+"</option>"));
				   		}
				    }  	
				  });    				
			}
			function onchanges(){
				$("#secondType").empty();
				var firstType=$("#firstType").val();
				if(firstType!=null && firstType.trim()!=""){
					document.getElementById("secondType").style.display="";
					$.ajax({
					    type : "POST",
						url:"${pageContext.request.contextPath}/ProductTypeServlet?method=SecondChange&firstType="+firstType,
					    dataType:"json",
					    success : function(data) {
					    	$("#secondType").append($("<option value>请选择二级分类</option>"));
					    	//把返回的数据使用each进行遍历读取
					   		for(var i=0;i<data.length;i++){
					   			$("#secondType").append($("<option value=\""+data[i].typeId+"\">"+data[i].typeName+"</option>"));
					   		}
					    }  	
					  });    	
				}else{
					document.getElementById("secondType").style.display="none";
				}
			}
			
			//校验
			function check(){
				var picture=$("#picture").val();
				var productName=document.getElementById("productName").value;
				var firstType=$("#firstType").val();
				var secondType=$("#secondType").val();
				var productDesc=document.getElementById("productDesc").value;
				if(productName==""){
					alert("请添加商品名称!");
					return false;
				}else if(firstType=="" && firstType.trim()==""){
					alert("请选择一级分类!");
					return false;
				}else if(secondType=="" && secondType.trim()==""){
					alert("请选择二级分类!");
					return false;
				}else if(picture.length==0){
					alert("请添加图片!");
					return false;
				}else if(productDesc==""){
					alert("请填写商品描述!");
					return false;
				}else{
					alert("修改成功!");
					return true;
				}
			}
			
			//图片的回显
			function showPreview(img){
				var file = img.files[0];
				//获取一个指向该元素的地址
				var path = window.URL.createObjectURL(file);
				document.getElementById("previewing").innerHTML=
				"<img src='"+path+"'/>"
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>商品管理--修改商品</span>
			</div>
			<div class="panel-body">
				<form action="${pageContext.request.contextPath}/AddUpdateServlet" method="post" enctype="multipart/form-data" onsubmit="return check()">
				<!-- 隐藏域 -->
				<input type='hidden' name='method' value='updateproduct'>
				<div class="text-line">
					<div class="text-lable">商品名称</div>
					<div class="text-input">
						<input type="hidden" id="ProductId" name="productId" value="${productid}"/>
						<input type="text" name="productName" id="productName" class="productName" value="${p.productName}"/>
					</div>
				</div>
				<div class="text-line">
					<div class="text-lable">所属分类</div>
					<div class="text-input">
						<select name="firstType" id="firstType" onchange="onchanges();">
						<!--运用ajax和json技术从后台读取主品类集合并遍历-->	
							<option value="${p.firstTypeId}">${FirstTypeName}</option>
						</select>
						<select name="secondType" id="secondType">
							<option value="${p.secondTypeId}">${SecondTypeName}</option>
						</select>					
					</div>
				</div>	
					
				<div class="text-line">
					<div class="text-lable">商品图片</div>
					<div class="text-input">
						<input type="file" name="picture" id="picture" onchange="showPreview(this)"/>
					</div>
					<div id="previewing" style="display:block;">
						<img src="/upload/${p.picture}"/>
					</div>
				</div>
				<div class="text-line">
					<div class="text-lable">商品详情</div>
					<div class="text-input">
						<textarea  name="productDesc" id="productDesc" class="textinput">${p.productDesc}</textarea>
					</div>
				</div>	
				<input type="submit" value="提交" class="btn"/>
				</form>
				</div>
			</div>		
	</body>
</html>