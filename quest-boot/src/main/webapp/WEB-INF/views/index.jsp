<!DOCTYPE html>
<html>

<style>
fieldset {
	display: flex;
	justify-content: center;
	align-items: center;
}

tr {
	text-align: center;
}

body {
	background-image: url('assets/images/tpt.png');
	background-position: center;
}

#error{color:red;}
</style>

<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<form method="POST" action="home">

		<fieldset>
			<table>
				<tr>
					<td><label for="input-login">Login :</label></td>
					<td><input type="text" id="input-login"
						placeholder="Saisir votre login" name="login"></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><input type="password"
						placeholder="Saisir votre mot de passe" name="password"></td>
				</tr>

			</table>
			<input type="submit" value="Se connecter">

		</fieldset>
		<div id="error">${error}</div>
	</form>
</body>
</html>