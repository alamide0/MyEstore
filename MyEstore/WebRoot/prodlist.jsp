<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  </head>
  
  <body>
   		<h1>商品列表
   		</h1><div align="right"><a href="${pageContext.request.contextPath}/index.jsp">回到主页</a></div><hr>
   		
   		<font color="red" size="4">点击图片查看详情</font>
   		<table width="100%" border="1">
   			<tr>
   					<th>商品图片</th>
   					<th>商品名称</th>
   					<th>商品价格</th>
   					<th>商品种类</th>
   					<th>存货情况</th>  			
   			</tr>
	   		<c:forEach items="${requestScope.list}" var="prod">
	   			<tr>
	   				<td width="15%" align="center">
	   				<a href="${pageContext.request.contextPath}/servlet/ProdInfoServlet?id=${prod.id }">
	   				<img src="${pageContext.request.contextPath}/servlet/ImgServlet?imgurl=${prod.imgurls }" style="cursor: pointer;"/>
	   				</a>
	   				</td>
	   				<td align="center">${prod.name }</td>
	   				<td align="center">${prod.price }元</td>
	   				<td align="center">${prod.category }</td>
	   				<td align="center">${prod.pnum }件</td>
	   			</tr>
	   		</c:forEach>
   		</table>
  </body>
</html>
