package fr.formation;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.formation.dao.IDAOClient;
import fr.formation.dao.IDAOFournisseur;
import fr.formation.dao.IDAOProduit;
import fr.formation.dao.jpa.DAOClientJPA;
import fr.formation.dao.jpa.DAOFournisseurJPA;
import fr.formation.dao.jpa.DAOProduitJPA;
import fr.formation.model.Achat;
import fr.formation.model.Client;
import fr.formation.model.Commande;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class Application {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurfyPU");
		IDAOProduit daoProduit = new DAOProduitJPA(emf);
		IDAOClient daoClient = new DAOClientJPA(emf);
		IDAOFournisseur daoFournisseur = new DAOFournisseurJPA(emf);
		
		
		//LISTE DES PRODUITS
		System.out.println("------------- LISTE DES PRODUITS -------------");
		for (Produit p : daoProduit.findAll()) {
			System.out.println(p.getModele());
		}
		
		//LISTE DES CLIENTS
		System.out.println("------------- LISTE DES CLIENTS -------------");
		for (Client c : daoClient.findAll()) {
			System.out.println(c.getNom());
		}
		
		//LISTE DES FOURNISSEURS
		System.out.println("------------- LISTE DES FOURNISSEURS -------------");
		for (Fournisseur f : daoFournisseur.findAll()) {
			System.out.println(f.getNom());
		}
		
		
		
		
		/* LES PRODUITS DU CLIENT #1 */
		System.out.println("------------- LES PRODUITS DU CLIENT #1 -------------");
		//SOLUTION #1 - ON PART DE LA DAO CLIENT
		for (Commande c : daoClient.findByIdWithProduitsAchetes(1).getCommandes()) {
			for (Achat a : c.getProduitsAchetes()) {
				System.out.println(a.getProduit().getModele());
			}
		}
		

		//SOLUTION #2 - ON PART DE LA DAO PRODUIT
		for (Produit p : daoProduit.findByClientId(1)) {
			System.out.println(p.getModele());
		}
		
		
		
		
		/* LES PRODUITS DU FOURNISSEUR #1 */
		System.out.println("------------- LES PRODUITS DU FOURNISSEUR #1 -------------");
		//SOLUTION #1 - ON PART DE LA DAO FOURNISSEUR
		for (Produit p : daoFournisseur.findByIdWithProduits(1).getProduits()) {
			System.out.println(p.getModele());
		}
		

		//SOLUTION #2 - ON PART DE LA DAO PRODUIT
		for (Produit p : daoProduit.findByFournisseurId(1)) {
			System.out.println(p.getModele());
		}
		
		emf.close();
	}
}
