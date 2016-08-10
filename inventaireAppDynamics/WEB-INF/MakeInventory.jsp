<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des agents appdynamics</title>
</head>
<body>
	<!-- MapNode contient des couples Nom_AgentAppDyn/Bean_AgentAppDyn -->
	<!-- Pour chaque bean, on récupère les attributs à afficher -->
	<c:forEach items="${MapNode}" var="mapItem">
		<c:if test="${mapItem.value.appAgentPresent = 'true'}">										
			${mapItem.value.name} ${mapItem.value.machineName} ${mapItem.value.appName} 
			${mapItem.value.tierName} ${mapItem.value.machineOSType} ${mapItem.value.codeIua} ${mapItem.value.trigramme}
			${mapItem.value.prodAppli} ${mapItem.value.domaine} ${mapItem.value.email}<br/> 	
	 	</c:if>	
	 </c:forEach>		
</body>
</html>