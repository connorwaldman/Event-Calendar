<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Venues</title>
<link rel="stylesheet" href="HomePage.css" type="text/css">
</head>
<body>
<h1>Manage Venues</h1>
<p>Please enter the venue ID and name you would like to insert, update, or delete</p>

<form action="./VenuesServlet" METHOD=GET>
		<table cellspacing="4">
			<tr>
				<td>Event:</td>
				<td><input name="eventID" type="text" size="20"></td>
			</tr>
			<tr>
				<td>Venue:</td>
				<td><input name="venueID" type="text" size="20"></td>
			</tr>
			<tr>
			<tr>
				<td>Name:</td>
				<td><input name="name" type="text" size="20"></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><input name="username" type="text" size="20"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login"></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>