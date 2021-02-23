package net.qualitibank.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.qualitibank.model.Cliente;

@Stateless
public class ClienteDAO {

	protected EntityManager entityManager;
	private static ClienteDAO instance;

	// padrão de projeto Singleton
	public static ClienteDAO getInstance() {
		if (instance == null) {
			instance = new ClienteDAO();
		}

		return instance;
	}

	private ClienteDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		// pega as configurações do arquivo persistence.xml
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("qualitibankPU");
		if (entityManager == null) {
			// responsável por realizar as operações de CRUD no banco de dados
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	// ***** CRUD *****
	
	public Cliente getById(final int id) {
		return entityManager.find(Cliente.class, id);
	}
	
	// Sobrecarga do método, pois no ContaServlet recebo um parâmetro String
	public Cliente getById(final String id) {
		return getById(Integer.parseInt(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getAll() {
		return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
	}

	public void persist(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			cliente = entityManager.find(Cliente.class, cliente.getId());
			entityManager.remove(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Cliente cliente = getById(id);
			remove(cliente);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}