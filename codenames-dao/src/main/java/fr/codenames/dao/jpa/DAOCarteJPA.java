package fr.codenames.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;

public class DAOCarteJPA implements IDAOCarte {
	private EntityManager em;
	
	
	public DAOCarteJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Carte> findAll() {
		return em
				.createQuery("select c from Carte c", Carte.class)
				.getResultList();
	}

	@Override
	public Carte findById(int id) {
		return em.find(Carte.class, id);
	}

	@Override
	public Carte save(Carte entity) {
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
	public void delete(Carte entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}
	
	@Override
	public void deleteById(int id) {
		Carte myCarte = new Carte();
		myCarte.setId(id);
		this.delete(myCarte);
	}
}