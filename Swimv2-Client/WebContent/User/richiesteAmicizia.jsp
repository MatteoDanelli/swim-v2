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
	</style>
	</head>

  <body>
	  <% 
		//verifico sessione
		Long id= (Long) request.getSession().getAttribute("userId");
		if(id==null){
			request.setAttribute("Errore", "logError");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
	  
	  //Controlla se la lista ottenuta dalla servlet è nulla
	  	@SuppressWarnings("unchecked")
		List<RichiestaAmicizia> elenco = (List<RichiestaAmicizia>)request.getAttribute("richiesteAmicizia");
		if(elenco== null) {
			response.sendRedirect("/Swimv2-Client/error.jsp");
			return;
		}
	  %>
	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
	  		
	  		<span class="page_title">
					Friendship Request
		  	</span> 		
  		</div>
 	</div> 	
 	
 	<div class="menu_bar">
		<div class="content">
			<ul id="menu">
				<li><a class="first" href="/Swimv2-Client/UserServlet">Home Page</a></li>
				<li><a href="/Swimv2-Client/ProfiloServlet?userId=<%out.print(id);%>">Profilo</a></li>
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
	  
		<p><a href="/Swimv2-Client/UserServlet">Home</a></p>
		<br>
		<div class="box">
	  			<div class="box_title">
					Richieste Amicizia
				</div>
 					
 					<div class="box_contents">
					<% 
					if(elenco.size()==0){
						out.println("<p>Non ci sono richieste d'amicizia</p>");
					}
						for(RichiestaAmicizia ric: elenco){
					  		%>
							<div class="elenco">
							<h3>User:</h3>
								<ul>
									<li><b>Nome:</b> <% out.println(ric.getMittente().getNome());%></li>
									<li><b>Cognome:</b> <% out.println(ric.getMittente().getCognome());%></li>
									<%if(ric.getMittente().getProvincia()!=null){ %>
										<li><b>Provincia:</b> <% out.println(ric.getMittente().getProvincia());%></li>
									<%}%>
								</ul>
								<p class="link_right_align">
									<a href="<% out.println("/Swimv2-Client/ProfiloServlet?userId="+ric.getMittente().getId());%>">Visualizza Profilo</a>  
									<a href="<% out.println("/Swimv2-Client/RichiesteAmiciziaServlet?idRic="+ric.getId());%>&accetta=1">Accetta</a>  
									<a href="<% out.println("/Swimv2-Client/RichiesteAmiciziaServlet?idRic="+ric.getId());%>&accetta=0">Rifiuta</a>
								</p>
							</div>
					  		<%
						}
					%> 
				</div> 
 			</div>
		</div>
	</div>
	
	
</body>
</html>