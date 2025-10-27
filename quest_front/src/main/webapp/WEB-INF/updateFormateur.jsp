<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
    
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>Modifier Pro fait soeur</title>
 <link rel="stylesheet" href="style.css">
</head>
<body>

    <form action="formateur" method="post">

        <input type="hidden" id="id" name="id" value="${formateur.id}">

        <label for="login">Login :</label>
        <input type="text" id="login" name="login" placeholder="login" required value="${formateur.login}"><br><br>

        <label for="password">Password :</label>
        <input type="password" name="password" placeholder="password" required value="${formateur.password}"><br><br>
        
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" placeholder="nom" required value="${formateur.nom}"><br><br>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" placeholder="prenom" required value="${formateur.prenom}"><br><br>




        <p>Civilite</p>
        
        <div class="civilite">
        <c:forEach items="${civilites}" var="civilite">
        	<c:choose>
        		<c:when test="${formateur.civilite==civilite}">
      			  <input type="radio" id="${civilite}" name="civilite" value="${civilite}" checked>
          		  <label for="${civilite}">${civilite}</label>
      			</c:when>
      			<c:otherwise>
      			  <input type="radio" id="${civilite}" name="civilite" value="${civilite}" >
          		  <label for="${civilite}">${civilite}</label>
      			</c:otherwise>
      	
            </c:choose>
            </c:forEach>
        </div>
        <br><br>
        
        
        
      
        <div class="admin">
        
        <c:choose>
        		<c:when test="${formateur.admin==true}">
      			  <input type="checkbox" id="admin" name="admin" checked>
            
      			</c:when>
      			<c:otherwise>
      			  <input type="checkbox" id="admin" name="admin">
      			</c:otherwise>
      	
            </c:choose>
        
            <label for="admin">Admin</label><br><br>
        </div>
        
        
        
        
        <input type="submit" value="Modifier">
    </form> 

</body>
</html>