<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2</title>
	<meta name="description" content="">
 	
	<style type="text/css">
	@import url(css/main.css);
	
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
	.denied_access{
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
	  		<a class="logo" href="#">
	  			<img alt="Swimv2" src="logo_swimv2_1.gif" width="348" height="60">
	  		</a>
	  		<span class="iscriviti">
	  		Sei l'amministratore?
	  		<a href="#">
	  			Clicca qui
	  		</a>
	  		</span>
  		</div>
 	</div>
	  
  	<div class="wrapper">
	  <div class="content">
		  <div class="description">
			  <h1 class="title">Swimv2</h1>
			  <br>
			  <div class="link">
				
				<h2>Hai bisogno di aiuto? Cerca qualcuno che ti può aiutare!</h2> <br>
				<h3>Puoi cercare anche senza bisogno di login! </h3>
				</div>

			</div>
		   
		   <div class="box login">
				<div class="box_title">
					Log In
				</div>
				<div class="box_contents">
					<form action="LoginServlet" method="post">
					<%
					String logout = request.getParameter("loginError");
					if(logout!= null && logout.equals("1")) {
						out.println("<p class=\"denied_access\">ACCESSO NEGATO!</p>");
					}
					%>
						<label>E-Mail: </label>
						<input type="text" name="email">
						<label>Password: </label>
						<input type="password" name="password">
						<input type="submit" value="Accedi">
					</form>		Non sei ancora registrato? Fallo <a href="registrati.html">qui! </a>
			</div> 


		  </div>
	  
	  </div>
	</div>
   
  <div class="footer">
  		<div class="line1">
  			2012 Swimv2 - Cantoni Daniel & Danelli Matteo - <a href="http://code.google.com/p/swim-v2">Here on Google Code</a>
  		</div>
  		<div class="line2">
  		</div>
  </div>
  
  </body>
</html>
