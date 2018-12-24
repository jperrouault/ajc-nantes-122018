package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IDAOAchat;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Achat;
import fr.formation.model.Client;

public class DAOAchatJPA extends DAOJPA implements IDAOAchat {

	@Override
	public List<Achat> findAll() {
		// TODO Auto-generated method stub
		return em
				.createQuery("select a from Achat a", Achat.class)
				.getResultList();
	}

	@Override
	public Achat findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Achat.class, id);
	}

	@Override
	public Achat save(Achat entity) {
		// TODO Auto-generated method stub
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
	public void delete(Achat entity) {
		// TODO Auto-generated method stub
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Achat myAchat = new Achat();
		myAchat.setId(id);
		this.delete(myAchat);
	}

}
