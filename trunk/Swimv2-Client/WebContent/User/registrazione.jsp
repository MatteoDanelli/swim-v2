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
									<input type="text" name="email">
									<br>
									<label>Password: </label>
									<input type="password" name="password">
									<br>
									<label>Nome: </label>
									<input type="text" name="nome">
									<br>
									<label>Cognome: </label>
									<input type="text" name="cognome">
									<br>
									<label>Provincia: </label>
									<input type="text" name="provincia">
									<br>
									<label>Sesso: </label>
									<select name="sesso" >
										<option selected="selected" value="N">Sesso</option>
										<option value="M">Maschio</option>
										<option value="F">Femmina</option>
	
									</select>
									<br>
									
									<label>Data di Nascita: </label>
									<select name="giornoNascita" >
											<option selected="selected" value="">Giorno</option>
											<%for(int i=1;i<=31;i++){
												out.println("<option value=\""+ i +"\">"+i+"</option>");
											}
											%>
			
									</select>
									
									<select name="meseNascita">
											<option selected="selected" value="">Mese</option>
											<%for(int i=0;i<=11;i++){
												int j= i+1;
													out.println("<option value=\""+ i +"\">"+j+"</option>");
											}
											%>
				
									 </select>
									 
									 <select name="annoNascita">
											<option selected="selected" value="">Anno</option>
											<%for(int i=1950;i<=2013;i++){
													out.println("<option value=\""+ i +"\">"+i+"</option>");
												}
											%>	
									 </select>
									<br>
									</div>
								
								<input type="submit" value="Registrati">
						</form>	
				</div> 

			</div>

	  </div>
	 </div>
 
  </body>
</html>