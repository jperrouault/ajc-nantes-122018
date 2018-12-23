package fr.formation;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.formation.dao.IDAOProduit;
import fr.formation.dao.jpa.DAOProduitJPA;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class Application {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurfyPU");
		IDAOProduit daoProduit = new DAOProduitJPA(emf);
		
		
		for (Produit p : daoProduit.findAll()) {
			System.out.println(p.getModele());
		}
		
//		Produit p = new Produit();
//		Fournisseur f = new Fournisseur();
//		
//		f.setId(1);
//		
//		p.setId(1);
//		p.setModele("TEST JPA 2");
//		p.setPrix(10f);
//		p.setFournisseur(f);
//		
//		daoProduit.save(p);

		emf.close();
	}
}
