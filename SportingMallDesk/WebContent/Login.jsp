<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>登录 --体育用品商城</title>
		<link rel="stylesheet" href="css/login-style.css" />
		<link rel="shortcut icon" href="img/X.png"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			//验证码变换
			function generatecode(){
				document.getElementById('code').src='GenerateCodeServlet?t='+Math.random();
			}
			//校验
			function check(){
				var username=document.getElementById("username").value;
				var password=document.getElementById("password").value;
				var code=document.getElementById("verify").value;
				var error=document.getElementById("error").style.display;
				if(username==""){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("账号不能为空");
					return false;
				}else if(password==""){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("密码不能为空");
					return false;
				}else if(code==""){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("验证码不能为空");
					return false;
				}else if(error==""){
					return false;
				}else{
					document.getElementById("error").style.display="none";
					return true;
				}
			}
			//检验用户名
			function checkCode(){
				var code=document.getElementById("verify").value;
				$.ajax({
				    type : "POST",
					url:"${pageContext.request.contextPath}/LoginServlet?method=checkCode&code="+code,
				    dataType:"json",
				    success : function(data) {
				    	if(data.error=="验证码正确"){
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
	<body >
		<div class="login-top">
			<a href="#"><img src="img/logo.png" /></a>
		</div>
		<div class="login-center">
			<div class="center-login">
				<div id="center1">用户登录</div>
				<c:choose>
					<c:when test="${empty error}">
						<div id="error" style="display:none;"></div>
					</c:when>
					<c:otherwise>
						<div id="error" style="display:block;">${error}</div>
					</c:otherwise>
				</c:choose>
				<%
				String username="";
				String password="";
				Cookie[] cookies=request.getCookies();
				if(cookies!=null&&cookies.length>0){
					for(Cookie c:cookies){
						if("username".equals(c.getName())){
							username=c.getValue();
						}if("password".equals(c.getName())){
							password=c.getValue();
						}
					}
				}
				%>
				<form name="myform" action="${pageContext.request.contextPath}/LoginServlet" method="post" onsubmit="return check()">
				<input type="hidden" name="method" value="CheckUser"/>				
					<div id="center-user">
						<label class="user-label"><img src="img/login1.png"></label>
						<input type="text" id="username" name="username" class="user-content" value="<%=username %>" placeholder="账号"/>
					</div>
					<div id="center-pwd">
						<label class="pwd-label"><img src="img/login2.png"></label>
						<input type="password" id="password" name="password" value="<%=password %>" class="pwd-content" placeholder="密码"/>
					</div>
					<div id="center-verify">
						<label class="verify-label"><img src="img/验证 验证码.png"></label>
						<input type="text" id="verify" class="verify-content" name="code" placeholder="验证码" onblur="checkCode();"/>
						<img id="code" src="GenerateCodeServlet">
						<a href="javaScript:generatecode();" class="change">看不清换一张</a>
					</div>
					<div class="checked">
					<input type="checkbox" name="isUserCookie" id="isUserCookie" checked="checked"/><p>一周内自动登录<p><br/>
					</div>
					<div class="center-bottom">
						<a href="register.jsp">立即注册</a>
						<a href="pass-reset.jsp">忘记密码</a>
					</div>
				    <input type="submit" name="sumbit" class="sumbit"  value="登  录"/>
			    </form>
			</div>
		</div>
		<div class="login-bottom"></div>
	</body>
</html>