<style>
footer {
    display:grid;
    grid-template-columns: 10px 1fr 1fr 1fr 1fr 1fr 3fr 10px;
    color: #ffffff;
    background-color: salmon;
    border: 2px solid black;
    text-shadow: 2px 2px 0px #000000;
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    padding: 0 10px;
    font-family: 'Comic sans MS';
}

footer button{

}

footer button:nth-child(odd) {
    background: linear-gradient(45deg, #ff00ff, #ffff00);
    color: #000000;
    border: 3px solid #00ffff;
    padding: 10px 15px;
    margin: 5px;
    font-family: 'Comic sans MS';
    font-weight: bold;
    text-shadow: 1px 1px 0px #ffffff;
    box-shadow: 3px 3px 0px #ff8000;
    cursor: pointer;
}

footer button:nth-child(even) {
    background: linear-gradient(45deg, #00ffff, #ff8000);
    color: #ff00ff;
    border: 3px solid #ffff00;
    padding: 10px 15px;
    margin: 5px;
    font-family: 'Comic sans MS';
    font-weight: bold;
    text-shadow: 1px 1px 0px #000000;
    box-shadow: 3px 3px 0px #ff00ff;
    cursor: pointer;
}

footer button:hover {
    transform: scale(1.1);
    box-shadow: 5px 5px 0px #000000;
}

footer p{
    margin:auto;

}

</style>

<footer>
<div></div>
 <c:if test="${connected!=null && connected.getClass().getSimpleName().equals('Formateur')}">
	<button onclick="location.href='filiere'">Gestion des filieres</button>
	<button onclick="location.href='matiere'">Gestion des matieres</button>
	<button onclick="location.href='ordinateur'">Gestion des ordinateurs</button>
	<button onclick="location.href='formateur'">Gestion des formateurs</button>
	<button onclick="location.href='stagiaire'">Gestion des stagiaires</button>
</c:if>
<p>&copy; CSS 2025 La_Terreur. All rights reserved.</p>
<div></div>

</footer>