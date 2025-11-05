<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modif Matiere</title>
</head>
<body>

<h1>Update de la matière ${matiere.id}</h1>

<form:form modelAttribute="matiere" method="POST" action="matiere/${matiere.id}">
	<form:hidden path="id"/>
		Libelle : <form:input required="required" type="text" path="libelle" placeholder="Saisir libellé"/><br>
	<input type="submit" value="Modifier">
	
</form:form>

</body>
</html>

