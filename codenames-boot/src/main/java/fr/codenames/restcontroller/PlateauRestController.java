package fr.codenames.restcontroller;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOCase;
import fr.codenames.dao.IDAOGrille;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Carte;
import fr.codenames.model.Case;
import fr.codenames.model.Couleur;
import fr.codenames.model.Difficulte;
import fr.codenames.model.Grille;
import fr.codenames.model.Partie;
import fr.codenames.projection.Views;

@RestController
@RequestMapping("/api/plateau")
@CrossOrigin("*")
public class PlateauRestController {
	@Autowired
	private IDAOPartie daoPartie;
	
	@Autowired
	private IDAOGrille daoGrille;
	
	@Autowired
	private IDAOCarte daoCarte;
	
	@Autowired
	private IDAOCase daoCase;
	
	@Autowired
	private Notifier notifier;
	
	private final String NOTIFIER_NAME = "plateau";
	
	
	@GetMapping("/create")
	@JsonView(Views.Grille.class)
	public Grille create() {
		Partie myPartie = new Partie();
		Grille myGrille = new Grille();
		List<Carte> myCartes = this.daoCarte.findAll();
		List<Couleur> myCouleurs = null;
		
		
		myGrille.setDifficulte(Difficulte.FACILE);

		myCouleurs = myGrille.generate();
		Collections.shuffle(myCartes);
		Collections.shuffle(myCouleurs);
		
		myCartes = myCartes.subList(0, 25);
		
		for (Carte c : myCartes) {
			myGrille.addCarte(c, myCouleurs.remove(0));
		}
		
		
		this.daoGrille.save(myGrille);
		
		myPartie.setGrille(myGrille);
		this.daoPartie.save(myPartie);
		
		return myGrille;
	}
	
	
	@GetMapping("/{id}")
	@JsonView(Views.Plateau.class)
	@Transactional
	public Grille findById(@PathVariable int id) {
		Grille myGrille = this.daoGrille.findById(id).get();
		
		Hibernate.initialize(myGrille.getCases());
		
	    return myGrille;
	}
	
	
	@GetMapping("/{id}/espion")
	@JsonView(Views.PlateauEspion.class)
//	@PreAuthorize("hasRole('ESPION')")
	@Transactional
	public Grille findEspionById(@PathVariable int id) {
		return this.findById(id);
	}
	
	
	@PostMapping("/{id}")
	@JsonView(Views.CaseReponse.class)
	public Case selectCarte(@RequestBody Case selectedCase) {
		Case myCase = this.daoCase.findById(selectedCase.getId()).get();
		
		myCase.setRevelee(true);
		this.notifier.publish(this.NOTIFIER_NAME, myCase, Views.CaseReponse.class);
		
		return myCase;
	}
	
	
	

	
	/********************
	 * SERVER SENT EVENTS
	 */
	@GetMapping("/subscribe-events")
	public SseEmitter subscribeEvents() {
		return this.notifier.add(this.NOTIFIER_NAME);
	}
}