package fr.codenames.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Partie;

public class DAOPartieJPA implements IDAOPartie {
	private EntityManager em;
	
	
	public DAOPartieJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Partie> findAll() {
		return em
				.createQuery("select p from Partie p", Partie.class)
				.getResultList();
	}

	@Override
	public Partie findById(int id) {
		return em.find(Partie.class, id);
	}

	@Override
	public Partie save(Partie entity) {
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
	public void delete(Partie entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}
	
	@Override
	public void deleteById(int id) {
		Partie myPartie = new Partie();
		myPartie.setId(id);
		this.delete(myPartie);
	}
}