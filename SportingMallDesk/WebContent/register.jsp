<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册 --体育用品商城</title>
		<link rel="stylesheet" href="css/register-style.css"/>
		<link rel="shortcut icon" href="img/X.png"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("input[id='username']").blur(function(){
					checkuname();
				});
				$("input[id='password']").blur(function(){
					checkpwd();
				});
				$("input[id='password1']").blur(function(){
					checkpwd();
				});
				$("input[id='email']").blur(function(){
					checkemail();
				});
				$("input[id='tel']").blur(function(){
					checktel();
				});	
				$("input[id='question']").blur(function(){
					checkquestion();
				});	
				$("input[id='answer']").blur(function(){
					checkanswer();
				});	
			});
			//验证账号输入是否合法
			function checkuname(){
				var uname = $("input[id='username']").val();
				var pattren = /^[0-9A-Za-z_]{5,10}$/;
				if(!pattren.test(uname)){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("账号输入不正确");
					return false;
				}else{
					document.getElementById("error").style.display="none";
				}
				return true;
				//利用ajax技术验证用户名是否存在。
			}
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
			//验证电话是否合法
			function checktel(){
				var tp =$("input[id=tel]").val();
				var pattern=/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
				if(!pattern.test(tp)){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("手机输入不合法");
					return false;
				}else{
					document.getElementById("error").style.display="none";
				}
				return true;
			}
			//验证email是否合法
			function checkemail(){
				var email = $("input[id='email']").val();
				var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
				if(!reg.test(email)){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("邮箱输入不合法");
					return false;
				}else{
                    document.getElementById("error").style.display="none";
				}
				return true;
			}
			//验证密保问题是否合法
			function checkquestion(){
				var qs= $("input[id='question']").val();
				var pattern = /^.{1,50}$/;
				if(!pattern.test(qs)){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("密保不能为空!");
					return false;
				}else{
					document.getElementById("error").style.display="none";
				}
				return true;
			}
			//验证密保答案是否合法
			function checkanswer(){
				var aw= $("input[id='answer']").val();
				var pattern = /^.{1,50}$/;
				if(!pattern.test(aw)){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("答案不能为空!");
					return false;
				}else{
					document.getElementById("error").style.display="none";
				}
				return true;
			}
			//验证提交
			function check(){
				if(checkuname() && checkpwd() && checktel() && checkemail() && checkquestion() && checkanswer()){
					return true;
				}
				return false;
			}
			
			//校验会员名是否存在
			function checkUserName(){
				var username=document.getElementById("username").value;
				$.ajax({
				    type : "POST",
					url:"${pageContext.request.contextPath}/LoginServlet?method=checkUserName&username="+username,
				    dataType:"json",
				    success : function(data) {
				    	if(data.error=="用户名可用"){
				    		document.getElementById("error").style.display="none";
				    	}else{
				    		document.getElementById("error").style.display="";
					    	document.getElementById("error").innerHTML=data.error;
				    	}	
				    }  	
				  });  					
			}
		</script>
	</head>
	<body>
		<div class="login-top">
			<a href="#"><img src="img/logo.png" /></a>
		</div>
		<div class="login-center">
			<div class="center-login">
				<div id="center1">新用户注册</div>
				<div id="error" style="display: none;"></div>
				<form name="myform" action="${pageContext.request.contextPath}/LoginServlet" method="post" onsubmit="return check()">
				<input type="hidden" name="method" value="addUser"/>
					<!--用户名-->
					<div id="center-user">
						<label class="label"><img src="img/login1.png"></label>
						<input type="text" id="username" name="username" class="content" placeholder="账号(请输入5~10位字母数字)" onblur="checkUserName();"/>
					</div>
					<!--密码-->
					<div id="center">
						<label class="label"><img src="img/login2.png"></label>
						<input type="password" id="password" name="password" class="content" placeholder="密码(长度在6~20之间)"/>
					</div>
					<!--验证密码一致-->
					<div id="center">
						<label class="label"><img src="img/login2.png"></label>
						<input type="password" id="password1" class="content" placeholder="再次输入密码"/>
					</div>
					<!--电话验证-->
					<div id="center">
						<label class="label"><img src="img/tel.png"></label>
						<input type="tel" id="tel" name="tel" class="content" placeholder="输入电话"/>
					</div>
					<!--验证邮箱-->
					<div id="center">
						<label class="label"><img src="img/email.png"></label>
						<input type="email" id="email" name="email" class="content" placeholder="输入邮箱"/>
					</div>
					<!--验证问题-->
					<div id="center">
						<label class="label"><img src="img/question.png"></label>
						<input type="text" id="question" name="question" class="content" placeholder="输入密码提示问题"/>
					</div>
					<!--验证答案-->
					<div id="center">
						<label class="label"><img src="img/answer.png"></label>
						<input type="text" id="answer" name="answer" class="content" placeholder="输入密码提示答案"/>
					</div>
					<!--已有账号-->
					<div class="center-bottom">
						<a href="Login.jsp">已有账号,去登陆>></a>
					</div>
				    <input type="submit" name="sumbit" class="sumbit"  value="立即注册"/>
			    </form>
			</div>
		</div>
		<div class="login-bottom"></div>
	</body>
</html>