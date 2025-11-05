<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="site-header">
    <c:if test="${connected!=null && connected.getClass().getSimpleName().equals('Formateur')}">
	    <nav class="menuItems" role="navigation" aria-label="Menu principal">
	      <ul>
	        <li><a href="matiere" data-item="Gestion matière">matière</a></li>
	        <li><a href="filiere" data-item="Gestion filière">filière</a></li>
	        <li><a href="ordinateur" data-item="Gestion ordinateur">ordinateur</a></li>
	        <li><a href="stagiaire" data-item="Gestion stagiaire">stagiaire</a></li>
	        <li><a href="formateur" data-item="Gestion formateur">formateur</a></li>
	      </ul>
	    </nav>
	</c:if>

  <div class="actions" role="region" aria-label="Actions utilisateur">
    <c:if test="${connected==null}">
      <a class="btn primary" href="home" id="btn-login">Se connecter</a>
    </c:if>

    <c:if test="${connected!=null}">
      <div>Welcome ${connected.login}</div>
      <a class="btn ghost" href="home/logout" id="btn-logout" >Se déconnecter</a>
    </c:if>
  </div>
</header>
