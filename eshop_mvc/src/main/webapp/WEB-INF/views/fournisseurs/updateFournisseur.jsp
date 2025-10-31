<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>

<title>Fiche Fournisseur ${fournisseur.id}</title>
</head>
<body>
	<div id="content">

		<h3>Modifier Fournisseur ${fournisseur.id}</h3>
		<form:form  modelAttribute="fournisseur" action="fournisseur/${fournisseur.id}" method="post">
			<form:hidden path="id"/>
			<table>
				<tr>
					<td>Nom :</td><td><form:input path="nom" placeholder="Saisir nom" required="required"/><td>
				</tr>
				<tr>
					<td>Prenom :</td><td><form:input path="prenom" placeholder="Saisir prenom" required="required"/></td>
				</tr>
				<tr>
				<td>Societe :</td><td><form:input required="required" path="societe" placeholder="Saisir societe"/></td>
				</tr>
			</table>
			
			<input class="btn btn-warning" type="submit" value="Modifier">
			<a href="fournisseur"><input type="button" class="btn btn-info"
				value="Retour"></a>
		</form:form>
	</div>
</body>
</html>
