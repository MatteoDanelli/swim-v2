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
	<script type="text/javascript" src="/Swimv2-Client/js/user.js"></script>
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
		if(user== null) {
			response.sendRedirect("/Swimv2-Client/error.jsp");
			return;
		}
		
		//ottengo skillset, se l'attributo non esiste redirigo sull user page
		@SuppressWarnings("unchecked")
		List<Skill> skillSet = (List<Skill>) request.getAttribute("skillSet");
		if(skillSet== null) {
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
					New Help Request
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

			<div class="box margintop">
		  			<div class="box_title">
						Richiesta Aiuto
					</div>
	 		
					<div class="box_contents">
					
	
						<form name="form" class="form" action="/Swimv2-Client/RichiesteAiutoServlet" method="post" onsubmit="return validateFormRichiestaAiuto();">
							
							<input type="hidden" name="destinatario" value="<%out.print(user.getId());%>">
							<div class="form_center_textarea">
								<p><b class="red_color">Destinatario: </b><%out.print(user.getCognome() + " " + user.getNome()); %></p>
								<br>

								<label>Skill Richiesta: </label>
								<select name="skill" >
										<option selected="selected" value="-1">Skill Richiesta</option>
										<%
											for(Skill skill: skillSet){
												out.print("<option value=\""+skill.getId()+"\">" + skill.getNome()  + "</option>");
											}
										%>
								</select>
								<br>
								<br>

								<textarea name="messaggio"></textarea>
								<br>
							</div>
							
							<p class="link_right_align"> <input type="submit" value="Invia" > </p>
						</form>		
					</div> 
	 		</div>
	 	</div>
	</div>	
</body>
</html>