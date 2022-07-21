<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favcon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1 style="text-align:center">agenda de contatos</h1>
	<a href="adicionar.html" class="button1">novo contato</a>
	
	<table id="tabela">
		<thead>
			<tr>
				<th>id</th>
				<th>nome</th>
				<th>fone</th>
				<th>e-mail</th>
				<th>op��es</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>" class=button2>editar</a></td>
				</tr>
			<% } %>
		</tbody>
	</table>

</body>
</html>