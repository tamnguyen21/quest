<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<html>
<head>

<title>Fiche Produit ${produit.id}</title>
</head>
<body>
	<div id="content">

		<h3>Modifier Produit ${produit.id}</h3>
		<form:form modelAttribute="produit" action="produit/${produit.id}" method="post">
			<form:hidden path="id"/>
			<table>
				<tr>
					<td>Libelle :</td>
					<td><form:input required="required" path="libelle" placeholder="Saisir votre libelle"/>
					<form:errors class="errorForm" path="libelle"/>
					</td>
				</tr>
				<tr>
					<td>Prix :</td>
					<td><form:input required="required" path="prix" type="number" placeholder="Saisir prix" step="0.01"/>
					<form:errors class="errorForm" path="prix"/>
					</td>
				</tr>
				<tr>
					<td>Fournisseur :</td>
					<td>
					<!--<select required name="fournisseur.id">
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
					</select>
					-->
					<form:select required="required" path="fournisseur.id">
							<form:option value="">Choisir un fournisseur</form:option>
							<form:options items="${fournisseurs}"  itemValue="id" itemLabel="infosSelect"/>
					</form:select>
					</td>
				</tr>
			</table>



			<input class="btn btn-warning" type="submit" value="Modifier">
			<a href="produit"><input type="button" class="btn btn-info"
				value="Retour"></a>
		</form:form>
	</div>
</body>
</html>
