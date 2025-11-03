<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>Gestion des Stagiaires</title>
<style>


#gras {

font-weight:bold;

}



</style>
</head>
<body>

<h1> Gestion des Stagiaires </h1>

<table> 

<tr>


<th>Id</th>
<th>Login</th>
<th>Password</th>
<th>Nom</th>
<th>Prenom</th>
<th>Civilite</th>
<th>Email</th>
<th>Adresse</th>
<th>Ordinateur</th>
<th>Filiere</th>
<th> action </th>

</tr>

<c:forEach items="${stagiaires}" var="stagiaire">
<tr>

<td>${stagiaire.id}</td>
<td>${stagiaire.login}</td>
<td>${stagiaire.password}</td>
<td>${stagiaire.nom}</td>
<td>${stagiaire.prenom}</td>
<td>${stagiaire.civilite}</td>
<td>${stagiaire.email}</td>
<td>${stagiaire.adresse}</td>
<td>${stagiaire.ordinateur.id} - ${stagiaire.ordinateur.marque}</td>
<td>${stagiaire.filiere.id} - ${stagiaire.filiere.libelle}</td>

<td>
<a href="stagiaire?id=${stagiaire.id}"><input type="button" value="Modifier"></a>
<a href="stagiaire?id=${stagiaire.id}&delete"><input type="button" value="Supprimer"></a>
</td>

</tr>
</c:forEach>
</table>



<fieldset>

<form  method="POST" action="stagiaire">

<table>
<tr>

<td id="gras"> Login : </td> <td> <input type="text" name="login" placeholder="login" required> <br> </td> </tr>
<tr> <td id="gras">Password : </td> <td> <input type="password" name="password" placeholder="password" required> <br> </td> </tr>
<tr> <td id="gras"> Nom : </td> <td>  <input type="text" name="nom" placeholder="nom" required> <br> </td> </tr>
<tr> <td id="gras">Prenom : </td> <td>  <input type="text" name="prenom" placeholder="prenom" required> <br> </td> </tr>
<tr> <td id="gras">Civilite : </td> <td>
<select name="civilite">
	<c:forEach items="${civilites}" var="civilite">
		<option>${civilite}</option>
	</c:forEach> 
	</select>
<tr> <td id="gras">Email : </td> <td> <input type="email" name="email" placeholder="email" required> <br> </td> </tr>

<tr> <td id="gras">Adresse : </td>  <td> <input type="text" name="adresse.numero" placeholder="numero_rue" required> <br> </td> 
 		<td><input type="text" name="adresse.voie" placeholder="rue" required> <br> </td>
 		<td>	<input type="text" name="adresse.cp" placeholder="cp" required> <br> </td>
 		<td>	<input type="text" name="adresse.ville" placeholder="ville" required> <br> </td> </tr>
 		
 <tr><td id="gras"> Ordinateur : </td> 
 <td>
 <select name="ordinateur.id" required>
 	<option value="">Choisir un ordinateur</option>
	<c:forEach var="ordinateur" items="${ordinateurs}">
		<option value="${ordinateur.id}">${ordinateur.marque}</option>
	</c:forEach>
</select>
</td>
</tr>

 <tr><td id="gras"> Filiere : </td> 
 <td>
 <select name="filiere.id" required>
  	<option value="">Choisir une filiere</option>
	<c:forEach var="filiere" items="${filieres}">
		<option value="${filiere.id}">${filiere.libelle}</option>
	</c:forEach>
</select>
</td>
</tr>


</table>
<input type="submit" value="Ajouter">




</form>
</fieldset>

</body>
</html>