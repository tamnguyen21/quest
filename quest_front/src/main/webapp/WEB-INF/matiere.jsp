<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<base href="/quest_front/">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<style>
tr {
	text-align: center;
}

#fond {
	background-image: url('assets/images/tpt.png');
	background-position: center;
}
</style>

<head>
<meta charset="UTF-8">

<title>Page Matiere</title>
</head>
<body id="fond">

	<h1>Gestion des matières</h1>

	<table>
		<tr>
			<th>ID</th>
			<th>Libellé</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${matieres}" var="matiere">

			<tr>
				<td>${matiere.id}</td>
				<td>${matiere.libelle}</td>

				<td><a href="matiere?id=${matiere.id}"><input type="button"
						value="Modifier"></a> <a
					href="matiere?id=${matiere.id}&delete"><input type="button"
						value="Supprimer"></a></td>
			</tr>
		</c:forEach>
	</table>

	<form method="POST" action="matiere">
		Libelle : <input required type="text" name="libelle"><br>
		<input type="submit" value="Ajouter">
	</form>
</body>

</html>