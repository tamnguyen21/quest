<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des formateurs</title>
 <link rel="stylesheet" href="style.css">
</head>
<body>

<h1>Gestion des formateurs</h1>

<div>${formateurs}</div>


<table>




	<tr><th>ID</th><th>Login</th><th>Password</th><th>Nom</th><th>Prénom</th><th>Civilité</th><th>Admin</th><th>Actions</th></tr>
	
	<c:forEach items="${formateurs}" var="formateur" >
	
	<tr>
	
	
	
		<td>${formateur.id}</td>
		<td>${formateur.login}</td>
		<td>${formateur.password}</td>
		<td>${formateur.nom}</td>
		<td>${formateur.prenom}</td>
		<td>${formateur.civilite}</td>
		
	    <c:choose>
			<c:when test="${formateur.admin==false}"> <td>non</td> </c:when>
			<c:otherwise><td>oui</td></c:otherwise>
		</c:choose>
		
		<td>
			<a href="formateur?id=${formateur.id}"><input type="button" value="Modifier"></a>
			<a href="formateur?id=${formateur.id}&delete"><input type="button" value="Supprimer"></a>
		</td>
	</tr>
	
	</c:forEach>
</table>

<form method="POST" action="formateur">
	Login : <input type="text" id="login" name="login" placeholder="login" required><br>
	Password : <input type="password" name="password" placeholder="password" required><br>
	Nom : <input type="text" id="nom" name="nom" placeholder="nom" required><br>
	Prénom : <input type="text" id="prenom" name="prenom" placeholder="prenom" required><br>
	Civilité : 
	
	        <c:forEach items="${civilites}" var="civilite">
        	
        	
      			  <input type="radio" id="${civilite}" name="civilite" value="${civilite}" >
          		  <label for="${civilite}">${civilite}</label>
      			
            </c:forEach>
            
            <c:forEach items="${civilites}" var="civilite" varStatus="status">
 
            
	
	<br>
	Admin : <input type="checkbox" id="admin" name="admin"> Admin
	<input type="submit" value="Ajouter">
</form>

</body>
</html>