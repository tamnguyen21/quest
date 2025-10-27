<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</style>

<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<form method="POST" action="index.jsp">

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
						placeholder="Saisir votre mot de passe"
						pattern="^(?=.[A-Za-z])(?=.\d)[A-Za-z\d]{8,}$" name="password"></td>
				</tr>

			</table>
			<input type="submit" value="Se connecter">
		</fieldset>
	</form>
</body>
</html>