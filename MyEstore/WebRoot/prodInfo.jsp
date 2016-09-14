<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>

	<body>
		<h1>
			商品详细信息
		</h1>
		<div align="right"><a href="${pageContext.request.contextPath}/index.jsp">回到主页</a></div>
		<hr>
		<div align="center">
			<font color="red" size="7" style="text-shadow: aqua">
				商品${requestScope.prod.name}<br> </font>
		</div>
		<table width="80%">
			<tr>
				<td width="40%">
					<img
						src="${pageContext.request.contextPath}/servlet/ImgServlet?imgurl=${prod.imgurl}" />
				</td>

				<td width="40%">
					<font size="6"> 商品名称：${requestScope.prod.name} <br>
						商品价格：${requestScope.prod.price}<br>
						商品种类：${requestScope.prod.category}<br>
						商品描述：${requestScope.prod.description}<br> </font>
						<a href="${pageContext.request.contextPath}/servlet/AddCartServlet?id=${ requestScope.prod.id}"><img  src="${pageContext.request.contextPath}/img/buy.bmp"/></a>
				</td>
			</tr>
		</table>
</html>
