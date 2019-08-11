<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				top: -20px;
				left: 80px;	
				width: 370px;		
			}	
</style>
<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript">
	function putIn(){
		var typeId=document.getElementById("Type-ID").innerHTML;
		var TypeName=document.getElementById("name").value;
		var url2="${pageContext.request.contextPath}/ProductTypeServlet?method=changeTypeName1";
		if(TypeName!=null && TypeName.length>0){
			//alert(typeId+TypeName);
			//1.获取XMLHttpRequest对象
			var xmlhttp;		
			if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			}else{// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			//2.注册监听器,根据XMlHttpRequst对象状态的变化调用函数
			xmlhttp.onreadystatechange=function()
			  {
		      //服务器正确响应客户端的请求		
			  if (xmlhttp.readyState==4 && xmlhttp.status==200){
			       var result =xmlhttp.responseText;
			        if(result == "true"){
						url2=url2+"&typeId="+typeId+"&typeName="+TypeName;
						window.location.href= url2; 
			       }else{
			    	  alert("品类名称已存在,请换一个试试!");
			       } 
			       
			    }
			  }
			//3.向服务器发送请求
			//3.1获取客户端添加的商品名
			var url = "${pageContext.request.contextPath}/ProductTypeServlet?method=checkfirstType&typeName=" +TypeName; 
			//3.2向服务器发送请求
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
		}else{
			alert("请填写品类名称");
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
			<div class="text-line">
				<div class="text-lable">品类ID:</div>
				<div class="text-ID"><span id="Type-ID">${firstTypeId}</span></div>
			</div>
			<div class="text-line">
				<div class="text-lable">品类名称</div>
				<div class="text-input">
					<input type="text" name="name" id="name" class="productName" value="${firstTypeName}"/>
				</div>
			</div>
			<a href="javaScript:putIn();" target="rightFrame" class="btn">提交</a>
		</div>
	</div>	
</body>
</html>