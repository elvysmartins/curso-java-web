<%@page import="br.com.cursojavaweb.entidades.UsuarioBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
</head>
<body>

<%
//Scriptlet
UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
%>	

	<form action="usuarioControle.do" method="post" >

		<label> ID: </label>
		<input type="text" readonly="readonly" name="txtid" value ="<%= usuario.getId() %>" size="20" />
		
		<label> Nome: </label>
		<input type="text" name="txtnome" value ="<% out.print(usuario.getNome()); %>" size="20" />
		
		<label> Login: </label> 
		<input type="text" name="txtlogin" value ="<%= usuario.getLogin() %>" size="20" />
		
		<label> Senha: </label>
		<input type="password" name="txtsenha" value ="<%= usuario.getSenha() %>" maxlength="6" />
		
		<input type="submit" value="Salvar" />

	</form>

</body>
</html>