package fr.formation;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;

public class AppListeProduit {
	@Autowired
	private IDAOProduit daoProduit;
	
	
	@Transactional
	public void run(String[] args) {
		// LISTE DES PRODUITS
		System.out.println("------------- LISTE DES PRODUITS -------------");
		for (Produit p : this.daoProduit.findAll()) {
			System.out.println(p.getModele());
			System.out.println(p.getAchats());
		}
		
		
		
		
		//AJOUTER UN NOUVEAU PRODUIT
//		Produit p = new Produit();
//		Fournisseur f = new Fournisseur();
//		
//		f.setId(1);
//		
//		p.setModele("TEST JPA 42");
//		p.setPrix(10f);
//		p.setFournisseur(f);
//		
//		daoProduit.save(p);
	}
}
