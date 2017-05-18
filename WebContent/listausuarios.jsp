<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de usuários com JSTL</title>

<script type="text/javascript">

function confirmaExclusao(id){
	
	if (window.confirm("Tem certeza que deseja excluir o registro: " + id + "?")){
		location.href ="usuarioControle.do?acao=exc&id=" + id;	
	}
	
	
}

</script>

</head>
<body>

	<c:import url="includes/menu.jsp"></c:import>

	<table border='1'> 
	
   		<tr bgcolor="#CCCCCC"> 
   			<th> Id </th> <th> Nome </th> <th> Login </th> <th> Senha </th> <th> Ação </th>
   		</tr>	

<c:forEach items="${requestScope.lista}" var="usu">

     	<tr> 
     		<td> ${usu.id}    </td> 
     		<td> ${usu.nome}  </td>
     		<td> ${usu.login} </td> 
     		<td> ${usu.senha} </td>
     		<td> 
     			<a href ="javascript:confirmaExclusao(${usu.id})"> Excluir </a> 
				|
     			<a href ="usuarioControle.do?acao=alt&id=${usu.id}"> Alterar </a> 
     		</td>
		</tr>	

</c:forEach>

	</table>

</body>
</html>