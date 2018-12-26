package fr.formation.dao;


import fr.formation.model.Client;

public interface IDAOClient extends IDAO<Client> {
	public Client findByIdWithProduitsAchetes(int id);
}