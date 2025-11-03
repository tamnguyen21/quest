<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des clients</title>
</head>
<div id="content">
	<h1>Liste des Clients</h1>
	<a href="home"><input type="button" class="btn btn-info" value="Retour"></a>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Age</th>
				<th>Date de naissance</th>
				<th>Adresse</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${clients}" var="client">


				<tr>
					<td>${client.id}</td>
					<td>${client.nom}</td>
					<td>${client.prenom}</td>
					<td>${client.age}</td>
					<td>${client.dateNaissance}</td>
					<td>${client.adresse.numero} ${client.adresse.voie} ${client.adresse.ville}, ${client.adresse.cp}</td>
					<td>
						<a href="client/${client.id}"><input type="button" class="btn btn-warning" value="Modifier"></a> 
						<a href="client/delete/${client.id}"><input type="button" class="btn btn-danger" value="Supprimer"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div id="addFormFournisseur" class="formAjout">
		
		<c:choose>
			<c:when test="${client.id==null}">
				<h3>Ajouter Client</h3>
			</c:when>
			<c:otherwise>
				<h3>Modifier Client ${client.id}</h3>
			</c:otherwise>
			</c:choose>
			
		
		
			<form:form modelAttribute="client" action="${action}" method="post">
			<table>
				<tr>
					<td>Nom :</td>
					<td><form:input path="nom" placeholder="Saisir nom" required="required" /><td>
				
				</tr>
				<tr>
					<td>Prenom :</td>
					<td><form:input path="prenom" placeholder="Saisir prenom" required="required" /></td>
				</tr>
				<tr>
				<td>Age :</td>
					<td><form:input required="required" type="number" path="age" placeholder="Saisir age" /></td>
				</tr>
				
				<tr>
				<td>Naissance :</td>
					<td><form:input required="required" type="date" path="dateNaissance"/></td>
				</tr>
				
				<tr>
				<td>Numero :</td>
					<td><form:input required="required" path="adresse.numero" placeholder="Saisir numero" /></td>
				</tr>
				
				<tr>
				<td>Voie :</td>
					<td><form:input required="required" path="adresse.voie" placeholder="Saisir voie" /></td>
				</tr>
				
				<tr>
				<td>Ville :</td>
					<td><form:input required="required" path="adresse.ville" placeholder="Saisir ville" /></td>
				</tr>
				
				
				<tr>
				<td>CP :</td>
					<td><form:input required="required" path="adresse.cp" placeholder="Saisir cp" /></td>
				</tr>
			</table>
			
			<c:choose>
			<c:when test="${client.id==null}">
				<input class="btn btn-success" type="submit" value="Ajouter">
			</c:when>
			<c:otherwise>
				<input class="btn btn-warning" type="submit" value="Modifier">
			</c:otherwise>
			</c:choose>
		</form:form>
	</div>

</div>


</body>
</html>