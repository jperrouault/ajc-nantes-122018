package fr.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;

//@Service
public class ConsoleApplication implements CommandLineRunner {
	@Autowired
	private IDAOProduit daoProduit;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");
		
		System.out.println("-----------------------");
		
		for (Produit p : daoProduit.findAll()) {
			System.out.println(p.getModele());
		}
		
		System.out.println("-----------------------");
	}
}