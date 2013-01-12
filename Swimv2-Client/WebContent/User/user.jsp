<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
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
					User Page
		  	</span>
	  		
  		</div>
 	</div>
 	
 		
 	<div class="menu_bar">
		<div class="content">
			<ul id="menu">
				<li><a class="first" href="/Swimv2-Client/UserServlet">Home Page</a></li>
				<li><a href="/Swimv2-Client/RicercaUtentiUserServlet">Cerca Utente</a></li>
				<li><a href="/Swimv2-Client/AmiciUserServlet">Visualizza Amici</a></li>
				<li><a href="/Swimv2-Client/ModificaDatiServlet">Modifica Dati</a></li>
				<li><a href="/Swimv2-Client/PersonalSkillServlet">Modifica Skills</a></li>
				<li><a href="/Swimv2-Client/ProponiAbilitaServlet">Proponi Abilità</a></li>
				<li><a href="/Swimv2-Client/LogoutServlet">Logout</a></li>
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
					
					//verifico attributo user
					User user = (User) request.getAttribute("user");
					if(user!= null) {
						out.println("<h3> Benvenuto "+ user.getNome() +"</h3>");
					}else{
						response.sendRedirect("/Swimv2-Client/error.jsp");
						return;
					}
					
					Integer numRichiesteAmicizia = (Integer)request.getAttribute("richiesteAmicizia");
					if( numRichiesteAmicizia==null){
						numRichiesteAmicizia=0;
					}
					
						
					%>
					
 					<div class="box margintop">
			  			<div class="box_title">
							Notifiche
						</div>
	  		
				  		<div class="box_contents">
				  			<div class="elenco">
							<p>Ci sono:</p><br>
								<p>0 Nuovi Messaggi -<a href="#">Visualizza tutti i messaggi</a></p>
								<p>0 Nuovi Richieste d'aiuto -<a href="#">Visualizza tutte le richiesta d'aiuto</a>	</p>
								<p><%out.print(numRichiesteAmicizia.intValue()); %> Richieste d'amicizia - <a href="/Swimv2-Client/RichiesteAmiciziaServlet">Visualizza tutte le richiesta d'amicizia</a></p>		
							</div>
						</div> 
	  		
	  				</div>


	  
	  </div>
	</div>
  
  </body>
</html>
