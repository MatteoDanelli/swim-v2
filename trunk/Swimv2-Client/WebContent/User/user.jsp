<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
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
	  		
  		</div>
 	</div>
	  
  	<div class="wrapper">
	  <div class="content">
		  <div class="description">
		  
			<%
			Long id= (Long) request.getSession().getAttribute("user_id");
			out.print( "<p>UtenteId: " + id +"</p>" );
			%>

		  </div>
	  
	  </div>
	</div>
   
  
  </body>
</html>
