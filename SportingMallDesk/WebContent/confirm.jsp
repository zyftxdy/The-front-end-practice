<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>订单确认--体育用品商城</title>
		<link rel="shortcut icon" href="img/X.png"/>
		<link type="text/css"  rel="stylesheet" href="css/confirm.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/info.js"></script>
		<script type="text/javascript">
			function search(){
				var name=document.getElementById("search").value;
				var url="${pageContext.request.contextPath}/ProductServlet?method=find";
				if(name!=""){
					url=url+"&productName="+name;
					window.location.href=url;
				}else{
					alert("请输入您想查找的商品!");
				}
				//提交给servlet
			}
			//显示城市
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
			//提交订单验证
			$(function(){
				var _this=this;
				$(document).on('click','.address-item',function(){
					$(this).addClass('active').siblings('.address-item').removeClass('active');
					_this.data=$(this).data('id');
					//alert(_this.data);
				});
				$(document).on('click','.order-submit',function(){
					var shoppingId=_this.data;
					var total=document.getElementById("sumbit-total").innerHTML;
					if(shoppingId){
						var productId=document.getElementsByName('datilsId');
						var url="${pageContext.request.contextPath}/OrderServlet?method=order&total="+total+"&shoppingId="+shoppingId;
						for(var i=0;i<productId.length;i++){
						  url=url+"&datilsId="+productId[i].value;
						//交给servlet处理
						}
						window.location.href=url;
					}else{
						alert('请选择地址后再提交!');
					}
				});
			})
			//验证姓名
			function checkName(){
				var name = document.getElementById('receiver-name').value;
				var pattern =/^.{1,50}$/;
				if(!pattern.test(name)){
					window.alert('请输入收件人姓名！');
					return false;
				}
				return true;	
			}
			//验证详细地址
			function checkAddress(){
				var address = document.getElementById('addressDatils').value;
				var pattern =/^.{1,50}$/;
				if(!pattern.test(address)){
					window.alert('请输入详细地址！');
					return false;
				}
				return true;	
			}			
			//验证联系方式
			function checkTel(){
				var tel = document.getElementById('tel').value;
				var pattern =/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
				if(!pattern.test(tel)){
					window.alert('联系方式不正确！');
					return false;
				}
				return true;	
			}
			//添加窗口的显示与关闭
			$(document).ready(function(){
				//显示窗口
				$(".address-add").click(function(){
				  		$(".add-new").fadeIn(200);
				});
				//关闭窗口
				$(".hide").click(function(){
				  		$(".add-new").fadeOut(100);
				});
			});	
			
			//校验地址输入是否正确并且重新查询地址
			function check(){
				if(checkName()==true && checkAddress()==true && checkTel()== true){
					//获取输入
					var name = document.getElementById('receiver-name').value;
					var address = document.getElementById('addressDatils').value;
					var tel = document.getElementById('tel').value;
					//验证省份城市选择
					var one=$(".form-line");
					var province=one.find("#province").val();
					if(!province){ 
						alert("请选择省份");
					}else{
						var _city = document.getElementById('city');
						var index=_city.selectedIndex;
						var value=_city[index].text;
						if(value=="请选择"){
							alert("请选择城市");
						}else{
							//ajax使用
							$.ajax({
							    type : "POST",
								url:"${pageContext.request.contextPath}/AddressServlet?method=addNewAddress&addressName="+name+"&province="+province+"&city="+value+"&addressDatils="+address+"&tel="+tel,
							    dataType:"json",
							    success : function(data) {
							    	var str="";
							    	$('.address-list').empty();
							    	document.getElementById("add-new").style.display="none";
							    	for(var i=0;i<data.length;i++){
							    		str+='<div class="address-item" data-id='+data[i].addressid+'>';
							    		str+='<div class="address-title">'+data[i].province;
							    		str+=' '+data[i].city;
							    		str+=' ('+data[i].addressname+'收)';
							    		str+='</div>';
							    		str+='<div class="address-details">'+data[i].addressdatils;
							    		str+=' '+data[i].telephone;
							    		str+='</div>'
							    		str+='<div class="address-opera"><a href="#">修改</a></div>';
							    		str+='</div>';
							    	}
							    	$('.address-list').append(str);
							    }  	
							  }); 
						}
					}
					//交给servlet处理
				}
			}
			
			//删除地址并且重新查询
			function deleteAddress(addressId){
				var _addressId=addressId;
				if(confirm("确定要删除嘛？")){
					$.ajax({
						type : "POST",
						url:"${pageContext.request.contextPath}/AddressServlet?method=deleteAddress&addressId="+_addressId,
					    dataType:"json",
						success : function(data) {
					    	var str="";
					    	$('.address-list').empty();
					    	document.getElementById("add-new").style.display="none";
					    	for(var i=0;i<data.length;i++){
					    		str+='<div class="address-item" data-id='+data[i].addressid+'>';
					    		str+='<div class="address-title">'+data[i].province;
					    		str+=' '+data[i].city;
					    		str+=' ('+data[i].addressname+'收)';
					    		str+='</div>';
					    		str+='<div class="address-details">'+data[i].addressdatils;
					    		str+=' '+data[i].telephone;
					    		str+='</div>'
					    		str+='<div class="address-opera"><a href="#" onclick="updateAddress('+data[i].addressid+')>修改</a><a href="#" onclick="deleteAddress('+data[i].addressid+')">删除</a></div>';
					    		str+='</div>';
					    	}
					    	$('.address-list').append(str);
					    }  	
				  }); 
				}
			}
		</script>
	</head>
	<body class="body">
		<!--头部-->
		<div class="top">
			<div class="head">
			<!-- 判断用户名SESSION-->
				<p>欢迎您:<font color="red">${username}</font> <a href="${pageContext.request.contextPath}/LoginServlet?method=logout">退出</a></p>
			<ul>
				<li><a href="${pageContext.request.contextPath}/CartServlet?method=findCart"><img src="img/cart.png"/>购物车</a></li>
				<li><a href="person-center.jsp">个人中心</a></li>
				<li><a href="#">关于网站</a></li>
			</ul>
			</div>
		</div>
		<!--主体-->
		<div class="main">
			<!--搜索框 -->
			<div class="search-main">
				<a href="#" class="logo"><p>ZYF</p></a>
				<input type="text" id="search" class="search" placeholder="请输入商品名称"/>
				<a href="javaScript:search();" class="submit">搜索</a>
			</div>
			<!--导航-->
			<div class="nav-main">
				<div class="w">
					<a href="#">ZYF</a>
					<span>></span>
					<a>订单确认</a>
				</div>
			</div>
			<div class="confirm-w">
				<!--收货地址-->
				<div class="panel">
					<h1 class="panel-title">收货地址</h1>
					<div class="address-list">
						<c:forEach items="${address}" var="a">
							<div class="address-item" data-id="${a.addressid}">
								<div class="address-title">
									${a.province} ${a.city} (${a.addressname}收)
								</div>
								<div class="address-details">
									${a.addressdatils} ${a.telephone}
								</div>
								<div class="address-opera">
									<a href="#">修改</a>
									<%-- <a href="#"  onclick="deleteAddress('${a.addressid}')">删除</a> --%>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="address-add">
							<div class="address-new">
								<img src="img/add.png"/>
								<div class="text">使用新地址</div>
							</div>
					</div>
				</div>
				<!--商品清单-->
				<div class="panel">
					<h1 class="panel-title">商品清单</h1>
					<div class="product-con">
						<table class="cart-table">
							<tbody>
								<tr>
									<th width="10%">&nbsp;</th>
									<th style="text-align: left;" width="30%">商品信息</th>
									<th width="10%">商品详情</th>
									<th width="15%">单价</th>
									<th width="15%">数量</th>
									<th width="20%">合计</th>
								</tr>
								<c:forEach items="${product}" var="p">
								<tr>
									<td class="list-img"><a href="#" target="_blank"><img src="/upload/${p.PICTURE}"/></a></td>
									<td class="list-info">
										<a href="#" target="_blank">${p.PRODUCTNAME}</a>
										<input type="hidden" name="cartId" id="cartId" value="${p.ID}"/>
										<input type="hidden" name="productId" id="productId" value="${p.PRODUCTID}"/>
										<input type="hidden" name="datilsId" id="datilsId" value="${p.DATILSID}"/>
									</td>
									<td class="list-datils">
										<ul>
											<c:if test="${p.COLOUR!=null}">
												<li>颜色:${p.COLOUR}</li>
											</c:if>
											<c:if test="${p.CLOTHINGSIZE!=null}">
												<li>尺寸:${p.CLOTHINGSIZE}</li>
											</c:if>
											<c:if test="${p.SHOTSIZE!=null}">
												<li>鞋码:${p.SHOTSIZE}</li>
											</c:if>
										</ul>
									</td>
									<td class="list-price">¥${p.PRICE}</td>
									<td class="list-count">x${p.QUANTITY}</td>
									<td class="list-total">¥${p.PRICE*p.QUANTITY}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>						
					</div>
				</div>
				<!--结算-->
				<div class="sumbit-on">
					<span>订单总价:</span>
					<span class="sumbit-total">¥<i id="sumbit-total">${total}</i></span>
					<span class="order-submit">提交订单</span>
				</div>
			</div>
			<!--尾部-->
			<div class="bottom">
				<div class="lianjie">
					<div class="bottom-lianjie">
					<a href="#">张永峰</a>|<a href="#">毕业设计</a>|<a href="#">关于本人</a>
					<hr style="margin-top: 10px;padding-bottom:5px;border-left: none;border-bottom: none;">
					<p>版本所有:<span>张永峰</span>&nbsp;此版本为毕业设计</p>
					</div>
				</div>
			</div>
		</div>
		<div class="add-new" id="add-new" style="display: none;">
			<div class="new-add" id="new-add">
				<div class="add-header">
					<h1>添加新地址</h1>
					<span class="hide"><img src="img/d.png"/></span>
				</div>
				<div class="add-body">
					<div class="form">
						<div class="form-line">
							<label><span>*</span>收件人姓名:</label>
							<input type="text" class="form-item" id="receiver-name" placeholder="请输入收件人姓名"/>
						</div>
						<div class="form-line">
							<label><span>*</span>所在城市:</label>
							<select name="province" class="form-item" id="province" onchange="displayCity();">
							<option value="">请选择</option>
				            <script type="text/javascript"> 
				                for(var i=0;i<provinceArr.length;i++)
				                    {
				                        document.write("<option value='"+provinceArr[i]+"'>"+provinceArr[i]+"</option>");
				                                    
				                    }
				            </script>
							</select>
							<select name="city" class="form-item" id="city">
								<option>请选择</option>			
							</select>
						</div>
						<div class="form-line">
							<label><span>*</span>详细地址:</label>
							<input type="text" class="form-item" id="addressDatils" placeholder="请输入详细地址"/>
						</div>
						<div class="form-line">
							<label><span>*</span>联系方式:</label>
							<input type="tel" class="form-item" id="tel" placeholder="请输入联系方式"/>
						</div>
						<div class="form-line">
							<span class="btn" onclick="check();">保存收货地址</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>