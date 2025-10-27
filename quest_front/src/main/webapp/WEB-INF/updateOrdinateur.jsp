<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update PC</title>
<style>
body {
	background: linear-gradient(45deg, #ff00ff, #00ffff, #ffff00, #ff8000);
	font-family: 'Comic sans MS';
	color: #ff00ff;
	background-size: 100vh 100vw;
	min-height: 100vh;
}

h1 {
	color: #ff00ff;
	text-align: center;
	font-size: 2.5em;
	margin: 0 auto 30px auto;
	background-color: darkgray;
	border: 5px solid black;
	text-shadow: 2px 2px 0px #000000;
	display: block;
	width: fit-content;
	padding : 20px;
}

form {
	background-color: rgba(251, 255, 0, 0.8);
	color : #000000;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	max-width: 400px;
	border : 3px solid black;
}

footer {
	color: #ffffff;
	background-color: salmon;
	border: 2px solid black;
	text-shadow: 2px 2px 0px #000000;
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	padding: 0 10px;
}
img {
	position: absolute; 
	top: 20px; right: 
	20px; border: 
	2px solid black;
	width: 150px;
}

</style>
</head>
<body>

<div>${ordinateur}</div>
<h1>Update de l'ordinateur ${ordinateur.id}</h1>

<img src="https://i.ibb.co/j9w1t5mg/image.webp" alt="Blason">

<form method="POST" action="ordinateur">
	<input type="hidden" name="id" value="${ordinateur.id}">
	Marque : <input name="marque" value="${ordinateur.marque}"><br>
	RAM (Giga): <input required type="number" name="ram" value="${ordinateur.ram}"><br>
	<input type="submit" value="Modifier">
</form>
</body>
</html>