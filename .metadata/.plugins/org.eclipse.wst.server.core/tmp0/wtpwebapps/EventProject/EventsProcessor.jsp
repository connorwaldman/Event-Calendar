<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Events Processor</title>
<link rel="stylesheet" href="HomePage.css" type="text/css">
</head>
<body>
	<c:if test="${sqlStatement != null}">
		<c:set var="sqlStatement" value="" />
	</c:if>
	
	<%
	    String eventID = "?";
	    String id = request.getParameter("id");
	    if (id != null)
	        eventID = id;
	    Object fullid = request.getAttribute("id");
	    if (fullid != null)
	        eventID = fullid.toString();
	%>
	
	<%
	    String venueID = "?";
	    String venue = request.getParameter("venue");
	    if (venue != null)
	        venueID = venue;
	    Object venName = request.getAttribute("venue");
	    if (venName != null)
	        venueID = venName.toString();
	%>
	
	<%
	    String person = "?";
	    String user = request.getParameter("username");
	    if (id != null)
	        person = user;
	    Object userName = request.getAttribute("username");
	    if (userName != null)
	        person = userName.toString();
	%>
	
	<%
	    String performerName = "?";
	    String name = request.getParameter("performer");
	    if (name != null)
	        performerName = name;
	    Object fullname = request.getAttribute("performer");
	    if (fullname != null)
	        performerName = fullname.toString();
	%>

	<h1>Manage Events</h1>

	<p>
		<b>What would you like to do?</b><br>
	</p>
	
	<form action="./EventsSQLServlet" method="post">
		<select name="sqlStatement">
		<option value = "SELECT * FROM EVENT">${sqlStatement} LIST</option>
		<option value = "DELETE FROM EVENT WHERE EVENTID = '<%=id%>'">${sqlStatement} DELETE</option>
		<option value = "INSERT INTO EVENT VALUES ('<%=eventID%>', '<%=venueID%>', '<%=person%>', '<%=performerName%>')">${sqlStatement} INSERT</option>
		<option value = "UPDATE EVENT SET PERFORMER = 'UpdatedEvent' WHERE EVENTID = '<%=eventID%>'">${sqlStatement} UPDATE</option>
		</select>
		<br> <br> <input type="submit" value="Execute">
	</form>
	<p>
		<b>Result:</b><br> ${sqlResult}
	</p>
	
</body>
</html>