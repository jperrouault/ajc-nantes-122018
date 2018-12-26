package fr.formation.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.formation.dao.IDAOFournisseur;
import fr.formation.model.Fournisseur;

public class DAOFournisseurJPA implements IDAOFournisseur {
	private EntityManager em;
	
	
	public DAOFournisseurJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Fournisseur> findAll() {
		return em
				.createQuery("select f from Fournisseur f", Fournisseur.class)
				.getResultList();
	}

	@Override
	public Fournisseur findById(int id) {
		return em.find(Fournisseur.class, id);
	}

	@Override
	public Fournisseur save(Fournisseur entity) {
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
	public void delete(Fournisseur entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Fournisseur myCommande = new Fournisseur();
		myCommande.setId(id);
		this.delete(myCommande);
	}


	@Override
	public Fournisseur findByIdWithProduits(int id) {
		return em
				.createQuery("select f from Fournisseur f left join fetch f.produits p where f.id = :id", Fournisseur.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}