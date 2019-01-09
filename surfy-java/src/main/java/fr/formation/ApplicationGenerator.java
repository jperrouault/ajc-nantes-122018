package fr.formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.dao.IDAOCommande;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Achat;
import fr.formation.model.Client;
import fr.formation.model.Commande;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class ApplicationGenerator {
	@Autowired
	private IDAOProduit daoProduit;
	
	@Autowired
	private IDAOCommande daoCommande;
	
	
	public void run(String[] args) {
		//AJOUTER UNE NOUVELLE COMMANDE (BESOIN D'UN CLIENT, ET DES ACHATS)
		Commande c = new Commande();
		Client cli = new Client();
		Produit p1 = new Produit();
		Produit p2 = new Produit();
		
		
		//ASSOCIER CLIENT AVEC COMMANDE
		c.setClient(cli);
		
		//ASSOCIER LES ACHATS A LA COMMANDE
		c.ajouterProduit(p1, 1);
		c.ajouterProduit(p2, 1);
		
		//ON CHOISI LE CLIENT ET LES PRODUITS
		cli.setId(1); //CLIENT #1
		p1.setId(3); //PRODUIT #3
		p2.setId(1); //PRODUIT #1
		
		//PLUS QU'A SAUVEGARDER ...
		c.setDate(new Date());
		daoCommande.save(c);
		
		
		//AJOUTER UN NOUVEAU PRODUIT
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
