<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2</title>
	 
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
					User Data Page
		  	</span>
	  		
  		</div>
 	</div>
 	
 		
	<div class="menu_bar">
		<div class="content">
			<ul id="menu">
				<li><a class="first" href="UserServlet">Home Page</a></li>
				<li><a href="#">Cerca Utente</a></li>
				<li><a href="#">Visualizza Amici</a></li>
				<li><a href="ModificaDatiServlet">Modifica Dati</a></li>
				<li><a href="#">Modifica Skills</a></li>
				<li><a href="#">Proponi Abilità</a></li>
				<li><a href="LogoutServlet">Logout</a></li>
			</ul>
		</div>		
	</div>	
	  
  	<div class="wrapper">
	  <div class="margintop content">
	  				<%
	  				//verifico sessione
					Long id= (Long) request.getSession().getAttribute("userId");
					if(id==null){
						request.setAttribute("Errore", "logError");
						request.getRequestDispatcher("/index.jsp").forward(request, response);
						return;
					}
					
					User user = (User) request.getAttribute("user");
					if(user== null) {
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
						
					%>
					 <div class="box">
			  			<div class="box_title">
							Modifica i tuoi Dati
						</div>
	  		
				  		<div class="box_contents">
					<form action="ModificaDatiServlet" method="post">

						<label>E-Mail: </label>
						<input type="text" name="email" value="<%out.print(user.getEmail());%>">
						<br>
						<label>Password: </label>
						<input type="password" name="password">
						<br>
						<label>Nome: </label>
						<input type="text" name="nome" value="<%out.print(user.getNome());%>">
						<br>
						<label>Cognome: </label>
						<input type="text" name="cognome" value="<%out.print(user.getCognome());%>">
						<br>
						<label>Provincia: </label>
						<input type="text" name="provincia" value="<%out.print(user.getProvincia());%>">
						<br>
						<label>Sesso: </label>
						<%if(user.getSesso()=='m'){ %>
							<input type="radio" name="sesso" value="m" checked>Maschio
							<input type="radio" name="sesso" value="f">Femmina
						<%}else if(user.getSesso()=='f'){ %>
							<input type="radio" name="sesso" value="m">Maschio
							<input type="radio" name="sesso" value="f" checked>Femmina
						<%}else{ %>
							<input type="radio" name="sesso" value="m">Maschio
							<input type="radio" name="sesso" value="f">Femmina
						<%} %>
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
									out.println("<option value=\""+ i +"\">"+ j +"</option>");
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
						
						
						<input type="submit" value="Modifica">
					</form>		
						</div> 
	  		
	  				</div>
					
	  </div>
	</div>
  
  </body>
</html>
