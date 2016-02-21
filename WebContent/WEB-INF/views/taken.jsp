<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>taken</title>
</head>
<body>
<BR>Taken Disks:<BR>
<c:forEach items="${currents}" var="current">
		<BR><c:out value="${current}"/><BR>
</c:forEach>
	<FORM METHOD=POST ACTION="logout">
		<BR>
		<INPUT TYPE=SUBMIT VALUE=logout>
		<BR>
	</FORM>
			<BR><input id="btn_home" type="button" value="home"><BR>
			<BR><input id="btn_refresh" type="button" value="refresh"><BR>
</body>
</html>