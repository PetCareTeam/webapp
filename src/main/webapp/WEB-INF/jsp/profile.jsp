<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>

	<h1>${user.username}</h1>
	${user.profileImage}
	<img src="C:\Users\Denicija\Pictures\webcam-toy-photo1.jpg" alt="my image" />
	<form action="post" method="post" commandName="petuser">
		<p>Enter comment text:</p>
		<textarea name="message" rows="5" cols="20"></textarea>
		
		<br /> <input type="submit" value="Post" />
		<h2>
			Click To | <a href="/petCareWeb/j_spring_security_logout" > Logout</a>
		</h2>
	</form>
	${comment.message}
</body>
</html>