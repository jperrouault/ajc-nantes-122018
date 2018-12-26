package fr.formation.dao;


import java.util.List;

import fr.formation.model.Produit;

public interface IDAOProduit extends IDAO<Produit> {
	public List<Produit> findByClientId(int id);
	public List<Produit> findByFournisseurId(int id);
}