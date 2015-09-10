<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Profile</title>
</head>
<body>
	<form:form action="comments/post" modelAttribute="comment" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td><label for="username">${user.username}</label></td>
			</tr>
			<tr>
				<td>${user.firstName} | ${user.lastName}</td>
			</tr>
			<tr>
				<td><h2>
						<label>Profile picture:</label>
					</h2></td>
			</tr>
			<tr>
				<td colspan="2"><img src="data:image/jpg;base64,${pic}"
					height="200" width="150" /></td>
			</tr>
		</table>
		<table>
			<tr>
				<td colspan="2">Enter comment text:</td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="message" rows="5" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<td>Upload image</td>
				<td><input type="file" name="image" /></td>
			</tr>
			<tr>
				<td><label>Type:</label></td>
				<td><select name="type">
						<option value="buy">buy a pet</option>
						<option value="found">found a pet</option>
						<option value="adopt">adopt a pet</option>
				</select>
				<td />
			</tr>
			<tr>
				<td>Contact phone:</td>
				<td><input type="text" name="contact_phone"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Post" /></td>
			</tr>
		</table>
		<h2>
			Click To | <a href="/petCareWeb/j_spring_security_logout">
				Logout</a>
		</h2>
		</table>
	</form:form>
	 <c:if test="${not empty comments}">
	 <h2>My Comments:</h2><br>
	 <table border=2>
		<c:forEach items="${comments}" var="comment">
			<tr>
			<td>
			<img src="data:image/jpg;base64,Base64.encodeBase64String(${comment.image_comment})" height="200" width="150" /></td>
			<td>
			<td>
			<div>
				${comment.pet.username}<br>
				${comment.message}<br>
				posted at:${comment.time_post}
			</div>
			</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>


</body>
</html>