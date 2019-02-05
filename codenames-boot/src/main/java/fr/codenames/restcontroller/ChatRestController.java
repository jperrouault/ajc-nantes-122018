package fr.codenames.restcontroller;

import java.util.List;

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

import fr.codenames.dao.IDAOMessage;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.model.Joueur;
import fr.codenames.model.Message;
import fr.codenames.projection.Views;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatRestController {
	@Autowired
	private IDAOMessage daoMessage;
	
	@Autowired
	private IDAOUtilisateur daoUtilisateur;

	@Autowired
	private Notifier notifier;
	
	private final String NOTIFIER_NAME = "chat";
	
	
	@GetMapping("/{id}")
	@JsonView(Views.Message.class)
	public List<Message> findById(@PathVariable int id) {
		return this.daoMessage.findAllByPartieGrilleId(id);
	}
	
	
	@PostMapping("/{id}")
	@JsonView(Views.Message.class)
	public Message save(@RequestBody Message message /* @AuthenticationPrincipal UtilisateurPrincipal user */) { //L'ARGUMENT EN COMMENTAIRE PERMETTRA DE RECUPERER L'USER CONNECTE
		this.daoMessage.save(message);
		message.setJoueur((Joueur)this.daoUtilisateur.findById(message.getJoueur().getId()).get());
		
		this.notifier.publish(this.NOTIFIER_NAME, message, Views.Message.class);
		return message;
	}
	
	
	

	
	/********************
	 * SERVER SENT EVENTS
	 */
	@GetMapping("/subscribe-events")
	public SseEmitter subscribeEvents() {
		return this.notifier.add(this.NOTIFIER_NAME);
	}
}