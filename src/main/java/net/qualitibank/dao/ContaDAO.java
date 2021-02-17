package net.qualitibank.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.qualitibank.model.Conta;

@Stateless
public class ContaDAO {

	protected EntityManager entityManager;
	private static ContaDAO instance;

	// padrão de projeto Singleton
	public static ContaDAO getInstance() {
		if (instance == null) {
			instance = new ContaDAO();
		}

		return instance;
	}

	private ContaDAO() {
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

	public Conta getById(final int id) {
		return entityManager.find(Conta.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Conta> getContasCliente(final int cliente_id) {
		return entityManager.createQuery("FROM " + Conta.class.getName() + " WHERE cliente_id = :cliente_id")
				.setParameter("cliente_id", cliente_id)
				.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Conta> getAll() {
		return entityManager.createQuery("FROM " + Conta.class.getName()).getResultList();
	}

	public void persist(Conta conta) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(conta);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Conta conta) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(conta);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Conta conta) {
		try {
			entityManager.getTransaction().begin();
			conta = entityManager.find(Conta.class, conta.getId());
			entityManager.remove(conta);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Conta conta = getById(id);
			remove(conta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}