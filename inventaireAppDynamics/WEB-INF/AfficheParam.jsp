<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Param&egrave;tres de configuration</title>
</head>
<body>
	<!-- Ce serait mieux dans un tableau, hein ? -->
	<pr>
	<c:forEach items="${mapParamAppli}" var="mapItem">
		<c:if test="${mapItem.key != 'passwordApm'}">
	${mapItem.key}: ${mapItem.value}<br>
		</c:if>	
	</c:forEach>
	</pr>
</body>
</html>