package fr.formation.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;

public class DAOProduitJPA implements IDAOProduit {
	private EntityManager em;
	
	
	public DAOProduitJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Produit> findAll() {
		return em
				.createQuery("select p from Produit p", Produit.class)
				.getResultList();
	}

	@Override
	public Produit findById(int id) {
		return em.find(Produit.class, id);
	}

	@Override
	public Produit save(Produit entity) {
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
	public void delete(Produit entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(int id) {
		Produit myProduit = new Produit();
		myProduit.setId(id);
		this.delete(myProduit);
	}
}