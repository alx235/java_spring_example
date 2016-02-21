<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>authentication</title>
</head>
<body>
	<FORM METHOD=POST>

		<c:if test="${empty Access}">
		<BR>Login: <INPUT TYPE=TEXT NAME=UserName> <BR>
		<BR>PASSWORD: <INPUT TYPE=TEXT NAME=password> <BR>
		<INPUT TYPE=SUBMIT VALUE=Submit>
		</c:if>
	</FORM>
	<c:if test="${not empty error}">
Error: ${error}
</c:if>
	<c:if test="${not empty errorDB}">
Error: ${errorDB}
</c:if>
	<c:if test="${not empty LogOut}">
	<BR>Login Out<BR>
	</c:if>
	<c:if test="${not empty Access}">
	<BR>Login succesfull<BR>
	</c:if>
	<c:if test="${not empty NewUser}">
	<BR>User created<BR>
	</c:if>
</body>
</html>