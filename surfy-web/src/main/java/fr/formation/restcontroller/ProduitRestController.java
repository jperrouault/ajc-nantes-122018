package fr.formation.restcontroller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;
import fr.formation.projection.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/produit")
public class ProduitRestController {
	@Autowired
	private IDAOProduit daoProduit;
	
	@GetMapping
	@JsonView(Views.Produit.class)
	public List<Produit> findAll() {
		return this.daoProduit.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ProduitWithAchatsAndFournisseur.class)
	@Transactional
	public Produit findById(@PathVariable int id) {
		Produit myProduit = this.daoProduit.findById(id).get();
		
		//ON DEMANDE A HIBERNATE DE RECUPERER LA LISTE DES ACHATS
		Hibernate.initialize(myProduit.getAchats());
		
		return myProduit;
	}
	
	
	@PostMapping
	@JsonView(Views.Produit.class)
	public Produit save(@RequestBody Produit produit) {
		this.daoProduit.save(produit);
		return produit;
	}
	
	
	@PutMapping("/{id}")
	@JsonView(Views.Produit.class)
	public Produit save(@PathVariable int id, @RequestBody Produit produit) {
		produit.setId(id);
		this.daoProduit.save(produit);
		return produit;
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		this.daoProduit.deleteById(id);
	}
}