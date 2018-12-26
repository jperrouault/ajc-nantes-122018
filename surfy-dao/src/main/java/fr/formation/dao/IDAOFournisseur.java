package fr.formation.dao;

import fr.formation.model.Fournisseur;

public interface IDAOFournisseur extends IDAO<Fournisseur> {
	public Fournisseur findByIdWithProduits(int id);
}