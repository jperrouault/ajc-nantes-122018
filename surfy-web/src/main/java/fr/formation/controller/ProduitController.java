package fr.formation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IDAOFournisseur;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;
import fr.formation.security.annotation.IsAdmin;

@Controller
@RequestMapping("/produit")
//@IsAdmin
public class ProduitController {
	@Autowired
	private IDAOProduit daoProduit;
	
	@Autowired
	private IDAOFournisseur daoFournisseur;
	
	
	
	@GetMapping
//	@PreAuthorize("isAuthenticated()")
	public String findAll(Model model) {
		model.addAttribute("produits", daoProduit.findAll());
		return "produits";
	}
	
	@GetMapping("/ajouter")
	@IsAdmin
	public String ajouter(Model model) {
		model.addAttribute("fournisseurs", daoFournisseur.findAll());
		return "form-editer";
	}
	
	@PostMapping("/ajouter")
	@IsAdmin
	public String ajouter(@Valid @ModelAttribute Produit produit, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("fournisseurs", daoFournisseur.findAll());
			return "form-editer";
		}
		
		daoProduit.save(produit);
		return "redirect:./";
	}
	
	
	@GetMapping("/supprimer")
	@IsAdmin
	public String supprimer(@RequestParam int id) {
		daoProduit.deleteById(id);
		return "redirect:./";
	}
	
	
	@GetMapping("/editer")
	@IsAdmin
	public String editer(@RequestParam int id, Model model) {
		model.addAttribute("fournisseurs", daoFournisseur.findAll());
		model.addAttribute("produit", daoProduit.findById(id).get());
		return "form-editer";
	}
	
	@PostMapping("/editer")
	@IsAdmin
	public String editer(@RequestParam int id, Model model, @Valid @ModelAttribute Produit produit, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("fournisseurs", daoFournisseur.findAll());
			return "form-editer";
		}
		
		daoProduit.save(produit);
		return "redirect:./";
	}
}