<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:general title="Quest">
	<jsp:body>
        <h2>Vous etes sur l'espace Stagiaire</h2>

        <p>Site en cours de construction</p>

        <form action="stagiaire/changeConnect" method="POST">
            <input type="text" name="login" placeholder="saisir votre login"><br>
            <input type="password" name="password" placeholder="saisir votre nouveau password">
            <input type="submit" value="Envoyer">
        </form>
	</jsp:body>
</t:general>
