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
					Messages
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
	  
	  <%
		//se risultati non/lista amici non è settato esco
		@SuppressWarnings("unchecked")
		List<Messaggio> messaggi= (List<Messaggio>) request.getAttribute("elencoMessaggi");
		if(messaggi== null) {
			response.sendRedirect("/Swimv2-Client/UserServlet");
			return;
		}
		
		
		if(messaggi!=null){

			for(Messaggio mess:messaggi){
				
				%>

				<div class="elenco">
					<%
						if(mess.isMessaggioLetto()==false){
							%><p class="red_color">Nuovo!</p><%
						}
					%>
					<ul>
						<li><b>Mittente:</b> <% out.println(mess.getMittente().getCognome() + " " +mess.getMittente().getNome());%></li>
						<li><b>Data:</b> <% out.println(mess.getDataInvio().get(Calendar.DAY_OF_MONTH) +"/"+(mess.getDataInvio().get(Calendar.MONTH)+1) +"/"+mess.getDataInvio().get(Calendar.YEAR));%></li>
						<li><b>Testo:</b> <% 
											if(mess.getTesto().length()>15){
												out.println(mess.getTesto().substring(0, 15)+"...");
											}else{
												out.println(mess.getTesto());
											}
											%>
											</li>
					</ul>
					<p class="link_right_align"><a href="<% out.println("/Swimv2-Client/VisualizzaMessaggiServlet?idMex="+mess.getId());%>">Visualizza Messaggio</a></p>
				</div>
				
				<%
				
			}
		}
		
		if(messaggi.size()==0){
			out.println("<br><p>Non ci sono messaggi</p>");
		}
				
			%>

	  </div>
	</div>
  
  </body>
</html>
