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
	
	//se risultati non/liota amici non è settato esco
	Messaggio richiesta= (Messaggio) request.getAttribute("singolaRichiesta");
	if(richiesta== null) {
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
					Request
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
	  
	  
	  	<p><a href="/Swimv2-Client/VisualizzaRichiesteAiutoServlet">Visualizza tutte le richieste</a></p>
		<br>	 
		 <div class="box">
  			<div class="box_title">
				Richiesta
			</div>
		
	  		<div class="box_contents">
				
					<p><b>Mittente:</b> <% out.println(richiesta.getMittente().getCognome() + " " +richiesta.getMittente().getNome());%></p>
					<p><b>Data:</b> <% out.println(richiesta.getDataInvio().get(Calendar.DAY_OF_MONTH) +"/"+(richiesta.getDataInvio().get(Calendar.MONTH)+1) +"/"+richiesta.getDataInvio().get(Calendar.YEAR));%></p>
					<p><b>Skill:</b> <% out.println(richiesta.getSkillRichiesta().getNome());%></p>
					
					<%
					if(richiesta.getTesto()!=null && !richiesta.getTesto().equals("")){
						out.println("<div class=\"elenco\">");

						StringBuffer text = new StringBuffer(richiesta.getTesto());
						  
				        int loc = (new String(text)).indexOf('\n');
				        while(loc > 0){
				            text.replace(loc, loc+1, "<br>");
				            loc = (new String(text)).indexOf('\n');
				        }
						
						out.println(text.toString());
						out.println("</div>");
					}
						
						%>
					
					<br>
					<p class="link_right_align"><a href="/Swimv2-Client/MessaggiServlet?destinatario=<%out.print(richiesta.getMittente().getId());%>&risposta=ric">Rispondi</a></p>
					
				
			</div>
		</div>
				
				

	  </div>
	</div>
  
  </body>
</html>
