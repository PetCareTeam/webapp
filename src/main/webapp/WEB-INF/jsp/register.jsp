<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form:form method="POST" action="done" commandName="petuser" enctype="multipart/form-data">	
		<label>User name</label>
		<input type="text" name="username"> <br/>
		<label>Password</label>
		<input type="password" name="password"> <br/>
		<label>Fist Name</label>
		<input type="text" name="firstName"> <br/>
		<label>Last Name</label>
		<input type="text" name="lastName"> <br/>
		<label>Profile picture:</label> 
		<input type="file" name="profileImage"/>
		<input type="submit" value="REGISTER"/>
	</form:form>
</body>
</html>