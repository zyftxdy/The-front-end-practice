<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/productAdd.css"/>
		<style type="text/css">
			#productName{
				width: 200px;
			}
			#productPrice{
				width: 200px;
				border: 1px solid #ccc;
				border-radius: 4px;
			}
			.text-line1 .text-input{
				width: 230px;
				border: none;
			}
			.btn{
				margin-top:25px;
			}
		</style>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function check(){
				//权限账号
				var authorityName=document.getElementById("productName").value;
				//权限密码
				var authorityPassword=document.getElementById("productPrice").value;
				//分配权限
				var radioAuthority=$("[name=radioAuthority]:checked").val();
				if(authorityName==""){
					alert("请输入权限账号");
					return false;
				}else if(authorityPassword==""){
					alert("请输入权限密码");
					return false;
				}else if(radioAuthority==null){
					alert("请分配账号权限");
					return false;
				}else{
					alert("添加成功!");
					//交给selevt
					return true;
				}
				
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>权限管理--添加权限人员</span>
			</div>
			<div class="panel-body">
			<form action="${pageContext.request.contextPath}/AuthorityServlet" method="post" onsubmit="return check();">
			<input type="hidden" name="method" value="addAuthority">
				<div class="text-line">
					<div class="text-lable">权限账号</div>
					<div class="text-input">
						<input type="text" name="productName" id="productName" class="productName" placeholder="权限账号"/>
					</div>
				</div>
				<div class="text-line1">
					<div class="text-lable">权限密码</div>
					<div class="text-input">
						<input type="text" name="productPrice" id="productPrice" class="product" placeholder="权限密码"/>
					</div>
				</div>
				<div class="text-line1">
					<div class="text-lable">分配权限</div>
					<div class="text-input">
						<input type="radio" name="radioAuthority" value="1"/>会员管理权限
						<input type="radio" name="radioAuthority" value="2"/>商品管理权限
						<input type="radio" name="radioAuthority" value="3"/>订单管理权限
						<input type="radio" name="radioAuthority" value="4"/>超级管理员权限
					</div>
				</div>
				<input type="submit" value="提交" class="btn"/>
			</form>
			</div>
		</div>		
	</body>
</html>