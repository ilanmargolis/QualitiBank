package net.qualitibank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qualitibank.dao.ClienteDAO;
import net.qualitibank.model.Cliente;

/**
 * Servlet implementation class ClienteServlet
 */
@javax.servlet.annotation.WebServlet({ "/", "/cliente" })
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

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
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if (action == null) {
			doGet_index(request, response);
		} else if (action.equalsIgnoreCase("inserir")) {
			doGet_inserir(request, response);
		} else if (action.equalsIgnoreCase("alterar")) {
			doGet_alterar(request, response);
		}
	}

	protected void doGet_index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet_listagem(request, response);
	}

	protected void doGet_listagem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Cliente> clienteList = ClienteDAO.getInstance().getAll();
		request.setAttribute("clientes", clienteList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/cliente-list.jsp");
		dispatcher.forward(request, response);
	}

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

	protected void doGet_inserir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		Cliente cliente = new Cliente(nome, email);

		ClienteDAO.getInstance().persist(cliente);
		
		response.sendRedirect(request.getContextPath());
	}

	protected void doGet_alterar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		Cliente cliente = new Cliente(id, nome, email);
		ClienteDAO.getInstance().merge(cliente);

		response.sendRedirect(request.getContextPath());
	}

	protected void doGet_deletar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		ClienteDAO.getInstance().removeById(id);

		response.sendRedirect(request.getContextPath());
	}
}
