package fr.formation.dao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;

@Repository
@Transactional
public class DAOProduitRepository implements IDAOProduit {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Produit> findAll() {
		return this.em
				.createQuery("select p from Produit p", Produit.class)
				.getResultList();

	}

	@Override
	public Produit findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit save(Produit entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Produit entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Produit> findByClientId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> findByFournisseurId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
