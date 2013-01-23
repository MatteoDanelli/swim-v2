<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
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
		
		//verifico attributo user
		User user = (User) request.getAttribute("user");
		if(user== null) {
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
					New Feedback
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

	  		<p><a href="javascript:history.back()">Indietro</a></p>
			<br>	
		
			<div class="box">
		  			<div class="box_title">
						Crea feedback
					</div>
	 		
					<div class="box_contents">
					
	
						<form class="form" action="/Swimv2-Client/FeedbackServlet" method="post">
							
							<input type="hidden" name="destinatario" value="<%out.print(user.getId());%>">
							<div class="form_center_textarea">
								<p><b class="red_color">Destinatario: </b><%out.print(user.getCognome() + " " + user.getNome()); %></p>
								<br>

								<label>Voto: </label>
								<select name="stelle" >
										<option selected="selected" value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
								</select>
								<br>
								<br>

								<textarea name="commento"></textarea>
								<br>
							</div>
							
							<p class="link_right_align"> <input type="submit" value="Crea"> </p>
						</form>		
					</div> 
	 		</div>
	 	</div>
	</div>	
</body>
</html>