package fr.loupgarou.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.loupgarou.dao.IDAOPersonnage;
import fr.loupgarou.model.Personnage;

public class DAOPersonnageJPA implements IDAOPersonnage {
	private EntityManager em;
	
	
	public DAOPersonnageJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Personnage> findAll() {
		return em
				.createQuery("select p from Personnage p", Personnage.class)
				.getResultList();
	}

	@Override
	public Personnage findById(int id) {
		return em.find(Personnage.class, id);
	}

	@Override
	public Personnage save(Personnage entity) {
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
	public void delete(Personnage entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}
	
	@Override
	public void deleteById(int id) {
		Personnage myPersonnage = new Personnage();
		myPersonnage.setId(id);
		this.delete(myPersonnage);
	}
}