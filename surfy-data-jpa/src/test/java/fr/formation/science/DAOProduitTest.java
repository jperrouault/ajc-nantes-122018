package fr.formation.science;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.formation.config.JpaConfig;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JpaConfig.class })
public class DAOProduitTest {
	@Autowired(required=false)
	private IDAOProduit daoProduit;
	
	
	@Test
	public void exists() {
		assertNotNull("la DAO n'existe pas !", daoProduit);
	}
	
	@Test
	public void getProduit() {
		Optional<Produit> myOptionalProduit = daoProduit.findById(1);
		
		assertTrue("Le produit n'a pas �t� trouv�", myOptionalProduit.isPresent());
		assertNotNull("Produit trouv� mais r�sultat nul", myOptionalProduit.get());
		assertNotNull("Produit trouv� mais infos non remont�es",
				myOptionalProduit.get().getModele());
	}
}
