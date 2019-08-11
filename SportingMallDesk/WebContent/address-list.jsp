<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/address-list.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/info.js"></script>
		<script type="text/javascript">
			function displayCity(){
				//获取选择省份的索引值
				var selectedIndex =document.getElementById('province').selectedIndex;
				var _city = document.getElementById('city');
				//清空城市下拉框中的元素内容
				_city.options.length = 0;
				for(var i in cityNames[selectedIndex]){
					var op= 
					new Option(cityNames[selectedIndex][i],i);
					//下拉框中添加option元素
					_city.options.add(op);

				}
			}
			//验证姓名
			function checkName(){
				var name = document.getElementById('address-name').value;
				var pattern =/^.{1,50}$/;
				if(!pattern.test(name)){
					window.alert('请输入收件人姓名！');
					return false;
				}
				return true;	
			}
			//验证详细地址
			function checkAddress(){
				var address = document.getElementById('address-minute').value;
				var pattern =/^.{1,50}$/;
				if(!pattern.test(address)){
					window.alert('请输入详细地址！');
					return false;
				}
				return true;	
			}			
			//验证联系方式
			function checkTel(){
				var tel = document.getElementById('address-tel').value;
				var pattern =/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
				if(!pattern.test(tel)){
					window.alert('联系方式不正确！');
					return false;
				}
				return true;	
			}						
			//验证提交
			function addAddress(){
				if(checkName()==true && checkAddress()==true && checkTel()== true){
					//获取输入
					var name = document.getElementById('address-name').value;
					var address = document.getElementById('address-minute').value;
					var tel = document.getElementById('address-tel').value;
					//验证省份城市选择
					var one=$(".text-line");
					var province=one.find("#province").val();
					var url="${pageContext.request.contextPath}/UserServlet?method=addAddress";
					if(!province){ 
						alert("请选择省份");
					}else{
						var _city = document.getElementById('city');
						var index=_city.selectedIndex;
						var value=_city[index].text;
						if(value=="请选择"){
							alert("请选择城市");
						}else{
							url=url+"&addressname="+name+"&province="+province+"&city="+value+"&addressdatils="+address+"&telephone="+tel;
							window.location.href=url;
						}
					}
					//交给servlet处理
				}
			}
		</script>
	</head>
	<body>
		<div class="panel">
		<!--添加新地址-->
		<div class="panel">
			<div class="panel-title">添加新地址</div>
			<div class="panel-body">
				<div class="text-line">
					<span class="text-label">收件人姓名:</span>
					<input type="text" id="address-name" placeholder="请输入收件人姓名"/>
				</div>
				<div class="text-line">
					<span class="text-label">所在城市:</span>
					<select name="province" id="province" onchange="displayCity();">
						<option value="">请选择</option>
		            <script type="text/javascript"> 
		                for(var i=0;i<provinceArr.length;i++)    
		                    {
		                        document.write("<option value='"+provinceArr[i]+"'>"+provinceArr[i]+"</option>");
		                                    
		                    }
		            </script>
					</select>
					<select name="city" id="city">
						<option>请选择</option>			
					</select>					
				</div>
				<div class="text-line">
					<span class="text-label">详细地址:</span>
					<input type="text" id="address-minute" placeholder="请输入详细地址"/>
				</div>
				<div class="text-line">
					<span class="text-label">联系方式:</span>
					<input type="tel" id="address-tel" placeholder="请输入联系方式"/>
				</div>
				<div class="text-line">
					<a href="javaScript:addAddress()" class="btn" target="rightframe">保存收货地址</a>
				</div>
			</div>
		</div>
        <!--收货地址-->
		<div class="panel">
			<div class="panel-title">收货地址</div>
			<div class="panel-body">
				<div class="address-list">
					<!--地址列表-->
					<table class="address-main">
						<tr>
							<th width="15%">收货人</th>
							<th width="15%">所在省份</th>
							<th width="10%">所在城市</th>
							<th width="25%">详细地址</th>
							<th width="20%">联系方式</th>
							<th width="15%">操作</th>
						</tr>
						<c:choose>
							<c:when test="${empty address}">
								<tr>
									<td style="text-align: center;" colspan="6">暂无收货地址</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${address}" var="a">
									<tr>
										<td>${a.addressname}</td>
										<td>${a.province}</td>
										<td>${a.city}</td>
										<td>${a.addressdatils}</td>
										<td>${a.telephone}</td>
										<td>
											<a href="${pageContext.request.contextPath}/UserServlet?method=findAddressByCd&addressId=${a.addressid}" target="rightframe">修改</a>|
											<a href="${pageContext.request.contextPath}/UserServlet?method=deleteAddress&addressId=${a.addressid}" target="rightframe">删除</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>