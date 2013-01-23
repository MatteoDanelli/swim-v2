<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2</title>
	<meta name="description" content="">
 	
	<style type="text/css">
		@import url(/Swimv2-Client/css/main.css);
	</style>
	</head>

  <body>

	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
  		</div>
 	</div>
	   	
 		<div class="menu_bar">
		<div class="content">
			<ul id="menu">
				<li><a class="first" href="/Swimv2-Client/index.jsp">Home Page</a></li>
			</ul>
		</div>		
	</div>
	
  	<div class="wrapper">
	  <div class="content">
		  <div class="description">
		  		<br>			
				<h2>Si è verificato un errore!</h2> <br>
				<br>
				<%
				String error= (String) request.getAttribute("Errore");
				if(error!= null) {
					out.println("<p class=\"error\">"+error+" </p>");
				}
				%>
		  </div>
	  </div>
	</div>
   
  <div class="footer">
  		<div class="line1">
  			2013 Swimv2 - Cantoni Daniel - Danelli Matteo - <a href="http://code.google.com/p/swim-v2">Here on Google Code</a>
  		</div>
  		<div class="line2">
  		</div>
  </div>
  
  </body>
</html>
