<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Stagiaire</title>
</head>
<body>

<h1> Modifier Stagiaires</h1>

<form  method="POST" action="stagiaire">

<input type="hidden" name="id" value="${stagiaire.id}">

Login : <input type="text" name="login" placeholder="login" required value="${stagiaire.login}"> <br>
Password : <input type="password" name="password" placeholder="password" required value="${stagiaire.password}"> <br>
Nom : <input type="text" name="nom" placeholder="nom" required value="${stagiaire.nom}"> <br>
Prenom : <input type="text" name="prenom" placeholder="prenom" required value="${stagiaire.prenom}"> <br>

Civilite : 
<select name="civilite">
<c:forEach items="${civilites}" var="civilite">
			<c:choose>
		<c:when test="${stagiaire.civilite.equals(civilite)}">
			<option selected>${civilite}</option>
		</c:when>
		<c:otherwise>
		<option>${civilite}</option>
		</c:otherwise>
		
		</c:choose>
</c:forEach> 
</select>
<br>

Email : <input type="email" name="email" placeholder="email" required value="${stagiaire.email}"> <br>

Adresse :   <input type="text" name="adresse.numero" placeholder="numero_rue" required value="${stagiaire.adresse.numero}"> <br>
 			<input type="text" name="adresse.voie" placeholder="rue" required value="${stagiaire.adresse.voie}"> <br>
 			<input type="text" name="adresse.cp" placeholder="cp" required value="${stagiaire.adresse.cp}"> <br>
 			<input type="text" name="adresse.ville" placeholder="ville" required value="${stagiaire.adresse.ville}"> <br>
 
 Ordinateur: <select name="ordinateur.id" required>
<option value="">Choisir un ordinateur</option>
<c:forEach var="ordinateur" items="${ordinateurs}">
	<c:choose>
		<c:when test="${ordinateur.id==stagiaire.ordinateur.id}">
			<option selected value="${ordinateur.id}">${ordinateur.marque}</option>
		</c:when>
		<c:otherwise>
			<option value="${ordinateur.id}">${ordinateur.marque}</option>
		</c:otherwise>
	</c:choose>
</c:forEach>
</select>
<br>

Filiere: <select name="filiere.id" required>
  <option value="">Choisir une filiere</option>

<c:forEach var="filiere" items="${filieres}">
	<c:choose>
		<c:when test="${filiere.id==stagiaire.filiere.id}">
			<option selected value="${filiere.id}">${filiere.libelle}</option>
		</c:when>
		<c:otherwise>
			<option value="${filiere.id}">${filiere.libelle}</option>
		</c:otherwise>
	</c:choose>
</c:forEach>
</select>
<br>

<input type="submit" value="Modifier">




</form>


</body>
</html>