<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="SE2.Swimv2.Entity.*"%>
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

	<div class="header">
		<div class="content">
			<a class="logo" href="/Swimv2-Client/index.jsp"> <img
				alt="Swimv2" src="/Swimv2-Client/img/logo.gif" width="320"
				height="60">
			</a> <span class="page_title"> Admin Page </span>

		</div>
	</div>


	<div class="wrapper">
		<div class="content">
			<div class="box login">
				<div class="box_title">Log In</div>
				<div class="box_contents">
					<form action="LoginServlet" method="post">
						<%
							String error = (String) request.getAttribute("Errore");
							if (error != null && error.equals("logError")) {
								out.println("<p class=\"error\">ACCESSO NEGATO!</p>");
							}

							String message = (String) request.getAttribute("Messaggio");
							if (message != null && message.equals("logoutOk")) {
								out.println("<p class=\"message\">LOGOUT ESEGUITO CORRETTAMENTE!</p>");
							}
						%>
						<label>E-Mail: </label> <input type="text" name="email"> <label>Password:
						</label> <input type="password" name="password"> <input
							type="submit" value="Accedi">
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
