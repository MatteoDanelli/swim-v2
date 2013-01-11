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

	<div class="header">
		<div class="content">
	  		<a class="logo" href="/Swimv2-Client/index.jsp">
	  			<img alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320" height="60">
	  		</a>
	  		
	  		<span class="page_title">
					User Data Page
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
					
					User user = (User) request.getAttribute("user");
					if(user== null) {
						response.sendRedirect("/Swimv2-Client/UserServlet");
						return;
					}
						
					%>
					 <div class="box">
			  			<div class="box_title">
							Modifica i tuoi Dati
						</div>
	  		
				  		<div class="box_contents">
							<form class="form" action="/Swimv2-Client/ModificaDatiServlet" method="post">
								
								<div class="form_center_contents">
									<label>E-Mail: </label>
									<input type="text" name="email" value="<%out.print(user.getEmail());%>">
									<br>
									<label>Password: </label>
									<input type="password" name="password">
									<br>
									<label>Nome: </label>
									<input type="text" name="nome" value="<%out.print(user.getNome());%>">
									<br>
									<label>Cognome: </label>
									<input type="text" name="cognome" value="<%out.print(user.getCognome());%>">
									<br>
									<label>Provincia: </label>
									<input type="text" name="provincia" value="<%if(user.getProvincia()!=null){out.print(user.getProvincia());}%>">
									<br>
									<label>Sesso: </label>
									<select name="sesso" >
										<%if(user.getSesso()=='M'){ %>
											<option value="N">Sesso</option>
											<option selected="selected" value="M">Maschio</option>
											<option value="F">Femmina</option>
										<%}else if(user.getSesso()=='F'){ %>
											<option value="N">Sesso</option>
											<option value="M">Maschio</option>
											<option selected="selected" value="F" >Femmina</option>
										<%}else{ %>
											<option selected="selected" value="N">Sesso</option>
											<option value="M">Maschio</option>
											<option value="F">Femmina</option>
										<%} %>
									</select>
									<br>
									
									<label>Data di Nascita: </label>
									<select name="giornoNascita" >
											<option <% if(user.getDataDiNascita()==null){out.print("selected=\"selected\"");}%> value="">Giorno</option>
											<%for(int i=1;i<=31;i++){
												if(user.getDataDiNascita()!=null &&user.getDataDiNascita().get(Calendar.DAY_OF_MONTH)==i){
													out.println("<option selected=\"selected\" value=\""+ i +"\">"+i+"</option>");
												}else{
													out.println("<option value=\""+ i +"\">"+i+"</option>");
												}
											}
											%>
			
									</select>
									
									<select name="meseNascita">
											<option <% if(user.getDataDiNascita()==null){out.print("selected=\"selected\"");}%> value="">Mese</option>
											<%for(int i=0;i<=11;i++){
												int j= i+1;
												if(user.getDataDiNascita()!=null &&user.getDataDiNascita().get(Calendar.MONTH)==i){
													out.println("<option selected=\"selected\" value=\""+ i +"\">"+j+"</option>");
												}else{
													out.println("<option value=\""+ i +"\">"+j+"</option>");
												}
											}
											%>
				
									 </select>
									 
									 <select name="annoNascita">
											<option <% if(user.getDataDiNascita()==null){out.print("selected=\"selected\"");}%> value="">Anno</option>
											<%for(int i=1950;i<=2013;i++){
												if(user.getDataDiNascita()!=null &&user.getDataDiNascita().get(Calendar.YEAR)==i){
													out.println("<option selected=\"selected\" value=\""+ i +"\">"+i+"</option>");
												}else{
													out.println("<option value=\""+ i +"\">"+i+"</option>");
												}
											}
											%>	
									 </select>
								</div>
								
								<p class="link_right_align"><input type="submit" value="Modifica"></p>
							</form>		
						</div> 
	  		
	  				</div>
					
	  </div>
	</div>
  
  </body>
</html>
