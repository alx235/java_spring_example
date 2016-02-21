<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>newdisk</title>
</head>
<body>
<BR>Input disk name and click button below to create new disk<BR>
<FORM METHOD=POST>
		<BR>Disk name: <BR>
		<INPUT TYPE=TEXT NAME="Disk name:"> <BR>
		<INPUT TYPE=SUBMIT VALUE="Create Disk">
	</FORM>
<FORM METHOD=POST ACTION="logout">
		<BR>
		<INPUT TYPE=SUBMIT VALUE=logout>
		<BR>
	</FORM>
			<BR><input id="btn_home" type="button" value="home"><BR>
</body>
</html>