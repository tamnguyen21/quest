<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:general title="Gestion des matières">
	<jsp:attribute name="customStyle">
		tr {
			text-align: center;
		}

		#fond {
			background-image: url('assets/images/tpt.png');
			background-position: center;
		}
	</jsp:attribute>

	<jsp:attribute name="customScript">
		<script>
			filterByLib.onkeyup=function(event)
			{
				$.ajax("matiere/filter", {
					type: "GET",
					data: {
					searchLike: $("#filterByLib").val()
					},
					success: function (resp) {
					$('#tbody').html(resp);
					}
				});
			}
		</script>
	</jsp:attribute>

	<jsp:body>
		<input type="text" placeholder="Filtrer par libelle" id="filterByLib" />

		<table>
			<tr>
				<th>ID</th>
				<th>Libellé</th>
				<th>Actions</th>
			</tr>

			<tbody id="tbody">
				<c:forEach items="${matieres}" var="matiere">
					<tr>
						<td>${matiere.id}</td>
						<td>${matiere.libelle}</td>

						<td>
							<a href="matiere/${matiere.id}"><input type="button" value="Modifier"></a>
							<a href="matiere/delete/${matiere.id}"><input type="button" value="Supprimer"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form:form modelAttribute="matiere" method="POST" action="matiere">
			Libelle : <form:input required="required" type="text" path="libelle" placeholder="Saisir libellé"/><br>
			<input type="submit" value="Ajouter">
		</form:form>
	</jsp:body>
</t:general>
