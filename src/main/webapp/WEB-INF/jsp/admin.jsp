<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name}
			<br>
			${user.firstName}
			<br>
			${user.lastName}
			<br> 
			<img src="data:image/jpg;base64,${pic}" />
			| <a href="/petCareWeb/j_spring_security_logout" > Logout</a>
		</h2>
	</c:if>

</body>
</html>