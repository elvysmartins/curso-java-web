package br.com.cursojavaweb.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cursojavaweb.entidades.UsuarioBean;
import br.com.cursojavaweb.jdbc.UsuarioDAO;

@WebServlet("/usuarioControle.do")
public class UsuarioControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsuarioControle() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Chamando Método GET");
		
		String acao = request.getParameter("acao");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (acao!=null && acao.equals("exc")){
			String id = request.getParameter("id");
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(Integer.parseInt(id));
			usuarioDAO.excluir(usuario);
			// redirecionar pelo browser
			response.sendRedirect("usuarioControle.do?acao=lis");
			//acao="lis";
		} 

		if (acao!=null && acao.equals("alt")){
			String id = request.getParameter("id");
			UsuarioBean usuario = usuarioDAO.buscarPorId(Integer.parseInt(id));
			// Engavetar no request a lista
			request.setAttribute("usuario", usuario);
			// Enviar ao JSP
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);			
		} 

		if (acao!=null && acao.equals("cad")){
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");			
			// Engavetar no request a lista
			request.setAttribute("usuario", usuario);
			// Enviar ao JSP
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);			
		} 
		
		if (acao!=null && acao.equals("lis")){
			//Obter a lista
			List<UsuarioBean> listaResultado = usuarioDAO.buscarTodos();

			// Engavetar no request a lista
			request.setAttribute("lista", listaResultado);
			
			// Enviar ao JSP
			RequestDispatcher saida = request.getRequestDispatcher("listausuarios.jsp");
			saida.forward(request, response);
			
			// HTML puro para montar a lista
			//String htmlSaida = "<html> <body> <table border='1'";
			//htmlSaida += " <tr> <td> Id </td> <td> Nome </td> <td> Login </td> <td> Senha </td>";
			//for (Usuario usuario: listaResultado){
			//	htmlSaida += " <tr> <td> " + usuario.getId()    + " </td> <td> " + usuario.getNome()  + " </td> " +
			//		   	           "<td> " + usuario.getLogin() + " </td> <td> "+  usuario.getSenha() + " </td> ";
			//} 
			//htmlSaida += "</table> </body> </html>";
			//PrintWriter saida = response.getWriter();
			//saida.print(htmlSaida);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Chamando Método POST");
		
		String id = request.getParameter("txtid");
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");

		UsuarioBean usuario = new UsuarioBean();
		
		if (id==null || id=="" || id=="0") {
		} else{
			usuario.setId(Integer.parseInt(id));
		}
		
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		PrintWriter saida = response.getWriter();
		saida.print("Usuario Salvo com sucesso!!!");
		
	}

}