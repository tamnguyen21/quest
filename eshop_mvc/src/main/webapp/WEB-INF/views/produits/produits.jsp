<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des produits</title>
</head>
<div id="content">
  <h1>Liste des Produits</h1>
  <!-- <input id="btnAddProduit" type="button" class ="btn btn-success" value="Ajouter"> -->
  <a href="home"><input type="button" class ="btn btn-info" value="Retour"></a>

  <table class="table table-striped">
    <thead>
      <tr>
        <th>Id</th>
        <th>Libelle</th>
        <th>Prix</th>
        <th>Fournisseur</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${produits}" var="produit">
    
    
    <tr>
        <td>${produit.id}</td>
        <td>${produit.libelle}</td>
        <td>${produit.prix}</td>
         <c:choose>
	        <c:when test="${produit.fournisseur!=null}">
		       <td>${produit.fournisseur.id} - ${produit.fournisseur.prenom} ${produit.fournisseur.nom} </td>
	        </c:when>
	        <c:otherwise>
		        <td>/</td>
	        </c:otherwise>
        </c:choose>
        <td>
          <a href="produit/${produit.id}"><input type="button" class ="btn btn-warning" value="Modifier"></a>
          <a href="produit/delete/${produit.id}"><input type="button" class ="btn btn-danger" value="Supprimer"></a>
        </td>
      </tr>
    </c:forEach>
      
    </tbody>
  </table>





  <div id="addFormProduit" class="formAjout">
    <h3>Ajouter Produit</h3>
    <form:form modelAttribute="produitVide" action="produit" method="post">
      <table>
      <tr><td>Libelle : </td><td><form:input required="required" path="libelle" placeholder="Saisir votre libelle"/> </td></tr>
      <tr><td>Prix : </td><td><form:input required="required" path="prix" type="number" placeholder="Saisir prix" step="0.01"/> </td></tr>
      <tr><td>Fournisseur : </td><td>
      <form:select required="required" path="fournisseur.id">
      	<form:option value="">Choisir un fournisseur</form:option>
     	<form:options items="${fournisseurs}" itemValue="id" itemLabel="infosSelect"/>
      </form:select></td></tr>
      
      </table>


      <input class ="btn btn-success" type="submit" value="Ajouter">
    </form:form>
  </div>

</div>


</body>
</html>
<!--  <script>


  btnAddProduit.onclick=function()
  {
    addFormProduit.style.display="block";
  }
 
</script>-->