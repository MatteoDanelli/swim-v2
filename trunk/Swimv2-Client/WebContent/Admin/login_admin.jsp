<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2</title>
	<meta name="description" content="">
 	
	<style type="text/css">
	@import url(/Swimv2-Client/css/main.css);
	
	.iscriviti{
	  width: 400px;
	  float: right;
	  text-align: right;
	  display:block;
	  height:70px;
	  line-height:70px;
	}
	.login {
	  width: 350px;
	}
	.description {
	  width: 608px;
	  float: left;
	}
	.description h1{
		color:#AA2424;
	}
	.login input[type=text],
	.login input[type=password] {
	  width: 320px;
	  height: 25px;
	  font-size: 14px;
	}
	.error, .message{
		color:#AA2424; 
		font-weight:bold;
		text-align: center;
		font-size: 14px;
	}
	.login label{
		width: 100%;	
		font-size: 14px;
		font-weight: bold;
		display: block;
		margin-top: 16px;
		color:#AA2424;
	}
	.login input[type=submit]{
	  width: 150px;
	  height: 40px;
	  font-size: 16px;
	  margin-top: 20px;
	  margin-left: 85px;
	  margin-bottom: 20px;
	  position: relative;
	}
	
	</style>
</head>
	
	
<body>

	<div class="header">
		<div class="content">
			<a class="logo" href="/Swimv2-Client/index.jsp"> <img
				alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320"
				height="60">
			</a> <span class="page_title"> Admin Login </span>
		</div>
	</div>


  	<div class="wrapper">
	  <div class="content">
		  <div class="description">
			<h4><a href="/Swimv2-Client/index.jsp"> Torna alla HomePage</a></h4> <br>
			</div>
			
			
			<div class="box login">
				<div class="box_title">Log In</div>
				<div class="box_contents">
					<form action="/Swimv2-Client/LoginServlet?type=admin" method="post">
						<%
							String error = (String) request.getAttribute("Errore");
							if (error != null && error.equals("logError")) {
								out.println("<p class=\"error\">ACCESSO NEGATO!</p>");
							}

							String message = (String) request.getAttribute("Messaggio");
							if (message != null && message.equals("logoutOk")) {
								out.println("<p class=\"message\">LOGOUT ESEGUITO CORRETTAMENTE!</p>");
							}
						%>
						<label>E-Mail: </label> 
						<input type="text" name="email"> 
						<label>Password:</label> 
						<input type="password" name="password"> 
						<input type="submit" value="Accedi">
					</form>
				</div>
			</div>
		</div>
	</div>
	
	  <div class="footer">
  		<div class="line1">
  			2013 Swimv2 - Cantoni Daniel & Danelli Matteo - <a href="http://code.google.com/p/swim-v2">Here on Google Code</a>
  		</div>
  		<div class="line2">
  		</div>
  </div>

</body>
</html>
