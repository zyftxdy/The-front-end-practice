<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/product-manger.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			function search(){
				var one=$(".from-group");
				var searchValue=one.find("#search").val();
				var name=document.getElementById("name").value;
				var productid=document.getElementById("productid").value;
				var url="${pageContext.request.contextPath}/ProductServlet?method=datils&productid="+productid;
				//alert(searchValue);
				if(searchValue=="按商品颜色查询"){
					url=url+"&color="+name;
				}else if(searchValue=="按商品尺寸查询"){
					url=url+"&clothingsize="+name;
				}else if(searchValue=="按商品鞋码查询"){
					url=url+"&shotsize="+name;
				}
				window.location.href=url;
			}
			
			function delcfm() {
			    if (!confirm("确认要下架？")) {
			        window.event.returnValue = false;
			    }
			}
		</script>
	</head>
	<body>
		<div class="panel">
			<div class="panel-title">
				<span>商品管理--商品详情</span>
				<a href="${pageContext.request.contextPath}/ProductServlet?method=addDatils&productid=${productid}&productname=${productName}" target="rightFrame">
					<img src="img/add.png"/>
					<span>添加商品详情</span></a>
			</div>
			<div class="panel-body">
				<div class="from-search">
					<div class="from-group">
						<select name="search" id="search">
							<option value="按商品颜色查询">按商品颜色查询</option>
							<option value="按商品尺寸查询">按商品尺寸查询</option>
							<option value="按商品鞋码查询">按商品鞋码查询</option>
						</select>
					</div>
					<input type="text" id="name" placeholder="关键字"/>
					<input type="hidden" id="productid" value="${productid}"/>
					<a href="javaScript:search();" target="rightFrame"><span>查询</span></a>
				</div>
				<div class="table-list">
					<table class="product-list">
						<thead>
							<tr>
								<th width="10%">id</th>
								<c:if test="${colors==0}">
										<th width="10%" style="display:none">颜色</th>
								</c:if>
								<c:if test="${colors!=0}">
										<th width="10%">颜色</th>
								</c:if>
								<c:if test="${clothingsizes==0}">
										<th width="10%" style="display:none">尺寸</th>
								</c:if>
								<c:if test="${clothingsizes!=0}">
										<th width="10%">尺寸</th>
								</c:if>
								<c:if test="${shotsizes==0}">
										<th width="10%" style="display:none">鞋码</th>
								</c:if>
								<c:if test="${shotsizes!=0}">
										<th width="10%">鞋码</th>
								</c:if>
								<th width="20%">创建时间</th>
								<th width="15%">价格</th>
								<th width="10%">库存</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody>
                            <c:choose>
                            	<c:when test="${empty products}">
		                            <tr>
										<td class="text-center" colspan="8">暂无结果</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${products}" var="p">
										<tr>
											<input type="hidden" name="datilsId" value="${p.DATILSID}"/>
											<td>${p.PRODUCTID}</td>
											<c:choose>
												<c:when test="${empty p.COLOUR}">
													<td style="display:none">${p.COLOUR}</td>
												</c:when>
												<c:otherwise>
													<td>${p.COLOUR}</td>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${empty p.CLOTHINGSIZE}">
													<td style="display:none">${p.CLOTHINGSIZE}</td>
												</c:when>
												<c:otherwise>
													<td>${p.CLOTHINGSIZE}</td>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${empty p.SHOTSIZE}">
													<td style="display:none">${p.SHOTSIZE}</td>
												</c:when>
												<c:otherwise>
													<td>${p.SHOTSIZE}</td>
												</c:otherwise>
											</c:choose>
											<td>${p.CD}</td>
											<td>${p.PRICE}</td>
											<td>${p.NUMS}</td>
											<td>
												<a href="${pageContext.request.contextPath}/ProductServlet?method=changeDatils&productid=${p.PRODUCTID}&productName=${productName}&color=${p.COLOUR}&shotsize=${p.SHOTSIZE}&clothingsize=${p.CLOTHINGSIZE}&picture=${p.PICTURE}&datilsId=${p.DATILSID}" target="rightFrame">编辑</a>
												<a href="${pageContext.request.contextPath}/ProductServlet?method=deleteDatils&productid=${p.PRODUCTID}&productName=${productName}&datilsId=${p.DATILSID}" onclick="delcfm();" target="rightFrame">下架</a>
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>		
						</tbody>
					</table>					
				</div>
				<!--分页-->
			    <div class="pagin">
			    	<div class="message">
			    		总计<i class="blue">${totalRecords}</i>条记录，共<i class="blue">${totalpages}</i>页，当前显示第<i class="blue">${currentpage}</i>页
			    	</div>
			        <ul class="paginList">
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?method=datils&currentpage=1&productid=${productid}&color=${color}&clothingsize=${clothingsize}&shotsize=${shotsize}&productName=${productName}">首页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?method=datils&currentpage=${currentpage-1<=0?1:(currentpage-1)}&productid=${productid}&color=${color}&clothingsize=${clothingsize}&shotsize=${shotsize}&productName=${productName}">上页</a>
				        </li>			     
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?method=datils&currentpage=${(currentpage+1)>totalpages?totalpages:(currentpage+1)}&productid=${productid}&color=${color}&clothingsize=${clothingsize}&shotsize=${shotsize}&productName=${productName}">下页</a>
				        </li>
				        <li class="paginItem">
				        	<a href="${pageContext.request.contextPath}/ProductServlet?method=datils&currentpage=${totalpages}&productid=${productid}&color=${color}&clothingsize=${clothingsize}&shotsize=${shotsize}&productName=${productName}">末页</a>
				        </li>
			        </ul>
			    </div>
			</div>
		</div>
	</body>
</html>

