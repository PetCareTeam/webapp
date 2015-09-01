<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
        <style>
.error {
	padding: 5px;
	margin-bottom: 5px;
	border: 1px solid transparent;
	border-radius: 2px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
</style>
</head>
<body>
	<c:if test="${not empty error}">
			<div class="error">${error}</div>
	</c:if>
	<br/>
	<form:form method="POST" action="done" modelAttribute="petuser" enctype="multipart/form-data">	
		<table>
		<tr>
			<td><label>User name</label></td>
			<td><input type="text" name="username"></td>
			<td><form:errors path="username" class="error"/></td>
		</tr>
		<tr>
			<td><label>Password</label></td>
			<td><input type="password" name="password"></td>
			<td><form:errors path="password" class="error"/></td>
		</tr>
		<tr>
			<td><label>Fist Name</label></td>
			<td><input type="text" name="firstName"></td>
		</tr>
		<tr>
			<td><label>Last Name</label></td>
			<td><input type="text" name="lastName"></td> 
		</tr>
		<tr>
			<td><label>Profile picture:</label></td> 
			<td><input type="file" name="image"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="REGISTER"/></td>
		</tr>
		</table>
	</form:form>
</body>
</html>