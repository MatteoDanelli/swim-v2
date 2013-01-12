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

	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
	  		
	  		<span class="page_title">
					Aggiungi Skill 
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
	  
	  %>
				<div class="box margintop">
			  			<div class="box_title">
							Aggiungi Skill
						</div>
	  		
						<div class="box_contents">
							<form class="form" action="/Swimv2-Client/AggiungiSkillServlet" method="post">
								
								<div class="form_center_contents">
									<label>Nuova skill: </label>
									<input type="text" name="skill">
									<br>
								</div>
								
								<p class="link_right_align"> <input type="submit" value="Inserisci"> </p>
							</form>		
						</div> 


	  				</div>
	</div>
	</div>	
</body>
</html>