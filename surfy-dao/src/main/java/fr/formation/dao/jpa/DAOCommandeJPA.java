package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IDAOCommande;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Commande;
import fr.formation.model.Produit;

public class DAOCommandeJPA extends DAOJPA implements IDAOCommande {

	@Override
	public List<Commande> findAll() {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from Commande c", Commande.class)
				.getResultList();
		
	}

	@Override
	public Commande findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Commande.class, id);
	}

	@Override
	public Commande save(Commande entity) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Commande myCommande = new Commande();
		myCommande.setId(id);
		this.delete(myCommande);
	}

}
