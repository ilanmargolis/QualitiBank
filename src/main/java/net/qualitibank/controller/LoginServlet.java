package net.qualitibank.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qualitibank.dao.ClienteDAO;
import net.qualitibank.dao.FuncionarioDAO;
import net.qualitibank.model.Cliente;
import net.qualitibank.model.Funcionario;
import net.qualitibank.model.Usuario;
import net.qualitibank.util.Funcoes;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		List<Cliente> clientes = null; //ClienteDAO.getInstance().getAll();
		List<Funcionario> funcionarios = FuncionarioDAO.getInstance().getAll();
		
		if (clientes == null && funcionarios == null) {
			dispatcher = request.getRequestDispatcher("/cliente?action=novo");
		} else {
			dispatcher = request.getRequestDispatcher("/forms/login.jsp");			
		}
		
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao == null) {
			doGet(request, response);
		} else if (acao.equalsIgnoreCase("logar")) {
			try {
				doPost_logar(request, response);
			} catch (NoSuchAlgorithmException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost_logar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		String email = request.getParameter("email");
		String senha = Funcoes.crypto(request.getParameter("senha"));

		Usuario usuario = null;

		usuario = ClienteDAO.getInstance().getByEmail(email);
		if (usuario == null) {
			usuario = FuncionarioDAO.getInstance().getByEmail(email);

			// email não foi encontrado em nenhuma classe que extend de Usuario
			if (usuario == null) {
				throw new IOException("E-mail errado");
			}
		}

		// Senha confere
		if (senha.equals(usuario.getSenha())) {
			RequestDispatcher dispatcher = null;
		       
			// verifica o tipo de usuario e faz login na área correspondente
			if (usuario instanceof Cliente) {
				dispatcher = request.getRequestDispatcher("/cliente");
			} else if (usuario instanceof Funcionario) {
				dispatcher = request.getRequestDispatcher("/funcionario");				
			}
			
			dispatcher.forward(request, response);
		} else {
			throw new IOException("Senha errada " + senha + " = " + usuario.getSenha());			
		}
	}
}
