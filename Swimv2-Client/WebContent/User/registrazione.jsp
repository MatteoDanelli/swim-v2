<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2-New User</title>
	 
	<style type="text/css">
		@import url(/Swimv2-Client/css/main.css);
		@import url(/Swimv2-Client/css/form.css);
	</style>
	</head>
	

  <body>

	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
	  		
	  		<span class="page_title">
					Registrazione
		  	</span>
	  		
  		</div>
 	</div>
 	
 		
 	<div class="wrapper">
	  <div class="content">
	  
	  		<div class="box">
	  			<div class="box_title">
					Completa i campi sottostanti per registrarti
				</div>
	  		
		  		<div class="box_contents">
						<form class="form" action="/Swimv2-Client/RegistraUserServlet" method="post">
							    <div class="form_center_contents">
							      <label>E-Mail: </label>
							      <input type="email" name="email" />
							      
							       <label>Password:</label>
							      <input type="password" name="password" />
							      				
							      <label>Nome:</label>
							      <input type="text" name="nome" />
							      
							       <label>Cognome:  </label>							       
							      <input type="text" name="cognome" />
							      
							       <label>Provincia (Sigla):</label>
							      <input type="text" name="provincia" />
							      
							       <label>Sesso (M/F):</label>
							      <input type="text" name="sesso" />
							      
							       <label>Data di nascita:</label>
							      <input type="date" name="dataDiNascita" />
							      
							       <label>Skill:</label>
							      <input type="text" name="skill" >
							<input type="submit" value="Registra!">
							</div>
						</form>	
														
							
				</div> 

				
				
			</div>
	  		

	  
	  </div>
 
  </body>
</html>