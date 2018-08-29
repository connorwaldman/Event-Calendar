<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" href="HomePage.css" type="text/css">
</head>
<body>

<h1>Hello, welcome to this site: </h1>

<%
	    String person = "?";
	    String user = request.getParameter("username");
	    if (user != null)
	        person = user;
	    Object fullname = request.getAttribute("username");
	    if (fullname != null)
	        person = fullname.toString();
%>

	<%=person%>!
	
<h2>What would you like to manage?</h2>

<form action="Users.jsp">
<table>
<tr>
	<th><input type="submit" value="Users"></th>
</tr>
</table>
</form>

<form action="Events.jsp">
<table>
<tr>
	<th><input type="submit" value="Events"></th>
</tr>
</table>
</form>

<form action="Venues.jsp">
<table>
<tr>
	<th><input type="submit" value="Venues"></th>
</tr>
</table>
</form>

<form action="Performers.jsp">
<table>
<tr>
	<th><input type="submit" value="Performers"></th>
</tr>
</table>
</form>



</body>
</html>