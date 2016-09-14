<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<script type="text/javascript">
			function changeNum(id,obj,oldnum){
				if(!/^[1-9]\d*$/.test(obj.value)){
					alert("数量必须为正整数");
					obj.value=oldnum;
					return;
				}
				window.location.href="/MyEstore/servlet/ChangeProdNumServlet?id="+id+"&buynum="+obj.value;
			}
		</script>
	</head>
	<body>
		<h1>
			购物车页面
		</h1>
		<div align="right">
			<a href="${pageContext.request.contextPath}/servlet/ProductListServlet"> 继续购物</a>
			<a href="${pageContext.request.contextPath}/servlet/ClearCartServlet">
				清空购物车</a>
			<a href="${pageContext.request.contextPath}/index.jsp">回到主页</a>
		</div>
		<hr>
		<c:if test="${empty sessionScope.map}">
			<div align="center">
				<a href="${pageContext.request.contextPath}/servlet/ProductListServlet"><font
					color="red" size="6">您的购物车空空如也，快去购物吧....</font>
				</a>
			</div>
		</c:if>
		<c:if test="${ not empty sessionScope.map}">
			<table width="100%" border="1">
				<tr>
					<th>
						商品图片
					</th>
					<th>
						商品名称
					</th>
					<th>
						商品种类
					</th>
					<th>
						商品单价
					</th>
					<th>
						购买数量
					</th>
					<th>
						库存状态
					</th>
					<th>
						商品总额
					</th>
					<th>
						删除
					</th>
				</tr>

				<c:set var="totalmoney" value="0"></c:set>
				<c:forEach items="${sessionScope.map}" var="entry">

					<tr>
						<td width="15%" align="center">
							<img
								src="${pageContext.request.contextPath}/servlet/ImgServlet?imgurl=${entry.key.imgurls }"
								style="cursor: pointer;" />
						</td>
						<td align="center">
							${entry.key.name }
						</td>
						<td align="center">
							${entry.key.category }
						</td>
						<td align="center">
							${entry.key.price }元
						</td>
						<td align="center">
							<input type="text" value="${entry.value}" style="width: 40px" onchange="changeNum('${entry.key.id}',this,${entry.value })"/>件
						</td>
						<td align="center">
							<c:if test="${entry.key.pnum>0}">
	   					有货
	   				</c:if>
							<c:if test="${entry.key.pnum<=0}">
								<font color="red">缺货</font>
							</c:if>
						</td>
						<td align="center">
							${entry.key.price*entry.value }元
							<c:set var="totalmoney"
								value="${totalmoney+entry.key.price*entry.value}"></c:set>
						</td>
						<td align="center">
							<a
								href="${pageContext.request.contextPath}/servlet/DelProdServlet?id=${entry.key.id }">删除</a>
						</td>
					</tr>
				</c:forEach>

			</table>
			<div align="right">
				<font color="red" size="5">商品总金额 ${totalmoney}元</font>
			</div>
		</c:if>
	</body>
</html>
