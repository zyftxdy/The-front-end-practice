<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/person-change.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
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
			//提交修改验证
			function change(){
				if(checktel() && checkemail() && checkquestion() && checkanswer()){
					var tel=document.getElementById("tel").value;
					var email=document.getElementById("email").value;
					var question=document.getElementById("question").value;
					var answer=document.getElementById("answer").value;
					var url="${pageContext.request.contextPath}/UserServlet?method=updateUser";
					url=url+"&telephone="+tel+"&email="+email+"&question="+question+"&answer="+answer;
					alert("信息更新成功");
					window.location.href=url;
				}
				return false;
			}
		</script>
	</head>
	<body class="body">
		<div class="panel">
		<div class="panel-title">
			<span>修改个人信息</span>
		</div>
		<div id="error" style="display:none;"></div>
		<div class="panel-body">
			<div class="user-pro">
				<div class="text-line">
					<span class="text-label">用户名:</span>
					<span class="text">${user.username}</span>
				</div>
				<div class="text-line">
						<span class="text-label">电话:</span>
						<input type="tel" id="tel" value="${user.telephone}"/>									
				</div>
				<div class="text-line">
						<span class="text-label">邮箱:</span>
						<input type="email" id="email" value="${user.email}"/>									
				</div>
				<div class="text-line">
						<span class="text-label">密保问题:</span>
						<input type="text" id="question" value="${user.question}"/>									
				</div>
				<div class="text-line">
					<span class="text-label">密保答案:</span>
					<input type="text" id="answer" value="${user.answer}"/>									
				</div>
				<div class="text-line">
					<a href="javaScript:change();" target="rightframe">提交修改</a>								
				</div>
			</div>
		</div>
		</div>
	</body>
</html>