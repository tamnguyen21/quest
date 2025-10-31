<html>
<head>

<title>Fiche Produit ${produit.id}</title>
</head>
<body>
	<div id="content">

		<h3>Modifier Produit ${produit.id}</h3>
		<form action="produit/${produit.id}" method="post">
			<input type="hidden" name="id" value="${produit.id}">
			<table>
				<tr>
					<td>Libelle :</td>
					<td><input required name="libelle" value="${produit.libelle}"
						type="text" placeholder="Saisir votre libelle"></td>
				</tr>
				<tr>
					<td>Prix :</td>
					<td><input required value="${produit.prix}" name="prix"
						type="number" placeholder="Saisir prix" step="0.01"></td>
				</tr>
				<tr>
					<td>Fournisseur :</td>
					<td><select required name="fournisseur.id">
							<option value="">Choisir un fournisseur</option>
							<c:forEach items="${fournisseurs}" var="fournisseur">
								<c:choose>
									<c:when test="${produit.fournisseur.id==fournisseur.id}">
										<option selected value="${fournisseur.id}">${fournisseur.id} -
											${fournisseur.nom} ${fournisseur.prenom}</option>
									</c:when>
									<c:otherwise>
										<option value="${fournisseur.id}">${fournisseur.id} -
											${fournisseur.nom} ${fournisseur.prenom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
			</table>



			<input class="btn btn-warning" type="submit" value="Modifier">
			<a href="produit"><input type="button" class="btn btn-info"
				value="Retour"></a>
		</form>
	</div>
</body>
</html>
