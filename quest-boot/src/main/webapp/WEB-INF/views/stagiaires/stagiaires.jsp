<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>


<t:general title="Gestion des Stagiaires">
	<jsp:attribute name="customStyle">
		#gras {
			font-weight:bold;
		}
	</jsp:attribute>

	<jsp:body>
		<table>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Password</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Civilite</th>
				<th>Email</th>
				<th>Adresse</th>
				<th>Ordinateur</th>
				<th>Filiere</th>
				<th> action </th>
			</tr>

			<c:forEach items="${stagiaires}" var="stagiaire">
				<tr>
					<td>${stagiaire.id}</td>
					<td>${stagiaire.login}</td>
					<td>${stagiaire.password}</td>
					<td>${stagiaire.nom}</td>
					<td>${stagiaire.prenom}</td>
					<td>${stagiaire.civilite}</td>
					<td>${stagiaire.email}</td>
					<td>${stagiaire.adresse}</td>
					<td>${stagiaire.ordinateur.id} - ${stagiaire.ordinateur.marque}</td>
					<td>${stagiaire.filiere.id} - ${stagiaire.filiere.libelle}</td>

					<td>
						<a href="stagiaire/${stagiaire.id}"><input type="button" value="Modifier"></a>
						<a href="stagiaire/delete/${stagiaire.id}"><input type="button" value="Supprimer"></a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<fieldset>
			<form:form  modelAttribute="stagiaire" method="POST" action="stagiaire">
				<table>
					<tr>
						<td id="gras"> Login : </td>
						<td>
							<form:input type="text" path="login" required="required"/> <br>
						</td>
					</tr>

					<tr>
						<td id="gras">Password : </td>
						<td>
							<form:input type="password" path="password" required="required"/> <br>
						</td>
					</tr>

					<tr>
						<td id="gras"> Nom : </td>
						<td>
							<form:input type="text" path="nom" required="required"/> <br>
						</td>
					</tr>

					<tr>
						<td id="gras">Prenom : </td>
						<td>
							<form:input type="text" path="prenom" required="required"/> <br>
						</td>
					</tr>

					<tr>
						<td id="gras">Civilite : </td>
						<td>
							<form:select path="civilite">
								<form:options items="${civilites}"/>
							</form:select>
						</td>
					</tr>

					<tr>
						<td id="gras">Email : </td>
						<td>
							<form:input type="email" path="email" required="required"/> <br>
						</td>
					</tr>

					<tr>
						<td id="gras">Adresse : </td>
						<td>
							<form:input type="text" path="adresse.numero" required="required"/> <br>
						</td>

						<td>
							<form:input type="text" path="adresse.voie" required="required"/> <br>
						</td>

						<td>
							<form:input type="text" path="adresse.cp" required="required"/> <br>
						</td>

						<td>
							<form:input type="text" path="adresse.ville" required="required"/> <br>
						</td>
					</tr>

					<tr>
						<td id="gras"> Ordinateur : </td>
						<td>
							<form:select required="required" path="ordinateur.id">
								<form:option value="">Choisir Ordinateur</form:option>
								<form:options items="${ordinateurs}" itemValue="id" itemLabel="infos"/>
							</form:select>
						</td>
					</tr>

					<tr>
						<td id="gras"> Filiere : </td>
						<td>
							<form:select required="required" path="filiere.id">
								<form:option value="">Choisir une filiere</form:option>
								<form:options items="${filieres}" itemValue="id" itemLabel="infos"/>
							</form:select>
						</td>
					</tr>
				</table>

				<input type="submit" value="Ajouter">
			</form:form>
		</fieldset>
	</jsp:body>
</t:general>
