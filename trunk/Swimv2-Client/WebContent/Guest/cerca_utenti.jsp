<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2-Search User</title>
	 
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
	  		
	  		<span class="page_title">
					Search User
		  	</span>
	  		
  		</div>
 	</div>
 	
 		
 	<div class="wrapper">
	  <div class="content">
	  
	  		<div class="box">
	  			<div class="box_title">
					Cerca Per Nominativo
				</div>
	  		
		  		<div class="box_contents">
						<form action="/Swimv2-Client/RicercaUtentiServlet?type=nominativo" method="post">
							<label>Nome: </label>
							<input type="text" name="nome">
							<label>Cognome: </label>
							<input type="text" name="cognome">
							<input type="submit" value="Cerca">
						</form>		
				</div> 
	  		
	  		</div>
	  		
	  		<div class="box margintop">
	  			
	  			<div class="box_title">
					Cerca Skill
				</div>
				
				<div class="box_contents">
						<form action="/Swimv2-Client/RicercaUtentiServlet?type=skill" method="post">
							<label>Skill: </label>
							<input type="text" name="skill">
							<input type="submit" value="Cerca">
						</form>		
				</div> 
	  		
	  		</div>
 
			<%
				List<User> risultati= (List<User>) request.getAttribute("RisultatiRicerca");
				String mex= (String) request.getAttribute("Messaggio");

				//apro il box
				if(risultati!=null || mex!=null){
					%>
					<div class="box margintop">
						<div class="box_title">
							Risultati
						</div>
						<div class="box_contents">
						<br>
					<%
				}
				
				
				if(risultati!=null && mex==null){

					for(User user:risultati){
						
						%>
						<div class="box ">
							<div class="box_contents">
								<b>NOME: </b><% out.println(user.getNome());%><br>
								<b>COGNOME: </b><% out.println(user.getCognome());%><br>
								<a href="<% out.println("#");%>">Visualizza Profilo</a>
							</div>
						</div>
						<br>
						<%
						
					}
				}
				
				if(mex!=null){
					out.println("<p>"+ mex +"</p>");
				}
				
				
				//chiudo il box
				if(risultati!=null || mex!=null){
					%>
						</div>
					</div>
					<%
				}
				
			%>

	  
	  </div>
	</div>
  
  </body>
</html>
