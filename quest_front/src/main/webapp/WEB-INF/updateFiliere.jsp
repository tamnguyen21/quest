<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<h1>Update de la filiere ${filiere.id}</h1>

<form method="POST" action="filiere">
	<input type="hidden" name="id" value="${filiere.id}">
	Nom de la formation : <input required type="text" name="libelle" value="${filiere.libelle}"><br>
	Date de debut : <input required type="date" name="debut" value="${filiere.debut}" id = "debutDate" onchange="updateFin()">
	<br>
	Date de fin : <input required type="date" name="fin" value="${filiere.fin}" min = "${filiere.debut}" id = "finDate">
	<br>  
	<input type="submit" value="Modifier">
	<a href="filiere" ><input type="button" value="Retour" class="retour"></a>
	
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