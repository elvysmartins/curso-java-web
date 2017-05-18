package br.com.cursojavaweb.teste;

import br.com.cursojavaweb.entidades.UsuarioBean;
import br.com.cursojavaweb.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		testeBuscarTodos();
		testeCadastrar();
		testeAlterar();
		testeExcluir();
		testeAutenticar();
		testeBuscarPorId();
	}

	private static void testeBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		java.util.List<UsuarioBean> listaResultado = usuarioDAO.buscarTodos();
		
		for (UsuarioBean usuario: listaResultado){
			System.out.println(usuario.getId()+ " " + usuario.getNome() + " " + usuario.getLogin() + " " + usuario.getSenha());
		}
	}

	private static void testeCadastrar() {
		UsuarioBean usuario = new UsuarioBean();
		
		usuario.setNome("Elvys Martins");
		usuario.setLogin("elvynho");
		usuario.setSenha("123");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.cadastrar(usuario);
	}

	private static void testeAlterar() {
		UsuarioBean usuario = new UsuarioBean();
		
		usuario.setNome("Elvys Martins");
		usuario.setLogin("elvynho");
		usuario.setSenha("123");
		usuario.setId(1);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.alterar(usuario);
	}

	private static void testeExcluir() {
		UsuarioBean usuario = new UsuarioBean();
		
		usuario.setId(1);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
	}

	private static void testeAutenticar () {
		UsuarioBean usuario = new UsuarioBean();
		usuario.setLogin("elvynho");
		usuario.setSenha("123");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
    	System.out.println(usuarioDAO.autenticar(usuario));
	}

	private static void testeBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioBean retorno = usuarioDAO.buscarPorId(2);
		if  (retorno != null){
			System.out.println(retorno);	
		}
		
	}
	
}
