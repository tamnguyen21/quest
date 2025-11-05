<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:general title="Update de la matière ${ matiere.id }">
	<jsp:attribute name="customStyle">
		tr {
			text-align: center;
		}

		#fond {
			background-image: url('assets/images/tpt.png');
			background-position: center;
		}
	</jsp:attribute>

	<jsp:body>
		<form:form modelAttribute="matiere" method="POST" action="matiere/${ matiere.id }">
			<form:hidden path="id"/>
			Libelle : <form:input required="required" type="text" path="libelle" placeholder="Saisir libellé"/><br>
			<input type="submit" value="Modifier">
		</form:form>
	</jsp:body>
</t:general>