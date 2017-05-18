package br.com.cursojavaweb.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;

import br.com.cursojavaweb.entidades.UsuarioBean;

public class UsuarioDAO {
	
	private Connection conexao = Conexao.getConnection();
	
	public void cadastrar(UsuarioBean usuario){
		
		//System.out.println("Entrou cadastrar - UsuarioDAO");
		
		String sql = "Insert into curso.usuario (nome, login, senha) values (?, ?, ?)";
		try {
			//System.out.println("Entrou try - UsuarioDAO");
			//System.out.println("nome: " + usuario.getNome());
			//System.out.println("login: " + usuario.getLogin());
			//System.out.println("senha: " + usuario.getSenha());
			java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
            preparador.setString(2, usuario.getLogin());
            preparador.setString(3, usuario.getSenha());
            
            preparador.execute();
            preparador.close();
            
            System.out.println("Cadastrado com sucesso!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterar(UsuarioBean usuario){
			
			String sql = "Update curso.usuario set nome=?, login=?, senha=? where id=?";

			try {
				java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
	            preparador.setString(1, usuario.getNome());
	            preparador.setString(2, usuario.getLogin());
	            preparador.setString(3, usuario.getSenha());
	            preparador.setInt(4, usuario.getId());
	            
	            preparador.execute();
	            preparador.close();
	            
	            System.out.println("Alterado com sucesso!!!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void salvar(UsuarioBean usuario) {
		
		if (usuario.getId()!=null && usuario.getId()!=0 ){
			alterar(usuario);
		}else{
			cadastrar(usuario);
		}
	}

	public void excluir(UsuarioBean usuario){
		
		String sql = "Delete from curso.usuario where id=?";

		try {
			java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setInt(1, usuario.getId());
            
            preparador.execute();
            preparador.close();
            
            System.out.println("Excluído com sucesso!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public List<UsuarioBean> buscarTodos(){
		
		String sql = "Select * from curso.usuario order by id";
		List<UsuarioBean> lista = new ArrayList<UsuarioBean>();

		try {
			java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = (ResultSet) preparador.executeQuery();
			
			while (resultado.next()){
				UsuarioBean usuario = new UsuarioBean();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				lista.add(usuario);
			}
			
            preparador.close();
            
            System.out.println("Consulta todos com sucesso!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	
	}

	public UsuarioBean buscarPorId (Integer id){
		
		String sql = "Select * from curso.usuario where id=?";
		UsuarioBean usuario = null;

		try {
			java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setInt(1, id);
            
            ResultSet resultado = (ResultSet) preparador.executeQuery();

            if (resultado.next()){
            	usuario = new UsuarioBean();
    			usuario.setId(resultado.getInt("id"));
    			usuario.setNome(resultado.getString("nome"));
    			usuario.setLogin(resultado.getString("login"));
    			usuario.setSenha(resultado.getString("senha"));            
                System.out.println("Consulta por id com sucesso!!!");
            } else{
            	System.out.println("Id não encontrado!!!");
            }
            
            preparador.close();

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return usuario;
        
	}

	public List<UsuarioBean> buscarPorNome (String nome){
		
		String sql = "Select * from curso.usuario where nome like ?";
		List<UsuarioBean> lista = new ArrayList<UsuarioBean>();

		try {
			java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setString(1, "%" + nome + "%");
            
            ResultSet resultado = (ResultSet) preparador.executeQuery();

            while (resultado.next()){
            	UsuarioBean usuario = new UsuarioBean();
    			usuario.setId(resultado.getInt("id"));
    			usuario.setNome(resultado.getString("nome"));
    			usuario.setLogin(resultado.getString("login"));
    			usuario.setSenha(resultado.getString("senha"));
    			lista.add(usuario);
            }
            preparador.close();
            System.out.println("Consulta por Nome com sucesso!!!");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return lista;
        
	}

	/**
	 * Busca login e senha do usuário
	 * @param usuario - Objeto com login e senha a ser consultado
	 * @return null quando não encontra no banco ou um ponteiro a um usuário quando encontra
	 */
	public UsuarioBean autenticar (UsuarioBean usuario){
		
		String sql = "Select * from curso.usuario where login=? and senha =?";
		UsuarioBean usuarioRetorno = null;

		try {
			java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setString(1, usuario.getLogin());
            preparador.setString(2, usuario.getSenha());
            ResultSet resultado = (ResultSet) preparador.executeQuery();

            if (resultado.next()){
            	usuarioRetorno = new UsuarioBean();
            	usuarioRetorno.setId(resultado.getInt("id"));
            	usuarioRetorno.setNome(resultado.getString("nome"));
            	usuarioRetorno.setLogin(resultado.getString("login"));
            	usuarioRetorno.setSenha(resultado.getString("senha"));            
                System.out.println("Autenticação com sucesso!!!");
            } else{
            	System.out.println("Usuário não encontrado!!!");
            }
            
            preparador.close();

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return usuarioRetorno;
        
	}

	public Boolean existeUsuario (UsuarioBean usuario){
		
		String sql = "Select * from curso.usuario where login=? and senha =?";
		boolean ret = false;

		try {
			java.sql.PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setString(1, usuario.getLogin());
            preparador.setString(2, usuario.getSenha());
            ResultSet resultado = (ResultSet) preparador.executeQuery();
            ret = resultado.next();
            preparador.close();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return ret;
        
	}
	
}
