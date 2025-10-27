<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PC</title>
<style>
body {
	background: linear-gradient(45deg, #ff00ff, #00ffff, #ffff00, #ff8000);
	font-family: 'Comic sans MS';
	color: #ff00ff;
	background-size: 100vh 100vw;
	min-height: 100vh;
}

h1 {
	color: #ff00ff;
	text-align: center;
	font-size: 2.5em;
	margin: 0 auto 30px auto;
	background-color: darkgray;
	border: 5px solid black;
	text-shadow: 2px 2px 0px #000000;
	display: block;
	width: fit-content;
	padding : 20px;
}

table {
	width : 60%;
	background-color : darkgreen;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	margin: 20px auto;
}

th{
	background-color : purple;
	color : white; 
	padding : 12px;
	text-align: center;
	border : 1px solid black;
}

td{
	
	color : black; 
	padding : 12px;
	text-align: center;
	border : 1px solid black;
}

tr:nth-child(even) {
	background-color : lightgreen;
}

tr:nth-child(odd) {
	background-color : pink;
}

tr:hover {
    background-color: white;
}


form {
	background-color: rgba(251, 255, 0, 0.8);
	color : #000000;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	max-width: 400px;
	border : 3px solid black;
}

img {
	position: absolute; 
	top: 20px; right: 
	20px; border: 
	2px solid black;
	width: 150px;
}

</style>
</head>
<body>

<!-- Il faudrait verifier sur chaque page si on a le droit d'etre ici, sinon on est redirect ailleurs -->
<c:if test="${connected==null || connected.getClass().getSimpleName().equals('Stagiaire')}">
	<c:redirect url="home"></c:redirect>
</c:if>



<h1>Gestion des ordinateurs</h1>

<img src="https://i.ibb.co/j9w1t5mg/image.webp" alt="Blason">

<table>
<tr><th>ID</th><th>Marque</th><th>RAM (Giga)</th><th>Actions</th>

<c:forEach items="${ordinateurs}" var="ordinateur">
<tr>
<td>${ordinateur.id }</td>
<td>${ordinateur.marque }</td>
<td>${ordinateur.ram }</td>

<td>
	<a href="ordinateur?id=${ordinateur.id }"><input type="button" value="Modifier"></a>
	<a href="ordinateur?id=${ordinateur.id }&delete"><input type="button" value="Supprimer"></a>
</td>
</tr>

</c:forEach>
</table>

<form method="POST" action="ordinateur">
	Marque : <input name="marque"><br>
	RAM (Giga): <input required type="number" name="ram"><br>
	<input type="submit" value="Ajouter">
</form>

</body>
</html>