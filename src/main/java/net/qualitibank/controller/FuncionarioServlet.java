package net.qualitibank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qualitibank.dao.ClienteDAO;
import net.qualitibank.dao.ContaDAO;
import net.qualitibank.model.Cliente;
import net.qualitibank.model.Conta;

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet("/funcionario")
public class FuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FuncionarioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		try {
			if (action == null) {
				doGet_index(request, response);
			} else if (action.equalsIgnoreCase("listagem")) {
				doGet_listagem(request, response);
			} else if (action.equalsIgnoreCase("novo")) {
				doGet_novo(request, response);
			} else if (action.equalsIgnoreCase("editar")) {
				doGet_editar(request, response);
			} else if (action.equalsIgnoreCase("deletar")) {
				doGet_deletar(request, response);
			}
		} catch (Exception e) {
			throw new ServletException();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			doGet_index(request, response);
		} else if (action.equalsIgnoreCase("inserir")) {
			doPost_inserir(request, response);
		} else if (action.equalsIgnoreCase("alterar")) {
			doPost_alterar(request, response);
		}
	}

	protected void doGet_index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet_listagem(request, response);
	}

	protected void doGet_listagem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/funcionario.jsp");
		dispatcher.forward(request, response);
	}

	// SELEÇÃO DE ITENS DE MENU
	protected void doGet_novo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/cliente-form.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet_editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Cliente cliente = ClienteDAO.getInstance().getById(id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/cliente-form.jsp");
		request.setAttribute("cliente", cliente);
		dispatcher.forward(request, response);
	}

	protected void doGet_deletar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		ClienteDAO.getInstance().removeById(id);

		response.sendRedirect(request.getContextPath());
	}

	// PERSISTENCIA DE DADOS NO BANCO
	protected void doPost_inserir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		Cliente cliente = new Cliente(nome, email);

		ClienteDAO.getInstance().persist(cliente);

		response.sendRedirect(request.getContextPath());
	}

	protected void doPost_alterar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		List<Conta> contas = ContaDAO.getInstance().getContasCliente(id);
		Cliente cliente = new Cliente(id, nome, email, contas);

		ClienteDAO.getInstance().merge(cliente);

		response.sendRedirect(request.getContextPath());
	}
}
