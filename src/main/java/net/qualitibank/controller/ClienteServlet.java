package net.qualitibank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/cliente")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) {
			doGet_Index(request, response);
		} else {
			if (action.equalsIgnoreCase("novo")) {
				doGet_novo(request, response);
			} else if (action.equalsIgnoreCase("listagem")) {
				doGet_listagem(request, response);
			} else if (action.equalsIgnoreCase("editar")) {
				doGet_editar(request, response);
			} else if (action.equalsIgnoreCase("deletar")) {
				doGet_deletar(request, response);
			}
		}
	}	
	
	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/form-principal.jsp").forward(request, response);
	}
	
	protected void doGet_novo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", "Estou em novo cliente");
		request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}

	protected void doGet_listagem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", "Estou listando clientes");
		request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}	

	protected void doGet_editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", "Estou editando cliente");
		request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}	

	protected void doGet_deletar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", "Estou deletando cliente");
		request.getRequestDispatcher("/menu.jsp").forward(request, response);
	}	
}
