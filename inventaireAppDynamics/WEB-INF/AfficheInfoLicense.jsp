<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Licence</title>
<link type="text/css" rel="stylesheet" href="css/AfficheInfoLicense.css"/>
</head>
<body>
	<table>
		<tr>
			<th>Composant</th><th>Average Usage</th><th>Max Allowed</th>
		</tr>	
		<tr>
			 <td>java</td> <td>${myMapInfoLicense['java'].maxUnitsUsed}</td> <td>${myMapInfoLicense['java'].avgUnitsAllowed}</td> 
		</tr>
		<tr>
			 <td>dot-net</td> <td>${myMapInfoLicense['dot-net'].maxUnitsUsed}</td> <td>${myMapInfoLicense['dot-net'].avgUnitsAllowed}</td>
		</tr>
		<tr>
			 <td>php</td> <td>${myMapInfoLicense['php'].maxUnitsUsed}</td> <td>${myMapInfoLicense['php'].avgUnitsAllowed}</td>
		</tr>
		<tr>
			 <td>database</td> <td>${myMapInfoLicense['database'].maxUnitsUsed}</td> <td>${myMapInfoLicense['database'].avgUnitsAllowed}</td>			 
		</tr>
		
	</table>
</body>
</html>