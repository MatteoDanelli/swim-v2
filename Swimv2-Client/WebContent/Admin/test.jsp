<%@page import="SE2.Swimv2.Session.GestoreAdminRemote"%>
<%@page import="SE2.Swimv2.Session.GestoreUserRemote"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.naming.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test page</title>
</head>
<body>
Esempio di pagina - TEST <br>
<%
	try {
		Context jndiContext = new InitialContext();
		Object r1 = jndiContext.lookup("GestoreUser/remote");
		Object r2 = jndiContext.lookup("GestoreAdmin/remote");
		GestoreUserRemote u = (GestoreUserRemote) r1;
		GestoreAdminRemote a = (GestoreAdminRemote) r2;
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
</body>
</html>