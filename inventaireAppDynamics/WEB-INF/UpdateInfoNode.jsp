<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des agents appdynamics</title>
<link type="text/css" rel="stylesheet" href="css/UpdateInfoNode.css"/>
<script type="text/javascript">
function verif_formulaire()
{
 if(document.formulaire.prodAppli.value == "")  {
   alert("Veuillez entrer un nom d'équipe de production !");
   return false;
  } 
 if(document.formulaire.email.value == "")  {
	   alert("Veuillez entrer une adresse email !");
	   return false;
	  }
 if(document.formulaire.codeIua.value == "")  {
	   alert("Veuillez entrer un code IUA !");
	   return false;
	  }
} 
</script>
</head>

<body>
<h1>Liste des agents appdynamics en production</h1>
<form name="formulaire" action="<c:url value="/UpdateInfoNode"/>" method="POST" onSubmit="return verif_formulaire()">
	<fieldset>
		<legend>Compléter les informations pour une machine</legend>
		<p>
		<label for="machine">Machine:</label> <input size=35 type="text" name="machine" id="machine" value="<c:out value="${param.machine}"/>"><br>
		<label for="agentNode">AgentNode:</label> <input size=35 type="text" name="agentNode" id="agentNode" value="<c:out value="${param.agentNode}"/>"><br>
		<label for="prodAppli">Equipe de production:</label> <input size=35 type="text" name="prodAppli" id="prodAppli"><br>
		<label for="domaine">Domaine:</label>
			<select name="domaine" id="domaine">
				<option value="bgc">BGC</option>
				<option value="epg">EPG</option>
				<option value="sfs">SFS/ECC</option>
				<option value="tsv">TSV</option>
			</select><br>
		<label for="email">Email:</label> <input size=35 type="text" name="email" id="email"><br>		
		<label for="codeIua">Code Iua:</label> <input size=35 type="text" name="codeIua" id="codeIua"><br>
		<input type="submit" value="OK">
		<input type="reset" value="Annuler">
		</p>
	</fieldset>	
</form>
<c:if test="${not empty MyBeanMachineDivers}">
		<p class="titre_result"><u>Informations ajoutées pour ${MyBeanMachineDivers.machine}:</u></p>
		<p class="result">
		AgentNode => ${MyBeanMachineDivers.agentNode}<br>
		   Equipe => ${MyBeanMachineDivers.prodAppli}<br>
		  Domaine => ${MyBeanMachineDivers.domaine}<br>
		    Email => ${MyBeanMachineDivers.email}<br>
		  CodeIua => ${MyBeanMachineDivers.codeIua}
		</p>
		<p>Mise à jour de l'inventaire terminée<br>
		Retour sur la page d'inventaire: <a href="<c:url value="AfficheInventaire"/>">Inventaire Appdynamics</a> 
		</p>		
</c:if>
</body>
</html>