<html>
<head>

<title>Fiche Fournisseur ${fournisseur.id}</title>
</head>
<body>
	<div id="content">

		<h3>Modifier Fournisseur ${fournisseur.id}</h3>
		<form action="fournisseur/${fournisseur.id}" method="post">
			<input type="hidden" name="id" value="${fournisseur.id}">
			<table>
				<tr>
					<td>Nom :</td>
					<td><input required name="nom" value="${fournisseur.nom}"
						type="text" placeholder="Saisir nom"></td>
				</tr>
				<tr>
					<td>Prenom :</td>
					<td><input required value="${fournisseur.prenom}" name="prenom"
						type="text" placeholder="Saisir prenom"></td>
				</tr>
				<tr>
					<td>Societe :</td>
					<td><input required value="${fournisseur.societe}" name="societe"
						type="text" placeholder="Saisir societe"></td>
				</tr>
			</table>



			<input class="btn btn-warning" type="submit" value="Modifier">
			<a href="fournisseur"><input type="button" class="btn btn-info"
				value="Retour"></a>
		</form>
	</div>
</body>
</html>
