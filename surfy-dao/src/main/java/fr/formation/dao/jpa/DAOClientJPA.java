package fr.formation.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.formation.dao.IDAOClient;
import fr.formation.model.Client;

public class DAOClientJPA implements IDAOClient {
	private EntityManager em;
	
	
	public DAOClientJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Client> findAll() {
		return em
				.createQuery("select c from Client c", Client.class)
				.getResultList();
	}

	@Override
	public Client findById(int id) {
		return em.find(Client.class, id);
	}

	@Override
	public Client save(Client entity) {
		//On démarre la transaction
		em.getTransaction().begin();
		
		if (entity.getId() == 0) {
			em.persist(entity);
		}
		
		else {
			entity = em.merge(entity);
		}
		
		//On commit la transaction
		em.getTransaction().commit();
		
		return entity;
	}

	@Override
	public void delete(Client entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Client myCommande = new Client();
		myCommande.setId(id);
		this.delete(myCommande);
	}


	@Override
	public Client findByIdWithProduitsAchetes(int id) {
		return em
				.createQuery("select c from Client c left join fetch c.commandes cmd left join fetch cmd.produitsAchetes pa where c.id = :id", Client.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}