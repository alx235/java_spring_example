<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body>
<BR><input type="button" value="freelist"
	onClick="javascript:window.location='freelist';"><BR>
<BR><input type="button" value="newdisk"
	onClick="javascript:window.location='newdisk';"><BR>
<BR><input type="button" value="taken"
	onClick="javascript:window.location='taken';"><BR>
<BR><input type="button" value="recieved"
	onClick="javascript:window.location='recieved';"><BR>
<FORM METHOD=POST ACTION="logout">
	<BR>
	<INPUT TYPE=SUBMIT VALUE=logout>
	<BR>
</FORM>
</body>
</html>