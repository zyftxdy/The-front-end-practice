<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>网站后台管理</title>
<link rel="shortcut icon" href="img/X.png"/>
</head>
<frameset rows="61,*" frameborder="no" border="0" framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame"/>
	<frameset cols="247,*">
		<frame src="left-menu.jsp" name="leftFrame" scrolling="no" id="leftFrame" title="leftFrame"/>
		<frameset rows="590,*">
			<frame src="index.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
			<frame src="bottom.jsp" name="bottomFrame" id="bottomFrame" scrolling="no" title="bottomFrame" />
		</frameset>
	</frameset>
</frameset>
</html>