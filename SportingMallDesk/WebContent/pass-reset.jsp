<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>找回密码 --体育用品商城</title>
		<link rel="stylesheet" href="css/pass-reset.css"/>
		<link rel="shortcut icon" href="img/X.png"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
		    //验证用户名是否正确
			function usernext(){
				var username=document.getElementById("username").value;
				var pattern = /^.{1,50}$/;
				if(!pattern.test(username)){
					document.getElementById("error").style.display="";
					$("div[id='error']").html("请输入您的用户名");
				}else{
					//提交给servlet
					//利用ajax技术实现页面跳转
					$.ajax({
				    type : "POST",
					url:"${pageContext.request.contextPath}/LoginServlet?method=checkUserName&username="+username,
				    dataType:"json",
				    async: false,
				    success : function(data) {
				    	if(data.error=="用户名已存在"){
				    		document.getElementById("error").style.display="none";
				    		$.ajax({
				    	        async : false,  
				    	        url:"${pageContext.request.contextPath}/LoginServlet?method=findQuestion&username="+username,
				    	        type:'post',
				    	        success:function(data){
				    	        	document.getElementById("qs").innerHTML=data;
									document.getElementById("user-step").style.display="none";
									document.getElementById("question-step").style.display=""; 
				    	        },                                                    
				    	    });    
				    	}else{
				    		document.getElementById("error").style.display="";
					    	document.getElementById("error").innerHTML="用户名不存在";
				    	}
				    	
				    }  	
				  });  	
					
				}
			}
			//验证密码提示答案是否正确
			function questionnext(){
				var answer=document.getElementById("answer").value;
				var username=document.getElementById("username").value;
				var pattern = /^.{1,50}$/;
				if(!pattern.test(answer)){
					document.getElementById("error1").style.display="";
					$("div[id='error1']").html("请输入密码提示答案");
				}else{
					//提交给servlet
					//利用ajax技术实现页面跳转
					$.ajax({
					async : false,
				    type : "POST",
					url:"${pageContext.request.contextPath}/LoginServlet?method=checkAnswer&username="+username+"&answer="+answer,
				    success : function(data) {
				    	if(data=="true"){
				    		document.getElementById("error").style.display="none";
				    		document.getElementById("question-step").style.display="none";
							document.getElementById("password-step").style.display="";   
				    	}else{
				    		document.getElementById("error1").style.display="";
					    	document.getElementById("error1").innerHTML="密码提示答案不正确";
				    	}
				    	
				    }  	
				  });  	
				}
			}
			//验证密码是否合法
			function passwordnext(){
				var username=document.getElementById("username").value;
				var ps=document.getElementById("password").value;
				var url="${pageContext.request.contextPath}/LoginServlet?method=changePassword";
				var pattern = /^.{6,20}$/;
				if(!pattern.test(ps)){
					document.getElementById("error2").style.display="";
					$("div[id='error2']").html("密码长度不得小于6位");
				}else{
					//提交给servlet
					url=url+"&username="+username+"&password="+ps;
					window.location.href=url;
				}
			}
		</script>
	</head>
	<body >
		<div class="login-top">
			<a href="#"><img src="img/logo.png" /></a>
		</div>
		<div class="login-center">
			<div class="center-login">
				<div id="center1">找回密码</div>
				<!--输入用户名 -->
				<div id="user-step" style="display:;">
					<div id="error" style="display:none ;"></div>
					<p class="user-p">请输入用户名:</p>
					<div id="center-user">
						<label class="user-label"><img src="img/login1.png"></label>
						<input type="text" id="username" class="user-content" placeholder="请输入用户名"/>
					</div>
				    <a href="javaScript:usernext();" class="sumbit">下一步</a>
			    </div>
			    <!--输入密码提示 -->
			    <div id="question-step" style="display:none;">
					<div id="error1" style="display:none;"></div>
					<p class="question-p">密码提示问题为:<span id="qs"></span></p>
					<p class="user-p">请输入密码提示答案:</p>
					<div id="center-user">
						<label class="user-label"><img src="img/question.png"></label>
						<input type="text" id="answer" class="user-content" placeholder="请输入密码提示答案"/>
					</div>
				    <a href="javaScript:questionnext();" class="sumbit">下一步</a>
			    </div>
			    <!--输入新密码 -->
			    <div id="password-step" style="display:none;">
					<div id="error2" style="display:none;"></div>
					<p class="user-p">请输入新密码:</p>
					<div id="center-user">
						<label class="user-label"><img src="img/login2.png"></label>
						<input type="password" id="password" class="user-content" placeholder="请输入新密码"/>
					</div>
				    <a href="javaScript:passwordnext();" class="sumbit">提交</a>
			    </div>
			</div>
		</div>
	</body>
</html>