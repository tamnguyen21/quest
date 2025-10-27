<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modif Matiere</title>
</head>
<body>

<h1>Update de la matière ${matiere.id}</h1>

<form method="POST" action="matiere">
	<input type="hidden" name="id" value="${matiere.id}">
	Libelle  : <input required type="text" name="libelle" value="${matiere.libelle}"><br>  
	<input type="submit" value="Modifier">
	</form>

</body>
</html>

