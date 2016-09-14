<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<script type="text/javascript">
  		function changImage(img){
  			img.src = img.src+"?nowtime="+new Date().getTime;
  		}
  		
  		function checkForm(){
  			var check1 = true;
  			check1 = checkNull("username","用户名不能为空") && check1;
  			check1 = checkNull("password","密码不能为空") && check1;
  			check1 = checkNull("password2","确认密码不能为空") && check1;
  			check1 = checkNull("nickname","昵称不能为空") && check1;
  			check1 = checkNull("email","邮箱不能为空") && check1;
  			check1 = checkNull("valistr","验证码不能为空") && check1;
  			
  			var pwd1 = document.getElementsByName("password")[0].value;
  			var pwd2 = document.getElementsByName("password2")[0].value;
  			
  			if(pwd1!=pwd2){
  				document.getElementById("password2_msg").innerHTML = "<font color='red' size='2'>前后密码不一置,请重新输入</font>";
  				document.getElementsByName("password2")[0].value="";
  				document.getElementsByName("password")[0].value="";
  				check1=false;
  			}
  			
  			var email = document.getElementsByName("email")[0].value;
  			if(email !=null && email!="" && !/^\w+@\w+(\.\w+)+$/.test(email)){
  				document.getElementById("email_msg").innerHTML = "<font color='red' size='2'>邮箱格式不正确</font>";
  				document.getElementsByName("email")[0].value="";
  				check1 = false;
  			}
  			
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
			<h1>
				用户注册
				<font size="1" color="black">验证码不清楚?点击图片换一张!</font>
			</h1>
			<div align="right"><a href="${pageContext.request.contextPath}/index.jsp">回到主页</a></div><hr>
			<form
				action="${pageContext.request.contextPath}/servlet/RegisterServlet"
				method="POST" onsubmit="return checkForm()">
				<table>
					<tr>
						<td>
							用户姓名:
						</td>
						<td>
							<input type="text" name="username" value="${param.username}" />
						</td>
						<td id="username_msg"><font color="red" size="2">${rename }</font></td>
					</tr>
					<tr>
						<td>
							用户密码:
						</td>
						<td>
							<input type="password" name="password" />
						</td>
						<td id="password_msg"></td>
					</tr>
					<tr>
						<td>
							确认密码:
						</td>
						<td>
							<input type="password" name="password2" />
						</td>
						<td id="password2_msg"></td>
					</tr>
					<tr>
						<td>
							用户昵称:
						</td>
						<td>
							<input type="text" name="nickname" value="${param.nickname}" />
						</td>
						<td id="nickname_msg"></td>
					</tr>
					<tr>
						<td>
							用户邮箱:
						</td>
						<td>
							<input type="text" name="email" value="${param.email}" />
						</td>
						<td id="email_msg"></td>
					</tr>

					<tr>
						<td>
							验证码:
						</td>
						<td>
							<input type="text" name="valistr" />

						</td>
						<td id="valistr_msg">
							<font color="red" size="2">${valierror }</font>
						</td>
					</tr>

					<tr>
						<td>
							<input type="submit" value="注册用户" />
						</td>
						<td>
							<img src="${pageContext.request.contextPath}/servlet/Vailimag"
								style="cursor: pointer;" onclick="changImage(this)">
						</td>
					</tr>

				</table>
			</form>
		</div>
	</body>
</html>
