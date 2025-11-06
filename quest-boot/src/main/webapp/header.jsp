<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<header class="site-header">
  <sec:authorize access="hasRole('FORMATEUR')">
    <nav class="menuItems" role="navigation" aria-label="Menu principal">
      <ul>
        <li><a href="matiere" data-item="Gestion matière">matière</a></li>
        <li><a href="filiere" data-item="Gestion filière">filière</a></li>
        <li><a href="ordinateur" data-item="Gestion ordinateur">ordinateur</a></li>
        <li><a href="stagiaire" data-item="Gestion stagiaire">stagiaire</a></li>
        <li><a href="formateur" data-item="Gestion formateur">formateur</a></li>
      </ul>
    </nav>
  </sec:authorize>

  <div class="actions" role="region" aria-label="Actions utilisateur">
    <sec:authorize access="!isAuthenticated()">
      <a class="btn primary" href="home" id="btn-login">Se connecter</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
      <div>Welcome <sec:authentication property="principal.username" /> !</div>
      <a class="btn ghost" href="logout" id="btn-logout" >Se déconnecter</a>
    </sec:authorize>
  </div>
</header>
