package fr.loupgarou.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.loupgarou.dao.IDAOPouvoir;
import fr.loupgarou.model.Pouvoir;

public class DAOPouvoirJPA implements IDAOPouvoir {
	private EntityManager em;
	
	
	public DAOPouvoirJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Pouvoir> findAll() {
		return em
				.createQuery("select p from Pouvoir p", Pouvoir.class)
				.getResultList();
	}

	@Override
	public Pouvoir findById(int id) {
		return em.find(Pouvoir.class, id);
	}

	@Override
	public Pouvoir save(Pouvoir entity) {
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
	public void delete(Pouvoir entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}
	
	@Override
	public void deleteById(int id) {
		Pouvoir myPouvoir = new Pouvoir();
		myPouvoir.setId(id);
		this.delete(myPouvoir);
	}
}