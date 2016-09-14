<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
   <font size="15px"><a>MyEstore</a></font>	
   	<font size="2px" style="border-style: double" color=""black"><a>by up_group@sina.com</a></font>	
   	<div align="right">author:@zsy</div>
   	<hr>
  
   	<c:if test="${sessionScope.user!=null}">
   		欢迎回来${sessionScope.user.username }<br>
   		<a href="${pageContext.request.contextPath}/addProd.jsp">添加商品</a>
   		<a href="${pageContext.request.contextPath}/servlet/ProductListServlet">查看商品列表</a>
   		<a href="${pageContext.request.contextPath}/servlet/LogoutServlet">注销</a>
   	</c:if>
   	
   	<c:if test="${sessionScope.user==null}">
   		欢迎您游客 <a href="${pageContext.request.contextPath}/login.jsp">登陆</a>
   		<a href="${pageContext.request.contextPath}/register.jsp">注册</a>
   	</c:if>
  </body>
</html>
