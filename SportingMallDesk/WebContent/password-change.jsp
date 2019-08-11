<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/password-change.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("input[id='password']").blur(function(){
					checkpwd();
				});
			});
			//验证密码输入是否合法
			function checkpwd(){
				var pwd =$("input[id='password']").val();
				var pattern = /^.{6,20}$/;
				if(!pattern.test(pwd)){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("密码输入不正确");
					return false;
				}else{
					document.getElementById("error").style.display="none";
				}
				//验证密码是否一致
				var confirmpwd =$("input[id='password1']").val();
				if(pwd != confirmpwd){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("密码输入不一致");
					return false;
				}else{
					document.getElementById("error").style.display="none";
				}
				return true;
			}	
			function examine(){
				if(checkpwd()){
					var pwd=document.getElementById("password").value;
					var url="${pageContext.request.contextPath}/UserServlet?method=checkPassword";
					url=url+"&password="+pwd;
					alert("密码更新成功");
					window.location.href=url;
					//交给servlet
				}
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">修改密码</div>
			<div class="panel-body">
				<div class="panel-info">
					<div id="error" style="display: none;"></div>
					<div class="text-line">
						<span>新密码:</span>
						<input type="password" id="password" placeholder="6~20位字母数字"/>
					</div>
					<div class="text-line">
						<span>确认密码:</span>
						<input type="password" id="password1" placeholder="确认密码"/>
					</div>
					<div class="text-line">
						<a href="javaScript:examine()" target="rightframe">提交修改</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>