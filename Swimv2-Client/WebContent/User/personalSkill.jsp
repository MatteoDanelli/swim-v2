<%@page import="org.jboss.aspects.security.Unchecked"%>
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
		.grid_element{
			width: 33%;
			overflow: hidden;
			display: inline-block;

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
					User PersonalSkill
		  	</span>
	  		
  		</div>
 	</div>
 	
 		
	<div class="menu_bar">
		<div class="content">
			<ul id="menu">
				<li><a class="first" href="/Swimv2-Client/UserServlet">Home Page</a></li>
				<li><a href="/Swimv2-Client/RicercaUtentiUserServlet">Cerca Utente</a></li>
				<li><a href="#">Visualizza Amici</a></li>
				<li><a href="/Swimv2-Client/ModificaDatiServlet">Modifica Dati</a></li>
				<li><a href="/Swimv2-Client/PersonalSkillServlet">Modifica Skills</a></li>
				<li><a href="#">Proponi Abilità</a></li>
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
					
					//ottengo skillset, se l' attributo non esiste redirigo sull user page
					@SuppressWarnings("unchecked")
					List<Skill> skillSet = (List<Skill>) request.getAttribute("skillSet");
					if(skillSet== null) {
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
					
					
					%>
					 <div class="box">
			  			<div class="box_title">
							Le tue abilità
						</div>
	  		
				  		<div class="box_contents">
							<form action="/Swimv2-Client/PersonalSkillServlet" method="post">
									<%

									for(Skill skill: skillSet){

										out.print("<div class=\"grid_element\">");

											out.print("<input name=\"" + skill.getId() + "\" type=\"checkbox\" value=\"" + skill.getId() + "\" ");
											
											for(Skill s: personalSkill){
												if(s.getId()==skill.getId()){
													out.print(" checked ");
													break;
												}
											}
											out.print(">"+ skill.getNome() +"<br>\n");
											
										out.print("</div>");

									}

									%>
								<p class="link_right_align"><input class="submit_button" type="submit" value="Modifica"></p>
							</form>		
						</div> 
	  		
	  				</div>
					
	  </div>
	</div>
  
  </body>
</html>
