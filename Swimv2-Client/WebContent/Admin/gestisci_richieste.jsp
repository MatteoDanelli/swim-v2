<%@page import="SE2.Swimv2.Exceptions.RichiesteSkillException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
<%@ page import="java.util.List;"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2</title>
	 
	<style type="text/css">
		@import url(/Swimv2-Client/css/main.css);

	form {	
	display: inline	
	}
	</style>
	</head>

  <body>

	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
	  		
	  		<span class="page_title">
					Gestione Richieste
		  	</span> 		
  		</div>
 	</div> 	
 	
 		<div class="menu_bar">
		<div class="content">
			<ul id="menu">
				<li><a class="first" href="/Swimv2-Client/AdminServlet">Home Page</a></li>
				<li><a href="/Swimv2-Client/AggiungiSkillServlet">Aggiungi Skill</a></li>
				<li><a href="/Swimv2-Client/ModificaPasswordAdminServlet">Modifica password</a></li>
				<li><a href="LogoutServlet">Logout</a></li>
			</ul>
		</div>		
	</div>
	
	<div class="wrapper">
	  <div class="margintop content">
	  <% 
	  //Controllo se esiste la sessione
	  Long id = (Long) request.getSession().getAttribute("adminId");
	  if (id==null) {
			request.setAttribute("Errore", "logError");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
	  
	  //Controlla se la lista ottenuta dalla servlet è nulla
		List<RichiestaSkill> elenco = (List<RichiestaSkill>)request.getAttribute("richieste");
		if(elenco== null) {
			response.sendRedirect("/Swimv2-Client/error.jsp");
			return;
		}
	  %>
				<div class="box margintop">
			  			<div class="box_title">
							Nuove Richieste
						</div>
	  		
				  		<div class="box_contents">
				  		<div class="elenco">
							<% for (RichiestaSkill richiesta : elenco) { 
								out.println(richiesta.getSkillRichiesta());
								long idRichiesta =richiesta.getId();
								%> 
							<form class="form" action='"/Swimv2-Client/RichiesteAggiuntaSkillServlet?choice=a"+idRichiesta' method="post">
							<input type="submit" value="Accetta!"></form> 
							<form class="form" action="/Swimv2-Client/RichiesteAggiuntaSkillServlet?choice=r+idRichiesta" method="post">
							<input type="submit" value="Rifiuta!"></form> <br> 						
								<%
							}
							%> 
							</div> 
	  					</div>
	  				</div>
			</div>
	</div>
	
	
</body>
</html>