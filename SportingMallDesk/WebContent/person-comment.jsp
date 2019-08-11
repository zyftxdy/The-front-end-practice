<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/comment.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function comment(){
				var pid=document.getElementById("productId").value;
				var cmt=document.getElementById("comment").value;
				var url="${pageContext.request.contextPath}/OrderServlet?method=addcomment";
				if(cmt){
					url=url+"&productId="+pid+"&commentdatils="+cmt;
					alert("评论成功!");
					window.top.location.href=url;
				//交给servlet
				}else{
					alert("请填写评论后再提交哦!");
				}
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">商品评论</div>
			<div class="panel-body">
				<div class="product">
					<input type="hidden" id="productId" value="${product.productid}"/>
					<div class="text-line">
						<div class="text-img">
							<img src="/upload/${product.picture}"/>
						</div>
						<div class="text">
							<span>${product.productname}</span>
						</div>
					</div>
				</div>
				<div class="comment">
					<textarea id="comment" placeholder="请小主对该商品进行评论"></textarea>
					<a href="javaScript:comment();">提交评论</a>
				</div>
			</div>
		</div>
	</body>
</html>