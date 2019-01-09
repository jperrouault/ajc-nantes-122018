package fr.formation;


import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class AppListeProduit {
	@Autowired
	private IDAOProduit daoProduit;

	public void run(String[] args) {
		// LISTE DES PRODUITS
		System.out.println("------------- LISTE DES PRODUITS -------------");
		for (Produit p : this.daoProduit.findAll()) {
			System.out.println(p.getModele());
		}
		
		
		//AJOUTER UN NOUVEAU PRODUIT
		Produit p = new Produit();
		Fournisseur f = new Fournisseur();
		
		f.setId(1);
		
		p.setModele("TEST JPA 42");
		p.setPrix(10f);
		p.setFournisseur(f);
		
		daoProduit.save(p);
	}
}
