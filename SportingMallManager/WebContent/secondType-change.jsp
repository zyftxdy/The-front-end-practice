<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/productAdd.css"/>
		<style type="text/css">
			.text-input .productName{
				color: #999;
			    width: 220px;
			    height: 20px;
			    padding: 6px 12px;
			    font-size: 14px;
			    font-family: "微软雅黑";
			    background-color: #fff;
			    background-image: none;
			    border: 1px solid #ccc;
			    border-radius: 4px;
			    box-shadow: inset 0px 1px 1px rgba(0,0,0,0.075);
			    transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
			}
			.text-ID{
			    position: relative;
			    top: -25px;
			    left: 80px;	
			    width: 370px;		
			}
			.typeid{
				color: #999;
			    width: 220px;
			    height: 20px;
			    padding: 6px 12px;
			    font-size: 14px;
			    font-family: "微软雅黑";
			    background-color: #fff;
			    background-image: none;
			    border: 1px solid #ccc;
			    border-radius: 4px;
			    box-shadow: inset 0px 1px 1px rgba(0,0,0,0.075);
			    transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
			}
		</style>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
		<script type="text/javascript">
			function putIn(){
				var TypeName=document.getElementById("name").value;
				var flag;
				$.ajax({
				    type : "POST",
				    url  :"${pageContext.request.contextPath}/ProductTypeServlet?method=checksecondType&typeName="+TypeName,
				    async : false,		
				    success : function(data) {
				    	if(data=="false"){
				    		alert("品类名称已存在，换一个试试!");
				    		flag=false;
				    	}else if(data=="true"){
				    		flag=true;
				    	}
				    }
				  });
				return flag;
			}
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
				var name=document.getElementById("name").value;
				var picture=document.getElementById("picture").value;
				if(name==""){
					alert("请填写分类名称!");
					return false;
				}else if(picture==""){
					alert("请添加分类图片!");
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
				<span>品类管理--修改品类名称</span>
			</div>
			<div class="panel-body">
			<form action="${pageContext.request.contextPath}/AddUpdateTypeServlet" method="post" enctype="multipart/form-data" onsubmit="return check();">
				<input type="hidden" name="method" value="updateType"/>
				<input type="hidden" id="firstTypeId" name="firstTypeId" value="${firstTypeId}"/>
				<div class="text-line">
					<div class="text-lable">品类ID:</div>
					<div class="text-ID">
						<input type="text" name="typeId" id="typeid" class="typeid" value="${secondTypeId}" readonly="readonly"/>
					</div>
				</div>
				<div class="text-line">
					<div class="text-lable">品类名称</div>
					<div class="text-input">
						<input type="text" name="name" id="name" class="productName" value="${secondTypeName}" onchange="javaScript:putIn();"/>
					</div>
				</div>
				<div class="text-line">
					<div class="text-lable">商品图片</div>
					<div class="text-input">
						<input type="file" name="picture" id="picture" onchange="showPreview(this)"/>
					</div>
					<div id="previewing" style="display:block;">
						<img src="/upload/${picture}"/>
					</div>
				</div>
				<input type="submit" value="提交" class="btn"/>
				</form>
			</div>
		</div>		
	</body>
</html>