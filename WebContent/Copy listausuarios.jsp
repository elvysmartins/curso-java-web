<%@page import="br.com.cursojavaweb.entidades.UsuarioBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de usuários</title>
</head>
<body>

	<table border='1'> 
	
   		<tr bgcolor="#CCCCCC"> 
   			<th> Id </th> <th> Nome </th> <th> Login </th> <th> Senha </th> <th> Ação </th>
   		</tr>	
 		
<%
//Scriptlet
List<UsuarioBean> lista = (List<UsuarioBean>) request.getAttribute("lista");

for (UsuarioBean usu: lista){
%>	
     	<tr> 
     		<td> <%= usu.getId() %> </td> 
     		<td> <% out.print(usu.getNome());  %> </td>
     		<td> <%= usu.getLogin() %> </td> 
     		<td> <%= usu.getSenha() %> </td>
     		<td> 
     			<a href ="usuarioControle.do?acao=exc&id=<%= usu.getId() %>"> Excluir </a> 
				|
     			<a href ="usuarioControle.do?acao=alt&id=<%= usu.getId() %>"> Alterar </a> 
     		</td>
		</tr>	
<% } %>

	</table>

</body>
</html>