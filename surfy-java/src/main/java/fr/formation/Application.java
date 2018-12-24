package fr.formation;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.formation.dao.IDAOProduit;
import fr.formation.dao.jpa.DAOProduitJPA;
import fr.formation.model.Achat;
import fr.formation.model.Client;
import fr.formation.model.Commande;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class Application {
	public static void main(String[] args) {

//		Afficher produits
//		IDAOProduit daoProduit = new DAOProduitJPA();
//		
//		
//		for (Produit p : daoProduit.findAll()) {
//			System.out.println(p.getModele());
//		}
		

		Commande newCmd = new Commande();
		Achat newAch= new Achat();
		Produit newPro1 = new Produit();
		Produit newPro2 = new Produit();
		Client newCli=new Client();
		Fournisseur newFou= new Fournisseur();
		
		newCli.setNom("Liner");
		newCli.setPrenom("Dylan");
		newCli.setPassword("mdp");
		
		newPro1.setCategorie(1);
		newPro1.setDescription("surf en or");
		newPro1.setDisponibilites(1);
		newPro1.setFournisseur(newFou);
		newPro1.setModele("Golden Surf");
		newPro1.setPhoto("surf.jpeg");
		newPro1.setType(type);
		newPro1.setTaille(taille);
		newPro1.setPrix(prix);
		
		newPro2.
	
		newCmd.setClient(newCli);
		newAch.setProduit(newPro1);
		newAch.setProduit(newPro2);
		
//		Ajouter une commande. Besoin de 
		
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


	}
}
