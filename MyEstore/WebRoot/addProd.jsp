<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   		<script type="text/javascript">
			function changeNum(obj){
				if(!/^[1-9]\d*$/.test(obj.value)){
					alert("库存数量必须为正整数");
					obj.value=oldnum;
					return;
				}
			}
			
			function checkForm(){
  			var check1 = true;
  			check1 = checkNull("name","商品名不能为空!") && check1;
  			check1 = checkNull("file1","图片不能为空!") && check1;	
  			return check1;
  		}
  		
  		function checkNull(name,msg){
  			document.getElementById(name+"_msg").innerHTML = "";
  			var objValue = document.getElementsByName(name)[0].value;
  			if(objValue==null || objValue==""){//花了很多时间检查这个错误，吧||写出了&& ！！！！！！
  				//document.getElementsByName(name)[0].value="";
  				document.getElementById(name+"_msg").innerHTML = "<font color='red' size='2'>"+msg+"</font>";
  				return false;
  			}
  			
  			return true;
  		}
  		</script>
  </head>
  
  <body>
  <div align="center">
  	<h1>添加商品</h1><div align="right"><a href="${pageContext.request.contextPath}/index.jsp">回到主页</a></div><hr>
   		<form action="${pageContext.request.contextPath}/servlet/AddProdServlet" method="POST" enctype="multipart/form-data" onsubmit="return checkForm()">
   			<table>
   				<tr>
   					<td>商品名称</td>
   					<td><input type="text" name="name"/></td>
   					<td id="name_msg"><font color="red" size="2"></font></td>
   					
   				</tr>
   				<tr>
   					<td>单  价</td>
   					<td><input type="text" name="price"/></td>
   					
   				</tr>
   				<tr>
   					<td>商品种类</td>
   					<td>
   						<select name="category">
   							<option value="电子数码"/>电子数码
   							<option value="日用百货"/>日用百货
   							<option value="居家旅行"/>居家旅行
   						</select>
   					</td>
   				</tr>
   				<tr>
   					<td>库存数量</td>
   					<td><input type="text" name="pnum" onchange="changeNum(this)"/></td>
   				</tr>
   				<tr>
   					<td>商品图片</td>
   					<td><input type="file" name="file1"/></td>
   					<td id="file1_msg"><font color="red" size="2"></font></td>
   				</tr>
   				<tr>
   					<td>描述信息</td>
   					<td><textarea rows="5" cols="30" name="description">
   					
   					</textarea></td>
   				</tr>
   				
   				<tr>
   	
   					<td colspan="2"><input type="submit" value="添加商品" /></td>
   				</tr>
   			</table>
   		</form>
   	</div>
  </body>
</html>
