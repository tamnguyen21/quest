<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Filières</title>
<style>
body{
	text-align:center;
	font-family: cursive;
	background-color: #f2f2f2;

}
table{
width : 100%;
}
table, th, td {
border: 1px solid black;
border-collapse: collapse;
padding: 5px;
}
th {
background-color: #f2f2f2;
}
form {
margin-bottom: 20px;
}
.retour{
background-color: gray;
}
.supprimer{
background-color:red;
width:30%;
}
.modifier{
background-color:orange;
width:30%;
}
.ajouter{
background-color:yellow;
}

</style>
</head>


<body>


<h1>Gestion des filières</h1>

<a href="index.jsp" ><input type="button" value="Retour" class="retour"></a>
<table>
<tr>
<th>ID</th>
<th>Libellé</th>
<th>Début</th>
<th>Fin</th>
<th>Actions</th>
</tr>

<c:if test="${filieres.isEmpty()}">

	<tr><td colspan="5" align="center">Pas de filiere</td></tr>

</c:if>
<c:forEach items="${filieres}" var="filiere" >
<tr>
<td>${filiere.id}</td>
<td>${filiere.libelle}</td>
<td>${filiere.debut}</td>
<td>${filiere.fin}</td>
<td>
<a href="filiere?id=${filiere.id}">
<button class="modifier">Modifier</button>
</a>
<a href="filiere?id=${filiere.id}&delete=true" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette filière ?');">
<button class="supprimer">Supprimer</button>
</a>
</td>
</tr>
</c:forEach>
</table>







	<form method="POST" action="filiere">
	Nom de la formation : <input required type="text" name="libelle" placeholder = "nom de la formation"><br>
	Date de debut : <input required type="date" name="debut" onchange="updateFin()" id = "debutDate">
	<br>
	Date de fin : <input required type="date" name="fin" min = "${filiere.debut}" id = "finDate">
	<br>  
	<input type="submit" value="Ajouter" class="ajouter">
	</form>
</body>
</html>

<script>
function updateFin(){
	var debutInput = document.getElementById("debutDate");
	var finInput = document.getElementById("finDate");
	
	finInput.min = debutInput.value;
	finInput.value = "";
}

</script>