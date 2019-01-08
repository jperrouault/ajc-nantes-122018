package fr.formation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import fr.formation.dao.IDAOProduit;

import fr.formation.model.Produit;

public class Application {
	
	@Autowired
	private ApplicationContext myContext;
	
	public void run(String[] args) {
		IDAOProduit myDao = myContext.getBean("DAOProduitRepository", IDAOProduit.class);
		
		//LISTE DES PRODUITS
		System.out.println("------------- LISTE DES PRODUITS -------------");
		for (Produit p : myDao.findAll()) {
			System.out.println(p.getModele());
		}
		
	}
}
