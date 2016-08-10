<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des agents appdynamics</title>
<link type="text/css" rel="stylesheet" href="css/AfficheInventaire.css"/>
<script type="text/javascript" language="javascript" src="TableFilter/tablefilter.js"></script>
</head>
<body>
	<!-- MapNode contient des couples Nom_AgentAppDyn/Bean_AgentAppDyn -->
	<!-- Pour chaque bean, on récupère les attributs à afficher -->
	<table id="t01">
		<thead>
		<tr>
		<th>Equipe de production</th>
		<th>Domaine</th>
		<th>ApplicationName</th>
		<th>TierName</th>
		<th>Machine</th>
		<th>AgentNode</th>
		<th>Techno</th>
		<th>Operating System</th>
		<th>Code Iua</th>				
		<th>Email</th>				
		</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${MapNode}" var="mapItem">
			<c:if test="${mapItem.value.appAgentPresent == 'true'}">
				<tr>
				<c:choose>
					<c:when test="${mapItem.value.prodAppli == 'null'}">								
						<td><a title="Cliquer pour compléter les infos" href="<c:url value="/UpdateInfoNode?machine=${mapItem.value.machineName}&agentNode=${mapItem.value.name}"/>">null</a></td>
					</c:when>
					<c:otherwise>
						<td>${mapItem.value.prodAppli}</td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${mapItem.value.domaine == 'null'}">								
						<td><a title="Cliquer pour compléter les infos" href="<c:url value="/UpdateInfoNode?machine=${mapItem.value.machineName}&agentNode=${mapItem.value.name}"/>">null</a></td>
					</c:when>
					<c:otherwise>
						<td>${mapItem.value.domaine}</td>
					</c:otherwise>
				</c:choose>				
				<td>${mapItem.value.appName}</td>
				<td>${mapItem.value.tierName}</td>
				<td>${mapItem.value.machineName}</td>
				<td>${mapItem.value.name}</td>
				<td>${mapItem.value.techno}</td>
				<td>${mapItem.value.machineOSType}</td>
				<c:choose>
					<c:when test="${mapItem.value.codeIua == 'null'}">								
						<td><a title="Cliquer pour compléter les infos" href="<c:url value="/UpdateInfoNode?machine=${mapItem.value.machineName}&agentNode=${mapItem.value.name}"/>">null</a></td>
					</c:when>
					<c:otherwise>
						<td>${mapItem.value.codeIua}</td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${mapItem.value.email == 'null'}">								
						<td><a title="Cliquer pour compléter les infos" href="<c:url value="/UpdateInfoNode?machine=${mapItem.value.machineName}&agentNode=${mapItem.value.name}"/>">null</a></td>
					</c:when>
					<c:otherwise>
						<td>${mapItem.value.email}</td>
					</c:otherwise>
				</c:choose>	
				</tr>		
	 		</c:if>	 	
	 	</c:forEach>
	 	</tbody>
	 	<tfoot>
	 	</tfoot>	
	</table>
	<script language="javascript" type="text/javascript">
		var totRowIndex = tf_Tag(tf_Id('t01'),"tr").length;
		var t01_Props =  {
			rows_counter: true,
            col_0: "select",
            col_1: "select",
            col_6: "select",
            col_7: "select",            
            filters_row_index: 1,
            display_all_text: " [ Show all ] ",  
            sort_select: true,
            sort: true,
            sort_config: {            	
    			sort_types:['String', 'String', 'String', 'String', 'String', 'String', 'None', 'None', 'None', 'None'],
    			sort_col: [0,false]				
    		}	    
        };				
		var tf2 = setFilterGrid( "t01",t01_Props );      
    </script>		
</body>
</html>