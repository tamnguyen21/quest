 <base href="/quest_front/">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:opsz,wght@12..96,200..800&display=swap" rel="stylesheet">
  
  <style>
  	
.bricolage-grotesque {
  font-family: "Bricolage Grotesque", sans-serif;
  font-optical-sizing: auto;
  font-weight: 200;
  font-style: normal;
  font-variation-settings:
    "wdth" 100;
}
  
    * {
      padding: 0;
      margin: 0;
      box-sizing: border-box;
    }

    body {
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
      min-height: 100vh;
      font-family:"Bricolage Grotesque", sans-serif;
      background-color: #ffffff;
    }

    header.site-header {
     
     
      padding: 10px 20px;
      display: flex;
      align-items: center;
      justify-content: space-between;
    gap: 16px;
      width: 100%;
    }

    nav.menuItems {
      background: #ffffff;
      padding: 16px 24px;
      border-radius: 16px;
      box-shadow:  20px 20px 60px #e9e3e3,
             -20px -20px 60px #ffffff;
    
    }

    nav.menuItems ul {
      list-style: none;
      display: flex;
      gap: 40px;
    }

    nav.menuItems li a {
      text-decoration: none;
      color: #1C1A1A;
      font-size: 18px;
      font-weight: 500;
      text-transform: uppercase;
      position: relative;
      transition: all 0.5s ease-in-out;
      font-family: "Bricolage Grotesque", sans-serif;
    }

     nav.menuItems li a:hover {
      text-decoration: none;
      color: #FF4742;
      font-size: 18px;
      font-weight: bolder;
      text-transform: uppercase;
      position: relative;
      transition: all 0.2s ease-in-out;
      font-family: "Bricolage Grotesque", sans-serif;
   
    

    }

     nav.menuItems li a:focus {
      text-decoration: none;
      color: #ffffff;
      font-size: 18px;
      font-weight: bolder;
      text-transform: uppercase;
      position: relative;
      transition: all 0.2s ease-in-out;
      font-family: "Bricolage Grotesque", sans-serif;
        background-color: #FF4742;
     border-radius: 24px;
     padding: 4px 8px;

    

    }



   /* nav.menuItems li a::before {
      content: attr(data-item);
      position: absolute;
      top: 0;
      left: 0;
      width: 0;
      color: #8254ff;
      overflow: hidden;
      transition: width 0.5s ease-in-out;
    }

    nav.menuItems li a:hover::before {
      width: 100%;
    } */

    .actions {
      display: flex;
      gap: 12px;
    }

    .btn {
      background: #FF4742;
      border: 1px solid #FF4742;
      border-radius: 16px;
      box-shadow: rgba(0, 0, 0, 0.1) 1px 2px 4px;
      color: #FFFFFF;
      cursor: pointer;
      font-family:  "Bricolage Grotesque", sans-serif;
      font-size: 16px;
      font-weight: 800;
      
      padding: 16px 16px;
      text-align: center;
      text-transform: none;
      
      text-decoration: none;
    }

    .btn:hover {
    
      background-image: url("https://images.mariouniversalis.fr/cards/illus/334.jpg");
      background-size: 70% ;
      background-position: center;
      color: #1C1A1A;
      border :1px solid  #1C1A1A;
    }

    .btn:active {
      opacity: 0.5;
    }

  </style>
  
  
  
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
      	<a class="btn ghost" href="home?logout" id="btn-logout" >Se déconnecter</a>
      </c:if>
    </div>
   
  </header>
 