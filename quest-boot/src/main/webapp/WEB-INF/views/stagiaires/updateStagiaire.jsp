<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:general title="Modifier Stagiaire">
	<jsp:body>
		<form:form  modelAttribute="stagiaire" method="POST" action="stagiaire/${stagiaire.id}">
			<form:hidden path="id"/>

			Login : <form:input type="text" path="login" required="required" value="${stagiaire.login}"/> <br>
			Password : <form:input type="password" path="password" required="required" value="${stagiaire.password}"/> <br>
			Nom : <form:input type="text" path="nom" placeholder="nom" required="required" value="${stagiaire.nom}"/> <br>
			Prenom : <form:input type="text" path="prenom" placeholder="prenom" required="required" value="${stagiaire.prenom}"/> <br>

			Civilite :
			<form:select required="required" path="civilite">
				<form:option value="">Choisir genre</form:option>
				<form:options items="${civilites}"/>
			</form:select>

			<br>

			Email : <form:input type="email" path="email" placeholder="email" required="required" value="${stagiaire.email}"/> <br>

			Adresse :   <form:input type="text" path="adresse.numero"  placeholder="numero_rue" required="required" value="${stagiaire.adresse.numero}"/><br>
						<form:input type="text" path="adresse.voie" placeholder="rue" required="required" value="${stagiaire.adresse.voie}"/><br>
						<form:input type="text" path="adresse.cp" placeholder="cp" required="required" value="${stagiaire.adresse.cp}"/><br>
						<form:input type="text" path="adresse.ville" placeholder="ville" required="required" value="${stagiaire.adresse.ville}"/><br>

			Ordinateur:
			<form:select required="required" path="ordinateur.id">
				<form:option value="">Choisir ordinateur</form:option>
				<form:options items="${ordinateurs}" itemValue="id" itemLabel="infos"/>
			</form:select>

			<br>

			Filiere:
			<form:select required="required" path="filiere.id">
				<form:option value="">Choisir filiere</form:option>
				<form:options items="${filieres}" itemValue="id" itemLabel="infos"/>
			</form:select>

			<br>

			<input type="submit" value="Modifier">
		</form:form>
	</jsp:body>
</t:general>
