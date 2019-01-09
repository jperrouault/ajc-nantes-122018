package fr.formation.dao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;


@Repository
public class DAOProduitRepository implements IDAOProduit {
	@PersistenceContext
	private EntityManager em;
	
	
	
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
	@Transactional
	public Produit save(Produit entity) {
		if (entity.getId() == 0) {
			em.persist(entity);
		}
		
		else {
			entity = em.merge(entity);
		}
		
		return entity;
	}

	@Override
	@Transactional
	public void delete(Produit entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(int id) {
		Produit myProduit = new Produit();
		myProduit.setId(id);
		this.delete(myProduit);
	}


	@Override
	public List<Produit> findByClientId(int id) {
		return em
				.createQuery("select p from Produit p left join p.achats a where a.commande.client.id = :id", Produit.class)
				.setParameter("id", id)
				.getResultList();
	}


	@Override
	public List<Produit> findByFournisseurId(int id) {
		return em
				.createQuery("select p from Produit p where p.fournisseur.id = :id", Produit.class)
				.setParameter("id", id)
				.getResultList();
	}
}