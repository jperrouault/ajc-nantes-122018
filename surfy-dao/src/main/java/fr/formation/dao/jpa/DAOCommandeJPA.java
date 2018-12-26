package fr.formation.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.formation.dao.IDAOCommande;
import fr.formation.model.Commande;

public class DAOCommandeJPA implements IDAOCommande {
	private EntityManager em;
	
	
	public DAOCommandeJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Commande> findAll() {
		return em
				.createQuery("select c from Commande c", Commande.class)
				.getResultList();
	}

	@Override
	public Commande findById(int id) {
		return em.find(Commande.class, id);
	}

	@Override
	public Commande save(Commande entity) {
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
	public void delete(Commande entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Commande myCommande = new Commande();
		myCommande.setId(id);
		this.delete(myCommande);
	}
}