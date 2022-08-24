<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="images/favcon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1 style="text-align: center">editar contato</h1>
	<form name="frmContato" action="update">
		<table id="adicionarContato">
			<tr>
				<td><input type="text" name="idcon" id="static-box" readonly
					value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="box1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="box2"
					value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="box1"
					value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="salvar" class="button1"
			onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>