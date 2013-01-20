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
					//verifico attributo user
					User user = (User) request.getAttribute("user");
					if(user== null){
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}

					@SuppressWarnings("unchecked")
					//ottengo i feedback, se l' attributo non esiste redirigo sull user page
					List<Feedback> feed = (List<Feedback>) request.getAttribute("feedback");
					if(feed== null) {
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
					@SuppressWarnings("unchecked")
					//ottengo il personalSkill, se l' attributo non esiste redirigo sull user page
					List<Skill> personalSkill = (List<Skill>) request.getAttribute("personalSkill");
					if(personalSkill== null) {
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
					
					//verifico attributo feedback avg
					Double avg = (Double) request.getAttribute("feedback_avg");
					if(avg == null){
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
					
					//verifico attributo sendFeedback
					Boolean sendFeedback = (Boolean) request.getAttribute("sendFeedback");
					if(sendFeedback == null){
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
					
					//verifico attributo ricAmici
					Boolean richiestaAmicizia = (Boolean) request.getAttribute("ricAmici");
					if(richiestaAmicizia == null){
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
					
					//verifico attributo pulsanti
					Boolean mostraPulsanti = (Boolean) request.getAttribute("pulsanti");
					if(mostraPulsanti == null){
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
					
	 %>	
	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
	  		
	  		<span class="page_title">
					User Profile
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
	  <div class="content">
	  
	  		<%
				if(mostraPulsanti){
					%>
					
					<div class="submenu">
						<%
							if(richiestaAmicizia){
								%> <a href="#">Richiedi Amicizia</a> <%
							}
						
							if(sendFeedback){
								%> <a href="/Swimv2-Client/FeedbackServlet?destinatario=<%out.print(user.getId());%>">Crea Feedback</a> <%
							}
						%>
						<a href="/Swimv2-Client/RichiesteAiutoServlet?destinatario=<%out.print(user.getId());%>">Invia Richiesta Aiuto</a>
						<a href="/Swimv2-Client/MessaggiServlet?destinatario=<%out.print(user.getId());%>">Invia Messaggio</a>
					</div>		

					
					<%
				}else{
					out.print("<br><br>");
				}
			%>

	 		<h1><%out.print(user.getCognome() + " " +user.getNome()); %></h1>
	 		<br>
	 		<h2 class="red_color">Dati Personali:</h2>
	 		<ul>
	 			<li><b>Email:</b> <%out.print(user.getEmail());%></li>
	 			<%
	 			Calendar data = user.getDataDiNascita();
	 			if(data!= null){
	 				out.print("<li><b>Data di Nascita:</b> "+data.get(Calendar.DAY_OF_MONTH) +"/"+(data.get(Calendar.MONTH)+1) +"/"+data.get(Calendar.YEAR)+"</li>");
	 			}
	 			String provincia = user.getProvincia();
	 			if(provincia!= null){
	 				out.print("<li><b>Provincia:</b> "+provincia+"</li>");
	 			}
	 			char sesso = user.getSesso();
	 			if(sesso== 'M'){
	 				out.print("<li><b>Sesso:</b> Maschio</li>");
	 			}
	 			if(sesso== 'F'){
	 				out.print("<li><b>Sesso:</b> Femmina</li>");
	 			}
	 			%>
	 		
	 		</ul>
	 		
	 		<h2 class="red_color">Skill Possedute:</h2>
	 		<br>
	 		<p>
	 		<%	
	 			int size=personalSkill.size();
				if(size>0){
					out.print(personalSkill.get(0).getNome());
				}else{
					out.print("L' utente non possiede nessun abilità");
				}
	 			
	 			for(int i=1;i<size;i++){
	 				out.print(", " +personalSkill.get(i).getNome());
	 			}
	 		%>
	 		</p>
	 		
	 		<br>
	 		<h2 class="red_color">Feedback:</h2>
	 		<br>
	 		<p><b>Media Voti: </b><%out.print(avg); %></p>
			<% 
			if(feed.size()==0){
				out.println("<p><br>Non ci sono Feedback</p>");
			}
				for(Feedback f: feed){
			  		%>
					<div class="elenco">
						<ul>
							<li><b>Data Pubblicazione:</b> <% out.println(f.getDataPubblicazione().get(Calendar.DAY_OF_MONTH) +"/"+(f.getDataPubblicazione().get(Calendar.MONTH)+1) +"/"+f.getDataPubblicazione().get(Calendar.YEAR));%></li>
							<li><b>Stelle:</b> <% out.println(f.getStelleAssegnate());%></li>
							<%
							if(f.getCommento()!=null){
								
								StringBuffer text = new StringBuffer(f.getCommento());
								  
						        int loc = (new String(text)).indexOf('\n');
						        while(loc > 0){
						            text.replace(loc, loc+1, "<br>");
						            loc = (new String(text)).indexOf('\n');
						       }
								out.print("<li><b>Commento:<br></b> "+ text.toString() +"</li>");
							}
							%>
						</ul>
					</div>
			  		<%
				}
			%> 
	  
	  </div>
	</div>  
  </body>
</html>
