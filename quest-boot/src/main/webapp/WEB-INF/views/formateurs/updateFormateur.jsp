<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:general title="Modifier Pro fait soeur ${ formateur.id }" includeCss="style.css">
	<jsp:body>
		<form:form modelAttribute="formateur" action="formateur/${formateur.id}" method="post">
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

        	<input type="submit" value="Modifier">
		</form:form>
	</jsp:body>
</t:general>