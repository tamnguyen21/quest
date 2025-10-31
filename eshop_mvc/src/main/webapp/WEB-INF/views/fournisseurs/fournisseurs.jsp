<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des fournisseurs</title>
</head>
<div id="content">
	<h1>Liste des Fournisseurs</h1>
	<input id="btnAddFournisseur" type="button" class="btn btn-success"
		value="Ajouter"> <a href="home"><input type="button"
		class="btn btn-info" value="Retour"></a>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Societe</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fournisseurs}" var="fournisseur">


				<tr>
					<td>${fournisseur.id}</td>
					<td>${fournisseur.nom}</td>
					<td>${fournisseur.prenom}</td>
					<td>${fournisseur.societe}</td>
					<td><a href="fournisseur/${fournisseur.id}"><input
							type="button" class="btn btn-warning" value="Modifier"></a> <c:choose>
							<c:when test="${fournisseur.stock.isEmpty()==false}">
								<balise onmouseover="showWarning(${fournisseur.id})"
									onmouseout="hideWarning(${fournisseur.id})">
								<input disabled type="button" class="btn btn-danger"
									value="Supprimer">
								<balise> <span id="warning-${fournisseur.id}"
									class="disabledBtn">*Impossible de supprimer un
									fournisseur ayant des produits</span>
							</c:when>
							<c:otherwise>
								<a href="fournisseur/delete/${fournisseur.id}"><input
									type="button" class="btn btn-danger" value="Supprimer"></a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>





	<div id="addFormFournisseur" class="formAjout">
		<h3>Ajouter Fournisseur</h3>
		<form:form modelAttribute="fournisseur"
			action="fournisseur" method="post">
			<table>
				<tr>
					<td>Nom :</td>
					<td><form:input path="nom" placeholder="Saisir nom"
							required="required" /><td>
				
				</tr>
				<tr>
					<td>Prenom :</td>
					<td><form:input path="prenom" placeholder="Saisir prenom"
							required="required" /></td>
				</tr>
				<tr>
				<td>Societe :</td>
					<td><form:input required="required" path="societe"
							placeholder="Saisir societe" /></td>
				</tr>
			</table>
			
			<input class="btn btn-success" type="submit" value="Ajouter">
		</form:form>
	</div>

</div>


</body>
</html>
<script>


  btnAddFournisseur.onclick=function()
  {
    addFormFournisseur.style.display="block";
  }
  
  function showWarning(id)
  {
	document.getElementById("warning-"+id).style.visibility="visible";  
  }
  
  function hideWarning(id)
  {
	document.getElementById("warning-"+id).style.visibility="hidden";  
  }
 
</script>