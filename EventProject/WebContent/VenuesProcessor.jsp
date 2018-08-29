<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Venues Processor</title>
<link rel="stylesheet" href="HomePage.css" type="text/css">
</head>
<body>
	<c:if test="${sqlStatement != null}">
		<c:set var="sqlStatement" value="" />
	</c:if>
	
	<%
	    String eventID = "?";
	    String id = request.getParameter("eventID");
	    if (id != null)
	        eventID = id;
	    Object fullid = request.getAttribute("eventID");
	    if (fullid != null)
	        eventID = fullid.toString();
	%>
	
	<%
	    String venueID = "?";
	    String venue = request.getParameter("venueID");
	    if (venue != null)
	        venueID = venue;
	    Object venName = request.getAttribute("venueID");
	    if (venName != null)
	        venueID = venName.toString();
	%>
	
	<%
	    String venueName = "?";
	    String name = request.getParameter("name");
	    if (id != null)
	        venueName = name;
	    Object venueFull = request.getAttribute("name");
	    if (venueFull != null)
	        venueName = venueFull.toString();
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

	<h1>Manage Venues</h1>

	<p>
		<b>What would you like to do?</b><br>
	</p>
	
	<form action="./VenuesSQLServlet" method="post">
		<select name="sqlStatement">
		<option value = "SELECT * FROM VENUE">${sqlStatement} LIST</option>
		<option value = "DELETE FROM VENUE WHERE VENUEID = '<%=venueID%>'">${sqlStatement} DELETE</option>
		<option value = "INSERT INTO VENUE VALUES ('<%=eventID%>', '<%=venueID%>', '<%=venueName%>', '<%=person%>')">${sqlStatement} INSERT</option>
		<option value = "UPDATE VENUE SET NAME = 'rev1' WHERE VENUEID = '<%=venueID%>'">${sqlStatement} UPDATE</option>
		</select>
		<br> <br> <input type="submit" value="Execute">
	</form>
	<p>
		<b>Result:</b><br> ${sqlResult}
	</p>
	
</body>
</html>