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
<base href="/">
</head>
<body>
	<form method="POST" action="process_login">

		<fieldset>
			<table>
				<tr>
					<td><label for="input-login">Login :</label></td>
					<td><input type="text" id="input-login"
						placeholder="Saisir votre login" name="username"></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><input type="password"
						placeholder="Saisir votre mot de passe" name="password"></td>
				</tr>

				<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" />

			</table>
			<input type="submit" value="Se connecter">

		</fieldset>
		<div id="error">${error}</div>
	</form>
</body>
</html>