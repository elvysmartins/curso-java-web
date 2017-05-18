package br.com.cursojavaweb.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.cursojavaweb.entidades.UsuarioBean;
import br.com.cursojavaweb.jdbc.UsuarioDAO;

@WebServlet("/autenticadorControle.do")
public class autenticadorControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public autenticadorControle() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();

		if (sessao != null) {
			sessao.invalidate();
		}
		response.sendRedirect("login.html");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Chamando Método POST");
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		UsuarioBean usuario = new UsuarioBean();
		usuario.setLogin(login);
		usuario.setSenha(senha);		

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioBean usuRetorno =  usuarioDAO.autenticar(usuario);

		if (usuRetorno!=null) {
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("usulogado", usuRetorno);
			request.getRequestDispatcher("index.jsp").forward(request, response);				
		} else{
			response.sendRedirect("login.html");
		}		
	}
}