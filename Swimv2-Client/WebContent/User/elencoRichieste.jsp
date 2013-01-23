<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Swimv2</title>
	 
	<style type="text/css">
		@import url(/Swimv2-Client/css/main.css);
		@import url(/Swimv2-Client/css/form.css);
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
  %>
	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
	  		
	  		<span class="page_title">
					Help Requests
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
				Richieste d'aiuto
			</div>
		
	  		<div class="box_contents">
	  <%
		//se risultati non/lista amici non è settato esco
		@SuppressWarnings("unchecked")
		List<Messaggio> richieste= (List<Messaggio>) request.getAttribute("elencoRichieste");
		if(richieste== null) {
			response.sendRedirect("/Swimv2-Client/UserServlet");
			return;
		}
		
		
		if(richieste!=null){

			for(Messaggio req:richieste){
				
				%>

				<div class="elenco">
					<%
						if(req.isMessaggioLetto()==false){
							%><p class="red_color">Nuova!</p><%
						}
					%>
					<ul>
						<li><b>Mittente:</b> <% out.println(req.getMittente().getCognome() + " " +req.getMittente().getNome());%></li>
						<li><b>Data:</b> <% out.println(req.getDataInvio().get(Calendar.DAY_OF_MONTH) +"/"+(req.getDataInvio().get(Calendar.MONTH)+1) +"/"+req.getDataInvio().get(Calendar.YEAR));%></li>
						<li><b>Skill:</b> <% out.println(req.getSkillRichiesta().getNome());%></li>
						 <%
							if(!req.getTesto().equals("")){
								out.print("<li><b>Testo:</b>");
								
								if(req.getTesto().length()>15){
									out.println(req.getTesto().substring(0, 15)+"...");
								}else{
									out.println(req.getTesto());
								}
								out.print("</li>");
							}
						%>
											
					</ul>
					<p class="link_right_align"><a href="<% out.println("/Swimv2-Client/VisualizzaRichiesteAiutoServlet?idRichiesta="+req.getId());%>">Visualizza Richiesta</a></p>
				</div>
				
				<%
				
			}
		}
		
		if(richieste.size()==0){
			out.println("<br><p>Non ci sono richieste d'aiuto</p>");
		}
				
			%>
	  		</div>
		</div>
	  </div>
	</div>
  
  </body>
</html>
