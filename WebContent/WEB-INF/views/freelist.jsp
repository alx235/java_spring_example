<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>freelist</title>
</head>
<body>
<BR>Free Disks:<BR>
<FORM METHOD=POST>

	<c:forEach items="${currents}" varStatus="i"  var="current">
		<BR><c:out value="${current}" />
		<input type="checkbox" name="check" 
		value="${currents2[i.index]}"/><BR>
</c:forEach>
	<c:if test="${not empty currents}">
		<INPUT TYPE=SUBMIT VALUE=Take>
	</c:if>	
	
</FORM>
	<FORM METHOD=POST ACTION="logout">
		<BR>
		<INPUT TYPE=SUBMIT VALUE=logout>
		<BR>
	</FORM>
			<BR><input id="btn_home" type="button" value="home"><BR>
			<BR><input id="btn_refresh" type="button" value="refresh"><BR>
</body>
</html>