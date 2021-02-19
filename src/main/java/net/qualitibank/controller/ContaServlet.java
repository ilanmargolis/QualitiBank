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
import net.qualitibank.model.Operacao;
import net.qualitibank.model.Bonificada;
import net.qualitibank.model.Poupanca;

/**
 * Servlet implementation class ContaServlet
 */
@WebServlet("/conta")
public class ContaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContaServlet() {
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
		} else if (action.equalsIgnoreCase("nova")) {
			doGet_nova(request, response);
		} else if (action.equalsIgnoreCase("creditar")) {
			doGet_creditar(request, response);
		} else if (action.equalsIgnoreCase("debitar")) {
			doGet_debitar(request, response);
		} else if (action.equalsIgnoreCase("transferir")) {
			doGet_transferir(request, response);
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
		}
	}

	protected void doGet_index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet_listagem(request, response);
	}

	// abre formulário com as contas do cliente
	protected void doGet_listagem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
		List<Conta> contaList = ClienteDAO.getInstance().getContas(cliente_id);
		Cliente cliente = ClienteDAO.getInstance().getById(cliente_id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/cliente-contas.jsp");
		request.setAttribute("cliente", cliente);
		request.setAttribute("contas", contaList);
		dispatcher.forward(request, response);
	}
	
	// abre o formulário para inserir dados da nova conta
	protected void doGet_nova(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cliente_id = request.getParameter("cliente_id");
		Cliente cliente = ClienteDAO.getInstance().getById(cliente_id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/conta-form.jsp");
		request.setAttribute("cliente", cliente);
		dispatcher.forward(request, response);
	}

	// persiste a nova conta no banco de dados
	protected void doGet_inserir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		String cliente_id = request.getParameter("cliente_id");
		Cliente cliente = ClienteDAO.getInstance().getById(cliente_id);
		
		Conta conta = null;
		
		if (tipo.equals("Conta")) {
			conta = new Conta(numero, cliente);
		} else if (tipo.equals("Poupança")) {
			conta = new Poupanca(numero, cliente);
		} else if (tipo.equals("Bonificada")) {
			conta = new Bonificada(numero, cliente);
		}

		ContaDAO.getInstance().persist(conta);
		
//		response.sendRedirect(request.getContextPath());
		doGet_listagem(request, response);
	}

	protected void doGet_creditar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Conta conta = ContaDAO.getInstance().getById(id);
		Cliente cliente = conta.getCliente();
		Operacao operacao = Operacao.CREDITO;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/forms/conta-form.jsp");
		request.setAttribute("conta", conta);
		request.setAttribute("cliente", cliente);
		request.setAttribute("operacao", "operacao123");
		dispatcher.forward(request, response);
	}

	protected void doGet_debitar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		ClienteDAO.getInstance().removeById(id);

		response.sendRedirect(request.getContextPath());
	}

	protected void doGet_transferir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		ClienteDAO.getInstance().removeById(id);

		response.sendRedirect(request.getContextPath());
	}
}
