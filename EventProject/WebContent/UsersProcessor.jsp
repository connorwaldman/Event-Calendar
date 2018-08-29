<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Users Processor</title>
<link rel="stylesheet" href="HomePage.css" type="text/css">
</head>
<body>
	<c:if test="${sqlStatement != null}">
		<c:set var="sqlStatement" value="" />
	</c:if>
	
	<%
	    String person = "?";
	    String user = request.getParameter("username");
	    if (user != null)
	        person = user;
	    Object fullname = request.getAttribute("username");
	    if (fullname != null)
	        person = fullname.toString();
	%>
	
	<%
	    String pass = "?";
	    String code = request.getParameter("password");
	    if (code != null)
	        pass = code;
	    Object word = request.getAttribute("password");
	    if (word != null)
	        pass = word.toString();
	%>

	<h1>Manage Users</h1>

	<p>
		<b>What would you like to do?</b><br>
	</p>
	
	<form action="./SQLServlet" method="post">
		<select name="sqlStatement">
		<option value = "SELECT * FROM USERAUTH">${sqlStatement} LIST</option>
		<option value = "DELETE FROM USERAUTH WHERE USERNAME = '<%=person%>'">${sqlStatement} DELETE</option>
		<option value = "INSERT INTO USERAUTH VALUES ('<%=person%>', '<%=pass%>')">${sqlStatement} INSERT</option>
		<option value = "UPDATE USERAUTH SET USERNAME = 'UpdatedUser' WHERE USERNAME = '<%=person%>'">${sqlStatement} UPDATE</option>
		</select>
		<br> <br> <input type="submit" value="Execute">
	</form>
	<p>
		<b>Result:</b><br> ${sqlResult}
	</p>
	
</body>
</html>