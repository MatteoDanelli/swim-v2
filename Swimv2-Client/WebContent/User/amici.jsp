<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
<%@ page import="java.util.List"%>
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
		
		//se risultati non/lista amici non è settato esco
		@SuppressWarnings("unchecked")
		List<User> risultati= (List<User>) request.getAttribute("RisultatiRicerca");
		if(risultati== null) {
			response.sendRedirect("/Swimv2-Client/UserServlet");
			return;
		}
		
		String mex= (String) request.getAttribute("Messaggio");

		
		if(risultati!=null && mex==null){

			for(User user:risultati){
				
				%>

				<div class="elenco">
				<h3>User:</h3>
					<ul>
						<li><b>Nome:</b> <% out.println(user.getNome());%></li>
						<li><b>Cognome:</b> <% out.println(user.getCognome());%></li>
						<%if(user.getProvincia()!=null){ %>
							<li><b>Provincia:</b> <% out.println(user.getProvincia());%></li>
						<%}%>
					</ul>
					<p class="link_right_align"><a href="<% out.println("/Swimv2-Client/ProfiloServlet?userId="+user.getId());%>">Visualizza Profilo</a></p>
				</div>
				
				<%
				
			}
		}
		
		if(mex!=null){
			out.println("<br><p>"+ mex +"</p>");
		}
				
			%>

	  </div>
	</div>
  
  </body>
</html>
