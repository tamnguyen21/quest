<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
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

<form:form modelAttribute="filiere" methode="POST" action="filiere">
	<form:input type="hidden" path="id"/>
	<form:input type="hidden" path="version"/>
	Nom de la formation : <form:input required="required" path="libelle"/><br>
	Date de debut : <form:input required="required" type="date" path="debut" id = "debutDate" onchange="updateFin()"/>
	<br>
	Date de fin : <form:input required="required" type="date" path="fin" min = "${filiere.debut}" id = "finDate"/>
	<br>  
	<input type="submit" value="Modifier">
	<a href="filiere" ><input type="button" value="Retour" class="retour"></a>
	
	</form:form>
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