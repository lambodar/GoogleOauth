<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
	<td colspan="2"><img src="<%=session.getAttribute("img")%>" height="300" width="250"></td>
</tr>
<tr>
	<td>Your FullName:</td>
	<td><%=session.getAttribute("name")%></td>
</tr>
<tr>
	<td>Your FristName:</td>
	<td><%=session.getAttribute("firstname")%></td>
</tr>
<tr>
	<td>Your LastName:</td>
	<td><%=session.getAttribute("family")%></td>
</tr>
<tr>
	<td>Your user name:</td>
	<td><%=session.getAttribute("username")%></td>
</tr>
<tr>
	<td>Your Gender:</td>
	<td><%=session.getAttribute("gender")%></td>
</tr>
<tr>
	<td>Your MailId:</td>
	<td><%=session.getAttribute("email")%></td>
</tr>


</table>
</body>
</html>