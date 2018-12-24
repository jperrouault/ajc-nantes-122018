package fr.formation.dao.jpa;

import java.util.List;

import fr.formation.dao.IDAOClient;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Client;
import fr.formation.model.Commande;

public class DAOClientJPA extends DAOJPA implements IDAOClient {

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from client c", Client.class)
				.getResultList();
	}

	@Override
	public Client findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Client.class, id);
	}

	@Override
	public Client save(Client entity) {
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
	public void delete(Client entity) {
		// TODO Auto-generated method stub
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Client myClient = new Client();
		myClient.setId(id);
		this.delete(myClient);
	}

}
