<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Synthèse des agents appdynamics</title>
<link type="text/css" rel="stylesheet" href="css/CalculByEntity.css"/>
</head>
<body>
	<div id="page">
		<div class="encart">
			<h2 class="titre"><a aria-controls="panneau-1" aria-expanded="false" href="#panneau-1">Domaine BGC: ${ mapSommeByDomaine.bgc }</a></h2>
			<div style="display: none;" class="panneau" id="panneau-1">
				<table>
					<c:forEach items="${mapSommeByProdAppli.bgc}" var="mapProd">
						<tr>
							<td>${mapProd.key}</td><td>${mapProd.value.size()}</td>
						</tr>					
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="encart">
			<h2 class="titre"><a aria-controls="panneau-2" aria-expanded="false" href="#panneau-2">Domaine EPG: ${ mapSommeByDomaine.epg }</a></h2>
			<div style="display: none;" class="panneau" id="panneau-2">
				<table>
					<c:forEach items="${mapSommeByProdAppli.epg}" var="mapProd">
						<tr>
							<td>${mapProd.key}</td><td>${mapProd.value.size()}</td>
						</tr>					
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="encart">
			<h2 class="titre"><a aria-controls="panneau-3" aria-expanded="false" href="#panneau-3">Domaine SFS: ${ mapSommeByDomaine.sfs }</a></h2>
			<div style="display: none;" class="panneau" id="panneau-3">
				<table>
					<c:forEach items="${mapSommeByProdAppli.sfs}" var="mapProd">
						<tr>
							<td>${mapProd.key}</td><td>${mapProd.value.size()}</td>
						</tr>					
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="encart">
			<h2 class="titre"><a aria-controls="panneau-4" aria-expanded="false" href="#panneau-4">Domaine TSV: ${ mapSommeByDomaine.tsv }</a></h2>
			<div style="display: none;" class="panneau" id="panneau-4">
				<table>
					<c:forEach items="${mapSommeByProdAppli.tsv}" var="mapProd">
						<tr>
							<td>${mapProd.key}</td><td>${mapProd.value.size()}</td>
						</tr>					
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="encart">
			<h2 class="titre"><a aria-controls="panneau-5" aria-expanded="false" href="#panneau-5">Non renseigné: ${ mapSommeByDomaine.domaineNull }</a></h2>
			<div style="display: none;" class="panneau" id="panneau-5">				
			</div>
		</div>
		<h2>Total: ${ mapSommeByDomaine.total }</h2>
	</div>

<script src="js/jquery.js"></script>
<script src="js/script.js"></script>		
</body>
</html>