<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:general title="Gestion des formateurs" includeCss="style.css">
	<jsp:body>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Login</th>
					<th>Password</th>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Civilité</th>
					<th>Admin</th>
					<th>Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${formateurs}" var="formateur">
					<tr>
						<td>${formateur.id}</td>
						<td>${formateur.login}</td>
						<td>${formateur.password}</td>
						<td>${formateur.nom}</td>
						<td>${formateur.prenom}</td>
						<td>${formateur.civilite}</td>
						<c:choose>
							<c:when test="${formateur.admin==false}"> <td>non</td> </c:when>
							<c:otherwise><td>oui</td></c:otherwise>
						</c:choose>
						<td>
							<a href="formateur/${formateur.id}"><input type="button" value="Modifier"></a>
							<a href="formateur/delete/${formateur.id}"><input type="button" value="Supprimer"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<h1>Ajouter Formateur</h1>

		<form:form modelAttribute="formateur" action="formateur" method="post">
			<table>
				<tr>
					<td>Login :</td>
					<td><form:input path="login" placeholder="Saisir login" required="required" /><td>
				</tr>

				<tr>
					<td>Password :</td>
					<td><form:input path="password" placeholder="Saisir password" required="required" /><td>
				</tr>

				<tr>
					<td>Nom :</td>
					<td><form:input path="nom" placeholder="Saisir nom" required="required" /><td>
				</tr>
				<tr>
					<td>Prenom :</td>
					<td><form:input path="prenom" placeholder="Saisir prenom" required="required" /></td>
				</tr>
				<tr>
					<td>Civilite :</td>
					<td><form:radiobuttons path="civilite" items="${civilites}" required="required" /></td>
				</tr>
				<tr>
					<td>Administrateur :</td>
					<td><form:checkbox path="admin"/></td>
				</tr>
			</table>

			<input class="btn btn-success" type="submit" value="Ajouter">
		</form:form>

<!--
<table>
	<tr><th>ID</th><th>Login</th><th>Password</th><th>Nom</th><th>Prénom</th><th>Civilité</th><th>Admin</th><th>Actions</th></tr>
	<c:if test="${formateurs.isEmpty()}"><tr><td align="center" colspan="8">Aucun Formateur</td></tr></c:if>
	<c:forEach items="${formateurs}" var="formateur" >
	<tr>
		<td>${formateur.id}</td>
		<td>${formateur.login}</td>
		<td>${formateur.password}</td>
		<td>${formateur.nom}</td>
		<td>${formateur.prenom}</td>
		<td>${formateur.civilite}</td>

	    <c:choose>
			<c:when test="${formateur.admin==false}"> <td>non</td> </c:when>
			<c:otherwise><td>oui</td></c:otherwise>
		</c:choose>

		<td>
			<a href="formateur?id=${formateur.id}"><input type="button" value="Modifier"></a>
			<a href="formateur?id=${formateur.id}&delete"><input type="button" value="Supprimer"></a>
		</td>
	</tr>

	</c:forEach>
</table>

<form method="POST" action="formateur">
	Login : <input type="text" id="login" name="login" placeholder="login" required><br>
	Password : <input type="password" name="password" placeholder="password" required><br>
	Nom : <input type="text" id="nom" name="nom" placeholder="nom" required><br>
	Prénom : <input type="text" id="prenom" name="prenom" placeholder="prenom" required><br>
	Civilité :

	        <c:forEach items="${civilites}" var="civilite" varStatus="status">
      			  <input <c:if  test="${status.first}">checked</c:if> type="radio" id="${civilite}" name="civilite" value="${civilite}" >
          		  <label for="${civilite}">${civilite}</label>

            </c:forEach>



	<br>
	Admin : <input type="checkbox" id="admin" name="admin"> Admin
	<input type="submit" value="Ajouter">
</form>
-->
	</jsp:body>
</t:general>
