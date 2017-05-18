package br.com.cursojavaweb.jdbc;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static String status = "STATUS ---> Não conectado";
    
    public Conexao(){}
    
public static com.mysql.jdbc.Connection getConnection(){

		
		Connection conexao = null;

         try {
		    Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/conexaolocal";
			String usuario = "root";
			String senha = "1234";
			conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
			
	        if (conexao != null){
	        	System.out.println("Conexão OK!!!");
	            status = ("STATUS ---> Conectado com sucesso");
	         } else{
	            //JOptionPane.showMessageDialog(null, "Não foi possível realizar conexão!");  
	            status = ("STATUS ---> Não foi possível realizar conexão");
	         }		
	        
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar: " + e.getMessage());
			status = e.getMessage();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver não encontrado: " + e.getMessage());
			status = e.getMessage();
			e.printStackTrace();
		}
        
        return conexao;
        
	}
}
