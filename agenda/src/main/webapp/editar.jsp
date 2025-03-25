<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar</title>
<link rel="icon"href "imagens/phone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>

	<h1>Editar contacto</h1>

	<form name="frmContacto" action="update">

		<table>

			<tr>
				<td><input type="text" name="idcon" placeholder="Id Contacto"
				  class="caixa"	id="caixaId" readonly="readonly" value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			
			<tr>
				<td><input type="text" name="nome" placeholder="Nome"
					class="caixa" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="fone" placeholder="fone"
					class="caixa" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="email" placeholder="email"
					class="caixa" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>

		<input type="button" value="Editar" class="botao1"
			onclick="validarContacto();">

	</form>

	<script src="scripts/validar.js"></script>

</body>


</html>