package net.qualitibank.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.qualitibank.model.Funcionario;

@Stateless
public class FuncionarioDAO {

	protected EntityManager entityManager;
	private static FuncionarioDAO instance;

	// padrão de projeto Singleton
	public static FuncionarioDAO getInstance() {
		if (instance == null) {
			instance = new FuncionarioDAO();
		}

		return instance;
	}

	private FuncionarioDAO() {
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
	public Funcionario getById(final int id) {
		return entityManager.find(Funcionario.class, id);
	}
	
	// Sobrecarga do método, pois no ContaServlet recebo um parâmetro String
	public Funcionario getById(final String id) {
		return getById(Integer.parseInt(id));
	}
		
	public Funcionario getByEmail(final String email) {
		try {
			return (Funcionario) entityManager.createQuery("FROM " + Funcionario.class.getName() + " WHERE email = :email")
					.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> getAll() {
		return entityManager.createQuery("FROM " + Funcionario.class.getName()).getResultList();
	}

	public void persist(Funcionario funcionario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(funcionario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Funcionario funcionario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(funcionario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Funcionario funcionario) {
		try {
			entityManager.getTransaction().begin();
			funcionario = entityManager.find(Funcionario.class, funcionario.getId());
			entityManager.remove(funcionario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Funcionario funcionario = getById(id);
			remove(funcionario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}