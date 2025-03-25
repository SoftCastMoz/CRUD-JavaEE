<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contactos");//chamando dados de tabela contactos e colocar na lista
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon"href "imagens/phone.png">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="sweetAlert/sweetalert2.css">
<script type="text/javascript" src="sweetAlert/sweetalert2.all.js"></script>

<title>agenda de contato</title>
</head>
<body>

	<h1>Agenda de contacto</h1>
	<a href="novo.html" class="botao1">Novo contacto</a>
	<a href="reporter" class="botao2">Relatorio</a>

	<table id="tabela">
		<thead>
			<th>Id</th>
			<th>Nome</th>
			<th>Fone</th>
			<th>Email</th>
			<th>Opacoes</th>
		</thead>

		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>

			<tr>
				<td><%=lista.get(i).getIdcon()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>"
					class="botao1">Editar</a> <%--Envia id para folha javascript cvonfiramador para confirmacao de accao --%>
					<a href="javascript:confirmar2(<%=lista.get(i).getIdcon()%>)"
					class="botao2">Excluir</a></td>
			</tr>
			<%--encaminhar o idcon para o contacto que quer editar --%>

			<%
			}
			%>

		</tbody>

	</table>

	<script src="scripts/confirmador.js"></script>


</body>
</html>